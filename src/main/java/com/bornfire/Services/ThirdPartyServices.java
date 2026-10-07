package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.BAMLThirdPartyTran;
import com.bornfire.entity.CMG_MASTER;
import com.bornfire.entity.Cust_Black_List_Ind_Entity;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.bornfire.entity.RefcodeEntity;
import com.bornfire.entity.ThirdPartyRepository;
import com.bornfire.entity.ThirdPartyResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@ConfigurationProperties("output")
@Transactional
public class ThirdPartyServices {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	EMAIL email;

	@Autowired
	EMAILREP emailRep;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	@Autowired
	AlertManagementRepository alertrep;

	@Autowired
	Environment env;

	private static final Logger logger = LoggerFactory.getLogger(ThirdPartyServices.class);

	public ThirdPartyResponse createThirdPartyPayment(BAMLThirdPartyTran bamlThirdPartyTran, String formmode,
			String PartyTranAmt, Date todate1, String NID, String payMode, String custId) throws ParseException {

		ThirdPartyResponse msg = new ThirdPartyResponse();

		Session hs = sessionFactory.getCurrentSession();
		// AlertManagementEntity ame = new AlertManagementEntity();

		if (formmode.equals("add")) {
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			BigDecimal EMAIL = (BigDecimal) hs.createNativeQuery("SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			BAMLThirdPartyTran up = bamlThirdPartyTran;

			long ThirdPartyCount = thirdPartyRepository.findThirdPartyCount(custId);
			long NormalLimit = thirdPartyRepository.findNormalLimit();
			long NormalLimitself = thirdPartyRepository.findNormalLimitself();
			long CreditLimitPerDay = thirdPartyRepository.findCreditLimitPerDay();
			long CreditLimitPerLast14Day = thirdPartyRepository.findCreditLimitPerLast14Day();
			String NIDcheck = thirdPartyRepository.findNIDBlackList(NID);

			String Fullname = up.getCust_full_name();
			String Shortname = up.getCust_short_name();
			int PEP = thirdPartyRepository.findNamePEPList(Fullname, Shortname);

			String Firstname = up.getCust_first_name();
			String Lastname = up.getCust_last_name();
			int UNSC_IND = thirdPartyRepository.findNameUNSCINDList(Firstname, Lastname);
			int UNSC_ENT = thirdPartyRepository.findNameUNSCENTList(Fullname);

			System.out.println("NIDcheck" + NIDcheck);
			System.out.println("NID" + NIDcheck);
			Calendar cal = Calendar.getInstance();
			cal.setTime(todate1);
			cal.add(Calendar.DATE, -14);
			Date prevPayDate = cal.getTime();
			String PartyTranPrevAmt = thirdPartyRepository.findPrevAmt(prevPayDate, todate1, NID, payMode);
			String PartyTranCurAmt = thirdPartyRepository.findPrevAmt(todate1, todate1, NID, payMode);

			String Tran_id = up.getTran_id();
			String Part_tran_id = up.getPart_tran_type();
			// Date Tran_Date = up.getPayment_date();
			int ExistTran = thirdPartyRepository.findPreTran(Tran_id, Part_tran_id, todate1);
			if (!(ExistTran > 0)) {

				if (up.getMode_of_payment().equals("Debit/Credit Card")) {

					if ((Double.parseDouble(up.getAmount_paid().toString()) > NormalLimitself)
							) {
						msg.setStatus("Daily amount limit exceeded");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Evidence Source of Fund Alert");
							EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ "
									+ up.getAcct_name() + "/ National ID: /" + up.getNid() + "/ Transaction ID: /"
									+ up.getTran_id() + "/ Amount Paid :/" + up.getAmount_paid()
									+ ":/ Transaction Date : /" + up.getPayment_date() + ":/ Transaction Type : /"
									+ up.getProd_type() + ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (Double.parseDouble(up.getAmount_paid().toString()) > CreditLimitPerDay) {
						msg.setStatus("Card daily limit reached");
						msg.setTranID("0");

						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Card Limit Alert");
							EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ "
									+ up.getAcct_name() + "/ National ID: /" + up.getNid() + "/ Transaction ID: /"
									+ up.getTran_id() + "/ Amount Paid :/" + up.getAmount_paid()
									+ ":/ Transaction Date : /" + up.getPayment_date() + ":/ Transaction Type : /"
									+ up.getProd_type() + ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
							// EA.setEmail_body("Customer Name "+up.getAcct_name()+ ":/ Transaction ID:
							// "+up.getTran_id() +":/ Amount Paid :"+up.getAmount_paid()+":/ Transaction
							// Type : "+up.getProd_type() +"/ Card daily limit reached");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} /*
						 * else if (Double.parseDouble(PartyTranCurAmt) +
						 * Double.parseDouble(up.getAmount_paid().toString()) > CreditLimitPerDay) {
						 * msg.setStatus("Card daily limit reached"); msg.setTranID("0");
						 * 
						 * EmailAlert EA = new EmailAlert(); String alertcode = "THIRD-PARTY-AMT";
						 * AlertManagementEntity AM = alertrep.getalertdetail(alertcode); if
						 * (AM.getEmail_flg().equals("Y")) { EA.setEmail_id(AM.getEmail_1());
						 * EA.setEmail_id_cc1(AM.getEmail_2()); EA.setEmail_id_cc2(AM.getEmail_3());
						 * EA.setEmail_sub("Card Limit Alert");
						 * EA.setEmail_body("Card daily limit reached"); EA.setEmail_date(new Date());
						 * EA.setEmail_srl_no(EMAIL); EA.setSend_flg("N"); emailRep.save(EA); } return
						 * msg; }
						 */ else if (Double.parseDouble(PartyTranPrevAmt)
							+ Double.parseDouble(up.getAmount_paid().toString()) > CreditLimitPerLast14Day) {
						msg.setStatus("Card daily limit reached for last 14 days");
						msg.setTranID("0");

						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Card Limit Alert");
							EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ "
									+ up.getAcct_name() + "/ National ID: /" + up.getNid() + "/ Transaction ID: /"
									+ up.getTran_id() + "/ Amount Paid :/" + up.getAmount_paid()
									+ ":/ Transaction Date : /" + up.getPayment_date() + ":/ Transaction Type : /"
									+ up.getProd_type() + ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
							// EA.setEmail_body("Customer Name "+up.getAcct_name()+ ":/ Transaction ID:
							// "+up.getTran_id() +":/ Amount Paid :"+up.getAmount_paid()+":/ Transaction
							// Type : "+up.getProd_type() +"/ Card daily limit reached for last 14 days");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if ((NIDcheck != null) && (NIDcheck.equals(NID))) {

						msg.setStatus("Black Listed");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Black Listed Customer Alert");
							EA.setEmail_body("Customer Name : " + up.getAcct_name() + "/ Transaction ID: "
									+ up.getTran_id() + ":/ Amount Paid :" + up.getAmount_paid()
									+ ":/ Transaction Type : " + up.getProd_type() + "/ Customer is Blacklisted");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (PEP > 0) {

						msg.setStatus("Customer found in PEP List");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("PEP List Customer Alert");
							EA.setEmail_body("Customer Name: " + up.getAcct_name() + "/ Transaction ID: "
									+ up.getTran_id() + "/ Amount Paid :" + up.getAmount_paid()
									+ "/ Transaction Type : " + up.getProd_type() + "/ Customer found in PEP List");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (UNSC_IND > 0) {

						msg.setStatus("Customer found in UNSC List");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("UNSC Listed Customer Alert");
							EA.setEmail_body("Customer Name: " + up.getAcct_name() + "/ Transaction ID: "
									+ up.getTran_id() + "/ Amount Paid :" + up.getAmount_paid()
									+ "/ Transaction Type : " + up.getProd_type() + "/ Customer found in UNSC List");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (UNSC_ENT > 0) {

						msg.setStatus("Customer found in UNSC List");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("UNSC Listed Customer Alert");
							EA.setEmail_body("Customer Name : " + up.getAcct_name() + "/ Transaction ID: "
									+ up.getTran_id() + "/ Amount Paid :" + up.getAmount_paid()
									+ "/ Transaction Type : " + up.getProd_type() + "/ Customer found in UNSC List");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else {
						up.setEntity_flg("N");
						up.setDel_flg("N");
						up.setNid(up.getNid());

						thirdPartyRepository.save(up);
						msg.setStatus("Added Successfully");

						BigDecimal Number = (BigDecimal) hs
								.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
								.getSingleResult();

						String modi = "Transaction ID : " + bamlThirdPartyTran.getTran_id() + " Customer Name "
								+ bamlThirdPartyTran.getAcct_name() + ": TRANSACTION ADDED";

						audit.setAudit_date(new Date());
						audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
						audit.setFunc_code("RECORD CREATED");
						audit.setRemarks("ADDED");
						audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
						audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
						audit.setEvent_id(bamlThirdPartyTran.getCust_id());
						audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "ADDED");
						audit.setModi_details(modi);
						audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
						audit.setEntry_time(bamlThirdPartyTran.getEntry_time());
						audit.setAudit_ref_no(Number.toString());
						auditLocal.save(audit);
						if (up.getTp_nid()!= null) {
							EmailAlert EA = new EmailAlert();
							String alertcode = "THIRD-PARTY-AMT";
							AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
							if (AM.getEmail_flg().equals("Y")) {
								EA.setEmail_id(AM.getEmail_1());
								EA.setEmail_id_cc1(AM.getEmail_2());
								EA.setEmail_id_cc2(AM.getEmail_3());
								EA.setEmail_body(
										"Customer Name : " + up.getAcct_name() + "/ Transaction ID: " + up.getTran_id()
												+ "/ Amount Paid :" + up.getAmount_paid() + "/ Transaction Type : "
												+ up.getProd_type() + "/  Third party transaction added successfully");
								EA.setEmail_date(new Date());
								EA.setEmail_srl_no(EMAIL);
								EA.setSend_flg("N");
								emailRep.save(EA);
							}
						} else {
							EmailAlert EA = new EmailAlert();

							String alertcode = "THIRD-PARTY-AMT";
							AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
							if (AM.getEmail_flg().equals("Y")) {
								EA.setEmail_id(AM.getEmail_1());
								EA.setEmail_id_cc1(AM.getEmail_2());
								EA.setEmail_id_cc2(AM.getEmail_3());
								EA.setEmail_body(
										"Customer Name : " + up.getAcct_name() + "/ Transaction ID: " + up.getTran_id()
												+ "/ Amount Paid :" + up.getAmount_paid() + "/ Transaction Type : "
												+ up.getProd_type() + "/  Transaction added successfully");
								EA.setEmail_date(new Date());
								EA.setEmail_srl_no(EMAIL);
								EA.setSend_flg("N");
								emailRep.save(EA);
							}
							return msg;
						}
					}
				} else {
					if ((Double.parseDouble(up.getAmount_paid().toString()) > NormalLimitself)
							) {
						msg.setStatus("Daily amount limit exceeded");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Evidence Source of Fund Alert");
							EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ "
									+ up.getAcct_name() + "/ National ID: /" + up.getNid() + "/ Transaction ID: /"
									+ up.getTran_id() + "/ Amount Paid :/" + up.getAmount_paid()
									+ ":/ Transaction Date : /" + up.getPayment_date() + ":/ Transaction Type : /"
									+ up.getProd_type() + ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					}
//					} else if (ThirdPartyCount > 3) {
//						System.out.println("count123:" + up.getCust_id());
//						msg.setStatus("Payment done by third party");
//						msg.setTranID("0");
//						EmailAlert EA = new EmailAlert();
//						String alertcode = "THIRD-PARTY-AMT";
//						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//						if (AM.getEmail_flg().equals("Y")) {
//							EA.setEmail_id(AM.getEmail_1());
//							EA.setEmail_id_cc1(AM.getEmail_2());
//							EA.setEmail_id_cc2(AM.getEmail_3());
//							EA.setEmail_sub("Third Party Payment Alert");
//							EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: "
//									+ up.getTran_id() + ":/ Amount Paid :" + up.getAmount_paid()
//									+ ":/ Transaction Type : " + up.getProd_type() + "/ Payment done by third party");
//							EA.setEmail_date(new Date());
//							EA.setEmail_srl_no(EMAIL);
//							EA.setSend_flg("N");
//							emailRep.save(EA);
//						}
//						return msg;
//					} 
					else if ((NIDcheck != null) && (NIDcheck.equals(NID))) {
						// System.out.println("count123:" + up.getCust_id());
						msg.setStatus("Black Listed");
						msg.setTranID("1");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Black Listed Customer Alert");
							EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: "
									+ up.getTran_id() + ":/ Amount Paid :" + up.getAmount_paid()
									+ ":/ Transaction Type : " + up.getProd_type() + "/ Customer is Blacklisted");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (PEP > 0) {

						msg.setStatus("Customer found in PEP List");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("PEP Listed Customer Alert");
							EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: "
									+ up.getTran_id() + ":/ Amount Paid :" + up.getAmount_paid()
									+ ":/ Transaction Type : " + up.getProd_type() + "/ Customer found in PEP List");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (UNSC_IND > 0) {

						msg.setStatus("Customer found in UNSC List");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("UNSC Listed Customer Alert ");
							EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: "
									+ up.getTran_id() + ":/ Amount Paid :" + up.getAmount_paid()
									+ ":/ Transaction Type : " + up.getProd_type() + "/ Customer found in UNSC List");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else if (UNSC_ENT > 0) {

						msg.setStatus("Customer found in UNSC List");
						msg.setTranID("0");
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("UNSC Listed Customer Alert");
							EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: "
									+ up.getTran_id() + ":/ Amount Paid :" + up.getAmount_paid()
									+ ":/ Transaction Type : " + up.getProd_type() + "/ Customer found in UNSC List");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						return msg;
					} else {
						up.setEntity_flg("N");
						up.setDel_flg("N");
						up.setNid(up.getNid());
						thirdPartyRepository.save(up);
						msg.setStatus("Added Successfully");

						BigDecimal Number = (BigDecimal) hs
								.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
								.getSingleResult();

						String modi = "TRANSACTION ADDED";

						audit.setAudit_date(new Date());
						audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
						audit.setFunc_code("RECORD CREATED");
						audit.setRemarks("ADDED");
						audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
						audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
						audit.setEvent_id(bamlThirdPartyTran.getCust_id());
						audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "ADDED");
						audit.setModi_details(modi);
						audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
						audit.setEntry_time(bamlThirdPartyTran.getEntry_time());
						audit.setAudit_ref_no(Number.toString());
						auditLocal.save(audit);
						if (up.getTp_nid()!= null) {
							EmailAlert EA = new EmailAlert();
							String alertcode = "THIRD-PARTY-AMT";
							AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
							if (AM.getEmail_flg().equals("Y")) {
								EA.setEmail_id(AM.getEmail_1());
								EA.setEmail_id_cc1(AM.getEmail_2());
								EA.setEmail_id_cc2(AM.getEmail_3());
								EA.setEmail_sub("THIRD PARTY TRANSACTION ADDED");
								EA.setEmail_body(
										"Customer Name : " + up.getAcct_name() + "/ Transaction ID: " + up.getTran_id()
												+ "/ Amount Paid :" + up.getAmount_paid() + "/ Transaction Type : "
												+ up.getProd_type() + "/Third Party Transaction Added Successfully");
								EA.setEmail_date(new Date());
								EA.setEmail_srl_no(EMAIL);
								EA.setSend_flg("N");
								emailRep.save(EA);
							}

						} else {
							EmailAlert EA = new EmailAlert();
							String alertcode = "THIRD-PARTY-AMT";
							AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
							if (AM.getEmail_flg().equals("Y")) {
								EA.setEmail_id(AM.getEmail_1());
								EA.setEmail_id_cc1(AM.getEmail_2());
								EA.setEmail_id_cc2(AM.getEmail_3());
								EA.setEmail_sub("TRANSACTION ADDED");
								EA.setEmail_body(
										"Customer Name : " + up.getAcct_name() + "/ Transaction ID: " + up.getTran_id()
												+ "/ Amount Paid :" + up.getAmount_paid() + "/ Transaction Type : "
												+ up.getProd_type() + "/ Transaction added successfully");
								EA.setEmail_date(new Date());
								EA.setEmail_srl_no(EMAIL);
								EA.setSend_flg("N");
								emailRep.save(EA);
							}
						}
						return msg;
					}

				}
			} else {
				msg.setStatus("Transaction Already Exist");

				return msg;
			}
		} else if (formmode.equals("proceed")) {
			BigDecimal EMAIL = (BigDecimal) hs.createNativeQuery("SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			BAMLThirdPartyTran up = bamlThirdPartyTran;

			long ThirdPartyCount = thirdPartyRepository.findThirdPartyCount(custId);
			long NormalLimit = thirdPartyRepository.findNormalLimit();
			long NormalLimitself = thirdPartyRepository.findNormalLimitself();
			String Fullname = up.getCust_full_name();
			String Shortname = up.getCust_short_name();
			int PEP = thirdPartyRepository.findNamePEPList(Fullname, Shortname);

			String Firstname = up.getCust_first_name();
			String Lastname = up.getCust_last_name();
			int UNSC_IND = thirdPartyRepository.findNameUNSCINDList(Firstname, Lastname);
			int UNSC_ENT = thirdPartyRepository.findNameUNSCENTList(Fullname);

			String NIDcheck = thirdPartyRepository.findNIDBlackList(NID);
//			if ((Double.parseDouble(up.getAmount_paid().toString()) > NormalLimitself)) {
//				msg.setStatus("Daily amount limit exceeded");
//				msg.setTranID("0");
//				EmailAlert EA = new EmailAlert();
//				String alertcode = "THIRD-PARTY-AMT";
//				AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//				if (AM.getEmail_flg().equals("Y")) {
//					EA.setEmail_id(AM.getEmail_1());
//					EA.setEmail_id_cc1(AM.getEmail_2());
//					EA.setEmail_id_cc2(AM.getEmail_3());
//					EA.setEmail_sub("Evidence Source of Fund");
//					EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ " + up.getAcct_name()
//							+ "/ National ID: /" + up.getNid() + "/ Transaction ID: /" + up.getTran_id()
//							+ "/ Amount Paid :/" + up.getAmount_paid() + ":/ Transaction Date : /"
//							+ up.getPayment_date() + ":/ Transaction Type : /" + up.getProd_type()
//							+ ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
//					EA.setEmail_date(new Date());
//					EA.setEmail_srl_no(EMAIL);
//					EA.setSend_flg("N");
//					emailRep.save(EA);
//				}
//				return msg;
//			}
////			} else if (ThirdPartyCount > 3) {
////				System.out.println("count123:" + up.getCust_id());
////				msg.setStatus("Three payment done by third party");
////				msg.setTranID("0");
////				EmailAlert EA = new EmailAlert();
////				String alertcode = "THIRD-PARTY-AMT";
////				AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
////				if (AM.getEmail_flg().equals("Y")) {
////					EA.setEmail_id(AM.getEmail_1());
////					EA.setEmail_id_cc1(AM.getEmail_2());
////					EA.setEmail_id_cc2(AM.getEmail_3());
////					EA.setEmail_sub("Payment By Third Party");
////					EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
////							+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Transaction Type : " + up.getProd_type()
////							+ "/  Payment done by third party");
////					EA.setEmail_date(new Date());
////					EA.setEmail_srl_no(EMAIL);
////					EA.setSend_flg("N");
////					emailRep.save(EA);
////				}
////				return msg;
////			}
//				else if ((NIDcheck != null) && (NIDcheck.equals(NID))) {
//				// System.out.println("count123:" + up.getCust_id());
//				msg.setStatus("Black Listed");
//				msg.setTranID("0");
//				EmailAlert EA = new EmailAlert();
//				String alertcode = "THIRD-PARTY-AMT";
//				AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//				if (AM.getEmail_flg().equals("Y")) {
//					EA.setEmail_id(AM.getEmail_1());
//					EA.setEmail_id_cc1(AM.getEmail_2());
//					EA.setEmail_id_cc2(AM.getEmail_3());
//					EA.setEmail_sub("Black List");
//					EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
//							+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Customer is Blacklisted");
//					EA.setEmail_date(new Date());
//					EA.setEmail_srl_no(EMAIL);
//					EA.setSend_flg("N");
//					emailRep.save(EA);
//				}
//				return msg;
//			} else if (PEP > 0) {
//
//				msg.setStatus("Customer found in PEP List");
//				msg.setTranID("0");
//				EmailAlert EA = new EmailAlert();
//				String alertcode = "THIRD-PARTY-AMT";
//				AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//				if (AM.getEmail_flg().equals("Y")) {
//					EA.setEmail_id(AM.getEmail_1());
//					EA.setEmail_id_cc1(AM.getEmail_2());
//					EA.setEmail_id_cc2(AM.getEmail_3());
//					EA.setEmail_sub("PEP List");
//					EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
//							+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Transaction Type : " + up.getProd_type()
//							+ "/ Customer found in PEP List");
//					EA.setEmail_date(new Date());
//					EA.setEmail_srl_no(EMAIL);
//					EA.setSend_flg("N");
//					emailRep.save(EA);
//				}
//				return msg;
//			} else if (UNSC_IND > 0) {
//
//				msg.setStatus("Customer found in UNSC List");
//				msg.setTranID("0");
//				EmailAlert EA = new EmailAlert();
//				String alertcode = "THIRD-PARTY-AMT";
//				AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//				if (AM.getEmail_flg().equals("Y")) {
//					EA.setEmail_id(AM.getEmail_1());
//					EA.setEmail_id_cc1(AM.getEmail_2());
//					EA.setEmail_id_cc2(AM.getEmail_3());
//					EA.setEmail_sub("UNSC List");
//					EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
//							+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Transaction Type : " + up.getProd_type()
//							+ "/ Customer found in UNSC List");
//					EA.setEmail_date(new Date());
//					EA.setEmail_srl_no(EMAIL);
//					EA.setSend_flg("N");
//					emailRep.save(EA);
//				}
//				return msg;
//			} else if (UNSC_ENT > 0) {
//
//				msg.setStatus("Customer found in UNSC List");
//				msg.setTranID("0");
//				EmailAlert EA = new EmailAlert();
//				String alertcode = "THIRD-PARTY-AMT";
//				AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//				if (AM.getEmail_flg().equals("Y")) {
//					EA.setEmail_id(AM.getEmail_1());
//					EA.setEmail_id_cc1(AM.getEmail_2());
//					EA.setEmail_id_cc2(AM.getEmail_3());
//					EA.setEmail_sub("UNSC List");
//					EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
//							+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Customer found in UNSC List");
//					EA.setEmail_date(new Date());
//					EA.setEmail_srl_no(EMAIL);
//					EA.setSend_flg("N");
//					emailRep.save(EA);
//				}
//				return msg;
//			} else {
				up.setEntity_flg("N");
				up.setDel_flg("N");
				up.setNid(up.getNid());
				thirdPartyRepository.save(up);
				msg.setStatus("Added Successfully");
				AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
				BigDecimal Number = (BigDecimal) hs
						.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();

				String modi = "Transaction ID : " + bamlThirdPartyTran.getTran_id() + " Customer Name "
						+ bamlThirdPartyTran.getAcct_name() + ": TRANSACTION ADDED";

				audit.setAudit_date(new Date());
				audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
				audit.setFunc_code("RECORD CREATED");
				audit.setRemarks("ADDED");
				audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
				audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
				audit.setEvent_id(bamlThirdPartyTran.getCust_id());
				audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "ADDED");
				audit.setModi_details(modi);
				audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
				audit.setEntry_time(bamlThirdPartyTran.getEntry_time());
				audit.setAudit_ref_no(Number.toString());
				auditLocal.save(audit);
				if (up.getTp_nid()!= null) {
					EmailAlert EA = new EmailAlert();
					String alertcode = "THIRD-PARTY-AMT";
					AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
					if (AM.getEmail_flg().equals("Y")) {
						EA.setEmail_id(AM.getEmail_1());
						EA.setEmail_id_cc1(AM.getEmail_2());
						EA.setEmail_id_cc2(AM.getEmail_3());
						EA.setEmail_sub("THIRD PARTY TRANSACTION ADDED");
						EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
								+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Transaction Type : "
								+ up.getProd_type() + "/ Third party transaction added successfully");
						EA.setEmail_date(new Date());
						EA.setEmail_srl_no(EMAIL);
						EA.setSend_flg("N");
						emailRep.save(EA);
					}
				} else {

					EmailAlert EA = new EmailAlert();
					String alertcode = "THIRD-PARTY-AMT";
					AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
					if (AM.getEmail_flg().equals("Y")) {
						EA.setEmail_id(AM.getEmail_1());
						EA.setEmail_id_cc1(AM.getEmail_2());
						EA.setEmail_id_cc2(AM.getEmail_3());
						EA.setEmail_sub("TRANSACTION ADDED");
						EA.setEmail_body("Customer Name " + up.getAcct_name() + ":/ Transaction ID: " + up.getTran_id()
								+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Transaction Type : "
								+ up.getProd_type() + "/ Transaction added successfully");
						EA.setEmail_date(new Date());
						EA.setEmail_srl_no(EMAIL);
						EA.setSend_flg("N");
						emailRep.save(EA);
					}
				
				return msg;
			}

		} else if (formmode.equals("edit")) {
			
			BigDecimal EMAIL = (BigDecimal) hs.createNativeQuery("SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			BAMLThirdPartyTran up = bamlThirdPartyTran;

			long ThirdPartyCount = thirdPartyRepository.findThirdPartyCount(custId);
			long NormalLimit = thirdPartyRepository.findNormalLimit();
			long CreditLimitPerDay = thirdPartyRepository.findCreditLimitPerDay();
			long CreditLimitPerLast14Day = thirdPartyRepository.findCreditLimitPerLast14Day();
			String Tran_id = up.getTran_id();
			String Part_tran_id = up.getPart_tran_type();
			// Date Tran_Date = up.getPayment_date();
			int ExistTran = thirdPartyRepository.findPreTran(Tran_id, Part_tran_id, todate1);
			Calendar cal = Calendar.getInstance();
			cal.setTime(todate1);
			cal.add(Calendar.DATE, -14);
			Date prevPayDate = cal.getTime();
			String PartyTranPrevAmt = thirdPartyRepository.findPrevAmt(prevPayDate, todate1, NID, payMode);
			String PartyTranCurAmt = thirdPartyRepository.findPrevAmt(todate1, todate1, NID, payMode);
			
			if (!(ExistTran > 0)) {

			//if (up.getMode_of_payment().equals("Debit/Credit Card")) {
//				if (Double.parseDouble(up.getAmount_paid().toString()) > CreditLimitPerDay) {
//					msg.setStatus("Card daily limit reached");
//					msg.setTranID("0");
//
//					EmailAlert EA = new EmailAlert();
//					String alertcode = "THIRD-PARTY-AMT";
//					AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//					if (AM.getEmail_flg().equals("Y")) {
//						EA.setEmail_id(AM.getEmail_1());
//						EA.setEmail_id_cc1(AM.getEmail_2());
//						EA.setEmail_id_cc2(AM.getEmail_3());
//						EA.setEmail_sub("Card Limit Alert");
//						EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ " + up.getAcct_name()
//								+ "/ National ID: /" + up.getNid() + "/ Transaction ID: /" + up.getTran_id()
//								+ "/ Amount Paid :/" + up.getAmount_paid() + ":/ Transaction Date : /"
//								+ up.getPayment_date() + ":/ Transaction Type : /" + up.getProd_type()
//								+ ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
//						EA.setEmail_date(new Date());
//						EA.setEmail_srl_no(EMAIL);
//						EA.setSend_flg("N");
//						emailRep.save(EA);
//					}
//					return msg;
//				}
//				} else if (Double.parseDouble(PartyTranCurAmt)
//						+ Double.parseDouble(up.getAmount_paid().toString()) > CreditLimitPerDay) {
//					msg.setStatus("Card daily limit reached");
//					msg.setTranID("0");
//
//					EmailAlert EA = new EmailAlert();
//					String alertcode = "THIRD-PARTY-AMT";
//					AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//					if (AM.getEmail_flg().equals("Y")) {
//						EA.setEmail_id(AM.getEmail_1());
//						EA.setEmail_id_cc1(AM.getEmail_2());
//						EA.setEmail_id_cc2(AM.getEmail_3());
//						EA.setEmail_sub("Card Limit Alert");
//						EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ " + up.getAcct_name()
//								+ "/ National ID: /" + up.getNid() + "/ Transaction ID: /" + up.getTran_id()
//								+ "/ Amount Paid :/" + up.getAmount_paid() + ":/ Transaction Date : /"
//								+ up.getPayment_date() + ":/ Transaction Type : /" + up.getProd_type()
//								+ ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
//						EA.setEmail_date(new Date());
//						EA.setEmail_srl_no(EMAIL);
//						EA.setSend_flg("N");
//						emailRep.save(EA);
//					}
//					return msg;
//				}
//				} else if (Double.parseDouble(PartyTranPrevAmt)
//						+ Double.parseDouble(up.getAmount_paid().toString()) > CreditLimitPerLast14Day) {
//					msg.setStatus("Card daily limit reached for last 14 days");
//					msg.setTranID("0");
//
//					BigDecimal Number = (BigDecimal) hs
//							.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//
//					String modi = "TRANSACTION MODIFIED";
//					AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
//					audit.setAudit_date(new Date());
//					audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
//					audit.setFunc_code("RECORD CREATED");
//					audit.setRemarks("MODIFIED");
//					audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
//					audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
//					audit.setEvent_id(bamlThirdPartyTran.getCust_id());
//					audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "MODIFIED");
//					audit.setModi_details(modi);
//					audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
//					audit.setEntry_time(bamlThirdPartyTran.getEntry_time());
//					audit.setAudit_ref_no(Number.toString());
//					auditLocal.save(audit);
//
//					EmailAlert EA = new EmailAlert();
//					String alertcode = "THIRD-PARTY-AMT";
//					AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
//					if (AM.getEmail_flg().equals("Y")) {
//						EA.setEmail_id(AM.getEmail_1());
//						EA.setEmail_id_cc1(AM.getEmail_2());
//						EA.setEmail_id_cc2(AM.getEmail_3());
//						EA.setEmail_sub("Card Limit Alert");
//						EA.setEmail_body("Customer ID: /" + up.getCust_id() + ": /Customer Name:/ " + up.getAcct_name()
//								+ "/ National ID: /" + up.getNid() + "/ Transaction ID: /" + up.getTran_id()
//								+ "/ Amount Paid :/" + up.getAmount_paid() + ":/ Transaction Date : /"
//								+ up.getPayment_date() + ":/ Transaction Type : /" + up.getProd_type()
//								+ ":/ Evidence Source Of Fund : /" + up.getEvidence_sof_flg());
//						EA.setEmail_date(new Date());
//						EA.setEmail_srl_no(EMAIL);
//						EA.setSend_flg("N");
//						emailRep.save(EA);
//					}
//					return msg;
//				} else {
				//else {
					up.setEntity_flg("N");
					up.setDel_flg("N");
					up.setNid(up.getNid());
					thirdPartyRepository.save(up);
					msg.setStatus("Edited Successfully");
					BigDecimal Number = (BigDecimal) hs
							.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();

					String modi = "TRANSACTION MODIFIED";
					AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
					audit.setAudit_date(new Date());
					audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
					audit.setFunc_code("RECORD CREATED");
					audit.setRemarks("MODIFIED");
					audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
					audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
					audit.setEvent_id(bamlThirdPartyTran.getCust_id());
					audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "MODIFIED");
					audit.setModi_details(modi);
					audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
					audit.setEntry_time(bamlThirdPartyTran.getEntry_time());
					audit.setAudit_ref_no(Number.toString());
					auditLocal.save(audit);

					if (up.getTp_nid()!= null) {
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("THIRD PARTY TRANSACTION EDITED  SUCCESSFULLY");
							EA.setEmail_body(
									"Customer Name :" + up.getAcct_name() + "/ Transaction ID: " + up.getTran_id()
											+ "/ Amount Paid :" + up.getAmount_paid() + "/ Transaction Type : "
											+ up.getProd_type() + "/ Third Party transaction was edited successfully");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
					} else {
						EmailAlert EA = new EmailAlert();
						String alertcode = "THIRD-PARTY-AMT";
						AlertManagementEntity AM = alertrep.getalertdetail(alertcode);
						if (AM.getEmail_flg().equals("Y")) {
							EA.setEmail_id(AM.getEmail_1());
							EA.setEmail_id_cc1(AM.getEmail_2());
							EA.setEmail_id_cc2(AM.getEmail_3());
							EA.setEmail_sub("Edited Successfully");
							EA.setEmail_body(
									"Customer Name : " + up.getAcct_name() + "/ Transaction ID: " + up.getTran_id()
											+ ":/ Amount Paid :" + up.getAmount_paid() + ":/ Transaction Type : "
											+ up.getProd_type() + "/ Transaction was edited successfully");
							EA.setEmail_date(new Date());
							EA.setEmail_srl_no(EMAIL);
							EA.setSend_flg("N");
							emailRep.save(EA);
						}
						
					}
					
					return msg;
				}else {
					
					msg.setStatus("Transaction already exist");
					return msg;
				}
	
					
			}
	else if (formmode.equals("verify")) {

			BAMLThirdPartyTran up = bamlThirdPartyTran;
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			thirdPartyRepository.save(up);
			msg.setStatus("Verified Successfully");

			String master = up.getSrl_no();
			AML_AUDIT_LOCAL audit = auditLocal.getAuditVerifyUserthirdparty(master);
			if (audit.getRemarks().equals("ADDED")) {
				String modi = "Transaction ID : " + bamlThirdPartyTran.getTran_id() + " Customer Name "
						+ bamlThirdPartyTran.getAcct_name() + ": TRANSACTION VERIFIED";

				audit.setAudit_date(new Date());
				audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
				audit.setFunc_code("RECORD CREATED");
				audit.setRemarks("VERIFIED");
				audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
				audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
				audit.setEvent_id(bamlThirdPartyTran.getCust_id());
				audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "VERIFIED");
				audit.setModi_details(modi);
				audit.setEntry_user(audit.getEntry_user());
				audit.setEntry_time(audit.getEntry_time());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
				audit.setAuth_user(bamlThirdPartyTran.getVerify_user());
				audit.setAuth_time(new Date());
				auditLocal.save(audit);
			} else if (audit.getRemarks().equals("MODIFIED")) {
				String modi = "Transaction ID : " + bamlThirdPartyTran.getTran_id() + " Customer Name "
						+ bamlThirdPartyTran.getAcct_name() + ": TRANSACTION VERIFIED";

				audit.setAudit_date(new Date());
				audit.setEntry_user(bamlThirdPartyTran.getEntry_user());
				audit.setFunc_code("RECORD MODIFIED");
				audit.setRemarks("VERIFIED");
				audit.setAudit_table("THIRD_PARTY_TRAN_TABLE");
				audit.setAudit_screen("THIRD PARTY TRANSACTION PAYMENT");
				audit.setEvent_id(bamlThirdPartyTran.getCust_id());
				audit.setEvent_name(bamlThirdPartyTran.getSrl_no() + "-" + "VERIFIED");
				audit.setModi_details(modi);
				audit.setEntry_user(audit.getEntry_user());
				audit.setEntry_time(audit.getEntry_time());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
				audit.setAuth_user(bamlThirdPartyTran.getVerify_user());
				audit.setAuth_time(new Date());
				auditLocal.save(audit);
			}

		}
		return msg;
	}

	public BAMLThirdPartyTran getJobID(String jobId) {

		BAMLThirdPartyTran up = thirdPartyRepository.findByIdcustom(jobId);

		return up;

	}

	public String getThirdPartySrlNo() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("00");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT THIRD_PARTY.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = numformate.format(billNumber);
		return serialno;

	}

	public File getFile(String formmode, String filetype, String FROM_DATE, String TO_DATE)
			throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate1 = null;
		String strDate2 = null;

		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		logger.info("Getting Output file : Third_PARTY");

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(TO_DATE);
			Date ConDate1 = dateFormat1.parse(FROM_DATE);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			strDate1 = formatter1.format(ConDate);
			strDate2 = formatter1.format(ConDate1);
			fileName = "Third_Party_Transaction" + strDate2 + "-" + strDate1;
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			logger.info("Getting Jasper file :" + "Third_PARTY");

			if (filetype.equals("pdf")) {
				if (formmode.equals("list")) {
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ThirdPartyTran/Third_Party_Transaction_RPT.jasper");
				} else {
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ThirdPartyTran/Third_Party_Transaction_RPT.jasper");
				}

			} else {
				if (formmode.equals("list")) {
					logger.info("Inside report");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ThirdPartyTran/Third_Party_Transaction_RPT.jasper");
				} else {
					logger.info("Inside archive");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ThirdPartyTran/Third_Party_Transaction_RPT.jasper");
				}
			}

			JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("TO_DATE", strDate1);
			map.put("FROM_DATE", strDate2);

			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				logger.info("PDF File exported");
			} else {

				System.out.println("EXCEEEEEll");
				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
				logger.info("Excel File exported");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;

	}

	@SuppressWarnings("unchecked")
	public List<Object> getNidFetchDet(String nid2) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CMG_MASTER where nat_id_card_num=?1 ", CMG_MASTER.class);
		query.setParameter(1, nid2);

		List<Object> result = query.getResultList();

		// System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getBlackListDet(String nid2) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Cust_Black_List_Ind_Entity where UPPER(IDTYPER1)=?1 ",
				Cust_Black_List_Ind_Entity.class);
		query.setParameter(1, nid2);

		List<Object> result = query.getResultList();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getBlackListDetByName(String firstname) {

		// ThirdPartyResponse msg = new ThirdPartyResponse();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Cust_Black_List_Ind_Entity where firstname=?1 ",
				Cust_Black_List_Ind_Entity.class);
		query.setParameter(1, firstname);

		List<Object> result = query.getResultList();
		System.out.println(result);

		return result;
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getblackListBCM(String nid2) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CMG_MASTER where NAT_ID_CARD_NUM=?1 ", CMG_MASTER.class);
		query.setParameter(1, nid2);

		List<Object> result = query.getResultList();

		System.out.println(result);
		return result;
	}

}

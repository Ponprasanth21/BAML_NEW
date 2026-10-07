package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.bornfire.entity.ReportValidations;
import com.bornfire.entity.ReportValidationsRepo;
import com.bornfire.entity.T2CurrentRptRepo;
import com.bornfire.entity.ValidationResponse;
import com.bornfire.entity.t10.T10ReportRepo;
import com.bornfire.entity.t12.T12ReportRepo;
import com.bornfire.entity.t13.T13ReportRepo;
import com.bornfire.entity.t14.T14ReportRepo;
import com.bornfire.entity.t15.T15ReportRepo;
import com.bornfire.entity.t18.T18ReportRepo;
import com.bornfire.entity.t3a.T3AReportRepo;
import com.bornfire.entity.t5.T5ReportRepo;
import com.bornfire.entity.t8.T8SumRep;
import com.bornfire.entity.t9.T9ReportRepo;

@Service
@Transactional
@ConfigurationProperties("output")
public class RBSValidationservices {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ReportValidationsRepo reportValidationsRepo;

	@Autowired
	T2CurrentRptRepo t2CurrentRptRepo;

	@Autowired
	T3AReportRepo t3AReportRepo;

	@Autowired
	T8SumRep t8SumRep;

	@Autowired
	T5ReportRepo t5ReportRepo;

	@Autowired
	T18ReportRepo t18ReportRepo;

	@Autowired
	T15ReportRepo t15ReportRepo;

	@Autowired
	T14ReportRepo t14ReportRepo;

	@Autowired
	T9ReportRepo t9ReportRepo;

	@Autowired
	T10ReportRepo t10ReportRepo;

	@Autowired
	T13ReportRepo t13ReportRepo;

	@Autowired
	T12ReportRepo t12ReportRepo;

	private static final Logger logger = LoggerFactory.getLogger(RBSValidationservices.class);

	public ValidationResponse chkRBSValidations(ReportValidations reportValidations, String srl_no,
			String report_date) {
		logger.info("Entered Services");
		ReportValidations up = reportValidations;
		logger.info("report_date" + report_date);
		ValidationResponse msg = new ValidationResponse();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(report_date);
			
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			logger.info(strDate1);
			logger.info("Entered try");
			if (up.getSrl_no().equals("1")) {
				try {
					logger.info("1");
					
					
					long firstvalidationlow = t2CurrentRptRepo.findVal1low(strDate1);
					
				
					long SecondValidationlow = t3AReportRepo.findVal2low(strDate1);
					
					long firstvalidationmedium = t2CurrentRptRepo.findVal1medium(strDate1);
					
					
					long SecondValidationmedium = t3AReportRepo.findVal2medium(strDate1);
					
					long firstvalidationhigh = t2CurrentRptRepo.findVal1high(strDate1);
					
					
					long SecondValidationhigh = t3AReportRepo.findVal2high(strDate1);
					
					logger.info("1" + "firstvalidation:" + firstvalidationlow);
					logger.info("1" + "SecondValidation:" + SecondValidationlow);
					logger.info("1" + "firstvalidation:" + firstvalidationmedium);
					logger.info("1" + "SecondValidation:" + SecondValidationmedium);
					logger.info("1" + "firstvalidation:" + firstvalidationhigh);
					logger.info("1" + "SecondValidation:" + SecondValidationhigh);
					if (up.getSrl_no().equals("1")) {
						if (firstvalidationlow == (SecondValidationlow)) {
							logger.info("Same:");
							up.setCur_status("Y");
							up.setVal_det("Total No of Customers in T2 and T3A");
							up.setVal_tables("T2, T3A");
							reportValidationsRepo.save(up);

						}
						 else {
								up.setCur_status("N");
								up.setVal_det("Total No of Customers in T2 and T3A");
								up.setVal_tables("T2, T3A");
								reportValidationsRepo.save(up);
								logger.info("Not Same:");
								msg.setStatus("T2 and T3A not matched");
								msg.setGenID("0");
								return msg;
							}
						if (firstvalidationmedium ==(SecondValidationmedium)) {
							logger.info("Same:");
							up.setCur_status("Y");
							up.setVal_det("Total No of Customers in T2 and T3A");
							up.setVal_tables("T2, T3A");
							reportValidationsRepo.save(up);

						}
						 else {
								up.setCur_status("N");
								up.setVal_det("Total No of Customers in T2 and T3A");
								up.setVal_tables("T2, T3A");
								reportValidationsRepo.save(up);
								logger.info("Not Same:");
								msg.setStatus("T2 and T3A not matched");
								msg.setGenID("0");
								return msg;
							}
						
						if ( firstvalidationhigh == (SecondValidationhigh) ) {
							logger.info("Same:");
							up.setCur_status("Y");
							up.setVal_det("Total No of Customers in T2 and T3A");
							up.setVal_tables("T2, T3A");
							reportValidationsRepo.save(up);

						}
						 else {
								up.setCur_status("N");
								up.setVal_det("Total No of Customers in T2 and T3A");
								up.setVal_tables("T2, T3A");
								reportValidationsRepo.save(up);
								logger.info("Not Same:");
								msg.setStatus("T2 and T3A not matched");
								msg.setGenID("0");
								return msg;
							}
					}
					
				} catch (Exception e) {
					logger.info("Exception:" + e);
				}

			}

			else if (up.getSrl_no().equals("2")) {
				try {
					logger.info("2");

					Session session = sessionFactory.getCurrentSession();

					logger.info("session");
					//long firstvalidation = (long) session.createNativeQuery(
							//"SELECT SUM((B_13_TOT)+(G_13_TOT)+(L_13_TOT)) AS TOTAL from T3A_PROFILE_FACE_TO_FACE WHERE REPORT_DATE=?1")
							//.setParameter(1, strDate1).getSingleResult();
				//	long SecondValidation = (long) session.createNativeQuery(
							//"SELECT SUM((T5_9C_TOTAL_CUST_UNCHANGE_CUR_FACETOFACE)+(T5_10C_TOTAL_CUR_FACETOFACE)) as TOTAL FROM T5_RISK_RATING_MIG_SUMARY_TABLE WHERE T5_REPORT_TO_DATE=?1")
							//.setParameter(1, strDate1).getSingleResult();

					 long firstvalidation = t3AReportRepo.findVal3(strDate1);
					 long SecondValidation = t5ReportRepo.findVal4(strDate1);
					logger.info("2" + "firstvalidation:" + firstvalidation);
					logger.info("2" + "SecondValidation:" + SecondValidation);
					if (firstvalidation == (SecondValidation)) {
						logger.info("Same:");
						up.setCur_status("Y");
						up.setVal_det("Total No of Customer in T3A and T5 Active Number of Customers(FACE TO FACE)");
						up.setVal_tables("T3A,T5");
						reportValidationsRepo.save(up);

					} else {
						up.setCur_status("N");
						up.setVal_det("Total No of Customer in T3A and T5 Active Number of Customers(FACE TO FACE)");
						up.setVal_tables("T3A,T5");
						reportValidationsRepo.save(up);
						logger.info("Not Same:");
						msg.setStatus("T3A and T5 not matched");
						msg.setGenID("0");
						return msg;
					}
				} catch (Exception e) {
					logger.info("Exception" + e);
				}

			} else if (up.getSrl_no().equals("3")) {
				logger.info("3");
				long firstvalidation = t3AReportRepo.findVal5(strDate1);
				long SecondValidation = t5ReportRepo.findVal6(strDate1);
				logger.info("3" + "firstvalidation:" + firstvalidation);
				logger.info("3" + "SecondValidation:" + SecondValidation);
				if (firstvalidation==SecondValidation) {
					System.out.println("Same:");
					up.setCur_status("Y");
					up.setVal_det("Total No of Customer in T3B and T5 Active Number of Customers(NON FACE TO FACE)");
					up.setVal_tables("T3B,T5");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det("Total No of Customer in T3B and T5 Active Number of Customers(NON FACE TO FACE)");
					up.setVal_tables("T3B,T5");
					reportValidationsRepo.save(up);
					System.out.println("Not Same:");
					msg.setStatus("T3B and T5 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("4")) {
				logger.info("4");
				long firstvalidation = t3AReportRepo.findVal7(strDate1);
				long SecondValidation = t8SumRep.findVal8(strDate1);
				logger.info("4" + "firstvalidation:" + firstvalidation);
				logger.info("4" + "SecondValidation:" + SecondValidation);
				if (firstvalidation==SecondValidation) {
					System.out.println("Same:");
					up.setCur_status("Y");
					up.setVal_det("Total Number of Transactions in T3A and T3B should tally with T8");
					up.setVal_tables("T3A,T3B,T8");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det("Total Number of Transactions in T3A and T3B should tally with T8");
					up.setVal_tables("T3A,T3B,T8");
					reportValidationsRepo.save(up);
					logger.info("4" + "Not Same:");
					msg.setStatus("T3A,T3B and T8 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("5")) {
				logger.info("5");
				long firstvalidation = t8SumRep.findVal9(strDate1);
				long SecondValidation = t3AReportRepo.findVal10(strDate1);

				logger.info("5" + "firstvalidation:" + firstvalidation);
				logger.info("5" + "SecondValidation:" + SecondValidation);
				if (firstvalidation==SecondValidation) {
					logger.info("5" + "Same:");
					up.setCur_status("Y");
					up.setVal_det("Total Amount OF  Transactions in T3A and T3B should tally with T8");
					up.setVal_tables("T3A,T3B,T8");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det("Total Amount OF  Transactions in T3A and T3B should tally with T8");
					up.setVal_tables("T3A,T3B,T8");
					reportValidationsRepo.save(up);
					logger.info("5" + "Not Same:");
					msg.setStatus("T3A,T3B and T8 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("6")) {
				logger.info("6");
				long firstvalidation = t12ReportRepo.findVal11(strDate1);
				long SecondValidation = t8SumRep.findVal12(strDate1);
				long ThirdValidation = t8SumRep.findVal13(strDate1);
				long FourthValidation = t12ReportRepo.findVal14(strDate1);

				logger.info("6" + "firstvalidation:" + firstvalidation);
				logger.info("6" + "SecondValidation:" + SecondValidation);
				logger.info("6" + "ThirdValidation:" + ThirdValidation);
				logger.info("6" + "FourthValidation:" + FourthValidation);
				if (firstvalidation==SecondValidation && ThirdValidation==FourthValidation) {
					logger.info("6" + "Same:");
					up.setCur_status("Y");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cash Deposists in T8 should tally with T12");
					up.setVal_tables("T8,T12");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cash Deposists in T8 should tally with T12");
					up.setVal_tables("T8,T12");
					reportValidationsRepo.save(up);
					logger.info("6" + "Not Same:");
					msg.setStatus("T8 and T12 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("7")) {
				logger.info("7");
				long firstvalidation = t13ReportRepo.findVal15(strDate1);
				long SecondValidation = t8SumRep.findVal16(strDate1);
				long ThirdValidation = t13ReportRepo.findVal17(strDate1);
				long FourthValidation = t8SumRep.findVal18(strDate1);

				logger.info("7" + "firstvalidation:" + firstvalidation);
				logger.info("7" + "SecondValidation:" + SecondValidation);
				logger.info("7" + "ThirdValidation:" + ThirdValidation);
				logger.info("7" + "FourthValidation:" + FourthValidation);
				if (firstvalidation==SecondValidation && ThirdValidation==FourthValidation) {
					logger.info("7" + "Same:");
					up.setCur_status("Y");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cash Withdrawals in T8 should tally with T13");
					up.setVal_tables("T8,T13");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cash Withdrawals in T8 should tally with T13");
					up.setVal_tables("T8,T13");
					reportValidationsRepo.save(up);
					logger.info("7" + "Not Same:");
					msg.setStatus("T8 and T13 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("8")) {

				logger.info("8");
				long firstvalidation = t8SumRep.findVal19(strDate1);
				long SecondValidation = t8SumRep.findVal20(strDate1);
				long ThirdValidation = t10ReportRepo.findVal21(strDate1);
				long FourthValidation = t10ReportRepo.findVal22(strDate1);

				logger.info("8" + "firstvalidation:" + firstvalidation);
				logger.info("8" + "SecondValidation:" + SecondValidation);
				logger.info("8" + "ThirdValidation:" + ThirdValidation);
				logger.info("8" + "FourthValidation:" + FourthValidation);
				if (firstvalidation==ThirdValidation && SecondValidation==FourthValidation) {
					logger.info("8" + "Same:");
					up.setCur_status("Y");
					up.setVal_det(
							"Total Number and Amount of Transaction in Local Bank Transfers - Inward in T8 should tally with T10");
					up.setVal_tables("T8,T10");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det(
							"Total Number and Amount of Transaction in Local Bank Transfers - Inward in T8 should tally with T10");
					up.setVal_tables("T8,T10");
					reportValidationsRepo.save(up);
					logger.info("8" + "Not Same:");
					msg.setStatus("T8 and T10 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("9")) {
				logger.info("9");
				long firstvalidation = t8SumRep.findVal23(strDate1);
				long SecondValidation = t8SumRep.findVal24(strDate1);
				long ThirdValidation = t9ReportRepo.findVal25(strDate1);
				long FourthValidation = t9ReportRepo.findVal26(strDate1);

				logger.info("9" + "firstvalidation:" + firstvalidation);
				logger.info("9" + "SecondValidation:" + SecondValidation);
				logger.info("9" + "ThirdValidation:" + ThirdValidation);
				logger.info("9" + "FourthValidation:" + FourthValidation);
				if ((firstvalidation - ThirdValidation) <=2 && (SecondValidation - FourthValidation)<= 2) {
					logger.info("9" + "Same:");
					up.setCur_status("Y");
					up.setVal_det(
							"Total Number and Amount of Transaction in Local Bank Transfers - Outward in T8 should tally with T9");
					up.setVal_tables("T8,T9");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det(
							"Total Number and Amount of Transaction in Local Bank Transfers - Outward in T8 should tally with T9");
					up.setVal_tables("T8,T9");
					reportValidationsRepo.save(up);
					logger.info("9" + "Not Same:");
					msg.setStatus("T8 and T9 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("10")) {
				logger.info("10");
				long firstvalidation = t8SumRep.findVal27(strDate1);
				long SecondValidation = t8SumRep.findVal28(strDate1);
				long ThirdValidation = t14ReportRepo.findVal29(strDate1);
				long FourthValidation = t14ReportRepo.findVal30(strDate1);

				logger.info("10" + "firstvalidation:" + firstvalidation);
				logger.info("10" + "SecondValidation:" + SecondValidation);
				logger.info("10" + "ThirdValidation:" + ThirdValidation);
				logger.info("10" + "FourthValidation:" + FourthValidation);

				if ((firstvalidation==ThirdValidation && SecondValidation==FourthValidation)) {
					logger.info("10" + "Same:");
					up.setCur_status("Y");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cheque Inward in T8 should tally with T14");
					up.setVal_tables("T8,T14");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cheque Inward in T8 should tally with T14");
					up.setVal_tables("T8,T14");
					reportValidationsRepo.save(up);
					logger.info("10" + "Not Same:");
					msg.setStatus("T8 and T14 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("11")) {
				logger.info("11");
				long firstvalidation = t8SumRep.findVal31(strDate1);
				long SecondValidation = t8SumRep.findVal32(strDate1);
				long ThirdValidation = t15ReportRepo.findVal33(strDate1);
				long FourthValidation = t15ReportRepo.findVal34(strDate1);

				logger.info("11" + "firstvalidation:" + firstvalidation);
				logger.info("11" + "SecondValidation:" + SecondValidation);
				logger.info("11" + "ThirdValidation:" + ThirdValidation);
				logger.info("11" + "FourthValidation:" + FourthValidation);
				if (firstvalidation==ThirdValidation && SecondValidation==FourthValidation) {
					logger.info("11" + "Same:");
					up.setCur_status("Y");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cheque Outward in T8 should tally with T15");
					up.setVal_tables("T8,T15");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det(
							"Total Number and Amount of Transaction in Cheque Outward in T8 should tally with T15");
					up.setVal_tables("T8,T15");
					reportValidationsRepo.save(up);
					logger.info("11" + "Not Same:");
					msg.setStatus("T8 and T15 not matched");
					msg.setGenID("0");
					return msg;
				}

			} else if (up.getSrl_no().equals("12")) {
				logger.info("12");
				long firstvalidation = t18ReportRepo.findVal35(strDate1);
				long SecondValidation = t18ReportRepo.findVal36(strDate1);
				long ThirdValidation = t8SumRep.findVal37(strDate1);
				long FourthValidation = t8SumRep.findVal38(strDate1);

				logger.info("12" + "firstvalidation:" + firstvalidation);
				logger.info("12" + "SecondValidation:" + SecondValidation);
				logger.info("12" + "ThirdValidation:" + ThirdValidation);
				logger.info("12" + "FourthValidation:" + FourthValidation);
				if (firstvalidation==ThirdValidation && SecondValidation==FourthValidation) {
					logger.info("12" + "Same:");
					up.setCur_status("Y");
					up.setVal_det("Total Number and Amount of Transaction in T8 and T18");
					up.setVal_tables("T8,T18");
					reportValidationsRepo.save(up);

				} else {
					up.setCur_status("N");
					up.setVal_det("Total Number and Amount of Transaction in T8 and T18");
					up.setVal_tables("T8,T18");
					reportValidationsRepo.save(up);
					logger.info("12" + "Not Same:");
					msg.setStatus("T8 and T18 not matched");
					msg.setGenID("0");
					return msg;
				}

			}

		} catch (Exception e) {
			msg.setStatus("Please check report date");
			msg.setGenID("0");
			return msg;
		}

		return msg;

	}
}
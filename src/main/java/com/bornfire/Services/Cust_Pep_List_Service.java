package com.bornfire.Services;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/*  
 * 
 * Author : vijay corda
 * 
 * */
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.BAML_Cust_PEP_RPT_Entity;
import com.bornfire.entity.BAML_Cust_PEP_RPT_Repository;
import com.bornfire.entity.Cust_Pep_List_Entity;
import com.bornfire.entity.Cust_Pep_List_Repository;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.monitorjbl.xlsx.StreamingReader;

import au.com.bytecode.opencsv.CSVReader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
@ConfigurationProperties("output")
@Transactional
public class Cust_Pep_List_Service {

	@Autowired
	Cust_Pep_List_Repository cust_pep_list_Repository;
	
	@Autowired
	AlertManagementRepository alertrep;
	
	private static final Logger logger = LoggerFactory.getLogger(Cust_Pep_List_Service.class);

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	EMAIL email;
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;
	
	@Autowired
	EMAILREP emailRep;
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Autowired
	Environment env;
	
	@Autowired
	private BAML_Cust_PEP_RPT_Repository baml_cust_pep_rpt_repository;

	
	  private static String[] columns = {"SL", "Customer ID", "Last Name", "First Name","NID","Risk Category","PEP Description","Status","Membership Date","Date of PEP","Date of Resignation","Resignation Reason","Active Product Type"};
	   
	  private static List<BAML_Cust_PEP_RPT_Entity> pepList =  new ArrayList<BAML_Cust_PEP_RPT_Entity>();
	public String addPepList(Cust_Pep_List_Entity alertparam, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		Session hs = sessionFactory.getCurrentSession();
		if (formmode.equals("add")) {
			
			Cust_Pep_List_Entity up = alertparam;
		if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
				up.setRisk_category(null);
			}
			DecimalFormat numformate = new DecimalFormat("0");
			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT PEP_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
			String serialno =  numformate.format(billNumber);
			up.setAcid(serialno);
			alertparam.setAcid(serialno);
			up.setDel_flag("N");
			up.setEntity_flag("N");
			cust_pep_list_Repository.save(up);
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "PEP List OF "+alertparam.getCust_name()+" "+alertparam.getCust_short_name()+" is ADDED By " +alertparam.getAml_entry_time();
			
//				modi = modi + (" PEP_ID  " + up.getCif_id() + '+');
//				modi = modi + (" CIF_ID  " + alertparam.getCif_id() + '+');
//				modi = modi +(" CUSTOMER_SURNAME_NAME " + alertparam.getCust_short_name() + '+');
//				modi = modi +(" CUST_NAME  " + alertparam.getCust_name() + '+');
//				modi = modi +(" NID " + alertparam.getNat_id_card_num() + '+');
//				modi = modi +(" RISK CATEGORY " + alertparam.getRisk_category() + '+');
//				modi = modi +(" PEP_DESC " + alertparam.getPep_desc() + '+');
//				modi = modi +(" CUST_POSITION " + alertparam.getCust_position() + '+');
//				modi = modi +(" MEMBERSHIP_DATE " + alertparam.getMembership_date() + '+');
//				modi = modi +(" DATE_OF_PEP " + alertparam.getDate_of_pep() + '+');
//				modi = modi +(" DATE_OF_RESIGNATION " + alertparam.getDate_of_resig() + '+');
//				modi = modi +(" DATE_OF_CLOSE " + alertparam.getDate_of_close() + '+');
//				modi = modi +(" RESGN_REASON " + alertparam.getResig_reasons() + '+');
//				modi = modi +(" ACT_PROD_TYPE " + alertparam.getActive_prod_type() + '+');
//				modi = modi +(" SCHM_TYPE " + alertparam.getSchm_type() + '+');
//				modi = modi +(" SCHEME_CODE " + alertparam.getSchm_code() + '+');
//				modi = modi +(" ACCOUNT ID " + alertparam.getForacid() + '+');
//				modi = modi +(" ACC_OPN_DATE " + alertparam.getAcct_open_date() + '+');
//				modi = modi +(" REMSRKS " + alertparam.getRemarks() + '+');
			
			
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("BAML_CUST_PEP_LIST");
			audit.setAudit_screen("PEP LISTING MASTER");
			audit.setEvent_id(alertparam.getAcid());
			audit.setEvent_name(alertparam.getAcid()+"-"+"ADDED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setEntry_time(alertparam.getAml_entry_time());
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);
			
			
			

			msg = "Pep List Created Successfully";

		} else if (formmode.equals("edit")) {
		
			Optional<Cust_Pep_List_Entity> reg = cust_pep_list_Repository.findById(alertparam.getAcid());
			if (reg.isPresent()) {
				
				Cust_Pep_List_Entity reg1 = new Cust_Pep_List_Entity();
				reg1=reg.get();
				if ((reg1.getCif_id().equals(alertparam.getCif_id()))
						&& (reg1.getCust_short_name().equals(alertparam.getCust_short_name()))
						&& (reg1.getCust_name().equals(alertparam.getCust_name()))
						&& (alertparam.getNat_id_card_num() ==null || reg1.getNat_id_card_num() ==null  ? true : reg1.getNat_id_card_num().equals(alertparam.getNat_id_card_num()))
						&& (alertparam.getNat_id_card_num()!=null ? reg1.getNat_id_card_num().equals(alertparam.getNat_id_card_num()) : true)

						
						&& (alertparam.getRisk_category() ==null || reg1.getRisk_category() ==null  ? true : reg1.getRisk_category().equals(alertparam.getRisk_category()))
					
						&& (reg1.getPep_desc().equals(alertparam.getPep_desc()))
						&& (reg1.getCust_position().equals(alertparam.getCust_position()))
						&& (reg1.getMembership_date().compareTo(alertparam.getMembership_date()) == 0)
						&& (reg1.getDate_of_pep().compareTo(alertparam.getDate_of_pep()) == 0)
						&& (reg1.getDate_of_resig().compareTo(alertparam.getDate_of_resig()) == 0)
						&& (reg1.getDate_of_close().compareTo(alertparam.getDate_of_close()) == 0)

						&& (reg1.getResig_reasons().equals(alertparam.getResig_reasons()))
						
						&& (reg1.getActive_prod_type().equals(alertparam.getActive_prod_type()))
						&& (reg1.getSchm_type().equals(alertparam.getSchm_type()))
						&& (reg1.getSchm_code().equals(alertparam.getSchm_code()))
						&& (reg1.getForacid().equals(alertparam.getForacid()))
						&& (reg1.getAcct_open_date().compareTo(alertparam.getAcct_open_date()) == 0) ) {
					msg = "No Modification done";
				} else {
					String modi = "";

					BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
							.getSingleResult();
					
				
					
					if (!reg1.getCif_id().equals(alertparam.getCif_id())) {
					modi = modi + ("OLD CIF_ID + " + reg1.getCif_id() + "+ OLD CIF_ID " + alertparam.getCif_id() + '+');
					} else if (!reg1.getCust_short_name().equals(alertparam.getCust_short_name())) {
						modi = modi +("OLD - CUSTOMER_SURNAME_NAME " + reg1.getCust_short_name() + "+ NEW - CUSTOMER_SURNAME_NAME " + alertparam.getCust_short_name() + '+');
					} else if (!reg1.getCust_name().equals(alertparam.getCust_name())) {
						modi = modi +("OLD CUST_NAME  " + reg1.getCust_name() + "+ NEW CUST_NAME " + alertparam.getCust_name() + '+');
					}  else if (!reg1.getCust_name().equals(alertparam.getCust_name())) {
						modi = modi +("OLD CUST_NAME  " + reg1.getCust_name() + "+ NEW CUST_NAME " + alertparam.getCust_name() + '+');
					}  else if (!reg1.getNat_id_card_num().equals(alertparam.getNat_id_card_num())) {
							modi = modi +("OLD NID " + reg1.getNat_id_card_num() + "+ NEW NID " + alertparam.getNat_id_card_num() + '+');
					} else if (!reg1.getRisk_category().equals(alertparam.getRisk_category())) {
						modi = modi +("OLD RISK CATEGORY  " + reg1.getRisk_category() + "+ NEW RISK CATEGORY " + alertparam.getRisk_category() + '+');
					} 
					else if (!reg1.getPep_desc().equals(alertparam.getPep_desc())) {
						modi = modi +("OLD BUS_DESC " + reg1.getPep_desc() + "+ NEW  BUS_DESC " + alertparam.getPep_desc() + '+');
					}
				
					else if (!reg1.getPep_desc().equals(alertparam.getPep_desc())) {
						modi = modi +("OLD PEP_DESC " + reg1.getPep_desc() + "+ NEW  PEP_DESC " + alertparam.getPep_desc() + '+');
					} else if (!reg1.getCust_position().equals(alertparam.getCust_position())) {
						modi = modi +("OLD CUST_POSTN  " + reg1.getCust_position() + "+ NEW  CUST_POSTN " + alertparam.getCust_position() + '+');
					}else if (!reg1.getMembership_date().equals(alertparam.getMembership_date())) {
						modi = modi +("OLD MEMBERSHIP_DATE  " + reg1.getMembership_date() + "+ NEW  MEMBERSHIP_DATE " + alertparam.getMembership_date() + '+');
					}else if (!reg1.getDate_of_pep().equals(alertparam.getDate_of_pep())) {
						modi = modi +("OLD DATE_OF_PEP  " + reg1.getDate_of_pep() + "+ NEW  DATE_OF_PEP " + alertparam.getDate_of_pep() + '+');
					}else if (!reg1.getDate_of_resig().equals(alertparam.getDate_of_resig())) {
					modi = modi +("OLD DATE_OF_RESGN  " + reg1.getDate_of_resig() + "+ NEW  DATE_OF_RESGN " + alertparam.getDate_of_resig() + '+');
					}else if (!reg1.getDate_of_close().equals(alertparam.getDate_of_close())) {
						modi = modi +("OLD DATE_OF_CLOSE  " + reg1.getDate_of_close() + "+ NEW  DATE_OF_CLOSE " + alertparam.getDate_of_close() + '+');
					}else if (!reg1.getResig_reasons().equals(alertparam.getResig_reasons())) {
						modi = modi +("OLD RESGN_REASON  " + reg1.getResig_reasons() + "+ NEW  RESGN_REASON " + alertparam.getResig_reasons() + '+');
					}else if (!reg1.getActive_prod_type().equals(alertparam.getActive_prod_type())) {
						modi = modi +("OLD ACTIVE_PROD_TYPE " + reg1.getActive_prod_type() + "+ NEW ACTIVE_PROD_TYPE " + alertparam.getActive_prod_type() + '+');
					} else if (!reg1.getSchm_type().equals(alertparam.getSchm_type())) {
							modi = modi +("OLD ACTIVE_PROD_TYPE " + reg1.getSchm_type() + "+ NEW ACTIVE_PROD_TYPE " + alertparam.getSchm_type() + '+');
					} else if (!reg1.getSchm_code().equals(alertparam.getSchm_code())) {
						modi = modi +("OLD SCHM_CODE " + reg1.getSchm_code() + "+ NEW SCHM_CODE " + alertparam.getSchm_code() + '+');
					} else if (!reg1.getForacid().equals(alertparam.getForacid())) {
						modi = modi +("OLD FORACID " + reg1.getForacid() + "+ NEW FORACID " + alertparam.getForacid() + '+');
					} else if (!reg1.getAcct_open_date().equals(alertparam.getAcct_open_date())) {
						modi = modi +("OLD ACC_OPN_DATE  " + reg1.getAcct_open_date() + "+ NEW ACC_OPN_DATE " + alertparam.getAcct_open_date() + '+');
					}else if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
						modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag() + '+');
					}else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
						modi = modi +("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG " + alertparam.getModify_flag() + '+');
					}else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
						modi = modi +("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG " + alertparam.getEntity_flag() + '+');
					}
						
					audit.setAudit_date(new Date());
					audit.setEntry_user(alertparam.getAml_entry_user());
					audit.setFunc_code("RECORD MODIFIED");
					audit.setRemarks("MODIFIED");
					audit.setAudit_table("BAML_CUST_PEP_LIST");
					audit.setAudit_screen("PEP LISTING MASTER");
					audit.setEvent_id(alertparam.getAcid());
					audit.setEvent_name(alertparam.getCust_name()+" "+alertparam.getCust_short_name()+"-"+"MODIFIED");
					audit.setModi_details(modi);
					audit.setEntry_user(alertparam.getAml_modify_user());
					audit.setEntry_time(alertparam.getAml_modify_time());
					audit.setAudit_ref_no(Number.toString());
			
			if(alertparam.getRisk_category()==null || alertparam.getRisk_category().isEmpty() || alertparam.getRisk_category().equals("")) {
				alertparam.setRisk_category("LOW");
			}
			Cust_Pep_List_Entity up = alertparam;
			alertparam.setAcid(up.getAcid());
			alertparam.setDel_flag("N");
			alertparam.setModify_flag("Y");
			alertparam.setEntity_flag("N");
			
			cust_pep_list_Repository.saveAndFlush(alertparam);
			
			msg = "Pep List Edited Successfully";
			auditLocal.save(audit);
				}
			}
		} else if (formmode.equals("delete")) {
			Optional<Cust_Pep_List_Entity> regs = cust_pep_list_Repository.findById(alertparam.getAcid());
			if (regs.isPresent()) {
				Cust_Pep_List_Entity reg1 = new Cust_Pep_List_Entity();
				reg1=regs.get();
			
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD DELETED");
			audit.setRemarks("DELETED");
			audit.setAudit_table("BAML_CUST_PEP_LIST");
			audit.setAudit_screen("PEP LISTING MASTER");
			audit.setEvent_id(alertparam.getAcid());
			audit.setEvent_name(alertparam.getCust_name()+" "+alertparam.getCust_short_name()+"-"+"DELETED");
			
			audit.setEntry_user(alertparam.getAml_modify_user());
			audit.setEntry_time(alertparam.getAml_modify_time());
			audit.setAudit_ref_no(Number.toString());
			
			
			if(alertparam.getRisk_category()==null || alertparam.getRisk_category().isEmpty()) {
				alertparam.setRisk_category("LOW");
			}
			Cust_Pep_List_Entity up = alertparam;
			alertparam.setAcid(up.getAcid());
			alertparam.setDel_flag("Y");
			alertparam.setEntity_flag("N");
			cust_pep_list_Repository.save(alertparam);
			msg = "Pep List Deleted Successfully";
			
			
			String modi="PEP Customer : "+alertparam.getCust_name()+" "+alertparam.getCust_short_name()+" is Deleted by "+alertparam.getAml_entry_user();
		
			audit.setModi_details(modi);
			auditLocal.save(audit);
			
			}
			
			
			
		} else if (formmode.equals("verify")) {
			Optional<Cust_Pep_List_Entity> regv = cust_pep_list_Repository.findById(alertparam.getAcid());
			if (regv.isPresent()) {
				Cust_Pep_List_Entity reg1 = new Cust_Pep_List_Entity();
				reg1=regv.get();
			
				String modi="PEP Customer : "+alertparam.getCust_name()+" "+alertparam.getCust_short_name()+" is Verified by "+alertparam.getAml_verify_user();
		/*	if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
				modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag() + '+');
			}else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
				modi = modi +("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG " + alertparam.getModify_flag() + '+');
			}else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
				modi = modi +("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG + ");
			}*/
				AML_AUDIT_LOCAL audit1 = auditLocal.getAuditVerifyUserPEP(alertparam.getAcid());
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			BigDecimal EMAIL = (BigDecimal) hs.createNativeQuery("SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			if (audit1 !=null && audit1.getRemarks().equals("ADDED")) {
				audit1.setAudit_date(new Date());
				audit1.setAudit_table("BAML_CUST_PEP_LIST");
				audit1.setAudit_screen("PEP LIST - CREATION");
				audit1.setFunc_code("PEP VERIFIED");
				audit1.setRemarks("VERIFIED");
				audit1.setEvent_id(alertparam.getAcid());
				audit1.setEvent_name(alertparam.getCust_name());
				audit1.setModi_details(audit1.getModi_details());
				audit1.setAuth_user(alertparam.getAml_verify_user());
				audit1.setAuth_time(new Date());
				audit1.setAudit_ref_no(audit1.getAudit_ref_no());
				EmailAlert EA = new EmailAlert();
				String alertcode = "PEP-ADD";
				AlertManagementEntity  AM= alertrep.getalertdetail(alertcode);
				if(AM.getEmail_flg().equals("Y")) {
					EA.setEmail_id(AM.getEmail_1());
					EA.setEmail_id_cc1(AM.getEmail_2());
					EA.setEmail_id_cc2(AM.getEmail_3());
					EA.setEmail_sub(AM.getParam_1());
					EA.setEmail_body("PEP LIST RECORD CREATED ..../ PEP ID :"+alertparam.getAcid()+", PEP NAME :"+alertparam.getCust_name()+" "+alertparam.getCust_short_name()+" /PROCESSED BY : "+alertparam.getAml_verify_user());
					EA.setEmail_date(new Date());
					EA.setEmail_srl_no(EMAIL);
					EA.setSend_flg("N");
					emailRep.save(EA);
				}
			}else if (audit1!=null && audit1.getRemarks().equals("MODIFIED")) {
				audit1.setAudit_date(new Date());
				audit1.setAudit_table("BAML_CUST_PEP_LIST");
				audit1.setAudit_screen("PEP LIST - MODIFIED");
				audit1.setFunc_code("PEP LIST MODIFIED");
				audit1.setRemarks("VERIFIED");
				audit1.setEvent_id(alertparam.getAcid());
				audit1.setEvent_name(alertparam.getCust_name());
				audit1.setModi_details(audit1.getModi_details());
				audit1.setAuth_user(alertparam.getAml_verify_user());
				audit1.setAuth_time(new Date());
				audit1.setAudit_ref_no(audit1.getAudit_ref_no());
				EmailAlert EA = new EmailAlert();
				String alertcode = "PEP-MODIFY";
				AlertManagementEntity  AM= alertrep.getalertdetail(alertcode);
				if(AM.getEmail_flg().equals("Y")) {
					EA.setEmail_id(AM.getEmail_1());
					EA.setEmail_id_cc1(AM.getEmail_2());
					EA.setEmail_id_cc2(AM.getEmail_3());
					EA.setEmail_sub(AM.getParam_1());
					EA.setEmail_body("PEP LIST RECORD MODIFIED ..../ PEP ID :"+alertparam.getAcid()+", PEP NAME :"+alertparam.getCust_name()+" "+alertparam.getCust_short_name()+" /PROCESSED BY : "+alertparam.getAml_verify_user());
					EA.setEmail_date(new Date());
					EA.setEmail_srl_no(EMAIL);
					EA.setSend_flg("N");
					emailRep.save(EA);
				}
			}else if (audit1!=null && audit1.getRemarks().equals("DELETED")) {
				audit1.setAudit_date(new Date());
				audit1.setAudit_table("BAML_CUST_PEP_LIST");
				audit1.setAudit_screen("PEP LIST - DELETED");
				audit1.setFunc_code("PEP LIST DELETED");
				audit1.setRemarks("VERIFIED");
				audit1.setEvent_id(alertparam.getAcid());
				audit1.setEvent_name(alertparam.getCust_name());
				audit1.setModi_details(audit1.getModi_details());
				audit1.setAuth_user(alertparam.getAml_verify_user());
				System.out.println("verify user"+alertparam.getAml_verify_user());
				audit1.setAuth_time(new Date());
				audit1.setAudit_ref_no(audit1.getAudit_ref_no());
				EmailAlert EA = new EmailAlert();
				String alertcode = "PEP-MODIFY";
				AlertManagementEntity  AM= alertrep.getalertdetail(alertcode);
				if(AM.getEmail_flg().equals("Y")) {
					EA.setEmail_id(AM.getEmail_1());
					EA.setEmail_id_cc1(AM.getEmail_2());
					EA.setEmail_id_cc2(AM.getEmail_3());
					EA.setEmail_sub(AM.getParam_1());
					EA.setEmail_body("PEP LIST RECORD DELETED ..../ PEP ID :"+alertparam.getAcid()+", PEP NAME :"+alertparam.getCust_name()+" "+alertparam.getCust_short_name()+" /PROCESSED BY : "+alertparam.getAml_verify_user());
					EA.setEmail_date(new Date());
					EA.setEmail_srl_no(EMAIL);
					EA.setSend_flg("N");
					emailRep.save(EA);
				}
			}
			if(audit1!=null) {
				auditLocal.save(audit1);
				}
			
			
			

			Cust_Pep_List_Entity up = alertparam;
			if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
				up.setRisk_category("LOW");
			}
			alertparam.setAcid(up.getAcid());
			up.setEntity_flag("Y");
			up.setDel_flag("N");
			cust_pep_list_Repository.save(up);
			msg = "Pep List Verified Successfully";
			
			//auditLocal.save(audit1);
			}
		}
		return msg;
	}

	public Cust_Pep_List_Entity getSrlNo(String srlno) {

		if (cust_pep_list_Repository.existsById(srlno)) {
			Cust_Pep_List_Entity up = cust_pep_list_Repository.findById(srlno).get();
			return up;
		} else {
			return new Cust_Pep_List_Entity();
		}

	};

	public String deleteParameter(String inputSrlNo) {
		String msg = "";
		Optional<Cust_Pep_List_Entity> user = cust_pep_list_Repository.findById(inputSrlNo);
		Cust_Pep_List_Entity reg = user.get();
		reg.setDel_flag("N");
		/* montParameterRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String inputSrlNo) {
		String msg = "";
		Optional<Cust_Pep_List_Entity> user = cust_pep_list_Repository.findById(inputSrlNo);
		Cust_Pep_List_Entity reg = user.get();
		
		reg.setDel_flag("N");
		msg = "User Deleted Successfully";
		return msg;
	}
	
	
//	public String getNextRefValue() {
//		// getting the next no for unique ref id
//
//		Session hs = sessionFactory.getCurrentSession();
//
//		DecimalFormat numformate = new DecimalFormat("0");
//		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT id FROM NEGATIVE_LIST_NUM").getSingleResult();
//
//
//		String serialno = null;
//		if (billNumber == null) {
//			//********** incase num table is not having any vlaue then insert - NUM table shud contain
//			//********* only one Row shud be there in this table at all times 
//			hs.createNativeQuery("insert into negative_list_num(id) values(1)").getSingleResult();
//			serialno = "1";
//		} else {
//			serialno = numformate.format(billNumber);
//		}
//
//		return  serialno;
//	}
//		
//	public void updateNegative_list_Num() {
//		Session hs = sessionFactory.getCurrentSession();
//		// after save increement the num table value by 1
//		cust_pep_list_Repository.updatePepNumTB();
//		
//	}
		
	public String processUpload1(String screenId,MultipartFile file, String userid) throws IllegalStateException, IOException
	{

		String fileName = file.getOriginalFilename();
		File convertedFile = multipartToFile(file, fileName);

		String fileExt = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}
		logger.info("fileExt: " + fileExt);
		String Errormsg = "";
		String status = "";
		Session theSession = sessionFactory.getCurrentSession();
//theSession.createSQLQuery(" truncate table NEGATIVE_LIST ").executeUpdate();
//logger.info("NEGATIVE_LIST truncated");
		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {
			logger.info("reading values from Excel");
			String cellval = "";
			try (InputStream is = new FileInputStream(convertedFile);
					Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is)) {
				for (Sheet s : workbook) {
					logger.info("inside workbook");
					for (Row r : s) {
						ArrayList<String> resultList = new ArrayList<>();
						if (r.getRowNum() == 0) {
							continue;
						}
						cellval = "";
						String val = null;
						for (int j = 0; j < 19; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);
						}
						String cif_id = resultList.get(0);
						String cust_surname = resultList.get(1);
						String cust_name = resultList.get(2);
						String nic = resultList.get(3);
						String risk_cat = resultList.get(4);
						String pep_desc = resultList.get(5);
						String cust_position = resultList.get(6);
						String membership_date = resultList.get(7);
						String dateof_pep = resultList.get(8);
						String date_of_resgn = resultList.get(9);
						String date_of_close = resultList.get(10);
						String resgn_reason = resultList.get(11);
						String act_prod_type = resultList.get(12);
						String scheme_type = resultList.get(13);
						String scheme_code = resultList.get(14);
						String acc_id = resultList.get(15);
						String acc_opn_date = resultList.get(16);
						String pep_type= resultList.get(17);
						String remarks = resultList.get(18);
						Date membership_date1 = null;
						
						try {
							if (membership_date != null && !membership_date.isEmpty()) {
								membership_date1 = new SimpleDateFormat("dd/mm/yyyy").parse(membership_date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (membership_date != null && !membership_date.isEmpty()) {
									membership_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(membership_date);
								}
							} catch (Exception e1) {
								try {
									if (membership_date != null && !membership_date.isEmpty()) {
										membership_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(membership_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
					
						Date dateof_pep1 = null;
						try {
							if (dateof_pep != null && !dateof_pep.isEmpty()) {
								dateof_pep1 = new SimpleDateFormat("dd/mm/yyyy").parse(membership_date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (dateof_pep != null && !dateof_pep.isEmpty()) {
									dateof_pep1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(membership_date);
								}
							} catch (Exception e1) {
								try {
									if (dateof_pep != null && !dateof_pep.isEmpty()) {
										dateof_pep1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(membership_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						Date date_of_resgn1 = null;
						try {
							if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
								date_of_resgn1 = new SimpleDateFormat("dd/mm/yyyy").parse(date_of_resgn);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
									date_of_resgn1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_resgn);
								}
							} catch (Exception e1) {
								try {
									if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
										date_of_resgn1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_resgn);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
					
						Date date_of_close1 = null;
						try {
							if (date_of_close != null && !date_of_close.isEmpty()) {
								date_of_close1 = new SimpleDateFormat("dd/mm/yyyy").parse(date_of_close);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (date_of_close != null && !date_of_close.isEmpty()) {
									date_of_close1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_close);
								}
							} catch (Exception e1) {
								try {
									if (date_of_close != null && !date_of_close.isEmpty()) {
										date_of_close1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_close);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						
						Date acc_opn_date1 = null;
						try {
							if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
								acc_opn_date1 = new SimpleDateFormat("dd/mm/yyyy").parse(acc_opn_date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
									acc_opn_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(acc_opn_date);
								}
							} catch (Exception e1) {
								try {
									if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
										acc_opn_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(acc_opn_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						
						Date pep_date = new Date();
						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "N";
						String del_flg = "N";
						String acid ="";
						Cust_Pep_List_Entity info = new Cust_Pep_List_Entity(cif_id, cust_surname, cust_name, nic,
								risk_cat, pep_desc, membership_date1, dateof_pep1, date_of_resgn1, date_of_close1,
								resgn_reason, act_prod_type, scheme_type, scheme_code, acc_id, acc_opn_date1, pep_type,remarks,pep_date,
								cust_position, entry_user, entry_time, entity_flg, del_flg,acid);
						List<Cust_Pep_List_Entity> info1=null;
						if(acc_id != null && !acc_id.isEmpty()) {
						info1=cust_pep_list_Repository.findByforacid(acc_id);
						logger.info("MASTER VIJAY");
						}
						if (info1 != null && !info1.isEmpty()) {
							Errormsg = Errormsg + acc_id ;
						} else {
							if (!info.getCust_name().equals(null) && !info.getCust_name().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();
								DecimalFormat numformate = new DecimalFormat("0");
								BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT PEP_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
								String serialno =  numformate.format(billNumber);
								info.setAcid(serialno);
								cust_pep_list_Repository.save(info);								
								AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
								BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
										.getSingleResult();
								String modi = "RECORD ADDED";
								audit.setAudit_date(new Date());
								audit.setEntry_user(info.getAml_entry_user());
								audit.setFunc_code("RECORD UPLOADED");
								audit.setRemarks("ADDED");
								audit.setAudit_table("BAML_CUST_PEP_LIST");
								audit.setAudit_screen("PEP LISTING MASTER");
								audit.setEvent_id(info.getAcid());
								audit.setEvent_name(info.getAcid()+"-"+"ADDED");
								audit.setModi_details(modi);
								audit.setEntry_user(info.getAml_entry_user());
								audit.setEntry_time(info.getAml_entry_time());
								audit.setAudit_ref_no(Number.toString());
								auditLocal.save(audit);																
								status = "successfully uploaded";
							} else {
								Errormsg = Errormsg + "," + cif_id;
							}
//					theSession.flush();
//					theSession.clear();
						}
					}
					status = "successfully uploaded";
					logger.info("inserted values into PEP Fund List");
				}
			} catch (Exception e) {
			}
		} else {
			logger.info("reading values from CSV");
			try {
				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {
					if (skipRow > 0) {
						String cif_id = nextLine[0];
						String cust_surname = nextLine[1];
						String cust_name =nextLine[2];
						String nic = nextLine[3];
						String risk_cat =nextLine[4];
						String pep_desc =nextLine[5];
						String cust_position = nextLine[6];
						String membership_date = nextLine[7];
						String dateof_pep = nextLine[8];
						String date_of_resgn =nextLine[9];
						String date_of_close = nextLine[10];
						String resgn_reason =nextLine[11];
						String act_prod_type =nextLine[12];
						String scheme_type = nextLine[13];
						String scheme_code = nextLine[14];
						String acc_id = nextLine[15];
						String acc_opn_date = nextLine[16];
						String pep_type= nextLine[17];
						String remarks = nextLine[18];
					Date membership_date1 = null;
						
						try {
							if (membership_date != null && !membership_date.isEmpty()) {
								membership_date1 = new SimpleDateFormat("dd/mm/yyyy").parse(membership_date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (membership_date != null && !membership_date.isEmpty()) {
									membership_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(membership_date);
								}
							} catch (Exception e1) {
								try {
									if (membership_date != null && !membership_date.isEmpty()) {
										membership_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(membership_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
					
						Date dateof_pep1 = null;
						try {
							if (dateof_pep != null && !dateof_pep.isEmpty()) {
								dateof_pep1 = new SimpleDateFormat("dd/mm/yyyy").parse(membership_date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (dateof_pep != null && !dateof_pep.isEmpty()) {
									dateof_pep1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(membership_date);
								}
							} catch (Exception e1) {
								try {
									if (dateof_pep != null && !dateof_pep.isEmpty()) {
										dateof_pep1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(membership_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						Date date_of_resgn1 = null;
						try {
							if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
								date_of_resgn1 = new SimpleDateFormat("dd/mm/yyyy").parse(date_of_resgn);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
									date_of_resgn1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_resgn);
								}
							} catch (Exception e1) {
								try {
									if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
										date_of_resgn1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_resgn);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
					
						Date date_of_close1 = null;
						try {
							if (date_of_close != null && !date_of_close.isEmpty()) {
								date_of_close1 = new SimpleDateFormat("dd/mm/yyyy").parse(date_of_close);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (date_of_close != null && !date_of_close.isEmpty()) {
									date_of_close1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_close);
								}
							} catch (Exception e1) {
								try {
									if (date_of_close != null && !date_of_close.isEmpty()) {
										date_of_close1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_close);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						
						Date acc_opn_date1 = null;
						try {
							if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
								acc_opn_date1 = new SimpleDateFormat("dd/mm/yyyy").parse(acc_opn_date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
									acc_opn_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(acc_opn_date);
								}
							} catch (Exception e1) {
								try {
									if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
										acc_opn_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(acc_opn_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						Date pep_date = null;
						try {
							pep_date = new SimpleDateFormat("dd/MM/yyyy").parse(new Date().toString());
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the PEP_DATE field of cif_id -" + cif_id
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						
						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";
						String acid ="";
						Cust_Pep_List_Entity info = new Cust_Pep_List_Entity(cif_id, cust_surname, cust_name, nic,
								risk_cat, pep_desc, membership_date1, dateof_pep1, date_of_resgn1, date_of_close1,
								resgn_reason, act_prod_type, scheme_type, scheme_code, acc_id, acc_opn_date1, pep_type,remarks,pep_date,
								cust_position, entry_user, entry_time, entity_flg, del_flg,acid);

						List<Cust_Pep_List_Entity> info1=null;
						info1=cust_pep_list_Repository.findByforacid(acc_id);
						
						if (info1 != null && !info1.isEmpty()) {
							Errormsg = Errormsg + acc_id ;
						} else {
							if (!info.getCust_name().equals(null) && !info.getCust_name().isEmpty() && !info.getAcid().equals(null) && !info.getAcid().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();

//								DecimalFormat numformate = new DecimalFormat("0");
//								BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT PEP_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//								String serialno =  numformate.format(billNumber);
//								info.setPep_id(serialno);
								
								cust_pep_list_Repository.save(info);
								status = "successfully uploaded";
							} else {
								Errormsg = Errormsg + "," + cif_id;
							}

//					theSession.flush();
//					theSession.clear();
						}


					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into PEP fund ");

			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		if (!Errormsg.isEmpty()) {

			return "Uploaded Successfully Except - Account No -"+Errormsg +" Already Exists.";
		} else {
			return status;
		}

	}
public static File multipartToFile(MultipartFile multipart, String fileName)
			throws IllegalStateException, IOException {
	
	
	Path newFile = Paths.get(multipart.getOriginalFilename());
	  try(InputStream is = multipart.getInputStream();
	     OutputStream os = Files.newOutputStream(newFile)) {
	     byte[] buffer = new byte[4096];
	     int read = 0;
	     while((read = is.read(buffer)) > 0) {
	       os.write(buffer,0,read);
	     }
	  }
	  return newFile.toFile();  
	
	
//		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//		multipart.transferTo(convFile);
//		return convFile;
	}


public String processUpload(String asondate, MultipartFile files, String userid)
		throws SQLException, FileNotFoundException, IOException {

	String result = "";

	String status = "";

	MultipartFile uploadedFile = files;
	
	File folder = new File("C:/Users/kalid/Favorites/");
	File[] listOfFiles = folder.listFiles();

	for (File file : listOfFiles) {
	    if (file.isFile()) {
	        System.out.println(file.getName());
	    }
	}
	
	System.out.println(uploadedFile+asondate+userid);
	result = T8Upload(uploadedFile, asondate, userid);

	return result;
}

public String T8Upload(MultipartFile file, String asondate, String userid)
		throws SQLException, FileNotFoundException, IOException {

	String fileName = file.getOriginalFilename();
	File convertedFile = multipartToFile(file, fileName);

	String fileExt = "";

	int i = fileName.lastIndexOf('.');
	if (i > 0) {
		fileExt = fileName.substring(i + 1);
	}

	logger.info("file extension : " + fileExt);

	String Errormsg = "";

	String status = "";

	Session theSession = sessionFactory.getCurrentSession();
	
	
	
	logger.info("truncating table: T8-MOD-TABLE");

	

	logger.info("T8-MOD-TABLE truncated");

	if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

		logger.info("reading values from Excel");

		String cellval = "";

		try (InputStream is = new FileInputStream(convertedFile);

				Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is)) {

			for (Sheet s : workbook) {

				int sheetNumber = workbook.getSheetIndex(s);
				if (sheetNumber == 0) {

					logger.info("inside workbook");

					for (Row r : s) {

						ArrayList<String> resultList = new ArrayList<>();
						if (r.getRowNum() == 0) {
							continue;
						}

						cellval = "";
						String val = null;
						for (int j = 0; j < 29; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);
						}
						String cif_id = resultList.get(0);
						String cust_surname = resultList.get(1);
						String cust_name = resultList.get(2);
						String nic = resultList.get(3);
						String risk_cat = resultList.get(4);
						String pep_desc = resultList.get(5);
						String cust_position = resultList.get(6);
						String membership_date = resultList.get(7);
						String dateof_pep = resultList.get(8);
						String date_of_resgn = resultList.get(9);
						String date_of_close = resultList.get(10);
						String resgn_reason = resultList.get(11);
						String act_prod_type = resultList.get(12);
						String scheme_type = resultList.get(13);
						String scheme_code = resultList.get(14);
						String acc_id = resultList.get(15);
						String acc_opn_date = resultList.get(16);
						String pep_type= resultList.get(17);
						String remarks = resultList.get(18);
						String info1 = null;
						if(acc_id != null) {
						info1=cust_pep_list_Repository.findByforacidacc(acc_id);
						}else {
							Session hs = sessionFactory.getCurrentSession();
						
						DecimalFormat numformate = new DecimalFormat("0");
						BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT PEP_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
						info1 =  numformate.format(billNumber);
														
						
						}
						
						Date membership_date1 = null;

						
						try {
							if (membership_date != null && !membership_date.isEmpty()) {
								membership_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(membership_date);
							}else if (membership_date == null) {
								membership_date1 = new Date();
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (membership_date != null && !membership_date.isEmpty()) {
									membership_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(membership_date);
								}
							} catch (Exception e1) {
								try {
									if (membership_date != null && !membership_date.isEmpty()) {
										membership_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(membership_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
					
						Date dateof_pep1 = null;
						try {
							if (dateof_pep != null && !dateof_pep.isEmpty()) {
								dateof_pep1 = new SimpleDateFormat("dd/MM/yyyy").parse(membership_date);
							}else if (dateof_pep == null) {
								dateof_pep1 = new Date();
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (dateof_pep != null && !dateof_pep.isEmpty()) {
									dateof_pep1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(membership_date);
								}
							} catch (Exception e1) {
								try {
									if (dateof_pep != null && !dateof_pep.isEmpty()) {
										dateof_pep1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(membership_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						Date date_of_resgn1 = null;
						try {
							if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
								date_of_resgn1 = new SimpleDateFormat("dd/MM/yyyy").parse(date_of_resgn);
							}else if (date_of_resgn == null) {
								date_of_resgn1 = new Date();
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
									date_of_resgn1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_resgn);
								}
							} catch (Exception e1) {
								try {
									if (date_of_resgn != null && !date_of_resgn.isEmpty()) {
										date_of_resgn1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_resgn);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
					
						Date date_of_close1 = null;
						try {
							if (date_of_close != null && !date_of_close.isEmpty()) {
								date_of_close1 = new SimpleDateFormat("dd/MM/yyyy").parse(date_of_close);
							}else if (date_of_close == null) {
								date_of_close1 = new Date();
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (date_of_close != null && !date_of_close.isEmpty()) {
									date_of_close1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_close);
								}
							} catch (Exception e1) {
								try {
									if (date_of_close != null && !date_of_close.isEmpty()) {
										date_of_close1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_close);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						
						Date acc_opn_date1 = null;
						try {
							if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
								acc_opn_date1 = new SimpleDateFormat("dd/MM/yyyy").parse(acc_opn_date);
							}else if (acc_opn_date == null) {
								acc_opn_date1 = new Date();
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
									acc_opn_date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(acc_opn_date);
								}
							} catch (Exception e1) {
								try {
									if (acc_opn_date != null && !acc_opn_date.isEmpty()) {
										acc_opn_date1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(acc_opn_date);
									}
								} catch (Exception e111) {
	
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_id
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}
						}
						Date pep_date = new Date();
						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";
						Cust_Pep_List_Entity infoms = new Cust_Pep_List_Entity(cif_id, cust_surname, cust_name, nic,
								risk_cat, pep_desc, membership_date1, dateof_pep1, date_of_resgn1, date_of_close1,
								resgn_reason, act_prod_type, scheme_type, scheme_code, acc_id, acc_opn_date1, pep_type,remarks,pep_date,
								cust_position, entry_user, entry_time, entity_flg, del_flg,info1);

						logger.info("saving values:"+infoms);
						cust_pep_list_Repository.save(infoms);
						theSession.flush();
						theSession.clear();
					}

				}

				
			}
			
			
			status = "File Successfully Uploaded";
		}
		
		
		
		catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
	}

	return status;

}



public File getFile(String userid,String reportId, String fromdate, String todate, String currency, String dtltype,
		String filetype) throws FileNotFoundException, JRException, SQLException {
	Session hs = sessionFactory.getCurrentSession();
	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
	 SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
	 
	 
	 Date ConDateFromdate = null;
	try {
		ConDateFromdate = dateFormat1.parse(fromdate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 System.out.println(ConDateFromdate);
	
	 String strDate2 = formatter1.format(ConDateFromdate);
	 try {
		fromdate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	 Date ConToDate = null;
	try {
		ConToDate = dateFormat1.parse(todate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 System.out.println(ConToDate);
	   
	 String strDate1 = formatter1.format(ConToDate);
	 try {
		todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 
	String path = this.env.getProperty("output.exportpath");
	String fileName = "";
	
	File outputFile;

	logger.info("Getting Output file :" + reportId);

	fileName = reportId + "_" + dateFormat.format(new Date());

	


		try {
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + reportId);
			
				System.out.println("pdf");
			    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/PepList.jasper"); 
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("TO_DATE", todate);
			map.put("FROM_DATE", fromdate);

			logger.info("BEFORE GENERATING PDF :" + reportId);
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path +=  fileName;
				logger.info("BEFORE GENERATING PDF 1 :" + reportId);
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				logger.info("BEFORE GENERATING PDF 2 :" + path);
				JasperExportManager.exportReportToPdfFile(jp, path);
				logger.info("PDF File exported");
			}
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "FROM DATE-"+fromdate+"TO DATE-"+todate;
			
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

			audit.setAudit_date(new Date());
			audit.setEntry_user(userid);
			audit.setEntry_time(new Date());
			audit.setFunc_code("REPORT DOWNLOADED");
			audit.setRemarks("DOWNLOAD");
			audit.setAudit_table("CUST_PEP_LIST");
			audit.setAudit_screen("MONITORING PEP LISTING REPORT");
//			audit.setEvent_id(alertparam.getPep_id());
			audit.setEvent_name("REPORT"+"-"+"ADDED");
			audit.setModi_details(modi);
			
		
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	
	
	
	
	outputFile = new File(path);

	return outputFile;

}



public ByteArrayInputStream getFileExcel(String userid,String reportId, String fromdate, String todate, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Session hs = sessionFactory.getCurrentSession();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		String fromDAte = null;
		String toDAte = null;

		if (fromdate != null && !fromdate.isEmpty()) {
			Date ConDateFromdate = dateFormat1.parse(fromdate);
			String strDate2 = formatter1.format(ConDateFromdate);
			fromDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));

			Date ConToDate = dateFormat1.parse(todate);
			String strDate1 = formatter1.format(ConToDate);
			toDAte = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		}


		
		String fileName = "";

		FileOutputStream outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		try {
			InputStream fileStream = null;

			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("PEP_LIST");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLACK.getIndex());

			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row TitleRow = sheet.createRow(0);
			Cell cellTitle = TitleRow.createCell((short) 0);
			cellTitle.setCellValue("THE MAURITIUS CIVIL SERVICE MUTUAL AID ASSOCIATION LTD");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A1:M2"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellTitle.setCellStyle(headerCellStyle);
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
			
			Row ReportNameRow = sheet.createRow(2);
			Cell cellReporName = ReportNameRow.createCell((short) 0);
			cellReporName.setCellValue("PEP List Report");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A3:M3"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporName.setCellStyle(headerCellStyle);
			
			
			
			CellStyle reportDateCellStyle = workbook.createCellStyle();
			reportDateCellStyle.setFont(headerFont);
			
			Row Report_Date_Row = sheet.createRow(3);
			Cell cellReporDate = Report_Date_Row.createCell((short) 0);
			cellReporDate.setCellValue("From Date- "+fromDAte+" To Date- "+toDAte);
			sheet.addMergedRegion(CellRangeAddress.valueOf("A4:E4"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
//			reportDateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporDate.setCellStyle(reportDateCellStyle);
		
			Row headerRow = sheet.createRow(5);
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
			
			
			DataFormat fmt = workbook.createDataFormat();
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(
			    fmt.getFormat("@"));
			

			int rowNum = 5;
			int sn = 1;

			pepList = baml_cust_pep_rpt_repository.findAllCustPEPListReport(fromDAte, toDAte);
			for (BAML_Cust_PEP_RPT_Entity pep_List : pepList) {
				Row row = sheet.createRow(++rowNum);
				writeBook(pep_List, row, dateCellStyle,sn,cellStyle);
				sn++;
			}

			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			
			workbook.write(out);
			out.close();

			// Closing the workbook
			workbook.close();

			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "FROM DATE-" + fromdate + "TO DATE-" + todate;

			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

			audit.setAudit_date(new Date());
			audit.setEntry_user(userid);
			audit.setEntry_time(new Date());
			audit.setFunc_code("REPORT DOWNLOADED");
			audit.setRemarks("DOWNLOAD");
			audit.setAudit_table("CUST_PEP_LIST");
			audit.setAudit_screen("CUST_PEP  LISTING REPORT");
//			audit.setEvent_id(alertparam.getPep_id());
			audit.setEvent_name("REPORT" + "-" + "ADDED");
			audit.setModi_details(modi);

			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());

	}
private void writeBook(BAML_Cust_PEP_RPT_Entity aBook, Row row, CellStyle dateCellStyle,int sn,CellStyle dateCell) {

	Cell cell = row.createCell(0);
	cell.setCellValue(sn);
	cell.setCellStyle(dateCell);

	Cell cif = row.createCell(1);
	cif.setCellValue(aBook.getCIF());
	cif.setCellStyle(dateCell);

	Cell lastname = row.createCell(2);
	lastname.setCellValue(aBook.getCUST_LAST_NAME());
	lastname.setCellStyle(dateCell);
	
	Cell firstname = row.createCell(3);
	firstname.setCellValue(aBook.getCUST_FIRST_NAME());
	firstname.setCellStyle(dateCell);
	
	Cell nid = row.createCell(4);
	nid.setCellValue(aBook.getNAT_ID_CARD_NUM());
	nid.setCellStyle(dateCell);
	
	

	
	Cell risk_cat = row.createCell(5);
	risk_cat.setCellValue(aBook.getRISK_CATEGORY());
	risk_cat.setCellStyle(dateCell);
	

	
	Cell pep_desc = row.createCell(6);
	pep_desc.setCellValue(aBook.getPEP_DESC());
	pep_desc.setCellStyle(dateCell);


	
	Cell cust_pos = row.createCell(7);
		if (aBook.getCUST_POSITION().equals("001")) {
			cust_pos.setCellValue("ACTIVE");
		} else if (aBook.getCUST_POSITION().equals("002")) {
			cust_pos.setCellValue("INACTIVE");
		} else if (aBook.getCUST_POSITION().equals("004")) {
			cust_pos.setCellValue("N/A");
		} else if (aBook.getCUST_POSITION().equals("005")) {
			cust_pos.setCellValue("CLOSED");
		}else if (aBook.getCUST_POSITION().equals("DCSED")) {
			cust_pos.setCellValue("DCSED");
		}else {
			cust_pos.setCellValue(aBook.getCUST_POSITION());
		}
		cust_pos.setCellStyle(dateCell);
		

	Cell membershipDate = row.createCell(8);
	membershipDate.setCellValue(aBook.getMEMBERSHIP_DATE());
	membershipDate.setCellStyle(dateCellStyle);

	Cell dateofpep = row.createCell(9);
	dateofpep.setCellValue(aBook.getDATE_OF_PEP());
	dateofpep.setCellStyle(dateCellStyle);

	Cell dateofresgn = row.createCell(10);
	dateofresgn.setCellValue(aBook.getDATE_OF_RESIG());
	dateofresgn.setCellStyle(dateCellStyle);
	

	cell = row.createCell(11);
	cell.setCellValue(aBook.getRESIG_REASONS());

	cell = row.createCell(12);
	cell.setCellValue(aBook.getACTIVE_PRODUCT_TYPE());

}


}
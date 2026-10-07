package com.bornfire.Services;
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
import java.sql.SQLException;
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
import com.bornfire.entity.BAML_Cust_Blacklist_RPT_Entity;
import com.bornfire.entity.BAML_Cust_Blacklist_RPT_Repository;
import com.bornfire.entity.BAML_Cust_HNWI_RPT_Entity;
import com.bornfire.entity.BAML_Cust_HNWI_RPT_Repository;
import com.bornfire.entity.Cust_Hnwi_List_Entity;
import com.bornfire.entity.Cust_Hnwi_List_Repository;
import com.monitorjbl.xlsx.StreamingReader;

import au.com.bytecode.opencsv.CSVReader;
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
public class Cust_Hnwi_List_Service {

	@Autowired
	Cust_Hnwi_List_Repository cust_hnwi_list_Repository;
	
	private static final Logger logger = LoggerFactory.getLogger(Cust_Hnwi_List_Service.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	Environment env;
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	private static String[] columns = {"SL", "MASTER DATA ID", "CUSTOMER ID", "LAST NAME","FIRST NAME","NATIONAL ID","RISK CATEGORY","STATUS","BLACK LIST REASON NOTES","DATE FREEZED","DATE DEFREEZED","ACTIVE PROD TYPE"};
	   
	  private static List<BAML_Cust_HNWI_RPT_Entity> hnwi_List =  new ArrayList<BAML_Cust_HNWI_RPT_Entity>();
	 
	  @Autowired
		private BAML_Cust_HNWI_RPT_Repository baml_cust_hnwi_rpt_repository;
	  
	public String addHnwiList(Cust_Hnwi_List_Entity alertparam, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		Session hs = sessionFactory.getCurrentSession();
		if (formmode.equals("add")) {
			
			Cust_Hnwi_List_Entity up = alertparam;
			
			DecimalFormat numformate = new DecimalFormat("0");
//			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT HNWI_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//			String serialno =  numformate.format(billNumber);
//			up.setHnwi_id(serialno);
			
			up.setDel_flag("N");
			up.setEntity_flag("N");
			cust_hnwi_list_Repository.save(up);
			
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "RECORD ADDED";
			
//				modi = modi + (" HNWI_ID  " + up.getCif_id() + '+');
//				modi = modi + (" CIF_ID  " + alertparam.getCif_id() + '+');
//				modi = modi +(" CUSTOMER_SURNAME_NAME " + alertparam.getCust_short_name() + '+');
//				modi = modi +(" CUST_NAME  " + alertparam.getCust_name() + '+');
//				modi = modi +(" NID " + alertparam.getNat_id_card_num() + '+');
//				modi = modi +(" RISK CATEGORY " + alertparam.getRisk_category() + '+');
//				modi = modi +(" BUS_DESC " + alertparam.getBus_desc() + '+');
//				modi = modi +(" SECTOR " + alertparam.getSector() + '+');
//				modi = modi +(" START_DATE " + alertparam.getStart_date() + '+');
//				modi = modi +(" ACTIVE_PROD_TYPE " + alertparam.getActive_prod_type() + '+');
//				modi = modi +(" ACTIVE_PROD_TYPE " + alertparam.getSchm_type() + '+');
//				modi = modi +(" SCHM_CODE " + alertparam.getSchm_code() + '+');
//				modi = modi +(" FORACID " + alertparam.getForacid() + '+');
//				modi = modi +(" ACC_OPN_DATE " + alertparam.getAcct_open_date() + '+');
//				modi = modi +(" REMARKS1 " + alertparam.getRemarks1() + '+');
//				modi = modi +(" REMARKS2 " + alertparam.getRemarks2() + '+');
			
			
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("CUST_HNWI_LIST");
			audit.setAudit_screen("HNWI LISTING MASTER");
			audit.setEvent_id(alertparam.getAcid());
			audit.setEvent_name(alertparam.getAcid()+"-"+"ADDED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setEntry_time(alertparam.getAml_entry_time());
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);
			
			msg = "HNWI List Created Successfully";

		} else if (formmode.equals("edit")) {
//			Optional<Cust_Hnwi_List_Entity> reg = cust_hnwi_list_Repository.findById(alertparam.getHnwi_id());
//			if (reg.isPresent()) {
//				
//				Cust_Hnwi_List_Entity reg1 = new Cust_Hnwi_List_Entity();
//				reg1=reg.get();
////				System.out.println("old  =" + reg1.getModule() +" " +alertparam.getModule());
//				if ((reg1.getCif_id().equals(alertparam.getCif_id()))
//						&& (reg1.getCust_short_name().equals(alertparam.getCust_short_name()))
//						&& (reg1.getCust_name().equals(alertparam.getCust_name()))
//						&& (reg1.getNat_id_card_num().equals(alertparam.getNat_id_card_num()))
//						&& (reg1.getRisk_category().equals(alertparam.getRisk_category()))
//						&& (reg1.getBus_desc().equals(alertparam.getBus_desc()))
//						&& (reg1.getSector().equals(alertparam.getSector()))
//						&& (reg1.getStart_date().compareTo(alertparam.getStart_date()) == 0)
//						&& (reg1.getActive_prod_type().equals(alertparam.getActive_prod_type()))
//						&& (reg1.getSchm_type().equals(alertparam.getSchm_type()))
//						&& (reg1.getSchm_code().equals(alertparam.getSchm_code()))
//						&& (reg1.getForacid().equals(alertparam.getForacid()))
//						&& (reg1.getAcct_open_date().compareTo(alertparam.getAcct_open_date()) == 0)
//						&& (reg1.getRemarks1().equals(alertparam.getRemarks1()))
//						&& (reg1.getRemarks2().equals(alertparam.getRemarks2())) ) {
//					msg = "No Modification done";
//
//				} else {
//					
//					
//					String modi = "";
//
//					BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
//							.getSingleResult();
//					
//				
//					
//					if (!reg1.getCif_id().equals(alertparam.getCif_id())) {
//						modi = modi + ("OLD CIF_ID + " + reg1.getCif_id() + "+ OLD CIF_ID " + alertparam.getCif_id() + '+');
//					} else if (!reg1.getCust_short_name().equals(alertparam.getCust_short_name())) {
//						modi = modi +("OLD - CUSTOMER_SURNAME_NAME " + reg1.getCust_short_name() + "+ NEW - CUSTOMER_SURNAME_NAME " + alertparam.getCust_short_name() + '+');
//					} else if (!reg1.getCust_name().equals(alertparam.getCust_name())) {
//						modi = modi +("OLD CUST_NAME  " + reg1.getCust_name() + "+ NEW CUST_NAME " + alertparam.getCust_name() + '+');
//					}  else if (!reg1.getCust_name().equals(alertparam.getCust_name())) {
//						modi = modi +("OLD CUST_NAME  " + reg1.getCust_name() + "+ NEW CUST_NAME " + alertparam.getCust_name() + '+');
//					}  else if (!reg1.getNat_id_card_num().equals(alertparam.getNat_id_card_num())) {
//							modi = modi +("OLD NID " + reg1.getNat_id_card_num() + "+ NEW NID " + alertparam.getNat_id_card_num() + '+');
//					} else if (!reg1.getRisk_category().equals(alertparam.getRisk_category())) {
//						modi = modi +("OLD RISK CATEGORY  " + reg1.getRisk_category() + "+ NEW RISK CATEGORY " + alertparam.getRisk_category() + '+');
//					} 
//					else if (!reg1.getBus_desc().equals(alertparam.getBus_desc())) {
//						modi = modi +("OLD BUS_DESC " + reg1.getBus_desc() + "+ NEW  BUS_DESC " + alertparam.getBus_desc() + '+');
//					}else if (!reg1.getSector().equals(alertparam.getSector())) {
//						modi = modi +("OLD SECTOR " + reg1.getSector() + "+ NEW  SECTOR " + alertparam.getSector() + '+');
//					} else if (!reg1.getSector().equals(alertparam.getSector())) {
//						modi = modi +("OLD START_DATE  " + reg1.getStart_date() + "+ NEW  START_DATE " + alertparam.getStart_date() + '+');
//					} else if (!reg1.getActive_prod_type().equals(alertparam.getActive_prod_type())) {
//						modi = modi +("OLD ACTIVE_PROD_TYPE " + reg1.getActive_prod_type() + "+ NEW ACTIVE_PROD_TYPE " + alertparam.getActive_prod_type() + '+');
//					} else if (!reg1.getSchm_type().equals(alertparam.getSchm_type())) {
//							modi = modi +("OLD ACTIVE_PROD_TYPE " + reg1.getSchm_type() + "+ NEW ACTIVE_PROD_TYPE " + alertparam.getSchm_type() + '+');
//					} else if (!reg1.getSchm_code().equals(alertparam.getSchm_code())) {
//						modi = modi +("OLD SCHM_CODE " + reg1.getSchm_code() + "+ NEW SCHM_CODE " + alertparam.getSchm_code() + '+');
//					} else if (!reg1.getForacid().equals(alertparam.getForacid())) {
//						modi = modi +("OLD FORACID " + reg1.getForacid() + "+ NEW FORACID " + alertparam.getForacid() + '+');
//					} else if (!reg1.getAcct_open_date().equals(alertparam.getAcct_open_date())) {
//						modi = modi +("OLD ACC_OPN_DATE  " + reg1.getAcct_open_date() + "+ NEW ACC_OPN_DATE " + alertparam.getAcct_open_date() + '+');
//					}else if (!reg1.getRemarks1().equals(alertparam.getRemarks1())) {
//						modi = modi +("OLD REMARKS1 " + reg1.getRemarks1() + "+ NEW REMARKS1 " + alertparam.getRemarks1() + '+');
//					}else if (!reg1.getRemarks2().equals(alertparam.getRemarks2())) {
//						modi = modi +("OLD REMARKS2 " + reg1.getRemarks2() + "+ NEW REMARKS2 " + alertparam.getRemarks2() + '+');
//					}else if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
//						modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag() + '+');
//					}else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
//						modi = modi +("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG " + alertparam.getModify_flag() + '+');
//					}else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
//						modi = modi +("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG " + alertparam.getEntity_flag() + '+');
//					}
//						
//					audit.setAudit_date(new Date());
//					audit.setEntry_user(alertparam.getAml_entry_user());
//					audit.setFunc_code("RECORD MODIFIED");
//					audit.setRemarks("MODIFIED");
//					audit.setAudit_table("CUST_HNWI_LIST");
//					audit.setAudit_screen("HNWI LISTING MASTER");
//					audit.setEvent_id(alertparam.getHnwi_id());
//					audit.setEvent_name(alertparam.getHnwi_id()+"-"+"MODIFIED");
//					audit.setModi_details(modi);
//					audit.setEntry_user(alertparam.getAml_modify_user());
//					audit.setEntry_time(alertparam.getAml_modify_time());
//					audit.setAudit_ref_no(Number.toString());
					
					
					
//					Cust_Hnwi_List_Entity up = alertparam;
//					if(alertparam.getRisk_category()==null || alertparam.getRisk_category().isEmpty()) {
//						alertparam.setRisk_category(null);
//					}
					alertparam.setDel_flag("N");
					alertparam.setModify_flag("Y");
					alertparam.setEntity_flag("N");
					cust_hnwi_list_Repository.save(alertparam);
					msg = "HNWI List Edited Successfully";
					
//					auditLocal.save(audit);
					
					
//				}
//			}
		} else if (formmode.equals("delete")) {
			Optional<Cust_Hnwi_List_Entity> reg = cust_hnwi_list_Repository.findById(alertparam.getAcid());
			if (reg.isPresent()) {
				Cust_Hnwi_List_Entity reg1 = new Cust_Hnwi_List_Entity();
				reg1=reg.get();
			
			String modi="";
			if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
				modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG Y +");
			}else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
				modi = modi +("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG " + alertparam.getModify_flag() + '+');
			}else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
				modi = modi +("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG " + alertparam.getEntity_flag() + '+');
			}
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD DELETED");
			audit.setRemarks("DELETED");
			audit.setAudit_table("CUST_HNWI_LIST");
			audit.setAudit_screen("HNWI LISTING MASTER");
			audit.setEvent_id(alertparam.getAcid());
			audit.setEvent_name(alertparam.getAcid()+"-"+"DELETED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_modify_user());
			audit.setEntry_time(alertparam.getAml_modify_time());
			audit.setAudit_ref_no(Number.toString());
			Cust_Hnwi_List_Entity up = alertparam;
//			if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
//				up.setRisk_category(null);
//			}
			
			
			up.setDel_flag("Y");
			up.setEntity_flag("N");
			cust_hnwi_list_Repository.save(up);
			msg = "HNWI List Deleted Successfully";
			
			auditLocal.save(audit);
			
			}
			
		} else if (formmode.equals("verify")) {
			
			Optional<Cust_Hnwi_List_Entity> reg = cust_hnwi_list_Repository.findById(alertparam.getAcid());
			if (reg.isPresent()) {
				Cust_Hnwi_List_Entity reg1 = new Cust_Hnwi_List_Entity();
				reg1=reg.get();
			
			String modi="";
			if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
				modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag() + '+');
			}else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
				modi = modi +("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG " + alertparam.getModify_flag() + '+');
			}else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
				modi = modi +("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG Y +");
			}
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD DELETED");
			audit.setRemarks("DELETED");
			audit.setAudit_table("CUST_HNWI_LIST");
			audit.setAudit_screen("HNWI LISTING MASTER");
			audit.setEvent_id(alertparam.getAcid());
			audit.setEvent_name(alertparam.getAcid()+"-"+"DELETED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_verify_user());
			audit.setEntry_time(alertparam.getAml_verify_time());
			audit.setAudit_ref_no(Number.toString());
			
			
//			if(alertparam.getRisk_category()==null || alertparam.getRisk_category().isEmpty()) {
//				alertparam.setRisk_category(null);
//			}
			
			
			alertparam.setEntity_flag("Y");
			alertparam.setDel_flag("N");
			cust_hnwi_list_Repository.save(alertparam);
			msg = "HNWI List Verified Successfully";
			
			auditLocal.save(audit);
			
			}
		}
		return msg;
	}

	public Cust_Hnwi_List_Entity getSrlNo(String srlno) {

		if (cust_hnwi_list_Repository.existsById(srlno)) {
			Cust_Hnwi_List_Entity up = cust_hnwi_list_Repository.findById(srlno).get();
			return up;
		} else {
			return new Cust_Hnwi_List_Entity();
		}

	};

	public String deleteParameter(String inputSrlNo) {
		String msg = "";
		Optional<Cust_Hnwi_List_Entity> user = cust_hnwi_list_Repository.findById(inputSrlNo);
		Cust_Hnwi_List_Entity reg = user.get();
		reg.setDel_flag("N");
		/* montParameterRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String inputSrlNo) {
		String msg = "";
		Optional<Cust_Hnwi_List_Entity> user = cust_hnwi_list_Repository.findById(inputSrlNo);
		Cust_Hnwi_List_Entity reg = user.get();
		
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
		
	/*
	 * public void updateNegative_list_Num() { Session hs =
	 * sessionFactory.getCurrentSession(); // after save increement the num table
	 * value by 1 cust_hnwi_list_Repository.updatePepNumTB();
	 * 
	 * }
	 */
	public String processUpload(String screenId,MultipartFile file, String userid) throws IllegalStateException, IOException
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

//		logger.info("truncating table: Negative List");

		Session theSession = sessionFactory.getCurrentSession();
		
//		theSession.createSQLQuery(" truncate table NEGATIVE_LIST ").executeUpdate();

//		logger.info("NEGATIVE_LIST truncated");

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
						for (int j = 0; j < 15; j++) {
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
						String bus_desc = resultList.get(5);
						String sector = resultList.get(6);
						String start_date = resultList.get(7);
						String act_prod_type = resultList.get(8);
						String scheme_type = resultList.get(9);
						String scheme_code = resultList.get(10);
						String acc_Id = resultList.get(11);
						String acc_opn_date = resultList.get(12);
						String remarks1 = resultList.get(13);
						String remarks2 = resultList.get(14);

						Date start_date1 = null;
						try {
							if(start_date!=null && !start_date.isEmpty()) {
							 start_date1=new SimpleDateFormat("dd/MM/yy").parse(start_date);
							}
						}catch(Exception e) {
							Errormsg = "failed "+"Please fix the Start Date of cif_id -"+ cif_id +" Fromat to (DD/MM/YYYY)";
							continue;
							 
						}
						Date acc_op_date1 = null;
						try {
							if(acc_opn_date!=null && !acc_opn_date.isEmpty()) {
								acc_op_date1=new SimpleDateFormat("dd/MM/yy").parse(acc_opn_date);
							}
						}catch(Exception e) {
							Errormsg = "failed "+"Please fix the Acc Opening Date of cif_id -"+ cif_id +" Fromat to (DD/MM/YYYY)";
							continue;
							 
						}
						
						
						
						String entry_user=userid;
						Date entry_time= new Date();
						String entity_flg="Y";
						String del_flg="N";
						

						Cust_Hnwi_List_Entity info = new Cust_Hnwi_List_Entity(cif_id, cust_surname,cust_name, nic,
								risk_cat, bus_desc,sector, start_date1, act_prod_type, scheme_type,scheme_code,acc_Id,acc_op_date1,
								remarks1,remarks2, entry_user,entry_time,entity_flg,del_flg);

						List<Cust_Hnwi_List_Entity> info1=null;
						info1=cust_hnwi_list_Repository.findByforNatID(nic,acc_Id);
						
						if(info1 != null && !info1.isEmpty()) {
							Errormsg=Errormsg+"  Cif_Id "+cif_id +" already exists" ;
						} else {
							if(!info.getCif_id().equals(null) && !info.getCif_id().isEmpty() && !info.getAcid().equals(null) && !info.getAcid().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();
								
								
//								DecimalFormat numformate = new DecimalFormat("0");
//								BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT HNWI_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//								String serialno =  numformate.format(billNumber);
//								info.getAcid(serialno);
								status = "successfully uploaded";
								
								cust_hnwi_list_Repository.save(info);
								
								 AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
								BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
										.getSingleResult();
								String modi = "RECORD ADDED";
								audit.setAudit_date(new Date());
								audit.setEntry_user(info.getAml_entry_user());
								audit.setFunc_code("RECORD CREATED");
								audit.setRemarks("ADDED");
								audit.setAudit_table("CUST_HNWI_LIST");
								audit.setAudit_screen("HNWI LISTING MASTER");
								audit.setEvent_id(info.getAcid());
								audit.setEvent_name(info.getAcid()+"-"+"ADDED");
								audit.setModi_details(modi);
								audit.setEntry_user(info.getAml_entry_user());
								audit.setEntry_time(info.getAml_entry_time());
								audit.setAudit_ref_no(Number.toString());
								auditLocal.save(audit);
								
								
								status = "successfully uploaded";
							}else {
								Errormsg=Errormsg+","+cif_id ;
							}
						
//							theSession.flush();
//							theSession.clear();
						}
					}

					logger.info("inserted values into HNWI Fund List");
				}

			
			} catch (Exception e) {
				
			}
		}  else {
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
						String bus_desc = nextLine[5];
						String sector = nextLine[6];
						String start_date = nextLine[7];
						String act_prod_type =nextLine[8];
						String scheme_type = nextLine[9];
						String scheme_code = nextLine[10];
						String acc_Id =nextLine[11];
						String acc_opn_date = nextLine[12];
						String remarks1 = nextLine[13];
						String remarks2 =nextLine[14];

						
						try {
							Date start_date1=new SimpleDateFormat("dd/MM/yy").parse(start_date);
						}catch (Exception e) {
							// TODO: handle exception
						}
						Date start_date1=new SimpleDateFormat("dd/MM/yy").parse(start_date);
						Date acc_op_date1=new SimpleDateFormat("dd/MM/yy").parse(acc_opn_date);
						
						
					    
						
						String entry_user=userid;
						Date entry_time= new Date();
						String entity_flg="Y";
						String del_flg="N";
						

						Cust_Hnwi_List_Entity sup1000ManualS1 = new Cust_Hnwi_List_Entity(cif_id, cust_surname,cust_name, nic,
								risk_cat, bus_desc,sector, start_date1, act_prod_type, scheme_type,scheme_code,acc_Id,acc_op_date1,
								remarks1,remarks2, entry_user,entry_time,entity_flg,del_flg);

						List<Cust_Hnwi_List_Entity> info1=null;
						info1=cust_hnwi_list_Repository.findByforNatID(nic,acc_Id);
						
						if(info1 != null && !info1.isEmpty()) {
							Errormsg=Errormsg+"  Cif_Id "+cif_id +" already exists" ;
						} else {
							Session hs = sessionFactory.getCurrentSession();
							
							
//							DecimalFormat numformate = new DecimalFormat("0");
//							BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT HNWI_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//							String serialno =  numformate.format(billNumber);
//							sup1000ManualS1.setHnwi_id(serialno);
							if(sup1000ManualS1 !=null && !sup1000ManualS1.getAcid().isEmpty()) {
							cust_hnwi_list_Repository.save(sup1000ManualS1);
							}
							status = "successfully uploaded";
//							theSession.flush();
//							theSession.clear();
						}

					
					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into HNWI fund ");
				
			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		if(!Errormsg.isEmpty()) {
			
			return Errormsg;
		}else {
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
		
		
//			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//			multipart.transferTo(convFile);
//			return convFile;
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

		String strDate1 = formatter1.format(ConToDate);
		try {
			todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String path =  env.getProperty("output.exportpath");
		String fileName = "";

		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());


			try {
				InputStream fileStream = null;
				logger.info("Getting Jasper file :" + reportId);
					fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/HnwiList.jasper");

				JasperReport jr =  (JasperReport) JRLoader.loadObject(fileStream);
				
				
				
				
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("TODATE", todate);
				map.put("FROMDATE", fromdate);

				logger.info("BEFORE GENERATING PDF :" + reportId);
					fileName = fileName + ".pdf";
					path += fileName;
					logger.info("BEFORE GENERATING PDF 1 :" + reportId);
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					logger.info("BEFORE GENERATING PDF 2 :" + path);
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");

//				BigDecimal Number = (BigDecimal) hs
//						.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//				String modi = "FROM DATE-" + fromdate + "TO DATE-" + todate;
//
//				AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
//
//				audit.setAudit_date(new Date());
//				audit.setEntry_user(userid);
//				audit.setEntry_time(new Date());
//				audit.setFunc_code("REPORT DOWNLOADED");
//				audit.setRemarks("DOWNLOAD");
//				audit.setAudit_table("CUST_HNWI_LIST_TEMP");
//				audit.setAudit_screen("MONITORING PEP LISTING REPORT");
////				audit.setEvent_id(alertparam.getPep_id());
//				audit.setEvent_name("REPORT" + "-" + "ADDED");
//				audit.setModi_details(modi);
//
//				audit.setAudit_ref_no(Number.toString());
//				auditLocal.save(audit);

			} catch (Exception e) {
				e.printStackTrace();
			}


		outputFile = new File(path);

		return outputFile;

	}

	public ByteArrayInputStream getFile_HNWI_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
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
			Sheet sheet = workbook.createSheet("BLACK_LIST_CORP__LIST");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLACK.getIndex());

			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row TitleRow = sheet.createRow(0);
			Cell cellTitle = TitleRow.createCell((short) 0);
			cellTitle.setCellValue("THE MAURITIUS CIVIL SERVICE MUTUAL AID ASSOCIATION LTD");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A1:J2"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellTitle.setCellStyle(headerCellStyle);
//			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
			
			Row ReportNameRow = sheet.createRow(2);
			Cell cellReporName = ReportNameRow.createCell((short) 0);
			cellReporName.setCellValue("HNWI List Report");
			sheet.addMergedRegion(CellRangeAddress.valueOf("A3:J3"));
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellReporName.setCellStyle(headerCellStyle);
			
			
			
			CellStyle reportDateCellStyle = workbook.createCellStyle();
			reportDateCellStyle.setFont(headerFont);
			
			Row Report_Date_Row = sheet.createRow(3);
			Cell cellReporDate = Report_Date_Row.createCell((short) 0);
			cellReporDate.setCellValue("Date- "+dateFormat.format(new Date()));
			sheet.addMergedRegion(CellRangeAddress.valueOf("A4:E4"));
			reportDateCellStyle.setAlignment(HorizontalAlignment.LEFT);
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
			cellStyle.setDataFormat(fmt.getFormat("@"));
			
			DataFormat fmt1 = workbook.createDataFormat();
			CellStyle numStyle = workbook.createCellStyle();
			numStyle.setDataFormat(fmt1.getFormat("###,0.00"));
			

			int rowNum = 5;
			int sn = 1;

			hnwi_List = baml_cust_hnwi_rpt_repository.findAllCustHNWIListReport(fromDAte,toDAte);
			for (BAML_Cust_HNWI_RPT_Entity pep_List : hnwi_List) {
				Row row = sheet.createRow(++rowNum);
				writeBook(pep_List, row, dateCellStyle,sn,cellStyle,numStyle);
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
			audit.setAudit_table("BAML_CUST_ABAND_FUND_LIST");
			audit.setAudit_screen("ABANDONED FUND LISTING REPORT");
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

		private void writeBook(BAML_Cust_HNWI_RPT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
				CellStyle dateCell, CellStyle numStyle) {

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

			Cell riskcat = row.createCell(5);
			riskcat.setCellValue(aBook.getRISK_CATEGORY());
			riskcat.setCellStyle(dateCell);


			Cell freezed = row.createCell(6);
			freezed.setCellValue(aBook.getSTART_DATE());
			freezed.setCellStyle(dateCellStyle);
			
			
			Cell type = row.createCell(7);
			type.setCellValue(aBook.getACTIVE_PRODUCT_TYPE());
			type.setCellStyle(dateCell);

		}


}
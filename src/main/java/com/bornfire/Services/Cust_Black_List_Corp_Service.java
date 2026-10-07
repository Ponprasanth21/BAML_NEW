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
import com.bornfire.entity.Cust_Aband_Fund_List_Entity;
import com.bornfire.entity.Cust_Black_List_Corp_Entity;
import com.bornfire.entity.Cust_Black_List_Corp_Repository;
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
public class Cust_Black_List_Corp_Service {

	@Autowired
	Cust_Black_List_Corp_Repository cust_black_list_Corp_Repository;
	private static final Logger logger = LoggerFactory.getLogger(Cust_Black_List_Corp_Service.class);
	
	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	Environment env;
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;
	
	  private static String[] columns = {"SL", "MASTER DATA ID", "CUSTOMER ID", "LAST NAME","FIRST NAME","BRN","RISK CATEGORY","STATUS","BLACK LIST REASON NOTES","DATE FREEZED","DATE DEFREEZED","ACTIVE PROD TYPE"};
	   
	  private static List<BAML_Cust_Blacklist_RPT_Entity> corp_List =  new ArrayList<BAML_Cust_Blacklist_RPT_Entity>();
	 
	  @Autowired
		private BAML_Cust_Blacklist_RPT_Repository baml_cust_blacklist_rpt_repository;
	
	@Autowired
	SessionFactory sessionFactory;

	public String addBlack_IND_List(Cust_Black_List_Corp_Entity alertparam, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		Session hs = sessionFactory.getCurrentSession();
		if (formmode.equals("Corp_add")) {
			Cust_Black_List_Corp_Entity up = alertparam;
			
			DecimalFormat numformate = new DecimalFormat("0");
			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT CUST_BLACKLIST_CORP_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//			String serialno =  numformate.format(billNumber);
			up.setBlack_list_corp_data_setid(billNumber);
			
			if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
				up.setRisk_category(null);
			}
			up.setDel_flag("N");
			up.setEntity_flag("N");
			cust_black_list_Corp_Repository.save(up);
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "RECORD ADDED";
			
//				modi = modi + (" BLACK LIST CORP SET ID  " + alertparam.getBlack_list_corp_data_setid() + '+');
//				modi = modi +(" BANK ID " + alertparam.getBank_id() + '+');
//				modi = modi +(" UNIQUE REF ID  " + alertparam.getDummy_1() + '+');
//				modi = modi +(" ALT SHORT NAME " + alertparam.getShortname_alt1() + '+');
//				modi = modi +(" PREFERRED FORMAT " + alertparam.getPreferredformat() + '+');
//				modi = modi +(" REMARKS1 " + alertparam.getRemarks1() + '+');
//				modi = modi +(" ALT_CORPORATE_NAME " + alertparam.getFirstname_alt1() + '+');
//				modi = modi +(" NATIVE_CORPORATE_NAME " + alertparam.getCorporate_name_native() + '+');
//				modi = modi +(" NATIVE_SHORT_NAME " + alertparam.getShortname_native() + '+');
//				modi = modi +(" CORPORATE_ID " + alertparam.getCorp_id() + '+');
//				modi = modi +(" CORPORATE_KEY " + alertparam.getCorp_key() + '+');
//				modi = modi +(" ENTITY_TYPE " + alertparam.getEntity_type() + '+');
//				modi = modi +(" CONTACT_ID " + alertparam.getContactid() + '+');
//				modi = modi +(" MASTER_DATA_ID " + alertparam.getMasterdataid() + '+');
//				modi = modi +(" CIF_ID " + alertparam.getCifid() + '+');
//				modi = modi +(" ENTITY_BRANCH_NAME " + alertparam.getEntityboname() + '+');
//				modi = modi +(" SOURCE " + alertparam.getSource() + '+');
//				modi = modi +(" BL REASON NOTES " + alertparam.getBlack_list_reason_notes() + '+');
//				modi = modi +(" RISK_CATEGORY " + alertparam.getRisk_category() + '+');
//				modi = modi +(" SECTOR " + alertparam.getSector() + '+');
//				modi = modi +(" STATUS " + alertparam.getStatus() + '+');
//				modi = modi +(" DATE_FREEZED " + alertparam.getDate_freezed() + '+');
//				modi = modi +(" DATE_DEFREEZED " + alertparam.getDate_defreezed() + '+');
//				modi = modi +(" ACT_PROD_TYPE " + alertparam.getActive_product_type() + '+');
//				modi = modi +(" FIELD_ID " + alertparam.getFileid() + '+');
//				modi = modi +(" EXTERNAL_ENTITY_ID " + alertparam.getExternalentityid() + '+');
//				modi = modi +(" REMARKS 2 " + alertparam.getRemarks2() + '+');
//				modi = modi +(" FIRST_NAME " + alertparam.getFirstname() + '+');
//				modi = modi +(" ADDRESS_TYPE " + alertparam.getAddress_type() + '+');
//				modi = modi +(" DATE_OF_INCORP " + alertparam.getDate_of_incorp() + '+');
//				modi = modi +(" ADDRS_LINE1 " + alertparam.getAddressline1() + '+');
//				modi = modi +(" ADDRS_LINE2 " + alertparam.getAddressline2() + '+');
//				modi = modi +(" ADDRS_LINE3 " + alertparam.getAddressline3() + '+');
//				modi = modi +(" PAN " + alertparam.getPanno() + '+');
//				modi = modi +(" SSN " + alertparam.getSsn() + '+');
//				modi = modi +(" PASSPORT_NO " + alertparam.getPassportno() + '+');
//				modi = modi +(" CREDIT_CARD_NO " + alertparam.getCreditcardno() + '+');
//				modi = modi +(" NATIOAL_ID " + alertparam.getNationalid() + '+');
//				modi = modi +(" DRIVER_LICENSE_NO " + alertparam.getDriverlicenseno() + '+');
//				modi = modi +(" HOME_PHONE_NO " + alertparam.getHome_phoneno() + '+');
//				modi = modi +(" WORK_PHONE_NO " + alertparam.getWorkphoneno() + '+');
//				modi = modi +(" WORK_EMAIL " + alertparam.getWork_email() + '+');
//				modi = modi +(" HOME_MAIL " + alertparam.getHome_email() + '+');
//				modi = modi +(" WORK_FAX " + alertparam.getWork_fax() + '+');
//				modi = modi +(" WEBSITE " + alertparam.getWeb_site() + '+');
//				modi = modi +(" NON_CUSTOMERID " + alertparam.getNoncustomerid() + '+');
//				modi = modi +(" PHONE " + alertparam.getPhone() + '+');
//				modi = modi +(" EMAIL " + alertparam.getEmail() + '+');
//				modi = modi +(" HOUSE_NO " + alertparam.getHouseno() + '+');
//				modi = modi +(" PREMISE_NAME " + alertparam.getPremise_name() + '+');
//				modi = modi +(" BUILDING_NAME " + alertparam.getBuildingname() + '+');
//				modi = modi +(" BUILDING_LEVEL " + alertparam.getBuilding_level() + '+');
//				modi = modi +(" STREET_NO " + alertparam.getStreet_no() + '+');
//				modi = modi +(" STREET_NAME " + alertparam.getStreet_name() + '+');
//				modi = modi +(" LOCALITY_NAME " + alertparam.getLocality_name() + '+');
//				modi = modi +(" TOWN " + alertparam.getTown() + '+');
//				modi = modi +(" DOMICILE " + alertparam.getDomicile() + '+');
//				modi = modi +(" STATE " + alertparam.getState() + '+');
//				modi = modi +(" ZIP " + alertparam.getZip() + '+');
//				modi = modi +(" COUNTRY_OF_INCORP " + alertparam.getCountry_of_incorp() + '+');
//				modi = modi +(" COUNTRY_OF_PRINCIPAL_BUSINESS " + alertparam.getCountry_of_principle_business() + '+');
//				modi = modi +(" MAIL_STOP " + alertparam.getMail_stop() + '+');
//				modi = modi +(" NATURE_OF_BUSINESS " + alertparam.getNature_of_business() + '+');
//				modi = modi +(" START_DATE " + alertparam.getStart_date() + '+');
//				modi = modi +(" END_DATE " + alertparam.getEnd_date() + '+');
//				modi = modi +(" WORK_EXTENSIONS " + alertparam.getWork_extension() + '+');
//				modi = modi +(" bo_acl_id " + alertparam.getBoaclid() + '+');
//				modi = modi +(" REMARKS_3 " + alertparam.getRemarks3() + '+');
//				modi = modi +(" PHONE_NO_1 " + alertparam.getPhone_no_1() + '+');
//				modi = modi +(" PHONE_NO_1_CITY_CODE " + alertparam.getPhone_no1_city_code() + '+');
//				modi = modi +(" PHONE_NO_1_COUNTRY_CODE " + alertparam.getPhone_no1_country_code() + '+');
//				modi = modi +(" PHONE_NO_2 " + alertparam.getPhone_no_2() + '+');
//				modi = modi +(" PHONE_NO_2_CITY_CODE " + alertparam.getPhone_no2_city_code() + '+');
//				modi = modi +(" PHONE_NO_2_COUNTRY_CODE " + alertparam.getPhone_no2_country_code() + '+');
//				modi = modi +(" CELL " + alertparam.getCell() + '+');
//				modi = modi +(" CELL_CITY_CODE " + alertparam.getCell_city_code() + '+');
//				modi = modi +(" CELL_COUNTRY_CODE " + alertparam.getCell_country_code() + '+');
//				modi = modi +(" FAX " + alertparam.getFax() + '+');
//				modi = modi +(" FAX_CITY_CODE " + alertparam.getFax_city_code() + '+');
//				modi = modi +(" FAX_COUNTRY_CODE " + alertparam.getFax_country_code() + '+');
//				modi = modi +(" BO CREATED DATE " + alertparam.getBodatecreated() + '+');
//				modi = modi +(" BO CREATED USER " + alertparam.getBocreatedby() + '+');
//				modi = modi +(" BO VERIFIED DATE " + alertparam.getBodateverified() + '+');
//				modi = modi +(" BO VERIFIED USER " + alertparam.getBoverifiedby() + '+');
//				modi = modi +(" BO MODIFIED DATE " + alertparam.getBodatemodified() + '+');
//				modi = modi +(" BO MODIFIED USER " + alertparam.getBomodifiedby() + '+');
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("CUST_BLACKLIST_COR");
			audit.setAudit_screen("BLACK_LIST CORPORATE LISTING MASTER");
			audit.setEvent_id(alertparam.getBlack_list_corp_data_setid().toString());
			audit.setEvent_name(alertparam.getBlack_list_corp_data_setid()+"-"+"ADDED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setEntry_time(alertparam.getAml_entry_time());
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);

			msg = "Black List Created Successfully";

		} else if (formmode.equals("Corp_edit")) {
			Cust_Black_List_Corp_Entity up = alertparam;
			if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
				up.setRisk_category(null);
			}
			up.setDel_flag("N");
			up.setModify_flag("Y");
			up.setEntity_flag("N");
			cust_black_list_Corp_Repository.save(up);
			msg = "Black List Edited Successfully";
		} else if (formmode.equals("Corp_delete")) {
			
			Optional<Cust_Black_List_Corp_Entity> reg = cust_black_list_Corp_Repository.findById(alertparam.getBlack_list_corp_data_setid());
			if (reg.isPresent()) {
				Cust_Black_List_Corp_Entity reg1 = new Cust_Black_List_Corp_Entity();
				reg1=reg.get();
			
			String modi="";
			if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
				modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG Y" + '+');
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
			audit.setAudit_table("CUST_BLACKLIST_COR");
			audit.setAudit_screen("BLACK LIST CORPORATE LISTING MASTER");
			audit.setEvent_id(alertparam.getBlack_list_corp_data_setid().toString());
			audit.setEvent_name(alertparam.getBlack_list_corp_data_setid()+"-"+"DELETED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_modify_user());
			audit.setEntry_time(alertparam.getAml_modify_time());
			audit.setAudit_ref_no(Number.toString());
			
			Cust_Black_List_Corp_Entity up = alertparam;
			if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
				up.setRisk_category(null);
			}
			up.setDel_flag("Y");
			up.setEntity_flag("N");
			cust_black_list_Corp_Repository.save(up);
			
			auditLocal.save(audit);
			
			msg = "Black List Deleted Successfully";
			}
		} else if (formmode.equals("Corp_verify")) {
			Optional<Cust_Black_List_Corp_Entity> reg = cust_black_list_Corp_Repository.findById(alertparam.getBlack_list_corp_data_setid());
			if (reg.isPresent()) {
				Cust_Black_List_Corp_Entity reg1 = new Cust_Black_List_Corp_Entity();
				reg1=reg.get();
			
			String modi="";
			if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
				modi = modi +("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag() + '+');
			}else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
				modi = modi +("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG " + alertparam.getModify_flag() + '+');
			}else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
				modi = modi +("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG Y + ");
			}
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			
			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD VERFIED");
			audit.setRemarks("VERFIED");
			audit.setAudit_table("CUST_BLACKLIST_COR");
			audit.setAudit_screen("BLACK LIST CORPORATE LISTING MASTER");
			audit.setEvent_id(alertparam.getBlack_list_corp_data_setid().toString());
			audit.setEvent_name(alertparam.getBlack_list_corp_data_setid()+"-"+"VERFIED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_modify_user());
			audit.setEntry_time(alertparam.getAml_modify_time());
			audit.setAudit_ref_no(Number.toString());
			
			Cust_Black_List_Corp_Entity up = alertparam;
			if(up.getRisk_category()==null || up.getRisk_category().isEmpty()) {
				up.setRisk_category(null);
			}
			up.setEntity_flag("Y");
			up.setDel_flag("N");
			cust_black_list_Corp_Repository.save(up);
			
			auditLocal.save(audit);
		
			msg = "Black List Verified Successfully";
			}
		}
		return msg;
	}

	public Cust_Black_List_Corp_Entity getSrlNo(BigDecimal srlno) {

		if (cust_black_list_Corp_Repository.existsById(srlno)) {
			Cust_Black_List_Corp_Entity up = cust_black_list_Corp_Repository.findById(srlno).get();
			return up;
		} else {
			return new Cust_Black_List_Corp_Entity();
		}

	};

	public String deleteParameter(BigDecimal inputSrlNo) {
		String msg = "";
		Optional<Cust_Black_List_Corp_Entity> user = cust_black_list_Corp_Repository.findById(inputSrlNo);
		Cust_Black_List_Corp_Entity reg = user.get();
		reg.setDel_flag("N");
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(BigDecimal inputSrlNo) {
		String msg = "";
		Optional<Cust_Black_List_Corp_Entity> user = cust_black_list_Corp_Repository.findById(inputSrlNo);
		Cust_Black_List_Corp_Entity reg = user.get();
		
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
//		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT id FROM CUST_BLACK_LIST_CORP_NUM").getSingleResult();
//
//		BigDecimal z = new BigDecimal(1);
//
//		String serialno = null;
//		if (billNumber == null) {
//			//********** in case num table is not having any vlaue then insert - NUM table shud contain
//			//********* only one Row shud be there in this table at all times 
//			hs.createNativeQuery("insert into CUST_BLACK_LIST_CORP_NUM(id) values(1)").getSingleResult();
//			serialno = "1";
//		} else {
//			serialno = numformate.format(billNumber);
//		}
//
//		return   serialno;
//	}
	
	public void updateBlack_list_Corp_Num() {
		Session hs = sessionFactory.getCurrentSession();
		// after save increement the num table value by 1
		cust_black_list_Corp_Repository.updateCust_balck_List_Corp_NumTB();
		
	}
		
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
		
		String catch_Errormsg = "";

		String status = "";

//logger.info("truncating table: Negative List");

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
						for (int j = 0; j < 86; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);

						}
						

						
//						String BLACK_LIST_SET_ID = resultList.get(0);
						String BANK_ID	= resultList.get(0);
						String UNIQUE_REF_ID = resultList.get(1);
						String ALT_SHORT_NAME = resultList.get(2);
						String PREFERRED_FORMAT	= resultList.get(3);
						String REMARKS1 = resultList.get(4);	
						String ALT_CORP_NAME = resultList.get(5);
						String NATIVE_CORP_NAME = resultList.get(6);
						String NATIVE_SHORT_NAME = resultList.get(7);
						String CORPORATE_ID = resultList.get(8);
						String CORPORATE_KEY = resultList.get(9);
						String ENTITY_TYPE = resultList.get(10);	
						String CONTACT_ID = resultList.get(11);
						String MASTER_DATA_ID = resultList.get(12);	
						String CIF_ID = resultList.get(13);	
						String ENTITY_BRANCH_NAME = resultList.get(14);		
						String SOURCE	 = resultList.get(15);	 
						String BL_REASON_NOTES = resultList.get(16);	
						String RISK_CATEGORY = resultList.get(17);	
						String SECTOR = resultList.get(18);	
						String STATUS = resultList.get(19);	
						String DATE_FREEZED = resultList.get(20);
						String DATE_DE_FREEZED = resultList.get(21);
						String ACTIVE_PROD_TYPE = resultList.get(22);
						String FILE_ID = resultList.get(23);
						String EXTERNAL_ENTITY_ID = resultList.get(24);
						String REMARKS2 = resultList.get(25);
						String FIRST_NAME = resultList.get(26);
						String ADDRESS_TYPE = resultList.get(27);
						
						String DATE_OF_INCORP = resultList.get(28);
						
						String ADDRESS_LINE1 = resultList.get(29);
						String ADDRESS_LINE2 = resultList.get(30);	
						String ADDRESS_LINE3	 = resultList.get(31);
						String PAN_NO = resultList.get(32);
						String SSN_NO = resultList.get(33);
						String 	PASSPORT_NO	 = resultList.get(34);
						String CREDIT_CARD_No = resultList.get(35);
						String NATIONAL_ID_NO = resultList.get(36);
						String DRIVER_LICENSE_NO = resultList.get(37);
						String HOME_PHONE_NO = resultList.get(38);	
						String 	WORK_PHONE_NO = resultList.get(39);
						String WORK_EMAIL = resultList.get(40);
						String HOME_MAIL = resultList.get(41);
						String WORK_FAX = resultList.get(42);
						String WEBSITE = resultList.get(43);	
						String NON_CUSTOMER_ID	 = resultList.get(44);
						String PHONE = resultList.get(45);	
						String EMAIL = resultList.get(46);	
						String HOUSE_NO = resultList.get(47);	
						String PREMISE_NAME = resultList.get(48);
						String BUILDING_NAME = resultList.get(49);
						String BUILDING_LEVEL = resultList.get(50);	
						String STREET_NO = resultList.get(51);
						String STREET_NAME = resultList.get(52);	
						String LOCALITY_NAME = resultList.get(53);	
						String TOWN = resultList.get(54);
						String DOMICILE = resultList.get(55);
						String CITY = resultList.get(56);
						String STATE = resultList.get(57);
						String ZIP = resultList.get(58);
						String COUNTRY_OF_INCORP = resultList.get(59);
						String COUNTRY_OF_PRINCIPLE_BUSINESS = resultList.get(60);
						String MAIL_STOP = resultList.get(61);
						String NATURE_OF_BUSINESS = resultList.get(62);
						
						String START_DATE = resultList.get(63);
						String END_DATE = resultList.get(64);
						
						String WORK_EXTENSIONS = resultList.get(65);
						String BO_ACL_ID	 = resultList.get(66);
						String REMARKS_3	 = resultList.get(67);
						String PHONE_NO_1	 = resultList.get(68);
						String PHONE_NO_1_CITY_CODE	 = resultList.get(69);
						String PHONE_NO_1_COUNTRY_CODE	 = resultList.get(70);
						String PHONE_NO_2	 = resultList.get(71);
						String PHONE_NO_2_CITY_CODE	 = resultList.get(72);
						
						String PHONE_NO_2_COUNTRY_CODE	 = resultList.get(73);
						String CELL	 = resultList.get(74);
						String CELL_CITY_CODE	 = resultList.get(75);
						String CELL_COUNTRY_CODE	 = resultList.get(76);
						String FAX	 = resultList.get(77);
						String FAX_CITY_CODE	 = resultList.get(78);
						String FAX_COUNTRY_CODE	 = resultList.get(79);
						
						String BO_CREATED_DATE = resultList.get(80);
						String BO_CREATED_USER	 = resultList.get(81);
						String BO_VERIFIED_DATE = resultList.get(82);
						String B_VERFIED_USER = resultList.get(83);
						String BO_MODIFIED_DATE = resultList.get(84);
						String BO_MODIFIED_USER = resultList.get(85);

						
						
						
//						  BigDecimal setID = new BigDecimal(BLACK_LIST_SET_ID);
						  
						  
						  BigDecimal masterdataId=null;
						  if( MASTER_DATA_ID != null && !MASTER_DATA_ID.isEmpty()) {
							  masterdataId = new BigDecimal(MASTER_DATA_ID);
						  }
						
						  
						  BigDecimal fieldId=null;
						  if( FILE_ID != null && !FILE_ID.isEmpty() ) {
							  fieldId = new BigDecimal(FILE_ID);
						  }
						  
						  BigDecimal externatLEntity_Id=null;
						  if( EXTERNAL_ENTITY_ID  != null && !EXTERNAL_ENTITY_ID.isEmpty()  ) {
							  externatLEntity_Id = new BigDecimal(EXTERNAL_ENTITY_ID);
						  }
						  
						  
						  
						  
						  
						  BigDecimal corpId=null;
						  if(  CORPORATE_ID != null && !CORPORATE_ID.isEmpty()  ) {
							  corpId = new BigDecimal(CORPORATE_ID);
						  }
						  BigDecimal corpKey=null;
						  if(  CORPORATE_KEY != null && !CORPORATE_KEY.isEmpty()  ) {
							  corpKey = new BigDecimal(CORPORATE_KEY);
						  }
						  
						  BigDecimal boAclID=null;
						  if(  BO_ACL_ID != null && !BO_ACL_ID.isEmpty()  ) {
							  boAclID = new BigDecimal(BO_ACL_ID);
						  }
						 
						  
						

						Date DATE_FREEZED1 = null;
						try {
							if (DATE_FREEZED != null && !DATE_FREEZED.isEmpty()) {
								DATE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_FREEZED Date   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_DE_FREEZED1 = null;
						try {
							if (DATE_DE_FREEZED != null && !DATE_DE_FREEZED.isEmpty()) {
								DATE_DE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_DE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_DE_FREEZED field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_OF_INCORP1 = null;
						try {
							if (DATE_OF_INCORP != null && !DATE_OF_INCORP.isEmpty()) {
								DATE_OF_INCORP1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_OF_INCORP);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_OF_INCORP field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						
						Date START_DATE1 = null;
						try {
							if (START_DATE != null && !START_DATE.isEmpty()) {
								START_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(START_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the START_DATE field  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						Date END_DATE1 = null;
						try {
							if (END_DATE != null && !END_DATE.isEmpty()) {
								END_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(END_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the END_DATE field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						
						Date BO_CREATED_DATE1 = null;
						try {
							if (BO_CREATED_DATE != null && !BO_CREATED_DATE.isEmpty()) {
								BO_CREATED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_CREATED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_CREATED_DATE field  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date BO_VERIFIED_DATE1 = null;
						try {
							if (BO_VERIFIED_DATE != null && !BO_VERIFIED_DATE.isEmpty()) {
								BO_VERIFIED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_VERIFIED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_VERIFIED_DATE field  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						Date BO_MODIFIED_DATE1 = null;
						try {
							if (BO_MODIFIED_DATE != null && !BO_MODIFIED_DATE.isEmpty()) {
								BO_MODIFIED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_MODIFIED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_MODIFIED_DATE field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";
						 if( FIRST_NAME==null || FIRST_NAME.isEmpty()) {
							  catch_Errormsg = " failed " + " Missing FIRST_NAME " ;							
								continue;
							 
						  }
						
						  

						  
						Cust_Black_List_Corp_Entity info = new Cust_Black_List_Corp_Entity(	BANK_ID	,UNIQUE_REF_ID	,ALT_SHORT_NAME	,PREFERRED_FORMAT,	REMARKS1	,ALT_CORP_NAME	,NATIVE_CORP_NAME,	NATIVE_SHORT_NAME,
								corpId	,corpKey	,ENTITY_TYPE	,CONTACT_ID	,masterdataId	,CIF_ID	,ENTITY_BRANCH_NAME	,SOURCE	,BL_REASON_NOTES,	RISK_CATEGORY,SECTOR,	STATUS,
								DATE_FREEZED1,	DATE_DE_FREEZED1,	ACTIVE_PROD_TYPE	,fieldId,	externatLEntity_Id	,REMARKS2,FIRST_NAME,	ADDRESS_TYPE,DATE_OF_INCORP1,
								ADDRESS_LINE1	,ADDRESS_LINE2	,ADDRESS_LINE3	,PAN_NO	,SSN_NO	,PASSPORT_NO,	CREDIT_CARD_No,	NATIONAL_ID_NO	,DRIVER_LICENSE_NO	,HOME_PHONE_NO	,WORK_PHONE_NO,
								WORK_EMAIL,	HOME_MAIL,	WORK_FAX,WEBSITE ,NON_CUSTOMER_ID,	PHONE	,EMAIL,		HOUSE_NO	,PREMISE_NAME	,BUILDING_NAME,	BUILDING_LEVEL,	STREET_NO	,
								STREET_NAME,	LOCALITY_NAME	,TOWN	,DOMICILE	,CITY,	STATE , ZIP ,COUNTRY_OF_INCORP ,	COUNTRY_OF_PRINCIPLE_BUSINESS	,MAIL_STOP	,NATURE_OF_BUSINESS	,START_DATE1,END_DATE1,
								WORK_EXTENSIONS	,boAclID	,REMARKS_3	,PHONE_NO_1	,PHONE_NO_1_CITY_CODE ,PHONE_NO_1_COUNTRY_CODE	,PHONE_NO_2	,PHONE_NO_2_CITY_CODE	,PHONE_NO_2_COUNTRY_CODE,	CELL,	CELL_CITY_CODE	,CELL_COUNTRY_CODE	,FAX	,FAX_CITY_CODE	,FAX_COUNTRY_CODE,
								BO_CREATED_DATE1,	BO_CREATED_USER,	BO_VERIFIED_DATE1,	B_VERFIED_USER,	BO_MODIFIED_DATE1,	BO_MODIFIED_USER,
								entry_user, entry_time, entity_flg, del_flg);
						
						
						List<Cust_Black_List_Corp_Entity> info1=null;
						info1=cust_black_list_Corp_Repository.findByforNatID(NATIONAL_ID_NO);
						
						if (info1 != null && !info1.isEmpty()) {
							System.out.println("NATIONAL_ID_NO "+NATIONAL_ID_NO);
							Errormsg = Errormsg+ " National Id  -" + NATIONAL_ID_NO ;
						} else {
							if (!info.getFirstname().equals(null) && !info.getFirstname().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();

								DecimalFormat numformate = new DecimalFormat("0");
								BigDecimal billNumber = (BigDecimal) theSession.createNativeQuery("SELECT CUST_BLACKLIST_CORP_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
							    info.setBlack_list_corp_data_setid(billNumber);
								
							    cust_black_list_Corp_Repository.save(info);
							    
							    AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
								BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
										.getSingleResult();
								String modi = "RECORD ADDED";
								audit.setAudit_date(new Date());
								audit.setEntry_user(info.getAml_entry_user());
								audit.setFunc_code("RECORD CREATED");
								audit.setRemarks("ADDED");
								audit.setAudit_table("CUST_BLACKLIST_IND");
								audit.setAudit_screen("BLACK_LIST INDIVIDUAL LISTING MASTER");
								audit.setEvent_id(info.getBlack_list_corp_data_setid().toString());
								audit.setEvent_name(info.getBlack_list_corp_data_setid()+"-"+"ADDED");
								audit.setModi_details(modi);
								audit.setEntry_user(info.getAml_entry_user());
								audit.setEntry_time(info.getAml_entry_time());
								audit.setAudit_ref_no(Number.toString());
								auditLocal.save(audit);
								status = "successfully uploaded";
							} else {
								Errormsg = Errormsg + "," + NATIONAL_ID_NO;
							}

						}

						
						
					}

					logger.info("inserted values into Individual black list ");
				}

			} catch (Exception e) {
				catch_Errormsg="Error for ";
			}
		} else {
			logger.info("reading values from CSV");

			try {

				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {

					if (skipRow > 0) {

						

						
						
						
						String BANK_ID	=nextLine[0];
						String UNIQUE_REF_ID = nextLine[1];
						String ALT_SHORT_NAME =nextLine[2];
						String PREFERRED_FORMAT	= nextLine[3];
						String REMARKS1 =nextLine[4];
						String ALT_CORP_NAME = nextLine[5];
						String NATIVE_CORP_NAME = nextLine[6];
						String NATIVE_SHORT_NAME = nextLine[7];
						String CORPORATE_ID =nextLine[8];
						String CORPORATE_KEY = nextLine[9];
						String ENTITY_TYPE =nextLine[10];
						String CONTACT_ID = nextLine[11];
						String MASTER_DATA_ID =nextLine[12];
						String CIF_ID =nextLine[13];
						String ENTITY_BRANCH_NAME =nextLine[14];	
						String SOURCE	 = nextLine[15];	 
						String BL_REASON_NOTES = nextLine[16];	
						String RISK_CATEGORY = nextLine[17];
						String SECTOR =nextLine[18];	
						String STATUS = nextLine[19];
						String DATE_FREEZED = nextLine[20];
						String DATE_DE_FREEZED = nextLine[21];
						String ACTIVE_PROD_TYPE = nextLine[22];
						String FILE_ID =nextLine[23];
						String EXTERNAL_ENTITY_ID =nextLine[24];
						String REMARKS2 = nextLine[25];
						String FIRST_NAME =nextLine[26];
						String ADDRESS_TYPE = nextLine[27];
						
						String DATE_OF_INCORP =  nextLine[28];
						
						String ADDRESS_LINE1 =  nextLine[29];
						String ADDRESS_LINE2 =  nextLine[30];	
						String ADDRESS_LINE3	 =  nextLine[31];
						String PAN_NO =  nextLine[32];
						String SSN_NO = nextLine[33];
						String 	PASSPORT_NO	 =  nextLine[34];
						String CREDIT_CARD_No = nextLine[35];
						String NATIONAL_ID_NO =  nextLine[36];
						String DRIVER_LICENSE_NO = nextLine[37];
						String HOME_PHONE_NO = nextLine[38];
						String 	WORK_PHONE_NO =  nextLine[39];
						
						String WORK_EMAIL =  nextLine[40];
						String HOME_MAIL = nextLine[41];
						String WORK_FAX =  nextLine[42];
						String WEBSITE =  nextLine[43];
						String NON_CUSTOMER_ID	 =  nextLine[44];
						String PHONE = nextLine[45];
						String EMAIL =  nextLine[46];
						String HOUSE_NO = nextLine[47];
						String PREMISE_NAME =  nextLine[48];
						String BUILDING_NAME =  nextLine[49];
						String BUILDING_LEVEL =  nextLine[50];	
						String STREET_NO =  nextLine[51];
						String STREET_NAME =  nextLine[52];
						String LOCALITY_NAME = nextLine[53];
						String TOWN =  nextLine[54];
						
						String DOMICILE =nextLine[55];
						String CITY = nextLine[56];
						String STATE =nextLine[57];
						String ZIP = nextLine[58];
						String COUNTRY_OF_INCORP =nextLine[59];
						String COUNTRY_OF_PRINCIPLE_BUSINESS =nextLine[60];
						String MAIL_STOP = nextLine[61];
						String NATURE_OF_BUSINESS =nextLine[62];
						
						String START_DATE =nextLine[63];
						String END_DATE = nextLine[64];
						
						String WORK_EXTENSIONS = nextLine[65];
						String BO_ACL_ID	 = nextLine[66];
						String REMARKS_3	 =nextLine[67];
						String PHONE_NO_1	 = nextLine[68];
						String PHONE_NO_1_CITY_CODE	 = nextLine[69];
						String PHONE_NO_1_COUNTRY_CODE	 =nextLine[70];
						String PHONE_NO_2	 =nextLine[71];
						String PHONE_NO_2_CITY_CODE	 =nextLine[72];
						
						String PHONE_NO_2_COUNTRY_CODE	 = nextLine[73];
						String CELL	 = nextLine[74];
						String CELL_CITY_CODE	 = nextLine[75];
						String CELL_COUNTRY_CODE	 = nextLine[76];
						String FAX	 =nextLine[77];
						String FAX_CITY_CODE	 =nextLine[78];
						String FAX_COUNTRY_CODE	 = nextLine[79];
						
						String BO_CREATED_DATE =nextLine[80];
						String BO_CREATED_USER	 =nextLine[81];
						String BO_VERIFIED_DATE = nextLine[82];
						String B_VERFIED_USER = nextLine[83];
						String BO_MODIFIED_DATE =nextLine[84];
						String BO_MODIFIED_USER = nextLine[85];

						
						
						  
						  
						  BigDecimal masterdataId=null;
						  if( MASTER_DATA_ID != null && !MASTER_DATA_ID.isEmpty()) {
							  masterdataId = new BigDecimal(MASTER_DATA_ID);
						  }
						
						  
						  BigDecimal fieldId=null;
						  if( FILE_ID != null && !FILE_ID.isEmpty() ) {
							  fieldId = new BigDecimal(FILE_ID);
						  }
						  
						  BigDecimal externatLEntity_Id=null;
						  if( EXTERNAL_ENTITY_ID  != null && !EXTERNAL_ENTITY_ID.isEmpty()  ) {
							  externatLEntity_Id = new BigDecimal(EXTERNAL_ENTITY_ID);
						  }
						  
						  
						  
						  
						  
						  BigDecimal corpId=null;
						  if(  CORPORATE_ID != null && !CORPORATE_ID.isEmpty()  ) {
							  corpId = new BigDecimal(CORPORATE_ID);
						  }
						  BigDecimal corpKey=null;
						  if(  CORPORATE_KEY != null && !CORPORATE_KEY.isEmpty()  ) {
							  corpKey = new BigDecimal(CORPORATE_KEY);
						  }
						  
						  BigDecimal boAclID=null;
						  if(  BO_ACL_ID != null && !BO_ACL_ID.isEmpty()  ) {
							  boAclID = new BigDecimal(BO_ACL_ID);
						  }
						 
						  
						

						Date DATE_FREEZED1 = null;
						try {
							if (DATE_FREEZED != null && !DATE_FREEZED.isEmpty()) {
								DATE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_FREEZED Date   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_DE_FREEZED1 = null;
						try {
							if (DATE_DE_FREEZED != null && !DATE_DE_FREEZED.isEmpty()) {
								DATE_DE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_DE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_DE_FREEZED field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_OF_INCORP1 = null;
						try {
							if (DATE_OF_INCORP != null && !DATE_OF_INCORP.isEmpty()) {
								DATE_OF_INCORP1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_OF_INCORP);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_OF_INCORP field  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						
						Date START_DATE1 = null;
						try {
							if (START_DATE != null && !START_DATE.isEmpty()) {
								START_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(START_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the START_DATE field   -" +  FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						Date END_DATE1 = null;
						try {
							if (END_DATE != null && !END_DATE.isEmpty()) {
								END_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(END_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the END_DATE field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						
						Date BO_CREATED_DATE1 = null;
						try {
							if (BO_CREATED_DATE != null && !BO_CREATED_DATE.isEmpty()) {
								BO_CREATED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_CREATED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_CREATED_DATE field  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date BO_VERIFIED_DATE1 = null;
						try {
							if (BO_VERIFIED_DATE != null && !BO_VERIFIED_DATE.isEmpty()) {
								BO_VERIFIED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_VERIFIED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_VERIFIED_DATE field   -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						Date BO_MODIFIED_DATE1 = null;
						try {
							if (BO_MODIFIED_DATE != null && !BO_MODIFIED_DATE.isEmpty()) {
								BO_MODIFIED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_MODIFIED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_MODIFIED_DATE field -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						
						

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";

						
						 if( FIRST_NAME==null || FIRST_NAME.isEmpty()) {
							  catch_Errormsg = " failed " + " Missing FIRST_NAME " ;							
								continue;
							 
						  }

						  
						Cust_Black_List_Corp_Entity info = new Cust_Black_List_Corp_Entity(	BANK_ID	,UNIQUE_REF_ID	,ALT_SHORT_NAME	,PREFERRED_FORMAT,	REMARKS1	,ALT_CORP_NAME	,NATIVE_CORP_NAME,	NATIVE_SHORT_NAME,
								corpId	,corpKey	,ENTITY_TYPE	,CONTACT_ID	,masterdataId	,CIF_ID	,ENTITY_BRANCH_NAME	,SOURCE	,BL_REASON_NOTES,	RISK_CATEGORY,SECTOR,	STATUS,
								DATE_FREEZED1,	DATE_DE_FREEZED1,	ACTIVE_PROD_TYPE	,fieldId,	externatLEntity_Id	,REMARKS2,FIRST_NAME,	ADDRESS_TYPE,DATE_OF_INCORP1,
								ADDRESS_LINE1	,ADDRESS_LINE2	,ADDRESS_LINE3	,PAN_NO	,SSN_NO	,PASSPORT_NO,	CREDIT_CARD_No,	NATIONAL_ID_NO	,DRIVER_LICENSE_NO	,HOME_PHONE_NO	,WORK_PHONE_NO,
								WORK_EMAIL,	HOME_MAIL,	WORK_FAX,WEBSITE ,NON_CUSTOMER_ID,	PHONE	,EMAIL,		HOUSE_NO	,PREMISE_NAME	,BUILDING_NAME,	BUILDING_LEVEL,	STREET_NO	,
								STREET_NAME,	LOCALITY_NAME	,TOWN	,DOMICILE	,CITY,	STATE , ZIP ,COUNTRY_OF_INCORP ,	COUNTRY_OF_PRINCIPLE_BUSINESS	,MAIL_STOP	,NATURE_OF_BUSINESS	,START_DATE1,END_DATE1,
								WORK_EXTENSIONS	,boAclID	,REMARKS_3	,PHONE_NO_1	,PHONE_NO_1_CITY_CODE ,PHONE_NO_1_COUNTRY_CODE	,PHONE_NO_2	,PHONE_NO_2_CITY_CODE	,PHONE_NO_2_COUNTRY_CODE,	CELL,	CELL_CITY_CODE	,CELL_COUNTRY_CODE	,FAX	,FAX_CITY_CODE	,FAX_COUNTRY_CODE,
								BO_CREATED_DATE1,	BO_CREATED_USER,	BO_VERIFIED_DATE1,	B_VERFIED_USER,	BO_MODIFIED_DATE1,	BO_MODIFIED_USER,
								entry_user, entry_time, entity_flg, del_flg);

						List<Cust_Black_List_Corp_Entity> info1=null;
						info1=cust_black_list_Corp_Repository.findByforNatID(NATIONAL_ID_NO);
						
						if (info1 != null && !info1.isEmpty()) {
							System.out.println("NATIONAL_ID_NO "+NATIONAL_ID_NO);
							Errormsg = Errormsg+ " National Id  -" + NATIONAL_ID_NO ;
						} else {
							if (!info.getFirstname().equals(null) && !info.getFirstname().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();

								DecimalFormat numformate = new DecimalFormat("0");
								BigDecimal billNumber = (BigDecimal) theSession.createNativeQuery("SELECT CUST_BLACKLIST_CORP_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
							    info.setBlack_list_corp_data_setid(billNumber);
								
							    cust_black_list_Corp_Repository.save(info);
								status = "successfully uploaded";
							} else {
								Errormsg = Errormsg + "," + NATIONAL_ID_NO;
							}

						}

					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into Individual black list ");

			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		if (!Errormsg.isEmpty() || !catch_Errormsg.isEmpty()) {
			if(!catch_Errormsg.isEmpty() ) {
				return "Please Contact Administrator "+catch_Errormsg;
			}{
				return "Uploaded Successfully Except - " +Errormsg;
			}
			
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
public File getFile(String userid,String reportId, String fromdate, String todate, String currency, String dtltype,
		String filetype) throws FileNotFoundException, JRException, SQLException {

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
		e1.printStackTrace();
	}
	
	 Date ConToDate = null;
	try {
		ConToDate = dateFormat1.parse(todate);
	} catch (ParseException e1) {
		e1.printStackTrace();
	}
	 System.out.println(ConToDate);
	   
	 String strDate1 = formatter1.format(ConToDate);
	 try {
		todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
	} catch (ParseException e1) {
		e1.printStackTrace();
	}

	String path = env.getProperty("output.exportpath");
	String fileName = "";
	
	File outputFile;

	logger.info("Getting Output file :" + reportId);

	fileName = reportId + "_" + dateFormat.format(new Date());

	

		try {
		
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + reportId);
			    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/BlacklistCorp.jasper"); 
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("TODATE", todate);
			map.put("FROMDATE", fromdate);

			logger.info("BEFORE GENERATING PDF :" + reportId);
				fileName = fileName + ".pdf";
				path +=  fileName;
				logger.info("BEFORE GENERATING PDF 1 :" + reportId);
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				logger.info("BEFORE GENERATING PDF 2 :" + path);
				JasperExportManager.exportReportToPdfFile(jp, path);
				logger.info("PDF File exported");
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	outputFile = new File(path);

	return outputFile;

}
public ByteArrayInputStream getFile_Corp_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
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
//		headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellTitle.setCellStyle(headerCellStyle);
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
		
		Row ReportNameRow = sheet.createRow(2);
		Cell cellReporName = ReportNameRow.createCell((short) 0);
		cellReporName.setCellValue("Corporate Black List Report");
		sheet.addMergedRegion(CellRangeAddress.valueOf("A3:J3"));
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//		headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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

		corp_List = baml_cust_blacklist_rpt_repository.findAllCustBlackListCorpReport(fromDAte,toDAte);
		for (BAML_Cust_Blacklist_RPT_Entity pep_List : corp_List) {
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
//		audit.setEvent_id(alertparam.getPep_id());
		audit.setEvent_name("REPORT" + "-" + "ADDED");
		audit.setModi_details(modi);

		audit.setAudit_ref_no(Number.toString());
		auditLocal.save(audit);

	} catch (Exception e) {
		e.printStackTrace();
	}

	return new ByteArrayInputStream(out.toByteArray());

}

	private void writeBook(BAML_Cust_Blacklist_RPT_Entity aBook, Row row, CellStyle dateCellStyle, int sn,
			CellStyle dateCell, CellStyle numStyle) {

		Cell cell = row.createCell(0);
		cell.setCellValue(sn);
		cell.setCellStyle(dateCell);

		Cell masterdataID = row.createCell(1);
		if (aBook.getMASTERDATAID() != null) {
			masterdataID.setCellValue(aBook.getMASTERDATAID().toString());
		} else {
			masterdataID.setCellValue("");
		}
		masterdataID.setCellStyle(dateCell);

		Cell cif = row.createCell(2);
		cif.setCellValue(aBook.getCIF());
		cif.setCellStyle(dateCell);

		Cell lastname = row.createCell(3);
		lastname.setCellValue(aBook.getCUST_LAST_NAME());
		lastname.setCellStyle(dateCell);

		Cell firstname = row.createCell(4);
		firstname.setCellValue(aBook.getCUST_FIRST_NAME());
		firstname.setCellStyle(dateCell);

		Cell nid = row.createCell(5);
		nid.setCellValue(aBook.getNAT_ID_CARD_NUM());
		nid.setCellStyle(dateCell);

		Cell riskcat = row.createCell(6);
		riskcat.setCellValue(aBook.getRISK_CATEGORY());
		riskcat.setCellStyle(dateCell);

		Cell status = row.createCell(7);
		status.setCellValue(aBook.getSTATUS());
		status.setCellStyle(dateCell);

		Cell reason = row.createCell(8);
		reason.setCellValue(aBook.getBLACK_LIST_REASON_NOTES());
		reason.setCellStyle(numStyle);

		Cell freezed = row.createCell(9);
		freezed.setCellValue(aBook.getDATE_FREEZED());
		freezed.setCellStyle(dateCellStyle);
		
		Cell defreezed = row.createCell(10);
		defreezed.setCellValue(aBook.getDATE_DEFREEZED());
		defreezed.setCellStyle(dateCellStyle);
		
		Cell type = row.createCell(11);
		type.setCellValue(aBook.getACTIVE_PRODUCT_TYPE());
		type.setCellStyle(dateCell);

	}



}
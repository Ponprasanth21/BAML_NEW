package com.bornfire.Services;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.NegativeListEntity;
import com.bornfire.entity.NegativeListRepository;
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
public class NegativeListService {

	@Autowired
	NegativeListRepository negativeListRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(NegativeListService.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DataSource srcdataSource;
	
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	Environment env;

	public String addNegativeList(NegativeListEntity alertparam, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		Session hs = sessionFactory.getCurrentSession();
		if (formmode.equals("add")) {

			NegativeListEntity up = alertparam;
			
			DecimalFormat numformate = new DecimalFormat("0");
			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT NEG_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
//			String serialno =  numformate.format(billNumber);
			up.setNegativelistdatasetid(billNumber);
			
			hs.save(up);
			up.setDel_flag("N");
			up.setEntity_flag("N");
			negativeListRepository.save(up);
			
			
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "RECORD ADDED";
			
//				modi = modi + (" BLACK LIST SET ID  " + alertparam.getNegativelistdatasetid() + '+');
//				modi = modi +(" BANK ID " + alertparam.getBank_id() + '+');
//				modi = modi +(" UNIQUE REF ID  " + alertparam.getUniqueidnumber() + '+');
//				modi = modi +(" SHORT NAME " + alertparam.getShortname() + '+');
//				modi = modi +(" PREFERRED FORMAT " + alertparam.getPreferredformat() + '+');
//				modi = modi +(" REMARKS1 " + alertparam.getRemarks1() + '+');
//				modi = modi +(" ALT_FIRST_NAME " + alertparam.getFirstname_alt1() + '+');
//				modi = modi +(" ALT_MIDDLE_NAME " + alertparam.getMiddlename_alt1() + '+');
//				modi = modi +(" ALT_LAST_NAME " + alertparam.getLastname_alt1() + '+');
//				modi = modi +(" CUST_ID " + alertparam.getCustomerid() + '+');
//				modi = modi +(" APPLICATION_ID " + alertparam.getApplicationid() + '+');
//				modi = modi +(" CONTACT_ID " + alertparam.getContactid() + '+');
//				modi = modi +(" MASTER_DATA_ID " + alertparam.getMasterdataid() + '+');
//				modi = modi +(" CIF_ID " + alertparam.getCifid() + '+');
//				modi = modi +(" ENTITY_BRANCH_NAME " + alertparam.getEntityboname() + '+');
//				modi = modi +(" SOURCE " + alertparam.getSource() + '+');
//				modi = modi +(" BL REASON NOTES " + alertparam.getNegativelistreasonnotes() + '+');
//				modi = modi +(" RISK_CATEGORY " + alertparam.getRisk_category() + '+');
//				modi = modi +(" STATUS " + alertparam.getStatus() + '+');
//				modi = modi +(" DATE_FREEZED " + alertparam.getDate_freezed() + '+');
//				modi = modi +(" DATE_DEFREEZED " + alertparam.getDate_defreezed() + '+');
//				modi = modi +(" ACT_PROD_TYPE " + alertparam.getActive_product_type() + '+');
//				modi = modi +(" FIELD_ID " + alertparam.getFileid() + '+');
//				modi = modi +(" EXTERNAL_ENTITY_ID " + alertparam.getExternalentityid() + '+');
//				modi = modi +(" DATE_OF_BIRTH " + alertparam.getDateofbirth() + '+');
//				modi = modi +(" FIRST_NAME " + alertparam.getFirstname() + '+');
//				modi = modi +(" MIDDLE_NAME " + alertparam.getMiddlename() + '+');
//				modi = modi +(" LAST_NAME " + alertparam.getLastname() + '+');
//				modi = modi +(" ADDRS_LINE1 " + alertparam.getAddressline1() + '+');
//				modi = modi +(" ADDRS_LINE2 " + alertparam.getAddressline2() + '+');
//				modi = modi +(" ADDRS_LINE3 " + alertparam.getAddressline3() + '+');
//				modi = modi +(" PAN " + alertparam.getPanno() + '+');
//				modi = modi +(" SSN " + alertparam.getSsn() + '+');
//				modi = modi +(" PASSPORT_NO " + alertparam.getPassportno() + '+');
//				modi = modi +(" CREDIT_CARD_NO " + alertparam.getCreditcardno() + '+');
//				modi = modi +(" NATIOAL_ID " + alertparam.getNationalid() + '+');
//				modi = modi +(" DRIVER_LICENSE_NO " + alertparam.getDriverlicenseno() + '+');
//				modi = modi +(" HOME_PHONE_NO " + alertparam.getHomephoneno() + '+');
//				modi = modi +(" WORK_PHONE_NO " + alertparam.getWorkphoneno() + '+');
//				modi = modi +(" WORK_EMAIL " + alertparam.getWorkemail() + '+');
//				modi = modi +(" HOME_MAIL " + alertparam.getHomeemail() + '+');
//				modi = modi +(" WORK_FAX " + alertparam.getWorkfax() + '+');
//				modi = modi +(" REMARKS2 " + alertparam.getRemarks2() + '+');
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
//				modi = modi +(" BO ACL ID " + alertparam.getBoaclid() + '+');
//				modi = modi +(" NATIVE SHORT NAME " + alertparam.getShortname_native() + '+');
//				modi = modi +(" NATIVE FIRST NAME " + alertparam.getFirstname_native() + '+');
//				modi = modi +(" NATIVE MIDDLE NAME " + alertparam.getMiddlename_native() + '+');
//				modi = modi +(" NATIVE LAST NAME " + alertparam.getLastname_native() + '+');
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
			audit.setAudit_table("NEGATIVE_LIST");
			audit.setAudit_screen("NEGATIVE_LIST LISTING MASTER");
			audit.setEvent_id(alertparam.getNegativelistdatasetid().toString());
			audit.setEvent_name(alertparam.getNegativelistdatasetid()+"-"+"ADDED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setEntry_time(alertparam.getAml_entry_time());
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);
			

			msg = "Negative List Created Successfully";

		} else if (formmode.equals("edit")) {
			NegativeListEntity up = alertparam;
			up.setDel_flag("N");
			up.setModify_flag("Y");
			up.setEntity_flag("N");
			negativeListRepository.save(up);
			msg = "Negative List Edited Successfully";
		} else if (formmode.equals("delete")) {
			NegativeListEntity up = alertparam;
			up.setDel_flag("Y");
			up.setEntity_flag("N");
			negativeListRepository.save(up);
			msg = "Negative List Deleted Successfully";
		} else if (formmode.equals("verify")) {

			NegativeListEntity up = alertparam;
			up.setEntity_flag("Y");
			up.setDel_flag("N");
			negativeListRepository.save(up);
			msg = "Negative List Verified Successfully";
		}
		return msg;
	}

	public NegativeListEntity getSrlNo(BigDecimal srlno) {

		if (negativeListRepository.existsById(srlno)) {
			NegativeListEntity up = negativeListRepository.findById(srlno).get();
			return up;
		} else {
			return new NegativeListEntity();
		}

	};

	public String deleteParameter(BigDecimal inputSrlNo) {
		String msg = "";
		Optional<NegativeListEntity> user = negativeListRepository.findById(inputSrlNo);
		NegativeListEntity reg = user.get();
		reg.setDel_flag("N");
		/* montParameterRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(BigDecimal inputSrlNo) {
		String msg = "";
		Optional<NegativeListEntity> user = negativeListRepository.findById(inputSrlNo);
		NegativeListEntity reg = user.get();
		
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
//		BigDecimal z = new BigDecimal(1);
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
		
	public void updateNegative_list_Num() {
		Session hs = sessionFactory.getCurrentSession();
		// after save increement the num table value by 1
		negativeListRepository.updateNegListNumTB();
		
	}
	public String processUpload(String screenId,MultipartFile file, String userid) throws IllegalStateException, IOException
	{
		logger.info("NegativeListService -> processUpload()");

		String fileName = file.getOriginalFilename();
		logger.info(fileName);
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
						for (int j = 0; j < 65; j++) {
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
						
						String DATE_OF_BIRTH = resultList.get(4);	
						
						String ALT_FIRST_NAME = resultList.get(5);
						String ALT_MIDDLE_NAME = resultList.get(6);
						String ALT_LAST_NAME = resultList.get(7);
						String CUST_ID = resultList.get(8);
						String APPLICATION_ID = resultList.get(9);
						String SUSPECT_ID = resultList.get(10);	
						String CONTACT_ID = resultList.get(11);	
						String MASTER_DATA_ID = resultList.get(12);	
						String CIF_ID = resultList.get(13);	
						String ENTITY_BRANCH_NAME = resultList.get(14);		
						String SOURCE	 = resultList.get(15);	 
						String BL_REASON_NOTES = resultList.get(16);
						String FILE_ID = resultList.get(17);
						String EXTERNAL_ENTITY_ID = resultList.get(18);
						String RISK_CATEGORY = resultList.get(19);	
						String DATE_FREEZED = resultList.get(20);
						String DATE_DE_FREEZED = resultList.get(21);
						String ACTIVE_PROD_TYPE = resultList.get(22);
						String FIRST_NAME = resultList.get(23);
						String MIDDLE_NAME = resultList.get(24);
						String STATUS = resultList.get(25);
						String ADDRESS_LINE1 = resultList.get(26);
						String ADDRESS_LINE2 = resultList.get(27);	
						String ADDRESS_LINE3	 = resultList.get(28);
						String PAN_NO = resultList.get(29);
						String SSN_NO = resultList.get(30);
						String 	PASSPORT_NO	 = resultList.get(31);
						String CREDIT_CARD_No = resultList.get(32);
						String NATIONAL_ID_NO = resultList.get(33);
						String DRIVER_LICENSE_NO = resultList.get(34);
						
						String HOME_PHONE_NO = resultList.get(35);	
						String 	WORK_PHONE_NO = resultList.get(36);
						String WORK_EMAIL = resultList.get(37);
						String HOME_MAIL = resultList.get(38);	
						String WORK_FAX = resultList.get(39);
						
						String REMARKS1 = resultList.get(40);	
						String NON_CUSTOMER_ID	 = resultList.get(41);
						String PHONE = resultList.get(42);	
						String EMAIL = resultList.get(43);	
						String HOUSE_NO = resultList.get(44);
						
						String PREMISE_NAME = resultList.get(45);
						String BUILDING_NAME = resultList.get(46);
						String BUILDING_LEVEL = resultList.get(47);	
						String STREET_NO = resultList.get(48);
						String STREET_NAME = resultList.get(49);	
						String LOCALITY_NAME = resultList.get(50);	
						String TOWN = resultList.get(51);
						String DOMICILE = resultList.get(52);
						String CITY = resultList.get(53);
						String BO_ACL_ID	 = resultList.get(54);
						String NATIVE_SHORT_NAME	 = resultList.get(55);
						String NATIVE_FIRST_NAME = resultList.get(56);	
						String NATIVE_MIDDLE_NAME = resultList.get(57);	
						String NATIVE_LAST_NAME	 = resultList.get(58);
						
						String BO_CREATED_DATE = resultList.get(59);
						String BO_CREATED_USER	 = resultList.get(60);
						String BO_VERIFIED_DATE = resultList.get(61);
						String B_VERFIED_USER = resultList.get(62);
						String BO_MODIFIED_DATE = resultList.get(63);
						String BO_MODIFIED_USER = resultList.get(64);

						
						
						
//						  BigDecimal setID = new BigDecimal(BLACK_LIST_SET_ID);
						
						  
						  BigDecimal custId=null;
						  if( CUST_ID != null && !CUST_ID.isEmpty()) {
							  custId = new BigDecimal(CUST_ID);
						  }
						
						  
						  BigDecimal appId=null;
						  if( APPLICATION_ID != null && !APPLICATION_ID.isEmpty() ) {
							  appId = new BigDecimal(CUST_ID);
						  }
						  
						  BigDecimal suspectId=null;
						  if( SUSPECT_ID  != null && !SUSPECT_ID.isEmpty()  ) {
							  appId = new BigDecimal(SUSPECT_ID);
						  }
						  
						  BigDecimal contactId=null;
						  if(CONTACT_ID!=null && !CONTACT_ID.isEmpty() ) {
							  contactId = new BigDecimal(CONTACT_ID);
						  }
						  
						  BigDecimal masterDateID=null;
						  if( MASTER_DATA_ID != null  && !MASTER_DATA_ID.isEmpty() ) {
							  masterDateID = new BigDecimal(MASTER_DATA_ID);
						  }
						 
						  BigDecimal fieldId=null;
						  if( FILE_ID != null && !FILE_ID.isEmpty() ) {
							  fieldId = new BigDecimal(FILE_ID);
						  }
						  
						  BigDecimal externalentityId=null;
						  if(EXTERNAL_ENTITY_ID!= null && !EXTERNAL_ENTITY_ID.isEmpty()  ) {
							  externalentityId = new BigDecimal(EXTERNAL_ENTITY_ID);
						  }
						  
						  BigDecimal nocustomerID=null;
						  if( NON_CUSTOMER_ID != null && !EXTERNAL_ENTITY_ID.isEmpty() ) {
							  nocustomerID = new BigDecimal(NON_CUSTOMER_ID);
						  }
						  
						  BigDecimal boAclID=null;
						  if(  BO_ACL_ID != null && !BO_ACL_ID.isEmpty()  ) {
							  boAclID = new BigDecimal(BO_ACL_ID);
						  }
						 

						  
						  if( FIRST_NAME==null || FIRST_NAME.isEmpty()) {
							  catch_Errormsg = " failed " + " Missing FIRST_NAME " ;							
								continue;
							 
						  }

						Date DATE_FREEZED1 = null;
						try {
							if (DATE_FREEZED != null && !DATE_FREEZED.isEmpty()) {
								DATE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_FREEZED Date  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_DE_FREEZED1 = null;
						try {
							if (DATE_DE_FREEZED != null && !DATE_DE_FREEZED.isEmpty()) {
								DATE_DE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_DE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_DE_FREEZED Date -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_OF_BIRTH1 = null;
						try {
							if (DATE_OF_BIRTH != null && !DATE_OF_BIRTH.isEmpty()) {
								DATE_OF_BIRTH1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_OF_BIRTH);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_OF_BIRTH field of " + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date BO_CREATED_DATE1 = null;
						try {
							if (BO_CREATED_DATE != null && !BO_CREATED_DATE.isEmpty()) {
								BO_CREATED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_CREATED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_CREATED_DATE field -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date BO_VERIFIED_DATE1 = null;
						try {
							if (BO_VERIFIED_DATE != null && !BO_VERIFIED_DATE.isEmpty()) {
								BO_VERIFIED_DATE1 = new SimpleDateFormat("dd/MM/yy").parse(BO_VERIFIED_DATE);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the BO_VERIFIED_DATE field -" + FIRST_NAME
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

						
					
						  
						NegativeListEntity info = new NegativeListEntity(BANK_ID	,UNIQUE_REF_ID	,ALT_SHORT_NAME	,PREFERRED_FORMAT,	DATE_OF_BIRTH1	,ALT_FIRST_NAME	,ALT_MIDDLE_NAME,	ALT_LAST_NAME,
								custId	,appId	,suspectId	,contactId	,masterDateID	,CIF_ID	,ENTITY_BRANCH_NAME	,SOURCE	,BL_REASON_NOTES,	fieldId,	externalentityId,RISK_CATEGORY,
								DATE_FREEZED1,	DATE_DE_FREEZED1,	ACTIVE_PROD_TYPE		,	FIRST_NAME	,MIDDLE_NAME,	STATUS,
								ADDRESS_LINE1	,ADDRESS_LINE2	,ADDRESS_LINE3	,PAN_NO	,SSN_NO	,PASSPORT_NO,	CREDIT_CARD_No,	NATIONAL_ID_NO	,DRIVER_LICENSE_NO	,HOME_PHONE_NO	,WORK_PHONE_NO,
								WORK_EMAIL,	HOME_MAIL,	WORK_FAX	,REMARKS1	,nocustomerID,	PHONE	,EMAIL,		HOUSE_NO	,PREMISE_NAME	,BUILDING_NAME,	BUILDING_LEVEL,	STREET_NO	,
								STREET_NAME,	LOCALITY_NAME	,TOWN	,DOMICILE	,CITY,	boAclID	,NATIVE_SHORT_NAME,	NATIVE_FIRST_NAME	,NATIVE_MIDDLE_NAME	,NATIVE_LAST_NAME	,
								BO_CREATED_DATE1,	BO_CREATED_USER,	BO_VERIFIED_DATE1,	B_VERFIED_USER,	BO_MODIFIED_DATE1,	BO_MODIFIED_USER,
								entry_user, entry_time, entity_flg, del_flg);

						
						List<NegativeListEntity> info1=null;
						info1=negativeListRepository.findByforNatID(NATIONAL_ID_NO);
						
						if (info1 != null && !info1.isEmpty()) {
							System.out.println("NATIONAL_ID_NO "+NATIONAL_ID_NO);
							Errormsg = Errormsg+ " National Id  -" + NATIONAL_ID_NO ;
						} else {
							if (!info.getFirstname().equals(null) && !info.getFirstname().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();

								DecimalFormat numformate = new DecimalFormat("0");
								BigDecimal billNumber = (BigDecimal) theSession.createNativeQuery("SELECT NEG_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
							    info.setNegativelistdatasetid(billNumber);
								
							    negativeListRepository.save(info);
							    
							    AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL(); 
							    BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
										.getSingleResult();
								String modi = "RECORD ADDED";
								audit.setAudit_date(new Date());
								audit.setEntry_user(info.getAml_entry_user());
								audit.setFunc_code("RECORD CREATED");
								audit.setRemarks("ADDED");
								audit.setAudit_table("NEGATIVE_LIST");
								audit.setAudit_screen("NEGATIVE_LIST LISTING MASTER");
								audit.setEvent_id(info.getNegativelistdatasetid().toString());
								audit.setEvent_name(info.getNegativelistdatasetid()+"-"+"ADDED");
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
						
						
						
//						if (negativeListRepository.existsById(setID)) {
//							Errormsg = Errormsg + "  set_ID " + setID ;
//						} else {
//							if (!info.getNegativelistdatasetid().equals(null)) {
//								negativeListRepository.save(info);
//								status = "successfully uploaded";
//							} else {
//								Errormsg = Errormsg + "," + setID;
//							}
//						}
						
					}

					logger.info("inserted values into Negative list ");
				}

			} catch (Exception e) {
				catch_Errormsg=catch_Errormsg+" Error ";
			}
		} else {
			logger.info("reading values from CSV");

			try {

				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {

					if (skipRow > 0) {

						

						
//						String BLACK_LIST_SET_ID =  nextLine[0];
						String BANK_ID	= nextLine[0];
						String UNIQUE_REF_ID = nextLine[1];
						String ALT_SHORT_NAME =  nextLine[2];
						String PREFERRED_FORMAT	=  nextLine[3];
						
						String DATE_OF_BIRTH =  nextLine[4];
						
						String ALT_FIRST_NAME =  nextLine[5];
						String ALT_MIDDLE_NAME =  nextLine[6];
						String ALT_LAST_NAME =  nextLine[7];
						String CUST_ID =  nextLine[8];
						String APPLICATION_ID =  nextLine[9];
						
						String SUSPECT_ID =  nextLine[10];
						String CONTACT_ID = nextLine[11];
						String MASTER_DATA_ID =  nextLine[12];
						String CIF_ID =  nextLine[13];
						String ENTITY_BRANCH_NAME =  nextLine[14];
						String SOURCE	 =  nextLine[15]; 
						String BL_REASON_NOTES = nextLine[16];
						String FILE_ID =  nextLine[17];
						String EXTERNAL_ENTITY_ID =  nextLine[18];
						String RISK_CATEGORY =  nextLine[19];	
						
						String DATE_FREEZED = nextLine[20];
						String DATE_DE_FREEZED = nextLine[21];
						String ACTIVE_PROD_TYPE = nextLine[22];
						String FIRST_NAME =  nextLine[23];
						String MIDDLE_NAME =  nextLine[24];
						String STATUS =  nextLine[25];
						String ADDRESS_LINE1 = nextLine[26];
						String ADDRESS_LINE2 =  nextLine[27];
						String ADDRESS_LINE3	 =  nextLine[28];
						String PAN_NO =  nextLine[29];
						
						
						String SSN_NO =  nextLine[30];
						String 	PASSPORT_NO	 = nextLine[31];
						String CREDIT_CARD_No = nextLine[32];
						String NATIONAL_ID_NO =  nextLine[33];
						String DRIVER_LICENSE_NO = nextLine[34];
						
						String HOME_PHONE_NO =  nextLine[35];
						String 	WORK_PHONE_NO = nextLine[36];
						String WORK_EMAIL =  nextLine[37];
						String HOME_MAIL =  nextLine[38];
						String WORK_FAX =  nextLine[39];
						
						String REMARKS1 = nextLine[40];	
						String NON_CUSTOMER_ID	 =  nextLine[41];
						String PHONE =  nextLine[42];	
						String EMAIL = nextLine[43];
						String HOUSE_NO =  nextLine[44];
						
						String PREMISE_NAME = nextLine[45];
						String BUILDING_NAME = nextLine[46];
						String BUILDING_LEVEL =  nextLine[47];	
						String STREET_NO =  nextLine[48];
						String STREET_NAME =  nextLine[49];
						
						String LOCALITY_NAME =  nextLine[50];	
						String TOWN =  nextLine[51];
						String DOMICILE = nextLine[52];
						String CITY = nextLine[53];
						String BO_ACL_ID	 =  nextLine[54];
						String NATIVE_SHORT_NAME	 = nextLine[55];
						String NATIVE_FIRST_NAME = nextLine[56];
						String NATIVE_MIDDLE_NAME =  nextLine[57];	
						String NATIVE_LAST_NAME	 = nextLine[58];
						
						String BO_CREATED_DATE =  nextLine[59];
						String BO_CREATED_USER	 = nextLine[60];
						String BO_VERIFIED_DATE =  nextLine[61];
						String B_VERFIED_USER = nextLine[62];
						String BO_MODIFIED_DATE =  nextLine[63];
						String BO_MODIFIED_USER =  nextLine[64];

						
						
						  
						  BigDecimal custId=null;
						  if( CUST_ID != null && !CUST_ID.isEmpty()) {
							  custId = new BigDecimal(CUST_ID);
						  }
						 BigDecimal appId=null;
						  if( APPLICATION_ID != null && !APPLICATION_ID.isEmpty() ) {
							  appId = new BigDecimal(CUST_ID);
						  }
						  
						  BigDecimal suspectId=null;
						  if( SUSPECT_ID  != null && !SUSPECT_ID.isEmpty()  ) {
							  appId = new BigDecimal(SUSPECT_ID);
						  }
						  
						  BigDecimal contactId=null;
						  if(CONTACT_ID!=null && !CONTACT_ID.isEmpty() ) {
							  contactId = new BigDecimal(CONTACT_ID);
						  }
						  
						  BigDecimal masterDateID=null;
						  if( MASTER_DATA_ID != null  && !MASTER_DATA_ID.isEmpty() ) {
							  masterDateID = new BigDecimal(MASTER_DATA_ID);
						  }
						 
						  BigDecimal fieldId=null;
						  if( FILE_ID != null && !FILE_ID.isEmpty() ) {
							  fieldId = new BigDecimal(FILE_ID);
						  }
						  
						  BigDecimal externalentityId=null;
						  if(EXTERNAL_ENTITY_ID!= null && !EXTERNAL_ENTITY_ID.isEmpty()  ) {
							  externalentityId = new BigDecimal(EXTERNAL_ENTITY_ID);
						  }
						  
						  BigDecimal nocustomerID=null;
						  if( NON_CUSTOMER_ID != null && !EXTERNAL_ENTITY_ID.isEmpty() ) {
							  nocustomerID = new BigDecimal(NON_CUSTOMER_ID);
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
							Errormsg = "failed " + "Please fix the DATE_FREEZED Date  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_DE_FREEZED1 = null;
						try {
							if (DATE_DE_FREEZED != null && !DATE_DE_FREEZED.isEmpty()) {
								DATE_DE_FREEZED1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_DE_FREEZED);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_DE_FREEZED field  -" + FIRST_NAME
									+ " Fromat to (DD/MM/YYYY)";
							continue;

						}
						Date DATE_OF_BIRTH1 = null;
						try {
							if (DATE_OF_BIRTH != null && !DATE_OF_BIRTH.isEmpty()) {
								DATE_OF_BIRTH1 = new SimpleDateFormat("dd/MM/yy").parse(DATE_OF_BIRTH);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the DATE_OF_BIRTH field  -" + FIRST_NAME
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
							Errormsg = "failed " + "Please fix the BO_VERIFIED_DATE field -" + FIRST_NAME
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
						
						  if( FIRST_NAME.isEmpty() || FIRST_NAME==null) {
							  catch_Errormsg = " failed " + " Missing FIRST_NAME " ;							
								continue;
							 
						  }

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";

						  
						  
						NegativeListEntity info = new NegativeListEntity(BANK_ID	,UNIQUE_REF_ID	,ALT_SHORT_NAME	,PREFERRED_FORMAT,	DATE_OF_BIRTH1	,ALT_FIRST_NAME	,ALT_MIDDLE_NAME,	ALT_LAST_NAME,
								custId	,appId	,suspectId	,contactId	,masterDateID	,CIF_ID	,ENTITY_BRANCH_NAME	,SOURCE	,BL_REASON_NOTES,	fieldId,	externalentityId,RISK_CATEGORY,
								DATE_FREEZED1,	DATE_DE_FREEZED1,	ACTIVE_PROD_TYPE		,	FIRST_NAME	,MIDDLE_NAME,	STATUS,
								ADDRESS_LINE1	,ADDRESS_LINE2	,ADDRESS_LINE3	,PAN_NO	,SSN_NO	,PASSPORT_NO,	CREDIT_CARD_No,	NATIONAL_ID_NO	,DRIVER_LICENSE_NO	,HOME_PHONE_NO	,WORK_PHONE_NO,
								WORK_EMAIL,	HOME_MAIL,	WORK_FAX	,REMARKS1	,nocustomerID,	PHONE	,EMAIL,		HOUSE_NO	,PREMISE_NAME	,BUILDING_NAME,	BUILDING_LEVEL,	STREET_NO	,
								STREET_NAME,	LOCALITY_NAME	,TOWN	,DOMICILE	,CITY,	boAclID	,NATIVE_SHORT_NAME,	NATIVE_FIRST_NAME	,NATIVE_MIDDLE_NAME	,NATIVE_LAST_NAME	,
								BO_CREATED_DATE1,	BO_CREATED_USER,	BO_VERIFIED_DATE1,	B_VERFIED_USER,	BO_MODIFIED_DATE1,	BO_MODIFIED_USER,
								entry_user, entry_time, entity_flg, del_flg);

						List<NegativeListEntity> info1=null;
						info1=negativeListRepository.findByforNatID(NATIONAL_ID_NO);
						
						if (info1 != null && !info1.isEmpty()) {
							Errormsg = Errormsg + " National Id  -"+NATIONAL_ID_NO ;
						} else {
							if (!info.getFirstname().equals(null) && !info.getFirstname().isEmpty()) {
								Session hs = sessionFactory.getCurrentSession();

								DecimalFormat numformate = new DecimalFormat("0");
								BigDecimal billNumber = (BigDecimal) theSession.createNativeQuery("SELECT NEG_LIST_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
							    info.setNegativelistdatasetid(billNumber);
								
							    negativeListRepository.save(info);
								status = "successfully uploaded";
							} else {
								Errormsg = Errormsg + "," + NATIONAL_ID_NO;
							}

						}


					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into Negative list ");

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
	String path =  env.getProperty("output.exportpath");
	String fileName = "";
	
	File outputFile;

	logger.info("Getting Output file :" + reportId);

	fileName = reportId + "_" + dateFormat.format(new Date());

	

	if (!filetype.equals("xbrl")) {
		try {
		

			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + reportId);
			if (filetype.equals("xlsx")) {
				System.out.println("xlsx");
			    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/NegList.jasper");
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			}else {
				System.out.println("pdf");
			    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/NegList.jasper"); 
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			}

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("TODATE", todate);
			map.put("FROMDATE", fromdate);

//			File folders = new File(path);
////			boolean folders = (new File("TEST")).mkdir();
//			if (!folders.exists()) {
//				folders.mkdirs();
//			}
			logger.info("BEFORE GENERATING PDF :" + reportId);
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path =  fileName;
				logger.info("BEFORE GENERATING PDF 1 :" + reportId);
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				logger.info("BEFORE GENERATING PDF 2 :" + path);
				JasperExportManager.exportReportToPdfFile(jp, path);
				logger.info("PDF File exported");
			} else {
				fileName = fileName + ".xlsx";
				path =   fileName;
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

	}
	outputFile = new File(path);

	return outputFile;

}
	
}
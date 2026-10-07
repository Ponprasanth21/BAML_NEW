package com.bornfire.Services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
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
import com.bornfire.entity.BAML_Cust_HNWI_RPT_Entity;
import com.bornfire.entity.BAML_Cust_HNWI_RPT_Repository;
import com.bornfire.entity.BAML_Cust_PEP_RPT_Entity;
import com.bornfire.entity.BAML_RBS_PROCEDURE_REP;
import com.bornfire.entity.BAML_RBS_REPORT_PROCEDURE;
import com.bornfire.entity.Cust_Aband_Fund_List_Entity;
import com.bornfire.entity.Cust_Aband_Fund_List_Repository;
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
public class Cust_Aband_Fund_List_Service {

	@Autowired
	Cust_Aband_Fund_List_Repository cust_abond_list_Repository;

	private static final Logger logger = LoggerFactory.getLogger(Cust_Aband_Fund_List_Service.class);

	@Autowired
	DataSource srcdataSource;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	Environment env;
	@Autowired
	BAML_RBS_PROCEDURE_REP bAML_RBS_PROCEDURE_REP;
	

	@Autowired
	ListManagementSchedulerServices listServices;
	
	  private static String[] columns = {"SL", "Customer ID", "Customer Name", "Account No","Account Name","Account Open Date","Type of account","Balance(Rs)","Interest Paid(Rs)","Date of Transfer to BOM"};
	   
	  private static List<Cust_Aband_Fund_List_Entity> aband_List =  new ArrayList<Cust_Aband_Fund_List_Entity>();

	  private static String[] columns_UNSC = {"SL", "Customer ID", "Last Name", "First Name","NID","Risk Category","Active Product type"};
	   
	  private static List<BAML_Cust_HNWI_RPT_Entity> unsc_List =  new ArrayList<BAML_Cust_HNWI_RPT_Entity>();
		 
	  @Autowired
		private BAML_Cust_HNWI_RPT_Repository baml_cust_hnwi_rpt_repository;
	  
	public String addABondList(Cust_Aband_Fund_List_Entity alertparam, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
		Session hs = sessionFactory.getCurrentSession();
		if (formmode.equals("add")) {
			Cust_Aband_Fund_List_Entity up = alertparam;

			up.setDel_flag("N");
			up.setEntity_flag("N");
			cust_abond_list_Repository.save(up);

			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String modi = "RECORD ADDED";

			audit.setAudit_date(new Date());
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setFunc_code("RECORD CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("CUST_ABAND_FUND_LIST");
			audit.setAudit_screen("ABAND_FUND LISTING MASTER");
			audit.setEvent_id(alertparam.getCif_id());
			audit.setEvent_name(alertparam.getAcid() + "-" + "ADDED");
			audit.setModi_details(modi);
			audit.setEntry_user(alertparam.getAml_entry_user());
			audit.setEntry_time(alertparam.getAml_entry_time());
			audit.setAudit_ref_no(Number.toString());
			auditLocal.save(audit);

			msg = "ABAND List Created Successfully";

		} else if (formmode.equals("edit")) {
			Cust_Aband_Fund_List_Entity up = alertparam;
			up.setDel_flag("N");
			up.setModify_flag("Y");
			up.setEntity_flag("N");
			cust_abond_list_Repository.save(up);
			msg = "ABAND List Edited Successfully";
		} else if (formmode.equals("delete")) {
			Optional<Cust_Aband_Fund_List_Entity> reg = cust_abond_list_Repository.findById(alertparam.getCif_id());
			if (reg.isPresent()) {
				Cust_Aband_Fund_List_Entity reg1 = new Cust_Aband_Fund_List_Entity();
				reg1 = reg.get();

				String modi = "";
				if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
					modi = modi + ("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag()
							+ '+');
				} else if (!reg1.getModify_flag().equals(alertparam.getModify_flag())) {
					modi = modi + ("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG "
							+ alertparam.getModify_flag() + '+');
				} else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
					modi = modi + ("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG "
							+ alertparam.getEntity_flag() + '+');
				}
				BigDecimal Number = (BigDecimal) hs
						.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();

				audit.setAudit_date(new Date());
				audit.setEntry_user(alertparam.getAml_entry_user());
				audit.setFunc_code("RECORD DELETED");
				audit.setRemarks("DELETED");
				audit.setAudit_table("CUST_ABAND_FUND_LIST");
				audit.setAudit_screen("ABAND_FUND LISTING MASTER");
				audit.setEvent_id(alertparam.getCif_id());
				audit.setEvent_name(alertparam.getCif_id() + "-" + "DELETED");
				audit.setModi_details(modi);
				audit.setEntry_user(alertparam.getAml_modify_user());
				audit.setEntry_time(alertparam.getAml_modify_time());
				audit.setAudit_ref_no(Number.toString());

				Cust_Aband_Fund_List_Entity up = alertparam;
				up.setDel_flag("Y");
				up.setEntity_flag("N");
				cust_abond_list_Repository.save(up);

				auditLocal.save(audit);

				msg = "ABAND List Deleted Successfully";
			}
		} else if (formmode.equals("verify")) {

			Cust_Aband_Fund_List_Entity up = alertparam;
			up.setEntity_flag("Y");
			up.setDel_flag("N");
			cust_abond_list_Repository.save(up);

			Optional<Cust_Aband_Fund_List_Entity> reg = cust_abond_list_Repository.findById(alertparam.getForacid());
			if (reg.isPresent()) {
				Cust_Aband_Fund_List_Entity reg1 = new Cust_Aband_Fund_List_Entity();
				reg1 = reg.get();

				String modi = "";
				if (!reg1.getDel_flag().equals(alertparam.getDel_flag())) {
					modi = modi + ("OLD DEL_FLAG " + reg1.getDel_flag() + "+ NEW DEL_FLAG " + alertparam.getDel_flag()
							+ '+');
				} else if (alertparam.getModify_flag() != null
						&& !reg1.getModify_flag().equals(alertparam.getModify_flag())) {
					modi = modi + ("OLD MODI_FLAG  " + reg1.getModify_flag() + "+ NEW MODI_FLAG "
							+ alertparam.getModify_flag() + '+');
				} else if (!reg1.getEntity_flag().equals(alertparam.getEntity_flag())) {
					modi = modi + ("OLD ENTITY_FLAG " + reg1.getEntity_flag() + "+ NEW ENTITY_FLAG "
							+ alertparam.getEntity_flag() + '+');
				}
				BigDecimal Number = (BigDecimal) hs
						.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();

				audit.setAudit_date(new Date());
				audit.setEntry_user(alertparam.getAml_entry_user());
				audit.setFunc_code("RECORD VERIFIED");
				audit.setRemarks("VERIFIED");
				audit.setAudit_table("CUST_ABAND_FUND_LIST");
				audit.setAudit_screen("ABAND_FUND LISTING MASTER");
				audit.setEvent_id(alertparam.getCif_id());
				audit.setEvent_name(alertparam.getCif_id() + "-" + "VERIFIED");
				audit.setModi_details(modi);
				audit.setEntry_user(alertparam.getAml_verify_user());
				audit.setEntry_time(alertparam.getAml_verify_time());
				audit.setAudit_ref_no(Number.toString());

				auditLocal.save(audit);

				msg = "ABAND List Verified Successfully";
			}
		}
		return msg;
	}

	public Cust_Aband_Fund_List_Entity getSrlNo(String srlno) {

		if (cust_abond_list_Repository.existsById(srlno)) {
			Cust_Aband_Fund_List_Entity up = cust_abond_list_Repository.findById(srlno).get();
			return up;
		} else {
			return new Cust_Aband_Fund_List_Entity();
		}

	};

	public String deleteParameter(String inputSrlNo) {
		String msg = "";
		Optional<Cust_Aband_Fund_List_Entity> user = cust_abond_list_Repository.findById(inputSrlNo);
		Cust_Aband_Fund_List_Entity reg = user.get();
		reg.setDel_flag("N");
		/* montParameterRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String inputSrlNo) {
		String msg = "";
		Optional<Cust_Aband_Fund_List_Entity> user = cust_abond_list_Repository.findById(inputSrlNo);
		Cust_Aband_Fund_List_Entity reg = user.get();

		reg.setDel_flag("N");
		msg = "User Deleted Successfully";
		return msg;
	}

	/*
	 * public String getNextRefValue() { // getting the next no for unique ref id
	 * 
	 * Session hs = sessionFactory.getCurrentSession();
	 * 
	 * DecimalFormat numformate = new DecimalFormat("0"); BigDecimal billNumber =
	 * (BigDecimal)
	 * hs.createNativeQuery("SELECT id FROM NEGATIVE_LIST_NUM").getSingleResult();
	 * 
	 * 
	 * String serialno = null; if (billNumber == null) { //********** incase num
	 * table is not having any vlaue then insert - NUM table shud contain
	 * //********* only one Row shud be there in this table at all times
	 * hs.createNativeQuery("insert into negative_list_num(id) values(1)").
	 * getSingleResult(); serialno = "1"; } else { serialno =
	 * numformate.format(billNumber); }
	 * 
	 * return serialno; }
	 */

	/*
	 * public void updateNegative_list_Num() { Session hs =
	 * sessionFactory.getCurrentSession(); // after save increement the num table
	 * value by 1 cust_hnwi_list_Repository.updatePepNumTB();
	 * 
	 * }
	 */
	public String processUpload(String screenId, MultipartFile file, String userid)
			throws SQLException, FileNotFoundException, IOException {
		Session hs = sessionFactory.getCurrentSession();
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
						for (int j = 0; j < 28; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);

						}

						String primary_Branch_Code = resultList.get(0);
						String branch_Name = resultList.get(1);
						String cif_ID = resultList.get(2);
						String cif_Name = resultList.get(3);
						String gl_sub_head_code = resultList.get(4);
						String schm_type = resultList.get(5);
						String schm_Code = resultList.get(6);
						String currency_Code = resultList.get(7);
						String account_No = resultList.get(8);
						String account_Name = resultList.get(9);
						String account_Open_Date = resultList.get(10);
						String account_Status = resultList.get(11);
						String last_Transaction_Date = resultList.get(12);
						String Account_Balance = resultList.get(13);
						String interest_Accrued = resultList.get(14);
						String interest_Paid = resultList.get(15);
						String abandoned_Fund_Transfer_Date = resultList.get(16);
						String remarks1 = resultList.get(17);

						String date_of_Transfer_to_BOM = resultList.get(18);

						String amount_Transferred = resultList.get(19);
						String date_of_Claim_by_Customer = resultList.get(20);
						String remarks2 = resultList.get(21);

						String request_to_BOM_Date = resultList.get(22);

						String date_of_Funds_received_from_BOM = resultList.get(23);
						String amount_Received = resultList.get(24);
						String settlement_Date = resultList.get(25);
						String amount_Paid = resultList.get(26);
						String address = resultList.get(27);
						
						if(account_Name.length()>80) {
							Errormsg = "failed - Exceeds the size limit " + "Please fix the Account Name of cif_id -" + cif_ID;
							continue;
						}

						Date request_to_BOM_Date1 = null;
						try {
							if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
								request_to_BOM_Date1 = new SimpleDateFormat("dd/MM/yy").parse(request_to_BOM_Date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
									request_to_BOM_Date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(request_to_BOM_Date);
								}
							} catch (Exception e1) {
								try {
									if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
										request_to_BOM_Date1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(request_to_BOM_Date);
									}
								} catch (Exception e111) {
									
									
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_ID
											+ " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}

						Date date_of_Transfer_to_BOM1 = null;
						try {
							if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
								date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Transfer_to_BOM);
							}
						} catch (Exception e) {
							logger.info("inside workbook5" + e.getMessage());
							try {
								if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
									date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_Transfer_to_BOM);
								}
							} catch (Exception e1) {
								try {
									if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
										date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(date_of_Transfer_to_BOM);
									}
								} catch (Exception e12) {
									Errormsg = "failed " + "Please fix the date_of_Transfer_to_BOM1 of cif_id -" + cif_ID
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}

						}

						if (account_No == null) {
							logger.info("inside workbook7");
							Errormsg = "failed " + "Please Enter the Account Number for cif_id " + cif_ID;
							continue;
						}

						Date settlement_Date1 = null;
						try {
							if (settlement_Date != null && !settlement_Date.isEmpty()) {
								settlement_Date1 = new SimpleDateFormat("dd/MM/yy").parse(settlement_Date);
							}
						} catch (Exception e) {
							try {
								logger.info("inside workbook9" + e.getMessage());
								if (settlement_Date != null && !settlement_Date.isEmpty()) {
									settlement_Date1 = new SimpleDateFormat("dd-MM-yyyy").parse(settlement_Date);
								}
							} catch (Exception e1) {
								try {
									logger.info("inside workbook9" + e.getMessage());
									if (settlement_Date != null && !settlement_Date.isEmpty()) {
										settlement_Date1 = new SimpleDateFormat("dd-MMM-yy").parse(settlement_Date);
									}
								} catch (Exception e145) {
									logger.info("inside workbook10" + e.getMessage());
									Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -"
											+ cif_ID + " Fromat to  (dd/MM/yy)";
									continue;

								}
							}

						}
						Date date_of_Funds_received_from_BOM1 = null;
						try {
							if (date_of_Funds_received_from_BOM != null && !date_of_Funds_received_from_BOM.isEmpty()) {
								date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Funds_received_from_BOM);
							}
						} catch (Exception e) {
							logger.info("inside workbook12" + e.getMessage());
							try {
								if (date_of_Funds_received_from_BOM != null
										&& !date_of_Funds_received_from_BOM.isEmpty()) {
									date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_Funds_received_from_BOM);
								}
							} catch (Exception e1) {
								try {
									if (date_of_Funds_received_from_BOM != null
											&& !date_of_Funds_received_from_BOM.isEmpty()) {
										date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_Funds_received_from_BOM);
									}
								} catch (Exception e14556) {
									try {
										if (date_of_Funds_received_from_BOM != null
												&& !date_of_Funds_received_from_BOM.isEmpty()) {
											date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MMM-yy")
													.parse(date_of_Funds_received_from_BOM);
										}
									} catch (Exception eddd) {
										Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -"
												+ cif_ID + " Fromat to  (dd/MM/yy)";
										continue;

									}

								}

							}

						}
						Date date_of_Claim_by_Customer1 = null;
						try {
							if (date_of_Claim_by_Customer != null && !date_of_Claim_by_Customer.isEmpty()) {
								date_of_Claim_by_Customer1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Claim_by_Customer);
							}
						} catch (Exception e) {
							try {
								logger.info("inside 14" + e.getMessage());
								if (date_of_Claim_by_Customer != null && !date_of_Claim_by_Customer.isEmpty()) {
									date_of_Claim_by_Customer1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_Claim_by_Customer);
								}
							} catch (Exception e1) {
								try {
									if (date_of_Funds_received_from_BOM != null
											&& !date_of_Funds_received_from_BOM.isEmpty()) {
										date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(date_of_Funds_received_from_BOM);
									}
								} catch (Exception e14556) {
									Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -"
											+ cif_ID + " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						Date abandoned_Fund_Transfer_Date1 = null;
						try {
							if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
								abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd/MM/yy")
										.parse(abandoned_Fund_Transfer_Date);
							}
						} catch (Exception e) {
							try {
								logger.info("inside 16" + e.getMessage());
								if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
									abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(abandoned_Fund_Transfer_Date);
								}
							} catch (Exception e1) {
								try {
									logger.info("inside 16" + e.getMessage());
									if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
										abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(abandoned_Fund_Transfer_Date);
									}
								} catch (Exception e21) {
									Errormsg = "failed " + "Please fix the abandoned_Fund_Transfer_Date1 of cif_id -"
											+ cif_ID + " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						Date account_Open_Date1 = null;
						try {
							if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
								account_Open_Date1 = new SimpleDateFormat("dd/MM/yy").parse(account_Open_Date);
							}
						} catch (Exception e) {
							try {
								logger.info("inside 18" + e.getMessage());
								if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
									account_Open_Date1 = new SimpleDateFormat("dd-MM-yyyy").parse(account_Open_Date);
								}
							} catch (Exception e1) {
								try {
									logger.info("inside 18" + e.getMessage());
									if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
										account_Open_Date1 = new SimpleDateFormat("dd-MMM-yy").parse(account_Open_Date);
									}
								} catch (Exception e41) {
									Errormsg = "failed " + "Please fix the account_Open_Date of cif_id -" + cif_ID
											+ " Fromat to (dd/MM/yy)";
									continue;

								}

							}

						}
						Date last_Transaction_Date1 = null;
						try {
							if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
								last_Transaction_Date1 = new SimpleDateFormat("dd/MM/yy").parse(last_Transaction_Date);
							}
						} catch (Exception e) {
							logger.info("inside 19" + e.getMessage());
							try {
								if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
									last_Transaction_Date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(last_Transaction_Date);
								}
							} catch (Exception e1) {
								logger.info("inside 19" + e.getMessage());
								try {
									if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
										last_Transaction_Date1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(last_Transaction_Date);
									}
								} catch (Exception e143) {
									Errormsg = "failed " + "Please fix the last_Transaction_Date1 of cif_id -" + cif_ID
											+ " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						logger.info("inside 121");
						BigDecimal account_Balance1 = null;
						try {
							if (Account_Balance != null && !Account_Balance.isEmpty()) {
								account_Balance1 = new BigDecimal(Account_Balance.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 121" + e.getMessage());
							Errormsg = "failed " + "Please fix the account_Balance";
							continue;
						}
						BigDecimal interest_Accrued1 = null;
						try {
							if (interest_Accrued != null && !interest_Accrued.isEmpty()) {
								if (interest_Accrued != null && !interest_Accrued.isEmpty() && interest_Accrued!="-" && interest_Accrued!="0") {
									logger.info("inside 122 BEFORE- cif_ID "+interest_Accrued1+"-"+cif_ID);
									interest_Accrued1 = new BigDecimal(interest_Accrued.replaceAll("[a-zA-Z,\", ]", "").trim());
								}
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the interest_Accrued for cust ID "+cif_ID;
							logger.info("inside 122" + e.getMessage() +"-"+Errormsg+interest_Accrued1);
							continue;
						}

						BigDecimal interest_Paid1 = null;
						try {
							if (interest_Paid != null && !interest_Paid.isEmpty()) {
								interest_Paid1 = new BigDecimal(interest_Paid.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 123" + e.getMessage());
							Errormsg = "failed " + "Please fix the interest_Paid";
							continue;
						}

						BigDecimal amount_Transferred1 = null;
						try {
							if (amount_Transferred != null && !amount_Transferred.isEmpty()) {
								amount_Transferred1 = new BigDecimal(
										amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 124" + e.getMessage());
							Errormsg = "failed " + "Please fix the amount_Transferred";
							continue;
						}

						BigDecimal amount_Received1 = null;
						try {
							if (amount_Received != null && !amount_Received.isEmpty()) {
								amount_Received1 = new BigDecimal(
										amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 125" + e.getMessage());
							Errormsg = "failed " + "Please fix the amount_Transferred";
							continue;
						}

						BigDecimal amount_Paid1 = null;
						try {
							if (amount_Paid != null && !amount_Paid.isEmpty()) {
								amount_Paid1 = new BigDecimal(amount_Paid.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 126" + e.getMessage());
							Errormsg = "failed " + "Please fix the amount_Paid";
							continue;
						}

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";

						Cust_Aband_Fund_List_Entity sup1000ManualS1 = new Cust_Aband_Fund_List_Entity(
								primary_Branch_Code, branch_Name, cif_ID, cif_Name, gl_sub_head_code, schm_type,
								schm_Code, currency_Code, account_No, account_Name, account_Open_Date1, account_Status,
								last_Transaction_Date1, account_Balance1, interest_Accrued1, interest_Paid1,
								abandoned_Fund_Transfer_Date1, remarks1, amount_Transferred1,
								date_of_Claim_by_Customer1, remarks2, date_of_Funds_received_from_BOM1,
								amount_Received1, settlement_Date1, amount_Paid1, address, date_of_Transfer_to_BOM1,
								request_to_BOM_Date1, entry_user, entry_time, entity_flg, del_flg);

						if (cust_abond_list_Repository.existsById(account_No)) {
							logger.info("inside final1");
							sup1000ManualS1.setCif_id(cif_ID);

							try {
								if (sup1000ManualS1.getAcid() == null) {
									sup1000ManualS1.setAcid(cust_abond_list_Repository.update_Acid(account_No));
								}
							} catch (Exception e) {
								logger.info("inside final2" + e.getMessage());
							}
							try {
								if (sup1000ManualS1.getAcct_opn_date() == null) {
									sup1000ManualS1
											.setAcct_opn_date(cust_abond_list_Repository.update_AccOpnDate(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getGl_sub_head_code() == null) {
									sup1000ManualS1.setGl_sub_head_code(
											cust_abond_list_Repository.update_Gl_sub_head_Code(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_type() == null) {
									sup1000ManualS1
											.setSchm_type(cust_abond_list_Repository.update_Schm_Type(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_code() == null) {
									sup1000ManualS1
											.setSchm_code(cust_abond_list_Repository.update_Schm_code(account_No));
								}
							} catch (Exception e) {
							}
							logger.info("updated values into Abond Fund List_1");
							cust_abond_list_Repository.save(sup1000ManualS1);
							status = "Successfully uploaded";
//							Errormsg=Errormsg+"  Cif_Id "+cif_ID +" already exists" ;
						} else {
							sup1000ManualS1.setCif_id(cif_ID);
							sup1000ManualS1.setForacid(account_No);
							if (sup1000ManualS1.getBranch_name() == null) {
								sup1000ManualS1.setBranch_name("PORT LOUIS");
							}
							if (sup1000ManualS1.getPrimary_branch_code() == null) {
								sup1000ManualS1.setPrimary_branch_code("01");
							}
								
							try {
								if (sup1000ManualS1.getAcid() == null) {

									sup1000ManualS1.setAcid(cust_abond_list_Repository.update_Acid(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getAcct_opn_date() == null) {
									sup1000ManualS1
											.setAcct_opn_date(cust_abond_list_Repository.update_AccOpnDate(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getGl_sub_head_code() == null) {
									sup1000ManualS1.setGl_sub_head_code(
											cust_abond_list_Repository.update_Gl_sub_head_Code(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_type() == null) {
									sup1000ManualS1
											.setSchm_type(cust_abond_list_Repository.update_Schm_Type(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_code() == null) {
									sup1000ManualS1
											.setSchm_code(cust_abond_list_Repository.update_Schm_code(account_No));
								}
							} catch (Exception e) {
							}

							logger.info("inserted values into Abond Fund List_1");

							cust_abond_list_Repository.save(sup1000ManualS1);

							AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
							BigDecimal Number = (BigDecimal) hs
									.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
									.getSingleResult();
							String modi = "RECORD ADDED";
							audit.setAudit_date(new Date());
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setFunc_code("RECORD CREATED");
							audit.setRemarks("ADDED");
							audit.setAudit_table("CUST_ABAND_FUND_LIST");
							audit.setAudit_screen("ABAND_FUND LISTING MASTER");
							audit.setEvent_id(sup1000ManualS1.getCif_id());
							audit.setEvent_name(sup1000ManualS1.getCif_id() + "-" + "ADDED");
							audit.setModi_details(modi);
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setEntry_time(sup1000ManualS1.getAml_entry_time());
							audit.setAudit_ref_no(Number.toString());
							auditLocal.save(audit);

							status = "Successfully uploaded";
//							theSession.flush();
//							theSession.clear();
						}
					}

					logger.info("inserted values into Abond Fund List");
				}

			} catch (Exception e) {
				logger.info("inside final" + e.getMessage());
				e.printStackTrace();
				status = "failed";
			}
		} else {
			logger.info("reading values from CSV");

			try {

				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {

					if (skipRow > 0) {

						String primary_Branch_Code = nextLine[0];
						String branch_Name = nextLine[1];
						String cif_ID = nextLine[2];
						String cif_Name = nextLine[3];
						String gl_sub_head_code = nextLine[4];
						String schm_type = nextLine[5];
						String schm_Code = nextLine[6];
						String currency_Code = nextLine[7];
						String account_No = nextLine[8];
						String account_Name = nextLine[9];
						String account_Open_Date = nextLine[10];
						String account_Status = nextLine[11];
						String last_Transaction_Date = nextLine[12];
						String Account_Balance = nextLine[13];
						String interest_Accrued = nextLine[14];
						String interest_Paid = nextLine[15];
						String abandoned_Fund_Transfer_Date = nextLine[16];
						String remarks1 = nextLine[17];
						String date_of_Transfer_to_BOM = nextLine[18];
						String amount_Transferred = nextLine[19];
						String date_of_Claim_by_Customer = nextLine[20];
						String remarks2 = nextLine[21];
						String request_to_BOM_Date = nextLine[22];
						String date_of_Funds_received_from_BOM = nextLine[23];
						String amount_Received = nextLine[24];
						String settlement_Date = nextLine[25];
						String amount_Paid = nextLine[26];
						String address = nextLine[27];

						Date request_to_BOM_Date1 = null;
						try {
							if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
								request_to_BOM_Date1 = new SimpleDateFormat("dd/MM/yy").parse(request_to_BOM_Date);
							}
						} catch (Exception e) {
							try {
								request_to_BOM_Date1 = new SimpleDateFormat("dd/MM/yy").parse(request_to_BOM_Date);
							} catch (Exception e1) {
								Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_ID
										+ " Fromat to  (dd/MM/yy)";
								continue;
							}

						}

						Date date_of_Transfer_to_BOM1 = null;
						try {
							if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
								date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Transfer_to_BOM);
							}
						} catch (Exception e) {
							try {
								date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Transfer_to_BOM);
							} catch (Exception e1) {
								Errormsg = "failed " + "Please fix the date_of_Transfer_to_BOM1 of cif_id -" + cif_ID
										+ " Fromat to  (dd/MM/yy)";
								continue;
							}

						}

						if (account_No != null && !account_No.isEmpty()) {
							Errormsg = "failed " + "Please Enter the Account Number ";
							continue;
						}

						Date settlement_Date1 = null;
						try {
							if (settlement_Date != null && !settlement_Date.isEmpty()) {
								settlement_Date1 = new SimpleDateFormat("dd/MM/yy").parse(settlement_Date);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date date_of_Funds_received_from_BOM1 = null;
						try {
							if (date_of_Funds_received_from_BOM != null && !date_of_Funds_received_from_BOM.isEmpty()) {
								date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Funds_received_from_BOM);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date date_of_Claim_by_Customer1 = null;
						try {
							if (date_of_Claim_by_Customer != null && !date_of_Claim_by_Customer.isEmpty()) {
								date_of_Claim_by_Customer1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Claim_by_Customer);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the date_of_Claim_by_Customer of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date abandoned_Fund_Transfer_Date1 = null;
						try {
							if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
								abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd/MM/yy")
										.parse(abandoned_Fund_Transfer_Date);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the abandoned_Fund_Transfer_Date1 of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date account_Open_Date1 = null;
						try {
							if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
								account_Open_Date1 = new SimpleDateFormat("dd/MM/yy").parse(account_Open_Date);
							}
						} catch (Exception e) {
							System.out.println(e);
							Errormsg = "failed " + "Please fix the account_Open_Date of cif_id -" + cif_ID
									+ " Fromat to (dd/MM/yy)";
							continue;

						}
						Date last_Transaction_Date1 = null;
						try {
							if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
								last_Transaction_Date1 = new SimpleDateFormat("dd/MM/yy").parse(last_Transaction_Date);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the last_Transaction_Date1 of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						BigDecimal account_Balance1 = null;
						if (Account_Balance != null && !Account_Balance.isEmpty()) {
							account_Balance1 = new BigDecimal(Account_Balance.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal interest_Accrued1 = null;
						if (interest_Accrued != null && !interest_Accrued.isEmpty()) {
							interest_Accrued1 = new BigDecimal(interest_Accrued.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal interest_Paid1 = null;
						if (interest_Paid != null && !interest_Paid.isEmpty()) {
							interest_Paid1 = new BigDecimal(interest_Paid.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal amount_Transferred1 = null;
						if (amount_Transferred != null && !amount_Transferred.isEmpty()) {
							amount_Transferred1 = new BigDecimal(amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
						}
						BigDecimal amount_Received1 = null;
						if (amount_Received != null && !amount_Received.isEmpty()) {
							amount_Received1 = new BigDecimal(amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal amount_Paid1 = null;
						if (amount_Paid != null && !amount_Paid.isEmpty()) {
							amount_Paid1 = new BigDecimal(amount_Paid.replaceAll("[a-zA-Z,]", "").trim());
						}

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";

						Cust_Aband_Fund_List_Entity sup1000ManualS1 = new Cust_Aband_Fund_List_Entity(
								primary_Branch_Code, branch_Name, cif_ID, cif_Name, gl_sub_head_code, schm_type,
								schm_Code, currency_Code, account_No, account_Name, account_Open_Date1, account_Status,
								last_Transaction_Date1, account_Balance1, interest_Accrued1, interest_Paid1,
								abandoned_Fund_Transfer_Date1, remarks1, amount_Transferred1,
								date_of_Claim_by_Customer1, remarks2, date_of_Funds_received_from_BOM1,
								amount_Received1, settlement_Date1, amount_Paid1, address, date_of_Transfer_to_BOM1,
								request_to_BOM_Date1, entry_user, entry_time, entity_flg, del_flg);

						if (cust_abond_list_Repository.existsById(account_No)) {
							sup1000ManualS1.setCif_id(cif_ID);
							sup1000ManualS1.setForacid(account_No);
							cust_abond_list_Repository.save(sup1000ManualS1);

//							Errormsg=Errormsg+"  Cif_Id "+cif_ID +" already exists" ;
						} else {
							sup1000ManualS1.setCif_id(cif_ID);
							sup1000ManualS1.setForacid(account_No);
							cust_abond_list_Repository.save(sup1000ManualS1);

							AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
							BigDecimal Number = (BigDecimal) hs
									.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
									.getSingleResult();
							String modi = "RECORD ADDED";
							audit.setAudit_date(new Date());
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setFunc_code("RECORD CREATED");
							audit.setRemarks("ADDED");
							audit.setAudit_table("CUST_ABAND_FUND_LIST");
							audit.setAudit_screen("ABAND_FUND LISTING MASTER");
							audit.setEvent_id(sup1000ManualS1.getCif_id());
							audit.setEvent_name(sup1000ManualS1.getCif_id() + "-" + "ADDED");
							audit.setModi_details(modi);
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setEntry_time(sup1000ManualS1.getAml_entry_time());
							audit.setAudit_ref_no(Number.toString());
							auditLocal.save(audit);

							status = "successfully uploaded";
//							theSession.flush();
//							theSession.clear();
						}
					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into aband fund ");

			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		if (!Errormsg.isEmpty()) {
			Errormsg = "Successfully uploaded Except for Customer id " + Errormsg;
			return Errormsg;
		} else {
			return status;
		}

	}

	public String processUploadRbs(String screenId, MultipartFile file, String userid)
			throws SQLException, FileNotFoundException, IOException, ParseException {
		Session hs = sessionFactory.getCurrentSession();
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
						for (int j = 0; j < 28; j++) {
							Cell cell = r.getCell(j);
							if (cell == null || cell.getStringCellValue().length() == 0) {
								val = null;
							} else {
								val = cell.getStringCellValue();
							}
							resultList.add(val);

						}

						String primary_Branch_Code = resultList.get(0);
						String branch_Name = resultList.get(1);
						String cif_ID = resultList.get(2);
						String cif_Name = resultList.get(3);
						String gl_sub_head_code = resultList.get(4);
						String schm_type = resultList.get(5);
						String schm_Code = resultList.get(6);
						String currency_Code = resultList.get(7);
						String account_No = resultList.get(8);
						String account_Name = resultList.get(9);
						String account_Open_Date = resultList.get(10);
						String account_Status = resultList.get(11);
						String last_Transaction_Date = resultList.get(12);
						String Account_Balance = resultList.get(13);
						String interest_Accrued = resultList.get(14);
						String interest_Paid = resultList.get(15);
						String abandoned_Fund_Transfer_Date = resultList.get(16);
						String remarks1 = resultList.get(17);

						String date_of_Transfer_to_BOM = resultList.get(18);

						String amount_Transferred = resultList.get(19);
						String date_of_Claim_by_Customer = resultList.get(20);
						String remarks2 = resultList.get(21);

						String request_to_BOM_Date = resultList.get(22);

						String date_of_Funds_received_from_BOM = resultList.get(23);
						String amount_Received = resultList.get(24);
						String settlement_Date = resultList.get(25);
						String amount_Paid = resultList.get(26);
						String address = resultList.get(27);
						
						if(account_Name.length()>80) {
							Errormsg = "failed - Exceeds the size limit " + "Please fix the Account Name of cif_id -" + cif_ID;
							continue;
						}

						Date request_to_BOM_Date1 = null;
						try {
							if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
								request_to_BOM_Date1 = new SimpleDateFormat("dd/MM/yy").parse(request_to_BOM_Date);
							}
						} catch (Exception e) {
							logger.info("inside workbook3" + e.getMessage());
							try {
								if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
									request_to_BOM_Date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(request_to_BOM_Date);
								}
							} catch (Exception e1) {
								try {
									if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
										request_to_BOM_Date1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(request_to_BOM_Date);
									}
								} catch (Exception e111) {
									
									
									Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_ID
											+ " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}

						Date date_of_Transfer_to_BOM1 = null;
						try {
							if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
								date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Transfer_to_BOM);
							}
						} catch (Exception e) {
							logger.info("inside workbook5" + e.getMessage());
							try {
								if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
									date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_Transfer_to_BOM);
								}
							} catch (Exception e1) {
								try {
									if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
										date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(date_of_Transfer_to_BOM);
									}
								} catch (Exception e12) {
									Errormsg = "failed " + "Please fix the date_of_Transfer_to_BOM1 of cif_id -" + cif_ID
											+ " Fromat to  (dd/MM/yy)";
									continue;
								}
							}

						}

						if (account_No == null) {
							logger.info("inside workbook7");
							Errormsg = "failed " + "Please Enter the Account Number for cif_id " + cif_ID;
							continue;
						}

						Date settlement_Date1 = null;
						try {
							if (settlement_Date != null && !settlement_Date.isEmpty()) {
								settlement_Date1 = new SimpleDateFormat("dd/MM/yy").parse(settlement_Date);
							}
						} catch (Exception e) {
							try {
								logger.info("inside workbook9" + e.getMessage());
								if (settlement_Date != null && !settlement_Date.isEmpty()) {
									settlement_Date1 = new SimpleDateFormat("dd-MM-yyyy").parse(settlement_Date);
								}
							} catch (Exception e1) {
								try {
									logger.info("inside workbook9" + e.getMessage());
									if (settlement_Date != null && !settlement_Date.isEmpty()) {
										settlement_Date1 = new SimpleDateFormat("dd-MMM-yy").parse(settlement_Date);
									}
								} catch (Exception e145) {
									logger.info("inside workbook10" + e.getMessage());
									Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -"
											+ cif_ID + " Fromat to  (dd/MM/yy)";
									continue;

								}
							}

						}
						Date date_of_Funds_received_from_BOM1 = null;
						try {
							if (date_of_Funds_received_from_BOM != null && !date_of_Funds_received_from_BOM.isEmpty()) {
								date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Funds_received_from_BOM);
							}
						} catch (Exception e) {
							logger.info("inside workbook12" + e.getMessage());
							try {
								if (date_of_Funds_received_from_BOM != null
										&& !date_of_Funds_received_from_BOM.isEmpty()) {
									date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_Funds_received_from_BOM);
								}
							} catch (Exception e1) {
								try {
									if (date_of_Funds_received_from_BOM != null
											&& !date_of_Funds_received_from_BOM.isEmpty()) {
										date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MMM-yyyy")
												.parse(date_of_Funds_received_from_BOM);
									}
								} catch (Exception e14556) {
									try {
										if (date_of_Funds_received_from_BOM != null
												&& !date_of_Funds_received_from_BOM.isEmpty()) {
											date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MMM-yy")
													.parse(date_of_Funds_received_from_BOM);
										}
									} catch (Exception eddd) {
										Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -"
												+ cif_ID + " Fromat to  (dd/MM/yy)";
										continue;

									}

								}

							}

						}
						Date date_of_Claim_by_Customer1 = null;
						try {
							if (date_of_Claim_by_Customer != null && !date_of_Claim_by_Customer.isEmpty()) {
								date_of_Claim_by_Customer1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Claim_by_Customer);
							}
						} catch (Exception e) {
							try {
								logger.info("inside 14" + e.getMessage());
								if (date_of_Claim_by_Customer != null && !date_of_Claim_by_Customer.isEmpty()) {
									date_of_Claim_by_Customer1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(date_of_Claim_by_Customer);
								}
							} catch (Exception e1) {
								try {
									if (date_of_Funds_received_from_BOM != null
											&& !date_of_Funds_received_from_BOM.isEmpty()) {
										date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(date_of_Funds_received_from_BOM);
									}
								} catch (Exception e14556) {
									Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -"
											+ cif_ID + " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						Date abandoned_Fund_Transfer_Date1 = null;
						try {
							if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
								abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd/MM/yy")
										.parse(abandoned_Fund_Transfer_Date);
							}
						} catch (Exception e) {
							try {
								logger.info("inside 16" + e.getMessage());
								if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
									abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(abandoned_Fund_Transfer_Date);
								}
							} catch (Exception e1) {
								try {
									logger.info("inside 16" + e.getMessage());
									if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
										abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(abandoned_Fund_Transfer_Date);
									}
								} catch (Exception e21) {
									Errormsg = "failed " + "Please fix the abandoned_Fund_Transfer_Date1 of cif_id -"
											+ cif_ID + " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						Date account_Open_Date1 = null;
						try {
							if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
								account_Open_Date1 = new SimpleDateFormat("dd/MM/yy").parse(account_Open_Date);
							}
						} catch (Exception e) {
							try {
								logger.info("inside 18" + e.getMessage());
								if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
									account_Open_Date1 = new SimpleDateFormat("dd-MM-yyyy").parse(account_Open_Date);
								}
							} catch (Exception e1) {
								try {
									logger.info("inside 18" + e.getMessage());
									if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
										account_Open_Date1 = new SimpleDateFormat("dd-MMM-yy").parse(account_Open_Date);
									}
								} catch (Exception e41) {
									Errormsg = "failed " + "Please fix the account_Open_Date of cif_id -" + cif_ID
											+ " Fromat to (dd/MM/yy)";
									continue;

								}

							}

						}
						Date last_Transaction_Date1 = null;
						try {
							if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
								last_Transaction_Date1 = new SimpleDateFormat("dd/MM/yy").parse(last_Transaction_Date);
							}
						} catch (Exception e) {
							logger.info("inside 19" + e.getMessage());
							try {
								if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
									last_Transaction_Date1 = new SimpleDateFormat("dd-MM-yyyy")
											.parse(last_Transaction_Date);
								}
							} catch (Exception e1) {
								logger.info("inside 19" + e.getMessage());
								try {
									if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
										last_Transaction_Date1 = new SimpleDateFormat("dd-MMM-yy")
												.parse(last_Transaction_Date);
									}
								} catch (Exception e143) {
									Errormsg = "failed " + "Please fix the last_Transaction_Date1 of cif_id -" + cif_ID
											+ " Fromat to  (dd/MM/yy)";
									continue;

								}

							}

						}
						logger.info("inside 121");
						BigDecimal account_Balance1 = null;
						try {
							if (Account_Balance != null && !Account_Balance.isEmpty()) {
								account_Balance1 = new BigDecimal(Account_Balance.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 121" + e.getMessage());
							Errormsg = "failed " + "Please fix the account_Balance";
							continue;
						}
						BigDecimal interest_Accrued1 = null;
						try {
							if (interest_Accrued != null && !interest_Accrued.isEmpty()) {
								if (interest_Accrued != null && !interest_Accrued.isEmpty() && interest_Accrued!="-" && interest_Accrued!="0") {
									logger.info("inside 122 BEFORE- cif_ID "+interest_Accrued1+"-"+cif_ID);
									interest_Accrued1 = new BigDecimal(interest_Accrued.replaceAll("[a-zA-Z,\", ]", "").trim());
								}
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the interest_Accrued for cust ID "+cif_ID;
							logger.info("inside 122" + e.getMessage() +"-"+Errormsg+interest_Accrued1);
							continue;
						}

						BigDecimal interest_Paid1 = null;
						try {
							if (interest_Paid != null && !interest_Paid.isEmpty()) {
								interest_Paid1 = new BigDecimal(interest_Paid.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 123" + e.getMessage());
							Errormsg = "failed " + "Please fix the interest_Paid";
							continue;
						}

						BigDecimal amount_Transferred1 = null;
						try {
							if (amount_Transferred != null && !amount_Transferred.isEmpty()) {
								amount_Transferred1 = new BigDecimal(
										amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 124" + e.getMessage());
							Errormsg = "failed " + "Please fix the amount_Transferred";
							continue;
						}

						BigDecimal amount_Received1 = null;
						try {
							if (amount_Received != null && !amount_Received.isEmpty()) {
								amount_Received1 = new BigDecimal(
										amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 125" + e.getMessage());
							Errormsg = "failed " + "Please fix the amount_Transferred";
							continue;
						}

						BigDecimal amount_Paid1 = null;
						try {
							if (amount_Paid != null && !amount_Paid.isEmpty()) {
								amount_Paid1 = new BigDecimal(amount_Paid.replaceAll("[a-zA-Z,]", "").trim());
							}
						} catch (Exception e) {
							logger.info("inside 126" + e.getMessage());
							Errormsg = "failed " + "Please fix the amount_Paid";
							continue;
						}

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";

						Cust_Aband_Fund_List_Entity sup1000ManualS1 = new Cust_Aband_Fund_List_Entity(
								primary_Branch_Code, branch_Name, cif_ID, cif_Name, gl_sub_head_code, schm_type,
								schm_Code, currency_Code, account_No, account_Name, account_Open_Date1, account_Status,
								last_Transaction_Date1, account_Balance1, interest_Accrued1, interest_Paid1,
								abandoned_Fund_Transfer_Date1, remarks1, amount_Transferred1,
								date_of_Claim_by_Customer1, remarks2, date_of_Funds_received_from_BOM1,
								amount_Received1, settlement_Date1, amount_Paid1, address, date_of_Transfer_to_BOM1,
								request_to_BOM_Date1, entry_user, entry_time, entity_flg, del_flg);

						if (cust_abond_list_Repository.existsById(account_No)) {
							logger.info("inside final1");
							sup1000ManualS1.setCif_id(cif_ID);

							try {
								if (sup1000ManualS1.getAcid() == null) {
									sup1000ManualS1.setAcid(cust_abond_list_Repository.update_Acid(account_No));
								}
							} catch (Exception e) {
								logger.info("inside final2" + e.getMessage());
							}
							try {
								if (sup1000ManualS1.getAcct_opn_date() == null) {
									sup1000ManualS1
											.setAcct_opn_date(cust_abond_list_Repository.update_AccOpnDate(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getGl_sub_head_code() == null) {
									sup1000ManualS1.setGl_sub_head_code(
											cust_abond_list_Repository.update_Gl_sub_head_Code(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_type() == null) {
									sup1000ManualS1
											.setSchm_type(cust_abond_list_Repository.update_Schm_Type(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_code() == null) {
									sup1000ManualS1
											.setSchm_code(cust_abond_list_Repository.update_Schm_code(account_No));
								}
							} catch (Exception e) {
							}
							logger.info("updated values into Abond Fund List_1");
							cust_abond_list_Repository.save(sup1000ManualS1);
							status = "Successfully uploaded";
//							Errormsg=Errormsg+"  Cif_Id "+cif_ID +" already exists" ;
						} else {
							sup1000ManualS1.setCif_id(cif_ID);
							sup1000ManualS1.setForacid(account_No);
							if (sup1000ManualS1.getBranch_name() == null) {
								sup1000ManualS1.setBranch_name("PORT LOUIS");
							}
							if (sup1000ManualS1.getPrimary_branch_code() == null) {
								sup1000ManualS1.setPrimary_branch_code("01");
							}
								
							try {
								if (sup1000ManualS1.getAcid() == null) {

									sup1000ManualS1.setAcid(cust_abond_list_Repository.update_Acid(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getAcct_opn_date() == null) {
									sup1000ManualS1
											.setAcct_opn_date(cust_abond_list_Repository.update_AccOpnDate(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getGl_sub_head_code() == null) {
									sup1000ManualS1.setGl_sub_head_code(
											cust_abond_list_Repository.update_Gl_sub_head_Code(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_type() == null) {
									sup1000ManualS1
											.setSchm_type(cust_abond_list_Repository.update_Schm_Type(account_No));
								}
							} catch (Exception e) {
							}
							try {
								if (sup1000ManualS1.getSchm_code() == null) {
									sup1000ManualS1
											.setSchm_code(cust_abond_list_Repository.update_Schm_code(account_No));
								}
							} catch (Exception e) {
							}

							logger.info("inserted values into Abond Fund List_1");

							cust_abond_list_Repository.save(sup1000ManualS1);

							AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
							BigDecimal Number = (BigDecimal) hs
									.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
									.getSingleResult();
							String modi = "RECORD ADDED";
							audit.setAudit_date(new Date());
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setFunc_code("RECORD CREATED");
							audit.setRemarks("ADDED");
							audit.setAudit_table("CUST_ABAND_FUND_LIST");
							audit.setAudit_screen("ABAND_FUND LISTING MASTER");
							audit.setEvent_id(sup1000ManualS1.getCif_id());
							audit.setEvent_name(sup1000ManualS1.getCif_id() + "-" + "ADDED");
							audit.setModi_details(modi);
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setEntry_time(sup1000ManualS1.getAml_entry_time());
							audit.setAudit_ref_no(Number.toString());
							auditLocal.save(audit);

							status = "Successfully uploaded";
//							theSession.flush();
//							theSession.clear();
							System.out.println(screenId);
						}
					}

					logger.info("inserted values into Abond Fund List");
				}

			} catch (Exception e) {
				logger.info("inside final" + e.getMessage());
				e.printStackTrace();
				status = "failed";
			}
		} else {
			logger.info("reading values from CSV");

			try {

				CSVReader reader = new CSVReader(new FileReader(convertedFile.getAbsolutePath()), ',');
				String[] nextLine;
				int skipRow = 0;
				while ((nextLine = reader.readNext()) != null) {

					if (skipRow > 0) {

						String primary_Branch_Code = nextLine[0];
						String branch_Name = nextLine[1];
						String cif_ID = nextLine[2];
						String cif_Name = nextLine[3];
						String gl_sub_head_code = nextLine[4];
						String schm_type = nextLine[5];
						String schm_Code = nextLine[6];
						String currency_Code = nextLine[7];
						String account_No = nextLine[8];
						String account_Name = nextLine[9];
						String account_Open_Date = nextLine[10];
						String account_Status = nextLine[11];
						String last_Transaction_Date = nextLine[12];
						String Account_Balance = nextLine[13];
						String interest_Accrued = nextLine[14];
						String interest_Paid = nextLine[15];
						String abandoned_Fund_Transfer_Date = nextLine[16];
						String remarks1 = nextLine[17];
						String date_of_Transfer_to_BOM = nextLine[18];
						String amount_Transferred = nextLine[19];
						String date_of_Claim_by_Customer = nextLine[20];
						String remarks2 = nextLine[21];
						String request_to_BOM_Date = nextLine[22];
						String date_of_Funds_received_from_BOM = nextLine[23];
						String amount_Received = nextLine[24];
						String settlement_Date = nextLine[25];
						String amount_Paid = nextLine[26];
						String address = nextLine[27];

						Date request_to_BOM_Date1 = null;
						try {
							if (request_to_BOM_Date != null && !request_to_BOM_Date.isEmpty()) {
								request_to_BOM_Date1 = new SimpleDateFormat("dd/MM/yy").parse(request_to_BOM_Date);
							}
						} catch (Exception e) {
							try {
								request_to_BOM_Date1 = new SimpleDateFormat("dd/MM/yy").parse(request_to_BOM_Date);
							} catch (Exception e1) {
								Errormsg = "failed " + "Please fix the request_to_BOM_Date of cif_id -" + cif_ID
										+ " Fromat to  (dd/MM/yy)";
								continue;
							}

						}

						Date date_of_Transfer_to_BOM1 = null;
						try {
							if (date_of_Transfer_to_BOM != null && !date_of_Transfer_to_BOM.isEmpty()) {
								date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Transfer_to_BOM);
							}
						} catch (Exception e) {
							try {
								date_of_Transfer_to_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Transfer_to_BOM);
							} catch (Exception e1) {
								Errormsg = "failed " + "Please fix the date_of_Transfer_to_BOM1 of cif_id -" + cif_ID
										+ " Fromat to  (dd/MM/yy)";
								continue;
							}

						}

						if (account_No != null && !account_No.isEmpty()) {
							Errormsg = "failed " + "Please Enter the Account Number ";
							continue;
						}

						Date settlement_Date1 = null;
						try {
							if (settlement_Date != null && !settlement_Date.isEmpty()) {
								settlement_Date1 = new SimpleDateFormat("dd/MM/yy").parse(settlement_Date);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date date_of_Funds_received_from_BOM1 = null;
						try {
							if (date_of_Funds_received_from_BOM != null && !date_of_Funds_received_from_BOM.isEmpty()) {
								date_of_Funds_received_from_BOM1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Funds_received_from_BOM);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the date_of_Funds_received_from_BOM of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date date_of_Claim_by_Customer1 = null;
						try {
							if (date_of_Claim_by_Customer != null && !date_of_Claim_by_Customer.isEmpty()) {
								date_of_Claim_by_Customer1 = new SimpleDateFormat("dd/MM/yy")
										.parse(date_of_Claim_by_Customer);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the date_of_Claim_by_Customer of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date abandoned_Fund_Transfer_Date1 = null;
						try {
							if (abandoned_Fund_Transfer_Date != null && !abandoned_Fund_Transfer_Date.isEmpty()) {
								abandoned_Fund_Transfer_Date1 = new SimpleDateFormat("dd/MM/yy")
										.parse(abandoned_Fund_Transfer_Date);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the abandoned_Fund_Transfer_Date1 of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						Date account_Open_Date1 = null;
						try {
							if (account_Open_Date != null && !account_Open_Date.isEmpty()) {
								account_Open_Date1 = new SimpleDateFormat("dd/MM/yy").parse(account_Open_Date);
							}
						} catch (Exception e) {
							System.out.println(e);
							Errormsg = "failed " + "Please fix the account_Open_Date of cif_id -" + cif_ID
									+ " Fromat to (dd/MM/yy)";
							continue;

						}
						Date last_Transaction_Date1 = null;
						try {
							if (last_Transaction_Date != null && !last_Transaction_Date.isEmpty()) {
								last_Transaction_Date1 = new SimpleDateFormat("dd/MM/yy").parse(last_Transaction_Date);
							}
						} catch (Exception e) {
							Errormsg = "failed " + "Please fix the last_Transaction_Date1 of cif_id -" + cif_ID
									+ " Fromat to  (dd/MM/yy)";
							continue;

						}
						BigDecimal account_Balance1 = null;
						if (Account_Balance != null && !Account_Balance.isEmpty()) {
							account_Balance1 = new BigDecimal(Account_Balance.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal interest_Accrued1 = null;
						if (interest_Accrued != null && !interest_Accrued.isEmpty()) {
							interest_Accrued1 = new BigDecimal(interest_Accrued.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal interest_Paid1 = null;
						if (interest_Paid != null && !interest_Paid.isEmpty()) {
							interest_Paid1 = new BigDecimal(interest_Paid.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal amount_Transferred1 = null;
						if (amount_Transferred != null && !amount_Transferred.isEmpty()) {
							amount_Transferred1 = new BigDecimal(amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
						}
						BigDecimal amount_Received1 = null;
						if (amount_Received != null && !amount_Received.isEmpty()) {
							amount_Received1 = new BigDecimal(amount_Transferred.replaceAll("[a-zA-Z,]", "").trim());
						}

						BigDecimal amount_Paid1 = null;
						if (amount_Paid != null && !amount_Paid.isEmpty()) {
							amount_Paid1 = new BigDecimal(amount_Paid.replaceAll("[a-zA-Z,]", "").trim());
						}

						String entry_user = userid;
						Date entry_time = new Date();
						String entity_flg = "Y";
						String del_flg = "N";

						Cust_Aband_Fund_List_Entity sup1000ManualS1 = new Cust_Aband_Fund_List_Entity(
								primary_Branch_Code, branch_Name, cif_ID, cif_Name, gl_sub_head_code, schm_type,
								schm_Code, currency_Code, account_No, account_Name, account_Open_Date1, account_Status,
								last_Transaction_Date1, account_Balance1, interest_Accrued1, interest_Paid1,
								abandoned_Fund_Transfer_Date1, remarks1, amount_Transferred1,
								date_of_Claim_by_Customer1, remarks2, date_of_Funds_received_from_BOM1,
								amount_Received1, settlement_Date1, amount_Paid1, address, date_of_Transfer_to_BOM1,
								request_to_BOM_Date1, entry_user, entry_time, entity_flg, del_flg);

						if (cust_abond_list_Repository.existsById(account_No)) {
							sup1000ManualS1.setCif_id(cif_ID);
							sup1000ManualS1.setForacid(account_No);
							cust_abond_list_Repository.save(sup1000ManualS1);

//							Errormsg=Errormsg+"  Cif_Id "+cif_ID +" already exists" ;
						} else {
							sup1000ManualS1.setCif_id(cif_ID);
							sup1000ManualS1.setForacid(account_No);
							cust_abond_list_Repository.save(sup1000ManualS1);

							AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
							BigDecimal Number = (BigDecimal) hs
									.createNativeQuery("SELECT AML_AUDIT_SEQ.NEXTVAL AS SRL_NO FROM DUAL")
									.getSingleResult();
							String modi = "RECORD ADDED";
							audit.setAudit_date(new Date());
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setFunc_code("RECORD CREATED");
							audit.setRemarks("ADDED");
							audit.setAudit_table("CUST_ABAND_FUND_LIST");
							audit.setAudit_screen("ABAND_FUND LISTING MASTER");
							audit.setEvent_id(sup1000ManualS1.getCif_id());
							audit.setEvent_name(sup1000ManualS1.getCif_id() + "-" + "ADDED");
							audit.setModi_details(modi);
							audit.setEntry_user(sup1000ManualS1.getAml_entry_user());
							audit.setEntry_time(sup1000ManualS1.getAml_entry_time());
							audit.setAudit_ref_no(Number.toString());
							auditLocal.save(audit);

							status = "successfully uploaded";
//							theSession.flush();
//							theSession.clear();
							StoredProcedureQuery query1 = theSession.createStoredProcedureQuery("T22_RBS_DETAIL_SP")
									.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
							query1.setParameter("REPORT_DATE", screenId);
							query1.execute();
						}
						
					}
					skipRow++;
				}
				// theSession.saveOrUpdate(sup1000ManualS1List);
				logger.info("inserted values into aband fund ");

			} catch (Exception e) {
				e.printStackTrace();
				status = "failed";
			}

		}

		if (!Errormsg.isEmpty()) {
			Errormsg = "Successfully uploaded Except for Customer id " + Errormsg;
			return Errormsg;
		} else {
			Optional<BAML_RBS_REPORT_PROCEDURE> account = bAML_RBS_PROCEDURE_REP.findById("T22D");
			BAML_RBS_REPORT_PROCEDURE up = account.get();
			up.setReport_flag('Y');
			Date dt1;
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(screenId);
			up.setReport_date(dt1);
			bAML_RBS_PROCEDURE_REP.save(up);
/*
			StoredProcedureQuery query1 = theSession.createStoredProcedureQuery("T22_RBS_DETAIL_SP")
					.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
			query1.setParameter("REPORT_DATE", screenId);
			query1.execute();*/
			
			return status;
		}

	}
	public static File multipartToFile(MultipartFile multipart, String fileName)
			throws IllegalStateException, IOException {

		Path newFile = Paths.get(multipart.getOriginalFilename());
		try (InputStream is = multipart.getInputStream(); OutputStream os = Files.newOutputStream(newFile)) {
			byte[] buffer = new byte[4096];
			int read = 0;
			while ((read = is.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
		}
		return newFile.toFile();

//			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
//			multipart.transferTo(convFile);
//			return convFile;
	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String path = this.env.getProperty("output.exportpath");
		String fileName = "";

		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		try {

			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + reportId);
				System.out.println("xlsx");
				fileStream = this.getClass()
						.getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/AbandFund.jasper");

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");

			logger.info("BEFORE GENERATING PDF :" + reportId);
				fileName = fileName + ".pdf";
				path += fileName;
				logger.info("BEFORE GENERATING PDF 1 :" + path);
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
	public ByteArrayInputStream getFile_Aband_fund_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException, ParseException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Session hs = sessionFactory.getCurrentSession();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");



		
		String fileName = "";

		FileOutputStream outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		try {
			InputStream fileStream = null;

			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("ABAND_LIST");

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
			cellReporName.setCellValue("Abandoned Fund Listing Report");
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

			aband_List = cust_abond_list_Repository.paramlist();
			for (Cust_Aband_Fund_List_Entity pep_List : aband_List) {
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
private void writeBook(Cust_Aband_Fund_List_Entity aBook, Row row, CellStyle dateCellStyle,int sn,CellStyle dateCell,CellStyle numStyle) {

	Cell cell = row.createCell(0);
	cell.setCellValue(sn);
	cell.setCellStyle(dateCell);

	Cell cif = row.createCell(1);
	cif.setCellValue(aBook.getCif_id());
	cif.setCellStyle(dateCell);

	Cell cust_name = row.createCell(2);
	cust_name.setCellValue(aBook.getCust_name());
	cust_name.setCellStyle(dateCell);
	
	Cell foracid = row.createCell(3);
	foracid.setCellValue(aBook.getForacid());
	foracid.setCellStyle(dateCell);
	
	Cell acct_Name = row.createCell(4);
	acct_Name.setCellValue(aBook.getAcct_name());
	acct_Name.setCellStyle(dateCell);
	
	Cell acc_open_Date = row.createCell(5);
	acc_open_Date.setCellValue(aBook.getAcct_opn_date());
	acc_open_Date.setCellStyle(dateCellStyle);
	
	Cell schm_type = row.createCell(6);
	schm_type.setCellValue(aBook.getSchm_type());
	schm_type.setCellStyle(dateCell);
	
	Cell acc_bal = row.createCell(7);
	if(aBook.getAcct_bal()!=null) {
	acc_bal.setCellValue(aBook.getAcct_bal().toString());
	}else {
		acc_bal.setCellValue("");

	}
	acc_bal.setCellStyle(numStyle);
	
	Cell interestPaid = row.createCell(8);
	if(aBook.getInterest_paid()!=null) {
		interestPaid.setCellValue(aBook.getInterest_paid().toString());
	}else {
		interestPaid.setCellValue("");
	}
	interestPaid.setCellStyle(numStyle);

	Cell dateoftran = row.createCell(9);
	dateoftran.setCellValue(aBook.getDate_of_tran_bom());
	dateoftran.setCellStyle(dateCellStyle);

}
	public File getUNSCFile(String userid, String reportId, String fromdate, String todate, String currency,
			String dtltype, String filetype) throws FileNotFoundException, JRException, SQLException {

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
		String path = "";
		String fileName = "";

		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		if (!filetype.equals("xbrl")) {
			try {
				InputStream fileStream = null;
				logger.info("Getting Jasper file :" + reportId);
					fileStream = this.getClass()
							.getResourceAsStream("/static/jasper/AMLReports/MonitoringReports/UNSC.jasper");

				JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("TO_DATE", todate);
				map.put("FROM_DATE", fromdate);

				logger.info("BEFORE GENERATING PDF :" + reportId);
					fileName = fileName + ".pdf";
					path = fileName;
					logger.info("BEFORE GENERATING PDF 1 :" + reportId);
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					logger.info("BEFORE GENERATING PDF 2 :" + path);
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		outputFile = new File(path);

		
		
		
		return outputFile;

	}
	
	public ByteArrayInputStream getFile_UNSC_Excel(String userid,String reportId, String fromdate, String todate, String dtltype,
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

			unsc_List = baml_cust_hnwi_rpt_repository.findAllCustUNSClistReport(fromDAte,toDAte);
			for (BAML_Cust_HNWI_RPT_Entity pep_List : unsc_List) {
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
			
			Cell type = row.createCell(6);
			type.setCellValue(aBook.getACTIVE_PRODUCT_TYPE());
			type.setCellStyle(dateCell);

		}


		public void executeListManagement() throws JRException, SQLException, IOException {
			logger.info("Scheduler for ListManagement Begins");		logger.info("Scheduler for ListManagement Begins");
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			String path_xlsx =  env.getProperty("output.exportpath");
			String path_pdf =  env.getProperty("output.exportpath");
			
			String path_new_xlsx =  env.getProperty("output.exportpath");
			String path_new_pdf =  env.getProperty("output.exportpath");
			
			String path_cust_chk_xlsx =  env.getProperty("output.exportpath");
			String path_cust_chk_pdf =  env.getProperty("output.exportpath");

			Date sysDate=new Date();
			String str_Date=dateFormat.format(sysDate);
			
			String fileName = "";
			String filename_PDF = "";
			File outputFile_PDF;
			File outputFile_XLSX;
			
			
			fileName = "List_Management_Consolidated_check" +  "_" +str_Date;
			filename_PDF="List_Management_Consolidated_check" +  "_" +str_Date;
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + "List_Management_Consolidated_check");
			  
			fileStream = this.getClass().getResourceAsStream("/static/jasper/LIST_SCHD/ListSchedulerCons.jasper");

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("REPORT_DATE", str_Date);

			logger.info("BEFORE GENERATING XLSX :" + "List_Management_Consolidated_check");
			fileName = fileName + ".xlsx";
			path_xlsx +=   fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path_xlsx));
			exporter.exportReport();
			logger.info("Excel File exported");
			
			outputFile_XLSX = new File(path_xlsx);
			
			logger.info("BEFORE GENERATING PDF :" + "List_Management_Consolidated_check");
			
			filename_PDF = filename_PDF + ".pdf";
			path_pdf +=  filename_PDF;
			JasperPrint jppdf = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			logger.info("BEFORE GENERATING PDF 2 :" + filename_PDF);
			JasperExportManager.exportReportToPdfFile(jppdf, path_pdf);
			logger.info("PDF File exported");
		   	
			outputFile_PDF = new File(path_pdf);

//			********************************XLSX and pdf for new list************************************
			
			String fileName_new = "";
			String filename_PDF_new = "";
			File outputFile_PDF_new;
			File outputFile_XLSX_new;
			
			
			fileName_new = "List_Management_New_List_check" +  "_" +str_Date;
			filename_PDF_new="List_Management_New_List_check" +  "_" +str_Date;
			InputStream fileStream_new = null;
			logger.info("Getting Jasper file :" + "List_Management_New_List_check");
			  
			fileStream_new = this.getClass().getResourceAsStream("/static/jasper/LIST_SCHD/ListSchedulerNewList.jasper");

			JasperReport jr_new = (JasperReport) JRLoader.loadObject(fileStream_new);
			HashMap<String, Object> map_new = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map_new.put("REPORT_DATE", str_Date);

			logger.info("BEFORE GENERATING XLSX :" + "List_Management_New_List_check");
			fileName_new = fileName_new + ".xlsx";
			path_new_xlsx +=   fileName_new;
			JasperPrint jp_new = JasperFillManager.fillReport(jr_new, map_new, srcdataSource.getConnection());
			JRXlsxExporter exporter_new = new JRXlsxExporter();
			exporter_new.setExporterInput(new SimpleExporterInput(jp_new));
			exporter_new.setExporterOutput(new SimpleOutputStreamExporterOutput(path_new_xlsx));
			exporter_new.exportReport();
			logger.info("Excel File exported");
			
			outputFile_XLSX_new = new File(path_new_xlsx);
			
			logger.info("BEFORE GENERATING PDF :" + "List_Management_New_List_check");
			
			filename_PDF_new = filename_PDF_new + ".pdf";
			path_new_pdf +=  filename_PDF_new;
			JasperPrint jppdf_new = JasperFillManager.fillReport(jr_new, map_new, srcdataSource.getConnection());
			logger.info("BEFORE GENERATING PDF 2 :" + filename_PDF);
			JasperExportManager.exportReportToPdfFile(jppdf_new, path_new_pdf);
			logger.info("PDF File exported");
		   	
			outputFile_PDF_new = new File(path_new_pdf);

//			**********************************new cust check*****************************************
			
			String fileName_cust = "";
			String filename_PDF_cust = "";
			File outputFile_PDF_cust;
			File outputFile_XLSX_cust;
			
			
			fileName_cust = "List_Management_New_Customer_check" +  "_" +str_Date;
			filename_PDF_cust="List_Management_New_Customer_check" +  "_" +str_Date;
			InputStream fileStream_cust = null;
			logger.info("Getting Jasper file :" + "List_Management_New_Customer_check");
			  
			fileStream_cust = this.getClass().getResourceAsStream("/static/jasper/LIST_SCHD/ListSchedulerNewCustChk.jasper");

			JasperReport jr_cust = (JasperReport) JRLoader.loadObject(fileStream_cust);
			HashMap<String, Object> map_cust = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map_cust.put("REPORT_DATE", str_Date);

			logger.info("BEFORE GENERATING XLSX :" + "List_Management_New_List_check");
			fileName_cust = fileName_cust + ".xlsx";
			path_cust_chk_xlsx +=   fileName_cust;
			JasperPrint jp_cust = JasperFillManager.fillReport(jr_cust, map_cust, srcdataSource.getConnection());
			JRXlsxExporter exporter_cust = new JRXlsxExporter();
			exporter_cust.setExporterInput(new SimpleExporterInput(jp_cust));
			exporter_cust.setExporterOutput(new SimpleOutputStreamExporterOutput(path_cust_chk_xlsx));
			exporter_cust.exportReport();
			logger.info("Excel File exported");
			
			outputFile_XLSX_cust = new File(path_cust_chk_xlsx);
			
			logger.info("BEFORE GENERATING PDF :" + "List_Management_New_List_check");
			
			filename_PDF_cust = filename_PDF_cust + ".pdf";
			path_cust_chk_pdf +=  filename_PDF_cust;
			JasperPrint jppdf_cust = JasperFillManager.fillReport(jr_cust, map_cust, srcdataSource.getConnection());
			logger.info("BEFORE GENERATING PDF 2 :" + filename_PDF_cust);
			JasperExportManager.exportReportToPdfFile(jppdf_cust, path_cust_chk_pdf);
			logger.info("PDF File exported");
		   	
			outputFile_PDF_cust = new File(path_cust_chk_pdf);
			
			//***********************Email Code 
			logger.info("BEFORE ENTERING EMAIL ");

			listServices.sendEmail(fileName,fileName_new,fileName_cust);
			
			
	  }
}
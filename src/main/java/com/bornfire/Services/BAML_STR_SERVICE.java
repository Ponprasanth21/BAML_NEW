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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AML_STR_ENTITY;
import com.bornfire.entity.BAMLSearchFilter;
import com.bornfire.entity.BAMLSearchIntFilter;
import com.bornfire.entity.BAMLThirdPartySearchFilter;
import com.bornfire.entity.BAML_STR;
import com.bornfire.entity.BAML_STR_Internal;
import com.bornfire.entity.STR_REPOSITRY;
import com.bornfire.entity.TRAN_MASTER_REPOSITRY;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
@Transactional
@ConfigurationProperties("output")
public class BAML_STR_SERVICE {

	private static final Logger logger = LoggerFactory.getLogger(BAML_STR_SERVICE.class);

	@Autowired
	STR_REPOSITRY str_rep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	private TRAN_MASTER_REPOSITRY TRANMaster;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	private String pstd_date;

	public String addPARAMETER(AML_STR_ENTITY alertparam, String Formmode, String Userid) {
		Session hs = sessionFactory.getCurrentSession();

		String msg = "";
		if (Formmode.equals("add")) {
			AML_STR_ENTITY up = alertparam;

			BigDecimal Number1 = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();

			DecimalFormat numformate = new DecimalFormat("000");
			BigDecimal billNumber = (BigDecimal) hs
					.createNativeQuery("SELECT STR_RECORD_ID.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
			String serialno = "RPT" + numformate.format(billNumber);
			System.out.println("billno" + serialno);
			up.setReport_id(serialno);

			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(Userid);
			audit.setFunc_code("STR REPORT CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("BAML_STR_TABLE");
			audit.setAudit_screen("STR REPORT CREATED");
			audit.setEvent_id(up.getReport_id());
			audit.setEvent_name("STR REPORT");
			audit.setModi_details("REPORT CREATED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);
			str_rep.save(up);

			msg = "STR Created: RECORD_ID: " + serialno;
		}
		return msg;
	}

	public String addPARAMETER1(AML_STR_ENTITY alertparam, String Formmode, String Userid, String reportid) {
		Session hs = sessionFactory.getCurrentSession();

		String msg = "";
		if (Formmode.equals("edit")) {
			AML_STR_ENTITY up = alertparam;

			BigDecimal Number1 = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			up.setReport_id(reportid);
			str_rep.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(Userid);
			audit.setFunc_code("STR REPORT MODIFIED");
			audit.setRemarks("MODIFIED");
			audit.setAudit_table("BAML_STR_TABLE");
			audit.setAudit_screen("STR REPORT MODIFIED");
			audit.setEvent_id(reportid);
			audit.setEvent_name("STR REPORT");
			audit.setModi_details("REPORT MODIFIED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);

			msg = "STR RECORD EDITED SUCCESSFULLY : RECORD_ID : " + up.getReport_id();
		}
		return msg;
	}

	public AML_STR_ENTITY getSrlNo(String srlno) {

		if (str_rep.existsById(srlno)) {
			AML_STR_ENTITY up = str_rep.findById(srlno).get();
			System.out.println("inside the edit vijay1");
			return up;
		} else {
			return new AML_STR_ENTITY();
		}

	};

	public String getcustAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where cif_id =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return Number;

	};

	public String getMobAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where PREFERREDPHONE =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return Number;

	};

	public int getMobAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where PREFERREDPHONE =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return cust_len;

	};

	public String getNIDAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where NAT_ID_CARD_NUM =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return Number;

	};

	public int getNIDAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where NAT_ID_CARD_NUM =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return cust_len;

	};

	public int getcustAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where cif_id =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return cust_len;

	};

	public Date getTranDet(Date tran_date) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs.createNativeQuery("SELECT tran_date from BAML_STR_TABLE where tran_date =?1")
				.setParameter(1, tran_date).getSingleResult();

		return tran_date;

	};

	public String getTranDet(String tran_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs.createNativeQuery("SELECT tran_id from BAML_STR_TABLE where tran_id =?1")
				.setParameter(1, tran_id).getSingleResult();

		return tran_id;

	};

	public String getTranDetNo(String part_tran_srl_no) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT part_tran_srl_no from BAML_STR_TABLE where part_tran_srl_no =?1")
				.setParameter(1, part_tran_srl_no).getSingleResult();

		return part_tran_srl_no;

	};

	public String getMAILAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where PREFERREDEMAIL =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return Number;

	};

	public int getMAILAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		String Number = (String) hs
				.createNativeQuery("SELECT cust_id from BAML_CUST_MAST_TABLE where PREFERREDEMAIL =?1")
				.setParameter(1, cust_id).getSingleResult();

		int cust_len = Number.length();
		return cust_len;

	};

	public File getFile(String reportId) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String msg;
		String path = "";
		String fileName = "";

		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + "MCS";

		try {
			logger.info("Getting Jasper file :" + reportId);
			InputStream fileStream = null;
			HashMap<String, Object> map = new HashMap<String, Object>();

			fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/STR_MAIN.jasper");
			/*
			 * fileStream = this.getClass().getResourceAsStream(
			 * "/static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			 */

			InputStream subrep1 = this.getClass().getResourceAsStream("/static/jasper/STR/STR_1.jasper");
			InputStream subrep2 = this.getClass().getResourceAsStream("/static/jasper/STR/STR_2.jasper");
			InputStream subrep3 = this.getClass().getResourceAsStream("/static/jasper/STR/STR_3.jasper");

			/*
			 * File tempFile = File.createTempFile( "myfile", ".xls" );
			 * FileUtils.copyToFile( fileStream, tempFile );
			 */

			map.put("DIR_FILE1", subrep1);
			map.put("DIR_FILE2", subrep2);
			map.put("DIR_FILE3", subrep3);
			System.out.println("reportid -> " + reportId);
			map.put("REPORT_ID", reportId);

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);

			logger.info("Assigning Parameters for Jasper");

			fileName = fileName + ".pdf";
			path = fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported");

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		System.out.println(outputFile);

		msg = "PDF GENERATED SUCCESFULLY";

		return outputFile;

	}

	public String getSrlNoValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("000");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT STRSEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "STR" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

	public File getFile1(String str_ref_no) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		String msg = "";
		File outputFile;

		logger.info("Getting Output file :" + str_ref_no);

		fileName = str_ref_no + "_" + "MCS";

		zipFileName = fileName + ".zip";

		try {
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + str_ref_no);

			System.out.println("pdf");
			fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR_INTERNAL.jasper");
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			HashMap<String, Object> map = new HashMap<String, Object>();

			/*
			 * try { logger.info("Getting Jasper file :" + str_ref_no); //File jasperFile =
			 * null; HashMap<String, Object> map = new HashMap<String, Object>();
			 * 
			 * 
			 * 
			 * 
			 * //File jasperFile =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_MAIN.jasper"); //File
			 * subrep1 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/BAML_STR_INTERNAL.jasper")
			 * ; //File subrep2 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_2.jasper"); //File
			 * subrep3 = ResourceUtils.getFile("classpath:static/jasper/STR/STR_3.jasper");
			 * 
			 * // map.put("DIR_1", subrep1); //map.put("DIR_2", subrep2); //map.put("DIR_3",
			 * subrep3);
			 * 
			 */

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);

			logger.info("Assigning Parameters for Jasper");
			map.put("STR_REF_NO", str_ref_no);

			/*
			 * File folders = new File(path); if (!folders.exists()) { folders.mkdirs(); }
			 */

			fileName = fileName + ".pdf";
			path = fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported");

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		System.out.println(outputFile);

		msg = "Pdf Generated Sucessfullly";

		return outputFile;
	}

	public File getFile2(String str_ref_no) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		String msg = "";
		File outputFile;

		logger.info("Getting Output file :" + str_ref_no);

		fileName = str_ref_no + "_" + "MCS";

		zipFileName = fileName + ".zip";

		try {
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + str_ref_no);

			System.out.println("pdf");
			fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR1.jasper");
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			HashMap<String, Object> map = new HashMap<String, Object>();

			/*
			 * try { logger.info("Getting Jasper file :" + str_ref_no); //File jasperFile =
			 * null; HashMap<String, Object> map = new HashMap<String, Object>();
			 * 
			 * 
			 * 
			 * 
			 * //File jasperFile =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_MAIN.jasper"); //File
			 * subrep1 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/BAML_STR_INTERNAL.jasper")
			 * ; //File subrep2 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_2.jasper"); //File
			 * subrep3 = ResourceUtils.getFile("classpath:static/jasper/STR/STR_3.jasper");
			 * 
			 * // map.put("DIR_1", subrep1); //map.put("DIR_2", subrep2); //map.put("DIR_3",
			 * subrep3);
			 * 
			 */

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);

			logger.info("Assigning Parameters for Jasper");
			map.put("STR_REF_NO", str_ref_no);

			/*
			 * File folders = new File(path); if (!folders.exists()) { folders.mkdirs(); }
			 */

			fileName = fileName + ".pdf";
			path = fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported");

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		System.out.println(outputFile);

		msg = "Pdf Generated Sucessfullly";

		return outputFile;
	}

	public String addReport(BAML_STR bAML_STR, BAML_STR_Internal bAML_STR_Internal) {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";

		BAML_STR up = bAML_STR;
		BAML_STR_Internal up1 = bAML_STR_Internal;
		up.setEntity_flg("Y");
		up.setDel_flg("N");
		up1.setEntity_flg("Y");
		up1.setDel_flg("N");
		hs.saveOrUpdate(up);
		hs.saveOrUpdate(up1);
		msg = "STR Added Sucessfully";

		return msg;
	}

	public List<BAMLSearchFilter> getBAMLSearchFilter(String tran_id, String date_tran, String part_tran_srl_num)
			throws ParseException {

		List<BAMLSearchFilter> bamlSearchList = new ArrayList<>();
		List<Object[]> tranSearchList = TRANMaster.getSTRTranDet(tran_id, date_tran, part_tran_srl_num);
		System.out.println(tranSearchList = TRANMaster.getSTRTranDet(tran_id, date_tran, part_tran_srl_num));
		Date dt1;
		for (Object[] obj : tranSearchList) {
			BAMLSearchFilter bamlSearch = new BAMLSearchFilter();
			bamlSearch.setCust_id((String) obj[0]);
			bamlSearch.setTran_date((String) obj[1]);

			bamlSearch.setTran_id((String) obj[2]);
			bamlSearch.setPart_tran_srl_num((String) obj[3]);
			bamlSearch.setTran_type((Character) obj[4]);
			bamlSearch.setTran_sub_type((String) obj[5]);
			bamlSearch.setPart_tran_type((Character) obj[6]);
			bamlSearch.setTran_crncy_code((String) obj[7]);
			bamlSearch.setTran_amt((BigDecimal) obj[8]);
			bamlSearch.setTran_particular((String) obj[9]);
			bamlSearch.setFx_tran_amt((BigDecimal) obj[10]);

			bamlSearch.setPstd_date((String) obj[11]);
			// System.out.println("rate Code"+(String)obj[12]);
			bamlSearch.setRate_Code((String) obj[12]);
			bamlSearch.setForacid((String) obj[13]);
			bamlSearch.setAcct_name((String) obj[14]);
			bamlSearch.setAcct_opn_date((String) obj[15]);
			bamlSearch.setAcct_cls_date((String) obj[16]);
			bamlSearch.setCntry_code((String) obj[17]);
			bamlSearch.setCust_sector_code((String) obj[18]);
			bamlSearch.setAddress1((String) obj[19]);
			bamlSearch.setAddress2((String) obj[20]);
			bamlSearch.setCity_code((String) obj[21]);
			bamlSearch.setPreferredphone((String) obj[22]);
			bamlSearch.setCust_sex((String) obj[23]);
			bamlSearch.setCust_first_name((String) obj[24]);
			bamlSearch.setCust_last_name((String) obj[25]);
			bamlSearch.setNationality((String) obj[26]);
			bamlSearch.setCust_dob((String) obj[27]);

			bamlSearchList.add(bamlSearch);

		}
		return bamlSearchList;
	}

	public List<BAMLSearchIntFilter> getBAMLSearchFilterInt(String tran_id, String date_tran, String part_tran_srl_num)
			throws ParseException {

		List<BAMLSearchIntFilter> bamlSearchList = new ArrayList<>();
		List<Object[]> tranSearchList = TRANMaster.getBAMLSearchIntFilter(tran_id, date_tran, part_tran_srl_num);
		System.out.println(tranSearchList = TRANMaster.getSTRTranDet(tran_id, date_tran, part_tran_srl_num));
		Date dt1;
		for (Object[] obj : tranSearchList) {
			BAMLSearchIntFilter bamlSearch = new BAMLSearchIntFilter();
			bamlSearch.setCif_id((String) obj[0]);

			bamlSearch.setTran_date((String) obj[1]);
			bamlSearch.setTran_id((String) obj[2]);
			bamlSearch.setPart_tran_srl_num((String) obj[3]);
			bamlSearch.setTran_type((Character) obj[4]);
			bamlSearch.setTran_sub_type((String) obj[5]);
			bamlSearch.setPart_tran_type((Character) obj[6]);
			bamlSearch.setTran_amt((BigDecimal) obj[7]);
			bamlSearch.setAddress1((String) obj[8]);
			bamlSearch.setAddress2((String) obj[9]);
			bamlSearch.setNat_id_card_num((String) obj[10]);
			bamlSearch.setPreferredphone((String) obj[11]);
			bamlSearch.setCust_name((String) obj[12]);
			bamlSearch.setOccupation((String) obj[13]);
			bamlSearch.setPlaceofbirth((String) obj[14]);

			bamlSearchList.add(bamlSearch);

		}
		return bamlSearchList;
	}

	public List<BAMLThirdPartySearchFilter> getBAMLThirdSearchFilter(String mode,String cust_id, String tran_date
			) throws ParseException {

		List<BAMLThirdPartySearchFilter> bamlSearchList = new ArrayList<>();
		if(mode.equals("Current")) {
			List<Object[]> tranSearchList = TRANMaster.get3rdPartyTranDetCurrent(cust_id, tran_date);
			if(tranSearchList.isEmpty()) {
				 tranSearchList = TRANMaster.get3rdPartyTranDetCurrentREFNUM(cust_id, tran_date);
			}else {
				System.out.println("NO");
			}
			System.out.println(tranSearchList+"add");
			//System.out.println(tranSearchList = TRANMaster.get3rdPartyTranDetCurrent(cust_id, tran_date));
			Date dt1;
			for (Object[] obj : tranSearchList) {
				BAMLThirdPartySearchFilter bamlSearch = new BAMLThirdPartySearchFilter();
				bamlSearch.setCust_id((String) obj[0]);
				bamlSearch.setTran_date((String) obj[1]);
				bamlSearch.setTran_id((String) obj[2]);
				bamlSearch.setPart_tran_srl_num((String) obj[3]);
				bamlSearch.setTran_type((Character) obj[4]);
				bamlSearch.setTran_sub_type((String) obj[5]);
				bamlSearch.setPart_tran_type((Character) obj[6]);
				bamlSearch.setTran_crncy_code((String) obj[7]);
				bamlSearch.setTran_amt((BigDecimal) obj[8]);
				bamlSearch.setTran_particular((String) obj[9]);
				bamlSearch.setFx_tran_amt((BigDecimal) obj[10]);
				bamlSearch.setPstd_date((String) obj[11]);
				bamlSearch.setForacid((String) obj[12]);
				bamlSearch.setAcct_name((String) obj[13]);
				bamlSearch.setCntry_code((String) obj[14]);
				bamlSearch.setCif_id((String) obj[15]);
				bamlSearch.setNat_id_card_num((String) obj[16]);
				bamlSearch.setLocaletext((String) obj[17]);
				bamlSearch.setCity_code((String) obj[18]);
				bamlSearch.setPreferredphone((String) obj[19]);
				bamlSearch.setSex((String) obj[20]);
				bamlSearch.setCust_name((String) obj[21]);
				bamlSearch.setCust_first_name((String) obj[22]);
				bamlSearch.setCust_last_name((String) obj[23]);
				bamlSearch.setNationality((String) obj[24]);
				bamlSearch.setCust_dob((String) obj[25]);
				bamlSearch.setDepartment((String) obj[26]);
				bamlSearch.setOccupation((String) obj[27]);
				bamlSearch.setCust_short_name((String) obj[28]);
				bamlSearch.setTran_particular_code((String) obj[29]);
			


				bamlSearchList.add(bamlSearch);

			}
		}else if(mode.equals("History")) {
			List<Object[]> tranSearchList = TRANMaster.get3rdPartyTranDetHistory(cust_id, tran_date);
		//	System.out.println(tranSearchList = TRANMaster.get3rdPartyTranDetHistory(cust_id, tran_date));
			System.out.println(tranSearchList+"add");
			if(tranSearchList.isEmpty()) {
				tranSearchList = TRANMaster.get3rdPartyTranDetHistoryREFNUM(cust_id, tran_date);
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
			Date dt1;
			for (Object[] obj : tranSearchList) {
				BAMLThirdPartySearchFilter bamlSearch = new BAMLThirdPartySearchFilter();
				bamlSearch.setCust_id((String) obj[0]);
				bamlSearch.setTran_date((String) obj[1]);
				bamlSearch.setTran_id((String) obj[2]);
				bamlSearch.setPart_tran_srl_num((String) obj[3]);
				bamlSearch.setTran_type((Character) obj[4]);
				bamlSearch.setTran_sub_type((String) obj[5]);
				bamlSearch.setPart_tran_type((Character) obj[6]);
				bamlSearch.setTran_crncy_code((String) obj[7]);
				bamlSearch.setTran_amt((BigDecimal) obj[8]);
				bamlSearch.setTran_particular((String) obj[9]);
				bamlSearch.setFx_tran_amt((BigDecimal) obj[10]);
				bamlSearch.setPstd_date((String) obj[11]);
				bamlSearch.setForacid((String) obj[12]);
				bamlSearch.setAcct_name((String) obj[13]);
				bamlSearch.setCntry_code((String) obj[14]);
				bamlSearch.setCif_id((String) obj[15]);
				bamlSearch.setNat_id_card_num((String) obj[16]);
				bamlSearch.setLocaletext((String) obj[17]);
				bamlSearch.setCity_code((String) obj[18]);
				bamlSearch.setPreferredphone((String) obj[19]);
				bamlSearch.setSex((String) obj[20]);
				bamlSearch.setCust_name((String) obj[21]);
				bamlSearch.setCust_first_name((String) obj[22]);
				bamlSearch.setCust_last_name((String) obj[23]);
				bamlSearch.setNationality((String) obj[24]);
				bamlSearch.setCust_dob((String) obj[25]);
				bamlSearch.setDepartment((String) obj[26]);
				bamlSearch.setOccupation((String) obj[27]);
				bamlSearch.setCust_short_name((String) obj[28]);
				bamlSearch.setTran_particular_code((String) obj[29]);


				bamlSearchList.add(bamlSearch);

			}
		}
	

		return bamlSearchList;
	}

}
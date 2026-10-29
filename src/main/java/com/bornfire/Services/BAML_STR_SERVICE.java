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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.env.Environment;

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

	@Autowired
	private Environment env;

	private String pstd_date;

	public String addPARAMETER(AML_STR_ENTITY alertparam, String Formmode, String Userid) {
		Session hs = sessionFactory.getCurrentSession();

		String msg = "";
		if ("add".equalsIgnoreCase(Formmode)) {
			AML_STR_ENTITY up = alertparam;

			String auditRef = "1";
			try {
				BigDecimal Number1 = (BigDecimal) hs
						.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
						.getSingleResult();
				if (Number1 != null) {
					auditRef = Number1.toString();
				}
			} catch (Exception e) {
				auditRef = String.valueOf(System.currentTimeMillis());
			}

			DecimalFormat numformate = new DecimalFormat("000");
			BigDecimal billNumber = null;
			try {
				billNumber = (BigDecimal) hs
						.createNativeQuery("SELECT CAST(nextval('\"AML\".\"STR_RECORD_ID\"') AS NUMERIC)")
						.getSingleResult();
			} catch (Exception e1) {
				try {
					billNumber = (BigDecimal) hs
							.createNativeQuery("SELECT CAST(nextval('\"AML\".\"STRSEQUENCE_1\"') AS NUMERIC)")
							.getSingleResult();
				} catch (Exception e2) {
					long count = str_rep.count();
					billNumber = BigDecimal.valueOf(count + 1);
				}
			}
			String serialno = "RPT" + numformate.format(billNumber);
			logger.info("Generated STR Record ID: " + serialno);
			up.setReport_id(serialno);

			try {
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
				audit.setAudit_ref_no(auditRef);
				auditLocal.save(audit);
			} catch (Exception e) {
				logger.warn("Audit record save failed: " + e.getMessage());
			}
			str_rep.save(up);

			msg = "STR Created: RECORD_ID: " + serialno;
		}
		return msg;
	}

	public String addPARAMETER1(AML_STR_ENTITY alertparam, String Formmode, String Userid, String reportid) {
		Session hs = sessionFactory.getCurrentSession();

		String msg = "";
		if ("edit".equalsIgnoreCase(Formmode)) {
			AML_STR_ENTITY up = alertparam;
			up.setReport_id(reportid);
			str_rep.save(up);

			String auditRef = "1";
			try {
				BigDecimal Number1 = (BigDecimal) hs
						.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
						.getSingleResult();
				if (Number1 != null) {
					auditRef = Number1.toString();
				}
			} catch (Exception e) {
				auditRef = String.valueOf(System.currentTimeMillis());
			}

			try {
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
				audit.setAudit_ref_no(auditRef);
				auditLocal.save(audit);
			} catch (Exception e) {
				logger.warn("Audit record save failed: " + e.getMessage());
			}

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
		List<?> list = hs.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\" = ?1 or \"CUST_ID\" = ?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id;
		}
		return list.get(0).toString();
	};

	public String getMobAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"PREFERREDPHONE\" = ?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id;
		}
		return list.get(0).toString();
	};

	public int getMobAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"PREFERREDPHONE\" = ?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id != null ? cust_id.length() : 0;
		}
		return list.get(0).toString().length();
	};

	public String getNIDAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"NAT_ID_CARD_NUM\" = ?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id;
		}
		return list.get(0).toString();
	};

	public int getNIDAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"NAT_ID_CARD_NUM\" = ?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id != null ? cust_id.length() : 0;
		}
		return list.get(0).toString().length();
	};

	public int getcustAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"CIF_ID\" = ?1 or \"CUST_ID\" = ?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id != null ? cust_id.length() : 0;
		}
		return list.get(0).toString().length();
	};

	public Date getTranDet(Date tran_date) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs.createNativeQuery("SELECT \"TRAN_DATE\" from \"AML\".\"BAML_STR_TABLE\" where \"TRAN_DATE\" =?1 limit 1")
				.setParameter(1, tran_date).getResultList();
		return tran_date;
	}

	public String getTranDet(String tran_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs.createNativeQuery("SELECT \"TRAN_ID\" from \"AML\".\"BAML_STR_TABLE\" where \"TRAN_ID\" =?1 limit 1")
				.setParameter(1, tran_id).getResultList();
		return tran_id;
	}

	public String getTranDetNo(String part_tran_srl_no) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"PART_TRAN_SRL_NUM\" from \"AML\".\"BAML_ACCT_TRANS_TABLE\" where \"PART_TRAN_SRL_NUM\" =?1 limit 1")
				.setParameter(1, part_tran_srl_no).getResultList();
		return part_tran_srl_no;
	}

	public String getMAILAudit(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"PREFERREDEMAIL\" =?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id;
		}
		return list.get(0).toString();
	}

	public int getMAILAuditlen(String cust_id) {
		Session hs = sessionFactory.getCurrentSession();
		List<?> list = hs
				.createNativeQuery("SELECT \"CUST_ID\" from \"AML\".\"BAML_CUST_MAST_TABLE\" where \"PREFERREDEMAIL\" =?1 limit 1")
				.setParameter(1, cust_id).getResultList();
		if (list.isEmpty() || list.get(0) == null) {
			return cust_id != null ? cust_id.length() : 0;
		}
		return list.get(0).toString().length();
	}

	public File getFile(String reportId) throws FileNotFoundException, JRException, SQLException {

		String path = "";
		String fileName = reportId + "_" + "MCS";

		File outputFile = null;

		logger.info("Getting Output file :" + reportId);

		try {
			logger.info("Getting Jasper file :" + reportId);
			JasperReport jr;
			InputStream jrxmlStream = this.getClass().getResourceAsStream("/static/jasper/STR/STR_MAIN.jrxml");
			if (jrxmlStream != null) {
				try {
					jr = JasperCompileManager.compileReport(jrxmlStream);
				} catch (Exception ce) {
					logger.warn("Dynamic compilation of STR_MAIN.jrxml failed, falling back to precompiled jasper: " + ce.getMessage());
					InputStream fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/STR_MAIN.jasper");
					jr = (JasperReport) JRLoader.loadObject(fileStream);
				}
			} else {
				InputStream fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/STR_MAIN.jasper");
				jr = (JasperReport) JRLoader.loadObject(fileStream);
			}

			InputStream subrep1 = this.getClass().getResourceAsStream("/static/jasper/STR/STR_1.jasper");
			InputStream subrep2 = this.getClass().getResourceAsStream("/static/jasper/STR/STR_2.jasper");
			InputStream subrep3 = this.getClass().getResourceAsStream("/static/jasper/STR/STR_3.jasper");

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("DIR_FILE1", subrep1);
			map.put("DIR_FILE2", subrep2);
			map.put("DIR_FILE3", subrep3);
			logger.info("reportid -> " + reportId);
			map.put("REPORT_ID", reportId);

			String exportPath = env != null ? env.getProperty("output.exportpath") : null;
			if (exportPath == null || exportPath.trim().isEmpty()) {
				exportPath = System.getProperty("java.io.tmpdir");
			}
			File dir = new File(exportPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			outputFile = new File(dir, fileName + ".pdf");
			path = outputFile.getAbsolutePath();

			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported to: " + path);

		} catch (Exception e) {
			logger.error("Error in generating STR Jasper report", e);
			throw new RuntimeException("Error generating STR report: " + e.getMessage(), e);
		}

		return outputFile;
	}

	public String getSrlNoValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("000");
		BigDecimal billNumber = null;
		try {
			billNumber = (BigDecimal) hs
					.createNativeQuery("SELECT CAST(nextval('\"AML\".\"STRSEQUENCE_1\"') AS NUMERIC)")
					.getSingleResult();
		} catch (Exception e1) {
			try {
				billNumber = (BigDecimal) hs
						.createNativeQuery("SELECT CAST(nextval('\"AML\".\"STR_RECORD_ID\"') AS NUMERIC)")
						.getSingleResult();
			} catch (Exception e2) {
				long count = str_rep.count();
				billNumber = BigDecimal.valueOf(count + 1);
			}
		}
		String serialno = "STR" + numformate.format(billNumber);
		logger.info("Generated STR Ref No: " + serialno);
		return serialno;
	}

	public File getFile1(String str_ref_no) throws FileNotFoundException, JRException, SQLException {

		String path = "";
		String fileName = str_ref_no + "_" + "MCS";
		File outputFile = null;

		logger.info("Getting Output file :" + str_ref_no);

		try {
			JasperReport jr;
			InputStream jrxmlStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR_INTERNAL.jrxml");
			if (jrxmlStream != null) {
				try {
					jr = JasperCompileManager.compileReport(jrxmlStream);
				} catch (Exception ce) {
					logger.warn("Dynamic compilation of BAML_STR_INTERNAL.jrxml failed, falling back to precompiled jasper: " + ce.getMessage());
					InputStream fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR_INTERNAL.jasper");
					jr = (JasperReport) JRLoader.loadObject(fileStream);
				}
			} else {
				InputStream fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR_INTERNAL.jasper");
				jr = (JasperReport) JRLoader.loadObject(fileStream);
			}

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("STR_REF_NO", str_ref_no);

			String exportPath = env != null ? env.getProperty("output.exportpath") : null;
			if (exportPath == null || exportPath.trim().isEmpty()) {
				exportPath = System.getProperty("java.io.tmpdir");
			}
			File dir = new File(exportPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			outputFile = new File(dir, fileName + ".pdf");
			path = outputFile.getAbsolutePath();

			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported to: " + path);

		} catch (Exception e) {
			logger.error("Error in generating BAML_STR_INTERNAL report", e);
			throw new RuntimeException("Error generating BAML_STR_INTERNAL report: " + e.getMessage(), e);
		}

		return outputFile;
	}

	public File getFile2(String str_ref_no) throws FileNotFoundException, JRException, SQLException {

		String path = "";
		String fileName = str_ref_no + "_" + "MCS";
		File outputFile = null;

		logger.info("Getting Output file :" + str_ref_no);

		try {
			JasperReport jr;
			InputStream jrxmlStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR1.jrxml");
			if (jrxmlStream != null) {
				try {
					jr = JasperCompileManager.compileReport(jrxmlStream);
				} catch (Exception ce) {
					logger.warn("Dynamic compilation of BAML_STR1.jrxml failed, falling back to precompiled jasper: " + ce.getMessage());
					InputStream fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR1.jasper");
					jr = (JasperReport) JRLoader.loadObject(fileStream);
				}
			} else {
				InputStream fileStream = this.getClass().getResourceAsStream("/static/jasper/STR/BAML_STR1.jasper");
				jr = (JasperReport) JRLoader.loadObject(fileStream);
			}

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("STR_REF_NO", str_ref_no);

			String exportPath = env != null ? env.getProperty("output.exportpath") : null;
			if (exportPath == null || exportPath.trim().isEmpty()) {
				exportPath = System.getProperty("java.io.tmpdir");
			}
			File dir = new File(exportPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			outputFile = new File(dir, fileName + ".pdf");
			path = outputFile.getAbsolutePath();

			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported to: " + path);

		} catch (Exception e) {
			logger.error("Error in generating BAML_STR1 report", e);
			throw new RuntimeException("Error generating BAML_STR1 report: " + e.getMessage(), e);
		}

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
			bamlSearch.setTran_type(obj[4] != null ? obj[4].toString().charAt(0) : null);
			bamlSearch.setTran_sub_type((String) obj[5]);
			bamlSearch.setPart_tran_type(obj[6] != null ? obj[6].toString().charAt(0) : null);
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
		System.out.println("tranSearchList int count: " + (tranSearchList != null ? tranSearchList.size() : 0));
		Date dt1;
		for (Object[] obj : tranSearchList) {
			BAMLSearchIntFilter bamlSearch = new BAMLSearchIntFilter();
			bamlSearch.setCif_id((String) obj[0]);

			bamlSearch.setTran_date((String) obj[1]);
			bamlSearch.setTran_id((String) obj[2]);
			bamlSearch.setPart_tran_srl_num((String) obj[3]);
			bamlSearch.setTran_type(obj[4] != null ? obj[4].toString().charAt(0) : null);
			bamlSearch.setTran_sub_type((String) obj[5]);
			bamlSearch.setPart_tran_type(obj[6] != null ? obj[6].toString().charAt(0) : null);
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

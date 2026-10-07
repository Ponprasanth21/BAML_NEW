package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entity.t6.T6Details;
import com.bornfire.entity.t6.T6Report;

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
@Transactional
@ConfigurationProperties("output")
public class T6ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T6Report.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	Environment env;
	
	// summary starts
	public ModelAndView getT6currentView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T6urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive" + fromdate);
		qr = hs.createNativeQuery(
				"select * from T6_KYC_CDD_REVIEW_FREQ_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String d1_current_individuals_low = (String) a[0];
			String e1_current_individuals_medium = (String) a[1];
			String f1_current_individuals_high = (String) a[2];

			String d2_current_corporates_low = (String) a[3];
			String e2_current_corporates_medium = (String) a[4];
			String f2_current_corporates_high = (String) a[5];

			String d3_current_non_profit_organizations_low = (String) a[6];
			String e3_current_non_profit_organizations_medium = (String) a[7];
			String f3_current_non_profit_organizations_high = (String) a[8];

			String d4_current_trusts_other_than_npos_and_tcsps_low = (String) a[9];
			String e4_current_trusts_other_than_npos_and_tcsps_medium = (String) a[10];
			String f4_current_trusts_other_than_npos_and_tcsps_high = (String) a[11];

			String d5_current_all_others_low = (String) a[12];
			String e5_current_all_others_medium = (String) a[13];
			String f5_current_all_others_high = (String) a[14];

			String d6_current_peps_domestic_low = (String) a[15];
			String e6_current_peps_domestic_medium = (String) a[16];
			String f6_current_peps_domestic_high = (String) a[17];

			String d7_current_peps_foreign_low = (String) a[18];
			String e7_current_peps_foreign_medium = (String) a[19];
			String f7_current_peps_foreign_high = (String) a[20];

			String d8_current_trust_and_company_service_providers_tcps_low = (String) a[21];
			String e8_current_trust_and_company_service_providers_tcps_medium = (String) a[22];
			String f8_current_trust_and_company_service_providers_tcps_high = (String) a[23];
			Date report_date = (Date) a[24];
			Date report_due_date = (Date) a[25];
			Date rep_submit_date = (Date) a[26];
			Date rep_period_from = (Date) a[27];
			Date rep_period_to = (Date) a[28];
			String rep_freq = (String) a[29];
			String nil_report_flg = (String) a[30];
			String srl_no = (String) a[31];
			String entity_flg = (String) a[32];
			String modify_flg = (String) a[33];

			T6Report T6Report = new T6Report(d1_current_individuals_low, e1_current_individuals_medium,
					f1_current_individuals_high, d2_current_corporates_low, e2_current_corporates_medium,
					f2_current_corporates_high, d3_current_non_profit_organizations_low,
					e3_current_non_profit_organizations_medium, f3_current_non_profit_organizations_high,
					d4_current_trusts_other_than_npos_and_tcsps_low, e4_current_trusts_other_than_npos_and_tcsps_medium,
					f4_current_trusts_other_than_npos_and_tcsps_high, d5_current_all_others_low,
					e5_current_all_others_medium, f5_current_all_others_high, d6_current_peps_domestic_low,
					e6_current_peps_domestic_medium, f6_current_peps_domestic_high, d7_current_peps_foreign_low,
					e7_current_peps_foreign_medium, f7_current_peps_foreign_high,
					d8_current_trust_and_company_service_providers_tcps_low,
					e8_current_trust_and_company_service_providers_tcps_medium,
					f8_current_trust_and_company_service_providers_tcps_high, report_date, report_due_date,
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, srl_no, entity_flg,
					modify_flg);

			T6urrentrep.add(T6Report);

		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (T6urrentrep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, T6urrentrep.size());
		 * pagedlist = T6urrentrep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> T6urrentrepPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
		 * T6urrentrep.size());
		 */

		mv.setViewName("ReportT6");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T6urrentrep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		mv.addObject("displaymode", "summary");

		System.out.println("scv" + mv.getViewName());

		return mv;

	}

	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dT6;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT6 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs
					.createQuery("select count(*) from T6Report a where a.rep_period_from=?1 and a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT6).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T6Report a").getSingleResult();
				if (modcnt > 0) {
					msg = "success";

					/*
					 * msg = "Records Pending for Verification For the Report";
					 */ } else {
					msg = "success";
				}
			} else {
				msg = "Data Not available for the Report. Please Contact Administrator";

				// msg = "success";

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT6currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T6urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T6_KYC_CDD_REVIEW_FREQ_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String d1_current_individuals_low = (String) a[0];
			String e1_current_individuals_medium = (String) a[1];
			String f1_current_individuals_high = (String) a[2];

			String d2_current_corporates_low = (String) a[3];
			String e2_current_corporates_medium = (String) a[4];
			String f2_current_corporates_high = (String) a[5];

			String d3_current_non_profit_organizations_low = (String) a[6];
			String e3_current_non_profit_organizations_medium = (String) a[7];
			String f3_current_non_profit_organizations_high = (String) a[8];

			String d4_current_trusts_other_than_npos_and_tcsps_low = (String) a[9];
			String e4_current_trusts_other_than_npos_and_tcsps_medium = (String) a[10];
			String f4_current_trusts_other_than_npos_and_tcsps_high = (String) a[11];

			String d5_current_all_others_low = (String) a[12];
			String e5_current_all_others_medium = (String) a[13];
			String f5_current_all_others_high = (String) a[14];

			String d6_current_peps_domestic_low = (String) a[15];
			String e6_current_peps_domestic_medium = (String) a[16];
			String f6_current_peps_domestic_high = (String) a[17];

			String d7_current_peps_foreign_low = (String) a[18];
			String e7_current_peps_foreign_medium = (String) a[19];
			String f7_current_peps_foreign_high = (String) a[20];

			String d8_current_trust_and_company_service_providers_tcps_low = (String) a[21];
			String e8_current_trust_and_company_service_providers_tcps_medium = (String) a[22];
			String f8_current_trust_and_company_service_providers_tcps_high = (String) a[23];
			Date report_date = (Date) a[24];
			Date report_due_date = (Date) a[25];
			Date rep_submit_date = (Date) a[26];
			Date rep_period_from = (Date) a[27];
			Date rep_period_to = (Date) a[28];
			String rep_freq = (String) a[29];
			String nil_report_flg = (String) a[30];
			String srl_no = (String) a[31];
			String entity_flg = (String) a[32];
			String modify_flg = (String) a[33];

			T6Report T6Report = new T6Report(d1_current_individuals_low, e1_current_individuals_medium,
					f1_current_individuals_high, d2_current_corporates_low, e2_current_corporates_medium,
					f2_current_corporates_high, d3_current_non_profit_organizations_low,
					e3_current_non_profit_organizations_medium, f3_current_non_profit_organizations_high,
					d4_current_trusts_other_than_npos_and_tcsps_low, e4_current_trusts_other_than_npos_and_tcsps_medium,
					f4_current_trusts_other_than_npos_and_tcsps_high, d5_current_all_others_low,
					e5_current_all_others_medium, f5_current_all_others_high, d6_current_peps_domestic_low,
					e6_current_peps_domestic_medium, f6_current_peps_domestic_high, d7_current_peps_foreign_low,
					e7_current_peps_foreign_medium, f7_current_peps_foreign_high,
					d8_current_trust_and_company_service_providers_tcps_low,
					e8_current_trust_and_company_service_providers_tcps_medium,
					f8_current_trust_and_company_service_providers_tcps_high, report_date, report_due_date,
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, srl_no, entity_flg,
					modify_flg);

			T6urrentrep.add(T6Report);

		}
		;

		mv.setViewName("ReportT6");
		mv.addObject("reportsummary", T6urrentrep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;

	}

	public ModelAndView getT6currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T6urrentDt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T6_KYC_CDD_REVIEW_FREQ_DET_TABLE a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T6_KYC_CDD_REVIEW_FREQ_DET_TABLE a where report_date = ?1");
		}
		try {
			qr.setParameter(1, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("Getting Report Detail for : " + reportId + "," + fromdate + "," + todate + "," + currency);
		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {

			String cust_id = (String) a[0];
			String cust_name = (String) a[1];
			String cust_type = (String) a[2];
			Date kyc_date = (Date) a[3];
			Date kyc_due_date = (Date) a[4];
			String cust_rating_code = (String) a[5];
			Date cust_rating_date = (Date) a[6];
			Date cust_rating_due_date = (Date) a[7];
			Character entity_flg = (Character) a[8];
			Character del_flg = (Character) a[9];
			Character modify_flg = (Character) a[10];
			Date entry_date = (Date) a[11];
			Date modify_date = (Date) a[12];
			Date verify_date = (Date) a[13];
			String entry_user = (String) a[14];
			String modify_user = (String) a[15];
			String verify_user = (String) a[16];
			String report_code = (String) a[17];
			String report_name = (String) a[18];
			Date report_date = (Date) a[19];
			Character arch_flg = (Character) a[20];

			T6Details py = new T6Details(cust_id, cust_name, cust_type, kyc_date, kyc_due_date, cust_rating_code,
					cust_rating_date, cust_rating_due_date, entity_flg, del_flg, modify_flg, entry_date, modify_date,
					verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg);

			T6urrentDt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T6urrentDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T6urrentDt1.size());
			pagedlist = T6urrentDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T6urrentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T6urrentDt1.size());

		mv.setViewName("ReportT6 :: reportcontent");
		mv.addObject("reportdetails", T6urrentDt1Page);

		mv.addObject("singledetail", new T6Details());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	/*
	 * public File getFile(String reportId, String fromdate, String todate, String
	 * currency, String dtltype, String filetype) throws FileNotFoundException,
	 * JRException, SQLException {
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	 * 
	 * String path = exportpath; String fileName = ""; String zipFileName = ""; File
	 * outputFile;
	 * 
	 * logger.info("Getting Output file :" + reportId);
	 * 
	 * try { fileName = reportId + "_" + dateFormat.format(new
	 * SimpleDateFormat("dd-MMM-yyyy").parse(todate));
	 * 
	 * } catch (ParseException e1) {
	 * 
	 * logger.info(e1.getMessage()); e1.printStackTrace(); }
	 * 
	 * zipFileName = path + "/" + fileName + ".zip";
	 * 
	 * if (!filetype.equals("xbrl")) {
	 * 
	 * try { File jasperFile; logger.info("Getting Jasper file :" + reportId); if
	 * (filetype.equals("detailexcel")) { if (dtltype.equals("report")) { jasperFile
	 * = ResourceUtils.getFile("classpath:static/jasper/T6/T6.jasper"); } else {
	 * jasperFile = ResourceUtils.getFile("classpath:static/jasper/T6/T6.jasper"); }
	 * 
	 * } else { if (dtltype.equals("report")) { logger.info("Inside report");
	 * jasperFile = ResourceUtils.getFile("classpath:static/jasper/T6/T6.jasper"); }
	 * else { logger.info("Inside archive"); jasperFile =
	 * ResourceUtils.getFile("classpath:static/jasper/T6/T6.jasper"); } }
	 * 
	 * JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
	 * HashMap<String, Object> map = new HashMap<String, Object>();
	 * 
	 * logger.info("Assigning Parameters for Jasper"); map.put("REPORT_DATE",
	 * todate);
	 * 
	 * File folders = new File(path); if (!folders.exists()) { folders.mkdirs(); }
	 * 
	 * if (filetype.equals("pdf")) { fileName = fileName + ".pdf"; path = path + "/"
	 * + fileName; JasperPrint jp = JasperFillManager.fillReport(jr, map,
	 * srcdataSource.getConnection()); JasperExportManager.exportReportToPdfFile(jp,
	 * path); logger.info("PDF File exported"); } else { fileName = fileName +
	 * ".xlsx"; path = path + "/" + fileName; JasperPrint jp =
	 * JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	 * JRXlsxExporter exporter = new JRXlsxExporter(); exporter.setExporterInput(new
	 * SimpleExporterInput(jp)); exporter.setExporterOutput(new
	 * SimpleOutputStreamExporterOutput(path)); exporter.exportReport();
	 * logger.info("Excel File exported"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } outputFile = new File(path);
	 * 
	 * return outputFile;
	 * 
	 * }
	 */

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		logger.info("Getting Output file :" + reportId + "today=" + todate);

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(strDate1));

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName = fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T6/T6.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T6/T6.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T6/T6.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T6/T6.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("REPORT_DATE", todate);

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

		}
		outputFile = new File(path);

		return outputFile;

	}

}
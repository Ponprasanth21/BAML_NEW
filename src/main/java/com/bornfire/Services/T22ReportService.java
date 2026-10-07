package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
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

import com.bornfire.entity.t22.T22Details;
import com.bornfire.entity.t22.T22Reports;

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
public class T22ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T22Reports.class);

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
	public ModelAndView getT22currentView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T22urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive" + fromdate);
		qr = hs.createNativeQuery(
				"select * from T22_ABAND_FUND_ACCTS_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");

		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		System.out.println("test" + result);
		for (Object[] a : result) {

			String d1a_aband_fund_accts = (String) a[0];
			String d2a_aband_fund_accst_bom = (String) a[1];
			String d3a_unclaimed_draft = (String) a[2];
			String d4a_unclaimed_drafts_bom = (String) a[3];
			String c1b_aband_fund_accts_num = (String) a[4];
			String c2b_aband_fund_accts_bom_num = (String) a[5];
			String c3b_unclaimed_drafts_num = (String) a[6];
			String c4b_unclaimed_drats_bom_num = (String) a[7];
			String c1c_aband_fund_accts_amt = (String) a[8];
			String c2c_aband_fund_accts_bom_amt = (String) a[9];
			String c3c_unclaimed_drafts_amt = (String) a[10];
			String c4c_unclaimed_drats_bom_amt = (String) a[11];
			String report_code = (String) a[12];
			String report_name = (String) a[13];
			Date report_date = (Date) a[14];
			Date report_due_date = (Date) a[15];
			Date rep_submit_date = (Date) a[16];
			Date rep_period_from = (Date) a[17];
			Date rep_period_to = (Date) a[18];
			String rep_freq = (String) a[19];
			Character nil_report_flg = (Character) a[20];
			Character arch_flg = (Character) a[21];


			T22Reports T22Report = new T22Reports(d1a_aband_fund_accts, d2a_aband_fund_accst_bom, d3a_unclaimed_draft, d4a_unclaimed_drafts_bom, c1b_aband_fund_accts_num, c2b_aband_fund_accts_bom_num, c3b_unclaimed_drafts_num, c4b_unclaimed_drats_bom_num, c1c_aband_fund_accts_amt, c2c_aband_fund_accts_bom_amt, c3c_unclaimed_drafts_amt, c4c_unclaimed_drats_bom_amt, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);

			T22urrentrep.add(T22Report);

		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (T22urrentrep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, T22urrentrep.size());
		 * pagedlist = T22urrentrep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> T22urrentrepPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
		 * T22urrentrep.size());
		 */

		mv.setViewName("ReportT22");
		//mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T22urrentrep);
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
		Date dT22;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT22 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs
					.createQuery("select count(*) from T22Reports a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT22).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T22Reports a").getSingleResult();
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

	public ModelAndView getT22currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T22urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T22_ABAND_FUND_ACCTS_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		System.out.println("test" + result);
		for (Object[] a : result) {

			String d1a_aband_fund_accts = (String) a[0];
			String d2a_aband_fund_accst_bom = (String) a[1];
			String d3a_unclaimed_draft = (String) a[2];
			String d4a_unclaimed_drafts_bom = (String) a[3];
			String c1b_aband_fund_accts_num = (String) a[4];
			String c2b_aband_fund_accts_bom_num = (String) a[5];
			String c3b_unclaimed_drafts_num = (String) a[6];
			String c4b_unclaimed_drats_bom_num = (String) a[7];
			String c1c_aband_fund_accts_amt = (String) a[8];
			String c2c_aband_fund_accts_bom_amt = (String) a[9];
			String c3c_unclaimed_drafts_amt = (String) a[10];
			String c4c_unclaimed_drats_bom_amt = (String) a[11];
			String report_code = (String) a[12];
			String report_name = (String) a[13];
			Date report_date = (Date) a[14];
			Date report_due_date = (Date) a[15];
			Date rep_submit_date = (Date) a[16];
			Date rep_period_from = (Date) a[17];
			Date rep_period_to = (Date) a[18];
			String rep_freq = (String) a[19];
			Character nil_report_flg = (Character) a[20];
			Character arch_flg = (Character) a[21];


			T22Reports T22Report = new T22Reports(d1a_aband_fund_accts, d2a_aband_fund_accst_bom, d3a_unclaimed_draft, d4a_unclaimed_drafts_bom, c1b_aband_fund_accts_num, c2b_aband_fund_accts_bom_num, c3b_unclaimed_drafts_num, c4b_unclaimed_drats_bom_num, c1c_aband_fund_accts_amt, c2c_aband_fund_accts_bom_amt, c3c_unclaimed_drafts_amt, c4c_unclaimed_drats_bom_amt, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);

			T22urrentrep.add(T22Report);

		}
		;

		mv.setViewName("ReportT22");
		mv.addObject("reportsummary", T22urrentrep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		return mv;

	}

	public ModelAndView getT22currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable,String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T22urrentDt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			if(!filter.equals("null")) {
				logger.info("Conver");
			qr = hs.createNativeQuery("select * from T22_ABAND_FUND_ACCTS_DETAILS a where REPORT_DATE = ?1 and cell_mapping =?2");
			qr.setParameter(2,filter);
		} else {
			qr = hs.createNativeQuery("select * from T22_ABAND_FUND_ACCTS_DETAILS a where REPORT_DATE = ?1");
		} 
		}else {
			qr = hs.createNativeQuery("select * from T22_ABAND_FUND_ACCTS_DETAILS a where REPORT_DATE = ?1");
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
			String acct_no = (String) a[2];
			String acct_name = (String) a[3];
			Date date_of_open = (Date) a[4];
			Date date_of_aband = (Date) a[5];
			String acct_crncy = (String) a[6];
			BigDecimal acct_bal = (BigDecimal) a[7];
			Date date_of_claim_bom = (Date) a[8];
			String remarks = (String) a[9];
			Character qtr_flg = (Character) a[10];
			Character entity_flg = (Character) a[11];
			Character del_flg = (Character) a[12];
			Character modify_flg = (Character) a[13];
			Date entry_date = (Date) a[14];
			Date modify_date = (Date) a[15];
			Date verify_date = (Date) a[16];
			String entry_user = (String) a[17];
			String modify_user = (String) a[18];
			String verify_user = (String) a[19];
			String report_code = (String) a[20];
			String report_name = (String) a[21];
			Date report_date = (Date) a[22];
			Character arch_flg = (Character) a[23];
			
			String cell_mapping = (String) a[24];
			String process_owner = (String) a[25];
			String bank_id = (String) a[26];
			String cust_type = (String) a[27];
			String cust_rating = (String) a[28];
			Date cust_rating_date = (Date) a[29];
			String ownership_type = (String) a[30];
			String tran_channel = (String) a[31];


			T22Details py = new T22Details(cust_id, cust_name, acct_no, acct_name, date_of_open, date_of_aband,
					acct_crncy, acct_bal, date_of_claim_bom, remarks, qtr_flg, entity_flg, del_flg, modify_flg,
					entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code,
					report_name, report_date, arch_flg, cell_mapping,
					 process_owner,  bank_id,  cust_type,  cust_rating, cust_rating_date,
					 ownership_type,  tran_channel);

			T22urrentDt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T22urrentDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T22urrentDt1.size());
			pagedlist = T22urrentDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T22urrentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T22urrentDt1.size());

		mv.setViewName("ReportT22 :: reportcontent");
		mv.addObject("reportdetails", T22urrentDt1Page);

		mv.addObject("singledetail", new T22Details());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date ConDate = dateFormat1.parse(todate);
	System.out.println(ConDate);
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName = reportId + "_" + strDate1;
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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/Details/T22Detail/T22Detail.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T22/T22.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T22/T22.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T22/T22.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				try {
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
					 Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
			String strDate1 = formatter1.format(ConDate);
					
					String today = dateFormat.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
					map.put("REPORT_DATE", strDate1);
				} catch (ParseException e1) {

					logger.info(e1.getMessage());
					e1.printStackTrace();
				}

				 

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path +=   fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					fileName = fileName + ".xlsx";
					path +=  fileName;
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
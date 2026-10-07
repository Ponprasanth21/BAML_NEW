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

import com.bornfire.entity.t21.T21Detail;
import com.bornfire.entity.t21.T21DetailId;
import com.bornfire.entity.t21.T21Report;

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
public class T21ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T12ReportService.class);

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
	

	public ModelAndView getT21View(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		logger.info("T21ReportService -> getT21View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t21Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T21_INACTIVE_DORM_ACCTS_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String d11a_tot_dep_accts = (String) a[0];
			String d12a_inactive_dorm_accts = (String) a[1];
			String d13a_frozen_accts = (String) a[2];
			String d14a_inacive_forzen_dorm_reactivate = (String) a[3];
			BigDecimal c11b_tot_dep_accts_noa_hig = (BigDecimal) a[4];
			BigDecimal c12b_inactive_dorm_accts_noa_hig = (BigDecimal) a[5];
			BigDecimal c13b_frozen_accts_noa_hig = (BigDecimal) a[6];
			BigDecimal c14b_inacive_forzen_dorm_reactivate_noa_hig = (BigDecimal) a[7];
			BigDecimal c11c_tot_dep_accts_amt_hig = (BigDecimal) a[8];
			BigDecimal c12c_inactive_dorm_accts_amt_hig = (BigDecimal) a[9];
			BigDecimal c13c_frozen_accts_amt_hig = (BigDecimal) a[10];
			BigDecimal c14c_inacive_forzen_dorm_reactivate_amt_hig = (BigDecimal) a[11];
			BigDecimal c11d_tot_dep_accts_noa_med = (BigDecimal) a[12];
			BigDecimal c12d_inactive_dorm_accts_noa_med = (BigDecimal) a[13];
			BigDecimal c13d_frozen_accts_noa_med = (BigDecimal) a[14];
			BigDecimal c14d_inacive_forzen_dorm_reactivate_noa_med = (BigDecimal) a[15];
			BigDecimal c11e_tot_dep_accts_amt_med = (BigDecimal) a[16];
			BigDecimal c12e_inactive_dorm_accts_amt_med = (BigDecimal) a[17];
			BigDecimal c13e_frozen_accts_amt_med = (BigDecimal) a[18];
			BigDecimal c14e_inacive_forzen_dorm_reactivate_amt_med = (BigDecimal) a[19];
			BigDecimal c11f_tot_dep_accts_noa_low = (BigDecimal) a[20];
			BigDecimal c12f_inactive_dorm_accts_noa_low = (BigDecimal) a[21];
			BigDecimal c13f_frozen_accts_noa_low = (BigDecimal) a[22];
			BigDecimal c14f_inacive_forzen_dorm_reactivate_noa_low = (BigDecimal) a[23];
			BigDecimal c11g_tot_dep_accts_amt_low = (BigDecimal) a[24];
			BigDecimal c12g_inactive_dorm_accts_amt_low = (BigDecimal) a[25];
			BigDecimal c13g_frozen_accts_amt_low = (BigDecimal) a[26];
			BigDecimal c14g_inacive_forzen_dorm_reactivatg_amt_low = (BigDecimal) a[27];
			String report_code = (String) a[28];
			String report_name = (String) a[29];
			Date report_date = (Date) a[30];
			Date report_due_date = (Date) a[31];
			Date rep_submit_date = (Date) a[32];
			Date rep_period_from = (Date) a[33];
			Date rep_period_to = (Date) a[34];
			String rep_freq = (String) a[35];
			Character nil_report_flg = (Character) a[36];
			Character arch_flg = (Character) a[37];


			T21Report t21Report = new T21Report(d11a_tot_dep_accts, d12a_inactive_dorm_accts, d13a_frozen_accts, d14a_inacive_forzen_dorm_reactivate, 
					c11b_tot_dep_accts_noa_hig, c12b_inactive_dorm_accts_noa_hig, c13b_frozen_accts_noa_hig, c14b_inacive_forzen_dorm_reactivate_noa_hig,
					c11c_tot_dep_accts_amt_hig, c12c_inactive_dorm_accts_amt_hig, c13c_frozen_accts_amt_hig, c14c_inacive_forzen_dorm_reactivate_amt_hig, 
					c11d_tot_dep_accts_noa_med, c12d_inactive_dorm_accts_noa_med, c13d_frozen_accts_noa_med, c14d_inacive_forzen_dorm_reactivate_noa_med, 
					c11e_tot_dep_accts_amt_med, c12e_inactive_dorm_accts_amt_med, c13e_frozen_accts_amt_med, c14e_inacive_forzen_dorm_reactivate_amt_med, 
					c11f_tot_dep_accts_noa_low, c12f_inactive_dorm_accts_noa_low, c13f_frozen_accts_noa_low, c14f_inacive_forzen_dorm_reactivate_noa_low, 
					c11g_tot_dep_accts_amt_low, c12g_inactive_dorm_accts_amt_low, c13g_frozen_accts_amt_low, c14g_inacive_forzen_dorm_reactivatg_amt_low, 
					report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, 
					arch_flg);

			
			t21Rep.add(t21Report);
		}

		mv.setViewName("ReportT21");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t21Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	
	
	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt2;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dt2 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery(
					"select count(*) from T21Detail a where a.t21detailid.report_date=?1 ")
					.setParameter(1, dt2).getSingleResult();

			if (dtlcnt > 0) {
			
					msg = "success";

			} else {
				 msg = "Data Not available for the Report. Please Contact Administrator";


			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT21Rep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		logger.info("T21ReportService -> getT21Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t21Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T21_INACTIVE_DORM_ACCTS_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			String d11a_tot_dep_accts = (String) a[0];
			String d12a_inactive_dorm_accts = (String) a[1];
			String d13a_frozen_accts = (String) a[2];
			String d14a_inacive_forzen_dorm_reactivate = (String) a[3];
			BigDecimal c11b_tot_dep_accts_noa_hig = (BigDecimal) a[4];
			BigDecimal c12b_inactive_dorm_accts_noa_hig = (BigDecimal) a[5];
			BigDecimal c13b_frozen_accts_noa_hig = (BigDecimal) a[6];
			BigDecimal c14b_inacive_forzen_dorm_reactivate_noa_hig = (BigDecimal) a[7];
			BigDecimal c11c_tot_dep_accts_amt_hig = (BigDecimal) a[8];
			BigDecimal c12c_inactive_dorm_accts_amt_hig = (BigDecimal) a[9];
			BigDecimal c13c_frozen_accts_amt_hig = (BigDecimal) a[10];
			BigDecimal c14c_inacive_forzen_dorm_reactivate_amt_hig = (BigDecimal) a[11];
			BigDecimal c11d_tot_dep_accts_noa_med = (BigDecimal) a[12];
			BigDecimal c12d_inactive_dorm_accts_noa_med = (BigDecimal) a[13];
			BigDecimal c13d_frozen_accts_noa_med = (BigDecimal) a[14];
			BigDecimal c14d_inacive_forzen_dorm_reactivate_noa_med = (BigDecimal) a[15];
			BigDecimal c11e_tot_dep_accts_amt_med = (BigDecimal) a[16];
			BigDecimal c12e_inactive_dorm_accts_amt_med = (BigDecimal) a[17];
			BigDecimal c13e_frozen_accts_amt_med = (BigDecimal) a[18];
			BigDecimal c14e_inacive_forzen_dorm_reactivate_amt_med = (BigDecimal) a[19];
			BigDecimal c11f_tot_dep_accts_noa_low = (BigDecimal) a[20];
			BigDecimal c12f_inactive_dorm_accts_noa_low = (BigDecimal) a[21];
			BigDecimal c13f_frozen_accts_noa_low = (BigDecimal) a[22];
			BigDecimal c14f_inacive_forzen_dorm_reactivate_noa_low = (BigDecimal) a[23];
			BigDecimal c11g_tot_dep_accts_amt_low = (BigDecimal) a[24];
			BigDecimal c12g_inactive_dorm_accts_amt_low = (BigDecimal) a[25];
			BigDecimal c13g_frozen_accts_amt_low = (BigDecimal) a[26];
			BigDecimal c14g_inacive_forzen_dorm_reactivatg_amt_low = (BigDecimal) a[27];
			String report_code = (String) a[28];
			String report_name = (String) a[29];
			Date report_date = (Date) a[30];
			Date report_due_date = (Date) a[31];
			Date rep_submit_date = (Date) a[32];
			Date rep_period_from = (Date) a[33];
			Date rep_period_to = (Date) a[34];
			String rep_freq = (String) a[35];
			Character nil_report_flg = (Character) a[36];
			Character arch_flg = (Character) a[37];


			T21Report t21Report = new T21Report(d11a_tot_dep_accts, d12a_inactive_dorm_accts, d13a_frozen_accts, d14a_inacive_forzen_dorm_reactivate, 
					c11b_tot_dep_accts_noa_hig, c12b_inactive_dorm_accts_noa_hig, c13b_frozen_accts_noa_hig, c14b_inacive_forzen_dorm_reactivate_noa_hig,
					c11c_tot_dep_accts_amt_hig, c12c_inactive_dorm_accts_amt_hig, c13c_frozen_accts_amt_hig, c14c_inacive_forzen_dorm_reactivate_amt_hig, 
					c11d_tot_dep_accts_noa_med, c12d_inactive_dorm_accts_noa_med, c13d_frozen_accts_noa_med, c14d_inacive_forzen_dorm_reactivate_noa_med, 
					c11e_tot_dep_accts_amt_med, c12e_inactive_dorm_accts_amt_med, c13e_frozen_accts_amt_med, c14e_inacive_forzen_dorm_reactivate_amt_med, 
					c11f_tot_dep_accts_noa_low, c12f_inactive_dorm_accts_noa_low, c13f_frozen_accts_noa_low, c14f_inacive_forzen_dorm_reactivate_noa_low, 
					c11g_tot_dep_accts_amt_low, c12g_inactive_dorm_accts_amt_low, c13g_frozen_accts_amt_low, c14g_inacive_forzen_dorm_reactivatg_amt_low, 
					report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, 
					arch_flg);

			t21Rep.add(t21Report);
		}

		mv.setViewName("ReportT21");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t21Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}

	public ModelAndView getT21Dtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable,String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t12Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			if(!filter.equals("null")) {
				logger.info("Conver");
			qr = hs.createNativeQuery("select * from T21_INACTIVE_DORM_ACCTS_DETAILS a where REPORT_DATE = ?1 and cell_mapping =?2");
			qr.setParameter(2,filter);
		} else {
			qr = hs.createNativeQuery("select * from T21_INACTIVE_DORM_ACCTS_DETAILS a where REPORT_DATE = ?1");
		} 
		}else {
			qr = hs.createNativeQuery("select * from T21_INACTIVE_DORM_ACCTS_DETAILS a where REPORT_DATE = ?1");
		}
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {
			String cust_id = (String) a[0];
			String cust_name = (String) a[1];
			String customer_risk_rating = (String) a[2];
			Date risk_rating_date = (Date) a[3];
			String acct_no = (String) a[4];
			String acct_name = (String) a[5];
			Date date_of_open = (Date) a[6];
			String accc_status = (String) a[7];
			Date status_date = (Date) a[8];
			String acct_remarks = (String) a[9];
			String acct_crncy = (String) a[10];
			BigDecimal act_bal = (BigDecimal) a[11];
			Character qtr_flg = (Character) a[12];
			Character entity_flg = (Character) a[13];
			Character del_flg = (Character) a[14];
			Character modify_flg = (Character) a[15];
			Date entry_date = (Date) a[16];
			Date modify_date = (Date) a[17];
			Date verify_date = (Date) a[18];
			String entry_user = (String) a[19];
			String modify_user = (String) a[20];
			String verify_user = (String) a[21];
			String report_code = (String) a[22];
			String report_name = (String) a[23];
			Date report_date = (Date) a[24];
			Character arch_flg = (Character) a[25];

			T21DetailId t21detailid = new T21DetailId(cust_id, report_date);
			T21Detail t21Detail = new T21Detail(t21detailid, cust_name, customer_risk_rating, risk_rating_date, acct_no,
					acct_name, date_of_open, accc_status, status_date, acct_remarks, acct_crncy, act_bal, qtr_flg,
					entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user,
					verify_user, report_code, report_name, arch_flg);
			t12Dt1.add(t21Detail);

		}
		;

		List<Object> pagedlist;

		if (t12Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t12Dt1.size());
			pagedlist = t12Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> t12Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize), t12Dt1.size());

		mv.setViewName("ReportT21 :: reportcontent");
		mv.addObject("reportdetails", t12Dt1Page);
		mv.addObject("singledetail", new T21Detail());
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
			fileName = reportId + "_" +strDate1;
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName =  fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/Details/T21Detail/T21Detail.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T21Copy/T21.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T21Copy/T21.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T21Copy/T21.jasper");
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

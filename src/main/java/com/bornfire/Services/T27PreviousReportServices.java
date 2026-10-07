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

import com.bornfire.entity.t27.T27PDetail;
import com.bornfire.entity.t27.T27PReport;

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
public class T27PreviousReportServices {
	private static final Logger logger = LoggerFactory.getLogger(T27PreviousReportServices.class);

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


	
	
	//summary starts
	public ModelAndView getT27previousView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {
       
		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Object> T27previousrep = new ArrayList<Object>();
		Query<Object[]> qr;
		
		

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T27P_TRAN_NRE_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2 order by A_COUNTTRY  ");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String a_counttry = (String) a[0];
			BigDecimal b_nof_cust_cntry_incorp = (BigDecimal) a[1];
			BigDecimal c_nof_cust_cntry_oper = (BigDecimal) a[2];
			BigDecimal d_nof_inward_trans_low = (BigDecimal) a[3];
			BigDecimal e_value_inward_trans_low = (BigDecimal) a[4];
			BigDecimal f_nof_outward_trans_low = (BigDecimal) a[5];
			BigDecimal g_value_outward_trans_low = (BigDecimal) a[6];
			BigDecimal h_nof_inward_trans_med = (BigDecimal) a[7];
			BigDecimal i_value_inward_trans_med = (BigDecimal) a[8];
			BigDecimal j_nof_outward_trans_med = (BigDecimal) a[9];
			BigDecimal k_value_outward_trans_med = (BigDecimal) a[10];
			BigDecimal l_nof_inward_trans_high = (BigDecimal) a[11];
			BigDecimal m_value_inward_trans_high = (BigDecimal) a[12];
			BigDecimal n_nof_outward_trans_high = (BigDecimal) a[13];
			BigDecimal o_value_outward_trans_high = (BigDecimal) a[14];
			String p_report_crncy = (String) a[15];
			Date report_date = (Date) a[16];
			Date report_due_date = (Date) a[17];
			Date rep_submit_date = (Date) a[18];
			Date rep_period_from = (Date) a[19];
			Date rep_period_to = (Date) a[20];
			String rep_freq = (String) a[21];
			String nil_report_flg = (String) a[22];

	
			T27PReport T27Report = new T27PReport(a_counttry, b_nof_cust_cntry_incorp, c_nof_cust_cntry_oper, d_nof_inward_trans_low, e_value_inward_trans_low, f_nof_outward_trans_low, g_value_outward_trans_low, h_nof_inward_trans_med, i_value_inward_trans_med, j_nof_outward_trans_med, k_value_outward_trans_med, l_nof_inward_trans_high, m_value_inward_trans_high, n_nof_outward_trans_high, o_value_outward_trans_high, p_report_crncy, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg);
			T27previousrep.add(T27Report);

		}
		;

		
		  List<Object> pagedlist;
		 
		if (T27previousrep.size() < startItem) {
			pagedlist = Collections.emptyList(); 
			}
		 else {
			 int toIndex = Math.min(startItem + pageSize, T27previousrep.size());
		  pagedlist = T27previousrep.subList(startItem, toIndex);
		  }
		  logger.info("Converting to Page"); 
		  Page<Object> T27previousrepPage = new
		  PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				  T27previousrep.size());
		 

		mv.setViewName("ReportT27Previous");
		//mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T27previousrep);
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
		Date dT27;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT27 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery(
					"select count(*) from T27PReport a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT27).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T27PReport a").getSingleResult();
				if (modcnt > 0) {
					msg = "success";

					/*
					 * msg = "Records Pending for Verification For the Report";
					 */ } else {
					msg = "success";
				}
			} else {
				 msg = "Data Not available for the Report. Please Contact Administrator";

				//msg = "success";

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT27currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T27previousrep = new ArrayList<Object>();
		Query<Object[]> qr;


		

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T27P_TRAN_NRE_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String a_counttry = (String) a[0];
			BigDecimal b_nof_cust_cntry_incorp = (BigDecimal) a[1];
			BigDecimal c_nof_cust_cntry_oper = (BigDecimal) a[2];
			BigDecimal d_nof_inward_trans_low = (BigDecimal) a[3];
			BigDecimal e_value_inward_trans_low = (BigDecimal) a[4];
			BigDecimal f_nof_outward_trans_low = (BigDecimal) a[5];
			BigDecimal g_value_outward_trans_low = (BigDecimal) a[6];
			BigDecimal h_nof_inward_trans_med = (BigDecimal) a[7];
			BigDecimal i_value_inward_trans_med = (BigDecimal) a[8];
			BigDecimal j_nof_outward_trans_med = (BigDecimal) a[9];
			BigDecimal k_value_outward_trans_med = (BigDecimal) a[10];
			BigDecimal l_nof_inward_trans_high = (BigDecimal) a[11];
			BigDecimal m_value_inward_trans_high = (BigDecimal) a[12];
			BigDecimal n_nof_outward_trans_high = (BigDecimal) a[13];
			BigDecimal o_value_outward_trans_high = (BigDecimal) a[14];
			String p_report_crncy = (String) a[15];
			Date report_date = (Date) a[16];
			Date report_due_date = (Date) a[17];
			Date rep_submit_date = (Date) a[18];
			Date rep_period_from = (Date) a[19];
			Date rep_period_to = (Date) a[20];
			String rep_freq = (String) a[21];
			String nil_report_flg = (String) a[22];

	
			T27PReport T27Report = new T27PReport(a_counttry, b_nof_cust_cntry_incorp, c_nof_cust_cntry_oper, d_nof_inward_trans_low, e_value_inward_trans_low, f_nof_outward_trans_low, g_value_outward_trans_low, h_nof_inward_trans_med, i_value_inward_trans_med, j_nof_outward_trans_med, k_value_outward_trans_med, l_nof_inward_trans_high, m_value_inward_trans_high, n_nof_outward_trans_high, o_value_outward_trans_high, p_report_crncy, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg);
			T27previousrep.add(T27Report);


		}

		mv.setViewName("ReportT27Previous");
		mv.addObject("reportsummary", T27previousrep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;

	}

	public ModelAndView getT27previousDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
System.out.println("PreviousDetails");
		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T27previousDt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T27P_TRAN_NRE_DET_TABLE a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T27P_TRAN_NRE_DET_TABLE a where report_date = ?1");
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
			String cntry_res = (String) a[2];
			String cnty_incorp = (String) a[3];
			String cntry_oper = (String) a[4];
			String acct_num = (String) a[5];
			String act_name = (String) a[6];
			String risk_rate = (String) a[7];
			Date risk_date = (Date) a[8];
			String inw_outw_tran = (String) a[9];
			BigDecimal tran_amt = (BigDecimal) a[10];
			String tran_ref = (String) a[11];
			String tran_remarks = (String) a[12];
			String entity_flg = (String) a[13];
			String del_flg = (String) a[14];
			String modify_flg = (String) a[15];
			Date entry_date = (Date) a[16];
			Date modify_date = (Date) a[17];
			Date verify_date = (Date) a[18];
			String entry_user = (String) a[19];
			String modify_user = (String) a[20];
			String verify_user = (String) a[21];
			String report_code = (String) a[22];
			String report_name = (String) a[23];
			Date report_date = (Date) a[24];
			String arch_flg = (String) a[25];


			T27PDetail py = new T27PDetail(cust_id, cust_name, cntry_res, cnty_incorp, cntry_oper, acct_num, act_name, risk_rate,
					risk_date, inw_outw_tran, tran_amt, tran_ref, tran_remarks, entity_flg, del_flg, modify_flg, entry_date, modify_date,
					verify_date, entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg);

			T27previousDt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T27previousDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T27previousDt1.size());
			pagedlist = T27previousDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T27previousDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T27previousDt1.size());

		mv.setViewName("ReportT27Previous :: reportcontent");
		mv.addObject("reportdetails", T27previousDt1Page);

		mv.addObject("singledetail", new T27PDetail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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

		zipFileName =fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T27P/T27P.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T27P/T27P.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T27P/T27P.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T27P/T27P.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("REPORT_DATE", todate);

			 
				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path +=   fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					fileName = fileName + ".xlsx";
					path +=   fileName;
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

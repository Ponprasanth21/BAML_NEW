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

import com.bornfire.entity.t12.T12Detail;
import com.bornfire.entity.t17.T17Detail;
import com.bornfire.entity.t17.T17Report;

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
public class T17ReportService {

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
	
	public ModelAndView getT17View(String reportId, String fromdate, String todate) {

		logger.info("T17ReportService -> getT17View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T17_TRAN_MON_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String d1a_cash_dep_wdl = (String) a[0];
			String d2a_dom_inw_out_rem = (String) a[1];
			String d3a_chq_inw_out_tran = (String) a[2];
			String d4a_all_otr_tran = (String) a[3];
		
			String c1f_cash_dep_wdl_parl_post = (String) a[4];
			String c2f_dom_inw_out_rem_parl_post = (String) a[5];
			String c3f_chq_inw_out_tran_parl_post = (String) a[6];
			String c4f_all_otr_tran_parl_post = (String) a[7];
			String c1g_cash_dep_wdl_avg_time = (String) a[8];
			String c2g_dom_inw_out_rem_avg_time = (String) a[9];
			String c3g_chq_inw_out_tran_avg_time = (String) a[10];
			String c4g_all_otr_tran_avg_time = (String) a[11];
			String c1h_cash_dep_wdl_man_it = (String) a[12];
			String c2h_dom_inw_out_rem_man_it = (String) a[13];
			String c3h_chq_inw_out_tran_man_it = (String) a[14];
			String c4h_all_otr_tran_man_it = (String) a[15];
			String c1i_cash_dep_wdl_inh_outs = (String) a[16];
			String c2i_dom_inw_out_rem_inh_outs = (String) a[17];
			String c3i_chq_inw_out_tran_inh_outs = (String) a[18];
			String c4i_all_otr_tran_inh_outs = (String) a[19];
			String report_code = (String) a[20];
			String report_name = (String) a[21];
			Date report_date = (Date) a[22];
			Date report_due_date = (Date) a[23];
			Date rep_submit_date = (Date) a[24];
			Date rep_period_from = (Date) a[25];
			Date rep_period_to = (Date) a[26];
			String rep_freq = (String) a[27];
			String nil_report_flg = (String) a[28];
			String arch_flg = (String) a[29];
			String entity_flg = (String) a[30];
			String modify_flg = (String) a[31];
			String del_flg = (String) a[32];
			String entry_user = (String) a[33];
			String modify_user = (String) a[34];
			String verify_user = (String) a[35];
			Date entry_date = (Date) a[36];
			Date modify_date = (Date) a[37];
			Date verify_date = (Date) a[38];
			T17Report t17Report = new T17Report(d1a_cash_dep_wdl, d2a_dom_inw_out_rem, d3a_chq_inw_out_tran,
					d4a_all_otr_tran, c1f_cash_dep_wdl_parl_post,
					c2f_dom_inw_out_rem_parl_post, c3f_chq_inw_out_tran_parl_post, c4f_all_otr_tran_parl_post,
					c1g_cash_dep_wdl_avg_time, c2g_dom_inw_out_rem_avg_time, c3g_chq_inw_out_tran_avg_time,
					c4g_all_otr_tran_avg_time, c1h_cash_dep_wdl_man_it, c2h_dom_inw_out_rem_man_it,
					c3h_chq_inw_out_tran_man_it, c4h_all_otr_tran_man_it, c1i_cash_dep_wdl_inh_outs,
					c2i_dom_inw_out_rem_inh_outs, c3i_chq_inw_out_tran_inh_outs, c4i_all_otr_tran_inh_outs, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user,
					verify_user, entry_date, modify_date, verify_date);

			t17Rep.add(t17Report);
		}

		mv.setViewName("ReportT17");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t17Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	public String preCheck(String reportid, String fromdate, String todate) {
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1 = null;
		Date dT19;


			

		Query query = null;

		query = hs.createNativeQuery("select count(*) from T17_TRAN_MON_TABLE where report_date = ?1 ");
				

				try {
					query.setParameter(1, df.parse(todate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		BigDecimal count = (BigDecimal) query.getSingleResult();

		int value = count.intValue();
		if (value > 0) {
			msg = "success";
		} else {
			msg = "Data Not available for the Report. Please Contact Administrator";
		}

		return msg;

	}

	public ModelAndView getT17Rep(String reportId, String fromdate, String todate) {

		logger.info("T17ReportService -> getT17Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T17_TRAN_MON_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String d1a_cash_dep_wdl = (String) a[0];
			String d2a_dom_inw_out_rem = (String) a[1];
			String d3a_chq_inw_out_tran = (String) a[2];
			String d4a_all_otr_tran = (String) a[3];
		
			String c1f_cash_dep_wdl_parl_post = (String) a[4];
			String c2f_dom_inw_out_rem_parl_post = (String) a[5];
			String c3f_chq_inw_out_tran_parl_post = (String) a[6];
			String c4f_all_otr_tran_parl_post = (String) a[7];
			String c1g_cash_dep_wdl_avg_time = (String) a[8];
			String c2g_dom_inw_out_rem_avg_time = (String) a[9];
			String c3g_chq_inw_out_tran_avg_time = (String) a[10];
			String c4g_all_otr_tran_avg_time = (String) a[11];
			String c1h_cash_dep_wdl_man_it = (String) a[12];
			String c2h_dom_inw_out_rem_man_it = (String) a[13];
			String c3h_chq_inw_out_tran_man_it = (String) a[14];
			String c4h_all_otr_tran_man_it = (String) a[15];
			String c1i_cash_dep_wdl_inh_outs = (String) a[16];
			String c2i_dom_inw_out_rem_inh_outs = (String) a[17];
			String c3i_chq_inw_out_tran_inh_outs = (String) a[18];
			String c4i_all_otr_tran_inh_outs = (String) a[19];
			String report_code = (String) a[20];
			String report_name = (String) a[21];
			Date report_date = (Date) a[22];
			Date report_due_date = (Date) a[23];
			Date rep_submit_date = (Date) a[24];
			Date rep_period_from = (Date) a[25];
			Date rep_period_to = (Date) a[26];
			String rep_freq = (String) a[27];
			String nil_report_flg = (String) a[28];
			String arch_flg = (String) a[29];
			String entity_flg = (String) a[30];
			String modify_flg = (String) a[31];
			String del_flg = (String) a[32];
			String entry_user = (String) a[33];
			String modify_user = (String) a[34];
			String verify_user = (String) a[35];
			Date entry_date = (Date) a[36];
			Date modify_date = (Date) a[37];
			Date verify_date = (Date) a[38];
			T17Report t17Report = new T17Report(d1a_cash_dep_wdl, d2a_dom_inw_out_rem, d3a_chq_inw_out_tran,
					d4a_all_otr_tran,c1f_cash_dep_wdl_parl_post,
					c2f_dom_inw_out_rem_parl_post, c3f_chq_inw_out_tran_parl_post, c4f_all_otr_tran_parl_post,
					c1g_cash_dep_wdl_avg_time, c2g_dom_inw_out_rem_avg_time, c3g_chq_inw_out_tran_avg_time,
					c4g_all_otr_tran_avg_time, c1h_cash_dep_wdl_man_it, c2h_dom_inw_out_rem_man_it,
					c3h_chq_inw_out_tran_man_it, c4h_all_otr_tran_man_it, c1i_cash_dep_wdl_inh_outs,
					c2i_dom_inw_out_rem_inh_outs, c3i_chq_inw_out_tran_inh_outs, c4i_all_otr_tran_inh_outs, report_code,
					report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to,
					rep_freq, nil_report_flg, arch_flg, entity_flg, modify_flg, del_flg, entry_user, modify_user,
					verify_user, entry_date, modify_date, verify_date);

			t17Rep.add(t17Report);
		}

		mv.setViewName("ReportT17");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t17Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}

	public ModelAndView getT17currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t12Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T17_TRAN_MON_DETAILS a where REPORT_DATE = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T17_TRAN_MON_DETAILS a where REPORT_DATE = ?1");
		}

		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {
			String acct_no = (String) a[0];
			String acct_name = (String) a[1];
			Date tran_date = (Date) a[2];
			String tran_id = (String) a[3];
			BigDecimal part_tran_id = (BigDecimal) a[4];
			String part_tran_type = (String) a[5];
			String tran_crncy = (String) a[6];
			BigDecimal tran_amt = (BigDecimal) a[7];
			String tran_particulars = (String) a[8];
			String tran_channel = (String) a[9];
			String tran_category = (String) a[10];
			String tran_mon_code = (String) a[11];
			Date tran_mon_time = (Date) a[12];
			String qtr_flg = (String) a[13];
			Date entity_flg = (Date) a[14];
			Date del_flg = (Date) a[15];
			Date modify_flg = (Date) a[16];
			Date entry_date = (Date) a[17];
			Date modify_date = (Date) a[18];
			String verify_date = (String) a[19];
			String entry_user = (String) a[20];
			String modify_user = (String) a[21];
			String verify_user = (String) a[22];
			Date report_code = (Date) a[23];
			Date report_name = (Date) a[24];
			Date report_date = (Date) a[25];
			String arch_flg = (String) a[26];

			T17Detail t17Detail = new T17Detail(acct_no, acct_name, tran_date, tran_id, part_tran_id, part_tran_type,
					tran_crncy, tran_amt, tran_particulars, tran_channel, tran_category, tran_mon_code, tran_mon_time,
					qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user,
					modify_user, verify_user, report_code, report_name, report_date, arch_flg);

			t12Dt1.add(t17Detail);

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

		mv.setViewName("ReportT17 :: reportcontent");
		mv.addObject("reportdetails", t12Dt1Page);
		mv.addObject("singledetail", new T12Detail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}
	
	
	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {
		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		logger.info("Getting Output file :" + reportId);

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			 Date ConDate = dateFormat1.parse(todate);
	System.out.println(ConDate);
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
	String strDate1 = formatter1.format(ConDate);
			fileName = "t"+reportId + "_" + strDate1;
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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T17Copy/T17.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T17Copy/T17.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T17Copy/T17.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T17Copy/T17.jasper");
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
					map.put("REPORT_DATE", today);
				} catch (ParseException e1) {

					logger.info(e1.getMessage());
					e1.printStackTrace();
				}

				

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path +=  fileName;
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



	public String editT17(T17Report t17Report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		/* try { */
		T17Report up = t17Report;
		up.setReport_code("T17");
		up.setEntity_flg("N");
		up.setModify_flg("Y");
		up.setDel_flg("N");

		session.saveOrUpdate(up);
		msg = "Record Edited Successfully";

		return msg;
	}

	public String verifyT17(T17Report t17Report) {

		String msg = "";

		Session session = sessionFactory.getCurrentSession();

		T17Report up = t17Report;
		 if(up.getModify_user().equals(up.getVerify_user())) {
			  msg="Same User Cannot Verify !"; }else {
		up.setReport_code("T17");
		up.setEntity_flg("Y");
		up.setDel_flg("N");

		session.saveOrUpdate(up);
		msg = "Verified Successfully";
			  }
		return msg;
	}
}
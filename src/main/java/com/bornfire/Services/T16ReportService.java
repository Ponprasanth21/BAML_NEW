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

import com.bornfire.entity.T16.T16REPORT;
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
public class T16ReportService {

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

	public ModelAndView getT16View(String reportId, String fromdate, String todate) {

		logger.info("T16ReportService -> getT16View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T16_CASH_TRAN_QTR_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			String a1_desc = (String) a[0];
			BigDecimal a1_cust_low = (BigDecimal) a[1];
			BigDecimal a1_tran_low = (BigDecimal) a[2];
			BigDecimal a1_series_low = (BigDecimal) a[3];
			BigDecimal a1_total_low = (BigDecimal) a[4];
			BigDecimal a1_cust_medium = (BigDecimal) a[5];
			BigDecimal a1_tran_medium = (BigDecimal) a[6];
			BigDecimal a1_series_medium = (BigDecimal) a[7];
			BigDecimal a1_total_medium = (BigDecimal) a[8];
			BigDecimal a1_cust_high = (BigDecimal) a[9];
			BigDecimal a1_tran_high = (BigDecimal) a[10];
			BigDecimal a1_series_high = (BigDecimal) a[11];
			BigDecimal a1_total_high = (BigDecimal) a[12];
			String b1_desc = (String) a[13];
			BigDecimal b1_cust_low = (BigDecimal) a[14];
			BigDecimal b1_tran_low = (BigDecimal) a[15];
			BigDecimal b1_series_low = (BigDecimal) a[16];
			BigDecimal b1_total_low = (BigDecimal) a[17];
			BigDecimal b1_cust_medium = (BigDecimal) a[18];
			BigDecimal b1_tran_medium = (BigDecimal) a[19];
			BigDecimal b1_series_medium = (BigDecimal) a[20];
			BigDecimal b1_total_medium = (BigDecimal) a[21];
			BigDecimal b1_cust_high = (BigDecimal) a[22];
			BigDecimal b1_tran_high = (BigDecimal) a[23];
			BigDecimal b1_series_high = (BigDecimal) a[24];
			BigDecimal b1_total_high = (BigDecimal) a[25];
			String c1_desc = (String) a[26];
			BigDecimal c1_cust_low = (BigDecimal) a[27];
			BigDecimal c1_tran_low = (BigDecimal) a[28];
			BigDecimal c1_series_low = (BigDecimal) a[29];
			BigDecimal c1_total_low = (BigDecimal) a[30];
			BigDecimal c1_cust_medium = (BigDecimal) a[31];
			BigDecimal c1_tran_medium = (BigDecimal) a[32];
			BigDecimal c1_series_medium = (BigDecimal) a[33];
			BigDecimal c1_total_medium = (BigDecimal) a[34];
			BigDecimal c1_cust_high = (BigDecimal) a[35];
			BigDecimal c1_tran_high = (BigDecimal) a[36];
			BigDecimal c1_series_high = (BigDecimal) a[37];
			BigDecimal c1_total_high = (BigDecimal) a[38];
			String d1_desc = (String) a[39];
			BigDecimal d1_cust_low = (BigDecimal) a[40];
			BigDecimal d1_tran_low = (BigDecimal) a[41];
			BigDecimal d1_series_low = (BigDecimal) a[42];
			BigDecimal d1_total_low = (BigDecimal) a[43];
			BigDecimal d1_cust_medium = (BigDecimal) a[44];
			BigDecimal d1_tran_medium = (BigDecimal) a[45];
			BigDecimal d1_series_medium = (BigDecimal) a[46];
			BigDecimal d1_total_medium = (BigDecimal) a[47];
			BigDecimal d1_cust_high = (BigDecimal) a[48];
			BigDecimal d1_tran_high = (BigDecimal) a[49];
			BigDecimal d1_series_high = (BigDecimal) a[50];
			BigDecimal d1_total_high = (BigDecimal) a[51];
			String report_code = (String) a[52];
			String report_name = (String) a[53];
			Date report_date = (Date) a[54];
			Date report_due_date = (Date) a[55];
			Date rep_submit_date = (Date) a[56];
			Date rep_period_from = (Date) a[57];
			Date rep_period_to = (Date) a[58];
			String rep_freq = (String) a[59];
			String nil_report_flg = (String) a[60];
			String arch_flg = (String) a[61];
			String entry_user = (String) a[62];
			String modify_user = (String) a[63];
			String verify_user = (String) a[64];
			Date entry_time = (Date) a[65];
			Date modify_time = (Date) a[66];
			Date verify_time = (Date) a[67];
			Character entity_flg = (Character) a[68];
			Character modify_flg = (Character) a[69];
			Character del_flg = (Character) a[70];
			T16REPORT t16Report = new T16REPORT(a1_desc, a1_cust_low, a1_tran_low, a1_series_low, a1_total_low,
					a1_cust_medium, a1_tran_medium, a1_series_medium, a1_total_medium, a1_cust_high, a1_tran_high,
					a1_series_high, a1_total_high, b1_desc, b1_cust_low, b1_tran_low, b1_series_low, b1_total_low,
					b1_cust_medium, b1_tran_medium, b1_series_medium, b1_total_medium, b1_cust_high, b1_tran_high,
					b1_series_high, b1_total_high, c1_desc, c1_cust_low, c1_tran_low, c1_series_low, c1_total_low,
					c1_cust_medium, c1_tran_medium, c1_series_medium, c1_total_medium, c1_cust_high, c1_tran_high,
					c1_series_high, c1_total_high, d1_desc, d1_cust_low, d1_tran_low, d1_series_low, d1_total_low,
					d1_cust_medium, d1_tran_medium, d1_series_medium, d1_total_medium, d1_cust_high, d1_tran_high,
					d1_series_high, d1_total_high, report_code, report_name, report_date, report_due_date,
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg, entry_user,
					modify_user, verify_user, entry_time, modify_time, verify_time,entity_flg,modify_flg,del_flg);

			t17Rep.add(t16Report);
		}

		mv.setViewName("ReportT16");
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

		query = hs.createNativeQuery("select count(*) from T16_CASH_TRAN_QTR_TABLE where report_date = ?1 ");

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

	public ModelAndView getT16Rep(String reportId, String fromdate, String todate) {

		logger.info("T17ReportService -> getT17Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t17Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T16_CASH_TRAN_QTR_TABLE where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String a1_desc = (String) a[0];
			BigDecimal a1_cust_low = (BigDecimal) a[1];
			BigDecimal a1_tran_low = (BigDecimal) a[2];
			BigDecimal a1_series_low = (BigDecimal) a[3];
			BigDecimal a1_total_low = (BigDecimal) a[4];
			BigDecimal a1_cust_medium = (BigDecimal) a[5];
			BigDecimal a1_tran_medium = (BigDecimal) a[6];
			BigDecimal a1_series_medium = (BigDecimal) a[7];
			BigDecimal a1_total_medium = (BigDecimal) a[8];
			BigDecimal a1_cust_high = (BigDecimal) a[9];
			BigDecimal a1_tran_high = (BigDecimal) a[10];
			BigDecimal a1_series_high = (BigDecimal) a[11];
			BigDecimal a1_total_high = (BigDecimal) a[12];
			String b1_desc = (String) a[13];
			BigDecimal b1_cust_low = (BigDecimal) a[14];
			BigDecimal b1_tran_low = (BigDecimal) a[15];
			BigDecimal b1_series_low = (BigDecimal) a[16];
			BigDecimal b1_total_low = (BigDecimal) a[17];
			BigDecimal b1_cust_medium = (BigDecimal) a[18];
			BigDecimal b1_tran_medium = (BigDecimal) a[19];
			BigDecimal b1_series_medium = (BigDecimal) a[20];
			BigDecimal b1_total_medium = (BigDecimal) a[21];
			BigDecimal b1_cust_high = (BigDecimal) a[22];
			BigDecimal b1_tran_high = (BigDecimal) a[23];
			BigDecimal b1_series_high = (BigDecimal) a[24];
			BigDecimal b1_total_high = (BigDecimal) a[25];
			String c1_desc = (String) a[26];
			BigDecimal c1_cust_low = (BigDecimal) a[27];
			BigDecimal c1_tran_low = (BigDecimal) a[28];
			BigDecimal c1_series_low = (BigDecimal) a[29];
			BigDecimal c1_total_low = (BigDecimal) a[30];
			BigDecimal c1_cust_medium = (BigDecimal) a[31];
			BigDecimal c1_tran_medium = (BigDecimal) a[32];
			BigDecimal c1_series_medium = (BigDecimal) a[33];
			BigDecimal c1_total_medium = (BigDecimal) a[34];
			BigDecimal c1_cust_high = (BigDecimal) a[35];
			BigDecimal c1_tran_high = (BigDecimal) a[36];
			BigDecimal c1_series_high = (BigDecimal) a[37];
			BigDecimal c1_total_high = (BigDecimal) a[38];
			String d1_desc = (String) a[39];
			BigDecimal d1_cust_low = (BigDecimal) a[40];
			BigDecimal d1_tran_low = (BigDecimal) a[41];
			BigDecimal d1_series_low = (BigDecimal) a[42];
			BigDecimal d1_total_low = (BigDecimal) a[43];
			BigDecimal d1_cust_medium = (BigDecimal) a[44];
			BigDecimal d1_tran_medium = (BigDecimal) a[45];
			BigDecimal d1_series_medium = (BigDecimal) a[46];
			BigDecimal d1_total_medium = (BigDecimal) a[47];
			BigDecimal d1_cust_high = (BigDecimal) a[48];
			BigDecimal d1_tran_high = (BigDecimal) a[49];
			BigDecimal d1_series_high = (BigDecimal) a[50];
			BigDecimal d1_total_high = (BigDecimal) a[51];
			String report_code = (String) a[52];
			String report_name = (String) a[53];
			Date report_date = (Date) a[54];
			Date report_due_date = (Date) a[55];
			Date rep_submit_date = (Date) a[56];
			Date rep_period_from = (Date) a[57];
			Date rep_period_to = (Date) a[58];
			String rep_freq = (String) a[59];
			String nil_report_flg = (String) a[60];
			String arch_flg = (String) a[61];
			String entry_user = (String) a[62];
			String modify_user = (String) a[63];
			String verify_user = (String) a[64];
			Date entry_time = (Date) a[65];
			Date modify_time = (Date) a[66];
			Date verify_time = (Date) a[67];
			Character entity_flg = (Character) a[68];
			Character modify_flg = (Character) a[69];
			Character del_flg = (Character) a[70];
			T16REPORT t16Report = new T16REPORT(a1_desc, a1_cust_low, a1_tran_low, a1_series_low, a1_total_low,
					a1_cust_medium, a1_tran_medium, a1_series_medium, a1_total_medium, a1_cust_high, a1_tran_high,
					a1_series_high, a1_total_high, b1_desc, b1_cust_low, b1_tran_low, b1_series_low, b1_total_low,
					b1_cust_medium, b1_tran_medium, b1_series_medium, b1_total_medium, b1_cust_high, b1_tran_high,
					b1_series_high, b1_total_high, c1_desc, c1_cust_low, c1_tran_low, c1_series_low, c1_total_low,
					c1_cust_medium, c1_tran_medium, c1_series_medium, c1_total_medium, c1_cust_high, c1_tran_high,
					c1_series_high, c1_total_high, d1_desc, d1_cust_low, d1_tran_low, d1_series_low, d1_total_low,
					d1_cust_medium, d1_tran_medium, d1_series_medium, d1_total_medium, d1_cust_high, d1_tran_high,
					d1_series_high, d1_total_high, report_code, report_name, report_date, report_due_date,
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg, entry_user,
					modify_user, verify_user, entry_time, modify_time, verify_time,entity_flg,modify_flg,del_flg);

			t17Rep.add(t16Report);
		}

		mv.setViewName("ReportT16");
		mv.addObject("reportsummary", t17Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}

	public ModelAndView getT16currentDtl(String reportId, String fromdate, String todate, String currency,
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
			fileName = "t" + reportId + "_" + strDate1;
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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T16/T16.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T16/T16.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T16/T16.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T16/T16.jasper");
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
					path += fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {

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

	public String editT16(T16REPORT t16Report) {
		// TODO Auto-generated method stub
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		/* try { */
		T16REPORT up = t16Report;
		up.setReport_code("T17");
		 up.setEntity_flg('N');
		 up.setModify_flg('Y');
		 up.setDel_flg('N');

		session.saveOrUpdate(up);
		msg = "Record Edited Successfully";

		return msg;
	}

	public String verifyT16(T16REPORT t16Report) {

		String msg = "";

		Session session = sessionFactory.getCurrentSession();

		T16REPORT up = t16Report;
		if(up.getModify_user().equals(up.getVerify_user())) {
			msg = "Same User Cannot Verify! ";
		}else {
		up.setReport_code("T17");
		 up.setEntity_flg('Y');
		 up.setDel_flg('N');

		session.saveOrUpdate(up);
		msg = "Verified Successfully";
		}
		return msg;
	}
}
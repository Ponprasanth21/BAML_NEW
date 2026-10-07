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

import com.bornfire.entity.T2CurrentMast;
import com.bornfire.entity.t5.T5Detail;
import com.bornfire.entity.t5.T5Report;

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
public class T5ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T5ReportService.class);

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

	public ModelAndView getT2View(String reportId, String fromdate, String todate) {

		logger.info("T5ReportService -> getT2View()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t2Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		// getting the output from report table
		logger.info("getting output from report table");
		qr = hs.createNativeQuery("select * from T5_RISK_RATING_MIG_SUMARY_TABLE where T5_REPORT_TO_DATE = ?1");

		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			String t5_1_name = (String) a[0];
			BigDecimal t5_1a_total_cust_upgrade_pre_facetoface = (BigDecimal) a[1];
			BigDecimal t5_1b_total_cust_upgrade_pre_non_facetoface = (BigDecimal) a[2];
			BigDecimal t5_1c_total_cust_upgrade_cur_facetoface = (BigDecimal) a[3];
			BigDecimal t5_1d_total_cust_upgrade_cur_non_facetoface = (BigDecimal) a[4];
			String t5_2_name = (String) a[5];
			BigDecimal t5_2a_high_to_med_pre_facetoface = (BigDecimal) a[6];
			BigDecimal t5_2b_high_to_med_pre_non_facetoface = (BigDecimal) a[7];
			BigDecimal t5_2c_high_to_med_cur_facetoface = (BigDecimal) a[8];
			BigDecimal t5_2d_high_to_med_cur_non_facetoface = (BigDecimal) a[9];
			String t5_3_name = (String) a[10];
			BigDecimal t5_3a_high_to_low_pre_facetoface = (BigDecimal) a[11];
			BigDecimal t5_3b_high_to_low_pre_non_facetoface = (BigDecimal) a[12];
			BigDecimal t5_3c_high_to_low_cur_facetoface = (BigDecimal) a[13];
			BigDecimal t5_3d_high_to_low_cur_non_facetoface = (BigDecimal) a[14];
			String t5_4_name = (String) a[15];
			BigDecimal t5_4a_med_to_low_pre_facetoface = (BigDecimal) a[16];
			BigDecimal t5_4b_med_to_low_pre_non_facetoface = (BigDecimal) a[17];
			BigDecimal t5_4c_med_to_low_cur_facetoface = (BigDecimal) a[18];
			BigDecimal t5_4d_med_to_low_cur_non_facetoface = (BigDecimal) a[19];
			String t5_5_name = (String) a[20];
			BigDecimal t5_5a_total_cust_downgrade_pre_facetoface = (BigDecimal) a[21];
			BigDecimal t5_5b_total_cust_downgrade_pre_non_facetoface = (BigDecimal) a[22];
			BigDecimal t5_5c_total_cust_downgrade_cur_facetoface = (BigDecimal) a[23];
			BigDecimal t5_5d_total_cust_downgrade_cur_non_facetoface = (BigDecimal) a[24];
			String t5_6_name = (String) a[25];
			BigDecimal t5_6a_low_to_med_pre_facetoface = (BigDecimal) a[26];
			BigDecimal t5_6b_low_to_med_pre_non_facetoface = (BigDecimal) a[27];
			BigDecimal t5_6c_low_to_med_cur_facetoface = (BigDecimal) a[28];
			BigDecimal t5_6d_low_to_med_cur_non_facetoface = (BigDecimal) a[29];
			String t5_7_name = (String) a[30];
			BigDecimal t5_7a_low_to_high_pre_facetoface = (BigDecimal) a[31];
			BigDecimal t5_7b_low_to_high_pre_non_facetoface = (BigDecimal) a[32];
			BigDecimal t5_7c_low_to_high_cur_facetoface = (BigDecimal) a[33];
			BigDecimal t5_7d_low_to_high_cur_non_facetoface = (BigDecimal) a[34];
			String t5_8_name = (String) a[35];
			BigDecimal t5_8a_med_to_high_pre_facetoface = (BigDecimal) a[36];
			BigDecimal t5_8b_med_to_high_pre_non_facetoface = (BigDecimal) a[37];
			BigDecimal t5_8c_med_to_high_cur_facetoface = (BigDecimal) a[38];
			BigDecimal t5_8d_med_to_high_cur_non_facetoface = (BigDecimal) a[39];
			String t5_9_name = (String) a[40];
			BigDecimal t5_9a_total_cust_unchange_pre_facetoface = (BigDecimal) a[41];
			BigDecimal t5_9b_total_cust_unchange_pre_non_facetoface = (BigDecimal) a[42];
			BigDecimal t5_9c_total_cust_unchange_cur_facetoface = (BigDecimal) a[43];
			BigDecimal t5_9d_total_cust_unchange_cur_non_facetoface = (BigDecimal) a[44];
			String t5_10_name = (String) a[45];
			BigDecimal t5_10a_total_pre_facetoface = (BigDecimal) a[46];
			BigDecimal t5_10b_total_pre_non_facetoface = (BigDecimal) a[47];
			BigDecimal t5_10c_total_cur_facetoface = (BigDecimal) a[48];
			BigDecimal t5_10d_total_cur_non_facetoface = (BigDecimal) a[49];
			String t5_11_name = (String) a[50];
			String t5_11a_validation = (String) a[51];
			String t5_11b_validation = (String) a[52];
			String t5_11c_validation = (String) a[53];
			String t5_11d_validation = (String) a[54];
			String t5_12_name = (String) a[55];
			BigDecimal t5_12a_customer_terminated_cdd_pre_facetoface = (BigDecimal) a[56];
			BigDecimal t5_12b_customer_terminated_cdd_pre_non_facetoface = (BigDecimal) a[57];
			BigDecimal t5_12c_customer_terminated_cdd_cur_facetoface = (BigDecimal) a[58];
			BigDecimal t5_12d_customer_terminated_cdd_cur_non_facetoface = (BigDecimal) a[59];
			String t5_13_name = (String) a[60];
			BigDecimal t5_13a_customer_ceased_pre_facetoface = (BigDecimal) a[61];
			BigDecimal t5_13b_customer_ceased_pre_non_facetoface = (BigDecimal) a[62];
			BigDecimal t5_13c_customer_ceased_cur_facetoface = (BigDecimal) a[63];
			BigDecimal t5_13d_customer_ceased_cur_non_facetoface = (BigDecimal) a[64];
			Date t5_report_submit_date = (Date) a[65];
			Date t5_report_generate_date = (Date) a[66];
			Date t5_report_due_date = (Date) a[67];
			String t5_nil_report_flg = (String) a[68];
			Date t5_report_from_date = (Date) a[69];
			Date t5_report_to_date = (Date) a[70];
			String t5_frequency = (String) a[71];

			T5Report t5Report = new T5Report(t5_1_name, t5_1a_total_cust_upgrade_pre_facetoface,
					t5_1b_total_cust_upgrade_pre_non_facetoface, t5_1c_total_cust_upgrade_cur_facetoface,
					t5_1d_total_cust_upgrade_cur_non_facetoface, t5_2_name, t5_2a_high_to_med_pre_facetoface,
					t5_2b_high_to_med_pre_non_facetoface, t5_2c_high_to_med_cur_facetoface,
					t5_2d_high_to_med_cur_non_facetoface, t5_3_name, t5_3a_high_to_low_pre_facetoface,
					t5_3b_high_to_low_pre_non_facetoface, t5_3c_high_to_low_cur_facetoface,
					t5_3d_high_to_low_cur_non_facetoface, t5_4_name, t5_4a_med_to_low_pre_facetoface,
					t5_4b_med_to_low_pre_non_facetoface, t5_4c_med_to_low_cur_facetoface,
					t5_4d_med_to_low_cur_non_facetoface, t5_5_name, t5_5a_total_cust_downgrade_pre_facetoface,
					t5_5b_total_cust_downgrade_pre_non_facetoface, t5_5c_total_cust_downgrade_cur_facetoface,
					t5_5d_total_cust_downgrade_cur_non_facetoface, t5_6_name, t5_6a_low_to_med_pre_facetoface,
					t5_6b_low_to_med_pre_non_facetoface, t5_6c_low_to_med_cur_facetoface,
					t5_6d_low_to_med_cur_non_facetoface, t5_7_name, t5_7a_low_to_high_pre_facetoface,
					t5_7b_low_to_high_pre_non_facetoface, t5_7c_low_to_high_cur_facetoface,
					t5_7d_low_to_high_cur_non_facetoface, t5_8_name, t5_8a_med_to_high_pre_facetoface,
					t5_8b_med_to_high_pre_non_facetoface, t5_8c_med_to_high_cur_facetoface,
					t5_8d_med_to_high_cur_non_facetoface, t5_9_name, t5_9a_total_cust_unchange_pre_facetoface,
					t5_9b_total_cust_unchange_pre_non_facetoface, t5_9c_total_cust_unchange_cur_facetoface,
					t5_9d_total_cust_unchange_cur_non_facetoface, t5_10_name, t5_10a_total_pre_facetoface,
					t5_10b_total_pre_non_facetoface, t5_10c_total_cur_facetoface, t5_10d_total_cur_non_facetoface,
					t5_11_name, t5_11a_validation, t5_11b_validation, t5_11c_validation, t5_11d_validation, t5_12_name,
					t5_12a_customer_terminated_cdd_pre_facetoface, t5_12b_customer_terminated_cdd_pre_non_facetoface,
					t5_12c_customer_terminated_cdd_cur_facetoface, t5_12d_customer_terminated_cdd_cur_non_facetoface,
					t5_13_name, t5_13a_customer_ceased_pre_facetoface, t5_13b_customer_ceased_pre_non_facetoface,
					t5_13c_customer_ceased_cur_facetoface, t5_13d_customer_ceased_cur_non_facetoface,
					t5_report_submit_date, t5_report_generate_date, t5_report_due_date, t5_nil_report_flg,
					t5_report_from_date, t5_report_to_date, t5_frequency);

			t2Rep.add(t5Report);
		}

		mv.setViewName("ReportT5");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t2Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);

		logger.info("returning model view");
		return mv;
	}

	public String preCheck(String reportid, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1 = null;
		Date dt2;
		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Query query = null;

		query = hs.createNativeQuery("select count(*) from T5_RISK_RATING_MIG_DETAILED_TABLE where report_date = ?1 ");
		try {
			query.setParameter(1, df.parse(todate));

		} catch (ParseException e) {
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

	public ModelAndView getT2Rep(String reportId, String fromdate, String todate) {

		logger.info("T5ReportService -> getT2Rep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> t2Rep = new ArrayList<Object>();
		Query<Object[]> qr;

		// getting the output from report table
		logger.info("getting output from report table");
		qr = hs.createNativeQuery("select * from T5_RISK_RATING_MIG_SUMARY_TABLE where T5_REPORT_TO_DATE = ?1 ");
		try {
			qr.setParameter(1, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> results = qr.getResultList();
		for (Object[] a : results) {
			String t5_1_name = (String) a[0];
			BigDecimal t5_1a_total_cust_upgrade_pre_facetoface = (BigDecimal) a[1];
			BigDecimal t5_1b_total_cust_upgrade_pre_non_facetoface = (BigDecimal) a[2];
			BigDecimal t5_1c_total_cust_upgrade_cur_facetoface = (BigDecimal) a[3];
			BigDecimal t5_1d_total_cust_upgrade_cur_non_facetoface = (BigDecimal) a[4];
			String t5_2_name = (String) a[5];
			BigDecimal t5_2a_high_to_med_pre_facetoface = (BigDecimal) a[6];
			BigDecimal t5_2b_high_to_med_pre_non_facetoface = (BigDecimal) a[7];
			BigDecimal t5_2c_high_to_med_cur_facetoface = (BigDecimal) a[8];
			BigDecimal t5_2d_high_to_med_cur_non_facetoface = (BigDecimal) a[9];
			String t5_3_name = (String) a[10];
			BigDecimal t5_3a_high_to_low_pre_facetoface = (BigDecimal) a[11];
			BigDecimal t5_3b_high_to_low_pre_non_facetoface = (BigDecimal) a[12];
			BigDecimal t5_3c_high_to_low_cur_facetoface = (BigDecimal) a[13];
			BigDecimal t5_3d_high_to_low_cur_non_facetoface = (BigDecimal) a[14];
			String t5_4_name = (String) a[15];
			BigDecimal t5_4a_med_to_low_pre_facetoface = (BigDecimal) a[16];
			BigDecimal t5_4b_med_to_low_pre_non_facetoface = (BigDecimal) a[17];
			BigDecimal t5_4c_med_to_low_cur_facetoface = (BigDecimal) a[18];
			BigDecimal t5_4d_med_to_low_cur_non_facetoface = (BigDecimal) a[19];
			String t5_5_name = (String) a[20];
			BigDecimal t5_5a_total_cust_downgrade_pre_facetoface = (BigDecimal) a[21];
			BigDecimal t5_5b_total_cust_downgrade_pre_non_facetoface = (BigDecimal) a[22];
			BigDecimal t5_5c_total_cust_downgrade_cur_facetoface = (BigDecimal) a[23];
			BigDecimal t5_5d_total_cust_downgrade_cur_non_facetoface = (BigDecimal) a[24];
			String t5_6_name = (String) a[25];
			BigDecimal t5_6a_low_to_med_pre_facetoface = (BigDecimal) a[26];
			BigDecimal t5_6b_low_to_med_pre_non_facetoface = (BigDecimal) a[27];
			BigDecimal t5_6c_low_to_med_cur_facetoface = (BigDecimal) a[28];
			BigDecimal t5_6d_low_to_med_cur_non_facetoface = (BigDecimal) a[29];
			String t5_7_name = (String) a[30];
			BigDecimal t5_7a_low_to_high_pre_facetoface = (BigDecimal) a[31];
			BigDecimal t5_7b_low_to_high_pre_non_facetoface = (BigDecimal) a[32];
			BigDecimal t5_7c_low_to_high_cur_facetoface = (BigDecimal) a[33];
			BigDecimal t5_7d_low_to_high_cur_non_facetoface = (BigDecimal) a[34];
			String t5_8_name = (String) a[35];
			BigDecimal t5_8a_med_to_high_pre_facetoface = (BigDecimal) a[36];
			BigDecimal t5_8b_med_to_high_pre_non_facetoface = (BigDecimal) a[37];
			BigDecimal t5_8c_med_to_high_cur_facetoface = (BigDecimal) a[38];
			BigDecimal t5_8d_med_to_high_cur_non_facetoface = (BigDecimal) a[39];
			String t5_9_name = (String) a[40];
			BigDecimal t5_9a_total_cust_unchange_pre_facetoface = (BigDecimal) a[41];
			BigDecimal t5_9b_total_cust_unchange_pre_non_facetoface = (BigDecimal) a[42];
			BigDecimal t5_9c_total_cust_unchange_cur_facetoface = (BigDecimal) a[43];
			BigDecimal t5_9d_total_cust_unchange_cur_non_facetoface = (BigDecimal) a[44];
			String t5_10_name = (String) a[45];
			BigDecimal t5_10a_total_pre_facetoface = (BigDecimal) a[46];
			BigDecimal t5_10b_total_pre_non_facetoface = (BigDecimal) a[47];
			BigDecimal t5_10c_total_cur_facetoface = (BigDecimal) a[48];
			BigDecimal t5_10d_total_cur_non_facetoface = (BigDecimal) a[49];
			String t5_11_name = (String) a[50];
			String t5_11a_validation = (String) a[51];
			String t5_11b_validation = (String) a[52];
			String t5_11c_validation = (String) a[53];
			String t5_11d_validation = (String) a[54];
			String t5_12_name = (String) a[55];
			BigDecimal t5_12a_customer_terminated_cdd_pre_facetoface = (BigDecimal) a[56];
			BigDecimal t5_12b_customer_terminated_cdd_pre_non_facetoface = (BigDecimal) a[57];
			BigDecimal t5_12c_customer_terminated_cdd_cur_facetoface = (BigDecimal) a[58];
			BigDecimal t5_12d_customer_terminated_cdd_cur_non_facetoface = (BigDecimal) a[59];
			String t5_13_name = (String) a[60];
			BigDecimal t5_13a_customer_ceased_pre_facetoface = (BigDecimal) a[61];
			BigDecimal t5_13b_customer_ceased_pre_non_facetoface = (BigDecimal) a[62];
			BigDecimal t5_13c_customer_ceased_cur_facetoface = (BigDecimal) a[63];
			BigDecimal t5_13d_customer_ceased_cur_non_facetoface = (BigDecimal) a[64];
			Date t5_report_submit_date = (Date) a[65];
			Date t5_report_generate_date = (Date) a[66];
			Date t5_report_due_date = (Date) a[67];
			String t5_nil_report_flg = (String) a[68];
			Date t5_report_from_date = (Date) a[69];
			Date t5_report_to_date = (Date) a[70];
			String t5_frequency = (String) a[71];

			T5Report t5Report = new T5Report(t5_1_name, t5_1a_total_cust_upgrade_pre_facetoface,
					t5_1b_total_cust_upgrade_pre_non_facetoface, t5_1c_total_cust_upgrade_cur_facetoface,
					t5_1d_total_cust_upgrade_cur_non_facetoface, t5_2_name, t5_2a_high_to_med_pre_facetoface,
					t5_2b_high_to_med_pre_non_facetoface, t5_2c_high_to_med_cur_facetoface,
					t5_2d_high_to_med_cur_non_facetoface, t5_3_name, t5_3a_high_to_low_pre_facetoface,
					t5_3b_high_to_low_pre_non_facetoface, t5_3c_high_to_low_cur_facetoface,
					t5_3d_high_to_low_cur_non_facetoface, t5_4_name, t5_4a_med_to_low_pre_facetoface,
					t5_4b_med_to_low_pre_non_facetoface, t5_4c_med_to_low_cur_facetoface,
					t5_4d_med_to_low_cur_non_facetoface, t5_5_name, t5_5a_total_cust_downgrade_pre_facetoface,
					t5_5b_total_cust_downgrade_pre_non_facetoface, t5_5c_total_cust_downgrade_cur_facetoface,
					t5_5d_total_cust_downgrade_cur_non_facetoface, t5_6_name, t5_6a_low_to_med_pre_facetoface,
					t5_6b_low_to_med_pre_non_facetoface, t5_6c_low_to_med_cur_facetoface,
					t5_6d_low_to_med_cur_non_facetoface, t5_7_name, t5_7a_low_to_high_pre_facetoface,
					t5_7b_low_to_high_pre_non_facetoface, t5_7c_low_to_high_cur_facetoface,
					t5_7d_low_to_high_cur_non_facetoface, t5_8_name, t5_8a_med_to_high_pre_facetoface,
					t5_8b_med_to_high_pre_non_facetoface, t5_8c_med_to_high_cur_facetoface,
					t5_8d_med_to_high_cur_non_facetoface, t5_9_name, t5_9a_total_cust_unchange_pre_facetoface,
					t5_9b_total_cust_unchange_pre_non_facetoface, t5_9c_total_cust_unchange_cur_facetoface,
					t5_9d_total_cust_unchange_cur_non_facetoface, t5_10_name, t5_10a_total_pre_facetoface,
					t5_10b_total_pre_non_facetoface, t5_10c_total_cur_facetoface, t5_10d_total_cur_non_facetoface,
					t5_11_name, t5_11a_validation, t5_11b_validation, t5_11c_validation, t5_11d_validation, t5_12_name,
					t5_12a_customer_terminated_cdd_pre_facetoface, t5_12b_customer_terminated_cdd_pre_non_facetoface,
					t5_12c_customer_terminated_cdd_cur_facetoface, t5_12d_customer_terminated_cdd_cur_non_facetoface,
					t5_13_name, t5_13a_customer_ceased_pre_facetoface, t5_13b_customer_ceased_pre_non_facetoface,
					t5_13c_customer_ceased_cur_facetoface, t5_13d_customer_ceased_cur_non_facetoface,
					t5_report_submit_date, t5_report_generate_date, t5_report_due_date, t5_nil_report_flg,
					t5_report_from_date, t5_report_to_date, t5_frequency);

			t2Rep.add(t5Report);

		}

		mv.setViewName("ReportT5");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t2Rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	public ModelAndView getT2currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable, String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t5Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			if (!filter.equals("null")) {
				qr = hs.createNativeQuery(
						"select * from T5_RISK_RATING_MIG_DETAILED_TABLE a where REPORT_DATE = ?1 and cell_mapping=?2");
				qr.setParameter(2, filter);
			} else {
				qr = hs.createNativeQuery("select * from T5_RISK_RATING_MIG_DETAILED_TABLE a where REPORT_DATE = ?1");
			}
		} else {
			qr = hs.createNativeQuery("select * from T5_RISK_RATING_MIG_DETAILED_TABLE a where REPORT_DATE = ?1");
		}

		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {
			String cust_id = (String) a[0];
			String customer_name = (String) a[1];
			String branch_id = (String) a[2];
			String branch_name = (String) a[3];
			String bank_id = (String) a[4];
			String ownership_type = (String) a[5];
			String cust_rating = (String) a[6];
			Date customer_rating_date = (Date) a[7];
			Date customer_due_rating_date = (Date) a[8];
			String customer_type = (String) a[9];
			String report_quarter = (String) a[10];
			String remarks = (String) a[11];
			String del_flg = (String) a[12];
			String entity_cre_flg = (String) a[13];
			Date entity_cre_date = (Date) a[14];
			String mod_flg = (String) a[15];
			String entry_user = (String) a[16];
			String modify_user = (String) a[17];
			String auth_user = (String) a[18];
			Date entry_time = (Date) a[19];
			Date modify_time = (Date) a[20];
			Date auth_time = (Date) a[21];
			String aml_code_1 = (String) a[22];
			String aml_code_2 = (String) a[23];
			String aml_code_3 = (String) a[24];
			String aml_code_4 = (String) a[25];
			String aml_code_5 = (String) a[26];
			String aml_code_6 = (String) a[27];
			String aml_code_7 = (String) a[28];
			String aml_code_8 = (String) a[29];
			String aml_code_9 = (String) a[30];
			String aml_code_10 = (String) a[31];
			Date report_date = (Date) a[32];
			String cell_mapping = (String) a[33];
			String process_owner = (String) a[34];
			String qtr_flg = (String) a[35];
			Date verify_date = (Date) a[36];
			String verify_user = (String) a[37];
			String arch_flg = (String) a[38];
			String tran_channel = (String) a[39];
			String acct_num = (String) a[40];
			String act_name = (String) a[41];
			String tran_type = (String) a[42];
			String tran_sub_type = (String) a[43];
			Date tran_date = (Date) a[44];
			String tran_id = (String) a[45];
			String part_tran_id = (String) a[46];
			String part_tran_type = (String) a[47];
			String tran_crncy = (String) a[48];
			BigDecimal tran_amt = (BigDecimal) a[49];
			String tran_category = (String) a[50];
			Date relationship_date = (Date) a[51];
			String mis_face_to_face = (String) a[52];
			String mis_non_face_to_face = (String) a[53];
			String mis_internal_rating_grade = (String) a[54];
			String mis_internal_rating_scale = (String) a[55];

			T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type,
					cust_rating, customer_rating_date, customer_due_rating_date, customer_type, report_quarter, remarks,
					del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time,
					modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6,
					aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date, cell_mapping, process_owner, qtr_flg,
					verify_date, verify_user, arch_flg, tran_channel, acct_num, act_name, tran_type, tran_sub_type,
					tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_category,
					relationship_date, mis_face_to_face, mis_non_face_to_face, mis_internal_rating_grade,
					mis_internal_rating_scale);

			t5Dt1.add(t5Detail);

		}
		;

		List<Object> pagedlist;

		if (t5Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t5Dt1.size());
			pagedlist = t5Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> t5currentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				t5Dt1.size());

		mv.setViewName("ReportT5 :: reportcontent");
		mv.addObject("reportdetails", t5currentDt1Page);
		mv.addObject("singledetail", new T5Detail());
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
			fileName = reportId + "_" + dateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(strDate1));

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		zipFileName = fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile = null;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass()
								.getResourceAsStream("/static/jasper/Details/T5Detail/T5Detail.jasper");

					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T5/T5.jasper");
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

	public Page<T5Detail> parameterlistwithdecode(String rpt_date, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T5Detail> t9Dt1 = new ArrayList<T5Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T5_RISK_RATING_MIG_DETAILED_TABLE where report_date=?1");
		qr.setParameter(1, rpt_date);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				String cust_rating = (String) a[6];
				Date customer_rating_date = (Date) a[7];
				Date customer_due_rating_date = (Date) a[8];
				String customer_type = (String) a[9];
				String report_quarter = (String) a[10];
				String remarks = (String) a[11];
				String del_flg = (String) a[12];
				String entity_cre_flg = (String) a[13];
				Date entity_cre_date = (Date) a[14];
				String mod_flg = (String) a[15];
				String entry_user = (String) a[16];
				String modify_user = (String) a[17];
				String auth_user = (String) a[18];
				Date entry_time = (Date) a[19];
				Date modify_time = (Date) a[20];
				Date auth_time = (Date) a[21];
				String aml_code_1 = (String) a[22];
				String aml_code_2 = (String) a[23];
				String aml_code_3 = (String) a[24];
				String aml_code_4 = (String) a[25];
				String aml_code_5 = (String) a[26];
				String aml_code_6 = (String) a[27];
				String aml_code_7 = (String) a[28];
				String aml_code_8 = (String) a[29];
				String aml_code_9 = (String) a[30];
				String aml_code_10 = (String) a[31];
				Date report_date = (Date) a[32];
				String cell_mapping = (String) a[33];
				String process_owner = (String) a[34];
				String qtr_flg = (String) a[35];
				Date verify_date = (Date) a[36];
				String verify_user = (String) a[37];
				String arch_flg = (String) a[38];
				String tran_channel = (String) a[39];
				String acct_num = (String) a[40];
				String act_name = (String) a[41];
				String tran_type = (String) a[42];
				String tran_sub_type = (String) a[43];
				Date tran_date = (Date) a[44];
				String tran_id = (String) a[45];
				String part_tran_id = (String) a[46];
				String part_tran_type = (String) a[47];
				String tran_crncy = (String) a[48];
				BigDecimal tran_amt = (BigDecimal) a[49];
				String tran_category = (String) a[50];
				Date relationship_date = (Date) a[51];
				String mis_face_to_face = (String) a[52];
				String mis_non_face_to_face = (String) a[53];
				String mis_internal_rating_grade = (String) a[54];
				String mis_internal_rating_scale = (String) a[55];

				T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id,
						ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type,
						report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user,
						modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3,
						aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10,
						report_date, cell_mapping, process_owner, qtr_flg, verify_date, verify_user, arch_flg,
						tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id,
						part_tran_type, tran_crncy, tran_amt, tran_category, relationship_date, mis_face_to_face,
						mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(t5Detail);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T5Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T5Detail> t9Dt1Page = new PageImpl<T5Detail>(pagedlist, PageRequest.of(Page, pageSize), t9Dt1.size());

		return t9Dt1Page;

	}

	public Page<T5Detail> searchAll(String rpt_date, String Cust_ID, String P_O, String Cust_name, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T5Detail> t9Dt1 = new ArrayList<T5Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date=?1 and cust_id=?2 and process_owner= ?3 and UPPER(customer_name) like UPPER(?4)");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, Cust_ID);
		qr.setParameter(3, P_O);
		qr.setParameter(4, Cust_name);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				String cust_rating = (String) a[6];
				Date customer_rating_date = (Date) a[7];
				Date customer_due_rating_date = (Date) a[8];
				String customer_type = (String) a[9];
				String report_quarter = (String) a[10];
				String remarks = (String) a[11];
				String del_flg = (String) a[12];
				String entity_cre_flg = (String) a[13];
				Date entity_cre_date = (Date) a[14];
				String mod_flg = (String) a[15];
				String entry_user = (String) a[16];
				String modify_user = (String) a[17];
				String auth_user = (String) a[18];
				Date entry_time = (Date) a[19];
				Date modify_time = (Date) a[20];
				Date auth_time = (Date) a[21];
				String aml_code_1 = (String) a[22];
				String aml_code_2 = (String) a[23];
				String aml_code_3 = (String) a[24];
				String aml_code_4 = (String) a[25];
				String aml_code_5 = (String) a[26];
				String aml_code_6 = (String) a[27];
				String aml_code_7 = (String) a[28];
				String aml_code_8 = (String) a[29];
				String aml_code_9 = (String) a[30];
				String aml_code_10 = (String) a[31];
				Date report_date = (Date) a[32];
				String cell_mapping = (String) a[33];
				String process_owner = (String) a[34];
				String qtr_flg = (String) a[35];
				Date verify_date = (Date) a[36];
				String verify_user = (String) a[37];
				String arch_flg = (String) a[38];
				String tran_channel = (String) a[39];
				String acct_num = (String) a[40];
				String act_name = (String) a[41];
				String tran_type = (String) a[42];
				String tran_sub_type = (String) a[43];
				Date tran_date = (Date) a[44];
				String tran_id = (String) a[45];
				String part_tran_id = (String) a[46];
				String part_tran_type = (String) a[47];
				String tran_crncy = (String) a[48];
				BigDecimal tran_amt = (BigDecimal) a[49];
				String tran_category = (String) a[50];
				Date relationship_date = (Date) a[51];
				String mis_face_to_face = (String) a[52];
				String mis_non_face_to_face = (String) a[53];
				String mis_internal_rating_grade = (String) a[54];
				String mis_internal_rating_scale = (String) a[55];

				T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id,
						ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type,
						report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user,
						modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3,
						aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10,
						report_date, cell_mapping, process_owner, qtr_flg, verify_date, verify_user, arch_flg,
						tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id,
						part_tran_type, tran_crncy, tran_amt, tran_category, relationship_date, mis_face_to_face,
						mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(t5Detail);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T5Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T5Detail> t9Dt1Page = new PageImpl<T5Detail>(pagedlist, PageRequest.of(Page, pageSize), t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	

	public Page<T5Detail> searchbycust(String rpt_date, String Cust_ID,String Cust_name, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T5Detail> t9Dt1 = new ArrayList<T5Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date=?1 and cust_id=?2 and UPPER(customer_name) like UPPER(?3)");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, Cust_ID);
		
		qr.setParameter(3, Cust_name);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				String cust_rating = (String) a[6];
				Date customer_rating_date = (Date) a[7];
				Date customer_due_rating_date = (Date) a[8];
				String customer_type = (String) a[9];
				String report_quarter = (String) a[10];
				String remarks = (String) a[11];
				String del_flg = (String) a[12];
				String entity_cre_flg = (String) a[13];
				Date entity_cre_date = (Date) a[14];
				String mod_flg = (String) a[15];
				String entry_user = (String) a[16];
				String modify_user = (String) a[17];
				String auth_user = (String) a[18];
				Date entry_time = (Date) a[19];
				Date modify_time = (Date) a[20];
				Date auth_time = (Date) a[21];
				String aml_code_1 = (String) a[22];
				String aml_code_2 = (String) a[23];
				String aml_code_3 = (String) a[24];
				String aml_code_4 = (String) a[25];
				String aml_code_5 = (String) a[26];
				String aml_code_6 = (String) a[27];
				String aml_code_7 = (String) a[28];
				String aml_code_8 = (String) a[29];
				String aml_code_9 = (String) a[30];
				String aml_code_10 = (String) a[31];
				Date report_date = (Date) a[32];
				String cell_mapping = (String) a[33];
				String process_owner = (String) a[34];
				String qtr_flg = (String) a[35];
				Date verify_date = (Date) a[36];
				String verify_user = (String) a[37];
				String arch_flg = (String) a[38];
				String tran_channel = (String) a[39];
				String acct_num = (String) a[40];
				String act_name = (String) a[41];
				String tran_type = (String) a[42];
				String tran_sub_type = (String) a[43];
				Date tran_date = (Date) a[44];
				String tran_id = (String) a[45];
				String part_tran_id = (String) a[46];
				String part_tran_type = (String) a[47];
				String tran_crncy = (String) a[48];
				BigDecimal tran_amt = (BigDecimal) a[49];
				String tran_category = (String) a[50];
				Date relationship_date = (Date) a[51];
				String mis_face_to_face = (String) a[52];
				String mis_non_face_to_face = (String) a[53];
				String mis_internal_rating_grade = (String) a[54];
				String mis_internal_rating_scale = (String) a[55];

				T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id,
						ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type,
						report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user,
						modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3,
						aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10,
						report_date, cell_mapping, process_owner, qtr_flg, verify_date, verify_user, arch_flg,
						tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id,
						part_tran_type, tran_crncy, tran_amt, tran_category, relationship_date, mis_face_to_face,
						mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(t5Detail);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T5Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T5Detail> t9Dt1Page = new PageImpl<T5Detail>(pagedlist, PageRequest.of(Page, pageSize), t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	public Page<T5Detail> searchbycustID(String rpt_date, String Cust_ID, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T5Detail> t9Dt1 = new ArrayList<T5Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date=?1 and cust_id=?2 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, Cust_ID);
		
		//qr.setParameter(3, Cust_name);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				String cust_rating = (String) a[6];
				Date customer_rating_date = (Date) a[7];
				Date customer_due_rating_date = (Date) a[8];
				String customer_type = (String) a[9];
				String report_quarter = (String) a[10];
				String remarks = (String) a[11];
				String del_flg = (String) a[12];
				String entity_cre_flg = (String) a[13];
				Date entity_cre_date = (Date) a[14];
				String mod_flg = (String) a[15];
				String entry_user = (String) a[16];
				String modify_user = (String) a[17];
				String auth_user = (String) a[18];
				Date entry_time = (Date) a[19];
				Date modify_time = (Date) a[20];
				Date auth_time = (Date) a[21];
				String aml_code_1 = (String) a[22];
				String aml_code_2 = (String) a[23];
				String aml_code_3 = (String) a[24];
				String aml_code_4 = (String) a[25];
				String aml_code_5 = (String) a[26];
				String aml_code_6 = (String) a[27];
				String aml_code_7 = (String) a[28];
				String aml_code_8 = (String) a[29];
				String aml_code_9 = (String) a[30];
				String aml_code_10 = (String) a[31];
				Date report_date = (Date) a[32];
				String cell_mapping = (String) a[33];
				String process_owner = (String) a[34];
				String qtr_flg = (String) a[35];
				Date verify_date = (Date) a[36];
				String verify_user = (String) a[37];
				String arch_flg = (String) a[38];
				String tran_channel = (String) a[39];
				String acct_num = (String) a[40];
				String act_name = (String) a[41];
				String tran_type = (String) a[42];
				String tran_sub_type = (String) a[43];
				Date tran_date = (Date) a[44];
				String tran_id = (String) a[45];
				String part_tran_id = (String) a[46];
				String part_tran_type = (String) a[47];
				String tran_crncy = (String) a[48];
				BigDecimal tran_amt = (BigDecimal) a[49];
				String tran_category = (String) a[50];
				Date relationship_date = (Date) a[51];
				String mis_face_to_face = (String) a[52];
				String mis_non_face_to_face = (String) a[53];
				String mis_internal_rating_grade = (String) a[54];
				String mis_internal_rating_scale = (String) a[55];

				T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id,
						ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type,
						report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user,
						modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3,
						aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10,
						report_date, cell_mapping, process_owner, qtr_flg, verify_date, verify_user, arch_flg,
						tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id,
						part_tran_type, tran_crncy, tran_amt, tran_category, relationship_date, mis_face_to_face,
						mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(t5Detail);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T5Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T5Detail> t9Dt1Page = new PageImpl<T5Detail>(pagedlist, PageRequest.of(Page, pageSize), t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	
	
	public Page<T5Detail> searchbyPO(String rpt_date, String P_O, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T5Detail> t9Dt1 = new ArrayList<T5Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date=?1 and process_owner=?2 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, P_O);
		
		//qr.setParameter(3, Cust_name);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				String cust_rating = (String) a[6];
				Date customer_rating_date = (Date) a[7];
				Date customer_due_rating_date = (Date) a[8];
				String customer_type = (String) a[9];
				String report_quarter = (String) a[10];
				String remarks = (String) a[11];
				String del_flg = (String) a[12];
				String entity_cre_flg = (String) a[13];
				Date entity_cre_date = (Date) a[14];
				String mod_flg = (String) a[15];
				String entry_user = (String) a[16];
				String modify_user = (String) a[17];
				String auth_user = (String) a[18];
				Date entry_time = (Date) a[19];
				Date modify_time = (Date) a[20];
				Date auth_time = (Date) a[21];
				String aml_code_1 = (String) a[22];
				String aml_code_2 = (String) a[23];
				String aml_code_3 = (String) a[24];
				String aml_code_4 = (String) a[25];
				String aml_code_5 = (String) a[26];
				String aml_code_6 = (String) a[27];
				String aml_code_7 = (String) a[28];
				String aml_code_8 = (String) a[29];
				String aml_code_9 = (String) a[30];
				String aml_code_10 = (String) a[31];
				Date report_date = (Date) a[32];
				String cell_mapping = (String) a[33];
				String process_owner = (String) a[34];
				String qtr_flg = (String) a[35];
				Date verify_date = (Date) a[36];
				String verify_user = (String) a[37];
				String arch_flg = (String) a[38];
				String tran_channel = (String) a[39];
				String acct_num = (String) a[40];
				String act_name = (String) a[41];
				String tran_type = (String) a[42];
				String tran_sub_type = (String) a[43];
				Date tran_date = (Date) a[44];
				String tran_id = (String) a[45];
				String part_tran_id = (String) a[46];
				String part_tran_type = (String) a[47];
				String tran_crncy = (String) a[48];
				BigDecimal tran_amt = (BigDecimal) a[49];
				String tran_category = (String) a[50];
				Date relationship_date = (Date) a[51];
				String mis_face_to_face = (String) a[52];
				String mis_non_face_to_face = (String) a[53];
				String mis_internal_rating_grade = (String) a[54];
				String mis_internal_rating_scale = (String) a[55];

				T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id,
						ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type,
						report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user,
						modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3,
						aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10,
						report_date, cell_mapping, process_owner, qtr_flg, verify_date, verify_user, arch_flg,
						tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id,
						part_tran_type, tran_crncy, tran_amt, tran_category, relationship_date, mis_face_to_face,
						mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(t5Detail);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T5Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T5Detail> t9Dt1Page = new PageImpl<T5Detail>(pagedlist, PageRequest.of(Page, pageSize), t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	
	public Page<T5Detail> searchbyName(String rpt_date, String Cust_Name, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T5Detail> t9Dt1 = new ArrayList<T5Detail>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T5_RISK_RATING_MIG_DETAILED_TABLE  where report_date=?1 and UPPER(customer_name) like UPPER(?2)");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, Cust_Name);
		
		//qr.setParameter(3, Cust_name);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String cust_id = (String) a[0];
				String customer_name = (String) a[1];
				String branch_id = (String) a[2];
				String branch_name = (String) a[3];
				String bank_id = (String) a[4];
				String ownership_type = (String) a[5];
				String cust_rating = (String) a[6];
				Date customer_rating_date = (Date) a[7];
				Date customer_due_rating_date = (Date) a[8];
				String customer_type = (String) a[9];
				String report_quarter = (String) a[10];
				String remarks = (String) a[11];
				String del_flg = (String) a[12];
				String entity_cre_flg = (String) a[13];
				Date entity_cre_date = (Date) a[14];
				String mod_flg = (String) a[15];
				String entry_user = (String) a[16];
				String modify_user = (String) a[17];
				String auth_user = (String) a[18];
				Date entry_time = (Date) a[19];
				Date modify_time = (Date) a[20];
				Date auth_time = (Date) a[21];
				String aml_code_1 = (String) a[22];
				String aml_code_2 = (String) a[23];
				String aml_code_3 = (String) a[24];
				String aml_code_4 = (String) a[25];
				String aml_code_5 = (String) a[26];
				String aml_code_6 = (String) a[27];
				String aml_code_7 = (String) a[28];
				String aml_code_8 = (String) a[29];
				String aml_code_9 = (String) a[30];
				String aml_code_10 = (String) a[31];
				Date report_date = (Date) a[32];
				String cell_mapping = (String) a[33];
				String process_owner = (String) a[34];
				String qtr_flg = (String) a[35];
				Date verify_date = (Date) a[36];
				String verify_user = (String) a[37];
				String arch_flg = (String) a[38];
				String tran_channel = (String) a[39];
				String acct_num = (String) a[40];
				String act_name = (String) a[41];
				String tran_type = (String) a[42];
				String tran_sub_type = (String) a[43];
				Date tran_date = (Date) a[44];
				String tran_id = (String) a[45];
				String part_tran_id = (String) a[46];
				String part_tran_type = (String) a[47];
				String tran_crncy = (String) a[48];
				BigDecimal tran_amt = (BigDecimal) a[49];
				String tran_category = (String) a[50];
				Date relationship_date = (Date) a[51];
				String mis_face_to_face = (String) a[52];
				String mis_non_face_to_face = (String) a[53];
				String mis_internal_rating_grade = (String) a[54];
				String mis_internal_rating_scale = (String) a[55];

				T5Detail t5Detail = new T5Detail(cust_id, customer_name, branch_id, branch_name, bank_id,
						ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type,
						report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user,
						modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3,
						aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10,
						report_date, cell_mapping, process_owner, qtr_flg, verify_date, verify_user, arch_flg,
						tran_channel, acct_num, act_name, tran_type, tran_sub_type, tran_date, tran_id, part_tran_id,
						part_tran_type, tran_crncy, tran_amt, tran_category, relationship_date, mis_face_to_face,
						mis_non_face_to_face, mis_internal_rating_grade, mis_internal_rating_scale);

				t9Dt1.add(t5Detail);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T5Detail> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T5Detail> t9Dt1Page = new PageImpl<T5Detail>(pagedlist, PageRequest.of(Page, pageSize), t9Dt1.size());

		return t9Dt1Page;

	}
}

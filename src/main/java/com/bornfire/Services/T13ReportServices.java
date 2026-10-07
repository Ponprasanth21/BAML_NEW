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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entity.t13.T13Detail;
import com.bornfire.entity.t13.T13DetailRepo;
import com.bornfire.entity.t13.T13Report;

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
public class T13ReportServices {

	private static final Logger logger = LoggerFactory.getLogger(T13ReportServices.class);
	
	@Autowired
	T13DetailRepo t13DetailRepo;

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
	public ModelAndView getT13View(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> t13rep = new ArrayList<Object>();
		Query<Object[]> qr;
		
		

		logger.info("Inside archive"+fromdate+todate);
		qr = hs.createNativeQuery(
				"select * from T13_IND_CASH_WDL_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
			try {
		qr.setParameter(1, df.parse(fromdate));
		qr.setParameter(2, df.parse(todate));
		

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			
			String d1a_cur_bmur100k = (String) a[0];
			String d2a_cur_mur100_200k = (String) a[1];
			String d3a_cur_mur200_350k = (String) a[2];
			String d4a_cur_mur350_500k = (String) a[3];
			String d5a_cur_mur500_750k = (String) a[4];
			String d6a_cur_mur750_1000k = (String) a[5];
			String d7a_cur_amur1000k = (String) a[6];
			String d8a_cur_total = (String) a[7];
			BigDecimal c1b_cur_bmur100k_nof_wdl_tran_hig = (BigDecimal) a[8];
			BigDecimal c2b_cur_mur100_200k_nof_wdl_tran_hig = (BigDecimal) a[9];
			BigDecimal c3b_cur_mur200_350k_nof_wdl_tran_hig = (BigDecimal) a[10];
			BigDecimal c4b_cur_mur350_500k_nof_wdl_tran_hig = (BigDecimal) a[11];
			BigDecimal c5b_cur_mur500_750k_nof_wdl_tran_hig = (BigDecimal) a[12];
			BigDecimal c6b_cur_mur750_1000k_nof_wdl_tran_hig = (BigDecimal) a[13];
			BigDecimal c7b_cur_amur1000k_nof_wdl_tran_hig = (BigDecimal) a[14];
			BigDecimal c8b_cur_total_nof_wdl_tran_hig = (BigDecimal) a[15];
			BigDecimal c1c_cur_bmur100k_to_val_tran_hig = (BigDecimal) a[16];
			BigDecimal c2c_cur_mur100_200k_to_val_tran_hig = (BigDecimal) a[17];
			BigDecimal c3c_cur_mur200_350k_to_val_tran_hig = (BigDecimal) a[18];
			BigDecimal c4c_cur_mur350_500k_to_val_tran_hig = (BigDecimal) a[19];
			BigDecimal c5c_cur_mur500_750k_to_val_tran_hig = (BigDecimal) a[20];
			BigDecimal c6c_cur_mur750_1000k_to_val_tran_hig = (BigDecimal) a[21];
			BigDecimal c7c_cur_amur1000k_to_val_tran_hig = (BigDecimal) a[22];
			BigDecimal c8c_cur_total_to_val_tran_hig = (BigDecimal) a[23];
			BigDecimal c1d_cur_bmur100k_nof_wdl_tran_med = (BigDecimal) a[24];
			BigDecimal c2d_cur_mur100_200k_nof_wdl_tran_med = (BigDecimal) a[25];
			BigDecimal c3d_cur_mur200_350k_nof_wdl_tran_med = (BigDecimal) a[26];
			BigDecimal c4d_cur_mur350_500k_nof_wdl_tran_med = (BigDecimal) a[27];
			BigDecimal c5d_cur_mur500_750k_nof_wdl_tran_med = (BigDecimal) a[28];
			BigDecimal c6d_cur_mur750_1000k_nof_wdl_tran_med = (BigDecimal) a[29];
			BigDecimal c7d_cur_amur1000k_nof_wdl_tran_med = (BigDecimal) a[30];
			BigDecimal c8d_cur_total_nof_wdl_tran_med = (BigDecimal) a[31];
			BigDecimal c1e_cur_bmur100k_to_val_tran_med = (BigDecimal) a[32];
			BigDecimal c2e_cur_mur100_200k_to_val_tran_med = (BigDecimal) a[33];
			BigDecimal c3e_cur_mur200_350k_to_val_tran_med = (BigDecimal) a[34];
			BigDecimal c4e_cur_mur350_500k_to_val_tran_med = (BigDecimal) a[35];
			BigDecimal c5e_cur_mur500_750k_to_val_tran_med = (BigDecimal) a[36];
			BigDecimal c6e_cur_mur750_1000k_to_val_tran_med = (BigDecimal) a[37];
			BigDecimal c7e_cur_amur1000k_to_val_tran_med = (BigDecimal) a[38];
			BigDecimal c8e_cur_total_to_val_tran_med = (BigDecimal) a[39];
			BigDecimal c1f_cur_bmur100k_nof_wdl_tran_low = (BigDecimal) a[40];
			BigDecimal c2f_cur_mur100_200k_nof_wdl_tran_low = (BigDecimal) a[41];
			BigDecimal c3f_cur_mur200_350k_nof_wdl_tran_low = (BigDecimal) a[42];
			BigDecimal c4f_cur_mur350_500k_nof_wdl_tran_low = (BigDecimal) a[43];
			BigDecimal c5f_cur_mur500_750k_nof_wdl_tran_low = (BigDecimal) a[44];
			BigDecimal c6f_cur_mur750_1000k_nof_wdl_tran_low = (BigDecimal) a[45];
			BigDecimal c7f_cur_amur1000k_nof_wdl_tran_low = (BigDecimal) a[46];
			BigDecimal c8f_cur_total_nof_wdl_tran_low = (BigDecimal) a[47];
			BigDecimal c1g_cur_bmur100k_to_val_tran_low = (BigDecimal) a[48];
			BigDecimal c2g_cur_mur100_200k_to_val_tran_low = (BigDecimal) a[49];
			BigDecimal c3g_cur_mur200_350k_to_val_tran_low = (BigDecimal) a[50];
			BigDecimal c4g_cur_mur350_500k_to_val_tran_low = (BigDecimal) a[51];
			BigDecimal c5g_cur_mur500_750k_to_val_tran_low = (BigDecimal) a[52];
			BigDecimal c6g_cur_mur750_1000k_to_val_tran_low = (BigDecimal) a[53];
			BigDecimal c7g_cur_amur1000k_to_val_tran_low = (BigDecimal) a[54];
			BigDecimal c8g_cur_total_to_val_tran_low = (BigDecimal) a[55];
			String report_code = (String) a[56];
			String report_name = (String) a[57];
			Date report_date = (Date) a[58];
			Date report_due_date = (Date) a[59];
			Date rep_submit_date = (Date) a[60];
			Date rep_period_from = (Date) a[61];
			Date rep_period_to = (Date) a[62];
			String rep_freq = (String) a[63];
			Character nil_report_flg = (Character) a[64];
			Character arch_flg = (Character) a[65];



			T13Report t13report = new T13Report(d1a_cur_bmur100k, d2a_cur_mur100_200k, d3a_cur_mur200_350k, d4a_cur_mur350_500k, d5a_cur_mur500_750k, 
					d6a_cur_mur750_1000k, d7a_cur_amur1000k, d8a_cur_total, c1b_cur_bmur100k_nof_wdl_tran_hig, c2b_cur_mur100_200k_nof_wdl_tran_hig, 
					c3b_cur_mur200_350k_nof_wdl_tran_hig, c4b_cur_mur350_500k_nof_wdl_tran_hig, c5b_cur_mur500_750k_nof_wdl_tran_hig, 
					c6b_cur_mur750_1000k_nof_wdl_tran_hig, c7b_cur_amur1000k_nof_wdl_tran_hig, c8b_cur_total_nof_wdl_tran_hig, 
					c1c_cur_bmur100k_to_val_tran_hig, c2c_cur_mur100_200k_to_val_tran_hig, c3c_cur_mur200_350k_to_val_tran_hig, 
					c4c_cur_mur350_500k_to_val_tran_hig, c5c_cur_mur500_750k_to_val_tran_hig, c6c_cur_mur750_1000k_to_val_tran_hig, 
					c7c_cur_amur1000k_to_val_tran_hig, c8c_cur_total_to_val_tran_hig, c1d_cur_bmur100k_nof_wdl_tran_med, c2d_cur_mur100_200k_nof_wdl_tran_med, 
					c3d_cur_mur200_350k_nof_wdl_tran_med, c4d_cur_mur350_500k_nof_wdl_tran_med, c5d_cur_mur500_750k_nof_wdl_tran_med, 
					c6d_cur_mur750_1000k_nof_wdl_tran_med, c7d_cur_amur1000k_nof_wdl_tran_med, c8d_cur_total_nof_wdl_tran_med, 
					c1e_cur_bmur100k_to_val_tran_med, c2e_cur_mur100_200k_to_val_tran_med, c3e_cur_mur200_350k_to_val_tran_med, 
					c4e_cur_mur350_500k_to_val_tran_med, c5e_cur_mur500_750k_to_val_tran_med, c6e_cur_mur750_1000k_to_val_tran_med, 
					c7e_cur_amur1000k_to_val_tran_med, c8e_cur_total_to_val_tran_med, c1f_cur_bmur100k_nof_wdl_tran_low, c2f_cur_mur100_200k_nof_wdl_tran_low, 
					c3f_cur_mur200_350k_nof_wdl_tran_low, c4f_cur_mur350_500k_nof_wdl_tran_low, c5f_cur_mur500_750k_nof_wdl_tran_low, 
					c6f_cur_mur750_1000k_nof_wdl_tran_low, c7f_cur_amur1000k_nof_wdl_tran_low, c8f_cur_total_nof_wdl_tran_low, 
					c1g_cur_bmur100k_to_val_tran_low, c2g_cur_mur100_200k_to_val_tran_low, c3g_cur_mur200_350k_to_val_tran_low, 
					c4g_cur_mur350_500k_to_val_tran_low, c5g_cur_mur500_750k_to_val_tran_low, c6g_cur_mur750_1000k_to_val_tran_low, 
					c7g_cur_amur1000k_to_val_tran_low, c8g_cur_total_to_val_tran_low, report_code, report_name, report_date, report_due_date, 
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);
			t13rep.add(t13report);
		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (t13rep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, t13rep.size());
		 * pagedlist = t13rep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> t13repPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(Page, pageSize),
		 * t13rep.size());
		 */

		mv.setViewName("ReportT13");
		//mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t13rep);
		mv.addObject("displaymode", "summary");
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		System.out.println("scv" + mv.getViewName());
		return mv;

	}

/*	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;
		logger.info("Report precheck : " + reportId);

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dt9 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);


			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery(
					"select count(*) from T13Report a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dt9).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T13Report a").getSingleResult();
				if (modcnt > 0) {
					msg = "success";

					
					 * msg = "Records Pending for Verification For the Report";
					  } else {
					msg = "This Report is not Applicable";
				}
			} else {
				 msg = "This Report is not Applicable";

				//msg = "success";

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg ="This Report is not Applicable";
			e.printStackTrace();

		}

		return msg;

	}
*/
	public String preCheck(String reportid, String fromdate, String todate) {
		logger.info("t12 report service -> precheck()");
		logger.info("todate -> " + todate);
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			query = hs.createNativeQuery("select count(*) from T13_IND_CASH_WDL_TABLE where report_date = ?1 ")
					.setParameter(1, df.parse(todate));
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


	public ModelAndView getT13Rep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> t13rep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T13_IND_CASH_WDL_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {
			
			String d1a_cur_bmur100k = (String) a[0];
			String d2a_cur_mur100_200k = (String) a[1];
			String d3a_cur_mur200_350k = (String) a[2];
			String d4a_cur_mur350_500k = (String) a[3];
			String d5a_cur_mur500_750k = (String) a[4];
			String d6a_cur_mur750_1000k = (String) a[5];
			String d7a_cur_amur1000k = (String) a[6];
			String d8a_cur_total = (String) a[7];
			BigDecimal c1b_cur_bmur100k_nof_wdl_tran_hig = (BigDecimal) a[8];
			BigDecimal c2b_cur_mur100_200k_nof_wdl_tran_hig = (BigDecimal) a[9];
			BigDecimal c3b_cur_mur200_350k_nof_wdl_tran_hig = (BigDecimal) a[10];
			BigDecimal c4b_cur_mur350_500k_nof_wdl_tran_hig = (BigDecimal) a[11];
			BigDecimal c5b_cur_mur500_750k_nof_wdl_tran_hig = (BigDecimal) a[12];
			BigDecimal c6b_cur_mur750_1000k_nof_wdl_tran_hig = (BigDecimal) a[13];
			BigDecimal c7b_cur_amur1000k_nof_wdl_tran_hig = (BigDecimal) a[14];
			BigDecimal c8b_cur_total_nof_wdl_tran_hig = (BigDecimal) a[15];
			BigDecimal c1c_cur_bmur100k_to_val_tran_hig = (BigDecimal) a[16];
			BigDecimal c2c_cur_mur100_200k_to_val_tran_hig = (BigDecimal) a[17];
			BigDecimal c3c_cur_mur200_350k_to_val_tran_hig = (BigDecimal) a[18];
			BigDecimal c4c_cur_mur350_500k_to_val_tran_hig = (BigDecimal) a[19];
			BigDecimal c5c_cur_mur500_750k_to_val_tran_hig = (BigDecimal) a[20];
			BigDecimal c6c_cur_mur750_1000k_to_val_tran_hig = (BigDecimal) a[21];
			BigDecimal c7c_cur_amur1000k_to_val_tran_hig = (BigDecimal) a[22];
			BigDecimal c8c_cur_total_to_val_tran_hig = (BigDecimal) a[23];
			BigDecimal c1d_cur_bmur100k_nof_wdl_tran_med = (BigDecimal) a[24];
			BigDecimal c2d_cur_mur100_200k_nof_wdl_tran_med = (BigDecimal) a[25];
			BigDecimal c3d_cur_mur200_350k_nof_wdl_tran_med = (BigDecimal) a[26];
			BigDecimal c4d_cur_mur350_500k_nof_wdl_tran_med = (BigDecimal) a[27];
			BigDecimal c5d_cur_mur500_750k_nof_wdl_tran_med = (BigDecimal) a[28];
			BigDecimal c6d_cur_mur750_1000k_nof_wdl_tran_med = (BigDecimal) a[29];
			BigDecimal c7d_cur_amur1000k_nof_wdl_tran_med = (BigDecimal) a[30];
			BigDecimal c8d_cur_total_nof_wdl_tran_med = (BigDecimal) a[31];
			BigDecimal c1e_cur_bmur100k_to_val_tran_med = (BigDecimal) a[32];
			BigDecimal c2e_cur_mur100_200k_to_val_tran_med = (BigDecimal) a[33];
			BigDecimal c3e_cur_mur200_350k_to_val_tran_med = (BigDecimal) a[34];
			BigDecimal c4e_cur_mur350_500k_to_val_tran_med = (BigDecimal) a[35];
			BigDecimal c5e_cur_mur500_750k_to_val_tran_med = (BigDecimal) a[36];
			BigDecimal c6e_cur_mur750_1000k_to_val_tran_med = (BigDecimal) a[37];
			BigDecimal c7e_cur_amur1000k_to_val_tran_med = (BigDecimal) a[38];
			BigDecimal c8e_cur_total_to_val_tran_med = (BigDecimal) a[39];
			BigDecimal c1f_cur_bmur100k_nof_wdl_tran_low = (BigDecimal) a[40];
			BigDecimal c2f_cur_mur100_200k_nof_wdl_tran_low = (BigDecimal) a[41];
			BigDecimal c3f_cur_mur200_350k_nof_wdl_tran_low = (BigDecimal) a[42];
			BigDecimal c4f_cur_mur350_500k_nof_wdl_tran_low = (BigDecimal) a[43];
			BigDecimal c5f_cur_mur500_750k_nof_wdl_tran_low = (BigDecimal) a[44];
			BigDecimal c6f_cur_mur750_1000k_nof_wdl_tran_low = (BigDecimal) a[45];
			BigDecimal c7f_cur_amur1000k_nof_wdl_tran_low = (BigDecimal) a[46];
			BigDecimal c8f_cur_total_nof_wdl_tran_low = (BigDecimal) a[47];
			BigDecimal c1g_cur_bmur100k_to_val_tran_low = (BigDecimal) a[48];
			BigDecimal c2g_cur_mur100_200k_to_val_tran_low = (BigDecimal) a[49];
			BigDecimal c3g_cur_mur200_350k_to_val_tran_low = (BigDecimal) a[50];
			BigDecimal c4g_cur_mur350_500k_to_val_tran_low = (BigDecimal) a[51];
			BigDecimal c5g_cur_mur500_750k_to_val_tran_low = (BigDecimal) a[52];
			BigDecimal c6g_cur_mur750_1000k_to_val_tran_low = (BigDecimal) a[53];
			BigDecimal c7g_cur_amur1000k_to_val_tran_low = (BigDecimal) a[54];
			BigDecimal c8g_cur_total_to_val_tran_low = (BigDecimal) a[55];
			String report_code = (String) a[56];
			String report_name = (String) a[57];
			Date report_date = (Date) a[58];
			Date report_due_date = (Date) a[59];
			Date rep_submit_date = (Date) a[60];
			Date rep_period_from = (Date) a[61];
			Date rep_period_to = (Date) a[62];
			String rep_freq = (String) a[63];
			Character nil_report_flg = (Character) a[64];
			Character arch_flg = (Character) a[65];



			T13Report t13report = new T13Report(d1a_cur_bmur100k, d2a_cur_mur100_200k, d3a_cur_mur200_350k, d4a_cur_mur350_500k, d5a_cur_mur500_750k, 
					d6a_cur_mur750_1000k, d7a_cur_amur1000k, d8a_cur_total, c1b_cur_bmur100k_nof_wdl_tran_hig, c2b_cur_mur100_200k_nof_wdl_tran_hig, 
					c3b_cur_mur200_350k_nof_wdl_tran_hig, c4b_cur_mur350_500k_nof_wdl_tran_hig, c5b_cur_mur500_750k_nof_wdl_tran_hig, 
					c6b_cur_mur750_1000k_nof_wdl_tran_hig, c7b_cur_amur1000k_nof_wdl_tran_hig, c8b_cur_total_nof_wdl_tran_hig, 
					c1c_cur_bmur100k_to_val_tran_hig, c2c_cur_mur100_200k_to_val_tran_hig, c3c_cur_mur200_350k_to_val_tran_hig, 
					c4c_cur_mur350_500k_to_val_tran_hig, c5c_cur_mur500_750k_to_val_tran_hig, c6c_cur_mur750_1000k_to_val_tran_hig, 
					c7c_cur_amur1000k_to_val_tran_hig, c8c_cur_total_to_val_tran_hig, c1d_cur_bmur100k_nof_wdl_tran_med, c2d_cur_mur100_200k_nof_wdl_tran_med, 
					c3d_cur_mur200_350k_nof_wdl_tran_med, c4d_cur_mur350_500k_nof_wdl_tran_med, c5d_cur_mur500_750k_nof_wdl_tran_med, 
					c6d_cur_mur750_1000k_nof_wdl_tran_med, c7d_cur_amur1000k_nof_wdl_tran_med, c8d_cur_total_nof_wdl_tran_med, 
					c1e_cur_bmur100k_to_val_tran_med, c2e_cur_mur100_200k_to_val_tran_med, c3e_cur_mur200_350k_to_val_tran_med, 
					c4e_cur_mur350_500k_to_val_tran_med, c5e_cur_mur500_750k_to_val_tran_med, c6e_cur_mur750_1000k_to_val_tran_med, 
					c7e_cur_amur1000k_to_val_tran_med, c8e_cur_total_to_val_tran_med, c1f_cur_bmur100k_nof_wdl_tran_low, c2f_cur_mur100_200k_nof_wdl_tran_low, 
					c3f_cur_mur200_350k_nof_wdl_tran_low, c4f_cur_mur350_500k_nof_wdl_tran_low, c5f_cur_mur500_750k_nof_wdl_tran_low, 
					c6f_cur_mur750_1000k_nof_wdl_tran_low, c7f_cur_amur1000k_nof_wdl_tran_low, c8f_cur_total_nof_wdl_tran_low, 
					c1g_cur_bmur100k_to_val_tran_low, c2g_cur_mur100_200k_to_val_tran_low, c3g_cur_mur200_350k_to_val_tran_low, 
					c4g_cur_mur350_500k_to_val_tran_low, c5g_cur_mur500_750k_to_val_tran_low, c6g_cur_mur750_1000k_to_val_tran_low, 
					c7g_cur_amur1000k_to_val_tran_low, c8g_cur_total_to_val_tran_low, report_code, report_name, report_date, report_due_date, 
					rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);
			
			t13rep.add(t13report);
		}
		
		mv.setViewName("ReportT13");
		mv.addObject("reportsummary", t13rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;

	}

	public ModelAndView getT13Dtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;

		ModelAndView mv = new ModelAndView();
		
		/*

		Session hs = sessionFactory.getCurrentSession();
		List<Object> t13Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T13_IND_CASH_DEP_DETAILS a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T13_IND_CASH_DEP_DETAILS a where report_date = ?1");
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
			Date tran_date = (Date) a[4];
			String tran_id = (String) a[5];
			BigDecimal part_tran_id = (BigDecimal) a[6];
			Character part_tran_type = (Character) a[7];
			String tran_crncy = (String) a[8];
			BigDecimal tran_amt = (BigDecimal) a[9];
			String tran_particulars = (String) a[10];
			String tran_type = (String) a[11];
			String tran_sub_type = (String) a[12];
			Character qtr_flg = (Character) a[13];
			Character entity_flg = (Character) a[14];
			Character del_flg = (Character) a[15];
			Character modify_flg = (Character) a[16];
			Date entry_date = (Date) a[17];
			Date modify_date = (Date) a[18];
			Date verify_date = (Date) a[19];
			String entry_user = (String) a[20];
			String modify_user = (String) a[21];
			String verify_user = (String) a[22];
			String report_code = (String) a[23];
			String report_name = (String) a[24];
			Date report_date = (Date) a[25];
			Character arch_flg = (Character) a[26];


			T13DetailId py1 = new T13DetailId(cust_id, report_date);
			T13Detail py = new T13Detail(py1, cust_name, acct_no, acct_name, tran_date,
					tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt, tran_particulars, 
					tran_type, tran_sub_type, qtr_flg, entity_flg, del_flg, modify_flg, entry_date,
					modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, arch_flg);
			
			System.out.println(py.toString());

			t13Dt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (t13Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t13Dt1.size());
			pagedlist = t13Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> t13Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(Page, pageSize),
				t13Dt1.size());
				
		*/

		mv.setViewName("ReportT13 :: reportcontent");
		try {
			mv.addObject("reportdetails", t13DetailRepo.detailList(df.parse(todate), pageable));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mv.addObject("singledetail", new T13Detail());
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
		System.out.println(todate);

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

		zipFileName =  fileName + ".zip";

		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile = null;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("detailexcel")) {
					if (dtltype.equals("report")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T13/T13.jasper");
					} 

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T13/T13.jasper");
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
					path +=  fileName;
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

}

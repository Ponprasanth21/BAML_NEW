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

import com.bornfire.entity.t11.T11DetailRep;
import com.bornfire.entity.t11.T11Details;
import com.bornfire.entity.t11.T11Reports;

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
public class T11ReportService {

	private static final Logger logger = LoggerFactory.getLogger(T11Reports.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	T11DetailRep t11DetailsRep;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	Environment env;

	// summary starts
	public ModelAndView getT11currentView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T11urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive" + fromdate);
		qr = hs.createNativeQuery(
				"select * from T11_CDD_TRAN_TERM_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {

			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

		
			String d4a_cur_cdd_tran_term = (String) a[0];
			String d5a_cur_amlcft_tran_term = (String) a[1];
			String d6a_cur_total_tran = (String) a[2];

			BigDecimal c4b_cur_cdd_tran_term_not_low = (BigDecimal) a[3];
			BigDecimal c5b_cur_amlcft_tran_term_not_low = (BigDecimal) a[4];
			BigDecimal c6b_cur_total_not_low = (BigDecimal) a[5];

			BigDecimal c4c_cur_cdd_tran_term_tamt_low = (BigDecimal) a[6];
			BigDecimal c5c_cur_amlcft_tran_term_tamt_low = (BigDecimal) a[7];
			BigDecimal c6e_cur_total_tamt_low = (BigDecimal) a[8];

			BigDecimal c4d_cur_cdd_tran_term_not_med = (BigDecimal) a[9];
			BigDecimal c5d_cur_amlcft_tran_term_not_med = (BigDecimal) a[10];
			BigDecimal c6d_cur_total_not_med = (BigDecimal) a[11];

			BigDecimal c4e_cur_cdd_tran_term_tamt_med = (BigDecimal) a[12];
			BigDecimal c5e_cur_amlcft_tran_term_tamt_med = (BigDecimal) a[13];
			BigDecimal c6e_cur_total_tamt_med = (BigDecimal) a[14];

			BigDecimal c4f_cur_cdd_tran_term_not_hig = (BigDecimal) a[15];
			BigDecimal c5f_cur_amlcft_tran_term_not_hig = (BigDecimal) a[16];
			BigDecimal c6f_cur_total_not_hig = (BigDecimal) a[17];

			BigDecimal c4g_cur_cdd_tran_term_tamt_hig = (BigDecimal) a[18];
			BigDecimal c5g_cur_amlcft_tran_term_tamt_hig = (BigDecimal) a[19];
			BigDecimal c6g_cur_total_tamt_hig = (BigDecimal) a[20];

			String report_code = (String) a[21];
			String report_name = (String) a[22];
			Date report_date = (Date) a[23];
			Date report_due_date = (Date) a[24];
			Date rep_submit_date = (Date) a[25];
			Date rep_period_from = (Date) a[26];
			Date rep_period_to = (Date) a[27];
		
			String rep_freq = (String) a[28];
			Character nil_report_flg = (Character) a[29];
			Character arch_flg = (Character) a[30];

			T11Reports T11Report = new T11Reports( d4a_cur_cdd_tran_term, d5a_cur_amlcft_tran_term, d6a_cur_total_tran,
					c4b_cur_cdd_tran_term_not_low, c5b_cur_amlcft_tran_term_not_low, c6b_cur_total_not_low,
					c4c_cur_cdd_tran_term_tamt_low, c5c_cur_amlcft_tran_term_tamt_low, c6e_cur_total_tamt_low,
					c4d_cur_cdd_tran_term_not_med, c5d_cur_amlcft_tran_term_not_med, c6d_cur_total_not_med,
					c4e_cur_cdd_tran_term_tamt_med, c5e_cur_amlcft_tran_term_tamt_med, c6e_cur_total_tamt_med,
					c4f_cur_cdd_tran_term_not_hig, c5f_cur_amlcft_tran_term_not_hig, c6f_cur_total_not_hig,
					c4g_cur_cdd_tran_term_tamt_hig, c5g_cur_amlcft_tran_term_tamt_hig, c6g_cur_total_tamt_hig,
					report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from,
					rep_period_to, rep_freq, nil_report_flg, arch_flg);

			T11urrentrep.add(T11Report);

		}
		;

		/*
		 * List<Object> pagedlist;
		 * 
		 * if (T11urrentrep.size() < startItem) { pagedlist = Collections.emptyList(); }
		 * else { int toIndex = Math.min(startItem + pageSize, T11urrentrep.size());
		 * pagedlist = T11urrentrep.subList(startItem, toIndex); }
		 * logger.info("Converting to Page"); Page<Object> T11urrentrepPage = new
		 * PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
		 * T11urrentrep.size());
		 */

		mv.setViewName("ReportT11");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T11urrentrep);
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
		Date dT11;

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dT11 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);

			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs
					.createQuery("select count(*) from T11Reports a where a.rep_period_from=?1 and  a.rep_period_to=?2")
					.setParameter(1, dt1).setParameter(2, dT11).getSingleResult();

			if (dtlcnt > 0) {
				logger.info("Getting No of records in Mod table :" + reportId);
				Long modcnt = (Long) hs.createQuery("select count(*) from T11Reports a").getSingleResult();
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

	public ModelAndView getT11currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		Session hs = sessionFactory.getCurrentSession();

		List<Object> T11urrentrep = new ArrayList<Object>();
		Query<Object[]> qr;

		logger.info("Inside archive");
		qr = hs.createNativeQuery(
				"select * from T11_CDD_TRAN_TERM_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2");
		try {
			qr.setParameter(1, df.parse(fromdate));
			qr.setParameter(2, df.parse(todate));

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> result = qr.getResultList();
		for (Object[] a : result) {

			String d4a_cur_cdd_tran_term = (String) a[0];
			String d5a_cur_amlcft_tran_term = (String) a[1];
			String d6a_cur_total_tran = (String) a[2];

			BigDecimal c4b_cur_cdd_tran_term_not_low = (BigDecimal) a[3];
			BigDecimal c5b_cur_amlcft_tran_term_not_low = (BigDecimal) a[4];
			BigDecimal c6b_cur_total_not_low = (BigDecimal) a[5];

			BigDecimal c4c_cur_cdd_tran_term_tamt_low = (BigDecimal) a[6];
			BigDecimal c5c_cur_amlcft_tran_term_tamt_low = (BigDecimal) a[7];
			BigDecimal c6e_cur_total_tamt_low = (BigDecimal) a[8];

			BigDecimal c4d_cur_cdd_tran_term_not_med = (BigDecimal) a[9];
			BigDecimal c5d_cur_amlcft_tran_term_not_med = (BigDecimal) a[10];
			BigDecimal c6d_cur_total_not_med = (BigDecimal) a[11];

			BigDecimal c4e_cur_cdd_tran_term_tamt_med = (BigDecimal) a[12];
			BigDecimal c5e_cur_amlcft_tran_term_tamt_med = (BigDecimal) a[13];
			BigDecimal c6e_cur_total_tamt_med = (BigDecimal) a[14];

			BigDecimal c4f_cur_cdd_tran_term_not_hig = (BigDecimal) a[15];
			BigDecimal c5f_cur_amlcft_tran_term_not_hig = (BigDecimal) a[16];
			BigDecimal c6f_cur_total_not_hig = (BigDecimal) a[17];

			BigDecimal c4g_cur_cdd_tran_term_tamt_hig = (BigDecimal) a[18];
			BigDecimal c5g_cur_amlcft_tran_term_tamt_hig = (BigDecimal) a[19];
			BigDecimal c6g_cur_total_tamt_hig = (BigDecimal) a[20];

			String report_code = (String) a[21];
			String report_name = (String) a[22];
			Date report_date = (Date) a[23];
			Date report_due_date = (Date) a[24];
			Date rep_submit_date = (Date) a[25];
			Date rep_period_from = (Date) a[26];
			Date rep_period_to = (Date) a[27];
		
			String rep_freq = (String) a[28];
			Character nil_report_flg = (Character) a[29];
			Character arch_flg = (Character) a[30];

			T11Reports T11Report = new T11Reports( d4a_cur_cdd_tran_term, d5a_cur_amlcft_tran_term, d6a_cur_total_tran,
					c4b_cur_cdd_tran_term_not_low, c5b_cur_amlcft_tran_term_not_low, c6b_cur_total_not_low,
					c4c_cur_cdd_tran_term_tamt_low, c5c_cur_amlcft_tran_term_tamt_low, c6e_cur_total_tamt_low,
					c4d_cur_cdd_tran_term_not_med, c5d_cur_amlcft_tran_term_not_med, c6d_cur_total_not_med,
					c4e_cur_cdd_tran_term_tamt_med, c5e_cur_amlcft_tran_term_tamt_med, c6e_cur_total_tamt_med,
					c4f_cur_cdd_tran_term_not_hig, c5f_cur_amlcft_tran_term_not_hig, c6f_cur_total_not_hig,
					c4g_cur_cdd_tran_term_tamt_hig, c5g_cur_amlcft_tran_term_tamt_hig, c6g_cur_total_tamt_hig,
					report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from,
					rep_period_to, rep_freq, nil_report_flg, arch_flg);

			T11urrentrep.add(T11Report);

		}
		;

		mv.setViewName("ReportT11");
		mv.addObject("reportsummary", T11urrentrep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;

	}

	public ModelAndView getT11currentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T11urrentDt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			qr = hs.createNativeQuery("select * from T11_CDD_TRAN_TERM_DETAILS a where report_date = ?1");
		} else {
			qr = hs.createNativeQuery("select * from T11_CDD_TRAN_TERM_DETAILS a where report_date = ?1");
		}
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Getting Report Detail for : " + reportId + "," + fromdate + "," + todate + "," + currency);
		List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {

			String cust_id = (String) a[0];
			String cust_name = (String) a[1];
			String cust_type = (String) a[2];
			String cust_rating = (String) a[3];
			String acct_no = (String) a[4];
			String acct_name = (String) a[5];
			String tran_type = (String) a[6];
			String tran_sub_type = (String) a[7];
			Date tran_date = (Date) a[8];
			String tran_id = (String) a[9];
			BigDecimal part_tran_id = (BigDecimal) a[10];
			Character part_tran_type = (Character) a[11];
			String tran_crncy = (String) a[12];
			BigDecimal tran_amt = (BigDecimal) a[13];
			String tran_category = (String) a[14];
			Character qtr_flg = (Character) a[15];
			Character entity_flg = (Character) a[16];
			Character del_flg = (Character) a[17];
			Character modify_flg = (Character) a[18];
			Date entry_date = (Date) a[19];
			Date modify_date = (Date) a[20];
			Date verify_date = (Date) a[21];
			String entry_user = (String) a[22];
			String modify_user = (String) a[23];
			String verify_user = (String) a[24];
			String report_code = (String) a[25];
			Date report_name = (Date) a[26];
			Date report_date = (Date) a[27];
			Character arch_flg = (Character) a[28];
			String cdd = (String) a[29];
			String srl_no = (String) a[30];

			T11Details py = new T11Details(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name, tran_type,
					tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt,
					tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date,
					entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cdd,srl_no);

			T11urrentDt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T11urrentDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T11urrentDt1.size());
			pagedlist = T11urrentDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T11urrentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
				T11urrentDt1.size());

		mv.setViewName("ReportT11 :: reportcontent");
		mv.addObject("reportdetails", T11urrentDt1Page);

		mv.addObject("singledetail", new T11Details());
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

		logger.info("Getting Output file :" + reportId + "today = " + todate);

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
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T11jas/T11.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T11jas/T11.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T11jas/T11.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T11jas/T11.jasper");
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

	public String addt11(T11Details t11Details, String custid) {
		// TODO Auto-generated method stub
		String msg = "";
		/* try { */

		T11Details up = t11Details;
		// up.setReport_date("31-12-2019");
		t11DetailsRep.save(up);
		msg = "Added Successfully";

		return msg;
	}

	@SuppressWarnings("unchecked")
	public String getCustomerDetails(String custid) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct(cust_name) from CUST_MAST_GEN_TABLE where cust_id=?1  ");
		query.setParameter(1, custid);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

}
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

import com.bornfire.entity.TRAN_MASTER_DETAIL_RBS;
import com.bornfire.entity.t1.T1CurProdDetail;
import com.bornfire.entity.t1.T1MasterProdDetail;
import com.bornfire.entity.t15.T15Detail;
import com.bornfire.entity.t3a.T3ADataMaintenance;
import com.bornfire.entity.t3a.T3ADetail;
import com.bornfire.entity.t3a.T3ADetailRepo;
import com.bornfire.entity.t3a.T3AReport;
import com.bornfire.entity.t3a.T3AReportRepo;
import com.bornfire.entity.t5.T5Detail;

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
public class T3AReportService {

	private static final Logger logger = LoggerFactory.getLogger(T3AReportService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	@Autowired
	T3AReportRepo t3aReportRepo;

	@Autowired
	T3ADetailRepo t3aDetailRepo;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	Environment env;

	public ModelAndView getT3aView(String reportId, String fromdate, String todate) {

		ModelAndView mv = new ModelAndView();
		List<T3AReport> T3rep = new ArrayList<T3AReport>();

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			System.out.println("hiii" + strDate1);
			T3rep = t3aReportRepo.reportList(strDate1);

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		mv.setViewName("ReportT3A");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T3rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public ModelAndView getT3aViewB(String reportId, String fromdate, String todate) {

		ModelAndView mv = new ModelAndView();
		List<T3AReport> T3rep = new ArrayList<T3AReport>();

		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			System.out.println("hiii" + strDate1);
			T3rep = t3aReportRepo.reportListB(strDate1);

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		mv.setViewName("ReportT3B");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T3rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public String preCheck(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;
		logger.info("Report precheck : " + reportId);

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dt9 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);
			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery("select count(*) from T3ADetail a where a.report_date=?1")
					.setParameter(1, dt9).getSingleResult();

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

	public String preCheckB(String reportId, String fromdate, String todate) {

		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt9;
		logger.info("Report precheck : " + reportId);

		try {
			dt1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
			dt9 = new SimpleDateFormat("dd/MM/yyyy").parse(todate);
			logger.info("Getting No of records in Master table :" + reportId);
			Long dtlcnt = (Long) hs.createQuery("select count(*) from T3ADetail a where a.report_date=?1")
					.setParameter(1, dt9).getSingleResult();

			if (dtlcnt > 0) {
				msg = "success";
			} else {
				msg = "success";
				// msg = "Data Not available for the Report. Please Contact Administrator";
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			msg = "success";
			e.printStackTrace();

		}

		return msg;

	}

	public ModelAndView getT13Rep(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {
		ModelAndView mv = new ModelAndView();
		List<T3AReport> T3rep = new ArrayList<T3AReport>();
		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			System.out.println("hiii" + strDate1);
			T3rep = t3aReportRepo.reportList(strDate1);

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		// T3rep = t3aReportRepo.reportList(todate);
		mv.setViewName("ReportT3A");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T3rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public ModelAndView getT13RepB(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable) {
		ModelAndView mv = new ModelAndView();
		List<T3AReport> T3rep = new ArrayList<T3AReport>();
		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			System.out.println("hiii" + strDate1);
			T3rep = t3aReportRepo.reportListB(strDate1);

		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		// T3rep = t3aReportRepo.reportList(todate);
		mv.setViewName("ReportT3B");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", T3rep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}
	public ModelAndView getT13Dtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable, String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> T1Dt1 = new ArrayList<Object>();
		Query<Object[]> qr;

		if (dtltype.equals("report")) {
			if (!filter.equals("null")) {
				qr = hs.createNativeQuery(
						"select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T3_REPORT =?2");
				qr.setParameter(2, filter);
			} else {
				qr = hs.createNativeQuery("select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T3_REPORT  is not null");
			}
		} else {
			qr = hs.createNativeQuery("select * from TRAN_MASTER_DETAIL_RBS  where report_date = ?1 and T3_REPORT is not null");
		}
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<T3AReport> T1Master = new ArrayList<T3AReport>();

		try {
			T1Master = hs.createQuery("from T3AReport a where a.report_date = ?1 ", T3AReport.class)
					.setParameter(1, df.parse(todate)).getResultList();
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
			String part_tran_type = (String) a[11];
			String tran_crncy = (String) a[12];
			BigDecimal tran_amt = (BigDecimal) a[13];
			BigDecimal tran_amt_orgin = (BigDecimal) a[14];
			String tran_category = (String) a[15];
			Character qtr_flg = (Character) a[16];
			Character entity_flg = (Character) a[17];
			Character del_flg = (Character) a[18];
			Character modify_flg = (Character) a[19];
			Date entry_date = (Date) a[20];
			Date modify_date = (Date) a[21];
			Date verify_date = (Date) a[22];
			String entry_user = (String) a[23];
			String modify_user = (String) a[24];
			String verify_user = (String) a[25];
			String report_code = (String) a[26];
			String report_name = (String) a[27];
			Date report_date = (Date) a[28];
			Character arch_flg = (Character) a[29];
			String cell_mapping = (String) a[30];
			String process_owner = (String) a[31];
			String bank_id = (String) a[32];
			Date cust_rating_date = (Date) a[33];
			String tran_particulars = (String) a[34];
			String tran_channel = (String) a[35];
			String cntry_res = (String) a[36];
			String cnty_incorp = (String) a[37];
			String cntry_oper = (String) a[38];
			String aml_code_1 = (String) a[39];
			String aml_code_2 = (String) a[40];
			String aml_code_3 = (String) a[41];
			String aml_code_4 = (String) a[42];
			String aml_code_5 = (String) a[43];
			String aml_code_6 = (String) a[44];
			String aml_code_7 = (String) a[45];
			String aml_code_8 = (String) a[46];
			String aml_code_9 = (String) a[47];
			String aml_code_10 = (String) a[48];
			String t1_report = (String) a[49];
			String t2_report = (String) a[50];
			String t3_report = (String) a[51];
			String t4_report = (String) a[52];
			String t5_report = (String) a[53];
			String t6_report = (String) a[54];
			String t7_report = (String) a[55];
			String t8_report = (String) a[56];
			String t9_report = (String) a[57];
			String t10_report = (String) a[58];
			String t11_report = (String) a[59];
			String t12_report = (String) a[60];
			String t13_report = (String) a[61];
			String t14_report = (String) a[62];
			String t15_report = (String) a[63];
			String t16_report = (String) a[64];
			String t17_report = (String) a[65];
			String t18_report = (String) a[66];
			String t19_report = (String) a[67];
			String t20_report = (String) a[68];
			String t21_report = (String) a[69];
			String t22_report = (String) a[70];
			String t23_report = (String) a[71];
			String t24_report = (String) a[72];
			String t25_report = (String) a[73];
			String t26_report = (String) a[74];
			String t27_report = (String) a[75];
			String t28_report = (String) a[76];
			String t29_report = (String) a[77];
			BigDecimal srl_num = (BigDecimal) a[78];


			TRAN_MASTER_DETAIL_RBS py = new TRAN_MASTER_DETAIL_RBS(cust_id, cust_name, cust_type, cust_rating, acct_no, acct_name,
					tran_type, tran_sub_type, tran_date, tran_id, part_tran_id, part_tran_type, tran_crncy, tran_amt,tran_amt_orgin,
					tran_category, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date,
					entry_user, modify_user, verify_user, report_code, report_name, report_date, arch_flg, cell_mapping,
					process_owner, bank_id, cust_rating_date, tran_particulars, tran_channel, cntry_res, cnty_incorp,
					cntry_oper, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7,
					aml_code_8, aml_code_9, aml_code_10, t1_report, t2_report, t3_report,t4_report,t5_report,t6_report,t7_report,t8_report,t9_report,t10_report,
					 t11_report, t12_report, t13_report,t14_report,t15_report,t16_report,t17_report,t18_report,t19_report,t20_report, 
					 t21_report, t22_report, t23_report,t24_report,t25_report,t26_report,t27_report,t28_report,t29_report,srl_num);

			T1Dt1.add(py);

		}
		;

		List<Object> pagedlist;

		if (T1Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, T1Dt1.size());
			pagedlist = T1Dt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> T1Dt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize), T1Dt1.size());

		mv.setViewName("ReportT3A :: reportcontent");
		// mv.setViewName("ReportT1");
		mv.addObject("reportdetails", T1Dt1Page);
		mv.addObject("reportmaster", T1Master);
		mv.addObject("singledetail", new TRAN_MASTER_DETAIL_RBS());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}
	/*public ModelAndView getT13Dtl(String reportId, String fromdate, String todate, String currency, String dtltype,
			Pageable pageable, String filter) {
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;

		ModelAndView mv = new ModelAndView();

		mv.setViewName("ReportT3A :: reportcontent");
		try {
			if (!filter.equals("null")) {
				mv.addObject("reportdetails", t3aDetailRepo.detailList(df.parse(todate), filter, pageable));
			} else {
				mv.addObject("reportdetails", t3aDetailRepo.detailList1(df.parse(todate), pageable));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		mv.addObject("singledetail", new T3ADetail());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}*/

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
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			fileName = reportId + "_" + strDate1;

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
								.getResourceAsStream("/static/jasper/Details/NEW_AML_DETAILS/T3Detail.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T3A/T3A.jasper");
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
					System.out.println("todya" + today);
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

	public File getFile3(String reportId, String fromdate, String todate, String currency, String dtltype,
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
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate1 = formatter1.format(ConDate);
			fileName = reportId + "_" + strDate1;

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
								.getResourceAsStream("/static/jasper/Details/T3Detail/T3Detail.jasper");
					}

				} else {
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T3B/T3B.jasper");
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
					System.out.println("todya" + today);
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

	public Page<T3ADataMaintenance> parameterlistwithdecode(String rpt_date, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T3ADataMaintenance> t9Dt1 = new ArrayList<T3ADataMaintenance>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from T3_RBS_MASTER where report_date=?1");
		qr.setParameter(1, rpt_date);
		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {

				String foracid = (String) a[0];
				String tran_id = (String) a[1];
				String part_tran_id = (String) a[2];
				String part_tran_type = (String) a[3];
				String schm_code = (String) a[4];
				String acct_name = (String) a[5];
				Date acct_opn_date = (Date) a[6];
				String risk_rating_code = (String) a[7];
				String risk_rating = (String) a[8];
				Date tran_date = (Date) a[9];
				String tran_particular = (String) a[10];
				BigDecimal debit_amount = (BigDecimal) a[11];
				BigDecimal credit_amount = (BigDecimal) a[12];
				String resident_status = (String) a[13];
				Date acct_cls_date = (Date) a[14];
				String country_code = (String) a[15];
				String address_1 = (String) a[16];
				String address_2 = (String) a[17];
				String address_3 = (String) a[18];
				String sector_code = (String) a[19];
				String transfer_type = (String) a[20];
				Date report_date = (Date) a[21];
				String process_owner = (String) a[22];

				T3ADataMaintenance t3ADataMaintenance = new T3ADataMaintenance(foracid, tran_id, part_tran_id,
						part_tran_type, schm_code, acct_name, acct_opn_date, risk_rating_code, risk_rating, tran_date,
						tran_particular, debit_amount, credit_amount, resident_status, acct_cls_date, country_code,
						address_1, address_2, address_3, sector_code, transfer_type, report_date, process_owner);

				t9Dt1.add(t3ADataMaintenance);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T3ADataMaintenance> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T3ADataMaintenance> t9Dt1Page = new PageImpl<T3ADataMaintenance>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}

	
	public Page<T3ADataMaintenance> searchT3ABoth(String rpt_date, String tran_date1, String P_O, String tran_id1,BigDecimal part_tran_id1, Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T3ADataMaintenance> t9Dt1 = new ArrayList<T3ADataMaintenance>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T3_RBS_MASTER where report_date=?1 and tran_date=?2 and process_owner=?3 and trim(tran_id)=?4 and trim(part_tran_id)=?5");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		qr.setParameter(3, P_O);
		qr.setParameter(4, tran_id1);
		qr.setParameter(5, part_tran_id1);
		

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String foracid = (String) a[0];
				String tran_id = (String) a[1];
				String part_tran_id = (String) a[2];
				String part_tran_type = (String) a[3];
				String schm_code = (String) a[4];
				String acct_name = (String) a[5];
				Date acct_opn_date = (Date) a[6];
				String risk_rating_code = (String) a[7];
				String risk_rating = (String) a[8];
				Date tran_date = (Date) a[9];
				String tran_particular = (String) a[10];
				BigDecimal debit_amount = (BigDecimal) a[11];
				BigDecimal credit_amount = (BigDecimal) a[12];
				String resident_status = (String) a[13];
				Date acct_cls_date = (Date) a[14];
				String country_code = (String) a[15];
				String address_1 = (String) a[16];
				String address_2 = (String) a[17];
				String address_3 = (String) a[18];
				String sector_code = (String) a[19];
				String transfer_type = (String) a[20];
				Date report_date = (Date) a[21];
				String process_owner = (String) a[22];

				T3ADataMaintenance t3ADataMaintenance = new T3ADataMaintenance(foracid, tran_id, part_tran_id,
						part_tran_type, schm_code, acct_name, acct_opn_date, risk_rating_code, risk_rating, tran_date,
						tran_particular, debit_amount, credit_amount, resident_status, acct_cls_date, country_code,
						address_1, address_2, address_3, sector_code, transfer_type, report_date, process_owner);
				t9Dt1.add(t3ADataMaintenance);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T3ADataMaintenance> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T3ADataMaintenance> t9Dt1Page = new PageImpl<T3ADataMaintenance>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	public Page<T3ADataMaintenance> searchT3APO(String rpt_date, String P_O, Pageable pageable) throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T3ADataMaintenance> t9Dt1 = new ArrayList<T3ADataMaintenance>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T3_RBS_MASTER where report_date=?1 and process_owner=?2");
		qr.setParameter(1, rpt_date);

		qr.setParameter(2, P_O);

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String foracid = (String) a[0];
				String tran_id = (String) a[1];
				String part_tran_id = (String) a[2];
				String part_tran_type = (String) a[3];
				String schm_code = (String) a[4];
				String acct_name = (String) a[5];
				Date acct_opn_date = (Date) a[6];
				String risk_rating_code = (String) a[7];
				String risk_rating = (String) a[8];
				Date tran_date = (Date) a[9];
				String tran_particular = (String) a[10];
				BigDecimal debit_amount = (BigDecimal) a[11];
				BigDecimal credit_amount = (BigDecimal) a[12];
				String resident_status = (String) a[13];
				Date acct_cls_date = (Date) a[14];
				String country_code = (String) a[15];
				String address_1 = (String) a[16];
				String address_2 = (String) a[17];
				String address_3 = (String) a[18];
				String sector_code = (String) a[19];
				String transfer_type = (String) a[20];
				Date report_date = (Date) a[21];
				String process_owner = (String) a[22];

				T3ADataMaintenance t3ADataMaintenance = new T3ADataMaintenance(foracid, tran_id, part_tran_id,
						part_tran_type, schm_code, acct_name, acct_opn_date, risk_rating_code, risk_rating, tran_date,
						tran_particular, debit_amount, credit_amount, resident_status, acct_cls_date, country_code,
						address_1, address_2, address_3, sector_code, transfer_type, report_date, process_owner);
				t9Dt1.add(t3ADataMaintenance);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T3ADataMaintenance> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T3ADataMaintenance> t9Dt1Page = new PageImpl<T3ADataMaintenance>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	public Page<T3ADataMaintenance> searchT3ADate(String rpt_date, String tran_date1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T3ADataMaintenance> t9Dt1 = new ArrayList<T3ADataMaintenance>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T3_RBS_MASTER where report_date=?1 and tran_date=?2 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String foracid = (String) a[0];
				String tran_id = (String) a[1];
				String part_tran_id = (String) a[2];
				String part_tran_type = (String) a[3];
				String schm_code = (String) a[4];
				String acct_name = (String) a[5];
				Date acct_opn_date = (Date) a[6];
				String risk_rating_code = (String) a[7];
				String risk_rating = (String) a[8];
				Date tran_date = (Date) a[9];
				String tran_particular = (String) a[10];
				BigDecimal debit_amount = (BigDecimal) a[11];
				BigDecimal credit_amount = (BigDecimal) a[12];
				String resident_status = (String) a[13];
				Date acct_cls_date = (Date) a[14];
				String country_code = (String) a[15];
				String address_1 = (String) a[16];
				String address_2 = (String) a[17];
				String address_3 = (String) a[18];
				String sector_code = (String) a[19];
				String transfer_type = (String) a[20];
				Date report_date = (Date) a[21];
				String process_owner = (String) a[22];

				T3ADataMaintenance t3ADataMaintenance = new T3ADataMaintenance(foracid, tran_id, part_tran_id,
						part_tran_type, schm_code, acct_name, acct_opn_date, risk_rating_code, risk_rating, tran_date,
						tran_particular, debit_amount, credit_amount, resident_status, acct_cls_date, country_code,
						address_1, address_2, address_3, sector_code, transfer_type, report_date, process_owner);
				t9Dt1.add(t3ADataMaintenance);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T3ADataMaintenance> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T3ADataMaintenance> t9Dt1Page = new PageImpl<T3ADataMaintenance>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}
	
	
	
	
	
	
	public Page<T3ADataMaintenance> searchT3ASingleTran(String rpt_date, String tran_date1,String tran_id1, BigDecimal part_tran_id1,  Pageable pageable)
			throws ParseException {
		System.out.println("rpt_date" + rpt_date);
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<T3ADataMaintenance> t9Dt1 = new ArrayList<T3ADataMaintenance>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery(
				"select * from T3_RBS_MASTER where report_date=?1 and tran_date=?2 and trim(tran_id)=?3 and trim(part_tran_id)=?4 ");
		qr.setParameter(1, rpt_date);
		qr.setParameter(2, tran_date1);
		qr.setParameter(3, tran_id1);
		qr.setParameter(4, part_tran_id1);
		

		List<Object[]> result = qr.getResultList();

		try {
			for (Object[] a : result) {
				String foracid = (String) a[0];
				String tran_id = (String) a[1];
				String part_tran_id = (String) a[2];
				String part_tran_type = (String) a[3];
				String schm_code = (String) a[4];
				String acct_name = (String) a[5];
				Date acct_opn_date = (Date) a[6];
				String risk_rating_code = (String) a[7];
				String risk_rating = (String) a[8];
				Date tran_date = (Date) a[9];
				String tran_particular = (String) a[10];
				BigDecimal debit_amount = (BigDecimal) a[11];
				BigDecimal credit_amount = (BigDecimal) a[12];
				String resident_status = (String) a[13];
				Date acct_cls_date = (Date) a[14];
				String country_code = (String) a[15];
				String address_1 = (String) a[16];
				String address_2 = (String) a[17];
				String address_3 = (String) a[18];
				String sector_code = (String) a[19];
				String transfer_type = (String) a[20];
				Date report_date = (Date) a[21];
				String process_owner = (String) a[22];

				T3ADataMaintenance t3ADataMaintenance = new T3ADataMaintenance(foracid, tran_id, part_tran_id,
						part_tran_type, schm_code, acct_name, acct_opn_date, risk_rating_code, risk_rating, tran_date,
						tran_particular, debit_amount, credit_amount, resident_status, acct_cls_date, country_code,
						address_1, address_2, address_3, sector_code, transfer_type, report_date, process_owner);
				t9Dt1.add(t3ADataMaintenance);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		List<T3ADataMaintenance> pagedlist;

		if (t9Dt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
			pagedlist = t9Dt1.subList(startItem, toIndex);
		}
		Page<T3ADataMaintenance> t9Dt1Page = new PageImpl<T3ADataMaintenance>(pagedlist, PageRequest.of(Page, pageSize),
				t9Dt1.size());

		return t9Dt1Page;

	}


	
}

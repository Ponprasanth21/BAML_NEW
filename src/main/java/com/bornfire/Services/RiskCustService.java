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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.bornfire.entity.riskcust.RiskDetails;
import com.bornfire.entity.riskcust.RiskSuma;

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
public class RiskCustService {

	private static final Logger logger = LoggerFactory.getLogger(RiskCustService.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	
	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public ModelAndView getRiskCustView(String reportId, String fromdate, String todate) {

		logger.info("RiskCustReportService -> getRiskCustView()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> RiskCustRep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from RISK_OF_CUST_SUM where REPORT_DATE = ?1");
		
		try {
			qr.setParameter(1, df.parse(todate));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

/*		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {

			String risk_cat = (String) a[0];
			String num_of_cust = (String) a[1];
			Date total_balance_sd = (Date) a[2];
			Date total_balance_ed = (Date) a[3];
			String balance_review = (String) a[4];
			String report_code = (String) a[5];
			String report_name = (String) a[6];
			Date report_date = (Date) a[7];
			Date report_due_date = (Date) a[8];
			Date rep_submit_date = (Date) a[9];
			Date rep_period_from = (Date) a[10];
			Date rep_period_to = (Date) a[11];
			String rep_freq = (String) a[12];
			String nil_report_flg = (String) a[13];
			String arch_flg = (String) a[14];



			RiskSuma RiskCustReport = new RiskSuma(risk_cat, num_of_cust, total_balance_sd, total_balance_ed, balance_review, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);

			RiskCustRep.add(RiskCustReport);
		}

*/		mv.setViewName("ReportRiskCust");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", RiskCustRep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	public String preCheck(String reportid, String fromdate, String todate) {
		String msg = "";
		Session hs = sessionFactory.getCurrentSession();
		Date dt1;
		Date dt2;

		Query query = null;
		
		query = hs.createNativeQuery("select count(*) from RISK_OF_CUST_SUM where report_date = ?1 ");
				try {
					query.setParameter(1, df.parse(todate));
					
				} catch (ParseException e) {
					e.printStackTrace();
				}

		
		/*try {
		qr.setParameter(1, df.parse(todate));
	} catch (ParseException e) {
		e.printStackTrace();
	}*/

		BigDecimal count = (BigDecimal) query.getSingleResult();

		int value = count.intValue();
		if (value > 0) {
			msg = "success";
		} else {
			msg = "Data Not available for the Report. Please Contact Administrator";
		}

		return msg;

	}

	public ModelAndView getRiskCustRep(String reportId, String fromdate, String todate) {

		logger.info("RiskCustReportService -> getRiskCustRep()");

		// declaration of variable
		ModelAndView mv = new ModelAndView();
		Session hs = sessionFactory.getCurrentSession();
		List<Object> RiskCustRep = new ArrayList<Object>();
		Query<Object[]> qr;

		qr = hs.createNativeQuery("select * from RISK_OF_CUST_SUM where REPORT_DATE = ?1");
		try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

/*		List<Object[]> results = qr.getResultList();

		for (Object[] a : results) {
			String risk_cat = (String) a[0];
			String num_of_cust = (String) a[1];
			Date total_balance_sd = (Date) a[2];
			Date total_balance_ed = (Date) a[3];
			String balance_review = (String) a[4];
			String report_code = (String) a[5];
			String report_name = (String) a[6];
			Date report_date = (Date) a[7];
			Date report_due_date = (Date) a[8];
			Date rep_submit_date = (Date) a[9];
			Date rep_period_from = (Date) a[10];
			Date rep_period_to = (Date) a[11];
			String rep_freq = (String) a[12];
			String nil_report_flg = (String) a[13];
			String arch_flg = (String) a[14];

			RiskSuma RiskCustReport = new RiskSuma(risk_cat, num_of_cust, total_balance_sd, total_balance_ed, balance_review, report_code, report_name, report_date, report_due_date, rep_submit_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg, arch_flg);

			RiskCustRep.add(RiskCustReport);
		}

*/		mv.setViewName("ReportRiskCust");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", RiskCustRep);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		logger.info("returning model view");
		return mv;
	}

	public ModelAndView getRiskCustcurrentDtl(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable,String filter) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();
		List<Object> RiskCustDt1 = new ArrayList<Object>();
		Query<Object[]> qr;
System.out.println("TEST"+filter);
		if (dtltype.equals("report")) {
			if(!filter.equals("null")) {
				qr = hs.createNativeQuery("select * from RISK_OF_CUST a  ");
				qr.setParameter(2,filter);

			}else {
				qr = hs.createNativeQuery("select * from RISK_OF_CUST a ");

			}
		} else {
			qr = hs.createNativeQuery("select * from RISK_OF_CUST a ");
		}
		/*try {
			qr.setParameter(1, df.parse(todate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
*/
		/*List<Object[]> result = qr.getResultList();

		for (Object[] a : result) {
			String customer = (String) a[0];
			String cif = (String) a[1];
			String occupation = (String) a[2];
			String pep = (String) a[3];
			String hnwi = (String) a[4];
			String risk_cat = (String) a[5];
			String income = (String) a[6];
			String source = (String) a[7];
			String transaction = (String) a[8];
			String deposite_type = (String) a[9];
			Date total_balance_sd = (Date) a[10];
			Date total_balance_ed = (Date) a[11];
			String balance_review = (String) a[12];
			String mode_of_payment = (String) a[13];
			String qtr_flg = (String) a[14];
			String entity_flg = (String) a[15];
			String del_flg = (String) a[16];
			String modify_flg = (String) a[17];
			Date entry_date = (Date) a[18];
			Date modify_date = (Date) a[19];
			Date verify_date = (Date) a[20];
			String entry_user = (String) a[21];
			String modify_user = (String) a[22];
			String verify_user = (String) a[23];
			String report_code = (String) a[24];
			String report_name = (String) a[25];
			String arch_flg = (String) a[26];


			RiskDetails RiskCustDetail = new RiskDetails(customer, cif, occupation, pep, hnwi, risk_cat, income, source, transaction, deposite_type, total_balance_sd, total_balance_ed, balance_review, mode_of_payment, qtr_flg, entity_flg, del_flg, modify_flg, entry_date, modify_date, verify_date, entry_user, modify_user, verify_user, report_code, report_name, arch_flg);

			RiskCustDt1.add(RiskCustDetail);
		}
		;
*/
		List<Object> pagedlist;

		if (RiskCustDt1.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, RiskCustDt1.size());
			pagedlist = RiskCustDt1.subList(startItem, toIndex);
		}

		logger.info("Converting to Page");
		Page<Object> RiskCustDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize), RiskCustDt1.size());

		mv.setViewName("ReportRiskCust :: reportcontent");
		mv.addObject("reportdetails", RiskCustDt1Page);
		mv.addObject("singledetail", new RiskDetails());
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		return mv;
	}

	public File getFile(String userid,String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		 
		 
		 Date ConDateFromdate = null;
		try {
			ConDateFromdate = dateFormat1.parse(fromdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(ConDateFromdate);
		
		 String strDate2 = formatter1.format(ConDateFromdate);
		 try {
			fromdate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 Date ConToDate = null;
		try {
			ConToDate = dateFormat1.parse(todate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(ConToDate);
		   
		 String strDate1 = formatter1.format(ConToDate);
		 try {
			todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String path = "";
		String fileName = "";
		
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		

		if (!filetype.equals("xbrl")) {
			try {
			
				InputStream fileStream = null;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("xlsx")) {
					System.out.println("xlsx");
				    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/CUSTREVIEWREPORT/REVIEWSUMMARY.jasper");
//					jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
				}else {
					System.out.println("pdf");
				    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/CUSTREVIEWREPORT/REVIEWSUMMARY.jasper");
//					jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("TODATE", todate);
				map.put("FROMDATE", fromdate);

//				File folders = new File(path);
////				boolean folders = (new File("TEST")).mkdir();
//				if (!folders.exists()) {
//					folders.mkdirs();
//				}
				logger.info("BEFORE GENERATING PDF :" + reportId);
				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path =  fileName;
					logger.info("BEFORE GENERATING PDF 1 :" + reportId);
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					logger.info("BEFORE GENERATING PDF 2 :" + path);
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					fileName = fileName + ".xlsx";
					path =   fileName;
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
	public File getFiledetail(String userid,String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		 
		 
		 Date ConDateFromdate = null;
		try {
			ConDateFromdate = dateFormat1.parse(fromdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(ConDateFromdate);
		
		 String strDate2 = formatter1.format(ConDateFromdate);
		 try {
			fromdate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate2));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 Date ConToDate = null;
		try {
			ConToDate = dateFormat1.parse(todate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(ConToDate);
		   
		 String strDate1 = formatter1.format(ConToDate);
		 try {
			todate = formatter1.format(new SimpleDateFormat("dd-MMM-yyyy").parse(strDate1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String path = "";
		String fileName = "";
		
		File outputFile;

		logger.info("Getting Output file :" + reportId);

		fileName = reportId + "_" + dateFormat.format(new Date());

		

		if (!filetype.equals("xbrl")) {
			try {
			
				InputStream fileStream = null;
				logger.info("Getting Jasper file :" + reportId);
				if (filetype.equals("xlsx")) {
					System.out.println("xlsx");
				    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/CUSTREVIEWREPORT/REVIEWDETAIL.jasper");
//					jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
				}else {
					System.out.println("pdf");
				    fileStream = this.getClass().getResourceAsStream("/static/jasper/AMLReports/CUSTREVIEWREPORT/REVIEWDETAIL.jasper");
//					jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("TODATE", todate);
				map.put("FROMDATE", fromdate);

//				File folders = new File(path);
////				boolean folders = (new File("TEST")).mkdir();
//				if (!folders.exists()) {
//					folders.mkdirs();
//				}
				logger.info("BEFORE GENERATING PDF :" + reportId);
				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path =  fileName;
					logger.info("BEFORE GENERATING PDF 1 :" + reportId);
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					logger.info("BEFORE GENERATING PDF 2 :" + path);
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					fileName = fileName + ".xlsx";
					path =   fileName;
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

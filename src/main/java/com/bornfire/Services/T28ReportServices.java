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

import com.bornfire.entity.t28.T28ReportRepository;
import com.bornfire.entity.t28.T28Reports;

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
public class T28ReportServices {
	private static final Logger logger = LoggerFactory.getLogger(T28ReportServices.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	T28ReportRepository t28ReportRepository;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ReferenceCodeConfigure refCodeConfig;

	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	Environment env;

	// summary starts
	public ModelAndView getT28currentView(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<T28Reports> t28reportlist = new ArrayList<>();
		try {
			t28reportlist = t28ReportRepository.gett28Report(df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		mv.setViewName("ReportsT28");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t28reportlist);
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
			Query query = null;

			query = hs.createNativeQuery("select count(*) from T28_AML_CFT_INF_TABLE a where a.report_date=?1 ");
			System.out.println(todate);

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
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();

		}
		return msg;

	}

	
	
	
	public ModelAndView getT28currentRep(String reportId, String fromdate, String todate, String currency,
			String dtltype, Pageable pageable) {

		ModelAndView mv = new ModelAndView();

		Session hs = sessionFactory.getCurrentSession();

		List<T28Reports> t28reportlist = new ArrayList<>();
		try {
			t28reportlist = t28ReportRepository.gett28Report(df.parse(todate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		mv.setViewName("ReportsT28");
		// mv.addObject("currlist", refCodeConfig.currList());
		mv.addObject("reportsummary", t28reportlist);
		mv.addObject("reportsflag", "reportsflag");
		mv.addObject("menu", reportId);
		mv.addObject("displaymode", "summary");

		System.out.println("scv" + mv.getViewName());

		return mv;

	}

	/*
	 * public ModelAndView getT23currentDtl(String reportId, String fromdate, String
	 * todate, String currency, String dtltype, Pageable pageable) {
	 * 
	 * int pageSize = pageable.getPageSize(); int currentPage =
	 * pageable.getPageNumber(); int startItem = currentPage * pageSize;
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * Session hs = sessionFactory.getCurrentSession(); List<Object> T23urrentDt1 =
	 * new ArrayList<Object>(); Query<Object[]> qr;
	 * 
	 * if (dtltype.equals("report")) { qr = hs.
	 * createNativeQuery("select * from T23_AML_CFT_REVIEWS_DETAIL a where report_date = ?1"
	 * ); } else { qr = hs.
	 * createNativeQuery("select * from T23_AML_CFT_REVIEWS_DETAIL a where report_date = ?1"
	 * ); }
	 * 
	 * try { qr.setParameter(1, df.parse(todate));
	 * 
	 * } catch (ParseException e) { e.printStackTrace(); }
	 * 
	 * 
	 * logger.info("Getting Report Detail for : " + reportId + "," + fromdate + ","
	 * + todate + "," + currency); List<Object[]> result = qr.getResultList();
	 * 
	 * for (Object[] a : result) {
	 * 
	 * String primary_sol_id = (String) a[0]; String cust_id = (String) a[1]; String
	 * cust_name = (String) a[2]; String sol_id = (String) a[3]; Date cust_opn_date
	 * = (Date) a[4]; Date cust_susp_date = (Date) a[5]; Date cust_kyc_date = (Date)
	 * a[6]; Date cust_kyc_due_date = (Date) a[7]; String cust_rating_code =
	 * (String) a[8]; Date cust_rating_date = (Date) a[9]; Date cust_rating_due_date
	 * = (Date) a[10]; String gl_sub_head_code = (String) a[11]; String sch_code =
	 * (String) a[12]; String foracid = (String) a[13]; String acid = (String)
	 * a[14]; Date acct_opn_date = (Date) a[15]; Character acct_cls_flg =
	 * (Character) a[16]; Date acct_cls_date = (Date) a[17]; String acct_status =
	 * (String) a[18]; Date acct_status_date = (Date) a[19]; Character entity_flg =
	 * (Character) a[20]; Character del_flg = (Character) a[21]; Character
	 * modify_flg = (Character) a[22]; Date entry_date = (Date) a[23]; Date
	 * modify_date = (Date) a[24]; Date verify_date = (Date) a[25]; String
	 * entry_user = (String) a[26]; String modify_user = (String) a[27]; String
	 * verify_user = (String) a[28]; String report_code = (String) a[29]; String
	 * report_name = (String) a[30]; Date report_date = (Date) a[31]; Character
	 * arch_flg = (Character) a[32];
	 * 
	 * T28Detail t28Detail = new T28Detail(primary_sol_id, cust_id, cust_name,
	 * sol_id, cust_opn_date, cust_susp_date, cust_kyc_date, cust_kyc_due_date,
	 * cust_rating_code, cust_rating_date, cust_rating_due_date, gl_sub_head_code,
	 * sch_code, foracid, acid, acct_opn_date, acct_cls_flg, acct_cls_date,
	 * acct_status, acct_status_date, entity_flg, del_flg, modify_flg, entry_date,
	 * modify_date, verify_date, entry_user, modify_user, verify_user, report_code,
	 * report_name, report_date, arch_flg);
	 * 
	 * 
	 * T28urrentDt1.add(t28Detail);
	 * 
	 * } ;
	 * 
	 * List<Object> pagedlist;
	 * 
	 * if (T28urrentDt1.size() < startItem) { pagedlist = Collections.emptyList(); }
	 * else { int toIndex = Math.min(startItem + pageSize, T28urrentDt1.size());
	 * pagedlist = T28urrentDt1.subList(startItem, toIndex); }
	 * 
	 * logger.info("Converting to Page"); Page<Object> T28urrentDt1Page = new
	 * PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
	 * T28urrentDt1.size());
	 * 
	 * mv.setViewName("ReportT28 :: reportcontent"); mv.addObject("reportdetails",
	 * T28urrentDt1Page);
	 * 
	 * mv.addObject("singledetail", new T22Details()); mv.addObject("reportsflag",
	 * "reportsflag"); mv.addObject("menu", reportId); return mv; }
	 */
	/*
	 * public File getFile(String reportId, String fromdate, String todate, String
	 * dtltype, String filetype) throws FileNotFoundException, JRException,
	 * SQLException { String path = ""; String fileName = ""; String zipFileName =
	 * ""; File outputFile; DateFormat dateFormat = new
	 * SimpleDateFormat("dd-MMM-yyyy");
	 * 
	 * logger.info("Getting Output file :" + reportId);
	 * 
	 * try { SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy"); Date
	 * ConDate = dateFormat1.parse(todate); System.out.println(ConDate);
	 * SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy"); String
	 * strDate1 = formatter1.format(ConDate); fileName = "t" + reportId + "_" +
	 * strDate1; } catch (ParseException e1) {
	 * 
	 * logger.info(e1.getMessage()); e1.printStackTrace(); }
	 * 
	 * zipFileName = fileName + ".zip";
	 * 
	 * if (!filetype.equals("xbrl")) {
	 * 
	 * try { InputStream jasperFile; logger.info("Getting Jasper file :" +
	 * reportId); if (filetype.equals("detailexcel")) { if
	 * (dtltype.equals("report")) { jasperFile =
	 * this.getClass().getResourceAsStream(
	 * "/static/jasper/AmlJasper/T28Copy/T28.jasper"); } else { jasperFile =
	 * this.getClass().getResourceAsStream(
	 * "/static/jasper/AmlJasper/T28Copy/T28.jasper"); }
	 * 
	 * } else { if (dtltype.equals("report")) { logger.info("Inside report");
	 * jasperFile = this.getClass().getResourceAsStream(
	 * "/static/jasper/AmlJasper/T28Copy/T28.jasper"); } else {
	 * logger.info("Inside archive"); jasperFile =
	 * this.getClass().getResourceAsStream(
	 * "/static/jasper/AmlJasper/T28Copy/T28.jasper"); } }
	 * 
	 * JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
	 * HashMap<String, Object> map = new HashMap<String, Object>();
	 * 
	 * logger.info("Assigning Parameters for Jasper"); try { SimpleDateFormat
	 * dateFormat1 = new SimpleDateFormat("dd/MM/yyyy"); Date ConDate =
	 * dateFormat1.parse(todate); System.out.println(ConDate); SimpleDateFormat
	 * formatter1 = new SimpleDateFormat("dd-MMM-yyyy"); String strDate1 =
	 * formatter1.format(ConDate);
	 * 
	 * String today = dateFormat.format(new
	 * SimpleDateFormat("dd-MMM-yyyy").parse(strDate1)); map.put("REPORT_DATE",
	 * today); } catch (ParseException e1) {
	 * 
	 * logger.info(e1.getMessage()); e1.printStackTrace(); }
	 * 
	 * if (filetype.equals("pdf")) { fileName = fileName + ".pdf"; path = fileName;
	 * JasperPrint jp = JasperFillManager.fillReport(jr, map,
	 * srcdataSource.getConnection()); JasperExportManager.exportReportToPdfFile(jp,
	 * path); logger.info("PDF File exported"); } else {
	 * 
	 * fileName = fileName + ".xlsx"; path = fileName; JasperPrint jp =
	 * JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	 * JRXlsxExporter exporter = new JRXlsxExporter(); exporter.setExporterInput(new
	 * SimpleExporterInput(jp)); exporter.setExporterOutput(new
	 * SimpleOutputStreamExporterOutput(path)); exporter.exportReport();
	 * logger.info("Excel File exported"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } outputFile = new File(path);
	 * 
	 * return outputFile;
	 * 
	 * }
	 */

	public File getFile(String reportId, String fromdate, String todate, String currency, String dtltype,
			String filetype) throws FileNotFoundException, JRException, SQLException {
		String path = this.env.getProperty("output.exportpath");
		String fileName = "";
		File outputFile;
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		logger.info("Getting Output file :" + reportId);
		String strDate1 = null;
		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			Date ConDate = dateFormat1.parse(todate);
			System.out.println(ConDate);
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			 strDate1 = formatter1.format(ConDate);
			fileName = "t" + reportId + "_" + strDate1;
		} catch (ParseException e1) {

			logger.info(e1.getMessage());
			e1.printStackTrace();
		}


		if (!filetype.equals("xbrl")) {

			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportId);
			
					if (dtltype.equals("report")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T28OUT/T28.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T28OUT/T28.jasper");
					}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				map.put("REPORT_DATE", strDate1);

				logger.info("BEFORE GENERATING PDF :" + reportId);
				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path +=  fileName;
					logger.info("BEFORE GENERATING PDF 1 :" + reportId);
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					logger.info("BEFORE GENERATING PDF 2 :" + path);
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
				logger.info("ERROR "+e.getLocalizedMessage());

				e.printStackTrace();
			}

		}
		outputFile = new File(path);
		
//		 File myObj = new File(fileName); 
//		    if (myObj.delete()) { 
//		      System.out.println("Deleted the file: " + myObj.getName());
//		    } else {
//		      System.out.println("Failed to delete the file.");
//		    } 

		return outputFile;

	}

	public String editT28(T28Reports t28Reports) {
		// TODO Auto-generated method stub
		String msg = "";

		T28Reports up = t28Reports;
		System.out.println("Services" + up);
		up.setReport_code("T28");
		up.setEntity_flg("N");
		up.setModify_flg("Y");
		// up.setDel_flg("");
		t28ReportRepository.save(up);

		msg = "Record Edited Successfully ";

		return msg;
	}

	public String verifyT28(T28Reports t28reports) {
		// TODO Auto-generated method stub
		String msg = "";

		T28Reports up = t28reports;
		System.out.println("SERMOD" + up.getModify_user());
		System.out.println("SERVER" + up.getVerify_user());
		if (up.getModify_user().equals(up.getVerify_user())) {
			msg = "Same User Cannot Verify !";
		} else {
			up.setReport_code("T28");
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			t28ReportRepository.save(up);

			msg = "Record Verified Successfully";

		}

		return msg;
	}

}

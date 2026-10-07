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

import com.bornfire.entity.t4.T4ReportDetail;
import com.bornfire.entity.t4.T4ReportSum;

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
	public class T4ReportServices {

		private static final Logger logger = LoggerFactory.getLogger(T4ReportServices.class);

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
		public ModelAndView getT4currentView(String reportId, String fromdate, String todate, String currency,
				String dtltype, Pageable pageable) {
	       
			ModelAndView mv = new ModelAndView();

			Session hs = sessionFactory.getCurrentSession();
			int pageSize = pageable.getPageSize();
			int currentPage = pageable.getPageNumber();
			int startItem = currentPage * pageSize;
			List<Object> T4currentrep = new ArrayList<Object>();
			Query<Object[]> qr;
			
			

			logger.info("Inside archive");
			qr = hs.createNativeQuery(
					"select * from T4_CUSTOMER_PROFILING_SUMMARY_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2 ORDER BY instance_code");
			try {
				qr.setParameter(1, df.parse(fromdate));
				qr.setParameter(2, df.parse(todate));

			} catch (ParseException e) {
				e.printStackTrace();
			}

			List<Object[]> result = qr.getResultList();
			for (Object[] a : result) {
				BigDecimal instance_code = (BigDecimal) a[0];
				String instance_name = (String) a[1];
				BigDecimal low_risk_face_to_face = (BigDecimal) a[2];
				BigDecimal low_risk_non_face_to_face = (BigDecimal) a[3];
				BigDecimal med_risk_face_to_face = (BigDecimal) a[4];
				BigDecimal med_risk_non_face_to_face = (BigDecimal) a[5];
				BigDecimal high_risk_face_to_face = (BigDecimal) a[6];
				BigDecimal high_risk_non_face_to_face = (BigDecimal) a[7];
				BigDecimal rejected_risk_face_to_face = (BigDecimal) a[8];
				BigDecimal rejected_risk_non_face_to_face = (BigDecimal) a[9];
				Date report_date = (Date) a[10];
				Date rep_period_from = (Date) a[11];
				Date rep_period_to = (Date) a[12];
				String rep_freq = (String) a[13];
				String nil_report_flg = (String) a[14];


		
				T4ReportSum t4ReportSum = new T4ReportSum(instance_code, instance_name, low_risk_face_to_face, low_risk_non_face_to_face, med_risk_face_to_face, med_risk_non_face_to_face, high_risk_face_to_face, high_risk_non_face_to_face, rejected_risk_face_to_face, rejected_risk_non_face_to_face, report_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg);
				T4currentrep.add(t4ReportSum);

			}
			;

			
			  List<Object> pagedlist;
			 
			if (T4currentrep.size() < startItem) {
				pagedlist = Collections.emptyList(); 
				}
			 else {
				 int toIndex = Math.min(startItem + pageSize, T4currentrep.size());
			  pagedlist = T4currentrep.subList(startItem, toIndex);
			  }
			  logger.info("Converting to Page"); 
			  Page<Object> T4currentrepPage = new
			  PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
					  T4currentrep.size());
			 

			mv.setViewName("T4Report");
			//mv.addObject("currlist", refCodeConfig.currList());
			mv.addObject("reportsummary", T4currentrep);
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
						"select count(*) from T4ReportSum a where a.rep_period_from=?1 and  a.rep_period_to=?2")
						.setParameter(1, dt1).setParameter(2, dT27).getSingleResult();

				if (dtlcnt > 0) {
					logger.info("Getting No of records in Mod table :" + reportId);
					Long modcnt = (Long) hs.createQuery("select count(*) from T4ReportSum a").getSingleResult();
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

		public ModelAndView getT4currentRep(String reportId, String fromdate, String todate, String currency,
				String dtltype, Pageable pageable) {

			ModelAndView mv = new ModelAndView();

			int pageSize = pageable.getPageSize();
			int currentPage = pageable.getPageNumber();
			int startItem = currentPage * pageSize;

			Session hs = sessionFactory.getCurrentSession();

			List<Object> T4currentrep = new ArrayList<Object>();
			Query<Object[]> qr;


			

			logger.info("Inside archive");
			qr = hs.createNativeQuery(
					"select * from T4_CUSTOMER_PROFILING_SUMMARY_TABLE a where REP_PERIOD_FROM = ?1 and REP_PERIOD_TO = ?2 ORDER BY instance_code" );
			try {
				qr.setParameter(1, df.parse(fromdate));
				qr.setParameter(2, df.parse(todate));

			} catch (ParseException e) {
				e.printStackTrace();
			}

			List<Object[]> result = qr.getResultList();
			for (Object[] a : result) {

				BigDecimal instance_code = (BigDecimal) a[0];
				String instance_name = (String) a[1];
				BigDecimal low_risk_face_to_face = (BigDecimal) a[2];
				BigDecimal low_risk_non_face_to_face = (BigDecimal) a[3];
				BigDecimal med_risk_face_to_face = (BigDecimal) a[4];
				BigDecimal med_risk_non_face_to_face = (BigDecimal) a[5];
				BigDecimal high_risk_face_to_face = (BigDecimal) a[6];
				BigDecimal high_risk_non_face_to_face = (BigDecimal) a[7];
				BigDecimal rejected_risk_face_to_face = (BigDecimal) a[8];
				BigDecimal rejected_risk_non_face_to_face = (BigDecimal) a[9];
				Date report_date = (Date) a[10];
				Date rep_period_from = (Date) a[11];
				Date rep_period_to = (Date) a[12];
				String rep_freq = (String) a[13];
				String nil_report_flg = (String) a[14];


		
				T4ReportSum t4ReportSum = new T4ReportSum(instance_code, instance_name, low_risk_face_to_face, low_risk_non_face_to_face, med_risk_face_to_face, med_risk_non_face_to_face, high_risk_face_to_face, high_risk_non_face_to_face, rejected_risk_face_to_face, rejected_risk_non_face_to_face, report_date, rep_period_from, rep_period_to, rep_freq, nil_report_flg);
				T4currentrep.add(t4ReportSum);



			}

			mv.setViewName("T4Report");
			mv.addObject("reportsummary", T4currentrep);
			mv.addObject("reportsflag", "reportsflag");
			mv.addObject("menu", reportId);
			return mv;

		}

		public ModelAndView getT4Dtl(String reportId, String fromdate, String todate, String currency,
				String dtltype, Pageable pageable,String filter) {

			int pageSize = pageable.getPageSize();
			int currentPage = pageable.getPageNumber();
			int startItem = currentPage * pageSize;

			ModelAndView mv = new ModelAndView();
System.out.println("testing"+filter);
			Session hs = sessionFactory.getCurrentSession();
			List<Object> T4currentDt1 = new ArrayList<Object>();
			Query<Object[]> qr;

			if (dtltype.equals("report")) {
				if(!filter.equals("null")) {
					qr = hs.createNativeQuery("select * from T4_CUSTOMER_PROFILING_DETAILED_TABLE a where report_date = ?1 and CUST_RATING =?2");
					qr.setParameter(2,filter);
				}else {
					qr = hs.createNativeQuery("select * from T4_CUSTOMER_PROFILING_DETAILED_TABLE a where report_date = ?1");
				}
			} else {
				qr = hs.createNativeQuery("select * from T4_CUSTOMER_PROFILING_DETAILED_TABLE a where report_date = ?1");
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
				String	process_owner = (String) a[33];
				String	qtr_flg = (String) a[34];
				Date	verify_date = (Date) a[35];
				String	verify_user = (String) a[36];
				String	arch_flg = (String) a[37];
				String	cell_mapping = (String) a[38];
				String	tran_channel = (String) a[39];



				T4ReportDetail py = new T4ReportDetail(cust_id, customer_name, branch_id, branch_name, bank_id, ownership_type, cust_rating, customer_rating_date, customer_due_rating_date, customer_type, report_quarter, remarks, del_flg, entity_cre_flg, entity_cre_date, mod_flg, entry_user, modify_user, auth_user, entry_time, modify_time, auth_time, aml_code_1, aml_code_2, aml_code_3, aml_code_4, aml_code_5, aml_code_6, aml_code_7, aml_code_8, aml_code_9, aml_code_10, report_date,  process_owner,  qtr_flg, verify_date, verify_user, arch_flg,  cell_mapping,  tran_channel);
						
				T4currentDt1.add(py);

			}
			;

			List<Object> pagedlist;

			if (T4currentDt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, T4currentDt1.size());
				pagedlist = T4currentDt1.subList(startItem, toIndex);
			}

			logger.info("Converting to Page");
			Page<Object> T4currentDt1Page = new PageImpl<Object>(pagedlist, PageRequest.of(currentPage, pageSize),
					T4currentDt1.size());

			mv.setViewName("T4Report :: reportcontent");
			mv.addObject("reportdetails", T4currentDt1Page);

			mv.addObject("singledetail", new T4ReportDetail());
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

			zipFileName =  fileName + ".zip";

			if (!filetype.equals("xbrl")) {

				try {
					InputStream jasperFile = null;
					logger.info("Getting Jasper file :" + reportId);
					if (filetype.equals("detailexcel")) {
						if (dtltype.equals("report")) {
							jasperFile = this.getClass().getResourceAsStream("/static/jasper/Details/T4Detail/T4Detail.jasper");
						} 

					} else {
						if (dtltype.equals("report")) {
							logger.info("Inside report");
							jasperFile = this.getClass().getResourceAsStream("/static/jasper/AmlJasper/T4/T4.jasper");
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
						System.out.println("todya"+today);
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




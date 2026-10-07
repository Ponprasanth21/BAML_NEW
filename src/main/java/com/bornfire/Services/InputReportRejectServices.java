package com.bornfire.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.T4AccReport;
import com.bornfire.entity.T4AccReportsRep;
import com.bornfire.entity.T4Report;
import com.bornfire.entity.T4ReportsRep;
import com.bornfire.entity.t11.T11DetailRep;
import com.bornfire.entity.t11.T11Details;

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
@ConfigurationProperties("output")
@Transactional
public class InputReportRejectServices {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	T4AccReportsRep t4AccReportRep; 
	
	@Autowired
	T4ReportsRep t4ReportRep;
	
	@Autowired
	T11DetailRep t11DetailRep;
	
	@Autowired
	DataSource srcdataSource;
	
	private static final Logger logger = LoggerFactory.getLogger(InputReportRejectServices.class);

	public String addlist(T4Report t4Report, T4AccReport t4AccReport,T11Details t11Details, String formmode) throws ParseException {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("CustomerAdd")) {
			T4Report up = t4Report;
			up.setReport_code("T4");
			
			
			//System.out.println((new SimpleDateFormat("dd-MMM-yyyy").parse(up.getDate_of_appl().toString())));
		
			//up.setDate_of_appl(new SimpleDateFormat("dd-MMM-yyyy").parse(up.getDate_of_appl().toString()));
			//up.setDate_of_rej(new SimpleDateFormat("dd-MMM-yyyy").parse(up.getDate_of_appl().toString()));
			
			hs.save(up);
			msg = "Added Sucessfully";
		}else if (formmode.equals("AccountAdd")) {
			T4AccReport up = t4AccReport;
			up.setReport_code("T4");
			t4AccReportRep.save(up);
			msg = "Added Sucessfully";
		}else if (formmode.equals("TransactionAdd1")) {
			T11Details up = t11Details;
			up.setReport_code("T11");
			t11DetailRep.save(up);
			msg = "Added Sucessfully";
		} 
		return msg;
	}
	
	public File getCustFile(String report_code) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		String msg = "";
		File outputFile;

		logger.info("Getting Output file :" + report_code);

		fileName = report_code + "_" + "Report";

		zipFileName =  fileName + ".zip";

		try {
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + report_code);

			System.out.println("pdf");
			fileStream = this.getClass().getResourceAsStream("/static/jasper/T4/T4.jasper");
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			HashMap<String, Object> map = new HashMap<String, Object>();

			/*
			 * try { logger.info("Getting Jasper file :" + str_ref_no); //File jasperFile =
			 * null; HashMap<String, Object> map = new HashMap<String, Object>();
			 * 
			 * 
			 * 
			 * 
			 * //File jasperFile =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_MAIN.jasper"); //File
			 * subrep1 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/BAML_STR_INTERNAL.jasper")
			 * ; //File subrep2 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_2.jasper"); //File
			 * subrep3 = ResourceUtils.getFile("classpath:static/jasper/STR/STR_3.jasper");
			 * 
			 * // map.put("DIR_1", subrep1); //map.put("DIR_2", subrep2); //map.put("DIR_3",
			 * subrep3);
			 * 
			 */

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);

			logger.info("Assigning Parameters for Jasper");
			map.put("REPORT_CODE", report_code);

			/*
			 * File folders = new File(path); if (!folders.exists()) { folders.mkdirs(); }
			 */

			fileName = fileName + ".pdf";
			path = fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported");

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		System.out.println(outputFile);

		msg = "Pdf Generated Sucessfullly";

		return outputFile;
	}

	
	public File getAcctFile(String report_code) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		String msg = "";
		File outputFile;

		logger.info("Getting Output file :" + report_code);

		fileName = report_code + "_" + "Report";

		zipFileName =  fileName + ".zip";

		try {
			InputStream fileStream = null;
			logger.info("Getting Jasper file :" + report_code);

			System.out.println("pdf");
			fileStream = this.getClass().getResourceAsStream("/static/jasper/T4/T4.jasper");
//				jasperFile = ResourceUtils.getFile("classpath:static/jasper/AMLReports/MonitoringReports/PepList.jasper");
			HashMap<String, Object> map = new HashMap<String, Object>();

			/*
			 * try { logger.info("Getting Jasper file :" + str_ref_no); //File jasperFile =
			 * null; HashMap<String, Object> map = new HashMap<String, Object>();
			 * 
			 * 
			 * 
			 * 
			 * //File jasperFile =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_MAIN.jasper"); //File
			 * subrep1 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/BAML_STR_INTERNAL.jasper")
			 * ; //File subrep2 =
			 * ResourceUtils.getFile("classpath:static/jasper/STR/STR_2.jasper"); //File
			 * subrep3 = ResourceUtils.getFile("classpath:static/jasper/STR/STR_3.jasper");
			 * 
			 * // map.put("DIR_1", subrep1); //map.put("DIR_2", subrep2); //map.put("DIR_3",
			 * subrep3);
			 * 
			 */

			JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);

			logger.info("Assigning Parameters for Jasper");
			map.put("REPORT_CODE", report_code);

			/*
			 * File folders = new File(path); if (!folders.exists()) { folders.mkdirs(); }
			 */

			fileName = fileName + ".pdf";
			path = fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			logger.info("PDF File exported");

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		System.out.println(outputFile);

		msg = "Pdf Generated Sucessfullly";

		return outputFile;
	}
	
	public File getFile(String formmode, String filetype,
			String reportid) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		File outputFile;



		fileName = reportid + "_" + "Report";

		zipFileName =  fileName + ".zip";

	

				
			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportid);
				if (filetype.equals("excel")) {
					if (formmode.equals("Customer")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4.jasper");
					}

				} else {
					if (formmode.equals("Customer")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				//map.put("REPORT_DATE", todate);

				

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path =  fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					
					System.out.println("EXCEEEEEll");
					fileName = fileName + ".xlsx";
					path =  fileName;
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

		
		outputFile = new File(path);

		return outputFile;

	}
	
	public File getAccFile(String formmode, String filetype,
			String reportid) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		File outputFile;



		fileName = reportid + "_" + "Report";

		zipFileName =  fileName + ".zip";

	

				
			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportid);
				if (filetype.equals("excel")) {
					if (formmode.equals("Account")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4Account.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4Account.jasper");
					}

				} else {
					if (formmode.equals("Account")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4Account.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T4/T4Account.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				//map.put("REPORT_DATE", todate);

				

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path =  fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					
					System.out.println("EXCEEEEEll");
					fileName = fileName + ".xlsx";
					path =  fileName;
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

		
		outputFile = new File(path);

		return outputFile;

	}
	public File getTranFile(String formmode, String filetype,
			String reportid) throws FileNotFoundException, JRException, SQLException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String path = "";
		String fileName = "";
		String zipFileName = "";
		File outputFile;



		fileName = reportid + "_" + "Report";

		zipFileName =  fileName + ".zip";

	

				
			try {
				InputStream jasperFile;
				logger.info("Getting Jasper file :" + reportid);
				if (filetype.equals("excel")) {
					if (formmode.equals("TransactionSummary")) {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T11/T11.jasper");
					} else {
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T11/T11.jasper");
					}

				} else {
					if (formmode.equals("TransactionSummary")) {
						logger.info("Inside report");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T11/T11.jasper");
					} else {
						logger.info("Inside archive");
						jasperFile = this.getClass().getResourceAsStream("/static/jasper/T11/T11.jasper");
					}
				}

				JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
				HashMap<String, Object> map = new HashMap<String, Object>();

				logger.info("Assigning Parameters for Jasper");
				//map.put("REPORT_DATE", todate);

				

				if (filetype.equals("pdf")) {
					fileName = fileName + ".pdf";
					path =  fileName;
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);
					logger.info("PDF File exported");
				} else {
					
					System.out.println("EXCEEEEEll");
					fileName = fileName + ".xlsx";
					path =  fileName;
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

		
		outputFile = new File(path);

		return outputFile;

	}
	
	
	public String getT4SrlNo() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("00");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT CUSTREJ.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "CUSTREJ" + numformate.format(billNumber);
		System.out.println("CUSTREJ" + serialno);
		return serialno;
	}
	
	public String getAccSrlNo() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("00");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT ACCREJ.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "ACCREJ" + numformate.format(billNumber);
		System.out.println("ACCREJ" + serialno);
		return serialno;
	}
	
	public String getTranSrlNo() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("00");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT TRANREJ.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = numformate.format(billNumber);
		System.out.println("ACCREJ" + serialno);
		return serialno;
	}
}
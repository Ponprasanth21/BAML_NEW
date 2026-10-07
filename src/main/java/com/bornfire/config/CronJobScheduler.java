package com.bornfire.config;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.sql.DataSource;

import org.apache.tools.ant.types.FlexInteger;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bornfire.Services.ListManagementSchedulerServices;
import com.bornfire.Services.UNSCServices;
import com.bornfire.entity.BAML_List_Schd_RPT_Entity;
import com.bornfire.entity.BAML_List_Schd_RPT_REP;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Configuration
@Component
@Service
public class CronJobScheduler {
	@Autowired
	EMAILREP emailRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	UNSCServices unscServices;

	@Autowired
	Environment env;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	ListManagementSchedulerServices listServices;

	@Autowired
	BAML_List_Schd_RPT_REP list_rep;

	private static final Logger logger = LoggerFactory.getLogger(CronJobScheduler.class);

	// @Scheduled(cron = "0 * * ? * *")
//	public void gmail() {
//		String status;
//
//		String host = "webmail.bornfire.in";
//		final String user = "baml@bornfire.in";// change accordingly
//		final String password = "baml@MCS";// change accordingly
//		String to = "manivannan.b@bornfire.in";
//		// System.out.println("host " + host + " user " + user + " password " +
//		// password);
//
//		// String to = "kalaivanan.r@bornfire.in,manivannan.b@bornfire.in";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		// props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", host);
//        // use mail address from HTML form for from address
//		props.put("mail.from", "baml@bornfire.in");
//
//		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user, password);
//			}
//		});
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(user));
//			InternetAddress[] parse = InternetAddress.parse(to, true);
//
//			message.setRecipients(javax.mail.Message.RecipientType.TO, parse);
//
//			message.setSubject("PARAMETER CHANGED");
//
//			String det = "MASTER /MANIVANNAN/BORNFIRE/";
//
//			String[] dt1 = det.split("/");
//			String message1 = "<i>Please find below the alert particulars,</i><br>";
//			for (String info : dt1) {
//
//				message1 += "<b>" + info + "</b><br>";
//
//			}
//			System.out.println(message1);
//			message.setContent(message1, "text/html");
//			Transport.send(message);
//
//			status = "success";
//			logger.info(status);
//		} catch (Exception e) {
//			status = "Failure";
//			logger.info(status);
//		}
//		System.out.println(status);
//	}


	//0 */1 * * *  
	
	///@Scheduled(cron = "0 40 */1 ? * *")
	//@Scheduled(cron = "0 * * ? * *")
	public String execute() throws Exception {

		logger.info("EMAIL STARTS");
		String nextPage = "";
		String status;

		int count = emailRep.getEmailSentCount();
		String host = env.getProperty("mail.host");
		String user = env.getProperty("mail.username");// change accordingly
		String password = env.getProperty("mail.password");// change accordingly
		String port = env.getProperty("mail.port");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		if (count > 0) {

			List<EmailAlert> lst_Objects = emailRep.getEmailDetails();
			if (lst_Objects != null) {
				for (EmailAlert cmnVal : lst_Objects) {

					
					System.out.println("SEND MAIL...");
					try {
						System.out.println("Hi");
						MimeMessage msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(user));

						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(cmnVal.getEmail_id()));
						msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cmnVal.getEmail_id_cc1()));
						if (cmnVal.getEmail_id_cc2() != null) {
							msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cmnVal.getEmail_id_cc2()));
						}
						msg.setSentDate(new Date());
						msg.setSubject(cmnVal.getEmail_sub());
						// msg.setText(cmnVal.getEmail_body());
						/*
						 * StringBuilder sb = new StringBuilder();
						 * //sb.append("Dear Sir,").append(System.lineSeparator());
						 * sb.append("Please find below the alert particulars,").append(System.
						 * lineSeparator());
						 * 
						 * sb.append(cmnVal.getEmail_body());
						 * 
						 * msg.setText(sb.toString());
						 */

						String det = cmnVal.getEmail_body();

						String[] dt1 = det.split("/");
						String message1 = "<i>Please find below the alert particulars,</i><br><br>";
						System.out.println(cmnVal.getEmail_sub());
						if (cmnVal.getEmail_sub().equals("Evidence Source of Fund Alert")) {
							message1 += "<b>Daily amount limit exceeded for the following customer:</b><br><br><br>";

							message1 +="<table border=1><tr><td style=width:200px;><b>" + dt1[0].toString()
									+ "</b></td><td style=width:300px;><i>" + dt1[1].toString() + "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[2].toString()
									+ "</b></td><td style=width: 300px;><i>" + dt1[3].toString() + "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[4].toString()
									+ "</b></td><td style=width: 300px; ><i>" + dt1[5].toString() + "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[6].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[7].toString()+ "</i></td></tr>"
                                    + "<tr><td style=width:200px;><b>" + dt1[8].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[9].toString()+ "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[10].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[11].toString()+ "</i></td></tr>"
							        + "<tr><td style=width:200px;><b>" + dt1[12].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[13].toString()+ "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[14].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[15].toString()+ "</i></td></tr></table>";

//							message1 += "<table ><tr><td style=width:200px;><b>" + dt1[0].toString() + "</b></td><td style=width:300px;><b>" + dt1[1].toString() + "</b></td></tr>";
//							message1 += "<table><tr><td style=width:200px;><b>" + dt1[2].toString() + "</b></td><td style=width: 300px;><b>" + dt1[3].toString() + "</b></td></tr>";
//							message1 += "<table><tr><td style=width:200px;><b>" + dt1[4].toString() + "</b></td><td style=width: 300px; ><b>" + dt1[5].toString() + "</b></td></tr>";
//							message1 += "<table><tr><td style=width:200px;><b>" + dt1[6].toString() + "</b></td><td style=width: 300px;><b>" + dt1[7].toString() + "</b></td></tr></table>";

						}else if (cmnVal.getEmail_sub().equals("Card Limit Alert")) {
							message1 += "<b>Card daily limit reached for the following customer:</b><br><br><br>";

							message1 +="<table border=1><tr><td style=width:200px;><b>" + dt1[0].toString()
									+ "</b></td><td style=width:300px;><i>" + dt1[1].toString() + "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[2].toString()
									+ "</b></td><td style=width: 300px;><i>" + dt1[3].toString() + "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[4].toString()
									+ "</b></td><td style=width: 300px; ><i>" + dt1[5].toString() + "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[6].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[7].toString()+ "</i></td></tr>"
                                    + "<tr><td style=width:200px;><b>" + dt1[8].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[9].toString()+ "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[10].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[11].toString()+ "</i></td></tr>"
							        + "<tr><td style=width:200px;><b>" + dt1[12].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[13].toString()+ "</i></td></tr>"
									+ "<tr><td style=width:200px;><b>" + dt1[14].toString()+ "</b></td><td style=width: 300px;><i>" + dt1[15].toString()+ "</i></td></tr></table>";

//							message1 += "<table ><tr><td style=width:200px;><b>" + dt1[0].toString() + "</b></td><td style=width:300px;><b>" + dt1[1].toString() + "</b></td></tr>";
//							message1 += "<table><tr><td style=width:200px;><b>" + dt1[2].toString() + "</b></td><td style=width: 300px;><b>" + dt1[3].toString() + "</b></td></tr>";
//							message1 += "<table><tr><td style=width:200px;><b>" + dt1[4].toString() + "</b></td><td style=width: 300px; ><b>" + dt1[5].toString() + "</b></td></tr>";
//							message1 += "<table><tr><td style=width:200px;><b>" + dt1[6].toString() + "</b></td><td style=width: 300px;><b>" + dt1[7].toString() + "</b></td></tr></table>";

						} else {

							for (String info : dt1) {

								message1 += "<b>" + info + "</b><br>";

							}
						}
						System.out.println(message1);
						msg.setContent(message1, "text/html");

						Transport.send(msg);
					

						nextPage = "success";

					} catch (Exception E) {
						System.out.println("Oops something has gone pearshaped!");
						System.out.println(E);
						nextPage = "error";
					}
				
					if (nextPage.equals("success")) {
						BigDecimal srl = cmnVal.getEmail_srl_no();
						EmailAlert cv = emailRep.getEmailbySRl(srl);

						cv.setSend_flg("Y");
						cv.setMsg_status("Delivered Successfully");

						emailRep.save(cv);
					}
				}
			
			}
		}
		return nextPage;

	}
	/*
	 * @Scheduled(cron = "0 * * ? * *") public String testing() { String msg="";
	 * 
	 * BigDecimal Number1 = emailRep.gettestsrlno(); EmailAlert EA = new
	 * EmailAlert(); String alertcode = "USER-MODIFY";
	 * 
	 * EA.setEmail_id("manivannan.b@bornfire.in");
	 * EA.setEmail_id_cc1("ramprasath.p@bornfire.in");
	 * 
	 * EA.setEmail_sub("TESTING PURPOSE"); EA.
	 * setEmail_body("USER MODIFIED SUCCESSFULLY...., USER_ID : TEST, USER_NAME :TESTING"
	 * ); EA.setEmail_date(new Date()); EA.setEmail_srl_no(Number1);
	 * EA.setSend_flg("N"); emailRep.save(EA);
	 * 
	 * return msg; }
	 */

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	// @Scheduled(cron = "* /55 * * * *")
	// @Scheduled(cron = " */35 * * * * *")
	// At 09:00 A.M and 15:00 P.M

	// @Scheduled(cron = " 0 0/5 * * * *")
	// @Scheduled(cron = "0 0 9,15 * * *")
	//@Scheduled(cron = "0 0 9,12 * * *")
	//@Scheduled(cron = "*/5 * * * * ?")
	public void UNSC() {

		logger.info("Time to Refresh the UNSC Entity Data");
	//	String status = unscServices.uploadUNSC();

		//logger.info(" STATUS FOR UNSC Ent " + status);

		logger.info("Time to Refresh the UNSC Individual Data");
		String status1 = unscServices.uploadUNSCInt();
		logger.info(" STATUS FOR UNSC Ind " + status1);

	}

//	@Scheduled(cron = "*/5 * * * * ?")//for every 5 min
	// @Scheduled(cron = "0 0 15 * * ?")//for everyday 11 pm
	//@Scheduled(cron = "0 0 23 * * *")
	public void executeListManagement() throws JRException, SQLException, IOException {
		logger.info("Scheduler for ListManagement Begins");
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String path_xlsx = env.getProperty("output.exportpath");
		String path_pdf = env.getProperty("output.exportpath");

		String path_new_xlsx = env.getProperty("output.exportpath");
		String path_new_pdf = env.getProperty("output.exportpath");

		String path_cust_chk_xlsx = env.getProperty("output.exportpath");
		String path_cust_chk_pdf = env.getProperty("output.exportpath");

		Date sysDate = new Date();
		String str_Date = dateFormat.format(sysDate);

		String fileName = "";
		String filename_PDF = "";
		File outputFile_PDF;
		File outputFile_XLSX;

		fileName = "List_Management_Consolidated_check" + "_" + str_Date;
		filename_PDF = "List_Management_Consolidated_check" + "_" + str_Date;
		InputStream fileStream = null;
		logger.info("Getting Jasper file :" + "List_Management_Consolidated_check");

		fileStream = this.getClass().getResourceAsStream("/static/jasper/LIST_SCHD/ListSchedulerCons.jasper");

		JasperReport jr = (JasperReport) JRLoader.loadObject(fileStream);
		HashMap<String, Object> map = new HashMap<String, Object>();

		logger.info("Assigning Parameters for Jasper");
		map.put("REPORT_DATE", str_Date);

		logger.info("BEFORE GENERATING XLSX :" + "List_Management_Consolidated_check");
		fileName = fileName + ".xlsx";
		path_xlsx += fileName;
		JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path_xlsx));
		exporter.exportReport();
		logger.info("Excel File exported");

		outputFile_XLSX = new File(path_xlsx);

		logger.info("BEFORE GENERATING PDF :" + "List_Management_Consolidated_check");

		filename_PDF = filename_PDF + ".pdf";
		path_pdf += filename_PDF;
		JasperPrint jppdf = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
		logger.info("BEFORE GENERATING PDF 2 :" + filename_PDF);
		JasperExportManager.exportReportToPdfFile(jppdf, path_pdf);
		logger.info("PDF File exported");

		outputFile_PDF = new File(path_pdf);

//		********************************XLSX and pdf for new list************************************

		String fileName_new = "";
		String filename_PDF_new = "";
		File outputFile_PDF_new;
		File outputFile_XLSX_new;

		fileName_new = "List_Management_New_List_check" + "_" + str_Date;
		filename_PDF_new = "List_Management_New_List_check" + "_" + str_Date;
		InputStream fileStream_new = null;
		logger.info("Getting Jasper file :" + "List_Management_New_List_check");

		fileStream_new = this.getClass().getResourceAsStream("/static/jasper/LIST_SCHD/ListSchedulerNewList.jasper");

		JasperReport jr_new = (JasperReport) JRLoader.loadObject(fileStream_new);
		HashMap<String, Object> map_new = new HashMap<String, Object>();

		logger.info("Assigning Parameters for Jasper");
		map_new.put("REPORT_DATE", str_Date);

		logger.info("BEFORE GENERATING XLSX :" + "List_Management_New_List_check");
		fileName_new = fileName_new + ".xlsx";
		path_new_xlsx += fileName_new;
		JasperPrint jp_new = JasperFillManager.fillReport(jr_new, map_new, srcdataSource.getConnection());
		JRXlsxExporter exporter_new = new JRXlsxExporter();
		exporter_new.setExporterInput(new SimpleExporterInput(jp_new));
		exporter_new.setExporterOutput(new SimpleOutputStreamExporterOutput(path_new_xlsx));
		exporter_new.exportReport();
		logger.info("Excel File exported");

		outputFile_XLSX_new = new File(path_new_xlsx);

		logger.info("BEFORE GENERATING PDF :" + "List_Management_New_List_check");

		filename_PDF_new = filename_PDF_new + ".pdf";
		path_new_pdf += filename_PDF_new;
		JasperPrint jppdf_new = JasperFillManager.fillReport(jr_new, map_new, srcdataSource.getConnection());
		logger.info("BEFORE GENERATING PDF 2 :" + filename_PDF);
		JasperExportManager.exportReportToPdfFile(jppdf_new, path_new_pdf);
		logger.info("PDF File exported");

		outputFile_PDF_new = new File(path_new_pdf);

//		**********************************new cust check*****************************************

		String fileName_cust = "";
		String filename_PDF_cust = "";
		File outputFile_PDF_cust;
		File outputFile_XLSX_cust;

		fileName_cust = "List_Management_New_Customer_check" + "_" + str_Date;
		filename_PDF_cust = "List_Management_New_Customer_check" + "_" + str_Date;
		InputStream fileStream_cust = null;
		logger.info("Getting Jasper file :" + "List_Management_New_Customer_check");

		fileStream_cust = this.getClass()
				.getResourceAsStream("/static/jasper/LIST_SCHD/ListSchedulerNewCustChk.jasper");

		JasperReport jr_cust = (JasperReport) JRLoader.loadObject(fileStream_cust);
		HashMap<String, Object> map_cust = new HashMap<String, Object>();

		logger.info("Assigning Parameters for Jasper");
		map_cust.put("REPORT_DATE", str_Date);

		logger.info("BEFORE GENERATING XLSX :" + "List_Management_New_List_check");
		fileName_cust = fileName_cust + ".xlsx";
		path_cust_chk_xlsx += fileName_cust;
		JasperPrint jp_cust = JasperFillManager.fillReport(jr_cust, map_cust, srcdataSource.getConnection());
		JRXlsxExporter exporter_cust = new JRXlsxExporter();
		exporter_cust.setExporterInput(new SimpleExporterInput(jp_cust));
		exporter_cust.setExporterOutput(new SimpleOutputStreamExporterOutput(path_cust_chk_xlsx));
		exporter_cust.exportReport();
		logger.info("Excel File exported");

		outputFile_XLSX_cust = new File(path_cust_chk_xlsx);

		logger.info("BEFORE GENERATING PDF :" + "List_Management_New_List_check");

		filename_PDF_cust = filename_PDF_cust + ".pdf";
		path_cust_chk_pdf += filename_PDF_cust;
		JasperPrint jppdf_cust = JasperFillManager.fillReport(jr_cust, map_cust, srcdataSource.getConnection());
		logger.info("BEFORE GENERATING PDF 2 :" + filename_PDF_cust);
		JasperExportManager.exportReportToPdfFile(jppdf_cust, path_cust_chk_pdf);
		logger.info("PDF File exported");

		outputFile_PDF_cust = new File(path_cust_chk_pdf);

		// ***********************saving the pdf file to database
		File pdfFile = new File(path_pdf);
		byte[] pdfData = new byte[(int) pdfFile.length()];
		DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
		dis.readFully(pdfData); // read from file into byte[] array
		dis.close();

		File xlsxFile = new File(path_xlsx);
		byte[] xlsxData = new byte[(int) xlsxFile.length()];
		DataInputStream dis1 = new DataInputStream(new FileInputStream(xlsxFile));
		dis1.readFully(xlsxData); // read from file into byte[] array
		dis1.close();

		File pdfFile_new = new File(path_new_pdf);
		byte[] pdfData_new = new byte[(int) pdfFile_new.length()];
		DataInputStream dis_new = new DataInputStream(new FileInputStream(pdfFile_new));
		dis_new.readFully(pdfData_new); // read from file into byte[] array
		dis_new.close();

		File xlsxFile_new = new File(path_xlsx);
		byte[] xlsxData_new = new byte[(int) xlsxFile_new.length()];
		DataInputStream dis1_new = new DataInputStream(new FileInputStream(xlsxFile_new));
		dis1_new.readFully(xlsxData_new); // read from file into byte[] array
		dis1_new.close();

		File pdfFile_cust = new File(path_cust_chk_pdf);
		byte[] pdfData_cust = new byte[(int) pdfFile_cust.length()];
		DataInputStream dis_cust = new DataInputStream(new FileInputStream(pdfFile_cust));
		dis_cust.readFully(pdfData_cust); // read from file into byte[] array
		dis_cust.close();

		File xlsxFile_cust = new File(path_cust_chk_xlsx);
		byte[] xlsxData_cust = new byte[(int) xlsxFile_cust.length()];
		DataInputStream dis1_cust = new DataInputStream(new FileInputStream(xlsxFile_cust));
		dis1_cust.readFully(xlsxData_cust); // read from file into byte[] array
		dis1_cust.close();

		try {
			BAML_List_Schd_RPT_Entity info = new BAML_List_Schd_RPT_Entity();
			info.setRPT_DATE(new Date());
			info.setCONS_PDF(pdfData);
			info.setCONS_EXCEL(xlsxData);
			info.setNEW_LIST_PDF(pdfData_new);
			info.setNEW_LIST_EXCEL(xlsxData_new);
			info.setCUST_CHECK_PDF(pdfData_cust);
			info.setCUST_CHECK_EXCEL(xlsxData_cust);
			info.setENTRY_TIME(new Date());
			info.setENTRY_BY("SYSTEM");
			list_rep.save(info);
		} catch (Exception e) {
			logger.info("Save Exception " + e.getMessage());
		}

		// ***********************Email Code
		logger.info("BEFORE ENTERING EMAIL ");

		listServices.sendEmail(fileName, fileName_new, fileName_cust);

	}

}

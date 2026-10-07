package com.bornfire.Services;



import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.BAML_List_Schd_RPT_Entity;
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


@Service
@Component
public class ListManagementSchedulerServices {

	private static final Logger logger = LoggerFactory.getLogger(ListManagementSchedulerServices.class);

	@Autowired
	AlertManagementRepository alertrep;
	
	@Autowired
	Environment env;
	

public void sendEmail(String filename,String filenamenew,String filenamecust) {
	EmailAlert EA = new EmailAlert();
	String alertcode = "LIST_SCHED";
	AlertManagementEntity  AM= alertrep.getalertdetail(alertcode);
	if(AM.getEmail_flg().equals("Y")) {
		EA.setEmail_id(AM.getEmail_1());
		EA.setEmail_id_cc1(AM.getEmail_2());
		EA.setEmail_id_cc2(AM.getEmail_3());
		EA.setEmail_sub(AM.getParam_1());


		logger.info("EMAIL STARTS");
		String nextPage = "";
		String status;

		String host = env.getProperty("mail.host");
		String user = env.getProperty("mail.username");// change accordingly
		String password = env.getProperty("mail.password");// change accordingly
		String port = env.getProperty("mail.port");

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					// props   .put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", port);

					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
					});
					System.out.println("SEND MAIL...");
					try {
						MimeMessage msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(user));

						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(EA.getEmail_id()));
						msg.addRecipient(Message.RecipientType.CC, new InternetAddress(EA.getEmail_id_cc1()));
						if(EA.getEmail_id_cc2()!=null) {
							msg.addRecipient(Message.RecipientType.CC, new InternetAddress(EA.getEmail_id_cc2()));
						}


						msg.setSentDate(new Date());
						msg.setSubject(EA.getEmail_sub());

						   BodyPart messageBodyPart1 = new MimeBodyPart();  
						    messageBodyPart1.setText("Please do find the attached files for matching records(Black list/Pep List/UNSC List).");  
						      
//						  MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
//						  String path_xlsx =  env.getProperty("output.exportpath");
//						  path_xlsx+=filename;
//						    DataSource source = new FileDataSource(path_xlsx);  
//						    messageBodyPart2.setDataHandler(new DataHandler(source));  
//						    messageBodyPart2.setFileName(filename);  
						    
						    MimeBodyPart messageBodyPart3 = new MimeBodyPart();  
							  String path_xlsx_new =  env.getProperty("output.exportpath");
							  path_xlsx_new+=filenamenew;
							    DataSource source2 = new FileDataSource(path_xlsx_new);  
							    messageBodyPart3.setDataHandler(new DataHandler(source2));  
							    messageBodyPart3.setFileName(filenamenew);  
							    
							    
							    MimeBodyPart messageBodyPart4 = new MimeBodyPart();  
								  String path_xlsx_cust =  env.getProperty("output.exportpath");
								  path_xlsx_cust+=filenamecust;
								    DataSource source3 = new FileDataSource(path_xlsx_cust);  
								    messageBodyPart4.setDataHandler(new DataHandler(source3));  
								    messageBodyPart4.setFileName(filenamecust);  
						    
//							  MimeBodyPart messageBodyPart3 = new MimeBodyPart();  
//							String path_pdf =  env.getProperty("output.exportpath");
//							path_pdf+=filename;
//							 DataSource source2 = new FileDataSource(path_pdf);  
//							 messageBodyPart3.setDataHandler(new DataHandler(source2));  
//							 messageBodyPart3.setFileName(path_pdf); 
						     
						     
						    Multipart multipart = new MimeMultipart();  
						    multipart.addBodyPart(messageBodyPart1);  
						    //commented the first attachment for consolidated file .
//						    multipart.addBodyPart(messageBodyPart2);  
						    multipart.addBodyPart(messageBodyPart3);  
						    multipart.addBodyPart(messageBodyPart4);  
						    msg.setContent(multipart);  

						
						Transport.send(msg);

						nextPage = "success";

					} catch (Exception E) {
						nextPage = "error";
					}
					if (nextPage.equals("success")) {
//						BigDecimal srl = cmnVal.getEmail_srl_no();
//						EmailAlert cv = emailRep.getEmailbySRl(srl);
//
//						cv.setSend_flg("Y");
//						cv.setMsg_status("Delivered Successfully");
//
//						emailRep.save(cv);
					}
		
	}
}

}

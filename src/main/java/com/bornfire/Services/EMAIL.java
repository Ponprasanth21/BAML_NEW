package com.bornfire.Services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bornfire.entity.EMAILREP;


@Component
public class EMAIL {
	
	@Autowired
	EMAILREP emailRep;
	
	@Autowired
	Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(EMAIL.class);

	public String sendEmail() throws  MessagingException, NamingException {

		String host = env.getProperty("mail.host");
		final String user =env.getProperty("mail.username");// change accordingly
		final String password=env.getProperty("mail.password");//change accordingly
			String port=env.getProperty("mail.port");
			 Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		     // props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

			
			  Session session = Session.getInstance(props);
					 
			  System.out.println("SEND MAIL...");
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(
						"vijaycorda@gmail.com"));
						
				message.setSubject("BFI Account Opening");
				
			      message.setText("sddsads");
			      
			      
				
		         Transport.send(message);
			}
		         catch (Exception e) {
					// TODO: handle exception
				}
		return "succes";
			
}
}
	


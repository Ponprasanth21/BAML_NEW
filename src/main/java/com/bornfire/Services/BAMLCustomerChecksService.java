package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.controller.NavigationController;
import com.bornfire.entity.BAMLCustomerChecks;
import com.bornfire.entity.BAMLCustomerChecksRepo;

@Service
@Transactional
@ConfigurationProperties("output")
public class BAMLCustomerChecksService {
	

	
	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BAMLCustomerChecksRepo bamlCustomerChecksRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(BAMLCustomerChecksService.class);
	
	public BAMLCustomerChecks getRefNo(BigDecimal id) {

		if (bamlCustomerChecksRepo.existsById(id)) {
			System.out.println("getting tran id");
			BAMLCustomerChecks up = bamlCustomerChecksRepo.findById(id).get();

			return up;
		} else {
			return new BAMLCustomerChecks();
		}

	}
	
	public String preCheck(Date searchDate) {
		logger.info("preCheck()");
		String msg = "";
		
		long counter = bamlCustomerChecksRepo.count(searchDate);
		logger.info("counter " + counter);
		
		if(counter > 0) {
			msg="success";
			logger.info("preCheck()->success");
		} else {
			msg="no data found";
			logger.info("preCheck()->no data found");
		}
		
		logger.info("returning from preCheck()");
		return msg;
	}

	public String Generation(Date searchDate) {
		logger.info("preCheck()");
		String msg = "";
		
		Session theSession = sessionFactory.getCurrentSession();
		long counter = bamlCustomerChecksRepo.count(searchDate);
		logger.info("counter " + counter+searchDate);
	
System.out.println(searchDate);
SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
String strDate1 = formatter1.format(searchDate);

		StoredProcedureQuery query1 = theSession.createStoredProcedureQuery("BAML_CUST_CHECK_MAIN_PROCEDURE_LOCAL_SEARCH")
				.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
		query1.setParameter("REPORT_DATE", strDate1);
		query1.execute();
		
		System.out.println(strDate1);
		if(counter > 0) {
			msg="success";
			logger.info("preCheck()->success");
		} else {
			msg="no data found";
			logger.info("preCheck()->no data found");
		}
		
		logger.info("returning from preCheck()");
		return msg;
	}
}

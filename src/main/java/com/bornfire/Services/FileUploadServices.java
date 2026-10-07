package com.bornfire.Services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bornfire.entity.xml.ConsolidatedList;

public class FileUploadServices {
	
	
	private final Logger logger = LoggerFactory.getLogger(FileUploadServices.class);

	@Autowired
	SessionFactory sessionFactory;
	

	 public String newuserregister(ConsolidatedList CL) {
	 Session hs =sessionFactory.getCurrentSession(); 
	 String List = ""; 
	 hs.saveOrUpdate(CL);
	
	 return List;
	 
	 }

}

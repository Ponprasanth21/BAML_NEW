package com.bornfire.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bornfire.entity.RuleEngineEntity;
import com.bornfire.entity.RuleEngineRepository;
import com.bornfire.entity.TransMonitoringRepository;
import com.bornfire.entity.Transaction;




@Service
@ConfigurationProperties("output")
@Transactional

public class TransMonitoringServices {
	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);
	
	@Autowired
	TransMonitoringRepository transMonitoringRepository;
	
	@Autowired
	RuleEngineRepository ruleEngineRepository;
	
	@Autowired
	SessionFactory sessionFactory;


	


	public Page<Transaction> getTransactionDetails(PageRequest page,String ruleCode) {
		 List<RuleEngineEntity> ruleList= ruleEngineRepository.getRuleCodeData(ruleCode);
		 
		 System.out.println(ruleList.get(0).getValue_min());
		 System.out.println(ruleList.get(0).getValue_max());
		 
		 
		 return transMonitoringRepository.findAllCustomList(page,ruleList.get(0).getValue_min(),ruleList.get(0).getValue_max());
		 
		 
		 
	}
}
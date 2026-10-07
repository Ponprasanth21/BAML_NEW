package com.bornfire.Services;


import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.FinCMG;




@Service
@Transactional
public class  CustomerKYC{
	
	@Autowired
	SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerKYC.class);


	
	public List<FinCMG> getAccountList2(Pageable pageable){
		Session hs = sessionFactory.getCurrentSession();
		
		List<FinCMG> result =  hs.createQuery("from FinCMG",FinCMG.class).getResultList();
		List<FinCMG> pagedlist;
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;


		if (result.size() < startItem) {
			pagedlist = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, result.size());
			pagedlist = result.subList(startItem, toIndex);
			
			System.out.println("yg"+pagedlist);
		}
		logger.info("Converting to Page");
		Page<FinCMG> bls0100RepPage = new PageImpl<FinCMG>(pagedlist, PageRequest.of(currentPage, pageSize),
				result.size());


		
		return hs.createQuery("from FinCMG",FinCMG.class).getResultList();
	}
	

	public List<FinCMG> getAccountList(){
		
		Session hs = sessionFactory.getCurrentSession();
		return hs.createQuery("from FinCMG",FinCMG.class).getResultList();
	}

}

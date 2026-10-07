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

import com.bornfire.entity.AccountsInquiryEntity;




@Service
@Transactional
public class AccountsInquiry {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountsInquiry.class);
	


	
	public List<AccountsInquiryEntity> getAccountList2(Pageable pageable){
		Session hs = sessionFactory.getCurrentSession();

		
		List<AccountsInquiryEntity> result =  hs.createQuery("from AccountsInquiryEntity",AccountsInquiryEntity.class).getResultList();
		List<AccountsInquiryEntity> pagedlist;
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
		Page<AccountsInquiryEntity> bls0100RepPage = new PageImpl<AccountsInquiryEntity>(pagedlist, PageRequest.of(currentPage, pageSize),
				result.size());


		
		return hs.createQuery("from AccountsInquiryEntity",AccountsInquiryEntity.class).getResultList();
	}
	

	public List<AccountsInquiryEntity> getAccountList(){
		
		Session hs = sessionFactory.getCurrentSession();
		return hs.createQuery("from AccountsInquiryEntity",AccountsInquiryEntity.class).getResultList();
	}
	/**********************************************AML ACCOUNT INQUIRY SEARCH START*****************************************************************/
	
	@SuppressWarnings("unchecked")
	public List<AccountsInquiryEntity> getcustomerId(String CustId) {
		List<AccountsInquiryEntity> list = (List<AccountsInquiryEntity>) sessionFactory.getCurrentSession()
				.createQuery(" from AccountsInquiryEntity where cust_id  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<AccountsInquiryEntity> getACCNumber(String CustId) {
		List<AccountsInquiryEntity> list = (List<AccountsInquiryEntity>) sessionFactory.getCurrentSession()
				.createQuery(" from AccountsInquiryEntity where foracid  ='" + CustId + "'").getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountsInquiryEntity> getAccname(String CustId) {
		List<AccountsInquiryEntity> list = (List<AccountsInquiryEntity>) sessionFactory.getCurrentSession()
				.createQuery(" from AccountsInquiryEntity where acct_name LIKE : searchcustname ").setParameter("searchcustname", "%"+CustId+"%").getResultList();
		System.out.println(CustId);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<AccountsInquiryEntity> getSchcode(String CustId) {
		List<AccountsInquiryEntity> list = (List<AccountsInquiryEntity>) sessionFactory.getCurrentSession()
				.createQuery(" from AccountsInquiryEntity where schm_code  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<AccountsInquiryEntity> getCurcode(String CustId) {
		List<AccountsInquiryEntity> list = (List<AccountsInquiryEntity>) sessionFactory.getCurrentSession()
				.createQuery(" from AccountsInquiryEntity where acct_crncy_code  ='" + CustId + "'").getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<AccountsInquiryEntity> getAccbal(String CustId) {
		List<AccountsInquiryEntity> list = (List<AccountsInquiryEntity>) sessionFactory.getCurrentSession()
				.createQuery(" from AccountsInquiryEntity where clr_bal_amt  ='" + CustId + "'").getResultList();
		return list;
	}
	
	/**********************************************AML ACCOUNT INQUIRY SEARCH START*****************************************************************/

}

package com.bornfire.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AccountsInquiryEntity;
import com.bornfire.entity.CMG_MASTER;
import com.bornfire.entity.CMGrepository;
import com.bornfire.entity.FinCMG;
import com.bornfire.entity.HTDEntity;
import com.bornfire.entity.TransactionMaster;
import com.bornfire.entity.TransactionMasterRepository;

@Service
@ConfigurationProperties("output")
@Transactional

public class CustomerMasterService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerMasterService.class);
	@Autowired
	CMGrepository CMGrepository;
	@Autowired
	TransactionMasterRepository TransactionMasterRepository;

	@Autowired

	SessionFactory sessionFactory;

	public FinCMG getCustId(String id) {
		if (CMGrepository.existsById(id)) {
			System.out.println("getcustid");
			FinCMG up = CMGrepository.findById(id).get();
			System.out.println(up);

			return up;
		} else {
			return new FinCMG();
		}

	};
	
	public TransactionMaster getTranId(String id) {
		if (TransactionMasterRepository.existsById(id)) {
			System.out.println("getTranId");
			TransactionMaster up = TransactionMasterRepository.findById(id).get();
			System.out.println(up);

			return up;
		} else {
			return new TransactionMaster();
		}

	};

	public AccountsInquiryEntity getgamAcid(String id) {
		System.out.println("getgamAcid");
		System.out.println(id);
		Session hs = sessionFactory.getCurrentSession();
		/*
		 * if (accountsinquiryrepository.existsById(id)) {
		 * System.out.println("getcustid");
		 */
		/* AccountsInquiryEntity up = accountsinquiryrepository.findById(id).get(); */
		/*
		 * Query qr = hs.createNativeQuery( "SELECT * FROM GAM WHERE CUST_ID="+id);
		 */
		AccountsInquiryEntity up = (AccountsInquiryEntity) sessionFactory.getCurrentSession()
				.createQuery("from AccountsInquiryEntity where acid=?1").setParameter(1, id).getSingleResult();

		System.out.println(up);

		return up;
		/*
		 * } else { return new AccountsInquiryEntity(); }
		 */
	};

	public List<HTDEntity> gethtdAcid(String id) {
		System.out.println("gethtdAcid");
		System.out.println(id);
		Session hs = sessionFactory.getCurrentSession();

		List<HTDEntity> up =  sessionFactory.getCurrentSession().createQuery("from HTDEntity where acid=?1",HTDEntity.class) // create
																													// query
				.setParameter(1, id).getResultList();
		
		

		return up;

	};
	
	

	@SuppressWarnings("unchecked")
	public List<CMG_MASTER> getCustomerByName(String cifid) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CMG_MASTER where UPPER(cust_name)  like UPPER(?1) ", CMG_MASTER.class);
		query.setParameter(1, cifid);

		List<CMG_MASTER> result = query.getResultList();

		System.out.println(result);
		return result;
	}

}
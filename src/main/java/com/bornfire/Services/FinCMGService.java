package com.bornfire.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.FinCMG;

import ch.qos.logback.classic.Logger;



@Service
@Repository
@Transactional
public class FinCMGService {

	@Autowired
	SessionFactory sessionFactory;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FinCMGService.class);

	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerId(String CustId) {
		List<FinCMG> list = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where cust_id  ='" + CustId + "'").getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerName(String CustId) {
		List<FinCMG> list = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where cust_name LIKE : searchcustname ").setParameter("searchcustname", "%"+CustId+"%").getResultList();
		System.out.println(CustId);
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerDOB(String CustId) {
		System.out.println("date " + CustId);
		 SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");
		 Date date2 = null;
		 try {
			 date2=formatter2.parse(CustId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 Query q  =  (Query) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where date_of_birth  = ?1 ", FinCMG.class)
				.setParameter(1, date2);
		 
		 List list = q.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerNUMBER(String CustId) {
		List<FinCMG> list = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where cust_perm_phone_num  =?1").setParameter(1, CustId).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerEMAIL(String CustId) {
		List<FinCMG> list = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where email_id  =?1").setParameter(1, CustId).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerNatID(String CustId) {
		List<FinCMG> list = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where nat_id_card_num  =?1").setParameter(1, CustId).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FinCMG> getcustomerNRE(String CustId) {
		
		
		List<FinCMG> list = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery(" from FinCMG where cust_nre_flg  =?1 ").setParameter(1, CustId).getResultList();
		return list;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public String runprocedure(String module_name) {
		Session hs = sessionFactory.getCurrentSession();
		Session theSession = sessionFactory.getCurrentSession();
		String status ="";
		logger.info("inside workbook3");
		try {
			StoredProcedureQuery query1 = theSession.createStoredProcedureQuery(module_name);
					/*.registerStoredProcedureParameter("REPORT_DATE", String.class, ParameterMode.IN);
			query1.setParameter("REPORT_DATE", report_date);*/
			query1.execute();
			status ="Success";
		} catch (Exception e) {
			status ="Failure";
		}
		return status;
		
		
	}
	
/*	@SuppressWarnings("unchecked")
	public String runprocedure1(Date report_date,String module_name) {
		Session hs = sessionFactory.getCurrentSession();
		Session theSession = sessionFactory.getCurrentSession();
		String status ="";
		
		try {
			StoredProcedureQuery query1 = theSession.createStoredProcedureQuery(module_name)
					.registerStoredProcedureParameter("REPORT_DATE", Date.class, ParameterMode.IN);
			query1.setParameter("REPORT_DATE", report_date);
			query1.execute();
			status ="Success";
		} catch (Exception e) {
			status ="Failure";
		}
		return status;
		
		
	}	*/
	
}

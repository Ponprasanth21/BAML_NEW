package com.bornfire.Services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.FinCMG;

@Service
@Repository
@Transactional
public class CustomerDao {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<FinCMG>  getCustList() {
		List<FinCMG> cnt1 = (List<FinCMG>) sessionFactory.getCurrentSession()
				.createQuery("from FinCMG where ROWNUM<=10").getResultList();
	
		return cnt1;
	}

}

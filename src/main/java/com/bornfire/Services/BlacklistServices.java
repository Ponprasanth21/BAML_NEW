package com.bornfire.Services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.BlackListEntity;


@Service
@Transactional
public class BlacklistServices {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(BlacklistServices.class);

	public List<BlackListEntity> getBankData() {
		
		logger.info("BankServices-->>getBankData()");
		
		Session hs = sessionFactory.getCurrentSession();
		
		List<BlackListEntity> BlackListEntityList = hs.createQuery("from BlackListEntity a where nvl(a.del_flg,'N') != 'Y'").getResultList();
		
		return BlackListEntityList;
	}
	
	
	public String detailChanges(BlackListEntity detail, Character changeType) {

		String msg = "";

		try {
			
			Session hs = sessionFactory.getCurrentSession();

			if (changeType.equals('A')) {
					detail.setDelflag("N");
				hs.saveOrUpdate(detail);
				logger.info("Added Record");
				msg = "Added Successfully";
			}else if (changeType.equals('D')) {
				
				
				detail.setDelflag("Y");
				hs.saveOrUpdate(detail);
				logger.info("Deleted Record");
				msg = "Deleted Successfully";
			}

		} catch (Exception e) {

			msg = "error occured. Please contact Administrator";
			e.printStackTrace();
		}

		return msg;
	}
	
	

}

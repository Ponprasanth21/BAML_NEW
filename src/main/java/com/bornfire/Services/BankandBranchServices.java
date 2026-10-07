
package com.bornfire.Services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bornfire.entity.BAMLSolEntity;
import com.bornfire.entity.BAMLSolRepository;

@Service
@Transactional
@ConfigurationProperties("output")
public class BankandBranchServices {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	BAMLSolRepository bamlSolRepository;

	private static final Logger logger = LoggerFactory.getLogger(BankandBranchServices.class);

	public String modDetails(BAMLSolEntity bamlSolEntity, String formmode) {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("edit")) {
			BAMLSolEntity up = bamlSolEntity;
			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			bamlSolRepository.save(up);
			msg = "Bank and Branch Details Modified Sucessfully";
		} else if (formmode.equals("verify")) {
			BAMLSolEntity up = bamlSolEntity;
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			up.setDel_flg("N");
			bamlSolRepository.save(up);
			msg = "Bank and Branch Details Verified  Sucessfully";
		}
		return msg;
	}
	
	public BAMLSolEntity getSolID(String solId) {

		BAMLSolEntity up = bamlSolRepository.findByIdcustom(solId);

		return up;

	}

	

}

package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.BAMLBatchJobSchedular;
import com.bornfire.entity.BAMLBatchJobSchedularRepository;

@Service
@Transactional
@ConfigurationProperties("output")
public class BatchJobServices {

	private static final Logger logger = LoggerFactory.getLogger(BatchJobServices.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	BAMLBatchJobSchedularRepository bamlBatchJobSchedularRepository;

	public String getSrlNoValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("00");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT BATCHJOB.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "JOB" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

	public String createJob(BAMLBatchJobSchedular bamlBatchJobSchedular, String formmode) {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("edit")) {
			BAMLBatchJobSchedular up = bamlBatchJobSchedular;
			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			bamlBatchJobSchedularRepository.save(up);
			msg = "BatchJob Schedular Modified Sucessfully";
		} if (formmode.equals("add")) {
			BAMLBatchJobSchedular up = bamlBatchJobSchedular;
			up.setEntity_flg("N");
			up.setModify_flg("Y");
			up.setDel_flg("N");
			bamlBatchJobSchedularRepository.save(up);
			msg = "BatchJob Schedular Added Sucessfully";
		} else if (formmode.equals("verify")) {
			BAMLBatchJobSchedular up = bamlBatchJobSchedular;
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			
			bamlBatchJobSchedularRepository.save(up);
			msg = "BatchJob Schedular Verified  Sucessfully";
		}else if (formmode.equals("delete")) {
			BAMLBatchJobSchedular up = bamlBatchJobSchedular;
			up.setEntity_flg("N");
			//up.setModify_flg("N");
			up.setDel_flg("Y");
			bamlBatchJobSchedularRepository.save(up);
			msg = "BatchJob Schedular Deleted  Sucessfully";
		}
		return msg;
	}

	public BAMLBatchJobSchedular getJobID(String jobId) {

		BAMLBatchJobSchedular up = bamlBatchJobSchedularRepository.findByIdcustom(jobId);

		return up;

	}

}

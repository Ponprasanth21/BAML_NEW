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

	    BigDecimal billNumber = (BigDecimal) hs
	            .createNativeQuery("SELECT nextval('\"AML\".\"BATCHJOB\"')")
	            .getSingleResult();

	    String serialno = "JOB" + numformate.format(billNumber);

	    System.out.println("billno" + serialno);

	    return serialno;
	}

	public String createJob(BAMLBatchJobSchedular bamlBatchJobSchedular, String formmode) {

		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		if (formmode.equals("edit")) {
			BAMLBatchJobSchedular existing = getJobID(bamlBatchJobSchedular.getJob_id());
			if (existing != null) {
				if ("N".equals(existing.getEntity_flg())) {
					return "Record is pending verification and cannot be modified!";
				}
				if (bamlBatchJobSchedular.getJob_desc() != null) existing.setJob_desc(bamlBatchJobSchedular.getJob_desc());
				if (bamlBatchJobSchedular.getScript_type() != null) existing.setScript_type(bamlBatchJobSchedular.getScript_type());
				if (bamlBatchJobSchedular.getScript_name() != null) existing.setScript_name(bamlBatchJobSchedular.getScript_name());
				if (bamlBatchJobSchedular.getParmeters() != null) existing.setParmeters(bamlBatchJobSchedular.getParmeters());
				if (bamlBatchJobSchedular.getConditions() != null) existing.setConditions(bamlBatchJobSchedular.getConditions());
				if (bamlBatchJobSchedular.getFrequency() != null) existing.setFrequency(bamlBatchJobSchedular.getFrequency());
				if (bamlBatchJobSchedular.getStart_day() != null) existing.setStart_day(bamlBatchJobSchedular.getStart_day());
				if (bamlBatchJobSchedular.getStart_date() != null) existing.setStart_date(bamlBatchJobSchedular.getStart_date());
				if (bamlBatchJobSchedular.getEnd_date() != null) existing.setEnd_date(bamlBatchJobSchedular.getEnd_date());
				if (bamlBatchJobSchedular.getSuspend_flg() != null) existing.setSuspend_flg(bamlBatchJobSchedular.getSuspend_flg());
				if (bamlBatchJobSchedular.getSuspend_date() != null) existing.setSuspend_date(bamlBatchJobSchedular.getSuspend_date());
				if (bamlBatchJobSchedular.getExec_process() != null) existing.setExec_process(bamlBatchJobSchedular.getExec_process());
				if (bamlBatchJobSchedular.getExec_freq() != null) existing.setExec_freq(bamlBatchJobSchedular.getExec_freq());
				if (bamlBatchJobSchedular.getLast_tried_date() != null) existing.setLast_tried_date(bamlBatchJobSchedular.getLast_tried_date());
				if (bamlBatchJobSchedular.getNext_due_date() != null) existing.setNext_due_date(bamlBatchJobSchedular.getNext_due_date());
				if (bamlBatchJobSchedular.getAlert_flg() != null) existing.setAlert_flg(bamlBatchJobSchedular.getAlert_flg());
				if (bamlBatchJobSchedular.getAler_user() != null) existing.setAler_user(bamlBatchJobSchedular.getAler_user());
				if (bamlBatchJobSchedular.getAlert_email_id() != null) existing.setAlert_email_id(bamlBatchJobSchedular.getAlert_email_id());
				if (bamlBatchJobSchedular.getAlert_mobile() != null) existing.setAlert_mobile(bamlBatchJobSchedular.getAlert_mobile());
				if (bamlBatchJobSchedular.getExec_status() != null) existing.setExec_status(bamlBatchJobSchedular.getExec_status());
				if (bamlBatchJobSchedular.getModify_user() != null) existing.setModify_user(bamlBatchJobSchedular.getModify_user());
				existing.setModify_time(new java.util.Date());
				existing.setEntity_flg("N");
				existing.setModify_flg("Y");
				existing.setDel_flg("N");
				bamlBatchJobSchedularRepository.save(existing);
			} else {
				BAMLBatchJobSchedular up = bamlBatchJobSchedular;
				up.setModify_time(new java.util.Date());
				up.setEntity_flg("N");
				up.setModify_flg("Y");
				up.setDel_flg("N");
				bamlBatchJobSchedularRepository.save(up);
			}
			msg = "BatchJob Schedular Modified Sucessfully";
		} else if (formmode.equals("add")) {
			BAMLBatchJobSchedular up = bamlBatchJobSchedular;
			up.setEntry_time(new java.util.Date());
			up.setEntity_flg("N");
			up.setModify_flg("N");
			up.setDel_flg("N");
			bamlBatchJobSchedularRepository.save(up);
			msg = "BatchJob Schedular Added Sucessfully";
		} else if (formmode.equals("verify")) {
			BAMLBatchJobSchedular existing = getJobID(bamlBatchJobSchedular.getJob_id());
			if (existing != null) {
				String verifyUser = bamlBatchJobSchedular.getVerify_user();
				String modifyUser = existing.getModify_user();
				String entryUser = existing.getEntry_user();
				if (modifyUser != null && !modifyUser.trim().isEmpty()) {
					if (modifyUser.trim().equalsIgnoreCase(verifyUser != null ? verifyUser.trim() : "")) {
						return "Same User Cannot Verify!";
					}
				} else if (entryUser != null && !entryUser.trim().isEmpty()) {
					if (entryUser.trim().equalsIgnoreCase(verifyUser != null ? verifyUser.trim() : "")) {
						return "Same User Cannot Verify!";
					}
				}
				existing.setVerify_user(verifyUser);
				existing.setVerify_time(new java.util.Date());
				existing.setEntity_flg("Y");
				existing.setModify_flg("N");
				existing.setDel_flg("N");
				bamlBatchJobSchedularRepository.save(existing);
			} else {
				BAMLBatchJobSchedular up = bamlBatchJobSchedular;
				up.setVerify_time(new java.util.Date());
				up.setEntity_flg("Y");
				up.setModify_flg("N");
				up.setDel_flg("N");
				bamlBatchJobSchedularRepository.save(up);
			}
			msg = "BatchJob Schedular Verified  Sucessfully";
		} else if (formmode.equals("delete")) {
			BAMLBatchJobSchedular existing = getJobID(bamlBatchJobSchedular.getJob_id());
			if (existing != null) {
				existing.setDel_flg("Y");
				bamlBatchJobSchedularRepository.save(existing);
			} else {
				BAMLBatchJobSchedular up = bamlBatchJobSchedular;
				up.setDel_flg("Y");
				bamlBatchJobSchedularRepository.save(up);
			}
			msg = "BatchJob Schedular Deleted  Sucessfully";
		}
		return msg;
	}

	public BAMLBatchJobSchedular getJobID(String jobId) {

		BAMLBatchJobSchedular up = bamlBatchJobSchedularRepository.findByIdcustom(jobId);

		return up;

	}

}

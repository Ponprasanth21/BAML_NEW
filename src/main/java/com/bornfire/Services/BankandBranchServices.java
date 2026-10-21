
package com.bornfire.Services;

import java.util.Date;
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
			BAMLSolEntity existing = getSolID(bamlSolEntity.getSol_id());
			if (existing != null) {
				if ("N".equals(existing.getEntity_flg())) {
					return "Record is pending verification and cannot be modified!";
				}
				if (bamlSolEntity.getBank_code() != null) existing.setBank_code(bamlSolEntity.getBank_code());
				if (bamlSolEntity.getBank_name() != null) existing.setBank_name(bamlSolEntity.getBank_name());
				if (bamlSolEntity.getBr_code() != null) existing.setBr_code(bamlSolEntity.getBr_code());
				if (bamlSolEntity.getSol_desc() != null) existing.setSol_desc(bamlSolEntity.getSol_desc());
				if (bamlSolEntity.getSol_type() != null) existing.setSol_type(bamlSolEntity.getSol_type());
				if (bamlSolEntity.getAddr_1() != null) existing.setAddr_1(bamlSolEntity.getAddr_1());
				if (bamlSolEntity.getAddr_2() != null) existing.setAddr_2(bamlSolEntity.getAddr_2());
				if (bamlSolEntity.getCity_code() != null) existing.setCity_code(bamlSolEntity.getCity_code());
				if (bamlSolEntity.getState_code() != null) existing.setState_code(bamlSolEntity.getState_code());
				if (bamlSolEntity.getCountry_code() != null) existing.setCountry_code(bamlSolEntity.getCountry_code());
				if (bamlSolEntity.getZip_code() != null) existing.setZip_code(bamlSolEntity.getZip_code());
				if (bamlSolEntity.getScreen_flg() != null) existing.setScreen_flg(bamlSolEntity.getScreen_flg());
				if (bamlSolEntity.getMonitor_flg() != null) existing.setMonitor_flg(bamlSolEntity.getMonitor_flg());
				if (bamlSolEntity.getEmail_alert_flg() != null) existing.setEmail_alert_flg(bamlSolEntity.getEmail_alert_flg());
				if (bamlSolEntity.getSms_alert_flg() != null) existing.setSms_alert_flg(bamlSolEntity.getSms_alert_flg());
				if (bamlSolEntity.getUser_alert_flg() != null) existing.setUser_alert_flg(bamlSolEntity.getUser_alert_flg());
				if (bamlSolEntity.getAlert_type() != null) existing.setAlert_type(bamlSolEntity.getAlert_type());
				if (bamlSolEntity.getAml_parm_1() != null) existing.setAml_parm_1(bamlSolEntity.getAml_parm_1());
				if (bamlSolEntity.getAml_parm_2() != null) existing.setAml_parm_2(bamlSolEntity.getAml_parm_2());
				if (bamlSolEntity.getAml_parm_3() != null) existing.setAml_parm_3(bamlSolEntity.getAml_parm_3());
				if (bamlSolEntity.getAml_parm_4() != null) existing.setAml_parm_4(bamlSolEntity.getAml_parm_4());
				if (bamlSolEntity.getModify_user() != null) existing.setModify_user(bamlSolEntity.getModify_user());
				existing.setModify_time(new Date());
				existing.setEntity_flg("N");
				existing.setModify_flg("Y");
				existing.setDel_flg("N");
				bamlSolRepository.save(existing);
			} else {
				BAMLSolEntity up = bamlSolEntity;
				up.setModify_time(new Date());
				up.setEntity_flg("N");
				up.setModify_flg("Y");
				up.setDel_flg("N");
				bamlSolRepository.save(up);
			}
			msg = "Bank and Branch Details Modified Sucessfully";
		} else if (formmode.equals("verify")) {
			BAMLSolEntity existing = getSolID(bamlSolEntity.getSol_id());
			if (existing != null) {
				String verifyUser = bamlSolEntity.getVerify_user();
				String modifyUser = existing.getModify_user();
				String entryUser = existing.getEntry_user();
				if (modifyUser != null && !modifyUser.trim().isEmpty()) {
					if (modifyUser.equalsIgnoreCase(verifyUser)) {
						return "Same User Cannot Verify!";
					}
				} else if (entryUser != null && !entryUser.trim().isEmpty()) {
					if (entryUser.equalsIgnoreCase(verifyUser)) {
						return "Same User Cannot Verify!";
					}
				}
				existing.setVerify_user(verifyUser);
				existing.setVerify_time(new Date());
				existing.setEntity_flg("Y");
				existing.setModify_flg("N");
				existing.setDel_flg("N");
				bamlSolRepository.save(existing);
			} else {
				BAMLSolEntity up = bamlSolEntity;
				up.setEntity_flg("Y");
				up.setModify_flg("N");
				up.setDel_flg("N");
				bamlSolRepository.save(up);
			}
			msg = "Bank and Branch Details Verified  Sucessfully";
		}
		return msg;
	}
	
	public BAMLSolEntity getSolID(String solId) {

		BAMLSolEntity up = bamlSolRepository.findByIdcustom(solId);

		return up;

	}

	

}

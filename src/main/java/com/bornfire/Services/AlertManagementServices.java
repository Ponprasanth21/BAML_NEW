package com.bornfire.Services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AMLUserAlertNotification;
import com.bornfire.entity.AMLUserAlertNotificationpojo;
import com.bornfire.entity.AMLUserAlertReposirtory;
import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;

@Service
@ConfigurationProperties("output")
@Transactional

public class AlertManagementServices {

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);
	@Autowired
	AlertManagementRepository alertrepository;

	@Autowired
	AMLUserAlertReposirtory amlUserAlertReposirtory;
	@Autowired
	SessionFactory sessionFactory;

	public String addAlert(AlertManagementEntity alertparam, String formmode, String userid) {
		// TODO Auto-generated method stub
		String msg = "";
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = null;
		try {
			Number1 = (BigDecimal) hs1.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
					.getSingleResult();
		} catch (Exception e) {
			Number1 = new BigDecimal(System.currentTimeMillis());
		}

		/* try { */

		if (formmode.equals("add")) {

			AlertManagementEntity up = alertparam;
			up.setEntry_user(userid);
			up.setEntry_time(new Date());
			up.setDel_flg("N");
			alertrepository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(userid);
			audit.setFunc_code("ALERT CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("ALERT_MGMT_TABLE");
			audit.setAudit_screen("ALERT PARAMETER CREATED");
			audit.setEvent_id(up.getSrl_no());
			audit.setEvent_name("ALERT PARAMETER");
			audit.setModi_details("ALERT CREATED");
			audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
			auditLocal.save(audit);

			msg = "User Created Successfully";

		}
		// When the user data modifed and submitted.

		else if (formmode.equals("edit")) {
			AlertManagementEntity existing = getSrlNo(alertparam.getSrl_no());
			if (existing != null && existing.getSrl_no() != null) {
				if ("N".equals(existing.getEntity_flg())) {
					return "Record is pending verification and cannot be modified!";
				}
				if (alertparam.getAlert_date() != null) existing.setAlert_date(alertparam.getAlert_date());
				if (alertparam.getAlert_code() != null) existing.setAlert_code(alertparam.getAlert_code());
				if (alertparam.getAlert_desc() != null) existing.setAlert_desc(alertparam.getAlert_desc());
				if (alertparam.getParam_1() != null) existing.setParam_1(alertparam.getParam_1());
				existing.setParam_value_1(alertparam.getParam_value_1());
				if (alertparam.getParam_2() != null) existing.setParam_2(alertparam.getParam_2());
				existing.setParam_value_2(alertparam.getParam_value_2());
				if (alertparam.getParam_3() != null) existing.setParam_3(alertparam.getParam_3());
				existing.setParam_value_3(alertparam.getParam_value_3());
				if (alertparam.getEmail_flg() != null) existing.setEmail_flg(alertparam.getEmail_flg());
				if (alertparam.getEmail_1() != null) existing.setEmail_1(alertparam.getEmail_1());
				if (alertparam.getEmail_2() != null) existing.setEmail_2(alertparam.getEmail_2());
				if (alertparam.getEmail_3() != null) existing.setEmail_3(alertparam.getEmail_3());
				if (alertparam.getEmail_user_1() != null) existing.setEmail_user_1(alertparam.getEmail_user_1());
				if (alertparam.getEmail_user_2() != null) existing.setEmail_user_2(alertparam.getEmail_user_2());
				if (alertparam.getEmail_user_3() != null) existing.setEmail_user_3(alertparam.getEmail_user_3());
				if (alertparam.getSms_flg() != null) existing.setSms_flg(alertparam.getSms_flg());
				existing.setMobile_1(alertparam.getMobile_1());
				existing.setMobile_2(alertparam.getMobile_2());
				existing.setMobile_3(alertparam.getMobile_3());
				if (alertparam.getMobile_user_1() != null) existing.setMobile_user_1(alertparam.getMobile_user_1());
				if (alertparam.getMobile_user_2() != null) existing.setMobile_user_2(alertparam.getMobile_user_2());
				if (alertparam.getMobile_user_3() != null) existing.setMobile_user_3(alertparam.getMobile_user_3());
				if (alertparam.getAlert_flg() != null) existing.setAlert_flg(alertparam.getAlert_flg());
				if (alertparam.getAlert_userid_1() != null) existing.setAlert_userid_1(alertparam.getAlert_userid_1());
				if (alertparam.getAlert_userid_2() != null) existing.setAlert_userid_2(alertparam.getAlert_userid_2());
				if (alertparam.getAlert_userid_3() != null) existing.setAlert_userid_3(alertparam.getAlert_userid_3());
				if (alertparam.getAlert_user_1() != null) existing.setAlert_user_1(alertparam.getAlert_user_1());
				if (alertparam.getAlert_user_2() != null) existing.setAlert_user_2(alertparam.getAlert_user_2());
				if (alertparam.getAlert_user_3() != null) existing.setAlert_user_3(alertparam.getAlert_user_3());
				if (alertparam.getStart_date() != null) existing.setStart_date(alertparam.getStart_date());
				if (alertparam.getEnd_date() != null) existing.setEnd_date(alertparam.getEnd_date());
				if (alertparam.getSuspend_flg() != null) existing.setSuspend_flg(alertparam.getSuspend_flg());
				existing.setSuspend_date(alertparam.getSuspend_date());
				existing.setActivate_date(alertparam.getActivate_date());
				if (alertparam.getRemarks() != null) existing.setRemarks(alertparam.getRemarks());

				existing.setModify_user(userid);
				existing.setModify_time(new Date());
				existing.setMod_flg("Y");
				existing.setEntity_flg("N");
				existing.setDel_flg("N");
				alertrepository.save(existing);
			} else {
				AlertManagementEntity up = alertparam;
				up.setModify_user(userid);
				up.setModify_time(new Date());
				up.setMod_flg("Y");
				up.setEntity_flg("N");
				up.setDel_flg("N");
				alertrepository.save(up);
			}

			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(userid);
			audit.setFunc_code("ALERT MODIFIED");
			audit.setRemarks("MODIFIED");
			audit.setAudit_table("ALERT_MGMT_TABLE");
			audit.setAudit_screen("ALERT PARAMETER MODIFY");
			audit.setEvent_id(alertparam.getSrl_no());
			audit.setEvent_name("ALERT PARAMETER");
			audit.setModi_details("ALERT MODIFIED");
			audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
			auditLocal.save(audit);

			msg = "Alert Parameter Modified Successfully";

		} else if (formmode.equals("verify")) {
			AlertManagementEntity existing = getSrlNo(alertparam.getSrl_no());
			if (existing != null && existing.getSrl_no() != null) {
				String verifyUser = userid;
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
				existing.setAuth_user(verifyUser);
				existing.setAuth_time(new Date());
				existing.setEntity_flg("Y");
				existing.setMod_flg("N");
				existing.setDel_flg("N");
				alertrepository.save(existing);
			} else {
				AlertManagementEntity up = alertparam;
				up.setAuth_user(userid);
				up.setAuth_time(new Date());
				up.setEntity_flg("Y");
				up.setMod_flg("N");
				up.setDel_flg("N");
				alertrepository.save(up);
			}

			msg = "Alert Parameter Verified Successfully";

		}

		else if (formmode.equals("delete")) {
			AlertManagementEntity up = alertparam;

			up.setDel_flg("Y");
			alertrepository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(userid);
			audit.setFunc_code("ALERT DELETED");
			audit.setRemarks("DELETED");
			audit.setAudit_table("BAML_ALERT_MGMT_TABLE");
			audit.setAudit_screen("ALERT PARAMETER DELETE");
			audit.setEvent_id(up.getSrl_no());
			audit.setEvent_name("ALERT PARAMETER");
			audit.setModi_details("ALERT REMOVED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);

			msg = "User Deleted Successfully";

		}

		return msg;
	}

	public String getSrlNoValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("000");
		BigDecimal billNumber = null;
		try {
			billNumber = (BigDecimal) hs.createNativeQuery("SELECT CAST(nextval('\"AML\".\"ALERTSEQUENCE_1\"') AS NUMERIC)")
					.getSingleResult();
		} catch (Exception e) {
			try {
				BigInteger cnt = (BigInteger) hs.createNativeQuery("SELECT COUNT(*) FROM \"AML\".\"BAML_ALERT_MGMT_TABLE\"").getSingleResult();
				billNumber = new BigDecimal(cnt.longValue() + 1);
			} catch (Exception ex) {
				billNumber = new BigDecimal(System.currentTimeMillis() % 1000);
			}
		}
		String serialno = "ALT" + numformate.format(billNumber);
		return serialno;
	}

	public AlertManagementEntity getSrlNo(String id) {
		AlertManagementEntity up = alertrepository.findByIdcustom(id);
		if (up != null) {
			return up;
		}
		if (alertrepository.existsById(id)) {
			return alertrepository.findById(id).get();
		} else {
			return new AlertManagementEntity();
		}
	}

	public String deletealert(String inputSrlNo) {
		String msg = "";
		Optional<AlertManagementEntity> user = alertrepository.findById(inputSrlNo);
		AlertManagementEntity reg = user.get();
		reg.setDel_flg("Y");
		alertrepository.save(reg);
		msg = "User Deleted Successfully";
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = null;
		try {
			Number1 = (BigDecimal) hs1.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
					.getSingleResult();
		} catch (Exception e) {
			Number1 = new BigDecimal(System.currentTimeMillis());
		}

		AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

		audit.setAudit_date(new Date());
		audit.setEntry_time(new Date());
		audit.setEntry_user(reg.getModify_user());
		audit.setFunc_code("RULE DELETED");
		audit.setRemarks("DELETED");
		audit.setAudit_table("MONITORING_PRM_TABLE");
		audit.setAudit_screen("RULE ENGINE DELETE");
		audit.setEvent_id(reg.getSrl_no());
		audit.setEvent_name("RULE ENGINE");
		audit.setModi_details("RECORD REMOVED");
		audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
		auditLocal.save(audit);

		return msg;
	}

	public List<AMLUserAlertNotificationpojo> getAlertAList(String date) throws ParseException {
		String user_alert_date = "";
		boolean currentDate = false;
		if (date == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			user_alert_date = dateFormat.format(new Date());
			currentDate = true;
			System.out.println("tggg3234");
		} else {
			System.out.println("vhjhj");
			user_alert_date = date;
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date enteredDate = dateFormat.parse(date);

			if (dateFormat.format(enteredDate).equals(dateFormat.format(new Date()))) {
				currentDate = true;
				System.out.println("inn");
			} else {
				currentDate = false;
				System.out.println("out");
			}
		}

		List<AMLUserAlertNotificationpojo> transationMonitorPojoList = new ArrayList<AMLUserAlertNotificationpojo>();

		if (currentDate) {
			List<AMLUserAlertNotification> tranMonitor = amlUserAlertReposirtory.findAllCustomAlert(user_alert_date);

			for (int i = 0; i < tranMonitor.size(); i++) {
				AMLUserAlertNotificationpojo amlUserAlertNotificationpojo = new AMLUserAlertNotificationpojo();
				amlUserAlertNotificationpojo.setModule_id(tranMonitor.get(i).getModule_id());
				amlUserAlertNotificationpojo.setMsg_type(tranMonitor.get(i).getMsg_type());
				amlUserAlertNotificationpojo.setMsg_date(tranMonitor.get(i).getMsg_date());
				amlUserAlertNotificationpojo.setMsg_status(tranMonitor.get(i).getMsg_status());
				amlUserAlertNotificationpojo.setUser_alert_sub(tranMonitor.get(i).getUser_alert_sub());
				amlUserAlertNotificationpojo.setUser_alert_body(tranMonitor.get(i).getUser_alert_body());
				amlUserAlertNotificationpojo.setAlert_type(tranMonitor.get(i).getAlert_type());

				amlUserAlertNotificationpojo.setUser_alert_srl_no(tranMonitor.get(i).getUser_alert_srl_no());

				transationMonitorPojoList.add(amlUserAlertNotificationpojo);
			}
		} else {
			List<AMLUserAlertNotification> tranMonitor = amlUserAlertReposirtory.findAllCustomAlert(user_alert_date);

			for (int i = 0; i < tranMonitor.size(); i++) {
				AMLUserAlertNotificationpojo amlUserAlertNotificationpojo = new AMLUserAlertNotificationpojo();
				amlUserAlertNotificationpojo.setModule_id(tranMonitor.get(i).getModule_id());
				amlUserAlertNotificationpojo.setMsg_type(tranMonitor.get(i).getMsg_type());
				amlUserAlertNotificationpojo.setMsg_date(tranMonitor.get(i).getMsg_date());
				amlUserAlertNotificationpojo.setMsg_status(tranMonitor.get(i).getMsg_status());
				amlUserAlertNotificationpojo.setUser_alert_sub(tranMonitor.get(i).getUser_alert_sub());
				amlUserAlertNotificationpojo.setUser_alert_body(tranMonitor.get(i).getUser_alert_body());
				amlUserAlertNotificationpojo.setAlert_type(tranMonitor.get(i).getAlert_type());

				amlUserAlertNotificationpojo.setUser_alert_srl_no(tranMonitor.get(i).getUser_alert_srl_no());

				transationMonitorPojoList.add(amlUserAlertNotificationpojo);

			}

		}
		return transationMonitorPojoList;

	}
	
	
	public List<AMLUserAlertNotificationpojo> getAlertIList(String date) throws ParseException {
		String user_alert_date = "";
		boolean currentDate = false;
		if (date == null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			user_alert_date = dateFormat.format(new Date());
			currentDate = true;
			System.out.println("tggg3234");
		} else {
			System.out.println("vhjhj");
			user_alert_date = date;
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date enteredDate = dateFormat.parse(date);

			if (dateFormat.format(enteredDate).equals(dateFormat.format(new Date()))) {
				currentDate = true;
				System.out.println("inn");
			} else {
				currentDate = false;
				System.out.println("out");
			}
		}

		List<AMLUserAlertNotificationpojo> transationMonitorPojoList = new ArrayList<AMLUserAlertNotificationpojo>();

		if (currentDate) {
			List<AMLUserAlertNotification> tranMonitor = amlUserAlertReposirtory.findAllCustomIAlert(user_alert_date);

			for (int i = 0; i < tranMonitor.size(); i++) {
				AMLUserAlertNotificationpojo amlUserAlertNotificationpojo = new AMLUserAlertNotificationpojo();
				amlUserAlertNotificationpojo.setModule_id(tranMonitor.get(i).getModule_id());
				amlUserAlertNotificationpojo.setMsg_type(tranMonitor.get(i).getMsg_type());
				amlUserAlertNotificationpojo.setMsg_date(tranMonitor.get(i).getMsg_date());
				amlUserAlertNotificationpojo.setMsg_status(tranMonitor.get(i).getMsg_status());
				amlUserAlertNotificationpojo.setUser_alert_sub(tranMonitor.get(i).getUser_alert_sub());
				amlUserAlertNotificationpojo.setUser_alert_body(tranMonitor.get(i).getUser_alert_body());
				amlUserAlertNotificationpojo.setAlert_type(tranMonitor.get(i).getAlert_type());

				amlUserAlertNotificationpojo.setUser_alert_srl_no(tranMonitor.get(i).getUser_alert_srl_no());

				transationMonitorPojoList.add(amlUserAlertNotificationpojo);
			}
		} else {
			List<AMLUserAlertNotification> tranMonitor = amlUserAlertReposirtory.findAllCustomIAlert(user_alert_date);

			for (int i = 0; i < tranMonitor.size(); i++) {
				AMLUserAlertNotificationpojo amlUserAlertNotificationpojo = new AMLUserAlertNotificationpojo();
				amlUserAlertNotificationpojo.setModule_id(tranMonitor.get(i).getModule_id());
				amlUserAlertNotificationpojo.setMsg_type(tranMonitor.get(i).getMsg_type());
				amlUserAlertNotificationpojo.setMsg_date(tranMonitor.get(i).getMsg_date());
				amlUserAlertNotificationpojo.setMsg_status(tranMonitor.get(i).getMsg_status());
				amlUserAlertNotificationpojo.setUser_alert_sub(tranMonitor.get(i).getUser_alert_sub());
				amlUserAlertNotificationpojo.setUser_alert_body(tranMonitor.get(i).getUser_alert_body());
				amlUserAlertNotificationpojo.setAlert_type(tranMonitor.get(i).getAlert_type());

				amlUserAlertNotificationpojo.setUser_alert_srl_no(tranMonitor.get(i).getUser_alert_srl_no());

				transationMonitorPojoList.add(amlUserAlertNotificationpojo);

			}

		}
		return transationMonitorPojoList;

	}

}
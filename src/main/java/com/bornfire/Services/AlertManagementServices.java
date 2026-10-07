package com.bornfire.Services;

import java.math.BigDecimal;
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

		BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

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
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);

			msg = "User Created Successfully";

		}
		// When the user data modifed and submitted.

		else if (formmode.equals("edit")) {
			AlertManagementEntity up = alertparam;

			up.setModify_user(userid);
			up.setModify_time(new Date());
			up.setMod_flg("Y");
			up.setDel_flg("N");
			alertrepository.save(up);/* } */

			msg = "User Edited Successfully";

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
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT ALERTSEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "ALT" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

	public AlertManagementEntity getSrlNo(String id) {
		/* logger.info(id); */
		if (alertrepository.existsById(id)) {
			System.out.println("getsrlno");
			AlertManagementEntity up = alertrepository.findById(id).get();

			return up;
		} else {
			return new AlertManagementEntity();
		}

	};

	public String deletealert(String inputSrlNo) {
		String msg = "";
		Optional<AlertManagementEntity> user = alertrepository.findById(inputSrlNo);
		AlertManagementEntity reg = user.get();
		reg.setDel_flg("Y");
		alertrepository.save(reg);
		msg = "User Deleted Successfully";
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

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
		audit.setAudit_ref_no(Number1.toString());
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
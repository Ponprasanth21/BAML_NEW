package com.bornfire.Services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.AMLAccessRole;
import com.bornfire.entity.AMLUserAlertNotification;
import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.AccessandRolesRepository;


@Service
@ConfigurationProperties("output")
@Transactional
public class AMLAccessRoleService {

	@Autowired
	AccessandRolesRepository accessandrolesrepository;

	@Autowired
	SessionFactory sessionFactory;
	

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	
	
	@SuppressWarnings("unchecked")
	public List<AMLAccessRole> gettingaccessDetails(String roleid) {

		List<AMLAccessRole> list = (List<AMLAccessRole>) sessionFactory.getCurrentSession()
				.createQuery("from AMLAccessRole where role_id ='" + roleid + "'").getResultList();
		System.out.print("df" + list);
		return list;

	}
	
	public String addPARAMETER(AMLAccessRole alertparam, String formmode, String adminValue, String inquiryValue, String monitoringValue,String listValue,String interfaceValue,String screenValue,String riskValue,String reportValue, String caseValue, String thirdpartyValue,String amlReportValue,String archivalValue,String Strreport,String Auditlog,String finalString,String USERID) {
		// TODO Auto-generated method stub
		String msg = "";
		
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		/* try { */
		if (formmode.equals("add")) {
			AMLAccessRole up = alertparam;
		
			up.setDel_flg("N");
			up.setModify_flg("Y");
			up.setEntity_flg("N");
			up.setAdmin(adminValue);
			System.out.println("admin value" + adminValue);
			up.setInquiry(inquiryValue);
			up.setMonitoring(monitoringValue);
			up.setList_management(listValue);
			up.setInterfaces(interfaceValue);
			up.setScreening(screenValue);
			up.setRisk_management(riskValue);
			up.setCase_management(caseValue);
			up.setThirdparty_transaction(thirdpartyValue);
			up.setAmlarchival(archivalValue);
			up.setAmlreport(amlReportValue);
			up.setAuditlog(Auditlog);
			up.setStrreport(Strreport);
			up.setEntry_user(USERID);
			up.setEntry_time(new Date());
			up.setReport(reportValue);
			up.setMenulist(finalString);
			accessandrolesrepository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(USERID);
			audit.setFunc_code("ROLE CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("BAML_ACCESS_ROLE_TABLE");
			audit.setAudit_screen("AML ACCESS AND ROLE");
			audit.setEvent_id(up.getRole_id());
			audit.setEvent_name("ACCESS AND ROLE");
			audit.setModi_details("ROLE CREATED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);

			msg = "Role Created Successfully";
		} else if (formmode.equals("edit")) {
			AMLAccessRole up = alertparam;
			Optional<AMLAccessRole> user = accessandrolesrepository.findById(alertparam.getRole_id());
			AMLAccessRole user1 = user.get();
			up.setAdmin(adminValue);
			up.setInquiry(inquiryValue);
			up.setMonitoring(monitoringValue);
			up.setList_management(listValue);
			up.setInterfaces(interfaceValue);
			up.setScreening(screenValue);
			up.setRisk_management(riskValue);
			up.setCase_management(caseValue);
			up.setThirdparty_transaction(thirdpartyValue);
			up.setAmlarchival(archivalValue);
			up.setAmlreport(amlReportValue);
			up.setAuditlog(Auditlog);
			up.setStrreport(Strreport);
			up.setCase_management(caseValue);
			up.setThirdparty_transaction(thirdpartyValue);
			up.setAmlarchival(archivalValue);
			up.setAmlreport(amlReportValue);
			up.setReport(reportValue);
			up.setMenulist(finalString);
			up.setDel_flg("N");
			up.setModify_flg("Y");
			up.setEntity_flg("N");
			up.setEntry_user(user1.getEntry_user());
			up.setEntry_time(user1.getEntry_time());
			up.setModify_user(USERID);
			up.setModify_time(new Date());
			accessandrolesrepository.save(up);
			msg = "Role Edited Successfully";
		} else if (formmode.equals("delete")) {
			AMLAccessRole up = alertparam;
			up.setDel_flg("Y");
			up.setEntity_flg("N");
			accessandrolesrepository.save(up);
			msg = "Role Deleted Successfully";
		} else if (formmode.equals("verify")) {
			 AML_AUDIT_LOCAL audit = auditLocal.getAuditVerifyUseraccess(alertparam.getRole_id());
			 Optional<AMLAccessRole> user = accessandrolesrepository.findById(alertparam.getRole_id());
				AMLAccessRole user1 = user.get();
			AMLAccessRole up = alertparam;
			up.setAdmin(adminValue);
			up.setInquiry(inquiryValue);
			up.setMonitoring(monitoringValue);
			up.setList_management(listValue);
			up.setInterfaces(interfaceValue);
			up.setScreening(screenValue);
			up.setRisk_management(riskValue);
			up.setCase_management(caseValue);
			up.setThirdparty_transaction(thirdpartyValue);
			up.setAmlarchival(archivalValue);
			up.setAmlreport(amlReportValue);
			up.setReport(reportValue);
			up.setCase_management(caseValue);
			up.setThirdparty_transaction(thirdpartyValue);
			up.setAmlarchival(archivalValue);
			up.setAmlreport(amlReportValue);
			up.setAuditlog(Auditlog);
			up.setStrreport(Strreport);
			up.setMenulist(finalString);
			up.setEntity_flg("Y");
			up.setDel_flg("N");
			up.setAuth_user(USERID);
			up.setAuth_time(new Date());
			up.setEntry_user(user1.getEntry_user());
			up.setEntry_time(user1.getEntry_time());
			up.setModify_user(user1.getModify_user());
			up.setModify_time(user1.getModify_time());
			accessandrolesrepository.save(up);
			if (audit.getRemarks().equals("ADDED")) {
				System.out.println("ROLE ADDED");
				audit.setAudit_date(new Date());
				audit.setAudit_table("BAML_ACCESS_ROLE_TABLE");
				audit.setAudit_screen("ACCESS AND ROLE - CREATION");
				audit.setFunc_code("ROLE VERIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getRole_id());
				audit.setEvent_name("ACCESS AND ROLE");
				audit.setModi_details(audit.getModi_details());
				audit.setAuth_user(USERID);
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
			}else if (audit.getRemarks().equals("MODIFIED")) {
				System.out.println("ROLE MODIFIED");
				audit.setAudit_date(new Date());
				audit.setAudit_table("BAML_ACCESS_ROLE_TABLE");
				audit.setAudit_screen("ACCESS AND ROLE- MODIFIED");
				audit.setFunc_code("ROLE MODIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getRole_id());
				audit.setEvent_name("ACCESS AND ROLE");
				audit.setModi_details(audit.getModi_details());
				audit.setAuth_user(USERID);
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
			}else if(audit.getRemarks().equals("VERIFIED")) {
				System.out.println("ROLE VERIFIED");
					audit.setAudit_date(new Date());
					audit.setAudit_table("BAML_ACCESS_ROLE_TABLE");
					audit.setAudit_screen("ACCESS AND ROLE - CREATION");
					audit.setFunc_code("ROLE VERIFIED");
					audit.setRemarks("VERIFIED");
					audit.setEvent_id(up.getRole_id());
					audit.setEvent_name("ACCESS AND ROLE");
					audit.setModi_details(audit.getModi_details());
					audit.setAuth_user(USERID);
					audit.setAuth_time(new Date());
					audit.setAudit_ref_no(audit.getAudit_ref_no());
				
			}
			auditLocal.save(audit);
			msg = "Role Verified Successfully";
		}
		return msg;
	}
	
	
public AMLAccessRole getRoleId(String id) {
		
		System.out.println("getsrlno");
		Session session = sessionFactory.getCurrentSession();
		Query<AMLAccessRole> query = session
				.createQuery(" from AMLAccessRole where role_id=?1 ",AMLAccessRole.class);
		query.setParameter(1, id);
		
		System.out.println(id);
		
		List<AMLAccessRole> result =  query.getResultList();

		

		if (!result.isEmpty()) {
			
			return result.get(0);
		} else {
			
			return new AMLAccessRole();
		}

	};

	

public AMLUserAlertNotification getUserAlert(String id) {
		
		
		Session session = sessionFactory.getCurrentSession();
		Query<AMLUserAlertNotification> query = session
				.createQuery("select user_alert_sub from AMLUserAlertNotification ",AMLUserAlertNotification.class);
		
		
		/*
		 * List<AMLAccessRole> result = query.getResultList();
		 * 
		 * 
		 * 
		 * if (!result.isEmpty()) {
		 * 
		 * return result.get(0); } else {
		 */
			
			return new AMLUserAlertNotification();
		}

	;
public AMLAccessRole getRoleMenu(String id) {
		
		
		Session session = sessionFactory.getCurrentSession();
		Query<AMLAccessRole> query = session
				.createQuery(" from AMLAccessRole where role_id=?1 ",AMLAccessRole.class);
		query.setParameter(1, id);
		
		
		
		List<AMLAccessRole> result =  query.getResultList();

		

		if (!result.isEmpty()) {
			
			return result.get(0);
		} else {
			
			return new AMLAccessRole();
		}

	};
	public String deleteRole(String userid) {
		System.out.println("amlaccess deleteRole");
		Session hs = sessionFactory.getCurrentSession();
		Query qr;
		qr = hs.createQuery(
				"select count(*) from UserProfile where role_id= ?1");
		qr.setParameter(1, userid);
		long count = (long) qr.getSingleResult();
		System.out.println("count" + count);
		
		String msg = "";
		
		if(count == 0) {
			Optional<AMLAccessRole> user= accessandrolesrepository.findById(userid);
			 AMLAccessRole reg = user.get();       
				reg.setDel_flg("Y");
				accessandrolesrepository.save(reg);
				  msg = "Role Deleted Successfully";
		} else {
			msg = "This role has been assigned to an User.Cannot Delete ";
		}
		
		 System.out.println("SERVICE "+msg);
		return msg;
	}
}
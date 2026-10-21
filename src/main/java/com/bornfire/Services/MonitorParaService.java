package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.BAML_AUDIT_ENTITY;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.bornfire.entity.MONITOREP;
import com.bornfire.entity.MONITORINGPARAMETERENTIRY;
import com.bornfire.entity.Monitoringparameter;
import com.bornfire.entity.MontParameterRepository;

@Service
@ConfigurationProperties("output")
@Transactional
public class MonitorParaService {

	@Autowired
	MontParameterRepository montParameterRepository;

	@Autowired
	MONITOREP montREP;
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	EMAIL email;

	@Autowired
	EMAILREP emailRep;

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;

	public String addPARAMETER(Monitoringparameter alertparam, String formmode, String Userid) {
		// TODO Auto-generated method stub
		String msg = "";
		/* try { */
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();


		if (formmode.equals("add")) {
			Monitoringparameter up = alertparam;

			Session hs = sessionFactory.getCurrentSession();

			String ruleno = "";
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT RULESEQUENCE_1.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();

			System.out.println("RULE000" + Number);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

			

			if (Number.toString() != "") {
				DecimalFormat numformate = new DecimalFormat("000");
				ruleno = "RULE" + numformate.format(Number);
				up.setSrl_no(ruleno.toString());
				hs.save(up);
				up.setDel_flag("N");
				up.setEntity_flag("N");
				up.setEntry_user(Userid);
				montParameterRepository.save(up);
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(up.getEntry_user());
				audit.setFunc_code("RULE CREATED");
				audit.setRemarks("ADDED");
				audit.setAudit_table("BAML_RULE_ENGINE_TABLE");
				audit.setAudit_screen("RULE ENGINE CREATED");
				audit.setEvent_id(up.getSrl_no());
				audit.setEvent_name("RULE ENGINE");
				audit.setModi_details("RECORD CREATED");
				audit.setAudit_ref_no(Number1.toString());
				auditLocal.save(audit);

			}
			msg = "Parameter Created Successfully";
		} else if (formmode.equals("edit")) {
			Monitoringparameter up = alertparam;
			up.setDel_flag("N");
			up.setModify_flag("Y");
			up.setEntity_flag("N");
			up.setModify_user(Userid);
			montParameterRepository.save(up);
			
			Session hs = sessionFactory.getCurrentSession();
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();


				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(up.getEntry_user());
				audit.setFunc_code("RULE MODIFIED");
				audit.setRemarks("MODIFIED");
				audit.setAudit_table("BAML_RULE_ENGINE_TABLE");
				audit.setAudit_screen("RULE ENGINE MODIFIED");
				audit.setEvent_id(up.getSrl_no());
				audit.setEvent_name("RULE ENGINE");
				audit.setModi_details("RECORD MODIFIED");
				audit.setAudit_ref_no(Number1.toString());
				auditLocal.save(audit);

		

			msg = "Parameter Edited Successfully";
		} else if (formmode.equals("delete")) {
			Monitoringparameter up = alertparam;
			up.setDel_flag("Y");
			up.setEntity_flag("N");
			up.setModify_user(Userid);
			montParameterRepository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(up.getModify_user());
			audit.setFunc_code("RULE DELETED");
			audit.setRemarks("DELETED");
			audit.setAudit_table("BAML_RULE_ENGINE_TABLE");
			audit.setAudit_screen("RULE ENGINE DELETE");
			audit.setEvent_id(up.getSrl_no());
			audit.setEvent_name("RULE ENGINE");
			audit.setModi_details("RECORD REMOVED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);
			msg = "Parameter Deleted Successfully";
		} else if (formmode.equals("verify")) {
			 AML_AUDIT_LOCAL audit = auditLocal.getAuditVerifyMONITORING(alertparam.getSrl_no());
			 
			Monitoringparameter up = alertparam;
			up.setEntity_flag("Y");
			up.setDel_flag("N");
			if(!(up.getModify_user()).equals(Userid)) {
			montParameterRepository.save(up);
			if (audit.getRemarks().equals("ADDED")) {
				audit.setAudit_date(new Date());
				audit.setAudit_table("BAML_RULE_ENGINE_TABLE");
				audit.setAudit_screen("RULE ENGINE - CREATION");
				audit.setFunc_code("RULE VERIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getSrl_no());
				audit.setEvent_name("RULE ENGINE");
				audit.setModi_details(audit.getModi_details());
				audit.setAuth_user(up.getVerify_user());
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
			}else if (audit.getRemarks().equals("MODIFIED")) {
				audit.setAudit_date(new Date());
				audit.setAudit_table("BAML_RULE_ENGINE_TABLE");
				audit.setAudit_screen("RULE ENGINE - MODIFIED");
				audit.setFunc_code("RULE MODIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getSrl_no());
				audit.setEvent_name("RULE ENGINE");
				audit.setModi_details(audit.getModi_details());
				audit.setAuth_user(up.getVerify_user());
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
			}
			
			msg = "Parameter Verified Successfully";
		}else {
			msg = "Same User Cannot Verify!";}
		}
		
		return msg;
	}

	public Monitoringparameter getSrlNo(String id) {

		if (montParameterRepository.existsById(id)) {
			System.out.println("getsrlno");
			Monitoringparameter up = montParameterRepository.findByIdCustom(id);

			return up;
		} else {
			return new Monitoringparameter();
		}

	};

	public MONITORINGPARAMETERENTIRY getSrlNo1(String id) {
		if (id != null && !id.trim().isEmpty()) {
			try {
				MONITORINGPARAMETERENTIRY up = montREP.findByIdCustom(id.trim());
				if (up != null && up.getRef_no() != null) {
					return up;
				}
			} catch (Exception e) {
				System.out.println("findByIdCustom error: " + e.getMessage());
			}
			try {
				java.util.Optional<MONITORINGPARAMETERENTIRY> opt = montREP.findById(id.trim());
				if (opt.isPresent()) {
					return opt.get();
				}
			} catch (Exception e) {
				System.out.println("findById error: " + e.getMessage());
			}
		}
		return new MONITORINGPARAMETERENTIRY();
	};

	public String EDITPARAMETER(MONITORINGPARAMETERENTIRY alertparam, String formmode,String Userid,String user1) {
		// TODO Auto-generated method stub
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = null;
		try {
			Number1 = (BigDecimal) hs1.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
					.getSingleResult();
		} catch (Exception e) {
			Number1 = new BigDecimal(System.currentTimeMillis());
		}

		String msg = "";
		/* try { */
		if (formmode.equals("add")) {
			MONITORINGPARAMETERENTIRY up = alertparam;

			Session hs = sessionFactory.getCurrentSession();

			String ruleno = "";
			BigDecimal Number = (BigDecimal) hs.createNativeQuery("SELECT COUNT(*) FROM \"AML\".\"BAML_MON_ALERT_PARAM\"")
					.getSingleResult();

			

			if (Number.toString() != "") {
				DecimalFormat numformate = new DecimalFormat("00");
				ruleno = numformate.format(Number);
				up.setRef_no(ruleno.toString());
				hs.save(up);
				up.setDel_flg("N");
				up.setEntity_flg("N");
				montREP.save(up);
				AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(Userid);
				audit.setFunc_code("MONITORING PARAMETER CREATED");
				audit.setRemarks("ADDED");
				audit.setAudit_table("BAML_MON_ALERT_PARAM");
				audit.setAudit_screen("PARAMETER CREATED");
				audit.setEvent_id(up.getRef_no());
				audit.setEvent_name("MONITORING PARAMETER");
				audit.setModi_details("PARAMETER CREATED");
				audit.setAudit_ref_no(Number1.toString());
				auditLocal.save(audit);
			}
			msg = "Parameter Created Successfully";
		}else if (formmode.equals("modify")) {
			MONITORINGPARAMETERENTIRY existing = getSrlNo1(alertparam.getRef_no());
			if (existing != null && existing.getRef_no() != null) {
				if ("N".equals(existing.getEntity_flg())) {
					return "Record is pending verification and cannot be modified!";
				}
				if (alertparam.getMenu() != null) existing.setMenu(alertparam.getMenu());
				if (alertparam.getValue1() != null) existing.setValue1(alertparam.getValue1());
				if (alertparam.getTable1() != null) existing.setTable1(alertparam.getTable1());
				if (alertparam.getTable_field() != null) existing.setTable_field(alertparam.getTable_field());
				if (alertparam.getUser_id_one() != null) existing.setUser_id_one(alertparam.getUser_id_one());
				if (alertparam.getUser_name_one() != null) existing.setUser_name_one(alertparam.getUser_name_one());
				if (alertparam.getUser_mobile_one() != null) existing.setUser_mobile_one(alertparam.getUser_mobile_one());
				if (alertparam.getUser_email_one() != null) existing.setUser_email_one(alertparam.getUser_email_one());
				if (alertparam.getUser_id_two() != null) existing.setUser_id_two(alertparam.getUser_id_two());
				if (alertparam.getUser_name_two() != null) existing.setUser_name_two(alertparam.getUser_name_two());
				if (alertparam.getUser_mobile_two() != null) existing.setUser_mobile_two(alertparam.getUser_mobile_two());
				if (alertparam.getUser_email_two() != null) existing.setUser_email_two(alertparam.getUser_email_two());
				if (alertparam.getStart_date() != null) existing.setStart_date(alertparam.getStart_date());
				if (alertparam.getEnd_date() != null) existing.setEnd_date(alertparam.getEnd_date());
				if (alertparam.getRemarks() != null) existing.setRemarks(alertparam.getRemarks());

				existing.setDel_flg("N");
				existing.setModify_flg("Y");
				existing.setEntity_flg("N");
				existing.setModify_user(Userid);
				existing.setModify_time(new Date());
				montREP.save(existing);

				try {
					AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
					audit.setAudit_date(new Date());
					audit.setEntry_time(new Date());
					audit.setEntry_user(Userid);
					audit.setFunc_code("MONITORING PARAMETER MODIFIED");
					audit.setRemarks("MODIFIED");
					audit.setAudit_table("BAML_MON_ALERT_PARAM");
					audit.setAudit_screen("MONITORING PARAMETER MODIFY");
					audit.setEvent_id(existing.getRef_no());
					audit.setEvent_name("MONITORING PARAMETER");
					audit.setModi_details("PARAMETER MODIFIED");
					audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
					auditLocal.save(audit);
				} catch (Exception e) {
					System.out.println("Audit save error: " + e.getMessage());
				}

				try {
					EmailAlert cv = new EmailAlert();
					cv.setEmail_srl_no(Number1 != null ? Number1 : new BigDecimal(System.currentTimeMillis()));
					cv.setEmail_id(user1);
					cv.setEmail_sub("MONITORING PARAMETER CHANGES");
					cv.setEmail_body("OPERATIONS PARAMETER " + existing.getRef_no() + " MODIFIED");
					cv.setEmail_date(new Date());
					cv.setSend_flg("N");
					cv.setPhone_num(new BigDecimal(0));
					cv.setCuntry_code(new BigDecimal(0));
					emailRep.save(cv);
				} catch (Exception e) {
					System.out.println("Email alert error: " + e.getMessage());
				}
			} else {
				MONITORINGPARAMETERENTIRY up = alertparam;
				up.setDel_flg("N");
				up.setModify_flg("Y");
				up.setEntity_flg("N");
				up.setModify_user(Userid);
				up.setModify_time(new Date());
				montREP.save(up);
			}

			msg = "Parameter Edited Successfully";
		} else if (formmode.equals("delete")) {
			MONITORINGPARAMETERENTIRY up = alertparam;
			up.setDel_flg("Y");
			up.setEntity_flg("N");
			montREP.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();

			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(up.getModify_user());
			audit.setFunc_code("MONITORING PARAMETER DELETED");
			audit.setRemarks("DELETED");
			audit.setAudit_table("BAML_MON_ALERT_PARAM");
			audit.setAudit_screen("MONITORING PARAMETER DELETE");
			audit.setEvent_id(up.getRef_no());
			audit.setEvent_name("MONITORING PARAMETER");
			audit.setModi_details("RECORD REMOVED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);

			msg = "Parameter Deleted Successfully";
		} else if (formmode.equals("verify")) {
			MONITORINGPARAMETERENTIRY existing = getSrlNo1(alertparam.getRef_no());
			if (existing != null && existing.getRef_no() != null) {
				String verifyUser = Userid;
				String modifyUser = existing.getModify_user();
				String entryUser = existing.getEntry_user();
				if (modifyUser != null && !modifyUser.trim().isEmpty()) {
					if (modifyUser.trim().equalsIgnoreCase(verifyUser.trim())) {
						return "Same User Cannot Verify!";
					}
				} else if (entryUser != null && !entryUser.trim().isEmpty()) {
					if (entryUser.trim().equalsIgnoreCase(verifyUser.trim())) {
						return "Same User Cannot Verify!";
					}
				}

				existing.setVerify_user(verifyUser);
				existing.setVerify_time(new Date());
				existing.setEntity_flg("Y");
				existing.setModify_flg("N");
				existing.setDel_flg("N");
				montREP.save(existing);

				try {
					AML_AUDIT_LOCAL audit = auditLocal.getAuditVerifyUser(alertparam.getRef_no());
					if (audit != null) {
						if ("ADDED".equals(audit.getRemarks())) {
							audit.setAudit_date(new Date());
							audit.setAudit_table("BAML_MON_ALERT_PARAM");
							audit.setAudit_screen("MONITORING PARAMETER - CREATION");
							audit.setFunc_code("PARAMETER VERIFIED");
							audit.setRemarks("VERIFIED");
							audit.setEvent_id(existing.getRef_no());
							audit.setEvent_name("MONITORING PARAMETER");
							audit.setModi_details(audit.getModi_details());
							audit.setAuth_user(verifyUser);
							audit.setAuth_time(new Date());
							audit.setAudit_ref_no(audit.getAudit_ref_no());
							auditLocal.save(audit);
						} else if ("MODIFIED".equals(audit.getRemarks())) {
							audit.setAudit_date(new Date());
							audit.setAudit_table("BAML_MON_ALERT_PARAM");
							audit.setAudit_screen("MONITORING PARAMETER - MODIFIED");
							audit.setFunc_code("PARAMETER MODIFIED");
							audit.setRemarks("VERIFIED");
							audit.setEvent_id(existing.getRef_no());
							audit.setEvent_name("MONITORING PARAMETER");
							audit.setModi_details(audit.getModi_details());
							audit.setAuth_user(verifyUser);
							audit.setAuth_time(new Date());
							audit.setAudit_ref_no(audit.getAudit_ref_no());
							auditLocal.save(audit);
						}
					}
				} catch (Exception e) {
					System.out.println("Audit verify error: " + e.getMessage());
				}

				msg = "Parameter Verified Successfully";
			} else {
				msg = "Record Not Found!";
			}
		}
		return msg;
	}

	public String deleteParameter(String inputSrlNo) {
		System.out.println("hhhhhh");
		String msg = "";
		Optional<Monitoringparameter> user = montParameterRepository.findById(inputSrlNo);
		System.out.println(inputSrlNo);
		Monitoringparameter reg = user.get();
		reg.setDel_flag("N");
		/* montParameterRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String inputSrlNo) {
		System.out.println("hhhhhh");
		String msg = "";
		Optional<Monitoringparameter> user = montParameterRepository.findById(inputSrlNo);
		System.out.println(inputSrlNo);
		Monitoringparameter reg = user.get();

		reg.setDel_flag("N");
		/* montParameterRepository.save(reg); */
		msg = "User Deleted Successfully";
		return msg;
	}

	@SuppressWarnings("unchecked")
	public String getruletypedesc(String rulecode, String rulesubcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(
				"select distinct(rule_type_desc) from BAML_RULE_ENGINE_TABLE where rule_type=?1 and rule_sub_type=?2 ");
		query.setParameter(1, rulecode);
		query.setParameter(2, rulesubcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getrulecodedesc(String rulecode) {
		String result ="";
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select ref_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'MONITORING RULE' and REF_REC_TYPE = 'MONR' and ref_code = ?1  ");
		query.setParameter(1, rulecode);

		/*Query query1 = session
				.createNativeQuery("select count(*) from MONITORING_PRM_TABLE where rule_code = ?1  ");
		query1.setParameter(1, rulecode);
		BigDecimal result1 = (BigDecimal) query1.getSingleResult();
		System.out.println(result1);*/
		String count = montREP.getrulecount(rulecode);
		System.out.println(count);
		if(count.equals("0")){
			result = (String) query.getSingleResult();
			System.out.println();
		}else {
			System.out.println();
			 result = "Failure";
		}
		System.out.println(result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String getScriptdesc(String rulecode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select REF_desc from BAML_REFERENCE_CODE_TABLE where REF_REC_desc = 'SCRIPT TYPE' and REF_REC_TYPE = 'SCR' AND REF_CODE = ?1  ");
		query.setParameter(1, rulecode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public String RuleCodedescselect(String rulecode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select rule_code,rule_code_desc from BAML_RULE_ENGINE_TABLE where rec_type=?1");
		query.setParameter(1, rulecode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Monitoringparameter> GetRuleTypeselect(String rulecode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct(rule_sub_type) from BAML_RULE_ENGINE_TABLE where rule_type=?1  ");
		query.setParameter(1, rulecode);

		List<Monitoringparameter> result = (List<Monitoringparameter>) query.getResultList();

		System.out.println(result);
		return result;
	}
	
	public List<BAML_AUDIT_ENTITY> getAuditLog(String acct_num) {
		Session hs = sessionFactory.getCurrentSession();

		List<BAML_AUDIT_ENTITY> ls = hs.createQuery(
				"from BAML_AUDIT_TABLE where foracid =?1",
				BAML_AUDIT_ENTITY.class).setParameter(1, acct_num).getResultList();

		return ls;
	}
	

}
package com.bornfire.Services;
/*  
 * 
 * Author : vijay corda
 * 
 * */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.Aml_Scr_Parm_Cust_Type_Entity;
import com.bornfire.entity.Aml_Scr_Parm_Cust_Type_Repository;


@Service
@ConfigurationProperties("output")
@Transactional
public class Aml_Scr_Parm_Cust_Type_Service {

	@Autowired
	Aml_Scr_Parm_Cust_Type_Repository aml_scr_parm_cust_type_Repository;
	

	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;
	
	@Autowired
	SessionFactory sessionFactory;

	public String addBlack_IND_List(Aml_Scr_Parm_Cust_Type_Entity alertparam, String formmode ,String USERID) {
		String msg = "";
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = null;
		try {
			Number1 = (BigDecimal) hs1.createNativeQuery("SELECT CAST(nextval('\"AML\".\"AML_AUDIT_SEQ\"') AS NUMERIC)")
					.getSingleResult();
		} catch (Exception e) {
			Number1 = new BigDecimal(System.currentTimeMillis());
		}

		if (formmode.equals("Ind_add")) {
			Aml_Scr_Parm_Cust_Type_Entity up = alertparam;
			DecimalFormat numformate = new DecimalFormat("000");
			Session hs = sessionFactory.getCurrentSession();
			if (up.getRef_no() == null || up.getRef_no().trim().isEmpty()) {
				try {
					BigInteger cnt = (BigInteger) hs.createNativeQuery("SELECT COUNT(*) FROM \"AML\".\"BAML_SCR_PARM_CUST_TYPE\"").getSingleResult();
					up.setRef_no("CON" + numformate.format(cnt.longValue() + 1));
				} catch (Exception e) {
					up.setRef_no("CON" + System.currentTimeMillis() % 1000);
				}
			}
		
			up.setDel_flag("N");
			up.setEntity_flag("N");
			up.setModify_flag("N");
			up.setAml_entry_user(USERID);
			up.setAml_entry_time(new Date());
			aml_scr_parm_cust_type_Repository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(USERID);
			audit.setFunc_code("PARAMETER CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
			audit.setAudit_screen("CUSTOMER TYPE SCREENING PARAMETER");
			audit.setEvent_id(up.getCust_id());
			audit.setEvent_name(up.getRef_no());
			audit.setModi_details("PARAMETER CREATED");
			audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
			auditLocal.save(audit);

			msg = "Customer Type Created Successfully";

		} else if (formmode.equals("Ind_edit")) {
			Aml_Scr_Parm_Cust_Type_Entity existing = getSrlNo(alertparam.getRef_no());
			if (existing != null && existing.getRef_no() != null) {
				if ("N".equalsIgnoreCase(existing.getEntity_flag())) {
					return "Record is pending verification and cannot be modified!";
				}
				if (alertparam.getCrncy_code() != null) existing.setCrncy_code(alertparam.getCrncy_code());
				if (alertparam.getLoan_limit() != null) existing.setLoan_limit(alertparam.getLoan_limit());
				if (alertparam.getCust_type() != null) existing.setCust_type(alertparam.getCust_type());
				if (alertparam.getCust_sub_type() != null) existing.setCust_sub_type(alertparam.getCust_sub_type());
				if (alertparam.getSchm_code() != null) existing.setSchm_code(alertparam.getSchm_code());
				if (alertparam.getGl_sub_head_code() != null) existing.setGl_sub_head_code(alertparam.getGl_sub_head_code());
				if (alertparam.getCust_id() != null) existing.setCust_id(alertparam.getCust_id());
				if (alertparam.getParameters() != null) existing.setParameters(alertparam.getParameters());
				if (alertparam.getStart_date() != null) existing.setStart_date(alertparam.getStart_date());
				if (alertparam.getEnd_date() != null) existing.setEnd_date(alertparam.getEnd_date());
				if (alertparam.getRemarks1() != null) existing.setRemarks1(alertparam.getRemarks1());
				if (alertparam.getRemarks2() != null) existing.setRemarks2(alertparam.getRemarks2());

				existing.setDel_flag("N");
				existing.setModify_flag("Y");
				existing.setEntity_flag("N");
				existing.setAml_modify_user(USERID);
				existing.setAml_modify_time(new Date());
				aml_scr_parm_cust_type_Repository.save(existing);

				try {
					AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
					audit.setAudit_date(new Date());
					audit.setEntry_time(new Date());
					audit.setEntry_user(USERID);
					audit.setFunc_code("PARAMETER MODIFIED");
					audit.setRemarks("MODIFIED");
					audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
					audit.setAudit_screen("CUSTOMER TYPE SCREENING PARAMETER");
					audit.setEvent_id(existing.getCust_id());
					audit.setEvent_name(existing.getRef_no());
					audit.setModi_details("PARAMETER MODIFIED");
					audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
					auditLocal.save(audit);
				} catch (Exception e) {
					System.out.println("Audit save error: " + e.getMessage());
				}
				msg = "Customer Type Edited Successfully";
			} else {
				msg = "Record not found!";
			}
		} else if (formmode.equals("Ind_delete")) {
			Aml_Scr_Parm_Cust_Type_Entity existing = getSrlNo(alertparam.getRef_no());
			if (existing != null && existing.getRef_no() != null) {
				existing.setDel_flag("Y");
				existing.setEntity_flag("N");
				aml_scr_parm_cust_type_Repository.save(existing);
				AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
				audit.setAudit_date(new Date());
				audit.setEntry_time(new Date());
				audit.setEntry_user(USERID);
				audit.setFunc_code("PARAMETER REMOVED");
				audit.setRemarks("REMOVED");
				audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
				audit.setAudit_screen("CUSTOMER TYPE SCREENING PARAMETER");
				audit.setEvent_id(existing.getCust_id());
				audit.setEvent_name(existing.getRef_no());
				audit.setModi_details("PARAMETER DELETED");
				audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
				auditLocal.save(audit);
				msg = "Customer Type Deleted Successfully";
			} else {
				msg = "Record not found!";
			}
		} else if (formmode.equals("Ind_verify")) {
			Aml_Scr_Parm_Cust_Type_Entity existing = getSrlNo(alertparam.getRef_no());
			if (existing != null && existing.getRef_no() != null) {
				String verifyUser = USERID;
				String modifyUser = existing.getAml_modify_user();
				String entryUser = existing.getAml_entry_user();
				if (modifyUser != null && !modifyUser.trim().isEmpty()) {
					if (modifyUser.trim().equalsIgnoreCase(verifyUser.trim())) {
						return "Same User Cannot Verify!";
					}
				} else if (entryUser != null && !entryUser.trim().isEmpty()) {
					if (entryUser.trim().equalsIgnoreCase(verifyUser.trim())) {
						return "Same User Cannot Verify!";
					}
				}

				existing.setEntity_flag("Y");
				existing.setModify_flag("N");
				existing.setDel_flag("N");
				existing.setAml_verify_user(verifyUser);
				existing.setAml_verify_time(new Date());
				aml_scr_parm_cust_type_Repository.save(existing);

				try {
					AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
					audit.setAudit_date(new Date());
					audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
					audit.setAudit_screen("CUSTOMER TYPE SCREENING PARAMETER");
					audit.setFunc_code("PARAMETER MODIFIED");
					audit.setRemarks("VERIFIED");
					audit.setEvent_id(existing.getCust_id());
					audit.setEvent_name(existing.getRef_no());
					audit.setModi_details("PARAMETER VERIFIED");
					audit.setEntry_user(existing.getAml_modify_user() != null ? existing.getAml_modify_user() : existing.getAml_entry_user());
					audit.setEntry_time(existing.getAml_modify_time() != null ? existing.getAml_modify_time() : existing.getAml_entry_time());
					audit.setAuth_user(verifyUser);
					audit.setAuth_time(new Date());
					audit.setAudit_ref_no(Number1 != null ? Number1.toString() : "1");
					auditLocal.save(audit);
				} catch (Exception e) {
					System.out.println("Audit verify error: " + e.getMessage());
				}
				msg = "Customer Type Verified Successfully";
			} else {
				msg = "Record not found!";
			}
		}
		return msg;
	}

	public Aml_Scr_Parm_Cust_Type_Entity getSrlNo(String srlno) {
		if (srlno == null || srlno.trim().isEmpty()) {
			return new Aml_Scr_Parm_Cust_Type_Entity();
		}
		String cleanSrl = srlno.trim();
		if (aml_scr_parm_cust_type_Repository.existsById(cleanSrl)) {
			return aml_scr_parm_cust_type_Repository.findById(cleanSrl).get();
		} else {
			return new Aml_Scr_Parm_Cust_Type_Entity();
		}
	}

	public String deleteParameter(String inputSrlNo) {
		String msg = "";
		Optional<Aml_Scr_Parm_Cust_Type_Entity> user = aml_scr_parm_cust_type_Repository.findById(inputSrlNo);
		Aml_Scr_Parm_Cust_Type_Entity reg = user.get();
		reg.setDel_flag("N");
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String inputSrlNo) {
		String msg = "";
		Optional<Aml_Scr_Parm_Cust_Type_Entity> user = aml_scr_parm_cust_type_Repository.findById(inputSrlNo);
		Aml_Scr_Parm_Cust_Type_Entity reg = user.get();
		
		reg.setDel_flag("N");
		msg = "User Deleted Successfully";
		return msg;
	}
	
	
	public String getNextRefValue() {
		// getting the next no for unique ref id

		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("0");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT id FROM BAML_SCR_PARM_CUST_TYPE_NUM").getSingleResult();

		BigDecimal z = new BigDecimal(1);

		String serialno = null;
		if (billNumber == null) {
			//********** in case num table is not having any vlaue then insert - NUM table shud contain
			//********* only one Row shud be there in this table at all times 
			hs.createNativeQuery("insert into BAML_SCR_PARM_CUST_TYPE_NUM(id) values(1)").getSingleResult();
			serialno = "1";
		} else {
			serialno = numformate.format(billNumber);
		}

		return  serialno;
	}
	
	public void updateBlack_list_Ind_Num() {
		Session hs = sessionFactory.getCurrentSession();
		// after save increement the num table value by 1
//		aml_scr_parm_cust_type_Repository.updateCust_Cust_Type_List_NumTB();
		
	}
		
	
}
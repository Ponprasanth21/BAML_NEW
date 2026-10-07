package com.bornfire.Services;
/*  
 * 
 * Author : vijay corda
 * 
 * */
import java.math.BigDecimal;
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
		// TODO Auto-generated method stub
		String msg = "";
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		if (formmode.equals("Ind_add")) {
			Aml_Scr_Parm_Cust_Type_Entity up = alertparam;
			
			DecimalFormat numformate = new DecimalFormat("0");
			
			Session hs = sessionFactory.getCurrentSession();
			if (up.getRef_no().isEmpty() || up.getRef_no().equals(null)) {
				BigDecimal billNumber = (BigDecimal) hs
						.createNativeQuery("SELECT BAML_SCR_PARM_CUST_TYPE_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
						.getSingleResult();
				String serialno = numformate.format(billNumber);
				up.setRef_no(serialno);
			}
		
			hs.save(up);
			up.setDel_flag("N");
			up.setEntity_flag("N");
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
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);



			msg = "Customer Type Created Successfully";

		} else if (formmode.equals("Ind_edit")) {
			Aml_Scr_Parm_Cust_Type_Entity up = alertparam;
			up.setDel_flag("N");
			up.setModify_flag("Y");
			up.setEntity_flag("N");
			aml_scr_parm_cust_type_Repository.save(up);
			msg = "Customer Type Edited Successfully";
		} else if (formmode.equals("Ind_delete")) {
			Aml_Scr_Parm_Cust_Type_Entity up = alertparam;
			up.setDel_flag("Y");
			up.setEntity_flag("N");
			aml_scr_parm_cust_type_Repository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(USERID);
			audit.setFunc_code("PARAMETER REMOVED");
			audit.setRemarks("REMOVED");
			audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
			audit.setAudit_screen("CUSTOMER TYPE SCREENING PARAMETER");
			audit.setEvent_id(up.getCust_id());
			audit.setEvent_name(up.getRef_no());
			audit.setModi_details("PARAMETER DELETED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);
			msg = "Customer Type Deleted Successfully";
		} else if (formmode.equals("Ind_verify")) {

			Aml_Scr_Parm_Cust_Type_Entity up = alertparam;
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			up.setEntity_flag("Y");
			up.setDel_flag("N");
			aml_scr_parm_cust_type_Repository.save(up);
			
			
				audit.setAudit_date(new Date());
				audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
				audit.setAudit_screen("SCREENING PARAMETER- MODIFIED");
				audit.setFunc_code("PARAMETER MODIFIED");
				audit.setRemarks("VERIFIED");
				audit.setEvent_id(up.getCust_id());
				audit.setEvent_name(up.getRef_no());
				audit.setModi_details(audit.getModi_details());
				audit.setEntry_user(up.getAml_modify_user());
				audit.setEntry_time(up.getAml_modify_time());
				audit.setAuth_user(USERID);
				audit.setAuth_time(new Date());
				audit.setAudit_ref_no(audit.getAudit_ref_no());
		
			msg = "Customer Type Verified Successfully";
		}
		return msg;
	}

	public Aml_Scr_Parm_Cust_Type_Entity getSrlNo(String srlno) {

		if (aml_scr_parm_cust_type_Repository.existsById(srlno)) {
			Aml_Scr_Parm_Cust_Type_Entity up = aml_scr_parm_cust_type_Repository.findById(srlno).get();
			System.out.println("inside the edit vijay1");
			return up;
		} else {
			return new Aml_Scr_Parm_Cust_Type_Entity();
		}

	};

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
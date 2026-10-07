package com.bornfire.Services;
/*  
 * 
 * Author : vijay corda
 * 
 * */
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AML_AUDIT_LOCAL;
import com.bornfire.entity.AML_AUDIT_LOCAL_REP;
import com.bornfire.entity.Aml_Scr_Parm_Cust_Type_Entity;
import com.bornfire.entity.Aml_Scr_Parm_Schm_Type_Entity;
import com.bornfire.entity.Aml_Scr_Parm_Schm_Type_Repository;
import com.bornfire.entity.t9.T9Detail;


@Service
@ConfigurationProperties("output")
@Transactional
public class Aml_Scr_Parm_Schm_Type_Service {

	@Autowired
	Aml_Scr_Parm_Schm_Type_Repository aml_scr_parm_schm_type_Repository;
	
	@Autowired
	private AML_AUDIT_LOCAL_REP auditLocal;
	
	@Autowired
	SessionFactory sessionFactory;

	public String addSchm_Type_List(Aml_Scr_Parm_Schm_Type_Entity alertparam, String formmode,String USERID) {
		// TODO Auto-generated method stub
		String msg = "";
		
		Session hs1 = sessionFactory.getCurrentSession();

		BigDecimal Number1 = (BigDecimal) hs1.createNativeQuery("SELECT aml_audit_seq.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		if (formmode.equals("Corp_add")) {
			Aml_Scr_Parm_Schm_Type_Entity up = alertparam;
			Session hs = sessionFactory.getCurrentSession();
			
		DecimalFormat numformate = new DecimalFormat("0");
			
			if (up.getRef_no().isEmpty() || up.getRef_no().equals(null)) {
				BigDecimal billNumber = (BigDecimal) hs
						.createNativeQuery("SELECT BAML_SCR_PARM_SCHM_TYPE_SEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
						.getSingleResult();
				String serialno = numformate.format(billNumber);
				up.setRef_no(serialno);
			}
			
			
			hs.save(up);
			up.setDel_flag('N');
			up.setEntity_flag('N');
			aml_scr_parm_schm_type_Repository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(USERID);
			audit.setFunc_code("PARAMETER CREATED");
			audit.setRemarks("ADDED");
			audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
			audit.setAudit_screen("LOAN TYPE SCREENING PARAMETER");
			audit.setEvent_id(up.getCust_id());
			audit.setEvent_name(up.getRef_no());
			audit.setModi_details("PARAMETER CREATED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);

			msg = "Loan Scheme Created Successfully";

		} else if (formmode.equals("Corp_edit")) {
			Aml_Scr_Parm_Schm_Type_Entity up = alertparam;
			up.setDel_flag('N');
			up.setModify_flag('Y');
			up.setEntity_flag('N');
			aml_scr_parm_schm_type_Repository.save(up);
			msg = "Loan Scheme Edited Successfully";
		} else if (formmode.equals("Corp_delete")) {
			Aml_Scr_Parm_Schm_Type_Entity up = alertparam;
			up.setDel_flag('Y');
			up.setEntity_flag('N');
			aml_scr_parm_schm_type_Repository.save(up);
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			audit.setAudit_date(new Date());
			audit.setEntry_time(new Date());
			audit.setEntry_user(USERID);
			audit.setFunc_code("PARAMETER REMOVED");
			audit.setRemarks("REMOVED");
			audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
			audit.setAudit_screen("LOAN TYPE SCREENING PARAMETER");
			audit.setEvent_id(up.getCust_id());
			audit.setEvent_name(up.getRef_no());
			audit.setModi_details("PARAMETER DELETED");
			audit.setAudit_ref_no(Number1.toString());
			auditLocal.save(audit);
			msg = "Loan Scheme Deleted Successfully";
		} else if (formmode.equals("Corp_verify")) {
			AML_AUDIT_LOCAL audit = new AML_AUDIT_LOCAL();
			Aml_Scr_Parm_Schm_Type_Entity up = alertparam;
			up.setEntity_flag('Y');
			up.setDel_flag('N');
			aml_scr_parm_schm_type_Repository.save(up);
			audit.setAudit_date(new Date());
			audit.setAudit_table("BAML_SCR_PARM_CUST_TYPE");
			audit.setAudit_screen(" LOAN TYPE SCREENING PARAMETER- MODIFIED");
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
			msg = "Loan Scheme Verified Successfully";
		}
		return msg;
	}

	public Aml_Scr_Parm_Schm_Type_Entity getSrlNo(String srlno) {

		if (aml_scr_parm_schm_type_Repository.existsById(srlno)) {
			Aml_Scr_Parm_Schm_Type_Entity up = aml_scr_parm_schm_type_Repository.findById(srlno).get();
			System.out.println("inside the edit vijay1");
			return up;
		} else {
			return new Aml_Scr_Parm_Schm_Type_Entity();
		}

	};

	public String deleteParameter(String inputSrlNo) {
		String msg = "";
		Optional<Aml_Scr_Parm_Schm_Type_Entity> user = aml_scr_parm_schm_type_Repository.findById(inputSrlNo);
		Aml_Scr_Parm_Schm_Type_Entity reg = user.get();
		reg.setDel_flag('N');
		msg = "User Deleted Successfully";
		return msg;
	}

	public String verifysrlno(String inputSrlNo) {
		String msg = "";
		Optional<Aml_Scr_Parm_Schm_Type_Entity> user = aml_scr_parm_schm_type_Repository.findById(inputSrlNo);
		Aml_Scr_Parm_Schm_Type_Entity reg = user.get();
		
		reg.setDel_flag('N');
		msg = "User Deleted Successfully";
		return msg;
	}
	
	
	public String getNextRefValue() {
		// getting the next no for unique ref id

		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("0");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT id FROM BAML_SCR_PARM_SCHM_TYPE_NUM").getSingleResult();

		BigDecimal z = new BigDecimal(1);

		String serialno = null;
		if (billNumber == null) {
			//********** in case num table is not having any vlaue then insert - NUM table shud contain
			//********* only one Row shud be there in this table at all times 
			hs.createNativeQuery("insert into BAML_SCR_PARM_SCHM_TYPE_NUM(id) values(1)").getSingleResult();
			serialno = "1";
		} else {
			serialno = numformate.format(billNumber);
		}
		return  serialno;
	}
	
	public void updateSCR_SCHM_list_Num() {
		Session hs = sessionFactory.getCurrentSession();
		// after save increement the num table value by 1
//		aml_scr_parm_schm_type_Repository.updatescr_param_schm_NumTB();
		
	}
	public Page<Aml_Scr_Parm_Schm_Type_Entity> parameterlistwithdecode(Pageable pageable) {
		
		int pageSize = pageable.getPageSize();
		int Page = pageable.getPageNumber();
		int startItem = Page * pageSize;
		Session hs = sessionFactory.getCurrentSession();
		List<Aml_Scr_Parm_Schm_Type_Entity> t9Dt1 = new ArrayList<Aml_Scr_Parm_Schm_Type_Entity>();
		Query<Object[]> qr;

			qr = hs.createNativeQuery("select REF_NO,CRNCY_CODE,LOAN_LIMIT,LOAN_SCHEME,LOAN_CONDITIONS,DECODE(SCHM_CODE,SCHM_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='SC' AND SCHM_CODE = REF_CODE)) as SCHM_CODE," + 
					"DECODE(GL_SUB_HEAD_CODE,GL_SUB_HEAD_CODE,(Select REF_DESC from BAML_REFERENCE_CODE_TABLE  where REF_REC_TYPE='GL' AND GL_SUB_HEAD_CODE = REF_CODE)) as GL_SUB_HEAD_CODE," + 
					"CUST_ID,PARAMETERS,START_DATE,END_DATE,REMARKS1,REMARKS2,AML_ENTRY_TIME,AML_MODIFY_TIME,AML_VERIFY_TIME,AML_ENTRY_USER,AML_MODIFY_USER,AML_VERIFY_USER,ENTITY_FLAG,DEL_FLAG,MODIFY_FLAG from BAML_SCR_PARM_SCHM_TYPE  where DEL_FLAG='N' order by entity_flag,LPAD(REF_NO, 10) asc ");
		
			List<Object[]> result = qr.getResultList();
			try {
			for (Object[] a : result) {
				String ref_no = (String) a[0];
				String crncy_code = (String) a[1];
				BigDecimal loan_limit = (BigDecimal) a[2];
				String loanSCHEME = (String) a[3];
				String loan_conditions = (String) a[4];
				String schm_code = (String) a[5];
				String glsubHeadcode = (String) a[6];
				String custId = (String) a[7];
				String parameters = (String) a[8];
				Date startdate = (Date) a[9];
				Date enddate = (Date) a[10];
				String renarsk1 = (String) a[11];
				String remarks2 = (String) a[12];
				Date entrytime = (Date) a[13];
				Date modifytime = (Date) a[14];
				Date varifytime = (Date) a[15];
				String entryuser = (String) a[16];
				String modifyuser = (String) a[17];
				String verifyuser = (String) a[18];
				Character entityflg = (Character) a[19];
				Character delflg = (Character) a[20];
				Character modifyflg = (Character) a[21];
				
				Aml_Scr_Parm_Schm_Type_Entity py = new Aml_Scr_Parm_Schm_Type_Entity(ref_no, crncy_code, loan_limit,loanSCHEME, loan_conditions, schm_code,
						glsubHeadcode, custId, parameters, startdate, enddate, renarsk1, remarks2, 
						entrytime, modifytime, varifytime, entryuser, modifyuser, verifyuser, entityflg, delflg, 
						modifyflg);
				
				

				t9Dt1.add(py);
			}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			List<Aml_Scr_Parm_Schm_Type_Entity> pagedlist;

			if (t9Dt1.size() < startItem) {
				pagedlist = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, t9Dt1.size());
				pagedlist = t9Dt1.subList(startItem, toIndex);
			}
			Page<Aml_Scr_Parm_Schm_Type_Entity> t9Dt1Page = new PageImpl<Aml_Scr_Parm_Schm_Type_Entity>(pagedlist, PageRequest.of(Page, pageSize),
					t9Dt1.size());
		
		return t9Dt1Page;
		
	}

	
}
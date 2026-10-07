package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="BAML_CUST_CASE_MGMT")
public class AML_Cust_Case_Mgmt {
	@Id
	private String	case_ref_srl_no;
	
	private String	case_ref_sys_date;
	private String	primary_sol_id;
	
	private String	cust_id;
	private String	cust_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	cust_opn_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	ref_date;
	private String	ref_person;
	private String	case_status;
	private String	case_remarks;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	entry_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	modify_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	verify_date;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	
	private String cif_id;
	
	
	
	public String getCif_id() {
		return cif_id;
	}
	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}
	public String getCase_ref_srl_no() {
		return case_ref_srl_no;
	}
	public String getCase_ref_sys_date() {
		return case_ref_sys_date;
	}
	public String getPrimary_sol_id() {
		return primary_sol_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public Date getCust_opn_date() {
		return cust_opn_date;
	}
	public Date getRef_date() {
		return ref_date;
	}
	public String getRef_person() {
		return ref_person;
	}
	public String getCase_status() {
		return case_status;
	}
	public String getCase_remarks() {
		return case_remarks;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setCase_ref_srl_no(String case_ref_srl_no) {
		this.case_ref_srl_no = case_ref_srl_no;
	}
	public void setCase_ref_sys_date(String case_ref_sys_date) {
		this.case_ref_sys_date = case_ref_sys_date;
	}
	public void setPrimary_sol_id(String primary_sol_id) {
		this.primary_sol_id = primary_sol_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public void setCust_opn_date(Date cust_opn_date) {
		this.cust_opn_date = cust_opn_date;
	}
	public void setRef_date(Date ref_date) {
		this.ref_date = ref_date;
	}
	public void setRef_person(String ref_person) {
		this.ref_person = ref_person;
	}
	public void setCase_status(String case_status) {
		this.case_status = case_status;
	}
	public void setCase_remarks(String case_remarks) {
		this.case_remarks = case_remarks;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	
	
	
	public AML_Cust_Case_Mgmt(String case_ref_srl_no, String case_ref_sys_date, String primary_sol_id, String cust_id,
			String cust_name, Date cust_opn_date, Date ref_date, String ref_person, String case_status,
			String case_remarks, String entry_user, String modify_user, String verify_user, Date entry_date,
			Date modify_date, Date verify_date, String del_flg, String entity_flg, String modify_flg, String cif_id) {
		super();
		this.case_ref_srl_no = case_ref_srl_no;
		this.case_ref_sys_date = case_ref_sys_date;
		this.primary_sol_id = primary_sol_id;
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_opn_date = cust_opn_date;
		this.ref_date = ref_date;
		this.ref_person = ref_person;
		this.case_status = case_status;
		this.case_remarks = case_remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.cif_id = cif_id;
	}
	
	
	public AML_Cust_Case_Mgmt() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	 
}

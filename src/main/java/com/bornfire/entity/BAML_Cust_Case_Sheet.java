package com.bornfire.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_CUST_CASE_SHEET")
public class BAML_Cust_Case_Sheet {
	@Id
	private String case_ref_srl_no;
	private String case_ref_sys_date;
	private String primary_sol_id;
	private String cust_id;
	
	private String cif_id;
	private String cust_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cust_opn_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date opr_date;
	private String reference;
	private String oper_details;
	private String obser_user;
	private String obser_user_name;
	private String observations;
	private String review_user;
	private String review_user_name;
	private String review_comments;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date entry_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modify_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date verify_date;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;

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

	public Date getOpr_date() {
		return opr_date;
	}

	public String getReference() {
		return reference;
	}

	public String getOper_details() {
		return oper_details;
	}

	public String getObser_user() {
		return obser_user;
	}

	public String getObser_user_name() {
		return obser_user_name;
	}

	public String getObservations() {
		return observations;
	}

	public String getReview_user() {
		return review_user;
	}

	public String getReview_user_name() {
		return review_user_name;
	}

	public String getReview_comments() {
		return review_comments;
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

	public void setOpr_date(Date opr_date) {
		this.opr_date = opr_date;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setOper_details(String oper_details) {
		this.oper_details = oper_details;
	}

	public void setObser_user(String obser_user) {
		this.obser_user = obser_user;
	}

	public void setObser_user_name(String obser_user_name) {
		this.obser_user_name = obser_user_name;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public void setReview_user(String review_user) {
		this.review_user = review_user;
	}

	public void setReview_user_name(String review_user_name) {
		this.review_user_name = review_user_name;
	}

	public void setReview_comments(String review_comments) {
		this.review_comments = review_comments;
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

	
	public String getCif_id() {
		return cif_id;
	}

	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}

	
	public BAML_Cust_Case_Sheet(String case_ref_srl_no, String case_ref_sys_date, String primary_sol_id, String cust_id,
			String cif_id, String cust_name, Date cust_opn_date, Date opr_date, String reference, String oper_details,
			String obser_user, String obser_user_name, String observations, String review_user, String review_user_name,
			String review_comments, String entry_user, String modify_user, String verify_user, Date entry_date,
			Date modify_date, Date verify_date, String del_flg, String entity_flg, String modify_flg) {
		super();
		this.case_ref_srl_no = case_ref_srl_no;
		this.case_ref_sys_date = case_ref_sys_date;
		this.primary_sol_id = primary_sol_id;
		this.cust_id = cust_id;
		this.cif_id = cif_id;
		this.cust_name = cust_name;
		this.cust_opn_date = cust_opn_date;
		this.opr_date = opr_date;
		this.reference = reference;
		this.oper_details = oper_details;
		this.obser_user = obser_user;
		this.obser_user_name = obser_user_name;
		this.observations = observations;
		this.review_user = review_user;
		this.review_user_name = review_user_name;
		this.review_comments = review_comments;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}

	public BAML_Cust_Case_Sheet() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_KYC_HIST_TABLE")
public class KycHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String cust_id;
	private String cust_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date cust_opn_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date risk_review_date;
	private String riskrating;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date kyc_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date kyc_date_1;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date kyc_date_2;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date kyc_review_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date cur_kyc_date;
	private String doc_srl_no;
	private String doc_type;
	private String doc_type_1;
	private String doc_type_2;
	private String doc_id;
	private String doc_id_1;

	private String doc_id_2;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc_date_1;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc_date_2;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc_expiry_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc_expiry_date_1;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc_expiry_date_2;
	private String doc_details;
	private String doc_details_1;
	private String doc_details_2;
	private String doc_verified_flg;
	private String doc_verified_flg_1;
	private String doc_verified_flg_2;
	

	private String entry_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_date;
	private String modify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_date;
	private String verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date verify_date;
	private String entity_flg;
	private String del_flg;
	private String modify_flg;
	@Lob
	private byte[] doc_image;
	@Lob
	private byte[] doc_image_1;
	@Lob
	private byte[] doc_image_2;

	private String age;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date due_date;

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Date getRisk_review_date() {
		return risk_review_date;
	}

	public String getRiskrating() {
		return riskrating;
	}

	public Date getKyc_date() {
		return kyc_date;
	}

	public Date getKyc_review_date() {
		return kyc_review_date;
	}

	public Date getCur_kyc_date() {
		return cur_kyc_date;
	}

	public String getDoc_srl_no() {
		return doc_srl_no;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public String getDoc_id() {
		return doc_id;
	}

	public Date getDoc_date() {
		return doc_date;
	}

	public Date getDoc_expiry_date() {
		return doc_expiry_date;
	}

	public String getDoc_details() {
		return doc_details;
	}

	public String getDoc_verified_flg() {
		return doc_verified_flg;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public String getModify_user() {
		return modify_user;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public Date getVerify_date() {
		return verify_date;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public String getModify_flg() {
		return modify_flg;
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

	public void setRisk_review_date(Date risk_review_date) {
		this.risk_review_date = risk_review_date;
	}

	public void setRiskrating(String riskrating) {
		this.riskrating = riskrating;
	}

	public void setKyc_date(Date kyc_date) {
		this.kyc_date = kyc_date;
	}

	public void setKyc_review_date(Date kyc_review_date) {
		this.kyc_review_date = kyc_review_date;
	}

	public void setCur_kyc_date(Date cur_kyc_date) {
		this.cur_kyc_date = cur_kyc_date;
	}

	public void setDoc_srl_no(String doc_srl_no) {
		this.doc_srl_no = doc_srl_no;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}

	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}

	public void setDoc_expiry_date(Date doc_expiry_date) {
		this.doc_expiry_date = doc_expiry_date;
	}

	public void setDoc_details(String doc_details) {
		this.doc_details = doc_details;
	}

	public void setDoc_verified_flg(String doc_verified_flg) {
		this.doc_verified_flg = doc_verified_flg;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	

	public byte[] getDoc_image() {
		return doc_image;
	}

	public void setDoc_image(byte[] doc_image) {
		this.doc_image = doc_image;
	}

	public String getAge() {
		return age;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
	
	

	public String getDoc_type_1() {
		return doc_type_1;
	}

	public String getDoc_type_2() {
		return doc_type_2;
	}

	public Date getDoc_date_1() {
		return doc_date_1;
	}

	public Date getDoc_date_2() {
		return doc_date_2;
	}

	public String getDoc_details_1() {
		return doc_details_1;
	}

	public String getDoc_details_2() {
		return doc_details_2;
	}

	
	public byte[] getDoc_image_1() {
		return doc_image_1;
	}

	public byte[] getDoc_image_2() {
		return doc_image_2;
	}

	public void setDoc_type_1(String doc_type_1) {
		this.doc_type_1 = doc_type_1;
	}

	public void setDoc_type_2(String doc_type_2) {
		this.doc_type_2 = doc_type_2;
	}

	public void setDoc_date_1(Date doc_date_1) {
		this.doc_date_1 = doc_date_1;
	}

	public void setDoc_date_2(Date doc_date_2) {
		this.doc_date_2 = doc_date_2;
	}

	public void setDoc_details_1(String doc_details_1) {
		this.doc_details_1 = doc_details_1;
	}

	public void setDoc_details_2(String doc_details_2) {
		this.doc_details_2 = doc_details_2;
	}

	
	public void setDoc_image_1(byte[] doc_image_1) {
		this.doc_image_1 = doc_image_1;
	}

	public void setDoc_image_2(byte[] doc_image_2) {
		this.doc_image_2 = doc_image_2;
	}

	
	
	
	
	public Date getKyc_date_1() {
		return kyc_date_1;
	}

	public void setKyc_date_1(Date kyc_date_1) {
		this.kyc_date_1 = kyc_date_1;
	}

	public Date getKyc_date_2() {
		return kyc_date_2;
	}

	public void setKyc_date_2(Date kyc_date_2) {
		this.kyc_date_2 = kyc_date_2;
	}

	public String getDoc_id_1() {
		return doc_id_1;
	}

	public void setDoc_id_1(String doc_id_1) {
		this.doc_id_1 = doc_id_1;
	}

	public String getDoc_id_2() {
		return doc_id_2;
	}

	public void setDoc_id_2(String doc_id_2) {
		this.doc_id_2 = doc_id_2;
	}

	public Date getDoc_expiry_date_1() {
		return doc_expiry_date_1;
	}

	public void setDoc_expiry_date_1(Date doc_expiry_date_1) {
		this.doc_expiry_date_1 = doc_expiry_date_1;
	}

	public Date getDoc_expiry_date_2() {
		return doc_expiry_date_2;
	}

	public void setDoc_expiry_date_2(Date doc_expiry_date_2) {
		this.doc_expiry_date_2 = doc_expiry_date_2;
	}

	public String getDoc_verified_flg_1() {
		return doc_verified_flg_1;
	}

	public void setDoc_verified_flg_1(String doc_verified_flg_1) {
		this.doc_verified_flg_1 = doc_verified_flg_1;
	}

	public String getDoc_verified_flg_2() {
		return doc_verified_flg_2;
	}

	public void setDoc_verified_flg_2(String doc_verified_flg_2) {
		this.doc_verified_flg_2 = doc_verified_flg_2;
	}

	public KycHistory() {

		super();

	}

}
package com.bornfire.entity.t7;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T7CUST_KYC_CDD_REVIEW_MAST_TB")
public class T7Detail implements Serializable{
	
	@EmbeddedId
	T7DetailId t7detailid;
	private String	cust_name;
	private String	cust_type;
	private Date	cust_kyc_date;
	private Date	cust_kyc_review_date;
	private String	cust_rating;
	private Date	cust_rating_date;
	private Date	cust_rating_review_date;
	private String	entity_flg;
	private String	del_flg;
	private String	modify_flg;
	private Date	entry_date;
	private Date	modify_date;
	private Date	verify_date;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private String	report_code;
	private String	report_name;
	private Character	arch_flg;
	public T7DetailId getT7detailid() {
		return t7detailid;
	}
	public void setT7detailid(T7DetailId t7detailid) {
		this.t7detailid = t7detailid;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	public Date getCust_kyc_date() {
		return cust_kyc_date;
	}
	public void setCust_kyc_date(Date cust_kyc_date) {
		this.cust_kyc_date = cust_kyc_date;
	}
	public Date getCust_kyc_review_date() {
		return cust_kyc_review_date;
	}
	public void setCust_kyc_review_date(Date cust_kyc_review_date) {
		this.cust_kyc_review_date = cust_kyc_review_date;
	}
	public String getCust_rating() {
		return cust_rating;
	}
	public void setCust_rating(String cust_rating) {
		this.cust_rating = cust_rating;
	}
	public Date getCust_rating_date() {
		return cust_rating_date;
	}
	public void setCust_rating_date(Date cust_rating_date) {
		this.cust_rating_date = cust_rating_date;
	}
	public Date getCust_rating_review_date() {
		return cust_rating_review_date;
	}
	public void setCust_rating_review_date(Date cust_rating_review_date) {
		this.cust_rating_review_date = cust_rating_review_date;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public Character getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}
	public T7Detail(T7DetailId t7detailid, String cust_name, String cust_type, Date cust_kyc_date,
			Date cust_kyc_review_date, String cust_rating, Date cust_rating_date, Date cust_rating_review_date,
			String entity_flg, String del_flg, String modify_flg, Date entry_date, Date modify_date, Date verify_date,
			String entry_user, String modify_user, String verify_user, String report_code, String report_name,
			Character arch_flg) {
		super();
		this.t7detailid = t7detailid;
		this.cust_name = cust_name;
		this.cust_type = cust_type;
		this.cust_kyc_date = cust_kyc_date;
		this.cust_kyc_review_date = cust_kyc_review_date;
		this.cust_rating = cust_rating;
		this.cust_rating_date = cust_rating_date;
		this.cust_rating_review_date = cust_rating_review_date;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.report_code = report_code;
		this.report_name = report_name;
		this.arch_flg = arch_flg;
	}
	
	public T7Detail() {}
	

}

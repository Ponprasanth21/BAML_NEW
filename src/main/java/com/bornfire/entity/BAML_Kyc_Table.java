package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="BAML_KYC_TABLE")
public class BAML_Kyc_Table {

	private Date auth_time;
	private String auth_user;
	@Id
	private String cust_id;
	private String cust_name;
	private Date cust_opn_date;
	private String del_flg;
	private String entity_flg;
	private Date entry_time;
	private String entry_user;
	private Date kyc_date;
	private BigDecimal kyc_overdue;
	private Date kyc_review_date;
	private Date last_kyc_date;
	private String localetext;
	private String modify_flg;
	private Date modify_time;
	private String modify_user;
	private String nre_risk_category;
	private String primary_sol_id;
	private String risk_category;
	private Date risk_date;
	private Date risk_profile_expiry_date;
	private BigDecimal risk_profile_score;

	public Date getAuth_time() {
		return auth_time;
	}

	public String getAuth_user() {
		return auth_user;
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

	public String getDel_flg() {
		return del_flg;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public Date getKyc_date() {
		return kyc_date;
	}

	public BigDecimal getKyc_overdue() {
		return kyc_overdue;
	}

	public Date getKyc_review_date() {
		return kyc_review_date;
	}

	public Date getLast_kyc_date() {
		return last_kyc_date;
	}

	public String getLocaletext() {
		return localetext;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public String getModify_user() {
		return modify_user;
	}

	public String getNre_risk_category() {
		return nre_risk_category;
	}

	public String getPrimary_sol_id() {
		return primary_sol_id;
	}

	public String getRisk_category() {
		return risk_category;
	}

	public Date getRisk_date() {
		return risk_date;
	}

	public Date getRisk_profile_expiry_date() {
		return risk_profile_expiry_date;
	}

	public BigDecimal getRisk_profile_score() {
		return risk_profile_score;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
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

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public void setKyc_date(Date kyc_date) {
		this.kyc_date = kyc_date;
	}

	public void setKyc_overdue(BigDecimal kyc_overdue) {
		this.kyc_overdue = kyc_overdue;
	}

	public void setKyc_review_date(Date kyc_review_date) {
		this.kyc_review_date = kyc_review_date;
	}

	public void setLast_kyc_date(Date last_kyc_date) {
		this.last_kyc_date = last_kyc_date;
	}

	public void setLocaletext(String localetext) {
		this.localetext = localetext;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public void setNre_risk_category(String nre_risk_category) {
		this.nre_risk_category = nre_risk_category;
	}

	public void setPrimary_sol_id(String primary_sol_id) {
		this.primary_sol_id = primary_sol_id;
	}

	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}

	public void setRisk_date(Date risk_date) {
		this.risk_date = risk_date;
	}

	public void setRisk_profile_expiry_date(Date risk_profile_expiry_date) {
		this.risk_profile_expiry_date = risk_profile_expiry_date;
	}

	public void setRisk_profile_score(BigDecimal risk_profile_score) {
		this.risk_profile_score = risk_profile_score;
	}

	public BAML_Kyc_Table(Date auth_time, String auth_user, String cust_id, String cust_name, Date cust_opn_date,
			String del_flg, String entity_flg, Date entry_time, String entry_user, Date kyc_date,
			BigDecimal kyc_overdue, Date kyc_review_date, Date last_kyc_date, String localetext, String modify_flg,
			Date modify_time, String modify_user, String nre_risk_category, String primary_sol_id, String risk_category,
			Date risk_date, Date risk_profile_expiry_date, BigDecimal risk_profile_score) {
		super();
		this.auth_time = auth_time;
		this.auth_user = auth_user;
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_opn_date = cust_opn_date;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.entry_time = entry_time;
		this.entry_user = entry_user;
		this.kyc_date = kyc_date;
		this.kyc_overdue = kyc_overdue;
		this.kyc_review_date = kyc_review_date;
		this.last_kyc_date = last_kyc_date;
		this.localetext = localetext;
		this.modify_flg = modify_flg;
		this.modify_time = modify_time;
		this.modify_user = modify_user;
		this.nre_risk_category = nre_risk_category;
		this.primary_sol_id = primary_sol_id;
		this.risk_category = risk_category;
		this.risk_date = risk_date;
		this.risk_profile_expiry_date = risk_profile_expiry_date;
		this.risk_profile_score = risk_profile_score;
	}

	public BAML_Kyc_Table() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
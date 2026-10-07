package com.bornfire.entity.t6;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T6_KYC_CDD_REVIEW_FREQ_DET_TABLE")
public class T6Details implements Serializable {
	@Id
	private String	cust_id;
	private String	cust_name;
	private String	cust_type;
	private Date	kyc_date;
	private Date	kyc_due_date;
	private String	cust_rating_code;
	private Date	cust_rating_date;
	private Date	cust_rating_due_date;
	private Character	entity_flg;
	private Character	del_flg;
	private Character	modify_flg;
	private Date	entry_date;
	private Date	modify_date;
	private Date	verify_date;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private String	report_code;
	private String	report_name;
	private Date	report_date;
	private Character	arch_flg;
	
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
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
	public Date getKyc_date() {
		return kyc_date;
	}
	public void setKyc_date(Date kyc_date) {
		this.kyc_date = kyc_date;
	}
	public Date getKyc_due_date() {
		return kyc_due_date;
	}
	public void setKyc_due_date(Date kyc_due_date) {
		this.kyc_due_date = kyc_due_date;
	}
	public String getCust_rating_code() {
		return cust_rating_code;
	}
	public void setCust_rating_code(String cust_rating_code) {
		this.cust_rating_code = cust_rating_code;
	}
	public Date getCust_rating_date() {
		return cust_rating_date;
	}
	public void setCust_rating_date(Date cust_rating_date) {
		this.cust_rating_date = cust_rating_date;
	}
	public Date getCust_rating_due_date() {
		return cust_rating_due_date;
	}
	public void setCust_rating_due_date(Date cust_rating_due_date) {
		this.cust_rating_due_date = cust_rating_due_date;
	}
	public Character getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}
	public Character getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}
	public Character getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(Character modify_flg) {
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
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public Character getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}
	public T6Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	public T6Details(String cust_id, String cust_name, String cust_type, Date kyc_date, Date kyc_due_date,
			String cust_rating_code, Date cust_rating_date, Date cust_rating_due_date, Character entity_flg,
			Character del_flg, Character modify_flg, Date entry_date, Date modify_date, Date verify_date,
			String entry_user, String modify_user, String verify_user, String report_code, String report_name,
			Date report_date, Character arch_flg) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_type = cust_type;
		this.kyc_date = kyc_date;
		this.kyc_due_date = kyc_due_date;
		this.cust_rating_code = cust_rating_code;
		this.cust_rating_date = cust_rating_date;
		this.cust_rating_due_date = cust_rating_due_date;
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
		this.report_date = report_date;
		this.arch_flg = arch_flg;
	}

	
}

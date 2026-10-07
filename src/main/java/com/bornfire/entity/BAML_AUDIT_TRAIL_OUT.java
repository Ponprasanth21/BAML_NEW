package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_AUDIT_LOCAL")
public class BAML_AUDIT_TRAIL_OUT {

	private String  title;
	@Id
	private String	auditedid;
	private String	orgkey;
	private String  cust_name;
	private String	entry_user;
	private String	verify_user;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date  entry_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	verify_date;
	private String	new_value;
	private String old_value;
	private String acct_name;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date audit_date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuditedid() {
		return auditedid;
	}
	public void setAuditedid(String auditedid) {
		this.auditedid = auditedid;
	}
	public String getOrgkey() {
		return orgkey;
	}
	public void setOrgkey(String orgkey) {
		this.orgkey = orgkey;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public String getNew_value() {
		return new_value;
	}
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
	public String getOld_value() {
		return old_value;
	}
	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public BAML_AUDIT_TRAIL_OUT() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BAML_AUDIT_TRAIL_OUT(String title, String auditedid, String orgkey, String cust_name, String entry_user,
			String verify_user, Date entry_date, Date verify_date, String new_value, String old_value, String acct_name,
			Date audit_date) {
		super();
		this.title = title;
		this.auditedid = auditedid;
		this.orgkey = orgkey;
		this.cust_name = cust_name;
		this.entry_user = entry_user;
		this.verify_user = verify_user;
		this.entry_date = entry_date;
		this.verify_date = verify_date;
		this.new_value = new_value;
		this.old_value = old_value;
		this.acct_name = acct_name;
		this.audit_date = audit_date;
	}
	
	
	
}

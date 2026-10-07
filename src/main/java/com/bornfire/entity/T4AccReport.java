package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name= "BAML_ACCT_APPL_REJ")
public class T4AccReport {
	
	@Id
	private String	srl_no;
	private String	acct_name;
	private String	acct_type;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	date_of_appl;
	private String	reason_reject;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	date_of_rej;
	private String	remarks;
	private Character	entity_flg;
	private Character	del_flg;
	private Character	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	entry_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	verify_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	report_date;
	private String	report_code;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_from;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_to;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSrl_no() {
		return srl_no;
	}
	
	public Date getDate_of_appl() {
		return date_of_appl;
	}
	public String getReason_reject() {
		return reason_reject;
	}
	public Date getDate_of_rej() {
		return date_of_rej;
	}
	public String getRemarks() {
		return remarks;
	}
	public Character getEntity_flg() {
		return entity_flg;
	}
	public Character getDel_flg() {
		return del_flg;
	}
	public Character getModify_flg() {
		return modify_flg;
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
	public Date getEntry_time() {
		return entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public Date getVerify_time() {
		return verify_time;
	}
	public Date getReport_date() {
		return report_date;
	}
	public String getReport_code() {
		return report_code;
	}
	public Date getRep_period_from() {
		return rep_period_from;
	}
	public Date getRep_period_to() {
		return rep_period_to;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	
	public void setDate_of_appl(Date date_of_appl) {
		this.date_of_appl = date_of_appl;
	}
	public void setReason_reject(String reason_reject) {
		this.reason_reject = reason_reject;
	}
	public void setDate_of_rej(Date date_of_rej) {
		this.date_of_rej = date_of_rej;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}
	public void setModify_flg(Character modify_flg) {
		this.modify_flg = modify_flg;
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
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}
	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public String getAcct_type() {
		return acct_type;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}

	public T4AccReport(String srl_no, String acct_name, String acct_type, Date date_of_appl, String reason_reject,
			Date date_of_rej, String remarks, Character entity_flg, Character del_flg, Character modify_flg,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time, Date report_date, String report_code, Date rep_period_from, Date rep_period_to) {
		super();
		this.srl_no = srl_no;
		this.acct_name = acct_name;
		this.acct_type = acct_type;
		this.date_of_appl = date_of_appl;
		this.reason_reject = reason_reject;
		this.date_of_rej = date_of_rej;
		this.remarks = remarks;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.report_date = report_date;
		this.report_code = report_code;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
	}

	public T4AccReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
}
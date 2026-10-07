package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "RULE_ENGINE")
public class RuleEngineEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String	srl_no;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")

	private Date	rule_date;
	private String	rule_type;
	private String	rule_code;
	private String	rule_description;
	private String	rule_remarks;
	private BigDecimal	value_1;
	private BigDecimal	value_2;
	private BigDecimal	value_min;
	private BigDecimal	value_max;
	private String	e_mail_required;
	private String	e_mail_1;
	private String	email_user_1;
	private String	e_mail_2;
	private String	email_user_2;
	private String	e_mail_3;
	private String	email_user_3;
	private String	sms_required;
	private BigDecimal	mobile_1;
	private String	mobile_user_1;
	private BigDecimal	mobile_2;
	private String	mobile_user_2;
	private BigDecimal	mobile_3;
	private String	mobile_user_3;
	private String	alert_required;
	private String	alert_userid_1;
	private String	alert_user_1;
	private String	alert_userid_2;
	private String	alert_user_2;
	private String	alert_userid_3;
	private String	alert_user_3;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")

	private Date	start_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")

	private Date	end_date;
	private String	suspended;
	private String	remarks;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")

	private Date	suspended_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")

	private Date	activated_date;
	private String	del_flg;

	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public Date getRule_date() {
		return rule_date;
	}
	public void setRule_date(Date rule_date) {
		this.rule_date = rule_date;
	}
	public String getRule_type() {
		return rule_type;
	}
	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}
	public String getRule_code() {
		return rule_code;
	}
	public void setRule_code(String rule_code) {
		this.rule_code = rule_code;
	}
	public String getRule_description() {
		return rule_description;
	}
	public void setRule_description(String rule_description) {
		this.rule_description = rule_description;
	}
	public String getRule_remarks() {
		return rule_remarks;
	}
	public void setRule_remarks(String rule_remarks) {
		this.rule_remarks = rule_remarks;
	}
	public BigDecimal getValue_1() {
		return value_1;
	}
	public void setValue_1(BigDecimal value_1) {
		this.value_1 = value_1;
	}
	public BigDecimal getValue_2() {
		return value_2;
	}
	public void setValue_2(BigDecimal value_2) {
		this.value_2 = value_2;
	}
	public BigDecimal getValue_min() {
		return value_min;
	}
	public void setValue_min(BigDecimal value_min) {
		this.value_min = value_min;
	}
	public BigDecimal getValue_max() {
		return value_max;
	}
	public void setValue_max(BigDecimal value_max) {
		this.value_max = value_max;
	}
	public String getE_mail_required() {
		return e_mail_required;
	}
	public void setE_mail_required(String e_mail_required) {
		this.e_mail_required = e_mail_required;
	}
	public String getE_mail_1() {
		return e_mail_1;
	}
	public void setE_mail_1(String e_mail_1) {
		this.e_mail_1 = e_mail_1;
	}
	public String getEmail_user_1() {
		return email_user_1;
	}
	public void setEmail_user_1(String email_user_1) {
		this.email_user_1 = email_user_1;
	}
	public String getE_mail_2() {
		return e_mail_2;
	}
	public void setE_mail_2(String e_mail_2) {
		this.e_mail_2 = e_mail_2;
	}
	public String getEmail_user_2() {
		return email_user_2;
	}
	public void setEmail_user_2(String email_user_2) {
		this.email_user_2 = email_user_2;
	}
	public String getE_mail_3() {
		return e_mail_3;
	}
	public void setE_mail_3(String e_mail_3) {
		this.e_mail_3 = e_mail_3;
	}
	public String getEmail_user_3() {
		return email_user_3;
	}
	public void setEmail_user_3(String email_user_3) {
		this.email_user_3 = email_user_3;
	}
	public String getSms_required() {
		return sms_required;
	}
	public void setSms_required(String sms_required) {
		this.sms_required = sms_required;
	}
	public BigDecimal getMobile_1() {
		return mobile_1;
	}
	public void setMobile_1(BigDecimal mobile_1) {
		this.mobile_1 = mobile_1;
	}
	public String getMobile_user_1() {
		return mobile_user_1;
	}
	public void setMobile_user_1(String mobile_user_1) {
		this.mobile_user_1 = mobile_user_1;
	}
	public BigDecimal getMobile_2() {
		return mobile_2;
	}
	public void setMobile_2(BigDecimal mobile_2) {
		this.mobile_2 = mobile_2;
	}
	public String getMobile_user_2() {
		return mobile_user_2;
	}
	public void setMobile_user_2(String mobile_user_2) {
		this.mobile_user_2 = mobile_user_2;
	}
	public BigDecimal getMobile_3() {
		return mobile_3;
	}
	public void setMobile_3(BigDecimal mobile_3) {
		this.mobile_3 = mobile_3;
	}
	public String getMobile_user_3() {
		return mobile_user_3;
	}
	public void setMobile_user_3(String mobile_user_3) {
		this.mobile_user_3 = mobile_user_3;
	}
	public String getAlert_required() {
		return alert_required;
	}
	public void setAlert_required(String alert_required) {
		this.alert_required = alert_required;
	}
	public String getAlert_userid_1() {
		return alert_userid_1;
	}
	public void setAlert_userid_1(String alert_userid_1) {
		this.alert_userid_1 = alert_userid_1;
	}
	public String getAlert_user_1() {
		return alert_user_1;
	}
	public void setAlert_user_1(String alert_user_1) {
		this.alert_user_1 = alert_user_1;
	}
	public String getAlert_userid_2() {
		return alert_userid_2;
	}
	public void setAlert_userid_2(String alert_userid_2) {
		this.alert_userid_2 = alert_userid_2;
	}
	public String getAlert_user_2() {
		return alert_user_2;
	}
	public void setAlert_user_2(String alert_user_2) {
		this.alert_user_2 = alert_user_2;
	}
	public String getAlert_userid_3() {
		return alert_userid_3;
	}
	public void setAlert_userid_3(String alert_userid_3) {
		this.alert_userid_3 = alert_userid_3;
	}
	public String getAlert_user_3() {
		return alert_user_3;
	}
	public void setAlert_user_3(String alert_user_3) {
		this.alert_user_3 = alert_user_3;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getSuspended() {
		return suspended;
	}
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getSuspended_date() {
		return suspended_date;
	}
	public void setSuspended_date(Date suspended_date) {
		this.suspended_date = suspended_date;
	}
	public Date getActivated_date() {
		return activated_date;
	}
	public void setActivated_date(Date activated_date) {
		this.activated_date = activated_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

}

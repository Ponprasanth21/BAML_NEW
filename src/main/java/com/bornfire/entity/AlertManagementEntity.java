package com.bornfire.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "\"BAML_ALERT_MGMT_TABLE\"", schema = "\"AML\"")
public class AlertManagementEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"SRL_NO\"")
	private String	srl_no;

	@Column(name = "\"ALERT_DATE\"")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	alert_date;

	@Column(name = "\"ALERT_CODE\"")
	private String	alert_code;

	@Column(name = "\"ALERT_DESC\"")
	private String	alert_desc;

	@Column(name = "\"PARAM_1\"")
	private String	param_1;

	@Column(name = "\"PARAM_2\"")
	private String	param_2;

	@Column(name = "\"PARAM_3\"")
	private String	param_3;

	@Column(name = "\"PARAM_VALUE_1\"")
	private BigDecimal	param_value_1;

	@Column(name = "\"PARAM_VALUE_2\"")
	private BigDecimal	param_value_2;

	@Column(name = "\"PARAM_VALUE_3\"")
	private BigDecimal	param_value_3;

	@Column(name = "\"EMAIL_FLG\"")
	private String	email_flg;

	@Column(name = "\"EMAIL_1\"")
	private String	email_1;

	@Column(name = "\"EMAIL_2\"")
	private String	email_2;

	@Column(name = "\"EMAIL_3\"")
	private String	email_3;

	@Column(name = "\"EMAIL_USER_1\"")
	private String	email_user_1;

	@Column(name = "\"EMAIL_USER_2\"")
	private String	email_user_2;

	@Column(name = "\"EMAIL_USER_3\"")
	private String	email_user_3;

	@Column(name = "\"SMS_FLG\"")
	private String	sms_flg;

	@Column(name = "\"MOBILE_1\"")
	private BigDecimal	mobile_1;

	@Column(name = "\"MOBILE_2\"")
	private BigDecimal	mobile_2;

	@Column(name = "\"MOBILE_3\"")
	private BigDecimal	mobile_3;

	@Column(name = "\"MOBILE_USER_1\"")
	private String	mobile_user_1;

	@Column(name = "\"MOBILE_USER_2\"")
	private String	mobile_user_2;

	@Column(name = "\"MOBILE_USER_3\"")
	private String	mobile_user_3;

	@Column(name = "\"ALERT_FLG\"")
	private String	alert_flg;

	@Column(name = "\"ALERT_USERID_1\"")
	private String	alert_userid_1;

	@Column(name = "\"ALERT_USERID_2\"")
	private String	alert_userid_2;

	@Column(name = "\"ALERT_USERID_3\"")
	private String	alert_userid_3;

	@Column(name = "\"ALERT_USER_1\"")
	private String	alert_user_1;

	@Column(name = "\"ALERT_USER_2\"")
	private String	alert_user_2;

	@Column(name = "\"ALERT_USER_3\"")
	private String	alert_user_3;

	@Column(name = "\"START_DATE\"")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date;

	@Column(name = "\"END_DATE\"")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	end_date;

	@Column(name = "\"SUSPEND_FLG\"")
	private String	suspend_flg;

	@Column(name = "\"REMARKS\"")
	private String	remarks;

	@Column(name = "\"SUSPEND_DATE\"")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	suspend_date;

	@Column(name = "\"ACTIVATE_DATE\"")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	activate_date;

	@Column(name = "\"ENTITY_FLG\"")
	private String	entity_flg;

	@Column(name = "\"DEL_FLG\"")
	private String	del_flg;

	@Column(name = "\"MOD_FLG\"")
	private String	mod_flg;

	@Column(name = "\"ENTRY_USER\"")
	private String	entry_user;

	@Column(name = "\"MODIFY_USER\"")
	private String	modify_user;

	@Column(name = "\"AUTH_USER\"")
	private String	auth_user;

	@Column(name = "\"ENTRY_TIME\"")
	private Date	entry_time;

	@Column(name = "\"MODIFY_TIME\"")
	private Date	modify_time;

	@Column(name = "\"AUTH_TIME\"")
	private Date	auth_time;
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public Date getAlert_date() {
		return alert_date;
	}
	public void setAlert_date(Date alert_date) {
		this.alert_date = alert_date;
	}
	public String getAlert_code() {
		return alert_code;
	}
	public void setAlert_code(String alert_code) {
		this.alert_code = alert_code;
	}
	public String getAlert_desc() {
		return alert_desc;
	}
	public void setAlert_desc(String alert_desc) {
		this.alert_desc = alert_desc;
	}
	public String getParam_1() {
		return param_1;
	}
	public void setParam_1(String param_1) {
		this.param_1 = param_1;
	}
	public String getParam_2() {
		return param_2;
	}
	public void setParam_2(String param_2) {
		this.param_2 = param_2;
	}
	public String getParam_3() {
		return param_3;
	}
	public void setParam_3(String param_3) {
		this.param_3 = param_3;
	}
	public BigDecimal getParam_value_1() {
		return param_value_1;
	}
	public void setParam_value_1(BigDecimal param_value_1) {
		this.param_value_1 = param_value_1;
	}
	public BigDecimal getParam_value_2() {
		return param_value_2;
	}
	public void setParam_value_2(BigDecimal param_value_2) {
		this.param_value_2 = param_value_2;
	}
	public BigDecimal getParam_value_3() {
		return param_value_3;
	}
	public void setParam_value_3(BigDecimal param_value_3) {
		this.param_value_3 = param_value_3;
	}
	public String getEmail_flg() {
		return email_flg;
	}
	public void setEmail_flg(String email_flg) {
		this.email_flg = email_flg;
	}
	public String getEmail_1() {
		return email_1;
	}
	public void setEmail_1(String email_1) {
		this.email_1 = email_1;
	}
	public String getEmail_2() {
		return email_2;
	}
	public void setEmail_2(String email_2) {
		this.email_2 = email_2;
	}
	public String getEmail_3() {
		return email_3;
	}
	public void setEmail_3(String email_3) {
		this.email_3 = email_3;
	}
	public String getEmail_user_1() {
		return email_user_1;
	}
	public void setEmail_user_1(String email_user_1) {
		this.email_user_1 = email_user_1;
	}
	public String getEmail_user_2() {
		return email_user_2;
	}
	public void setEmail_user_2(String email_user_2) {
		this.email_user_2 = email_user_2;
	}
	public String getEmail_user_3() {
		return email_user_3;
	}
	public void setEmail_user_3(String email_user_3) {
		this.email_user_3 = email_user_3;
	}
	public String getSms_flg() {
		return sms_flg;
	}
	public void setSms_flg(String sms_flg) {
		this.sms_flg = sms_flg;
	}
	public BigDecimal getMobile_1() {
		return mobile_1;
	}
	public void setMobile_1(BigDecimal mobile_1) {
		this.mobile_1 = mobile_1;
	}
	public BigDecimal getMobile_2() {
		return mobile_2;
	}
	public void setMobile_2(BigDecimal mobile_2) {
		this.mobile_2 = mobile_2;
	}
	public BigDecimal getMobile_3() {
		return mobile_3;
	}
	public void setMobile_3(BigDecimal mobile_3) {
		this.mobile_3 = mobile_3;
	}
	public String getMobile_user_1() {
		return mobile_user_1;
	}
	public void setMobile_user_1(String mobile_user_1) {
		this.mobile_user_1 = mobile_user_1;
	}
	public String getMobile_user_2() {
		return mobile_user_2;
	}
	public void setMobile_user_2(String mobile_user_2) {
		this.mobile_user_2 = mobile_user_2;
	}
	public String getMobile_user_3() {
		return mobile_user_3;
	}
	public void setMobile_user_3(String mobile_user_3) {
		this.mobile_user_3 = mobile_user_3;
	}
	public String getAlert_flg() {
		return alert_flg;
	}
	public void setAlert_flg(String alert_flg) {
		this.alert_flg = alert_flg;
	}
	public String getAlert_userid_1() {
		return alert_userid_1;
	}
	public void setAlert_userid_1(String alert_userid_1) {
		this.alert_userid_1 = alert_userid_1;
	}
	public String getAlert_userid_2() {
		return alert_userid_2;
	}
	public void setAlert_userid_2(String alert_userid_2) {
		this.alert_userid_2 = alert_userid_2;
	}
	public String getAlert_userid_3() {
		return alert_userid_3;
	}
	public void setAlert_userid_3(String alert_userid_3) {
		this.alert_userid_3 = alert_userid_3;
	}
	public String getAlert_user_1() {
		return alert_user_1;
	}
	public void setAlert_user_1(String alert_user_1) {
		this.alert_user_1 = alert_user_1;
	}
	public String getAlert_user_2() {
		return alert_user_2;
	}
	public void setAlert_user_2(String alert_user_2) {
		this.alert_user_2 = alert_user_2;
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
	public String getSuspend_flg() {
		return suspend_flg;
	}
	public void setSuspend_flg(String suspend_flg) {
		this.suspend_flg = suspend_flg;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getSuspend_date() {
		return suspend_date;
	}
	public void setSuspend_date(Date suspend_date) {
		this.suspend_date = suspend_date;
	}
	public Date getActivate_date() {
		return activate_date;
	}
	public void setActivate_date(Date activate_date) {
		this.activate_date = activate_date;
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
	public String getMod_flg() {
		return mod_flg;
	}
	public void setMod_flg(String mod_flg) {
		this.mod_flg = mod_flg;
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
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

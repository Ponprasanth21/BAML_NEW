package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"BAML_SOL\"", schema =  "\"AML\"")
public class BAMLSolEntity {
	
	@Column(name = "\"BANK_CODE\"")
	private String bank_code;

	@Column(name = "\"BANK_NAME\"")
	private String bank_name;

	@Id
	@Column(name = "\"SOL_ID\"")
	private String sol_id;

	@Column(name = "\"SOL_DESC\"")
	private String sol_desc;

	@Column(name = "\"ADDR_1\"")
	private String addr_1;

	@Column(name = "\"ADDR_2\"")
	private String addr_2;

	@Column(name = "\"CITY_CODE\"")
	private String city_code;

	@Column(name = "\"STATE_CODE\"")
	private String state_code;

	@Column(name = "\"COUNTRY_CODE\"")
	private String country_code;

	@Column(name = "\"ZIP_CODE\"")
	private String zip_code;

	@Column(name = "\"SCREEN_FLG\"")
	private String screen_flg;

	@Column(name = "\"MONITOR_FLG\"")
	private String monitor_flg;

	@Column(name = "\"EMAIL_ALERT_FLG\"")
	private String email_alert_flg;

	@Column(name = "\"SMS_ALERT_FLG\"")
	private String sms_alert_flg;

	@Column(name = "\"USER_ALERT_FLG\"")
	private String user_alert_flg;

	@Column(name = "\"ALERT_TYPE\"")
	private String alert_type;

	@Column(name = "\"AML_PARM_1\"")
	private String aml_parm_1;

	@Column(name = "\"AML_PARM_2\"")
	private String aml_parm_2;

	@Column(name = "\"AML_PARM_3\"")
	private String aml_parm_3;

	@Column(name = "\"AML_PARM_4\"")
	private String aml_parm_4;

	@Column(name = "\"ENTITY_FLG\"")
	private String entity_flg;

	@Column(name = "\"DEL_FLG\"")
	private String del_flg;

	@Column(name = "\"MODIFY_FLG\"")
	private String modify_flg;

	@Column(name = "\"ENTRY_USER\"")
	private String entry_user;

	@Column(name = "\"MODIFY_USER\"")
	private String modify_user;

	@Column(name = "\"VERIFY_USER\"")
	private String verify_user;

	@Column(name = "\"ENTRY_TIME\"")
	private Date entry_time;

	@Column(name = "\"MODIFY_TIME\"")
	private Date modify_time;

	@Column(name = "\"VERIFY_TIME\"")
	private Date verify_time;

	@Column(name = "\"BR_CODE\"")
	private String br_code;

	@Column(name = "\"BOD_SYSDATE\"")
	private Date bod_sysdate;

	@Column(name = "\"SOL_TYPE\"")
	private String sol_type;	
	
	public String getBank_code() {
		return bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public String getSol_id() {
		return sol_id;
	}
	public String getSol_desc() {
		return sol_desc;
	}
	public String getAddr_1() {
		return addr_1;
	}
	public String getAddr_2() {
		return addr_2;
	}
	public String getCity_code() {
		return city_code;
	}
	public String getState_code() {
		return state_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public String getZip_code() {
		return zip_code;
	}
	public String getScreen_flg() {
		return screen_flg;
	}
	public String getMonitor_flg() {
		return monitor_flg;
	}
	public String getEmail_alert_flg() {
		return email_alert_flg;
	}
	public String getSms_alert_flg() {
		return sms_alert_flg;
	}
	public String getUser_alert_flg() {
		return user_alert_flg;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public String getAml_parm_1() {
		return aml_parm_1;
	}
	public String getAml_parm_2() {
		return aml_parm_2;
	}
	public String getAml_parm_3() {
		return aml_parm_3;
	}
	public String getAml_parm_4() {
		return aml_parm_4;
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
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public void setSol_desc(String sol_desc) {
		this.sol_desc = sol_desc;
	}
	public void setAddr_1(String addr_1) {
		this.addr_1 = addr_1;
	}
	public void setAddr_2(String addr_2) {
		this.addr_2 = addr_2;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public void setScreen_flg(String screen_flg) {
		this.screen_flg = screen_flg;
	}
	public void setMonitor_flg(String monitor_flg) {
		this.monitor_flg = monitor_flg;
	}
	public void setEmail_alert_flg(String email_alert_flg) {
		this.email_alert_flg = email_alert_flg;
	}
	public void setSms_alert_flg(String sms_alert_flg) {
		this.sms_alert_flg = sms_alert_flg;
	}
	public void setUser_alert_flg(String user_alert_flg) {
		this.user_alert_flg = user_alert_flg;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public void setAml_parm_1(String aml_parm_1) {
		this.aml_parm_1 = aml_parm_1;
	}
	public void setAml_parm_2(String aml_parm_2) {
		this.aml_parm_2 = aml_parm_2;
	}
	public void setAml_parm_3(String aml_parm_3) {
		this.aml_parm_3 = aml_parm_3;
	}
	public void setAml_parm_4(String aml_parm_4) {
		this.aml_parm_4 = aml_parm_4;
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
	public String getBr_code() {
		return br_code;
	}
	public Date getBod_sysdate() {
		return bod_sysdate;
	}
	public String getSol_type() {
		return sol_type;
	}
	public void setBr_code(String br_code) {
		this.br_code = br_code;
	}
	public void setBod_sysdate(Date bod_sysdate) {
		this.bod_sysdate = bod_sysdate;
	}
	public void setSol_type(String sol_type) {
		this.sol_type = sol_type;
	}
	
	
	public BAMLSolEntity(String bank_code, String bank_name, String sol_id, String sol_desc, String addr_1,
			String addr_2, String city_code, String state_code, String country_code, String zip_code, String screen_flg,
			String monitor_flg, String email_alert_flg, String sms_alert_flg, String user_alert_flg, String alert_type,
			String aml_parm_1, String aml_parm_2, String aml_parm_3, String aml_parm_4, String entity_flg,
			String del_flg, String modify_flg, String entry_user, String modify_user, String verify_user,
			Date entry_time, Date modify_time, Date verify_time, String br_code, Date bod_sysdate, String sol_type) {
		super();
		this.bank_code = bank_code;
		this.bank_name = bank_name;
		this.sol_id = sol_id;
		this.sol_desc = sol_desc;
		this.addr_1 = addr_1;
		this.addr_2 = addr_2;
		this.city_code = city_code;
		this.state_code = state_code;
		this.country_code = country_code;
		this.zip_code = zip_code;
		this.screen_flg = screen_flg;
		this.monitor_flg = monitor_flg;
		this.email_alert_flg = email_alert_flg;
		this.sms_alert_flg = sms_alert_flg;
		this.user_alert_flg = user_alert_flg;
		this.alert_type = alert_type;
		this.aml_parm_1 = aml_parm_1;
		this.aml_parm_2 = aml_parm_2;
		this.aml_parm_3 = aml_parm_3;
		this.aml_parm_4 = aml_parm_4;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.br_code = br_code;
		this.bod_sysdate = bod_sysdate;
		this.sol_type = sol_type;
	}
	
	
	public BAMLSolEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}

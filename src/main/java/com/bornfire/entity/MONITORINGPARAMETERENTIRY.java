package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_MON_ALERT_PARAM")
public class MONITORINGPARAMETERENTIRY {

	@Id
	private String ref_no;
	private String menu;
	private String value1;
	private String table1;
	private String table_field;
	private String user_id_one;
	private String user_name_one;
	private String user_email_one;
	private String user_mobile_one;
	private String user_id_two;
	private String user_name_two;
	private String user_email_two;
	private String user_mobile_two;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date start_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date end_date;
	private String suspend_flg;
	private String remarks;
	private String entity_flg;
	private String del_flg;
	private String modify_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date verify_time;

	public String getRef_no() {
		return ref_no;
	}

	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getTable1() {
		return table1;
	}

	public void setTable1(String table1) {
		this.table1 = table1;
	}

	public String getTable_field() {
		return table_field;
	}

	public void setTable_field(String table_field) {
		this.table_field = table_field;
	}

	public String getUser_id_one() {
		return user_id_one;
	}

	public void setUser_id_one(String user_id_one) {
		this.user_id_one = user_id_one;
	}

	public String getUser_name_one() {
		return user_name_one;
	}

	public void setUser_name_one(String user_name_one) {
		this.user_name_one = user_name_one;
	}

	public String getUser_email_one() {
		return user_email_one;
	}

	public void setUser_email_one(String user_email_one) {
		this.user_email_one = user_email_one;
	}

	public String getUser_mobile_one() {
		return user_mobile_one;
	}

	public void setUser_mobile_one(String user_mobile_one) {
		this.user_mobile_one = user_mobile_one;
	}

	public String getUser_id_two() {
		return user_id_two;
	}

	public void setUser_id_two(String user_id_two) {
		this.user_id_two = user_id_two;
	}

	public String getUser_name_two() {
		return user_name_two;
	}

	public void setUser_name_two(String user_name_two) {
		this.user_name_two = user_name_two;
	}

	public String getUser_email_two() {
		return user_email_two;
	}

	public void setUser_email_two(String user_email_two) {
		this.user_email_two = user_email_two;
	}

	public String getUser_mobile_two() {
		return user_mobile_two;
	}

	public void setUser_mobile_two(String user_mobile_two) {
		this.user_mobile_two = user_mobile_two;
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

	public Date getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public MONITORINGPARAMETERENTIRY(String ref_no, String menu, String value1, String table1, String table_field,
			String user_id_one, String user_name_one, String user_email_one, String user_mobile_one, String user_id_two,
			String user_name_two, String user_email_two, String user_mobile_two, Date start_date, Date end_date,
			String suspend_flg, String remarks, String entity_flg, String del_flg, String modify_flg, String entry_user,
			String modify_user, String verify_user, Date entry_time, Date modify_time, Date verify_time) {
		super();
		this.ref_no = ref_no;
		this.menu = menu;
		this.value1 = value1;
		this.table1 = table1;
		this.table_field = table_field;
		this.user_id_one = user_id_one;
		this.user_name_one = user_name_one;
		this.user_email_one = user_email_one;
		this.user_mobile_one = user_mobile_one;
		this.user_id_two = user_id_two;
		this.user_name_two = user_name_two;
		this.user_email_two = user_email_two;
		this.user_mobile_two = user_mobile_two;
		this.start_date = start_date;
		this.end_date = end_date;
		this.suspend_flg = suspend_flg;
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
	}

	public MONITORINGPARAMETERENTIRY() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MONITORING PARAMETER [REF_No=" + ref_no + ", MENU=" + menu + ", VALUE=" + value1 + ", TABLE="
				+ table1 + ", TABLE_FIELD=" + table_field + ", USER_ID_ONE=" + user_id_one + ", USER_NAME_ONE="
				+ user_name_one + ", USER_EMAIL_ONE=" + user_email_one + ", USER_MOBILE_ONE=" + user_mobile_one
				+ ", USER_ID_TWO=" + user_id_two + ", USER_NAME_TWO=" + user_name_two + ", USER_EMAIL_TWO="
				+ user_email_two + ", USER_MOBILE_TWO=" + user_mobile_two + ", START_DATE=" + start_date + ", END_DATE="
				+ end_date + " entity_flg=" + entity_flg + ", ENTRY_USER=" + entry_user + ", MODIFY_USER=" + modify_user
				+ ",ENTRY_TIME=" + entry_time + ", MODIFY_TIME=" + modify_time + "]";
	}

}

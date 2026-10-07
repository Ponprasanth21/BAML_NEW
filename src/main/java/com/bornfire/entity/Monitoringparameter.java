package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_RULE_ENGINE_TABLE")
public class Monitoringparameter  {
	
	
	
	@Id
	private String	srl_no;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	alert_date;
	private String	rule_type;
	private String	rule_sub_type;
	private String	rule_type_desc;
	private String	rule_remarks;
	private String	rule_code;
	private String	rule_code_desc;
	private String	script_type;
	private String	script_name;
	private String	exec_type;
	private String	frequency;
	private BigDecimal	start_day;
	private String	bj_remarks;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	last_tried_date;
	private String	exce_status;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	next_exec_status;
	private String	susp_flag;
	private String	add_parm_flag;
	private String	param_remarks;
	private String	given_period_flag;
	private BigDecimal	no_of_days;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	period_start_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	period_end_date;
	private String	customer_type;
	private String	account_type;
	private BigDecimal	threshold_limit;
	private BigDecimal	percentage;
	private BigDecimal	avg_trans;
	private BigDecimal	avg_amount;
	private BigDecimal	low_value;
	private BigDecimal	high_value;
	private String	match_criteria;
	private String	match_remarks;
	private String	menu_ind;
	private String	menu_names;
	private String	entity_flag;
	private String	del_flag;
	private String	modify_flag;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	verify_time;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
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
	public String getRule_type() {
		return rule_type;
	}
	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}
	public String getRule_sub_type() {
		return rule_sub_type;
	}
	public void setRule_sub_type(String rule_sub_type) {
		this.rule_sub_type = rule_sub_type;
	}
	public String getRule_type_desc() {
		return rule_type_desc;
	}
	public void setRule_type_desc(String rule_type_desc) {
		this.rule_type_desc = rule_type_desc;
	}
	public String getRule_remarks() {
		return rule_remarks;
	}
	public void setRule_remarks(String rule_remarks) {
		this.rule_remarks = rule_remarks;
	}
	public String getRule_code() {
		return rule_code;
	}
	public void setRule_code(String rule_code) {
		this.rule_code = rule_code;
	}
	public String getRule_code_desc() {
		return rule_code_desc;
	}
	public void setRule_code_desc(String rule_code_desc) {
		this.rule_code_desc = rule_code_desc;
	}
	public String getScript_type() {
		return script_type;
	}
	public void setScript_type(String script_type) {
		this.script_type = script_type;
	}
	public String getScript_name() {
		return script_name;
	}
	public void setScript_name(String script_name) {
		this.script_name = script_name;
	}
	public String getExec_type() {
		return exec_type;
	}
	public void setExec_type(String exec_type) {
		this.exec_type = exec_type;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public BigDecimal getStart_day() {
		return start_day;
	}
	public void setStart_day(BigDecimal start_day) {
		this.start_day = start_day;
	}
	public String getBj_remarks() {
		return bj_remarks;
	}
	public void setBj_remarks(String bj_remarks) {
		this.bj_remarks = bj_remarks;
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
	public Date getLast_tried_date() {
		return last_tried_date;
	}
	public void setLast_tried_date(Date last_tried_date) {
		this.last_tried_date = last_tried_date;
	}
	public String getExce_status() {
		return exce_status;
	}
	public void setExce_status(String exce_status) {
		this.exce_status = exce_status;
	}
	public Date getNext_exec_status() {
		return next_exec_status;
	}
	public void setNext_exec_status(Date next_exec_status) {
		this.next_exec_status = next_exec_status;
	}
	public String getSusp_flag() {
		return susp_flag;
	}
	public void setSusp_flag(String susp_flag) {
		this.susp_flag = susp_flag;
	}
	public String getAdd_parm_flag() {
		return add_parm_flag;
	}
	public void setAdd_parm_flag(String add_parm_flag) {
		this.add_parm_flag = add_parm_flag;
	}
	public String getParam_remarks() {
		return param_remarks;
	}
	public void setParam_remarks(String param_remarks) {
		this.param_remarks = param_remarks;
	}
	public String getGiven_period_flag() {
		return given_period_flag;
	}
	public void setGiven_period_flag(String given_period_flag) {
		this.given_period_flag = given_period_flag;
	}
	public BigDecimal getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(BigDecimal no_of_days) {
		this.no_of_days = no_of_days;
	}
	public Date getPeriod_start_date() {
		return period_start_date;
	}
	public void setPeriod_start_date(Date period_start_date) {
		this.period_start_date = period_start_date;
	}
	public Date getPeriod_end_date() {
		return period_end_date;
	}
	public void setPeriod_end_date(Date period_end_date) {
		this.period_end_date = period_end_date;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public BigDecimal getThreshold_limit() {
		return threshold_limit;
	}
	public void setThreshold_limit(BigDecimal threshold_limit) {
		this.threshold_limit = threshold_limit;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
	public BigDecimal getAvg_trans() {
		return avg_trans;
	}
	public void setAvg_trans(BigDecimal avg_trans) {
		this.avg_trans = avg_trans;
	}
	public BigDecimal getAvg_amount() {
		return avg_amount;
	}
	public void setAvg_amount(BigDecimal avg_amount) {
		this.avg_amount = avg_amount;
	}
	public BigDecimal getLow_value() {
		return low_value;
	}
	public void setLow_value(BigDecimal low_value) {
		this.low_value = low_value;
	}
	public BigDecimal getHigh_value() {
		return high_value;
	}
	public void setHigh_value(BigDecimal high_value) {
		this.high_value = high_value;
	}
	public String getMatch_criteria() {
		return match_criteria;
	}
	public void setMatch_criteria(String match_criteria) {
		this.match_criteria = match_criteria;
	}
	public String getMatch_remarks() {
		return match_remarks;
	}
	public void setMatch_remarks(String match_remarks) {
		this.match_remarks = match_remarks;
	}
	public String getMenu_ind() {
		return menu_ind;
	}
	public void setMenu_ind(String menu_ind) {
		this.menu_ind = menu_ind;
	}
	public String getMenu_names() {
		return menu_names;
	}
	public void setMenu_names(String menu_names) {
		this.menu_names = menu_names;
	}
	public String getEntity_flag() {
		return entity_flag;
	}
	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
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


	public Monitoringparameter() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Monitoringparameter [ srl_no=" + srl_no + ", alert_date=" + alert_date
				+ ", rule_type=" + rule_type + ", rule_sub_type=" + rule_sub_type + ", rule_type_desc=" + rule_type_desc
				+ ", rule_remarks=" + rule_remarks + ", rule_code=" + rule_code + ", rule_code_desc=" + rule_code_desc
				+ ", script_type=" + script_type + ", script_name=" + script_name + ", exec_type=" + exec_type
				+ ", frequency=" + frequency + ", start_day=" + start_day + ", bj_remarks=" + bj_remarks
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", last_tried_date=" + last_tried_date
				+ ", exce_status=" + exce_status + ", next_exec_status=" + next_exec_status + ", susp_flag=" + susp_flag
				+ ", add_parm_flag=" + add_parm_flag + ", param_remarks=" + param_remarks + ", given_period_flag="
				+ given_period_flag + ", no_of_days=" + no_of_days + ", period_start_date=" + period_start_date
				+ ", period_end_date=" + period_end_date + ", customer_type=" + customer_type + ", account_type="
				+ account_type + ", threshold_limit=" + threshold_limit + ", percentage=" + percentage + ", avg_trans="
				+ avg_trans + ", avg_amount=" + avg_amount + ", low_value=" + low_value + ", high_value=" + high_value
				+ ", match_criteria=" + match_criteria + ", match_remarks=" + match_remarks + ", menu_ind=" + menu_ind
				+ ", menu_names=" + menu_names + ", entity_flag=" + entity_flag + ", del_flag=" + del_flag
				+ ", modify_flag=" + modify_flag + ", entry_time=" + entry_time + ", modify_time=" + modify_time
				+ ", verify_time=" + verify_time + ", entry_user=" + entry_user + ", modify_user=" + modify_user
				+ ", verify_user=" + verify_user + "]";
	}
	
	

}
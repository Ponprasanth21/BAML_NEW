package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity	
@Table(name="BAML_BATCH_JOB_SCHEDULER")
public class BAMLBatchJobSchedular {

	
	@Id
	private String	job_id;
	private String	job_desc;
	private String	script_type;
	private String	script_name;
	private String	parmeters;
	private String	conditions;
	private String	frequency;
	private String	start_day;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	start_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	end_date;
	private String	suspend_flg;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	suspend_date;
	private String	exec_process;
	private String	exec_freq;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	last_tried_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	next_due_date;
	private String	alert_flg;
	private String	aler_user;
	private String	alert_email_id;
	private String	alert_mobile;
	private String	entity_flg;
	private String	del_flg;
	private String	modify_flg;
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
	private String exec_status;
	
	public String getJob_id() {
		return job_id;
	}
	public String getJob_desc() {
		return job_desc;
	}
	public String getScript_type() {
		return script_type;
	}
	public String getScript_name() {
		return script_name;
	}
	public String getParmeters() {
		return parmeters;
	}
	public String getConditions() {
		return conditions;
	}
	public String getFrequency() {
		return frequency;
	}
	public String getStart_day() {
		return start_day;
	}
	public Date getStart_date() {
		return start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public String getSuspend_flg() {
		return suspend_flg;
	}
	public Date getSuspend_date() {
		return suspend_date;
	}
	public String getExec_process() {
		return exec_process;
	}
	public String getExec_freq() {
		return exec_freq;
	}
	public Date getLast_tried_date() {
		return last_tried_date;
	}
	public Date getNext_due_date() {
		return next_due_date;
	}
	public String getAlert_flg() {
		return alert_flg;
	}
	public String getAler_user() {
		return aler_user;
	}
	public String getAlert_email_id() {
		return alert_email_id;
	}
	public String getAlert_mobile() {
		return alert_mobile;
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
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public void setJob_desc(String job_desc) {
		this.job_desc = job_desc;
	}
	public void setScript_type(String script_type) {
		this.script_type = script_type;
	}
	public void setScript_name(String script_name) {
		this.script_name = script_name;
	}
	public void setParmeters(String parmeters) {
		this.parmeters = parmeters;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public void setStart_day(String start_day) {
		this.start_day = start_day;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public void setSuspend_flg(String suspend_flg) {
		this.suspend_flg = suspend_flg;
	}
	public void setSuspend_date(Date suspend_date) {
		this.suspend_date = suspend_date;
	}
	public void setExec_process(String exec_process) {
		this.exec_process = exec_process;
	}
	public void setExec_freq(String exec_freq) {
		this.exec_freq = exec_freq;
	}
	public void setLast_tried_date(Date last_tried_date) {
		this.last_tried_date = last_tried_date;
	}
	public void setNext_due_date(Date next_due_date) {
		this.next_due_date = next_due_date;
	}
	public void setAlert_flg(String alert_flg) {
		this.alert_flg = alert_flg;
	}
	public void setAler_user(String aler_user) {
		this.aler_user = aler_user;
	}
	public void setAlert_email_id(String alert_email_id) {
		this.alert_email_id = alert_email_id;
	}
	public void setAlert_mobile(String alert_mobile) {
		this.alert_mobile = alert_mobile;
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
	
	
	
	public String getExec_status() {
		return exec_status;
	}
	public void setExec_status(String exec_status) {
		this.exec_status = exec_status;
	}
	
	public BAMLBatchJobSchedular() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BAMLBatchJobSchedular(String job_id, String job_desc, String script_type, String script_name,
			String parmeters, String conditions, String frequency, String start_day, Date start_date, Date end_date,
			String suspend_flg, Date suspend_date, String exec_process, String exec_freq, Date last_tried_date,
			Date next_due_date, String alert_flg, String aler_user, String alert_email_id, String alert_mobile,
			String entity_flg, String del_flg, String modify_flg, String entry_user, String modify_user,
			String verify_user, Date entry_time, Date modify_time, Date verify_time, String exec_status) {
		super();
		this.job_id = job_id;
		this.job_desc = job_desc;
		this.script_type = script_type;
		this.script_name = script_name;
		this.parmeters = parmeters;
		this.conditions = conditions;
		this.frequency = frequency;
		this.start_day = start_day;
		this.start_date = start_date;
		this.end_date = end_date;
		this.suspend_flg = suspend_flg;
		this.suspend_date = suspend_date;
		this.exec_process = exec_process;
		this.exec_freq = exec_freq;
		this.last_tried_date = last_tried_date;
		this.next_due_date = next_due_date;
		this.alert_flg = alert_flg;
		this.aler_user = aler_user;
		this.alert_email_id = alert_email_id;
		this.alert_mobile = alert_mobile;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.exec_status = exec_status;
	}
	
	
	
	
	

}

package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_STR_INTERNAL")
public class BAML_STR_Internal {

	@Id
	private String str_ref_no;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date str_date;
	private String str_by_person;
	private String str_to_mlro_dmlro;
	private String str_inform;
	private String str_action;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date entry_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date auth_time;
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_report;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_submission;
	private String remarks;

	public String getStr_ref_no() {
		return str_ref_no;
	}

	public Date getStr_date() {
		return str_date;
	}

	public String getStr_by_person() {
		return str_by_person;
	}

	public String getStr_to_mlro_dmlro() {
		return str_to_mlro_dmlro;
	}

	public String getStr_inform() {
		return str_inform;
	}

	public String getStr_action() {
		return str_action;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public String getEntity_flg() {
		return entity_flg;
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

	public String getAuth_user() {
		return auth_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public Date getAuth_time() {
		return auth_time;
	}

	public Date getDate_of_report() {
		return date_of_report;
	}

	public Date getDate_submission() {
		return date_submission;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setStr_ref_no(String str_ref_no) {
		this.str_ref_no = str_ref_no;
	}

	public void setStr_date(Date str_date) {
		this.str_date = str_date;
	}

	public void setStr_by_person(String str_by_person) {
		this.str_by_person = str_by_person;
	}

	public void setStr_to_mlro_dmlro(String str_to_mlro_dmlro) {
		this.str_to_mlro_dmlro = str_to_mlro_dmlro;
	}

	public void setStr_inform(String str_inform) {
		this.str_inform = str_inform;
	}

	public void setStr_action(String str_action) {
		this.str_action = str_action;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
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

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public void setDate_of_report(Date date_of_report) {
		this.date_of_report = date_of_report;
	}

	public void setDate_submission(Date date_submission) {
		this.date_submission = date_submission;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BAML_STR_Internal(String str_ref_no, Date str_date, String str_by_person, String str_to_mlro_dmlro,
			String str_inform, String str_action, String del_flg, String entity_flg, String modify_flg,
			String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time,
			Date date_of_report, Date date_submission, String remarks) {
		super();
		this.str_ref_no = str_ref_no;
		this.str_date = str_date;
		this.str_by_person = str_by_person;
		this.str_to_mlro_dmlro = str_to_mlro_dmlro;
		this.str_inform = str_inform;
		this.str_action = str_action;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.date_of_report = date_of_report;
		this.date_submission = date_submission;
		this.remarks = remarks;
	}

	public BAML_STR_Internal() {
		super();
		// TODO Auto-generated constructor stub
	}

}

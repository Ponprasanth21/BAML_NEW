package com.bornfire.entity.t25;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T25_AMLCFT_APPL_TABLE")
public class T25Report {

	private String d1a_ho;
	private String d2a_bo;
	private String d3a_nbdti;
	private String p1b_ho_fully_applied;
	private String p2b_bo_fully_applied;
	private String p3b_nbdti_not_applicable;
	private String c1b_ho_fully_applied;
	private String c2b_bo_fully_applied;
	private String c3b_nbdti_not_applicable;
	@Id
	private String report_code;
	private String report_name;
	
	private Date report_date;
	private Date report_due_date;
	private Date rep_submit_date;
	private Date rep_period_from;
	private Date rep_period_to;
	private String rep_freq;
	private String nil_report_flg;
	private String arch_flg;
	
	private String entity_flg;
	private String modify_flg;
	private String del_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private Date entry_time;
	private Date modify_time;
	private Date verify_time;

	public String getD1a_ho() {
		return d1a_ho;
	}

	public void setD1a_ho(String d1a_ho) {
		this.d1a_ho = d1a_ho;
	}

	public String getD2a_bo() {
		return d2a_bo;
	}

	public void setD2a_bo(String d2a_bo) {
		this.d2a_bo = d2a_bo;
	}

	public String getD3a_nbdti() {
		return d3a_nbdti;
	}

	public void setD3a_nbdti(String d3a_nbdti) {
		this.d3a_nbdti = d3a_nbdti;
	}

	public String getP1b_ho_fully_applied() {
		return p1b_ho_fully_applied;
	}

	public void setP1b_ho_fully_applied(String p1b_ho_fully_applied) {
		this.p1b_ho_fully_applied = p1b_ho_fully_applied;
	}

	public String getP2b_bo_fully_applied() {
		return p2b_bo_fully_applied;
	}

	public void setP2b_bo_fully_applied(String p2b_bo_fully_applied) {
		this.p2b_bo_fully_applied = p2b_bo_fully_applied;
	}

	public String getP3b_nbdti_not_applicable() {
		return p3b_nbdti_not_applicable;
	}

	public void setP3b_nbdti_not_applicable(String p3b_nbdti_not_applicable) {
		this.p3b_nbdti_not_applicable = p3b_nbdti_not_applicable;
	}

	public String getC1b_ho_fully_applied() {
		return c1b_ho_fully_applied;
	}

	public void setC1b_ho_fully_applied(String c1b_ho_fully_applied) {
		this.c1b_ho_fully_applied = c1b_ho_fully_applied;
	}

	public String getC2b_bo_fully_applied() {
		return c2b_bo_fully_applied;
	}

	public void setC2b_bo_fully_applied(String c2b_bo_fully_applied) {
		this.c2b_bo_fully_applied = c2b_bo_fully_applied;
	}

	public String getC3b_nbdti_not_applicable() {
		return c3b_nbdti_not_applicable;
	}

	public void setC3b_nbdti_not_applicable(String c3b_nbdti_not_applicable) {
		this.c3b_nbdti_not_applicable = c3b_nbdti_not_applicable;
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

	public Date getReport_due_date() {
		return report_due_date;
	}

	public void setReport_due_date(Date report_due_date) {
		this.report_due_date = report_due_date;
	}

	public Date getRep_submit_date() {
		return rep_submit_date;
	}

	public void setRep_submit_date(Date rep_submit_date) {
		this.rep_submit_date = rep_submit_date;
	}

	public Date getRep_period_from() {
		return rep_period_from;
	}

	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}

	public Date getRep_period_to() {
		return rep_period_to;
	}

	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}

	public String getRep_freq() {
		return rep_freq;
	}

	public void setRep_freq(String rep_freq) {
		this.rep_freq = rep_freq;
	}

	public String getNil_report_flg() {
		return nil_report_flg;
	}

	public void setNil_report_flg(String nil_report_flg) {
		this.nil_report_flg = nil_report_flg;
	}

	public String getArch_flg() {
		return arch_flg;
	}

	public void setArch_flg(String arch_flg) {
		this.arch_flg = arch_flg;
	}

	public T25Report(Date report_date) {
		this.report_date=report_date;
	}
	
	
	
	
	
	

	public String getEntity_flg() {
		return entity_flg;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public String getDel_flg() {
		return del_flg;
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

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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
	
	
	
	
	
	
	
	
	

	public T25Report(String d1a_ho, String d2a_bo, String d3a_nbdti, String p1b_ho_fully_applied,
			String p2b_bo_fully_applied, String p3b_nbdti_not_applicable, String c1b_ho_fully_applied,
			String c2b_bo_fully_applied, String c3b_nbdti_not_applicable, String report_code, String report_name,
			Date report_date, Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to,
			String rep_freq, String nil_report_flg, String arch_flg, String entity_flg, String modify_flg,
			String del_flg, String entry_user, String modify_user, String verify_user, Date entry_time,
			Date modify_time, Date verify_time) {
		super();
		this.d1a_ho = d1a_ho;
		this.d2a_bo = d2a_bo;
		this.d3a_nbdti = d3a_nbdti;
		this.p1b_ho_fully_applied = p1b_ho_fully_applied;
		this.p2b_bo_fully_applied = p2b_bo_fully_applied;
		this.p3b_nbdti_not_applicable = p3b_nbdti_not_applicable;
		this.c1b_ho_fully_applied = c1b_ho_fully_applied;
		this.c2b_bo_fully_applied = c2b_bo_fully_applied;
		this.c3b_nbdti_not_applicable = c3b_nbdti_not_applicable;
		this.report_code = report_code;
		this.report_name = report_name;
		this.report_date = report_date;
		this.report_due_date = report_due_date;
		this.rep_submit_date = rep_submit_date;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
		this.rep_freq = rep_freq;
		this.nil_report_flg = nil_report_flg;
		this.arch_flg = arch_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}

	public T25Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

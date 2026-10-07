package com.bornfire.entity.t28;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="T28_AML_CFT_INF")
public class T28Report {
	
	
	@Id
	private String	srl_no;
	private String	inf_reqd;
	private String	cur_qtr;
	private String	remarks_cur;
	private String	pre_qtr;
	private String	remarks_pre;
	private Character	entity_flg;
	private Character	del_flg;
	private Character	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	verify_time;
	private Date	report_date;
	private Date	rep_period_from;
	private Date	rep_period_to;
	private String srl_client;
	
	
	
	
	public String getInf_reqd() {
		return inf_reqd;
	}
	public String getCur_qtr() {
		return cur_qtr;
	}
	public String getRemarks_cur() {
		return remarks_cur;
	}
	public String getPre_qtr() {
		return pre_qtr;
	}
	public String getRemarks_pre() {
		return remarks_pre;
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
	public Date getRep_period_from() {
		return rep_period_from;
	}
	public Date getRep_period_to() {
		return rep_period_to;
	}
	
	public void setInf_reqd(String inf_reqd) {
		this.inf_reqd = inf_reqd;
	}
	public void setCur_qtr(String cur_qtr) {
		this.cur_qtr = cur_qtr;
	}
	public void setRemarks_cur(String remarks_cur) {
		this.remarks_cur = remarks_cur;
	}
	public void setPre_qtr(String pre_qtr) {
		this.pre_qtr = pre_qtr;
	}
	public void setRemarks_pre(String remarks_pre) {
		this.remarks_pre = remarks_pre;
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
	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}
	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}

	
	
	
	
	
	
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
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
	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}
	public void setModify_flg(Character modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getSrl_client() {
		return srl_client;
	}
	public void setSrl_client(String srl_client) {
		this.srl_client = srl_client;
	}
public T28Report(String srl_no, String inf_reqd, String cur_qtr, String remarks_cur, String pre_qtr,
			String remarks_pre, Character entity_flg, Character del_flg, Character modify_flg, String entry_user,
			String modify_user, String verify_user, Date entry_time, Date modify_time, Date verify_time,
			Date report_date, Date rep_period_from, Date rep_period_to, String srl_client) {
		super();
		this.srl_no = srl_no;
		this.inf_reqd = inf_reqd;
		this.cur_qtr = cur_qtr;
		this.remarks_cur = remarks_cur;
		this.pre_qtr = pre_qtr;
		this.remarks_pre = remarks_pre;
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
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
		this.srl_client = srl_client;
	}
	
	
	
	public T28Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


}
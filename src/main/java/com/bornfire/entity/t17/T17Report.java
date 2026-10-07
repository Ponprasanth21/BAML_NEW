package com.bornfire.entity.t17;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T17_TRAN_MON_TABLE")
public class T17Report {

	private String d1a_cash_dep_wdl;
	private String d2a_dom_inw_out_rem;
	private String d3a_chq_inw_out_tran;
	private String d4a_all_otr_tran;
	
	private String c1f_cash_dep_wdl_parl_post;
	private String c2f_dom_inw_out_rem_parl_post;
	private String c3f_chq_inw_out_tran_parl_post;
	private String c4f_all_otr_tran_parl_post;
	private String c1g_cash_dep_wdl_avg_time;
	private String c2g_dom_inw_out_rem_avg_time;
	private String c3g_chq_inw_out_tran_avg_time;
	private String c4g_all_otr_tran_avg_time;
	private String c1h_cash_dep_wdl_man_it;
	private String c2h_dom_inw_out_rem_man_it;
	private String c3h_chq_inw_out_tran_man_it;
	private String c4h_all_otr_tran_man_it;
	private String c1i_cash_dep_wdl_inh_outs;
	private String c2i_dom_inw_out_rem_inh_outs;
	private String c3i_chq_inw_out_tran_inh_outs;
	private String c4i_all_otr_tran_inh_outs;
	
  
	private String report_code;
	private String report_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	 @Id
	private Date report_date;
	private Date report_due_date;
	private Date rep_submit_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date rep_period_from;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date verify_date;

	public String getD1a_cash_dep_wdl() {
		return d1a_cash_dep_wdl;
	}

	public void setD1a_cash_dep_wdl(String d1a_cash_dep_wdl) {
		this.d1a_cash_dep_wdl = d1a_cash_dep_wdl;
	}

	public String getD2a_dom_inw_out_rem() {
		return d2a_dom_inw_out_rem;
	}

	public void setD2a_dom_inw_out_rem(String d2a_dom_inw_out_rem) {
		this.d2a_dom_inw_out_rem = d2a_dom_inw_out_rem;
	}

	public String getD3a_chq_inw_out_tran() {
		return d3a_chq_inw_out_tran;
	}

	public void setD3a_chq_inw_out_tran(String d3a_chq_inw_out_tran) {
		this.d3a_chq_inw_out_tran = d3a_chq_inw_out_tran;
	}

	public String getD4a_all_otr_tran() {
		return d4a_all_otr_tran;
	}

	public void setD4a_all_otr_tran(String d4a_all_otr_tran) {
		this.d4a_all_otr_tran = d4a_all_otr_tran;
	}

	public String getC1f_cash_dep_wdl_parl_post() {
		return c1f_cash_dep_wdl_parl_post;
	}

	public void setC1f_cash_dep_wdl_parl_post(String c1f_cash_dep_wdl_parl_post) {
		this.c1f_cash_dep_wdl_parl_post = c1f_cash_dep_wdl_parl_post;
	}

	public String getC2f_dom_inw_out_rem_parl_post() {
		return c2f_dom_inw_out_rem_parl_post;
	}

	public void setC2f_dom_inw_out_rem_parl_post(String c2f_dom_inw_out_rem_parl_post) {
		this.c2f_dom_inw_out_rem_parl_post = c2f_dom_inw_out_rem_parl_post;
	}

	public String getC3f_chq_inw_out_tran_parl_post() {
		return c3f_chq_inw_out_tran_parl_post;
	}

	public void setC3f_chq_inw_out_tran_parl_post(String c3f_chq_inw_out_tran_parl_post) {
		this.c3f_chq_inw_out_tran_parl_post = c3f_chq_inw_out_tran_parl_post;
	}

	public String getC4f_all_otr_tran_parl_post() {
		return c4f_all_otr_tran_parl_post;
	}

	public void setC4f_all_otr_tran_parl_post(String c4f_all_otr_tran_parl_post) {
		this.c4f_all_otr_tran_parl_post = c4f_all_otr_tran_parl_post;
	}

	public String getC1h_cash_dep_wdl_man_it() {
		return c1h_cash_dep_wdl_man_it;
	}

	public void setC1h_cash_dep_wdl_man_it(String c1h_cash_dep_wdl_man_it) {
		this.c1h_cash_dep_wdl_man_it = c1h_cash_dep_wdl_man_it;
	}

	public String getC2h_dom_inw_out_rem_man_it() {
		return c2h_dom_inw_out_rem_man_it;
	}

	public void setC2h_dom_inw_out_rem_man_it(String c2h_dom_inw_out_rem_man_it) {
		this.c2h_dom_inw_out_rem_man_it = c2h_dom_inw_out_rem_man_it;
	}

	public String getC3h_chq_inw_out_tran_man_it() {
		return c3h_chq_inw_out_tran_man_it;
	}

	public void setC3h_chq_inw_out_tran_man_it(String c3h_chq_inw_out_tran_man_it) {
		this.c3h_chq_inw_out_tran_man_it = c3h_chq_inw_out_tran_man_it;
	}

	public String getC4h_all_otr_tran_man_it() {
		return c4h_all_otr_tran_man_it;
	}

	public void setC4h_all_otr_tran_man_it(String c4h_all_otr_tran_man_it) {
		this.c4h_all_otr_tran_man_it = c4h_all_otr_tran_man_it;
	}

	public String getC1i_cash_dep_wdl_inh_outs() {
		return c1i_cash_dep_wdl_inh_outs;
	}

	public void setC1i_cash_dep_wdl_inh_outs(String c1i_cash_dep_wdl_inh_outs) {
		this.c1i_cash_dep_wdl_inh_outs = c1i_cash_dep_wdl_inh_outs;
	}

	public String getC2i_dom_inw_out_rem_inh_outs() {
		return c2i_dom_inw_out_rem_inh_outs;
	}

	public void setC2i_dom_inw_out_rem_inh_outs(String c2i_dom_inw_out_rem_inh_outs) {
		this.c2i_dom_inw_out_rem_inh_outs = c2i_dom_inw_out_rem_inh_outs;
	}

	public String getC3i_chq_inw_out_tran_inh_outs() {
		return c3i_chq_inw_out_tran_inh_outs;
	}

	public void setC3i_chq_inw_out_tran_inh_outs(String c3i_chq_inw_out_tran_inh_outs) {
		this.c3i_chq_inw_out_tran_inh_outs = c3i_chq_inw_out_tran_inh_outs;
	}

	public String getC4i_all_otr_tran_inh_outs() {
		return c4i_all_otr_tran_inh_outs;
	}

	public void setC4i_all_otr_tran_inh_outs(String c4i_all_otr_tran_inh_outs) {
		this.c4i_all_otr_tran_inh_outs = c4i_all_otr_tran_inh_outs;
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


	public String getC1g_cash_dep_wdl_avg_time() {
		return c1g_cash_dep_wdl_avg_time;
	}

	public String getC2g_dom_inw_out_rem_avg_time() {
		return c2g_dom_inw_out_rem_avg_time;
	}

	public String getC3g_chq_inw_out_tran_avg_time() {
		return c3g_chq_inw_out_tran_avg_time;
	}

	public String getC4g_all_otr_tran_avg_time() {
		return c4g_all_otr_tran_avg_time;
	}

	

	public void setC1g_cash_dep_wdl_avg_time(String c1g_cash_dep_wdl_avg_time) {
		this.c1g_cash_dep_wdl_avg_time = c1g_cash_dep_wdl_avg_time;
	}

	public void setC2g_dom_inw_out_rem_avg_time(String c2g_dom_inw_out_rem_avg_time) {
		this.c2g_dom_inw_out_rem_avg_time = c2g_dom_inw_out_rem_avg_time;
	}

	public void setC3g_chq_inw_out_tran_avg_time(String c3g_chq_inw_out_tran_avg_time) {
		this.c3g_chq_inw_out_tran_avg_time = c3g_chq_inw_out_tran_avg_time;
	}

	public void setC4g_all_otr_tran_avg_time(String c4g_all_otr_tran_avg_time) {
		this.c4g_all_otr_tran_avg_time = c4g_all_otr_tran_avg_time;
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

	public Date getEntry_date() {
		return entry_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public Date getVerify_date() {
		return verify_date;
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

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}

	

	public T17Report(String d1a_cash_dep_wdl, String d2a_dom_inw_out_rem, String d3a_chq_inw_out_tran,
			String d4a_all_otr_tran,String c1f_cash_dep_wdl_parl_post,
			String c2f_dom_inw_out_rem_parl_post, String c3f_chq_inw_out_tran_parl_post,
			String c4f_all_otr_tran_parl_post, String c1g_cash_dep_wdl_avg_time, String c2g_dom_inw_out_rem_avg_time,
			String c3g_chq_inw_out_tran_avg_time, String c4g_all_otr_tran_avg_time, String c1h_cash_dep_wdl_man_it,
			String c2h_dom_inw_out_rem_man_it, String c3h_chq_inw_out_tran_man_it, String c4h_all_otr_tran_man_it,
			String c1i_cash_dep_wdl_inh_outs, String c2i_dom_inw_out_rem_inh_outs, String c3i_chq_inw_out_tran_inh_outs,
			String c4i_all_otr_tran_inh_outs, String report_code, String report_name, Date report_date,
			Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq,
			String nil_report_flg, String arch_flg, String entity_flg, String modify_flg, String del_flg,
			String entry_user, String modify_user, String verify_user, Date entry_date, Date modify_date,
			Date verify_date) {
		super();
		this.d1a_cash_dep_wdl = d1a_cash_dep_wdl;
		this.d2a_dom_inw_out_rem = d2a_dom_inw_out_rem;
		this.d3a_chq_inw_out_tran = d3a_chq_inw_out_tran;
		this.d4a_all_otr_tran = d4a_all_otr_tran;
		
		this.c1f_cash_dep_wdl_parl_post = c1f_cash_dep_wdl_parl_post;
		this.c2f_dom_inw_out_rem_parl_post = c2f_dom_inw_out_rem_parl_post;
		this.c3f_chq_inw_out_tran_parl_post = c3f_chq_inw_out_tran_parl_post;
		this.c4f_all_otr_tran_parl_post = c4f_all_otr_tran_parl_post;
		this.c1g_cash_dep_wdl_avg_time = c1g_cash_dep_wdl_avg_time;
		this.c2g_dom_inw_out_rem_avg_time = c2g_dom_inw_out_rem_avg_time;
		this.c3g_chq_inw_out_tran_avg_time = c3g_chq_inw_out_tran_avg_time;
		this.c4g_all_otr_tran_avg_time = c4g_all_otr_tran_avg_time;
		this.c1h_cash_dep_wdl_man_it = c1h_cash_dep_wdl_man_it;
		this.c2h_dom_inw_out_rem_man_it = c2h_dom_inw_out_rem_man_it;
		this.c3h_chq_inw_out_tran_man_it = c3h_chq_inw_out_tran_man_it;
		this.c4h_all_otr_tran_man_it = c4h_all_otr_tran_man_it;
		this.c1i_cash_dep_wdl_inh_outs = c1i_cash_dep_wdl_inh_outs;
		this.c2i_dom_inw_out_rem_inh_outs = c2i_dom_inw_out_rem_inh_outs;
		this.c3i_chq_inw_out_tran_inh_outs = c3i_chq_inw_out_tran_inh_outs;
		this.c4i_all_otr_tran_inh_outs = c4i_all_otr_tran_inh_outs;
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
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
	}

	public T17Report() {
	}

	

}

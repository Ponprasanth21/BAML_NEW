package com.bornfire.entity.t23;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "T23_AML_CFT_REVIEWS_TABLE")
public class T23Report {
	
	private String	d1a_acct_opn_appl;
	private String	d2a_cust_rate;
	private String	d3a_large_cash_tran;
	private String	d4a_high_risk_client_tran;
	private String	d5a_high_risk_client_files;
	private String	d6a_med_risk_client_tran;
	private String	d7a_med_risk_client_files;
	private String	d8a_hits;
	private String	d9a_alerts_raised;
	private String	d10a_alerts_closed;
	private String	d11a_int_susp_rpts;
	private String	d12a_int_sups_rpts_closed;
	private String	d13a_accts_closed;
	private String	d14a_inact_accts_react;
	private String	d15a_nof_aband_accts;

	private BigDecimal	c1d_cur_acct_opn_appl;
	private BigDecimal	c2d_cur_cust_rate;
	private BigDecimal	c3d_cur_large_cash_tran;
	private BigDecimal	c4d_cur_high_risk_client_tran;
	private BigDecimal	c5d_cur_high_risk_client_files;
	private BigDecimal	c6d_cur_med_risk_client_tran;
	private BigDecimal	c7d_cur_med_risk_client_files;
	private BigDecimal	c8d_cur_hits;
	private BigDecimal	c9d_cur_alerts_raised;
	private BigDecimal	c10d_cur_alerts_closed;
	private BigDecimal	c11d_cur_int_susp_rpts;
	private BigDecimal	c12d_cur_int_sucp_rpts_closed;
	private BigDecimal	c13d_cur_accts_closed;
	private BigDecimal	c14d_cur_inact_accts_react;
	private BigDecimal	c15d_cur_nof_aband_accts;
	private BigDecimal	c1e_cur_gr_acct_opn_appl;
	private BigDecimal	c2e_cur_gr_cust_rate;
	private BigDecimal	c3e_cur_gr_large_cash_tran;
	private BigDecimal	c4e_cur_gr_high_risk_client_tran;
	private BigDecimal	c5e_cur_gr_high_risk_client_files;
	private BigDecimal	c6e_cur_gr_med_risk_client_tran;
	private BigDecimal	c7e_cur_gr_med_risk_client_files;
	private BigDecimal	c8e_cur_gr_hits;
	private BigDecimal	c9e_cur_gr_alerts_raised;
	private BigDecimal	c10e_cur_gr_alerts_closed;
	private BigDecimal	c11e_cur_gr_int_susp_rpts;
	private BigDecimal	c12e_cur_gr_int_susp_rpts_closed;
	private BigDecimal	c13e_cur_gr_accts_closed;
	private BigDecimal	c14e_cur_gr_inact_accts_react;
	private BigDecimal	c15e_cur_gr_nof_aband_accts;
	private String	report_code;
	private String	report_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Id
	private Date	report_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	report_due_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_submit_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_from;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_to;
	private String	rep_freq;
	private String	nil_report_flg;
	private String	arch_flg;
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
	private String	modify_flg;
	
	
	
	
	
	
	public String getD1a_acct_opn_appl() {
		return d1a_acct_opn_appl;
	}
	public String getD2a_cust_rate() {
		return d2a_cust_rate;
	}
	public String getD3a_large_cash_tran() {
		return d3a_large_cash_tran;
	}
	public String getD4a_high_risk_client_tran() {
		return d4a_high_risk_client_tran;
	}
	public String getD5a_high_risk_client_files() {
		return d5a_high_risk_client_files;
	}
	public String getD6a_med_risk_client_tran() {
		return d6a_med_risk_client_tran;
	}
	public String getD7a_med_risk_client_files() {
		return d7a_med_risk_client_files;
	}
	public String getD8a_hits() {
		return d8a_hits;
	}
	public String getD9a_alerts_raised() {
		return d9a_alerts_raised;
	}
	public String getD10a_alerts_closed() {
		return d10a_alerts_closed;
	}
	public String getD11a_int_susp_rpts() {
		return d11a_int_susp_rpts;
	}
	public String getD12a_int_sups_rpts_closed() {
		return d12a_int_sups_rpts_closed;
	}
	public String getD13a_accts_closed() {
		return d13a_accts_closed;
	}
	public String getD14a_inact_accts_react() {
		return d14a_inact_accts_react;
	}
	public String getD15a_nof_aband_accts() {
		return d15a_nof_aband_accts;
	}
	
	public BigDecimal getC1d_cur_acct_opn_appl() {
		return c1d_cur_acct_opn_appl;
	}
	public BigDecimal getC2d_cur_cust_rate() {
		return c2d_cur_cust_rate;
	}
	public BigDecimal getC3d_cur_large_cash_tran() {
		return c3d_cur_large_cash_tran;
	}
	public BigDecimal getC4d_cur_high_risk_client_tran() {
		return c4d_cur_high_risk_client_tran;
	}
	public BigDecimal getC5d_cur_high_risk_client_files() {
		return c5d_cur_high_risk_client_files;
	}
	public BigDecimal getC6d_cur_med_risk_client_tran() {
		return c6d_cur_med_risk_client_tran;
	}
	public BigDecimal getC7d_cur_med_risk_client_files() {
		return c7d_cur_med_risk_client_files;
	}
	public BigDecimal getC8d_cur_hits() {
		return c8d_cur_hits;
	}
	public BigDecimal getC9d_cur_alerts_raised() {
		return c9d_cur_alerts_raised;
	}
	public BigDecimal getC10d_cur_alerts_closed() {
		return c10d_cur_alerts_closed;
	}
	public BigDecimal getC11d_cur_int_susp_rpts() {
		return c11d_cur_int_susp_rpts;
	}
	public BigDecimal getC12d_cur_int_sucp_rpts_closed() {
		return c12d_cur_int_sucp_rpts_closed;
	}
	public BigDecimal getC13d_cur_accts_closed() {
		return c13d_cur_accts_closed;
	}
	public BigDecimal getC14d_cur_inact_accts_react() {
		return c14d_cur_inact_accts_react;
	}
	public BigDecimal getC15d_cur_nof_aband_accts() {
		return c15d_cur_nof_aband_accts;
	}
	public BigDecimal getC1e_cur_gr_acct_opn_appl() {
		return c1e_cur_gr_acct_opn_appl;
	}
	public BigDecimal getC2e_cur_gr_cust_rate() {
		return c2e_cur_gr_cust_rate;
	}
	public BigDecimal getC3e_cur_gr_large_cash_tran() {
		return c3e_cur_gr_large_cash_tran;
	}
	public BigDecimal getC4e_cur_gr_high_risk_client_tran() {
		return c4e_cur_gr_high_risk_client_tran;
	}
	public BigDecimal getC5e_cur_gr_high_risk_client_files() {
		return c5e_cur_gr_high_risk_client_files;
	}
	public BigDecimal getC6e_cur_gr_med_risk_client_tran() {
		return c6e_cur_gr_med_risk_client_tran;
	}
	public BigDecimal getC7e_cur_gr_med_risk_client_files() {
		return c7e_cur_gr_med_risk_client_files;
	}
	public BigDecimal getC8e_cur_gr_hits() {
		return c8e_cur_gr_hits;
	}
	public BigDecimal getC9e_cur_gr_alerts_raised() {
		return c9e_cur_gr_alerts_raised;
	}
	public BigDecimal getC10e_cur_gr_alerts_closed() {
		return c10e_cur_gr_alerts_closed;
	}
	public BigDecimal getC11e_cur_gr_int_susp_rpts() {
		return c11e_cur_gr_int_susp_rpts;
	}
	public BigDecimal getC12e_cur_gr_int_susp_rpts_closed() {
		return c12e_cur_gr_int_susp_rpts_closed;
	}
	public BigDecimal getC13e_cur_gr_accts_closed() {
		return c13e_cur_gr_accts_closed;
	}
	public BigDecimal getC14e_cur_gr_inact_accts_react() {
		return c14e_cur_gr_inact_accts_react;
	}
	public BigDecimal getC15e_cur_gr_nof_aband_accts() {
		return c15e_cur_gr_nof_aband_accts;
	}
	public String getReport_code() {
		return report_code;
	}
	public String getReport_name() {
		return report_name;
	}
	public Date getReport_date() {
		return report_date;
	}
	public Date getReport_due_date() {
		return report_due_date;
	}
	public Date getRep_submit_date() {
		return rep_submit_date;
	}
	public Date getRep_period_from() {
		return rep_period_from;
	}
	public Date getRep_period_to() {
		return rep_period_to;
	}
	public String getRep_freq() {
		return rep_freq;
	}
	public String getNil_report_flg() {
		return nil_report_flg;
	}
	public String getArch_flg() {
		return arch_flg;
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
	public String getModify_flg() {
		return modify_flg;
	}
	public void setD1a_acct_opn_appl(String d1a_acct_opn_appl) {
		this.d1a_acct_opn_appl = d1a_acct_opn_appl;
	}
	public void setD2a_cust_rate(String d2a_cust_rate) {
		this.d2a_cust_rate = d2a_cust_rate;
	}
	public void setD3a_large_cash_tran(String d3a_large_cash_tran) {
		this.d3a_large_cash_tran = d3a_large_cash_tran;
	}
	public void setD4a_high_risk_client_tran(String d4a_high_risk_client_tran) {
		this.d4a_high_risk_client_tran = d4a_high_risk_client_tran;
	}
	public void setD5a_high_risk_client_files(String d5a_high_risk_client_files) {
		this.d5a_high_risk_client_files = d5a_high_risk_client_files;
	}
	public void setD6a_med_risk_client_tran(String d6a_med_risk_client_tran) {
		this.d6a_med_risk_client_tran = d6a_med_risk_client_tran;
	}
	public void setD7a_med_risk_client_files(String d7a_med_risk_client_files) {
		this.d7a_med_risk_client_files = d7a_med_risk_client_files;
	}
	public void setD8a_hits(String d8a_hits) {
		this.d8a_hits = d8a_hits;
	}
	public void setD9a_alerts_raised(String d9a_alerts_raised) {
		this.d9a_alerts_raised = d9a_alerts_raised;
	}
	public void setD10a_alerts_closed(String d10a_alerts_closed) {
		this.d10a_alerts_closed = d10a_alerts_closed;
	}
	public void setD11a_int_susp_rpts(String d11a_int_susp_rpts) {
		this.d11a_int_susp_rpts = d11a_int_susp_rpts;
	}
	public void setD12a_int_sups_rpts_closed(String d12a_int_sups_rpts_closed) {
		this.d12a_int_sups_rpts_closed = d12a_int_sups_rpts_closed;
	}
	public void setD13a_accts_closed(String d13a_accts_closed) {
		this.d13a_accts_closed = d13a_accts_closed;
	}
	public void setD14a_inact_accts_react(String d14a_inact_accts_react) {
		this.d14a_inact_accts_react = d14a_inact_accts_react;
	}
	public void setD15a_nof_aband_accts(String d15a_nof_aband_accts) {
		this.d15a_nof_aband_accts = d15a_nof_aband_accts;
	}

	public void setC1d_cur_acct_opn_appl(BigDecimal c1d_cur_acct_opn_appl) {
		this.c1d_cur_acct_opn_appl = c1d_cur_acct_opn_appl;
	}
	public void setC2d_cur_cust_rate(BigDecimal c2d_cur_cust_rate) {
		this.c2d_cur_cust_rate = c2d_cur_cust_rate;
	}
	public void setC3d_cur_large_cash_tran(BigDecimal c3d_cur_large_cash_tran) {
		this.c3d_cur_large_cash_tran = c3d_cur_large_cash_tran;
	}
	public void setC4d_cur_high_risk_client_tran(BigDecimal c4d_cur_high_risk_client_tran) {
		this.c4d_cur_high_risk_client_tran = c4d_cur_high_risk_client_tran;
	}
	public void setC5d_cur_high_risk_client_files(BigDecimal c5d_cur_high_risk_client_files) {
		this.c5d_cur_high_risk_client_files = c5d_cur_high_risk_client_files;
	}
	public void setC6d_cur_med_risk_client_tran(BigDecimal c6d_cur_med_risk_client_tran) {
		this.c6d_cur_med_risk_client_tran = c6d_cur_med_risk_client_tran;
	}
	public void setC7d_cur_med_risk_client_files(BigDecimal c7d_cur_med_risk_client_files) {
		this.c7d_cur_med_risk_client_files = c7d_cur_med_risk_client_files;
	}
	public void setC8d_cur_hits(BigDecimal c8d_cur_hits) {
		this.c8d_cur_hits = c8d_cur_hits;
	}
	public void setC9d_cur_alerts_raised(BigDecimal c9d_cur_alerts_raised) {
		this.c9d_cur_alerts_raised = c9d_cur_alerts_raised;
	}
	public void setC10d_cur_alerts_closed(BigDecimal c10d_cur_alerts_closed) {
		this.c10d_cur_alerts_closed = c10d_cur_alerts_closed;
	}
	public void setC11d_cur_int_susp_rpts(BigDecimal c11d_cur_int_susp_rpts) {
		this.c11d_cur_int_susp_rpts = c11d_cur_int_susp_rpts;
	}
	public void setC12d_cur_int_sucp_rpts_closed(BigDecimal c12d_cur_int_sucp_rpts_closed) {
		this.c12d_cur_int_sucp_rpts_closed = c12d_cur_int_sucp_rpts_closed;
	}
	public void setC13d_cur_accts_closed(BigDecimal c13d_cur_accts_closed) {
		this.c13d_cur_accts_closed = c13d_cur_accts_closed;
	}
	public void setC14d_cur_inact_accts_react(BigDecimal c14d_cur_inact_accts_react) {
		this.c14d_cur_inact_accts_react = c14d_cur_inact_accts_react;
	}
	public void setC15d_cur_nof_aband_accts(BigDecimal c15d_cur_nof_aband_accts) {
		this.c15d_cur_nof_aband_accts = c15d_cur_nof_aband_accts;
	}
	public void setC1e_cur_gr_acct_opn_appl(BigDecimal c1e_cur_gr_acct_opn_appl) {
		this.c1e_cur_gr_acct_opn_appl = c1e_cur_gr_acct_opn_appl;
	}
	public void setC2e_cur_gr_cust_rate(BigDecimal c2e_cur_gr_cust_rate) {
		this.c2e_cur_gr_cust_rate = c2e_cur_gr_cust_rate;
	}
	public void setC3e_cur_gr_large_cash_tran(BigDecimal c3e_cur_gr_large_cash_tran) {
		this.c3e_cur_gr_large_cash_tran = c3e_cur_gr_large_cash_tran;
	}
	public void setC4e_cur_gr_high_risk_client_tran(BigDecimal c4e_cur_gr_high_risk_client_tran) {
		this.c4e_cur_gr_high_risk_client_tran = c4e_cur_gr_high_risk_client_tran;
	}
	public void setC5e_cur_gr_high_risk_client_files(BigDecimal c5e_cur_gr_high_risk_client_files) {
		this.c5e_cur_gr_high_risk_client_files = c5e_cur_gr_high_risk_client_files;
	}
	public void setC6e_cur_gr_med_risk_client_tran(BigDecimal c6e_cur_gr_med_risk_client_tran) {
		this.c6e_cur_gr_med_risk_client_tran = c6e_cur_gr_med_risk_client_tran;
	}
	public void setC7e_cur_gr_med_risk_client_files(BigDecimal c7e_cur_gr_med_risk_client_files) {
		this.c7e_cur_gr_med_risk_client_files = c7e_cur_gr_med_risk_client_files;
	}
	public void setC8e_cur_gr_hits(BigDecimal c8e_cur_gr_hits) {
		this.c8e_cur_gr_hits = c8e_cur_gr_hits;
	}
	public void setC9e_cur_gr_alerts_raised(BigDecimal c9e_cur_gr_alerts_raised) {
		this.c9e_cur_gr_alerts_raised = c9e_cur_gr_alerts_raised;
	}
	public void setC10e_cur_gr_alerts_closed(BigDecimal c10e_cur_gr_alerts_closed) {
		this.c10e_cur_gr_alerts_closed = c10e_cur_gr_alerts_closed;
	}
	public void setC11e_cur_gr_int_susp_rpts(BigDecimal c11e_cur_gr_int_susp_rpts) {
		this.c11e_cur_gr_int_susp_rpts = c11e_cur_gr_int_susp_rpts;
	}
	public void setC12e_cur_gr_int_susp_rpts_closed(BigDecimal c12e_cur_gr_int_susp_rpts_closed) {
		this.c12e_cur_gr_int_susp_rpts_closed = c12e_cur_gr_int_susp_rpts_closed;
	}
	public void setC13e_cur_gr_accts_closed(BigDecimal c13e_cur_gr_accts_closed) {
		this.c13e_cur_gr_accts_closed = c13e_cur_gr_accts_closed;
	}
	public void setC14e_cur_gr_inact_accts_react(BigDecimal c14e_cur_gr_inact_accts_react) {
		this.c14e_cur_gr_inact_accts_react = c14e_cur_gr_inact_accts_react;
	}
	public void setC15e_cur_gr_nof_aband_accts(BigDecimal c15e_cur_gr_nof_aband_accts) {
		this.c15e_cur_gr_nof_aband_accts = c15e_cur_gr_nof_aband_accts;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public void setReport_due_date(Date report_due_date) {
		this.report_due_date = report_due_date;
	}
	public void setRep_submit_date(Date rep_submit_date) {
		this.rep_submit_date = rep_submit_date;
	}
	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}
	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}
	public void setRep_freq(String rep_freq) {
		this.rep_freq = rep_freq;
	}
	public void setNil_report_flg(String nil_report_flg) {
		this.nil_report_flg = nil_report_flg;
	}
	public void setArch_flg(String arch_flg) {
		this.arch_flg = arch_flg;
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
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public T23Report(String d1a_acct_opn_appl, String d2a_cust_rate, String d3a_large_cash_tran,
			String d4a_high_risk_client_tran, String d5a_high_risk_client_files, String d6a_med_risk_client_tran,
			String d7a_med_risk_client_files, String d8a_hits, String d9a_alerts_raised, String d10a_alerts_closed,
			String d11a_int_susp_rpts, String d12a_int_sups_rpts_closed, String d13a_accts_closed,
			String d14a_inact_accts_react, String d15a_nof_aband_accts, BigDecimal p1b_pre_acct_opn_appl,
			BigDecimal p2b_pre_cust_rate, BigDecimal p3b_pre_large_cash_tran, BigDecimal p4b_pre_high_risk_client_tran,
			BigDecimal p5b_pre_high_risk_client_files, BigDecimal p6b_pre_med_risk_client_tran,
			BigDecimal p7b_pre_med_risk_client_files, BigDecimal p8b_pre_hits, BigDecimal p9b_pre_alerts_raised,
			BigDecimal p10b_pre_alerts_closed, BigDecimal p11b_pre_int_susp_rpts,
			BigDecimal p12b_pre_int_sups_rpts_closed, BigDecimal p13b_pre_accts_closed,
			BigDecimal p14b_pre_inact_accts_react, BigDecimal p15b_pre_nof_aband_accts,
			BigDecimal p1c_pre_gr_acct_opn_appl, BigDecimal p2c_pre_gr_cust_rate, BigDecimal p3c_pre_gr_large_cash_tran,
			BigDecimal p4c_pre_gr_high_risk_client_tran, BigDecimal p5c_pre_gr_high_risk_client_files,
			BigDecimal p6c_pre_gr_med_risk_client_tran, BigDecimal p7c_pre_gr_med_risk_client_files,
			BigDecimal p8c_pre_gr_hits, BigDecimal p9c_pre_gr_alerts_raised, BigDecimal p10c_pre_gr_alerts_closed,
			BigDecimal p11c_pre_gr_int_susp_rpts, BigDecimal p12c_pre_gr_int_sups_rpts_closed,
			BigDecimal p13c_pre_gr_accts_closed, BigDecimal p14c_pre_gr_inact_accts_react,
			BigDecimal p15c_pre_gr_nof_aband_accts, BigDecimal c1d_cur_acct_opn_appl, BigDecimal c2d_cur_cust_rate,
			BigDecimal c3d_cur_large_cash_tran, BigDecimal c4d_cur_high_risk_client_tran,
			BigDecimal c5d_cur_high_risk_client_files, BigDecimal c6d_cur_med_risk_client_tran,
			BigDecimal c7d_cur_med_risk_client_files, BigDecimal c8d_cur_hits, BigDecimal c9d_cur_alerts_raised,
			BigDecimal c10d_cur_alerts_closed, BigDecimal c11d_cur_int_susp_rpts,
			BigDecimal c12d_cur_int_sucp_rpts_closed, BigDecimal c13d_cur_accts_closed,
			BigDecimal c14d_cur_inact_accts_react, BigDecimal c15d_cur_nof_aband_accts,
			BigDecimal c1e_cur_gr_acct_opn_appl, BigDecimal c2e_cur_gr_cust_rate, BigDecimal c3e_cur_gr_large_cash_tran,
			BigDecimal c4e_cur_gr_high_risk_client_tran, BigDecimal c5e_cur_gr_high_risk_client_files,
			BigDecimal c6e_cur_gr_med_risk_client_tran, BigDecimal c7e_cur_gr_med_risk_client_files,
			BigDecimal c8e_cur_gr_hits, BigDecimal c9e_cur_gr_alerts_raised, BigDecimal c10e_cur_gr_alerts_closed,
			BigDecimal c11e_cur_gr_int_susp_rpts, BigDecimal c12e_cur_gr_int_susp_rpts_closed,
			BigDecimal c13e_cur_gr_accts_closed, BigDecimal c14e_cur_gr_inact_accts_react,
			BigDecimal c15e_cur_gr_nof_aband_accts, String report_code, String report_name, Date report_date,
			Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq,
			String nil_report_flg, String arch_flg, String entry_user, String modify_user, String verify_user,
			Date entry_time, Date modify_time, Date verify_time, String modify_flg) {
		super();
		this.d1a_acct_opn_appl = d1a_acct_opn_appl;
		this.d2a_cust_rate = d2a_cust_rate;
		this.d3a_large_cash_tran = d3a_large_cash_tran;
		this.d4a_high_risk_client_tran = d4a_high_risk_client_tran;
		this.d5a_high_risk_client_files = d5a_high_risk_client_files;
		this.d6a_med_risk_client_tran = d6a_med_risk_client_tran;
		this.d7a_med_risk_client_files = d7a_med_risk_client_files;
		this.d8a_hits = d8a_hits;
		this.d9a_alerts_raised = d9a_alerts_raised;
		this.d10a_alerts_closed = d10a_alerts_closed;
		this.d11a_int_susp_rpts = d11a_int_susp_rpts;
		this.d12a_int_sups_rpts_closed = d12a_int_sups_rpts_closed;
		this.d13a_accts_closed = d13a_accts_closed;
		this.d14a_inact_accts_react = d14a_inact_accts_react;
		this.d15a_nof_aband_accts = d15a_nof_aband_accts;
		this.c1d_cur_acct_opn_appl = c1d_cur_acct_opn_appl;
		this.c2d_cur_cust_rate = c2d_cur_cust_rate;
		this.c3d_cur_large_cash_tran = c3d_cur_large_cash_tran;
		this.c4d_cur_high_risk_client_tran = c4d_cur_high_risk_client_tran;
		this.c5d_cur_high_risk_client_files = c5d_cur_high_risk_client_files;
		this.c6d_cur_med_risk_client_tran = c6d_cur_med_risk_client_tran;
		this.c7d_cur_med_risk_client_files = c7d_cur_med_risk_client_files;
		this.c8d_cur_hits = c8d_cur_hits;
		this.c9d_cur_alerts_raised = c9d_cur_alerts_raised;
		this.c10d_cur_alerts_closed = c10d_cur_alerts_closed;
		this.c11d_cur_int_susp_rpts = c11d_cur_int_susp_rpts;
		this.c12d_cur_int_sucp_rpts_closed = c12d_cur_int_sucp_rpts_closed;
		this.c13d_cur_accts_closed = c13d_cur_accts_closed;
		this.c14d_cur_inact_accts_react = c14d_cur_inact_accts_react;
		this.c15d_cur_nof_aband_accts = c15d_cur_nof_aband_accts;
		this.c1e_cur_gr_acct_opn_appl = c1e_cur_gr_acct_opn_appl;
		this.c2e_cur_gr_cust_rate = c2e_cur_gr_cust_rate;
		this.c3e_cur_gr_large_cash_tran = c3e_cur_gr_large_cash_tran;
		this.c4e_cur_gr_high_risk_client_tran = c4e_cur_gr_high_risk_client_tran;
		this.c5e_cur_gr_high_risk_client_files = c5e_cur_gr_high_risk_client_files;
		this.c6e_cur_gr_med_risk_client_tran = c6e_cur_gr_med_risk_client_tran;
		this.c7e_cur_gr_med_risk_client_files = c7e_cur_gr_med_risk_client_files;
		this.c8e_cur_gr_hits = c8e_cur_gr_hits;
		this.c9e_cur_gr_alerts_raised = c9e_cur_gr_alerts_raised;
		this.c10e_cur_gr_alerts_closed = c10e_cur_gr_alerts_closed;
		this.c11e_cur_gr_int_susp_rpts = c11e_cur_gr_int_susp_rpts;
		this.c12e_cur_gr_int_susp_rpts_closed = c12e_cur_gr_int_susp_rpts_closed;
		this.c13e_cur_gr_accts_closed = c13e_cur_gr_accts_closed;
		this.c14e_cur_gr_inact_accts_react = c14e_cur_gr_inact_accts_react;
		this.c15e_cur_gr_nof_aband_accts = c15e_cur_gr_nof_aband_accts;
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
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.modify_flg = modify_flg;
	}
	public T23Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
   

}

package com.bornfire.entity.t11;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T11_CDD_TRAN_TERM_TABLE")
public class T11Reports {

	private String d4a_cur_cdd_tran_term;
	private String d5a_cur_amlcft_tran_term;
	private String d6a_cur_total_tran;

	private BigDecimal c4b_cur_cdd_tran_term_not_low;
	private BigDecimal c5b_cur_amlcft_tran_term_not_low;
	private BigDecimal c6b_cur_total_not_low;

	private BigDecimal c4c_cur_cdd_tran_term_tamt_low;
	private BigDecimal c5c_cur_amlcft_tran_term_tamt_low;
	private BigDecimal c6e_cur_total_tamt_low;

	private BigDecimal c4d_cur_cdd_tran_term_not_med;
	private BigDecimal c5d_cur_amlcft_tran_term_not_med;
	private BigDecimal c6d_cur_total_not_med;

	private BigDecimal c4e_cur_cdd_tran_term_tamt_med;
	private BigDecimal c5e_cur_amlcft_tran_term_tamt_med;
	private BigDecimal c6e_cur_total_tamt_med;

	private BigDecimal c4f_cur_cdd_tran_term_not_hig;
	private BigDecimal c5f_cur_amlcft_tran_term_not_hig;
	private BigDecimal c6f_cur_total_not_hig;

	private BigDecimal c4g_cur_cdd_tran_term_tamt_hig;
	private BigDecimal c5g_cur_amlcft_tran_term_tamt_hig;
	private BigDecimal c6g_cur_total_tamt_hig;

	private String report_code;
	private String report_name;
	@Id
	private Date report_date;
	private Date report_due_date;
	private Date rep_submit_date;
	private Date rep_period_from;
	private Date rep_period_to;
	private String rep_freq;
	private Character nil_report_flg;
	private Character arch_flg;

	

	public String getD4a_cur_cdd_tran_term() {
		return d4a_cur_cdd_tran_term;
	}

	public String getD5a_cur_amlcft_tran_term() {
		return d5a_cur_amlcft_tran_term;
	}

	public String getD6a_cur_total_tran() {
		return d6a_cur_total_tran;
	}

	public BigDecimal getC4b_cur_cdd_tran_term_not_low() {
		return c4b_cur_cdd_tran_term_not_low;
	}

	public BigDecimal getC5b_cur_amlcft_tran_term_not_low() {
		return c5b_cur_amlcft_tran_term_not_low;
	}

	public BigDecimal getC6b_cur_total_not_low() {
		return c6b_cur_total_not_low;
	}

	public BigDecimal getC4c_cur_cdd_tran_term_tamt_low() {
		return c4c_cur_cdd_tran_term_tamt_low;
	}

	public BigDecimal getC5c_cur_amlcft_tran_term_tamt_low() {
		return c5c_cur_amlcft_tran_term_tamt_low;
	}

	public BigDecimal getC6e_cur_total_tamt_low() {
		return c6e_cur_total_tamt_low;
	}

	public BigDecimal getC4d_cur_cdd_tran_term_not_med() {
		return c4d_cur_cdd_tran_term_not_med;
	}

	public BigDecimal getC5d_cur_amlcft_tran_term_not_med() {
		return c5d_cur_amlcft_tran_term_not_med;
	}

	public BigDecimal getC6d_cur_total_not_med() {
		return c6d_cur_total_not_med;
	}

	public BigDecimal getC4e_cur_cdd_tran_term_tamt_med() {
		return c4e_cur_cdd_tran_term_tamt_med;
	}

	public BigDecimal getC5e_cur_amlcft_tran_term_tamt_med() {
		return c5e_cur_amlcft_tran_term_tamt_med;
	}

	public BigDecimal getC6e_cur_total_tamt_med() {
		return c6e_cur_total_tamt_med;
	}

	public BigDecimal getC4f_cur_cdd_tran_term_not_hig() {
		return c4f_cur_cdd_tran_term_not_hig;
	}

	public BigDecimal getC5f_cur_amlcft_tran_term_not_hig() {
		return c5f_cur_amlcft_tran_term_not_hig;
	}

	public BigDecimal getC6f_cur_total_not_hig() {
		return c6f_cur_total_not_hig;
	}

	public BigDecimal getC4g_cur_cdd_tran_term_tamt_hig() {
		return c4g_cur_cdd_tran_term_tamt_hig;
	}

	public BigDecimal getC5g_cur_amlcft_tran_term_tamt_hig() {
		return c5g_cur_amlcft_tran_term_tamt_hig;
	}

	public BigDecimal getC6g_cur_total_tamt_hig() {
		return c6g_cur_total_tamt_hig;
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

	public Character getNil_report_flg() {
		return nil_report_flg;
	}

	public Character getArch_flg() {
		return arch_flg;
	}

	

	public void setD4a_cur_cdd_tran_term(String d4a_cur_cdd_tran_term) {
		this.d4a_cur_cdd_tran_term = d4a_cur_cdd_tran_term;
	}

	public void setD5a_cur_amlcft_tran_term(String d5a_cur_amlcft_tran_term) {
		this.d5a_cur_amlcft_tran_term = d5a_cur_amlcft_tran_term;
	}

	public void setD6a_cur_total_tran(String d6a_cur_total_tran) {
		this.d6a_cur_total_tran = d6a_cur_total_tran;
	}

	public void setC4b_cur_cdd_tran_term_not_low(BigDecimal c4b_cur_cdd_tran_term_not_low) {
		this.c4b_cur_cdd_tran_term_not_low = c4b_cur_cdd_tran_term_not_low;
	}

	public void setC5b_cur_amlcft_tran_term_not_low(BigDecimal c5b_cur_amlcft_tran_term_not_low) {
		this.c5b_cur_amlcft_tran_term_not_low = c5b_cur_amlcft_tran_term_not_low;
	}

	public void setC6b_cur_total_not_low(BigDecimal c6b_cur_total_not_low) {
		this.c6b_cur_total_not_low = c6b_cur_total_not_low;
	}

	public void setC4c_cur_cdd_tran_term_tamt_low(BigDecimal c4c_cur_cdd_tran_term_tamt_low) {
		this.c4c_cur_cdd_tran_term_tamt_low = c4c_cur_cdd_tran_term_tamt_low;
	}

	public void setC5c_cur_amlcft_tran_term_tamt_low(BigDecimal c5c_cur_amlcft_tran_term_tamt_low) {
		this.c5c_cur_amlcft_tran_term_tamt_low = c5c_cur_amlcft_tran_term_tamt_low;
	}

	public void setC6e_cur_total_tamt_low(BigDecimal c6e_cur_total_tamt_low) {
		this.c6e_cur_total_tamt_low = c6e_cur_total_tamt_low;
	}

	public void setC4d_cur_cdd_tran_term_not_med(BigDecimal c4d_cur_cdd_tran_term_not_med) {
		this.c4d_cur_cdd_tran_term_not_med = c4d_cur_cdd_tran_term_not_med;
	}

	public void setC5d_cur_amlcft_tran_term_not_med(BigDecimal c5d_cur_amlcft_tran_term_not_med) {
		this.c5d_cur_amlcft_tran_term_not_med = c5d_cur_amlcft_tran_term_not_med;
	}

	public void setC6d_cur_total_not_med(BigDecimal c6d_cur_total_not_med) {
		this.c6d_cur_total_not_med = c6d_cur_total_not_med;
	}

	public void setC4e_cur_cdd_tran_term_tamt_med(BigDecimal c4e_cur_cdd_tran_term_tamt_med) {
		this.c4e_cur_cdd_tran_term_tamt_med = c4e_cur_cdd_tran_term_tamt_med;
	}

	public void setC5e_cur_amlcft_tran_term_tamt_med(BigDecimal c5e_cur_amlcft_tran_term_tamt_med) {
		this.c5e_cur_amlcft_tran_term_tamt_med = c5e_cur_amlcft_tran_term_tamt_med;
	}

	public void setC6e_cur_total_tamt_med(BigDecimal c6e_cur_total_tamt_med) {
		this.c6e_cur_total_tamt_med = c6e_cur_total_tamt_med;
	}

	public void setC4f_cur_cdd_tran_term_not_hig(BigDecimal c4f_cur_cdd_tran_term_not_hig) {
		this.c4f_cur_cdd_tran_term_not_hig = c4f_cur_cdd_tran_term_not_hig;
	}

	public void setC5f_cur_amlcft_tran_term_not_hig(BigDecimal c5f_cur_amlcft_tran_term_not_hig) {
		this.c5f_cur_amlcft_tran_term_not_hig = c5f_cur_amlcft_tran_term_not_hig;
	}

	public void setC6f_cur_total_not_hig(BigDecimal c6f_cur_total_not_hig) {
		this.c6f_cur_total_not_hig = c6f_cur_total_not_hig;
	}

	public void setC4g_cur_cdd_tran_term_tamt_hig(BigDecimal c4g_cur_cdd_tran_term_tamt_hig) {
		this.c4g_cur_cdd_tran_term_tamt_hig = c4g_cur_cdd_tran_term_tamt_hig;
	}

	public void setC5g_cur_amlcft_tran_term_tamt_hig(BigDecimal c5g_cur_amlcft_tran_term_tamt_hig) {
		this.c5g_cur_amlcft_tran_term_tamt_hig = c5g_cur_amlcft_tran_term_tamt_hig;
	}

	public void setC6g_cur_total_tamt_hig(BigDecimal c6g_cur_total_tamt_hig) {
		this.c6g_cur_total_tamt_hig = c6g_cur_total_tamt_hig;
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

	public void setNil_report_flg(Character nil_report_flg) {
		this.nil_report_flg = nil_report_flg;
	}

	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}

	public T11Reports() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T11Reports(String d4a_cur_cdd_tran_term, String d5a_cur_amlcft_tran_term, String d6a_cur_total_tran,
			BigDecimal c4b_cur_cdd_tran_term_not_low, BigDecimal c5b_cur_amlcft_tran_term_not_low,
			BigDecimal c6b_cur_total_not_low, BigDecimal c4c_cur_cdd_tran_term_tamt_low,
			BigDecimal c5c_cur_amlcft_tran_term_tamt_low, BigDecimal c6e_cur_total_tamt_low,
			BigDecimal c4d_cur_cdd_tran_term_not_med, BigDecimal c5d_cur_amlcft_tran_term_not_med,
			BigDecimal c6d_cur_total_not_med, BigDecimal c4e_cur_cdd_tran_term_tamt_med,
			BigDecimal c5e_cur_amlcft_tran_term_tamt_med, BigDecimal c6e_cur_total_tamt_med,
			BigDecimal c4f_cur_cdd_tran_term_not_hig, BigDecimal c5f_cur_amlcft_tran_term_not_hig,
			BigDecimal c6f_cur_total_not_hig, BigDecimal c4g_cur_cdd_tran_term_tamt_hig,
			BigDecimal c5g_cur_amlcft_tran_term_tamt_hig, BigDecimal c6g_cur_total_tamt_hig, String report_code,
			String report_name, Date report_date, Date report_due_date, Date rep_submit_date, Date rep_period_from,
			Date rep_period_to, String rep_freq, Character nil_report_flg, Character arch_flg) {
		super();

		
		this.d4a_cur_cdd_tran_term = d4a_cur_cdd_tran_term;
		this.d5a_cur_amlcft_tran_term = d5a_cur_amlcft_tran_term;
		this.d6a_cur_total_tran = d6a_cur_total_tran;
		this.c4b_cur_cdd_tran_term_not_low = c4b_cur_cdd_tran_term_not_low;
		this.c5b_cur_amlcft_tran_term_not_low = c5b_cur_amlcft_tran_term_not_low;
		this.c6b_cur_total_not_low = c6b_cur_total_not_low;
		this.c4c_cur_cdd_tran_term_tamt_low = c4c_cur_cdd_tran_term_tamt_low;
		this.c5c_cur_amlcft_tran_term_tamt_low = c5c_cur_amlcft_tran_term_tamt_low;
		this.c6e_cur_total_tamt_low = c6e_cur_total_tamt_low;
		this.c4d_cur_cdd_tran_term_not_med = c4d_cur_cdd_tran_term_not_med;
		this.c5d_cur_amlcft_tran_term_not_med = c5d_cur_amlcft_tran_term_not_med;
		this.c6d_cur_total_not_med = c6d_cur_total_not_med;
		this.c4e_cur_cdd_tran_term_tamt_med = c4e_cur_cdd_tran_term_tamt_med;
		this.c5e_cur_amlcft_tran_term_tamt_med = c5e_cur_amlcft_tran_term_tamt_med;
		this.c6e_cur_total_tamt_med = c6e_cur_total_tamt_med;
		this.c4f_cur_cdd_tran_term_not_hig = c4f_cur_cdd_tran_term_not_hig;
		this.c5f_cur_amlcft_tran_term_not_hig = c5f_cur_amlcft_tran_term_not_hig;
		this.c6f_cur_total_not_hig = c6f_cur_total_not_hig;
		this.c4g_cur_cdd_tran_term_tamt_hig = c4g_cur_cdd_tran_term_tamt_hig;
		this.c5g_cur_amlcft_tran_term_tamt_hig = c5g_cur_amlcft_tran_term_tamt_hig;
		this.c6g_cur_total_tamt_hig = c6g_cur_total_tamt_hig;
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
	}

}
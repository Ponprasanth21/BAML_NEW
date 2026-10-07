package com.bornfire.entity.t27;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T27P_TRAN_NRE_TABLE")
public class T27PReport {
	@Id
	private String	a_counttry;
	private BigDecimal	b_nof_cust_cntry_incorp;
	private BigDecimal	c_nof_cust_cntry_oper;
	private BigDecimal	d_nof_inward_trans_low;
	private BigDecimal	e_value_inward_trans_low;
	private BigDecimal	f_nof_outward_trans_low;
	private BigDecimal	g_value_outward_trans_low;
	private BigDecimal	h_nof_inward_trans_med;
	private BigDecimal	i_value_inward_trans_med;
	private BigDecimal	j_nof_outward_trans_med;
	private BigDecimal	k_value_outward_trans_med;
	private BigDecimal	l_nof_inward_trans_high;
	private BigDecimal	m_value_inward_trans_high;
	private BigDecimal	n_nof_outward_trans_high;
	private BigDecimal	o_value_outward_trans_high;
	private String	p_report_crncy;
	private Date	report_date;
	private Date	report_due_date;
	private Date	rep_submit_date;
	private Date	rep_period_from;
	private Date	rep_period_to;
	private String	rep_freq;
	private String	nil_report_flg;
	public String getA_counttry() {
		return a_counttry;
	}
	public void setA_counttry(String a_counttry) {
		this.a_counttry = a_counttry;
	}
	public BigDecimal getB_nof_cust_cntry_incorp() {
		return b_nof_cust_cntry_incorp;
	}
	public void setB_nof_cust_cntry_incorp(BigDecimal b_nof_cust_cntry_incorp) {
		this.b_nof_cust_cntry_incorp = b_nof_cust_cntry_incorp;
	}
	public BigDecimal getC_nof_cust_cntry_oper() {
		return c_nof_cust_cntry_oper;
	}
	public void setC_nof_cust_cntry_oper(BigDecimal c_nof_cust_cntry_oper) {
		this.c_nof_cust_cntry_oper = c_nof_cust_cntry_oper;
	}
	public BigDecimal getD_nof_inward_trans_low() {
		return d_nof_inward_trans_low;
	}
	public void setD_nof_inward_trans_low(BigDecimal d_nof_inward_trans_low) {
		this.d_nof_inward_trans_low = d_nof_inward_trans_low;
	}
	public BigDecimal getE_value_inward_trans_low() {
		return e_value_inward_trans_low;
	}
	public void setE_value_inward_trans_low(BigDecimal e_value_inward_trans_low) {
		this.e_value_inward_trans_low = e_value_inward_trans_low;
	}
	public BigDecimal getF_nof_outward_trans_low() {
		return f_nof_outward_trans_low;
	}
	public void setF_nof_outward_trans_low(BigDecimal f_nof_outward_trans_low) {
		this.f_nof_outward_trans_low = f_nof_outward_trans_low;
	}
	public BigDecimal getG_value_outward_trans_low() {
		return g_value_outward_trans_low;
	}
	public void setG_value_outward_trans_low(BigDecimal g_value_outward_trans_low) {
		this.g_value_outward_trans_low = g_value_outward_trans_low;
	}
	public BigDecimal getH_nof_inward_trans_med() {
		return h_nof_inward_trans_med;
	}
	public void setH_nof_inward_trans_med(BigDecimal h_nof_inward_trans_med) {
		this.h_nof_inward_trans_med = h_nof_inward_trans_med;
	}
	public BigDecimal getI_value_inward_trans_med() {
		return i_value_inward_trans_med;
	}
	public void setI_value_inward_trans_med(BigDecimal i_value_inward_trans_med) {
		this.i_value_inward_trans_med = i_value_inward_trans_med;
	}
	public BigDecimal getJ_nof_outward_trans_med() {
		return j_nof_outward_trans_med;
	}
	public void setJ_nof_outward_trans_med(BigDecimal j_nof_outward_trans_med) {
		this.j_nof_outward_trans_med = j_nof_outward_trans_med;
	}
	public BigDecimal getK_value_outward_trans_med() {
		return k_value_outward_trans_med;
	}
	public void setK_value_outward_trans_med(BigDecimal k_value_outward_trans_med) {
		this.k_value_outward_trans_med = k_value_outward_trans_med;
	}
	public BigDecimal getL_nof_inward_trans_high() {
		return l_nof_inward_trans_high;
	}
	public void setL_nof_inward_trans_high(BigDecimal l_nof_inward_trans_high) {
		this.l_nof_inward_trans_high = l_nof_inward_trans_high;
	}
	public BigDecimal getM_value_inward_trans_high() {
		return m_value_inward_trans_high;
	}
	public void setM_value_inward_trans_high(BigDecimal m_value_inward_trans_high) {
		this.m_value_inward_trans_high = m_value_inward_trans_high;
	}
	public BigDecimal getN_nof_outward_trans_high() {
		return n_nof_outward_trans_high;
	}
	public void setN_nof_outward_trans_high(BigDecimal n_nof_outward_trans_high) {
		this.n_nof_outward_trans_high = n_nof_outward_trans_high;
	}
	public BigDecimal getO_value_outward_trans_high() {
		return o_value_outward_trans_high;
	}
	public void setO_value_outward_trans_high(BigDecimal o_value_outward_trans_high) {
		this.o_value_outward_trans_high = o_value_outward_trans_high;
	}
	public String getP_report_crncy() {
		return p_report_crncy;
	}
	public void setP_report_crncy(String p_report_crncy) {
		this.p_report_crncy = p_report_crncy;
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
	public T27PReport(String a_counttry, BigDecimal b_nof_cust_cntry_incorp, BigDecimal c_nof_cust_cntry_oper,
			BigDecimal d_nof_inward_trans_low, BigDecimal e_value_inward_trans_low, BigDecimal f_nof_outward_trans_low,
			BigDecimal g_value_outward_trans_low, BigDecimal h_nof_inward_trans_med,
			BigDecimal i_value_inward_trans_med, BigDecimal j_nof_outward_trans_med,
			BigDecimal k_value_outward_trans_med, BigDecimal l_nof_inward_trans_high,
			BigDecimal m_value_inward_trans_high, BigDecimal n_nof_outward_trans_high,
			BigDecimal o_value_outward_trans_high, String p_report_crncy, Date report_date, Date report_due_date,
			Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq, String nil_report_flg) {
		super();
		this.a_counttry = a_counttry;
		this.b_nof_cust_cntry_incorp = b_nof_cust_cntry_incorp;
		this.c_nof_cust_cntry_oper = c_nof_cust_cntry_oper;
		this.d_nof_inward_trans_low = d_nof_inward_trans_low;
		this.e_value_inward_trans_low = e_value_inward_trans_low;
		this.f_nof_outward_trans_low = f_nof_outward_trans_low;
		this.g_value_outward_trans_low = g_value_outward_trans_low;
		this.h_nof_inward_trans_med = h_nof_inward_trans_med;
		this.i_value_inward_trans_med = i_value_inward_trans_med;
		this.j_nof_outward_trans_med = j_nof_outward_trans_med;
		this.k_value_outward_trans_med = k_value_outward_trans_med;
		this.l_nof_inward_trans_high = l_nof_inward_trans_high;
		this.m_value_inward_trans_high = m_value_inward_trans_high;
		this.n_nof_outward_trans_high = n_nof_outward_trans_high;
		this.o_value_outward_trans_high = o_value_outward_trans_high;
		this.p_report_crncy = p_report_crncy;
		this.report_date = report_date;
		this.report_due_date = report_due_date;
		this.rep_submit_date = rep_submit_date;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
		this.rep_freq = rep_freq;
		this.nil_report_flg = nil_report_flg;
	}
	public T27PReport() {}
}

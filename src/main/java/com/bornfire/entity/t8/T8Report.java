package com.bornfire.entity.t8;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T8_TRAN_CUST_TYPE_TABLE")
public class T8Report {
	
	private String	d1a_cash_dep;
	private String	d2a_cash_wdl;
	private String	d3a_dom_inw_rem;
	private String	d4a_dom_out_rem;
	private String	d5a_chq_inw_tran;
	private String	d6a_chq_out_tran;
	private String	d7a_aotr_dr_tran;
	private String	d8a_aotr_cr_tran;
	private String	d9a_aotr_inw_tran;
	private String	d10a_aotr_out_tran;
	private String	d11a_tot_tran;
	private String	d12a_val;
	private BigDecimal	p1b_cash_dep_not_low;
	private BigDecimal	p2b_cash_wdl_not_low;
	private BigDecimal	p3b_bom_inw_rem_not_low;
	private BigDecimal	p4b_bom_out_rem_not_low;
	private BigDecimal	p5b_chq_inw_tran_not_low;
	private BigDecimal	p6b_chq_out_tran_not_low;
	private BigDecimal	p7b_aotr_dr_tran_not_low;
	private BigDecimal	p8b_aotr_cr_tran_not_low;
	private BigDecimal	p9b_aotr_inw_tran_not_low;
	private BigDecimal	p10b_aotr_out_tran_not_low;
	private BigDecimal	p11b_tot_tran_not_low;
	private BigDecimal	p12b_validation_not_low;
	private BigDecimal	p1c_cash_dep_tamt_low;
	private BigDecimal	p2c_cash_wdl_tamt_low;
	private BigDecimal	p3c_com_inw_rem_tamt_low;
	private BigDecimal	p4c_com_out_rem_tamt_low;
	private BigDecimal	p5c_chq_inw_tran_tamt_low;
	private BigDecimal	p6c_chq_out_tran_tamt_low;
	private BigDecimal	p7c_aotr_dr_tran_tamt_low;
	private BigDecimal	p8c_aotr_cr_tran_tamt_low;
	private BigDecimal	p9c_aotr_inw_tran_tamt_low;
	private BigDecimal	p10c_aotr_out_tran_tamt_low;
	private BigDecimal	p11c_tot_tran_tamt_low;
	private BigDecimal	p12c_validation_tamt_low;
	private BigDecimal	p1d_cash_dep_not_med;
	private BigDecimal	p2d_cash_wdl_not_med;
	private BigDecimal	p3d_bom_inw_rem_not_med;
	private BigDecimal	p4d_bom_out_rem_not_med;
	private BigDecimal	p5d_chq_inw_tran_not_med;
	private BigDecimal	p6d_chq_out_tran_not_med;
	private BigDecimal	p7d_aotr_dr_tran_not_med;
	private BigDecimal	p8d_aotr_cr_tran_not_med;
	private BigDecimal	p9d_aotr_inw_tran_not_med;
	private BigDecimal	p10d_aotr_out_tran_not_med;
	private BigDecimal	p11d_tot_tran_not_med;
	private BigDecimal	p12d_validation_not_med;
	private BigDecimal	p1e_cash_dep_tamt_med;
	private BigDecimal	p2e_cash_wdl_tamt_med;
	private BigDecimal	p3e_com_inw_rem_tamt_med;
	private BigDecimal	p4e_com_out_rem_tamt_med;
	private BigDecimal	p5e_chq_inw_tran_tamt_med;
	private BigDecimal	p6e_chq_out_tran_tamt_med;
	private BigDecimal	p7e_aotr_dr_tran_tamt_med;
	private BigDecimal	p8e_aotr_cr_tran_tamt_med;
	private BigDecimal	p9e_aotr_inw_tran_tamt_med;
	private BigDecimal	p10e_aotr_out_tran_tamt_med;
	private BigDecimal	p11e_tot_tran_tamt_med;
	private BigDecimal	p12e_validation_tamt_med;
	private BigDecimal	p1f_cash_dep_not_hig;
	private BigDecimal	p2f_cash_wdl_not_hig;
	private BigDecimal	p3f_bom_inw_rem_not_hig;
	private BigDecimal	p4f_bom_out_rem_not_hig;
	private BigDecimal	p5f_chq_inw_tran_not_hig;
	private BigDecimal	p6f_chq_out_tran_not_hig;
	private BigDecimal	p7f_aotr_dr_tran_not_hig;
	private BigDecimal	p8f_aotr_cr_tran_not_hig;
	private BigDecimal	p9f_aotr_inw_tran_not_hig;
	private BigDecimal	p10f_aotr_out_tran_not_hig;
	private BigDecimal	p11f_tot_tran_not_hig;
	private BigDecimal	p12f_validation_not_hig;
	private BigDecimal	p1g_cash_dep_tamt_hig;
	private BigDecimal	p2g_cash_wdl_tamt_hig;
	private BigDecimal	p3g_com_inw_rem_tamt_hig;
	private BigDecimal	p4g_com_out_rem_tamt_hig;
	private BigDecimal	p5g_chq_inw_tran_tamt_hig;
	private BigDecimal	p6g_chq_out_tran_tamt_hig;
	private BigDecimal	p7g_aotr_dr_tran_tamt_hig;
	private BigDecimal	p8g_aotr_cr_tran_tamt_hig;
	private BigDecimal	p9g_aotr_inw_tran_tamt_hig;
	private BigDecimal	p10g_aotr_out_tran_tamt_hig;
	private BigDecimal	p11g_tot_tran_tamt_hig;
	private BigDecimal	p12g_validation_tamt_hig;
	private BigDecimal	c1b_cash_dep_not_low;
	private BigDecimal	c2b_cash_wdl_not_low;
	private BigDecimal	c3b_bom_inw_rem_not_low;
	private BigDecimal	c4b_bom_out_rem_not_low;
	private BigDecimal	c5b_chq_inw_tran_not_low;
	private BigDecimal	c6b_chq_out_tran_not_low;
	private BigDecimal	c7b_aotr_dr_tran_not_low;
	private BigDecimal	c8b_aotr_cr_tran_not_low;
	private BigDecimal	c9b_aotr_inw_tran_not_low;
	private BigDecimal	c10b_aotr_out_tran_not_low;
	private BigDecimal	c11b_tot_tran_not_low;
	private BigDecimal	c12b_validation_not_low;
	private BigDecimal	c1c_cash_dep_tamt_low;
	private BigDecimal	c2c_cash_wdl_tamt_low;
	private BigDecimal	c3c_com_inw_rem_tamt_low;
	private BigDecimal	c4c_com_out_rem_tamt_low;
	private BigDecimal	c5c_chq_inw_tran_tamt_low;
	private BigDecimal	c6c_chq_out_tran_tamt_low;
	private BigDecimal	c7c_aotr_dr_tran_tamt_low;
	private BigDecimal	c8c_aotr_cr_tran_tamt_low;
	private BigDecimal	c9c_aotr_inw_tran_tamt_low;
	private BigDecimal	c10c_aotr_out_tran_tamt_low;
	private BigDecimal	c11c_tot_tran_tamt_low;
	private BigDecimal	c12c_validation_tamt_low;
	private BigDecimal	c1d_cash_dep_not_med;
	private BigDecimal	c2d_cash_wdl_not_med;
	private BigDecimal	c3d_bom_inw_rem_not_med;
	private BigDecimal	c4d_bom_out_rem_not_med;
	private BigDecimal	c5d_chq_inw_tran_not_med;
	private BigDecimal	c6d_chq_out_tran_not_med;
	private BigDecimal	c7d_aotr_dr_tran_not_med;
	private BigDecimal	c8d_aotr_cr_tran_not_med;
	private BigDecimal	c9d_aotr_inw_tran_not_med;
	private BigDecimal	c10d_aotr_out_tran_not_med;
	private BigDecimal	c11d_tot_tran_not_med;
	private BigDecimal	c12d_validation_not_med;
	private BigDecimal	c1e_cash_dep_tamt_med;
	private BigDecimal	c2e_cash_wdl_tamt_med;
	private BigDecimal	c3e_com_inw_rem_tamt_med;
	private BigDecimal	c4e_com_out_rem_tamt_med;
	private BigDecimal	c5e_chq_inw_tran_tamt_med;
	private BigDecimal	c6e_chq_out_tran_tamt_med;
	private BigDecimal	c7e_aotr_dr_tran_tamt_med;
	private BigDecimal	c8e_aotr_cr_tran_tamt_med;
	private BigDecimal	c9e_aotr_inw_tran_tamt_med;
	private BigDecimal	c10e_aotr_out_tran_tamt_med;
	private BigDecimal	c11e_tot_tran_tamt_med;
	private BigDecimal	c12e_validation_tamt_med;
	private BigDecimal	c1f_cash_dep_not_hig;
	private BigDecimal	c2f_cash_wdl_not_hig;
	private BigDecimal	c3f_bom_inw_rem_not_hig;
	private BigDecimal	c4f_bom_out_rem_not_hig;
	private BigDecimal	c5f_chq_inw_tran_not_hig;
	private BigDecimal	c6f_chq_out_tran_not_hig;
	private BigDecimal	c7f_aotr_dr_tran_not_hig;
	private BigDecimal	c8f_aotr_cr_tran_not_hig;
	private BigDecimal	c9f_aotr_inw_tran_not_hig;
	private BigDecimal	c10f_aotr_out_tran_not_hig;
	private BigDecimal	c11f_tot_tran_not_hig;
	private BigDecimal	c12f_validation_not_hig;
	private BigDecimal	c1g_cash_dep_tamt_hig;
	private BigDecimal	c2g_cash_wdl_tamt_hig;
	private BigDecimal	c3g_com_inw_rem_tamt_hig;
	private BigDecimal	c4g_com_out_rem_tamt_hig;
	private BigDecimal	c5g_chq_inw_tran_tamt_hig;
	private BigDecimal	c6g_chq_out_tran_tamt_hig;
	private BigDecimal	c7g_aotr_dr_tran_tamt_hig;
	private BigDecimal	c8g_aotr_cr_tran_tamt_hig;
	private BigDecimal	c9g_aotr_inw_tran_tamt_hig;
	private BigDecimal	c10g_aotr_out_tran_tamt_hig;
	private BigDecimal	c11g_tot_tran_tamt_hig;
	private BigDecimal	c12g_validation_tamt_hig;
	private String	report_code;
	private String	report_name;
	@Id
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	report_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	report_due_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_submit_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_from;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_to;
	private String	rep_freq;
	private String	nil_report_flg;
	private String	arch_flg;
	public String getD1a_cash_dep() {
		return d1a_cash_dep;
	}
	public void setD1a_cash_dep(String d1a_cash_dep) {
		this.d1a_cash_dep = d1a_cash_dep;
	}
	public String getD2a_cash_wdl() {
		return d2a_cash_wdl;
	}
	public void setD2a_cash_wdl(String d2a_cash_wdl) {
		this.d2a_cash_wdl = d2a_cash_wdl;
	}
	public String getD3a_dom_inw_rem() {
		return d3a_dom_inw_rem;
	}
	public void setD3a_dom_inw_rem(String d3a_dom_inw_rem) {
		this.d3a_dom_inw_rem = d3a_dom_inw_rem;
	}
	public String getD4a_dom_out_rem() {
		return d4a_dom_out_rem;
	}
	public void setD4a_dom_out_rem(String d4a_dom_out_rem) {
		this.d4a_dom_out_rem = d4a_dom_out_rem;
	}
	public String getD5a_chq_inw_tran() {
		return d5a_chq_inw_tran;
	}
	public void setD5a_chq_inw_tran(String d5a_chq_inw_tran) {
		this.d5a_chq_inw_tran = d5a_chq_inw_tran;
	}
	public String getD6a_chq_out_tran() {
		return d6a_chq_out_tran;
	}
	public void setD6a_chq_out_tran(String d6a_chq_out_tran) {
		this.d6a_chq_out_tran = d6a_chq_out_tran;
	}
	public String getD7a_aotr_dr_tran() {
		return d7a_aotr_dr_tran;
	}
	public void setD7a_aotr_dr_tran(String d7a_aotr_dr_tran) {
		this.d7a_aotr_dr_tran = d7a_aotr_dr_tran;
	}
	public String getD8a_aotr_cr_tran() {
		return d8a_aotr_cr_tran;
	}
	public void setD8a_aotr_cr_tran(String d8a_aotr_cr_tran) {
		this.d8a_aotr_cr_tran = d8a_aotr_cr_tran;
	}
	public String getD9a_aotr_inw_tran() {
		return d9a_aotr_inw_tran;
	}
	public void setD9a_aotr_inw_tran(String d9a_aotr_inw_tran) {
		this.d9a_aotr_inw_tran = d9a_aotr_inw_tran;
	}
	public String getD10a_aotr_out_tran() {
		return d10a_aotr_out_tran;
	}
	public void setD10a_aotr_out_tran(String d10a_aotr_out_tran) {
		this.d10a_aotr_out_tran = d10a_aotr_out_tran;
	}
	public String getD11a_tot_tran() {
		return d11a_tot_tran;
	}
	public void setD11a_tot_tran(String d11a_tot_tran) {
		this.d11a_tot_tran = d11a_tot_tran;
	}
	public String getD12a_val() {
		return d12a_val;
	}
	public void setD12a_val(String d12a_val) {
		this.d12a_val = d12a_val;
	}
	public BigDecimal getP1b_cash_dep_not_low() {
		return p1b_cash_dep_not_low;
	}
	public void setP1b_cash_dep_not_low(BigDecimal p1b_cash_dep_not_low) {
		this.p1b_cash_dep_not_low = p1b_cash_dep_not_low;
	}
	public BigDecimal getP2b_cash_wdl_not_low() {
		return p2b_cash_wdl_not_low;
	}
	public void setP2b_cash_wdl_not_low(BigDecimal p2b_cash_wdl_not_low) {
		this.p2b_cash_wdl_not_low = p2b_cash_wdl_not_low;
	}
	public BigDecimal getP3b_bom_inw_rem_not_low() {
		return p3b_bom_inw_rem_not_low;
	}
	public void setP3b_bom_inw_rem_not_low(BigDecimal p3b_bom_inw_rem_not_low) {
		this.p3b_bom_inw_rem_not_low = p3b_bom_inw_rem_not_low;
	}
	public BigDecimal getP4b_bom_out_rem_not_low() {
		return p4b_bom_out_rem_not_low;
	}
	public void setP4b_bom_out_rem_not_low(BigDecimal p4b_bom_out_rem_not_low) {
		this.p4b_bom_out_rem_not_low = p4b_bom_out_rem_not_low;
	}
	public BigDecimal getP5b_chq_inw_tran_not_low() {
		return p5b_chq_inw_tran_not_low;
	}
	public void setP5b_chq_inw_tran_not_low(BigDecimal p5b_chq_inw_tran_not_low) {
		this.p5b_chq_inw_tran_not_low = p5b_chq_inw_tran_not_low;
	}
	public BigDecimal getP6b_chq_out_tran_not_low() {
		return p6b_chq_out_tran_not_low;
	}
	public void setP6b_chq_out_tran_not_low(BigDecimal p6b_chq_out_tran_not_low) {
		this.p6b_chq_out_tran_not_low = p6b_chq_out_tran_not_low;
	}
	public BigDecimal getP7b_aotr_dr_tran_not_low() {
		return p7b_aotr_dr_tran_not_low;
	}
	public void setP7b_aotr_dr_tran_not_low(BigDecimal p7b_aotr_dr_tran_not_low) {
		this.p7b_aotr_dr_tran_not_low = p7b_aotr_dr_tran_not_low;
	}
	public BigDecimal getP8b_aotr_cr_tran_not_low() {
		return p8b_aotr_cr_tran_not_low;
	}
	public void setP8b_aotr_cr_tran_not_low(BigDecimal p8b_aotr_cr_tran_not_low) {
		this.p8b_aotr_cr_tran_not_low = p8b_aotr_cr_tran_not_low;
	}
	public BigDecimal getP9b_aotr_inw_tran_not_low() {
		return p9b_aotr_inw_tran_not_low;
	}
	public void setP9b_aotr_inw_tran_not_low(BigDecimal p9b_aotr_inw_tran_not_low) {
		this.p9b_aotr_inw_tran_not_low = p9b_aotr_inw_tran_not_low;
	}
	public BigDecimal getP10b_aotr_out_tran_not_low() {
		return p10b_aotr_out_tran_not_low;
	}
	public void setP10b_aotr_out_tran_not_low(BigDecimal p10b_aotr_out_tran_not_low) {
		this.p10b_aotr_out_tran_not_low = p10b_aotr_out_tran_not_low;
	}
	public BigDecimal getP11b_tot_tran_not_low() {
		return p11b_tot_tran_not_low;
	}
	public void setP11b_tot_tran_not_low(BigDecimal p11b_tot_tran_not_low) {
		this.p11b_tot_tran_not_low = p11b_tot_tran_not_low;
	}
	public BigDecimal getP12b_validation_not_low() {
		return p12b_validation_not_low;
	}
	public void setP12b_validation_not_low(BigDecimal p12b_validation_not_low) {
		this.p12b_validation_not_low = p12b_validation_not_low;
	}
	public BigDecimal getP1c_cash_dep_tamt_low() {
		return p1c_cash_dep_tamt_low;
	}
	public void setP1c_cash_dep_tamt_low(BigDecimal p1c_cash_dep_tamt_low) {
		this.p1c_cash_dep_tamt_low = p1c_cash_dep_tamt_low;
	}
	public BigDecimal getP2c_cash_wdl_tamt_low() {
		return p2c_cash_wdl_tamt_low;
	}
	public void setP2c_cash_wdl_tamt_low(BigDecimal p2c_cash_wdl_tamt_low) {
		this.p2c_cash_wdl_tamt_low = p2c_cash_wdl_tamt_low;
	}
	public BigDecimal getP3c_com_inw_rem_tamt_low() {
		return p3c_com_inw_rem_tamt_low;
	}
	public void setP3c_com_inw_rem_tamt_low(BigDecimal p3c_com_inw_rem_tamt_low) {
		this.p3c_com_inw_rem_tamt_low = p3c_com_inw_rem_tamt_low;
	}
	public BigDecimal getP4c_com_out_rem_tamt_low() {
		return p4c_com_out_rem_tamt_low;
	}
	public void setP4c_com_out_rem_tamt_low(BigDecimal p4c_com_out_rem_tamt_low) {
		this.p4c_com_out_rem_tamt_low = p4c_com_out_rem_tamt_low;
	}
	public BigDecimal getP5c_chq_inw_tran_tamt_low() {
		return p5c_chq_inw_tran_tamt_low;
	}
	public void setP5c_chq_inw_tran_tamt_low(BigDecimal p5c_chq_inw_tran_tamt_low) {
		this.p5c_chq_inw_tran_tamt_low = p5c_chq_inw_tran_tamt_low;
	}
	public BigDecimal getP6c_chq_out_tran_tamt_low() {
		return p6c_chq_out_tran_tamt_low;
	}
	public void setP6c_chq_out_tran_tamt_low(BigDecimal p6c_chq_out_tran_tamt_low) {
		this.p6c_chq_out_tran_tamt_low = p6c_chq_out_tran_tamt_low;
	}
	public BigDecimal getP7c_aotr_dr_tran_tamt_low() {
		return p7c_aotr_dr_tran_tamt_low;
	}
	public void setP7c_aotr_dr_tran_tamt_low(BigDecimal p7c_aotr_dr_tran_tamt_low) {
		this.p7c_aotr_dr_tran_tamt_low = p7c_aotr_dr_tran_tamt_low;
	}
	public BigDecimal getP8c_aotr_cr_tran_tamt_low() {
		return p8c_aotr_cr_tran_tamt_low;
	}
	public void setP8c_aotr_cr_tran_tamt_low(BigDecimal p8c_aotr_cr_tran_tamt_low) {
		this.p8c_aotr_cr_tran_tamt_low = p8c_aotr_cr_tran_tamt_low;
	}
	public BigDecimal getP9c_aotr_inw_tran_tamt_low() {
		return p9c_aotr_inw_tran_tamt_low;
	}
	public void setP9c_aotr_inw_tran_tamt_low(BigDecimal p9c_aotr_inw_tran_tamt_low) {
		this.p9c_aotr_inw_tran_tamt_low = p9c_aotr_inw_tran_tamt_low;
	}
	public BigDecimal getP10c_aotr_out_tran_tamt_low() {
		return p10c_aotr_out_tran_tamt_low;
	}
	public void setP10c_aotr_out_tran_tamt_low(BigDecimal p10c_aotr_out_tran_tamt_low) {
		this.p10c_aotr_out_tran_tamt_low = p10c_aotr_out_tran_tamt_low;
	}
	public BigDecimal getP11c_tot_tran_tamt_low() {
		return p11c_tot_tran_tamt_low;
	}
	public void setP11c_tot_tran_tamt_low(BigDecimal p11c_tot_tran_tamt_low) {
		this.p11c_tot_tran_tamt_low = p11c_tot_tran_tamt_low;
	}
	public BigDecimal getP12c_validation_tamt_low() {
		return p12c_validation_tamt_low;
	}
	public void setP12c_validation_tamt_low(BigDecimal p12c_validation_tamt_low) {
		this.p12c_validation_tamt_low = p12c_validation_tamt_low;
	}
	public BigDecimal getP1d_cash_dep_not_med() {
		return p1d_cash_dep_not_med;
	}
	public void setP1d_cash_dep_not_med(BigDecimal p1d_cash_dep_not_med) {
		this.p1d_cash_dep_not_med = p1d_cash_dep_not_med;
	}
	public BigDecimal getP2d_cash_wdl_not_med() {
		return p2d_cash_wdl_not_med;
	}
	public void setP2d_cash_wdl_not_med(BigDecimal p2d_cash_wdl_not_med) {
		this.p2d_cash_wdl_not_med = p2d_cash_wdl_not_med;
	}
	public BigDecimal getP3d_bom_inw_rem_not_med() {
		return p3d_bom_inw_rem_not_med;
	}
	public void setP3d_bom_inw_rem_not_med(BigDecimal p3d_bom_inw_rem_not_med) {
		this.p3d_bom_inw_rem_not_med = p3d_bom_inw_rem_not_med;
	}
	public BigDecimal getP4d_bom_out_rem_not_med() {
		return p4d_bom_out_rem_not_med;
	}
	public void setP4d_bom_out_rem_not_med(BigDecimal p4d_bom_out_rem_not_med) {
		this.p4d_bom_out_rem_not_med = p4d_bom_out_rem_not_med;
	}
	public BigDecimal getP5d_chq_inw_tran_not_med() {
		return p5d_chq_inw_tran_not_med;
	}
	public void setP5d_chq_inw_tran_not_med(BigDecimal p5d_chq_inw_tran_not_med) {
		this.p5d_chq_inw_tran_not_med = p5d_chq_inw_tran_not_med;
	}
	public BigDecimal getP6d_chq_out_tran_not_med() {
		return p6d_chq_out_tran_not_med;
	}
	public void setP6d_chq_out_tran_not_med(BigDecimal p6d_chq_out_tran_not_med) {
		this.p6d_chq_out_tran_not_med = p6d_chq_out_tran_not_med;
	}
	public BigDecimal getP7d_aotr_dr_tran_not_med() {
		return p7d_aotr_dr_tran_not_med;
	}
	public void setP7d_aotr_dr_tran_not_med(BigDecimal p7d_aotr_dr_tran_not_med) {
		this.p7d_aotr_dr_tran_not_med = p7d_aotr_dr_tran_not_med;
	}
	public BigDecimal getP8d_aotr_cr_tran_not_med() {
		return p8d_aotr_cr_tran_not_med;
	}
	public void setP8d_aotr_cr_tran_not_med(BigDecimal p8d_aotr_cr_tran_not_med) {
		this.p8d_aotr_cr_tran_not_med = p8d_aotr_cr_tran_not_med;
	}
	public BigDecimal getP9d_aotr_inw_tran_not_med() {
		return p9d_aotr_inw_tran_not_med;
	}
	public void setP9d_aotr_inw_tran_not_med(BigDecimal p9d_aotr_inw_tran_not_med) {
		this.p9d_aotr_inw_tran_not_med = p9d_aotr_inw_tran_not_med;
	}
	public BigDecimal getP10d_aotr_out_tran_not_med() {
		return p10d_aotr_out_tran_not_med;
	}
	public void setP10d_aotr_out_tran_not_med(BigDecimal p10d_aotr_out_tran_not_med) {
		this.p10d_aotr_out_tran_not_med = p10d_aotr_out_tran_not_med;
	}
	public BigDecimal getP11d_tot_tran_not_med() {
		return p11d_tot_tran_not_med;
	}
	public void setP11d_tot_tran_not_med(BigDecimal p11d_tot_tran_not_med) {
		this.p11d_tot_tran_not_med = p11d_tot_tran_not_med;
	}
	public BigDecimal getP12d_validation_not_med() {
		return p12d_validation_not_med;
	}
	public void setP12d_validation_not_med(BigDecimal p12d_validation_not_med) {
		this.p12d_validation_not_med = p12d_validation_not_med;
	}
	public BigDecimal getP1e_cash_dep_tamt_med() {
		return p1e_cash_dep_tamt_med;
	}
	public void setP1e_cash_dep_tamt_med(BigDecimal p1e_cash_dep_tamt_med) {
		this.p1e_cash_dep_tamt_med = p1e_cash_dep_tamt_med;
	}
	public BigDecimal getP2e_cash_wdl_tamt_med() {
		return p2e_cash_wdl_tamt_med;
	}
	public void setP2e_cash_wdl_tamt_med(BigDecimal p2e_cash_wdl_tamt_med) {
		this.p2e_cash_wdl_tamt_med = p2e_cash_wdl_tamt_med;
	}
	public BigDecimal getP3e_com_inw_rem_tamt_med() {
		return p3e_com_inw_rem_tamt_med;
	}
	public void setP3e_com_inw_rem_tamt_med(BigDecimal p3e_com_inw_rem_tamt_med) {
		this.p3e_com_inw_rem_tamt_med = p3e_com_inw_rem_tamt_med;
	}
	public BigDecimal getP4e_com_out_rem_tamt_med() {
		return p4e_com_out_rem_tamt_med;
	}
	public void setP4e_com_out_rem_tamt_med(BigDecimal p4e_com_out_rem_tamt_med) {
		this.p4e_com_out_rem_tamt_med = p4e_com_out_rem_tamt_med;
	}
	public BigDecimal getP5e_chq_inw_tran_tamt_med() {
		return p5e_chq_inw_tran_tamt_med;
	}
	public void setP5e_chq_inw_tran_tamt_med(BigDecimal p5e_chq_inw_tran_tamt_med) {
		this.p5e_chq_inw_tran_tamt_med = p5e_chq_inw_tran_tamt_med;
	}
	public BigDecimal getP6e_chq_out_tran_tamt_med() {
		return p6e_chq_out_tran_tamt_med;
	}
	public void setP6e_chq_out_tran_tamt_med(BigDecimal p6e_chq_out_tran_tamt_med) {
		this.p6e_chq_out_tran_tamt_med = p6e_chq_out_tran_tamt_med;
	}
	public BigDecimal getP7e_aotr_dr_tran_tamt_med() {
		return p7e_aotr_dr_tran_tamt_med;
	}
	public void setP7e_aotr_dr_tran_tamt_med(BigDecimal p7e_aotr_dr_tran_tamt_med) {
		this.p7e_aotr_dr_tran_tamt_med = p7e_aotr_dr_tran_tamt_med;
	}
	public BigDecimal getP8e_aotr_cr_tran_tamt_med() {
		return p8e_aotr_cr_tran_tamt_med;
	}
	public void setP8e_aotr_cr_tran_tamt_med(BigDecimal p8e_aotr_cr_tran_tamt_med) {
		this.p8e_aotr_cr_tran_tamt_med = p8e_aotr_cr_tran_tamt_med;
	}
	public BigDecimal getP9e_aotr_inw_tran_tamt_med() {
		return p9e_aotr_inw_tran_tamt_med;
	}
	public void setP9e_aotr_inw_tran_tamt_med(BigDecimal p9e_aotr_inw_tran_tamt_med) {
		this.p9e_aotr_inw_tran_tamt_med = p9e_aotr_inw_tran_tamt_med;
	}
	public BigDecimal getP10e_aotr_out_tran_tamt_med() {
		return p10e_aotr_out_tran_tamt_med;
	}
	public void setP10e_aotr_out_tran_tamt_med(BigDecimal p10e_aotr_out_tran_tamt_med) {
		this.p10e_aotr_out_tran_tamt_med = p10e_aotr_out_tran_tamt_med;
	}
	public BigDecimal getP11e_tot_tran_tamt_med() {
		return p11e_tot_tran_tamt_med;
	}
	public void setP11e_tot_tran_tamt_med(BigDecimal p11e_tot_tran_tamt_med) {
		this.p11e_tot_tran_tamt_med = p11e_tot_tran_tamt_med;
	}
	public BigDecimal getP12e_validation_tamt_med() {
		return p12e_validation_tamt_med;
	}
	public void setP12e_validation_tamt_med(BigDecimal p12e_validation_tamt_med) {
		this.p12e_validation_tamt_med = p12e_validation_tamt_med;
	}
	public BigDecimal getP1f_cash_dep_not_hig() {
		return p1f_cash_dep_not_hig;
	}
	public void setP1f_cash_dep_not_hig(BigDecimal p1f_cash_dep_not_hig) {
		this.p1f_cash_dep_not_hig = p1f_cash_dep_not_hig;
	}
	public BigDecimal getP2f_cash_wdl_not_hig() {
		return p2f_cash_wdl_not_hig;
	}
	public void setP2f_cash_wdl_not_hig(BigDecimal p2f_cash_wdl_not_hig) {
		this.p2f_cash_wdl_not_hig = p2f_cash_wdl_not_hig;
	}
	public BigDecimal getP3f_bom_inw_rem_not_hig() {
		return p3f_bom_inw_rem_not_hig;
	}
	public void setP3f_bom_inw_rem_not_hig(BigDecimal p3f_bom_inw_rem_not_hig) {
		this.p3f_bom_inw_rem_not_hig = p3f_bom_inw_rem_not_hig;
	}
	public BigDecimal getP4f_bom_out_rem_not_hig() {
		return p4f_bom_out_rem_not_hig;
	}
	public void setP4f_bom_out_rem_not_hig(BigDecimal p4f_bom_out_rem_not_hig) {
		this.p4f_bom_out_rem_not_hig = p4f_bom_out_rem_not_hig;
	}
	public BigDecimal getP5f_chq_inw_tran_not_hig() {
		return p5f_chq_inw_tran_not_hig;
	}
	public void setP5f_chq_inw_tran_not_hig(BigDecimal p5f_chq_inw_tran_not_hig) {
		this.p5f_chq_inw_tran_not_hig = p5f_chq_inw_tran_not_hig;
	}
	public BigDecimal getP6f_chq_out_tran_not_hig() {
		return p6f_chq_out_tran_not_hig;
	}
	public void setP6f_chq_out_tran_not_hig(BigDecimal p6f_chq_out_tran_not_hig) {
		this.p6f_chq_out_tran_not_hig = p6f_chq_out_tran_not_hig;
	}
	public BigDecimal getP7f_aotr_dr_tran_not_hig() {
		return p7f_aotr_dr_tran_not_hig;
	}
	public void setP7f_aotr_dr_tran_not_hig(BigDecimal p7f_aotr_dr_tran_not_hig) {
		this.p7f_aotr_dr_tran_not_hig = p7f_aotr_dr_tran_not_hig;
	}
	public BigDecimal getP8f_aotr_cr_tran_not_hig() {
		return p8f_aotr_cr_tran_not_hig;
	}
	public void setP8f_aotr_cr_tran_not_hig(BigDecimal p8f_aotr_cr_tran_not_hig) {
		this.p8f_aotr_cr_tran_not_hig = p8f_aotr_cr_tran_not_hig;
	}
	public BigDecimal getP9f_aotr_inw_tran_not_hig() {
		return p9f_aotr_inw_tran_not_hig;
	}
	public void setP9f_aotr_inw_tran_not_hig(BigDecimal p9f_aotr_inw_tran_not_hig) {
		this.p9f_aotr_inw_tran_not_hig = p9f_aotr_inw_tran_not_hig;
	}
	public BigDecimal getP10f_aotr_out_tran_not_hig() {
		return p10f_aotr_out_tran_not_hig;
	}
	public void setP10f_aotr_out_tran_not_hig(BigDecimal p10f_aotr_out_tran_not_hig) {
		this.p10f_aotr_out_tran_not_hig = p10f_aotr_out_tran_not_hig;
	}
	public BigDecimal getP11f_tot_tran_not_hig() {
		return p11f_tot_tran_not_hig;
	}
	public void setP11f_tot_tran_not_hig(BigDecimal p11f_tot_tran_not_hig) {
		this.p11f_tot_tran_not_hig = p11f_tot_tran_not_hig;
	}
	public BigDecimal getP12f_validation_not_hig() {
		return p12f_validation_not_hig;
	}
	public void setP12f_validation_not_hig(BigDecimal p12f_validation_not_hig) {
		this.p12f_validation_not_hig = p12f_validation_not_hig;
	}
	public BigDecimal getP1g_cash_dep_tamt_hig() {
		return p1g_cash_dep_tamt_hig;
	}
	public void setP1g_cash_dep_tamt_hig(BigDecimal p1g_cash_dep_tamt_hig) {
		this.p1g_cash_dep_tamt_hig = p1g_cash_dep_tamt_hig;
	}
	public BigDecimal getP2g_cash_wdl_tamt_hig() {
		return p2g_cash_wdl_tamt_hig;
	}
	public void setP2g_cash_wdl_tamt_hig(BigDecimal p2g_cash_wdl_tamt_hig) {
		this.p2g_cash_wdl_tamt_hig = p2g_cash_wdl_tamt_hig;
	}
	public BigDecimal getP3g_com_inw_rem_tamt_hig() {
		return p3g_com_inw_rem_tamt_hig;
	}
	public void setP3g_com_inw_rem_tamt_hig(BigDecimal p3g_com_inw_rem_tamt_hig) {
		this.p3g_com_inw_rem_tamt_hig = p3g_com_inw_rem_tamt_hig;
	}
	public BigDecimal getP4g_com_out_rem_tamt_hig() {
		return p4g_com_out_rem_tamt_hig;
	}
	public void setP4g_com_out_rem_tamt_hig(BigDecimal p4g_com_out_rem_tamt_hig) {
		this.p4g_com_out_rem_tamt_hig = p4g_com_out_rem_tamt_hig;
	}
	public BigDecimal getP5g_chq_inw_tran_tamt_hig() {
		return p5g_chq_inw_tran_tamt_hig;
	}
	public void setP5g_chq_inw_tran_tamt_hig(BigDecimal p5g_chq_inw_tran_tamt_hig) {
		this.p5g_chq_inw_tran_tamt_hig = p5g_chq_inw_tran_tamt_hig;
	}
	public BigDecimal getP6g_chq_out_tran_tamt_hig() {
		return p6g_chq_out_tran_tamt_hig;
	}
	public void setP6g_chq_out_tran_tamt_hig(BigDecimal p6g_chq_out_tran_tamt_hig) {
		this.p6g_chq_out_tran_tamt_hig = p6g_chq_out_tran_tamt_hig;
	}
	public BigDecimal getP7g_aotr_dr_tran_tamt_hig() {
		return p7g_aotr_dr_tran_tamt_hig;
	}
	public void setP7g_aotr_dr_tran_tamt_hig(BigDecimal p7g_aotr_dr_tran_tamt_hig) {
		this.p7g_aotr_dr_tran_tamt_hig = p7g_aotr_dr_tran_tamt_hig;
	}
	public BigDecimal getP8g_aotr_cr_tran_tamt_hig() {
		return p8g_aotr_cr_tran_tamt_hig;
	}
	public void setP8g_aotr_cr_tran_tamt_hig(BigDecimal p8g_aotr_cr_tran_tamt_hig) {
		this.p8g_aotr_cr_tran_tamt_hig = p8g_aotr_cr_tran_tamt_hig;
	}
	public BigDecimal getP9g_aotr_inw_tran_tamt_hig() {
		return p9g_aotr_inw_tran_tamt_hig;
	}
	public void setP9g_aotr_inw_tran_tamt_hig(BigDecimal p9g_aotr_inw_tran_tamt_hig) {
		this.p9g_aotr_inw_tran_tamt_hig = p9g_aotr_inw_tran_tamt_hig;
	}
	public BigDecimal getP10g_aotr_out_tran_tamt_hig() {
		return p10g_aotr_out_tran_tamt_hig;
	}
	public void setP10g_aotr_out_tran_tamt_hig(BigDecimal p10g_aotr_out_tran_tamt_hig) {
		this.p10g_aotr_out_tran_tamt_hig = p10g_aotr_out_tran_tamt_hig;
	}
	public BigDecimal getP11g_tot_tran_tamt_hig() {
		return p11g_tot_tran_tamt_hig;
	}
	public void setP11g_tot_tran_tamt_hig(BigDecimal p11g_tot_tran_tamt_hig) {
		this.p11g_tot_tran_tamt_hig = p11g_tot_tran_tamt_hig;
	}
	public BigDecimal getP12g_validation_tamt_hig() {
		return p12g_validation_tamt_hig;
	}
	public void setP12g_validation_tamt_hig(BigDecimal p12g_validation_tamt_hig) {
		this.p12g_validation_tamt_hig = p12g_validation_tamt_hig;
	}
	public BigDecimal getC1b_cash_dep_not_low() {
		return c1b_cash_dep_not_low;
	}
	public void setC1b_cash_dep_not_low(BigDecimal c1b_cash_dep_not_low) {
		this.c1b_cash_dep_not_low = c1b_cash_dep_not_low;
	}
	public BigDecimal getC2b_cash_wdl_not_low() {
		return c2b_cash_wdl_not_low;
	}
	public void setC2b_cash_wdl_not_low(BigDecimal c2b_cash_wdl_not_low) {
		this.c2b_cash_wdl_not_low = c2b_cash_wdl_not_low;
	}
	public BigDecimal getC3b_bom_inw_rem_not_low() {
		return c3b_bom_inw_rem_not_low;
	}
	public void setC3b_bom_inw_rem_not_low(BigDecimal c3b_bom_inw_rem_not_low) {
		this.c3b_bom_inw_rem_not_low = c3b_bom_inw_rem_not_low;
	}
	public BigDecimal getC4b_bom_out_rem_not_low() {
		return c4b_bom_out_rem_not_low;
	}
	public void setC4b_bom_out_rem_not_low(BigDecimal c4b_bom_out_rem_not_low) {
		this.c4b_bom_out_rem_not_low = c4b_bom_out_rem_not_low;
	}
	public BigDecimal getC5b_chq_inw_tran_not_low() {
		return c5b_chq_inw_tran_not_low;
	}
	public void setC5b_chq_inw_tran_not_low(BigDecimal c5b_chq_inw_tran_not_low) {
		this.c5b_chq_inw_tran_not_low = c5b_chq_inw_tran_not_low;
	}
	public BigDecimal getC6b_chq_out_tran_not_low() {
		return c6b_chq_out_tran_not_low;
	}
	public void setC6b_chq_out_tran_not_low(BigDecimal c6b_chq_out_tran_not_low) {
		this.c6b_chq_out_tran_not_low = c6b_chq_out_tran_not_low;
	}
	public BigDecimal getC7b_aotr_dr_tran_not_low() {
		return c7b_aotr_dr_tran_not_low;
	}
	public void setC7b_aotr_dr_tran_not_low(BigDecimal c7b_aotr_dr_tran_not_low) {
		this.c7b_aotr_dr_tran_not_low = c7b_aotr_dr_tran_not_low;
	}
	public BigDecimal getC8b_aotr_cr_tran_not_low() {
		return c8b_aotr_cr_tran_not_low;
	}
	public void setC8b_aotr_cr_tran_not_low(BigDecimal c8b_aotr_cr_tran_not_low) {
		this.c8b_aotr_cr_tran_not_low = c8b_aotr_cr_tran_not_low;
	}
	public BigDecimal getC9b_aotr_inw_tran_not_low() {
		return c9b_aotr_inw_tran_not_low;
	}
	public void setC9b_aotr_inw_tran_not_low(BigDecimal c9b_aotr_inw_tran_not_low) {
		this.c9b_aotr_inw_tran_not_low = c9b_aotr_inw_tran_not_low;
	}
	public BigDecimal getC10b_aotr_out_tran_not_low() {
		return c10b_aotr_out_tran_not_low;
	}
	public void setC10b_aotr_out_tran_not_low(BigDecimal c10b_aotr_out_tran_not_low) {
		this.c10b_aotr_out_tran_not_low = c10b_aotr_out_tran_not_low;
	}
	public BigDecimal getC11b_tot_tran_not_low() {
		return c11b_tot_tran_not_low;
	}
	public void setC11b_tot_tran_not_low(BigDecimal c11b_tot_tran_not_low) {
		this.c11b_tot_tran_not_low = c11b_tot_tran_not_low;
	}
	public BigDecimal getC12b_validation_not_low() {
		return c12b_validation_not_low;
	}
	public void setC12b_validation_not_low(BigDecimal c12b_validation_not_low) {
		this.c12b_validation_not_low = c12b_validation_not_low;
	}
	public BigDecimal getC1c_cash_dep_tamt_low() {
		return c1c_cash_dep_tamt_low;
	}
	public void setC1c_cash_dep_tamt_low(BigDecimal c1c_cash_dep_tamt_low) {
		this.c1c_cash_dep_tamt_low = c1c_cash_dep_tamt_low;
	}
	public BigDecimal getC2c_cash_wdl_tamt_low() {
		return c2c_cash_wdl_tamt_low;
	}
	public void setC2c_cash_wdl_tamt_low(BigDecimal c2c_cash_wdl_tamt_low) {
		this.c2c_cash_wdl_tamt_low = c2c_cash_wdl_tamt_low;
	}
	public BigDecimal getC3c_com_inw_rem_tamt_low() {
		return c3c_com_inw_rem_tamt_low;
	}
	public void setC3c_com_inw_rem_tamt_low(BigDecimal c3c_com_inw_rem_tamt_low) {
		this.c3c_com_inw_rem_tamt_low = c3c_com_inw_rem_tamt_low;
	}
	public BigDecimal getC4c_com_out_rem_tamt_low() {
		return c4c_com_out_rem_tamt_low;
	}
	public void setC4c_com_out_rem_tamt_low(BigDecimal c4c_com_out_rem_tamt_low) {
		this.c4c_com_out_rem_tamt_low = c4c_com_out_rem_tamt_low;
	}
	public BigDecimal getC5c_chq_inw_tran_tamt_low() {
		return c5c_chq_inw_tran_tamt_low;
	}
	public void setC5c_chq_inw_tran_tamt_low(BigDecimal c5c_chq_inw_tran_tamt_low) {
		this.c5c_chq_inw_tran_tamt_low = c5c_chq_inw_tran_tamt_low;
	}
	public BigDecimal getC6c_chq_out_tran_tamt_low() {
		return c6c_chq_out_tran_tamt_low;
	}
	public void setC6c_chq_out_tran_tamt_low(BigDecimal c6c_chq_out_tran_tamt_low) {
		this.c6c_chq_out_tran_tamt_low = c6c_chq_out_tran_tamt_low;
	}
	public BigDecimal getC7c_aotr_dr_tran_tamt_low() {
		return c7c_aotr_dr_tran_tamt_low;
	}
	public void setC7c_aotr_dr_tran_tamt_low(BigDecimal c7c_aotr_dr_tran_tamt_low) {
		this.c7c_aotr_dr_tran_tamt_low = c7c_aotr_dr_tran_tamt_low;
	}
	public BigDecimal getC8c_aotr_cr_tran_tamt_low() {
		return c8c_aotr_cr_tran_tamt_low;
	}
	public void setC8c_aotr_cr_tran_tamt_low(BigDecimal c8c_aotr_cr_tran_tamt_low) {
		this.c8c_aotr_cr_tran_tamt_low = c8c_aotr_cr_tran_tamt_low;
	}
	public BigDecimal getC9c_aotr_inw_tran_tamt_low() {
		return c9c_aotr_inw_tran_tamt_low;
	}
	public void setC9c_aotr_inw_tran_tamt_low(BigDecimal c9c_aotr_inw_tran_tamt_low) {
		this.c9c_aotr_inw_tran_tamt_low = c9c_aotr_inw_tran_tamt_low;
	}
	public BigDecimal getC10c_aotr_out_tran_tamt_low() {
		return c10c_aotr_out_tran_tamt_low;
	}
	public void setC10c_aotr_out_tran_tamt_low(BigDecimal c10c_aotr_out_tran_tamt_low) {
		this.c10c_aotr_out_tran_tamt_low = c10c_aotr_out_tran_tamt_low;
	}
	public BigDecimal getC11c_tot_tran_tamt_low() {
		return c11c_tot_tran_tamt_low;
	}
	public void setC11c_tot_tran_tamt_low(BigDecimal c11c_tot_tran_tamt_low) {
		this.c11c_tot_tran_tamt_low = c11c_tot_tran_tamt_low;
	}
	public BigDecimal getC12c_validation_tamt_low() {
		return c12c_validation_tamt_low;
	}
	public void setC12c_validation_tamt_low(BigDecimal c12c_validation_tamt_low) {
		this.c12c_validation_tamt_low = c12c_validation_tamt_low;
	}
	public BigDecimal getC1d_cash_dep_not_med() {
		return c1d_cash_dep_not_med;
	}
	public void setC1d_cash_dep_not_med(BigDecimal c1d_cash_dep_not_med) {
		this.c1d_cash_dep_not_med = c1d_cash_dep_not_med;
	}
	public BigDecimal getC2d_cash_wdl_not_med() {
		return c2d_cash_wdl_not_med;
	}
	public void setC2d_cash_wdl_not_med(BigDecimal c2d_cash_wdl_not_med) {
		this.c2d_cash_wdl_not_med = c2d_cash_wdl_not_med;
	}
	public BigDecimal getC3d_bom_inw_rem_not_med() {
		return c3d_bom_inw_rem_not_med;
	}
	public void setC3d_bom_inw_rem_not_med(BigDecimal c3d_bom_inw_rem_not_med) {
		this.c3d_bom_inw_rem_not_med = c3d_bom_inw_rem_not_med;
	}
	public BigDecimal getC4d_bom_out_rem_not_med() {
		return c4d_bom_out_rem_not_med;
	}
	public void setC4d_bom_out_rem_not_med(BigDecimal c4d_bom_out_rem_not_med) {
		this.c4d_bom_out_rem_not_med = c4d_bom_out_rem_not_med;
	}
	public BigDecimal getC5d_chq_inw_tran_not_med() {
		return c5d_chq_inw_tran_not_med;
	}
	public void setC5d_chq_inw_tran_not_med(BigDecimal c5d_chq_inw_tran_not_med) {
		this.c5d_chq_inw_tran_not_med = c5d_chq_inw_tran_not_med;
	}
	public BigDecimal getC6d_chq_out_tran_not_med() {
		return c6d_chq_out_tran_not_med;
	}
	public void setC6d_chq_out_tran_not_med(BigDecimal c6d_chq_out_tran_not_med) {
		this.c6d_chq_out_tran_not_med = c6d_chq_out_tran_not_med;
	}
	public BigDecimal getC7d_aotr_dr_tran_not_med() {
		return c7d_aotr_dr_tran_not_med;
	}
	public void setC7d_aotr_dr_tran_not_med(BigDecimal c7d_aotr_dr_tran_not_med) {
		this.c7d_aotr_dr_tran_not_med = c7d_aotr_dr_tran_not_med;
	}
	public BigDecimal getC8d_aotr_cr_tran_not_med() {
		return c8d_aotr_cr_tran_not_med;
	}
	public void setC8d_aotr_cr_tran_not_med(BigDecimal c8d_aotr_cr_tran_not_med) {
		this.c8d_aotr_cr_tran_not_med = c8d_aotr_cr_tran_not_med;
	}
	public BigDecimal getC9d_aotr_inw_tran_not_med() {
		return c9d_aotr_inw_tran_not_med;
	}
	public void setC9d_aotr_inw_tran_not_med(BigDecimal c9d_aotr_inw_tran_not_med) {
		this.c9d_aotr_inw_tran_not_med = c9d_aotr_inw_tran_not_med;
	}
	public BigDecimal getC10d_aotr_out_tran_not_med() {
		return c10d_aotr_out_tran_not_med;
	}
	public void setC10d_aotr_out_tran_not_med(BigDecimal c10d_aotr_out_tran_not_med) {
		this.c10d_aotr_out_tran_not_med = c10d_aotr_out_tran_not_med;
	}
	public BigDecimal getC11d_tot_tran_not_med() {
		return c11d_tot_tran_not_med;
	}
	public void setC11d_tot_tran_not_med(BigDecimal c11d_tot_tran_not_med) {
		this.c11d_tot_tran_not_med = c11d_tot_tran_not_med;
	}
	public BigDecimal getC12d_validation_not_med() {
		return c12d_validation_not_med;
	}
	public void setC12d_validation_not_med(BigDecimal c12d_validation_not_med) {
		this.c12d_validation_not_med = c12d_validation_not_med;
	}
	public BigDecimal getC1e_cash_dep_tamt_med() {
		return c1e_cash_dep_tamt_med;
	}
	public void setC1e_cash_dep_tamt_med(BigDecimal c1e_cash_dep_tamt_med) {
		this.c1e_cash_dep_tamt_med = c1e_cash_dep_tamt_med;
	}
	public BigDecimal getC2e_cash_wdl_tamt_med() {
		return c2e_cash_wdl_tamt_med;
	}
	public void setC2e_cash_wdl_tamt_med(BigDecimal c2e_cash_wdl_tamt_med) {
		this.c2e_cash_wdl_tamt_med = c2e_cash_wdl_tamt_med;
	}
	public BigDecimal getC3e_com_inw_rem_tamt_med() {
		return c3e_com_inw_rem_tamt_med;
	}
	public void setC3e_com_inw_rem_tamt_med(BigDecimal c3e_com_inw_rem_tamt_med) {
		this.c3e_com_inw_rem_tamt_med = c3e_com_inw_rem_tamt_med;
	}
	public BigDecimal getC4e_com_out_rem_tamt_med() {
		return c4e_com_out_rem_tamt_med;
	}
	public void setC4e_com_out_rem_tamt_med(BigDecimal c4e_com_out_rem_tamt_med) {
		this.c4e_com_out_rem_tamt_med = c4e_com_out_rem_tamt_med;
	}
	public BigDecimal getC5e_chq_inw_tran_tamt_med() {
		return c5e_chq_inw_tran_tamt_med;
	}
	public void setC5e_chq_inw_tran_tamt_med(BigDecimal c5e_chq_inw_tran_tamt_med) {
		this.c5e_chq_inw_tran_tamt_med = c5e_chq_inw_tran_tamt_med;
	}
	public BigDecimal getC6e_chq_out_tran_tamt_med() {
		return c6e_chq_out_tran_tamt_med;
	}
	public void setC6e_chq_out_tran_tamt_med(BigDecimal c6e_chq_out_tran_tamt_med) {
		this.c6e_chq_out_tran_tamt_med = c6e_chq_out_tran_tamt_med;
	}
	public BigDecimal getC7e_aotr_dr_tran_tamt_med() {
		return c7e_aotr_dr_tran_tamt_med;
	}
	public void setC7e_aotr_dr_tran_tamt_med(BigDecimal c7e_aotr_dr_tran_tamt_med) {
		this.c7e_aotr_dr_tran_tamt_med = c7e_aotr_dr_tran_tamt_med;
	}
	public BigDecimal getC8e_aotr_cr_tran_tamt_med() {
		return c8e_aotr_cr_tran_tamt_med;
	}
	public void setC8e_aotr_cr_tran_tamt_med(BigDecimal c8e_aotr_cr_tran_tamt_med) {
		this.c8e_aotr_cr_tran_tamt_med = c8e_aotr_cr_tran_tamt_med;
	}
	public BigDecimal getC9e_aotr_inw_tran_tamt_med() {
		return c9e_aotr_inw_tran_tamt_med;
	}
	public void setC9e_aotr_inw_tran_tamt_med(BigDecimal c9e_aotr_inw_tran_tamt_med) {
		this.c9e_aotr_inw_tran_tamt_med = c9e_aotr_inw_tran_tamt_med;
	}
	public BigDecimal getC10e_aotr_out_tran_tamt_med() {
		return c10e_aotr_out_tran_tamt_med;
	}
	public void setC10e_aotr_out_tran_tamt_med(BigDecimal c10e_aotr_out_tran_tamt_med) {
		this.c10e_aotr_out_tran_tamt_med = c10e_aotr_out_tran_tamt_med;
	}
	public BigDecimal getC11e_tot_tran_tamt_med() {
		return c11e_tot_tran_tamt_med;
	}
	public void setC11e_tot_tran_tamt_med(BigDecimal c11e_tot_tran_tamt_med) {
		this.c11e_tot_tran_tamt_med = c11e_tot_tran_tamt_med;
	}
	public BigDecimal getC12e_validation_tamt_med() {
		return c12e_validation_tamt_med;
	}
	public void setC12e_validation_tamt_med(BigDecimal c12e_validation_tamt_med) {
		this.c12e_validation_tamt_med = c12e_validation_tamt_med;
	}
	public BigDecimal getC1f_cash_dep_not_hig() {
		return c1f_cash_dep_not_hig;
	}
	public void setC1f_cash_dep_not_hig(BigDecimal c1f_cash_dep_not_hig) {
		this.c1f_cash_dep_not_hig = c1f_cash_dep_not_hig;
	}
	public BigDecimal getC2f_cash_wdl_not_hig() {
		return c2f_cash_wdl_not_hig;
	}
	public void setC2f_cash_wdl_not_hig(BigDecimal c2f_cash_wdl_not_hig) {
		this.c2f_cash_wdl_not_hig = c2f_cash_wdl_not_hig;
	}
	public BigDecimal getC3f_bom_inw_rem_not_hig() {
		return c3f_bom_inw_rem_not_hig;
	}
	public void setC3f_bom_inw_rem_not_hig(BigDecimal c3f_bom_inw_rem_not_hig) {
		this.c3f_bom_inw_rem_not_hig = c3f_bom_inw_rem_not_hig;
	}
	public BigDecimal getC4f_bom_out_rem_not_hig() {
		return c4f_bom_out_rem_not_hig;
	}
	public void setC4f_bom_out_rem_not_hig(BigDecimal c4f_bom_out_rem_not_hig) {
		this.c4f_bom_out_rem_not_hig = c4f_bom_out_rem_not_hig;
	}
	public BigDecimal getC5f_chq_inw_tran_not_hig() {
		return c5f_chq_inw_tran_not_hig;
	}
	public void setC5f_chq_inw_tran_not_hig(BigDecimal c5f_chq_inw_tran_not_hig) {
		this.c5f_chq_inw_tran_not_hig = c5f_chq_inw_tran_not_hig;
	}
	public BigDecimal getC6f_chq_out_tran_not_hig() {
		return c6f_chq_out_tran_not_hig;
	}
	public void setC6f_chq_out_tran_not_hig(BigDecimal c6f_chq_out_tran_not_hig) {
		this.c6f_chq_out_tran_not_hig = c6f_chq_out_tran_not_hig;
	}
	public BigDecimal getC7f_aotr_dr_tran_not_hig() {
		return c7f_aotr_dr_tran_not_hig;
	}
	public void setC7f_aotr_dr_tran_not_hig(BigDecimal c7f_aotr_dr_tran_not_hig) {
		this.c7f_aotr_dr_tran_not_hig = c7f_aotr_dr_tran_not_hig;
	}
	public BigDecimal getC8f_aotr_cr_tran_not_hig() {
		return c8f_aotr_cr_tran_not_hig;
	}
	public void setC8f_aotr_cr_tran_not_hig(BigDecimal c8f_aotr_cr_tran_not_hig) {
		this.c8f_aotr_cr_tran_not_hig = c8f_aotr_cr_tran_not_hig;
	}
	public BigDecimal getC9f_aotr_inw_tran_not_hig() {
		return c9f_aotr_inw_tran_not_hig;
	}
	public void setC9f_aotr_inw_tran_not_hig(BigDecimal c9f_aotr_inw_tran_not_hig) {
		this.c9f_aotr_inw_tran_not_hig = c9f_aotr_inw_tran_not_hig;
	}
	public BigDecimal getC10f_aotr_out_tran_not_hig() {
		return c10f_aotr_out_tran_not_hig;
	}
	public void setC10f_aotr_out_tran_not_hig(BigDecimal c10f_aotr_out_tran_not_hig) {
		this.c10f_aotr_out_tran_not_hig = c10f_aotr_out_tran_not_hig;
	}
	public BigDecimal getC11f_tot_tran_not_hig() {
		return c11f_tot_tran_not_hig;
	}
	public void setC11f_tot_tran_not_hig(BigDecimal c11f_tot_tran_not_hig) {
		this.c11f_tot_tran_not_hig = c11f_tot_tran_not_hig;
	}
	public BigDecimal getC12f_validation_not_hig() {
		return c12f_validation_not_hig;
	}
	public void setC12f_validation_not_hig(BigDecimal c12f_validation_not_hig) {
		this.c12f_validation_not_hig = c12f_validation_not_hig;
	}
	public BigDecimal getC1g_cash_dep_tamt_hig() {
		return c1g_cash_dep_tamt_hig;
	}
	public void setC1g_cash_dep_tamt_hig(BigDecimal c1g_cash_dep_tamt_hig) {
		this.c1g_cash_dep_tamt_hig = c1g_cash_dep_tamt_hig;
	}
	public BigDecimal getC2g_cash_wdl_tamt_hig() {
		return c2g_cash_wdl_tamt_hig;
	}
	public void setC2g_cash_wdl_tamt_hig(BigDecimal c2g_cash_wdl_tamt_hig) {
		this.c2g_cash_wdl_tamt_hig = c2g_cash_wdl_tamt_hig;
	}
	public BigDecimal getC3g_com_inw_rem_tamt_hig() {
		return c3g_com_inw_rem_tamt_hig;
	}
	public void setC3g_com_inw_rem_tamt_hig(BigDecimal c3g_com_inw_rem_tamt_hig) {
		this.c3g_com_inw_rem_tamt_hig = c3g_com_inw_rem_tamt_hig;
	}
	public BigDecimal getC4g_com_out_rem_tamt_hig() {
		return c4g_com_out_rem_tamt_hig;
	}
	public void setC4g_com_out_rem_tamt_hig(BigDecimal c4g_com_out_rem_tamt_hig) {
		this.c4g_com_out_rem_tamt_hig = c4g_com_out_rem_tamt_hig;
	}
	public BigDecimal getC5g_chq_inw_tran_tamt_hig() {
		return c5g_chq_inw_tran_tamt_hig;
	}
	public void setC5g_chq_inw_tran_tamt_hig(BigDecimal c5g_chq_inw_tran_tamt_hig) {
		this.c5g_chq_inw_tran_tamt_hig = c5g_chq_inw_tran_tamt_hig;
	}
	public BigDecimal getC6g_chq_out_tran_tamt_hig() {
		return c6g_chq_out_tran_tamt_hig;
	}
	public void setC6g_chq_out_tran_tamt_hig(BigDecimal c6g_chq_out_tran_tamt_hig) {
		this.c6g_chq_out_tran_tamt_hig = c6g_chq_out_tran_tamt_hig;
	}
	public BigDecimal getC7g_aotr_dr_tran_tamt_hig() {
		return c7g_aotr_dr_tran_tamt_hig;
	}
	public void setC7g_aotr_dr_tran_tamt_hig(BigDecimal c7g_aotr_dr_tran_tamt_hig) {
		this.c7g_aotr_dr_tran_tamt_hig = c7g_aotr_dr_tran_tamt_hig;
	}
	public BigDecimal getC8g_aotr_cr_tran_tamt_hig() {
		return c8g_aotr_cr_tran_tamt_hig;
	}
	public void setC8g_aotr_cr_tran_tamt_hig(BigDecimal c8g_aotr_cr_tran_tamt_hig) {
		this.c8g_aotr_cr_tran_tamt_hig = c8g_aotr_cr_tran_tamt_hig;
	}
	public BigDecimal getC9g_aotr_inw_tran_tamt_hig() {
		return c9g_aotr_inw_tran_tamt_hig;
	}
	public void setC9g_aotr_inw_tran_tamt_hig(BigDecimal c9g_aotr_inw_tran_tamt_hig) {
		this.c9g_aotr_inw_tran_tamt_hig = c9g_aotr_inw_tran_tamt_hig;
	}
	public BigDecimal getC10g_aotr_out_tran_tamt_hig() {
		return c10g_aotr_out_tran_tamt_hig;
	}
	public void setC10g_aotr_out_tran_tamt_hig(BigDecimal c10g_aotr_out_tran_tamt_hig) {
		this.c10g_aotr_out_tran_tamt_hig = c10g_aotr_out_tran_tamt_hig;
	}
	public BigDecimal getC11g_tot_tran_tamt_hig() {
		return c11g_tot_tran_tamt_hig;
	}
	public void setC11g_tot_tran_tamt_hig(BigDecimal c11g_tot_tran_tamt_hig) {
		this.c11g_tot_tran_tamt_hig = c11g_tot_tran_tamt_hig;
	}
	public BigDecimal getC12g_validation_tamt_hig() {
		return c12g_validation_tamt_hig;
	}
	public void setC12g_validation_tamt_hig(BigDecimal c12g_validation_tamt_hig) {
		this.c12g_validation_tamt_hig = c12g_validation_tamt_hig;
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
	public T8Report(String d1a_cash_dep, String d2a_cash_wdl, String d3a_dom_inw_rem, String d4a_dom_out_rem,
			String d5a_chq_inw_tran, String d6a_chq_out_tran, String d7a_aotr_dr_tran, String d8a_aotr_cr_tran,
			String d9a_aotr_inw_tran, String d10a_aotr_out_tran, String d11a_tot_tran, String d12a_val,
			BigDecimal p1b_cash_dep_not_low, BigDecimal p2b_cash_wdl_not_low, BigDecimal p3b_bom_inw_rem_not_low,
			BigDecimal p4b_bom_out_rem_not_low, BigDecimal p5b_chq_inw_tran_not_low,
			BigDecimal p6b_chq_out_tran_not_low, BigDecimal p7b_aotr_dr_tran_not_low,
			BigDecimal p8b_aotr_cr_tran_not_low, BigDecimal p9b_aotr_inw_tran_not_low,
			BigDecimal p10b_aotr_out_tran_not_low, BigDecimal p11b_tot_tran_not_low, BigDecimal p12b_validation_not_low,
			BigDecimal p1c_cash_dep_tamt_low, BigDecimal p2c_cash_wdl_tamt_low, BigDecimal p3c_com_inw_rem_tamt_low,
			BigDecimal p4c_com_out_rem_tamt_low, BigDecimal p5c_chq_inw_tran_tamt_low,
			BigDecimal p6c_chq_out_tran_tamt_low, BigDecimal p7c_aotr_dr_tran_tamt_low,
			BigDecimal p8c_aotr_cr_tran_tamt_low, BigDecimal p9c_aotr_inw_tran_tamt_low,
			BigDecimal p10c_aotr_out_tran_tamt_low, BigDecimal p11c_tot_tran_tamt_low,
			BigDecimal p12c_validation_tamt_low, BigDecimal p1d_cash_dep_not_med, BigDecimal p2d_cash_wdl_not_med,
			BigDecimal p3d_bom_inw_rem_not_med, BigDecimal p4d_bom_out_rem_not_med, BigDecimal p5d_chq_inw_tran_not_med,
			BigDecimal p6d_chq_out_tran_not_med, BigDecimal p7d_aotr_dr_tran_not_med,
			BigDecimal p8d_aotr_cr_tran_not_med, BigDecimal p9d_aotr_inw_tran_not_med,
			BigDecimal p10d_aotr_out_tran_not_med, BigDecimal p11d_tot_tran_not_med, BigDecimal p12d_validation_not_med,
			BigDecimal p1e_cash_dep_tamt_med, BigDecimal p2e_cash_wdl_tamt_med, BigDecimal p3e_com_inw_rem_tamt_med,
			BigDecimal p4e_com_out_rem_tamt_med, BigDecimal p5e_chq_inw_tran_tamt_med,
			BigDecimal p6e_chq_out_tran_tamt_med, BigDecimal p7e_aotr_dr_tran_tamt_med,
			BigDecimal p8e_aotr_cr_tran_tamt_med, BigDecimal p9e_aotr_inw_tran_tamt_med,
			BigDecimal p10e_aotr_out_tran_tamt_med, BigDecimal p11e_tot_tran_tamt_med,
			BigDecimal p12e_validation_tamt_med, BigDecimal p1f_cash_dep_not_hig, BigDecimal p2f_cash_wdl_not_hig,
			BigDecimal p3f_bom_inw_rem_not_hig, BigDecimal p4f_bom_out_rem_not_hig, BigDecimal p5f_chq_inw_tran_not_hig,
			BigDecimal p6f_chq_out_tran_not_hig, BigDecimal p7f_aotr_dr_tran_not_hig,
			BigDecimal p8f_aotr_cr_tran_not_hig, BigDecimal p9f_aotr_inw_tran_not_hig,
			BigDecimal p10f_aotr_out_tran_not_hig, BigDecimal p11f_tot_tran_not_hig, BigDecimal p12f_validation_not_hig,
			BigDecimal p1g_cash_dep_tamt_hig, BigDecimal p2g_cash_wdl_tamt_hig, BigDecimal p3g_com_inw_rem_tamt_hig,
			BigDecimal p4g_com_out_rem_tamt_hig, BigDecimal p5g_chq_inw_tran_tamt_hig,
			BigDecimal p6g_chq_out_tran_tamt_hig, BigDecimal p7g_aotr_dr_tran_tamt_hig,
			BigDecimal p8g_aotr_cr_tran_tamt_hig, BigDecimal p9g_aotr_inw_tran_tamt_hig,
			BigDecimal p10g_aotr_out_tran_tamt_hig, BigDecimal p11g_tot_tran_tamt_hig,
			BigDecimal p12g_validation_tamt_hig, BigDecimal c1b_cash_dep_not_low, BigDecimal c2b_cash_wdl_not_low,
			BigDecimal c3b_bom_inw_rem_not_low, BigDecimal c4b_bom_out_rem_not_low, BigDecimal c5b_chq_inw_tran_not_low,
			BigDecimal c6b_chq_out_tran_not_low, BigDecimal c7b_aotr_dr_tran_not_low,
			BigDecimal c8b_aotr_cr_tran_not_low, BigDecimal c9b_aotr_inw_tran_not_low,
			BigDecimal c10b_aotr_out_tran_not_low, BigDecimal c11b_tot_tran_not_low, BigDecimal c12b_validation_not_low,
			BigDecimal c1c_cash_dep_tamt_low, BigDecimal c2c_cash_wdl_tamt_low, BigDecimal c3c_com_inw_rem_tamt_low,
			BigDecimal c4c_com_out_rem_tamt_low, BigDecimal c5c_chq_inw_tran_tamt_low,
			BigDecimal c6c_chq_out_tran_tamt_low, BigDecimal c7c_aotr_dr_tran_tamt_low,
			BigDecimal c8c_aotr_cr_tran_tamt_low, BigDecimal c9c_aotr_inw_tran_tamt_low,
			BigDecimal c10c_aotr_out_tran_tamt_low, BigDecimal c11c_tot_tran_tamt_low,
			BigDecimal c12c_validation_tamt_low, BigDecimal c1d_cash_dep_not_med, BigDecimal c2d_cash_wdl_not_med,
			BigDecimal c3d_bom_inw_rem_not_med, BigDecimal c4d_bom_out_rem_not_med, BigDecimal c5d_chq_inw_tran_not_med,
			BigDecimal c6d_chq_out_tran_not_med, BigDecimal c7d_aotr_dr_tran_not_med,
			BigDecimal c8d_aotr_cr_tran_not_med, BigDecimal c9d_aotr_inw_tran_not_med,
			BigDecimal c10d_aotr_out_tran_not_med, BigDecimal c11d_tot_tran_not_med, BigDecimal c12d_validation_not_med,
			BigDecimal c1e_cash_dep_tamt_med, BigDecimal c2e_cash_wdl_tamt_med, BigDecimal c3e_com_inw_rem_tamt_med,
			BigDecimal c4e_com_out_rem_tamt_med, BigDecimal c5e_chq_inw_tran_tamt_med,
			BigDecimal c6e_chq_out_tran_tamt_med, BigDecimal c7e_aotr_dr_tran_tamt_med,
			BigDecimal c8e_aotr_cr_tran_tamt_med, BigDecimal c9e_aotr_inw_tran_tamt_med,
			BigDecimal c10e_aotr_out_tran_tamt_med, BigDecimal c11e_tot_tran_tamt_med,
			BigDecimal c12e_validation_tamt_med, BigDecimal c1f_cash_dep_not_hig, BigDecimal c2f_cash_wdl_not_hig,
			BigDecimal c3f_bom_inw_rem_not_hig, BigDecimal c4f_bom_out_rem_not_hig, BigDecimal c5f_chq_inw_tran_not_hig,
			BigDecimal c6f_chq_out_tran_not_hig, BigDecimal c7f_aotr_dr_tran_not_hig,
			BigDecimal c8f_aotr_cr_tran_not_hig, BigDecimal c9f_aotr_inw_tran_not_hig,
			BigDecimal c10f_aotr_out_tran_not_hig, BigDecimal c11f_tot_tran_not_hig, BigDecimal c12f_validation_not_hig,
			BigDecimal c1g_cash_dep_tamt_hig, BigDecimal c2g_cash_wdl_tamt_hig, BigDecimal c3g_com_inw_rem_tamt_hig,
			BigDecimal c4g_com_out_rem_tamt_hig, BigDecimal c5g_chq_inw_tran_tamt_hig,
			BigDecimal c6g_chq_out_tran_tamt_hig, BigDecimal c7g_aotr_dr_tran_tamt_hig,
			BigDecimal c8g_aotr_cr_tran_tamt_hig, BigDecimal c9g_aotr_inw_tran_tamt_hig,
			BigDecimal c10g_aotr_out_tran_tamt_hig, BigDecimal c11g_tot_tran_tamt_hig,
			BigDecimal c12g_validation_tamt_hig, String report_code, String report_name, Date report_date,
			Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq,
			String nil_report_flg, String arch_flg) {
		super();
		this.d1a_cash_dep = d1a_cash_dep;
		this.d2a_cash_wdl = d2a_cash_wdl;
		this.d3a_dom_inw_rem = d3a_dom_inw_rem;
		this.d4a_dom_out_rem = d4a_dom_out_rem;
		this.d5a_chq_inw_tran = d5a_chq_inw_tran;
		this.d6a_chq_out_tran = d6a_chq_out_tran;
		this.d7a_aotr_dr_tran = d7a_aotr_dr_tran;
		this.d8a_aotr_cr_tran = d8a_aotr_cr_tran;
		this.d9a_aotr_inw_tran = d9a_aotr_inw_tran;
		this.d10a_aotr_out_tran = d10a_aotr_out_tran;
		this.d11a_tot_tran = d11a_tot_tran;
		this.d12a_val = d12a_val;
		this.p1b_cash_dep_not_low = p1b_cash_dep_not_low;
		this.p2b_cash_wdl_not_low = p2b_cash_wdl_not_low;
		this.p3b_bom_inw_rem_not_low = p3b_bom_inw_rem_not_low;
		this.p4b_bom_out_rem_not_low = p4b_bom_out_rem_not_low;
		this.p5b_chq_inw_tran_not_low = p5b_chq_inw_tran_not_low;
		this.p6b_chq_out_tran_not_low = p6b_chq_out_tran_not_low;
		this.p7b_aotr_dr_tran_not_low = p7b_aotr_dr_tran_not_low;
		this.p8b_aotr_cr_tran_not_low = p8b_aotr_cr_tran_not_low;
		this.p9b_aotr_inw_tran_not_low = p9b_aotr_inw_tran_not_low;
		this.p10b_aotr_out_tran_not_low = p10b_aotr_out_tran_not_low;
		this.p11b_tot_tran_not_low = p11b_tot_tran_not_low;
		this.p12b_validation_not_low = p12b_validation_not_low;
		this.p1c_cash_dep_tamt_low = p1c_cash_dep_tamt_low;
		this.p2c_cash_wdl_tamt_low = p2c_cash_wdl_tamt_low;
		this.p3c_com_inw_rem_tamt_low = p3c_com_inw_rem_tamt_low;
		this.p4c_com_out_rem_tamt_low = p4c_com_out_rem_tamt_low;
		this.p5c_chq_inw_tran_tamt_low = p5c_chq_inw_tran_tamt_low;
		this.p6c_chq_out_tran_tamt_low = p6c_chq_out_tran_tamt_low;
		this.p7c_aotr_dr_tran_tamt_low = p7c_aotr_dr_tran_tamt_low;
		this.p8c_aotr_cr_tran_tamt_low = p8c_aotr_cr_tran_tamt_low;
		this.p9c_aotr_inw_tran_tamt_low = p9c_aotr_inw_tran_tamt_low;
		this.p10c_aotr_out_tran_tamt_low = p10c_aotr_out_tran_tamt_low;
		this.p11c_tot_tran_tamt_low = p11c_tot_tran_tamt_low;
		this.p12c_validation_tamt_low = p12c_validation_tamt_low;
		this.p1d_cash_dep_not_med = p1d_cash_dep_not_med;
		this.p2d_cash_wdl_not_med = p2d_cash_wdl_not_med;
		this.p3d_bom_inw_rem_not_med = p3d_bom_inw_rem_not_med;
		this.p4d_bom_out_rem_not_med = p4d_bom_out_rem_not_med;
		this.p5d_chq_inw_tran_not_med = p5d_chq_inw_tran_not_med;
		this.p6d_chq_out_tran_not_med = p6d_chq_out_tran_not_med;
		this.p7d_aotr_dr_tran_not_med = p7d_aotr_dr_tran_not_med;
		this.p8d_aotr_cr_tran_not_med = p8d_aotr_cr_tran_not_med;
		this.p9d_aotr_inw_tran_not_med = p9d_aotr_inw_tran_not_med;
		this.p10d_aotr_out_tran_not_med = p10d_aotr_out_tran_not_med;
		this.p11d_tot_tran_not_med = p11d_tot_tran_not_med;
		this.p12d_validation_not_med = p12d_validation_not_med;
		this.p1e_cash_dep_tamt_med = p1e_cash_dep_tamt_med;
		this.p2e_cash_wdl_tamt_med = p2e_cash_wdl_tamt_med;
		this.p3e_com_inw_rem_tamt_med = p3e_com_inw_rem_tamt_med;
		this.p4e_com_out_rem_tamt_med = p4e_com_out_rem_tamt_med;
		this.p5e_chq_inw_tran_tamt_med = p5e_chq_inw_tran_tamt_med;
		this.p6e_chq_out_tran_tamt_med = p6e_chq_out_tran_tamt_med;
		this.p7e_aotr_dr_tran_tamt_med = p7e_aotr_dr_tran_tamt_med;
		this.p8e_aotr_cr_tran_tamt_med = p8e_aotr_cr_tran_tamt_med;
		this.p9e_aotr_inw_tran_tamt_med = p9e_aotr_inw_tran_tamt_med;
		this.p10e_aotr_out_tran_tamt_med = p10e_aotr_out_tran_tamt_med;
		this.p11e_tot_tran_tamt_med = p11e_tot_tran_tamt_med;
		this.p12e_validation_tamt_med = p12e_validation_tamt_med;
		this.p1f_cash_dep_not_hig = p1f_cash_dep_not_hig;
		this.p2f_cash_wdl_not_hig = p2f_cash_wdl_not_hig;
		this.p3f_bom_inw_rem_not_hig = p3f_bom_inw_rem_not_hig;
		this.p4f_bom_out_rem_not_hig = p4f_bom_out_rem_not_hig;
		this.p5f_chq_inw_tran_not_hig = p5f_chq_inw_tran_not_hig;
		this.p6f_chq_out_tran_not_hig = p6f_chq_out_tran_not_hig;
		this.p7f_aotr_dr_tran_not_hig = p7f_aotr_dr_tran_not_hig;
		this.p8f_aotr_cr_tran_not_hig = p8f_aotr_cr_tran_not_hig;
		this.p9f_aotr_inw_tran_not_hig = p9f_aotr_inw_tran_not_hig;
		this.p10f_aotr_out_tran_not_hig = p10f_aotr_out_tran_not_hig;
		this.p11f_tot_tran_not_hig = p11f_tot_tran_not_hig;
		this.p12f_validation_not_hig = p12f_validation_not_hig;
		this.p1g_cash_dep_tamt_hig = p1g_cash_dep_tamt_hig;
		this.p2g_cash_wdl_tamt_hig = p2g_cash_wdl_tamt_hig;
		this.p3g_com_inw_rem_tamt_hig = p3g_com_inw_rem_tamt_hig;
		this.p4g_com_out_rem_tamt_hig = p4g_com_out_rem_tamt_hig;
		this.p5g_chq_inw_tran_tamt_hig = p5g_chq_inw_tran_tamt_hig;
		this.p6g_chq_out_tran_tamt_hig = p6g_chq_out_tran_tamt_hig;
		this.p7g_aotr_dr_tran_tamt_hig = p7g_aotr_dr_tran_tamt_hig;
		this.p8g_aotr_cr_tran_tamt_hig = p8g_aotr_cr_tran_tamt_hig;
		this.p9g_aotr_inw_tran_tamt_hig = p9g_aotr_inw_tran_tamt_hig;
		this.p10g_aotr_out_tran_tamt_hig = p10g_aotr_out_tran_tamt_hig;
		this.p11g_tot_tran_tamt_hig = p11g_tot_tran_tamt_hig;
		this.p12g_validation_tamt_hig = p12g_validation_tamt_hig;
		this.c1b_cash_dep_not_low = c1b_cash_dep_not_low;
		this.c2b_cash_wdl_not_low = c2b_cash_wdl_not_low;
		this.c3b_bom_inw_rem_not_low = c3b_bom_inw_rem_not_low;
		this.c4b_bom_out_rem_not_low = c4b_bom_out_rem_not_low;
		this.c5b_chq_inw_tran_not_low = c5b_chq_inw_tran_not_low;
		this.c6b_chq_out_tran_not_low = c6b_chq_out_tran_not_low;
		this.c7b_aotr_dr_tran_not_low = c7b_aotr_dr_tran_not_low;
		this.c8b_aotr_cr_tran_not_low = c8b_aotr_cr_tran_not_low;
		this.c9b_aotr_inw_tran_not_low = c9b_aotr_inw_tran_not_low;
		this.c10b_aotr_out_tran_not_low = c10b_aotr_out_tran_not_low;
		this.c11b_tot_tran_not_low = c11b_tot_tran_not_low;
		this.c12b_validation_not_low = c12b_validation_not_low;
		this.c1c_cash_dep_tamt_low = c1c_cash_dep_tamt_low;
		this.c2c_cash_wdl_tamt_low = c2c_cash_wdl_tamt_low;
		this.c3c_com_inw_rem_tamt_low = c3c_com_inw_rem_tamt_low;
		this.c4c_com_out_rem_tamt_low = c4c_com_out_rem_tamt_low;
		this.c5c_chq_inw_tran_tamt_low = c5c_chq_inw_tran_tamt_low;
		this.c6c_chq_out_tran_tamt_low = c6c_chq_out_tran_tamt_low;
		this.c7c_aotr_dr_tran_tamt_low = c7c_aotr_dr_tran_tamt_low;
		this.c8c_aotr_cr_tran_tamt_low = c8c_aotr_cr_tran_tamt_low;
		this.c9c_aotr_inw_tran_tamt_low = c9c_aotr_inw_tran_tamt_low;
		this.c10c_aotr_out_tran_tamt_low = c10c_aotr_out_tran_tamt_low;
		this.c11c_tot_tran_tamt_low = c11c_tot_tran_tamt_low;
		this.c12c_validation_tamt_low = c12c_validation_tamt_low;
		this.c1d_cash_dep_not_med = c1d_cash_dep_not_med;
		this.c2d_cash_wdl_not_med = c2d_cash_wdl_not_med;
		this.c3d_bom_inw_rem_not_med = c3d_bom_inw_rem_not_med;
		this.c4d_bom_out_rem_not_med = c4d_bom_out_rem_not_med;
		this.c5d_chq_inw_tran_not_med = c5d_chq_inw_tran_not_med;
		this.c6d_chq_out_tran_not_med = c6d_chq_out_tran_not_med;
		this.c7d_aotr_dr_tran_not_med = c7d_aotr_dr_tran_not_med;
		this.c8d_aotr_cr_tran_not_med = c8d_aotr_cr_tran_not_med;
		this.c9d_aotr_inw_tran_not_med = c9d_aotr_inw_tran_not_med;
		this.c10d_aotr_out_tran_not_med = c10d_aotr_out_tran_not_med;
		this.c11d_tot_tran_not_med = c11d_tot_tran_not_med;
		this.c12d_validation_not_med = c12d_validation_not_med;
		this.c1e_cash_dep_tamt_med = c1e_cash_dep_tamt_med;
		this.c2e_cash_wdl_tamt_med = c2e_cash_wdl_tamt_med;
		this.c3e_com_inw_rem_tamt_med = c3e_com_inw_rem_tamt_med;
		this.c4e_com_out_rem_tamt_med = c4e_com_out_rem_tamt_med;
		this.c5e_chq_inw_tran_tamt_med = c5e_chq_inw_tran_tamt_med;
		this.c6e_chq_out_tran_tamt_med = c6e_chq_out_tran_tamt_med;
		this.c7e_aotr_dr_tran_tamt_med = c7e_aotr_dr_tran_tamt_med;
		this.c8e_aotr_cr_tran_tamt_med = c8e_aotr_cr_tran_tamt_med;
		this.c9e_aotr_inw_tran_tamt_med = c9e_aotr_inw_tran_tamt_med;
		this.c10e_aotr_out_tran_tamt_med = c10e_aotr_out_tran_tamt_med;
		this.c11e_tot_tran_tamt_med = c11e_tot_tran_tamt_med;
		this.c12e_validation_tamt_med = c12e_validation_tamt_med;
		this.c1f_cash_dep_not_hig = c1f_cash_dep_not_hig;
		this.c2f_cash_wdl_not_hig = c2f_cash_wdl_not_hig;
		this.c3f_bom_inw_rem_not_hig = c3f_bom_inw_rem_not_hig;
		this.c4f_bom_out_rem_not_hig = c4f_bom_out_rem_not_hig;
		this.c5f_chq_inw_tran_not_hig = c5f_chq_inw_tran_not_hig;
		this.c6f_chq_out_tran_not_hig = c6f_chq_out_tran_not_hig;
		this.c7f_aotr_dr_tran_not_hig = c7f_aotr_dr_tran_not_hig;
		this.c8f_aotr_cr_tran_not_hig = c8f_aotr_cr_tran_not_hig;
		this.c9f_aotr_inw_tran_not_hig = c9f_aotr_inw_tran_not_hig;
		this.c10f_aotr_out_tran_not_hig = c10f_aotr_out_tran_not_hig;
		this.c11f_tot_tran_not_hig = c11f_tot_tran_not_hig;
		this.c12f_validation_not_hig = c12f_validation_not_hig;
		this.c1g_cash_dep_tamt_hig = c1g_cash_dep_tamt_hig;
		this.c2g_cash_wdl_tamt_hig = c2g_cash_wdl_tamt_hig;
		this.c3g_com_inw_rem_tamt_hig = c3g_com_inw_rem_tamt_hig;
		this.c4g_com_out_rem_tamt_hig = c4g_com_out_rem_tamt_hig;
		this.c5g_chq_inw_tran_tamt_hig = c5g_chq_inw_tran_tamt_hig;
		this.c6g_chq_out_tran_tamt_hig = c6g_chq_out_tran_tamt_hig;
		this.c7g_aotr_dr_tran_tamt_hig = c7g_aotr_dr_tran_tamt_hig;
		this.c8g_aotr_cr_tran_tamt_hig = c8g_aotr_cr_tran_tamt_hig;
		this.c9g_aotr_inw_tran_tamt_hig = c9g_aotr_inw_tran_tamt_hig;
		this.c10g_aotr_out_tran_tamt_hig = c10g_aotr_out_tran_tamt_hig;
		this.c11g_tot_tran_tamt_hig = c11g_tot_tran_tamt_hig;
		this.c12g_validation_tamt_hig = c12g_validation_tamt_hig;
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
	
	public T8Report() {}
	


}

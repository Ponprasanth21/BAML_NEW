package com.bornfire.entity.t18;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T18DIST_CHANNELS_TABLE")
public class T18Report {
	
	private String	d1a_agents;
	private String	d2a_cash;
	private String	d3a_elecronic_transfer;
	private String	d4a_credit_cards;
	private String	d5a_all_other_channels;
	private String	d6a_atm;
	private String	d7a_counter_trans;
	private String	d8a_clearing_trans;
	private String	d9a_00001_trans;
	private String	d10a_0002_trans;
	private String	d11a_total;
	private String	d12a_validation;
	private BigDecimal	c1e_agents_nof_cust;
	private BigDecimal	c2e_cash_nof_cust;
	private BigDecimal	c3e_elecronic_transfer_nof_cust;
	private BigDecimal	c4e_credit_carcs_nof_cust;
	private BigDecimal	c5e_all_other_channels_nof_cust;
	private BigDecimal	c6e_atm_nof_cust;
	private BigDecimal	c7e_counter_trans_nof_cust;
	private BigDecimal	c8e_clearing_trans_nof_cust;
	private BigDecimal	c9e_00001_trans_nof_cust;
	private BigDecimal	c10e_0002_trans_nof_cust;
	private BigDecimal	c11e_total_nof_cust;
	private BigDecimal	c12e_validation_nof_cust;
	private BigDecimal	c1f_agents_nof_trans;
	private BigDecimal	c2f_cash_nof_trans;
	private BigDecimal	c3f_elecronic_transfer_nof_trans;
	private BigDecimal	c4f_credit_cards_nof_trans;
	private BigDecimal	c5f_all_other_channels_nof_trans;
	private BigDecimal	c6f_atm_nof_trans;
	private BigDecimal	c7f_counter_trans_nof_trans;
	private BigDecimal	c8f_clearing_trans_nof_trans;
	private BigDecimal	c9f_00001_trans_nof_trans;
	private BigDecimal	c10f_0002_trans_nof_trans;
	private BigDecimal	c11f_total_nof_trans;
	private BigDecimal	c12f_validation_nof_trans;
	private BigDecimal	c1g_agents_val_trans;
	private BigDecimal	c2g_cash_val_trans;
	private BigDecimal	c3g_elecronic_transfer_val_trans;
	private BigDecimal	c4g_credit_cards_val_trans;
	private BigDecimal	c5g_all_other_channels_val_trans;
	private BigDecimal	c6g_atm_val_trans;
	private BigDecimal	c7g_counter_trans_val_trans;
	private BigDecimal	c8g_clearing_trans_val_trans;
	private BigDecimal	c9g_00001_trans_val_trans;
	private BigDecimal	c10g_0002_trans_val_trans;
	private BigDecimal	c11g_total_val_trans;
	private BigDecimal	c12g_validation_val_trans;
	private String	report_code;
	private String	report_name;
	@Id
	private Date	report_date;
	private Date	report_due_date;
	private Date	rep_submit_date;
	private Date	rep_period_from;
	private Date	rep_period_to;
	private String	rep_freq;
	private Character	nil_report_flg;
	private Character	arch_flg;

	
	
	
	
	public T18Report(String d1a_agents, String d2a_cash, String d3a_elecronic_transfer, String d4a_credit_cards,
			String d5a_all_other_channels, String d6a_atm, String d7a_counter_trans, String d8a_clearing_trans,
			String d9a_00001_trans, String d10a_0002_trans, String d11a_total, String d12a_validation,
			BigDecimal c1e_agents_nof_cust, BigDecimal c2e_cash_nof_cust, BigDecimal c3e_elecronic_transfer_nof_cust,
			BigDecimal c4e_credit_carcs_nof_cust, BigDecimal c5e_all_other_channels_nof_cust,
			BigDecimal c6e_atm_nof_cust, BigDecimal c7e_counter_trans_nof_cust, BigDecimal c8e_clearing_trans_nof_cust,
			BigDecimal c9e_00001_trans_nof_cust, BigDecimal c10e_0002_trans_nof_cust, BigDecimal c11e_total_nof_cust,
			BigDecimal c12e_validation_nof_cust, BigDecimal c1f_agents_nof_trans, BigDecimal c2f_cash_nof_trans,
			BigDecimal c3f_elecronic_transfer_nof_trans, BigDecimal c4f_credit_cards_nof_trans,
			BigDecimal c5f_all_other_channels_nof_trans, BigDecimal c6f_atm_nof_trans,
			BigDecimal c7f_counter_trans_nof_trans, BigDecimal c8f_clearing_trans_nof_trans,
			BigDecimal c9f_00001_trans_nof_trans, BigDecimal c10f_0002_trans_nof_trans, BigDecimal c11f_total_nof_trans,
			BigDecimal c12f_validation_nof_trans, BigDecimal c1g_agents_val_trans, BigDecimal c2g_cash_val_trans,
			BigDecimal c3g_elecronic_transfer_val_trans, BigDecimal c4g_credit_cards_val_trans,
			BigDecimal c5g_all_other_channels_val_trans, BigDecimal c6g_atm_val_trans,
			BigDecimal c7g_counter_trans_val_trans, BigDecimal c8g_clearing_trans_val_trans,
			BigDecimal c9g_00001_trans_val_trans, BigDecimal c10g_0002_trans_val_trans, BigDecimal c11g_total_val_trans,
			BigDecimal c12g_validation_val_trans, String report_code, String report_name, Date report_date,
			Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq,
			Character nil_report_flg, Character arch_flg) {
		this.d1a_agents = d1a_agents;
		this.d2a_cash = d2a_cash;
		this.d3a_elecronic_transfer = d3a_elecronic_transfer;
		this.d4a_credit_cards = d4a_credit_cards;
		this.d5a_all_other_channels = d5a_all_other_channels;
		this.d6a_atm = d6a_atm;
		this.d7a_counter_trans = d7a_counter_trans;
		this.d8a_clearing_trans = d8a_clearing_trans;
		this.d9a_00001_trans = d9a_00001_trans;
		this.d10a_0002_trans = d10a_0002_trans;
		this.d11a_total = d11a_total;
		this.d12a_validation = d12a_validation;
		this.c1e_agents_nof_cust = c1e_agents_nof_cust;
		this.c2e_cash_nof_cust = c2e_cash_nof_cust;
		this.c3e_elecronic_transfer_nof_cust = c3e_elecronic_transfer_nof_cust;
		this.c4e_credit_carcs_nof_cust = c4e_credit_carcs_nof_cust;
		this.c5e_all_other_channels_nof_cust = c5e_all_other_channels_nof_cust;
		this.c6e_atm_nof_cust = c6e_atm_nof_cust;
		this.c7e_counter_trans_nof_cust = c7e_counter_trans_nof_cust;
		this.c8e_clearing_trans_nof_cust = c8e_clearing_trans_nof_cust;
		this.c9e_00001_trans_nof_cust = c9e_00001_trans_nof_cust;
		this.c10e_0002_trans_nof_cust = c10e_0002_trans_nof_cust;
		this.c11e_total_nof_cust = c11e_total_nof_cust;
		this.c12e_validation_nof_cust = c12e_validation_nof_cust;
		this.c1f_agents_nof_trans = c1f_agents_nof_trans;
		this.c2f_cash_nof_trans = c2f_cash_nof_trans;
		this.c3f_elecronic_transfer_nof_trans = c3f_elecronic_transfer_nof_trans;
		this.c4f_credit_cards_nof_trans = c4f_credit_cards_nof_trans;
		this.c5f_all_other_channels_nof_trans = c5f_all_other_channels_nof_trans;
		this.c6f_atm_nof_trans = c6f_atm_nof_trans;
		this.c7f_counter_trans_nof_trans = c7f_counter_trans_nof_trans;
		this.c8f_clearing_trans_nof_trans = c8f_clearing_trans_nof_trans;
		this.c9f_00001_trans_nof_trans = c9f_00001_trans_nof_trans;
		this.c10f_0002_trans_nof_trans = c10f_0002_trans_nof_trans;
		this.c11f_total_nof_trans = c11f_total_nof_trans;
		this.c12f_validation_nof_trans = c12f_validation_nof_trans;
		this.c1g_agents_val_trans = c1g_agents_val_trans;
		this.c2g_cash_val_trans = c2g_cash_val_trans;
		this.c3g_elecronic_transfer_val_trans = c3g_elecronic_transfer_val_trans;
		this.c4g_credit_cards_val_trans = c4g_credit_cards_val_trans;
		this.c5g_all_other_channels_val_trans = c5g_all_other_channels_val_trans;
		this.c6g_atm_val_trans = c6g_atm_val_trans;
		this.c7g_counter_trans_val_trans = c7g_counter_trans_val_trans;
		this.c8g_clearing_trans_val_trans = c8g_clearing_trans_val_trans;
		this.c9g_00001_trans_val_trans = c9g_00001_trans_val_trans;
		this.c10g_0002_trans_val_trans = c10g_0002_trans_val_trans;
		this.c11g_total_val_trans = c11g_total_val_trans;
		this.c12g_validation_val_trans = c12g_validation_val_trans;
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





	public String getD1a_agents() {
		return d1a_agents;
	}





	public void setD1a_agents(String d1a_agents) {
		this.d1a_agents = d1a_agents;
	}





	public String getD2a_cash() {
		return d2a_cash;
	}





	public void setD2a_cash(String d2a_cash) {
		this.d2a_cash = d2a_cash;
	}





	public String getD3a_elecronic_transfer() {
		return d3a_elecronic_transfer;
	}





	public void setD3a_elecronic_transfer(String d3a_elecronic_transfer) {
		this.d3a_elecronic_transfer = d3a_elecronic_transfer;
	}





	public String getD4a_credit_cards() {
		return d4a_credit_cards;
	}





	public void setD4a_credit_cards(String d4a_credit_cards) {
		this.d4a_credit_cards = d4a_credit_cards;
	}





	public String getD5a_all_other_channels() {
		return d5a_all_other_channels;
	}





	public void setD5a_all_other_channels(String d5a_all_other_channels) {
		this.d5a_all_other_channels = d5a_all_other_channels;
	}





	public String getD6a_atm() {
		return d6a_atm;
	}





	public void setD6a_atm(String d6a_atm) {
		this.d6a_atm = d6a_atm;
	}





	public String getD7a_counter_trans() {
		return d7a_counter_trans;
	}





	public void setD7a_counter_trans(String d7a_counter_trans) {
		this.d7a_counter_trans = d7a_counter_trans;
	}





	public String getD8a_clearing_trans() {
		return d8a_clearing_trans;
	}





	public void setD8a_clearing_trans(String d8a_clearing_trans) {
		this.d8a_clearing_trans = d8a_clearing_trans;
	}





	public String getD9a_00001_trans() {
		return d9a_00001_trans;
	}





	public void setD9a_00001_trans(String d9a_00001_trans) {
		this.d9a_00001_trans = d9a_00001_trans;
	}





	public String getD10a_0002_trans() {
		return d10a_0002_trans;
	}





	public void setD10a_0002_trans(String d10a_0002_trans) {
		this.d10a_0002_trans = d10a_0002_trans;
	}





	public String getD11a_total() {
		return d11a_total;
	}





	public void setD11a_total(String d11a_total) {
		this.d11a_total = d11a_total;
	}





	public String getD12a_validation() {
		return d12a_validation;
	}





	public void setD12a_validation(String d12a_validation) {
		this.d12a_validation = d12a_validation;
	}





	public BigDecimal getC1e_agents_nof_cust() {
		return c1e_agents_nof_cust;
	}





	public void setC1e_agents_nof_cust(BigDecimal c1e_agents_nof_cust) {
		this.c1e_agents_nof_cust = c1e_agents_nof_cust;
	}





	public BigDecimal getC2e_cash_nof_cust() {
		return c2e_cash_nof_cust;
	}





	public void setC2e_cash_nof_cust(BigDecimal c2e_cash_nof_cust) {
		this.c2e_cash_nof_cust = c2e_cash_nof_cust;
	}





	public BigDecimal getC3e_elecronic_transfer_nof_cust() {
		return c3e_elecronic_transfer_nof_cust;
	}





	public void setC3e_elecronic_transfer_nof_cust(BigDecimal c3e_elecronic_transfer_nof_cust) {
		this.c3e_elecronic_transfer_nof_cust = c3e_elecronic_transfer_nof_cust;
	}





	public BigDecimal getC4e_credit_carcs_nof_cust() {
		return c4e_credit_carcs_nof_cust;
	}





	public void setC4e_credit_carcs_nof_cust(BigDecimal c4e_credit_carcs_nof_cust) {
		this.c4e_credit_carcs_nof_cust = c4e_credit_carcs_nof_cust;
	}





	public BigDecimal getC5e_all_other_channels_nof_cust() {
		return c5e_all_other_channels_nof_cust;
	}





	public void setC5e_all_other_channels_nof_cust(BigDecimal c5e_all_other_channels_nof_cust) {
		this.c5e_all_other_channels_nof_cust = c5e_all_other_channels_nof_cust;
	}





	public BigDecimal getC6e_atm_nof_cust() {
		return c6e_atm_nof_cust;
	}





	public void setC6e_atm_nof_cust(BigDecimal c6e_atm_nof_cust) {
		this.c6e_atm_nof_cust = c6e_atm_nof_cust;
	}





	public BigDecimal getC7e_counter_trans_nof_cust() {
		return c7e_counter_trans_nof_cust;
	}





	public void setC7e_counter_trans_nof_cust(BigDecimal c7e_counter_trans_nof_cust) {
		this.c7e_counter_trans_nof_cust = c7e_counter_trans_nof_cust;
	}





	public BigDecimal getC8e_clearing_trans_nof_cust() {
		return c8e_clearing_trans_nof_cust;
	}





	public void setC8e_clearing_trans_nof_cust(BigDecimal c8e_clearing_trans_nof_cust) {
		this.c8e_clearing_trans_nof_cust = c8e_clearing_trans_nof_cust;
	}





	public BigDecimal getC9e_00001_trans_nof_cust() {
		return c9e_00001_trans_nof_cust;
	}





	public void setC9e_00001_trans_nof_cust(BigDecimal c9e_00001_trans_nof_cust) {
		this.c9e_00001_trans_nof_cust = c9e_00001_trans_nof_cust;
	}





	public BigDecimal getC10e_0002_trans_nof_cust() {
		return c10e_0002_trans_nof_cust;
	}





	public void setC10e_0002_trans_nof_cust(BigDecimal c10e_0002_trans_nof_cust) {
		this.c10e_0002_trans_nof_cust = c10e_0002_trans_nof_cust;
	}





	public BigDecimal getC11e_total_nof_cust() {
		return c11e_total_nof_cust;
	}





	public void setC11e_total_nof_cust(BigDecimal c11e_total_nof_cust) {
		this.c11e_total_nof_cust = c11e_total_nof_cust;
	}





	public BigDecimal getC12e_validation_nof_cust() {
		return c12e_validation_nof_cust;
	}





	public void setC12e_validation_nof_cust(BigDecimal c12e_validation_nof_cust) {
		this.c12e_validation_nof_cust = c12e_validation_nof_cust;
	}





	public BigDecimal getC1f_agents_nof_trans() {
		return c1f_agents_nof_trans;
	}





	public void setC1f_agents_nof_trans(BigDecimal c1f_agents_nof_trans) {
		this.c1f_agents_nof_trans = c1f_agents_nof_trans;
	}





	public BigDecimal getC2f_cash_nof_trans() {
		return c2f_cash_nof_trans;
	}





	public void setC2f_cash_nof_trans(BigDecimal c2f_cash_nof_trans) {
		this.c2f_cash_nof_trans = c2f_cash_nof_trans;
	}





	public BigDecimal getC3f_elecronic_transfer_nof_trans() {
		return c3f_elecronic_transfer_nof_trans;
	}





	public void setC3f_elecronic_transfer_nof_trans(BigDecimal c3f_elecronic_transfer_nof_trans) {
		this.c3f_elecronic_transfer_nof_trans = c3f_elecronic_transfer_nof_trans;
	}





	public BigDecimal getC4f_credit_cards_nof_trans() {
		return c4f_credit_cards_nof_trans;
	}





	public void setC4f_credit_cards_nof_trans(BigDecimal c4f_credit_cards_nof_trans) {
		this.c4f_credit_cards_nof_trans = c4f_credit_cards_nof_trans;
	}





	public BigDecimal getC5f_all_other_channels_nof_trans() {
		return c5f_all_other_channels_nof_trans;
	}





	public void setC5f_all_other_channels_nof_trans(BigDecimal c5f_all_other_channels_nof_trans) {
		this.c5f_all_other_channels_nof_trans = c5f_all_other_channels_nof_trans;
	}





	public BigDecimal getC6f_atm_nof_trans() {
		return c6f_atm_nof_trans;
	}





	public void setC6f_atm_nof_trans(BigDecimal c6f_atm_nof_trans) {
		this.c6f_atm_nof_trans = c6f_atm_nof_trans;
	}





	public BigDecimal getC7f_counter_trans_nof_trans() {
		return c7f_counter_trans_nof_trans;
	}





	public void setC7f_counter_trans_nof_trans(BigDecimal c7f_counter_trans_nof_trans) {
		this.c7f_counter_trans_nof_trans = c7f_counter_trans_nof_trans;
	}





	public BigDecimal getC8f_clearing_trans_nof_trans() {
		return c8f_clearing_trans_nof_trans;
	}





	public void setC8f_clearing_trans_nof_trans(BigDecimal c8f_clearing_trans_nof_trans) {
		this.c8f_clearing_trans_nof_trans = c8f_clearing_trans_nof_trans;
	}





	public BigDecimal getC9f_00001_trans_nof_trans() {
		return c9f_00001_trans_nof_trans;
	}





	public void setC9f_00001_trans_nof_trans(BigDecimal c9f_00001_trans_nof_trans) {
		this.c9f_00001_trans_nof_trans = c9f_00001_trans_nof_trans;
	}





	public BigDecimal getC10f_0002_trans_nof_trans() {
		return c10f_0002_trans_nof_trans;
	}





	public void setC10f_0002_trans_nof_trans(BigDecimal c10f_0002_trans_nof_trans) {
		this.c10f_0002_trans_nof_trans = c10f_0002_trans_nof_trans;
	}





	public BigDecimal getC11f_total_nof_trans() {
		return c11f_total_nof_trans;
	}





	public void setC11f_total_nof_trans(BigDecimal c11f_total_nof_trans) {
		this.c11f_total_nof_trans = c11f_total_nof_trans;
	}





	public BigDecimal getC12f_validation_nof_trans() {
		return c12f_validation_nof_trans;
	}





	public void setC12f_validation_nof_trans(BigDecimal c12f_validation_nof_trans) {
		this.c12f_validation_nof_trans = c12f_validation_nof_trans;
	}





	public BigDecimal getC1g_agents_val_trans() {
		return c1g_agents_val_trans;
	}





	public void setC1g_agents_val_trans(BigDecimal c1g_agents_val_trans) {
		this.c1g_agents_val_trans = c1g_agents_val_trans;
	}





	public BigDecimal getC2g_cash_val_trans() {
		return c2g_cash_val_trans;
	}





	public void setC2g_cash_val_trans(BigDecimal c2g_cash_val_trans) {
		this.c2g_cash_val_trans = c2g_cash_val_trans;
	}





	public BigDecimal getC3g_elecronic_transfer_val_trans() {
		return c3g_elecronic_transfer_val_trans;
	}





	public void setC3g_elecronic_transfer_val_trans(BigDecimal c3g_elecronic_transfer_val_trans) {
		this.c3g_elecronic_transfer_val_trans = c3g_elecronic_transfer_val_trans;
	}





	public BigDecimal getC4g_credit_cards_val_trans() {
		return c4g_credit_cards_val_trans;
	}





	public void setC4g_credit_cards_val_trans(BigDecimal c4g_credit_cards_val_trans) {
		this.c4g_credit_cards_val_trans = c4g_credit_cards_val_trans;
	}





	public BigDecimal getC5g_all_other_channels_val_trans() {
		return c5g_all_other_channels_val_trans;
	}





	public void setC5g_all_other_channels_val_trans(BigDecimal c5g_all_other_channels_val_trans) {
		this.c5g_all_other_channels_val_trans = c5g_all_other_channels_val_trans;
	}





	public BigDecimal getC6g_atm_val_trans() {
		return c6g_atm_val_trans;
	}





	public void setC6g_atm_val_trans(BigDecimal c6g_atm_val_trans) {
		this.c6g_atm_val_trans = c6g_atm_val_trans;
	}





	public BigDecimal getC7g_counter_trans_val_trans() {
		return c7g_counter_trans_val_trans;
	}





	public void setC7g_counter_trans_val_trans(BigDecimal c7g_counter_trans_val_trans) {
		this.c7g_counter_trans_val_trans = c7g_counter_trans_val_trans;
	}





	public BigDecimal getC8g_clearing_trans_val_trans() {
		return c8g_clearing_trans_val_trans;
	}





	public void setC8g_clearing_trans_val_trans(BigDecimal c8g_clearing_trans_val_trans) {
		this.c8g_clearing_trans_val_trans = c8g_clearing_trans_val_trans;
	}





	public BigDecimal getC9g_00001_trans_val_trans() {
		return c9g_00001_trans_val_trans;
	}





	public void setC9g_00001_trans_val_trans(BigDecimal c9g_00001_trans_val_trans) {
		this.c9g_00001_trans_val_trans = c9g_00001_trans_val_trans;
	}





	public BigDecimal getC10g_0002_trans_val_trans() {
		return c10g_0002_trans_val_trans;
	}





	public void setC10g_0002_trans_val_trans(BigDecimal c10g_0002_trans_val_trans) {
		this.c10g_0002_trans_val_trans = c10g_0002_trans_val_trans;
	}





	public BigDecimal getC11g_total_val_trans() {
		return c11g_total_val_trans;
	}





	public void setC11g_total_val_trans(BigDecimal c11g_total_val_trans) {
		this.c11g_total_val_trans = c11g_total_val_trans;
	}





	public BigDecimal getC12g_validation_val_trans() {
		return c12g_validation_val_trans;
	}





	public void setC12g_validation_val_trans(BigDecimal c12g_validation_val_trans) {
		this.c12g_validation_val_trans = c12g_validation_val_trans;
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





	public Character getNil_report_flg() {
		return nil_report_flg;
	}





	public void setNil_report_flg(Character nil_report_flg) {
		this.nil_report_flg = nil_report_flg;
	}





	public Character getArch_flg() {
		return arch_flg;
	}





	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}





	public T18Report() {}
	


}

package com.bornfire.entity.t1;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "T1_CUR_PROD_SERVICES")
public class T1CurProdServices {
	
	public T1CurProdServices() {}
	
	private String	d1a_dep_tkg_services;
	private String	d2a_lend_fund_based;
	private String	d3a_fb_finance_lease;
	private String	d4a_fb_oper_lease;
	private String	d5a_fb_loans;
	private String	d6a_fb_factoring;
	private String	d7a_fb_others;
	private String	d8a_lending_non_fund_based;
	private String	d9a_credit_cards;
	private String	d10a_merchang_pos;
	private String	d11a_other_prod_services;
	private String	d12a_retirement_saving_schemes;
	private String	d1b_dep_tkg_services;
	private String	d2b_lend_fund_based;
	private String	d3b_fb_finance_lease;
	private String	d4b_fb_oper_lease;
	private String	d5b_fb_loans;
	private String	d6b_fb_factoring;
	private String	d7b_fb_others;
	private String	d8b_lending_non_fund_based;
	private String	d9b_credit_cards;
	private String	d10b_merchang_pos;
	private String	d11b_other_prod_services;
	private String	d12b_retirement_saving_schemes;
	private String	c1c_dep_tkg_services_nbtdi;
	private String	c2c_lend_fund_based_nbtdi;
	private String	c3c_fb_finance_lease_nbtdi;
	private String	c4c_fb_oper_lease_nbtdi;
	private String	c5c_fb_loans_nbtdi;
	private String	c6c_fb_factoring_nbtdi;
	private String	c7c_fb_others_nbtdi;
	private String	c8c_lending_non_fund_based_nbtdi;
	private String	c9c_credit_cards_nbtdi;
	private String	c10c_merchang_pos_nbtdi;
	private String	c11c_other_prod_services_nbtdi;
	private String	c12c_retirement_saving_schemes_nbtdi;
	private String	c1d_dep_tkg_services_offer_ftf;
	private String	c2d_lend_fund_based_offer_ftf;
	private String	c3d_fb_finance_lease_offer_ftf;
	private String	c4d_fb_oper_lease_offer_ftf;
	private String	c5d_fb_loans_offer_ftf;
	private String	c6d_fb_factoring_offer_ftf;
	private String	c7d_fb_others_offer_ftf;
	private String	c8d_lending_non_fund_based_offer_ftf;
	private String	c9d_credit_cards_offer_ftf;
	private String	c10d_merchang_pos_offer_ftf;
	private String	c11d_other_prod_services_offer_ftf;
	private String	c12d_retirement_saving_schemes_offer_ftf;
	private String	c1e_dep_tkg_services_offer_nftf;
	private String	c2e_lend_fund_based_offer_nftf;
	private String	c3e_fb_finance_lease_offer_nftf;
	private String	c4e_fb_oper_lease_offer_nftf;
	private String	c5e_fb_loans_offer_nftf;
	private String	c6e_fb_factoring_offer_nftf;
	private String	c7e_fb_others_offer_nftf;
	private String	c8e_lending_non_fund_based_offer_nftf;
	private String	c9e_credit_cards_offer_nftf;
	private String	c10e_merchang_pos_offer_nftf;
	private String	c11e_other_prod_services_offer_nftf;
	private String	c12e_retirement_saving_schemes_offer_nftf;
	private String	c1f_dep_tkg_services_offer_tp;
	private String	c2f_lend_fund_based_offer_tp;
	private String	c3f_fb_finance_lease_offer_tp;
	private String	c4f_fb_oper_lease_offer_tp;
	private String	c5f_fb_loans_offer_tp;
	private String	c6f_fb_factoring_offer_tp;
	private String	c7f_fb_others_offer_tp;
	private String	c8f_lending_non_fund_based_offer_tp;
	private String	c9f_credit_cards_offer_tp;
	private String	c10f_merchang_pos_offer_tp;
	private String	c11f_other_prod_services_offer_tp;
	private String	c12f_retirement_saving_schemes_offer_tp;
	private BigDecimal	c1g_dep_tkg_services_hig_nod;
	private BigDecimal	c2g_lend_fund_based_hig_nod;
	private BigDecimal	c3g_fb_finance_lease_hig_nod;
	private BigDecimal	c4g_fb_oper_lease_hig_nod;
	private BigDecimal	c5g_fb_loans_hig_nod;
	private BigDecimal	c6g_fb_factoring_hig_nod;
	private BigDecimal	c7g_fb_others_hig_nod;
	private BigDecimal	c8g_lending_non_fund_based_hig_nod;
	private BigDecimal	c9g_credit_cards_hig_nod;
	private BigDecimal	c10g_merchang_pos_hig_nod;
	private BigDecimal	c11g_other_prod_services_hig_nod;
	private BigDecimal	c12g_retirement_saving_schemes_hig_nod;
	private BigDecimal	c1h_dep_tkg_services_hig_vod;
	private BigDecimal	c2h_lend_fund_based_hig_vod;
	private BigDecimal	c3h_fb_finance_lease_hig_vod;
	private BigDecimal	c4h_fb_oper_lease_hig_vod;
	private BigDecimal	c5h_fb_loans_hig_vod;
	private BigDecimal	c6h_fb_factoring_hig_vod;
	private BigDecimal	c7h_fb_others_hig_vod;
	private BigDecimal	c8h_lending_non_fund_based_hig_vod;
	private BigDecimal	c9h_credit_cards_hig_vod;
	private BigDecimal	c10h_merchang_pos_hig_vod;
	private BigDecimal	c11h_other_prod_services_hig_vod;
	private BigDecimal	c12h_retirement_saving_schemes_hig_vod;
	private BigDecimal	c1i_dep_tkg_services_hig_noc;
	private BigDecimal	c2i_lend_fund_based_hig_noc;
	private BigDecimal	c3i_fb_finance_lease_hig_noc;
	private BigDecimal	c4i_fb_oper_lease_hig_noc;
	private BigDecimal	c5i_fb_loans_hig_noc;
	private BigDecimal	c6i_fb_factoring_hig_noc;
	private BigDecimal	c7i_fb_others_hig_noc;
	private BigDecimal	c8i_lending_non_fund_based_hig_noc;
	private BigDecimal	c9i_credit_cards_hig_noc;
	private BigDecimal	c10i_merchang_pos_hig_noc;
	private BigDecimal	c11i_other_prod_services_hig_noc;
	private BigDecimal	c12i_retirement_saving_schemes_hig_noc;
	private BigDecimal	c1j_dep_tkg_services_hig_voc;
	private BigDecimal	c2j_lend_fund_based_hig_voc;
	private BigDecimal	c3j_fb_finance_lease_hig_voc;
	private BigDecimal	c4j_fb_oper_lease_hig_voc;
	private BigDecimal	c5j_fb_loans_hig_voc;
	private BigDecimal	c6j_fb_factoring_hig_voc;
	private BigDecimal	c7j_fb_others_hig_voc;
	private BigDecimal	c8j_lending_non_fund_based_hig_voc;
	private BigDecimal	c9j_credit_cards_hig_voc;
	private BigDecimal	c10j_merchang_pos_hig_voc;
	private BigDecimal	c11j_other_prod_services_hig_voc;
	private BigDecimal	c12j_retirement_saving_schemes_hig_voc;
	private BigDecimal	c1k_dep_tkg_services_med_nod;
	private BigDecimal	c2k_lend_fund_based_med_nod;
	private BigDecimal	c3k_fb_finance_lease_med_nod;
	private BigDecimal	c4k_fb_oper_lease_med_nod;
	private BigDecimal	c5k_fb_loans_med_nod;
	private BigDecimal	c6k_fb_factoring_med_nod;
	private BigDecimal	c7k_fb_others_med_nod;
	private BigDecimal	c8k_lending_non_fund_based_med_nod;
	private BigDecimal	c9k_credit_cards_med_nod;
	private BigDecimal	c10k_merchang_pos_med_nod;
	private BigDecimal	c11k_other_prod_services_med_nod;
	private BigDecimal	c12k_retirement_saving_schemes_med_nod;
	private BigDecimal	c1l_dep_tkg_services_med_vod;
	private BigDecimal	c2l_lend_fund_based_med_vod;
	private BigDecimal	c3l_fb_finance_lease_med_vod;
	private BigDecimal	c4l_fb_oper_lease_med_vod;
	private BigDecimal	c5l_fb_loans_med_vod;
	private BigDecimal	c6l_fb_factoring_med_vod;
	private BigDecimal	c7l_fb_others_med_vod;
	private BigDecimal	c8l_lending_non_fund_based_med_vod;
	private BigDecimal	c9l_credit_cards_med_vod;
	private BigDecimal	c10l_merchang_pos_med_vod;
	private BigDecimal	c11l_other_prod_services_med_vod;
	private BigDecimal	c12l_retirement_saving_schemes_med_vod;
	private BigDecimal	c1m_dep_tkg_services_med_noc;
	private BigDecimal	c2m_lend_fund_based_med_noc;
	private BigDecimal	c3m_fb_finance_lease_med_noc;
	private BigDecimal	c4m_fb_oper_lease_med_noc;
	private BigDecimal	c5m_fb_loans_med_noc;
	private BigDecimal	c6m_fb_factoring_med_noc;
	private BigDecimal	c7m_fb_others_med_noc;
	private BigDecimal	c8m_lending_non_fund_based_med_noc;
	private BigDecimal	c9m_credit_cards_med_noc;
	private BigDecimal	c10m_merchang_pos_med_noc;
	private BigDecimal	c11m_other_prod_services_med_noc;
	private BigDecimal	c12m_retirement_saving_schemes_med_noc;
	private BigDecimal	c1n_dep_tkg_services_med_voc;
	private BigDecimal	c2n_lend_fund_based_med_voc;
	private BigDecimal	c3n_fb_finance_lease_med_voc;
	private BigDecimal	c4n_fb_oper_lease_med_voc;
	private BigDecimal	c5n_fb_loans_med_voc;
	private BigDecimal	c6n_fb_factoring_med_voc;
	private BigDecimal	c7n_fb_others_med_voc;
	private BigDecimal	c8n_lending_non_fund_based_med_voc;
	private BigDecimal	c9n_credit_cards_med_voc;
	private BigDecimal	c10n_merchang_pos_med_voc;
	private BigDecimal	c11n_other_prod_services_med_voc;
	private BigDecimal	c12n_retirement_saving_schemes_med_voc;
	private BigDecimal	c1o_dep_tkg_services_low_nod;
	private BigDecimal	c2o_lend_fund_based_low_nod;
	private BigDecimal	c3o_fb_finance_lease_low_nod;
	private BigDecimal	c4o_fb_oper_lease_low_nod;
	private BigDecimal	c5o_fb_loans_low_nod;
	private BigDecimal	c6o_fb_factoring_low_nod;
	private BigDecimal	c7o_fb_others_low_nod;
	private BigDecimal	c8o_lending_non_fund_based_low_nod;
	private BigDecimal	c9o_credit_cards_low_nod;
	private BigDecimal	c10o_merchang_pos_low_nod;
	private BigDecimal	c11o_other_prod_services_low_nod;
	private BigDecimal	c12o_retirement_saving_schemes_low_nod;
	private BigDecimal	c1p_dep_tkg_services_low_vod;
	private BigDecimal	c2p_lend_fund_based_low_vod;
	private BigDecimal	c3p_fb_finance_lease_low_vod;
	private BigDecimal	c4p_fb_oper_lease_low_vod;
	private BigDecimal	c5p_fb_loans_low_vod;
	private BigDecimal	c6p_fb_factoring_low_vod;
	private BigDecimal	c7p_fb_others_low_vod;
	private BigDecimal	c8p_lending_non_fund_based_low_vod;
	private BigDecimal	c9p_credit_cards_low_vod;
	private BigDecimal	c10p_merchang_pos_low_vod;
	private BigDecimal	c11p_other_prod_services_low_vod;
	private BigDecimal	c12p_retirement_saving_schemes_low_vod;
	private BigDecimal	c1q_dep_tkg_services_low_noc;
	private BigDecimal	c2q_lend_fund_based_low_noc;
	private BigDecimal	c3q_fb_finance_lease_low_noc;
	private BigDecimal	c4q_fb_oper_lease_low_noc;
	private BigDecimal	c5q_fb_loans_low_noc;
	private BigDecimal	c6q_fb_factoring_low_noc;
	private BigDecimal	c7q_fb_others_low_noc;
	private BigDecimal	c8q_lending_non_fund_based_low_noc;
	private BigDecimal	c9q_credit_cards_low_noc;
	private BigDecimal	c10q_merchang_pos_low_noc;
	private BigDecimal	c11q_other_prod_services_low_noc;
	private BigDecimal	c12q_retirement_saving_schemes_low_noc;
	private BigDecimal	c1r_dep_tkg_services_low_voc;
	private BigDecimal	c2r_lend_fund_based_low_voc;
	private BigDecimal	c3r_fb_finance_lease_low_voc;
	private BigDecimal	c4r_fb_oper_lease_low_voc;
	private BigDecimal	c5r_fb_loans_low_voc;
	private BigDecimal	c6r_fb_factoring_low_voc;
	private BigDecimal	c7r_fb_others_low_voc;
	private BigDecimal	c8r_lending_non_fund_based_low_voc;
	private BigDecimal	c9r_credit_cards_low_voc;
	private BigDecimal	c10r_merchang_pos_low_voc;
	private BigDecimal	c11r_other_prod_services_low_voc;
	private BigDecimal	c12r_retirement_saving_schemes_low_voc;
	private BigDecimal	c1s_dep_tkg_services_wic_nod;
	private BigDecimal	c2s_lend_fund_based_wic_nod;
	private BigDecimal	c3s_fb_finance_lease_wic_nod;
	private BigDecimal	c4s_fb_oper_lease_wic_nod;
	private BigDecimal	c5s_fb_loans_wic_nod;
	private BigDecimal	c6s_fb_factoring_wic_nod;
	private BigDecimal	c7s_fb_others_wic_nod;
	private BigDecimal	c8s_lending_non_fund_based_wic_nod;
	private BigDecimal	c9s_credit_cards_wic_nod;
	private BigDecimal	c10s_merchang_pos_wic_nod;
	private BigDecimal	c11s_other_prod_services_wic_nod;
	private BigDecimal	c12s_retirement_saving_schemes_wic_nod;
	private BigDecimal	c1t_dep_tkg_services_wic_vod;
	private BigDecimal	c2t_lend_fund_based_wic_vod;
	private BigDecimal	c3t_fb_finance_lease_wic_vod;
	private BigDecimal	c4t_fb_oper_lease_wic_vod;
	private BigDecimal	c5t_fb_loans_wic_vod;
	private BigDecimal	c6t_fb_factoring_wic_vod;
	private BigDecimal	c7t_fb_others_wic_vod;
	private BigDecimal	c8t_lending_non_fund_based_wic_vod;
	private BigDecimal	c9t_credit_cards_wic_vod;
	private BigDecimal	c10t_merchang_pos_wic_vod;
	private BigDecimal	c11t_other_prod_services_wic_vod;
	private BigDecimal	c12t_retirement_saving_schemes_wic_vod;
	private BigDecimal	c1u_dep_tkg_services_wic_noc;
	private BigDecimal	c2u_lend_fund_based_wic_noc;
	private BigDecimal	c3u_fb_finance_lease_wic_noc;
	private BigDecimal	c4u_fb_oper_lease_wic_noc;
	private BigDecimal	c5u_fb_loans_wic_noc;
	private BigDecimal	c6u_fb_factoring_wic_noc;
	private BigDecimal	c7u_fb_others_wic_noc;
	private BigDecimal	c8u_lending_non_fund_based_wic_noc;
	private BigDecimal	c9u_credit_cards_wic_noc;
	private BigDecimal	c10u_merchang_pos_wic_noc;
	private BigDecimal	c11u_other_prod_services_wic_noc;
	private BigDecimal	c12u_retirement_saving_schemes_wic_noc;
	private BigDecimal	c1v_dep_tkg_services_wic_voc;
	private BigDecimal	c2v_lend_fund_based_wic_voc;
	private BigDecimal	c3v_fb_finance_lease_wic_voc;
	private BigDecimal	c4v_fb_oper_lease_wic_voc;
	private BigDecimal	c5v_fb_loans_wic_voc;
	private BigDecimal	c6v_fb_factoring_wic_voc;
	private BigDecimal	c7v_fb_others_wic_voc;
	private BigDecimal	c8v_lending_non_fund_based_wic_voc;
	private BigDecimal	c9v_credit_cards_wic_voc;
	private BigDecimal	c10v_merchang_pos_wic_voc;
	private BigDecimal	c11v_other_prod_services_wic_voc;
	private BigDecimal	c12v_retirement_saving_schemes_wic_voc;
	private String	report_code;
	private String	report_name;
	
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
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
	public String getD1a_dep_tkg_services() {
		return d1a_dep_tkg_services;
	}
	public void setD1a_dep_tkg_services(String d1a_dep_tkg_services) {
		this.d1a_dep_tkg_services = d1a_dep_tkg_services;
	}
	public String getD2a_lend_fund_based() {
		return d2a_lend_fund_based;
	}
	public void setD2a_lend_fund_based(String d2a_lend_fund_based) {
		this.d2a_lend_fund_based = d2a_lend_fund_based;
	}
	public String getD3a_fb_finance_lease() {
		return d3a_fb_finance_lease;
	}
	public void setD3a_fb_finance_lease(String d3a_fb_finance_lease) {
		this.d3a_fb_finance_lease = d3a_fb_finance_lease;
	}
	public String getD4a_fb_oper_lease() {
		return d4a_fb_oper_lease;
	}
	public void setD4a_fb_oper_lease(String d4a_fb_oper_lease) {
		this.d4a_fb_oper_lease = d4a_fb_oper_lease;
	}
	public String getD5a_fb_loans() {
		return d5a_fb_loans;
	}
	public void setD5a_fb_loans(String d5a_fb_loans) {
		this.d5a_fb_loans = d5a_fb_loans;
	}
	public String getD6a_fb_factoring() {
		return d6a_fb_factoring;
	}
	public void setD6a_fb_factoring(String d6a_fb_factoring) {
		this.d6a_fb_factoring = d6a_fb_factoring;
	}
	public String getD7a_fb_others() {
		return d7a_fb_others;
	}
	public void setD7a_fb_others(String d7a_fb_others) {
		this.d7a_fb_others = d7a_fb_others;
	}
	public String getD8a_lending_non_fund_based() {
		return d8a_lending_non_fund_based;
	}
	public void setD8a_lending_non_fund_based(String d8a_lending_non_fund_based) {
		this.d8a_lending_non_fund_based = d8a_lending_non_fund_based;
	}
	public String getD9a_credit_cards() {
		return d9a_credit_cards;
	}
	public void setD9a_credit_cards(String d9a_credit_cards) {
		this.d9a_credit_cards = d9a_credit_cards;
	}
	public String getD10a_merchang_pos() {
		return d10a_merchang_pos;
	}
	public void setD10a_merchang_pos(String d10a_merchang_pos) {
		this.d10a_merchang_pos = d10a_merchang_pos;
	}
	public String getD11a_other_prod_services() {
		return d11a_other_prod_services;
	}
	public void setD11a_other_prod_services(String d11a_other_prod_services) {
		this.d11a_other_prod_services = d11a_other_prod_services;
	}
	public String getD12a_retirement_saving_schemes() {
		return d12a_retirement_saving_schemes;
	}
	public void setD12a_retirement_saving_schemes(String d12a_retirement_saving_schemes) {
		this.d12a_retirement_saving_schemes = d12a_retirement_saving_schemes;
	}
	public String getD1b_dep_tkg_services() {
		return d1b_dep_tkg_services;
	}
	public void setD1b_dep_tkg_services(String d1b_dep_tkg_services) {
		this.d1b_dep_tkg_services = d1b_dep_tkg_services;
	}
	public String getD2b_lend_fund_based() {
		return d2b_lend_fund_based;
	}
	public void setD2b_lend_fund_based(String d2b_lend_fund_based) {
		this.d2b_lend_fund_based = d2b_lend_fund_based;
	}
	public String getD3b_fb_finance_lease() {
		return d3b_fb_finance_lease;
	}
	public void setD3b_fb_finance_lease(String d3b_fb_finance_lease) {
		this.d3b_fb_finance_lease = d3b_fb_finance_lease;
	}
	public String getD4b_fb_oper_lease() {
		return d4b_fb_oper_lease;
	}
	public void setD4b_fb_oper_lease(String d4b_fb_oper_lease) {
		this.d4b_fb_oper_lease = d4b_fb_oper_lease;
	}
	public String getD5b_fb_loans() {
		return d5b_fb_loans;
	}
	public void setD5b_fb_loans(String d5b_fb_loans) {
		this.d5b_fb_loans = d5b_fb_loans;
	}
	public String getD6b_fb_factoring() {
		return d6b_fb_factoring;
	}
	public void setD6b_fb_factoring(String d6b_fb_factoring) {
		this.d6b_fb_factoring = d6b_fb_factoring;
	}
	public String getD7b_fb_others() {
		return d7b_fb_others;
	}
	public void setD7b_fb_others(String d7b_fb_others) {
		this.d7b_fb_others = d7b_fb_others;
	}
	public String getD8b_lending_non_fund_based() {
		return d8b_lending_non_fund_based;
	}
	public void setD8b_lending_non_fund_based(String d8b_lending_non_fund_based) {
		this.d8b_lending_non_fund_based = d8b_lending_non_fund_based;
	}
	public String getD9b_credit_cards() {
		return d9b_credit_cards;
	}
	public void setD9b_credit_cards(String d9b_credit_cards) {
		this.d9b_credit_cards = d9b_credit_cards;
	}
	public String getD10b_merchang_pos() {
		return d10b_merchang_pos;
	}
	public void setD10b_merchang_pos(String d10b_merchang_pos) {
		this.d10b_merchang_pos = d10b_merchang_pos;
	}
	public String getD11b_other_prod_services() {
		return d11b_other_prod_services;
	}
	public void setD11b_other_prod_services(String d11b_other_prod_services) {
		this.d11b_other_prod_services = d11b_other_prod_services;
	}
	public String getD12b_retirement_saving_schemes() {
		return d12b_retirement_saving_schemes;
	}
	public void setD12b_retirement_saving_schemes(String d12b_retirement_saving_schemes) {
		this.d12b_retirement_saving_schemes = d12b_retirement_saving_schemes;
	}
	public String getC1c_dep_tkg_services_nbtdi() {
		return c1c_dep_tkg_services_nbtdi;
	}
	public void setC1c_dep_tkg_services_nbtdi(String c1c_dep_tkg_services_nbtdi) {
		this.c1c_dep_tkg_services_nbtdi = c1c_dep_tkg_services_nbtdi;
	}
	public String getC2c_lend_fund_based_nbtdi() {
		return c2c_lend_fund_based_nbtdi;
	}
	public void setC2c_lend_fund_based_nbtdi(String c2c_lend_fund_based_nbtdi) {
		this.c2c_lend_fund_based_nbtdi = c2c_lend_fund_based_nbtdi;
	}
	public String getC3c_fb_finance_lease_nbtdi() {
		return c3c_fb_finance_lease_nbtdi;
	}
	public void setC3c_fb_finance_lease_nbtdi(String c3c_fb_finance_lease_nbtdi) {
		this.c3c_fb_finance_lease_nbtdi = c3c_fb_finance_lease_nbtdi;
	}
	public String getC4c_fb_oper_lease_nbtdi() {
		return c4c_fb_oper_lease_nbtdi;
	}
	public void setC4c_fb_oper_lease_nbtdi(String c4c_fb_oper_lease_nbtdi) {
		this.c4c_fb_oper_lease_nbtdi = c4c_fb_oper_lease_nbtdi;
	}
	public String getC5c_fb_loans_nbtdi() {
		return c5c_fb_loans_nbtdi;
	}
	public void setC5c_fb_loans_nbtdi(String c5c_fb_loans_nbtdi) {
		this.c5c_fb_loans_nbtdi = c5c_fb_loans_nbtdi;
	}
	public String getC6c_fb_factoring_nbtdi() {
		return c6c_fb_factoring_nbtdi;
	}
	public void setC6c_fb_factoring_nbtdi(String c6c_fb_factoring_nbtdi) {
		this.c6c_fb_factoring_nbtdi = c6c_fb_factoring_nbtdi;
	}
	public String getC7c_fb_others_nbtdi() {
		return c7c_fb_others_nbtdi;
	}
	public void setC7c_fb_others_nbtdi(String c7c_fb_others_nbtdi) {
		this.c7c_fb_others_nbtdi = c7c_fb_others_nbtdi;
	}
	public String getC8c_lending_non_fund_based_nbtdi() {
		return c8c_lending_non_fund_based_nbtdi;
	}
	public void setC8c_lending_non_fund_based_nbtdi(String c8c_lending_non_fund_based_nbtdi) {
		this.c8c_lending_non_fund_based_nbtdi = c8c_lending_non_fund_based_nbtdi;
	}
	public String getC9c_credit_cards_nbtdi() {
		return c9c_credit_cards_nbtdi;
	}
	public void setC9c_credit_cards_nbtdi(String c9c_credit_cards_nbtdi) {
		this.c9c_credit_cards_nbtdi = c9c_credit_cards_nbtdi;
	}
	public String getC10c_merchang_pos_nbtdi() {
		return c10c_merchang_pos_nbtdi;
	}
	public void setC10c_merchang_pos_nbtdi(String c10c_merchang_pos_nbtdi) {
		this.c10c_merchang_pos_nbtdi = c10c_merchang_pos_nbtdi;
	}
	public String getC11c_other_prod_services_nbtdi() {
		return c11c_other_prod_services_nbtdi;
	}
	public void setC11c_other_prod_services_nbtdi(String c11c_other_prod_services_nbtdi) {
		this.c11c_other_prod_services_nbtdi = c11c_other_prod_services_nbtdi;
	}
	public String getC12c_retirement_saving_schemes_nbtdi() {
		return c12c_retirement_saving_schemes_nbtdi;
	}
	public void setC12c_retirement_saving_schemes_nbtdi(String c12c_retirement_saving_schemes_nbtdi) {
		this.c12c_retirement_saving_schemes_nbtdi = c12c_retirement_saving_schemes_nbtdi;
	}
	public String getC1d_dep_tkg_services_offer_ftf() {
		return c1d_dep_tkg_services_offer_ftf;
	}
	public void setC1d_dep_tkg_services_offer_ftf(String c1d_dep_tkg_services_offer_ftf) {
		this.c1d_dep_tkg_services_offer_ftf = c1d_dep_tkg_services_offer_ftf;
	}
	public String getC2d_lend_fund_based_offer_ftf() {
		return c2d_lend_fund_based_offer_ftf;
	}
	public void setC2d_lend_fund_based_offer_ftf(String c2d_lend_fund_based_offer_ftf) {
		this.c2d_lend_fund_based_offer_ftf = c2d_lend_fund_based_offer_ftf;
	}
	public String getC3d_fb_finance_lease_offer_ftf() {
		return c3d_fb_finance_lease_offer_ftf;
	}
	public void setC3d_fb_finance_lease_offer_ftf(String c3d_fb_finance_lease_offer_ftf) {
		this.c3d_fb_finance_lease_offer_ftf = c3d_fb_finance_lease_offer_ftf;
	}
	public String getC4d_fb_oper_lease_offer_ftf() {
		return c4d_fb_oper_lease_offer_ftf;
	}
	public void setC4d_fb_oper_lease_offer_ftf(String c4d_fb_oper_lease_offer_ftf) {
		this.c4d_fb_oper_lease_offer_ftf = c4d_fb_oper_lease_offer_ftf;
	}
	public String getC5d_fb_loans_offer_ftf() {
		return c5d_fb_loans_offer_ftf;
	}
	public void setC5d_fb_loans_offer_ftf(String c5d_fb_loans_offer_ftf) {
		this.c5d_fb_loans_offer_ftf = c5d_fb_loans_offer_ftf;
	}
	public String getC6d_fb_factoring_offer_ftf() {
		return c6d_fb_factoring_offer_ftf;
	}
	public void setC6d_fb_factoring_offer_ftf(String c6d_fb_factoring_offer_ftf) {
		this.c6d_fb_factoring_offer_ftf = c6d_fb_factoring_offer_ftf;
	}
	public String getC7d_fb_others_offer_ftf() {
		return c7d_fb_others_offer_ftf;
	}
	public void setC7d_fb_others_offer_ftf(String c7d_fb_others_offer_ftf) {
		this.c7d_fb_others_offer_ftf = c7d_fb_others_offer_ftf;
	}
	public String getC8d_lending_non_fund_based_offer_ftf() {
		return c8d_lending_non_fund_based_offer_ftf;
	}
	public void setC8d_lending_non_fund_based_offer_ftf(String c8d_lending_non_fund_based_offer_ftf) {
		this.c8d_lending_non_fund_based_offer_ftf = c8d_lending_non_fund_based_offer_ftf;
	}
	public String getC9d_credit_cards_offer_ftf() {
		return c9d_credit_cards_offer_ftf;
	}
	public void setC9d_credit_cards_offer_ftf(String c9d_credit_cards_offer_ftf) {
		this.c9d_credit_cards_offer_ftf = c9d_credit_cards_offer_ftf;
	}
	public String getC10d_merchang_pos_offer_ftf() {
		return c10d_merchang_pos_offer_ftf;
	}
	public void setC10d_merchang_pos_offer_ftf(String c10d_merchang_pos_offer_ftf) {
		this.c10d_merchang_pos_offer_ftf = c10d_merchang_pos_offer_ftf;
	}
	public String getC11d_other_prod_services_offer_ftf() {
		return c11d_other_prod_services_offer_ftf;
	}
	public void setC11d_other_prod_services_offer_ftf(String c11d_other_prod_services_offer_ftf) {
		this.c11d_other_prod_services_offer_ftf = c11d_other_prod_services_offer_ftf;
	}
	public String getC12d_retirement_saving_schemes_offer_ftf() {
		return c12d_retirement_saving_schemes_offer_ftf;
	}
	public void setC12d_retirement_saving_schemes_offer_ftf(String c12d_retirement_saving_schemes_offer_ftf) {
		this.c12d_retirement_saving_schemes_offer_ftf = c12d_retirement_saving_schemes_offer_ftf;
	}
	public String getC1e_dep_tkg_services_offer_nftf() {
		return c1e_dep_tkg_services_offer_nftf;
	}
	public void setC1e_dep_tkg_services_offer_nftf(String c1e_dep_tkg_services_offer_nftf) {
		this.c1e_dep_tkg_services_offer_nftf = c1e_dep_tkg_services_offer_nftf;
	}
	public String getC2e_lend_fund_based_offer_nftf() {
		return c2e_lend_fund_based_offer_nftf;
	}
	public void setC2e_lend_fund_based_offer_nftf(String c2e_lend_fund_based_offer_nftf) {
		this.c2e_lend_fund_based_offer_nftf = c2e_lend_fund_based_offer_nftf;
	}
	public String getC3e_fb_finance_lease_offer_nftf() {
		return c3e_fb_finance_lease_offer_nftf;
	}
	public void setC3e_fb_finance_lease_offer_nftf(String c3e_fb_finance_lease_offer_nftf) {
		this.c3e_fb_finance_lease_offer_nftf = c3e_fb_finance_lease_offer_nftf;
	}
	public String getC4e_fb_oper_lease_offer_nftf() {
		return c4e_fb_oper_lease_offer_nftf;
	}
	public void setC4e_fb_oper_lease_offer_nftf(String c4e_fb_oper_lease_offer_nftf) {
		this.c4e_fb_oper_lease_offer_nftf = c4e_fb_oper_lease_offer_nftf;
	}
	public String getC5e_fb_loans_offer_nftf() {
		return c5e_fb_loans_offer_nftf;
	}
	public void setC5e_fb_loans_offer_nftf(String c5e_fb_loans_offer_nftf) {
		this.c5e_fb_loans_offer_nftf = c5e_fb_loans_offer_nftf;
	}
	public String getC6e_fb_factoring_offer_nftf() {
		return c6e_fb_factoring_offer_nftf;
	}
	public void setC6e_fb_factoring_offer_nftf(String c6e_fb_factoring_offer_nftf) {
		this.c6e_fb_factoring_offer_nftf = c6e_fb_factoring_offer_nftf;
	}
	public String getC7e_fb_others_offer_nftf() {
		return c7e_fb_others_offer_nftf;
	}
	public void setC7e_fb_others_offer_nftf(String c7e_fb_others_offer_nftf) {
		this.c7e_fb_others_offer_nftf = c7e_fb_others_offer_nftf;
	}
	public String getC8e_lending_non_fund_based_offer_nftf() {
		return c8e_lending_non_fund_based_offer_nftf;
	}
	public void setC8e_lending_non_fund_based_offer_nftf(String c8e_lending_non_fund_based_offer_nftf) {
		this.c8e_lending_non_fund_based_offer_nftf = c8e_lending_non_fund_based_offer_nftf;
	}
	public String getC9e_credit_cards_offer_nftf() {
		return c9e_credit_cards_offer_nftf;
	}
	public void setC9e_credit_cards_offer_nftf(String c9e_credit_cards_offer_nftf) {
		this.c9e_credit_cards_offer_nftf = c9e_credit_cards_offer_nftf;
	}
	public String getC10e_merchang_pos_offer_nftf() {
		return c10e_merchang_pos_offer_nftf;
	}
	public void setC10e_merchang_pos_offer_nftf(String c10e_merchang_pos_offer_nftf) {
		this.c10e_merchang_pos_offer_nftf = c10e_merchang_pos_offer_nftf;
	}
	public String getC11e_other_prod_services_offer_nftf() {
		return c11e_other_prod_services_offer_nftf;
	}
	public void setC11e_other_prod_services_offer_nftf(String c11e_other_prod_services_offer_nftf) {
		this.c11e_other_prod_services_offer_nftf = c11e_other_prod_services_offer_nftf;
	}
	public String getC12e_retirement_saving_schemes_offer_nftf() {
		return c12e_retirement_saving_schemes_offer_nftf;
	}
	public void setC12e_retirement_saving_schemes_offer_nftf(String c12e_retirement_saving_schemes_offer_nftf) {
		this.c12e_retirement_saving_schemes_offer_nftf = c12e_retirement_saving_schemes_offer_nftf;
	}
	public String getC1f_dep_tkg_services_offer_tp() {
		return c1f_dep_tkg_services_offer_tp;
	}
	public void setC1f_dep_tkg_services_offer_tp(String c1f_dep_tkg_services_offer_tp) {
		this.c1f_dep_tkg_services_offer_tp = c1f_dep_tkg_services_offer_tp;
	}
	public String getC2f_lend_fund_based_offer_tp() {
		return c2f_lend_fund_based_offer_tp;
	}
	public void setC2f_lend_fund_based_offer_tp(String c2f_lend_fund_based_offer_tp) {
		this.c2f_lend_fund_based_offer_tp = c2f_lend_fund_based_offer_tp;
	}
	public String getC3f_fb_finance_lease_offer_tp() {
		return c3f_fb_finance_lease_offer_tp;
	}
	public void setC3f_fb_finance_lease_offer_tp(String c3f_fb_finance_lease_offer_tp) {
		this.c3f_fb_finance_lease_offer_tp = c3f_fb_finance_lease_offer_tp;
	}
	public String getC4f_fb_oper_lease_offer_tp() {
		return c4f_fb_oper_lease_offer_tp;
	}
	public void setC4f_fb_oper_lease_offer_tp(String c4f_fb_oper_lease_offer_tp) {
		this.c4f_fb_oper_lease_offer_tp = c4f_fb_oper_lease_offer_tp;
	}
	public String getC5f_fb_loans_offer_tp() {
		return c5f_fb_loans_offer_tp;
	}
	public void setC5f_fb_loans_offer_tp(String c5f_fb_loans_offer_tp) {
		this.c5f_fb_loans_offer_tp = c5f_fb_loans_offer_tp;
	}
	public String getC6f_fb_factoring_offer_tp() {
		return c6f_fb_factoring_offer_tp;
	}
	public void setC6f_fb_factoring_offer_tp(String c6f_fb_factoring_offer_tp) {
		this.c6f_fb_factoring_offer_tp = c6f_fb_factoring_offer_tp;
	}
	public String getC7f_fb_others_offer_tp() {
		return c7f_fb_others_offer_tp;
	}
	public void setC7f_fb_others_offer_tp(String c7f_fb_others_offer_tp) {
		this.c7f_fb_others_offer_tp = c7f_fb_others_offer_tp;
	}
	public String getC8f_lending_non_fund_based_offer_tp() {
		return c8f_lending_non_fund_based_offer_tp;
	}
	public void setC8f_lending_non_fund_based_offer_tp(String c8f_lending_non_fund_based_offer_tp) {
		this.c8f_lending_non_fund_based_offer_tp = c8f_lending_non_fund_based_offer_tp;
	}
	public String getC9f_credit_cards_offer_tp() {
		return c9f_credit_cards_offer_tp;
	}
	public void setC9f_credit_cards_offer_tp(String c9f_credit_cards_offer_tp) {
		this.c9f_credit_cards_offer_tp = c9f_credit_cards_offer_tp;
	}
	public String getC10f_merchang_pos_offer_tp() {
		return c10f_merchang_pos_offer_tp;
	}
	public void setC10f_merchang_pos_offer_tp(String c10f_merchang_pos_offer_tp) {
		this.c10f_merchang_pos_offer_tp = c10f_merchang_pos_offer_tp;
	}
	public String getC11f_other_prod_services_offer_tp() {
		return c11f_other_prod_services_offer_tp;
	}
	public void setC11f_other_prod_services_offer_tp(String c11f_other_prod_services_offer_tp) {
		this.c11f_other_prod_services_offer_tp = c11f_other_prod_services_offer_tp;
	}
	public String getC12f_retirement_saving_schemes_offer_tp() {
		return c12f_retirement_saving_schemes_offer_tp;
	}
	public void setC12f_retirement_saving_schemes_offer_tp(String c12f_retirement_saving_schemes_offer_tp) {
		this.c12f_retirement_saving_schemes_offer_tp = c12f_retirement_saving_schemes_offer_tp;
	}
	public BigDecimal getC1g_dep_tkg_services_hig_nod() {
		return c1g_dep_tkg_services_hig_nod;
	}
	public void setC1g_dep_tkg_services_hig_nod(BigDecimal c1g_dep_tkg_services_hig_nod) {
		this.c1g_dep_tkg_services_hig_nod = c1g_dep_tkg_services_hig_nod;
	}
	public BigDecimal getC2g_lend_fund_based_hig_nod() {
		return c2g_lend_fund_based_hig_nod;
	}
	public void setC2g_lend_fund_based_hig_nod(BigDecimal c2g_lend_fund_based_hig_nod) {
		this.c2g_lend_fund_based_hig_nod = c2g_lend_fund_based_hig_nod;
	}
	public BigDecimal getC3g_fb_finance_lease_hig_nod() {
		return c3g_fb_finance_lease_hig_nod;
	}
	public void setC3g_fb_finance_lease_hig_nod(BigDecimal c3g_fb_finance_lease_hig_nod) {
		this.c3g_fb_finance_lease_hig_nod = c3g_fb_finance_lease_hig_nod;
	}
	public BigDecimal getC4g_fb_oper_lease_hig_nod() {
		return c4g_fb_oper_lease_hig_nod;
	}
	public void setC4g_fb_oper_lease_hig_nod(BigDecimal c4g_fb_oper_lease_hig_nod) {
		this.c4g_fb_oper_lease_hig_nod = c4g_fb_oper_lease_hig_nod;
	}
	public BigDecimal getC5g_fb_loans_hig_nod() {
		return c5g_fb_loans_hig_nod;
	}
	public void setC5g_fb_loans_hig_nod(BigDecimal c5g_fb_loans_hig_nod) {
		this.c5g_fb_loans_hig_nod = c5g_fb_loans_hig_nod;
	}
	public BigDecimal getC6g_fb_factoring_hig_nod() {
		return c6g_fb_factoring_hig_nod;
	}
	public void setC6g_fb_factoring_hig_nod(BigDecimal c6g_fb_factoring_hig_nod) {
		this.c6g_fb_factoring_hig_nod = c6g_fb_factoring_hig_nod;
	}
	public BigDecimal getC7g_fb_others_hig_nod() {
		return c7g_fb_others_hig_nod;
	}
	public void setC7g_fb_others_hig_nod(BigDecimal c7g_fb_others_hig_nod) {
		this.c7g_fb_others_hig_nod = c7g_fb_others_hig_nod;
	}
	public BigDecimal getC8g_lending_non_fund_based_hig_nod() {
		return c8g_lending_non_fund_based_hig_nod;
	}
	public void setC8g_lending_non_fund_based_hig_nod(BigDecimal c8g_lending_non_fund_based_hig_nod) {
		this.c8g_lending_non_fund_based_hig_nod = c8g_lending_non_fund_based_hig_nod;
	}
	public BigDecimal getC9g_credit_cards_hig_nod() {
		return c9g_credit_cards_hig_nod;
	}
	public void setC9g_credit_cards_hig_nod(BigDecimal c9g_credit_cards_hig_nod) {
		this.c9g_credit_cards_hig_nod = c9g_credit_cards_hig_nod;
	}
	public BigDecimal getC10g_merchang_pos_hig_nod() {
		return c10g_merchang_pos_hig_nod;
	}
	public void setC10g_merchang_pos_hig_nod(BigDecimal c10g_merchang_pos_hig_nod) {
		this.c10g_merchang_pos_hig_nod = c10g_merchang_pos_hig_nod;
	}
	public BigDecimal getC11g_other_prod_services_hig_nod() {
		return c11g_other_prod_services_hig_nod;
	}
	public void setC11g_other_prod_services_hig_nod(BigDecimal c11g_other_prod_services_hig_nod) {
		this.c11g_other_prod_services_hig_nod = c11g_other_prod_services_hig_nod;
	}
	public BigDecimal getC12g_retirement_saving_schemes_hig_nod() {
		return c12g_retirement_saving_schemes_hig_nod;
	}
	public void setC12g_retirement_saving_schemes_hig_nod(BigDecimal c12g_retirement_saving_schemes_hig_nod) {
		this.c12g_retirement_saving_schemes_hig_nod = c12g_retirement_saving_schemes_hig_nod;
	}
	public BigDecimal getC1h_dep_tkg_services_hig_vod() {
		return c1h_dep_tkg_services_hig_vod;
	}
	public void setC1h_dep_tkg_services_hig_vod(BigDecimal c1h_dep_tkg_services_hig_vod) {
		this.c1h_dep_tkg_services_hig_vod = c1h_dep_tkg_services_hig_vod;
	}
	public BigDecimal getC2h_lend_fund_based_hig_vod() {
		return c2h_lend_fund_based_hig_vod;
	}
	public void setC2h_lend_fund_based_hig_vod(BigDecimal c2h_lend_fund_based_hig_vod) {
		this.c2h_lend_fund_based_hig_vod = c2h_lend_fund_based_hig_vod;
	}
	public BigDecimal getC3h_fb_finance_lease_hig_vod() {
		return c3h_fb_finance_lease_hig_vod;
	}
	public void setC3h_fb_finance_lease_hig_vod(BigDecimal c3h_fb_finance_lease_hig_vod) {
		this.c3h_fb_finance_lease_hig_vod = c3h_fb_finance_lease_hig_vod;
	}
	public BigDecimal getC4h_fb_oper_lease_hig_vod() {
		return c4h_fb_oper_lease_hig_vod;
	}
	public void setC4h_fb_oper_lease_hig_vod(BigDecimal c4h_fb_oper_lease_hig_vod) {
		this.c4h_fb_oper_lease_hig_vod = c4h_fb_oper_lease_hig_vod;
	}
	public BigDecimal getC5h_fb_loans_hig_vod() {
		return c5h_fb_loans_hig_vod;
	}
	public void setC5h_fb_loans_hig_vod(BigDecimal c5h_fb_loans_hig_vod) {
		this.c5h_fb_loans_hig_vod = c5h_fb_loans_hig_vod;
	}
	public BigDecimal getC6h_fb_factoring_hig_vod() {
		return c6h_fb_factoring_hig_vod;
	}
	public void setC6h_fb_factoring_hig_vod(BigDecimal c6h_fb_factoring_hig_vod) {
		this.c6h_fb_factoring_hig_vod = c6h_fb_factoring_hig_vod;
	}
	public BigDecimal getC7h_fb_others_hig_vod() {
		return c7h_fb_others_hig_vod;
	}
	public void setC7h_fb_others_hig_vod(BigDecimal c7h_fb_others_hig_vod) {
		this.c7h_fb_others_hig_vod = c7h_fb_others_hig_vod;
	}
	public BigDecimal getC8h_lending_non_fund_based_hig_vod() {
		return c8h_lending_non_fund_based_hig_vod;
	}
	public void setC8h_lending_non_fund_based_hig_vod(BigDecimal c8h_lending_non_fund_based_hig_vod) {
		this.c8h_lending_non_fund_based_hig_vod = c8h_lending_non_fund_based_hig_vod;
	}
	public BigDecimal getC9h_credit_cards_hig_vod() {
		return c9h_credit_cards_hig_vod;
	}
	public void setC9h_credit_cards_hig_vod(BigDecimal c9h_credit_cards_hig_vod) {
		this.c9h_credit_cards_hig_vod = c9h_credit_cards_hig_vod;
	}
	public BigDecimal getC10h_merchang_pos_hig_vod() {
		return c10h_merchang_pos_hig_vod;
	}
	public void setC10h_merchang_pos_hig_vod(BigDecimal c10h_merchang_pos_hig_vod) {
		this.c10h_merchang_pos_hig_vod = c10h_merchang_pos_hig_vod;
	}
	public BigDecimal getC11h_other_prod_services_hig_vod() {
		return c11h_other_prod_services_hig_vod;
	}
	public void setC11h_other_prod_services_hig_vod(BigDecimal c11h_other_prod_services_hig_vod) {
		this.c11h_other_prod_services_hig_vod = c11h_other_prod_services_hig_vod;
	}
	public BigDecimal getC12h_retirement_saving_schemes_hig_vod() {
		return c12h_retirement_saving_schemes_hig_vod;
	}
	public void setC12h_retirement_saving_schemes_hig_vod(BigDecimal c12h_retirement_saving_schemes_hig_vod) {
		this.c12h_retirement_saving_schemes_hig_vod = c12h_retirement_saving_schemes_hig_vod;
	}
	public BigDecimal getC1i_dep_tkg_services_hig_noc() {
		return c1i_dep_tkg_services_hig_noc;
	}
	public void setC1i_dep_tkg_services_hig_noc(BigDecimal c1i_dep_tkg_services_hig_noc) {
		this.c1i_dep_tkg_services_hig_noc = c1i_dep_tkg_services_hig_noc;
	}
	public BigDecimal getC2i_lend_fund_based_hig_noc() {
		return c2i_lend_fund_based_hig_noc;
	}
	public void setC2i_lend_fund_based_hig_noc(BigDecimal c2i_lend_fund_based_hig_noc) {
		this.c2i_lend_fund_based_hig_noc = c2i_lend_fund_based_hig_noc;
	}
	public BigDecimal getC3i_fb_finance_lease_hig_noc() {
		return c3i_fb_finance_lease_hig_noc;
	}
	public void setC3i_fb_finance_lease_hig_noc(BigDecimal c3i_fb_finance_lease_hig_noc) {
		this.c3i_fb_finance_lease_hig_noc = c3i_fb_finance_lease_hig_noc;
	}
	public BigDecimal getC4i_fb_oper_lease_hig_noc() {
		return c4i_fb_oper_lease_hig_noc;
	}
	public void setC4i_fb_oper_lease_hig_noc(BigDecimal c4i_fb_oper_lease_hig_noc) {
		this.c4i_fb_oper_lease_hig_noc = c4i_fb_oper_lease_hig_noc;
	}
	public BigDecimal getC5i_fb_loans_hig_noc() {
		return c5i_fb_loans_hig_noc;
	}
	public void setC5i_fb_loans_hig_noc(BigDecimal c5i_fb_loans_hig_noc) {
		this.c5i_fb_loans_hig_noc = c5i_fb_loans_hig_noc;
	}
	public BigDecimal getC6i_fb_factoring_hig_noc() {
		return c6i_fb_factoring_hig_noc;
	}
	public void setC6i_fb_factoring_hig_noc(BigDecimal c6i_fb_factoring_hig_noc) {
		this.c6i_fb_factoring_hig_noc = c6i_fb_factoring_hig_noc;
	}
	public BigDecimal getC7i_fb_others_hig_noc() {
		return c7i_fb_others_hig_noc;
	}
	public void setC7i_fb_others_hig_noc(BigDecimal c7i_fb_others_hig_noc) {
		this.c7i_fb_others_hig_noc = c7i_fb_others_hig_noc;
	}
	public BigDecimal getC8i_lending_non_fund_based_hig_noc() {
		return c8i_lending_non_fund_based_hig_noc;
	}
	public void setC8i_lending_non_fund_based_hig_noc(BigDecimal c8i_lending_non_fund_based_hig_noc) {
		this.c8i_lending_non_fund_based_hig_noc = c8i_lending_non_fund_based_hig_noc;
	}
	public BigDecimal getC9i_credit_cards_hig_noc() {
		return c9i_credit_cards_hig_noc;
	}
	public void setC9i_credit_cards_hig_noc(BigDecimal c9i_credit_cards_hig_noc) {
		this.c9i_credit_cards_hig_noc = c9i_credit_cards_hig_noc;
	}
	public BigDecimal getC10i_merchang_pos_hig_noc() {
		return c10i_merchang_pos_hig_noc;
	}
	public void setC10i_merchang_pos_hig_noc(BigDecimal c10i_merchang_pos_hig_noc) {
		this.c10i_merchang_pos_hig_noc = c10i_merchang_pos_hig_noc;
	}
	public BigDecimal getC11i_other_prod_services_hig_noc() {
		return c11i_other_prod_services_hig_noc;
	}
	public void setC11i_other_prod_services_hig_noc(BigDecimal c11i_other_prod_services_hig_noc) {
		this.c11i_other_prod_services_hig_noc = c11i_other_prod_services_hig_noc;
	}
	public BigDecimal getC12i_retirement_saving_schemes_hig_noc() {
		return c12i_retirement_saving_schemes_hig_noc;
	}
	public void setC12i_retirement_saving_schemes_hig_noc(BigDecimal c12i_retirement_saving_schemes_hig_noc) {
		this.c12i_retirement_saving_schemes_hig_noc = c12i_retirement_saving_schemes_hig_noc;
	}
	public BigDecimal getC1j_dep_tkg_services_hig_voc() {
		return c1j_dep_tkg_services_hig_voc;
	}
	public void setC1j_dep_tkg_services_hig_voc(BigDecimal c1j_dep_tkg_services_hig_voc) {
		this.c1j_dep_tkg_services_hig_voc = c1j_dep_tkg_services_hig_voc;
	}
	public BigDecimal getC2j_lend_fund_based_hig_voc() {
		return c2j_lend_fund_based_hig_voc;
	}
	public void setC2j_lend_fund_based_hig_voc(BigDecimal c2j_lend_fund_based_hig_voc) {
		this.c2j_lend_fund_based_hig_voc = c2j_lend_fund_based_hig_voc;
	}
	public BigDecimal getC3j_fb_finance_lease_hig_voc() {
		return c3j_fb_finance_lease_hig_voc;
	}
	public void setC3j_fb_finance_lease_hig_voc(BigDecimal c3j_fb_finance_lease_hig_voc) {
		this.c3j_fb_finance_lease_hig_voc = c3j_fb_finance_lease_hig_voc;
	}
	public BigDecimal getC4j_fb_oper_lease_hig_voc() {
		return c4j_fb_oper_lease_hig_voc;
	}
	public void setC4j_fb_oper_lease_hig_voc(BigDecimal c4j_fb_oper_lease_hig_voc) {
		this.c4j_fb_oper_lease_hig_voc = c4j_fb_oper_lease_hig_voc;
	}
	public BigDecimal getC5j_fb_loans_hig_voc() {
		return c5j_fb_loans_hig_voc;
	}
	public void setC5j_fb_loans_hig_voc(BigDecimal c5j_fb_loans_hig_voc) {
		this.c5j_fb_loans_hig_voc = c5j_fb_loans_hig_voc;
	}
	public BigDecimal getC6j_fb_factoring_hig_voc() {
		return c6j_fb_factoring_hig_voc;
	}
	public void setC6j_fb_factoring_hig_voc(BigDecimal c6j_fb_factoring_hig_voc) {
		this.c6j_fb_factoring_hig_voc = c6j_fb_factoring_hig_voc;
	}
	public BigDecimal getC7j_fb_others_hig_voc() {
		return c7j_fb_others_hig_voc;
	}
	public void setC7j_fb_others_hig_voc(BigDecimal c7j_fb_others_hig_voc) {
		this.c7j_fb_others_hig_voc = c7j_fb_others_hig_voc;
	}
	public BigDecimal getC8j_lending_non_fund_based_hig_voc() {
		return c8j_lending_non_fund_based_hig_voc;
	}
	public void setC8j_lending_non_fund_based_hig_voc(BigDecimal c8j_lending_non_fund_based_hig_voc) {
		this.c8j_lending_non_fund_based_hig_voc = c8j_lending_non_fund_based_hig_voc;
	}
	public BigDecimal getC9j_credit_cards_hig_voc() {
		return c9j_credit_cards_hig_voc;
	}
	public void setC9j_credit_cards_hig_voc(BigDecimal c9j_credit_cards_hig_voc) {
		this.c9j_credit_cards_hig_voc = c9j_credit_cards_hig_voc;
	}
	public BigDecimal getC10j_merchang_pos_hig_voc() {
		return c10j_merchang_pos_hig_voc;
	}
	public void setC10j_merchang_pos_hig_voc(BigDecimal c10j_merchang_pos_hig_voc) {
		this.c10j_merchang_pos_hig_voc = c10j_merchang_pos_hig_voc;
	}
	public BigDecimal getC11j_other_prod_services_hig_voc() {
		return c11j_other_prod_services_hig_voc;
	}
	public void setC11j_other_prod_services_hig_voc(BigDecimal c11j_other_prod_services_hig_voc) {
		this.c11j_other_prod_services_hig_voc = c11j_other_prod_services_hig_voc;
	}
	public BigDecimal getC12j_retirement_saving_schemes_hig_voc() {
		return c12j_retirement_saving_schemes_hig_voc;
	}
	public void setC12j_retirement_saving_schemes_hig_voc(BigDecimal c12j_retirement_saving_schemes_hig_voc) {
		this.c12j_retirement_saving_schemes_hig_voc = c12j_retirement_saving_schemes_hig_voc;
	}
	public BigDecimal getC1k_dep_tkg_services_med_nod() {
		return c1k_dep_tkg_services_med_nod;
	}
	public void setC1k_dep_tkg_services_med_nod(BigDecimal c1k_dep_tkg_services_med_nod) {
		this.c1k_dep_tkg_services_med_nod = c1k_dep_tkg_services_med_nod;
	}
	public BigDecimal getC2k_lend_fund_based_med_nod() {
		return c2k_lend_fund_based_med_nod;
	}
	public void setC2k_lend_fund_based_med_nod(BigDecimal c2k_lend_fund_based_med_nod) {
		this.c2k_lend_fund_based_med_nod = c2k_lend_fund_based_med_nod;
	}
	public BigDecimal getC3k_fb_finance_lease_med_nod() {
		return c3k_fb_finance_lease_med_nod;
	}
	public void setC3k_fb_finance_lease_med_nod(BigDecimal c3k_fb_finance_lease_med_nod) {
		this.c3k_fb_finance_lease_med_nod = c3k_fb_finance_lease_med_nod;
	}
	public BigDecimal getC4k_fb_oper_lease_med_nod() {
		return c4k_fb_oper_lease_med_nod;
	}
	public void setC4k_fb_oper_lease_med_nod(BigDecimal c4k_fb_oper_lease_med_nod) {
		this.c4k_fb_oper_lease_med_nod = c4k_fb_oper_lease_med_nod;
	}
	public BigDecimal getC5k_fb_loans_med_nod() {
		return c5k_fb_loans_med_nod;
	}
	public void setC5k_fb_loans_med_nod(BigDecimal c5k_fb_loans_med_nod) {
		this.c5k_fb_loans_med_nod = c5k_fb_loans_med_nod;
	}
	public BigDecimal getC6k_fb_factoring_med_nod() {
		return c6k_fb_factoring_med_nod;
	}
	public void setC6k_fb_factoring_med_nod(BigDecimal c6k_fb_factoring_med_nod) {
		this.c6k_fb_factoring_med_nod = c6k_fb_factoring_med_nod;
	}
	public BigDecimal getC7k_fb_others_med_nod() {
		return c7k_fb_others_med_nod;
	}
	public void setC7k_fb_others_med_nod(BigDecimal c7k_fb_others_med_nod) {
		this.c7k_fb_others_med_nod = c7k_fb_others_med_nod;
	}
	public BigDecimal getC8k_lending_non_fund_based_med_nod() {
		return c8k_lending_non_fund_based_med_nod;
	}
	public void setC8k_lending_non_fund_based_med_nod(BigDecimal c8k_lending_non_fund_based_med_nod) {
		this.c8k_lending_non_fund_based_med_nod = c8k_lending_non_fund_based_med_nod;
	}
	public BigDecimal getC9k_credit_cards_med_nod() {
		return c9k_credit_cards_med_nod;
	}
	public void setC9k_credit_cards_med_nod(BigDecimal c9k_credit_cards_med_nod) {
		this.c9k_credit_cards_med_nod = c9k_credit_cards_med_nod;
	}
	public BigDecimal getC10k_merchang_pos_med_nod() {
		return c10k_merchang_pos_med_nod;
	}
	public void setC10k_merchang_pos_med_nod(BigDecimal c10k_merchang_pos_med_nod) {
		this.c10k_merchang_pos_med_nod = c10k_merchang_pos_med_nod;
	}
	public BigDecimal getC11k_other_prod_services_med_nod() {
		return c11k_other_prod_services_med_nod;
	}
	public void setC11k_other_prod_services_med_nod(BigDecimal c11k_other_prod_services_med_nod) {
		this.c11k_other_prod_services_med_nod = c11k_other_prod_services_med_nod;
	}
	public BigDecimal getC12k_retirement_saving_schemes_med_nod() {
		return c12k_retirement_saving_schemes_med_nod;
	}
	public void setC12k_retirement_saving_schemes_med_nod(BigDecimal c12k_retirement_saving_schemes_med_nod) {
		this.c12k_retirement_saving_schemes_med_nod = c12k_retirement_saving_schemes_med_nod;
	}
	public BigDecimal getC1l_dep_tkg_services_med_vod() {
		return c1l_dep_tkg_services_med_vod;
	}
	public void setC1l_dep_tkg_services_med_vod(BigDecimal c1l_dep_tkg_services_med_vod) {
		this.c1l_dep_tkg_services_med_vod = c1l_dep_tkg_services_med_vod;
	}
	public BigDecimal getC2l_lend_fund_based_med_vod() {
		return c2l_lend_fund_based_med_vod;
	}
	public void setC2l_lend_fund_based_med_vod(BigDecimal c2l_lend_fund_based_med_vod) {
		this.c2l_lend_fund_based_med_vod = c2l_lend_fund_based_med_vod;
	}
	public BigDecimal getC3l_fb_finance_lease_med_vod() {
		return c3l_fb_finance_lease_med_vod;
	}
	public void setC3l_fb_finance_lease_med_vod(BigDecimal c3l_fb_finance_lease_med_vod) {
		this.c3l_fb_finance_lease_med_vod = c3l_fb_finance_lease_med_vod;
	}
	public BigDecimal getC4l_fb_oper_lease_med_vod() {
		return c4l_fb_oper_lease_med_vod;
	}
	public void setC4l_fb_oper_lease_med_vod(BigDecimal c4l_fb_oper_lease_med_vod) {
		this.c4l_fb_oper_lease_med_vod = c4l_fb_oper_lease_med_vod;
	}
	public BigDecimal getC5l_fb_loans_med_vod() {
		return c5l_fb_loans_med_vod;
	}
	public void setC5l_fb_loans_med_vod(BigDecimal c5l_fb_loans_med_vod) {
		this.c5l_fb_loans_med_vod = c5l_fb_loans_med_vod;
	}
	public BigDecimal getC6l_fb_factoring_med_vod() {
		return c6l_fb_factoring_med_vod;
	}
	public void setC6l_fb_factoring_med_vod(BigDecimal c6l_fb_factoring_med_vod) {
		this.c6l_fb_factoring_med_vod = c6l_fb_factoring_med_vod;
	}
	public BigDecimal getC7l_fb_others_med_vod() {
		return c7l_fb_others_med_vod;
	}
	public void setC7l_fb_others_med_vod(BigDecimal c7l_fb_others_med_vod) {
		this.c7l_fb_others_med_vod = c7l_fb_others_med_vod;
	}
	public BigDecimal getC8l_lending_non_fund_based_med_vod() {
		return c8l_lending_non_fund_based_med_vod;
	}
	public void setC8l_lending_non_fund_based_med_vod(BigDecimal c8l_lending_non_fund_based_med_vod) {
		this.c8l_lending_non_fund_based_med_vod = c8l_lending_non_fund_based_med_vod;
	}
	public BigDecimal getC9l_credit_cards_med_vod() {
		return c9l_credit_cards_med_vod;
	}
	public void setC9l_credit_cards_med_vod(BigDecimal c9l_credit_cards_med_vod) {
		this.c9l_credit_cards_med_vod = c9l_credit_cards_med_vod;
	}
	public BigDecimal getC10l_merchang_pos_med_vod() {
		return c10l_merchang_pos_med_vod;
	}
	public void setC10l_merchang_pos_med_vod(BigDecimal c10l_merchang_pos_med_vod) {
		this.c10l_merchang_pos_med_vod = c10l_merchang_pos_med_vod;
	}
	public BigDecimal getC11l_other_prod_services_med_vod() {
		return c11l_other_prod_services_med_vod;
	}
	public void setC11l_other_prod_services_med_vod(BigDecimal c11l_other_prod_services_med_vod) {
		this.c11l_other_prod_services_med_vod = c11l_other_prod_services_med_vod;
	}
	public BigDecimal getC12l_retirement_saving_schemes_med_vod() {
		return c12l_retirement_saving_schemes_med_vod;
	}
	public void setC12l_retirement_saving_schemes_med_vod(BigDecimal c12l_retirement_saving_schemes_med_vod) {
		this.c12l_retirement_saving_schemes_med_vod = c12l_retirement_saving_schemes_med_vod;
	}
	public BigDecimal getC1m_dep_tkg_services_med_noc() {
		return c1m_dep_tkg_services_med_noc;
	}
	public void setC1m_dep_tkg_services_med_noc(BigDecimal c1m_dep_tkg_services_med_noc) {
		this.c1m_dep_tkg_services_med_noc = c1m_dep_tkg_services_med_noc;
	}
	public BigDecimal getC2m_lend_fund_based_med_noc() {
		return c2m_lend_fund_based_med_noc;
	}
	public void setC2m_lend_fund_based_med_noc(BigDecimal c2m_lend_fund_based_med_noc) {
		this.c2m_lend_fund_based_med_noc = c2m_lend_fund_based_med_noc;
	}
	public BigDecimal getC3m_fb_finance_lease_med_noc() {
		return c3m_fb_finance_lease_med_noc;
	}
	public void setC3m_fb_finance_lease_med_noc(BigDecimal c3m_fb_finance_lease_med_noc) {
		this.c3m_fb_finance_lease_med_noc = c3m_fb_finance_lease_med_noc;
	}
	public BigDecimal getC4m_fb_oper_lease_med_noc() {
		return c4m_fb_oper_lease_med_noc;
	}
	public void setC4m_fb_oper_lease_med_noc(BigDecimal c4m_fb_oper_lease_med_noc) {
		this.c4m_fb_oper_lease_med_noc = c4m_fb_oper_lease_med_noc;
	}
	public BigDecimal getC5m_fb_loans_med_noc() {
		return c5m_fb_loans_med_noc;
	}
	public void setC5m_fb_loans_med_noc(BigDecimal c5m_fb_loans_med_noc) {
		this.c5m_fb_loans_med_noc = c5m_fb_loans_med_noc;
	}
	public BigDecimal getC6m_fb_factoring_med_noc() {
		return c6m_fb_factoring_med_noc;
	}
	public void setC6m_fb_factoring_med_noc(BigDecimal c6m_fb_factoring_med_noc) {
		this.c6m_fb_factoring_med_noc = c6m_fb_factoring_med_noc;
	}
	public BigDecimal getC7m_fb_others_med_noc() {
		return c7m_fb_others_med_noc;
	}
	public void setC7m_fb_others_med_noc(BigDecimal c7m_fb_others_med_noc) {
		this.c7m_fb_others_med_noc = c7m_fb_others_med_noc;
	}
	public BigDecimal getC8m_lending_non_fund_based_med_noc() {
		return c8m_lending_non_fund_based_med_noc;
	}
	public void setC8m_lending_non_fund_based_med_noc(BigDecimal c8m_lending_non_fund_based_med_noc) {
		this.c8m_lending_non_fund_based_med_noc = c8m_lending_non_fund_based_med_noc;
	}
	public BigDecimal getC9m_credit_cards_med_noc() {
		return c9m_credit_cards_med_noc;
	}
	public void setC9m_credit_cards_med_noc(BigDecimal c9m_credit_cards_med_noc) {
		this.c9m_credit_cards_med_noc = c9m_credit_cards_med_noc;
	}
	public BigDecimal getC10m_merchang_pos_med_noc() {
		return c10m_merchang_pos_med_noc;
	}
	public void setC10m_merchang_pos_med_noc(BigDecimal c10m_merchang_pos_med_noc) {
		this.c10m_merchang_pos_med_noc = c10m_merchang_pos_med_noc;
	}
	public BigDecimal getC11m_other_prod_services_med_noc() {
		return c11m_other_prod_services_med_noc;
	}
	public void setC11m_other_prod_services_med_noc(BigDecimal c11m_other_prod_services_med_noc) {
		this.c11m_other_prod_services_med_noc = c11m_other_prod_services_med_noc;
	}
	public BigDecimal getC12m_retirement_saving_schemes_med_noc() {
		return c12m_retirement_saving_schemes_med_noc;
	}
	public void setC12m_retirement_saving_schemes_med_noc(BigDecimal c12m_retirement_saving_schemes_med_noc) {
		this.c12m_retirement_saving_schemes_med_noc = c12m_retirement_saving_schemes_med_noc;
	}
	public BigDecimal getC1n_dep_tkg_services_med_voc() {
		return c1n_dep_tkg_services_med_voc;
	}
	public void setC1n_dep_tkg_services_med_voc(BigDecimal c1n_dep_tkg_services_med_voc) {
		this.c1n_dep_tkg_services_med_voc = c1n_dep_tkg_services_med_voc;
	}
	public BigDecimal getC2n_lend_fund_based_med_voc() {
		return c2n_lend_fund_based_med_voc;
	}
	public void setC2n_lend_fund_based_med_voc(BigDecimal c2n_lend_fund_based_med_voc) {
		this.c2n_lend_fund_based_med_voc = c2n_lend_fund_based_med_voc;
	}
	public BigDecimal getC3n_fb_finance_lease_med_voc() {
		return c3n_fb_finance_lease_med_voc;
	}
	public void setC3n_fb_finance_lease_med_voc(BigDecimal c3n_fb_finance_lease_med_voc) {
		this.c3n_fb_finance_lease_med_voc = c3n_fb_finance_lease_med_voc;
	}
	public BigDecimal getC4n_fb_oper_lease_med_voc() {
		return c4n_fb_oper_lease_med_voc;
	}
	public void setC4n_fb_oper_lease_med_voc(BigDecimal c4n_fb_oper_lease_med_voc) {
		this.c4n_fb_oper_lease_med_voc = c4n_fb_oper_lease_med_voc;
	}
	public BigDecimal getC5n_fb_loans_med_voc() {
		return c5n_fb_loans_med_voc;
	}
	public void setC5n_fb_loans_med_voc(BigDecimal c5n_fb_loans_med_voc) {
		this.c5n_fb_loans_med_voc = c5n_fb_loans_med_voc;
	}
	public BigDecimal getC6n_fb_factoring_med_voc() {
		return c6n_fb_factoring_med_voc;
	}
	public void setC6n_fb_factoring_med_voc(BigDecimal c6n_fb_factoring_med_voc) {
		this.c6n_fb_factoring_med_voc = c6n_fb_factoring_med_voc;
	}
	public BigDecimal getC7n_fb_others_med_voc() {
		return c7n_fb_others_med_voc;
	}
	public void setC7n_fb_others_med_voc(BigDecimal c7n_fb_others_med_voc) {
		this.c7n_fb_others_med_voc = c7n_fb_others_med_voc;
	}
	public BigDecimal getC8n_lending_non_fund_based_med_voc() {
		return c8n_lending_non_fund_based_med_voc;
	}
	public void setC8n_lending_non_fund_based_med_voc(BigDecimal c8n_lending_non_fund_based_med_voc) {
		this.c8n_lending_non_fund_based_med_voc = c8n_lending_non_fund_based_med_voc;
	}
	public BigDecimal getC9n_credit_cards_med_voc() {
		return c9n_credit_cards_med_voc;
	}
	public void setC9n_credit_cards_med_voc(BigDecimal c9n_credit_cards_med_voc) {
		this.c9n_credit_cards_med_voc = c9n_credit_cards_med_voc;
	}
	public BigDecimal getC10n_merchang_pos_med_voc() {
		return c10n_merchang_pos_med_voc;
	}
	public void setC10n_merchang_pos_med_voc(BigDecimal c10n_merchang_pos_med_voc) {
		this.c10n_merchang_pos_med_voc = c10n_merchang_pos_med_voc;
	}
	public BigDecimal getC11n_other_prod_services_med_voc() {
		return c11n_other_prod_services_med_voc;
	}
	public void setC11n_other_prod_services_med_voc(BigDecimal c11n_other_prod_services_med_voc) {
		this.c11n_other_prod_services_med_voc = c11n_other_prod_services_med_voc;
	}
	public BigDecimal getC12n_retirement_saving_schemes_med_voc() {
		return c12n_retirement_saving_schemes_med_voc;
	}
	public void setC12n_retirement_saving_schemes_med_voc(BigDecimal c12n_retirement_saving_schemes_med_voc) {
		this.c12n_retirement_saving_schemes_med_voc = c12n_retirement_saving_schemes_med_voc;
	}
	public BigDecimal getC1o_dep_tkg_services_low_nod() {
		return c1o_dep_tkg_services_low_nod;
	}
	public void setC1o_dep_tkg_services_low_nod(BigDecimal c1o_dep_tkg_services_low_nod) {
		this.c1o_dep_tkg_services_low_nod = c1o_dep_tkg_services_low_nod;
	}
	public BigDecimal getC2o_lend_fund_based_low_nod() {
		return c2o_lend_fund_based_low_nod;
	}
	public void setC2o_lend_fund_based_low_nod(BigDecimal c2o_lend_fund_based_low_nod) {
		this.c2o_lend_fund_based_low_nod = c2o_lend_fund_based_low_nod;
	}
	public BigDecimal getC3o_fb_finance_lease_low_nod() {
		return c3o_fb_finance_lease_low_nod;
	}
	public void setC3o_fb_finance_lease_low_nod(BigDecimal c3o_fb_finance_lease_low_nod) {
		this.c3o_fb_finance_lease_low_nod = c3o_fb_finance_lease_low_nod;
	}
	public BigDecimal getC4o_fb_oper_lease_low_nod() {
		return c4o_fb_oper_lease_low_nod;
	}
	public void setC4o_fb_oper_lease_low_nod(BigDecimal c4o_fb_oper_lease_low_nod) {
		this.c4o_fb_oper_lease_low_nod = c4o_fb_oper_lease_low_nod;
	}
	public BigDecimal getC5o_fb_loans_low_nod() {
		return c5o_fb_loans_low_nod;
	}
	public void setC5o_fb_loans_low_nod(BigDecimal c5o_fb_loans_low_nod) {
		this.c5o_fb_loans_low_nod = c5o_fb_loans_low_nod;
	}
	public BigDecimal getC6o_fb_factoring_low_nod() {
		return c6o_fb_factoring_low_nod;
	}
	public void setC6o_fb_factoring_low_nod(BigDecimal c6o_fb_factoring_low_nod) {
		this.c6o_fb_factoring_low_nod = c6o_fb_factoring_low_nod;
	}
	public BigDecimal getC7o_fb_others_low_nod() {
		return c7o_fb_others_low_nod;
	}
	public void setC7o_fb_others_low_nod(BigDecimal c7o_fb_others_low_nod) {
		this.c7o_fb_others_low_nod = c7o_fb_others_low_nod;
	}
	public BigDecimal getC8o_lending_non_fund_based_low_nod() {
		return c8o_lending_non_fund_based_low_nod;
	}
	public void setC8o_lending_non_fund_based_low_nod(BigDecimal c8o_lending_non_fund_based_low_nod) {
		this.c8o_lending_non_fund_based_low_nod = c8o_lending_non_fund_based_low_nod;
	}
	public BigDecimal getC9o_credit_cards_low_nod() {
		return c9o_credit_cards_low_nod;
	}
	public void setC9o_credit_cards_low_nod(BigDecimal c9o_credit_cards_low_nod) {
		this.c9o_credit_cards_low_nod = c9o_credit_cards_low_nod;
	}
	public BigDecimal getC10o_merchang_pos_low_nod() {
		return c10o_merchang_pos_low_nod;
	}
	public void setC10o_merchang_pos_low_nod(BigDecimal c10o_merchang_pos_low_nod) {
		this.c10o_merchang_pos_low_nod = c10o_merchang_pos_low_nod;
	}
	public BigDecimal getC11o_other_prod_services_low_nod() {
		return c11o_other_prod_services_low_nod;
	}
	public void setC11o_other_prod_services_low_nod(BigDecimal c11o_other_prod_services_low_nod) {
		this.c11o_other_prod_services_low_nod = c11o_other_prod_services_low_nod;
	}
	public BigDecimal getC12o_retirement_saving_schemes_low_nod() {
		return c12o_retirement_saving_schemes_low_nod;
	}
	public void setC12o_retirement_saving_schemes_low_nod(BigDecimal c12o_retirement_saving_schemes_low_nod) {
		this.c12o_retirement_saving_schemes_low_nod = c12o_retirement_saving_schemes_low_nod;
	}
	public BigDecimal getC1p_dep_tkg_services_low_vod() {
		return c1p_dep_tkg_services_low_vod;
	}
	public void setC1p_dep_tkg_services_low_vod(BigDecimal c1p_dep_tkg_services_low_vod) {
		this.c1p_dep_tkg_services_low_vod = c1p_dep_tkg_services_low_vod;
	}
	public BigDecimal getC2p_lend_fund_based_low_vod() {
		return c2p_lend_fund_based_low_vod;
	}
	public void setC2p_lend_fund_based_low_vod(BigDecimal c2p_lend_fund_based_low_vod) {
		this.c2p_lend_fund_based_low_vod = c2p_lend_fund_based_low_vod;
	}
	public BigDecimal getC3p_fb_finance_lease_low_vod() {
		return c3p_fb_finance_lease_low_vod;
	}
	public void setC3p_fb_finance_lease_low_vod(BigDecimal c3p_fb_finance_lease_low_vod) {
		this.c3p_fb_finance_lease_low_vod = c3p_fb_finance_lease_low_vod;
	}
	public BigDecimal getC4p_fb_oper_lease_low_vod() {
		return c4p_fb_oper_lease_low_vod;
	}
	public void setC4p_fb_oper_lease_low_vod(BigDecimal c4p_fb_oper_lease_low_vod) {
		this.c4p_fb_oper_lease_low_vod = c4p_fb_oper_lease_low_vod;
	}
	public BigDecimal getC5p_fb_loans_low_vod() {
		return c5p_fb_loans_low_vod;
	}
	public void setC5p_fb_loans_low_vod(BigDecimal c5p_fb_loans_low_vod) {
		this.c5p_fb_loans_low_vod = c5p_fb_loans_low_vod;
	}
	public BigDecimal getC6p_fb_factoring_low_vod() {
		return c6p_fb_factoring_low_vod;
	}
	public void setC6p_fb_factoring_low_vod(BigDecimal c6p_fb_factoring_low_vod) {
		this.c6p_fb_factoring_low_vod = c6p_fb_factoring_low_vod;
	}
	public BigDecimal getC7p_fb_others_low_vod() {
		return c7p_fb_others_low_vod;
	}
	public void setC7p_fb_others_low_vod(BigDecimal c7p_fb_others_low_vod) {
		this.c7p_fb_others_low_vod = c7p_fb_others_low_vod;
	}
	public BigDecimal getC8p_lending_non_fund_based_low_vod() {
		return c8p_lending_non_fund_based_low_vod;
	}
	public void setC8p_lending_non_fund_based_low_vod(BigDecimal c8p_lending_non_fund_based_low_vod) {
		this.c8p_lending_non_fund_based_low_vod = c8p_lending_non_fund_based_low_vod;
	}
	public BigDecimal getC9p_credit_cards_low_vod() {
		return c9p_credit_cards_low_vod;
	}
	public void setC9p_credit_cards_low_vod(BigDecimal c9p_credit_cards_low_vod) {
		this.c9p_credit_cards_low_vod = c9p_credit_cards_low_vod;
	}
	public BigDecimal getC10p_merchang_pos_low_vod() {
		return c10p_merchang_pos_low_vod;
	}
	public void setC10p_merchang_pos_low_vod(BigDecimal c10p_merchang_pos_low_vod) {
		this.c10p_merchang_pos_low_vod = c10p_merchang_pos_low_vod;
	}
	public BigDecimal getC11p_other_prod_services_low_vod() {
		return c11p_other_prod_services_low_vod;
	}
	public void setC11p_other_prod_services_low_vod(BigDecimal c11p_other_prod_services_low_vod) {
		this.c11p_other_prod_services_low_vod = c11p_other_prod_services_low_vod;
	}
	public BigDecimal getC12p_retirement_saving_schemes_low_vod() {
		return c12p_retirement_saving_schemes_low_vod;
	}
	public void setC12p_retirement_saving_schemes_low_vod(BigDecimal c12p_retirement_saving_schemes_low_vod) {
		this.c12p_retirement_saving_schemes_low_vod = c12p_retirement_saving_schemes_low_vod;
	}
	public BigDecimal getC1q_dep_tkg_services_low_noc() {
		return c1q_dep_tkg_services_low_noc;
	}
	public void setC1q_dep_tkg_services_low_noc(BigDecimal c1q_dep_tkg_services_low_noc) {
		this.c1q_dep_tkg_services_low_noc = c1q_dep_tkg_services_low_noc;
	}
	public BigDecimal getC2q_lend_fund_based_low_noc() {
		return c2q_lend_fund_based_low_noc;
	}
	public void setC2q_lend_fund_based_low_noc(BigDecimal c2q_lend_fund_based_low_noc) {
		this.c2q_lend_fund_based_low_noc = c2q_lend_fund_based_low_noc;
	}
	public BigDecimal getC3q_fb_finance_lease_low_noc() {
		return c3q_fb_finance_lease_low_noc;
	}
	public void setC3q_fb_finance_lease_low_noc(BigDecimal c3q_fb_finance_lease_low_noc) {
		this.c3q_fb_finance_lease_low_noc = c3q_fb_finance_lease_low_noc;
	}
	public BigDecimal getC4q_fb_oper_lease_low_noc() {
		return c4q_fb_oper_lease_low_noc;
	}
	public void setC4q_fb_oper_lease_low_noc(BigDecimal c4q_fb_oper_lease_low_noc) {
		this.c4q_fb_oper_lease_low_noc = c4q_fb_oper_lease_low_noc;
	}
	public BigDecimal getC5q_fb_loans_low_noc() {
		return c5q_fb_loans_low_noc;
	}
	public void setC5q_fb_loans_low_noc(BigDecimal c5q_fb_loans_low_noc) {
		this.c5q_fb_loans_low_noc = c5q_fb_loans_low_noc;
	}
	public BigDecimal getC6q_fb_factoring_low_noc() {
		return c6q_fb_factoring_low_noc;
	}
	public void setC6q_fb_factoring_low_noc(BigDecimal c6q_fb_factoring_low_noc) {
		this.c6q_fb_factoring_low_noc = c6q_fb_factoring_low_noc;
	}
	public BigDecimal getC7q_fb_others_low_noc() {
		return c7q_fb_others_low_noc;
	}
	public void setC7q_fb_others_low_noc(BigDecimal c7q_fb_others_low_noc) {
		this.c7q_fb_others_low_noc = c7q_fb_others_low_noc;
	}
	public BigDecimal getC8q_lending_non_fund_based_low_noc() {
		return c8q_lending_non_fund_based_low_noc;
	}
	public void setC8q_lending_non_fund_based_low_noc(BigDecimal c8q_lending_non_fund_based_low_noc) {
		this.c8q_lending_non_fund_based_low_noc = c8q_lending_non_fund_based_low_noc;
	}
	public BigDecimal getC9q_credit_cards_low_noc() {
		return c9q_credit_cards_low_noc;
	}
	public void setC9q_credit_cards_low_noc(BigDecimal c9q_credit_cards_low_noc) {
		this.c9q_credit_cards_low_noc = c9q_credit_cards_low_noc;
	}
	public BigDecimal getC10q_merchang_pos_low_noc() {
		return c10q_merchang_pos_low_noc;
	}
	public void setC10q_merchang_pos_low_noc(BigDecimal c10q_merchang_pos_low_noc) {
		this.c10q_merchang_pos_low_noc = c10q_merchang_pos_low_noc;
	}
	public BigDecimal getC11q_other_prod_services_low_noc() {
		return c11q_other_prod_services_low_noc;
	}
	public void setC11q_other_prod_services_low_noc(BigDecimal c11q_other_prod_services_low_noc) {
		this.c11q_other_prod_services_low_noc = c11q_other_prod_services_low_noc;
	}
	public BigDecimal getC12q_retirement_saving_schemes_low_noc() {
		return c12q_retirement_saving_schemes_low_noc;
	}
	public void setC12q_retirement_saving_schemes_low_noc(BigDecimal c12q_retirement_saving_schemes_low_noc) {
		this.c12q_retirement_saving_schemes_low_noc = c12q_retirement_saving_schemes_low_noc;
	}
	public BigDecimal getC1r_dep_tkg_services_low_voc() {
		return c1r_dep_tkg_services_low_voc;
	}
	public void setC1r_dep_tkg_services_low_voc(BigDecimal c1r_dep_tkg_services_low_voc) {
		this.c1r_dep_tkg_services_low_voc = c1r_dep_tkg_services_low_voc;
	}
	public BigDecimal getC2r_lend_fund_based_low_voc() {
		return c2r_lend_fund_based_low_voc;
	}
	public void setC2r_lend_fund_based_low_voc(BigDecimal c2r_lend_fund_based_low_voc) {
		this.c2r_lend_fund_based_low_voc = c2r_lend_fund_based_low_voc;
	}
	public BigDecimal getC3r_fb_finance_lease_low_voc() {
		return c3r_fb_finance_lease_low_voc;
	}
	public void setC3r_fb_finance_lease_low_voc(BigDecimal c3r_fb_finance_lease_low_voc) {
		this.c3r_fb_finance_lease_low_voc = c3r_fb_finance_lease_low_voc;
	}
	public BigDecimal getC4r_fb_oper_lease_low_voc() {
		return c4r_fb_oper_lease_low_voc;
	}
	public void setC4r_fb_oper_lease_low_voc(BigDecimal c4r_fb_oper_lease_low_voc) {
		this.c4r_fb_oper_lease_low_voc = c4r_fb_oper_lease_low_voc;
	}
	public BigDecimal getC5r_fb_loans_low_voc() {
		return c5r_fb_loans_low_voc;
	}
	public void setC5r_fb_loans_low_voc(BigDecimal c5r_fb_loans_low_voc) {
		this.c5r_fb_loans_low_voc = c5r_fb_loans_low_voc;
	}
	public BigDecimal getC6r_fb_factoring_low_voc() {
		return c6r_fb_factoring_low_voc;
	}
	public void setC6r_fb_factoring_low_voc(BigDecimal c6r_fb_factoring_low_voc) {
		this.c6r_fb_factoring_low_voc = c6r_fb_factoring_low_voc;
	}
	public BigDecimal getC7r_fb_others_low_voc() {
		return c7r_fb_others_low_voc;
	}
	public void setC7r_fb_others_low_voc(BigDecimal c7r_fb_others_low_voc) {
		this.c7r_fb_others_low_voc = c7r_fb_others_low_voc;
	}
	public BigDecimal getC8r_lending_non_fund_based_low_voc() {
		return c8r_lending_non_fund_based_low_voc;
	}
	public void setC8r_lending_non_fund_based_low_voc(BigDecimal c8r_lending_non_fund_based_low_voc) {
		this.c8r_lending_non_fund_based_low_voc = c8r_lending_non_fund_based_low_voc;
	}
	public BigDecimal getC9r_credit_cards_low_voc() {
		return c9r_credit_cards_low_voc;
	}
	public void setC9r_credit_cards_low_voc(BigDecimal c9r_credit_cards_low_voc) {
		this.c9r_credit_cards_low_voc = c9r_credit_cards_low_voc;
	}
	public BigDecimal getC10r_merchang_pos_low_voc() {
		return c10r_merchang_pos_low_voc;
	}
	public void setC10r_merchang_pos_low_voc(BigDecimal c10r_merchang_pos_low_voc) {
		this.c10r_merchang_pos_low_voc = c10r_merchang_pos_low_voc;
	}
	public BigDecimal getC11r_other_prod_services_low_voc() {
		return c11r_other_prod_services_low_voc;
	}
	public void setC11r_other_prod_services_low_voc(BigDecimal c11r_other_prod_services_low_voc) {
		this.c11r_other_prod_services_low_voc = c11r_other_prod_services_low_voc;
	}
	public BigDecimal getC12r_retirement_saving_schemes_low_voc() {
		return c12r_retirement_saving_schemes_low_voc;
	}
	public void setC12r_retirement_saving_schemes_low_voc(BigDecimal c12r_retirement_saving_schemes_low_voc) {
		this.c12r_retirement_saving_schemes_low_voc = c12r_retirement_saving_schemes_low_voc;
	}
	public BigDecimal getC1s_dep_tkg_services_wic_nod() {
		return c1s_dep_tkg_services_wic_nod;
	}
	public void setC1s_dep_tkg_services_wic_nod(BigDecimal c1s_dep_tkg_services_wic_nod) {
		this.c1s_dep_tkg_services_wic_nod = c1s_dep_tkg_services_wic_nod;
	}
	public BigDecimal getC2s_lend_fund_based_wic_nod() {
		return c2s_lend_fund_based_wic_nod;
	}
	public void setC2s_lend_fund_based_wic_nod(BigDecimal c2s_lend_fund_based_wic_nod) {
		this.c2s_lend_fund_based_wic_nod = c2s_lend_fund_based_wic_nod;
	}
	public BigDecimal getC3s_fb_finance_lease_wic_nod() {
		return c3s_fb_finance_lease_wic_nod;
	}
	public void setC3s_fb_finance_lease_wic_nod(BigDecimal c3s_fb_finance_lease_wic_nod) {
		this.c3s_fb_finance_lease_wic_nod = c3s_fb_finance_lease_wic_nod;
	}
	public BigDecimal getC4s_fb_oper_lease_wic_nod() {
		return c4s_fb_oper_lease_wic_nod;
	}
	public void setC4s_fb_oper_lease_wic_nod(BigDecimal c4s_fb_oper_lease_wic_nod) {
		this.c4s_fb_oper_lease_wic_nod = c4s_fb_oper_lease_wic_nod;
	}
	public BigDecimal getC5s_fb_loans_wic_nod() {
		return c5s_fb_loans_wic_nod;
	}
	public void setC5s_fb_loans_wic_nod(BigDecimal c5s_fb_loans_wic_nod) {
		this.c5s_fb_loans_wic_nod = c5s_fb_loans_wic_nod;
	}
	public BigDecimal getC6s_fb_factoring_wic_nod() {
		return c6s_fb_factoring_wic_nod;
	}
	public void setC6s_fb_factoring_wic_nod(BigDecimal c6s_fb_factoring_wic_nod) {
		this.c6s_fb_factoring_wic_nod = c6s_fb_factoring_wic_nod;
	}
	public BigDecimal getC7s_fb_others_wic_nod() {
		return c7s_fb_others_wic_nod;
	}
	public void setC7s_fb_others_wic_nod(BigDecimal c7s_fb_others_wic_nod) {
		this.c7s_fb_others_wic_nod = c7s_fb_others_wic_nod;
	}
	public BigDecimal getC8s_lending_non_fund_based_wic_nod() {
		return c8s_lending_non_fund_based_wic_nod;
	}
	public void setC8s_lending_non_fund_based_wic_nod(BigDecimal c8s_lending_non_fund_based_wic_nod) {
		this.c8s_lending_non_fund_based_wic_nod = c8s_lending_non_fund_based_wic_nod;
	}
	public BigDecimal getC9s_credit_cards_wic_nod() {
		return c9s_credit_cards_wic_nod;
	}
	public void setC9s_credit_cards_wic_nod(BigDecimal c9s_credit_cards_wic_nod) {
		this.c9s_credit_cards_wic_nod = c9s_credit_cards_wic_nod;
	}
	public BigDecimal getC10s_merchang_pos_wic_nod() {
		return c10s_merchang_pos_wic_nod;
	}
	public void setC10s_merchang_pos_wic_nod(BigDecimal c10s_merchang_pos_wic_nod) {
		this.c10s_merchang_pos_wic_nod = c10s_merchang_pos_wic_nod;
	}
	public BigDecimal getC11s_other_prod_services_wic_nod() {
		return c11s_other_prod_services_wic_nod;
	}
	public void setC11s_other_prod_services_wic_nod(BigDecimal c11s_other_prod_services_wic_nod) {
		this.c11s_other_prod_services_wic_nod = c11s_other_prod_services_wic_nod;
	}
	public BigDecimal getC12s_retirement_saving_schemes_wic_nod() {
		return c12s_retirement_saving_schemes_wic_nod;
	}
	public void setC12s_retirement_saving_schemes_wic_nod(BigDecimal c12s_retirement_saving_schemes_wic_nod) {
		this.c12s_retirement_saving_schemes_wic_nod = c12s_retirement_saving_schemes_wic_nod;
	}
	public BigDecimal getC1t_dep_tkg_services_wic_vod() {
		return c1t_dep_tkg_services_wic_vod;
	}
	public void setC1t_dep_tkg_services_wic_vod(BigDecimal c1t_dep_tkg_services_wic_vod) {
		this.c1t_dep_tkg_services_wic_vod = c1t_dep_tkg_services_wic_vod;
	}
	public BigDecimal getC2t_lend_fund_based_wic_vod() {
		return c2t_lend_fund_based_wic_vod;
	}
	public void setC2t_lend_fund_based_wic_vod(BigDecimal c2t_lend_fund_based_wic_vod) {
		this.c2t_lend_fund_based_wic_vod = c2t_lend_fund_based_wic_vod;
	}
	public BigDecimal getC3t_fb_finance_lease_wic_vod() {
		return c3t_fb_finance_lease_wic_vod;
	}
	public void setC3t_fb_finance_lease_wic_vod(BigDecimal c3t_fb_finance_lease_wic_vod) {
		this.c3t_fb_finance_lease_wic_vod = c3t_fb_finance_lease_wic_vod;
	}
	public BigDecimal getC4t_fb_oper_lease_wic_vod() {
		return c4t_fb_oper_lease_wic_vod;
	}
	public void setC4t_fb_oper_lease_wic_vod(BigDecimal c4t_fb_oper_lease_wic_vod) {
		this.c4t_fb_oper_lease_wic_vod = c4t_fb_oper_lease_wic_vod;
	}
	public BigDecimal getC5t_fb_loans_wic_vod() {
		return c5t_fb_loans_wic_vod;
	}
	public void setC5t_fb_loans_wic_vod(BigDecimal c5t_fb_loans_wic_vod) {
		this.c5t_fb_loans_wic_vod = c5t_fb_loans_wic_vod;
	}
	public BigDecimal getC6t_fb_factoring_wic_vod() {
		return c6t_fb_factoring_wic_vod;
	}
	public void setC6t_fb_factoring_wic_vod(BigDecimal c6t_fb_factoring_wic_vod) {
		this.c6t_fb_factoring_wic_vod = c6t_fb_factoring_wic_vod;
	}
	public BigDecimal getC7t_fb_others_wic_vod() {
		return c7t_fb_others_wic_vod;
	}
	public void setC7t_fb_others_wic_vod(BigDecimal c7t_fb_others_wic_vod) {
		this.c7t_fb_others_wic_vod = c7t_fb_others_wic_vod;
	}
	public BigDecimal getC8t_lending_non_fund_based_wic_vod() {
		return c8t_lending_non_fund_based_wic_vod;
	}
	public void setC8t_lending_non_fund_based_wic_vod(BigDecimal c8t_lending_non_fund_based_wic_vod) {
		this.c8t_lending_non_fund_based_wic_vod = c8t_lending_non_fund_based_wic_vod;
	}
	public BigDecimal getC9t_credit_cards_wic_vod() {
		return c9t_credit_cards_wic_vod;
	}
	public void setC9t_credit_cards_wic_vod(BigDecimal c9t_credit_cards_wic_vod) {
		this.c9t_credit_cards_wic_vod = c9t_credit_cards_wic_vod;
	}
	public BigDecimal getC10t_merchang_pos_wic_vod() {
		return c10t_merchang_pos_wic_vod;
	}
	public void setC10t_merchang_pos_wic_vod(BigDecimal c10t_merchang_pos_wic_vod) {
		this.c10t_merchang_pos_wic_vod = c10t_merchang_pos_wic_vod;
	}
	public BigDecimal getC11t_other_prod_services_wic_vod() {
		return c11t_other_prod_services_wic_vod;
	}
	public void setC11t_other_prod_services_wic_vod(BigDecimal c11t_other_prod_services_wic_vod) {
		this.c11t_other_prod_services_wic_vod = c11t_other_prod_services_wic_vod;
	}
	public BigDecimal getC12t_retirement_saving_schemes_wic_vod() {
		return c12t_retirement_saving_schemes_wic_vod;
	}
	public void setC12t_retirement_saving_schemes_wic_vod(BigDecimal c12t_retirement_saving_schemes_wic_vod) {
		this.c12t_retirement_saving_schemes_wic_vod = c12t_retirement_saving_schemes_wic_vod;
	}
	public BigDecimal getC1u_dep_tkg_services_wic_noc() {
		return c1u_dep_tkg_services_wic_noc;
	}
	public void setC1u_dep_tkg_services_wic_noc(BigDecimal c1u_dep_tkg_services_wic_noc) {
		this.c1u_dep_tkg_services_wic_noc = c1u_dep_tkg_services_wic_noc;
	}
	public BigDecimal getC2u_lend_fund_based_wic_noc() {
		return c2u_lend_fund_based_wic_noc;
	}
	public void setC2u_lend_fund_based_wic_noc(BigDecimal c2u_lend_fund_based_wic_noc) {
		this.c2u_lend_fund_based_wic_noc = c2u_lend_fund_based_wic_noc;
	}
	public BigDecimal getC3u_fb_finance_lease_wic_noc() {
		return c3u_fb_finance_lease_wic_noc;
	}
	public void setC3u_fb_finance_lease_wic_noc(BigDecimal c3u_fb_finance_lease_wic_noc) {
		this.c3u_fb_finance_lease_wic_noc = c3u_fb_finance_lease_wic_noc;
	}
	public BigDecimal getC4u_fb_oper_lease_wic_noc() {
		return c4u_fb_oper_lease_wic_noc;
	}
	public void setC4u_fb_oper_lease_wic_noc(BigDecimal c4u_fb_oper_lease_wic_noc) {
		this.c4u_fb_oper_lease_wic_noc = c4u_fb_oper_lease_wic_noc;
	}
	public BigDecimal getC5u_fb_loans_wic_noc() {
		return c5u_fb_loans_wic_noc;
	}
	public void setC5u_fb_loans_wic_noc(BigDecimal c5u_fb_loans_wic_noc) {
		this.c5u_fb_loans_wic_noc = c5u_fb_loans_wic_noc;
	}
	public BigDecimal getC6u_fb_factoring_wic_noc() {
		return c6u_fb_factoring_wic_noc;
	}
	public void setC6u_fb_factoring_wic_noc(BigDecimal c6u_fb_factoring_wic_noc) {
		this.c6u_fb_factoring_wic_noc = c6u_fb_factoring_wic_noc;
	}
	public BigDecimal getC7u_fb_others_wic_noc() {
		return c7u_fb_others_wic_noc;
	}
	public void setC7u_fb_others_wic_noc(BigDecimal c7u_fb_others_wic_noc) {
		this.c7u_fb_others_wic_noc = c7u_fb_others_wic_noc;
	}
	public BigDecimal getC8u_lending_non_fund_based_wic_noc() {
		return c8u_lending_non_fund_based_wic_noc;
	}
	public void setC8u_lending_non_fund_based_wic_noc(BigDecimal c8u_lending_non_fund_based_wic_noc) {
		this.c8u_lending_non_fund_based_wic_noc = c8u_lending_non_fund_based_wic_noc;
	}
	public BigDecimal getC9u_credit_cards_wic_noc() {
		return c9u_credit_cards_wic_noc;
	}
	public void setC9u_credit_cards_wic_noc(BigDecimal c9u_credit_cards_wic_noc) {
		this.c9u_credit_cards_wic_noc = c9u_credit_cards_wic_noc;
	}
	public BigDecimal getC10u_merchang_pos_wic_noc() {
		return c10u_merchang_pos_wic_noc;
	}
	public void setC10u_merchang_pos_wic_noc(BigDecimal c10u_merchang_pos_wic_noc) {
		this.c10u_merchang_pos_wic_noc = c10u_merchang_pos_wic_noc;
	}
	public BigDecimal getC11u_other_prod_services_wic_noc() {
		return c11u_other_prod_services_wic_noc;
	}
	public void setC11u_other_prod_services_wic_noc(BigDecimal c11u_other_prod_services_wic_noc) {
		this.c11u_other_prod_services_wic_noc = c11u_other_prod_services_wic_noc;
	}
	public BigDecimal getC12u_retirement_saving_schemes_wic_noc() {
		return c12u_retirement_saving_schemes_wic_noc;
	}
	public void setC12u_retirement_saving_schemes_wic_noc(BigDecimal c12u_retirement_saving_schemes_wic_noc) {
		this.c12u_retirement_saving_schemes_wic_noc = c12u_retirement_saving_schemes_wic_noc;
	}
	public BigDecimal getC1v_dep_tkg_services_wic_voc() {
		return c1v_dep_tkg_services_wic_voc;
	}
	public void setC1v_dep_tkg_services_wic_voc(BigDecimal c1v_dep_tkg_services_wic_voc) {
		this.c1v_dep_tkg_services_wic_voc = c1v_dep_tkg_services_wic_voc;
	}
	public BigDecimal getC2v_lend_fund_based_wic_voc() {
		return c2v_lend_fund_based_wic_voc;
	}
	public void setC2v_lend_fund_based_wic_voc(BigDecimal c2v_lend_fund_based_wic_voc) {
		this.c2v_lend_fund_based_wic_voc = c2v_lend_fund_based_wic_voc;
	}
	public BigDecimal getC3v_fb_finance_lease_wic_voc() {
		return c3v_fb_finance_lease_wic_voc;
	}
	public void setC3v_fb_finance_lease_wic_voc(BigDecimal c3v_fb_finance_lease_wic_voc) {
		this.c3v_fb_finance_lease_wic_voc = c3v_fb_finance_lease_wic_voc;
	}
	public BigDecimal getC4v_fb_oper_lease_wic_voc() {
		return c4v_fb_oper_lease_wic_voc;
	}
	public void setC4v_fb_oper_lease_wic_voc(BigDecimal c4v_fb_oper_lease_wic_voc) {
		this.c4v_fb_oper_lease_wic_voc = c4v_fb_oper_lease_wic_voc;
	}
	public BigDecimal getC5v_fb_loans_wic_voc() {
		return c5v_fb_loans_wic_voc;
	}
	public void setC5v_fb_loans_wic_voc(BigDecimal c5v_fb_loans_wic_voc) {
		this.c5v_fb_loans_wic_voc = c5v_fb_loans_wic_voc;
	}
	public BigDecimal getC6v_fb_factoring_wic_voc() {
		return c6v_fb_factoring_wic_voc;
	}
	public void setC6v_fb_factoring_wic_voc(BigDecimal c6v_fb_factoring_wic_voc) {
		this.c6v_fb_factoring_wic_voc = c6v_fb_factoring_wic_voc;
	}
	public BigDecimal getC7v_fb_others_wic_voc() {
		return c7v_fb_others_wic_voc;
	}
	public void setC7v_fb_others_wic_voc(BigDecimal c7v_fb_others_wic_voc) {
		this.c7v_fb_others_wic_voc = c7v_fb_others_wic_voc;
	}
	public BigDecimal getC8v_lending_non_fund_based_wic_voc() {
		return c8v_lending_non_fund_based_wic_voc;
	}
	public void setC8v_lending_non_fund_based_wic_voc(BigDecimal c8v_lending_non_fund_based_wic_voc) {
		this.c8v_lending_non_fund_based_wic_voc = c8v_lending_non_fund_based_wic_voc;
	}
	public BigDecimal getC9v_credit_cards_wic_voc() {
		return c9v_credit_cards_wic_voc;
	}
	public void setC9v_credit_cards_wic_voc(BigDecimal c9v_credit_cards_wic_voc) {
		this.c9v_credit_cards_wic_voc = c9v_credit_cards_wic_voc;
	}
	public BigDecimal getC10v_merchang_pos_wic_voc() {
		return c10v_merchang_pos_wic_voc;
	}
	public void setC10v_merchang_pos_wic_voc(BigDecimal c10v_merchang_pos_wic_voc) {
		this.c10v_merchang_pos_wic_voc = c10v_merchang_pos_wic_voc;
	}
	public BigDecimal getC11v_other_prod_services_wic_voc() {
		return c11v_other_prod_services_wic_voc;
	}
	public void setC11v_other_prod_services_wic_voc(BigDecimal c11v_other_prod_services_wic_voc) {
		this.c11v_other_prod_services_wic_voc = c11v_other_prod_services_wic_voc;
	}
	public BigDecimal getC12v_retirement_saving_schemes_wic_voc() {
		return c12v_retirement_saving_schemes_wic_voc;
	}
	public void setC12v_retirement_saving_schemes_wic_voc(BigDecimal c12v_retirement_saving_schemes_wic_voc) {
		this.c12v_retirement_saving_schemes_wic_voc = c12v_retirement_saving_schemes_wic_voc;
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
	
	
	


}

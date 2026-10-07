package com.bornfire.entity.t19;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T19_TYPES_HITS_TABLE")
public class T19Report {

	private String	d1a_large_cash_trans;
	private String	d2a_cash_in_cash_out_trans;
	private String	d3a_linked_cash_trans;
	private String	d4a_funds_in_funds_out_trans;
	private String	d5a_caution_list_santion_cust;
	private String	d6a_sanction_jurisdiction;
	private String	d7a_trans_non_equi_jurisdiction;
	private String	d8a_trans_peps_high_risk_accts;
	private String	d9a_trans_new_reactivate_accts;
	private String	d10a_trans_minor_accts;
	private String	d11a_all_otr_type_trans;
	private String	d12a_note;

	private BigDecimal	c1j_large_cash_trans_nof_hits;
	private BigDecimal	c2j_cash_in_cash_out_trans_nof_hits;
	private BigDecimal	c3j_linked_cash_trans_nof_hits;
	private BigDecimal	c4j_funds_in_funds_out_trans_nof_hits;
	private BigDecimal	c5j_caution_list_santion_custs_nof_hits;
	private BigDecimal	c6j_sanction_jurispiction_nof_hits;
	private BigDecimal	c7j_trans_non_equi_jurisdiction_nof_hits;
	private BigDecimal	c8j_trans_peps_high_risk_accts_nof_hits;
	private BigDecimal	c9j_trans_new_reactivate_accts_nof_hits;
	private BigDecimal	c10j_trans_minor_accts_nof_hits;
	private BigDecimal	c11j_all_otr_type_trans_nof_hits;
	private String	c12j_note_nof_hits;
	private BigDecimal	c1k_large_cash_trans_nof_phits;
	private BigDecimal	c2k_cash_in_cash_out_trans_nof_phits;
	private BigDecimal	c3k_linked_cash_trans_nof_phits;
	private BigDecimal	c4k_funds_in_funds_out_trans_nof_phits;
	private BigDecimal	c5k_caution_list_santion_custs_nof_phits;
	private BigDecimal	c6k_sanction_jurispiction_nof_phits;
	private BigDecimal	c7k_trans_non_equi_jurisdiction;
	private BigDecimal	c8k_trans_peps_high_risk_accts_nof_phits;
	private BigDecimal	c9k_trans_new_reactivate_accts_nof_phits;
	private BigDecimal	c10k_trans_minor_accts_nof_phits;
	private BigDecimal	c11k_all_otr_type_trans_nof_phits;
	private String	c12k_note_nof_phits;
	private BigDecimal	c1l_large_cash_trans_nof_phitsc;
	private BigDecimal	c2l_cash_in_cash_out_trans_nof_phitsc;
	private BigDecimal	c3l_linkel_cash_trans_nof_phitsc;
	private BigDecimal	c4l_funds_in_funds_out_trans_nof_phitsc;
	private BigDecimal	c5l_caution_list_santion_custs_nof_phitsc;
	private BigDecimal	c6l_sanction_jurispiction_nof_phitsc;
	private BigDecimal	c7l_trans_non_equi_jurisdiction_nof_phitsc;
	private BigDecimal	c8l_trans_peps_high_risk_accts_nof_phitsc;
	private BigDecimal	c9l_trans_new_reactivate_accts_nof_phitsc;
	private BigDecimal	c10l_trans_minor_accts_nof_phitsc;
	private BigDecimal	c11l_all_otr_type_trans_nof_phitsc;
	private String	c12l_note_nof_phitsc;
	private BigDecimal	c1m_largm_cash_trans_nof_str;
	private BigDecimal	c2m_cash_in_cash_out_trans_nof_str;
	private BigDecimal	c3m_linked_cash_trans_nof_str;
	private BigDecimal	c4m_funds_in_funds_out_trans_nof_str;
	private BigDecimal	c5m_caution_list_santion_custs_nof_str;
	private BigDecimal	c6m_sanction_jurispiction_nof_str;
	private BigDecimal	c7m_trans_non_equi_jurisdiction_nof_str;
	private BigDecimal	c8m_trans_peps_high_risk_accts_nof_str;
	private BigDecimal	c9m_trans_new_reactivate_accts_nof_str;
	private BigDecimal	c10m_trans_minor_accts_nof_str;
	private BigDecimal	c11m_all_otr_type_trans_nof_str;
	private String	c12m_note_nof_str;
	private BigDecimal	c1n_large_cash_trans_nof_alerts;
	private BigDecimal	c2n_cash_in_cash_out_trans_nof_alerts;
	private BigDecimal	c3n_linked_cash_trans_nof_alerts;
	private BigDecimal	c4n_funds_in_funds_out_trans_nof_alerts;
	private BigDecimal	c5n_caution_list_santion_custs_nof_alerts;
	private BigDecimal	c6n_sanction_jurispiction_nof_alerts;
	private BigDecimal	c7n_trans_nof_equi_jurisdiction_nof_alerts;
	private BigDecimal	c8n_trans_peps_high_risk_accts_nof_alerts;
	private BigDecimal	c9n_trans_new_reactivate_accts_nof_alerts;
	private BigDecimal	c10n_trans_minor_accts_nof_alerts;
	private BigDecimal	c11n_all_otr_type_trans_nof_alerts;
	private String	c12n_note_nof_alerts;
	private BigDecimal	c1o_large_cash_trans_nof_alerts_nostr;
	private BigDecimal	c2o_cash_in_cash_out_trans_nof_alerts_nostr;
	private BigDecimal	c3o_linked_cash_trans_nof_alerts_nostr;
	private BigDecimal	c4o_funds_in_funds_out_trans_nof_alerts_nostr;
	private BigDecimal	c5o_caution_list_santion_custs_nof_alerts_nostr;
	private BigDecimal	c6o_sanction_jurispiction_nof_alerts_nostr;
	private BigDecimal	c7o_trans_non_equi_jurisdiction_nof_alerts_nostr;
	private BigDecimal	c8o_trans_peps_high_risk_accts_nof_alerts_nostr;
	private BigDecimal	c9o_trans_new_reactivate_accts_nof_alerts_nostr;
	private BigDecimal	c10o_trans_minor_accts_nof_alerts_nostr;
	private BigDecimal	c11o_all_otr_type_trans_nof_alerts_nostr;
	private String	c12o_note_nof_alerts_nostr;

	
	private String	c1p_large_cash_trans_avg_time;
	private String	c2p_casp_in_cash_out_trans_avg_time;
	private String	c3p_linked_cash_trans_avg_time;
	private String	c4p_funds_in_funds_out_trans_avg_time;
	private String	c5p_caution_list_santion_custs_avg_time;
	private String	c6p_sanction_jurispiction_avg_time;
	private String	c7p_trans_non_equi_jurisdiction_avg_time;
	private String	c8p_trans_peps_higp_risk_accts_avg_time;
	private String	c9p_trans_new_reactivate_accts_avg_time;
	private String	c10p_trans_minor_accts_avg_time;
	private String	c11p_all_otr_type_trans_avg_time;

	private String	c12p_note_avg_time;
	private BigDecimal	c1q_large_cash_trans_nof_alerts_str;
	private BigDecimal	c2q_cash_in_cash_out_trans_nof_alerts_str;
	private BigDecimal	c3q_linkeq_cash_trans_nof_alerts_str;
	private BigDecimal	c4q_funds_in_funds_out_trans_nof_alerts_str;
	private BigDecimal	c5q_caution_list_santion_custs_nof_alerts_str;
	private BigDecimal	c6q_sanction_jurispiction_nof_alerts_str;
	private BigDecimal	c7q_trans_non_equi_jurisdiction_nof_alerts_str;
	private BigDecimal	c8q_trans_peps_high_risk_accts_nof_alerts_str;
	private BigDecimal	c9q_trans_new_reactivate_accts_nof_alerts_str;
	private BigDecimal	c10q_trans_minor_accts_nof_alerts_str;
	private BigDecimal	c11q_all_otr_type_trans_nof_alerts_str;
	private String	c12q_note_nof_alerts_str;
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
	private Character	arch_flg;
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
	private Character entity_flg;
	private Character modify_flg;
	private Character del_flg;
	
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public Character getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}
	public Character getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(Character modify_flg) {
		this.modify_flg = modify_flg;
	}
	public Character getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}

	public String getD1a_large_cash_trans() {
		return d1a_large_cash_trans;
	}
	public void setD1a_large_cash_trans(String d1a_large_cash_trans) {
		this.d1a_large_cash_trans = d1a_large_cash_trans;
	}
	public String getD2a_cash_in_cash_out_trans() {
		return d2a_cash_in_cash_out_trans;
	}
	public void setD2a_cash_in_cash_out_trans(String d2a_cash_in_cash_out_trans) {
		this.d2a_cash_in_cash_out_trans = d2a_cash_in_cash_out_trans;
	}
	public String getD3a_linked_cash_trans() {
		return d3a_linked_cash_trans;
	}
	public void setD3a_linked_cash_trans(String d3a_linked_cash_trans) {
		this.d3a_linked_cash_trans = d3a_linked_cash_trans;
	}
	public String getD4a_funds_in_funds_out_trans() {
		return d4a_funds_in_funds_out_trans;
	}
	public void setD4a_funds_in_funds_out_trans(String d4a_funds_in_funds_out_trans) {
		this.d4a_funds_in_funds_out_trans = d4a_funds_in_funds_out_trans;
	}
	public String getD5a_caution_list_santion_cust() {
		return d5a_caution_list_santion_cust;
	}
	public void setD5a_caution_list_santion_cust(String d5a_caution_list_santion_cust) {
		this.d5a_caution_list_santion_cust = d5a_caution_list_santion_cust;
	}
	public String getD6a_sanction_jurisdiction() {
		return d6a_sanction_jurisdiction;
	}
	public void setD6a_sanction_jurisdiction(String d6a_sanction_jurisdiction) {
		this.d6a_sanction_jurisdiction = d6a_sanction_jurisdiction;
	}
	public String getD7a_trans_non_equi_jurisdiction() {
		return d7a_trans_non_equi_jurisdiction;
	}
	public void setD7a_trans_non_equi_jurisdiction(String d7a_trans_non_equi_jurisdiction) {
		this.d7a_trans_non_equi_jurisdiction = d7a_trans_non_equi_jurisdiction;
	}
	public String getD8a_trans_peps_high_risk_accts() {
		return d8a_trans_peps_high_risk_accts;
	}
	public void setD8a_trans_peps_high_risk_accts(String d8a_trans_peps_high_risk_accts) {
		this.d8a_trans_peps_high_risk_accts = d8a_trans_peps_high_risk_accts;
	}
	public String getD9a_trans_new_reactivate_accts() {
		return d9a_trans_new_reactivate_accts;
	}
	public void setD9a_trans_new_reactivate_accts(String d9a_trans_new_reactivate_accts) {
		this.d9a_trans_new_reactivate_accts = d9a_trans_new_reactivate_accts;
	}
	public String getD10a_trans_minor_accts() {
		return d10a_trans_minor_accts;
	}
	public void setD10a_trans_minor_accts(String d10a_trans_minor_accts) {
		this.d10a_trans_minor_accts = d10a_trans_minor_accts;
	}
	public String getD11a_all_otr_type_trans() {
		return d11a_all_otr_type_trans;
	}
	public void setD11a_all_otr_type_trans(String d11a_all_otr_type_trans) {
		this.d11a_all_otr_type_trans = d11a_all_otr_type_trans;
	}
	public String getD12a_note() {
		return d12a_note;
	}
	public void setD12a_note(String d12a_note) {
		this.d12a_note = d12a_note;
	}
	public BigDecimal getC1j_large_cash_trans_nof_hits() {
		return c1j_large_cash_trans_nof_hits;
	}
	public void setC1j_large_cash_trans_nof_hits(BigDecimal c1j_large_cash_trans_nof_hits) {
		this.c1j_large_cash_trans_nof_hits = c1j_large_cash_trans_nof_hits;
	}
	public BigDecimal getC2j_cash_in_cash_out_trans_nof_hits() {
		return c2j_cash_in_cash_out_trans_nof_hits;
	}
	public void setC2j_cash_in_cash_out_trans_nof_hits(BigDecimal c2j_cash_in_cash_out_trans_nof_hits) {
		this.c2j_cash_in_cash_out_trans_nof_hits = c2j_cash_in_cash_out_trans_nof_hits;
	}
	public BigDecimal getC3j_linked_cash_trans_nof_hits() {
		return c3j_linked_cash_trans_nof_hits;
	}
	public void setC3j_linked_cash_trans_nof_hits(BigDecimal c3j_linked_cash_trans_nof_hits) {
		this.c3j_linked_cash_trans_nof_hits = c3j_linked_cash_trans_nof_hits;
	}
	public BigDecimal getC4j_funds_in_funds_out_trans_nof_hits() {
		return c4j_funds_in_funds_out_trans_nof_hits;
	}
	public void setC4j_funds_in_funds_out_trans_nof_hits(BigDecimal c4j_funds_in_funds_out_trans_nof_hits) {
		this.c4j_funds_in_funds_out_trans_nof_hits = c4j_funds_in_funds_out_trans_nof_hits;
	}
	public BigDecimal getC5j_caution_list_santion_custs_nof_hits() {
		return c5j_caution_list_santion_custs_nof_hits;
	}
	public void setC5j_caution_list_santion_custs_nof_hits(BigDecimal c5j_caution_list_santion_custs_nof_hits) {
		this.c5j_caution_list_santion_custs_nof_hits = c5j_caution_list_santion_custs_nof_hits;
	}
	public BigDecimal getC6j_sanction_jurispiction_nof_hits() {
		return c6j_sanction_jurispiction_nof_hits;
	}
	public void setC6j_sanction_jurispiction_nof_hits(BigDecimal c6j_sanction_jurispiction_nof_hits) {
		this.c6j_sanction_jurispiction_nof_hits = c6j_sanction_jurispiction_nof_hits;
	}
	public BigDecimal getC7j_trans_non_equi_jurisdiction_nof_hits() {
		return c7j_trans_non_equi_jurisdiction_nof_hits;
	}
	public void setC7j_trans_non_equi_jurisdiction_nof_hits(BigDecimal c7j_trans_non_equi_jurisdiction_nof_hits) {
		this.c7j_trans_non_equi_jurisdiction_nof_hits = c7j_trans_non_equi_jurisdiction_nof_hits;
	}
	public BigDecimal getC8j_trans_peps_high_risk_accts_nof_hits() {
		return c8j_trans_peps_high_risk_accts_nof_hits;
	}
	public void setC8j_trans_peps_high_risk_accts_nof_hits(BigDecimal c8j_trans_peps_high_risk_accts_nof_hits) {
		this.c8j_trans_peps_high_risk_accts_nof_hits = c8j_trans_peps_high_risk_accts_nof_hits;
	}
	public BigDecimal getC9j_trans_new_reactivate_accts_nof_hits() {
		return c9j_trans_new_reactivate_accts_nof_hits;
	}
	public void setC9j_trans_new_reactivate_accts_nof_hits(BigDecimal c9j_trans_new_reactivate_accts_nof_hits) {
		this.c9j_trans_new_reactivate_accts_nof_hits = c9j_trans_new_reactivate_accts_nof_hits;
	}
	public BigDecimal getC10j_trans_minor_accts_nof_hits() {
		return c10j_trans_minor_accts_nof_hits;
	}
	public void setC10j_trans_minor_accts_nof_hits(BigDecimal c10j_trans_minor_accts_nof_hits) {
		this.c10j_trans_minor_accts_nof_hits = c10j_trans_minor_accts_nof_hits;
	}
	public BigDecimal getC11j_all_otr_type_trans_nof_hits() {
		return c11j_all_otr_type_trans_nof_hits;
	}
	public void setC11j_all_otr_type_trans_nof_hits(BigDecimal c11j_all_otr_type_trans_nof_hits) {
		this.c11j_all_otr_type_trans_nof_hits = c11j_all_otr_type_trans_nof_hits;
	}
	public String getC12j_note_nof_hits() {
		return c12j_note_nof_hits;
	}
	public void setC12j_note_nof_hits(String c12j_note_nof_hits) {
		this.c12j_note_nof_hits = c12j_note_nof_hits;
	}
	public BigDecimal getC1k_large_cash_trans_nof_phits() {
		return c1k_large_cash_trans_nof_phits;
	}
	public void setC1k_large_cash_trans_nof_phits(BigDecimal c1k_large_cash_trans_nof_phits) {
		this.c1k_large_cash_trans_nof_phits = c1k_large_cash_trans_nof_phits;
	}
	public BigDecimal getC2k_cash_in_cash_out_trans_nof_phits() {
		return c2k_cash_in_cash_out_trans_nof_phits;
	}
	public void setC2k_cash_in_cash_out_trans_nof_phits(BigDecimal c2k_cash_in_cash_out_trans_nof_phits) {
		this.c2k_cash_in_cash_out_trans_nof_phits = c2k_cash_in_cash_out_trans_nof_phits;
	}
	public BigDecimal getC3k_linked_cash_trans_nof_phits() {
		return c3k_linked_cash_trans_nof_phits;
	}
	public void setC3k_linked_cash_trans_nof_phits(BigDecimal c3k_linked_cash_trans_nof_phits) {
		this.c3k_linked_cash_trans_nof_phits = c3k_linked_cash_trans_nof_phits;
	}
	public BigDecimal getC4k_funds_in_funds_out_trans_nof_phits() {
		return c4k_funds_in_funds_out_trans_nof_phits;
	}
	public void setC4k_funds_in_funds_out_trans_nof_phits(BigDecimal c4k_funds_in_funds_out_trans_nof_phits) {
		this.c4k_funds_in_funds_out_trans_nof_phits = c4k_funds_in_funds_out_trans_nof_phits;
	}
	public BigDecimal getC5k_caution_list_santion_custs_nof_phits() {
		return c5k_caution_list_santion_custs_nof_phits;
	}
	public void setC5k_caution_list_santion_custs_nof_phits(BigDecimal c5k_caution_list_santion_custs_nof_phits) {
		this.c5k_caution_list_santion_custs_nof_phits = c5k_caution_list_santion_custs_nof_phits;
	}
	public BigDecimal getC6k_sanction_jurispiction_nof_phits() {
		return c6k_sanction_jurispiction_nof_phits;
	}
	public void setC6k_sanction_jurispiction_nof_phits(BigDecimal c6k_sanction_jurispiction_nof_phits) {
		this.c6k_sanction_jurispiction_nof_phits = c6k_sanction_jurispiction_nof_phits;
	}
	public BigDecimal getC7k_trans_non_equi_jurisdiction() {
		return c7k_trans_non_equi_jurisdiction;
	}
	public void setC7k_trans_non_equi_jurisdiction(BigDecimal c7k_trans_non_equi_jurisdiction) {
		this.c7k_trans_non_equi_jurisdiction = c7k_trans_non_equi_jurisdiction;
	}
	public BigDecimal getC8k_trans_peps_high_risk_accts_nof_phits() {
		return c8k_trans_peps_high_risk_accts_nof_phits;
	}
	public void setC8k_trans_peps_high_risk_accts_nof_phits(BigDecimal c8k_trans_peps_high_risk_accts_nof_phits) {
		this.c8k_trans_peps_high_risk_accts_nof_phits = c8k_trans_peps_high_risk_accts_nof_phits;
	}
	public BigDecimal getC9k_trans_new_reactivate_accts_nof_phits() {
		return c9k_trans_new_reactivate_accts_nof_phits;
	}
	public void setC9k_trans_new_reactivate_accts_nof_phits(BigDecimal c9k_trans_new_reactivate_accts_nof_phits) {
		this.c9k_trans_new_reactivate_accts_nof_phits = c9k_trans_new_reactivate_accts_nof_phits;
	}
	public BigDecimal getC10k_trans_minor_accts_nof_phits() {
		return c10k_trans_minor_accts_nof_phits;
	}
	public void setC10k_trans_minor_accts_nof_phits(BigDecimal c10k_trans_minor_accts_nof_phits) {
		this.c10k_trans_minor_accts_nof_phits = c10k_trans_minor_accts_nof_phits;
	}
	public BigDecimal getC11k_all_otr_type_trans_nof_phits() {
		return c11k_all_otr_type_trans_nof_phits;
	}
	public void setC11k_all_otr_type_trans_nof_phits(BigDecimal c11k_all_otr_type_trans_nof_phits) {
		this.c11k_all_otr_type_trans_nof_phits = c11k_all_otr_type_trans_nof_phits;
	}
	public String getC12k_note_nof_phits() {
		return c12k_note_nof_phits;
	}
	public void setC12k_note_nof_phits(String c12k_note_nof_phits) {
		this.c12k_note_nof_phits = c12k_note_nof_phits;
	}
	public BigDecimal getC1l_large_cash_trans_nof_phitsc() {
		return c1l_large_cash_trans_nof_phitsc;
	}
	public void setC1l_large_cash_trans_nof_phitsc(BigDecimal c1l_large_cash_trans_nof_phitsc) {
		this.c1l_large_cash_trans_nof_phitsc = c1l_large_cash_trans_nof_phitsc;
	}
	public BigDecimal getC2l_cash_in_cash_out_trans_nof_phitsc() {
		return c2l_cash_in_cash_out_trans_nof_phitsc;
	}
	public void setC2l_cash_in_cash_out_trans_nof_phitsc(BigDecimal c2l_cash_in_cash_out_trans_nof_phitsc) {
		this.c2l_cash_in_cash_out_trans_nof_phitsc = c2l_cash_in_cash_out_trans_nof_phitsc;
	}
	public BigDecimal getC3l_linkel_cash_trans_nof_phitsc() {
		return c3l_linkel_cash_trans_nof_phitsc;
	}
	public void setC3l_linkel_cash_trans_nof_phitsc(BigDecimal c3l_linkel_cash_trans_nof_phitsc) {
		this.c3l_linkel_cash_trans_nof_phitsc = c3l_linkel_cash_trans_nof_phitsc;
	}
	public BigDecimal getC4l_funds_in_funds_out_trans_nof_phitsc() {
		return c4l_funds_in_funds_out_trans_nof_phitsc;
	}
	public void setC4l_funds_in_funds_out_trans_nof_phitsc(BigDecimal c4l_funds_in_funds_out_trans_nof_phitsc) {
		this.c4l_funds_in_funds_out_trans_nof_phitsc = c4l_funds_in_funds_out_trans_nof_phitsc;
	}
	public BigDecimal getC5l_caution_list_santion_custs_nof_phitsc() {
		return c5l_caution_list_santion_custs_nof_phitsc;
	}
	public void setC5l_caution_list_santion_custs_nof_phitsc(BigDecimal c5l_caution_list_santion_custs_nof_phitsc) {
		this.c5l_caution_list_santion_custs_nof_phitsc = c5l_caution_list_santion_custs_nof_phitsc;
	}
	public BigDecimal getC6l_sanction_jurispiction_nof_phitsc() {
		return c6l_sanction_jurispiction_nof_phitsc;
	}
	public void setC6l_sanction_jurispiction_nof_phitsc(BigDecimal c6l_sanction_jurispiction_nof_phitsc) {
		this.c6l_sanction_jurispiction_nof_phitsc = c6l_sanction_jurispiction_nof_phitsc;
	}
	public BigDecimal getC7l_trans_non_equi_jurisdiction_nof_phitsc() {
		return c7l_trans_non_equi_jurisdiction_nof_phitsc;
	}
	public void setC7l_trans_non_equi_jurisdiction_nof_phitsc(BigDecimal c7l_trans_non_equi_jurisdiction_nof_phitsc) {
		this.c7l_trans_non_equi_jurisdiction_nof_phitsc = c7l_trans_non_equi_jurisdiction_nof_phitsc;
	}
	public BigDecimal getC8l_trans_peps_high_risk_accts_nof_phitsc() {
		return c8l_trans_peps_high_risk_accts_nof_phitsc;
	}
	public void setC8l_trans_peps_high_risk_accts_nof_phitsc(BigDecimal c8l_trans_peps_high_risk_accts_nof_phitsc) {
		this.c8l_trans_peps_high_risk_accts_nof_phitsc = c8l_trans_peps_high_risk_accts_nof_phitsc;
	}
	public BigDecimal getC9l_trans_new_reactivate_accts_nof_phitsc() {
		return c9l_trans_new_reactivate_accts_nof_phitsc;
	}
	public void setC9l_trans_new_reactivate_accts_nof_phitsc(BigDecimal c9l_trans_new_reactivate_accts_nof_phitsc) {
		this.c9l_trans_new_reactivate_accts_nof_phitsc = c9l_trans_new_reactivate_accts_nof_phitsc;
	}
	public BigDecimal getC10l_trans_minor_accts_nof_phitsc() {
		return c10l_trans_minor_accts_nof_phitsc;
	}
	public void setC10l_trans_minor_accts_nof_phitsc(BigDecimal c10l_trans_minor_accts_nof_phitsc) {
		this.c10l_trans_minor_accts_nof_phitsc = c10l_trans_minor_accts_nof_phitsc;
	}
	public BigDecimal getC11l_all_otr_type_trans_nof_phitsc() {
		return c11l_all_otr_type_trans_nof_phitsc;
	}
	public void setC11l_all_otr_type_trans_nof_phitsc(BigDecimal c11l_all_otr_type_trans_nof_phitsc) {
		this.c11l_all_otr_type_trans_nof_phitsc = c11l_all_otr_type_trans_nof_phitsc;
	}
	public String getC12l_note_nof_phitsc() {
		return c12l_note_nof_phitsc;
	}
	public void setC12l_note_nof_phitsc(String c12l_note_nof_phitsc) {
		this.c12l_note_nof_phitsc = c12l_note_nof_phitsc;
	}
	public BigDecimal getC1m_largm_cash_trans_nof_str() {
		return c1m_largm_cash_trans_nof_str;
	}
	public void setC1m_largm_cash_trans_nof_str(BigDecimal c1m_largm_cash_trans_nof_str) {
		this.c1m_largm_cash_trans_nof_str = c1m_largm_cash_trans_nof_str;
	}
	public BigDecimal getC2m_cash_in_cash_out_trans_nof_str() {
		return c2m_cash_in_cash_out_trans_nof_str;
	}
	public void setC2m_cash_in_cash_out_trans_nof_str(BigDecimal c2m_cash_in_cash_out_trans_nof_str) {
		this.c2m_cash_in_cash_out_trans_nof_str = c2m_cash_in_cash_out_trans_nof_str;
	}
	public BigDecimal getC3m_linked_cash_trans_nof_str() {
		return c3m_linked_cash_trans_nof_str;
	}
	public void setC3m_linked_cash_trans_nof_str(BigDecimal c3m_linked_cash_trans_nof_str) {
		this.c3m_linked_cash_trans_nof_str = c3m_linked_cash_trans_nof_str;
	}
	public BigDecimal getC4m_funds_in_funds_out_trans_nof_str() {
		return c4m_funds_in_funds_out_trans_nof_str;
	}
	public void setC4m_funds_in_funds_out_trans_nof_str(BigDecimal c4m_funds_in_funds_out_trans_nof_str) {
		this.c4m_funds_in_funds_out_trans_nof_str = c4m_funds_in_funds_out_trans_nof_str;
	}
	public BigDecimal getC5m_caution_list_santion_custs_nof_str() {
		return c5m_caution_list_santion_custs_nof_str;
	}
	public void setC5m_caution_list_santion_custs_nof_str(BigDecimal c5m_caution_list_santion_custs_nof_str) {
		this.c5m_caution_list_santion_custs_nof_str = c5m_caution_list_santion_custs_nof_str;
	}
	public BigDecimal getC6m_sanction_jurispiction_nof_str() {
		return c6m_sanction_jurispiction_nof_str;
	}
	public void setC6m_sanction_jurispiction_nof_str(BigDecimal c6m_sanction_jurispiction_nof_str) {
		this.c6m_sanction_jurispiction_nof_str = c6m_sanction_jurispiction_nof_str;
	}
	public BigDecimal getC7m_trans_non_equi_jurisdiction_nof_str() {
		return c7m_trans_non_equi_jurisdiction_nof_str;
	}
	public void setC7m_trans_non_equi_jurisdiction_nof_str(BigDecimal c7m_trans_non_equi_jurisdiction_nof_str) {
		this.c7m_trans_non_equi_jurisdiction_nof_str = c7m_trans_non_equi_jurisdiction_nof_str;
	}
	public BigDecimal getC8m_trans_peps_high_risk_accts_nof_str() {
		return c8m_trans_peps_high_risk_accts_nof_str;
	}
	public void setC8m_trans_peps_high_risk_accts_nof_str(BigDecimal c8m_trans_peps_high_risk_accts_nof_str) {
		this.c8m_trans_peps_high_risk_accts_nof_str = c8m_trans_peps_high_risk_accts_nof_str;
	}
	public BigDecimal getC9m_trans_new_reactivate_accts_nof_str() {
		return c9m_trans_new_reactivate_accts_nof_str;
	}
	public void setC9m_trans_new_reactivate_accts_nof_str(BigDecimal c9m_trans_new_reactivate_accts_nof_str) {
		this.c9m_trans_new_reactivate_accts_nof_str = c9m_trans_new_reactivate_accts_nof_str;
	}
	public BigDecimal getC10m_trans_minor_accts_nof_str() {
		return c10m_trans_minor_accts_nof_str;
	}
	public void setC10m_trans_minor_accts_nof_str(BigDecimal c10m_trans_minor_accts_nof_str) {
		this.c10m_trans_minor_accts_nof_str = c10m_trans_minor_accts_nof_str;
	}
	public BigDecimal getC11m_all_otr_type_trans_nof_str() {
		return c11m_all_otr_type_trans_nof_str;
	}
	public void setC11m_all_otr_type_trans_nof_str(BigDecimal c11m_all_otr_type_trans_nof_str) {
		this.c11m_all_otr_type_trans_nof_str = c11m_all_otr_type_trans_nof_str;
	}
	public String getC12m_note_nof_str() {
		return c12m_note_nof_str;
	}
	public void setC12m_note_nof_str(String c12m_note_nof_str) {
		this.c12m_note_nof_str = c12m_note_nof_str;
	}
	public BigDecimal getC1n_large_cash_trans_nof_alerts() {
		return c1n_large_cash_trans_nof_alerts;
	}
	public void setC1n_large_cash_trans_nof_alerts(BigDecimal c1n_large_cash_trans_nof_alerts) {
		this.c1n_large_cash_trans_nof_alerts = c1n_large_cash_trans_nof_alerts;
	}
	public BigDecimal getC2n_cash_in_cash_out_trans_nof_alerts() {
		return c2n_cash_in_cash_out_trans_nof_alerts;
	}
	public void setC2n_cash_in_cash_out_trans_nof_alerts(BigDecimal c2n_cash_in_cash_out_trans_nof_alerts) {
		this.c2n_cash_in_cash_out_trans_nof_alerts = c2n_cash_in_cash_out_trans_nof_alerts;
	}
	public BigDecimal getC3n_linked_cash_trans_nof_alerts() {
		return c3n_linked_cash_trans_nof_alerts;
	}
	public void setC3n_linked_cash_trans_nof_alerts(BigDecimal c3n_linked_cash_trans_nof_alerts) {
		this.c3n_linked_cash_trans_nof_alerts = c3n_linked_cash_trans_nof_alerts;
	}
	public BigDecimal getC4n_funds_in_funds_out_trans_nof_alerts() {
		return c4n_funds_in_funds_out_trans_nof_alerts;
	}
	public void setC4n_funds_in_funds_out_trans_nof_alerts(BigDecimal c4n_funds_in_funds_out_trans_nof_alerts) {
		this.c4n_funds_in_funds_out_trans_nof_alerts = c4n_funds_in_funds_out_trans_nof_alerts;
	}
	public BigDecimal getC5n_caution_list_santion_custs_nof_alerts() {
		return c5n_caution_list_santion_custs_nof_alerts;
	}
	public void setC5n_caution_list_santion_custs_nof_alerts(BigDecimal c5n_caution_list_santion_custs_nof_alerts) {
		this.c5n_caution_list_santion_custs_nof_alerts = c5n_caution_list_santion_custs_nof_alerts;
	}
	public BigDecimal getC6n_sanction_jurispiction_nof_alerts() {
		return c6n_sanction_jurispiction_nof_alerts;
	}
	public void setC6n_sanction_jurispiction_nof_alerts(BigDecimal c6n_sanction_jurispiction_nof_alerts) {
		this.c6n_sanction_jurispiction_nof_alerts = c6n_sanction_jurispiction_nof_alerts;
	}
	public BigDecimal getC7n_trans_nof_equi_jurisdiction_nof_alerts() {
		return c7n_trans_nof_equi_jurisdiction_nof_alerts;
	}
	public void setC7n_trans_nof_equi_jurisdiction_nof_alerts(BigDecimal c7n_trans_nof_equi_jurisdiction_nof_alerts) {
		this.c7n_trans_nof_equi_jurisdiction_nof_alerts = c7n_trans_nof_equi_jurisdiction_nof_alerts;
	}
	public BigDecimal getC8n_trans_peps_high_risk_accts_nof_alerts() {
		return c8n_trans_peps_high_risk_accts_nof_alerts;
	}
	public void setC8n_trans_peps_high_risk_accts_nof_alerts(BigDecimal c8n_trans_peps_high_risk_accts_nof_alerts) {
		this.c8n_trans_peps_high_risk_accts_nof_alerts = c8n_trans_peps_high_risk_accts_nof_alerts;
	}
	public BigDecimal getC9n_trans_new_reactivate_accts_nof_alerts() {
		return c9n_trans_new_reactivate_accts_nof_alerts;
	}
	public void setC9n_trans_new_reactivate_accts_nof_alerts(BigDecimal c9n_trans_new_reactivate_accts_nof_alerts) {
		this.c9n_trans_new_reactivate_accts_nof_alerts = c9n_trans_new_reactivate_accts_nof_alerts;
	}
	public BigDecimal getC10n_trans_minor_accts_nof_alerts() {
		return c10n_trans_minor_accts_nof_alerts;
	}
	public void setC10n_trans_minor_accts_nof_alerts(BigDecimal c10n_trans_minor_accts_nof_alerts) {
		this.c10n_trans_minor_accts_nof_alerts = c10n_trans_minor_accts_nof_alerts;
	}
	public BigDecimal getC11n_all_otr_type_trans_nof_alerts() {
		return c11n_all_otr_type_trans_nof_alerts;
	}
	public void setC11n_all_otr_type_trans_nof_alerts(BigDecimal c11n_all_otr_type_trans_nof_alerts) {
		this.c11n_all_otr_type_trans_nof_alerts = c11n_all_otr_type_trans_nof_alerts;
	}
	public String getC12n_note_nof_alerts() {
		return c12n_note_nof_alerts;
	}
	public void setC12n_note_nof_alerts(String c12n_note_nof_alerts) {
		this.c12n_note_nof_alerts = c12n_note_nof_alerts;
	}
	public BigDecimal getC1o_large_cash_trans_nof_alerts_nostr() {
		return c1o_large_cash_trans_nof_alerts_nostr;
	}
	public void setC1o_large_cash_trans_nof_alerts_nostr(BigDecimal c1o_large_cash_trans_nof_alerts_nostr) {
		this.c1o_large_cash_trans_nof_alerts_nostr = c1o_large_cash_trans_nof_alerts_nostr;
	}
	public BigDecimal getC2o_cash_in_cash_out_trans_nof_alerts_nostr() {
		return c2o_cash_in_cash_out_trans_nof_alerts_nostr;
	}
	public void setC2o_cash_in_cash_out_trans_nof_alerts_nostr(BigDecimal c2o_cash_in_cash_out_trans_nof_alerts_nostr) {
		this.c2o_cash_in_cash_out_trans_nof_alerts_nostr = c2o_cash_in_cash_out_trans_nof_alerts_nostr;
	}
	public BigDecimal getC3o_linked_cash_trans_nof_alerts_nostr() {
		return c3o_linked_cash_trans_nof_alerts_nostr;
	}
	public void setC3o_linked_cash_trans_nof_alerts_nostr(BigDecimal c3o_linked_cash_trans_nof_alerts_nostr) {
		this.c3o_linked_cash_trans_nof_alerts_nostr = c3o_linked_cash_trans_nof_alerts_nostr;
	}
	public BigDecimal getC4o_funds_in_funds_out_trans_nof_alerts_nostr() {
		return c4o_funds_in_funds_out_trans_nof_alerts_nostr;
	}
	public void setC4o_funds_in_funds_out_trans_nof_alerts_nostr(BigDecimal c4o_funds_in_funds_out_trans_nof_alerts_nostr) {
		this.c4o_funds_in_funds_out_trans_nof_alerts_nostr = c4o_funds_in_funds_out_trans_nof_alerts_nostr;
	}
	public BigDecimal getC5o_caution_list_santion_custs_nof_alerts_nostr() {
		return c5o_caution_list_santion_custs_nof_alerts_nostr;
	}
	public void setC5o_caution_list_santion_custs_nof_alerts_nostr(
			BigDecimal c5o_caution_list_santion_custs_nof_alerts_nostr) {
		this.c5o_caution_list_santion_custs_nof_alerts_nostr = c5o_caution_list_santion_custs_nof_alerts_nostr;
	}
	public BigDecimal getC6o_sanction_jurispiction_nof_alerts_nostr() {
		return c6o_sanction_jurispiction_nof_alerts_nostr;
	}
	public void setC6o_sanction_jurispiction_nof_alerts_nostr(BigDecimal c6o_sanction_jurispiction_nof_alerts_nostr) {
		this.c6o_sanction_jurispiction_nof_alerts_nostr = c6o_sanction_jurispiction_nof_alerts_nostr;
	}
	public BigDecimal getC7o_trans_non_equi_jurisdiction_nof_alerts_nostr() {
		return c7o_trans_non_equi_jurisdiction_nof_alerts_nostr;
	}
	public void setC7o_trans_non_equi_jurisdiction_nof_alerts_nostr(
			BigDecimal c7o_trans_non_equi_jurisdiction_nof_alerts_nostr) {
		this.c7o_trans_non_equi_jurisdiction_nof_alerts_nostr = c7o_trans_non_equi_jurisdiction_nof_alerts_nostr;
	}
	public BigDecimal getC8o_trans_peps_high_risk_accts_nof_alerts_nostr() {
		return c8o_trans_peps_high_risk_accts_nof_alerts_nostr;
	}
	public void setC8o_trans_peps_high_risk_accts_nof_alerts_nostr(
			BigDecimal c8o_trans_peps_high_risk_accts_nof_alerts_nostr) {
		this.c8o_trans_peps_high_risk_accts_nof_alerts_nostr = c8o_trans_peps_high_risk_accts_nof_alerts_nostr;
	}
	public BigDecimal getC9o_trans_new_reactivate_accts_nof_alerts_nostr() {
		return c9o_trans_new_reactivate_accts_nof_alerts_nostr;
	}
	public void setC9o_trans_new_reactivate_accts_nof_alerts_nostr(
			BigDecimal c9o_trans_new_reactivate_accts_nof_alerts_nostr) {
		this.c9o_trans_new_reactivate_accts_nof_alerts_nostr = c9o_trans_new_reactivate_accts_nof_alerts_nostr;
	}
	public BigDecimal getC10o_trans_minor_accts_nof_alerts_nostr() {
		return c10o_trans_minor_accts_nof_alerts_nostr;
	}
	public void setC10o_trans_minor_accts_nof_alerts_nostr(BigDecimal c10o_trans_minor_accts_nof_alerts_nostr) {
		this.c10o_trans_minor_accts_nof_alerts_nostr = c10o_trans_minor_accts_nof_alerts_nostr;
	}
	public BigDecimal getC11o_all_otr_type_trans_nof_alerts_nostr() {
		return c11o_all_otr_type_trans_nof_alerts_nostr;
	}
	public void setC11o_all_otr_type_trans_nof_alerts_nostr(BigDecimal c11o_all_otr_type_trans_nof_alerts_nostr) {
		this.c11o_all_otr_type_trans_nof_alerts_nostr = c11o_all_otr_type_trans_nof_alerts_nostr;
	}
	public String getC12o_note_nof_alerts_nostr() {
		return c12o_note_nof_alerts_nostr;
	}
	public void setC12o_note_nof_alerts_nostr(String c12o_note_nof_alerts_nostr) {
		this.c12o_note_nof_alerts_nostr = c12o_note_nof_alerts_nostr;
	}
	public String getC1p_large_cash_trans_avg_time() {
		return c1p_large_cash_trans_avg_time;
	}
	public void setC1p_large_cash_trans_avg_time(String c1p_large_cash_trans_avg_time) {
		this.c1p_large_cash_trans_avg_time = c1p_large_cash_trans_avg_time;
	}
	public String getC2p_casp_in_cash_out_trans_avg_time() {
		return c2p_casp_in_cash_out_trans_avg_time;
	}
	public void setC2p_casp_in_cash_out_trans_avg_time(String c2p_casp_in_cash_out_trans_avg_time) {
		this.c2p_casp_in_cash_out_trans_avg_time = c2p_casp_in_cash_out_trans_avg_time;
	}
	public String getC3p_linked_cash_trans_avg_time() {
		return c3p_linked_cash_trans_avg_time;
	}
	public void setC3p_linked_cash_trans_avg_time(String c3p_linked_cash_trans_avg_time) {
		this.c3p_linked_cash_trans_avg_time = c3p_linked_cash_trans_avg_time;
	}
	public String getC4p_funds_in_funds_out_trans_avg_time() {
		return c4p_funds_in_funds_out_trans_avg_time;
	}
	public void setC4p_funds_in_funds_out_trans_avg_time(String c4p_funds_in_funds_out_trans_avg_time) {
		this.c4p_funds_in_funds_out_trans_avg_time = c4p_funds_in_funds_out_trans_avg_time;
	}
	public String getC5p_caution_list_santion_custs_avg_time() {
		return c5p_caution_list_santion_custs_avg_time;
	}
	public void setC5p_caution_list_santion_custs_avg_time(String c5p_caution_list_santion_custs_avg_time) {
		this.c5p_caution_list_santion_custs_avg_time = c5p_caution_list_santion_custs_avg_time;
	}
	public String getC6p_sanction_jurispiction_avg_time() {
		return c6p_sanction_jurispiction_avg_time;
	}
	public void setC6p_sanction_jurispiction_avg_time(String c6p_sanction_jurispiction_avg_time) {
		this.c6p_sanction_jurispiction_avg_time = c6p_sanction_jurispiction_avg_time;
	}
	public String getC7p_trans_non_equi_jurisdiction_avg_time() {
		return c7p_trans_non_equi_jurisdiction_avg_time;
	}
	public void setC7p_trans_non_equi_jurisdiction_avg_time(String c7p_trans_non_equi_jurisdiction_avg_time) {
		this.c7p_trans_non_equi_jurisdiction_avg_time = c7p_trans_non_equi_jurisdiction_avg_time;
	}
	public String getC8p_trans_peps_higp_risk_accts_avg_time() {
		return c8p_trans_peps_higp_risk_accts_avg_time;
	}
	public void setC8p_trans_peps_higp_risk_accts_avg_time(String c8p_trans_peps_higp_risk_accts_avg_time) {
		this.c8p_trans_peps_higp_risk_accts_avg_time = c8p_trans_peps_higp_risk_accts_avg_time;
	}
	public String getC9p_trans_new_reactivate_accts_avg_time() {
		return c9p_trans_new_reactivate_accts_avg_time;
	}
	public void setC9p_trans_new_reactivate_accts_avg_time(String c9p_trans_new_reactivate_accts_avg_time) {
		this.c9p_trans_new_reactivate_accts_avg_time = c9p_trans_new_reactivate_accts_avg_time;
	}
	public String getC10p_trans_minor_accts_avg_time() {
		return c10p_trans_minor_accts_avg_time;
	}
	public void setC10p_trans_minor_accts_avg_time(String c10p_trans_minor_accts_avg_time) {
		this.c10p_trans_minor_accts_avg_time = c10p_trans_minor_accts_avg_time;
	}
	public String getC11p_all_otr_type_trans_avg_time() {
		return c11p_all_otr_type_trans_avg_time;
	}
	public void setC11p_all_otr_type_trans_avg_time(String c11p_all_otr_type_trans_avg_time) {
		this.c11p_all_otr_type_trans_avg_time = c11p_all_otr_type_trans_avg_time;
	}
	public String getC12p_note_avg_time() {
		return c12p_note_avg_time;
	}
	public void setC12p_note_avg_time(String c12p_note_avg_time) {
		this.c12p_note_avg_time = c12p_note_avg_time;
	}
	public BigDecimal getC1q_large_cash_trans_nof_alerts_str() {
		return c1q_large_cash_trans_nof_alerts_str;
	}
	public void setC1q_large_cash_trans_nof_alerts_str(BigDecimal c1q_large_cash_trans_nof_alerts_str) {
		this.c1q_large_cash_trans_nof_alerts_str = c1q_large_cash_trans_nof_alerts_str;
	}
	public BigDecimal getC2q_cash_in_cash_out_trans_nof_alerts_str() {
		return c2q_cash_in_cash_out_trans_nof_alerts_str;
	}
	public void setC2q_cash_in_cash_out_trans_nof_alerts_str(BigDecimal c2q_cash_in_cash_out_trans_nof_alerts_str) {
		this.c2q_cash_in_cash_out_trans_nof_alerts_str = c2q_cash_in_cash_out_trans_nof_alerts_str;
	}
	public BigDecimal getC3q_linkeq_cash_trans_nof_alerts_str() {
		return c3q_linkeq_cash_trans_nof_alerts_str;
	}
	public void setC3q_linkeq_cash_trans_nof_alerts_str(BigDecimal c3q_linkeq_cash_trans_nof_alerts_str) {
		this.c3q_linkeq_cash_trans_nof_alerts_str = c3q_linkeq_cash_trans_nof_alerts_str;
	}
	public BigDecimal getC4q_funds_in_funds_out_trans_nof_alerts_str() {
		return c4q_funds_in_funds_out_trans_nof_alerts_str;
	}
	public void setC4q_funds_in_funds_out_trans_nof_alerts_str(BigDecimal c4q_funds_in_funds_out_trans_nof_alerts_str) {
		this.c4q_funds_in_funds_out_trans_nof_alerts_str = c4q_funds_in_funds_out_trans_nof_alerts_str;
	}
	public BigDecimal getC5q_caution_list_santion_custs_nof_alerts_str() {
		return c5q_caution_list_santion_custs_nof_alerts_str;
	}
	public void setC5q_caution_list_santion_custs_nof_alerts_str(BigDecimal c5q_caution_list_santion_custs_nof_alerts_str) {
		this.c5q_caution_list_santion_custs_nof_alerts_str = c5q_caution_list_santion_custs_nof_alerts_str;
	}
	public BigDecimal getC6q_sanction_jurispiction_nof_alerts_str() {
		return c6q_sanction_jurispiction_nof_alerts_str;
	}
	public void setC6q_sanction_jurispiction_nof_alerts_str(BigDecimal c6q_sanction_jurispiction_nof_alerts_str) {
		this.c6q_sanction_jurispiction_nof_alerts_str = c6q_sanction_jurispiction_nof_alerts_str;
	}
	public BigDecimal getC7q_trans_non_equi_jurisdiction_nof_alerts_str() {
		return c7q_trans_non_equi_jurisdiction_nof_alerts_str;
	}
	public void setC7q_trans_non_equi_jurisdiction_nof_alerts_str(
			BigDecimal c7q_trans_non_equi_jurisdiction_nof_alerts_str) {
		this.c7q_trans_non_equi_jurisdiction_nof_alerts_str = c7q_trans_non_equi_jurisdiction_nof_alerts_str;
	}
	public BigDecimal getC8q_trans_peps_high_risk_accts_nof_alerts_str() {
		return c8q_trans_peps_high_risk_accts_nof_alerts_str;
	}
	public void setC8q_trans_peps_high_risk_accts_nof_alerts_str(BigDecimal c8q_trans_peps_high_risk_accts_nof_alerts_str) {
		this.c8q_trans_peps_high_risk_accts_nof_alerts_str = c8q_trans_peps_high_risk_accts_nof_alerts_str;
	}
	public BigDecimal getC9q_trans_new_reactivate_accts_nof_alerts_str() {
		return c9q_trans_new_reactivate_accts_nof_alerts_str;
	}
	public void setC9q_trans_new_reactivate_accts_nof_alerts_str(BigDecimal c9q_trans_new_reactivate_accts_nof_alerts_str) {
		this.c9q_trans_new_reactivate_accts_nof_alerts_str = c9q_trans_new_reactivate_accts_nof_alerts_str;
	}
	public BigDecimal getC10q_trans_minor_accts_nof_alerts_str() {
		return c10q_trans_minor_accts_nof_alerts_str;
	}
	public void setC10q_trans_minor_accts_nof_alerts_str(BigDecimal c10q_trans_minor_accts_nof_alerts_str) {
		this.c10q_trans_minor_accts_nof_alerts_str = c10q_trans_minor_accts_nof_alerts_str;
	}
	public BigDecimal getC11q_all_otr_type_trans_nof_alerts_str() {
		return c11q_all_otr_type_trans_nof_alerts_str;
	}
	public void setC11q_all_otr_type_trans_nof_alerts_str(BigDecimal c11q_all_otr_type_trans_nof_alerts_str) {
		this.c11q_all_otr_type_trans_nof_alerts_str = c11q_all_otr_type_trans_nof_alerts_str;
	}
	public String getC12q_note_nof_alerts_str() {
		return c12q_note_nof_alerts_str;
	}
	public void setC12q_note_nof_alerts_str(String c12q_note_nof_alerts_str) {
		this.c12q_note_nof_alerts_str = c12q_note_nof_alerts_str;
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
	public Character getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}
	public T19Report(String d1a_large_cash_trans, String d2a_cash_in_cash_out_trans, String d3a_linked_cash_trans,
			String d4a_funds_in_funds_out_trans, String d5a_caution_list_santion_cust, String d6a_sanction_jurisdiction,
			String d7a_trans_non_equi_jurisdiction, String d8a_trans_peps_high_risk_accts,
			String d9a_trans_new_reactivate_accts, String d10a_trans_minor_accts, String d11a_all_otr_type_trans,
			String d12a_note, BigDecimal c1j_large_cash_trans_nof_hits, BigDecimal c2j_cash_in_cash_out_trans_nof_hits,
			BigDecimal c3j_linked_cash_trans_nof_hits, BigDecimal c4j_funds_in_funds_out_trans_nof_hits,
			BigDecimal c5j_caution_list_santion_custs_nof_hits, BigDecimal c6j_sanction_jurispiction_nof_hits,
			BigDecimal c7j_trans_non_equi_jurisdiction_nof_hits, BigDecimal c8j_trans_peps_high_risk_accts_nof_hits,
			BigDecimal c9j_trans_new_reactivate_accts_nof_hits, BigDecimal c10j_trans_minor_accts_nof_hits,
			BigDecimal c11j_all_otr_type_trans_nof_hits, String c12j_note_nof_hits,
			BigDecimal c1k_large_cash_trans_nof_phits, BigDecimal c2k_cash_in_cash_out_trans_nof_phits,
			BigDecimal c3k_linked_cash_trans_nof_phits, BigDecimal c4k_funds_in_funds_out_trans_nof_phits,
			BigDecimal c5k_caution_list_santion_custs_nof_phits, BigDecimal c6k_sanction_jurispiction_nof_phits,
			BigDecimal c7k_trans_non_equi_jurisdiction, BigDecimal c8k_trans_peps_high_risk_accts_nof_phits,
			BigDecimal c9k_trans_new_reactivate_accts_nof_phits, BigDecimal c10k_trans_minor_accts_nof_phits,
			BigDecimal c11k_all_otr_type_trans_nof_phits, String c12k_note_nof_phits,
			BigDecimal c1l_large_cash_trans_nof_phitsc, BigDecimal c2l_cash_in_cash_out_trans_nof_phitsc,
			BigDecimal c3l_linkel_cash_trans_nof_phitsc, BigDecimal c4l_funds_in_funds_out_trans_nof_phitsc,
			BigDecimal c5l_caution_list_santion_custs_nof_phitsc, BigDecimal c6l_sanction_jurispiction_nof_phitsc,
			BigDecimal c7l_trans_non_equi_jurisdiction_nof_phitsc, BigDecimal c8l_trans_peps_high_risk_accts_nof_phitsc,
			BigDecimal c9l_trans_new_reactivate_accts_nof_phitsc, BigDecimal c10l_trans_minor_accts_nof_phitsc,
			BigDecimal c11l_all_otr_type_trans_nof_phitsc, String c12l_note_nof_phitsc,
			BigDecimal c1m_largm_cash_trans_nof_str, BigDecimal c2m_cash_in_cash_out_trans_nof_str,
			BigDecimal c3m_linked_cash_trans_nof_str, BigDecimal c4m_funds_in_funds_out_trans_nof_str,
			BigDecimal c5m_caution_list_santion_custs_nof_str, BigDecimal c6m_sanction_jurispiction_nof_str,
			BigDecimal c7m_trans_non_equi_jurisdiction_nof_str, BigDecimal c8m_trans_peps_high_risk_accts_nof_str,
			BigDecimal c9m_trans_new_reactivate_accts_nof_str, BigDecimal c10m_trans_minor_accts_nof_str,
			BigDecimal c11m_all_otr_type_trans_nof_str, String c12m_note_nof_str,
			BigDecimal c1n_large_cash_trans_nof_alerts, BigDecimal c2n_cash_in_cash_out_trans_nof_alerts,
			BigDecimal c3n_linked_cash_trans_nof_alerts, BigDecimal c4n_funds_in_funds_out_trans_nof_alerts,
			BigDecimal c5n_caution_list_santion_custs_nof_alerts, BigDecimal c6n_sanction_jurispiction_nof_alerts,
			BigDecimal c7n_trans_nof_equi_jurisdiction_nof_alerts, BigDecimal c8n_trans_peps_high_risk_accts_nof_alerts,
			BigDecimal c9n_trans_new_reactivate_accts_nof_alerts, BigDecimal c10n_trans_minor_accts_nof_alerts,
			BigDecimal c11n_all_otr_type_trans_nof_alerts, String c12n_note_nof_alerts,
			BigDecimal c1o_large_cash_trans_nof_alerts_nostr, BigDecimal c2o_cash_in_cash_out_trans_nof_alerts_nostr,
			BigDecimal c3o_linked_cash_trans_nof_alerts_nostr, BigDecimal c4o_funds_in_funds_out_trans_nof_alerts_nostr,
			BigDecimal c5o_caution_list_santion_custs_nof_alerts_nostr,
			BigDecimal c6o_sanction_jurispiction_nof_alerts_nostr,
			BigDecimal c7o_trans_non_equi_jurisdiction_nof_alerts_nostr,
			BigDecimal c8o_trans_peps_high_risk_accts_nof_alerts_nostr,
			BigDecimal c9o_trans_new_reactivate_accts_nof_alerts_nostr,
			BigDecimal c10o_trans_minor_accts_nof_alerts_nostr, BigDecimal c11o_all_otr_type_trans_nof_alerts_nostr,
			String c12o_note_nof_alerts_nostr, String c1p_large_cash_trans_avg_time,
			String c2p_casp_in_cash_out_trans_avg_time, String c3p_linked_cash_trans_avg_time,
			String c4p_funds_in_funds_out_trans_avg_time, String c5p_caution_list_santion_custs_avg_time,
			String c6p_sanction_jurispiction_avg_time, String c7p_trans_non_equi_jurisdiction_avg_time,
			String c8p_trans_peps_higp_risk_accts_avg_time, String c9p_trans_new_reactivate_accts_avg_time,
			String c10p_trans_minor_accts_avg_time, String c11p_all_otr_type_trans_avg_time, String c12p_note_avg_time,
			BigDecimal c1q_large_cash_trans_nof_alerts_str, BigDecimal c2q_cash_in_cash_out_trans_nof_alerts_str,
			BigDecimal c3q_linkeq_cash_trans_nof_alerts_str, BigDecimal c4q_funds_in_funds_out_trans_nof_alerts_str,
			BigDecimal c5q_caution_list_santion_custs_nof_alerts_str,
			BigDecimal c6q_sanction_jurispiction_nof_alerts_str,
			BigDecimal c7q_trans_non_equi_jurisdiction_nof_alerts_str,
			BigDecimal c8q_trans_peps_high_risk_accts_nof_alerts_str,
			BigDecimal c9q_trans_new_reactivate_accts_nof_alerts_str, BigDecimal c10q_trans_minor_accts_nof_alerts_str,
			BigDecimal c11q_all_otr_type_trans_nof_alerts_str, String c12q_note_nof_alerts_str, String report_code,
			String report_name, Date report_date, Date report_due_date, Date rep_submit_date, Date rep_period_from,
			Date rep_period_to, String rep_freq, String nil_report_flg, Character arch_flg, String entry_user,
			String modify_user, String verify_user, Date entry_time, Date modify_time, Date verify_time,
			Character entity_flg, Character modify_flg, Character del_flg) {
	
		this.d1a_large_cash_trans = d1a_large_cash_trans;
		this.d2a_cash_in_cash_out_trans = d2a_cash_in_cash_out_trans;
		this.d3a_linked_cash_trans = d3a_linked_cash_trans;
		this.d4a_funds_in_funds_out_trans = d4a_funds_in_funds_out_trans;
		this.d5a_caution_list_santion_cust = d5a_caution_list_santion_cust;
		this.d6a_sanction_jurisdiction = d6a_sanction_jurisdiction;
		this.d7a_trans_non_equi_jurisdiction = d7a_trans_non_equi_jurisdiction;
		this.d8a_trans_peps_high_risk_accts = d8a_trans_peps_high_risk_accts;
		this.d9a_trans_new_reactivate_accts = d9a_trans_new_reactivate_accts;
		this.d10a_trans_minor_accts = d10a_trans_minor_accts;
		this.d11a_all_otr_type_trans = d11a_all_otr_type_trans;
		this.d12a_note = d12a_note;
		this.c1j_large_cash_trans_nof_hits = c1j_large_cash_trans_nof_hits;
		this.c2j_cash_in_cash_out_trans_nof_hits = c2j_cash_in_cash_out_trans_nof_hits;
		this.c3j_linked_cash_trans_nof_hits = c3j_linked_cash_trans_nof_hits;
		this.c4j_funds_in_funds_out_trans_nof_hits = c4j_funds_in_funds_out_trans_nof_hits;
		this.c5j_caution_list_santion_custs_nof_hits = c5j_caution_list_santion_custs_nof_hits;
		this.c6j_sanction_jurispiction_nof_hits = c6j_sanction_jurispiction_nof_hits;
		this.c7j_trans_non_equi_jurisdiction_nof_hits = c7j_trans_non_equi_jurisdiction_nof_hits;
		this.c8j_trans_peps_high_risk_accts_nof_hits = c8j_trans_peps_high_risk_accts_nof_hits;
		this.c9j_trans_new_reactivate_accts_nof_hits = c9j_trans_new_reactivate_accts_nof_hits;
		this.c10j_trans_minor_accts_nof_hits = c10j_trans_minor_accts_nof_hits;
		this.c11j_all_otr_type_trans_nof_hits = c11j_all_otr_type_trans_nof_hits;
		this.c12j_note_nof_hits = c12j_note_nof_hits;
		this.c1k_large_cash_trans_nof_phits = c1k_large_cash_trans_nof_phits;
		this.c2k_cash_in_cash_out_trans_nof_phits = c2k_cash_in_cash_out_trans_nof_phits;
		this.c3k_linked_cash_trans_nof_phits = c3k_linked_cash_trans_nof_phits;
		this.c4k_funds_in_funds_out_trans_nof_phits = c4k_funds_in_funds_out_trans_nof_phits;
		this.c5k_caution_list_santion_custs_nof_phits = c5k_caution_list_santion_custs_nof_phits;
		this.c6k_sanction_jurispiction_nof_phits = c6k_sanction_jurispiction_nof_phits;
		this.c7k_trans_non_equi_jurisdiction = c7k_trans_non_equi_jurisdiction;
		this.c8k_trans_peps_high_risk_accts_nof_phits = c8k_trans_peps_high_risk_accts_nof_phits;
		this.c9k_trans_new_reactivate_accts_nof_phits = c9k_trans_new_reactivate_accts_nof_phits;
		this.c10k_trans_minor_accts_nof_phits = c10k_trans_minor_accts_nof_phits;
		this.c11k_all_otr_type_trans_nof_phits = c11k_all_otr_type_trans_nof_phits;
		this.c12k_note_nof_phits = c12k_note_nof_phits;
		this.c1l_large_cash_trans_nof_phitsc = c1l_large_cash_trans_nof_phitsc;
		this.c2l_cash_in_cash_out_trans_nof_phitsc = c2l_cash_in_cash_out_trans_nof_phitsc;
		this.c3l_linkel_cash_trans_nof_phitsc = c3l_linkel_cash_trans_nof_phitsc;
		this.c4l_funds_in_funds_out_trans_nof_phitsc = c4l_funds_in_funds_out_trans_nof_phitsc;
		this.c5l_caution_list_santion_custs_nof_phitsc = c5l_caution_list_santion_custs_nof_phitsc;
		this.c6l_sanction_jurispiction_nof_phitsc = c6l_sanction_jurispiction_nof_phitsc;
		this.c7l_trans_non_equi_jurisdiction_nof_phitsc = c7l_trans_non_equi_jurisdiction_nof_phitsc;
		this.c8l_trans_peps_high_risk_accts_nof_phitsc = c8l_trans_peps_high_risk_accts_nof_phitsc;
		this.c9l_trans_new_reactivate_accts_nof_phitsc = c9l_trans_new_reactivate_accts_nof_phitsc;
		this.c10l_trans_minor_accts_nof_phitsc = c10l_trans_minor_accts_nof_phitsc;
		this.c11l_all_otr_type_trans_nof_phitsc = c11l_all_otr_type_trans_nof_phitsc;
		this.c12l_note_nof_phitsc = c12l_note_nof_phitsc;
		this.c1m_largm_cash_trans_nof_str = c1m_largm_cash_trans_nof_str;
		this.c2m_cash_in_cash_out_trans_nof_str = c2m_cash_in_cash_out_trans_nof_str;
		this.c3m_linked_cash_trans_nof_str = c3m_linked_cash_trans_nof_str;
		this.c4m_funds_in_funds_out_trans_nof_str = c4m_funds_in_funds_out_trans_nof_str;
		this.c5m_caution_list_santion_custs_nof_str = c5m_caution_list_santion_custs_nof_str;
		this.c6m_sanction_jurispiction_nof_str = c6m_sanction_jurispiction_nof_str;
		this.c7m_trans_non_equi_jurisdiction_nof_str = c7m_trans_non_equi_jurisdiction_nof_str;
		this.c8m_trans_peps_high_risk_accts_nof_str = c8m_trans_peps_high_risk_accts_nof_str;
		this.c9m_trans_new_reactivate_accts_nof_str = c9m_trans_new_reactivate_accts_nof_str;
		this.c10m_trans_minor_accts_nof_str = c10m_trans_minor_accts_nof_str;
		this.c11m_all_otr_type_trans_nof_str = c11m_all_otr_type_trans_nof_str;
		this.c12m_note_nof_str = c12m_note_nof_str;
		this.c1n_large_cash_trans_nof_alerts = c1n_large_cash_trans_nof_alerts;
		this.c2n_cash_in_cash_out_trans_nof_alerts = c2n_cash_in_cash_out_trans_nof_alerts;
		this.c3n_linked_cash_trans_nof_alerts = c3n_linked_cash_trans_nof_alerts;
		this.c4n_funds_in_funds_out_trans_nof_alerts = c4n_funds_in_funds_out_trans_nof_alerts;
		this.c5n_caution_list_santion_custs_nof_alerts = c5n_caution_list_santion_custs_nof_alerts;
		this.c6n_sanction_jurispiction_nof_alerts = c6n_sanction_jurispiction_nof_alerts;
		this.c7n_trans_nof_equi_jurisdiction_nof_alerts = c7n_trans_nof_equi_jurisdiction_nof_alerts;
		this.c8n_trans_peps_high_risk_accts_nof_alerts = c8n_trans_peps_high_risk_accts_nof_alerts;
		this.c9n_trans_new_reactivate_accts_nof_alerts = c9n_trans_new_reactivate_accts_nof_alerts;
		this.c10n_trans_minor_accts_nof_alerts = c10n_trans_minor_accts_nof_alerts;
		this.c11n_all_otr_type_trans_nof_alerts = c11n_all_otr_type_trans_nof_alerts;
		this.c12n_note_nof_alerts = c12n_note_nof_alerts;
		this.c1o_large_cash_trans_nof_alerts_nostr = c1o_large_cash_trans_nof_alerts_nostr;
		this.c2o_cash_in_cash_out_trans_nof_alerts_nostr = c2o_cash_in_cash_out_trans_nof_alerts_nostr;
		this.c3o_linked_cash_trans_nof_alerts_nostr = c3o_linked_cash_trans_nof_alerts_nostr;
		this.c4o_funds_in_funds_out_trans_nof_alerts_nostr = c4o_funds_in_funds_out_trans_nof_alerts_nostr;
		this.c5o_caution_list_santion_custs_nof_alerts_nostr = c5o_caution_list_santion_custs_nof_alerts_nostr;
		this.c6o_sanction_jurispiction_nof_alerts_nostr = c6o_sanction_jurispiction_nof_alerts_nostr;
		this.c7o_trans_non_equi_jurisdiction_nof_alerts_nostr = c7o_trans_non_equi_jurisdiction_nof_alerts_nostr;
		this.c8o_trans_peps_high_risk_accts_nof_alerts_nostr = c8o_trans_peps_high_risk_accts_nof_alerts_nostr;
		this.c9o_trans_new_reactivate_accts_nof_alerts_nostr = c9o_trans_new_reactivate_accts_nof_alerts_nostr;
		this.c10o_trans_minor_accts_nof_alerts_nostr = c10o_trans_minor_accts_nof_alerts_nostr;
		this.c11o_all_otr_type_trans_nof_alerts_nostr = c11o_all_otr_type_trans_nof_alerts_nostr;
		this.c12o_note_nof_alerts_nostr = c12o_note_nof_alerts_nostr;
		this.c1p_large_cash_trans_avg_time = c1p_large_cash_trans_avg_time;
		this.c2p_casp_in_cash_out_trans_avg_time = c2p_casp_in_cash_out_trans_avg_time;
		this.c3p_linked_cash_trans_avg_time = c3p_linked_cash_trans_avg_time;
		this.c4p_funds_in_funds_out_trans_avg_time = c4p_funds_in_funds_out_trans_avg_time;
		this.c5p_caution_list_santion_custs_avg_time = c5p_caution_list_santion_custs_avg_time;
		this.c6p_sanction_jurispiction_avg_time = c6p_sanction_jurispiction_avg_time;
		this.c7p_trans_non_equi_jurisdiction_avg_time = c7p_trans_non_equi_jurisdiction_avg_time;
		this.c8p_trans_peps_higp_risk_accts_avg_time = c8p_trans_peps_higp_risk_accts_avg_time;
		this.c9p_trans_new_reactivate_accts_avg_time = c9p_trans_new_reactivate_accts_avg_time;
		this.c10p_trans_minor_accts_avg_time = c10p_trans_minor_accts_avg_time;
		this.c11p_all_otr_type_trans_avg_time = c11p_all_otr_type_trans_avg_time;
		this.c12p_note_avg_time = c12p_note_avg_time;
		this.c1q_large_cash_trans_nof_alerts_str = c1q_large_cash_trans_nof_alerts_str;
		this.c2q_cash_in_cash_out_trans_nof_alerts_str = c2q_cash_in_cash_out_trans_nof_alerts_str;
		this.c3q_linkeq_cash_trans_nof_alerts_str = c3q_linkeq_cash_trans_nof_alerts_str;
		this.c4q_funds_in_funds_out_trans_nof_alerts_str = c4q_funds_in_funds_out_trans_nof_alerts_str;
		this.c5q_caution_list_santion_custs_nof_alerts_str = c5q_caution_list_santion_custs_nof_alerts_str;
		this.c6q_sanction_jurispiction_nof_alerts_str = c6q_sanction_jurispiction_nof_alerts_str;
		this.c7q_trans_non_equi_jurisdiction_nof_alerts_str = c7q_trans_non_equi_jurisdiction_nof_alerts_str;
		this.c8q_trans_peps_high_risk_accts_nof_alerts_str = c8q_trans_peps_high_risk_accts_nof_alerts_str;
		this.c9q_trans_new_reactivate_accts_nof_alerts_str = c9q_trans_new_reactivate_accts_nof_alerts_str;
		this.c10q_trans_minor_accts_nof_alerts_str = c10q_trans_minor_accts_nof_alerts_str;
		this.c11q_all_otr_type_trans_nof_alerts_str = c11q_all_otr_type_trans_nof_alerts_str;
		this.c12q_note_nof_alerts_str = c12q_note_nof_alerts_str;
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
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
	}
	public T19Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	

	
	

}
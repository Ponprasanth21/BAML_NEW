package com.bornfire.entity.t7;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T7CUST_KYC_CDD_REVIEW_RPT_TABLE")
public class T7Report {

	private String	d1a_ind_low;
	private String	d2a_ind_med;
	private String	d3a_ind_hig;
	private String	d4a_cor_low;
	private String	d5a_cor_med;
	private String	d6a_cor_hig;
	private String	d7a_npr_org_low;
	private String	d8a_npr_org_med;
	private String	d9a_npr_org_hig;
	private String	d10a_trust_low;
	private String	d11a_trust_med;
	private String	d12a_trust_hig;
	private String	d13a_all_otr_low;
	private String	d14a_all_otr_med;
	private String	d15a_all_otr_hig;
	private String	d16a_peps_dom_low;
	private String	d17a_peps_dom_med;
	private String	d18a_peps_dom_hig;
	private String	d19a_peps_frn_low;
	private String	d20a_peps_frn_med;
	private String	d21a_peps_frn_hig;
	private String	d22a_tcps_low;
	private String	d23a_tcps_med;
	private String	d24a_tcps_hig;
	private BigDecimal	c1f_ind_low_b30;
	private BigDecimal	c2f_ind_med_b30;
	private BigDecimal	c3f_ind_hig_b30;
	private BigDecimal	c4f_cor_low_b30;
	private BigDecimal	c5f_cor_med_b30;
	private BigDecimal	c6f_cor_hig_b30;
	private BigDecimal	c7f_npr_org_low_b30;
	private BigDecimal	c8f_npr_org_med_b30;
	private BigDecimal	c9f_npr_org_hig_b30;
	private BigDecimal	c10f_trust_low_b30;
	private BigDecimal	c11f_trust_med_b30;
	private BigDecimal	c12f_trust_hig_b30;
	private BigDecimal	c13f_all_otr_low_b30;
	private BigDecimal	c14f_all_otr_med_b30;
	private BigDecimal	c15f_all_otr_hig_b30;
	private BigDecimal	c16f_peps_dom_low_b30;
	private BigDecimal	c17f_peps_dom_med_b30;
	private BigDecimal	c18f_peps_dom_hig_b30;
	private BigDecimal	c19f_peps_frn_low_b30;
	private BigDecimal	c20f_peps_frn_med_b30;
	private BigDecimal	c21f_peps_frn_hig_b30;
	private BigDecimal	c22f_tcps_low_b30;
	private BigDecimal	c23f_tcps_med_b30;
	private BigDecimal	c24f_tcps_hig_b30;
	private BigDecimal	c1g_ind_low_30_60;
	private BigDecimal	c2g_ind_med_30_60;
	private BigDecimal	c3g_ind_hig_30_60;
	private BigDecimal	c4g_cor_low_30_60;
	private BigDecimal	c5g_cor_med_30_60;
	private BigDecimal	c6g_cor_hig_30_60;
	private BigDecimal	c7g_npr_org_low_30_60;
	private BigDecimal	c8g_npr_org_med_30_60;
	private BigDecimal	c9g_npr_org_hig_30_60;
	private BigDecimal	c10g_trust_low_30_60;
	private BigDecimal	c11g_trust_med_30_60;
	private BigDecimal	c12g_trust_hig_30_60;
	private BigDecimal	c13g_all_otr_low_30_60;
	private BigDecimal	c14g_all_otr_med_30_60;
	private BigDecimal	c15g_all_otr_hig_30_60;
	private BigDecimal	c16g_peps_dom_low_30_60;
	private BigDecimal	c17g_peps_dom_med_30_60;
	private BigDecimal	c18g_peps_dom_hig_30_60;
	private BigDecimal	c19g_peps_frn_low_30_60;
	private BigDecimal	c20g_peps_frn_med_30_60;
	private BigDecimal	c21g_peps_frn_hig_30_60;
	private BigDecimal	c22g_tcps_low_30_60;
	private BigDecimal	c23g_tcps_med_30_60;
	private BigDecimal	c24g_tcps_hig_30_60;
	private BigDecimal	c1h_ind_low_60_90;
	private BigDecimal	c2h_ind_med_60_90;
	private BigDecimal	c3h_ind_hig_60_90;
	private BigDecimal	c4h_cor_low_60_90;
	private BigDecimal	c5h_cor_med_60_90;
	private BigDecimal	c6h_cor_hig_60_90;
	private BigDecimal	c7h_npr_org_low_60_90;
	private BigDecimal	c8h_npr_org_med_60_90;
	private BigDecimal	c9h_npr_org_hig_60_90;
	private BigDecimal	c10h_trust_low_60_90;
	private BigDecimal	c11h_trust_med_60_90;
	private BigDecimal	c12h_trust_hig_60_90;
	private BigDecimal	c13h_all_otr_low_60_90;
	private BigDecimal	c14h_all_otr_med_60_90;
	private BigDecimal	c15h_all_otr_hig_60_90;
	private BigDecimal	c16h_peps_dom_low_60_90;
	private BigDecimal	c17h_peps_dom_med_60_90;
	private BigDecimal	c18h_peps_dom_hig_60_90;
	private BigDecimal	c19h_peps_frn_low_60_90;
	private BigDecimal	c20h_peps_frn_med_60_90;
	private BigDecimal	c21h_peps_frn_hig_60_90;
	private BigDecimal	c22h_tcps_low_60_90;
	private BigDecimal	c23h_tcps_med_60_90;
	private BigDecimal	c24h_tcps_hig_60_90;
	private BigDecimal	c1i_ind_low_a90;
	private BigDecimal	c2i_ind_med_a90;
	private BigDecimal	c3i_ind_hig_a90;
	private BigDecimal	c4i_cor_low_a90;
	private BigDecimal	c5i_cor_med_a90;
	private BigDecimal	c6i_cor_hig_a90;
	private BigDecimal	c7i_npr_org_low_a90;
	private BigDecimal	c8i_npr_org_med_a90;
	private BigDecimal	c9i_npr_org_hig_a90;
	private BigDecimal	c10i_trust_low_a90;
	private BigDecimal	c11i_trust_med_a90;
	private BigDecimal	c12i_trust_hig_a90;
	private BigDecimal	c13i_all_otr_low_a90;
	private BigDecimal	c14i_all_otr_med_a90;
	private BigDecimal	c15i_all_otr_hig_a90;
	private BigDecimal	c16i_peps_dom_low_a90;
	private BigDecimal	c17i_peps_dom_med_a90;
	private BigDecimal	c18i_peps_dom_hig_a90;
	private BigDecimal	c19i_peps_frn_low_a90;
	private BigDecimal	c20i_peps_frn_med_a90;
	private BigDecimal	c21i_peps_frn_hig_a90;
	private BigDecimal	c22i_tcps_low_a90;
	private BigDecimal	c23i_tcps_med_a90;
	private BigDecimal	c24i_tcps_hig_a90;
	private String	report_code;
	private String	report_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Id
	private Date	report_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	report_due_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	rep_submit_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	rep_period_from;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	rep_period_to;
	private String	rep_freq;
	private String	nil_report_flg;
	private String	arch_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	verify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	verify_time;

	public String getD1a_ind_low() {
		return d1a_ind_low;
	}
	public void setD1a_ind_low(String d1a_ind_low) {
		this.d1a_ind_low = d1a_ind_low;
	}
	public String getD2a_ind_med() {
		return d2a_ind_med;
	}
	public void setD2a_ind_med(String d2a_ind_med) {
		this.d2a_ind_med = d2a_ind_med;
	}
	public String getD3a_ind_hig() {
		return d3a_ind_hig;
	}
	public void setD3a_ind_hig(String d3a_ind_hig) {
		this.d3a_ind_hig = d3a_ind_hig;
	}
	public String getD4a_cor_low() {
		return d4a_cor_low;
	}
	public void setD4a_cor_low(String d4a_cor_low) {
		this.d4a_cor_low = d4a_cor_low;
	}
	public String getD5a_cor_med() {
		return d5a_cor_med;
	}
	public void setD5a_cor_med(String d5a_cor_med) {
		this.d5a_cor_med = d5a_cor_med;
	}
	public String getD6a_cor_hig() {
		return d6a_cor_hig;
	}
	public void setD6a_cor_hig(String d6a_cor_hig) {
		this.d6a_cor_hig = d6a_cor_hig;
	}
	public String getD7a_npr_org_low() {
		return d7a_npr_org_low;
	}
	public void setD7a_npr_org_low(String d7a_npr_org_low) {
		this.d7a_npr_org_low = d7a_npr_org_low;
	}
	public String getD8a_npr_org_med() {
		return d8a_npr_org_med;
	}
	public void setD8a_npr_org_med(String d8a_npr_org_med) {
		this.d8a_npr_org_med = d8a_npr_org_med;
	}
	public String getD9a_npr_org_hig() {
		return d9a_npr_org_hig;
	}
	public void setD9a_npr_org_hig(String d9a_npr_org_hig) {
		this.d9a_npr_org_hig = d9a_npr_org_hig;
	}
	public String getD10a_trust_low() {
		return d10a_trust_low;
	}
	public void setD10a_trust_low(String d10a_trust_low) {
		this.d10a_trust_low = d10a_trust_low;
	}
	public String getD11a_trust_med() {
		return d11a_trust_med;
	}
	public void setD11a_trust_med(String d11a_trust_med) {
		this.d11a_trust_med = d11a_trust_med;
	}
	public String getD12a_trust_hig() {
		return d12a_trust_hig;
	}
	public void setD12a_trust_hig(String d12a_trust_hig) {
		this.d12a_trust_hig = d12a_trust_hig;
	}
	public String getD13a_all_otr_low() {
		return d13a_all_otr_low;
	}
	public void setD13a_all_otr_low(String d13a_all_otr_low) {
		this.d13a_all_otr_low = d13a_all_otr_low;
	}
	public String getD14a_all_otr_med() {
		return d14a_all_otr_med;
	}
	public void setD14a_all_otr_med(String d14a_all_otr_med) {
		this.d14a_all_otr_med = d14a_all_otr_med;
	}
	public String getD15a_all_otr_hig() {
		return d15a_all_otr_hig;
	}
	public void setD15a_all_otr_hig(String d15a_all_otr_hig) {
		this.d15a_all_otr_hig = d15a_all_otr_hig;
	}
	public String getD16a_peps_dom_low() {
		return d16a_peps_dom_low;
	}
	public void setD16a_peps_dom_low(String d16a_peps_dom_low) {
		this.d16a_peps_dom_low = d16a_peps_dom_low;
	}
	public String getD17a_peps_dom_med() {
		return d17a_peps_dom_med;
	}
	public void setD17a_peps_dom_med(String d17a_peps_dom_med) {
		this.d17a_peps_dom_med = d17a_peps_dom_med;
	}
	public String getD18a_peps_dom_hig() {
		return d18a_peps_dom_hig;
	}
	public void setD18a_peps_dom_hig(String d18a_peps_dom_hig) {
		this.d18a_peps_dom_hig = d18a_peps_dom_hig;
	}
	public String getD19a_peps_frn_low() {
		return d19a_peps_frn_low;
	}
	public void setD19a_peps_frn_low(String d19a_peps_frn_low) {
		this.d19a_peps_frn_low = d19a_peps_frn_low;
	}
	public String getD20a_peps_frn_med() {
		return d20a_peps_frn_med;
	}
	public void setD20a_peps_frn_med(String d20a_peps_frn_med) {
		this.d20a_peps_frn_med = d20a_peps_frn_med;
	}
	public String getD21a_peps_frn_hig() {
		return d21a_peps_frn_hig;
	}
	public void setD21a_peps_frn_hig(String d21a_peps_frn_hig) {
		this.d21a_peps_frn_hig = d21a_peps_frn_hig;
	}
	public String getD22a_tcps_low() {
		return d22a_tcps_low;
	}
	public void setD22a_tcps_low(String d22a_tcps_low) {
		this.d22a_tcps_low = d22a_tcps_low;
	}
	public String getD23a_tcps_med() {
		return d23a_tcps_med;
	}
	public void setD23a_tcps_med(String d23a_tcps_med) {
		this.d23a_tcps_med = d23a_tcps_med;
	}
	public String getD24a_tcps_hig() {
		return d24a_tcps_hig;
	}
	public void setD24a_tcps_hig(String d24a_tcps_hig) {
		this.d24a_tcps_hig = d24a_tcps_hig;
	}
	 
	public BigDecimal getC1f_ind_low_b30() {
		return c1f_ind_low_b30;
	}
	public void setC1f_ind_low_b30(BigDecimal c1f_ind_low_b30) {
		this.c1f_ind_low_b30 = c1f_ind_low_b30;
	}
	public BigDecimal getC2f_ind_med_b30() {
		return c2f_ind_med_b30;
	}
	public void setC2f_ind_med_b30(BigDecimal c2f_ind_med_b30) {
		this.c2f_ind_med_b30 = c2f_ind_med_b30;
	}
	public BigDecimal getC3f_ind_hig_b30() {
		return c3f_ind_hig_b30;
	}
	public void setC3f_ind_hig_b30(BigDecimal c3f_ind_hig_b30) {
		this.c3f_ind_hig_b30 = c3f_ind_hig_b30;
	}
	public BigDecimal getC4f_cor_low_b30() {
		return c4f_cor_low_b30;
	}
	public void setC4f_cor_low_b30(BigDecimal c4f_cor_low_b30) {
		this.c4f_cor_low_b30 = c4f_cor_low_b30;
	}
	public BigDecimal getC5f_cor_med_b30() {
		return c5f_cor_med_b30;
	}
	public void setC5f_cor_med_b30(BigDecimal c5f_cor_med_b30) {
		this.c5f_cor_med_b30 = c5f_cor_med_b30;
	}
	public BigDecimal getC6f_cor_hig_b30() {
		return c6f_cor_hig_b30;
	}
	public void setC6f_cor_hig_b30(BigDecimal c6f_cor_hig_b30) {
		this.c6f_cor_hig_b30 = c6f_cor_hig_b30;
	}
	public BigDecimal getC7f_npr_org_low_b30() {
		return c7f_npr_org_low_b30;
	}
	public void setC7f_npr_org_low_b30(BigDecimal c7f_npr_org_low_b30) {
		this.c7f_npr_org_low_b30 = c7f_npr_org_low_b30;
	}
	public BigDecimal getC8f_npr_org_med_b30() {
		return c8f_npr_org_med_b30;
	}
	public void setC8f_npr_org_med_b30(BigDecimal c8f_npr_org_med_b30) {
		this.c8f_npr_org_med_b30 = c8f_npr_org_med_b30;
	}
	public BigDecimal getC9f_npr_org_hig_b30() {
		return c9f_npr_org_hig_b30;
	}
	public void setC9f_npr_org_hig_b30(BigDecimal c9f_npr_org_hig_b30) {
		this.c9f_npr_org_hig_b30 = c9f_npr_org_hig_b30;
	}
	public BigDecimal getC10f_trust_low_b30() {
		return c10f_trust_low_b30;
	}
	public void setC10f_trust_low_b30(BigDecimal c10f_trust_low_b30) {
		this.c10f_trust_low_b30 = c10f_trust_low_b30;
	}
	public BigDecimal getC11f_trust_med_b30() {
		return c11f_trust_med_b30;
	}
	public void setC11f_trust_med_b30(BigDecimal c11f_trust_med_b30) {
		this.c11f_trust_med_b30 = c11f_trust_med_b30;
	}
	public BigDecimal getC12f_trust_hig_b30() {
		return c12f_trust_hig_b30;
	}
	public void setC12f_trust_hig_b30(BigDecimal c12f_trust_hig_b30) {
		this.c12f_trust_hig_b30 = c12f_trust_hig_b30;
	}
	public BigDecimal getC13f_all_otr_low_b30() {
		return c13f_all_otr_low_b30;
	}
	public void setC13f_all_otr_low_b30(BigDecimal c13f_all_otr_low_b30) {
		this.c13f_all_otr_low_b30 = c13f_all_otr_low_b30;
	}
	public BigDecimal getC14f_all_otr_med_b30() {
		return c14f_all_otr_med_b30;
	}
	public void setC14f_all_otr_med_b30(BigDecimal c14f_all_otr_med_b30) {
		this.c14f_all_otr_med_b30 = c14f_all_otr_med_b30;
	}
	public BigDecimal getC15f_all_otr_hig_b30() {
		return c15f_all_otr_hig_b30;
	}
	public void setC15f_all_otr_hig_b30(BigDecimal c15f_all_otr_hig_b30) {
		this.c15f_all_otr_hig_b30 = c15f_all_otr_hig_b30;
	}
	public BigDecimal getC16f_peps_dom_low_b30() {
		return c16f_peps_dom_low_b30;
	}
	public void setC16f_peps_dom_low_b30(BigDecimal c16f_peps_dom_low_b30) {
		this.c16f_peps_dom_low_b30 = c16f_peps_dom_low_b30;
	}
	public BigDecimal getC17f_peps_dom_med_b30() {
		return c17f_peps_dom_med_b30;
	}
	public void setC17f_peps_dom_med_b30(BigDecimal c17f_peps_dom_med_b30) {
		this.c17f_peps_dom_med_b30 = c17f_peps_dom_med_b30;
	}
	public BigDecimal getC18f_peps_dom_hig_b30() {
		return c18f_peps_dom_hig_b30;
	}
	public void setC18f_peps_dom_hig_b30(BigDecimal c18f_peps_dom_hig_b30) {
		this.c18f_peps_dom_hig_b30 = c18f_peps_dom_hig_b30;
	}
	public BigDecimal getC19f_peps_frn_low_b30() {
		return c19f_peps_frn_low_b30;
	}
	public void setC19f_peps_frn_low_b30(BigDecimal c19f_peps_frn_low_b30) {
		this.c19f_peps_frn_low_b30 = c19f_peps_frn_low_b30;
	}
	public BigDecimal getC20f_peps_frn_med_b30() {
		return c20f_peps_frn_med_b30;
	}
	public void setC20f_peps_frn_med_b30(BigDecimal c20f_peps_frn_med_b30) {
		this.c20f_peps_frn_med_b30 = c20f_peps_frn_med_b30;
	}
	public BigDecimal getC21f_peps_frn_hig_b30() {
		return c21f_peps_frn_hig_b30;
	}
	public void setC21f_peps_frn_hig_b30(BigDecimal c21f_peps_frn_hig_b30) {
		this.c21f_peps_frn_hig_b30 = c21f_peps_frn_hig_b30;
	}
	public BigDecimal getC22f_tcps_low_b30() {
		return c22f_tcps_low_b30;
	}
	public void setC22f_tcps_low_b30(BigDecimal c22f_tcps_low_b30) {
		this.c22f_tcps_low_b30 = c22f_tcps_low_b30;
	}
	public BigDecimal getC23f_tcps_med_b30() {
		return c23f_tcps_med_b30;
	}
	public void setC23f_tcps_med_b30(BigDecimal c23f_tcps_med_b30) {
		this.c23f_tcps_med_b30 = c23f_tcps_med_b30;
	}
	public BigDecimal getC24f_tcps_hig_b30() {
		return c24f_tcps_hig_b30;
	}
	public void setC24f_tcps_hig_b30(BigDecimal c24f_tcps_hig_b30) {
		this.c24f_tcps_hig_b30 = c24f_tcps_hig_b30;
	}
	public BigDecimal getC1g_ind_low_30_60() {
		return c1g_ind_low_30_60;
	}
	public void setC1g_ind_low_30_60(BigDecimal c1g_ind_low_30_60) {
		this.c1g_ind_low_30_60 = c1g_ind_low_30_60;
	}
	public BigDecimal getC2g_ind_med_30_60() {
		return c2g_ind_med_30_60;
	}
	public void setC2g_ind_med_30_60(BigDecimal c2g_ind_med_30_60) {
		this.c2g_ind_med_30_60 = c2g_ind_med_30_60;
	}
	public BigDecimal getC3g_ind_hig_30_60() {
		return c3g_ind_hig_30_60;
	}
	public void setC3g_ind_hig_30_60(BigDecimal c3g_ind_hig_30_60) {
		this.c3g_ind_hig_30_60 = c3g_ind_hig_30_60;
	}
	public BigDecimal getC4g_cor_low_30_60() {
		return c4g_cor_low_30_60;
	}
	public void setC4g_cor_low_30_60(BigDecimal c4g_cor_low_30_60) {
		this.c4g_cor_low_30_60 = c4g_cor_low_30_60;
	}
	public BigDecimal getC5g_cor_med_30_60() {
		return c5g_cor_med_30_60;
	}
	public void setC5g_cor_med_30_60(BigDecimal c5g_cor_med_30_60) {
		this.c5g_cor_med_30_60 = c5g_cor_med_30_60;
	}
	public BigDecimal getC6g_cor_hig_30_60() {
		return c6g_cor_hig_30_60;
	}
	public void setC6g_cor_hig_30_60(BigDecimal c6g_cor_hig_30_60) {
		this.c6g_cor_hig_30_60 = c6g_cor_hig_30_60;
	}
	public BigDecimal getC7g_npr_org_low_30_60() {
		return c7g_npr_org_low_30_60;
	}
	public void setC7g_npr_org_low_30_60(BigDecimal c7g_npr_org_low_30_60) {
		this.c7g_npr_org_low_30_60 = c7g_npr_org_low_30_60;
	}
	public BigDecimal getC8g_npr_org_med_30_60() {
		return c8g_npr_org_med_30_60;
	}
	public void setC8g_npr_org_med_30_60(BigDecimal c8g_npr_org_med_30_60) {
		this.c8g_npr_org_med_30_60 = c8g_npr_org_med_30_60;
	}
	public BigDecimal getC9g_npr_org_hig_30_60() {
		return c9g_npr_org_hig_30_60;
	}
	public void setC9g_npr_org_hig_30_60(BigDecimal c9g_npr_org_hig_30_60) {
		this.c9g_npr_org_hig_30_60 = c9g_npr_org_hig_30_60;
	}
	public BigDecimal getC10g_trust_low_30_60() {
		return c10g_trust_low_30_60;
	}
	public void setC10g_trust_low_30_60(BigDecimal c10g_trust_low_30_60) {
		this.c10g_trust_low_30_60 = c10g_trust_low_30_60;
	}
	public BigDecimal getC11g_trust_med_30_60() {
		return c11g_trust_med_30_60;
	}
	public void setC11g_trust_med_30_60(BigDecimal c11g_trust_med_30_60) {
		this.c11g_trust_med_30_60 = c11g_trust_med_30_60;
	}
	public BigDecimal getC12g_trust_hig_30_60() {
		return c12g_trust_hig_30_60;
	}
	public void setC12g_trust_hig_30_60(BigDecimal c12g_trust_hig_30_60) {
		this.c12g_trust_hig_30_60 = c12g_trust_hig_30_60;
	}
	public BigDecimal getC13g_all_otr_low_30_60() {
		return c13g_all_otr_low_30_60;
	}
	public void setC13g_all_otr_low_30_60(BigDecimal c13g_all_otr_low_30_60) {
		this.c13g_all_otr_low_30_60 = c13g_all_otr_low_30_60;
	}
	public BigDecimal getC14g_all_otr_med_30_60() {
		return c14g_all_otr_med_30_60;
	}
	public void setC14g_all_otr_med_30_60(BigDecimal c14g_all_otr_med_30_60) {
		this.c14g_all_otr_med_30_60 = c14g_all_otr_med_30_60;
	}
	public BigDecimal getC15g_all_otr_hig_30_60() {
		return c15g_all_otr_hig_30_60;
	}
	public void setC15g_all_otr_hig_30_60(BigDecimal c15g_all_otr_hig_30_60) {
		this.c15g_all_otr_hig_30_60 = c15g_all_otr_hig_30_60;
	}
	public BigDecimal getC16g_peps_dom_low_30_60() {
		return c16g_peps_dom_low_30_60;
	}
	public void setC16g_peps_dom_low_30_60(BigDecimal c16g_peps_dom_low_30_60) {
		this.c16g_peps_dom_low_30_60 = c16g_peps_dom_low_30_60;
	}
	public BigDecimal getC17g_peps_dom_med_30_60() {
		return c17g_peps_dom_med_30_60;
	}
	public void setC17g_peps_dom_med_30_60(BigDecimal c17g_peps_dom_med_30_60) {
		this.c17g_peps_dom_med_30_60 = c17g_peps_dom_med_30_60;
	}
	public BigDecimal getC18g_peps_dom_hig_30_60() {
		return c18g_peps_dom_hig_30_60;
	}
	public void setC18g_peps_dom_hig_30_60(BigDecimal c18g_peps_dom_hig_30_60) {
		this.c18g_peps_dom_hig_30_60 = c18g_peps_dom_hig_30_60;
	}
	public BigDecimal getC19g_peps_frn_low_30_60() {
		return c19g_peps_frn_low_30_60;
	}
	public void setC19g_peps_frn_low_30_60(BigDecimal c19g_peps_frn_low_30_60) {
		this.c19g_peps_frn_low_30_60 = c19g_peps_frn_low_30_60;
	}
	public BigDecimal getC20g_peps_frn_med_30_60() {
		return c20g_peps_frn_med_30_60;
	}
	public void setC20g_peps_frn_med_30_60(BigDecimal c20g_peps_frn_med_30_60) {
		this.c20g_peps_frn_med_30_60 = c20g_peps_frn_med_30_60;
	}
	public BigDecimal getC21g_peps_frn_hig_30_60() {
		return c21g_peps_frn_hig_30_60;
	}
	public void setC21g_peps_frn_hig_30_60(BigDecimal c21g_peps_frn_hig_30_60) {
		this.c21g_peps_frn_hig_30_60 = c21g_peps_frn_hig_30_60;
	}
	public BigDecimal getC22g_tcps_low_30_60() {
		return c22g_tcps_low_30_60;
	}
	public void setC22g_tcps_low_30_60(BigDecimal c22g_tcps_low_30_60) {
		this.c22g_tcps_low_30_60 = c22g_tcps_low_30_60;
	}
	public BigDecimal getC23g_tcps_med_30_60() {
		return c23g_tcps_med_30_60;
	}
	public void setC23g_tcps_med_30_60(BigDecimal c23g_tcps_med_30_60) {
		this.c23g_tcps_med_30_60 = c23g_tcps_med_30_60;
	}
	public BigDecimal getC24g_tcps_hig_30_60() {
		return c24g_tcps_hig_30_60;
	}
	public void setC24g_tcps_hig_30_60(BigDecimal c24g_tcps_hig_30_60) {
		this.c24g_tcps_hig_30_60 = c24g_tcps_hig_30_60;
	}
	public BigDecimal getC1h_ind_low_60_90() {
		return c1h_ind_low_60_90;
	}
	public void setC1h_ind_low_60_90(BigDecimal c1h_ind_low_60_90) {
		this.c1h_ind_low_60_90 = c1h_ind_low_60_90;
	}
	public BigDecimal getC2h_ind_med_60_90() {
		return c2h_ind_med_60_90;
	}
	public void setC2h_ind_med_60_90(BigDecimal c2h_ind_med_60_90) {
		this.c2h_ind_med_60_90 = c2h_ind_med_60_90;
	}
	public BigDecimal getC3h_ind_hig_60_90() {
		return c3h_ind_hig_60_90;
	}
	public void setC3h_ind_hig_60_90(BigDecimal c3h_ind_hig_60_90) {
		this.c3h_ind_hig_60_90 = c3h_ind_hig_60_90;
	}
	public BigDecimal getC4h_cor_low_60_90() {
		return c4h_cor_low_60_90;
	}
	public void setC4h_cor_low_60_90(BigDecimal c4h_cor_low_60_90) {
		this.c4h_cor_low_60_90 = c4h_cor_low_60_90;
	}
	public BigDecimal getC5h_cor_med_60_90() {
		return c5h_cor_med_60_90;
	}
	public void setC5h_cor_med_60_90(BigDecimal c5h_cor_med_60_90) {
		this.c5h_cor_med_60_90 = c5h_cor_med_60_90;
	}
	public BigDecimal getC6h_cor_hig_60_90() {
		return c6h_cor_hig_60_90;
	}
	public void setC6h_cor_hig_60_90(BigDecimal c6h_cor_hig_60_90) {
		this.c6h_cor_hig_60_90 = c6h_cor_hig_60_90;
	}
	public BigDecimal getC7h_npr_org_low_60_90() {
		return c7h_npr_org_low_60_90;
	}
	public void setC7h_npr_org_low_60_90(BigDecimal c7h_npr_org_low_60_90) {
		this.c7h_npr_org_low_60_90 = c7h_npr_org_low_60_90;
	}
	public BigDecimal getC8h_npr_org_med_60_90() {
		return c8h_npr_org_med_60_90;
	}
	public void setC8h_npr_org_med_60_90(BigDecimal c8h_npr_org_med_60_90) {
		this.c8h_npr_org_med_60_90 = c8h_npr_org_med_60_90;
	}
	public BigDecimal getC9h_npr_org_hig_60_90() {
		return c9h_npr_org_hig_60_90;
	}
	public void setC9h_npr_org_hig_60_90(BigDecimal c9h_npr_org_hig_60_90) {
		this.c9h_npr_org_hig_60_90 = c9h_npr_org_hig_60_90;
	}
	public BigDecimal getC10h_trust_low_60_90() {
		return c10h_trust_low_60_90;
	}
	public void setC10h_trust_low_60_90(BigDecimal c10h_trust_low_60_90) {
		this.c10h_trust_low_60_90 = c10h_trust_low_60_90;
	}
	public BigDecimal getC11h_trust_med_60_90() {
		return c11h_trust_med_60_90;
	}
	public void setC11h_trust_med_60_90(BigDecimal c11h_trust_med_60_90) {
		this.c11h_trust_med_60_90 = c11h_trust_med_60_90;
	}
	public BigDecimal getC12h_trust_hig_60_90() {
		return c12h_trust_hig_60_90;
	}
	public void setC12h_trust_hig_60_90(BigDecimal c12h_trust_hig_60_90) {
		this.c12h_trust_hig_60_90 = c12h_trust_hig_60_90;
	}
	public BigDecimal getC13h_all_otr_low_60_90() {
		return c13h_all_otr_low_60_90;
	}
	public void setC13h_all_otr_low_60_90(BigDecimal c13h_all_otr_low_60_90) {
		this.c13h_all_otr_low_60_90 = c13h_all_otr_low_60_90;
	}
	public BigDecimal getC14h_all_otr_med_60_90() {
		return c14h_all_otr_med_60_90;
	}
	public void setC14h_all_otr_med_60_90(BigDecimal c14h_all_otr_med_60_90) {
		this.c14h_all_otr_med_60_90 = c14h_all_otr_med_60_90;
	}
	public BigDecimal getC15h_all_otr_hig_60_90() {
		return c15h_all_otr_hig_60_90;
	}
	public void setC15h_all_otr_hig_60_90(BigDecimal c15h_all_otr_hig_60_90) {
		this.c15h_all_otr_hig_60_90 = c15h_all_otr_hig_60_90;
	}
	public BigDecimal getC16h_peps_dom_low_60_90() {
		return c16h_peps_dom_low_60_90;
	}
	public void setC16h_peps_dom_low_60_90(BigDecimal c16h_peps_dom_low_60_90) {
		this.c16h_peps_dom_low_60_90 = c16h_peps_dom_low_60_90;
	}
	public BigDecimal getC17h_peps_dom_med_60_90() {
		return c17h_peps_dom_med_60_90;
	}
	public void setC17h_peps_dom_med_60_90(BigDecimal c17h_peps_dom_med_60_90) {
		this.c17h_peps_dom_med_60_90 = c17h_peps_dom_med_60_90;
	}
	public BigDecimal getC18h_peps_dom_hig_60_90() {
		return c18h_peps_dom_hig_60_90;
	}
	public void setC18h_peps_dom_hig_60_90(BigDecimal c18h_peps_dom_hig_60_90) {
		this.c18h_peps_dom_hig_60_90 = c18h_peps_dom_hig_60_90;
	}
	public BigDecimal getC19h_peps_frn_low_60_90() {
		return c19h_peps_frn_low_60_90;
	}
	public void setC19h_peps_frn_low_60_90(BigDecimal c19h_peps_frn_low_60_90) {
		this.c19h_peps_frn_low_60_90 = c19h_peps_frn_low_60_90;
	}
	public BigDecimal getC20h_peps_frn_med_60_90() {
		return c20h_peps_frn_med_60_90;
	}
	public void setC20h_peps_frn_med_60_90(BigDecimal c20h_peps_frn_med_60_90) {
		this.c20h_peps_frn_med_60_90 = c20h_peps_frn_med_60_90;
	}
	public BigDecimal getC21h_peps_frn_hig_60_90() {
		return c21h_peps_frn_hig_60_90;
	}
	public void setC21h_peps_frn_hig_60_90(BigDecimal c21h_peps_frn_hig_60_90) {
		this.c21h_peps_frn_hig_60_90 = c21h_peps_frn_hig_60_90;
	}
	public BigDecimal getC22h_tcps_low_60_90() {
		return c22h_tcps_low_60_90;
	}
	public void setC22h_tcps_low_60_90(BigDecimal c22h_tcps_low_60_90) {
		this.c22h_tcps_low_60_90 = c22h_tcps_low_60_90;
	}
	public BigDecimal getC23h_tcps_med_60_90() {
		return c23h_tcps_med_60_90;
	}
	public void setC23h_tcps_med_60_90(BigDecimal c23h_tcps_med_60_90) {
		this.c23h_tcps_med_60_90 = c23h_tcps_med_60_90;
	}
	public BigDecimal getC24h_tcps_hig_60_90() {
		return c24h_tcps_hig_60_90;
	}
	public void setC24h_tcps_hig_60_90(BigDecimal c24h_tcps_hig_60_90) {
		this.c24h_tcps_hig_60_90 = c24h_tcps_hig_60_90;
	}
	public BigDecimal getC1i_ind_low_a90() {
		return c1i_ind_low_a90;
	}
	public void setC1i_ind_low_a90(BigDecimal c1i_ind_low_a90) {
		this.c1i_ind_low_a90 = c1i_ind_low_a90;
	}
	public BigDecimal getC2i_ind_med_a90() {
		return c2i_ind_med_a90;
	}
	public void setC2i_ind_med_a90(BigDecimal c2i_ind_med_a90) {
		this.c2i_ind_med_a90 = c2i_ind_med_a90;
	}
	public BigDecimal getC3i_ind_hig_a90() {
		return c3i_ind_hig_a90;
	}
	public void setC3i_ind_hig_a90(BigDecimal c3i_ind_hig_a90) {
		this.c3i_ind_hig_a90 = c3i_ind_hig_a90;
	}
	public BigDecimal getC4i_cor_low_a90() {
		return c4i_cor_low_a90;
	}
	public void setC4i_cor_low_a90(BigDecimal c4i_cor_low_a90) {
		this.c4i_cor_low_a90 = c4i_cor_low_a90;
	}
	public BigDecimal getC5i_cor_med_a90() {
		return c5i_cor_med_a90;
	}
	public void setC5i_cor_med_a90(BigDecimal c5i_cor_med_a90) {
		this.c5i_cor_med_a90 = c5i_cor_med_a90;
	}
	public BigDecimal getC6i_cor_hig_a90() {
		return c6i_cor_hig_a90;
	}
	public void setC6i_cor_hig_a90(BigDecimal c6i_cor_hig_a90) {
		this.c6i_cor_hig_a90 = c6i_cor_hig_a90;
	}
	public BigDecimal getC7i_npr_org_low_a90() {
		return c7i_npr_org_low_a90;
	}
	public void setC7i_npr_org_low_a90(BigDecimal c7i_npr_org_low_a90) {
		this.c7i_npr_org_low_a90 = c7i_npr_org_low_a90;
	}
	public BigDecimal getC8i_npr_org_med_a90() {
		return c8i_npr_org_med_a90;
	}
	public void setC8i_npr_org_med_a90(BigDecimal c8i_npr_org_med_a90) {
		this.c8i_npr_org_med_a90 = c8i_npr_org_med_a90;
	}
	public BigDecimal getC9i_npr_org_hig_a90() {
		return c9i_npr_org_hig_a90;
	}
	public void setC9i_npr_org_hig_a90(BigDecimal c9i_npr_org_hig_a90) {
		this.c9i_npr_org_hig_a90 = c9i_npr_org_hig_a90;
	}
	public BigDecimal getC10i_trust_low_a90() {
		return c10i_trust_low_a90;
	}
	public void setC10i_trust_low_a90(BigDecimal c10i_trust_low_a90) {
		this.c10i_trust_low_a90 = c10i_trust_low_a90;
	}
	public BigDecimal getC11i_trust_med_a90() {
		return c11i_trust_med_a90;
	}
	public void setC11i_trust_med_a90(BigDecimal c11i_trust_med_a90) {
		this.c11i_trust_med_a90 = c11i_trust_med_a90;
	}
	public BigDecimal getC12i_trust_hig_a90() {
		return c12i_trust_hig_a90;
	}
	public void setC12i_trust_hig_a90(BigDecimal c12i_trust_hig_a90) {
		this.c12i_trust_hig_a90 = c12i_trust_hig_a90;
	}
	public BigDecimal getC13i_all_otr_low_a90() {
		return c13i_all_otr_low_a90;
	}
	public void setC13i_all_otr_low_a90(BigDecimal c13i_all_otr_low_a90) {
		this.c13i_all_otr_low_a90 = c13i_all_otr_low_a90;
	}
	public BigDecimal getC14i_all_otr_med_a90() {
		return c14i_all_otr_med_a90;
	}
	public void setC14i_all_otr_med_a90(BigDecimal c14i_all_otr_med_a90) {
		this.c14i_all_otr_med_a90 = c14i_all_otr_med_a90;
	}
	public BigDecimal getC15i_all_otr_hig_a90() {
		return c15i_all_otr_hig_a90;
	}
	public void setC15i_all_otr_hig_a90(BigDecimal c15i_all_otr_hig_a90) {
		this.c15i_all_otr_hig_a90 = c15i_all_otr_hig_a90;
	}
	public BigDecimal getC16i_peps_dom_low_a90() {
		return c16i_peps_dom_low_a90;
	}
	public void setC16i_peps_dom_low_a90(BigDecimal c16i_peps_dom_low_a90) {
		this.c16i_peps_dom_low_a90 = c16i_peps_dom_low_a90;
	}
	public BigDecimal getC17i_peps_dom_med_a90() {
		return c17i_peps_dom_med_a90;
	}
	public void setC17i_peps_dom_med_a90(BigDecimal c17i_peps_dom_med_a90) {
		this.c17i_peps_dom_med_a90 = c17i_peps_dom_med_a90;
	}
	public BigDecimal getC18i_peps_dom_hig_a90() {
		return c18i_peps_dom_hig_a90;
	}
	public void setC18i_peps_dom_hig_a90(BigDecimal c18i_peps_dom_hig_a90) {
		this.c18i_peps_dom_hig_a90 = c18i_peps_dom_hig_a90;
	}
	public BigDecimal getC19i_peps_frn_low_a90() {
		return c19i_peps_frn_low_a90;
	}
	public void setC19i_peps_frn_low_a90(BigDecimal c19i_peps_frn_low_a90) {
		this.c19i_peps_frn_low_a90 = c19i_peps_frn_low_a90;
	}
	public BigDecimal getC20i_peps_frn_med_a90() {
		return c20i_peps_frn_med_a90;
	}
	public void setC20i_peps_frn_med_a90(BigDecimal c20i_peps_frn_med_a90) {
		this.c20i_peps_frn_med_a90 = c20i_peps_frn_med_a90;
	}
	public BigDecimal getC21i_peps_frn_hig_a90() {
		return c21i_peps_frn_hig_a90;
	}
	public void setC21i_peps_frn_hig_a90(BigDecimal c21i_peps_frn_hig_a90) {
		this.c21i_peps_frn_hig_a90 = c21i_peps_frn_hig_a90;
	}
	public BigDecimal getC22i_tcps_low_a90() {
		return c22i_tcps_low_a90;
	}
	public void setC22i_tcps_low_a90(BigDecimal c22i_tcps_low_a90) {
		this.c22i_tcps_low_a90 = c22i_tcps_low_a90;
	}
	public BigDecimal getC23i_tcps_med_a90() {
		return c23i_tcps_med_a90;
	}
	public void setC23i_tcps_med_a90(BigDecimal c23i_tcps_med_a90) {
		this.c23i_tcps_med_a90 = c23i_tcps_med_a90;
	}
	public BigDecimal getC24i_tcps_hig_a90() {
		return c24i_tcps_hig_a90;
	}
	public void setC24i_tcps_hig_a90(BigDecimal c24i_tcps_hig_a90) {
		this.c24i_tcps_hig_a90 = c24i_tcps_hig_a90;
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
	public T7Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getVerify_flg() {
		return verify_flg;
	}
	public void setVerify_flg(String verify_flg) {
		this.verify_flg = verify_flg;
	}
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
	public T7Report(String d1a_ind_low, String d2a_ind_med, String d3a_ind_hig, String d4a_cor_low, String d5a_cor_med,
			String d6a_cor_hig, String d7a_npr_org_low, String d8a_npr_org_med, String d9a_npr_org_hig,
			String d10a_trust_low, String d11a_trust_med, String d12a_trust_hig, String d13a_all_otr_low,
			String d14a_all_otr_med, String d15a_all_otr_hig, String d16a_peps_dom_low, String d17a_peps_dom_med,
			String d18a_peps_dom_hig, String d19a_peps_frn_low, String d20a_peps_frn_med, String d21a_peps_frn_hig,
			String d22a_tcps_low, String d23a_tcps_med, String d24a_tcps_hig, BigDecimal c1f_ind_low_b30,
			BigDecimal c2f_ind_med_b30, BigDecimal c3f_ind_hig_b30, BigDecimal c4f_cor_low_b30,
			BigDecimal c5f_cor_med_b30, BigDecimal c6f_cor_hig_b30, BigDecimal c7f_npr_org_low_b30,
			BigDecimal c8f_npr_org_med_b30, BigDecimal c9f_npr_org_hig_b30, BigDecimal c10f_trust_low_b30,
			BigDecimal c11f_trust_med_b30, BigDecimal c12f_trust_hig_b30, BigDecimal c13f_all_otr_low_b30,
			BigDecimal c14f_all_otr_med_b30, BigDecimal c15f_all_otr_hig_b30, BigDecimal c16f_peps_dom_low_b30,
			BigDecimal c17f_peps_dom_med_b30, BigDecimal c18f_peps_dom_hig_b30, BigDecimal c19f_peps_frn_low_b30,
			BigDecimal c20f_peps_frn_med_b30, BigDecimal c21f_peps_frn_hig_b30, BigDecimal c22f_tcps_low_b30,
			BigDecimal c23f_tcps_med_b30, BigDecimal c24f_tcps_hig_b30, BigDecimal c1g_ind_low_30_60,
			BigDecimal c2g_ind_med_30_60, BigDecimal c3g_ind_hig_30_60, BigDecimal c4g_cor_low_30_60,
			BigDecimal c5g_cor_med_30_60, BigDecimal c6g_cor_hig_30_60, BigDecimal c7g_npr_org_low_30_60,
			BigDecimal c8g_npr_org_med_30_60, BigDecimal c9g_npr_org_hig_30_60, BigDecimal c10g_trust_low_30_60,
			BigDecimal c11g_trust_med_30_60, BigDecimal c12g_trust_hig_30_60, BigDecimal c13g_all_otr_low_30_60,
			BigDecimal c14g_all_otr_med_30_60, BigDecimal c15g_all_otr_hig_30_60, BigDecimal c16g_peps_dom_low_30_60,
			BigDecimal c17g_peps_dom_med_30_60, BigDecimal c18g_peps_dom_hig_30_60, BigDecimal c19g_peps_frn_low_30_60,
			BigDecimal c20g_peps_frn_med_30_60, BigDecimal c21g_peps_frn_hig_30_60, BigDecimal c22g_tcps_low_30_60,
			BigDecimal c23g_tcps_med_30_60, BigDecimal c24g_tcps_hig_30_60, BigDecimal c1h_ind_low_60_90,
			BigDecimal c2h_ind_med_60_90, BigDecimal c3h_ind_hig_60_90, BigDecimal c4h_cor_low_60_90,
			BigDecimal c5h_cor_med_60_90, BigDecimal c6h_cor_hig_60_90, BigDecimal c7h_npr_org_low_60_90,
			BigDecimal c8h_npr_org_med_60_90, BigDecimal c9h_npr_org_hig_60_90, BigDecimal c10h_trust_low_60_90,
			BigDecimal c11h_trust_med_60_90, BigDecimal c12h_trust_hig_60_90, BigDecimal c13h_all_otr_low_60_90,
			BigDecimal c14h_all_otr_med_60_90, BigDecimal c15h_all_otr_hig_60_90, BigDecimal c16h_peps_dom_low_60_90,
			BigDecimal c17h_peps_dom_med_60_90, BigDecimal c18h_peps_dom_hig_60_90, BigDecimal c19h_peps_frn_low_60_90,
			BigDecimal c20h_peps_frn_med_60_90, BigDecimal c21h_peps_frn_hig_60_90, BigDecimal c22h_tcps_low_60_90,
			BigDecimal c23h_tcps_med_60_90, BigDecimal c24h_tcps_hig_60_90, BigDecimal c1i_ind_low_a90,
			BigDecimal c2i_ind_med_a90, BigDecimal c3i_ind_hig_a90, BigDecimal c4i_cor_low_a90,
			BigDecimal c5i_cor_med_a90, BigDecimal c6i_cor_hig_a90, BigDecimal c7i_npr_org_low_a90,
			BigDecimal c8i_npr_org_med_a90, BigDecimal c9i_npr_org_hig_a90, BigDecimal c10i_trust_low_a90,
			BigDecimal c11i_trust_med_a90, BigDecimal c12i_trust_hig_a90, BigDecimal c13i_all_otr_low_a90,
			BigDecimal c14i_all_otr_med_a90, BigDecimal c15i_all_otr_hig_a90, BigDecimal c16i_peps_dom_low_a90,
			BigDecimal c17i_peps_dom_med_a90, BigDecimal c18i_peps_dom_hig_a90, BigDecimal c19i_peps_frn_low_a90,
			BigDecimal c20i_peps_frn_med_a90, BigDecimal c21i_peps_frn_hig_a90, BigDecimal c22i_tcps_low_a90,
			BigDecimal c23i_tcps_med_a90, BigDecimal c24i_tcps_hig_a90, String report_code, String report_name,
			Date report_date, Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to,
			String rep_freq, String nil_report_flg, String arch_flg, String entity_flg, String modify_flg,
			String verify_flg, String entry_user, String modify_user, String verify_user, Date entry_time,
			Date modify_time, Date verify_time) {
		super();
		this.d1a_ind_low = d1a_ind_low;
		this.d2a_ind_med = d2a_ind_med;
		this.d3a_ind_hig = d3a_ind_hig;
		this.d4a_cor_low = d4a_cor_low;
		this.d5a_cor_med = d5a_cor_med;
		this.d6a_cor_hig = d6a_cor_hig;
		this.d7a_npr_org_low = d7a_npr_org_low;
		this.d8a_npr_org_med = d8a_npr_org_med;
		this.d9a_npr_org_hig = d9a_npr_org_hig;
		this.d10a_trust_low = d10a_trust_low;
		this.d11a_trust_med = d11a_trust_med;
		this.d12a_trust_hig = d12a_trust_hig;
		this.d13a_all_otr_low = d13a_all_otr_low;
		this.d14a_all_otr_med = d14a_all_otr_med;
		this.d15a_all_otr_hig = d15a_all_otr_hig;
		this.d16a_peps_dom_low = d16a_peps_dom_low;
		this.d17a_peps_dom_med = d17a_peps_dom_med;
		this.d18a_peps_dom_hig = d18a_peps_dom_hig;
		this.d19a_peps_frn_low = d19a_peps_frn_low;
		this.d20a_peps_frn_med = d20a_peps_frn_med;
		this.d21a_peps_frn_hig = d21a_peps_frn_hig;
		this.d22a_tcps_low = d22a_tcps_low;
		this.d23a_tcps_med = d23a_tcps_med;
		this.d24a_tcps_hig = d24a_tcps_hig;
		this.c1f_ind_low_b30 = c1f_ind_low_b30;
		this.c2f_ind_med_b30 = c2f_ind_med_b30;
		this.c3f_ind_hig_b30 = c3f_ind_hig_b30;
		this.c4f_cor_low_b30 = c4f_cor_low_b30;
		this.c5f_cor_med_b30 = c5f_cor_med_b30;
		this.c6f_cor_hig_b30 = c6f_cor_hig_b30;
		this.c7f_npr_org_low_b30 = c7f_npr_org_low_b30;
		this.c8f_npr_org_med_b30 = c8f_npr_org_med_b30;
		this.c9f_npr_org_hig_b30 = c9f_npr_org_hig_b30;
		this.c10f_trust_low_b30 = c10f_trust_low_b30;
		this.c11f_trust_med_b30 = c11f_trust_med_b30;
		this.c12f_trust_hig_b30 = c12f_trust_hig_b30;
		this.c13f_all_otr_low_b30 = c13f_all_otr_low_b30;
		this.c14f_all_otr_med_b30 = c14f_all_otr_med_b30;
		this.c15f_all_otr_hig_b30 = c15f_all_otr_hig_b30;
		this.c16f_peps_dom_low_b30 = c16f_peps_dom_low_b30;
		this.c17f_peps_dom_med_b30 = c17f_peps_dom_med_b30;
		this.c18f_peps_dom_hig_b30 = c18f_peps_dom_hig_b30;
		this.c19f_peps_frn_low_b30 = c19f_peps_frn_low_b30;
		this.c20f_peps_frn_med_b30 = c20f_peps_frn_med_b30;
		this.c21f_peps_frn_hig_b30 = c21f_peps_frn_hig_b30;
		this.c22f_tcps_low_b30 = c22f_tcps_low_b30;
		this.c23f_tcps_med_b30 = c23f_tcps_med_b30;
		this.c24f_tcps_hig_b30 = c24f_tcps_hig_b30;
		this.c1g_ind_low_30_60 = c1g_ind_low_30_60;
		this.c2g_ind_med_30_60 = c2g_ind_med_30_60;
		this.c3g_ind_hig_30_60 = c3g_ind_hig_30_60;
		this.c4g_cor_low_30_60 = c4g_cor_low_30_60;
		this.c5g_cor_med_30_60 = c5g_cor_med_30_60;
		this.c6g_cor_hig_30_60 = c6g_cor_hig_30_60;
		this.c7g_npr_org_low_30_60 = c7g_npr_org_low_30_60;
		this.c8g_npr_org_med_30_60 = c8g_npr_org_med_30_60;
		this.c9g_npr_org_hig_30_60 = c9g_npr_org_hig_30_60;
		this.c10g_trust_low_30_60 = c10g_trust_low_30_60;
		this.c11g_trust_med_30_60 = c11g_trust_med_30_60;
		this.c12g_trust_hig_30_60 = c12g_trust_hig_30_60;
		this.c13g_all_otr_low_30_60 = c13g_all_otr_low_30_60;
		this.c14g_all_otr_med_30_60 = c14g_all_otr_med_30_60;
		this.c15g_all_otr_hig_30_60 = c15g_all_otr_hig_30_60;
		this.c16g_peps_dom_low_30_60 = c16g_peps_dom_low_30_60;
		this.c17g_peps_dom_med_30_60 = c17g_peps_dom_med_30_60;
		this.c18g_peps_dom_hig_30_60 = c18g_peps_dom_hig_30_60;
		this.c19g_peps_frn_low_30_60 = c19g_peps_frn_low_30_60;
		this.c20g_peps_frn_med_30_60 = c20g_peps_frn_med_30_60;
		this.c21g_peps_frn_hig_30_60 = c21g_peps_frn_hig_30_60;
		this.c22g_tcps_low_30_60 = c22g_tcps_low_30_60;
		this.c23g_tcps_med_30_60 = c23g_tcps_med_30_60;
		this.c24g_tcps_hig_30_60 = c24g_tcps_hig_30_60;
		this.c1h_ind_low_60_90 = c1h_ind_low_60_90;
		this.c2h_ind_med_60_90 = c2h_ind_med_60_90;
		this.c3h_ind_hig_60_90 = c3h_ind_hig_60_90;
		this.c4h_cor_low_60_90 = c4h_cor_low_60_90;
		this.c5h_cor_med_60_90 = c5h_cor_med_60_90;
		this.c6h_cor_hig_60_90 = c6h_cor_hig_60_90;
		this.c7h_npr_org_low_60_90 = c7h_npr_org_low_60_90;
		this.c8h_npr_org_med_60_90 = c8h_npr_org_med_60_90;
		this.c9h_npr_org_hig_60_90 = c9h_npr_org_hig_60_90;
		this.c10h_trust_low_60_90 = c10h_trust_low_60_90;
		this.c11h_trust_med_60_90 = c11h_trust_med_60_90;
		this.c12h_trust_hig_60_90 = c12h_trust_hig_60_90;
		this.c13h_all_otr_low_60_90 = c13h_all_otr_low_60_90;
		this.c14h_all_otr_med_60_90 = c14h_all_otr_med_60_90;
		this.c15h_all_otr_hig_60_90 = c15h_all_otr_hig_60_90;
		this.c16h_peps_dom_low_60_90 = c16h_peps_dom_low_60_90;
		this.c17h_peps_dom_med_60_90 = c17h_peps_dom_med_60_90;
		this.c18h_peps_dom_hig_60_90 = c18h_peps_dom_hig_60_90;
		this.c19h_peps_frn_low_60_90 = c19h_peps_frn_low_60_90;
		this.c20h_peps_frn_med_60_90 = c20h_peps_frn_med_60_90;
		this.c21h_peps_frn_hig_60_90 = c21h_peps_frn_hig_60_90;
		this.c22h_tcps_low_60_90 = c22h_tcps_low_60_90;
		this.c23h_tcps_med_60_90 = c23h_tcps_med_60_90;
		this.c24h_tcps_hig_60_90 = c24h_tcps_hig_60_90;
		this.c1i_ind_low_a90 = c1i_ind_low_a90;
		this.c2i_ind_med_a90 = c2i_ind_med_a90;
		this.c3i_ind_hig_a90 = c3i_ind_hig_a90;
		this.c4i_cor_low_a90 = c4i_cor_low_a90;
		this.c5i_cor_med_a90 = c5i_cor_med_a90;
		this.c6i_cor_hig_a90 = c6i_cor_hig_a90;
		this.c7i_npr_org_low_a90 = c7i_npr_org_low_a90;
		this.c8i_npr_org_med_a90 = c8i_npr_org_med_a90;
		this.c9i_npr_org_hig_a90 = c9i_npr_org_hig_a90;
		this.c10i_trust_low_a90 = c10i_trust_low_a90;
		this.c11i_trust_med_a90 = c11i_trust_med_a90;
		this.c12i_trust_hig_a90 = c12i_trust_hig_a90;
		this.c13i_all_otr_low_a90 = c13i_all_otr_low_a90;
		this.c14i_all_otr_med_a90 = c14i_all_otr_med_a90;
		this.c15i_all_otr_hig_a90 = c15i_all_otr_hig_a90;
		this.c16i_peps_dom_low_a90 = c16i_peps_dom_low_a90;
		this.c17i_peps_dom_med_a90 = c17i_peps_dom_med_a90;
		this.c18i_peps_dom_hig_a90 = c18i_peps_dom_hig_a90;
		this.c19i_peps_frn_low_a90 = c19i_peps_frn_low_a90;
		this.c20i_peps_frn_med_a90 = c20i_peps_frn_med_a90;
		this.c21i_peps_frn_hig_a90 = c21i_peps_frn_hig_a90;
		this.c22i_tcps_low_a90 = c22i_tcps_low_a90;
		this.c23i_tcps_med_a90 = c23i_tcps_med_a90;
		this.c24i_tcps_hig_a90 = c24i_tcps_hig_a90;
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
		this.verify_flg = verify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}
		
	}

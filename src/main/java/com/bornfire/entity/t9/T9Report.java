package com.bornfire.entity.t9;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T9_DOM_OW_REMIT_TABLE")
public class T9Report {
	
	private String	d1_cur_bmur30k;
	private String	d2_cur_bmur30_60k;
	private String	d3_cur_bmur60_150k;
	private String	d4_cur_bmur150_300k;
	private String	d5_cur_bmur300_500k;
	private String	d6_cur_bmur500_1000k;
	private String	d7_cur_bmur1000_15000k;
	private String	d8_cur_bmur1500_3000k;
	private String	d9_cur_amur3000k;
	private String	d10_cur_total;
	private BigDecimal	c11b_cur_bmur30k_not_low;
	private BigDecimal	c12b_cur_mur30_60k_not_low;
	private BigDecimal	c13b_cur_mur60_150k_not_low;
	private BigDecimal	c14b_cur_mur150_300k_not_low;
	private BigDecimal	c15b_cur_mur300_500k_not_low;
	private BigDecimal	c16b_cur_mur500_1000k_not_low;
	private BigDecimal	c17b_cur_mur1000_15000k_not_low;
	private BigDecimal	c18b_cur_mur1500_3000k_not_low;
	private BigDecimal	c19b_cur_amur3000k_not_low;
	private BigDecimal	c20b_cur_total_not_low;
	private BigDecimal	c11c_cur_bmur30k_tamt_low;
	private BigDecimal	c12c_cur_bmur30_60k_tamt_low;
	private BigDecimal	c13c_cur_bmur60_150k_tamt_low;
	private BigDecimal	c14c_cur_bmur150_300k_tamt_low;
	private BigDecimal	c15c_cur_bmur300_500k_tamt_low;
	private BigDecimal	c16c_cur_bmur500_1000k_tamt_low;
	private BigDecimal	c17c_cur_bmur1000_15000k_tamt_low;
	private BigDecimal	c18c_cur_bmur1500_3000k_tamt_low;
	private BigDecimal	c19c_cur_amur3000k_tamt_low;
	private BigDecimal	c20c_cur_total_tamt_low;
	private BigDecimal	c11d_cur_bmur30k_not_med;
	private BigDecimal	c12d_cur_mur30_60k_not_med;
	private BigDecimal	c13d_cur_mur60_150k_not_med;
	private BigDecimal	c14d_cur_mur150_300k_not_med;
	private BigDecimal	c15d_cur_mur300_500k_not_med;
	private BigDecimal	c16d_cur_mur500_1000k_not_med;
	private BigDecimal	c17d_cur_mur1000_15000k_not_med;
	private BigDecimal	c18d_cur_mur1500_3000k_not_med;
	private BigDecimal	c19d_cur_amur3000k_not_med;
	private BigDecimal	c20d_cur_total_not_med;
	private BigDecimal	c11e_cur_bmur30k_tamt_med;
	private BigDecimal	c12e_cur_bmur30_60k_tamt_med;
	private BigDecimal	c13e_cur_bmur60_150k_tamt_med;
	private BigDecimal	c14e_cur_bmur150_300k_tamt_med;
	private BigDecimal	c15e_cur_bmur300_500k_tamt_med;
	private BigDecimal	c16e_cur_bmur500_1000k_tamt_med;
	private BigDecimal	c17e_cur_bmur1000_15000k_tamt_med;
	private BigDecimal	c18e_cur_bmur1500_3000k_tamt_med;
	private BigDecimal	c19e_cur_amur3000k_tamt_med;
	private BigDecimal	c20e_cur_total_tamt_med;
	private BigDecimal	c11f_cur_bmur30k_not_hig;
	private BigDecimal	c12f_cur_mur30_60k_not_hig;
	private BigDecimal	c13f_cur_mur60_150k_not_hig;
	private BigDecimal	c14f_cur_mur150_300k_not_hig;
	private BigDecimal	c15f_cur_mur300_500k_not_hig;
	private BigDecimal	c16f_cur_mur500_1000k_not_hig;
	private BigDecimal	c17f_cur_mur1000_15000k_not_hig;
	private BigDecimal	c18f_cur_mur1500_3000k_not_hig;
	private BigDecimal	c19f_cur_amur3000k_not_hig;
	private BigDecimal	c20f_cur_total_not_hig;
	private BigDecimal	c11g_cur_bmur30k_tamt_hig;
	private BigDecimal	c12g_cur_bmur30_60k_tamt_hig;
	private BigDecimal	c13g_cur_bmur60_150k_tamt_hig;
	private BigDecimal	c14g_cur_bmur150_300k_tamt_hig;
	private BigDecimal	c15g_cur_bmur300_500k_tamt_hig;
	private BigDecimal	c16g_cur_bmur500_1000k_tamt_hig;
	private BigDecimal	c17g_cur_bmur1000_15000k_tamt_hig;
	private BigDecimal	c18g_cur_bmur1500_3000k_tamt_hig;
	private BigDecimal	c19g_cur_amur3000k_tamt_hig;
	private BigDecimal	c20g_cur_total_tamt_hig;
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
	public String getD1_cur_bmur30k() {
		return d1_cur_bmur30k;
	}
	public void setD1_cur_bmur30k(String d1_cur_bmur30k) {
		this.d1_cur_bmur30k = d1_cur_bmur30k;
	}
	public String getD2_cur_bmur30_60k() {
		return d2_cur_bmur30_60k;
	}
	public void setD2_cur_bmur30_60k(String d2_cur_bmur30_60k) {
		this.d2_cur_bmur30_60k = d2_cur_bmur30_60k;
	}
	public String getD3_cur_bmur60_150k() {
		return d3_cur_bmur60_150k;
	}
	public void setD3_cur_bmur60_150k(String d3_cur_bmur60_150k) {
		this.d3_cur_bmur60_150k = d3_cur_bmur60_150k;
	}
	public String getD4_cur_bmur150_300k() {
		return d4_cur_bmur150_300k;
	}
	public void setD4_cur_bmur150_300k(String d4_cur_bmur150_300k) {
		this.d4_cur_bmur150_300k = d4_cur_bmur150_300k;
	}
	public String getD5_cur_bmur300_500k() {
		return d5_cur_bmur300_500k;
	}
	public void setD5_cur_bmur300_500k(String d5_cur_bmur300_500k) {
		this.d5_cur_bmur300_500k = d5_cur_bmur300_500k;
	}
	public String getD6_cur_bmur500_1000k() {
		return d6_cur_bmur500_1000k;
	}
	public void setD6_cur_bmur500_1000k(String d6_cur_bmur500_1000k) {
		this.d6_cur_bmur500_1000k = d6_cur_bmur500_1000k;
	}
	public String getD7_cur_bmur1000_15000k() {
		return d7_cur_bmur1000_15000k;
	}
	public void setD7_cur_bmur1000_15000k(String d7_cur_bmur1000_15000k) {
		this.d7_cur_bmur1000_15000k = d7_cur_bmur1000_15000k;
	}
	public String getD8_cur_bmur1500_3000k() {
		return d8_cur_bmur1500_3000k;
	}
	public void setD8_cur_bmur1500_3000k(String d8_cur_bmur1500_3000k) {
		this.d8_cur_bmur1500_3000k = d8_cur_bmur1500_3000k;
	}
	public String getD9_cur_amur3000k() {
		return d9_cur_amur3000k;
	}
	public void setD9_cur_amur3000k(String d9_cur_amur3000k) {
		this.d9_cur_amur3000k = d9_cur_amur3000k;
	}
	public String getD10_cur_total() {
		return d10_cur_total;
	}
	public void setD10_cur_total(String d10_cur_total) {
		this.d10_cur_total = d10_cur_total;
	}
	public BigDecimal getC11b_cur_bmur30k_not_low() {
		return c11b_cur_bmur30k_not_low;
	}
	public void setC11b_cur_bmur30k_not_low(BigDecimal c11b_cur_bmur30k_not_low) {
		this.c11b_cur_bmur30k_not_low = c11b_cur_bmur30k_not_low;
	}
	public BigDecimal getC12b_cur_mur30_60k_not_low() {
		return c12b_cur_mur30_60k_not_low;
	}
	public void setC12b_cur_mur30_60k_not_low(BigDecimal c12b_cur_mur30_60k_not_low) {
		this.c12b_cur_mur30_60k_not_low = c12b_cur_mur30_60k_not_low;
	}
	public BigDecimal getC13b_cur_mur60_150k_not_low() {
		return c13b_cur_mur60_150k_not_low;
	}
	public void setC13b_cur_mur60_150k_not_low(BigDecimal c13b_cur_mur60_150k_not_low) {
		this.c13b_cur_mur60_150k_not_low = c13b_cur_mur60_150k_not_low;
	}
	public BigDecimal getC14b_cur_mur150_300k_not_low() {
		return c14b_cur_mur150_300k_not_low;
	}
	public void setC14b_cur_mur150_300k_not_low(BigDecimal c14b_cur_mur150_300k_not_low) {
		this.c14b_cur_mur150_300k_not_low = c14b_cur_mur150_300k_not_low;
	}
	public BigDecimal getC15b_cur_mur300_500k_not_low() {
		return c15b_cur_mur300_500k_not_low;
	}
	public void setC15b_cur_mur300_500k_not_low(BigDecimal c15b_cur_mur300_500k_not_low) {
		this.c15b_cur_mur300_500k_not_low = c15b_cur_mur300_500k_not_low;
	}
	public BigDecimal getC16b_cur_mur500_1000k_not_low() {
		return c16b_cur_mur500_1000k_not_low;
	}
	public void setC16b_cur_mur500_1000k_not_low(BigDecimal c16b_cur_mur500_1000k_not_low) {
		this.c16b_cur_mur500_1000k_not_low = c16b_cur_mur500_1000k_not_low;
	}
	public BigDecimal getC17b_cur_mur1000_15000k_not_low() {
		return c17b_cur_mur1000_15000k_not_low;
	}
	public void setC17b_cur_mur1000_15000k_not_low(BigDecimal c17b_cur_mur1000_15000k_not_low) {
		this.c17b_cur_mur1000_15000k_not_low = c17b_cur_mur1000_15000k_not_low;
	}
	public BigDecimal getC18b_cur_mur1500_3000k_not_low() {
		return c18b_cur_mur1500_3000k_not_low;
	}
	public void setC18b_cur_mur1500_3000k_not_low(BigDecimal c18b_cur_mur1500_3000k_not_low) {
		this.c18b_cur_mur1500_3000k_not_low = c18b_cur_mur1500_3000k_not_low;
	}
	public BigDecimal getC19b_cur_amur3000k_not_low() {
		return c19b_cur_amur3000k_not_low;
	}
	public void setC19b_cur_amur3000k_not_low(BigDecimal c19b_cur_amur3000k_not_low) {
		this.c19b_cur_amur3000k_not_low = c19b_cur_amur3000k_not_low;
	}
	public BigDecimal getC20b_cur_total_not_low() {
		return c20b_cur_total_not_low;
	}
	public void setC20b_cur_total_not_low(BigDecimal c20b_cur_total_not_low) {
		this.c20b_cur_total_not_low = c20b_cur_total_not_low;
	}
	public BigDecimal getC11c_cur_bmur30k_tamt_low() {
		return c11c_cur_bmur30k_tamt_low;
	}
	public void setC11c_cur_bmur30k_tamt_low(BigDecimal c11c_cur_bmur30k_tamt_low) {
		this.c11c_cur_bmur30k_tamt_low = c11c_cur_bmur30k_tamt_low;
	}
	public BigDecimal getC12c_cur_bmur30_60k_tamt_low() {
		return c12c_cur_bmur30_60k_tamt_low;
	}
	public void setC12c_cur_bmur30_60k_tamt_low(BigDecimal c12c_cur_bmur30_60k_tamt_low) {
		this.c12c_cur_bmur30_60k_tamt_low = c12c_cur_bmur30_60k_tamt_low;
	}
	public BigDecimal getC13c_cur_bmur60_150k_tamt_low() {
		return c13c_cur_bmur60_150k_tamt_low;
	}
	public void setC13c_cur_bmur60_150k_tamt_low(BigDecimal c13c_cur_bmur60_150k_tamt_low) {
		this.c13c_cur_bmur60_150k_tamt_low = c13c_cur_bmur60_150k_tamt_low;
	}
	public BigDecimal getC14c_cur_bmur150_300k_tamt_low() {
		return c14c_cur_bmur150_300k_tamt_low;
	}
	public void setC14c_cur_bmur150_300k_tamt_low(BigDecimal c14c_cur_bmur150_300k_tamt_low) {
		this.c14c_cur_bmur150_300k_tamt_low = c14c_cur_bmur150_300k_tamt_low;
	}
	public BigDecimal getC15c_cur_bmur300_500k_tamt_low() {
		return c15c_cur_bmur300_500k_tamt_low;
	}
	public void setC15c_cur_bmur300_500k_tamt_low(BigDecimal c15c_cur_bmur300_500k_tamt_low) {
		this.c15c_cur_bmur300_500k_tamt_low = c15c_cur_bmur300_500k_tamt_low;
	}
	public BigDecimal getC16c_cur_bmur500_1000k_tamt_low() {
		return c16c_cur_bmur500_1000k_tamt_low;
	}
	public void setC16c_cur_bmur500_1000k_tamt_low(BigDecimal c16c_cur_bmur500_1000k_tamt_low) {
		this.c16c_cur_bmur500_1000k_tamt_low = c16c_cur_bmur500_1000k_tamt_low;
	}
	public BigDecimal getC17c_cur_bmur1000_15000k_tamt_low() {
		return c17c_cur_bmur1000_15000k_tamt_low;
	}
	public void setC17c_cur_bmur1000_15000k_tamt_low(BigDecimal c17c_cur_bmur1000_15000k_tamt_low) {
		this.c17c_cur_bmur1000_15000k_tamt_low = c17c_cur_bmur1000_15000k_tamt_low;
	}
	public BigDecimal getC18c_cur_bmur1500_3000k_tamt_low() {
		return c18c_cur_bmur1500_3000k_tamt_low;
	}
	public void setC18c_cur_bmur1500_3000k_tamt_low(BigDecimal c18c_cur_bmur1500_3000k_tamt_low) {
		this.c18c_cur_bmur1500_3000k_tamt_low = c18c_cur_bmur1500_3000k_tamt_low;
	}
	public BigDecimal getC19c_cur_amur3000k_tamt_low() {
		return c19c_cur_amur3000k_tamt_low;
	}
	public void setC19c_cur_amur3000k_tamt_low(BigDecimal c19c_cur_amur3000k_tamt_low) {
		this.c19c_cur_amur3000k_tamt_low = c19c_cur_amur3000k_tamt_low;
	}
	public BigDecimal getC20c_cur_total_tamt_low() {
		return c20c_cur_total_tamt_low;
	}
	public void setC20c_cur_total_tamt_low(BigDecimal c20c_cur_total_tamt_low) {
		this.c20c_cur_total_tamt_low = c20c_cur_total_tamt_low;
	}
	public BigDecimal getC11d_cur_bmur30k_not_med() {
		return c11d_cur_bmur30k_not_med;
	}
	public void setC11d_cur_bmur30k_not_med(BigDecimal c11d_cur_bmur30k_not_med) {
		this.c11d_cur_bmur30k_not_med = c11d_cur_bmur30k_not_med;
	}
	public BigDecimal getC12d_cur_mur30_60k_not_med() {
		return c12d_cur_mur30_60k_not_med;
	}
	public void setC12d_cur_mur30_60k_not_med(BigDecimal c12d_cur_mur30_60k_not_med) {
		this.c12d_cur_mur30_60k_not_med = c12d_cur_mur30_60k_not_med;
	}
	public BigDecimal getC13d_cur_mur60_150k_not_med() {
		return c13d_cur_mur60_150k_not_med;
	}
	public void setC13d_cur_mur60_150k_not_med(BigDecimal c13d_cur_mur60_150k_not_med) {
		this.c13d_cur_mur60_150k_not_med = c13d_cur_mur60_150k_not_med;
	}
	public BigDecimal getC14d_cur_mur150_300k_not_med() {
		return c14d_cur_mur150_300k_not_med;
	}
	public void setC14d_cur_mur150_300k_not_med(BigDecimal c14d_cur_mur150_300k_not_med) {
		this.c14d_cur_mur150_300k_not_med = c14d_cur_mur150_300k_not_med;
	}
	public BigDecimal getC15d_cur_mur300_500k_not_med() {
		return c15d_cur_mur300_500k_not_med;
	}
	public void setC15d_cur_mur300_500k_not_med(BigDecimal c15d_cur_mur300_500k_not_med) {
		this.c15d_cur_mur300_500k_not_med = c15d_cur_mur300_500k_not_med;
	}
	public BigDecimal getC16d_cur_mur500_1000k_not_med() {
		return c16d_cur_mur500_1000k_not_med;
	}
	public void setC16d_cur_mur500_1000k_not_med(BigDecimal c16d_cur_mur500_1000k_not_med) {
		this.c16d_cur_mur500_1000k_not_med = c16d_cur_mur500_1000k_not_med;
	}
	public BigDecimal getC17d_cur_mur1000_15000k_not_med() {
		return c17d_cur_mur1000_15000k_not_med;
	}
	public void setC17d_cur_mur1000_15000k_not_med(BigDecimal c17d_cur_mur1000_15000k_not_med) {
		this.c17d_cur_mur1000_15000k_not_med = c17d_cur_mur1000_15000k_not_med;
	}
	public BigDecimal getC18d_cur_mur1500_3000k_not_med() {
		return c18d_cur_mur1500_3000k_not_med;
	}
	public void setC18d_cur_mur1500_3000k_not_med(BigDecimal c18d_cur_mur1500_3000k_not_med) {
		this.c18d_cur_mur1500_3000k_not_med = c18d_cur_mur1500_3000k_not_med;
	}
	public BigDecimal getC19d_cur_amur3000k_not_med() {
		return c19d_cur_amur3000k_not_med;
	}
	public void setC19d_cur_amur3000k_not_med(BigDecimal c19d_cur_amur3000k_not_med) {
		this.c19d_cur_amur3000k_not_med = c19d_cur_amur3000k_not_med;
	}
	public BigDecimal getC20d_cur_total_not_med() {
		return c20d_cur_total_not_med;
	}
	public void setC20d_cur_total_not_med(BigDecimal c20d_cur_total_not_med) {
		this.c20d_cur_total_not_med = c20d_cur_total_not_med;
	}
	public BigDecimal getC11e_cur_bmur30k_tamt_med() {
		return c11e_cur_bmur30k_tamt_med;
	}
	public void setC11e_cur_bmur30k_tamt_med(BigDecimal c11e_cur_bmur30k_tamt_med) {
		this.c11e_cur_bmur30k_tamt_med = c11e_cur_bmur30k_tamt_med;
	}
	public BigDecimal getC12e_cur_bmur30_60k_tamt_med() {
		return c12e_cur_bmur30_60k_tamt_med;
	}
	public void setC12e_cur_bmur30_60k_tamt_med(BigDecimal c12e_cur_bmur30_60k_tamt_med) {
		this.c12e_cur_bmur30_60k_tamt_med = c12e_cur_bmur30_60k_tamt_med;
	}
	public BigDecimal getC13e_cur_bmur60_150k_tamt_med() {
		return c13e_cur_bmur60_150k_tamt_med;
	}
	public void setC13e_cur_bmur60_150k_tamt_med(BigDecimal c13e_cur_bmur60_150k_tamt_med) {
		this.c13e_cur_bmur60_150k_tamt_med = c13e_cur_bmur60_150k_tamt_med;
	}
	public BigDecimal getC14e_cur_bmur150_300k_tamt_med() {
		return c14e_cur_bmur150_300k_tamt_med;
	}
	public void setC14e_cur_bmur150_300k_tamt_med(BigDecimal c14e_cur_bmur150_300k_tamt_med) {
		this.c14e_cur_bmur150_300k_tamt_med = c14e_cur_bmur150_300k_tamt_med;
	}
	public BigDecimal getC15e_cur_bmur300_500k_tamt_med() {
		return c15e_cur_bmur300_500k_tamt_med;
	}
	public void setC15e_cur_bmur300_500k_tamt_med(BigDecimal c15e_cur_bmur300_500k_tamt_med) {
		this.c15e_cur_bmur300_500k_tamt_med = c15e_cur_bmur300_500k_tamt_med;
	}
	public BigDecimal getC16e_cur_bmur500_1000k_tamt_med() {
		return c16e_cur_bmur500_1000k_tamt_med;
	}
	public void setC16e_cur_bmur500_1000k_tamt_med(BigDecimal c16e_cur_bmur500_1000k_tamt_med) {
		this.c16e_cur_bmur500_1000k_tamt_med = c16e_cur_bmur500_1000k_tamt_med;
	}
	public BigDecimal getC17e_cur_bmur1000_15000k_tamt_med() {
		return c17e_cur_bmur1000_15000k_tamt_med;
	}
	public void setC17e_cur_bmur1000_15000k_tamt_med(BigDecimal c17e_cur_bmur1000_15000k_tamt_med) {
		this.c17e_cur_bmur1000_15000k_tamt_med = c17e_cur_bmur1000_15000k_tamt_med;
	}
	public BigDecimal getC18e_cur_bmur1500_3000k_tamt_med() {
		return c18e_cur_bmur1500_3000k_tamt_med;
	}
	public void setC18e_cur_bmur1500_3000k_tamt_med(BigDecimal c18e_cur_bmur1500_3000k_tamt_med) {
		this.c18e_cur_bmur1500_3000k_tamt_med = c18e_cur_bmur1500_3000k_tamt_med;
	}
	public BigDecimal getC19e_cur_amur3000k_tamt_med() {
		return c19e_cur_amur3000k_tamt_med;
	}
	public void setC19e_cur_amur3000k_tamt_med(BigDecimal c19e_cur_amur3000k_tamt_med) {
		this.c19e_cur_amur3000k_tamt_med = c19e_cur_amur3000k_tamt_med;
	}
	public BigDecimal getC20e_cur_total_tamt_med() {
		return c20e_cur_total_tamt_med;
	}
	public void setC20e_cur_total_tamt_med(BigDecimal c20e_cur_total_tamt_med) {
		this.c20e_cur_total_tamt_med = c20e_cur_total_tamt_med;
	}
	public BigDecimal getC11f_cur_bmur30k_not_hig() {
		return c11f_cur_bmur30k_not_hig;
	}
	public void setC11f_cur_bmur30k_not_hig(BigDecimal c11f_cur_bmur30k_not_hig) {
		this.c11f_cur_bmur30k_not_hig = c11f_cur_bmur30k_not_hig;
	}
	public BigDecimal getC12f_cur_mur30_60k_not_hig() {
		return c12f_cur_mur30_60k_not_hig;
	}
	public void setC12f_cur_mur30_60k_not_hig(BigDecimal c12f_cur_mur30_60k_not_hig) {
		this.c12f_cur_mur30_60k_not_hig = c12f_cur_mur30_60k_not_hig;
	}
	public BigDecimal getC13f_cur_mur60_150k_not_hig() {
		return c13f_cur_mur60_150k_not_hig;
	}
	public void setC13f_cur_mur60_150k_not_hig(BigDecimal c13f_cur_mur60_150k_not_hig) {
		this.c13f_cur_mur60_150k_not_hig = c13f_cur_mur60_150k_not_hig;
	}
	public BigDecimal getC14f_cur_mur150_300k_not_hig() {
		return c14f_cur_mur150_300k_not_hig;
	}
	public void setC14f_cur_mur150_300k_not_hig(BigDecimal c14f_cur_mur150_300k_not_hig) {
		this.c14f_cur_mur150_300k_not_hig = c14f_cur_mur150_300k_not_hig;
	}
	public BigDecimal getC15f_cur_mur300_500k_not_hig() {
		return c15f_cur_mur300_500k_not_hig;
	}
	public void setC15f_cur_mur300_500k_not_hig(BigDecimal c15f_cur_mur300_500k_not_hig) {
		this.c15f_cur_mur300_500k_not_hig = c15f_cur_mur300_500k_not_hig;
	}
	public BigDecimal getC16f_cur_mur500_1000k_not_hig() {
		return c16f_cur_mur500_1000k_not_hig;
	}
	public void setC16f_cur_mur500_1000k_not_hig(BigDecimal c16f_cur_mur500_1000k_not_hig) {
		this.c16f_cur_mur500_1000k_not_hig = c16f_cur_mur500_1000k_not_hig;
	}
	public BigDecimal getC17f_cur_mur1000_15000k_not_hig() {
		return c17f_cur_mur1000_15000k_not_hig;
	}
	public void setC17f_cur_mur1000_15000k_not_hig(BigDecimal c17f_cur_mur1000_15000k_not_hig) {
		this.c17f_cur_mur1000_15000k_not_hig = c17f_cur_mur1000_15000k_not_hig;
	}
	public BigDecimal getC18f_cur_mur1500_3000k_not_hig() {
		return c18f_cur_mur1500_3000k_not_hig;
	}
	public void setC18f_cur_mur1500_3000k_not_hig(BigDecimal c18f_cur_mur1500_3000k_not_hig) {
		this.c18f_cur_mur1500_3000k_not_hig = c18f_cur_mur1500_3000k_not_hig;
	}
	public BigDecimal getC19f_cur_amur3000k_not_hig() {
		return c19f_cur_amur3000k_not_hig;
	}
	public void setC19f_cur_amur3000k_not_hig(BigDecimal c19f_cur_amur3000k_not_hig) {
		this.c19f_cur_amur3000k_not_hig = c19f_cur_amur3000k_not_hig;
	}
	public BigDecimal getC20f_cur_total_not_hig() {
		return c20f_cur_total_not_hig;
	}
	public void setC20f_cur_total_not_hig(BigDecimal c20f_cur_total_not_hig) {
		this.c20f_cur_total_not_hig = c20f_cur_total_not_hig;
	}
	public BigDecimal getC11g_cur_bmur30k_tamt_hig() {
		return c11g_cur_bmur30k_tamt_hig;
	}
	public void setC11g_cur_bmur30k_tamt_hig(BigDecimal c11g_cur_bmur30k_tamt_hig) {
		this.c11g_cur_bmur30k_tamt_hig = c11g_cur_bmur30k_tamt_hig;
	}
	public BigDecimal getC12g_cur_bmur30_60k_tamt_hig() {
		return c12g_cur_bmur30_60k_tamt_hig;
	}
	public void setC12g_cur_bmur30_60k_tamt_hig(BigDecimal c12g_cur_bmur30_60k_tamt_hig) {
		this.c12g_cur_bmur30_60k_tamt_hig = c12g_cur_bmur30_60k_tamt_hig;
	}
	public BigDecimal getC13g_cur_bmur60_150k_tamt_hig() {
		return c13g_cur_bmur60_150k_tamt_hig;
	}
	public void setC13g_cur_bmur60_150k_tamt_hig(BigDecimal c13g_cur_bmur60_150k_tamt_hig) {
		this.c13g_cur_bmur60_150k_tamt_hig = c13g_cur_bmur60_150k_tamt_hig;
	}
	public BigDecimal getC14g_cur_bmur150_300k_tamt_hig() {
		return c14g_cur_bmur150_300k_tamt_hig;
	}
	public void setC14g_cur_bmur150_300k_tamt_hig(BigDecimal c14g_cur_bmur150_300k_tamt_hig) {
		this.c14g_cur_bmur150_300k_tamt_hig = c14g_cur_bmur150_300k_tamt_hig;
	}
	public BigDecimal getC15g_cur_bmur300_500k_tamt_hig() {
		return c15g_cur_bmur300_500k_tamt_hig;
	}
	public void setC15g_cur_bmur300_500k_tamt_hig(BigDecimal c15g_cur_bmur300_500k_tamt_hig) {
		this.c15g_cur_bmur300_500k_tamt_hig = c15g_cur_bmur300_500k_tamt_hig;
	}
	public BigDecimal getC16g_cur_bmur500_1000k_tamt_hig() {
		return c16g_cur_bmur500_1000k_tamt_hig;
	}
	public void setC16g_cur_bmur500_1000k_tamt_hig(BigDecimal c16g_cur_bmur500_1000k_tamt_hig) {
		this.c16g_cur_bmur500_1000k_tamt_hig = c16g_cur_bmur500_1000k_tamt_hig;
	}
	public BigDecimal getC17g_cur_bmur1000_15000k_tamt_hig() {
		return c17g_cur_bmur1000_15000k_tamt_hig;
	}
	public void setC17g_cur_bmur1000_15000k_tamt_hig(BigDecimal c17g_cur_bmur1000_15000k_tamt_hig) {
		this.c17g_cur_bmur1000_15000k_tamt_hig = c17g_cur_bmur1000_15000k_tamt_hig;
	}
	public BigDecimal getC18g_cur_bmur1500_3000k_tamt_hig() {
		return c18g_cur_bmur1500_3000k_tamt_hig;
	}
	public void setC18g_cur_bmur1500_3000k_tamt_hig(BigDecimal c18g_cur_bmur1500_3000k_tamt_hig) {
		this.c18g_cur_bmur1500_3000k_tamt_hig = c18g_cur_bmur1500_3000k_tamt_hig;
	}
	public BigDecimal getC19g_cur_amur3000k_tamt_hig() {
		return c19g_cur_amur3000k_tamt_hig;
	}
	public void setC19g_cur_amur3000k_tamt_hig(BigDecimal c19g_cur_amur3000k_tamt_hig) {
		this.c19g_cur_amur3000k_tamt_hig = c19g_cur_amur3000k_tamt_hig;
	}
	public BigDecimal getC20g_cur_total_tamt_hig() {
		return c20g_cur_total_tamt_hig;
	}
	public void setC20g_cur_total_tamt_hig(BigDecimal c20g_cur_total_tamt_hig) {
		this.c20g_cur_total_tamt_hig = c20g_cur_total_tamt_hig;
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
	public T9Report(String d1_cur_bmur30k, String d2_cur_bmur30_60k, String d3_cur_bmur60_150k,
			String d4_cur_bmur150_300k, String d5_cur_bmur300_500k, String d6_cur_bmur500_1000k,
			String d7_cur_bmur1000_15000k, String d8_cur_bmur1500_3000k, String d9_cur_amur3000k, String d10_cur_total,
			BigDecimal c11b_cur_bmur30k_not_low, BigDecimal c12b_cur_mur30_60k_not_low,
			BigDecimal c13b_cur_mur60_150k_not_low, BigDecimal c14b_cur_mur150_300k_not_low,
			BigDecimal c15b_cur_mur300_500k_not_low, BigDecimal c16b_cur_mur500_1000k_not_low,
			BigDecimal c17b_cur_mur1000_15000k_not_low, BigDecimal c18b_cur_mur1500_3000k_not_low,
			BigDecimal c19b_cur_amur3000k_not_low, BigDecimal c20b_cur_total_not_low,
			BigDecimal c11c_cur_bmur30k_tamt_low, BigDecimal c12c_cur_bmur30_60k_tamt_low,
			BigDecimal c13c_cur_bmur60_150k_tamt_low, BigDecimal c14c_cur_bmur150_300k_tamt_low,
			BigDecimal c15c_cur_bmur300_500k_tamt_low, BigDecimal c16c_cur_bmur500_1000k_tamt_low,
			BigDecimal c17c_cur_bmur1000_15000k_tamt_low, BigDecimal c18c_cur_bmur1500_3000k_tamt_low,
			BigDecimal c19c_cur_amur3000k_tamt_low, BigDecimal c20c_cur_total_tamt_low,
			BigDecimal c11d_cur_bmur30k_not_med, BigDecimal c12d_cur_mur30_60k_not_med,
			BigDecimal c13d_cur_mur60_150k_not_med, BigDecimal c14d_cur_mur150_300k_not_med,
			BigDecimal c15d_cur_mur300_500k_not_med, BigDecimal c16d_cur_mur500_1000k_not_med,
			BigDecimal c17d_cur_mur1000_15000k_not_med, BigDecimal c18d_cur_mur1500_3000k_not_med,
			BigDecimal c19d_cur_amur3000k_not_med, BigDecimal c20d_cur_total_not_med,
			BigDecimal c11e_cur_bmur30k_tamt_med, BigDecimal c12e_cur_bmur30_60k_tamt_med,
			BigDecimal c13e_cur_bmur60_150k_tamt_med, BigDecimal c14e_cur_bmur150_300k_tamt_med,
			BigDecimal c15e_cur_bmur300_500k_tamt_med, BigDecimal c16e_cur_bmur500_1000k_tamt_med,
			BigDecimal c17e_cur_bmur1000_15000k_tamt_med, BigDecimal c18e_cur_bmur1500_3000k_tamt_med,
			BigDecimal c19e_cur_amur3000k_tamt_med, BigDecimal c20e_cur_total_tamt_med,
			BigDecimal c11f_cur_bmur30k_not_hig, BigDecimal c12f_cur_mur30_60k_not_hig,
			BigDecimal c13f_cur_mur60_150k_not_hig, BigDecimal c14f_cur_mur150_300k_not_hig,
			BigDecimal c15f_cur_mur300_500k_not_hig, BigDecimal c16f_cur_mur500_1000k_not_hig,
			BigDecimal c17f_cur_mur1000_15000k_not_hig, BigDecimal c18f_cur_mur1500_3000k_not_hig,
			BigDecimal c19f_cur_amur3000k_not_hig, BigDecimal c20f_cur_total_not_hig,
			BigDecimal c11g_cur_bmur30k_tamt_hig, BigDecimal c12g_cur_bmur30_60k_tamt_hig,
			BigDecimal c13g_cur_bmur60_150k_tamt_hig, BigDecimal c14g_cur_bmur150_300k_tamt_hig,
			BigDecimal c15g_cur_bmur300_500k_tamt_hig, BigDecimal c16g_cur_bmur500_1000k_tamt_hig,
			BigDecimal c17g_cur_bmur1000_15000k_tamt_hig, BigDecimal c18g_cur_bmur1500_3000k_tamt_hig,
			BigDecimal c19g_cur_amur3000k_tamt_hig, BigDecimal c20g_cur_total_tamt_hig, String report_code,
			String report_name, Date report_date, Date report_due_date, Date rep_submit_date, Date rep_period_from,
			Date rep_period_to, String rep_freq, Character nil_report_flg, Character arch_flg) {
		this.d1_cur_bmur30k = d1_cur_bmur30k;
		this.d2_cur_bmur30_60k = d2_cur_bmur30_60k;
		this.d3_cur_bmur60_150k = d3_cur_bmur60_150k;
		this.d4_cur_bmur150_300k = d4_cur_bmur150_300k;
		this.d5_cur_bmur300_500k = d5_cur_bmur300_500k;
		this.d6_cur_bmur500_1000k = d6_cur_bmur500_1000k;
		this.d7_cur_bmur1000_15000k = d7_cur_bmur1000_15000k;
		this.d8_cur_bmur1500_3000k = d8_cur_bmur1500_3000k;
		this.d9_cur_amur3000k = d9_cur_amur3000k;
		this.d10_cur_total = d10_cur_total;
		this.c11b_cur_bmur30k_not_low = c11b_cur_bmur30k_not_low;
		this.c12b_cur_mur30_60k_not_low = c12b_cur_mur30_60k_not_low;
		this.c13b_cur_mur60_150k_not_low = c13b_cur_mur60_150k_not_low;
		this.c14b_cur_mur150_300k_not_low = c14b_cur_mur150_300k_not_low;
		this.c15b_cur_mur300_500k_not_low = c15b_cur_mur300_500k_not_low;
		this.c16b_cur_mur500_1000k_not_low = c16b_cur_mur500_1000k_not_low;
		this.c17b_cur_mur1000_15000k_not_low = c17b_cur_mur1000_15000k_not_low;
		this.c18b_cur_mur1500_3000k_not_low = c18b_cur_mur1500_3000k_not_low;
		this.c19b_cur_amur3000k_not_low = c19b_cur_amur3000k_not_low;
		this.c20b_cur_total_not_low = c20b_cur_total_not_low;
		this.c11c_cur_bmur30k_tamt_low = c11c_cur_bmur30k_tamt_low;
		this.c12c_cur_bmur30_60k_tamt_low = c12c_cur_bmur30_60k_tamt_low;
		this.c13c_cur_bmur60_150k_tamt_low = c13c_cur_bmur60_150k_tamt_low;
		this.c14c_cur_bmur150_300k_tamt_low = c14c_cur_bmur150_300k_tamt_low;
		this.c15c_cur_bmur300_500k_tamt_low = c15c_cur_bmur300_500k_tamt_low;
		this.c16c_cur_bmur500_1000k_tamt_low = c16c_cur_bmur500_1000k_tamt_low;
		this.c17c_cur_bmur1000_15000k_tamt_low = c17c_cur_bmur1000_15000k_tamt_low;
		this.c18c_cur_bmur1500_3000k_tamt_low = c18c_cur_bmur1500_3000k_tamt_low;
		this.c19c_cur_amur3000k_tamt_low = c19c_cur_amur3000k_tamt_low;
		this.c20c_cur_total_tamt_low = c20c_cur_total_tamt_low;
		this.c11d_cur_bmur30k_not_med = c11d_cur_bmur30k_not_med;
		this.c12d_cur_mur30_60k_not_med = c12d_cur_mur30_60k_not_med;
		this.c13d_cur_mur60_150k_not_med = c13d_cur_mur60_150k_not_med;
		this.c14d_cur_mur150_300k_not_med = c14d_cur_mur150_300k_not_med;
		this.c15d_cur_mur300_500k_not_med = c15d_cur_mur300_500k_not_med;
		this.c16d_cur_mur500_1000k_not_med = c16d_cur_mur500_1000k_not_med;
		this.c17d_cur_mur1000_15000k_not_med = c17d_cur_mur1000_15000k_not_med;
		this.c18d_cur_mur1500_3000k_not_med = c18d_cur_mur1500_3000k_not_med;
		this.c19d_cur_amur3000k_not_med = c19d_cur_amur3000k_not_med;
		this.c20d_cur_total_not_med = c20d_cur_total_not_med;
		this.c11e_cur_bmur30k_tamt_med = c11e_cur_bmur30k_tamt_med;
		this.c12e_cur_bmur30_60k_tamt_med = c12e_cur_bmur30_60k_tamt_med;
		this.c13e_cur_bmur60_150k_tamt_med = c13e_cur_bmur60_150k_tamt_med;
		this.c14e_cur_bmur150_300k_tamt_med = c14e_cur_bmur150_300k_tamt_med;
		this.c15e_cur_bmur300_500k_tamt_med = c15e_cur_bmur300_500k_tamt_med;
		this.c16e_cur_bmur500_1000k_tamt_med = c16e_cur_bmur500_1000k_tamt_med;
		this.c17e_cur_bmur1000_15000k_tamt_med = c17e_cur_bmur1000_15000k_tamt_med;
		this.c18e_cur_bmur1500_3000k_tamt_med = c18e_cur_bmur1500_3000k_tamt_med;
		this.c19e_cur_amur3000k_tamt_med = c19e_cur_amur3000k_tamt_med;
		this.c20e_cur_total_tamt_med = c20e_cur_total_tamt_med;
		this.c11f_cur_bmur30k_not_hig = c11f_cur_bmur30k_not_hig;
		this.c12f_cur_mur30_60k_not_hig = c12f_cur_mur30_60k_not_hig;
		this.c13f_cur_mur60_150k_not_hig = c13f_cur_mur60_150k_not_hig;
		this.c14f_cur_mur150_300k_not_hig = c14f_cur_mur150_300k_not_hig;
		this.c15f_cur_mur300_500k_not_hig = c15f_cur_mur300_500k_not_hig;
		this.c16f_cur_mur500_1000k_not_hig = c16f_cur_mur500_1000k_not_hig;
		this.c17f_cur_mur1000_15000k_not_hig = c17f_cur_mur1000_15000k_not_hig;
		this.c18f_cur_mur1500_3000k_not_hig = c18f_cur_mur1500_3000k_not_hig;
		this.c19f_cur_amur3000k_not_hig = c19f_cur_amur3000k_not_hig;
		this.c20f_cur_total_not_hig = c20f_cur_total_not_hig;
		this.c11g_cur_bmur30k_tamt_hig = c11g_cur_bmur30k_tamt_hig;
		this.c12g_cur_bmur30_60k_tamt_hig = c12g_cur_bmur30_60k_tamt_hig;
		this.c13g_cur_bmur60_150k_tamt_hig = c13g_cur_bmur60_150k_tamt_hig;
		this.c14g_cur_bmur150_300k_tamt_hig = c14g_cur_bmur150_300k_tamt_hig;
		this.c15g_cur_bmur300_500k_tamt_hig = c15g_cur_bmur300_500k_tamt_hig;
		this.c16g_cur_bmur500_1000k_tamt_hig = c16g_cur_bmur500_1000k_tamt_hig;
		this.c17g_cur_bmur1000_15000k_tamt_hig = c17g_cur_bmur1000_15000k_tamt_hig;
		this.c18g_cur_bmur1500_3000k_tamt_hig = c18g_cur_bmur1500_3000k_tamt_hig;
		this.c19g_cur_amur3000k_tamt_hig = c19g_cur_amur3000k_tamt_hig;
		this.c20g_cur_total_tamt_hig = c20g_cur_total_tamt_hig;
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
	public T9Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	
	
	
	
	}

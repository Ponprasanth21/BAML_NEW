package com.bornfire.entity.t28;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="T28_AML_CFT_INF_TABLE")
public class T28Reports {
	
    private String a1_srl_no;
	private String	b1_information;
	private String	c1_quarter;
	private String	d1_remarks;
	private String	a2_srl_no;
	private String	b2_information;
	private String	c2_quarter;
	private String	d2_remarks;
	private String	a3_srl_no;
	private String	b3_information;
	private String	c3_quarter;
	private String	d3_remarks;
	private String	a4_srl_no;
	private String	b4_information;
	private String	c4_quarter;
	private String	d4_remarls;
	private String	a5_srl_no;
	private String	b5_information;
	private String	c5_quarter;
	private String	d5_remarks;
	private String	a6_srl_no;
	private String	b6_information;
	private String	c6_quarter;
	private String	d6_remarks;
	private String	a7_srl_no;
	private String	b7_information;
	private String	c7_quarter;
	private String	d7_remarks;
	private String	a8_srl_no;
	private String	b8_information;
	private String	c8_quarter;
	private String	d8_remarks;
	private String	a9_srl_no;
	private String	b9_information;
	private String	c9_quarter;
	private String	d9_remarks;
	private String	a10_srl_no;
	private String	b10_information;
	private String	c10_quarter;
	private String	d10_remarks;
	private String	a11_srl_no;
	private String	b11_information;
	private String	c11_quarter;
	private String	d11_remarks;
	private String	a12_srl_no;
	private String	b12_information;
	private String	c12_quarter;
	private String	d12_remarks;
	private String	a13_srl_no;
	private String	b13_information;
	private String	c13_quarter;
	private String	d13_remarks;
	private String	a14_srl_no;
	private String	b14_information;
	private String	c14_quarter;
	private String	d14_remarks;
	private String	a15_srl_no;
	private String	b15_information;
	private String	c15_quarter;
	private String	d15_remarks;
	private String	a16_srl_no;
	private String	b16_information;
	private String	c16_quarter;
	private String	d16_remarks;
	private String	a17_srl_no;
	private String	b17_information;
	private String	c17_quarter;
	private String	d17_remarks;
	private String	a18_srl_no;
	private String	b18_information;
	private String	c18_quarter;
	private String	d18_remarks;
	private String	a19_srl_no;
	private String	b19_information;
	private String	c19_quarter;
	private String	d19_remarks;
	private String	a20_srl_no;
	private String	b20_information;
	private String	c20_quarter;
	private String	d20_remarks;
	private String	a21_srl_no_a;
	private String	b21_information_a;
	private String	c21_quarter_a;
	private String	d21_remarks_a;
	private String	a21_srl_no_b;
	private String	b21_information_b;
	private String	c21_quarter_b;
	private String	d21_remarks_b;
	private String	a21_srl_no_c;
	private String	b21_information_c;
	private String	c21_quarter_c;
	private String	d21_remarks_c;
	private String	a21_srl_no_d;
	private String	b21_information_d;
	private String	c21_quarter_d;
	private String	d21_remarks_d;
	private String	a22_srl_no;
	private String	b22_information;
	private String	c22_quarter;
	private String	d22_remarks;
	private String	a23_srl_no;
	private String	b23_information;
	private String	c23_quarter;
	private String	d23_remarks;
	private String	a24_srl_no;
	private String	b24_information;
	private String	c24_quarter;
	private String	d24_remarks;
	private String	a25_srl_no;
	private String	b25_information;
	private String	c25_quarter;
	private String	d25_remarks;
	private String	a26_srl_no;
	private String	b26_information;
	private String	c26_quarter;
	private String	d26_remarks;
	private String	a27_srl_no;
	private String	b27_information;
	private String	c27_quarter;
	private String	d27_remarks;
	private String	a28_srl_no;
	private String	b28_information;
	private String	c28_quarter;
	private String	d28_remarks;
	private String	report_code;
	private String	report_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Id
	private Date	report_date;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_from;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_to;
	private String	entity_flg;
	private String	modify_flg;
	private String	verify_flg;
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
	
	
	
	public String getA1_srl_no() {
		return a1_srl_no;
	}
	public void setA1_srl_no(String a1_srl_no) {
		this.a1_srl_no = a1_srl_no;
	}
	public String getB1_information() {
		return b1_information;
	}
	public String getC1_quarter() {
		return c1_quarter;
	}
	public String getD1_remarks() {
		return d1_remarks;
	}
	public String getA2_srl_no() {
		return a2_srl_no;
	}
	public String getB2_information() {
		return b2_information;
	}
	public String getC2_quarter() {
		return c2_quarter;
	}
	public String getD2_remarks() {
		return d2_remarks;
	}
	public String getA3_srl_no() {
		return a3_srl_no;
	}
	public String getB3_information() {
		return b3_information;
	}
	public String getC3_quarter() {
		return c3_quarter;
	}
	public String getD3_remarks() {
		return d3_remarks;
	}
	public String getA4_srl_no() {
		return a4_srl_no;
	}
	public String getB4_information() {
		return b4_information;
	}
	public String getC4_quarter() {
		return c4_quarter;
	}
	public String getD4_remarls() {
		return d4_remarls;
	}
	public String getA5_srl_no() {
		return a5_srl_no;
	}
	public String getB5_information() {
		return b5_information;
	}
	public String getC5_quarter() {
		return c5_quarter;
	}
	public String getD5_remarks() {
		return d5_remarks;
	}
	public String getA6_srl_no() {
		return a6_srl_no;
	}
	public String getB6_information() {
		return b6_information;
	}
	public String getC6_quarter() {
		return c6_quarter;
	}
	public String getD6_remarks() {
		return d6_remarks;
	}
	public String getA7_srl_no() {
		return a7_srl_no;
	}
	public String getB7_information() {
		return b7_information;
	}
	public String getC7_quarter() {
		return c7_quarter;
	}
	public String getD7_remarks() {
		return d7_remarks;
	}
	public String getA8_srl_no() {
		return a8_srl_no;
	}
	public String getB8_information() {
		return b8_information;
	}
	public String getC8_quarter() {
		return c8_quarter;
	}
	public String getD8_remarks() {
		return d8_remarks;
	}
	public String getA9_srl_no() {
		return a9_srl_no;
	}
	public String getB9_information() {
		return b9_information;
	}
	public String getC9_quarter() {
		return c9_quarter;
	}
	public String getD9_remarks() {
		return d9_remarks;
	}
	public String getA10_srl_no() {
		return a10_srl_no;
	}
	public String getB10_information() {
		return b10_information;
	}
	public String getC10_quarter() {
		return c10_quarter;
	}
	public String getD10_remarks() {
		return d10_remarks;
	}
	public String getA11_srl_no() {
		return a11_srl_no;
	}
	public String getB11_information() {
		return b11_information;
	}
	public String getC11_quarter() {
		return c11_quarter;
	}
	public String getD11_remarks() {
		return d11_remarks;
	}
	public String getA12_srl_no() {
		return a12_srl_no;
	}
	public String getB12_information() {
		return b12_information;
	}
	public String getC12_quarter() {
		return c12_quarter;
	}
	public String getD12_remarks() {
		return d12_remarks;
	}
	public String getA13_srl_no() {
		return a13_srl_no;
	}
	public String getB13_information() {
		return b13_information;
	}
	public String getC13_quarter() {
		return c13_quarter;
	}
	public String getD13_remarks() {
		return d13_remarks;
	}
	public String getA14_srl_no() {
		return a14_srl_no;
	}
	public String getB14_information() {
		return b14_information;
	}
	public String getC14_quarter() {
		return c14_quarter;
	}
	public String getD14_remarks() {
		return d14_remarks;
	}
	public String getA15_srl_no() {
		return a15_srl_no;
	}
	public String getB15_information() {
		return b15_information;
	}
	public String getC15_quarter() {
		return c15_quarter;
	}
	public String getD15_remarks() {
		return d15_remarks;
	}
	public String getA16_srl_no() {
		return a16_srl_no;
	}
	public String getB16_information() {
		return b16_information;
	}
	public String getC16_quarter() {
		return c16_quarter;
	}
	public String getD16_remarks() {
		return d16_remarks;
	}
	public String getA17_srl_no() {
		return a17_srl_no;
	}
	public String getB17_information() {
		return b17_information;
	}
	public String getC17_quarter() {
		return c17_quarter;
	}
	public String getD17_remarks() {
		return d17_remarks;
	}
	public String getA18_srl_no() {
		return a18_srl_no;
	}
	public String getB18_information() {
		return b18_information;
	}
	public String getC18_quarter() {
		return c18_quarter;
	}
	public String getD18_remarks() {
		return d18_remarks;
	}
	public String getA19_srl_no() {
		return a19_srl_no;
	}
	public String getB19_information() {
		return b19_information;
	}
	public String getC19_quarter() {
		return c19_quarter;
	}
	public String getD19_remarks() {
		return d19_remarks;
	}
	public String getA20_srl_no() {
		return a20_srl_no;
	}
	public String getB20_information() {
		return b20_information;
	}
	public String getC20_quarter() {
		return c20_quarter;
	}
	public String getD20_remarks() {
		return d20_remarks;
	}
	public String getA21_srl_no_a() {
		return a21_srl_no_a;
	}
	public String getB21_information_a() {
		return b21_information_a;
	}
	public String getC21_quarter_a() {
		return c21_quarter_a;
	}
	public String getD21_remarks_a() {
		return d21_remarks_a;
	}
	public String getA21_srl_no_b() {
		return a21_srl_no_b;
	}
	public String getB21_information_b() {
		return b21_information_b;
	}
	public String getC21_quarter_b() {
		return c21_quarter_b;
	}
	public String getD21_remarks_b() {
		return d21_remarks_b;
	}
	public String getA21_srl_no_c() {
		return a21_srl_no_c;
	}
	public String getB21_information_c() {
		return b21_information_c;
	}
	public String getC21_quarter_c() {
		return c21_quarter_c;
	}
	public String getD21_remarks_c() {
		return d21_remarks_c;
	}
	public String getA21_srl_no_d() {
		return a21_srl_no_d;
	}
	public String getB21_information_d() {
		return b21_information_d;
	}
	public String getC21_quarter_d() {
		return c21_quarter_d;
	}
	public String getD21_remarks_d() {
		return d21_remarks_d;
	}
	public String getA22_srl_no() {
		return a22_srl_no;
	}
	public String getB22_information() {
		return b22_information;
	}
	public String getC22_quarter() {
		return c22_quarter;
	}
	public String getD22_remarks() {
		return d22_remarks;
	}
	public String getA23_srl_no() {
		return a23_srl_no;
	}
	public String getB23_information() {
		return b23_information;
	}
	public String getC23_quarter() {
		return c23_quarter;
	}
	public String getD23_remarks() {
		return d23_remarks;
	}
	public String getA24_srl_no() {
		return a24_srl_no;
	}
	public String getB24_information() {
		return b24_information;
	}
	public String getC24_quarter() {
		return c24_quarter;
	}
	public String getD24_remarks() {
		return d24_remarks;
	}
	public String getA25_srl_no() {
		return a25_srl_no;
	}
	public String getB25_information() {
		return b25_information;
	}
	public String getC25_quarter() {
		return c25_quarter;
	}
	public String getD25_remarks() {
		return d25_remarks;
	}
	public String getA26_srl_no() {
		return a26_srl_no;
	}
	public String getB26_information() {
		return b26_information;
	}
	public String getC26_quarter() {
		return c26_quarter;
	}
	public String getD26_remarks() {
		return d26_remarks;
	}
	public String getA27_srl_no() {
		return a27_srl_no;
	}
	public String getB27_information() {
		return b27_information;
	}
	public String getC27_quarter() {
		return c27_quarter;
	}
	public String getD27_remarks() {
		return d27_remarks;
	}
	public String getA28_srl_no() {
		return a28_srl_no;
	}
	public String getB28_information() {
		return b28_information;
	}
	public String getC28_quarter() {
		return c28_quarter;
	}
	public String getD28_remarks() {
		return d28_remarks;
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
	public Date getRep_period_from() {
		return rep_period_from;
	}
	public Date getRep_period_to() {
		return rep_period_to;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public String getVerify_flg() {
		return verify_flg;
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
	public void setB1_information(String b1_information) {
		this.b1_information = b1_information;
	}
	public void setC1_quarter(String c1_quarter) {
		this.c1_quarter = c1_quarter;
	}
	public void setD1_remarks(String d1_remarks) {
		this.d1_remarks = d1_remarks;
	}
	public void setA2_srl_no(String a2_srl_no) {
		this.a2_srl_no = a2_srl_no;
	}
	public void setB2_information(String b2_information) {
		this.b2_information = b2_information;
	}
	public void setC2_quarter(String c2_quarter) {
		this.c2_quarter = c2_quarter;
	}
	public void setD2_remarks(String d2_remarks) {
		this.d2_remarks = d2_remarks;
	}
	public void setA3_srl_no(String a3_srl_no) {
		this.a3_srl_no = a3_srl_no;
	}
	public void setB3_information(String b3_information) {
		this.b3_information = b3_information;
	}
	public void setC3_quarter(String c3_quarter) {
		this.c3_quarter = c3_quarter;
	}
	public void setD3_remarks(String d3_remarks) {
		this.d3_remarks = d3_remarks;
	}
	public void setA4_srl_no(String a4_srl_no) {
		this.a4_srl_no = a4_srl_no;
	}
	public void setB4_information(String b4_information) {
		this.b4_information = b4_information;
	}
	public void setC4_quarter(String c4_quarter) {
		this.c4_quarter = c4_quarter;
	}
	public void setD4_remarls(String d4_remarls) {
		this.d4_remarls = d4_remarls;
	}
	public void setA5_srl_no(String a5_srl_no) {
		this.a5_srl_no = a5_srl_no;
	}
	public void setB5_information(String b5_information) {
		this.b5_information = b5_information;
	}
	public void setC5_quarter(String c5_quarter) {
		this.c5_quarter = c5_quarter;
	}
	public void setD5_remarks(String d5_remarks) {
		this.d5_remarks = d5_remarks;
	}
	public void setA6_srl_no(String a6_srl_no) {
		this.a6_srl_no = a6_srl_no;
	}
	public void setB6_information(String b6_information) {
		this.b6_information = b6_information;
	}
	public void setC6_quarter(String c6_quarter) {
		this.c6_quarter = c6_quarter;
	}
	public void setD6_remarks(String d6_remarks) {
		this.d6_remarks = d6_remarks;
	}
	public void setA7_srl_no(String a7_srl_no) {
		this.a7_srl_no = a7_srl_no;
	}
	public void setB7_information(String b7_information) {
		this.b7_information = b7_information;
	}
	public void setC7_quarter(String c7_quarter) {
		this.c7_quarter = c7_quarter;
	}
	public void setD7_remarks(String d7_remarks) {
		this.d7_remarks = d7_remarks;
	}
	public void setA8_srl_no(String a8_srl_no) {
		this.a8_srl_no = a8_srl_no;
	}
	public void setB8_information(String b8_information) {
		this.b8_information = b8_information;
	}
	public void setC8_quarter(String c8_quarter) {
		this.c8_quarter = c8_quarter;
	}
	public void setD8_remarks(String d8_remarks) {
		this.d8_remarks = d8_remarks;
	}
	public void setA9_srl_no(String a9_srl_no) {
		this.a9_srl_no = a9_srl_no;
	}
	public void setB9_information(String b9_information) {
		this.b9_information = b9_information;
	}
	public void setC9_quarter(String c9_quarter) {
		this.c9_quarter = c9_quarter;
	}
	public void setD9_remarks(String d9_remarks) {
		this.d9_remarks = d9_remarks;
	}
	public void setA10_srl_no(String a10_srl_no) {
		this.a10_srl_no = a10_srl_no;
	}
	public void setB10_information(String b10_information) {
		this.b10_information = b10_information;
	}
	public void setC10_quarter(String c10_quarter) {
		this.c10_quarter = c10_quarter;
	}
	public void setD10_remarks(String d10_remarks) {
		this.d10_remarks = d10_remarks;
	}
	public void setA11_srl_no(String a11_srl_no) {
		this.a11_srl_no = a11_srl_no;
	}
	public void setB11_information(String b11_information) {
		this.b11_information = b11_information;
	}
	public void setC11_quarter(String c11_quarter) {
		this.c11_quarter = c11_quarter;
	}
	public void setD11_remarks(String d11_remarks) {
		this.d11_remarks = d11_remarks;
	}
	public void setA12_srl_no(String a12_srl_no) {
		this.a12_srl_no = a12_srl_no;
	}
	public void setB12_information(String b12_information) {
		this.b12_information = b12_information;
	}
	public void setC12_quarter(String c12_quarter) {
		this.c12_quarter = c12_quarter;
	}
	public void setD12_remarks(String d12_remarks) {
		this.d12_remarks = d12_remarks;
	}
	public void setA13_srl_no(String a13_srl_no) {
		this.a13_srl_no = a13_srl_no;
	}
	public void setB13_information(String b13_information) {
		this.b13_information = b13_information;
	}
	public void setC13_quarter(String c13_quarter) {
		this.c13_quarter = c13_quarter;
	}
	public void setD13_remarks(String d13_remarks) {
		this.d13_remarks = d13_remarks;
	}
	public void setA14_srl_no(String a14_srl_no) {
		this.a14_srl_no = a14_srl_no;
	}
	public void setB14_information(String b14_information) {
		this.b14_information = b14_information;
	}
	public void setC14_quarter(String c14_quarter) {
		this.c14_quarter = c14_quarter;
	}
	public void setD14_remarks(String d14_remarks) {
		this.d14_remarks = d14_remarks;
	}
	public void setA15_srl_no(String a15_srl_no) {
		this.a15_srl_no = a15_srl_no;
	}
	public void setB15_information(String b15_information) {
		this.b15_information = b15_information;
	}
	public void setC15_quarter(String c15_quarter) {
		this.c15_quarter = c15_quarter;
	}
	public void setD15_remarks(String d15_remarks) {
		this.d15_remarks = d15_remarks;
	}
	public void setA16_srl_no(String a16_srl_no) {
		this.a16_srl_no = a16_srl_no;
	}
	public void setB16_information(String b16_information) {
		this.b16_information = b16_information;
	}
	public void setC16_quarter(String c16_quarter) {
		this.c16_quarter = c16_quarter;
	}
	public void setD16_remarks(String d16_remarks) {
		this.d16_remarks = d16_remarks;
	}
	public void setA17_srl_no(String a17_srl_no) {
		this.a17_srl_no = a17_srl_no;
	}
	public void setB17_information(String b17_information) {
		this.b17_information = b17_information;
	}
	public void setC17_quarter(String c17_quarter) {
		this.c17_quarter = c17_quarter;
	}
	public void setD17_remarks(String d17_remarks) {
		this.d17_remarks = d17_remarks;
	}
	public void setA18_srl_no(String a18_srl_no) {
		this.a18_srl_no = a18_srl_no;
	}
	public void setB18_information(String b18_information) {
		this.b18_information = b18_information;
	}
	public void setC18_quarter(String c18_quarter) {
		this.c18_quarter = c18_quarter;
	}
	public void setD18_remarks(String d18_remarks) {
		this.d18_remarks = d18_remarks;
	}
	public void setA19_srl_no(String a19_srl_no) {
		this.a19_srl_no = a19_srl_no;
	}
	public void setB19_information(String b19_information) {
		this.b19_information = b19_information;
	}
	public void setC19_quarter(String c19_quarter) {
		this.c19_quarter = c19_quarter;
	}
	public void setD19_remarks(String d19_remarks) {
		this.d19_remarks = d19_remarks;
	}
	public void setA20_srl_no(String a20_srl_no) {
		this.a20_srl_no = a20_srl_no;
	}
	public void setB20_information(String b20_information) {
		this.b20_information = b20_information;
	}
	public void setC20_quarter(String c20_quarter) {
		this.c20_quarter = c20_quarter;
	}
	public void setD20_remarks(String d20_remarks) {
		this.d20_remarks = d20_remarks;
	}
	public void setA21_srl_no_a(String a21_srl_no_a) {
		this.a21_srl_no_a = a21_srl_no_a;
	}
	public void setB21_information_a(String b21_information_a) {
		this.b21_information_a = b21_information_a;
	}
	public void setC21_quarter_a(String c21_quarter_a) {
		this.c21_quarter_a = c21_quarter_a;
	}
	public void setD21_remarks_a(String d21_remarks_a) {
		this.d21_remarks_a = d21_remarks_a;
	}
	public void setA21_srl_no_b(String a21_srl_no_b) {
		this.a21_srl_no_b = a21_srl_no_b;
	}
	public void setB21_information_b(String b21_information_b) {
		this.b21_information_b = b21_information_b;
	}
	public void setC21_quarter_b(String c21_quarter_b) {
		this.c21_quarter_b = c21_quarter_b;
	}
	public void setD21_remarks_b(String d21_remarks_b) {
		this.d21_remarks_b = d21_remarks_b;
	}
	public void setA21_srl_no_c(String a21_srl_no_c) {
		this.a21_srl_no_c = a21_srl_no_c;
	}
	public void setB21_information_c(String b21_information_c) {
		this.b21_information_c = b21_information_c;
	}
	public void setC21_quarter_c(String c21_quarter_c) {
		this.c21_quarter_c = c21_quarter_c;
	}
	public void setD21_remarks_c(String d21_remarks_c) {
		this.d21_remarks_c = d21_remarks_c;
	}
	public void setA21_srl_no_d(String a21_srl_no_d) {
		this.a21_srl_no_d = a21_srl_no_d;
	}
	public void setB21_information_d(String b21_information_d) {
		this.b21_information_d = b21_information_d;
	}
	public void setC21_quarter_d(String c21_quarter_d) {
		this.c21_quarter_d = c21_quarter_d;
	}
	public void setD21_remarks_d(String d21_remarks_d) {
		this.d21_remarks_d = d21_remarks_d;
	}
	public void setA22_srl_no(String a22_srl_no) {
		this.a22_srl_no = a22_srl_no;
	}
	public void setB22_information(String b22_information) {
		this.b22_information = b22_information;
	}
	public void setC22_quarter(String c22_quarter) {
		this.c22_quarter = c22_quarter;
	}
	public void setD22_remarks(String d22_remarks) {
		this.d22_remarks = d22_remarks;
	}
	public void setA23_srl_no(String a23_srl_no) {
		this.a23_srl_no = a23_srl_no;
	}
	public void setB23_information(String b23_information) {
		this.b23_information = b23_information;
	}
	public void setC23_quarter(String c23_quarter) {
		this.c23_quarter = c23_quarter;
	}
	public void setD23_remarks(String d23_remarks) {
		this.d23_remarks = d23_remarks;
	}
	public void setA24_srl_no(String a24_srl_no) {
		this.a24_srl_no = a24_srl_no;
	}
	public void setB24_information(String b24_information) {
		this.b24_information = b24_information;
	}
	public void setC24_quarter(String c24_quarter) {
		this.c24_quarter = c24_quarter;
	}
	public void setD24_remarks(String d24_remarks) {
		this.d24_remarks = d24_remarks;
	}
	public void setA25_srl_no(String a25_srl_no) {
		this.a25_srl_no = a25_srl_no;
	}
	public void setB25_information(String b25_information) {
		this.b25_information = b25_information;
	}
	public void setC25_quarter(String c25_quarter) {
		this.c25_quarter = c25_quarter;
	}
	public void setD25_remarks(String d25_remarks) {
		this.d25_remarks = d25_remarks;
	}
	public void setA26_srl_no(String a26_srl_no) {
		this.a26_srl_no = a26_srl_no;
	}
	public void setB26_information(String b26_information) {
		this.b26_information = b26_information;
	}
	public void setC26_quarter(String c26_quarter) {
		this.c26_quarter = c26_quarter;
	}
	public void setD26_remarks(String d26_remarks) {
		this.d26_remarks = d26_remarks;
	}
	public void setA27_srl_no(String a27_srl_no) {
		this.a27_srl_no = a27_srl_no;
	}
	public void setB27_information(String b27_information) {
		this.b27_information = b27_information;
	}
	public void setC27_quarter(String c27_quarter) {
		this.c27_quarter = c27_quarter;
	}
	public void setD27_remarks(String d27_remarks) {
		this.d27_remarks = d27_remarks;
	}
	public void setA28_srl_no(String a28_srl_no) {
		this.a28_srl_no = a28_srl_no;
	}
	public void setB28_information(String b28_information) {
		this.b28_information = b28_information;
	}
	public void setC28_quarter(String c28_quarter) {
		this.c28_quarter = c28_quarter;
	}
	public void setD28_remarks(String d28_remarks) {
		this.d28_remarks = d28_remarks;
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
	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}
	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public void setVerify_flg(String verify_flg) {
		this.verify_flg = verify_flg;
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

	public T28Reports() {
		super();
		// TODO Auto-generated constructor stub
	}
	public T28Reports(String a1_srl_no, String b1_information, String c1_quarter, String d1_remarks, String a2_srl_no,
			String b2_information, String c2_quarter, String d2_remarks, String a3_srl_no, String b3_information,
			String c3_quarter, String d3_remarks, String a4_srl_no, String b4_information, String c4_quarter,
			String d4_remarls, String a5_srl_no, String b5_information, String c5_quarter, String d5_remarks,
			String a6_srl_no, String b6_information, String c6_quarter, String d6_remarks, String a7_srl_no,
			String b7_information, String c7_quarter, String d7_remarks, String a8_srl_no, String b8_information,
			String c8_quarter, String d8_remarks, String a9_srl_no, String b9_information, String c9_quarter,
			String d9_remarks, String a10_srl_no, String b10_information, String c10_quarter, String d10_remarks,
			String a11_srl_no, String b11_information, String c11_quarter, String d11_remarks, String a12_srl_no,
			String b12_information, String c12_quarter, String d12_remarks, String a13_srl_no, String b13_information,
			String c13_quarter, String d13_remarks, String a14_srl_no, String b14_information, String c14_quarter,
			String d14_remarks, String a15_srl_no, String b15_information, String c15_quarter, String d15_remarks,
			String a16_srl_no, String b16_information, String c16_quarter, String d16_remarks, String a17_srl_no,
			String b17_information, String c17_quarter, String d17_remarks, String a18_srl_no, String b18_information,
			String c18_quarter, String d18_remarks, String a19_srl_no, String b19_information, String c19_quarter,
			String d19_remarks, String a20_srl_no, String b20_information, String c20_quarter, String d20_remarks,
			String a21_srl_no_a, String b21_information_a, String c21_quarter_a, String d21_remarks_a,
			String a21_srl_no_b, String b21_information_b, String c21_quarter_b, String d21_remarks_b,
			String a21_srl_no_c, String b21_information_c, String c21_quarter_c, String d21_remarks_c,
			String a21_srl_no_d, String b21_information_d, String c21_quarter_d, String d21_remarks_d,
			String a22_srl_no, String b22_information, String c22_quarter, String d22_remarks, String a23_srl_no,
			String b23_information, String c23_quarter, String d23_remarks, String a24_srl_no, String b24_information,
			String c24_quarter, String d24_remarks, String a25_srl_no, String b25_information, String c25_quarter,
			String d25_remarks, String a26_srl_no, String b26_information, String c26_quarter, String d26_remarks,
			String a27_srl_no, String b27_information, String c27_quarter, String d27_remarks, String a28_srl_no,
			String b28_information, String c28_quarter, String d28_remarks, String report_code, String report_name,
			Date report_date, Date rep_period_from, Date rep_period_to, String entity_flg, String modify_flg,
			String verify_flg, String entry_user, String modify_user, String verify_user, Date entry_time,
			Date modify_time, Date verify_time) {
		super();
		this.a1_srl_no = a1_srl_no;
		this.b1_information = b1_information;
		this.c1_quarter = c1_quarter;
		this.d1_remarks = d1_remarks;
		this.a2_srl_no = a2_srl_no;
		this.b2_information = b2_information;
		this.c2_quarter = c2_quarter;
		this.d2_remarks = d2_remarks;
		this.a3_srl_no = a3_srl_no;
		this.b3_information = b3_information;
		this.c3_quarter = c3_quarter;
		this.d3_remarks = d3_remarks;
		this.a4_srl_no = a4_srl_no;
		this.b4_information = b4_information;
		this.c4_quarter = c4_quarter;
		this.d4_remarls = d4_remarls;
		this.a5_srl_no = a5_srl_no;
		this.b5_information = b5_information;
		this.c5_quarter = c5_quarter;
		this.d5_remarks = d5_remarks;
		this.a6_srl_no = a6_srl_no;
		this.b6_information = b6_information;
		this.c6_quarter = c6_quarter;
		this.d6_remarks = d6_remarks;
		this.a7_srl_no = a7_srl_no;
		this.b7_information = b7_information;
		this.c7_quarter = c7_quarter;
		this.d7_remarks = d7_remarks;
		this.a8_srl_no = a8_srl_no;
		this.b8_information = b8_information;
		this.c8_quarter = c8_quarter;
		this.d8_remarks = d8_remarks;
		this.a9_srl_no = a9_srl_no;
		this.b9_information = b9_information;
		this.c9_quarter = c9_quarter;
		this.d9_remarks = d9_remarks;
		this.a10_srl_no = a10_srl_no;
		this.b10_information = b10_information;
		this.c10_quarter = c10_quarter;
		this.d10_remarks = d10_remarks;
		this.a11_srl_no = a11_srl_no;
		this.b11_information = b11_information;
		this.c11_quarter = c11_quarter;
		this.d11_remarks = d11_remarks;
		this.a12_srl_no = a12_srl_no;
		this.b12_information = b12_information;
		this.c12_quarter = c12_quarter;
		this.d12_remarks = d12_remarks;
		this.a13_srl_no = a13_srl_no;
		this.b13_information = b13_information;
		this.c13_quarter = c13_quarter;
		this.d13_remarks = d13_remarks;
		this.a14_srl_no = a14_srl_no;
		this.b14_information = b14_information;
		this.c14_quarter = c14_quarter;
		this.d14_remarks = d14_remarks;
		this.a15_srl_no = a15_srl_no;
		this.b15_information = b15_information;
		this.c15_quarter = c15_quarter;
		this.d15_remarks = d15_remarks;
		this.a16_srl_no = a16_srl_no;
		this.b16_information = b16_information;
		this.c16_quarter = c16_quarter;
		this.d16_remarks = d16_remarks;
		this.a17_srl_no = a17_srl_no;
		this.b17_information = b17_information;
		this.c17_quarter = c17_quarter;
		this.d17_remarks = d17_remarks;
		this.a18_srl_no = a18_srl_no;
		this.b18_information = b18_information;
		this.c18_quarter = c18_quarter;
		this.d18_remarks = d18_remarks;
		this.a19_srl_no = a19_srl_no;
		this.b19_information = b19_information;
		this.c19_quarter = c19_quarter;
		this.d19_remarks = d19_remarks;
		this.a20_srl_no = a20_srl_no;
		this.b20_information = b20_information;
		this.c20_quarter = c20_quarter;
		this.d20_remarks = d20_remarks;
		this.a21_srl_no_a = a21_srl_no_a;
		this.b21_information_a = b21_information_a;
		this.c21_quarter_a = c21_quarter_a;
		this.d21_remarks_a = d21_remarks_a;
		this.a21_srl_no_b = a21_srl_no_b;
		this.b21_information_b = b21_information_b;
		this.c21_quarter_b = c21_quarter_b;
		this.d21_remarks_b = d21_remarks_b;
		this.a21_srl_no_c = a21_srl_no_c;
		this.b21_information_c = b21_information_c;
		this.c21_quarter_c = c21_quarter_c;
		this.d21_remarks_c = d21_remarks_c;
		this.a21_srl_no_d = a21_srl_no_d;
		this.b21_information_d = b21_information_d;
		this.c21_quarter_d = c21_quarter_d;
		this.d21_remarks_d = d21_remarks_d;
		this.a22_srl_no = a22_srl_no;
		this.b22_information = b22_information;
		this.c22_quarter = c22_quarter;
		this.d22_remarks = d22_remarks;
		this.a23_srl_no = a23_srl_no;
		this.b23_information = b23_information;
		this.c23_quarter = c23_quarter;
		this.d23_remarks = d23_remarks;
		this.a24_srl_no = a24_srl_no;
		this.b24_information = b24_information;
		this.c24_quarter = c24_quarter;
		this.d24_remarks = d24_remarks;
		this.a25_srl_no = a25_srl_no;
		this.b25_information = b25_information;
		this.c25_quarter = c25_quarter;
		this.d25_remarks = d25_remarks;
		this.a26_srl_no = a26_srl_no;
		this.b26_information = b26_information;
		this.c26_quarter = c26_quarter;
		this.d26_remarks = d26_remarks;
		this.a27_srl_no = a27_srl_no;
		this.b27_information = b27_information;
		this.c27_quarter = c27_quarter;
		this.d27_remarks = d27_remarks;
		this.a28_srl_no = a28_srl_no;
		this.b28_information = b28_information;
		this.c28_quarter = c28_quarter;
		this.d28_remarks = d28_remarks;
		this.report_code = report_code;
		this.report_name = report_name;
		this.report_date = report_date;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
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

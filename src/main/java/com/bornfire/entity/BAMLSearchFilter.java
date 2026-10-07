package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class BAMLSearchFilter {

	private String cust_id;
	private String tran_date;
	private String tran_id;
	private String part_tran_srl_num;
	private Character tran_type;
	private String tran_sub_type;
	private Character part_tran_type;
	private String tran_crncy_code;
	private BigDecimal tran_amt;
	private String tran_particular;
	private BigDecimal fx_tran_amt;

	private String pstd_date;
	private String rate_Code;
	private String foracid;
	private String acct_name;
    private String acct_opn_date;
	private String acct_cls_date;
	private String cntry_code;
	private String cust_sector_code;
	private String address1;
	private String address2;
	private String city_code;
	private String preferredphone;
	private String cust_sex;
	private String cust_first_name;
	private String cust_last_name;
	private String nationality;

	private String cust_dob;

	public String getCust_id() {
		return cust_id;
	}

	public String getTran_date() {
		return tran_date;
	}

	public String getTran_id() {
		return tran_id;
	}

	public String getPart_tran_srl_num() {
		return part_tran_srl_num;
	}

	public Character getTran_type() {
		return tran_type;
	}

	public String getTran_sub_type() {
		return tran_sub_type;
	}

	public Character getPart_tran_type() {
		return part_tran_type;
	}

	public String getTran_crncy_code() {
		return tran_crncy_code;
	}

	public BigDecimal getTran_amt() {
		return tran_amt;
	}

	public String getTran_particular() {
		return tran_particular;
	}

	public BigDecimal getFx_tran_amt() {
		return fx_tran_amt;
	}

	public String getPstd_date() {
		return pstd_date;
	}

	public String getRate_Code() {
		return rate_Code;
	}

	public String getForacid() {
		return foracid;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public String getAcct_opn_date() {
		return acct_opn_date;
	}

	public String getAcct_cls_date() {
		return acct_cls_date;
	}

	public String getCntry_code() {
		return cntry_code;
	}

	public String getCust_sector_code() {
		return cust_sector_code;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity_code() {
		return city_code;
	}

	public String getPreferredphone() {
		return preferredphone;
	}

	public String getCust_sex() {
		return cust_sex;
	}

	public String getCust_first_name() {
		return cust_first_name;
	}

	public String getCust_last_name() {
		return cust_last_name;
	}

	public String getNationality() {
		return nationality;
	}

	public String getCust_dob() {
		return cust_dob;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}

	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}

	public void setPart_tran_srl_num(String part_tran_srl_num) {
		this.part_tran_srl_num = part_tran_srl_num;
	}

	public void setTran_type(Character tran_type) {
		this.tran_type = tran_type;
	}

	public void setTran_sub_type(String tran_sub_type) {
		this.tran_sub_type = tran_sub_type;
	}

	public void setPart_tran_type(Character part_tran_type) {
		this.part_tran_type = part_tran_type;
	}

	public void setTran_crncy_code(String tran_crncy_code) {
		this.tran_crncy_code = tran_crncy_code;
	}

	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}

	public void setTran_particular(String tran_particular) {
		this.tran_particular = tran_particular;
	}

	public void setFx_tran_amt(BigDecimal fx_tran_amt) {
		this.fx_tran_amt = fx_tran_amt;
	}

	public void setPstd_date(String pstd_date) {
		this.pstd_date = pstd_date;
	}

	public void setRate_Code(String rate_Code) {
		this.rate_Code = rate_Code;
	}

	public void setForacid(String foracid) {
		this.foracid = foracid;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public void setAcct_opn_date(String acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
	}

	public void setAcct_cls_date(String acct_cls_date) {
		this.acct_cls_date = acct_cls_date;
	}

	public void setCntry_code(String cntry_code) {
		this.cntry_code = cntry_code;
	}

	public void setCust_sector_code(String cust_sector_code) {
		this.cust_sector_code = cust_sector_code;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public void setPreferredphone(String preferredphone) {
		this.preferredphone = preferredphone;
	}

	public void setCust_sex(String cust_sex) {
		this.cust_sex = cust_sex;
	}

	public void setCust_first_name(String cust_first_name) {
		this.cust_first_name = cust_first_name;
	}

	public void setCust_last_name(String cust_last_name) {
		this.cust_last_name = cust_last_name;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setCust_dob(String cust_dob) {
		this.cust_dob = cust_dob;
	}

	@Override
	public String toString() {
		return "BAMLSearchFilter [cust_id=" + cust_id + ", tran_date=" + tran_date + ", tran_id=" + tran_id
				+ ", part_tran_srl_num=" + part_tran_srl_num + ", tran_type=" + tran_type + ", tran_sub_type="
				+ tran_sub_type + ", part_tran_type=" + part_tran_type + ", tran_crncy_code=" + tran_crncy_code
				+ ", tran_amt=" + tran_amt + ", tran_particular=" + tran_particular + ", fx_tran_amt=" + fx_tran_amt
				+ ", pstd_date=" + pstd_date + ", rate_Code=" + rate_Code + ", foracid=" + foracid + ", acct_name="
				+ acct_name + ", acct_opn_date=" + acct_opn_date + ", acct_cls_date=" + acct_cls_date + ", cntry_code="
				+ cntry_code + ", cust_sector_code=" + cust_sector_code + ", address1=" + address1 + ", address2="
				+ address2 + ", city_code=" + city_code + ", preferredphone=" + preferredphone + ", cust_sex="
				+ cust_sex + ", cust_first_name=" + cust_first_name + ", cust_last_name=" + cust_last_name
				+ ", nationality=" + nationality + ", cust_dob=" + cust_dob + "]";
	}


}

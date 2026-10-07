package com.bornfire.entity;

import java.math.BigDecimal;

public class BAMLSearchIntFilter {
	
	private String cif_id;
	private String tran_date;
	private String tran_id;
	private String part_tran_srl_num;
	private Character tran_type;
	private String tran_sub_type;
	private Character part_tran_type;
	private BigDecimal tran_amt;
	private String address1;
	private String address2;
	private String nat_id_card_num;
	private String preferredphone;
	private String cust_name;
	private String occupation;
	private String placeofbirth;
	
	public String getCif_id() {
		return cif_id;
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
	public String getTran_sub_type() {
		return tran_sub_type;
	}
	public BigDecimal getTran_amt() {
		return tran_amt;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getNat_id_card_num() {
		return nat_id_card_num;
	}
	public String getPreferredphone() {
		return preferredphone;
	}
	public String getCust_name() {
		return cust_name;
	}
	public String getOccupation() {
		return occupation;
	}
	public String getPlaceofbirth() {
		return placeofbirth;
	}
	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}
	public void setTran_date(String obj) {
		this.tran_date = obj;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public void setPart_tran_srl_num(String part_tran_srl_num) {
		this.part_tran_srl_num = part_tran_srl_num;
	}
	public void setTran_sub_type(String tran_sub_type) {
		this.tran_sub_type = tran_sub_type;
	}
	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setNat_id_card_num(String nat_id_card_num) {
		this.nat_id_card_num = nat_id_card_num;
	}
	public void setPreferredphone(String preferredphone) {
		this.preferredphone = preferredphone;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public void setPlaceofbirth(String placeofbirth) {
		this.placeofbirth = placeofbirth;
	}
	public Character getTran_type() {
		return tran_type;
	}
	public Character getPart_tran_type() {
		return part_tran_type;
	}
	public void setTran_type(Character tran_type) {
		this.tran_type = tran_type;
	}
	public void setPart_tran_type(Character part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	
	
	@Override
	public String toString() {
		return "BAMLSearchIntFilter [cif_id=" + cif_id + ", tran_date=" + tran_date + ", tran_id=" + tran_id
				+ ", part_tran_srl_num=" + part_tran_srl_num + ", tran_type=" + tran_type + ", tran_sub_type="
				+ tran_sub_type + ", part_tran_type=" + part_tran_type + ", tran_amt=" + tran_amt + ", address1="
				+ address1 + ", address2=" + address2 + ", nat_id_card_num=" + nat_id_card_num + ", preferredphone="
				+ preferredphone + ", cust_name=" + cust_name + ", occupation=" + occupation + ", placeofbirth="
				+ placeofbirth + "]";
	}
	
	
	

}

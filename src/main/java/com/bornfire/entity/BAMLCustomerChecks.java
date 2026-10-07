package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BAML_ON_BOARD_CHECKS")
public class BAMLCustomerChecks {
	
	@Id
	private BigDecimal	ref_no;
	private Date	ref_date;
	private String	cust_id;
	private String	foracid;
	private String	acid;
	private String	cust_name;
	private String	cust_short_name;
	private String	cust_first_name;
	private String	cust_middle_name;
	private String	cust_last_name;
	private String	address_id;
	private String	address1;
	private String	address2;
	private String	address3;
	private String	city_code;
	private String	state_code;
	private String	cntry_code;
	private String	pin_code;
	private String	nationality;
	private String	residence_country;
	private Date	cust_dob;
	private String	pan;
	private String	ssn;
	private String	nat_id_card_num;
	private String	preferredphone;
	private String	preferredmail;
	private String	match_data;
	private String	match_cust_name;
	private String	match_cust_short_name;
	private String	match_cust_first_name;
	private String	match_cust_middle_name;
	private String	match_cust_last_name;
	private String	match_address_id;
	private String	match_address1;
	private String	match_address2;
	private String	match_address3;
	private String	match_city_code;
	private String	match_state_code;
	private String	match_cntry_code;
	private String	match_pin_code;
	private String	match_nationality;
	private String	match_residence_country;
	private Date	match_cust_dob;
	private String	match_pan;
	private String	match_ssn;
	private String	match_nat_id_card_num;
	private String	match_preferredphone;
	private String	match_preferredmail;
	private String	remarks;
	private String	email_flg;
	private String	alert_flg;
	private String	sms_flg;
	private String	email_sent_flg;
	private String	alert_sent_flg;
	private String	sms_sent_flg;
	private String	override_flg;
	private String	override_remarks;
	private String	operation;
	private String	type_of_mode;

	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getType_of_mode() {
		return type_of_mode;
	}
	public void setType_of_mode(String type_of_mode) {
		this.type_of_mode = type_of_mode;
	}
	public BigDecimal getRef_no() {
		return ref_no;
	}
	public void setRef_no(BigDecimal ref_no) {
		this.ref_no = ref_no;
	}
	public Date getRef_date() {
		return ref_date;
	}
	public void setRef_date(Date ref_date) {
		this.ref_date = ref_date;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getForacid() {
		return foracid;
	}
	public void setForacid(String foracid) {
		this.foracid = foracid;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_short_name() {
		return cust_short_name;
	}
	public void setCust_short_name(String cust_short_name) {
		this.cust_short_name = cust_short_name;
	}
	public String getCust_first_name() {
		return cust_first_name;
	}
	public void setCust_first_name(String cust_first_name) {
		this.cust_first_name = cust_first_name;
	}
	public String getCust_middle_name() {
		return cust_middle_name;
	}
	public void setCust_middle_name(String cust_middle_name) {
		this.cust_middle_name = cust_middle_name;
	}
	public String getCust_last_name() {
		return cust_last_name;
	}
	public void setCust_last_name(String cust_last_name) {
		this.cust_last_name = cust_last_name;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getCntry_code() {
		return cntry_code;
	}
	public void setCntry_code(String cntry_code) {
		this.cntry_code = cntry_code;
	}
	public String getPin_code() {
		return pin_code;
	}
	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getResidence_country() {
		return residence_country;
	}
	public void setResidence_country(String residence_country) {
		this.residence_country = residence_country;
	}
	public Date getCust_dob() {
		return cust_dob;
	}
	public void setCust_dob(Date cust_dob) {
		this.cust_dob = cust_dob;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getNat_id_card_num() {
		return nat_id_card_num;
	}
	public void setNat_id_card_num(String nat_id_card_num) {
		this.nat_id_card_num = nat_id_card_num;
	}
	public String getPreferredphone() {
		return preferredphone;
	}
	public void setPreferredphone(String preferredphone) {
		this.preferredphone = preferredphone;
	}
	public String getPreferredmail() {
		return preferredmail;
	}
	public void setPreferredmail(String preferredmail) {
		this.preferredmail = preferredmail;
	}
	public String getMatch_data() {
		return match_data;
	}
	public void setMatch_data(String match_data) {
		this.match_data = match_data;
	}
	public String getMatch_cust_name() {
		return match_cust_name;
	}
	public void setMatch_cust_name(String match_cust_name) {
		this.match_cust_name = match_cust_name;
	}
	public String getMatch_cust_short_name() {
		return match_cust_short_name;
	}
	public void setMatch_cust_short_name(String match_cust_short_name) {
		this.match_cust_short_name = match_cust_short_name;
	}
	public String getMatch_cust_first_name() {
		return match_cust_first_name;
	}
	public void setMatch_cust_first_name(String match_cust_first_name) {
		this.match_cust_first_name = match_cust_first_name;
	}
	public String getMatch_cust_middle_name() {
		return match_cust_middle_name;
	}
	public void setMatch_cust_middle_name(String match_cust_middle_name) {
		this.match_cust_middle_name = match_cust_middle_name;
	}
	public String getMatch_cust_last_name() {
		return match_cust_last_name;
	}
	public void setMatch_cust_last_name(String match_cust_last_name) {
		this.match_cust_last_name = match_cust_last_name;
	}
	public String getMatch_address_id() {
		return match_address_id;
	}
	public void setMatch_address_id(String match_address_id) {
		this.match_address_id = match_address_id;
	}
	public String getMatch_address1() {
		return match_address1;
	}
	public void setMatch_address1(String match_address1) {
		this.match_address1 = match_address1;
	}
	public String getMatch_address2() {
		return match_address2;
	}
	public void setMatch_address2(String match_address2) {
		this.match_address2 = match_address2;
	}
	public String getMatch_address3() {
		return match_address3;
	}
	public void setMatch_address3(String match_address3) {
		this.match_address3 = match_address3;
	}
	public String getMatch_city_code() {
		return match_city_code;
	}
	public void setMatch_city_code(String match_city_code) {
		this.match_city_code = match_city_code;
	}
	public String getMatch_state_code() {
		return match_state_code;
	}
	public void setMatch_state_code(String match_state_code) {
		this.match_state_code = match_state_code;
	}
	public String getMatch_cntry_code() {
		return match_cntry_code;
	}
	public void setMatch_cntry_code(String match_cntry_code) {
		this.match_cntry_code = match_cntry_code;
	}
	public String getMatch_pin_code() {
		return match_pin_code;
	}
	public void setMatch_pin_code(String match_pin_code) {
		this.match_pin_code = match_pin_code;
	}
	public String getMatch_nationality() {
		return match_nationality;
	}
	public void setMatch_nationality(String match_nationality) {
		this.match_nationality = match_nationality;
	}
	public String getMatch_residence_country() {
		return match_residence_country;
	}
	public void setMatch_residence_country(String match_residence_country) {
		this.match_residence_country = match_residence_country;
	}
	public Date getMatch_cust_dob() {
		return match_cust_dob;
	}
	public void setMatch_cust_dob(Date match_cust_dob) {
		this.match_cust_dob = match_cust_dob;
	}
	public String getMatch_pan() {
		return match_pan;
	}
	public void setMatch_pan(String match_pan) {
		this.match_pan = match_pan;
	}
	public String getMatch_ssn() {
		return match_ssn;
	}
	public void setMatch_ssn(String match_ssn) {
		this.match_ssn = match_ssn;
	}
	public String getMatch_nat_id_card_num() {
		return match_nat_id_card_num;
	}
	public void setMatch_nat_id_card_num(String match_nat_id_card_num) {
		this.match_nat_id_card_num = match_nat_id_card_num;
	}
	public String getMatch_preferredphone() {
		return match_preferredphone;
	}
	public void setMatch_preferredphone(String match_preferredphone) {
		this.match_preferredphone = match_preferredphone;
	}
	public String getMatch_preferredmail() {
		return match_preferredmail;
	}
	public void setMatch_preferredmail(String match_preferredmail) {
		this.match_preferredmail = match_preferredmail;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEmail_flg() {
		return email_flg;
	}
	public void setEmail_flg(String email_flg) {
		this.email_flg = email_flg;
	}
	public String getAlert_flg() {
		return alert_flg;
	}
	public void setAlert_flg(String alert_flg) {
		this.alert_flg = alert_flg;
	}
	public String getSms_flg() {
		return sms_flg;
	}
	public void setSms_flg(String sms_flg) {
		this.sms_flg = sms_flg;
	}
	public String getEmail_sent_flg() {
		return email_sent_flg;
	}
	public void setEmail_sent_flg(String email_sent_flg) {
		this.email_sent_flg = email_sent_flg;
	}
	public String getAlert_sent_flg() {
		return alert_sent_flg;
	}
	public void setAlert_sent_flg(String alert_sent_flg) {
		this.alert_sent_flg = alert_sent_flg;
	}
	public String getSms_sent_flg() {
		return sms_sent_flg;
	}
	public void setSms_sent_flg(String sms_sent_flg) {
		this.sms_sent_flg = sms_sent_flg;
	}
	public String getOverride_flg() {
		return override_flg;
	}
	public void setOverride_flg(String override_flg) {
		this.override_flg = override_flg;
	}
	public String getOverride_remarks() {
		return override_remarks;
	}
	public void setOverride_remarks(String override_remarks) {
		this.override_remarks = override_remarks;
	}
	public BAMLCustomerChecks(BigDecimal ref_no, Date ref_date, String cust_id, String foracid, String acid,
			String cust_name, String cust_short_name, String cust_first_name, String cust_middle_name,
			String cust_last_name, String address_id, String address1, String address2, String address3,
			String city_code, String state_code, String cntry_code, String pin_code, String nationality,
			String residence_country, Date cust_dob, String pan, String ssn, String nat_id_card_num,
			String preferredphone, String preferredmail, String match_data, String match_cust_name,
			String match_cust_short_name, String match_cust_first_name, String match_cust_middle_name,
			String match_cust_last_name, String match_address_id, String match_address1, String match_address2,
			String match_address3, String match_city_code, String match_state_code, String match_cntry_code,
			String match_pin_code, String match_nationality, String match_residence_country, Date match_cust_dob,
			String match_pan, String match_ssn, String match_nat_id_card_num, String match_preferredphone,
			String match_preferredmail, String remarks, String email_flg, String alert_flg, String sms_flg,
			String email_sent_flg, String alert_sent_flg, String sms_sent_flg, String override_flg,
			String override_remarks, String operation, String type_of_mode) {
		super();
		this.ref_no = ref_no;
		this.ref_date = ref_date;
		this.cust_id = cust_id;
		this.foracid = foracid;
		this.acid = acid;
		this.cust_name = cust_name;
		this.cust_short_name = cust_short_name;
		this.cust_first_name = cust_first_name;
		this.cust_middle_name = cust_middle_name;
		this.cust_last_name = cust_last_name;
		this.address_id = address_id;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city_code = city_code;
		this.state_code = state_code;
		this.cntry_code = cntry_code;
		this.pin_code = pin_code;
		this.nationality = nationality;
		this.residence_country = residence_country;
		this.cust_dob = cust_dob;
		this.pan = pan;
		this.ssn = ssn;
		this.nat_id_card_num = nat_id_card_num;
		this.preferredphone = preferredphone;
		this.preferredmail = preferredmail;
		this.match_data = match_data;
		this.match_cust_name = match_cust_name;
		this.match_cust_short_name = match_cust_short_name;
		this.match_cust_first_name = match_cust_first_name;
		this.match_cust_middle_name = match_cust_middle_name;
		this.match_cust_last_name = match_cust_last_name;
		this.match_address_id = match_address_id;
		this.match_address1 = match_address1;
		this.match_address2 = match_address2;
		this.match_address3 = match_address3;
		this.match_city_code = match_city_code;
		this.match_state_code = match_state_code;
		this.match_cntry_code = match_cntry_code;
		this.match_pin_code = match_pin_code;
		this.match_nationality = match_nationality;
		this.match_residence_country = match_residence_country;
		this.match_cust_dob = match_cust_dob;
		this.match_pan = match_pan;
		this.match_ssn = match_ssn;
		this.match_nat_id_card_num = match_nat_id_card_num;
		this.match_preferredphone = match_preferredphone;
		this.match_preferredmail = match_preferredmail;
		this.remarks = remarks;
		this.email_flg = email_flg;
		this.alert_flg = alert_flg;
		this.sms_flg = sms_flg;
		this.email_sent_flg = email_sent_flg;
		this.alert_sent_flg = alert_sent_flg;
		this.sms_sent_flg = sms_sent_flg;
		this.override_flg = override_flg;
		this.override_remarks = override_remarks;
		this.operation = operation;
		this.type_of_mode = type_of_mode;
	}
	public BAMLCustomerChecks() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}

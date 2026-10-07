package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_STR_TABLE")
public class AML_STR_ENTITY {
	
	@Id
	private String report_id;
	private String report_type;
	
	private String entity_reference_no;
	private String fiu_reference_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date submission_date;
	private String business_type;
	private String details_for_others_1;
	private String name_entity;
	private String acronym;
	private String sector;
	private String first_name_2;
	private String last_name_2;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_birth_2;
	private String nic;
	private String occupation_2;
	private String street_address_2_2;
	private String city_2_2;
	private String country_2;
	private String telephone_no_2;
	private String fax_no_2;
	private String supervised_by;
	private BigDecimal total_str;
	private String in_off1;
	private String in_off2;
	private String in_off3;
	private String in_off4;
	private String in_off5;
	private String in_off6;
	private String details_for_others_3;
	private String description_taken_3_2;
	private String material_impact;
	private String material_impact_desc;
	private String description_taken_3_3;
	private String tran_id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date tran_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date tran_date_1;
	private String tran_type;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_detected;
	private String crncy_code;
	private BigDecimal fx_tran_amt;
	private BigDecimal rate_5_8;
	private BigDecimal tran_amt;
	private String tran_particular;
	private String item_type;
	private String comments_services;
	private String previously_to;
	private String presently_to;
	private BigDecimal estimate_value;
	private String status_code;
	private BigDecimal disposed_value;
	private String street_address;
	private String city_5;
	private String name_persons_1;
	private String reporting_entity_1;
	private String capacity_transaction_1;
	private String name_persons_2;
	private String reporting_entity_2;
	private String capacity_transaction_2;
	private String name_persons_3;
	private String reporting_entity_3;
	private String capacity_transaction_3;
	private String name_persons_4;
	private String reporting_entity_4;
	private String capacity_transaction_4;
	private String name_persons_5;
	private String reporting_entity_5;
	private String capacity_transaction_5;
	private String from_type;
	private String from_party;
	private String to_type;
	private String to_party;
	private String client_account;
	private String client_type;
	private String client_person;
	private String non_client_account;
	private String non_type;
	private String non_person;
	private String ref_num;
	private String party_code;
	private String role_6;
	private String funds_type_6;
	private String country_6;
	private String foracid;
	private String acct_name;
	private String acct_name_1;
	private String schm_type;
	private String acct_crncy_code;
	private String acct_status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date acct_opn_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date acct_closed_date;
	private BigDecimal clr_bal_amt;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date tran_date_7;
	private String comments_details_6;
	private String tran_id_7;
	private String party_code_7;
	private String role_7;
	private String funds_type_7;
	private String country_7_4;
	private String name_7;
	private String incorporation_form_7;
	private String sector_7;
	private String registration_number;
	private String addr_7;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_incorporation;
	private String countryofincorporation;
	private String city_7;
	private String country_7_13;
	private String phone_7;
	private String phone_9;
	private String comments_details_7;
	private String tran_id_8;
	private String party_code_8;
	private String role_8;
	private String funds_type_8;
	private String country_8_4;
	private String gender_8;
	private String cust_first_name;
	private String cust_last_name;
	private String addr_8;
	private String city_8;
	private String country_8_10;
	private String phone_8;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_birth_8;
	private String nationality_8;
	private String residence_country;
	private String identifier_8;
	private String insider_relationship;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_suspension;
	private String is_signatory;
	private String primary_signatory_role;
	private String role_8_18;
	private String comments_details_8;

	public String getReport_id() {
		return report_id;
	}

	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}

	public String getReport_type() {
		return report_type;
	}

	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getEntity_reference_no() {
		return entity_reference_no;
	}

	public void setEntity_reference_no(String entity_reference_no) {
		this.entity_reference_no = entity_reference_no;
	}

	public String getFiu_reference_no() {
		return fiu_reference_no;
	}

	public void setFiu_reference_no(String fiu_reference_no) {
		this.fiu_reference_no = fiu_reference_no;
	}

	public Date getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getDetails_for_others_1() {
		return details_for_others_1;
	}

	public void setDetails_for_others_1(String details_for_others_1) {
		this.details_for_others_1 = details_for_others_1;
	}

	public String getName_entity() {
		return name_entity;
	}

	public void setName_entity(String name_entity) {
		this.name_entity = name_entity;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getFirst_name_2() {
		return first_name_2;
	}

	public void setFirst_name_2(String first_name_2) {
		this.first_name_2 = first_name_2;
	}

	public String getLast_name_2() {
		return last_name_2;
	}

	public void setLast_name_2(String last_name_2) {
		this.last_name_2 = last_name_2;
	}

	public Date getDate_of_birth_2() {
		return date_of_birth_2;
	}

	public void setDate_of_birth_2(Date date_of_birth_2) {
		this.date_of_birth_2 = date_of_birth_2;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getOccupation_2() {
		return occupation_2;
	}

	public void setOccupation_2(String occupation_2) {
		this.occupation_2 = occupation_2;
	}

	public String getStreet_address_2_2() {
		return street_address_2_2;
	}

	public void setStreet_address_2_2(String street_address_2_2) {
		this.street_address_2_2 = street_address_2_2;
	}

	public String getCity_2_2() {
		return city_2_2;
	}

	public void setCity_2_2(String city_2_2) {
		this.city_2_2 = city_2_2;
	}

	public String getCountry_2() {
		return country_2;
	}

	public void setCountry_2(String country_2) {
		this.country_2 = country_2;
	}

	public String getTelephone_no_2() {
		return telephone_no_2;
	}

	public void setTelephone_no_2(String telephone_no_2) {
		this.telephone_no_2 = telephone_no_2;
	}

	public String getFax_no_2() {
		return fax_no_2;
	}

	public void setFax_no_2(String fax_no_2) {
		this.fax_no_2 = fax_no_2;
	}

	public String getSupervised_by() {
		return supervised_by;
	}

	public void setSupervised_by(String supervised_by) {
		this.supervised_by = supervised_by;
	}

	public BigDecimal getTotal_str() {
		return total_str;
	}

	public void setTotal_str(BigDecimal total_str) {
		this.total_str = total_str;
	}

	public String getIn_off1() {
		return in_off1;
	}

	public void setIn_off1(String in_off1) {
		this.in_off1 = in_off1;
	}

	public String getIn_off2() {
		return in_off2;
	}

	public void setIn_off2(String in_off2) {
		this.in_off2 = in_off2;
	}

	public String getIn_off3() {
		return in_off3;
	}

	public void setIn_off3(String in_off3) {
		this.in_off3 = in_off3;
	}

	public String getIn_off4() {
		return in_off4;
	}

	public void setIn_off4(String in_off4) {
		this.in_off4 = in_off4;
	}

	public String getIn_off5() {
		return in_off5;
	}

	public void setIn_off5(String in_off5) {
		this.in_off5 = in_off5;
	}

	public String getIn_off6() {
		return in_off6;
	}

	public void setIn_off6(String in_off6) {
		this.in_off6 = in_off6;
	}

	public String getDetails_for_others_3() {
		return details_for_others_3;
	}

	public void setDetails_for_others_3(String details_for_others_3) {
		this.details_for_others_3 = details_for_others_3;
	}

	public String getDescription_taken_3_2() {
		return description_taken_3_2;
	}

	public void setDescription_taken_3_2(String description_taken_3_2) {
		this.description_taken_3_2 = description_taken_3_2;
	}

	public String getMaterial_impact() {
		return material_impact;
	}

	public void setMaterial_impact(String material_impact) {
		this.material_impact = material_impact;
	}

	public String getMaterial_impact_desc() {
		return material_impact_desc;
	}

	public void setMaterial_impact_desc(String material_impact_desc) {
		this.material_impact_desc = material_impact_desc;
	}

	public String getDescription_taken_3_3() {
		return description_taken_3_3;
	}

	public void setDescription_taken_3_3(String description_taken_3_3) {
		this.description_taken_3_3 = description_taken_3_3;
	}

	public String getTran_id() {
		return tran_id;
	}

	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}

	public Date getTran_date() {
		return tran_date;
	}

	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}

	public Date getTran_date_1() {
		return tran_date_1;
	}

	public void setTran_date_1(Date tran_date_1) {
		this.tran_date_1 = tran_date_1;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	public Date getDate_detected() {
		return date_detected;
	}

	public void setDate_detected(Date date_detected) {
		this.date_detected = date_detected;
	}

	public String getCrncy_code() {
		return crncy_code;
	}

	public void setCrncy_code(String crncy_code) {
		this.crncy_code = crncy_code;
	}

	public BigDecimal getFx_tran_amt() {
		return fx_tran_amt;
	}

	public void setFx_tran_amt(BigDecimal fx_tran_amt) {
		this.fx_tran_amt = fx_tran_amt;
	}

	public BigDecimal getRate_5_8() {
		return rate_5_8;
	}

	public void setRate_5_8(BigDecimal rate_5_8) {
		this.rate_5_8 = rate_5_8;
	}

	public BigDecimal getTran_amt() {
		return tran_amt;
	}

	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}

	public String getTran_particular() {
		return tran_particular;
	}

	public void setTran_particular(String tran_particular) {
		this.tran_particular = tran_particular;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getComments_services() {
		return comments_services;
	}

	public void setComments_services(String comments_services) {
		this.comments_services = comments_services;
	}

	public String getPreviously_to() {
		return previously_to;
	}

	public void setPreviously_to(String previously_to) {
		this.previously_to = previously_to;
	}

	public String getPresently_to() {
		return presently_to;
	}

	public void setPresently_to(String presently_to) {
		this.presently_to = presently_to;
	}

	public BigDecimal getEstimate_value() {
		return estimate_value;
	}

	public void setEstimate_value(BigDecimal estimate_value) {
		this.estimate_value = estimate_value;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public BigDecimal getDisposed_value() {
		return disposed_value;
	}

	public void setDisposed_value(BigDecimal disposed_value) {
		this.disposed_value = disposed_value;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity_5() {
		return city_5;
	}

	public void setCity_5(String city_5) {
		this.city_5 = city_5;
	}

	public String getName_persons_1() {
		return name_persons_1;
	}

	public void setName_persons_1(String name_persons_1) {
		this.name_persons_1 = name_persons_1;
	}

	public String getReporting_entity_1() {
		return reporting_entity_1;
	}

	public void setReporting_entity_1(String reporting_entity_1) {
		this.reporting_entity_1 = reporting_entity_1;
	}

	public String getCapacity_transaction_1() {
		return capacity_transaction_1;
	}

	public void setCapacity_transaction_1(String capacity_transaction_1) {
		this.capacity_transaction_1 = capacity_transaction_1;
	}

	public String getName_persons_2() {
		return name_persons_2;
	}

	public void setName_persons_2(String name_persons_2) {
		this.name_persons_2 = name_persons_2;
	}

	public String getReporting_entity_2() {
		return reporting_entity_2;
	}

	public void setReporting_entity_2(String reporting_entity_2) {
		this.reporting_entity_2 = reporting_entity_2;
	}

	public String getCapacity_transaction_2() {
		return capacity_transaction_2;
	}

	public void setCapacity_transaction_2(String capacity_transaction_2) {
		this.capacity_transaction_2 = capacity_transaction_2;
	}

	public String getName_persons_3() {
		return name_persons_3;
	}

	public void setName_persons_3(String name_persons_3) {
		this.name_persons_3 = name_persons_3;
	}

	public String getReporting_entity_3() {
		return reporting_entity_3;
	}

	public void setReporting_entity_3(String reporting_entity_3) {
		this.reporting_entity_3 = reporting_entity_3;
	}

	public String getCapacity_transaction_3() {
		return capacity_transaction_3;
	}

	public void setCapacity_transaction_3(String capacity_transaction_3) {
		this.capacity_transaction_3 = capacity_transaction_3;
	}

	public String getName_persons_4() {
		return name_persons_4;
	}

	public void setName_persons_4(String name_persons_4) {
		this.name_persons_4 = name_persons_4;
	}

	public String getReporting_entity_4() {
		return reporting_entity_4;
	}

	public void setReporting_entity_4(String reporting_entity_4) {
		this.reporting_entity_4 = reporting_entity_4;
	}

	public String getCapacity_transaction_4() {
		return capacity_transaction_4;
	}

	public void setCapacity_transaction_4(String capacity_transaction_4) {
		this.capacity_transaction_4 = capacity_transaction_4;
	}

	public String getName_persons_5() {
		return name_persons_5;
	}

	public void setName_persons_5(String name_persons_5) {
		this.name_persons_5 = name_persons_5;
	}

	public String getReporting_entity_5() {
		return reporting_entity_5;
	}

	public void setReporting_entity_5(String reporting_entity_5) {
		this.reporting_entity_5 = reporting_entity_5;
	}

	public String getCapacity_transaction_5() {
		return capacity_transaction_5;
	}

	public void setCapacity_transaction_5(String capacity_transaction_5) {
		this.capacity_transaction_5 = capacity_transaction_5;
	}

	public String getFrom_type() {
		return from_type;
	}

	public void setFrom_type(String from_type) {
		this.from_type = from_type;
	}

	public String getFrom_party() {
		return from_party;
	}

	public void setFrom_party(String from_party) {
		this.from_party = from_party;
	}

	public String getTo_type() {
		return to_type;
	}

	public void setTo_type(String to_type) {
		this.to_type = to_type;
	}

	public String getTo_party() {
		return to_party;
	}

	public void setTo_party(String to_party) {
		this.to_party = to_party;
	}

	public String getClient_account() {
		return client_account;
	}

	public void setClient_account(String client_account) {
		this.client_account = client_account;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getClient_person() {
		return client_person;
	}

	public void setClient_person(String client_person) {
		this.client_person = client_person;
	}

	public String getNon_client_account() {
		return non_client_account;
	}

	public void setNon_client_account(String non_client_account) {
		this.non_client_account = non_client_account;
	}

	public String getNon_type() {
		return non_type;
	}

	public void setNon_type(String non_type) {
		this.non_type = non_type;
	}

	public String getNon_person() {
		return non_person;
	}

	public void setNon_person(String non_person) {
		this.non_person = non_person;
	}

	public String getRef_num() {
		return ref_num;
	}

	public void setRef_num(String ref_num) {
		this.ref_num = ref_num;
	}

	public String getParty_code() {
		return party_code;
	}

	public void setParty_code(String party_code) {
		this.party_code = party_code;
	}

	public String getRole_6() {
		return role_6;
	}

	public void setRole_6(String role_6) {
		this.role_6 = role_6;
	}

	public String getFunds_type_6() {
		return funds_type_6;
	}

	public void setFunds_type_6(String funds_type_6) {
		this.funds_type_6 = funds_type_6;
	}

	public String getCountry_6() {
		return country_6;
	}

	public void setCountry_6(String country_6) {
		this.country_6 = country_6;
	}

	public String getForacid() {
		return foracid;
	}

	public void setForacid(String foracid) {
		this.foracid = foracid;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public String getAcct_name_1() {
		return acct_name_1;
	}

	public void setAcct_name_1(String acct_name_1) {
		this.acct_name_1 = acct_name_1;
	}

	public String getSchm_type() {
		return schm_type;
	}

	public void setSchm_type(String schm_type) {
		this.schm_type = schm_type;
	}

	public String getAcct_crncy_code() {
		return acct_crncy_code;
	}

	public void setAcct_crncy_code(String acct_crncy_code) {
		this.acct_crncy_code = acct_crncy_code;
	}

	public String getAcct_status() {
		return acct_status;
	}

	public void setAcct_status(String acct_status) {
		this.acct_status = acct_status;
	}

	public Date getAcct_opn_date() {
		return acct_opn_date;
	}

	public void setAcct_opn_date(Date acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
	}

	public Date getAcct_closed_date() {
		return acct_closed_date;
	}

	public void setAcct_closed_date(Date acct_closed_date) {
		this.acct_closed_date = acct_closed_date;
	}

	public BigDecimal getClr_bal_amt() {
		return clr_bal_amt;
	}

	public void setClr_bal_amt(BigDecimal clr_bal_amt) {
		this.clr_bal_amt = clr_bal_amt;
	}

	public Date getTran_date_7() {
		return tran_date_7;
	}

	public void setTran_date_7(Date tran_date_7) {
		this.tran_date_7 = tran_date_7;
	}

	public String getComments_details_6() {
		return comments_details_6;
	}

	public void setComments_details_6(String comments_details_6) {
		this.comments_details_6 = comments_details_6;
	}

	public String getTran_id_7() {
		return tran_id_7;
	}

	public void setTran_id_7(String tran_id_7) {
		this.tran_id_7 = tran_id_7;
	}

	public String getParty_code_7() {
		return party_code_7;
	}

	public void setParty_code_7(String party_code_7) {
		this.party_code_7 = party_code_7;
	}

	public String getRole_7() {
		return role_7;
	}

	public void setRole_7(String role_7) {
		this.role_7 = role_7;
	}

	public String getFunds_type_7() {
		return funds_type_7;
	}

	public void setFunds_type_7(String funds_type_7) {
		this.funds_type_7 = funds_type_7;
	}

	public String getCountry_7_4() {
		return country_7_4;
	}

	public void setCountry_7_4(String country_7_4) {
		this.country_7_4 = country_7_4;
	}

	public String getName_7() {
		return name_7;
	}

	public void setName_7(String name_7) {
		this.name_7 = name_7;
	}

	public String getIncorporation_form_7() {
		return incorporation_form_7;
	}

	public void setIncorporation_form_7(String incorporation_form_7) {
		this.incorporation_form_7 = incorporation_form_7;
	}

	public String getSector_7() {
		return sector_7;
	}

	public void setSector_7(String sector_7) {
		this.sector_7 = sector_7;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getAddr_7() {
		return addr_7;
	}

	public void setAddr_7(String addr_7) {
		this.addr_7 = addr_7;
	}

	public Date getDate_of_incorporation() {
		return date_of_incorporation;
	}

	public void setDate_of_incorporation(Date date_of_incorporation) {
		this.date_of_incorporation = date_of_incorporation;
	}

	public String getCountryofincorporation() {
		return countryofincorporation;
	}

	public void setCountryofincorporation(String countryofincorporation) {
		this.countryofincorporation = countryofincorporation;
	}

	public String getCity_7() {
		return city_7;
	}

	public void setCity_7(String city_7) {
		this.city_7 = city_7;
	}

	public String getCountry_7_13() {
		return country_7_13;
	}

	public void setCountry_7_13(String country_7_13) {
		this.country_7_13 = country_7_13;
	}

	public String getPhone_7() {
		return phone_7;
	}

	public void setPhone_7(String phone_7) {
		this.phone_7 = phone_7;
	}

	public String getComments_details_7() {
		return comments_details_7;
	}

	public void setComments_details_7(String comments_details_7) {
		this.comments_details_7 = comments_details_7;
	}

	public String getTran_id_8() {
		return tran_id_8;
	}

	public void setTran_id_8(String tran_id_8) {
		this.tran_id_8 = tran_id_8;
	}

	public String getParty_code_8() {
		return party_code_8;
	}

	public void setParty_code_8(String party_code_8) {
		this.party_code_8 = party_code_8;
	}

	public String getRole_8() {
		return role_8;
	}

	public void setRole_8(String role_8) {
		this.role_8 = role_8;
	}

	public String getFunds_type_8() {
		return funds_type_8;
	}

	public void setFunds_type_8(String funds_type_8) {
		this.funds_type_8 = funds_type_8;
	}

	public String getCountry_8_4() {
		return country_8_4;
	}

	public void setCountry_8_4(String country_8_4) {
		this.country_8_4 = country_8_4;
	}

	public String getGender_8() {
		return gender_8;
	}

	public void setGender_8(String gender_8) {
		this.gender_8 = gender_8;
	}

	public String getCust_first_name() {
		return cust_first_name;
	}

	public void setCust_first_name(String cust_first_name) {
		this.cust_first_name = cust_first_name;
	}

	public String getCust_last_name() {
		return cust_last_name;
	}

	public void setCust_last_name(String cust_last_name) {
		this.cust_last_name = cust_last_name;
	}

	public String getAddr_8() {
		return addr_8;
	}

	public void setAddr_8(String addr_8) {
		this.addr_8 = addr_8;
	}

	public String getCity_8() {
		return city_8;
	}

	public void setCity_8(String city_8) {
		this.city_8 = city_8;
	}

	public String getCountry_8_10() {
		return country_8_10;
	}

	public void setCountry_8_10(String country_8_10) {
		this.country_8_10 = country_8_10;
	}

	public String getPhone_8() {
		return phone_8;
	}

	public void setPhone_8(String phone_8) {
		this.phone_8 = phone_8;
	}

	public Date getDate_of_birth_8() {
		return date_of_birth_8;
	}

	public void setDate_of_birth_8(Date date_of_birth_8) {
		this.date_of_birth_8 = date_of_birth_8;
	}

	public String getNationality_8() {
		return nationality_8;
	}

	public void setNationality_8(String nationality_8) {
		this.nationality_8 = nationality_8;
	}

	public String getResidence_country() {
		return residence_country;
	}

	public void setResidence_country(String residence_country) {
		this.residence_country = residence_country;
	}

	public String getIdentifier_8() {
		return identifier_8;
	}

	public void setIdentifier_8(String identifier_8) {
		this.identifier_8 = identifier_8;
	}

	public String getInsider_relationship() {
		return insider_relationship;
	}

	public void setInsider_relationship(String insider_relationship) {
		this.insider_relationship = insider_relationship;
	}

	public Date getDate_of_suspension() {
		return date_of_suspension;
	}

	public void setDate_of_suspension(Date date_of_suspension) {
		this.date_of_suspension = date_of_suspension;
	}

	public String getIs_signatory() {
		return is_signatory;
	}

	public void setIs_signatory(String is_signatory) {
		this.is_signatory = is_signatory;
	}

	public String getPrimary_signatory_role() {
		return primary_signatory_role;
	}

	public void setPrimary_signatory_role(String primary_signatory_role) {
		this.primary_signatory_role = primary_signatory_role;
	}

	public String getRole_8_18() {
		return role_8_18;
	}

	public void setRole_8_18(String role_8_18) {
		this.role_8_18 = role_8_18;
	}

	public String getComments_details_8() {
		return comments_details_8;
	}

	public void setComments_details_8(String comments_details_8) {
		this.comments_details_8 = comments_details_8;
	}

	@Override
	public String toString() {
		return "AML_STR_ENTITY [report_id=" + report_id + ", report_type=" + report_type + ", entity_reference_no="
				+ entity_reference_no + ", fiu_reference_no=" + fiu_reference_no + ", submission_date="
				+ submission_date + ", business_type=" + business_type + ", details_for_others_1="
				+ details_for_others_1 + ", name_entity=" + name_entity + ", acronym=" + acronym + ", sector=" + sector
				+ ", first_name_2=" + first_name_2 + ", last_name_2=" + last_name_2 + ", date_of_birth_2="
				+ date_of_birth_2 + ", nic=" + nic + ", occupation_2=" + occupation_2 + ", street_address_2_2="
				+ street_address_2_2 + ", city_2_2=" + city_2_2 + ", country_2=" + country_2 + ", telephone_no_2="
				+ telephone_no_2 + ", fax_no_2=" + fax_no_2 + ", supervised_by=" + supervised_by + ", total_str="
				+ total_str + ", in_off1=" + in_off1 + ", in_off2=" + in_off2 + ", in_off3=" + in_off3 + ", in_off4="
				+ in_off4 + ", in_off5=" + in_off5 + ", in_off6=" + in_off6 + ", details_for_others_3="
				+ details_for_others_3 + ", description_taken_3_2=" + description_taken_3_2 + ", material_impact="
				+ material_impact + ", material_impact_desc=" + material_impact_desc + ", description_taken_3_3="
				+ description_taken_3_3 + ", tran_id=" + tran_id + ", tran_date=" + tran_date + ", tran_date_1="
				+ tran_date_1 + ", tran_type=" + tran_type + ", date_detected=" + date_detected + ", crncy_code="
				+ crncy_code + ", fx_tran_amt=" + fx_tran_amt + ", rate_5_8=" + rate_5_8 + ", tran_amt=" + tran_amt
				+ ", tran_particular=" + tran_particular + ", item_type=" + item_type + ", comments_services="
				+ comments_services + ", previously_to=" + previously_to + ", presently_to=" + presently_to
				+ ", estimate_value=" + estimate_value + ", status_code=" + status_code + ", disposed_value="
				+ disposed_value + ", street_address=" + street_address + ", city_5=" + city_5 + ", name_persons_1="
				+ name_persons_1 + ", reporting_entity_1=" + reporting_entity_1 + ", capacity_transaction_1="
				+ capacity_transaction_1 + ", name_persons_2=" + name_persons_2 + ", reporting_entity_2="
				+ reporting_entity_2 + ", capacity_transaction_2=" + capacity_transaction_2 + ", name_persons_3="
				+ name_persons_3 + ", reporting_entity_3=" + reporting_entity_3 + ", capacity_transaction_3="
				+ capacity_transaction_3 + ", name_persons_4=" + name_persons_4 + ", reporting_entity_4="
				+ reporting_entity_4 + ", capacity_transaction_4=" + capacity_transaction_4 + ", name_persons_5="
				+ name_persons_5 + ", reporting_entity_5=" + reporting_entity_5 + ", capacity_transaction_5="
				+ capacity_transaction_5 + ", from_type=" + from_type + ", from_party=" + from_party + ", to_type="
				+ to_type + ", to_party=" + to_party + ", client_account=" + client_account + ", client_type="
				+ client_type + ", client_person=" + client_person + ", non_client_account=" + non_client_account
				+ ", non_type=" + non_type + ", non_person=" + non_person + ", ref_num=" + ref_num + ", party_code="
				+ party_code + ", role_6=" + role_6 + ", funds_type_6=" + funds_type_6 + ", country_6=" + country_6
				+ ", foracid=" + foracid + ", acct_name=" + acct_name + ", acct_name_1=" + acct_name_1 + ", schm_type="
				+ schm_type + ", acct_crncy_code=" + acct_crncy_code + ", acct_status=" + acct_status
				+ ", acct_opn_date=" + acct_opn_date + ", acct_closed_date=" + acct_closed_date + ", clr_bal_amt="
				+ clr_bal_amt + ", tran_date_7=" + tran_date_7 + ", comments_details_6=" + comments_details_6
				+ ", tran_id_7=" + tran_id_7 + ", party_code_7=" + party_code_7 + ", role_7=" + role_7
				+ ", funds_type_7=" + funds_type_7 + ", country_7_4=" + country_7_4 + ", name_7=" + name_7
				+ ", incorporation_form_7=" + incorporation_form_7 + ", sector_7=" + sector_7 + ", registration_number="
				+ registration_number + ", addr_7=" + addr_7 + ", date_of_incorporation=" + date_of_incorporation
				+ ", countryofincorporation=" + countryofincorporation + ", city_7=" + city_7 + ", country_7_13="
				+ country_7_13 + ", phone_7=" + phone_7 + ", comments_details_7=" + comments_details_7 + ", tran_id_8="
				+ tran_id_8 + ", party_code_8=" + party_code_8 + ", role_8=" + role_8 + ", funds_type_8=" + funds_type_8
				+ ", country_8_4=" + country_8_4 + ", gender_8=" + gender_8 + ", cust_first_name=" + cust_first_name
				+ ", cust_last_name=" + cust_last_name + ", addr_8=" + addr_8 + ", city_8=" + city_8 + ", country_8_10="
				+ country_8_10 + ", phone_8=" + phone_8 + ", date_of_birth_8=" + date_of_birth_8 + ", nationality_8="
				+ nationality_8 + ", residence_country=" + residence_country + ", identifier_8=" + identifier_8
				+ ", insider_relationship=" + insider_relationship + ", date_of_suspension=" + date_of_suspension
				+ ", is_signatory=" + is_signatory + ", primary_signatory_role=" + primary_signatory_role
				+ ", role_8_18=" + role_8_18 + ", comments_details_8=" + comments_details_8 + ", getReport_id()="
				+ getReport_id() + ", getReport_type()=" + getReport_type() + ", getEntity_reference_no()="
				+ getEntity_reference_no() + ", getFiu_reference_no()=" + getFiu_reference_no()
				+ ", getSubmission_date()=" + getSubmission_date() + ", getBusiness_type()=" + getBusiness_type()
				+ ", getDetails_for_others_1()=" + getDetails_for_others_1() + ", getName_entity()=" + getName_entity()
				+ ", getAcronym()=" + getAcronym() + ", getSector()=" + getSector() + ", getFirst_name_2()="
				+ getFirst_name_2() + ", getLast_name_2()=" + getLast_name_2() + ", getDate_of_birth_2()="
				+ getDate_of_birth_2() + ", getNic()=" + getNic() + ", getOccupation_2()=" + getOccupation_2()
				+ ", getStreet_address_2_2()=" + getStreet_address_2_2() + ", getCity_2_2()=" + getCity_2_2()
				+ ", getCountry_2()=" + getCountry_2() + ", getTelephone_no_2()=" + getTelephone_no_2()
				+ ", getFax_no_2()=" + getFax_no_2() + ", getSupervised_by()=" + getSupervised_by()
				+ ", getTotal_str()=" + getTotal_str() + ", getIn_off1()=" + getIn_off1() + ", getIn_off2()="
				+ getIn_off2() + ", getIn_off3()=" + getIn_off3() + ", getIn_off4()=" + getIn_off4() + ", getIn_off5()="
				+ getIn_off5() + ", getIn_off6()=" + getIn_off6() + ", getDetails_for_others_3()="
				+ getDetails_for_others_3() + ", getDescription_taken_3_2()=" + getDescription_taken_3_2()
				+ ", getMaterial_impact()=" + getMaterial_impact() + ", getMaterial_impact_desc()="
				+ getMaterial_impact_desc() + ", getDescription_taken_3_3()=" + getDescription_taken_3_3()
				+ ", getTran_id()=" + getTran_id() + ", getTran_date()=" + getTran_date() + ", getTran_date_1()="
				+ getTran_date_1() + ", getTran_type()=" + getTran_type() + ", getDate_detected()=" + getDate_detected()
				+ ", getCrncy_code()=" + getCrncy_code() + ", getFx_tran_amt()=" + getFx_tran_amt() + ", getRate_5_8()="
				+ getRate_5_8() + ", getTran_amt()=" + getTran_amt() + ", getTran_particular()=" + getTran_particular()
				+ ", getItem_type()=" + getItem_type() + ", getComments_services()=" + getComments_services()
				+ ", getPreviously_to()=" + getPreviously_to() + ", getPresently_to()=" + getPresently_to()
				+ ", getEstimate_value()=" + getEstimate_value() + ", getStatus_code()=" + getStatus_code()
				+ ", getDisposed_value()=" + getDisposed_value() + ", getStreet_address()=" + getStreet_address()
				+ ", getCity_5()=" + getCity_5() + ", getName_persons_1()=" + getName_persons_1()
				+ ", getReporting_entity_1()=" + getReporting_entity_1() + ", getCapacity_transaction_1()="
				+ getCapacity_transaction_1() + ", getName_persons_2()=" + getName_persons_2()
				+ ", getReporting_entity_2()=" + getReporting_entity_2() + ", getCapacity_transaction_2()="
				+ getCapacity_transaction_2() + ", getName_persons_3()=" + getName_persons_3()
				+ ", getReporting_entity_3()=" + getReporting_entity_3() + ", getCapacity_transaction_3()="
				+ getCapacity_transaction_3() + ", getName_persons_4()=" + getName_persons_4()
				+ ", getReporting_entity_4()=" + getReporting_entity_4() + ", getCapacity_transaction_4()="
				+ getCapacity_transaction_4() + ", getName_persons_5()=" + getName_persons_5()
				+ ", getReporting_entity_5()=" + getReporting_entity_5() + ", getCapacity_transaction_5()="
				+ getCapacity_transaction_5() + ", getFrom_type()=" + getFrom_type() + ", getFrom_party()="
				+ getFrom_party() + ", getTo_type()=" + getTo_type() + ", getTo_party()=" + getTo_party()
				+ ", getClient_account()=" + getClient_account() + ", getClient_type()=" + getClient_type()
				+ ", getClient_person()=" + getClient_person() + ", getNon_client_account()=" + getNon_client_account()
				+ ", getNon_type()=" + getNon_type() + ", getNon_person()=" + getNon_person() + ", getRef_num()="
				+ getRef_num() + ", getParty_code()=" + getParty_code() + ", getRole_6()=" + getRole_6()
				+ ", getFunds_type_6()=" + getFunds_type_6() + ", getCountry_6()=" + getCountry_6() + ", getForacid()="
				+ getForacid() + ", getAcct_name()=" + getAcct_name() + ", getAcct_name_1()=" + getAcct_name_1()
				+ ", getSchm_type()=" + getSchm_type() + ", getAcct_crncy_code()=" + getAcct_crncy_code()
				+ ", getAcct_status()=" + getAcct_status() + ", getAcct_opn_date()=" + getAcct_opn_date()
				+ ", getAcct_closed_date()=" + getAcct_closed_date() + ", getClr_bal_amt()=" + getClr_bal_amt()
				+ ", getTran_date_7()=" + getTran_date_7() + ", getComments_details_6()=" + getComments_details_6()
				+ ", getTran_id_7()=" + getTran_id_7() + ", getParty_code_7()=" + getParty_code_7() + ", getRole_7()="
				+ getRole_7() + ", getFunds_type_7()=" + getFunds_type_7() + ", getCountry_7_4()=" + getCountry_7_4()
				+ ", getName_7()=" + getName_7() + ", getIncorporation_form_7()=" + getIncorporation_form_7()
				+ ", getSector_7()=" + getSector_7() + ", getRegistration_number()=" + getRegistration_number()
				+ ", getAddr_7()=" + getAddr_7() + ", getDate_of_incorporation()=" + getDate_of_incorporation()
				+ ", getCountryofincorporation()=" + getCountryofincorporation() + ", getCity_7()=" + getCity_7()
				+ ", getCountry_7_13()=" + getCountry_7_13() + ", getPhone_7()=" + getPhone_7()
				+ ", getComments_details_7()=" + getComments_details_7() + ", getTran_id_8()=" + getTran_id_8()
				+ ", getParty_code_8()=" + getParty_code_8() + ", getRole_8()=" + getRole_8() + ", getFunds_type_8()="
				+ getFunds_type_8() + ", getCountry_8_4()=" + getCountry_8_4() + ", getGender_8()=" + getGender_8()
				+ ", getCust_first_name()=" + getCust_first_name() + ", getCust_last_name()=" + getCust_last_name()
				+ ", getAddr_8()=" + getAddr_8() + ", getCity_8()=" + getCity_8() + ", getCountry_8_10()="
				+ getCountry_8_10() + ", getPhone_8()=" + getPhone_8() + ", getDate_of_birth_8()="
				+ getDate_of_birth_8() + ", getNationality_8()=" + getNationality_8() + ", getResidence_country()="
				+ getResidence_country() + ", getIdentifier_8()=" + getIdentifier_8() + ", getInsider_relationship()="
				+ getInsider_relationship() + ", getDate_of_suspension()=" + getDate_of_suspension()
				+ ", getIs_signatory()=" + getIs_signatory() + ", getPrimary_signatory_role()="
				+ getPrimary_signatory_role() + ", getRole_8_18()=" + getRole_8_18() + ", getComments_details_8()="
				+ getComments_details_8() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public AML_STR_ENTITY(String report_id, String report_type, String entity_reference_no, String fiu_reference_no,
			Date submission_date, String business_type, String details_for_others_1, String name_entity, String acronym,
			String sector, String first_name_2, String last_name_2, Date date_of_birth_2, String nic,
			String occupation_2, String street_address_2_2, String city_2_2, String country_2, String telephone_no_2,
			String fax_no_2, String supervised_by, BigDecimal total_str, String in_off1, String in_off2, String in_off3,
			String in_off4, String in_off5, String in_off6, String details_for_others_3, String description_taken_3_2,
			String material_impact, String material_impact_desc, String description_taken_3_3, String tran_id,
			Date tran_date, Date tran_date_1, String tran_type, Date date_detected, String crncy_code,
			BigDecimal fx_tran_amt, BigDecimal rate_5_8, BigDecimal tran_amt, String tran_particular, String item_type,
			String comments_services, String previously_to, String presently_to, BigDecimal estimate_value,
			String status_code, BigDecimal disposed_value, String street_address, String city_5, String name_persons_1,
			String reporting_entity_1, String capacity_transaction_1, String name_persons_2, String reporting_entity_2,
			String capacity_transaction_2, String name_persons_3, String reporting_entity_3,
			String capacity_transaction_3, String name_persons_4, String reporting_entity_4,
			String capacity_transaction_4, String name_persons_5, String reporting_entity_5,
			String capacity_transaction_5, String from_type, String from_party, String to_type, String to_party,
			String client_account, String client_type, String client_person, String non_client_account, String non_type,
			String non_person, String ref_num, String party_code, String role_6, String funds_type_6, String country_6,
			String foracid, String acct_name, String acct_name_1, String schm_type, String acct_crncy_code,
			String acct_status, Date acct_opn_date, Date acct_closed_date, BigDecimal clr_bal_amt, Date tran_date_7,
			String comments_details_6, String tran_id_7, String party_code_7, String role_7, String funds_type_7,
			String country_7_4, String name_7, String incorporation_form_7, String sector_7, String registration_number,
			String addr_7, Date date_of_incorporation, String countryofincorporation, String city_7,
			String country_7_13, String phone_7, String comments_details_7, String tran_id_8, String party_code_8,
			String role_8, String funds_type_8, String country_8_4, String gender_8, String cust_first_name,
			String cust_last_name, String addr_8, String city_8, String country_8_10, String phone_8,
			Date date_of_birth_8, String nationality_8, String residence_country, String identifier_8,
			String insider_relationship, Date date_of_suspension, String is_signatory, String primary_signatory_role,
			String role_8_18, String comments_details_8) {
		super();
		this.report_id = report_id;
		this.report_type = report_type;
		this.entity_reference_no = entity_reference_no;
		this.fiu_reference_no = fiu_reference_no;
		this.submission_date = submission_date;
		this.business_type = business_type;
		this.details_for_others_1 = details_for_others_1;
		this.name_entity = name_entity;
		this.acronym = acronym;
		this.sector = sector;
		this.first_name_2 = first_name_2;
		this.last_name_2 = last_name_2;
		this.date_of_birth_2 = date_of_birth_2;
		this.nic = nic;
		this.occupation_2 = occupation_2;
		this.street_address_2_2 = street_address_2_2;
		this.city_2_2 = city_2_2;
		this.country_2 = country_2;
		this.telephone_no_2 = telephone_no_2;
		this.fax_no_2 = fax_no_2;
		this.supervised_by = supervised_by;
		this.total_str = total_str;
		this.in_off1 = in_off1;
		this.in_off2 = in_off2;
		this.in_off3 = in_off3;
		this.in_off4 = in_off4;
		this.in_off5 = in_off5;
		this.in_off6 = in_off6;
		this.details_for_others_3 = details_for_others_3;
		this.description_taken_3_2 = description_taken_3_2;
		this.material_impact = material_impact;
		this.material_impact_desc = material_impact_desc;
		this.description_taken_3_3 = description_taken_3_3;
		this.tran_id = tran_id;
		this.tran_date = tran_date;
		this.tran_date_1 = tran_date_1;
		this.tran_type = tran_type;
		this.date_detected = date_detected;
		this.crncy_code = crncy_code;
		this.fx_tran_amt = fx_tran_amt;
		this.rate_5_8 = rate_5_8;
		this.tran_amt = tran_amt;
		this.tran_particular = tran_particular;
		this.item_type = item_type;
		this.comments_services = comments_services;
		this.previously_to = previously_to;
		this.presently_to = presently_to;
		this.estimate_value = estimate_value;
		this.status_code = status_code;
		this.disposed_value = disposed_value;
		this.street_address = street_address;
		this.city_5 = city_5;
		this.name_persons_1 = name_persons_1;
		this.reporting_entity_1 = reporting_entity_1;
		this.capacity_transaction_1 = capacity_transaction_1;
		this.name_persons_2 = name_persons_2;
		this.reporting_entity_2 = reporting_entity_2;
		this.capacity_transaction_2 = capacity_transaction_2;
		this.name_persons_3 = name_persons_3;
		this.reporting_entity_3 = reporting_entity_3;
		this.capacity_transaction_3 = capacity_transaction_3;
		this.name_persons_4 = name_persons_4;
		this.reporting_entity_4 = reporting_entity_4;
		this.capacity_transaction_4 = capacity_transaction_4;
		this.name_persons_5 = name_persons_5;
		this.reporting_entity_5 = reporting_entity_5;
		this.capacity_transaction_5 = capacity_transaction_5;
		this.from_type = from_type;
		this.from_party = from_party;
		this.to_type = to_type;
		this.to_party = to_party;
		this.client_account = client_account;
		this.client_type = client_type;
		this.client_person = client_person;
		this.non_client_account = non_client_account;
		this.non_type = non_type;
		this.non_person = non_person;
		this.ref_num = ref_num;
		this.party_code = party_code;
		this.role_6 = role_6;
		this.funds_type_6 = funds_type_6;
		this.country_6 = country_6;
		this.foracid = foracid;
		this.acct_name = acct_name;
		this.acct_name_1 = acct_name_1;
		this.schm_type = schm_type;
		this.acct_crncy_code = acct_crncy_code;
		this.acct_status = acct_status;
		this.acct_opn_date = acct_opn_date;
		this.acct_closed_date = acct_closed_date;
		this.clr_bal_amt = clr_bal_amt;
		this.tran_date_7 = tran_date_7;
		this.comments_details_6 = comments_details_6;
		this.tran_id_7 = tran_id_7;
		this.party_code_7 = party_code_7;
		this.role_7 = role_7;
		this.funds_type_7 = funds_type_7;
		this.country_7_4 = country_7_4;
		this.name_7 = name_7;
		this.incorporation_form_7 = incorporation_form_7;
		this.sector_7 = sector_7;
		this.registration_number = registration_number;
		this.addr_7 = addr_7;
		this.date_of_incorporation = date_of_incorporation;
		this.countryofincorporation = countryofincorporation;
		this.city_7 = city_7;
		this.country_7_13 = country_7_13;
		this.phone_7 = phone_7;
		this.comments_details_7 = comments_details_7;
		this.tran_id_8 = tran_id_8;
		this.party_code_8 = party_code_8;
		this.role_8 = role_8;
		this.funds_type_8 = funds_type_8;
		this.country_8_4 = country_8_4;
		this.gender_8 = gender_8;
		this.cust_first_name = cust_first_name;
		this.cust_last_name = cust_last_name;
		this.addr_8 = addr_8;
		this.city_8 = city_8;
		this.country_8_10 = country_8_10;
		this.phone_8 = phone_8;
		this.date_of_birth_8 = date_of_birth_8;
		this.nationality_8 = nationality_8;
		this.residence_country = residence_country;
		this.identifier_8 = identifier_8;
		this.insider_relationship = insider_relationship;
		this.date_of_suspension = date_of_suspension;
		this.is_signatory = is_signatory;
		this.primary_signatory_role = primary_signatory_role;
		this.role_8_18 = role_8_18;
		this.comments_details_8 = comments_details_8;
	}

	public AML_STR_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhone_9() {
		return phone_9;
	}

	public void setPhone_9(String phone_9) {
		this.phone_9 = phone_9;
	}

}

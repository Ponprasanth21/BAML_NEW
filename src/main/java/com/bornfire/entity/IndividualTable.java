package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_UNSC_INDIVIDUAL_TABLE")
public class IndividualTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	private String dataid;
	private String versionnum;
	private String first_name;
	private String second_name;
	private String third_name;
	private String un_list_type;
	private String reference_number;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date listed_on;
	private String comments1;
	private String designation_value;
	private String nationality_value;
	private String list_type_value;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date last_day_updated_date;
	private String individual_alias_quality;
	private String individual_alias_alias_name;
	private String individual_alias_note;
	private String individual_address_street;
	private String individual_address_state;
	private String individual_address_city;
	private String individual_address_state_province;
	private String individual_address_country;
	private String individual_address_note;
	private String individual_date_of_birth_type_of_date;
	private String individual_date_of_birth_year;
	private String individual_date_of_birth_from_year;
	private String individual_date_of_birth_to_year;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date individual_date_of_birth_date;
	private String individual_place_of_birth_city;
	private String individual_place_of_birth_state_province;
	private String individual_place_of_birth_country;
	private String individual_place_of_birth_note;
	private String individual_document_type_of_document1;
	private String individual_document_type_of_document2;
	private Date individual_document_date_of_issue;
	private String individual_document_num;
	private String individual_document_note;
	private String individual_document_issuing_country;
	private String individual_document_country_of_issue;
	private String individual_document_city_of_issue;
	private String sort_key;
	private String sort_key_last_mod;

	private String name_original_script;
	private String title_value;
	
	private String entity_flg;
	private String modify_flg;
	private String del_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date entry_time;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modify_time;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date verify_time;
	private String individual_alias_low_name;
	private String individual_alias_good_name;
	private String full_name;
	
	
	

	
	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDataid() {
		return dataid;
	}

	public String getVersionnum() {
		return versionnum;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public String getThird_name() {
		return third_name;
	}

	public String getUn_list_type() {
		return un_list_type;
	}

	public String getReference_number() {
		return reference_number;
	}

	public Date getListed_on() {
		return listed_on;
	}

	public String getComments1() {
		return comments1;
	}

	public String getDesignation_value() {
		return designation_value;
	}

	public String getNationality_value() {
		return nationality_value;
	}

	public String getList_type_value() {
		return list_type_value;
	}

	public Date getLast_day_updated_date() {
		return last_day_updated_date;
	}

	public String getIndividual_alias_quality() {
		return individual_alias_quality;
	}

	public String getIndividual_alias_alias_name() {
		return individual_alias_alias_name;
	}

	public String getIndividual_alias_note() {
		return individual_alias_note;
	}

	public String getIndividual_address_street() {
		return individual_address_street;
	}

	public String getIndividual_address_state() {
		return individual_address_state;
	}

	public String getIndividual_address_city() {
		return individual_address_city;
	}

	public String getIndividual_address_state_province() {
		return individual_address_state_province;
	}

	public String getIndividual_address_country() {
		return individual_address_country;
	}

	public String getIndividual_address_note() {
		return individual_address_note;
	}

	public String getIndividual_date_of_birth_type_of_date() {
		return individual_date_of_birth_type_of_date;
	}

	public String getIndividual_date_of_birth_year() {
		return individual_date_of_birth_year;
	}

	public String getIndividual_date_of_birth_from_year() {
		return individual_date_of_birth_from_year;
	}

	public String getIndividual_date_of_birth_to_year() {
		return individual_date_of_birth_to_year;
	}

	public Date getIndividual_date_of_birth_date() {
		return individual_date_of_birth_date;
	}

	public String getIndividual_place_of_birth_city() {
		return individual_place_of_birth_city;
	}

	public String getIndividual_place_of_birth_state_province() {
		return individual_place_of_birth_state_province;
	}

	public String getIndividual_place_of_birth_country() {
		return individual_place_of_birth_country;
	}

	public String getIndividual_place_of_birth_note() {
		return individual_place_of_birth_note;
	}

	public String getIndividual_document_type_of_document1() {
		return individual_document_type_of_document1;
	}

	public String getIndividual_document_type_of_document2() {
		return individual_document_type_of_document2;
	}

	public Date getIndividual_document_date_of_issue() {
		return individual_document_date_of_issue;
	}

	public String getIndividual_document_num() {
		return individual_document_num;
	}

	public String getIndividual_document_note() {
		return individual_document_note;
	}

	public String getIndividual_document_issuing_country() {
		return individual_document_issuing_country;
	}

	public String getIndividual_document_country_of_issue() {
		return individual_document_country_of_issue;
	}

	public String getIndividual_document_city_of_issue() {
		return individual_document_city_of_issue;
	}

	public String getSort_key() {
		return sort_key;
	}

	public String getSort_key_last_mod() {
		return sort_key_last_mod;
	}

	public String getName_original_script() {
		return name_original_script;
	}

	public String getTitle_value() {
		return title_value;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public String getDel_flg() {
		return del_flg;
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

	public String getIndividual_alias_low_name() {
		return individual_alias_low_name;
	}

	public String getIndividual_alias_good_name() {
		return individual_alias_good_name;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public void setVersionnum(String versionnum) {
		this.versionnum = versionnum;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public void setThird_name(String third_name) {
		this.third_name = third_name;
	}

	public void setUn_list_type(String un_list_type) {
		this.un_list_type = un_list_type;
	}

	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}

	public void setListed_on(Date listed_on) {
		this.listed_on = listed_on;
	}

	public void setComments1(String comments1) {
		this.comments1 = comments1;
	}

	public void setDesignation_value(String designation_value) {
		this.designation_value = designation_value;
	}

	public void setNationality_value(String nationality_value) {
		this.nationality_value = nationality_value;
	}

	public void setList_type_value(String list_type_value) {
		this.list_type_value = list_type_value;
	}

	public void setLast_day_updated_date(Date last_day_updated_date) {
		this.last_day_updated_date = last_day_updated_date;
	}

	public void setIndividual_alias_quality(String individual_alias_quality) {
		this.individual_alias_quality = individual_alias_quality;
	}

	public void setIndividual_alias_alias_name(String individual_alias_alias_name) {
		this.individual_alias_alias_name = individual_alias_alias_name;
	}

	public void setIndividual_alias_note(String individual_alias_note) {
		this.individual_alias_note = individual_alias_note;
	}

	public void setIndividual_address_street(String individual_address_street) {
		this.individual_address_street = individual_address_street;
	}

	public void setIndividual_address_state(String individual_address_state) {
		this.individual_address_state = individual_address_state;
	}

	public void setIndividual_address_city(String individual_address_city) {
		this.individual_address_city = individual_address_city;
	}

	public void setIndividual_address_state_province(String individual_address_state_province) {
		this.individual_address_state_province = individual_address_state_province;
	}

	public void setIndividual_address_country(String individual_address_country) {
		this.individual_address_country = individual_address_country;
	}

	public void setIndividual_address_note(String individual_address_note) {
		this.individual_address_note = individual_address_note;
	}

	public void setIndividual_date_of_birth_type_of_date(String individual_date_of_birth_type_of_date) {
		this.individual_date_of_birth_type_of_date = individual_date_of_birth_type_of_date;
	}

	public void setIndividual_date_of_birth_year(String individual_date_of_birth_year) {
		this.individual_date_of_birth_year = individual_date_of_birth_year;
	}

	public void setIndividual_date_of_birth_from_year(String individual_date_of_birth_from_year) {
		this.individual_date_of_birth_from_year = individual_date_of_birth_from_year;
	}

	public void setIndividual_date_of_birth_to_year(String individual_date_of_birth_to_year) {
		this.individual_date_of_birth_to_year = individual_date_of_birth_to_year;
	}

	public void setIndividual_date_of_birth_date(Date individual_date_of_birth_date) {
		this.individual_date_of_birth_date = individual_date_of_birth_date;
	}

	public void setIndividual_place_of_birth_city(String individual_place_of_birth_city) {
		this.individual_place_of_birth_city = individual_place_of_birth_city;
	}

	public void setIndividual_place_of_birth_state_province(String individual_place_of_birth_state_province) {
		this.individual_place_of_birth_state_province = individual_place_of_birth_state_province;
	}

	public void setIndividual_place_of_birth_country(String individual_place_of_birth_country) {
		this.individual_place_of_birth_country = individual_place_of_birth_country;
	}

	public void setIndividual_place_of_birth_note(String individual_place_of_birth_note) {
		this.individual_place_of_birth_note = individual_place_of_birth_note;
	}

	public void setIndividual_document_type_of_document1(String individual_document_type_of_document1) {
		this.individual_document_type_of_document1 = individual_document_type_of_document1;
	}

	public void setIndividual_document_type_of_document2(String individual_document_type_of_document2) {
		this.individual_document_type_of_document2 = individual_document_type_of_document2;
	}

	public void setIndividual_document_date_of_issue(Date individual_document_date_of_issue) {
		this.individual_document_date_of_issue = individual_document_date_of_issue;
	}

	public void setIndividual_document_num(String individual_document_num) {
		this.individual_document_num = individual_document_num;
	}

	public void setIndividual_document_note(String individual_document_note) {
		this.individual_document_note = individual_document_note;
	}

	public void setIndividual_document_issuing_country(String individual_document_issuing_country) {
		this.individual_document_issuing_country = individual_document_issuing_country;
	}

	public void setIndividual_document_country_of_issue(String individual_document_country_of_issue) {
		this.individual_document_country_of_issue = individual_document_country_of_issue;
	}

	public void setIndividual_document_city_of_issue(String individual_document_city_of_issue) {
		this.individual_document_city_of_issue = individual_document_city_of_issue;
	}

	public void setSort_key(String sort_key) {
		this.sort_key = sort_key;
	}

	public void setSort_key_last_mod(String sort_key_last_mod) {
		this.sort_key_last_mod = sort_key_last_mod;
	}

	public void setName_original_script(String name_original_script) {
		this.name_original_script = name_original_script;
	}

	public void setTitle_value(String title_value) {
		this.title_value = title_value;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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

	public void setIndividual_alias_low_name(String individual_alias_low_name) {
		this.individual_alias_low_name = individual_alias_low_name;
	}

	public void setIndividual_alias_good_name(String individual_alias_good_name) {
		this.individual_alias_good_name = individual_alias_good_name;
	}

	public IndividualTable(String dataid, String versionnum, String first_name, String second_name,
			String third_name, String un_list_type, String reference_number, Date listed_on,
			String name_original_script, String comments1, String designation_value, String title_value,
			String nationality_value, String list_type_value, Date last_day_updated_date,
			String individual_alias_quality, String individual_alias_alias_name, String individual_alias_note,
			String individual_address_street, String individual_address_state, String individual_address_city,
			String individual_address_state_province, String individual_address_country,
			String individual_address_note, String individual_date_of_birth_type_of_date,
			String individual_date_of_birth_year, String individual_date_of_birth_from_year,
			String individual_date_of_birth_to_year, Date individual_date_of_birth_date,
			String individual_place_of_birth_city, String individual_place_of_birth_state_province,
			String individual_place_of_birth_country, String individual_place_of_birth_note,
			String individual_document_type_of_document1, String individual_document_type_of_document2,
			Date individual_document_date_of_issue, String individual_document_num, String individual_document_note,
			String individual_document_issuing_country, String individual_document_country_of_issue,
			String individual_document_city_of_issue, String sort_key, String sort_key_last_mod,String individual_alias_low_name,
			String individual_alias_good_name, String full_name,Date entry_time) {
		super();
		this.dataid = dataid;
		this.versionnum = versionnum;
		this.first_name = first_name;
		this.second_name = second_name;
		this.third_name = third_name;
		this.un_list_type = un_list_type;
		this.reference_number = reference_number;
		this.listed_on = listed_on;
		this.name_original_script = name_original_script;
		this.comments1 = comments1;
		this.designation_value = designation_value;
		this.title_value = title_value;
		this.nationality_value = nationality_value;
		this.list_type_value = list_type_value;
		this.last_day_updated_date = last_day_updated_date;
		this.individual_alias_quality = individual_alias_quality;
		this.individual_alias_alias_name = individual_alias_alias_name;
		this.individual_alias_note = individual_alias_note;
		this.individual_address_street = individual_address_street;
		this.individual_address_state = individual_address_state;
		this.individual_address_city = individual_address_city;
		this.individual_address_state_province = individual_address_state_province;
		this.individual_address_country = individual_address_country;
		this.individual_address_note = individual_address_note;
		this.individual_date_of_birth_type_of_date = individual_date_of_birth_type_of_date;
		this.individual_date_of_birth_year = individual_date_of_birth_year;
		this.individual_date_of_birth_from_year = individual_date_of_birth_from_year;
		this.individual_date_of_birth_to_year = individual_date_of_birth_to_year;
		this.individual_date_of_birth_date = individual_date_of_birth_date;
		this.individual_place_of_birth_city = individual_place_of_birth_city;
		this.individual_place_of_birth_state_province = individual_place_of_birth_state_province;
		this.individual_place_of_birth_country = individual_place_of_birth_country;
		this.individual_document_type_of_document1 = individual_document_type_of_document1;
		this.individual_document_type_of_document2 = individual_document_type_of_document2;
		this.individual_document_date_of_issue = individual_document_date_of_issue;
		this.individual_document_num = individual_document_num;
		this.individual_document_note = individual_document_note;
		this.individual_document_issuing_country = individual_document_issuing_country;
		this.individual_document_country_of_issue = individual_document_country_of_issue;
		this.individual_document_city_of_issue = individual_document_city_of_issue;
		this.sort_key = sort_key;
		this.sort_key_last_mod = sort_key_last_mod;
		this.individual_alias_low_name =individual_alias_low_name;
		this.individual_alias_good_name = individual_alias_good_name;
		this.full_name= full_name;
		this.entry_time=entry_time;
		

	}

	public IndividualTable() {
		// TODO Auto-generated constructor stub
	}

	
}
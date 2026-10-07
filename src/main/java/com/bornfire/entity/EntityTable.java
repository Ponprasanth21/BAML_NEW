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
@Table(name="BAML_UNSC_ENTITY_TABLE")
public class EntityTable implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Id
	private String	dataid;
	private String	versionnum;
	private String	first_name;
	private String	second_name;
	private String	third_name;
	private String	un_list_type;
	private String	reference_number;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	listed_on;
	private String	comments1;
	private String name_original_script;
	private String	nationality_value;
	private String	list_type_value;
	private String	last_day_updated_date;
	private String	entity_alias_quality;
	private String	entity_alias_name;
	private String	entity_alias_note;
	private String	entity_address_street;
	private String	entity_address_city;
	private String	entity_address_state_province;
	private String	entity_address_country;
	private String	entity_address_note;
	private String	entity_date_of_birth_type_of_date;
	private String	entity_date_of_birth_year;
	private String	entity_date_of_birth_from_year;
	private String	entity_date_of_birth_to_year;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	entity_date_of_birth_date;
	private String	entity_date_of_birth_city;
	private String	entity_date_of_birth_state_province;
	private String	entity_date_of_birth_country;
	private String	entity_date_of_birth_note;
	private String	entity_document_type_of_document1;
	private String	entity_document_type_of_document2;
	//@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	entity_document_date_of_issue;
	private String	entity_document_num;
	private String	entity_document_note;
	private String	entity_document_issuing_country;
	private String	entity_document_country_of_issue;
	private String	entity_document_city_of_issue;
	private String	sort_key;
	private String	sort_key_last_mod;
	private String	entity_place_of_birth;
	private String	entity_place_of_birth_city;
	private String	entity_place_of_birth_state_province;
	private String	entity_place_of_birth_country;
	private String	entity_place_of_birth_note;
	private String	entity_address_state;
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
	private String entity_alias_low_name;
	private String entity_alias_good_name;
	
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
	
	public String getName_original_script() {
		return name_original_script;
	}
	public void setName_original_script(String name_original_script) {
		this.name_original_script = name_original_script;
	}
	public String getNationality_value() {
		return nationality_value;
	}
	public String getList_type_value() {
		return list_type_value;
	}
	public String getLast_day_updated_date() {
		return last_day_updated_date;
	}
	public String getEntity_alias_quality() {
		return entity_alias_quality;
	}
	
	public String getEntity_alias_note() {
		return entity_alias_note;
	}
	public String getEntity_address_street() {
		return entity_address_street;
	}
	public String getEntity_address_city() {
		return entity_address_city;
	}
	public String getEntity_address_state_province() {
		return entity_address_state_province;
	}
	public String getEntity_address_country() {
		return entity_address_country;
	}
	public String getEntity_address_note() {
		return entity_address_note;
	}
	public String getEntity_date_of_birth_type_of_date() {
		return entity_date_of_birth_type_of_date;
	}
	public String getEntity_date_of_birth_year() {
		return entity_date_of_birth_year;
	}
	public String getEntity_date_of_birth_from_year() {
		return entity_date_of_birth_from_year;
	}
	public String getEntity_date_of_birth_to_year() {
		return entity_date_of_birth_to_year;
	}
	public Date getEntity_date_of_birth_date() {
		return entity_date_of_birth_date;
	}
	public String getEntity_date_of_birth_city() {
		return entity_date_of_birth_city;
	}
	public String getEntity_date_of_birth_state_province() {
		return entity_date_of_birth_state_province;
	}
	public String getEntity_date_of_birth_country() {
		return entity_date_of_birth_country;
	}
	public String getEntity_date_of_birth_note() {
		return entity_date_of_birth_note;
	}
	public String getEntity_document_type_of_document1() {
		return entity_document_type_of_document1;
	}
	public String getEntity_document_type_of_document2() {
		return entity_document_type_of_document2;
	}
	public Date getEntity_document_date_of_issue() {
		return entity_document_date_of_issue;
	}
	public String getEntity_document_num() {
		return entity_document_num;
	}
	public String getEntity_document_note() {
		return entity_document_note;
	}
	public String getEntity_document_issuing_country() {
		return entity_document_issuing_country;
	}
	public String getEntity_document_country_of_issue() {
		return entity_document_country_of_issue;
	}
	public String getEntity_document_city_of_issue() {
		return entity_document_city_of_issue;
	}
	public String getSort_key() {
		return sort_key;
	}
	public String getSort_key_last_mod() {
		return sort_key_last_mod;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getEntity_place_of_birth() {
		return entity_place_of_birth;
	}
	public String getEntity_place_of_birth_city() {
		return entity_place_of_birth_city;
	}
	public String getEntity_place_of_birth_state_province() {
		return entity_place_of_birth_state_province;
	}
	public String getEntity_place_of_birth_country() {
		return entity_place_of_birth_country;
	}
	public String getEntity_place_of_birth_note() {
		return entity_place_of_birth_note;
	}
	public String getEntity_address_state() {
		return entity_address_state;
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
	public void setNationality_value(String nationality_value) {
		this.nationality_value = nationality_value;
	}
	public void setList_type_value(String list_type_value) {
		this.list_type_value = list_type_value;
	}
	public void setLast_day_updated_date(String last_day_updated_date) {
		this.last_day_updated_date = last_day_updated_date;
	}
	public void setEntity_alias_quality(String entity_alias_quality) {
		this.entity_alias_quality = entity_alias_quality;
	}
	
	public void setEntity_alias_note(String entity_alias_note) {
		this.entity_alias_note = entity_alias_note;
	}
	public void setEntity_address_street(String entity_address_street) {
		this.entity_address_street = entity_address_street;
	}
	public void setEntity_address_city(String entity_address_city) {
		this.entity_address_city = entity_address_city;
	}
	public void setEntity_address_state_province(String entity_address_state_province) {
		this.entity_address_state_province = entity_address_state_province;
	}
	public void setEntity_address_country(String entity_address_country) {
		this.entity_address_country = entity_address_country;
	}
	public void setEntity_address_note(String entity_address_note) {
		this.entity_address_note = entity_address_note;
	}
	public void setEntity_date_of_birth_type_of_date(String entity_date_of_birth_type_of_date) {
		this.entity_date_of_birth_type_of_date = entity_date_of_birth_type_of_date;
	}
	public void setEntity_date_of_birth_year(String entity_date_of_birth_year) {
		this.entity_date_of_birth_year = entity_date_of_birth_year;
	}
	public void setEntity_date_of_birth_from_year(String entity_date_of_birth_from_year) {
		this.entity_date_of_birth_from_year = entity_date_of_birth_from_year;
	}
	public void setEntity_date_of_birth_to_year(String entity_date_of_birth_to_year) {
		this.entity_date_of_birth_to_year = entity_date_of_birth_to_year;
	}
	public void setEntity_date_of_birth_date(Date entity_date_of_birth_date) {
		this.entity_date_of_birth_date = entity_date_of_birth_date;
	}
	public void setEntity_date_of_birth_city(String entity_date_of_birth_city) {
		this.entity_date_of_birth_city = entity_date_of_birth_city;
	}
	public void setEntity_date_of_birth_state_province(String entity_date_of_birth_state_province) {
		this.entity_date_of_birth_state_province = entity_date_of_birth_state_province;
	}
	public void setEntity_date_of_birth_country(String entity_date_of_birth_country) {
		this.entity_date_of_birth_country = entity_date_of_birth_country;
	}
	public void setEntity_date_of_birth_note(String entity_date_of_birth_note) {
		this.entity_date_of_birth_note = entity_date_of_birth_note;
	}
	public void setEntity_document_type_of_document1(String entity_document_type_of_document1) {
		this.entity_document_type_of_document1 = entity_document_type_of_document1;
	}
	public void setEntity_document_type_of_document2(String entity_document_type_of_document2) {
		this.entity_document_type_of_document2 = entity_document_type_of_document2;
	}
	public void setEntity_document_date_of_issue(Date entity_document_date_of_issue) {
		this.entity_document_date_of_issue = entity_document_date_of_issue;
	}
	public void setEntity_document_num(String entity_document_num) {
		this.entity_document_num = entity_document_num;
	}
	public void setEntity_document_note(String entity_document_note) {
		this.entity_document_note = entity_document_note;
	}
	public void setEntity_document_issuing_country(String entity_document_issuing_country) {
		this.entity_document_issuing_country = entity_document_issuing_country;
	}
	public void setEntity_document_country_of_issue(String entity_document_country_of_issue) {
		this.entity_document_country_of_issue = entity_document_country_of_issue;
	}
	public void setEntity_document_city_of_issue(String entity_document_city_of_issue) {
		this.entity_document_city_of_issue = entity_document_city_of_issue;
	}
	public void setSort_key(String sort_key) {
		this.sort_key = sort_key;
	}
	public void setSort_key_last_mod(String sort_key_last_mod) {
		this.sort_key_last_mod = sort_key_last_mod;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setEntity_place_of_birth(String entity_place_of_birth) {
		this.entity_place_of_birth = entity_place_of_birth;
	}
	public void setEntity_place_of_birth_city(String entity_place_of_birth_city) {
		this.entity_place_of_birth_city = entity_place_of_birth_city;
	}
	public void setEntity_place_of_birth_state_province(String entity_place_of_birth_state_province) {
		this.entity_place_of_birth_state_province = entity_place_of_birth_state_province;
	}
	public void setEntity_place_of_birth_country(String entity_place_of_birth_country) {
		this.entity_place_of_birth_country = entity_place_of_birth_country;
	}
	public void setEntity_place_of_birth_note(String entity_place_of_birth_note) {
		this.entity_place_of_birth_note = entity_place_of_birth_note;
	}
	public void setEntity_address_state(String entity_address_state) {
		this.entity_address_state = entity_address_state;
	}
	
	
	
	
	
	public String getEntity_alias_low_name() {
		return entity_alias_low_name;
	}
	public String getEntity_alias_good_name() {
		return entity_alias_good_name;
	}
	public void setEntity_alias_low_name(String entity_alias_low_name) {
		this.entity_alias_low_name = entity_alias_low_name;
	}
	public void setEntity_alias_good_name(String entity_alias_good_name) {
		this.entity_alias_good_name = entity_alias_good_name;
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
	public String getEntity_alias_name() {
		return entity_alias_name;
	}
	public void setEntity_alias_name(String entity_alias_name) {
		this.entity_alias_name = entity_alias_name;
	}
	public EntityTable(String dataid, String versionnum, String first_name, String un_list_type,
			String reference_number, Date listed_on,String name_original_script, String comments1, String list_type_value,
			String last_day_updated_date, String entity_alias_quality, String entity_alias_name,
			String entity_alias_note, String entity_address_street, String entity_address_city,
			String entity_address_state_province, String entity_address_country, String entity_address_note,
			String sort_key, String sort_key_last_mod,String entity_alias_low_name,String entity_alias_good_name,Date entry_time) {
	
		super();
		this.dataid = dataid;
		this.versionnum = versionnum;
		this.first_name = first_name;
		this.un_list_type = un_list_type;
		this.reference_number = reference_number;
		this.listed_on = listed_on;
		this.name_original_script = name_original_script;
		this.comments1 = comments1;
		this.list_type_value = list_type_value;
		this.last_day_updated_date = last_day_updated_date;
		this.entity_alias_quality = entity_alias_quality;
		this.entity_alias_name = entity_alias_name ;
		this.entity_alias_note = entity_alias_note;
		this.entity_address_street = entity_address_street;
		this.entity_address_city = entity_address_city;
		this.entity_address_state_province = entity_address_state_province;
		this.entity_address_country = entity_address_country;
		this.entity_address_note = entity_address_note;
		this.sort_key = sort_key;
		this.sort_key_last_mod = sort_key_last_mod;
		this.entity_alias_low_name = entity_alias_low_name;
		this.entity_alias_good_name = entity_alias_good_name;
		this.entry_time = entry_time;
		
	}
	
	
	
	@Override
	public String toString() {
		return "EntityTable [dataid=" + dataid + ", versionnum=" + versionnum + ", first_name=" + first_name
				+ ", second_name=" + second_name + ", third_name=" + third_name + ", un_list_type=" + un_list_type
				+ ", reference_number=" + reference_number + ", listed_on=" + listed_on + ", comments1=" + comments1
				+ ", name_original_script=" + name_original_script + ", nationality_value=" + nationality_value
				+ ", list_type_value=" + list_type_value + ", last_day_updated_date=" + last_day_updated_date
				+ ", entity_alias_quality=" + entity_alias_quality + ", entity_alias_name=" + entity_alias_name
				+ ", entity_alias_note=" + entity_alias_note + ", entity_address_street=" + entity_address_street
				+ ", entity_address_city=" + entity_address_city + ", entity_address_state_province="
				+ entity_address_state_province + ", entity_address_country=" + entity_address_country
				+ ", entity_address_note=" + entity_address_note + ", entity_date_of_birth_type_of_date="
				+ entity_date_of_birth_type_of_date + ", entity_date_of_birth_year=" + entity_date_of_birth_year
				+ ", entity_date_of_birth_from_year=" + entity_date_of_birth_from_year
				+ ", entity_date_of_birth_to_year=" + entity_date_of_birth_to_year + ", entity_date_of_birth_date="
				+ entity_date_of_birth_date + ", entity_date_of_birth_city=" + entity_date_of_birth_city
				+ ", entity_date_of_birth_state_province=" + entity_date_of_birth_state_province
				+ ", entity_date_of_birth_country=" + entity_date_of_birth_country + ", entity_date_of_birth_note="
				+ entity_date_of_birth_note + ", entity_document_type_of_document1=" + entity_document_type_of_document1
				+ ", entity_document_type_of_document2=" + entity_document_type_of_document2
				+ ", entity_document_date_of_issue=" + entity_document_date_of_issue + ", entity_document_num="
				+ entity_document_num + ", entity_document_note=" + entity_document_note
				+ ", entity_document_issuing_country=" + entity_document_issuing_country
				+ ", entity_document_country_of_issue=" + entity_document_country_of_issue
				+ ", entity_document_city_of_issue=" + entity_document_city_of_issue + ", sort_key=" + sort_key
				+ ", sort_key_last_mod=" + sort_key_last_mod + ", entity_flg=" + entity_flg + ", entity_place_of_birth="
				+ entity_place_of_birth + ", entity_place_of_birth_city=" + entity_place_of_birth_city
				+ ", entity_place_of_birth_state_province=" + entity_place_of_birth_state_province
				+ ", entity_place_of_birth_country=" + entity_place_of_birth_country + ", entity_place_of_birth_note="
				+ entity_place_of_birth_note + ", entity_address_state=" + entity_address_state + "]";
	}
	public EntityTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
		
	

	
	
	
	

}
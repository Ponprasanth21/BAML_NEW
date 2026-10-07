package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="BAML_CUST_BLACK_LIST_COR")
public class Cust_Black_List_Corp_Entity implements Serializable{
	public Cust_Black_List_Corp_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private BigDecimal	black_list_corp_data_setid;

	private String	bank_id;
	
	private String	dummy_1;
	private String	idtyper1;
	private String	idtyper2;
	private String	idtyper3;
	private String	idtyper4;
	private String	idtyper5;
	private String	id_type_r6;
	private String	id_type_r7;
	private String	id_type_r8;
	private String	id_type_r9;
	private String	id_type_r10;
	private String	firstname_alt1;
	private String	preferredformat;
	private String	dummy_2;
	private String	dummy_3;
	private String	shortname_alt1;
	private BigDecimal	corp_id;
	private BigDecimal	corp_key;
	private BigDecimal	dummy_4;
	private BigDecimal	dummy_5;
	private BigDecimal	masterdataid;
	private String	cifid;
	private String	entityboname;
	private String	entity_type;
	private String	source;
	private String	black_list_reason_notes;
	private BigDecimal	fileid;
	private BigDecimal	externalentityid;
	private String	firstname;
	private String	dummy_6;
	private String	dummy_7;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	date_of_incorp;
	
	private String	address_type;
	private String	addressline1;
	private String	addressline2;
	private String	addressline3;
	private String	dummy_8;
	private String	dummy_9;
	private String	dummy_10;
	private String	dummy_11;
	private String	dummy_12;
	private String	dummy_13;
	private String	dummy_14;
	private String	dummy_15;
	private String	dummy_16;
	private String	dummy_17;
	private String	dummy_18;
	private String	web_site;
	private String	userfield1;
	private String	userfield2;
	private String	userfield3;
	private String	userfield4;
	private String	userfield5;
	private String	userfield6;
	private String	userfield7;
	private String	userfield8;
	private String	userfield9;
	private String	userfield10;
	private BigDecimal	userfield11;
	private BigDecimal	userfield12;
	private BigDecimal	userfield13;
	private BigDecimal	userfield14;
	private BigDecimal	userfield15;
	private BigDecimal	userfield16;
	private BigDecimal	userfield17;
	private BigDecimal	userfield18;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	userfield19;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	userfield20;
	
	private BigDecimal	dummy_19;
	private String	phone;
	private String	phone_no1_city_code;
	private String	phone_no1_local_code;
	private String	phone_no1_country_code;
	private String	phone_no2_local_code;
	private String	dummy_20;
	private String	dummy_21;
	private String	dummy_22;
	private String	dummy_23;
	private String	dummy_24;
	private String	dummy_25;
	private String	dummy_26;
	private String	dummy_27;
	private String	dummy_28;
	private String	dummy_29;
	private String	dummy_30;
	private String	email;
	private String	shortname;
	private String	houseno;
	private String	premise_name;
	private String	building_level;
	private String	street_no;
	private String	street_name;
	private String	suburb;
	private String	locality_name;
	private String	town;
	private String	domicile;
	private String	city;
	private String	state;
	private String	zip;
	private String	dummy_31;
	private String	dummy_32;
	private String	dummy_33;
	private String	corporate_name_native;
	private String	dummy_34;
	private String	dummy_35;
	private String	firstname_native;
	private String	middlename_native;
	private String	lastname_native;
	private String	shortname_native;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	bodatecreated;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	bodatemodified;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	bodateverified;
	
	
	private String	boverifiedby;
	private String	bocreatedby;
	private String	bomodifiedby;
	private BigDecimal	securityilhint;
	private BigDecimal	securityiuhint;
	private BigDecimal	securityglhint;
	private BigDecimal	securityguhint;
	private BigDecimal	concurdetect_x;
	private BigDecimal	boaclid;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	incr_date_update;
	
	private String	nature_of_business_alt1;
	private String	nature_of_business;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	end_date;
	
	private String	work_extension;
	private String	remarks1;
	private String	remarks2;
	private String	remarks3;
	private String buildingname;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	aml_entry_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	aml_modify_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	aml_verify_time;
	
	private String	aml_entry_user;
	private String	aml_modify_user;
	private String	aml_verify_user;
	private String	entity_flag;
	private String	del_flag;
	private String	modify_flag;
	
	
	
	
	
	
	private String work_phoneno;
	private String home_email;
	private String work_email;
	private String home_phoneno;
	private String work_fax;
	private String country_of_incorp;
	private String country_of_principle_business;
	private String mail_stop;
	private String contactid;
	
	private String	panno;
	private String	ssn;
	private String	passportno;
	private String	creditcardno;
	private String	nationalid;
	private String	driverlicenseno;
	private String	workphoneno;
	private String noncustomerid;
	private String risk_category;
	private String sector;
	private String status;
	private String active_product_type;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_freezed;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_defreezed;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date start_date;

	private String phone_no_1;
	private String phone_no_2;


	private String phone_no2_city_code;
	private String phone_no2_country_code;

	private String cell;
	private String cell_city_code;
	private String cell_country_code;

	private String fax;	
	private String fax_city_code;
	private String fax_country_code;





	
	
	public String getDummy_1() {
		return dummy_1;
	}
	public void setDummy_1(String dummy_1) {
		this.dummy_1 = dummy_1;
	}
	public String getIdtyper1() {
		return idtyper1;
	}
	public void setIdtyper1(String idtyper1) {
		this.idtyper1 = idtyper1;
	}
	public String getIdtyper2() {
		return idtyper2;
	}
	public void setIdtyper2(String idtyper2) {
		this.idtyper2 = idtyper2;
	}
	public String getIdtyper3() {
		return idtyper3;
	}
	public void setIdtyper3(String idtyper3) {
		this.idtyper3 = idtyper3;
	}
	public String getIdtyper4() {
		return idtyper4;
	}
	public void setIdtyper4(String idtyper4) {
		this.idtyper4 = idtyper4;
	}
	public String getIdtyper5() {
		return idtyper5;
	}
	public void setIdtyper5(String idtyper5) {
		this.idtyper5 = idtyper5;
	}
	public String getId_type_r6() {
		return id_type_r6;
	}
	public void setId_type_r6(String id_type_r6) {
		this.id_type_r6 = id_type_r6;
	}
	public String getId_type_r7() {
		return id_type_r7;
	}
	public void setId_type_r7(String id_type_r7) {
		this.id_type_r7 = id_type_r7;
	}
	public String getId_type_r8() {
		return id_type_r8;
	}
	public void setId_type_r8(String id_type_r8) {
		this.id_type_r8 = id_type_r8;
	}
	public String getId_type_r9() {
		return id_type_r9;
	}
	public void setId_type_r9(String id_type_r9) {
		this.id_type_r9 = id_type_r9;
	}
	public String getId_type_r10() {
		return id_type_r10;
	}
	public void setId_type_r10(String id_type_r10) {
		this.id_type_r10 = id_type_r10;
	}
	public String getFirstname_alt1() {
		return firstname_alt1;
	}
	public void setFirstname_alt1(String firstname_alt1) {
		this.firstname_alt1 = firstname_alt1;
	}
	public String getPreferredformat() {
		return preferredformat;
	}
	public void setPreferredformat(String preferredformat) {
		this.preferredformat = preferredformat;
	}
	public String getDummy_2() {
		return dummy_2;
	}
	public void setDummy_2(String dummy_2) {
		this.dummy_2 = dummy_2;
	}
	public String getDummy_3() {
		return dummy_3;
	}
	public void setDummy_3(String dummy_3) {
		this.dummy_3 = dummy_3;
	}
	public String getShortname_alt1() {
		return shortname_alt1;
	}
	public void setShortname_alt1(String shortname_alt1) {
		this.shortname_alt1 = shortname_alt1;
	}
	public BigDecimal getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(BigDecimal corp_id) {
		this.corp_id = corp_id;
	}
	public BigDecimal getCorp_key() {
		return corp_key;
	}
	public void setCorp_key(BigDecimal corp_key) {
		this.corp_key = corp_key;
	}
	public BigDecimal getDummy_4() {
		return dummy_4;
	}
	public void setDummy_4(BigDecimal dummy_4) {
		this.dummy_4 = dummy_4;
	}
	public BigDecimal getDummy_5() {
		return dummy_5;
	}
	public void setDummy_5(BigDecimal dummy_5) {
		this.dummy_5 = dummy_5;
	}
	public BigDecimal getMasterdataid() {
		return masterdataid;
	}
	public void setMasterdataid(BigDecimal masterdataid) {
		this.masterdataid = masterdataid;
	}
	public String getCifid() {
		return cifid;
	}
	public void setCifid(String cifid) {
		this.cifid = cifid;
	}
	public String getEntityboname() {
		return entityboname;
	}
	public void setEntityboname(String entityboname) {
		this.entityboname = entityboname;
	}
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBlack_list_reason_notes() {
		return black_list_reason_notes;
	}
	public void setBlack_list_reason_notes(String black_list_reason_notes) {
		this.black_list_reason_notes = black_list_reason_notes;
	}
	public BigDecimal getFileid() {
		return fileid;
	}
	public void setFileid(BigDecimal fileid) {
		this.fileid = fileid;
	}
	public BigDecimal getExternalentityid() {
		return externalentityid;
	}
	public void setExternalentityid(BigDecimal externalentityid) {
		this.externalentityid = externalentityid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getDummy_6() {
		return dummy_6;
	}
	public void setDummy_6(String dummy_6) {
		this.dummy_6 = dummy_6;
	}
	public String getDummy_7() {
		return dummy_7;
	}
	public void setDummy_7(String dummy_7) {
		this.dummy_7 = dummy_7;
	}
	public Date getDate_of_incorp() {
		return date_of_incorp;
	}
	public void setDate_of_incorp(Date date_of_incorp) {
		this.date_of_incorp = date_of_incorp;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getAddressline3() {
		return addressline3;
	}
	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}
	public String getDummy_8() {
		return dummy_8;
	}
	public void setDummy_8(String dummy_8) {
		this.dummy_8 = dummy_8;
	}
	public String getDummy_9() {
		return dummy_9;
	}
	public void setDummy_9(String dummy_9) {
		this.dummy_9 = dummy_9;
	}
	public String getDummy_10() {
		return dummy_10;
	}
	public void setDummy_10(String dummy_10) {
		this.dummy_10 = dummy_10;
	}
	public String getDummy_11() {
		return dummy_11;
	}
	public void setDummy_11(String dummy_11) {
		this.dummy_11 = dummy_11;
	}
	public String getDummy_12() {
		return dummy_12;
	}
	public void setDummy_12(String dummy_12) {
		this.dummy_12 = dummy_12;
	}
	public String getDummy_13() {
		return dummy_13;
	}
	public void setDummy_13(String dummy_13) {
		this.dummy_13 = dummy_13;
	}
	public String getDummy_14() {
		return dummy_14;
	}
	public void setDummy_14(String dummy_14) {
		this.dummy_14 = dummy_14;
	}
	public String getDummy_15() {
		return dummy_15;
	}
	public void setDummy_15(String dummy_15) {
		this.dummy_15 = dummy_15;
	}
	public String getDummy_16() {
		return dummy_16;
	}
	public void setDummy_16(String dummy_16) {
		this.dummy_16 = dummy_16;
	}
	public String getDummy_17() {
		return dummy_17;
	}
	public void setDummy_17(String dummy_17) {
		this.dummy_17 = dummy_17;
	}
	public String getDummy_18() {
		return dummy_18;
	}
	public void setDummy_18(String dummy_18) {
		this.dummy_18 = dummy_18;
	}
	public String getWeb_site() {
		return web_site;
	}
	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}
	public String getUserfield1() {
		return userfield1;
	}
	public void setUserfield1(String userfield1) {
		this.userfield1 = userfield1;
	}
	public String getUserfield2() {
		return userfield2;
	}
	public void setUserfield2(String userfield2) {
		this.userfield2 = userfield2;
	}
	public String getUserfield3() {
		return userfield3;
	}
	public void setUserfield3(String userfield3) {
		this.userfield3 = userfield3;
	}
	public String getUserfield4() {
		return userfield4;
	}
	public void setUserfield4(String userfield4) {
		this.userfield4 = userfield4;
	}
	public String getUserfield5() {
		return userfield5;
	}
	public void setUserfield5(String userfield5) {
		this.userfield5 = userfield5;
	}
	public String getUserfield6() {
		return userfield6;
	}
	public void setUserfield6(String userfield6) {
		this.userfield6 = userfield6;
	}
	public String getUserfield7() {
		return userfield7;
	}
	public void setUserfield7(String userfield7) {
		this.userfield7 = userfield7;
	}
	public String getUserfield8() {
		return userfield8;
	}
	public void setUserfield8(String userfield8) {
		this.userfield8 = userfield8;
	}
	public String getUserfield9() {
		return userfield9;
	}
	public void setUserfield9(String userfield9) {
		this.userfield9 = userfield9;
	}
	public String getUserfield10() {
		return userfield10;
	}
	public void setUserfield10(String userfield10) {
		this.userfield10 = userfield10;
	}
	public BigDecimal getUserfield11() {
		return userfield11;
	}
	public void setUserfield11(BigDecimal userfield11) {
		this.userfield11 = userfield11;
	}
	public BigDecimal getUserfield12() {
		return userfield12;
	}
	public void setUserfield12(BigDecimal userfield12) {
		this.userfield12 = userfield12;
	}
	public BigDecimal getUserfield13() {
		return userfield13;
	}
	public void setUserfield13(BigDecimal userfield13) {
		this.userfield13 = userfield13;
	}
	public BigDecimal getUserfield14() {
		return userfield14;
	}
	public void setUserfield14(BigDecimal userfield14) {
		this.userfield14 = userfield14;
	}
	public BigDecimal getUserfield15() {
		return userfield15;
	}
	public void setUserfield15(BigDecimal userfield15) {
		this.userfield15 = userfield15;
	}
	public BigDecimal getUserfield16() {
		return userfield16;
	}
	public void setUserfield16(BigDecimal userfield16) {
		this.userfield16 = userfield16;
	}
	public BigDecimal getUserfield17() {
		return userfield17;
	}
	public void setUserfield17(BigDecimal userfield17) {
		this.userfield17 = userfield17;
	}
	public BigDecimal getUserfield18() {
		return userfield18;
	}
	public void setUserfield18(BigDecimal userfield18) {
		this.userfield18 = userfield18;
	}
	public Date getUserfield19() {
		return userfield19;
	}
	public void setUserfield19(Date userfield19) {
		this.userfield19 = userfield19;
	}
	public Date getUserfield20() {
		return userfield20;
	}
	public void setUserfield20(Date userfield20) {
		this.userfield20 = userfield20;
	}
	public BigDecimal getDummy_19() {
		return dummy_19;
	}
	public void setDummy_19(BigDecimal dummy_19) {
		this.dummy_19 = dummy_19;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone_no1_city_code() {
		return phone_no1_city_code;
	}
	public void setPhone_no1_city_code(String phone_no1_city_code) {
		this.phone_no1_city_code = phone_no1_city_code;
	}
	public String getPhone_no1_local_code() {
		return phone_no1_local_code;
	}
	public void setPhone_no1_local_code(String phone_no1_local_code) {
		this.phone_no1_local_code = phone_no1_local_code;
	}
	public String getPhone_no1_country_code() {
		return phone_no1_country_code;
	}
	public void setPhone_no1_country_code(String phone_no1_country_code) {
		this.phone_no1_country_code = phone_no1_country_code;
	}
	public String getPhone_no2_local_code() {
		return phone_no2_local_code;
	}
	public void setPhone_no2_local_code(String phone_no2_local_code) {
		this.phone_no2_local_code = phone_no2_local_code;
	}
	public String getDummy_20() {
		return dummy_20;
	}
	public void setDummy_20(String dummy_20) {
		this.dummy_20 = dummy_20;
	}
	public String getDummy_21() {
		return dummy_21;
	}
	public void setDummy_21(String dummy_21) {
		this.dummy_21 = dummy_21;
	}
	public String getDummy_22() {
		return dummy_22;
	}
	public void setDummy_22(String dummy_22) {
		this.dummy_22 = dummy_22;
	}
	public String getDummy_23() {
		return dummy_23;
	}
	public void setDummy_23(String dummy_23) {
		this.dummy_23 = dummy_23;
	}
	public String getDummy_24() {
		return dummy_24;
	}
	public void setDummy_24(String dummy_24) {
		this.dummy_24 = dummy_24;
	}
	public String getDummy_25() {
		return dummy_25;
	}
	public void setDummy_25(String dummy_25) {
		this.dummy_25 = dummy_25;
	}
	public String getDummy_26() {
		return dummy_26;
	}
	public void setDummy_26(String dummy_26) {
		this.dummy_26 = dummy_26;
	}
	public String getDummy_27() {
		return dummy_27;
	}
	public void setDummy_27(String dummy_27) {
		this.dummy_27 = dummy_27;
	}
	public String getDummy_28() {
		return dummy_28;
	}
	public void setDummy_28(String dummy_28) {
		this.dummy_28 = dummy_28;
	}
	public String getDummy_29() {
		return dummy_29;
	}
	public void setDummy_29(String dummy_29) {
		this.dummy_29 = dummy_29;
	}
	public String getDummy_30() {
		return dummy_30;
	}
	public void setDummy_30(String dummy_30) {
		this.dummy_30 = dummy_30;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getHouseno() {
		return houseno;
	}
	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}
	public String getPremise_name() {
		return premise_name;
	}
	public void setPremise_name(String premise_name) {
		this.premise_name = premise_name;
	}
	public String getBuilding_level() {
		return building_level;
	}
	public void setBuilding_level(String building_level) {
		this.building_level = building_level;
	}
	public String getStreet_no() {
		return street_no;
	}
	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getLocality_name() {
		return locality_name;
	}
	public void setLocality_name(String locality_name) {
		this.locality_name = locality_name;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getDummy_31() {
		return dummy_31;
	}
	public void setDummy_31(String dummy_31) {
		this.dummy_31 = dummy_31;
	}
	public String getDummy_32() {
		return dummy_32;
	}
	public void setDummy_32(String dummy_32) {
		this.dummy_32 = dummy_32;
	}
	public String getDummy_33() {
		return dummy_33;
	}
	public void setDummy_33(String dummy_33) {
		this.dummy_33 = dummy_33;
	}
	
	public String getDummy_34() {
		return dummy_34;
	}
	public void setDummy_34(String dummy_34) {
		this.dummy_34 = dummy_34;
	}
	public String getDummy_35() {
		return dummy_35;
	}
	public void setDummy_35(String dummy_35) {
		this.dummy_35 = dummy_35;
	}
	public String getFirstname_native() {
		return firstname_native;
	}
	public void setFirstname_native(String firstname_native) {
		this.firstname_native = firstname_native;
	}
	public String getMiddlename_native() {
		return middlename_native;
	}
	public void setMiddlename_native(String middlename_native) {
		this.middlename_native = middlename_native;
	}
	public String getLastname_native() {
		return lastname_native;
	}
	public void setLastname_native(String lastname_native) {
		this.lastname_native = lastname_native;
	}
	public String getShortname_native() {
		return shortname_native;
	}
	public void setShortname_native(String shortname_native) {
		this.shortname_native = shortname_native;
	}
	public Date getBodatecreated() {
		return bodatecreated;
	}
	public void setBodatecreated(Date bodatecreated) {
		this.bodatecreated = bodatecreated;
	}
	public Date getBodatemodified() {
		return bodatemodified;
	}
	public void setBodatemodified(Date bodatemodified) {
		this.bodatemodified = bodatemodified;
	}
	public Date getBodateverified() {
		return bodateverified;
	}
	public void setBodateverified(Date bodateverified) {
		this.bodateverified = bodateverified;
	}
	public String getBoverifiedby() {
		return boverifiedby;
	}
	public void setBoverifiedby(String boverifiedby) {
		this.boverifiedby = boverifiedby;
	}
	public String getBocreatedby() {
		return bocreatedby;
	}
	public void setBocreatedby(String bocreatedby) {
		this.bocreatedby = bocreatedby;
	}
	public String getBomodifiedby() {
		return bomodifiedby;
	}
	public void setBomodifiedby(String bomodifiedby) {
		this.bomodifiedby = bomodifiedby;
	}
	public BigDecimal getSecurityilhint() {
		return securityilhint;
	}
	public void setSecurityilhint(BigDecimal securityilhint) {
		this.securityilhint = securityilhint;
	}
	public BigDecimal getSecurityiuhint() {
		return securityiuhint;
	}
	public void setSecurityiuhint(BigDecimal securityiuhint) {
		this.securityiuhint = securityiuhint;
	}
	public BigDecimal getSecurityglhint() {
		return securityglhint;
	}
	public void setSecurityglhint(BigDecimal securityglhint) {
		this.securityglhint = securityglhint;
	}
	public BigDecimal getSecurityguhint() {
		return securityguhint;
	}
	public void setSecurityguhint(BigDecimal securityguhint) {
		this.securityguhint = securityguhint;
	}
	public BigDecimal getConcurdetect_x() {
		return concurdetect_x;
	}
	public void setConcurdetect_x(BigDecimal concurdetect_x) {
		this.concurdetect_x = concurdetect_x;
	}
	public BigDecimal getBoaclid() {
		return boaclid;
	}
	public void setBoaclid(BigDecimal boaclid) {
		this.boaclid = boaclid;
	}
	public Date getIncr_date_update() {
		return incr_date_update;
	}
	public void setIncr_date_update(Date incr_date_update) {
		this.incr_date_update = incr_date_update;
	}
	public String getNature_of_business_alt1() {
		return nature_of_business_alt1;
	}
	public void setNature_of_business_alt1(String nature_of_business_alt1) {
		this.nature_of_business_alt1 = nature_of_business_alt1;
	}
	public String getNature_of_business() {
		return nature_of_business;
	}
	public void setNature_of_business(String nature_of_business) {
		this.nature_of_business = nature_of_business;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	public String getRemarks3() {
		return remarks3;
	}
	public void setRemarks3(String remarks3) {
		this.remarks3 = remarks3;
	}
	public Date getAml_entry_time() {
		return aml_entry_time;
	}
	public void setAml_entry_time(Date aml_entry_time) {
		this.aml_entry_time = aml_entry_time;
	}
	public Date getAml_modify_time() {
		return aml_modify_time;
	}
	public void setAml_modify_time(Date aml_modify_time) {
		this.aml_modify_time = aml_modify_time;
	}
	public Date getAml_verify_time() {
		return aml_verify_time;
	}
	public void setAml_verify_time(Date aml_verify_time) {
		this.aml_verify_time = aml_verify_time;
	}
	public String getAml_entry_user() {
		return aml_entry_user;
	}
	public void setAml_entry_user(String aml_entry_user) {
		this.aml_entry_user = aml_entry_user;
	}
	public String getAml_modify_user() {
		return aml_modify_user;
	}
	public void setAml_modify_user(String aml_modify_user) {
		this.aml_modify_user = aml_modify_user;
	}
	public String getAml_verify_user() {
		return aml_verify_user;
	}
	public void setAml_verify_user(String aml_verify_user) {
		this.aml_verify_user = aml_verify_user;
	}
	public String getEntity_flag() {
		return entity_flag;
	}
	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public BigDecimal getBlack_list_corp_data_setid() {
		return black_list_corp_data_setid;
	}
	public void setBlack_list_corp_data_setid(BigDecimal black_list_corp_data_setid) {
		this.black_list_corp_data_setid = black_list_corp_data_setid;
	}
	public String getWork_phoneno() {
		return work_phoneno;
	}
	public void setWork_phoneno(String work_phoneno) {
		this.work_phoneno = work_phoneno;
	}
	public String getHome_email() {
		return home_email;
	}
	public void setHome_email(String home_email) {
		this.home_email = home_email;
	}
	public String getCountry_of_incorp() {
		return country_of_incorp;
	}
	public void setCountry_of_incorp(String country_of_incorp) {
		this.country_of_incorp = country_of_incorp;
	}
	public String getCountry_of_principle_business() {
		return country_of_principle_business;
	}
	public void setCountry_of_principle_business(String country_of_principle_business) {
		this.country_of_principle_business = country_of_principle_business;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public String getPhone_no_1() {
		return phone_no_1;
	}
	public void setPhone_no_1(String phone_no_1) {
		this.phone_no_1 = phone_no_1;
	}
	public String getPhone_no_2() {
		return phone_no_2;
	}
	public void setPhone_no_2(String phone_no_2) {
		this.phone_no_2 = phone_no_2;
	}
	public String getPhone_no2_city_code() {
		return phone_no2_city_code;
	}
	public void setPhone_no2_city_code(String phone_no2_city_code) {
		this.phone_no2_city_code = phone_no2_city_code;
	}
	public String getPhone_no2_country_code() {
		return phone_no2_country_code;
	}
	public void setPhone_no2_country_code(String phone_no2_country_code) {
		this.phone_no2_country_code = phone_no2_country_code;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getCell_city_code() {
		return cell_city_code;
	}
	public void setCell_city_code(String cell_city_code) {
		this.cell_city_code = cell_city_code;
	}
	public String getCell_country_code() {
		return cell_country_code;
	}
	public void setCell_country_code(String cell_country_code) {
		this.cell_country_code = cell_country_code;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFax_city_code() {
		return fax_city_code;
	}
	public void setFax_city_code(String fax_city_code) {
		this.fax_city_code = fax_city_code;
	}
	public String getFax_country_code() {
		return fax_country_code;
	}
	public void setFax_country_code(String fax_country_code) {
		this.fax_country_code = fax_country_code;
	}
	public String getWork_fax() {
		return work_fax;
	}
	public void setWork_fax(String work_fax) {
		this.work_fax = work_fax;
	}
	public String getWork_email() {
		return work_email;
	}
	public void setWork_email(String work_email) {
		this.work_email = work_email;
	}
	public String getHome_phoneno() {
		return home_phoneno;
	}
	public void setHome_phoneno(String home_phoneno) {
		this.home_phoneno = home_phoneno;
	}
	public String getMail_stop() {
		return mail_stop;
	}
	public void setMail_stop(String mail_stop) {
		this.mail_stop = mail_stop;
	}
	public String getCorporate_name_native() {
		return corporate_name_native;
	}
	public void setCorporate_name_native(String corporate_name_native) {
		this.corporate_name_native = corporate_name_native;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getPanno() {
		return panno;
	}
	public void setPanno(String panno) {
		this.panno = panno;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getCreditcardno() {
		return creditcardno;
	}
	public void setCreditcardno(String creditcardno) {
		this.creditcardno = creditcardno;
	}
	public String getNationalid() {
		return nationalid;
	}
	public void setNationalid(String nationalid) {
		this.nationalid = nationalid;
	}
	public String getDriverlicenseno() {
		return driverlicenseno;
	}
	public void setDriverlicenseno(String driverlicenseno) {
		this.driverlicenseno = driverlicenseno;
	}
	public String getPassportno() {
		return passportno;
	}
	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}
	public String getWorkphoneno() {
		return workphoneno;
	}
	public void setWorkphoneno(String workphoneno) {
		this.workphoneno = workphoneno;
	}
	public String getNoncustomerid() {
		return noncustomerid;
	}
	public void setNoncustomerid(String noncustomerid) {
		this.noncustomerid = noncustomerid;
	}
	public String getWork_extension() {
		return work_extension;
	}
	public void setWork_extension(String work_extension) {
		this.work_extension = work_extension;
	}
	public String getRisk_category() {
		return risk_category;
	}
	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActive_product_type() {
		return active_product_type;
	}
	public void setActive_product_type(String active_product_type) {
		this.active_product_type = active_product_type;
	}
	public Date getDate_freezed() {
		return date_freezed;
	}
	public void setDate_freezed(Date date_freezed) {
		this.date_freezed = date_freezed;
	}
	public Date getDate_defreezed() {
		return date_defreezed;
	}
	public void setDate_defreezed(Date date_defreezed) {
		this.date_defreezed = date_defreezed;
	}
	public Cust_Black_List_Corp_Entity(String bANK_ID2, String uNIQUE_REF_ID, String aLT_SHORT_NAME, String pREFERRED_FORMAT, String rEMARKS1, 
			String aLT_CORP_NAME, String nATIVE_CORP_NAME, String nATIVE_SHORT_NAME, BigDecimal cORPORATE_ID, BigDecimal cORPORATE_KEY, String eNTITY_TYPE2, String cONTACT_ID, 
			BigDecimal masterdataId, String cIF_ID, String eNTITY_BRANCH_NAME, String sOURCE2, String bL_REASON_NOTES, String rISK_CATEGORY2, String sECTOR2, 
			String sTATUS2, Date dATE_FREEZED1, Date dATE_DE_FREEZED1, String aCTIVE_PROD_TYPE, BigDecimal fieldId, BigDecimal externatLEntity_Id, String rEMARKS22,
			String fIRST_NAME, String aDDRESS_TYPE2, Date dATE_OF_INCORP1, String aDDRESS_LINE1, String aDDRESS_LINE2, String aDDRESS_LINE3, String pAN_NO,
			String sSN_NO, String pASSPORT_NO, String cREDIT_CARD_No, String nATIONAL_ID_NO, String dRIVER_LICENSE_NO, String hOME_PHONE_NO, String wORK_PHONE_NO,
			String wORK_EMAIL2, String hOME_MAIL, String wORK_FAX2, String wEBSITE, String nocustomerID, String pHONE2, String eMAIL2, String hOUSE_NO,
			String pREMISE_NAME2, String bUILDING_NAME, String bUILDING_LEVEL2, String sTREET_NO2, String sTREET_NAME2, String lOCALITY_NAME2, String tOWN2, 
			String dOMICILE2, String cITY2, String sTATE2, String zIP2, String cOUNTRY_OF_INCORP2, String cOUNTRY_OF_PRINCIPLE_BUSINESS2, String mAIL_STOP2,
			String nATURE_OF_BUSINESS2, Date sTART_DATE1, Date eND_DATE1, String wORK_EXTENSIONS, BigDecimal bO_ACL_ID, String rEMARKS_3, String pHONE_NO_12,
			String pHONE_NO_1_CITY_CODE, String pHONE_NO_1_COUNTRY_CODE, String pHONE_NO_22, String pHONE_NO_2_CITY_CODE, String pHONE_NO_2_COUNTRY_CODE, 
			String cELL2, String cELL_CITY_CODE2, String cELL_COUNTRY_CODE2, String fAX2, String fAX_CITY_CODE2, String fAX_COUNTRY_CODE2,
			
			Date bO_CREATED_DATE1, String bO_CREATED_USER, Date bO_VERIFIED_DATE1, String b_VERFIED_USER, Date bO_MODIFIED_DATE1, String bO_MODIFIED_USER,
			String entry_user,Date entry_time, String entity_flg, String del_flg) {
		super();
		
		this.bank_id=bANK_ID2;
		this.dummy_1=uNIQUE_REF_ID;
		this.shortname=aLT_SHORT_NAME;
		this.preferredformat=pREFERRED_FORMAT;
		this.remarks1=rEMARKS1;
		this.firstname_alt1=aLT_CORP_NAME;
		this.corporate_name_native=nATIVE_CORP_NAME;
		this.shortname_native=nATIVE_SHORT_NAME;
		this.corp_id=cORPORATE_ID;
		this.corp_key=cORPORATE_KEY;
		this.entity_type=eNTITY_TYPE2;
		this.contactid=cONTACT_ID;
		this.masterdataid=masterdataId;
		this.cifid=cIF_ID;
		this.entityboname=eNTITY_BRANCH_NAME;
		this.source=sOURCE2;
		this.black_list_reason_notes=bL_REASON_NOTES;
		this.risk_category=rISK_CATEGORY2;
		this.sector=sECTOR2;
		this.status=sTATUS2;
		this.date_defreezed=dATE_FREEZED1;
		this.date_defreezed=dATE_DE_FREEZED1;
		this.active_product_type=aCTIVE_PROD_TYPE;
		this.fileid=fieldId;
		this.externalentityid=externatLEntity_Id;
		this.remarks2=rEMARKS22;
		this.firstname=fIRST_NAME;
		this.address_type=aDDRESS_TYPE2;
		this.date_of_incorp=dATE_OF_INCORP1;
		this.addressline1=aDDRESS_LINE1;
		this.addressline2=aDDRESS_LINE2;
		this.addressline3=aDDRESS_LINE3;
		this.panno=pAN_NO;
		this.ssn=sSN_NO;
		this.passportno=pASSPORT_NO;
		this.creditcardno=cREDIT_CARD_No;
		this.nationalid=nATIONAL_ID_NO;
		this.driverlicenseno=dRIVER_LICENSE_NO;
		this.home_phoneno=hOME_PHONE_NO;
		this.workphoneno=wORK_PHONE_NO;
		this.work_email=wORK_EMAIL2;
		this.home_email=hOME_MAIL;
		this.work_fax=wORK_FAX2;
		this.web_site=wEBSITE;
		this.noncustomerid=nocustomerID;
		this.phone=pHONE2;
		this.email=eMAIL2;
		this.houseno=hOUSE_NO;
		this.premise_name=pREMISE_NAME2;
		this.buildingname=bUILDING_NAME;
		this.building_level=bUILDING_LEVEL2;
		this.street_no=sTREET_NO2;
		this.street_name=sTREET_NAME2;
		this.locality_name=lOCALITY_NAME2;
		this.town=tOWN2;
		this.domicile=dOMICILE2;
		this.city=cITY2;
		this.state=sTATE2;
		this.country_of_incorp=cOUNTRY_OF_INCORP2;
		this.country_of_principle_business=cOUNTRY_OF_PRINCIPLE_BUSINESS2;
		this.mail_stop=mAIL_STOP2;
		this.nature_of_business=nATURE_OF_BUSINESS2;
		this.start_date=sTART_DATE1;
		this.end_date=eND_DATE1;
		this.work_extension=wORK_EXTENSIONS;
		this.boaclid=bO_ACL_ID;
		this.remarks3=rEMARKS_3;
		this.phone_no_1=pHONE_NO_12;
		this.phone_no1_city_code=pHONE_NO_1_CITY_CODE;
		this.phone_no1_country_code=pHONE_NO_1_COUNTRY_CODE;
		this.phone_no_2=pHONE_NO_22;
		this.phone_no2_city_code=pHONE_NO_2_CITY_CODE;
		this.phone_no2_country_code=pHONE_NO_2_COUNTRY_CODE;
		
		this.cell =cELL2;
		this.cell_city_code=cELL_CITY_CODE2;
		this.cell_country_code=cELL_COUNTRY_CODE2;
		this.phone_no2_country_code=fAX2;
		this.fax=fAX_CITY_CODE2;
		this.fax_city_code=fAX_COUNTRY_CODE2;
		this.fax_country_code=fAX_COUNTRY_CODE2;
		this.bodatecreated=bO_CREATED_DATE1;
		this.bocreatedby=bO_CREATED_USER;
		this.bodateverified=bO_VERIFIED_DATE1;
		this.boverifiedby=b_VERFIED_USER;
		this.bodatemodified=bO_MODIFIED_DATE1;
		this.bomodifiedby=bO_MODIFIED_USER;
		
		this.aml_entry_user=entry_user;
		this.aml_entry_time=entry_time;
		this.entity_flag=entity_flg;
		this.del_flag=del_flg;
		
	}
	}

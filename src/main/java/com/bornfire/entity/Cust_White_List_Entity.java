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
@Table(name="BAML_CUST_WHITE_LIST")
public class Cust_White_List_Entity implements Serializable{
	public Cust_White_List_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private BigDecimal cust_white_list_id;
	private String	bank_id;
	
	private String	uniqueidnumber;
	private String	idtyper1;
	private String	idtyper2;
	private String	idtyper3;
	private String	idtyper4;
	private String	idtyper5;
	private String	firstname_alt1;
	private String	preferredformat;
	private String	addressline3;
	private String	middlename_alt1;
	private String	lastname_alt1;
	private String	shortname_alt1;
	private BigDecimal customerid;
	private BigDecimal applicationid;
	private BigDecimal suspectid;
	private BigDecimal contactid;
	private BigDecimal masterdataid;
	private String	cifid;
	private String	entityboname;
	private String	source;
	private String	negativelistreasonnotes;
	private BigDecimal fileid;
	private BigDecimal externalentityid;
	private String	firstname;
	private String	middlename;
	private String	lastname;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	dateofbirth;
	
	private String	addressline1;
	private String	addressline2;
	private String	panno;
	private String	ssn;
	private String	passportno;
	private String	creditcardno;
	private String	nationalid;
	private String	driverlicenseno;
	private String	homephoneno;
	private String	workphoneno;
	private String	mobilephoneno;
	private String	homeemail;
	private String	workfax;
	private String	workemail;
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
	private BigDecimal userfield11;
	private BigDecimal userfield12;
	private BigDecimal userfield13;
	private BigDecimal userfield14;
	private BigDecimal userfield15;
	private BigDecimal userfield16;
	private BigDecimal userfield17;
	private BigDecimal userfield18;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	userfield19;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	userfield20;
	
	private BigDecimal noncustomerid;
	private String	phone;
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
	private String	firstname_native;
	private String	middlename_native;
	private String	lastname_native;
	private String	shortname_native;
	private String buildingname;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	bodatecreated;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	bodatemodified;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	bodateverified;
	
	private String	bocreatedby;
	private String	bomodifiedby;
	private String	boverifiedby;
	private BigDecimal securityilhint;
	private BigDecimal securityiuhint;
	private BigDecimal securityglhint;
	private BigDecimal securityguhint;
	private BigDecimal concurdetect_x;
	private BigDecimal boaclid;
	
	private String risk_category;
	private String status;
	private String active_product_type;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_freezed;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_defreezed;
	
	private String remarks1;
	private String remarks2;
	private String remarks3;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	aml_entry_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	aml_modify_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	aml_verify_time;
	
	private String	aml_entry_user;
	private String	aml_modify_user;
	private String	aml_verify_user;
	
	private String	entity_flag;
	private String	del_flag;
	private String	modify_flag;
	
	
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getUniqueidnumber() {
		return uniqueidnumber;
	}
	public void setUniqueidnumber(String uniqueidnumber) {
		this.uniqueidnumber = uniqueidnumber;
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
	public String getAddressline3() {
		return addressline3;
	}
	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}
	public String getMiddlename_alt1() {
		return middlename_alt1;
	}
	public void setMiddlename_alt1(String middlename_alt1) {
		this.middlename_alt1 = middlename_alt1;
	}
	public String getLastname_alt1() {
		return lastname_alt1;
	}
	public void setLastname_alt1(String lastname_alt1) {
		this.lastname_alt1 = lastname_alt1;
	}
	public String getShortname_alt1() {
		return shortname_alt1;
	}
	public void setShortname_alt1(String shortname_alt1) {
		this.shortname_alt1 = shortname_alt1;
	}
	public BigDecimal getCustomerid() {
		return customerid;
	}
	public void setCustomerid(BigDecimal customerid) {
		this.customerid = customerid;
	}
	public BigDecimal getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(BigDecimal applicationid) {
		this.applicationid = applicationid;
	}
	public BigDecimal getSuspectid() {
		return suspectid;
	}
	public void setSuspectid(BigDecimal suspectid) {
		this.suspectid = suspectid;
	}
	public BigDecimal getContactid() {
		return contactid;
	}
	public void setContactid(BigDecimal contactid) {
		this.contactid = contactid;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getNegativelistreasonnotes() {
		return negativelistreasonnotes;
	}
	public void setNegativelistreasonnotes(String negativelistreasonnotes) {
		this.negativelistreasonnotes = negativelistreasonnotes;
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
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
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
	public String getPassportno() {
		return passportno;
	}
	public void setPassportno(String passportno) {
		this.passportno = passportno;
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
	public String getHomephoneno() {
		return homephoneno;
	}
	public void setHomephoneno(String homephoneno) {
		this.homephoneno = homephoneno;
	}
	public String getWorkphoneno() {
		return workphoneno;
	}
	public void setWorkphoneno(String workphoneno) {
		this.workphoneno = workphoneno;
	}
	public String getMobilephoneno() {
		return mobilephoneno;
	}
	public void setMobilephoneno(String mobilephoneno) {
		this.mobilephoneno = mobilephoneno;
	}
	public String getHomeemail() {
		return homeemail;
	}
	public void setHomeemail(String homeemail) {
		this.homeemail = homeemail;
	}
	public String getWorkfax() {
		return workfax;
	}
	public void setWorkfax(String workfax) {
		this.workfax = workfax;
	}
	public String getWorkemail() {
		return workemail;
	}
	public void setWorkemail(String workemail) {
		this.workemail = workemail;
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
	public BigDecimal getNoncustomerid() {
		return noncustomerid;
	}
	public void setNoncustomerid(BigDecimal noncustomerid) {
		this.noncustomerid = noncustomerid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
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
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public String getRemarks3() {
		return remarks3;
	}
	public void setRemarks3(String remarks3) {
		this.remarks3 = remarks3;
	}
	public String getRisk_category() {
		return risk_category;
	}
	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
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
	public Cust_White_List_Entity( String bANK_ID2, String uNIQUE_REF_ID, String aLT_SHORT_NAME, String pREFERRED_FORMAT, Date dateofbirth, String aLT_FIRST_NAME,
			String aLT_MIDDLE_NAME, String aLT_LAST_NAME, BigDecimal custId, BigDecimal appId, BigDecimal suspectId2, BigDecimal contactId2, BigDecimal masterDateID, 
			String cIF_ID, String eNTITY_BRANCH_NAME, String sOURCE2, String bL_REASON_NOTES, BigDecimal fieldId, BigDecimal externalentityId2, String rISK_CATEGORY2, 
			Date dATE_FREEZED1, Date dATE_DE_FREEZED1, String aCTIVE_PROD_TYPE, String fIRST_NAME, String mIDDLE_NAME, String sTATUS2, String aDDRESS_LINE1, String aDDRESS_LINE2,
			
			String aDDRESS_LINE3, String pAN_NO, String sSN_NO, String pASSPORT_NO, String cREDIT_CARD_No, String nATIONAL_ID_NO, String dRIVER_LICENSE_NO, String hOME_PHONE_NO,
			
			String wORK_PHONE_NO, String wORK_EMAIL, String hOME_MAIL, String wORK_FAX, String rEMARKS13, BigDecimal nocustomerID, String pHONE2, String eMAIL2, String hOUSE_NO,
			String pREMISE_NAME2, String bUILDING_NAME, String bUILDING_LEVEL2, String sTREET_NO2, String sTREET_NAME2, String lOCALITY_NAME2, String tOWN2, String dOMICILE2, 
			String cITY2, BigDecimal boAclID2, String nATIVE_SHORT_NAME, String nATIVE_FIRST_NAME, String nATIVE_MIDDLE_NAME, String nATIVE_LAST_NAME, Date bO_CREATED_DATE1,
			String bO_CREATED_USER, Date bO_VERIFIED_DATE1, String b_VERFIED_USER, Date bO_MODIFIED_DATE1, String bO_MODIFIED_USER, String entry_user, Date entry_time, 
			String entity_flg, String del_flg) {
		super();
		
		this.bank_id=bANK_ID2;
		this.uniqueidnumber=uNIQUE_REF_ID;
		this.shortname=aLT_SHORT_NAME;
		this.preferredformat=pREFERRED_FORMAT;
		this.dateofbirth=dateofbirth;
		this.firstname_alt1=aLT_FIRST_NAME;
		this.middlename_alt1=aLT_MIDDLE_NAME;
		this.lastname_alt1=aLT_LAST_NAME;
		this.customerid=custId;
		this.applicationid=appId;
		this.suspectid=suspectId2;
		this.contactid=contactId2;
		this.masterdataid=masterDateID;
		this.cifid=cIF_ID;
		this.entityboname=eNTITY_BRANCH_NAME;
		this.source=sOURCE2;
		this.negativelistreasonnotes=bL_REASON_NOTES;
		this.fileid=fieldId;
		this.externalentityid=externalentityId2;
		this.risk_category=rISK_CATEGORY2;
		this.date_defreezed=dATE_FREEZED1;
		this.date_defreezed=dATE_DE_FREEZED1;
		this.active_product_type=aCTIVE_PROD_TYPE;
		this.firstname=fIRST_NAME;
		this.middlename=mIDDLE_NAME;
		this.status=sTATUS2;
		this.addressline1=aDDRESS_LINE1;
		this.addressline2=aDDRESS_LINE2;
		this.addressline3=aDDRESS_LINE3;
		
		this.panno=pAN_NO;
		this.ssn=sSN_NO;
		this.passportno=pASSPORT_NO;
		this.creditcardno=cREDIT_CARD_No;
		this.nationalid=nATIONAL_ID_NO;
		this.driverlicenseno=dRIVER_LICENSE_NO;
		this.homephoneno=hOME_PHONE_NO;
		this.workphoneno=wORK_PHONE_NO;
		this.workemail=wORK_EMAIL;
		this.homeemail=hOME_MAIL;
		this.workfax=wORK_FAX;
		this.remarks2=rEMARKS13;
		this.noncustomerid=nocustomerID;
		this.phone=hOUSE_NO;
		this.email=eMAIL2;
		this.houseno=aDDRESS_LINE3;
		this.premise_name=pREMISE_NAME2;
		this.buildingname=bUILDING_NAME;
		this.building_level=bUILDING_LEVEL2;
		this.street_no=sTREET_NO2;
		this.street_name=sTREET_NAME2;
		this.locality_name=lOCALITY_NAME2;
		this.town=tOWN2;
		this.domicile=dOMICILE2;
		this.city=cITY2;
		this.boaclid=boAclID2;
		this.shortname_native=nATIVE_SHORT_NAME;
		this.firstname_native=nATIVE_FIRST_NAME;
		this.middlename_native=nATIVE_MIDDLE_NAME;
		this.lastname_native=nATIVE_LAST_NAME;
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
	public BigDecimal getCust_white_list_id() {
		return cust_white_list_id;
	}
	public void setCust_white_list_id(BigDecimal cust_white_list_id) {
		this.cust_white_list_id = cust_white_list_id;
	}
	

}

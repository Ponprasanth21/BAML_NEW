package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "\"BAML_CUST_MAST_TABLE\"", schema = "\"AML\"")
public class CMG_MASTER {
	@Column(name = "\"PRIMARY_SOL_ID\"")
	private String primary_sol_id;

	@Column(name = "\"CUST_ID\"")
	private String cust_id;

	@Id
	@Column(name = "\"CIF_ID\"")
	private String cif_id;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"CUST_OPN_DATE\"")
	private Date cust_opn_date;

	@Column(name = "\"CUST_NAME\"")
	private String cust_name;

	@Column(name = "\"CUST_SHORT_NAME\"")
	private String cust_short_name;

	@Column(name = "\"CUST_FIRST_NAME\"")
	private String cust_first_name;

	@Column(name = "\"CUST_MIDDLE_NAME\"")
	private String cust_middle_name;

	@Column(name = "\"CUST_LAST_NAME\"")
	private String cust_last_name;

	@Column(name = "\"CUST_TYPE_CODE\"")
	private String cust_type_code;

	@Column(name = "\"CUST_SECTOR_CODE\"")
	private String cust_sector_code;

	@Column(name = "\"CUST_NRE_FLG\"")
	private String cust_nre_flg;

	@Column(name = "\"CUST_TITLE_CODE\"")
	private String cust_title_code;

	@Column(name = "\"CUST_SEX\"")
	private String cust_sex;

	@Column(name = "\"CUST_GRP\"")
	private String cust_grp;

	@Column(name = "\"CUST_CONST\"")
	private String cust_const;

	@Column(name = "\"UNIQUEID\"")
	private String uniqueid;

	@Column(name = "\"NUM_OF_ACCOUNTS\"")
	private BigDecimal num_of_accounts;

	@Column(name = "\"PREFERREDADDRESS\"")
	private String preferredaddress;

	@Column(name = "\"ADDR_ID\"")
	private String addr_id;

	@Column(name = "\"ADDRESS1\"")
	private String address1;

	@Column(name = "\"ADDRESS2\"")
	private String address2;

	@Column(name = "\"ADDRESS3\"")
	private String address3;

	@Column(name = "\"CITY_CODE\"")
	private String city_code;

	@Column(name = "\"STATE_CODE\"")
	private String state_code;

	@Column(name = "\"CNTRY_CODE\"")
	private String cntry_code;

	@Column(name = "\"PIN_CODE\"")
	private String pin_code;

	@Column(name = "\"NATIONALITY\"")
	private String nationality;

	@Column(name = "\"RESIDENCE_COUNTRY\"")
	private String residence_country;

	@Column(name = "\"EMPLOYERID_CODE\"")
	private String employerid_code;

	@Column(name = "\"EMPLOYERSNAME\"")
	private String employersname;

	@Column(name = "\"SOURCEOFINCOME\"")
	private String sourceofincome;

	@Column(name = "\"ANNUAL_SALARY_INCOME\"")
	private BigDecimal annual_salary_income;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"CUST_DOB\"")
	private Date cust_dob;

	@Column(name = "\"PAN\"")
	private String pan;

	@Column(name = "\"SSN\"")
	private String ssn;

	@Column(name = "\"NAT_ID_CARD_NUM\"")
	private String nat_id_card_num;

	@Column(name = "\"PREFERREDPHONE\"")
	private String preferredphone;

	@Column(name = "\"PREFERREDEMAIL\"")
	private String preferredemail;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"KYC_DATE\"")
	private Date kyc_date;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"KYC_REVIEWDATE\"")
	private Date kyc_reviewdate;

	@Column(name = "\"RISK_PROFILE_SCORE\"")
	private BigDecimal risk_profile_score;

	@Column(name = "\"RISKRATING\"")
	private String riskrating;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"RISK_PROFILE_EXPIRY_DATE\"")
	private Date risk_profile_expiry_date;

	@Column(name = "\"STRUSERFIELD10\"")
	private String struserfield10;

	@Column(name = "\"OCCUPATION\"")
	private String occupation;

	@Column(name = "\"BLACKLISTED\"")
	private String blacklisted;

	@Column(name = "\"BLACKLISTNOTES\"")
	private String blacklistnotes;

	@Column(name = "\"BLACKLIST_REASON\"")
	private String blacklist_reason;

	@Column(name = "\"PASSPORTNO\"")
	private String passportno;

	@Column(name = "\"PSPRT_DET\"")
	private String psprt_det;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"PSPRT_EXP_DATE\"")
	private Date psprt_exp_date;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"PSPRT_ISSUE_DATE\"")
	private Date psprt_issue_date;

	@Column(name = "\"FREE_CODE_6\"")
	private String free_code_6;

	@Column(name = "\"FREE_CODE_7\"")
	private String free_code_7;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"DATE_OF_INCORPORATION\"")
	private Date date_of_incorporation;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"DATE_OF_COMMENCEMENT\"")
	private Date date_of_commencement;

	@Column(name = "\"REGISTRATION_NUMBER\"")
	private String registration_number;

	@Column(name = "\"COUNTRY_OF_BIRTH\"")
	private String country_of_birth;

	@Column(name = "\"ANNUALREVENUE\"")
	private BigDecimal annualrevenue;

	@Column(name = "\"PLACEOFBIRTH\"")
	private String placeofbirth;

	@Column(name = "\"SUBSEGMENT\"")
	private String subsegment;

	@Column(name = "\"TYPE\"")
	private String type;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"DTDATE1\"")
	private Date dtdate1;

	@Column(name = "\"VALUE\"")
	private String value;

	@Column(name = "\"LOCALETEXT\"")
	private String localetext;

	@Column(name = "\"SEGMENTATION_CLASS\"")
	private String segmentation_class;

	@Column(name = "\"PREFERREDNAME\"")
	private String preferredname;

	@Column(name = "\"PHONE_HOME\"")
	private String phone_home;

	@Column(name = "\"CUST_STAT_CODE\"")
	private String cust_stat_code;

	@Column(name = "\"CUST_STAT_CHG_DATE\"")
	private Date cust_stat_chg_date;

	@Column(name = "\"EMPLOYMENT_STATUS\"")
	private String employment_status;

	@Column(name = "\"STATUS\"")
	private String status;

	@Column(name = "\"STRUSERFIELD11\"")
	private String struserfield11;

	@Column(name = "\"STRUSERFIELD13\"")
	private String struserfield13;

	@Column(name = "\"STRUSERFIELD15\"")
	private String struserfield15;

	@Column(name = "\"FREE_TEXT_1\"")
	private String free_text_1;

	@Column(name = "\"FREE_CODE_2\"")
	private String free_code_2;

	@Column(name = "\"ACTIVE_CUSTOMER_FLG\"")
	private String active_customer_flg;

	@Column(name = "\"CUSTOMER_STATUS_DATE\"")
	private Date customer_status_date;

	@Column(name = "\"RESIDING_COUNTRY\"")
	private String residing_country;

	public String getResiding_country() {
		return residing_country;
	}

	public void setResiding_country(String residing_country) {
		this.residing_country = residing_country;
	}

	public String getActive_customer_flg() {
		return active_customer_flg;
	}

	public void setActive_customer_flg(String active_customer_flg) {
		this.active_customer_flg = active_customer_flg;
	}

	public Date getCustomer_status_date() {
		return customer_status_date;
	}

	public void setCustomer_status_date(Date customer_status_date) {
		this.customer_status_date = customer_status_date;
	}

	public String getFree_text_1() {
		return free_text_1;
	}

	public void setFree_text_1(String free_text_1) {
		this.free_text_1 = free_text_1;
	}

	public String getFree_code_2() {
		return free_code_2;
	}

	public void setFree_code_2(String free_code_2) {
		this.free_code_2 = free_code_2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStruserfield11() {
		return struserfield11;
	}

	public void setStruserfield11(String struserfield11) {
		this.struserfield11 = struserfield11;
	}

	public String getStruserfield13() {
		return struserfield13;
	}

	public void setStruserfield13(String struserfield13) {
		this.struserfield13 = struserfield13;
	}

	public String getStruserfield15() {
		return struserfield15;
	}

	public void setStruserfield15(String struserfield15) {
		this.struserfield15 = struserfield15;
	}

	public String getSegmentation_class() {
		return segmentation_class;
	}

	public void setSegmentation_class(String segmentation_class) {
		this.segmentation_class = segmentation_class;
	}

	public String getPreferredname() {
		return preferredname;
	}

	public void setPreferredname(String preferredname) {
		this.preferredname = preferredname;
	}

	public String getPhone_home() {
		return phone_home;
	}

	public void setPhone_home(String phone_home) {
		this.phone_home = phone_home;
	}

	public String getCust_stat_code() {
		return cust_stat_code;
	}

	public void setCust_stat_code(String cust_stat_code) {
		this.cust_stat_code = cust_stat_code;
	}

	public Date getCust_stat_chg_date() {
		return cust_stat_chg_date;
	}

	public void setCust_stat_chg_date(Date cust_stat_chg_date) {
		this.cust_stat_chg_date = cust_stat_chg_date;
	}

	public String getEmployment_status() {
		return employment_status;
	}

	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLocaletext() {
		return localetext;
	}

	public void setLocaletext(String localetext) {
		this.localetext = localetext;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getSubsegment() {
		return subsegment;
	}

	public void setSubsegment(String subsegment) {
		this.subsegment = subsegment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDtdate1() {
		return dtdate1;
	}

	public void setDtdate1(Date dtdate1) {
		this.dtdate1 = dtdate1;
	}

	public String getCountry_of_birth() {
		return country_of_birth;
	}

	public void setCountry_of_birth(String country_of_birth) {
		this.country_of_birth = country_of_birth;
	}

	public BigDecimal getAnnualrevenue() {
		return annualrevenue;
	}

	public void setAnnualrevenue(BigDecimal annualrevenue) {
		this.annualrevenue = annualrevenue;
	}

	public String getPlaceofbirth() {
		return placeofbirth;
	}

	public void setPlaceofbirth(String placeofbirth) {
		this.placeofbirth = placeofbirth;
	}

	public String getPrimary_sol_id() {
		return primary_sol_id;
	}

	public void setPrimary_sol_id(String primary_sol_id) {
		this.primary_sol_id = primary_sol_id;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getCif_id() {
		return cif_id;
	}

	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}

	public Date getCust_opn_date() {
		return cust_opn_date;
	}

	public void setCust_opn_date(Date cust_opn_date) {
		this.cust_opn_date = cust_opn_date;
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

	public String getCust_type_code() {
		return cust_type_code;
	}

	public void setCust_type_code(String cust_type_code) {
		this.cust_type_code = cust_type_code;
	}

	public String getCust_sector_code() {
		return cust_sector_code;
	}

	public void setCust_sector_code(String cust_sector_code) {
		this.cust_sector_code = cust_sector_code;
	}

	public String getCust_nre_flg() {
		return cust_nre_flg;
	}

	public void setCust_nre_flg(String cust_nre_flg) {
		this.cust_nre_flg = cust_nre_flg;
	}

	public String getCust_title_code() {
		return cust_title_code;
	}

	public void setCust_title_code(String cust_title_code) {
		this.cust_title_code = cust_title_code;
	}

	public String getCust_sex() {
		return cust_sex;
	}

	public void setCust_sex(String cust_sex) {
		this.cust_sex = cust_sex;
	}

	public String getCust_grp() {
		return cust_grp;
	}

	public void setCust_grp(String cust_grp) {
		this.cust_grp = cust_grp;
	}

	public String getCust_const() {
		return cust_const;
	}

	public void setCust_const(String cust_const) {
		this.cust_const = cust_const;
	}

	public BigDecimal getNum_of_accounts() {
		return num_of_accounts;
	}

	public void setNum_of_accounts(BigDecimal num_of_accounts) {
		this.num_of_accounts = num_of_accounts;
	}

	public String getPreferredaddress() {
		return preferredaddress;
	}

	public void setPreferredaddress(String preferredaddress) {
		this.preferredaddress = preferredaddress;
	}

	public String getAddr_id() {
		return addr_id;
	}

	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
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

	public String getEmployerid_code() {
		return employerid_code;
	}

	public void setEmployerid_code(String employerid_code) {
		this.employerid_code = employerid_code;
	}

	public String getEmployersname() {
		return employersname;
	}

	public void setEmployersname(String employersname) {
		this.employersname = employersname;
	}

	public String getSourceofincome() {
		return sourceofincome;
	}

	public void setSourceofincome(String sourceofincome) {
		this.sourceofincome = sourceofincome;
	}

	public BigDecimal getAnnual_salary_income() {
		return annual_salary_income;
	}

	public void setAnnual_salary_income(BigDecimal annual_salary_income) {
		this.annual_salary_income = annual_salary_income;
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

	public String getPreferredemail() {
		return preferredemail;
	}

	public void setPreferredemail(String preferredemail) {
		this.preferredemail = preferredemail;
	}

	public Date getKyc_date() {
		return kyc_date;
	}

	public void setKyc_date(Date kyc_date) {
		this.kyc_date = kyc_date;
	}

	public Date getKyc_reviewdate() {
		return kyc_reviewdate;
	}

	public void setKyc_reviewdate(Date kyc_reviewdate) {
		this.kyc_reviewdate = kyc_reviewdate;
	}

	public BigDecimal getRisk_profile_score() {
		return risk_profile_score;
	}

	public void setRisk_profile_score(BigDecimal risk_profile_score) {
		this.risk_profile_score = risk_profile_score;
	}

	public String getRiskrating() {
		return riskrating;
	}

	public void setRiskrating(String riskrating) {
		this.riskrating = riskrating;
	}

	public Date getRisk_profile_expiry_date() {
		return risk_profile_expiry_date;
	}

	public void setRisk_profile_expiry_date(Date risk_profile_expiry_date) {
		this.risk_profile_expiry_date = risk_profile_expiry_date;
	}

	public String getStruserfield10() {
		return struserfield10;
	}

	public void setStruserfield10(String struserfield10) {
		this.struserfield10 = struserfield10;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBlacklisted() {
		return blacklisted;
	}

	public void setBlacklisted(String blacklisted) {
		this.blacklisted = blacklisted;
	}

	public String getBlacklistnotes() {
		return blacklistnotes;
	}

	public void setBlacklistnotes(String blacklistnotes) {
		this.blacklistnotes = blacklistnotes;
	}

	public String getBlacklist_reason() {
		return blacklist_reason;
	}

	public void setBlacklist_reason(String blacklist_reason) {
		this.blacklist_reason = blacklist_reason;
	}

	public String getPassportno() {
		return passportno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public String getPsprt_det() {
		return psprt_det;
	}

	public void setPsprt_det(String psprt_det) {
		this.psprt_det = psprt_det;
	}

	public Date getPsprt_exp_date() {
		return psprt_exp_date;
	}

	public void setPsprt_exp_date(Date psprt_exp_date) {
		this.psprt_exp_date = psprt_exp_date;
	}

	public Date getPsprt_issue_date() {
		return psprt_issue_date;
	}

	public void setPsprt_issue_date(Date psprt_issue_date) {
		this.psprt_issue_date = psprt_issue_date;
	}

	public String getFree_code_6() {
		return free_code_6;
	}

	public void setFree_code_6(String free_code_6) {
		this.free_code_6 = free_code_6;
	}

	public String getFree_code_7() {
		return free_code_7;
	}

	public void setFree_code_7(String free_code_7) {
		this.free_code_7 = free_code_7;
	}

	public Date getDate_of_incorporation() {
		return date_of_incorporation;
	}

	public void setDate_of_incorporation(Date date_of_incorporation) {
		this.date_of_incorporation = date_of_incorporation;
	}

	public Date getDate_of_commencement() {
		return date_of_commencement;
	}

	public void setDate_of_commencement(Date date_of_commencement) {
		this.date_of_commencement = date_of_commencement;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public CMG_MASTER(String primary_sol_id, String cust_id, String cif_id, Date cust_opn_date, String cust_name,
			String cust_short_name, String cust_first_name, String cust_middle_name, String cust_last_name,
			String cust_type_code, String cust_sector_code, String cust_nre_flg, String cust_title_code,
			String cust_sex, String cust_grp, String cust_const, String uniqueid, BigDecimal num_of_accounts,
			String preferredaddress, String addr_id, String address1, String address2, String address3,
			String city_code, String state_code, String cntry_code, String pin_code, String nationality,
			String residence_country, String employerid_code, String employersname, String sourceofincome,
			BigDecimal annual_salary_income, Date cust_dob, String pan, String ssn, String nat_id_card_num,
			String preferredphone, String preferredemail, Date kyc_date, Date kyc_reviewdate,
			BigDecimal risk_profile_score, String riskrating, Date risk_profile_expiry_date, String struserfield10,
			String occupation, String blacklisted, String blacklistnotes, String blacklist_reason, String passportno,
			String psprt_det, Date psprt_exp_date, Date psprt_issue_date, String free_code_6, String free_code_7,
			Date date_of_incorporation, Date date_of_commencement, String registration_number, String country_of_birth,
			BigDecimal annualrevenue, String placeofbirth, String subsegment, String type, Date dtdate1, String value,
			String localetext, String segmentation_class, String preferredname, String phone_home,
			String cust_stat_code, Date cust_stat_chg_date, String employment_status, String status,
			String struserfield11, String struserfield13, String struserfield15, String free_text_1, String free_code_2,
			String active_customer_flg, Date customer_status_date, String residing_country) {
		super();
		this.primary_sol_id = primary_sol_id;
		this.cust_id = cust_id;
		this.cif_id = cif_id;
		this.cust_opn_date = cust_opn_date;
		this.cust_name = cust_name;
		this.cust_short_name = cust_short_name;
		this.cust_first_name = cust_first_name;
		this.cust_middle_name = cust_middle_name;
		this.cust_last_name = cust_last_name;
		this.cust_type_code = cust_type_code;
		this.cust_sector_code = cust_sector_code;
		this.cust_nre_flg = cust_nre_flg;
		this.cust_title_code = cust_title_code;
		this.cust_sex = cust_sex;
		this.cust_grp = cust_grp;
		this.cust_const = cust_const;
		this.uniqueid = uniqueid;
		this.num_of_accounts = num_of_accounts;
		this.preferredaddress = preferredaddress;
		this.addr_id = addr_id;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city_code = city_code;
		this.state_code = state_code;
		this.cntry_code = cntry_code;
		this.pin_code = pin_code;
		this.nationality = nationality;
		this.residence_country = residence_country;
		this.employerid_code = employerid_code;
		this.employersname = employersname;
		this.sourceofincome = sourceofincome;
		this.annual_salary_income = annual_salary_income;
		this.cust_dob = cust_dob;
		this.pan = pan;
		this.ssn = ssn;
		this.nat_id_card_num = nat_id_card_num;
		this.preferredphone = preferredphone;
		this.preferredemail = preferredemail;
		this.kyc_date = kyc_date;
		this.kyc_reviewdate = kyc_reviewdate;
		this.risk_profile_score = risk_profile_score;
		this.riskrating = riskrating;
		this.risk_profile_expiry_date = risk_profile_expiry_date;
		this.struserfield10 = struserfield10;
		this.occupation = occupation;
		this.blacklisted = blacklisted;
		this.blacklistnotes = blacklistnotes;
		this.blacklist_reason = blacklist_reason;
		this.passportno = passportno;
		this.psprt_det = psprt_det;
		this.psprt_exp_date = psprt_exp_date;
		this.psprt_issue_date = psprt_issue_date;
		this.free_code_6 = free_code_6;
		this.free_code_7 = free_code_7;
		this.date_of_incorporation = date_of_incorporation;
		this.date_of_commencement = date_of_commencement;
		this.registration_number = registration_number;
		this.country_of_birth = country_of_birth;
		this.annualrevenue = annualrevenue;
		this.placeofbirth = placeofbirth;
		this.subsegment = subsegment;
		this.type = type;
		this.dtdate1 = dtdate1;
		this.value = value;
		this.localetext = localetext;
		this.segmentation_class = segmentation_class;
		this.preferredname = preferredname;
		this.phone_home = phone_home;
		this.cust_stat_code = cust_stat_code;
		this.cust_stat_chg_date = cust_stat_chg_date;
		this.employment_status = employment_status;
		this.status = status;
		this.struserfield11 = struserfield11;
		this.struserfield13 = struserfield13;
		this.struserfield15 = struserfield15;
		this.free_text_1 = free_text_1;
		this.free_code_2 = free_code_2;
		this.active_customer_flg = active_customer_flg;
		this.customer_status_date = customer_status_date;
		this.residing_country = residing_country;
	}

	public CMG_MASTER() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active_customer_flg == null) ? 0 : active_customer_flg.hashCode());
		result = prime * result + ((addr_id == null) ? 0 : addr_id.hashCode());
		result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + ((address3 == null) ? 0 : address3.hashCode());
		result = prime * result + ((annual_salary_income == null) ? 0 : annual_salary_income.hashCode());
		result = prime * result + ((annualrevenue == null) ? 0 : annualrevenue.hashCode());
		result = prime * result + ((blacklist_reason == null) ? 0 : blacklist_reason.hashCode());
		result = prime * result + ((blacklisted == null) ? 0 : blacklisted.hashCode());
		result = prime * result + ((blacklistnotes == null) ? 0 : blacklistnotes.hashCode());
		result = prime * result + ((cif_id == null) ? 0 : cif_id.hashCode());
		result = prime * result + ((city_code == null) ? 0 : city_code.hashCode());
		result = prime * result + ((cntry_code == null) ? 0 : cntry_code.hashCode());
		result = prime * result + ((country_of_birth == null) ? 0 : country_of_birth.hashCode());
		result = prime * result + ((cust_const == null) ? 0 : cust_const.hashCode());
		result = prime * result + ((cust_dob == null) ? 0 : cust_dob.hashCode());
		result = prime * result + ((cust_first_name == null) ? 0 : cust_first_name.hashCode());
		result = prime * result + ((cust_grp == null) ? 0 : cust_grp.hashCode());
		result = prime * result + ((cust_id == null) ? 0 : cust_id.hashCode());
		result = prime * result + ((cust_last_name == null) ? 0 : cust_last_name.hashCode());
		result = prime * result + ((cust_middle_name == null) ? 0 : cust_middle_name.hashCode());
		result = prime * result + ((cust_name == null) ? 0 : cust_name.hashCode());
		result = prime * result + ((cust_nre_flg == null) ? 0 : cust_nre_flg.hashCode());
		result = prime * result + ((cust_opn_date == null) ? 0 : cust_opn_date.hashCode());
		result = prime * result + ((cust_sector_code == null) ? 0 : cust_sector_code.hashCode());
		result = prime * result + ((cust_sex == null) ? 0 : cust_sex.hashCode());
		result = prime * result + ((cust_short_name == null) ? 0 : cust_short_name.hashCode());
		result = prime * result + ((cust_stat_chg_date == null) ? 0 : cust_stat_chg_date.hashCode());
		result = prime * result + ((cust_stat_code == null) ? 0 : cust_stat_code.hashCode());
		result = prime * result + ((cust_title_code == null) ? 0 : cust_title_code.hashCode());
		result = prime * result + ((cust_type_code == null) ? 0 : cust_type_code.hashCode());
		result = prime * result + ((customer_status_date == null) ? 0 : customer_status_date.hashCode());
		result = prime * result + ((date_of_commencement == null) ? 0 : date_of_commencement.hashCode());
		result = prime * result + ((date_of_incorporation == null) ? 0 : date_of_incorporation.hashCode());
		result = prime * result + ((dtdate1 == null) ? 0 : dtdate1.hashCode());
		result = prime * result + ((employerid_code == null) ? 0 : employerid_code.hashCode());
		result = prime * result + ((employersname == null) ? 0 : employersname.hashCode());
		result = prime * result + ((employment_status == null) ? 0 : employment_status.hashCode());
		result = prime * result + ((free_code_2 == null) ? 0 : free_code_2.hashCode());
		result = prime * result + ((free_code_6 == null) ? 0 : free_code_6.hashCode());
		result = prime * result + ((free_code_7 == null) ? 0 : free_code_7.hashCode());
		result = prime * result + ((free_text_1 == null) ? 0 : free_text_1.hashCode());
		result = prime * result + ((kyc_date == null) ? 0 : kyc_date.hashCode());
		result = prime * result + ((kyc_reviewdate == null) ? 0 : kyc_reviewdate.hashCode());
		result = prime * result + ((localetext == null) ? 0 : localetext.hashCode());
		result = prime * result + ((nat_id_card_num == null) ? 0 : nat_id_card_num.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((num_of_accounts == null) ? 0 : num_of_accounts.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		result = prime * result + ((pan == null) ? 0 : pan.hashCode());
		result = prime * result + ((passportno == null) ? 0 : passportno.hashCode());
		result = prime * result + ((phone_home == null) ? 0 : phone_home.hashCode());
		result = prime * result + ((pin_code == null) ? 0 : pin_code.hashCode());
		result = prime * result + ((placeofbirth == null) ? 0 : placeofbirth.hashCode());
		result = prime * result + ((preferredaddress == null) ? 0 : preferredaddress.hashCode());
		result = prime * result + ((preferredemail == null) ? 0 : preferredemail.hashCode());
		result = prime * result + ((preferredname == null) ? 0 : preferredname.hashCode());
		result = prime * result + ((preferredphone == null) ? 0 : preferredphone.hashCode());
		result = prime * result + ((primary_sol_id == null) ? 0 : primary_sol_id.hashCode());
		result = prime * result + ((psprt_det == null) ? 0 : psprt_det.hashCode());
		result = prime * result + ((psprt_exp_date == null) ? 0 : psprt_exp_date.hashCode());
		result = prime * result + ((psprt_issue_date == null) ? 0 : psprt_issue_date.hashCode());
		result = prime * result + ((registration_number == null) ? 0 : registration_number.hashCode());
		result = prime * result + ((residence_country == null) ? 0 : residence_country.hashCode());
		result = prime * result + ((risk_profile_expiry_date == null) ? 0 : risk_profile_expiry_date.hashCode());
		result = prime * result + ((risk_profile_score == null) ? 0 : risk_profile_score.hashCode());
		result = prime * result + ((riskrating == null) ? 0 : riskrating.hashCode());
		result = prime * result + ((segmentation_class == null) ? 0 : segmentation_class.hashCode());
		result = prime * result + ((sourceofincome == null) ? 0 : sourceofincome.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		result = prime * result + ((state_code == null) ? 0 : state_code.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((struserfield10 == null) ? 0 : struserfield10.hashCode());
		result = prime * result + ((struserfield11 == null) ? 0 : struserfield11.hashCode());
		result = prime * result + ((struserfield13 == null) ? 0 : struserfield13.hashCode());
		result = prime * result + ((struserfield15 == null) ? 0 : struserfield15.hashCode());
		result = prime * result + ((subsegment == null) ? 0 : subsegment.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uniqueid == null) ? 0 : uniqueid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMG_MASTER other = (CMG_MASTER) obj;
		if (active_customer_flg == null) {
			if (other.active_customer_flg != null)
				return false;
		} else if (!active_customer_flg.equals(other.active_customer_flg))
			return false;
		if (addr_id == null) {
			if (other.addr_id != null)
				return false;
		} else if (!addr_id.equals(other.addr_id))
			return false;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
			return false;
		if (address3 == null) {
			if (other.address3 != null)
				return false;
		} else if (!address3.equals(other.address3))
			return false;
		if (annual_salary_income == null) {
			if (other.annual_salary_income != null)
				return false;
		} else if (!annual_salary_income.equals(other.annual_salary_income))
			return false;
		if (annualrevenue == null) {
			if (other.annualrevenue != null)
				return false;
		} else if (!annualrevenue.equals(other.annualrevenue))
			return false;
		if (blacklist_reason == null) {
			if (other.blacklist_reason != null)
				return false;
		} else if (!blacklist_reason.equals(other.blacklist_reason))
			return false;
		if (blacklisted == null) {
			if (other.blacklisted != null)
				return false;
		} else if (!blacklisted.equals(other.blacklisted))
			return false;
		if (blacklistnotes == null) {
			if (other.blacklistnotes != null)
				return false;
		} else if (!blacklistnotes.equals(other.blacklistnotes))
			return false;
		if (cif_id == null) {
			if (other.cif_id != null)
				return false;
		} else if (!cif_id.equals(other.cif_id))
			return false;
		if (city_code == null) {
			if (other.city_code != null)
				return false;
		} else if (!city_code.equals(other.city_code))
			return false;
		if (cntry_code == null) {
			if (other.cntry_code != null)
				return false;
		} else if (!cntry_code.equals(other.cntry_code))
			return false;
		if (country_of_birth == null) {
			if (other.country_of_birth != null)
				return false;
		} else if (!country_of_birth.equals(other.country_of_birth))
			return false;
		if (cust_const == null) {
			if (other.cust_const != null)
				return false;
		} else if (!cust_const.equals(other.cust_const))
			return false;
		if (cust_dob == null) {
			if (other.cust_dob != null)
				return false;
		} else if (!cust_dob.equals(other.cust_dob))
			return false;
		if (cust_first_name == null) {
			if (other.cust_first_name != null)
				return false;
		} else if (!cust_first_name.equals(other.cust_first_name))
			return false;
		if (cust_grp == null) {
			if (other.cust_grp != null)
				return false;
		} else if (!cust_grp.equals(other.cust_grp))
			return false;
		if (cust_id == null) {
			if (other.cust_id != null)
				return false;
		} else if (!cust_id.equals(other.cust_id))
			return false;
		if (cust_last_name == null) {
			if (other.cust_last_name != null)
				return false;
		} else if (!cust_last_name.equals(other.cust_last_name))
			return false;
		if (cust_middle_name == null) {
			if (other.cust_middle_name != null)
				return false;
		} else if (!cust_middle_name.equals(other.cust_middle_name))
			return false;
		if (cust_name == null) {
			if (other.cust_name != null)
				return false;
		} else if (!cust_name.equals(other.cust_name))
			return false;
		if (cust_nre_flg == null) {
			if (other.cust_nre_flg != null)
				return false;
		} else if (!cust_nre_flg.equals(other.cust_nre_flg))
			return false;
		if (cust_opn_date == null) {
			if (other.cust_opn_date != null)
				return false;
		} else if (!cust_opn_date.equals(other.cust_opn_date))
			return false;
		if (cust_sector_code == null) {
			if (other.cust_sector_code != null)
				return false;
		} else if (!cust_sector_code.equals(other.cust_sector_code))
			return false;
		if (cust_sex == null) {
			if (other.cust_sex != null)
				return false;
		} else if (!cust_sex.equals(other.cust_sex))
			return false;
		if (cust_short_name == null) {
			if (other.cust_short_name != null)
				return false;
		} else if (!cust_short_name.equals(other.cust_short_name))
			return false;
		if (cust_stat_chg_date == null) {
			if (other.cust_stat_chg_date != null)
				return false;
		} else if (!cust_stat_chg_date.equals(other.cust_stat_chg_date))
			return false;
		if (cust_stat_code == null) {
			if (other.cust_stat_code != null)
				return false;
		} else if (!cust_stat_code.equals(other.cust_stat_code))
			return false;
		if (cust_title_code == null) {
			if (other.cust_title_code != null)
				return false;
		} else if (!cust_title_code.equals(other.cust_title_code))
			return false;
		if (cust_type_code == null) {
			if (other.cust_type_code != null)
				return false;
		} else if (!cust_type_code.equals(other.cust_type_code))
			return false;
		if (customer_status_date == null) {
			if (other.customer_status_date != null)
				return false;
		} else if (!customer_status_date.equals(other.customer_status_date))
			return false;
		if (date_of_commencement == null) {
			if (other.date_of_commencement != null)
				return false;
		} else if (!date_of_commencement.equals(other.date_of_commencement))
			return false;
		if (date_of_incorporation == null) {
			if (other.date_of_incorporation != null)
				return false;
		} else if (!date_of_incorporation.equals(other.date_of_incorporation))
			return false;
		if (dtdate1 == null) {
			if (other.dtdate1 != null)
				return false;
		} else if (!dtdate1.equals(other.dtdate1))
			return false;
		if (employerid_code == null) {
			if (other.employerid_code != null)
				return false;
		} else if (!employerid_code.equals(other.employerid_code))
			return false;
		if (employersname == null) {
			if (other.employersname != null)
				return false;
		} else if (!employersname.equals(other.employersname))
			return false;
		if (employment_status == null) {
			if (other.employment_status != null)
				return false;
		} else if (!employment_status.equals(other.employment_status))
			return false;
		if (free_code_2 == null) {
			if (other.free_code_2 != null)
				return false;
		} else if (!free_code_2.equals(other.free_code_2))
			return false;
		if (free_code_6 == null) {
			if (other.free_code_6 != null)
				return false;
		} else if (!free_code_6.equals(other.free_code_6))
			return false;
		if (free_code_7 == null) {
			if (other.free_code_7 != null)
				return false;
		} else if (!free_code_7.equals(other.free_code_7))
			return false;
		if (free_text_1 == null) {
			if (other.free_text_1 != null)
				return false;
		} else if (!free_text_1.equals(other.free_text_1))
			return false;
		if (kyc_date == null) {
			if (other.kyc_date != null)
				return false;
		} else if (!kyc_date.equals(other.kyc_date))
			return false;
		if (kyc_reviewdate == null) {
			if (other.kyc_reviewdate != null)
				return false;
		} else if (!kyc_reviewdate.equals(other.kyc_reviewdate))
			return false;
		if (localetext == null) {
			if (other.localetext != null)
				return false;
		} else if (!localetext.equals(other.localetext))
			return false;
		if (nat_id_card_num == null) {
			if (other.nat_id_card_num != null)
				return false;
		} else if (!nat_id_card_num.equals(other.nat_id_card_num))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (num_of_accounts == null) {
			if (other.num_of_accounts != null)
				return false;
		} else if (!num_of_accounts.equals(other.num_of_accounts))
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		if (pan == null) {
			if (other.pan != null)
				return false;
		} else if (!pan.equals(other.pan))
			return false;
		if (passportno == null) {
			if (other.passportno != null)
				return false;
		} else if (!passportno.equals(other.passportno))
			return false;
		if (phone_home == null) {
			if (other.phone_home != null)
				return false;
		} else if (!phone_home.equals(other.phone_home))
			return false;
		if (pin_code == null) {
			if (other.pin_code != null)
				return false;
		} else if (!pin_code.equals(other.pin_code))
			return false;
		if (placeofbirth == null) {
			if (other.placeofbirth != null)
				return false;
		} else if (!placeofbirth.equals(other.placeofbirth))
			return false;
		if (preferredaddress == null) {
			if (other.preferredaddress != null)
				return false;
		} else if (!preferredaddress.equals(other.preferredaddress))
			return false;
		if (preferredemail == null) {
			if (other.preferredemail != null)
				return false;
		} else if (!preferredemail.equals(other.preferredemail))
			return false;
		if (preferredname == null) {
			if (other.preferredname != null)
				return false;
		} else if (!preferredname.equals(other.preferredname))
			return false;
		if (preferredphone == null) {
			if (other.preferredphone != null)
				return false;
		} else if (!preferredphone.equals(other.preferredphone))
			return false;
		if (primary_sol_id == null) {
			if (other.primary_sol_id != null)
				return false;
		} else if (!primary_sol_id.equals(other.primary_sol_id))
			return false;
		if (psprt_det == null) {
			if (other.psprt_det != null)
				return false;
		} else if (!psprt_det.equals(other.psprt_det))
			return false;
		if (psprt_exp_date == null) {
			if (other.psprt_exp_date != null)
				return false;
		} else if (!psprt_exp_date.equals(other.psprt_exp_date))
			return false;
		if (psprt_issue_date == null) {
			if (other.psprt_issue_date != null)
				return false;
		} else if (!psprt_issue_date.equals(other.psprt_issue_date))
			return false;
		if (registration_number == null) {
			if (other.registration_number != null)
				return false;
		} else if (!registration_number.equals(other.registration_number))
			return false;
		if (residence_country == null) {
			if (other.residence_country != null)
				return false;
		} else if (!residence_country.equals(other.residence_country))
			return false;
		if (risk_profile_expiry_date == null) {
			if (other.risk_profile_expiry_date != null)
				return false;
		} else if (!risk_profile_expiry_date.equals(other.risk_profile_expiry_date))
			return false;
		if (risk_profile_score == null) {
			if (other.risk_profile_score != null)
				return false;
		} else if (!risk_profile_score.equals(other.risk_profile_score))
			return false;
		if (riskrating == null) {
			if (other.riskrating != null)
				return false;
		} else if (!riskrating.equals(other.riskrating))
			return false;
		if (segmentation_class == null) {
			if (other.segmentation_class != null)
				return false;
		} else if (!segmentation_class.equals(other.segmentation_class))
			return false;
		if (sourceofincome == null) {
			if (other.sourceofincome != null)
				return false;
		} else if (!sourceofincome.equals(other.sourceofincome))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		if (state_code == null) {
			if (other.state_code != null)
				return false;
		} else if (!state_code.equals(other.state_code))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (struserfield10 == null) {
			if (other.struserfield10 != null)
				return false;
		} else if (!struserfield10.equals(other.struserfield10))
			return false;
		if (struserfield11 == null) {
			if (other.struserfield11 != null)
				return false;
		} else if (!struserfield11.equals(other.struserfield11))
			return false;
		if (struserfield13 == null) {
			if (other.struserfield13 != null)
				return false;
		} else if (!struserfield13.equals(other.struserfield13))
			return false;
		if (struserfield15 == null) {
			if (other.struserfield15 != null)
				return false;
		} else if (!struserfield15.equals(other.struserfield15))
			return false;
		if (subsegment == null) {
			if (other.subsegment != null)
				return false;
		} else if (!subsegment.equals(other.subsegment))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uniqueid == null) {
			if (other.uniqueid != null)
				return false;
		} else if (!uniqueid.equals(other.uniqueid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}

package com.bornfire.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_THIRD_PARTY_TRAN_TABLE")
public class BAMLThirdPartyTran {

	@Id
	private String srl_no;
	private String cust_id;
	private String cust_first_name;
	private String cust_last_name;
	private String cust_full_name;
	private String nid;
	private String risk_category;
	private String place_of_birth;
	private String country_of_residence;
	private String cell_phone;
	private String home_phone;
	private String department;
	private String occupation;
	private String loa_flg;
	private String letter_of_authorization;
	private String tp_payment_flg;
	private String tp_first_name;
	private String tp_last_name;
	private String tp_full_name;
	private String tp_nid;
	private String tp_pob;
	private String tp_country_of_residence;
	private String relationship;
	private String reasons_for_payment;
	private String source_of_funds;
	private String evidence_sof_flg;
	private String mode_of_payment;
	private String prod_type;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date payment_date;
	private BigDecimal amount_paid;
	private String del_flg;
	private String entity_flg;
	private String entry_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date entry_time;
	private String modify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modify_time;
	private String verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date verify_time;
	private String modify_flg;
	private String tran_rearks;
	private String acct_no;
	private String acct_name;
	private String tran_id;
	private String part_tran_type;
	private String payment_flg;
	private String chq_bank_name;
	private String chq_no;
	private String address1;
	private String address2;
	private String black_list_chk_nid;
	private String black_list_chk_name;
	private String cust_short_name;
	private String black_list_fin;
	private String pep_list_fin;
	private String unsc_list_fin;
	private String tp_pep_list;
	private String tp_unsc_list;
	private String hnwi_list_fin;
	private String tp_hnwi_list;
	private String abandoned_fund_list_fin;
	private String tp_abandoned_fund_list;
	
	
	
	
	
	
	

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	
	public String getBlack_list_chk_nid() {
		return black_list_chk_nid;
	}

	public String getBlack_list_chk_name() {
		return black_list_chk_name;
	}

	public void setBlack_list_chk_nid(String black_list_chk_nid) {
		this.black_list_chk_nid = black_list_chk_nid;
	}

	public void setBlack_list_chk_name(String black_list_chk_name) {
		this.black_list_chk_name = black_list_chk_name;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	

	public String getSrl_no() {
		return srl_no;
	}

	public String getCust_id() {
		return cust_id;
	}

	public String getCust_first_name() {
		return cust_first_name;
	}

	public String getCust_last_name() {
		return cust_last_name;
	}

	public String getCust_full_name() {
		return cust_full_name;
	}

	public String getNid() {
		return nid;
	}

	public String getRisk_category() {
		return risk_category;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public String getCountry_of_residence() {
		return country_of_residence;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public String getDepartment() {
		return department;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getLoa_flg() {
		return loa_flg;
	}

	public String getLetter_of_authorization() {
		return letter_of_authorization;
	}

	public String getTp_payment_flg() {
		return tp_payment_flg;
	}

	public String getTp_first_name() {
		return tp_first_name;
	}

	public String getTp_last_name() {
		return tp_last_name;
	}

	public String getTp_full_name() {
		return tp_full_name;
	}

	public String getTp_nid() {
		return tp_nid;
	}

	public String getTp_pob() {
		return tp_pob;
	}

	public String getTp_country_of_residence() {
		return tp_country_of_residence;
	}

	public String getRelationship() {
		return relationship;
	}

	public String getReasons_for_payment() {
		return reasons_for_payment;
	}

	public String getSource_of_funds() {
		return source_of_funds;
	}

	public String getEvidence_sof_flg() {
		return evidence_sof_flg;
	}

	public String getMode_of_payment() {
		return mode_of_payment;
	}

	public String getProd_type() {
		return prod_type;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public BigDecimal getAmount_paid() {
		return amount_paid;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public String getModify_user() {
		return modify_user;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public String getTran_rearks() {
		return tran_rearks;
	}

	public String getAcct_no() {
		return acct_no;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public String getTran_id() {
		return tran_id;
	}

	public String getPart_tran_type() {
		return part_tran_type;
	}

	public String getPayment_flg() {
		return payment_flg;
	}

	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public void setCust_first_name(String cust_first_name) {
		this.cust_first_name = cust_first_name;
	}

	public void setCust_last_name(String cust_last_name) {
		this.cust_last_name = cust_last_name;
	}

	public void setCust_full_name(String cust_full_name) {
		this.cust_full_name = cust_full_name;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}

	public void setCountry_of_residence(String country_of_residence) {
		this.country_of_residence = country_of_residence;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setLoa_flg(String loa_flg) {
		this.loa_flg = loa_flg;
	}

	public void setLetter_of_authorization(String letter_of_authorization) {
		this.letter_of_authorization = letter_of_authorization;
	}

	public void setTp_payment_flg(String tp_payment_flg) {
		this.tp_payment_flg = tp_payment_flg;
	}

	public void setTp_first_name(String tp_first_name) {
		this.tp_first_name = tp_first_name;
	}

	public void setTp_last_name(String tp_last_name) {
		this.tp_last_name = tp_last_name;
	}

	public void setTp_full_name(String tp_full_name) {
		this.tp_full_name = tp_full_name;
	}

	public void setTp_nid(String tp_nid) {
		this.tp_nid = tp_nid;
	}

	public void setTp_pob(String tp_pob) {
		this.tp_pob = tp_pob;
	}

	public void setTp_country_of_residence(String tp_country_of_residence) {
		this.tp_country_of_residence = tp_country_of_residence;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public void setReasons_for_payment(String reasons_for_payment) {
		this.reasons_for_payment = reasons_for_payment;
	}

	public void setSource_of_funds(String source_of_funds) {
		this.source_of_funds = source_of_funds;
	}

	public void setEvidence_sof_flg(String evidence_sof_flg) {
		this.evidence_sof_flg = evidence_sof_flg;
	}

	public void setMode_of_payment(String mode_of_payment) {
		this.mode_of_payment = mode_of_payment;
	}

	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public void setTran_rearks(String tran_rearks) {
		this.tran_rearks = tran_rearks;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}

	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}

	public void setPayment_flg(String payment_flg) {
		this.payment_flg = payment_flg;
	}

	public String getChq_bank_name() {
		return chq_bank_name;
	}

	public String getChq_no() {
		return chq_no;
	}

	public void setChq_bank_name(String chq_bank_name) {
		this.chq_bank_name = chq_bank_name;
	}

	public void setChq_no(String chq_no) {
		this.chq_no = chq_no;
	}



	


	public String getCust_short_name() {
		return cust_short_name;
	}

	public void setCust_short_name(String cust_short_name) {
		this.cust_short_name = cust_short_name;
	}

	

	public String getBlack_list_fin() {
		return black_list_fin;
	}

	public String getPep_list_fin() {
		return pep_list_fin;
	}

	public String getUnsc_list_fin() {
		return unsc_list_fin;
	}

	public String getTp_pep_list() {
		return tp_pep_list;
	}

	public String getTp_unsc_list() {
		return tp_unsc_list;
	}

	public String getHnwi_list_fin() {
		return hnwi_list_fin;
	}

	public String getTp_hnwi_list() {
		return tp_hnwi_list;
	}

	public String getAbandoned_fund_list_fin() {
		return abandoned_fund_list_fin;
	}

	public String getTp_abandoned_fund_list() {
		return tp_abandoned_fund_list;
	}

	public void setBlack_list_fin(String black_list_fin) {
		this.black_list_fin = black_list_fin;
	}

	public void setPep_list_fin(String pep_list_fin) {
		this.pep_list_fin = pep_list_fin;
	}

	public void setUnsc_list_fin(String unsc_list_fin) {
		this.unsc_list_fin = unsc_list_fin;
	}

	public void setTp_pep_list(String tp_pep_list) {
		this.tp_pep_list = tp_pep_list;
	}

	public void setTp_unsc_list(String tp_unsc_list) {
		this.tp_unsc_list = tp_unsc_list;
	}

	public void setHnwi_list_fin(String hnwi_list_fin) {
		this.hnwi_list_fin = hnwi_list_fin;
	}

	public void setTp_hnwi_list(String tp_hnwi_list) {
		this.tp_hnwi_list = tp_hnwi_list;
	}

	public void setAbandoned_fund_list_fin(String abandoned_fund_list_fin) {
		this.abandoned_fund_list_fin = abandoned_fund_list_fin;
	}

	public void setTp_abandoned_fund_list(String tp_abandoned_fund_list) {
		this.tp_abandoned_fund_list = tp_abandoned_fund_list;
	}

	
	public BAMLThirdPartyTran(String srl_no, String cust_id, String cust_first_name, String cust_last_name,
			String cust_full_name, String nid, String risk_category, String place_of_birth, String country_of_residence,
			String cell_phone, String home_phone, String department, String occupation, String loa_flg,
			String letter_of_authorization, String tp_payment_flg, String tp_first_name, String tp_last_name,
			String tp_full_name, String tp_nid, String tp_pob, String tp_country_of_residence, String relationship,
			String reasons_for_payment, String source_of_funds, String evidence_sof_flg, String mode_of_payment,
			String prod_type, Date payment_date, BigDecimal amount_paid, String del_flg, String entity_flg,
			String entry_user, Date entry_time, String modify_user, Date modify_time, String verify_user,
			Date verify_time, String modify_flg, String tran_rearks, String acct_no, String acct_name, String tran_id,
			String part_tran_type, String payment_flg, String chq_bank_name, String chq_no, String address1,
			String address2, String black_list_chk_nid, String black_list_chk_name, String cust_short_name,
			String black_list_fin, String pep_list_fin, String unsc_list_fin, String tp_pep_list, String tp_unsc_list,
			String hnwi_list_fin, String tp_hnwi_list, String abandoned_fund_list_fin, String tp_abandoned_fund_list) {
		super();
		this.srl_no = srl_no;
		this.cust_id = cust_id;
		this.cust_first_name = cust_first_name;
		this.cust_last_name = cust_last_name;
		this.cust_full_name = cust_full_name;
		this.nid = nid;
		this.risk_category = risk_category;
		this.place_of_birth = place_of_birth;
		this.country_of_residence = country_of_residence;
		this.cell_phone = cell_phone;
		this.home_phone = home_phone;
		this.department = department;
		this.occupation = occupation;
		this.loa_flg = loa_flg;
		this.letter_of_authorization = letter_of_authorization;
		this.tp_payment_flg = tp_payment_flg;
		this.tp_first_name = tp_first_name;
		this.tp_last_name = tp_last_name;
		this.tp_full_name = tp_full_name;
		this.tp_nid = tp_nid;
		this.tp_pob = tp_pob;
		this.tp_country_of_residence = tp_country_of_residence;
		this.relationship = relationship;
		this.reasons_for_payment = reasons_for_payment;
		this.source_of_funds = source_of_funds;
		this.evidence_sof_flg = evidence_sof_flg;
		this.mode_of_payment = mode_of_payment;
		this.prod_type = prod_type;
		this.payment_date = payment_date;
		this.amount_paid = amount_paid;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
		this.modify_flg = modify_flg;
		this.tran_rearks = tran_rearks;
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.tran_id = tran_id;
		this.part_tran_type = part_tran_type;
		this.payment_flg = payment_flg;
		this.chq_bank_name = chq_bank_name;
		this.chq_no = chq_no;
		this.address1 = address1;
		this.address2 = address2;
		this.black_list_chk_nid = black_list_chk_nid;
		this.black_list_chk_name = black_list_chk_name;
		this.cust_short_name = cust_short_name;
		this.black_list_fin = black_list_fin;
		this.pep_list_fin = pep_list_fin;
		this.unsc_list_fin = unsc_list_fin;
		this.tp_pep_list = tp_pep_list;
		this.tp_unsc_list = tp_unsc_list;
		this.hnwi_list_fin = hnwi_list_fin;
		this.tp_hnwi_list = tp_hnwi_list;
		this.abandoned_fund_list_fin = abandoned_fund_list_fin;
		this.tp_abandoned_fund_list = tp_abandoned_fund_list;
	}

	public BAMLThirdPartyTran() {
		super();
		// TODO Auto-generated constructor stub
	}

}

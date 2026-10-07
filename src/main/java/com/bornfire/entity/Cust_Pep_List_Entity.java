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
@Table(name="BAML_CUST_PEP_LIST")
public class Cust_Pep_List_Entity implements Serializable{
	public Cust_Pep_List_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	private String acid;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date pep_date;
	
	private String pep_type;
	
	
	
	private String cif_id;
	private String cust_short_name;
	private String cust_name;
	private String nat_id_card_num;
	private String risk_category;
	private String pep_desc;
	private String cust_position;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date membership_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date date_of_pep;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date date_of_resig;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date date_of_close;
	
	
	private String resig_reasons;
	private String active_prod_type;
	private String schm_type;
	private String schm_code;
	private String foracid;

	private String remarks;
	private String remarks2;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date acct_open_date;

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
	
	
	

	public String getCif_id() {
		return cif_id;
	}


	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}


	public String getCust_name() {
		return cust_name;
	}


	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}


	public String getNat_id_card_num() {
		return nat_id_card_num;
	}


	public void setNat_id_card_num(String nat_id_card_num) {
		this.nat_id_card_num = nat_id_card_num;
	}


	public String getRisk_category() {
		return risk_category;
	}


	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}


	public String getPep_desc() {
		return pep_desc;
	}


	public void setPep_desc(String pep_desc) {
		this.pep_desc = pep_desc;
	}


	public String getCust_position() {
		return cust_position;
	}


	public void setCust_position(String cust_position) {
		this.cust_position = cust_position;
	}


	public Date getMembership_date() {
		return membership_date;
	}


	public void setMembership_date(Date membership_date) {
		this.membership_date = membership_date;
	}


	public Date getDate_of_pep() {
		return date_of_pep;
	}


	public void setDate_of_pep(Date date_of_pep) {
		this.date_of_pep = date_of_pep;
	}


	public Date getDate_of_resig() {
		return date_of_resig;
	}


	public void setDate_of_resig(Date date_of_resig) {
		this.date_of_resig = date_of_resig;
	}


	public Date getDate_of_close() {
		return date_of_close;
	}


	public void setDate_of_close(Date date_of_close) {
		this.date_of_close = date_of_close;
	}


	public String getResig_reasons() {
		return resig_reasons;
	}


	public void setResig_reasons(String resig_reasons) {
		this.resig_reasons = resig_reasons;
	}


	public String getActive_prod_type() {
		return active_prod_type;
	}


	public void setActive_prod_type(String active_prod_type) {
		this.active_prod_type = active_prod_type;
	}


	public String getSchm_type() {
		return schm_type;
	}


	public void setSchm_type(String schm_type) {
		this.schm_type = schm_type;
	}


	public String getSchm_code() {
		return schm_code;
	}


	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
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


	public Date getAcct_open_date() {
		return acct_open_date;
	}


	public void setAcct_open_date(Date acct_open_date) {
		this.acct_open_date = acct_open_date;
	}


	public String getCust_short_name() {
		return cust_short_name;
	}


	public void setCust_short_name(String cust_short_name) {
		this.cust_short_name = cust_short_name;
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


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Cust_Pep_List_Entity(String cif_id,String cust_surname,String cust_name,String nic,
			String risk_cat,String pep_desc ,Date membership_date1 ,Date dateof_pep,Date date_of_resgn,Date date_of_close,
			String resgn_reason,String act_prod_type,String scheme_type,String scheme_code,String acc_id,Date acc_opn_date,String pep_type,String remarks,Date pep_date,String cust_position,
			String entry_user,Date entry_time,String entity_flg,String del_flg ,String acid) { 
		super();
		
		this.cif_id=cif_id;
		this.cust_short_name=cust_surname;
		this.cust_name=cust_name;
		this.nat_id_card_num=nic;
		this.risk_category=risk_cat;
		this.pep_desc=pep_desc;
		this.membership_date=membership_date1;
		this.date_of_pep=dateof_pep;
		this.date_of_resig=date_of_resgn;
		this.date_of_close=date_of_close;
		this.resig_reasons=resgn_reason;
		this.active_prod_type=act_prod_type;
		this.schm_type=scheme_type;
		this.schm_code=scheme_code;
		this.foracid=acc_id;
		this.acct_open_date=acc_opn_date;
		this.pep_type=pep_type;
		this.remarks=remarks;
		this.pep_date=pep_date;
		this.aml_entry_user=entry_user;
		this.aml_entry_time=entry_time;
		this.entity_flag=entity_flg;
		this.del_flag=del_flg;
		this.cust_position=cust_position;
		this.acid = acid;
	}


//	public String getPep_id() {
//		return pep_id;
//	}
//
//
//	public void setPep_id(String pep_id) {
//		this.pep_id = pep_id;
//	}


	public Date getPep_date() {
		return pep_date;
	}


	public void setPep_date(Date pep_date) {
		this.pep_date = pep_date;
	}


	public String getPep_type() {
		return pep_type;
	}


	public void setPep_type(String pep_type) {
		this.pep_type = pep_type;
	}


	public String getRemarks2() {
		return remarks2;
	}


	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	}

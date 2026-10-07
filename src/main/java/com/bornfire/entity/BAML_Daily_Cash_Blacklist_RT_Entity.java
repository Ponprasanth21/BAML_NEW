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
@Table(name="BAML_DAILY_CASH_BLACKLIST_RPT_TEMP_TABLE")
public class BAML_Daily_Cash_Blacklist_RT_Entity implements Serializable{
	public BAML_Daily_Cash_Blacklist_RT_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private BigDecimal sl;
	private String cif;
	private String cust_id;
	private String name;
	private String nid;
	private String risk_category;
	private String occupation;
	private String hnwi_name;
	private String black_list_ind_name;
	private String black_list_ind_nid;
	private String black_list_corp_name;
	private String black_list_corp_nid;
	private String pep_name;
	private String pep_occupation;
	private String unsc_name;
	private String unsc_country;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date tran_date;
	
	private BigDecimal tran_amount;
	
	
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getRisk_category() {
		return risk_category;
	}
	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getBlack_list_ind_name() {
		return black_list_ind_name;
	}
	public void setBlack_list_ind_name(String black_list_ind_name) {
		this.black_list_ind_name = black_list_ind_name;
	}
	public String getBlack_list_ind_nid() {
		return black_list_ind_nid;
	}
	public void setBlack_list_ind_nid(String black_list_ind_nid) {
		this.black_list_ind_nid = black_list_ind_nid;
	}
	public String getBlack_list_corp_name() {
		return black_list_corp_name;
	}
	public void setBlack_list_corp_name(String black_list_corp_name) {
		this.black_list_corp_name = black_list_corp_name;
	}
	public String getBlack_list_corp_nid() {
		return black_list_corp_nid;
	}
	public void setBlack_list_corp_nid(String black_list_corp_nid) {
		this.black_list_corp_nid = black_list_corp_nid;
	}
	public String getPep_name() {
		return pep_name;
	}
	public void setPep_name(String pep_name) {
		this.pep_name = pep_name;
	}
	public String getPep_occupation() {
		return pep_occupation;
	}
	public void setPep_occupation(String pep_occupation) {
		this.pep_occupation = pep_occupation;
	}
	public String getUnsc_name() {
		return unsc_name;
	}
	public void setUnsc_name(String unsc_name) {
		this.unsc_name = unsc_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public BigDecimal getTran_amount() {
		return tran_amount;
	}
	public void setTran_amount(BigDecimal tran_amount) {
		this.tran_amount = tran_amount;
	}
	public String getUnsc_country() {
		return unsc_country;
	}
	public void setUnsc_country(String unsc_country) {
		this.unsc_country = unsc_country;
	}
	public String getHnwi_name() {
		return hnwi_name;
	}
	public void setHnwi_name(String hnwi_name) {
		this.hnwi_name = hnwi_name;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}



	}

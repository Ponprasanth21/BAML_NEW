package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_DOC_HIST_TABLE")
public class BAML_Doc_Hist_Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * @EmbeddedId BAML_Doc_Hist_Id bAML_Doc_Hist_Id;
	 */

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date bodatecreated;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date bodatemodified;
	private String countryofissue;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cust_dob;
	@Id
	private String cust_id;
	private String referencenumber;
	private String cust_name;
	private String cust_nre_flg;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date cust_opn_date;
	private String doccode;
	private String docdescr;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date docexpirydate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date docissuedate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date docreceiveddate;
	private BigDecimal entityid;
	private String entitytype;
	private String ismandatory;
	private String isverifiedflg;
	private String orgkey;
	private String placeofissue;
	private String primary_sol_id;

	private String scanned;
	private String status;
	
	@Lob
	private byte[] docimage;

	public Date getBodatecreated() {
		return bodatecreated;
	}

	public Date getBodatemodified() {
		return bodatemodified;
	}

	public String getCountryofissue() {
		return countryofissue;
	}

	public Date getCust_dob() {
		return cust_dob;
	}

	public String getCust_name() {
		return cust_name;
	}

	public String getCust_nre_flg() {
		return cust_nre_flg;
	}

	public Date getCust_opn_date() {
		return cust_opn_date;
	}

	public String getDoccode() {
		return doccode;
	}

	public String getDocdescr() {
		return docdescr;
	}

	public Date getDocexpirydate() {
		return docexpirydate;
	}

	public Date getDocissuedate() {
		return docissuedate;
	}

	public Date getDocreceiveddate() {
		return docreceiveddate;
	}

	public BigDecimal getEntityid() {
		return entityid;
	}

	public String getEntitytype() {
		return entitytype;
	}

	public String getIsmandatory() {
		return ismandatory;
	}

	public String getIsverifiedflg() {
		return isverifiedflg;
	}

	public String getOrgkey() {
		return orgkey;
	}

	public String getPlaceofissue() {
		return placeofissue;
	}

	public String getPrimary_sol_id() {
		return primary_sol_id;
	}

	public String getScanned() {
		return scanned;
	}

	public String getStatus() {
		return status;
	}

	public void setBodatecreated(Date bodatecreated) {
		this.bodatecreated = bodatecreated;
	}

	public void setBodatemodified(Date bodatemodified) {
		this.bodatemodified = bodatemodified;
	}

	public void setCountryofissue(String countryofissue) {
		this.countryofissue = countryofissue;
	}

	public void setCust_dob(Date cust_dob) {
		this.cust_dob = cust_dob;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public void setCust_nre_flg(String cust_nre_flg) {
		this.cust_nre_flg = cust_nre_flg;
	}

	public void setCust_opn_date(Date cust_opn_date) {
		this.cust_opn_date = cust_opn_date;
	}

	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}

	public void setDocdescr(String docdescr) {
		this.docdescr = docdescr;
	}

	public void setDocexpirydate(Date docexpirydate) {
		this.docexpirydate = docexpirydate;
	}

	public void setDocissuedate(Date docissuedate) {
		this.docissuedate = docissuedate;
	}

	public void setDocreceiveddate(Date docreceiveddate) {
		this.docreceiveddate = docreceiveddate;
	}

	public void setEntityid(BigDecimal entityid) {
		this.entityid = entityid;
	}

	public void setEntitytype(String entitytype) {
		this.entitytype = entitytype;
	}

	public void setIsmandatory(String ismandatory) {
		this.ismandatory = ismandatory;
	}

	public void setIsverifiedflg(String isverifiedflg) {
		this.isverifiedflg = isverifiedflg;
	}

	public void setOrgkey(String orgkey) {
		this.orgkey = orgkey;
	}

	public void setPlaceofissue(String placeofissue) {
		this.placeofissue = placeofissue;
	}

	public void setPrimary_sol_id(String primary_sol_id) {
		this.primary_sol_id = primary_sol_id;
	}

	public void setScanned(String scanned) {
		this.scanned = scanned;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public byte[] getDocimage() {
		return docimage;
	}

	public void setDocimage(byte[] docimage) {
		this.docimage = docimage;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public String getCust_id() {
		return cust_id;
	}

	public String getReferencenumber() {
		return referencenumber;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber;
	}

	

	public BAML_Doc_Hist_Table() {
		super();
		// TODO Auto-generated constructor stub
	}

}
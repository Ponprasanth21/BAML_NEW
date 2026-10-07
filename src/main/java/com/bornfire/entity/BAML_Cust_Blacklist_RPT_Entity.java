package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BAML_BLACKLIST_RPT_TEMP_TABLE")
public class BAML_Cust_Blacklist_RPT_Entity implements Serializable{
	public BAML_Cust_Blacklist_RPT_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String FORACID;
	private String CIF;
	private String CUST_ID;
	private String MASTERDATAID;
	private String  CUST_LAST_NAME;
	private String CUST_FIRST_NAME;
	private String NAT_ID_CARD_NUM;
	private BigDecimal BLACK_LIST_DATA_SETID;
	private String RISK_CATEGORY;
	private String SECTOR;
	private String STATUS;
	private String BLACK_LIST_REASON_NOTES;
	private Date DATE_FREEZED;
	private Date DATE_DEFREEZED;
	private String ACTIVE_PRODUCT_TYPE;
	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
	}
	public String getCUST_ID() {
		return CUST_ID;
	}
	public void setCUST_ID(String cUST_ID) {
		CUST_ID = cUST_ID;
	}
	public String getCUST_LAST_NAME() {
		return CUST_LAST_NAME;
	}
	public void setCUST_LAST_NAME(String cUST_LAST_NAME) {
		CUST_LAST_NAME = cUST_LAST_NAME;
	}
	public String getCUST_FIRST_NAME() {
		return CUST_FIRST_NAME;
	}
	public void setCUST_FIRST_NAME(String cUST_FIRST_NAME) {
		CUST_FIRST_NAME = cUST_FIRST_NAME;
	}
	public String getNAT_ID_CARD_NUM() {
		return NAT_ID_CARD_NUM;
	}
	public void setNAT_ID_CARD_NUM(String nAT_ID_CARD_NUM) {
		NAT_ID_CARD_NUM = nAT_ID_CARD_NUM;
	}
	public BigDecimal getBLACK_LIST_DATA_SETID() {
		return BLACK_LIST_DATA_SETID;
	}
	public void setBLACK_LIST_DATA_SETID(BigDecimal bLACK_LIST_DATA_SETID) {
		BLACK_LIST_DATA_SETID = bLACK_LIST_DATA_SETID;
	}
	public String getRISK_CATEGORY() {
		return RISK_CATEGORY;
	}
	public void setRISK_CATEGORY(String rISK_CATEGORY) {
		RISK_CATEGORY = rISK_CATEGORY;
	}
	public String getSECTOR() {
		return SECTOR;
	}
	public void setSECTOR(String sECTOR) {
		SECTOR = sECTOR;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getBLACK_LIST_REASON_NOTES() {
		return BLACK_LIST_REASON_NOTES;
	}
	public void setBLACK_LIST_REASON_NOTES(String bLACK_LIST_REASON_NOTES) {
		BLACK_LIST_REASON_NOTES = bLACK_LIST_REASON_NOTES;
	}
	public Date getDATE_FREEZED() {
		return DATE_FREEZED;
	}
	public void setDATE_FREEZED(Date dATE_FREEZED) {
		DATE_FREEZED = dATE_FREEZED;
	}
	public Date getDATE_DEFREEZED() {
		return DATE_DEFREEZED;
	}
	public void setDATE_DEFREEZED(Date dATE_DEFREEZED) {
		DATE_DEFREEZED = dATE_DEFREEZED;
	}
	public String getACTIVE_PRODUCT_TYPE() {
		return ACTIVE_PRODUCT_TYPE;
	}
	public void setACTIVE_PRODUCT_TYPE(String aCTIVE_PRODUCT_TYPE) {
		ACTIVE_PRODUCT_TYPE = aCTIVE_PRODUCT_TYPE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFORACID() {
		return FORACID;
	}
	public void setFORACID(String fORACID) {
		FORACID = fORACID;
	}
	public String getMASTERDATAID() {
		return MASTERDATAID;
	}
	public void setMASTERDATAID(String mASTERDATAID) {
		MASTERDATAID = mASTERDATAID;
	}
	



	}

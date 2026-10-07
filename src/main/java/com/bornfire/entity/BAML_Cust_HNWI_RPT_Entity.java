package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BAML_CUST_HNWI_RPT_TEMP_TABLE")
public class BAML_Cust_HNWI_RPT_Entity implements Serializable{
	public BAML_Cust_HNWI_RPT_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String FORACID;
	private String CUST_ID;
	private String CIF;
	private String CUST_LAST_NAME;
	private String CUST_FIRST_NAME;
	private String NAT_ID_CARD_NUM;
	private String HNWI_ID;
	private String RISK_CATEGORY;
	private String BUSINESS_DESCRIPTION;
	private String SECTOR;
	private Date START_DATE;
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
	public String getFORACID() {
		return FORACID;
	}
	public void setFORACID(String fORACID) {
		FORACID = fORACID;
	}
	public String getHNWI_ID() {
		return HNWI_ID;
	}
	public void setHNWI_ID(String hNWI_ID) {
		HNWI_ID = hNWI_ID;
	}
	public String getRISK_CATEGORY() {
		return RISK_CATEGORY;
	}
	public void setRISK_CATEGORY(String rISK_CATEGORY) {
		RISK_CATEGORY = rISK_CATEGORY;
	}
	public String getBUSINESS_DESCRIPTION() {
		return BUSINESS_DESCRIPTION;
	}
	public void setBUSINESS_DESCRIPTION(String bUSINESS_DESCRIPTION) {
		BUSINESS_DESCRIPTION = bUSINESS_DESCRIPTION;
	}
	public String getSECTOR() {
		return SECTOR;
	}
	public void setSECTOR(String sECTOR) {
		SECTOR = sECTOR;
	}
	public Date getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(Date sTART_DATE) {
		START_DATE = sTART_DATE;
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
	}

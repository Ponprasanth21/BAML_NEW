package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BAML_CUST_PEP_RPT_TEMP_TABLE")
public class BAML_Cust_PEP_RPT_Entity implements Serializable{
	public BAML_Cust_PEP_RPT_Entity() {
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
	private String PEP_ID;
	private String RISK_CATEGORY;
	private String ACTIVE_PRODUCT_TYPE;
	private String PEP_DESC;
	private String CUST_POSITION;
	private Date MEMBERSHIP_DATE;
	private Date DATE_OF_PEP;
	private Date DATE_OF_RESIG;
	private String RESIG_REASONS;
	
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
	public String getPEP_ID() {
		return PEP_ID;
	}
	public void setPEP_ID(String pEP_ID) {
		PEP_ID = pEP_ID;
	}
	public String getACTIVE_PRODUCT_TYPE() {
		return ACTIVE_PRODUCT_TYPE;
	}
	public void setACTIVE_PRODUCT_TYPE(String aCTIVE_PRODUCT_TYPE) {
		ACTIVE_PRODUCT_TYPE = aCTIVE_PRODUCT_TYPE;
	}
	public String getPEP_DESC() {
		return PEP_DESC;
	}
	public void setPEP_DESC(String pEP_DESC) {
		PEP_DESC = pEP_DESC;
	}
	public String getCUST_POSITION() {
		return CUST_POSITION;
	}
	public void setCUST_POSITION(String cUST_POSITION) {
		CUST_POSITION = cUST_POSITION;
	}
	public Date getMEMBERSHIP_DATE() {
		return MEMBERSHIP_DATE;
	}
	public void setMEMBERSHIP_DATE(Date mEMBERSHIP_DATE) {
		MEMBERSHIP_DATE = mEMBERSHIP_DATE;
	}
	public Date getDATE_OF_PEP() {
		return DATE_OF_PEP;
	}
	public void setDATE_OF_PEP(Date dATE_OF_PEP) {
		DATE_OF_PEP = dATE_OF_PEP;
	}
	public Date getDATE_OF_RESIG() {
		return DATE_OF_RESIG;
	}
	public void setDATE_OF_RESIG(Date dATE_OF_RESIG) {
		DATE_OF_RESIG = dATE_OF_RESIG;
	}
	public String getRESIG_REASONS() {
		return RESIG_REASONS;
	}
	public void setRESIG_REASONS(String rESIG_REASONS) {
		RESIG_REASONS = rESIG_REASONS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRISK_CATEGORY() {
		return RISK_CATEGORY;
	}
	public void setRISK_CATEGORY(String rISK_CATEGORY) {
		RISK_CATEGORY = rISK_CATEGORY;
	}
	}

package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="BAML_RISK_REPORTS")
public class BAML_Risk_Category_RPT_Entity implements Serializable{
	public BAML_Risk_Category_RPT_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String FORACID;
	
	
	private String FORACID_2;
	
	private String CIF;
	
	private String CIF_2;
	
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date ACCT_OPN_DATE;
	
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date MEMBERSHIP_DATE;
	
	
	
	
	private String BENEFICIARY;
	private String ACID	;
	private BigDecimal AMT_APPL;
	private BigDecimal AMT_DIS;
	
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date APPL_DATE;
	
	private BigDecimal CONT_AMT	;
	private String DEPARTMENT;
	private BigDecimal DEP_AMT;
	
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date EFF_DATE;
	
	private BigDecimal MLY_INCOME;
	private BigDecimal MLY_INCOME_2;
	private String NAME;
	private String NAME_2;
	private String NID;
	private String NID_2;
	private String OCCUPATION;
	private String OCCUPATION_2	;
	
	private String POB;
	
	private String POB_2;
	
	
	private String RISK_CATEGORY;
	private String RISK_CATEGORY_2;
	private String RPT_TYPE	;
	private String STATUS_NEW_REIN;
	private String TYPE_OF_LOAN	;
	public String getFORACID() {
		return FORACID;
	}
	public void setFORACID(String fORACID) {
		FORACID = fORACID;
	}

	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
	}
	public Date getACCT_OPN_DATE() {
		return ACCT_OPN_DATE;
	}
	public void setACCT_OPN_DATE(Date aCCT_OPN_DATE) {
		ACCT_OPN_DATE = aCCT_OPN_DATE;
	}
	public String getACID() {
		return ACID;
	}
	public void setACID(String aCID) {
		ACID = aCID;
	}
	public BigDecimal getAMT_APPL() {
		return AMT_APPL;
	}
	public void setAMT_APPL(BigDecimal aMT_APPL) {
		AMT_APPL = aMT_APPL;
	}
	public BigDecimal getAMT_DIS() {
		return AMT_DIS;
	}
	public void setAMT_DIS(BigDecimal aMT_DIS) {
		AMT_DIS = aMT_DIS;
	}
	public Date getAPPL_DATE() {
		return APPL_DATE;
	}
	public void setAPPL_DATE(Date aPPL_DATE) {
		APPL_DATE = aPPL_DATE;
	}
	public BigDecimal getCONT_AMT() {
		return CONT_AMT;
	}
	public void setCONT_AMT(BigDecimal cONT_AMT) {
		CONT_AMT = cONT_AMT;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public BigDecimal getDEP_AMT() {
		return DEP_AMT;
	}
	public void setDEP_AMT(BigDecimal dEP_AMT) {
		DEP_AMT = dEP_AMT;
	}
	public Date getEFF_DATE() {
		return EFF_DATE;
	}
	public void setEFF_DATE(Date eFF_DATE) {
		EFF_DATE = eFF_DATE;
	}
	public BigDecimal getMLY_INCOME() {
		return MLY_INCOME;
	}
	public void setMLY_INCOME(BigDecimal mLY_INCOME) {
		MLY_INCOME = mLY_INCOME;
	}
	public BigDecimal getMLY_INCOME_2() {
		return MLY_INCOME_2;
	}
	public void setMLY_INCOME_2(BigDecimal mLY_INCOME_2) {
		MLY_INCOME_2 = mLY_INCOME_2;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getNAME_2() {
		return NAME_2;
	}
	public void setNAME_2(String nAME_2) {
		NAME_2 = nAME_2;
	}
	public String getNID() {
		return NID;
	}
	public void setNID(String nID) {
		NID = nID;
	}
	public String getNID_2() {
		return NID_2;
	}
	public void setNID_2(String nID_2) {
		NID_2 = nID_2;
	}
	public String getOCCUPATION() {
		return OCCUPATION;
	}
	public void setOCCUPATION(String oCCUPATION) {
		OCCUPATION = oCCUPATION;
	}
	public String getOCCUPATION_2() {
		return OCCUPATION_2;
	}
	public void setOCCUPATION_2(String oCCUPATION_2) {
		OCCUPATION_2 = oCCUPATION_2;
	}
	public String getRISK_CATEGORY() {
		return RISK_CATEGORY;
	}
	public void setRISK_CATEGORY(String rISK_CATEGORY) {
		RISK_CATEGORY = rISK_CATEGORY;
	}
	public String getRISK_CATEGORY_2() {
		return RISK_CATEGORY_2;
	}
	public void setRISK_CATEGORY_2(String rISK_CATEGORY_2) {
		RISK_CATEGORY_2 = rISK_CATEGORY_2;
	}
	public String getRPT_TYPE() {
		return RPT_TYPE;
	}
	public void setRPT_TYPE(String rPT_TYPE) {
		RPT_TYPE = rPT_TYPE;
	}
	public String getSTATUS_NEW_REIN() {
		return STATUS_NEW_REIN;
	}
	public void setSTATUS_NEW_REIN(String sTATUS_NEW_REIN) {
		STATUS_NEW_REIN = sTATUS_NEW_REIN;
	}
	public String getTYPE_OF_LOAN() {
		return TYPE_OF_LOAN;
	}
	public void setTYPE_OF_LOAN(String tYPE_OF_LOAN) {
		TYPE_OF_LOAN = tYPE_OF_LOAN;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getMEMBERSHIP_DATE() {
		return MEMBERSHIP_DATE;
	}
	public void setMEMBERSHIP_DATE(Date mEMBERSHIP_DATE) {
		MEMBERSHIP_DATE = mEMBERSHIP_DATE;
	}
	public String getBENEFICIARY() {
		return BENEFICIARY;
	}
	public void setBENEFICIARY(String bENEFICIARY) {
		BENEFICIARY = bENEFICIARY;
	}
	
	public String getCIF_2() {
		return CIF_2;
	}
	public void setCIF_2(String cIF_2) {
		CIF_2 = cIF_2;
	}
	public String getFORACID_2() {
		return FORACID_2;
	}
	public void setFORACID_2(String fORACID_2) {
		FORACID_2 = fORACID_2;
	}
	public String getPOB() {
		return POB;
	}
	public void setPOB(String pOB) {
		POB = pOB;
	}
	public String getPOB_2() {
		return POB_2;
	}
	public void setPOB_2(String pOB_2) {
		POB_2 = pOB_2;
	}
	
	
	}

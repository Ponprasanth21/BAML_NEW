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
@Table(name="BAML_SCR_ALERT_OPER")
public class BAML_SCR_Alert_Oper_Entity implements Serializable{
	public BAML_SCR_Alert_Oper_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private BigDecimal  AML_TRAN_REF_NO;
	private String FORACID;
	private String SOL_ID;
	private String SCHM_TYPE;
	private String SCHM_CODE;
	private String GL_SUB_HEAD_CODE	;
	//displaying cif 
	private String CUST_ID	;
	private String ACID	;
	private String ACCT_NAME;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ACCT_OPN_DATE	;
	
	private BigDecimal SANCT_LIM	;
	private BigDecimal DIS_AMT;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date  DATE_DIS;
	
	private BigDecimal PART_TRAN_SRL_NUM	;
	private String  PART_TRAN_TYPE	;
	private String FLOW_CODE	;
	private String RULE_REF	;
	private String RULE_DESC	;
	private String REMARKS;
	private String TRAN_CODE;
	
	
	
	private String 	CASE_REF;
	private BigDecimal CEILING;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	AERT_DATE;
	
	private String  ALERT_CODE	;
	private String  ALERT_REMARKS;
	private String  ALERT_TYPE	;

	private String DEL_FLG	;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	ENTRY_DATE;
	
	private String ENTRY_USER_ID;
	private String  FREE_FIELD_1;
	private String  FREE_FIELD_2	;
	private String  FREE_FIELD_3	;
	private String OPERATION;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	OPER_DATE;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	PSTD_DATE;

	private String  PSTD_FLG;
	private String  PSTD_USER_ID	;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	SCR_DATE;
	
	private String  STR_REF;
	private BigDecimal TRAN_AMT;
	private String 	TRAN_CRNCY_CODE	;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	TRAN_DATE;
	
	private String  QUALIFIER;
	private String  TRAN_PARTICULAR_CODE;
	private String  TRAN_ID	;
	private String TRAN_PARTICULAR;
	private String TRAN_REMARKS;
	private String TRAN_SUB_TYPE;
	private String TRAN_TYPE;
	private String USER_OBSERVATION	;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	VALUE_DATE;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	VFD_DATE;
	
	private String VFD_USER_ID	;
	private String 	ENTITY_FLAG;
	public String getFORACID() {
		return FORACID;
	}

	public void setFORACID(String fORACID) {
		FORACID = fORACID;
	}

	public String getSOL_ID() {
		return SOL_ID;
	}

	public void setSOL_ID(String sOL_ID) {
		SOL_ID = sOL_ID;
	}

	public String getSCHM_TYPE() {
		return SCHM_TYPE;
	}

	public void setSCHM_TYPE(String sCHM_TYPE) {
		SCHM_TYPE = sCHM_TYPE;
	}

	public String getSCHM_CODE() {
		return SCHM_CODE;
	}

	public void setSCHM_CODE(String sCHM_CODE) {
		SCHM_CODE = sCHM_CODE;
	}

	public String getGL_SUB_HEAD_CODE() {
		return GL_SUB_HEAD_CODE;
	}

	public void setGL_SUB_HEAD_CODE(String gL_SUB_HEAD_CODE) {
		GL_SUB_HEAD_CODE = gL_SUB_HEAD_CODE;
	}

	public String getCUST_ID() {
		return CUST_ID;
	}

	public void setCUST_ID(String cUST_ID) {
		CUST_ID = cUST_ID;
	}

	public String getACID() {
		return ACID;
	}

	public void setACID(String aCID) {
		ACID = aCID;
	}

	public String getACCT_NAME() {
		return ACCT_NAME;
	}

	public void setACCT_NAME(String aCCT_NAME) {
		ACCT_NAME = aCCT_NAME;
	}

	public Date getACCT_OPN_DATE() {
		return ACCT_OPN_DATE;
	}

	public void setACCT_OPN_DATE(Date aCCT_OPN_DATE) {
		ACCT_OPN_DATE = aCCT_OPN_DATE;
	}

	public BigDecimal getSANCT_LIM() {
		return SANCT_LIM;
	}

	public void setSANCT_LIM(BigDecimal sANCT_LIM) {
		SANCT_LIM = sANCT_LIM;
	}

	public BigDecimal getDIS_AMT() {
		return DIS_AMT;
	}

	public void setDIS_AMT(BigDecimal dIS_AMT) {
		DIS_AMT = dIS_AMT;
	}

	public Date getDATE_DIS() {
		return DATE_DIS;
	}

	public void setDATE_DIS(Date dATE_DIS) {
		DATE_DIS = dATE_DIS;
	}

	public BigDecimal getPART_TRAN_SRL_NUM() {
		return PART_TRAN_SRL_NUM;
	}

	public void setPART_TRAN_SRL_NUM(BigDecimal pART_TRAN_SRL_NUM) {
		PART_TRAN_SRL_NUM = pART_TRAN_SRL_NUM;
	}

	public String getPART_TRAN_TYPE() {
		return PART_TRAN_TYPE;
	}

	public void setPART_TRAN_TYPE(String pART_TRAN_TYPE) {
		PART_TRAN_TYPE = pART_TRAN_TYPE;
	}

	public String getFLOW_CODE() {
		return FLOW_CODE;
	}

	public void setFLOW_CODE(String fLOW_CODE) {
		FLOW_CODE = fLOW_CODE;
	}

	public String getRULE_REF() {
		return RULE_REF;
	}

	public void setRULE_REF(String rULE_REF) {
		RULE_REF = rULE_REF;
	}

	public String getRULE_DESC() {
		return RULE_DESC;
	}

	public void setRULE_DESC(String rULE_DESC) {
		RULE_DESC = rULE_DESC;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getCASE_REF() {
		return CASE_REF;
	}

	public void setCASE_REF(String cASE_REF) {
		CASE_REF = cASE_REF;
	}

	public BigDecimal getCEILING() {
		return CEILING;
	}

	public void setCEILING(BigDecimal cEILING) {
		CEILING = cEILING;
	}

	public Date getAERT_DATE() {
		return AERT_DATE;
	}

	public void setAERT_DATE(Date aERT_DATE) {
		AERT_DATE = aERT_DATE;
	}

	public String getALERT_CODE() {
		return ALERT_CODE;
	}

	public void setALERT_CODE(String aLERT_CODE) {
		ALERT_CODE = aLERT_CODE;
	}

	public String getALERT_REMARKS() {
		return ALERT_REMARKS;
	}

	public void setALERT_REMARKS(String aLERT_REMARKS) {
		ALERT_REMARKS = aLERT_REMARKS;
	}

	public String getALERT_TYPE() {
		return ALERT_TYPE;
	}

	public void setALERT_TYPE(String aLERT_TYPE) {
		ALERT_TYPE = aLERT_TYPE;
	}


	public String getDEL_FLG() {
		return DEL_FLG;
	}

	public void setDEL_FLG(String dEL_FLG) {
		DEL_FLG = dEL_FLG;
	}



	public String getENTRY_USER_ID() {
		return ENTRY_USER_ID;
	}

	public void setENTRY_USER_ID(String eNTRY_USER_ID) {
		ENTRY_USER_ID = eNTRY_USER_ID;
	}

	public String getFREE_FIELD_1() {
		return FREE_FIELD_1;
	}

	public void setFREE_FIELD_1(String fREE_FIELD_1) {
		FREE_FIELD_1 = fREE_FIELD_1;
	}

	public String getFREE_FIELD_2() {
		return FREE_FIELD_2;
	}

	public void setFREE_FIELD_2(String fREE_FIELD_2) {
		FREE_FIELD_2 = fREE_FIELD_2;
	}

	public String getFREE_FIELD_3() {
		return FREE_FIELD_3;
	}

	public void setFREE_FIELD_3(String fREE_FIELD_3) {
		FREE_FIELD_3 = fREE_FIELD_3;
	}

	public String getOPERATION() {
		return OPERATION;
	}

	public void setOPERATION(String oPERATION) {
		OPERATION = oPERATION;
	}

	public Date getOPER_DATE() {
		return OPER_DATE;
	}

	public void setOPER_DATE(Date oPER_DATE) {
		OPER_DATE = oPER_DATE;
	}

	public Date getPSTD_DATE() {
		return PSTD_DATE;
	}

	public void setPSTD_DATE(Date pSTD_DATE) {
		PSTD_DATE = pSTD_DATE;
	}

	public String getPSTD_FLG() {
		return PSTD_FLG;
	}

	public void setPSTD_FLG(String pSTD_FLG) {
		PSTD_FLG = pSTD_FLG;
	}

	public String getPSTD_USER_ID() {
		return PSTD_USER_ID;
	}

	public void setPSTD_USER_ID(String pSTD_USER_ID) {
		PSTD_USER_ID = pSTD_USER_ID;
	}

	public Date getSCR_DATE() {
		return SCR_DATE;
	}

	public void setSCR_DATE(Date sCR_DATE) {
		SCR_DATE = sCR_DATE;
	}

	public String getSTR_REF() {
		return STR_REF;
	}

	public void setSTR_REF(String sTR_REF) {
		STR_REF = sTR_REF;
	}

	public BigDecimal getTRAN_AMT() {
		return TRAN_AMT;
	}

	public void setTRAN_AMT(BigDecimal tRAN_AMT) {
		TRAN_AMT = tRAN_AMT;
	}

	public String getTRAN_CRNCY_CODE() {
		return TRAN_CRNCY_CODE;
	}

	public void setTRAN_CRNCY_CODE(String tRAN_CRNCY_CODE) {
		TRAN_CRNCY_CODE = tRAN_CRNCY_CODE;
	}

	public Date getTRAN_DATE() {
		return TRAN_DATE;
	}

	public void setTRAN_DATE(Date tRAN_DATE) {
		TRAN_DATE = tRAN_DATE;
	}

	public String getTRAN_ID() {
		return TRAN_ID;
	}

	public void setTRAN_ID(String tRAN_ID) {
		TRAN_ID = tRAN_ID;
	}

	public String getTRAN_PARTICULAR() {
		return TRAN_PARTICULAR;
	}

	public void setTRAN_PARTICULAR(String tRAN_PARTICULAR) {
		TRAN_PARTICULAR = tRAN_PARTICULAR;
	}

	public String getTRAN_REMARKS() {
		return TRAN_REMARKS;
	}

	public void setTRAN_REMARKS(String tRAN_REMARKS) {
		TRAN_REMARKS = tRAN_REMARKS;
	}

	public String getTRAN_SUB_TYPE() {
		return TRAN_SUB_TYPE;
	}

	public void setTRAN_SUB_TYPE(String tRAN_SUB_TYPE) {
		TRAN_SUB_TYPE = tRAN_SUB_TYPE;
	}

	public String getTRAN_TYPE() {
		return TRAN_TYPE;
	}

	public void setTRAN_TYPE(String tRAN_TYPE) {
		TRAN_TYPE = tRAN_TYPE;
	}

	public String getUSER_OBSERVATION() {
		return USER_OBSERVATION;
	}

	public void setUSER_OBSERVATION(String uSER_OBSERVATION) {
		USER_OBSERVATION = uSER_OBSERVATION;
	}

	public Date getVALUE_DATE() {
		return VALUE_DATE;
	}

	public void setVALUE_DATE(Date vALUE_DATE) {
		VALUE_DATE = vALUE_DATE;
	}

	public Date getVFD_DATE() {
		return VFD_DATE;
	}

	public void setVFD_DATE(Date vFD_DATE) {
		VFD_DATE = vFD_DATE;
	}

	public String getVFD_USER_ID() {
		return VFD_USER_ID;
	}

	public void setVFD_USER_ID(String vFD_USER_ID) {
		VFD_USER_ID = vFD_USER_ID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getAML_TRAN_REF_NO() {
		return AML_TRAN_REF_NO;
	}

	public void setAML_TRAN_REF_NO(BigDecimal aML_TRAN_REF_NO) {
		AML_TRAN_REF_NO = aML_TRAN_REF_NO;
	}

	public String getENTITY_FLAG() {
		return ENTITY_FLAG;
	}

	public void setENTITY_FLAG(String eNTITY_FLAG) {
		ENTITY_FLAG = eNTITY_FLAG;
	}

	public String getQUALIFIER() {
		return QUALIFIER;
	}

	public void setQUALIFIER(String qUALIFIER) {
		QUALIFIER = qUALIFIER;
	}

	public String getTRAN_PARTICULAR_CODE() {
		return TRAN_PARTICULAR_CODE;
	}

	public void setTRAN_PARTICULAR_CODE(String tRAN_PARTICULAR_CODE) {
		TRAN_PARTICULAR_CODE = tRAN_PARTICULAR_CODE;
	}

	public String getTRAN_CODE() {
		return TRAN_CODE;
	}

	public void setTRAN_CODE(String tRAN_CODE) {
		TRAN_CODE = tRAN_CODE;
	}

	public Date getENTRY_DATE() {
		return ENTRY_DATE;
	}

	public void setENTRY_DATE(Date eNTRY_DATE) {
		ENTRY_DATE = eNTRY_DATE;
	}
	
	
	
	}

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
@Table(name="BAML_LIST_SCHD_RPT")
public class BAML_List_Schd_RPT_Entity implements Serializable{
	public BAML_List_Schd_RPT_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date RPT_DATE;
	
	private byte[] CONS_PDF;
	private byte[] CONS_EXCEL; 
	
	private byte[] NEW_LIST_PDF;
	private byte[] NEW_LIST_EXCEL; 
	
	private byte[] CUST_CHECK_PDF;
	private byte[] CUST_CHECK_EXCEL; 
	
	private String REMARKS;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date ENTRY_TIME;
	
	private String ENTRY_BY;


	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public Date getENTRY_TIME() {
		return ENTRY_TIME;
	}

	public void setENTRY_TIME(Date eNTRY_TIME) {
		ENTRY_TIME = eNTRY_TIME;
	}

	public String getENTRY_BY() {
		return ENTRY_BY;
	}

	public void setENTRY_BY(String eNTRY_BY) {
		ENTRY_BY = eNTRY_BY;
	}

	public Date getRPT_DATE() {
		return RPT_DATE;
	}

	public void setRPT_DATE(Date rPT_DATE) {
		RPT_DATE = rPT_DATE;
	}

	public byte[] getCONS_PDF() {
		return CONS_PDF;
	}

	public void setCONS_PDF(byte[] cONS_PDF) {
		CONS_PDF = cONS_PDF;
	}

	public byte[] getCONS_EXCEL() {
		return CONS_EXCEL;
	}

	public void setCONS_EXCEL(byte[] cONS_EXCEL) {
		CONS_EXCEL = cONS_EXCEL;
	}

	public byte[] getNEW_LIST_PDF() {
		return NEW_LIST_PDF;
	}

	public void setNEW_LIST_PDF(byte[] nEW_LIST_PDF) {
		NEW_LIST_PDF = nEW_LIST_PDF;
	}

	public byte[] getNEW_LIST_EXCEL() {
		return NEW_LIST_EXCEL;
	}

	public void setNEW_LIST_EXCEL(byte[] nEW_LIST_EXCEL) {
		NEW_LIST_EXCEL = nEW_LIST_EXCEL;
	}

	public byte[] getCUST_CHECK_PDF() {
		return CUST_CHECK_PDF;
	}

	public void setCUST_CHECK_PDF(byte[] cUST_CHECK_PDF) {
		CUST_CHECK_PDF = cUST_CHECK_PDF;
	}

	public byte[] getCUST_CHECK_EXCEL() {
		return CUST_CHECK_EXCEL;
	}

	public void setCUST_CHECK_EXCEL(byte[] cUST_CHECK_EXCEL) {
		CUST_CHECK_EXCEL = cUST_CHECK_EXCEL;
	}
	
}

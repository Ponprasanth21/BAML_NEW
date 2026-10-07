package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "BAML_ACCT_BAL_TABLE")
public class BAML_ACCT_BAL_TABLE {

	
	
	@Id
	private String	acid;
	private Date	eod_date;
	private Date	end_eod_date;
	private BigDecimal	tran_date_bal;
	private String	eab_crncy_code;
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public Date getEod_date() {
		return eod_date;
	}
	public void setEod_date(Date eod_date) {
		this.eod_date = eod_date;
	}
	public Date getEnd_eod_date() {
		return end_eod_date;
	}
	public void setEnd_eod_date(Date end_eod_date) {
		this.end_eod_date = end_eod_date;
	}
	public BigDecimal getTran_date_bal() {
		return tran_date_bal;
	}
	public void setTran_date_bal(BigDecimal tran_date_bal) {
		this.tran_date_bal = tran_date_bal;
	}
	public String getEab_crncy_code() {
		return eab_crncy_code;
	}
	public void setEab_crncy_code(String eab_crncy_code) {
		this.eab_crncy_code = eab_crncy_code;
	}
	public BAML_ACCT_BAL_TABLE(String acid, Date eod_date, Date end_eod_date, BigDecimal tran_date_bal,
			String eab_crncy_code) {
		super();
		this.acid = acid;
		this.eod_date = eod_date;
		this.end_eod_date = end_eod_date;
		this.tran_date_bal = tran_date_bal;
		this.eab_crncy_code = eab_crncy_code;
	}

}

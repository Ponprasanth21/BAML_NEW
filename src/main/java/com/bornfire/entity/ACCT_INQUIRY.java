package com.bornfire.entity;

import java.util.Date;

public class ACCT_INQUIRY {
	
	
	private String	clr_bal_amt;
	private Date	last_frez_date;
	public String getClr_bal_amt() {
		return clr_bal_amt;
	}
	public void setClr_bal_amt(String clr_bal_amt) {
		this.clr_bal_amt = clr_bal_amt;
	}
	public Date getLast_frez_date() {
		return last_frez_date;
	}
	public void setLast_frez_date(Date last_frez_date) {
		this.last_frez_date = last_frez_date;
	}
	
}

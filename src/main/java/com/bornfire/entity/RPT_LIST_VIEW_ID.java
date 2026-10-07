package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class RPT_LIST_VIEW_ID implements Serializable{
	
	private String rpt_code;
	private Date rpt_date;
	public String getRpt_code() {
		return rpt_code;
	}
	public Date getRpt_date() {
		return rpt_date;
	}
	public void setRpt_code(String rpt_code) {
		this.rpt_code = rpt_code;
	}
	public void setRpt_date(Date rpt_date) {
		this.rpt_date = rpt_date;
	}
	
	
	

}

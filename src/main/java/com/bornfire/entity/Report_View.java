package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="RPT_LIST_VIEW")
@IdClass(RPT_LIST_VIEW_ID.class)
public class Report_View {
	@Id
	private String rpt_code;
	private String rpt_description;
	@Id
	private Date  rpt_date;
	
	
	public String getRpt_code() {
		return rpt_code;
	}
	public String getRpt_description() {
		return rpt_description;
	}
	public Date getRpt_date() {
		return rpt_date;
	}
	
	public void setRpt_code(String rpt_code) {
		this.rpt_code = rpt_code;
	}
	public void setRpt_description(String rpt_description) {
		this.rpt_description = rpt_description;
	}
	public void setRpt_date(Date rpt_date) {
		this.rpt_date = rpt_date;
	}
	

	
	
}

package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BAML_RBS_REPORT_MAINTENANCE")
public class BAML_RBS_REPORT_PROCEDURE {
	
	@Id
	private String report_code;
	private String report_name;
	private Character report_flag;
	private Date report_date;
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public Character getReport_flag() {
		return report_flag;
	}
	public void setReport_flag(Character report_flag) {
		this.report_flag = report_flag;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public BAML_RBS_REPORT_PROCEDURE(String report_code, String report_name, Character report_flag, Date report_date) {
		super();
		this.report_code = report_code;
		this.report_name = report_name;
		this.report_flag = report_flag;
		this.report_date = report_date;
	}
	public BAML_RBS_REPORT_PROCEDURE() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

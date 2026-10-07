package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BAML_ETL_ERROR_INFO_TB")
public class EtlErrorInfo {

	
	String module_name;
	String procedure_name;
	@Id
	Date report_date;
	String error_details;
	Date recr_time;
	
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getProcedure_name() {
		return procedure_name;
	}
	public void setProcedure_name(String procedure_name) {
		this.procedure_name = procedure_name;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getError_details() {
		return error_details;
	}
	public void setError_details(String error_details) {
		this.error_details = error_details;
	}
	public Date getRecr_time() {
		return recr_time;
	}
	public void setRecr_time(Date recr_time) {
		this.recr_time = recr_time;
	}
	public EtlErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.bornfire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BAML_REPORT_VALIDATION_TABLE")
public class ReportValidations {
	@Id
	private String srl_no;
	private String val_det;
	private String val_tables;
	private String cur_status;
	private String pre_status;

	public String getSrl_no() {
		return srl_no;
	}

	public String getVal_det() {
		return val_det;
	}

	public String getVal_tables() {
		return val_tables;
	}

	

	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}

	public void setVal_det(String val_det) {
		this.val_det = val_det;
	}

	public void setVal_tables(String val_tables) {
		this.val_tables = val_tables;
	}

	public String getCur_status() {
		return cur_status;
	}

	public String getPre_status() {
		return pre_status;
	}

	public void setCur_status(String cur_status) {
		this.cur_status = cur_status;
	}

	public void setPre_status(String pre_status) {
		this.pre_status = pre_status;
	}

	public ReportValidations(String srl_no, String val_det, String val_tables, String cur_status, String pre_status) {
		super();
		this.srl_no = srl_no;
		this.val_det = val_det;
		this.val_tables = val_tables;
		this.cur_status = cur_status;
		this.pre_status = pre_status;
	}

	public ReportValidations() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

package com.bornfire.entity.t21;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class T21DetailId implements Serializable {

	private String cust_id;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date report_date;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public T21DetailId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T21DetailId(String cust_id, Date report_date) {
		super();
		this.cust_id = cust_id;
		this.report_date = report_date;
	}

	
}

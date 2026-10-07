package com.bornfire.entity.t13;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class T13DetailId implements Serializable {

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

	public T13DetailId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T13DetailId(String cust_id, Date report_date) {
		super();
		this.cust_id = cust_id;
		this.report_date = report_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cust_id == null) ? 0 : cust_id.hashCode());
		result = prime * result + ((report_date == null) ? 0 : report_date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		T13DetailId other = (T13DetailId) obj;
		if (cust_id == null) {
			if (other.cust_id != null)
				return false;
		} else if (!cust_id.equals(other.cust_id))
			return false;
		if (report_date == null) {
			if (other.report_date != null)
				return false;
		} else if (!report_date.equals(other.report_date))
			return false;
		return true;
	}

	
}

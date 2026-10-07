package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;
@Embeddable
public class T2CurrentId implements Serializable{

	private static final long serialVersionUID = 1L;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	report_from_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	report_to_date;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date report_date;

	public Date getReport_from_date() {
		return report_from_date;
	}

	public void setReport_from_date(Date report_from_date) {
		this.report_from_date = report_from_date;
	}

	public Date getReport_to_date() {
		return report_to_date;
	}

	public void setReport_to_date(Date report_to_date) {
		this.report_to_date = report_to_date;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public T2CurrentId(Date report_from_date, Date report_to_date, Date report_date) {
		this.report_from_date = report_from_date;
		this.report_to_date = report_to_date;
		this.report_date = report_date;
	}
	
	public T2CurrentId() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((report_date == null) ? 0 : report_date.hashCode());
		result = prime * result + ((report_from_date == null) ? 0 : report_from_date.hashCode());
		result = prime * result + ((report_to_date == null) ? 0 : report_to_date.hashCode());
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
		T2CurrentId other = (T2CurrentId) obj;
		if (report_date == null) {
			if (other.report_date != null)
				return false;
		} else if (!report_date.equals(other.report_date))
			return false;
		if (report_from_date == null) {
			if (other.report_from_date != null)
				return false;
		} else if (!report_from_date.equals(other.report_from_date))
			return false;
		if (report_to_date == null) {
			if (other.report_to_date != null)
				return false;
		} else if (!report_to_date.equals(other.report_to_date))
			return false;
		return true;
	}
	
	
	
	
	
	
}

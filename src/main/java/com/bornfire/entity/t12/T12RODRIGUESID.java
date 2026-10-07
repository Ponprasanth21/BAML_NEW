package com.bornfire.entity.t12;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class T12RODRIGUESID implements Serializable {

	private BigDecimal	sn;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	report_date;
	public BigDecimal getSn() {
		return sn;
	}
	public void setSn(BigDecimal sn) {
		this.sn = sn;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public T12RODRIGUESID(BigDecimal sn, Date report_date) {
		super();
		this.sn = sn;
		this.report_date = report_date;
	}
	public T12RODRIGUESID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.bornfire.entity.t9;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class T9DataMaintenanceId implements Serializable{
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	private Date tran_date;
	private String tran_id;
	private BigDecimal part_tran_id;
	public Date getTran_date() {
		return tran_date;
	}
	public String getTran_id() {
		return tran_id;
	}
	
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public BigDecimal getPart_tran_id() {
		return part_tran_id;
	}
	public void setPart_tran_id(BigDecimal part_tran_id) {
		this.part_tran_id = part_tran_id;
	}
	

}

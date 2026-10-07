package com.bornfire.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;
@Embeddable
public class AlertTransactionEmbedded implements Serializable{


	
	private String	tran_id;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	tran_date;
	private String	part_tran_srl_num;
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getPart_tran_srl_num() {
		return part_tran_srl_num;
	}
	public void setPart_tran_srl_num(String part_tran_srl_num) {
		this.part_tran_srl_num = part_tran_srl_num;
	}
	public AlertTransactionEmbedded(String tran_id, Date tran_date, String part_tran_srl_num) {
		super();
		this.tran_id = tran_id;
		this.tran_date = tran_date;
		this.part_tran_srl_num = part_tran_srl_num;
	}
	public AlertTransactionEmbedded() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AlertTransactionEmbedded [tran_id=" + tran_id + ", tran_date=" + tran_date + ", part_tran_srl_num="
				+ part_tran_srl_num + "]";
	}
	
	
}

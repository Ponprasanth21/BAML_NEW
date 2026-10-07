package com.bornfire.entity.t12;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T12_RODRIGUES")
public class T12RODRIGUES {
	@Id
	private BigDecimal	sn;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	report_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	tran_date;
	private String	cust_name;

	private String	cif;
	private String	purpose_of_tran;
	private BigDecimal	tran_amount;
	private String	risk_category;
	private String	type_of_tran;
	private String	nid;

	
	private String risk_rating;
	private String report_name;
	
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getPurpose_of_tran() {
		return purpose_of_tran;
	}
	public void setPurpose_of_tran(String purpose_of_tran) {
		this.purpose_of_tran = purpose_of_tran;
	}
	public BigDecimal getTran_amount() {
		return tran_amount;
	}
	public void setTran_amount(BigDecimal tran_amount) {
		this.tran_amount = tran_amount;
	}
	public String getRisk_category() {
		return risk_category;
	}
	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}
	public String getType_of_tran() {
		return type_of_tran;
	}
	public void setType_of_tran(String type_of_tran) {
		this.type_of_tran = type_of_tran;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getRisk_rating() {
		return risk_rating;
	}
	public void setRisk_rating(String risk_rating) {
		this.risk_rating = risk_rating;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
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
	public T12RODRIGUES(BigDecimal sn, Date report_date, Date tran_date, String cust_name, String cif,
			String purpose_of_tran, BigDecimal tran_amount, String risk_category, String type_of_tran, String nid,
			String risk_rating, String report_name) {
		super();
		this.sn = sn;
		this.report_date = report_date;
		this.tran_date = tran_date;
		this.cust_name = cust_name;
		this.cif = cif;
		this.purpose_of_tran = purpose_of_tran;
		this.tran_amount = tran_amount;
		this.risk_category = risk_category;
		this.type_of_tran = type_of_tran;
		this.nid = nid;
		this.risk_rating = risk_rating;
		this.report_name = report_name;
	}
	public T12RODRIGUES() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}

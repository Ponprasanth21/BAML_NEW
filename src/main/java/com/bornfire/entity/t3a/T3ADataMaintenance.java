package com.bornfire.entity.t3a;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="T3_RBS_MASTER")
@IdClass(T3ADataMaintenanceId.class)
public class T3ADataMaintenance {
	
	private String	foracid;
	@Id
	private String	tran_id;
	@Id
	private String	part_tran_id;
	private String	part_tran_type;
	private String	schm_code;
	private String	acct_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	acct_opn_date;
	private String	risk_rating_code;
	private String	risk_rating;
	@Id
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	tran_date;
	private String	tran_particular;
	private BigDecimal	debit_amount;
	private BigDecimal	credit_amount;
	private String	resident_status;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	acct_cls_date;
	private String	country_code;
	private String	address_1;
	private String	address_2;
	private String	address_3;
	private String	sector_code;
	private String	transfer_type;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	report_date;
	private String	process_owner;
	
	
	
	
	public String getForacid() {
		return foracid;
	}
	public String getTran_id() {
		return tran_id;
	}
	public String getPart_tran_id() {
		return part_tran_id;
	}
	public String getPart_tran_type() {
		return part_tran_type;
	}
	public String getSchm_code() {
		return schm_code;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public Date getAcct_opn_date() {
		return acct_opn_date;
	}
	public String getRisk_rating_code() {
		return risk_rating_code;
	}
	public String getRisk_rating() {
		return risk_rating;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public String getTran_particular() {
		return tran_particular;
	}
	public BigDecimal getDebit_amount() {
		return debit_amount;
	}
	public BigDecimal getCredit_amount() {
		return credit_amount;
	}
	public String getResident_status() {
		return resident_status;
	}
	public Date getAcct_cls_date() {
		return acct_cls_date;
	}
	public String getCountry_code() {
		return country_code;
	}
	public String getAddress_1() {
		return address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public String getAddress_3() {
		return address_3;
	}
	public String getSector_code() {
		return sector_code;
	}
	public String getTransfer_type() {
		return transfer_type;
	}
	public Date getReport_date() {
		return report_date;
	}
	public String getProcess_owner() {
		return process_owner;
	}
	public void setForacid(String foracid) {
		this.foracid = foracid;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public void setPart_tran_id(String part_tran_id) {
		this.part_tran_id = part_tran_id;
	}
	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public void setAcct_opn_date(Date acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
	}
	public void setRisk_rating_code(String risk_rating_code) {
		this.risk_rating_code = risk_rating_code;
	}
	public void setRisk_rating(String risk_rating) {
		this.risk_rating = risk_rating;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public void setTran_particular(String tran_particular) {
		this.tran_particular = tran_particular;
	}
	public void setDebit_amount(BigDecimal debit_amount) {
		this.debit_amount = debit_amount;
	}
	public void setCredit_amount(BigDecimal credit_amount) {
		this.credit_amount = credit_amount;
	}
	public void setResident_status(String resident_status) {
		this.resident_status = resident_status;
	}
	public void setAcct_cls_date(Date acct_cls_date) {
		this.acct_cls_date = acct_cls_date;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public void setAddress_3(String address_3) {
		this.address_3 = address_3;
	}
	public void setSector_code(String sector_code) {
		this.sector_code = sector_code;
	}
	public void setTransfer_type(String transfer_type) {
		this.transfer_type = transfer_type;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public void setProcess_owner(String process_owner) {
		this.process_owner = process_owner;
	}
	public T3ADataMaintenance(String foracid, String tran_id, String part_tran_id, String part_tran_type,
			String schm_code, String acct_name, Date acct_opn_date, String risk_rating_code, String risk_rating,
			Date tran_date, String tran_particular, BigDecimal debit_amount, BigDecimal credit_amount,
			String resident_status, Date acct_cls_date, String country_code, String address_1, String address_2,
			String address_3, String sector_code, String transfer_type, Date report_date, String process_owner) {
		super();
		this.foracid = foracid;
		this.tran_id = tran_id;
		this.part_tran_id = part_tran_id;
		this.part_tran_type = part_tran_type;
		this.schm_code = schm_code;
		this.acct_name = acct_name;
		this.acct_opn_date = acct_opn_date;
		this.risk_rating_code = risk_rating_code;
		this.risk_rating = risk_rating;
		this.tran_date = tran_date;
		this.tran_particular = tran_particular;
		this.debit_amount = debit_amount;
		this.credit_amount = credit_amount;
		this.resident_status = resident_status;
		this.acct_cls_date = acct_cls_date;
		this.country_code = country_code;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.address_3 = address_3;
		this.sector_code = sector_code;
		this.transfer_type = transfer_type;
		this.report_date = report_date;
		this.process_owner = process_owner;
	}
	public T3ADataMaintenance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
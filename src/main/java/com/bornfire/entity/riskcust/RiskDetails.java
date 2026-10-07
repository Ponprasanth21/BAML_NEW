package com.bornfire.entity.riskcust;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_REVIEW_RISK_DETAIL_TEMP")
public class RiskDetails {
	
	private String	cust_name;
	private String	cif_id;
	@Id
	private String	acid;
	private String	occupation;
	private String	pep;
	private String	hnwi;
	private String	risk_category;
	private String	income;
	private String	source_of_income;
	private String	nature_of_transaction;
	private String	schm_type;
	private String	schm_code;
	private BigDecimal	total_bal_a;
	private BigDecimal	total_bal_b;
	private BigDecimal	diff_amount;
	private String	mode_of_payment;
	private String	status_chng_customer;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCif_id() {
		return cif_id;
	}
	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public String getHnwi() {
		return hnwi;
	}
	public void setHnwi(String hnwi) {
		this.hnwi = hnwi;
	}
	public String getRisk_category() {
		return risk_category;
	}
	public void setRisk_category(String risk_category) {
		this.risk_category = risk_category;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getSource_of_income() {
		return source_of_income;
	}
	public void setSource_of_income(String source_of_income) {
		this.source_of_income = source_of_income;
	}
	public String getNature_of_transaction() {
		return nature_of_transaction;
	}
	public void setNature_of_transaction(String nature_of_transaction) {
		this.nature_of_transaction = nature_of_transaction;
	}
	public String getSchm_type() {
		return schm_type;
	}
	public void setSchm_type(String schm_type) {
		this.schm_type = schm_type;
	}
	public String getSchm_code() {
		return schm_code;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	public BigDecimal getTotal_bal_a() {
		return total_bal_a;
	}
	public void setTotal_bal_a(BigDecimal total_bal_a) {
		this.total_bal_a = total_bal_a;
	}
	public BigDecimal getTotal_bal_b() {
		return total_bal_b;
	}
	public void setTotal_bal_b(BigDecimal total_bal_b) {
		this.total_bal_b = total_bal_b;
	}
	public BigDecimal getDiff_amount() {
		return diff_amount;
	}
	public void setDiff_amount(BigDecimal diff_amount) {
		this.diff_amount = diff_amount;
	}
	public String getMode_of_payment() {
		return mode_of_payment;
	}
	public void setMode_of_payment(String mode_of_payment) {
		this.mode_of_payment = mode_of_payment;
	}
	public String getStatus_chng_customer() {
		return status_chng_customer;
	}
	public void setStatus_chng_customer(String status_chng_customer) {
		this.status_chng_customer = status_chng_customer;
	}
	public RiskDetails(String cust_name, String cif_id, String acid, String occupation, String pep, String hnwi,
			String risk_category, String income, String source_of_income, String nature_of_transaction,
			String schm_type, String schm_code, BigDecimal total_bal_a, BigDecimal total_bal_b, BigDecimal diff_amount,
			String mode_of_payment, String status_chng_customer) {
		super();
		this.cust_name = cust_name;
		this.cif_id = cif_id;
		this.acid = acid;
		this.occupation = occupation;
		this.pep = pep;
		this.hnwi = hnwi;
		this.risk_category = risk_category;
		this.income = income;
		this.source_of_income = source_of_income;
		this.nature_of_transaction = nature_of_transaction;
		this.schm_type = schm_type;
		this.schm_code = schm_code;
		this.total_bal_a = total_bal_a;
		this.total_bal_b = total_bal_b;
		this.diff_amount = diff_amount;
		this.mode_of_payment = mode_of_payment;
		this.status_chng_customer = status_chng_customer;
	}
	public RiskDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

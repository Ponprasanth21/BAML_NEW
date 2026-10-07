package com.bornfire.entity.riskcust;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_REVIEW_RISK_TABLE")
public class RiskSuma {
	@Id
	private String	risk_category_h;
	private String	risk_category_m;
	private String	risk_category_l;
	private String	risk_category_t;
	private BigDecimal	total_cust_h;
	private BigDecimal	total_cust_m;
	private BigDecimal	total_cust_l;
	private BigDecimal	total_cust_t;
	private BigDecimal	start_date_bal_h;
	private BigDecimal	start_date_bal_m;
	private BigDecimal	start_date_bal_l;
	private BigDecimal	start_date_bal_t;
	private BigDecimal	end_date_bal_h;
	private BigDecimal	end_date_bal_m;
	private BigDecimal	end_date_bal_l;
	private BigDecimal	end_date_bal_t;
	private BigDecimal	diff_balance_h;
	private BigDecimal	diff_balance_m;
	private BigDecimal	diff_balance_l;
	private BigDecimal	diff_balance_t;
	public String getRisk_category_h() {
		return risk_category_h;
	}
	public void setRisk_category_h(String risk_category_h) {
		this.risk_category_h = risk_category_h;
	}
	public String getRisk_category_m() {
		return risk_category_m;
	}
	public void setRisk_category_m(String risk_category_m) {
		this.risk_category_m = risk_category_m;
	}
	public String getRisk_category_l() {
		return risk_category_l;
	}
	public void setRisk_category_l(String risk_category_l) {
		this.risk_category_l = risk_category_l;
	}
	public String getRisk_category_t() {
		return risk_category_t;
	}
	public void setRisk_category_t(String risk_category_t) {
		this.risk_category_t = risk_category_t;
	}
	public BigDecimal getTotal_cust_h() {
		return total_cust_h;
	}
	public void setTotal_cust_h(BigDecimal total_cust_h) {
		this.total_cust_h = total_cust_h;
	}
	public BigDecimal getTotal_cust_m() {
		return total_cust_m;
	}
	public void setTotal_cust_m(BigDecimal total_cust_m) {
		this.total_cust_m = total_cust_m;
	}
	public BigDecimal getTotal_cust_l() {
		return total_cust_l;
	}
	public void setTotal_cust_l(BigDecimal total_cust_l) {
		this.total_cust_l = total_cust_l;
	}
	public BigDecimal getTotal_cust_t() {
		return total_cust_t;
	}
	public void setTotal_cust_t(BigDecimal total_cust_t) {
		this.total_cust_t = total_cust_t;
	}
	public BigDecimal getStart_date_bal_h() {
		return start_date_bal_h;
	}
	public void setStart_date_bal_h(BigDecimal start_date_bal_h) {
		this.start_date_bal_h = start_date_bal_h;
	}
	public BigDecimal getStart_date_bal_m() {
		return start_date_bal_m;
	}
	public void setStart_date_bal_m(BigDecimal start_date_bal_m) {
		this.start_date_bal_m = start_date_bal_m;
	}
	public BigDecimal getStart_date_bal_l() {
		return start_date_bal_l;
	}
	public void setStart_date_bal_l(BigDecimal start_date_bal_l) {
		this.start_date_bal_l = start_date_bal_l;
	}
	public BigDecimal getStart_date_bal_t() {
		return start_date_bal_t;
	}
	public void setStart_date_bal_t(BigDecimal start_date_bal_t) {
		this.start_date_bal_t = start_date_bal_t;
	}
	public BigDecimal getEnd_date_bal_h() {
		return end_date_bal_h;
	}
	public void setEnd_date_bal_h(BigDecimal end_date_bal_h) {
		this.end_date_bal_h = end_date_bal_h;
	}
	public BigDecimal getEnd_date_bal_m() {
		return end_date_bal_m;
	}
	public void setEnd_date_bal_m(BigDecimal end_date_bal_m) {
		this.end_date_bal_m = end_date_bal_m;
	}
	public BigDecimal getEnd_date_bal_l() {
		return end_date_bal_l;
	}
	public void setEnd_date_bal_l(BigDecimal end_date_bal_l) {
		this.end_date_bal_l = end_date_bal_l;
	}
	public BigDecimal getEnd_date_bal_t() {
		return end_date_bal_t;
	}
	public void setEnd_date_bal_t(BigDecimal end_date_bal_t) {
		this.end_date_bal_t = end_date_bal_t;
	}
	public BigDecimal getDiff_balance_h() {
		return diff_balance_h;
	}
	public void setDiff_balance_h(BigDecimal diff_balance_h) {
		this.diff_balance_h = diff_balance_h;
	}
	public BigDecimal getDiff_balance_m() {
		return diff_balance_m;
	}
	public void setDiff_balance_m(BigDecimal diff_balance_m) {
		this.diff_balance_m = diff_balance_m;
	}
	public BigDecimal getDiff_balance_l() {
		return diff_balance_l;
	}
	public void setDiff_balance_l(BigDecimal diff_balance_l) {
		this.diff_balance_l = diff_balance_l;
	}
	public BigDecimal getDiff_balance_t() {
		return diff_balance_t;
	}
	public void setDiff_balance_t(BigDecimal diff_balance_t) {
		this.diff_balance_t = diff_balance_t;
	}
	public RiskSuma(String risk_category_h, String risk_category_m, String risk_category_l, String risk_category_t,
			BigDecimal total_cust_h, BigDecimal total_cust_m, BigDecimal total_cust_l, BigDecimal total_cust_t,
			BigDecimal start_date_bal_h, BigDecimal start_date_bal_m, BigDecimal start_date_bal_l,
			BigDecimal start_date_bal_t, BigDecimal end_date_bal_h, BigDecimal end_date_bal_m,
			BigDecimal end_date_bal_l, BigDecimal end_date_bal_t, BigDecimal diff_balance_h, BigDecimal diff_balance_m,
			BigDecimal diff_balance_l, BigDecimal diff_balance_t) {
		super();
		this.risk_category_h = risk_category_h;
		this.risk_category_m = risk_category_m;
		this.risk_category_l = risk_category_l;
		this.risk_category_t = risk_category_t;
		this.total_cust_h = total_cust_h;
		this.total_cust_m = total_cust_m;
		this.total_cust_l = total_cust_l;
		this.total_cust_t = total_cust_t;
		this.start_date_bal_h = start_date_bal_h;
		this.start_date_bal_m = start_date_bal_m;
		this.start_date_bal_l = start_date_bal_l;
		this.start_date_bal_t = start_date_bal_t;
		this.end_date_bal_h = end_date_bal_h;
		this.end_date_bal_m = end_date_bal_m;
		this.end_date_bal_l = end_date_bal_l;
		this.end_date_bal_t = end_date_bal_t;
		this.diff_balance_h = diff_balance_h;
		this.diff_balance_m = diff_balance_m;
		this.diff_balance_l = diff_balance_l;
		this.diff_balance_t = diff_balance_t;
	}
	public RiskSuma() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}

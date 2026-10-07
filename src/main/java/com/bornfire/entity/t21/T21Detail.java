package com.bornfire.entity.t21;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = " T21_INACTIVE_DORM_ACCTS_DETAILS")
public class T21Detail implements Serializable {

	@EmbeddedId
	T21DetailId t21detailid;

	private String cust_name;
	private String customer_risk_rating;
	private Date risk_rating_date;
	private String acct_no;
	private String acct_name;
	private Date date_of_open;
	private String accc_status;
	private Date status_date;
	private String acct_remarks;
	private String acct_crncy;
	private BigDecimal act_bal;
	private Character qtr_flg;
	private Character entity_flg;
	private Character del_flg;
	private Character modify_flg;
	private Date entry_date;
	private Date modify_date;
	private Date verify_date;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private String report_code;
	private String report_name;
	private Character arch_flg;

	public T21DetailId getT21detailid() {
		return t21detailid;
	}

	public void setT21detailid(T21DetailId t21detailid) {
		this.t21detailid = t21detailid;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCustomer_risk_rating() {
		return customer_risk_rating;
	}

	public void setCustomer_risk_rating(String customer_risk_rating) {
		this.customer_risk_rating = customer_risk_rating;
	}

	public Date getRisk_rating_date() {
		return risk_rating_date;
	}

	public void setRisk_rating_date(Date risk_rating_date) {
		this.risk_rating_date = risk_rating_date;
	}

	public String getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public Date getDate_of_open() {
		return date_of_open;
	}

	public void setDate_of_open(Date date_of_open) {
		this.date_of_open = date_of_open;
	}

	public String getAccc_status() {
		return accc_status;
	}

	public void setAccc_status(String accc_status) {
		this.accc_status = accc_status;
	}

	public Date getStatus_date() {
		return status_date;
	}

	public void setStatus_date(Date status_date) {
		this.status_date = status_date;
	}

	public String getAcct_remarks() {
		return acct_remarks;
	}

	public void setAcct_remarks(String acct_remarks) {
		this.acct_remarks = acct_remarks;
	}

	public String getAcct_crncy() {
		return acct_crncy;
	}

	public void setAcct_crncy(String acct_crncy) {
		this.acct_crncy = acct_crncy;
	}

	public BigDecimal getAct_bal() {
		return act_bal;
	}

	public void setAct_bal(BigDecimal act_bal) {
		this.act_bal = act_bal;
	}

	
	public Character getQtr_flg() {
		return qtr_flg;
	}

	public void setQtr_flg(Character qtr_flg) {
		this.qtr_flg = qtr_flg;
	}

	public Character getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}

	public Character getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}

	public Character getModify_flg() {
		return modify_flg;
	}

	public void setModify_flg(Character modify_flg) {
		this.modify_flg = modify_flg;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Date getVerify_date() {
		return verify_date;
	}

	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public String getReport_code() {
		return report_code;
	}

	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}


	public Character getArch_flg() {
		return arch_flg;
	}

	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}

	public T21Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T21Detail(T21DetailId t21detailid, String cust_name, String customer_risk_rating, Date risk_rating_date,
			String acct_no, String acct_name, Date date_of_open, String accc_status, Date status_date,
			String acct_remarks, String acct_crncy, BigDecimal act_bal, Character qtr_flg2, Character entity_flg2,
			Character del_flg2, Character modify_flg2, Date entry_date, Date modify_date, Date verify_date, String entry_user,
			String modify_user, String verify_user, String report_code, String report_name, Character arch_flg2) {
		super();
		this.t21detailid = t21detailid;
		this.cust_name = cust_name;
		this.customer_risk_rating = customer_risk_rating;
		this.risk_rating_date = risk_rating_date;
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.date_of_open = date_of_open;
		this.accc_status = accc_status;
		this.status_date = status_date;
		this.acct_remarks = acct_remarks;
		this.acct_crncy = acct_crncy;
		this.act_bal = act_bal;
		this.qtr_flg = qtr_flg2;
		this.entity_flg = entity_flg2;
		this.del_flg = del_flg2;
		this.modify_flg = modify_flg2;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.report_code = report_code;
		this.report_name = report_name;
		this.arch_flg = arch_flg2;
	}
	
	

}

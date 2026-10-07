package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name = "BAML_USER_PROFILE_TABLE_FINACLE")
public class FinUserProfileEntity {
	
	@Id
	private String	user_id;
	private String	user_work_class;
	private String	bank_id;
	private String	bank_name;
	private String	sol_id;
	private String	sol_desc;
	private String	user_sol_tenor;
	private BigDecimal	user_max_inactive_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	user_acct_expy_date;
	private String	user_appl_name;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	user_disabled_from_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	user_disabled_upto_date;
	private String	user_emp_id;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date	user_login_time_low;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date	user_login_time_high;
	private String	lchg_user_id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	lchg_time;
	private String	rcre_user_id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	rcre_time;
	private String	role_id;
	private String	emp_name;
	private String	emp_desig;
	private String	emp_stat;
	private String	emp_email_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_work_class() {
		return user_work_class;
	}
	public void setUser_work_class(String user_work_class) {
		this.user_work_class = user_work_class;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getSol_desc() {
		return sol_desc;
	}
	public void setSol_desc(String sol_desc) {
		this.sol_desc = sol_desc;
	}
	public String getUser_sol_tenor() {
		return user_sol_tenor;
	}
	public void setUser_sol_tenor(String user_sol_tenor) {
		this.user_sol_tenor = user_sol_tenor;
	}
	public BigDecimal getUser_max_inactive_time() {
		return user_max_inactive_time;
	}
	public void setUser_max_inactive_time(BigDecimal user_max_inactive_time) {
		this.user_max_inactive_time = user_max_inactive_time;
	}
	public Date getUser_acct_expy_date() {
		return user_acct_expy_date;
	}
	public void setUser_acct_expy_date(Date user_acct_expy_date) {
		this.user_acct_expy_date = user_acct_expy_date;
	}
	public String getUser_appl_name() {
		return user_appl_name;
	}
	public void setUser_appl_name(String user_appl_name) {
		this.user_appl_name = user_appl_name;
	}
	public Date getUser_disabled_from_date() {
		return user_disabled_from_date;
	}
	public void setUser_disabled_from_date(Date user_disabled_from_date) {
		this.user_disabled_from_date = user_disabled_from_date;
	}
	public Date getUser_disabled_upto_date() {
		return user_disabled_upto_date;
	}
	public void setUser_disabled_upto_date(Date user_disabled_upto_date) {
		this.user_disabled_upto_date = user_disabled_upto_date;
	}
	public String getUser_emp_id() {
		return user_emp_id;
	}
	public void setUser_emp_id(String user_emp_id) {
		this.user_emp_id = user_emp_id;
	}
	public Date getUser_login_time_low() {
		return user_login_time_low;
	}
	public void setUser_login_time_low(Date user_login_time_low) {
		this.user_login_time_low = user_login_time_low;
	}
	public Date getUser_login_time_high() {
		return user_login_time_high;
	}
	public void setUser_login_time_high(Date user_login_time_high) {
		this.user_login_time_high = user_login_time_high;
	}
	public String getLchg_user_id() {
		return lchg_user_id;
	}
	public void setLchg_user_id(String lchg_user_id) {
		this.lchg_user_id = lchg_user_id;
	}
	public Date getLchg_time() {
		return lchg_time;
	}
	public void setLchg_time(Date lchg_time) {
		this.lchg_time = lchg_time;
	}
	public String getRcre_user_id() {
		return rcre_user_id;
	}
	public void setRcre_user_id(String rcre_user_id) {
		this.rcre_user_id = rcre_user_id;
	}
	public Date getRcre_time() {
		return rcre_time;
	}
	public void setRcre_time(Date rcre_time) {
		this.rcre_time = rcre_time;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_desig() {
		return emp_desig;
	}
	public void setEmp_desig(String emp_desig) {
		this.emp_desig = emp_desig;
	}
	public String getEmp_stat() {
		return emp_stat;
	}
	public void setEmp_stat(String emp_stat) {
		this.emp_stat = emp_stat;
	}
	public String getEmp_email_id() {
		return emp_email_id;
	}
	public void setEmp_email_id(String emp_email_id) {
		this.emp_email_id = emp_email_id;
	}

	
	
	
	

}

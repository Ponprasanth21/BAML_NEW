package com.bornfire.entity.t4;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="T4_CUSTOMER_PROFILING_DETAILED_TABLE")
public class T4ReportDetail {
	
	private String	cust_id;
	private String	customer_name;
	private String	branch_id;
	private String	branch_name;
	private String	bank_id;
	private String	ownership_type;
	private String	cust_rating;
	private Date	customer_rating_date;
	private Date	customer_due_rating_date;
	private String	customer_type;
	private String	report_quarter;
	private String	remarks;
	private String	del_flg;
	private String	entity_cre_flg;
	private Date	entity_cre_date;
	private String	mod_flg;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	private String	aml_code_1;
	private String	aml_code_2;
	private String	aml_code_3;
	private String	aml_code_4;
	private String	aml_code_5;
	private String	aml_code_6;
	private String	aml_code_7;
	private String	aml_code_8;
	private String	aml_code_9;
	private String	aml_code_10;
	@Id
	private Date	report_date;
	private String	process_owner;
	private String	qtr_flg;
	private Date	verify_date;
	private String	verify_user;
	private String	arch_flg;
	private String	cell_mapping;
	private String	tran_channel;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getOwnership_type() {
		return ownership_type;
	}
	public void setOwnership_type(String ownership_type) {
		this.ownership_type = ownership_type;
	}
	public String getCust_rating() {
		return cust_rating;
	}
	public void setCust_rating(String cust_rating) {
		this.cust_rating = cust_rating;
	}
	public Date getCustomer_rating_date() {
		return customer_rating_date;
	}
	public void setCustomer_rating_date(Date customer_rating_date) {
		this.customer_rating_date = customer_rating_date;
	}
	public Date getCustomer_due_rating_date() {
		return customer_due_rating_date;
	}
	public void setCustomer_due_rating_date(Date customer_due_rating_date) {
		this.customer_due_rating_date = customer_due_rating_date;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getReport_quarter() {
		return report_quarter;
	}
	public void setReport_quarter(String report_quarter) {
		this.report_quarter = report_quarter;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(String entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public Date getEntity_cre_date() {
		return entity_cre_date;
	}
	public void setEntity_cre_date(Date entity_cre_date) {
		this.entity_cre_date = entity_cre_date;
	}
	public String getMod_flg() {
		return mod_flg;
	}
	public void setMod_flg(String mod_flg) {
		this.mod_flg = mod_flg;
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
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getAml_code_1() {
		return aml_code_1;
	}
	public void setAml_code_1(String aml_code_1) {
		this.aml_code_1 = aml_code_1;
	}
	public String getAml_code_2() {
		return aml_code_2;
	}
	public void setAml_code_2(String aml_code_2) {
		this.aml_code_2 = aml_code_2;
	}
	public String getAml_code_3() {
		return aml_code_3;
	}
	public void setAml_code_3(String aml_code_3) {
		this.aml_code_3 = aml_code_3;
	}
	public String getAml_code_4() {
		return aml_code_4;
	}
	public void setAml_code_4(String aml_code_4) {
		this.aml_code_4 = aml_code_4;
	}
	public String getAml_code_5() {
		return aml_code_5;
	}
	public void setAml_code_5(String aml_code_5) {
		this.aml_code_5 = aml_code_5;
	}
	public String getAml_code_6() {
		return aml_code_6;
	}
	public void setAml_code_6(String aml_code_6) {
		this.aml_code_6 = aml_code_6;
	}
	public String getAml_code_7() {
		return aml_code_7;
	}
	public void setAml_code_7(String aml_code_7) {
		this.aml_code_7 = aml_code_7;
	}
	public String getAml_code_8() {
		return aml_code_8;
	}
	public void setAml_code_8(String aml_code_8) {
		this.aml_code_8 = aml_code_8;
	}
	public String getAml_code_9() {
		return aml_code_9;
	}
	public void setAml_code_9(String aml_code_9) {
		this.aml_code_9 = aml_code_9;
	}
	public String getAml_code_10() {
		return aml_code_10;
	}
	public void setAml_code_10(String aml_code_10) {
		this.aml_code_10 = aml_code_10;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getProcess_owner() {
		return process_owner;
	}
	public void setProcess_owner(String process_owner) {
		this.process_owner = process_owner;
	}
	public String getQtr_flg() {
		return qtr_flg;
	}
	public void setQtr_flg(String qtr_flg) {
		this.qtr_flg = qtr_flg;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public String getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(String arch_flg) {
		this.arch_flg = arch_flg;
	}
	public String getCell_mapping() {
		return cell_mapping;
	}
	public void setCell_mapping(String cell_mapping) {
		this.cell_mapping = cell_mapping;
	}
	public String getTran_channel() {
		return tran_channel;
	}
	public void setTran_channel(String tran_channel) {
		this.tran_channel = tran_channel;
	}
	public T4ReportDetail(String cust_id, String customer_name, String branch_id, String branch_name, String bank_id,
			String ownership_type, String cust_rating, Date customer_rating_date, Date customer_due_rating_date,
			String customer_type, String report_quarter, String remarks, String del_flg, String entity_cre_flg,
			Date entity_cre_date, String mod_flg, String entry_user, String modify_user, String auth_user,
			Date entry_time, Date modify_time, Date auth_time, String aml_code_1, String aml_code_2, String aml_code_3,
			String aml_code_4, String aml_code_5, String aml_code_6, String aml_code_7, String aml_code_8,
			String aml_code_9, String aml_code_10, Date report_date, String process_owner, String qtr_flg,
			Date verify_date, String verify_user, String arch_flg, String cell_mapping, String tran_channel) {
		super();
		this.cust_id = cust_id;
		this.customer_name = customer_name;
		this.branch_id = branch_id;
		this.branch_name = branch_name;
		this.bank_id = bank_id;
		this.ownership_type = ownership_type;
		this.cust_rating = cust_rating;
		this.customer_rating_date = customer_rating_date;
		this.customer_due_rating_date = customer_due_rating_date;
		this.customer_type = customer_type;
		this.report_quarter = report_quarter;
		this.remarks = remarks;
		this.del_flg = del_flg;
		this.entity_cre_flg = entity_cre_flg;
		this.entity_cre_date = entity_cre_date;
		this.mod_flg = mod_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.aml_code_1 = aml_code_1;
		this.aml_code_2 = aml_code_2;
		this.aml_code_3 = aml_code_3;
		this.aml_code_4 = aml_code_4;
		this.aml_code_5 = aml_code_5;
		this.aml_code_6 = aml_code_6;
		this.aml_code_7 = aml_code_7;
		this.aml_code_8 = aml_code_8;
		this.aml_code_9 = aml_code_9;
		this.aml_code_10 = aml_code_10;
		this.report_date = report_date;
		this.process_owner = process_owner;
		this.qtr_flg = qtr_flg;
		this.verify_date = verify_date;
		this.verify_user = verify_user;
		this.arch_flg = arch_flg;
		this.cell_mapping = cell_mapping;
		this.tran_channel = tran_channel;
	}
	public T4ReportDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

package com.bornfire.entity.t22;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T22_ABAND_FUND_ACCTS_DETAILS")
public class T22Details {
	
	@Id
	private String	cust_id;
	private String	cust_name;
	private String	acct_no;
	private String	acct_name;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_open;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_aband;
	private String	acct_crncy;
	private BigDecimal	acct_bal;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_claim_bom;
	private String	remarks;
	private Character	qtr_flg;
	private Character	entity_flg;
	private Character	del_flg;
	private Character	modify_flg;
	private Date	entry_date;
	private Date	modify_date;
	private Date	verify_date;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private String	report_code;
	private String	report_name;
	private Date	report_date;
	private Character	arch_flg;
	private String	cell_mapping;
	private String	process_owner;
	private String	bank_id;
	private String	cust_type;
	private String	cust_rating;
	private Date	cust_rating_date;
	private String	ownership_type;
	private String	tran_channel;

	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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
	public Date getDate_of_aband() {
		return date_of_aband;
	}
	public void setDate_of_aband(Date date_of_aband) {
		this.date_of_aband = date_of_aband;
	}
	public String getAcct_crncy() {
		return acct_crncy;
	}
	public void setAcct_crncy(String acct_crncy) {
		this.acct_crncy = acct_crncy;
	}
	public BigDecimal getAcct_bal() {
		return acct_bal;
	}
	public void setAcct_bal(BigDecimal acct_bal) {
		this.acct_bal = acct_bal;
	}
	public Date getDate_of_claim_bom() {
		return date_of_claim_bom;
	}
	public void setDate_of_claim_bom(Date date_of_claim_bom) {
		this.date_of_claim_bom = date_of_claim_bom;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public Character getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}
	public T22Details() {
		super();
	}
	public String getCell_mapping() {
		return cell_mapping;
	}
	public void setCell_mapping(String cell_mapping) {
		this.cell_mapping = cell_mapping;
	}
	public String getProcess_owner() {
		return process_owner;
	}
	public void setProcess_owner(String process_owner) {
		this.process_owner = process_owner;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	public String getCust_rating() {
		return cust_rating;
	}
	public void setCust_rating(String cust_rating) {
		this.cust_rating = cust_rating;
	}
	public Date getCust_rating_date() {
		return cust_rating_date;
	}
	public void setCust_rating_date(Date cust_rating_date) {
		this.cust_rating_date = cust_rating_date;
	}
	public String getOwnership_type() {
		return ownership_type;
	}
	public void setOwnership_type(String ownership_type) {
		this.ownership_type = ownership_type;
	}
	public String getTran_channel() {
		return tran_channel;
	}
	public void setTran_channel(String tran_channel) {
		this.tran_channel = tran_channel;
	}
	public T22Details(String cust_id, String cust_name, String acct_no, String acct_name, Date date_of_open,
			Date date_of_aband, String acct_crncy, BigDecimal acct_bal, Date date_of_claim_bom, String remarks,
			Character qtr_flg, Character entity_flg, Character del_flg, Character modify_flg, Date entry_date,
			Date modify_date, Date verify_date, String entry_user, String modify_user, String verify_user,
			String report_code, String report_name, Date report_date, Character arch_flg, String cell_mapping,
			String process_owner, String bank_id, String cust_type, String cust_rating, Date cust_rating_date,
			String ownership_type, String tran_channel) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.date_of_open = date_of_open;
		this.date_of_aband = date_of_aband;
		this.acct_crncy = acct_crncy;
		this.acct_bal = acct_bal;
		this.date_of_claim_bom = date_of_claim_bom;
		this.remarks = remarks;
		this.qtr_flg = qtr_flg;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.report_code = report_code;
		this.report_name = report_name;
		this.report_date = report_date;
		this.arch_flg = arch_flg;
		this.cell_mapping = cell_mapping;
		this.process_owner = process_owner;
		this.bank_id = bank_id;
		this.cust_type = cust_type;
		this.cust_rating = cust_rating;
		this.cust_rating_date = cust_rating_date;
		this.ownership_type = ownership_type;
		this.tran_channel = tran_channel;
	}
	
	
}

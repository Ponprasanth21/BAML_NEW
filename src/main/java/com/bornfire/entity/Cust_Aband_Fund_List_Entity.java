package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="BAML_CUST_ABAND_FUND_LIST")
public class Cust_Aband_Fund_List_Entity implements Serializable{
	public Cust_Aband_Fund_List_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String foracid;
	private String acid;
	private String cif_id;
	private String cust_id;
	private String cust_name;
	private String acct_name;
	
	private String schm_type;
	private String schm_code;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date acct_opn_date;
	
	private String nature_of_tran;
	private String prod_type;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date maturity_date;
	
	private BigDecimal acct_bal;
	private BigDecimal interest_paid;
	private String remarks1;
	private String remarks2;
	private String address;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	aml_entry_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	aml_modify_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	aml_verify_time;
	
	private String	aml_entry_user;
	private String	aml_modify_user;
	private String	aml_verify_user;
	private String	entity_flag;
	private String	del_flag;
	private String	modify_flag;
	
	private String primary_branch_code;
	private String branch_name;
	private String gl_sub_head_code;
	private String currency_code;
	private String  acct_status;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date last_tran_date;
	 
	private BigDecimal interest_accrued;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date abandoned_fund_transfer_date;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date date_of_tran_bom;
	
	private BigDecimal  amount_transferred;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date date_of_claim;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date request_to_bom_date;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date received_from_bom_date;
	
	
	private BigDecimal amount_received;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date settlement_date;
	
	private BigDecimal amount_paid;
	
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getForacid() {
		return foracid;
	}
	public void setForacid(String foracid) {
		this.foracid = foracid;
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
	public Date getAcct_opn_date() {
		return acct_opn_date;
	}
	public void setAcct_opn_date(Date acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
	}
	public String getNature_of_tran() {
		return nature_of_tran;
	}
	public void setNature_of_tran(String nature_of_tran) {
		this.nature_of_tran = nature_of_tran;
	}
	public Date getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(Date maturity_date) {
		this.maturity_date = maturity_date;
	}
	public BigDecimal getInterest_paid() {
		return interest_paid;
	}
	public void setInterest_paid(BigDecimal interest_paid) {
		this.interest_paid = interest_paid;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	public Date getAml_entry_time() {
		return aml_entry_time;
	}
	public void setAml_entry_time(Date aml_entry_time) {
		this.aml_entry_time = aml_entry_time;
	}
	public Date getAml_modify_time() {
		return aml_modify_time;
	}
	public void setAml_modify_time(Date aml_modify_time) {
		this.aml_modify_time = aml_modify_time;
	}
	public Date getAml_verify_time() {
		return aml_verify_time;
	}
	public void setAml_verify_time(Date aml_verify_time) {
		this.aml_verify_time = aml_verify_time;
	}
	public String getAml_entry_user() {
		return aml_entry_user;
	}
	public void setAml_entry_user(String aml_entry_user) {
		this.aml_entry_user = aml_entry_user;
	}
	public String getAml_modify_user() {
		return aml_modify_user;
	}
	public void setAml_modify_user(String aml_modify_user) {
		this.aml_modify_user = aml_modify_user;
	}
	public String getAml_verify_user() {
		return aml_verify_user;
	}
	public void setAml_verify_user(String aml_verify_user) {
		this.aml_verify_user = aml_verify_user;
	}
	public String getEntity_flag() {
		return entity_flag;
	}
	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public Cust_Aband_Fund_List_Entity(String cif_id,String cust_name,String nat_of_tran,
			String product_type, Date maturity_date,BigDecimal amount,BigDecimal interest_paid,String scheme_type,String scheme_code,
			Date acc_opp_date,String remarks1,String remarks2, String entry_user,Date entry_time,String entity_flg,String del_flg ) {
		super();
		
	
	}
	public Cust_Aband_Fund_List_Entity(String primary_Branch_Code2, String branch_Name2, String cif_ID2,
			String cif_Name, String gl_sub_head_code2, String schm_type2, String schm_Code2, String currency_Code2,
			String account_No, String account_Name, Date account_Open_Date1, String account_Status,
			Date last_Transaction_Date1, BigDecimal account_Balance1, BigDecimal interest_Accrued1,
			BigDecimal interest_Paid1, Date abandoned_Fund_Transfer_Date1, String remarks12,
			BigDecimal amount_Transferred1, Date date_of_Claim_by_Customer1, String remarks22,
			Date date_of_Funds_received_from_BOM1, BigDecimal amount_Received1, Date settlement_Date1,
			BigDecimal amount_Paid1, String address,Date date_of_Transfer_to_BOM,Date request_to_BOM_Date1,String entry_user,Date entry_time,String entity_flg,String del_flg) {
		super();
	this.cust_id=cif_id;
		
		this.acct_name=cust_name;
		this.primary_branch_code=primary_Branch_Code2;
		this.branch_name=branch_Name2;
		this.cif_id=cif_ID2;
		this.cust_name=cif_Name;
		this.gl_sub_head_code=gl_sub_head_code2;
		this.schm_type=schm_type2;
		this.schm_code=schm_Code2;
		this.currency_code=currency_Code2;
		this.foracid=account_No;
		this.acct_name=account_Name;
		this.acct_opn_date=account_Open_Date1;
		this.acct_status=account_Status;
		this.last_tran_date=last_Transaction_Date1;
		this.acct_bal=account_Balance1;
		this.interest_accrued=interest_Accrued1;
		this.interest_paid=interest_Paid1;
		this.abandoned_fund_transfer_date=abandoned_Fund_Transfer_Date1;
		this.remarks1=remarks12;
		this.amount_transferred=amount_Transferred1;
		this.date_of_claim=date_of_Claim_by_Customer1;
		this.remarks2=remarks22;
		this.received_from_bom_date=date_of_Funds_received_from_BOM1;
		this.amount_received=amount_Received1;
		this.settlement_date=settlement_Date1;
		this.amount_paid=amount_Paid1;
		this.address=address;
		this.date_of_tran_bom=date_of_Transfer_to_BOM;
		this.request_to_bom_date=request_to_BOM_Date1;
		this.aml_entry_user=entry_user;
		this.aml_entry_time=entry_time;
		this.entity_flag=entity_flg;
		this.del_flag=del_flg;
		// TODO Auto-generated constructor stub
	}
	public String getCif_id() {
		return cif_id;
	}
	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}
	public String getPrimary_branch_code() {
		return primary_branch_code;
	}
	public void setPrimary_branch_code(String primary_branch_code) {
		this.primary_branch_code = primary_branch_code;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getGl_sub_head_code() {
		return gl_sub_head_code;
	}
	public void setGl_sub_head_code(String gl_sub_head_code) {
		this.gl_sub_head_code = gl_sub_head_code;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getAcct_status() {
		return acct_status;
	}
	public void setAcct_status(String acct_status) {
		this.acct_status = acct_status;
	}
	public Date getLast_tran_date() {
		return last_tran_date;
	}
	public void setLast_tran_date(Date last_tran_date) {
		this.last_tran_date = last_tran_date;
	}
	public BigDecimal getInterest_accrued() {
		return interest_accrued;
	}
	public void setInterest_accrued(BigDecimal interest_accrued) {
		this.interest_accrued = interest_accrued;
	}
	public Date getAbandoned_fund_transfer_date() {
		return abandoned_fund_transfer_date;
	}
	public void setAbandoned_fund_transfer_date(Date abandoned_fund_transfer_date) {
		this.abandoned_fund_transfer_date = abandoned_fund_transfer_date;
	}
	public Date getDate_of_tran_bom() {
		return date_of_tran_bom;
	}
	public void setDate_of_tran_bom(Date date_of_tran_bom) {
		this.date_of_tran_bom = date_of_tran_bom;
	}
	public Date getDate_of_claim() {
		return date_of_claim;
	}
	public void setDate_of_claim(Date date_of_claim) {
		this.date_of_claim = date_of_claim;
	}
	public Date getRequest_to_bom_date() {
		return request_to_bom_date;
	}
	public void setRequest_to_bom_date(Date request_to_bom_date) {
		this.request_to_bom_date = request_to_bom_date;
	}
	public Date getReceived_from_bom_date() {
		return received_from_bom_date;
	}
	public void setReceived_from_bom_date(Date received_from_bom_date) {
		this.received_from_bom_date = received_from_bom_date;
	}
	public BigDecimal getAmount_received() {
		return amount_received;
	}
	public void setAmount_received(BigDecimal amount_received) {
		this.amount_received = amount_received;
	}
	public Date getSettlement_date() {
		return settlement_date;
	}
	public void setSettlement_date(Date settlement_date) {
		this.settlement_date = settlement_date;
	}
	public BigDecimal getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}
	public BigDecimal getAcct_bal() {
		return acct_bal;
	}
	public void setAcct_bal(BigDecimal acct_bal) {
		this.acct_bal = acct_bal;
	}
	public BigDecimal getAmount_transferred() {
		return amount_transferred;
	}
	public void setAmount_transferred(BigDecimal amount_transferred) {
		this.amount_transferred = amount_transferred;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	


	}

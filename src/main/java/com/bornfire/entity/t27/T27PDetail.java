package com.bornfire.entity.t27;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T27P_TRAN_NRE_DET_TABLE")
public class T27PDetail {
	
	@Id
	private String	cust_id;
	private String	cust_name;
	private String	cntry_res;
	private String	cnty_incorp;
	private String	cntry_oper;
	private String	acct_num;
	private String	act_name;
	private String	risk_rate;
	private Date	risk_date;
	private String	inw_outw_tran;
	private BigDecimal	tran_amt;
	private String	tran_ref;
	private String	tran_remarks;
	private String	entity_flg;
	private String	del_flg;
	private String	modify_flg;
	private Date	entry_date;
	private Date	modify_date;
	private Date	verify_date;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private String	report_code;
	private String	report_name;
	private Date	report_date;
	private String	arch_flg;
	
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
	public String getCntry_res() {
		return cntry_res;
	}
	public void setCntry_res(String cntry_res) {
		this.cntry_res = cntry_res;
	}
	public String getCnty_incorp() {
		return cnty_incorp;
	}
	public void setCnty_incorp(String cnty_incorp) {
		this.cnty_incorp = cnty_incorp;
	}
	public String getCntry_oper() {
		return cntry_oper;
	}
	public void setCntry_oper(String cntry_oper) {
		this.cntry_oper = cntry_oper;
	}
	public String getAcct_num() {
		return acct_num;
	}
	public void setAcct_num(String acct_num) {
		this.acct_num = acct_num;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getRisk_rate() {
		return risk_rate;
	}
	public void setRisk_rate(String risk_rate) {
		this.risk_rate = risk_rate;
	}
	public Date getRisk_date() {
		return risk_date;
	}
	public void setRisk_date(Date risk_date) {
		this.risk_date = risk_date;
	}
	public String getInw_outw_tran() {
		return inw_outw_tran;
	}
	public void setInw_outw_tran(String inw_outw_tran) {
		this.inw_outw_tran = inw_outw_tran;
	}
	public BigDecimal getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getTran_ref() {
		return tran_ref;
	}
	public void setTran_ref(String tran_ref) {
		this.tran_ref = tran_ref;
	}
	public String getTran_remarks() {
		return tran_remarks;
	}
	public void setTran_remarks(String tran_remarks) {
		this.tran_remarks = tran_remarks;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
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
	public String getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(String arch_flg) {
		this.arch_flg = arch_flg;
	}
	public T27PDetail(String cust_id, String cust_name, String cntry_res, String cnty_incorp, String cntry_oper,
			String acct_num, String act_name, String risk_rate, Date risk_date, String inw_outw_tran,
			BigDecimal tran_amt, String tran_ref, String tran_remarks, String entity_flg, String del_flg,
			String modify_flg, Date entry_date, Date modify_date, Date verify_date, String entry_user,
			String modify_user, String verify_user, String report_code, String report_name, Date report_date,
			String arch_flg) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cntry_res = cntry_res;
		this.cnty_incorp = cnty_incorp;
		this.cntry_oper = cntry_oper;
		this.acct_num = acct_num;
		this.act_name = act_name;
		this.risk_rate = risk_rate;
		this.risk_date = risk_date;
		this.inw_outw_tran = inw_outw_tran;
		this.tran_amt = tran_amt;
		this.tran_ref = tran_ref;
		this.tran_remarks = tran_remarks;
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
	}
	public T27PDetail() {}
}

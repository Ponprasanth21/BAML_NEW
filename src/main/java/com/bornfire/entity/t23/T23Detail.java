package com.bornfire.entity.t23;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T23_AML_CFT_REVIEWS_DETAIL")
public class T23Detail {
	
	private String	primary_sol_id;
	private String	cust_id;
	private String	cust_name;
	private String	sol_id;
	private Date	cust_opn_date;
	private Date	cust_susp_date;
	private Date	cust_kyc_date;
	private Date	cust_kyc_due_date;
	private String	cust_rating_code;
	private Date	cust_rating_date;
	private Date	cust_rating_due_date;
	private String	gl_sub_head_code;
	private String	sch_code;
	
	@Id
	private String	foracid;
	private String	acid;
	private Date	acct_opn_date;
	private Character	acct_cls_flg;
	private Date	acct_cls_date;
	private String	acct_status;
	private Date	acct_status_date;
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
	public String getPrimary_sol_id() {
		return primary_sol_id;
	}
	public void setPrimary_sol_id(String primary_sol_id) {
		this.primary_sol_id = primary_sol_id;
	}
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
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public Date getCust_opn_date() {
		return cust_opn_date;
	}
	public void setCust_opn_date(Date cust_opn_date) {
		this.cust_opn_date = cust_opn_date;
	}
	public Date getCust_susp_date() {
		return cust_susp_date;
	}
	public void setCust_susp_date(Date cust_susp_date) {
		this.cust_susp_date = cust_susp_date;
	}
	public Date getCust_kyc_date() {
		return cust_kyc_date;
	}
	public void setCust_kyc_date(Date cust_kyc_date) {
		this.cust_kyc_date = cust_kyc_date;
	}
	public Date getCust_kyc_due_date() {
		return cust_kyc_due_date;
	}
	public void setCust_kyc_due_date(Date cust_kyc_due_date) {
		this.cust_kyc_due_date = cust_kyc_due_date;
	}
	public String getCust_rating_code() {
		return cust_rating_code;
	}
	public void setCust_rating_code(String cust_rating_code) {
		this.cust_rating_code = cust_rating_code;
	}
	public Date getCust_rating_date() {
		return cust_rating_date;
	}
	public void setCust_rating_date(Date cust_rating_date) {
		this.cust_rating_date = cust_rating_date;
	}
	public Date getCust_rating_due_date() {
		return cust_rating_due_date;
	}
	public void setCust_rating_due_date(Date cust_rating_due_date) {
		this.cust_rating_due_date = cust_rating_due_date;
	}
	public String getGl_sub_head_code() {
		return gl_sub_head_code;
	}
	public void setGl_sub_head_code(String gl_sub_head_code) {
		this.gl_sub_head_code = gl_sub_head_code;
	}
	public String getSch_code() {
		return sch_code;
	}
	public void setSch_code(String sch_code) {
		this.sch_code = sch_code;
	}
	public String getForacid() {
		return foracid;
	}
	public void setForacid(String foracid) {
		this.foracid = foracid;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public Date getAcct_opn_date() {
		return acct_opn_date;
	}
	public void setAcct_opn_date(Date acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
	}
	public Character getAcct_cls_flg() {
		return acct_cls_flg;
	}
	public void setAcct_cls_flg(Character acct_cls_flg) {
		this.acct_cls_flg = acct_cls_flg;
	}
	public Date getAcct_cls_date() {
		return acct_cls_date;
	}
	public void setAcct_cls_date(Date acct_cls_date) {
		this.acct_cls_date = acct_cls_date;
	}
	public String getAcct_status() {
		return acct_status;
	}
	public void setAcct_status(String acct_status) {
		this.acct_status = acct_status;
	}
	public Date getAcct_status_date() {
		return acct_status_date;
	}
	public void setAcct_status_date(Date acct_status_date) {
		this.acct_status_date = acct_status_date;
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
	public T23Detail(String primary_sol_id, String cust_id, String cust_name, String sol_id, Date cust_opn_date,
			Date cust_susp_date, Date cust_kyc_date, Date cust_kyc_due_date, String cust_rating_code,
			Date cust_rating_date, Date cust_rating_due_date, String gl_sub_head_code, String sch_code, String foracid,
			String acid, Date acct_opn_date, Character acct_cls_flg, Date acct_cls_date, String acct_status,
			Date acct_status_date, Character entity_flg, Character del_flg, Character modify_flg, Date entry_date,
			Date modify_date, Date verify_date, String entry_user, String modify_user, String verify_user,
			String report_code, String report_name, Date report_date, Character arch_flg) {
		this.primary_sol_id = primary_sol_id;
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.sol_id = sol_id;
		this.cust_opn_date = cust_opn_date;
		this.cust_susp_date = cust_susp_date;
		this.cust_kyc_date = cust_kyc_date;
		this.cust_kyc_due_date = cust_kyc_due_date;
		this.cust_rating_code = cust_rating_code;
		this.cust_rating_date = cust_rating_date;
		this.cust_rating_due_date = cust_rating_due_date;
		this.gl_sub_head_code = gl_sub_head_code;
		this.sch_code = sch_code;
		this.foracid = foracid;
		this.acid = acid;
		this.acct_opn_date = acct_opn_date;
		this.acct_cls_flg = acct_cls_flg;
		this.acct_cls_date = acct_cls_date;
		this.acct_status = acct_status;
		this.acct_status_date = acct_status_date;
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
	
	public T23Detail() {}
	
	


}

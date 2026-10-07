package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BAML_TRAN_ALERTS_MASTER")
public class BAMLTranAlertsMaster {
	

	@Id
	private String	tran_id;
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date	tran_date;
	private String	part_tran_srl_num;
	private BigDecimal	aml_tran_ref_no;
	private String	sol_id;
	
	
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date	value_date;
	
	private String	tran_type;
	private String	tran_sub_type;
	private String	part_tran_type;
	
	private String	tran_crncy_code;
	private BigDecimal	tran_amt;
	private String	tran_particulars;
	private String	tran_remarks;
	private String	tran_code;
	private String	tran_particular_code;
	private String	acid;
	private String	foracid;
	private String	acct_name;
	private String	cust_id;
	private String	schm_type;
	private String	schm_code;
	private String	gl_sub_head_code;
	private String	entry_user_id;
	private String	pstd_user_id;
	private String	vfd_user_id;
	
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date	entry_date;
	
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date	pstd_date;
	
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date	vfd_date;
	
	private String	pstd_flg;
	private String	del_flg;
	private String	rule_code;
	
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date	aert_date;
	
	private String	alert_code;
	private String	alert_remarks;
	private String	user_observations;
	private String	str_ref;
	private String	case_ref;
	private String	free_field_1;
	private String	free_field_2;
	private String	free_field_3;
	private String	entity_flag;
	private String  alert_desc;
	private BigDecimal celling_amount;
	@DateTimeFormat(pattern =  "dd/MM/yyyy")
	private Date alert_from_date;
	

	
	
	public BigDecimal getCelling_amount() {
		return celling_amount;
	}



	public void setCelling_amount(BigDecimal celling_amount) {
		this.celling_amount = celling_amount;
	}



	public Date getAlert_from_date() {
		return alert_from_date;
	}



	public void setAlert_from_date(Date alert_from_date) {
		this.alert_from_date = alert_from_date;
	}



	public String getAlert_desc() {
		return alert_desc;
	}



	public void setAlert_desc(String alert_desc) {
		this.alert_desc = alert_desc;
	}



	public BAMLTranAlertsMaster() {}


	public BigDecimal getAml_tran_ref_no() {
		return aml_tran_ref_no;
	}



	public void setAml_tran_ref_no(BigDecimal aml_tran_ref_no) {
		this.aml_tran_ref_no = aml_tran_ref_no;
	}



	public String getSol_id() {
		return sol_id;
	}



	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}



	public Date getValue_date() {
		return value_date;
	}



	public void setValue_date(Date value_date) {
		this.value_date = value_date;
	}



	public String getTran_type() {
		return tran_type;
	}



	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}



	public String getTran_sub_type() {
		return tran_sub_type;
	}



	public void setTran_sub_type(String tran_sub_type) {
		this.tran_sub_type = tran_sub_type;
	}



	public String getPart_tran_type() {
		return part_tran_type;
	}



	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}



	public String getTran_crncy_code() {
		return tran_crncy_code;
	}



	public void setTran_crncy_code(String tran_crncy_code) {
		this.tran_crncy_code = tran_crncy_code;
	}



	public BigDecimal getTran_amt() {
		return tran_amt;
	}



	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}



	public String getTran_particulars() {
		return tran_particulars;
	}



	public void setTran_particulars(String tran_particulars) {
		this.tran_particulars = tran_particulars;
	}



	public String getTran_remarks() {
		return tran_remarks;
	}



	public void setTran_remarks(String tran_remarks) {
		this.tran_remarks = tran_remarks;
	}



	public String getTran_code() {
		return tran_code;
	}



	public void setTran_code(String tran_code) {
		this.tran_code = tran_code;
	}



	public String getTran_particular_code() {
		return tran_particular_code;
	}



	public void setTran_particular_code(String tran_particular_code) {
		this.tran_particular_code = tran_particular_code;
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



	public String getAcct_name() {
		return acct_name;
	}



	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}



	public String getCust_id() {
		return cust_id;
	}



	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
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



	public String getGl_sub_head_code() {
		return gl_sub_head_code;
	}



	public void setGl_sub_head_code(String gl_sub_head_code) {
		this.gl_sub_head_code = gl_sub_head_code;
	}



	public String getEntry_user_id() {
		return entry_user_id;
	}



	public void setEntry_user_id(String entry_user_id) {
		this.entry_user_id = entry_user_id;
	}



	public String getPstd_user_id() {
		return pstd_user_id;
	}



	public void setPstd_user_id(String pstd_user_id) {
		this.pstd_user_id = pstd_user_id;
	}



	public String getVfd_user_id() {
		return vfd_user_id;
	}



	public void setVfd_user_id(String vfd_user_id) {
		this.vfd_user_id = vfd_user_id;
	}



	public Date getEntry_date() {
		return entry_date;
	}



	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}



	public Date getPstd_date() {
		return pstd_date;
	}



	public void setPstd_date(Date pstd_date) {
		this.pstd_date = pstd_date;
	}



	public Date getVfd_date() {
		return vfd_date;
	}



	public void setVfd_date(Date vfd_date) {
		this.vfd_date = vfd_date;
	}



	public String getPstd_flg() {
		return pstd_flg;
	}



	public void setPstd_flg(String pstd_flg) {
		this.pstd_flg = pstd_flg;
	}



	public String getDel_flg() {
		return del_flg;
	}



	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}



	public String getRule_code() {
		return rule_code;
	}



	public void setRule_code(String rule_code) {
		this.rule_code = rule_code;
	}



	public Date getAert_date() {
		return aert_date;
	}



	public void setAert_date(Date aert_date) {
		this.aert_date = aert_date;
	}



	public String getAlert_code() {
		return alert_code;
	}



	public void setAlert_code(String alert_code) {
		this.alert_code = alert_code;
	}



	public String getAlert_remarks() {
		return alert_remarks;
	}



	public void setAlert_remarks(String alert_remarks) {
		this.alert_remarks = alert_remarks;
	}



	public String getUser_observations() {
		return user_observations;
	}



	public void setUser_observations(String user_observations) {
		this.user_observations = user_observations;
	}



	public String getStr_ref() {
		return str_ref;
	}



	public void setStr_ref(String str_ref) {
		this.str_ref = str_ref;
	}



	public String getCase_ref() {
		return case_ref;
	}



	public void setCase_ref(String case_ref) {
		this.case_ref = case_ref;
	}



	public String getFree_field_1() {
		return free_field_1;
	}



	public void setFree_field_1(String free_field_1) {
		this.free_field_1 = free_field_1;
	}



	public String getFree_field_2() {
		return free_field_2;
	}



	public void setFree_field_2(String free_field_2) {
		this.free_field_2 = free_field_2;
	}



	public String getFree_field_3() {
		return free_field_3;
	}



	public void setFree_field_3(String free_field_3) {
		this.free_field_3 = free_field_3;
	}



	public String getEntity_flag() {
		return entity_flag;
	}



	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}



	public String getTran_id() {
		return tran_id;
	}



	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}



	public Date getTran_date() {
		return tran_date;
	}



	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}



	public String getPart_tran_srl_num() {
		return part_tran_srl_num;
	}



	public void setPart_tran_srl_num(String part_tran_srl_num) {
		this.part_tran_srl_num = part_tran_srl_num;
	}



	public BAMLTranAlertsMaster(String tran_id, Date tran_date, String part_tran_srl_num, BigDecimal aml_tran_ref_no,
			String sol_id, Date value_date, String tran_type, String tran_sub_type, String part_tran_type,
			String tran_crncy_code, BigDecimal tran_amt, String tran_particulars, String tran_remarks, String tran_code,
			String tran_particular_code, String acid, String foracid, String acct_name, String cust_id,
			String schm_type, String schm_code, String gl_sub_head_code, String entry_user_id, String pstd_user_id,
			String vfd_user_id, Date entry_date, Date pstd_date, Date vfd_date, String pstd_flg, String del_flg,
			String rule_code, Date aert_date, String alert_code, String alert_remarks, String user_observations,
			String str_ref, String case_ref, String free_field_1, String free_field_2, String free_field_3,
			String entity_flag, String alert_desc, BigDecimal celling_amount, Date alert_from_date) {
		super();
		this.tran_id = tran_id;
		this.tran_date = tran_date;
		this.part_tran_srl_num = part_tran_srl_num;
		this.aml_tran_ref_no = aml_tran_ref_no;
		this.sol_id = sol_id;
		this.value_date = value_date;
		this.tran_type = tran_type;
		this.tran_sub_type = tran_sub_type;
		this.part_tran_type = part_tran_type;
		this.tran_crncy_code = tran_crncy_code;
		this.tran_amt = tran_amt;
		this.tran_particulars = tran_particulars;
		this.tran_remarks = tran_remarks;
		this.tran_code = tran_code;
		this.tran_particular_code = tran_particular_code;
		this.acid = acid;
		this.foracid = foracid;
		this.acct_name = acct_name;
		this.cust_id = cust_id;
		this.schm_type = schm_type;
		this.schm_code = schm_code;
		this.gl_sub_head_code = gl_sub_head_code;
		this.entry_user_id = entry_user_id;
		this.pstd_user_id = pstd_user_id;
		this.vfd_user_id = vfd_user_id;
		this.entry_date = entry_date;
		this.pstd_date = pstd_date;
		this.vfd_date = vfd_date;
		this.pstd_flg = pstd_flg;
		this.del_flg = del_flg;
		this.rule_code = rule_code;
		this.aert_date = aert_date;
		this.alert_code = alert_code;
		this.alert_remarks = alert_remarks;
		this.user_observations = user_observations;
		this.str_ref = str_ref;
		this.case_ref = case_ref;
		this.free_field_1 = free_field_1;
		this.free_field_2 = free_field_2;
		this.free_field_3 = free_field_3;
		this.entity_flag = entity_flag;
		this.alert_desc = alert_desc;
		this.celling_amount = celling_amount;
		this.alert_from_date = alert_from_date;
	}



	
}

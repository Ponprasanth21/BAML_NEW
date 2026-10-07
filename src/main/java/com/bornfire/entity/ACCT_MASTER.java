package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BAML_ACCT_MAST_TABLE")
public class ACCT_MASTER implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String	sol_id;
	private String	schm_type;
	private String	schm_code;
	private String	gl_sub_head_code;
	private String	acct_ownership;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	acct_opn_date;
	private String	cust_id;
	private String	cif_id;
	@Id
	private String	foracid;
	private String	acid;
	private String	acct_name;
	private String	acct_short_name;
	private String	acct_crncy_code;
	private BigDecimal	sanct_lim;
	private String	clr_bal_amt;
	private String	acct_cls_flg;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	acct_cls_date;
	private String	frez_code;
	private String	frez_reason_code;
	private BigDecimal	bal_on_frez_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	last_frez_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	last_unfrez_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	last_tran_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	last_any_tran_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	lchg_time;
	private String	acct_locn_code;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	rcre_time;
	private String	acct_status;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	acct_status_date;
	private String	v_risk_rate;
	private String	v_nre_rating;
	private String mode_of_oper_code;
	private String purpose_of_advn;
	private String acct_occp_code;
	private	String source_of_fund;
	private Character del_flg;
	private Character entity_cre_flg;
	private String nom_name1;
	private String nom_name2;
	private String	tam_deposit_status;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	tam_open_effective_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	tam_maturity_date;
	private BigDecimal	tam_maturity_amount;
	private BigDecimal	tam_auto_renewed_counter;
	private String	lam_acct_status_flg;
	private BigDecimal	ldt_emi_amt;
	private String free_text_8;
	private String free_text_10;
	private String free_text_12;
	private String type_of_advn;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date relationshipopendate;
	private BigDecimal lam_rep_perd_mths;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date lam_maturity_date;
	
	

	
	
	
	
	
	
	
	
	
	public BigDecimal getLam_rep_perd_mths() {
		return lam_rep_perd_mths;
	}
	public void setLam_rep_perd_mths(BigDecimal lam_rep_perd_mths) {
		this.lam_rep_perd_mths = lam_rep_perd_mths;
	}
	public Date getLam_maturity_date() {
		return lam_maturity_date;
	}
	public void setLam_maturity_date(Date lam_maturity_date) {
		this.lam_maturity_date = lam_maturity_date;
	}
	public String getType_of_advn() {
		return type_of_advn;
	}
	public void setType_of_advn(String type_of_advn) {
		this.type_of_advn = type_of_advn;
	}
	public Date getRelationshipopendate() {
		return relationshipopendate;
	}
	public void setRelationshipopendate(Date relationshipopendate) {
		this.relationshipopendate = relationshipopendate;
	}
	public String getFree_text_8() {
		return free_text_8;
	}
	public void setFree_text_8(String free_text_8) {
		this.free_text_8 = free_text_8;
	}
	public String getFree_text_10() {
		return free_text_10;
	}
	public void setFree_text_10(String free_text_10) {
		this.free_text_10 = free_text_10;
	}
	public String getFree_text_12() {
		return free_text_12;
	}
	public void setFree_text_12(String free_text_12) {
		this.free_text_12 = free_text_12;
	}
	public String getTam_deposit_status() {
		return tam_deposit_status;
	}
	public void setTam_deposit_status(String tam_deposit_status) {
		this.tam_deposit_status = tam_deposit_status;
	}
	public Date getTam_open_effective_date() {
		return tam_open_effective_date;
	}
	public void setTam_open_effective_date(Date tam_open_effective_date) {
		this.tam_open_effective_date = tam_open_effective_date;
	}
	public Date getTam_maturity_date() {
		return tam_maturity_date;
	}
	public void setTam_maturity_date(Date tam_maturity_date) {
		this.tam_maturity_date = tam_maturity_date;
	}
	public BigDecimal getTam_maturity_amount() {
		return tam_maturity_amount;
	}
	public void setTam_maturity_amount(BigDecimal tam_maturity_amount) {
		this.tam_maturity_amount = tam_maturity_amount;
	}
	public BigDecimal getTam_auto_renewed_counter() {
		return tam_auto_renewed_counter;
	}
	public void setTam_auto_renewed_counter(BigDecimal tam_auto_renewed_counter) {
		this.tam_auto_renewed_counter = tam_auto_renewed_counter;
	}
	public String getLam_acct_status_flg() {
		return lam_acct_status_flg;
	}
	public void setLam_acct_status_flg(String lam_acct_status_flg) {
		this.lam_acct_status_flg = lam_acct_status_flg;
	}
	public BigDecimal getLdt_emi_amt() {
		return ldt_emi_amt;
	}
	public void setLdt_emi_amt(BigDecimal ldt_emi_amt) {
		this.ldt_emi_amt = ldt_emi_amt;
	}
	public Character getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}
	public Character getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(Character entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public String getNom_name1() {
		return nom_name1;
	}
	public void setNom_name1(String nom_name1) {
		this.nom_name1 = nom_name1;
	}
	public String getNom_name2() {
		return nom_name2;
	}
	public void setNom_name2(String nom_name2) {
		this.nom_name2 = nom_name2;
	}
	public String getSource_of_fund() {
		return source_of_fund;
	}
	public void setSource_of_fund(String source_of_fund) {
		this.source_of_fund = source_of_fund;
	}
	public String getPurpose_of_advn() {
		return purpose_of_advn;
	}
	public void setPurpose_of_advn(String purpose_of_advn) {
		this.purpose_of_advn = purpose_of_advn;
	}
	public String getAcct_occp_code() {
		return acct_occp_code;
	}
	public void setAcct_occp_code(String acct_occp_code) {
		this.acct_occp_code = acct_occp_code;
	}
	public String getMode_of_oper_code() {
		return mode_of_oper_code;
	}
	public void setMode_of_oper_code(String mode_of_oper_code) {
		this.mode_of_oper_code = mode_of_oper_code;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
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
	public String getAcct_ownership() {
		return acct_ownership;
	}
	public void setAcct_ownership(String acct_ownership) {
		this.acct_ownership = acct_ownership;
	}
	public Date getAcct_opn_date() {
		return acct_opn_date;
	}
	public void setAcct_opn_date(Date acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCif_id() {
		return cif_id;
	}
	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
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
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public String getAcct_short_name() {
		return acct_short_name;
	}
	public void setAcct_short_name(String acct_short_name) {
		this.acct_short_name = acct_short_name;
	}
	public String getAcct_crncy_code() {
		return acct_crncy_code;
	}
	public void setAcct_crncy_code(String acct_crncy_code) {
		this.acct_crncy_code = acct_crncy_code;
	}
	public String getClr_bal_amt() {
		return clr_bal_amt;
	}
	public void setClr_bal_amt(String clr_bal_amt) {
		this.clr_bal_amt = clr_bal_amt;
	}
	public String getAcct_cls_flg() {
		return acct_cls_flg;
	}
	public void setAcct_cls_flg(String acct_cls_flg) {
		this.acct_cls_flg = acct_cls_flg;
	}
	public Date getAcct_cls_date() {
		return acct_cls_date;
	}
	public void setAcct_cls_date(Date acct_cls_date) {
		this.acct_cls_date = acct_cls_date;
	}
	public String getFrez_code() {
		return frez_code;
	}
	public void setFrez_code(String frez_code) {
		this.frez_code = frez_code;
	}
	public String getFrez_reason_code() {
		return frez_reason_code;
	}
	public void setFrez_reason_code(String frez_reason_code) {
		this.frez_reason_code = frez_reason_code;
	}
	public BigDecimal getBal_on_frez_date() {
		return bal_on_frez_date;
	}
	public void setBal_on_frez_date(BigDecimal bal_on_frez_date) {
		this.bal_on_frez_date = bal_on_frez_date;
	}
	public Date getLast_frez_date() {
		return last_frez_date;
	}
	public void setLast_frez_date(Date last_frez_date) {
		this.last_frez_date = last_frez_date;
	}
	public Date getLast_unfrez_date() {
		return last_unfrez_date;
	}
	public void setLast_unfrez_date(Date last_unfrez_date) {
		this.last_unfrez_date = last_unfrez_date;
	}
	public Date getLast_tran_date() {
		return last_tran_date;
	}
	public void setLast_tran_date(Date last_tran_date) {
		this.last_tran_date = last_tran_date;
	}
	public Date getLast_any_tran_date() {
		return last_any_tran_date;
	}
	public void setLast_any_tran_date(Date last_any_tran_date) {
		this.last_any_tran_date = last_any_tran_date;
	}
	public Date getLchg_time() {
		return lchg_time;
	}
	public void setLchg_time(Date lchg_time) {
		this.lchg_time = lchg_time;
	}
	public String getAcct_locn_code() {
		return acct_locn_code;
	}
	public void setAcct_locn_code(String acct_locn_code) {
		this.acct_locn_code = acct_locn_code;
	}
	public Date getRcre_time() {
		return rcre_time;
	}
	public void setRcre_time(Date rcre_time) {
		this.rcre_time = rcre_time;
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
	public BigDecimal getSanct_lim() {
		return sanct_lim;
	}
	public void setSanct_lim(BigDecimal sanct_lim) {
		this.sanct_lim = sanct_lim;
	}
	public String getV_risk_rate() {
		return v_risk_rate;
	}
	public void setV_risk_rate(String v_risk_rate) {
		this.v_risk_rate = v_risk_rate;
	}
	public String getV_nre_rating() {
		return v_nre_rating;
	}
	public void setV_nre_rating(String v_nre_rating) {
		this.v_nre_rating = v_nre_rating;
	}
	public ACCT_MASTER() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ACCT_MASTER(String sol_id, String schm_type, String schm_code, String gl_sub_head_code,
			String acct_ownership, Date acct_opn_date, String cust_id, String cif_id, String foracid, String acid,
			String acct_name, String acct_short_name, String acct_crncy_code, BigDecimal sanct_lim, String clr_bal_amt,
			String acct_cls_flg, Date acct_cls_date, String frez_code, String frez_reason_code,
			BigDecimal bal_on_frez_date, Date last_frez_date, Date last_unfrez_date, Date last_tran_date,
			Date last_any_tran_date, Date lchg_time, String acct_locn_code, Date rcre_time, String acct_status,
			Date acct_status_date, String v_risk_rate, String v_nre_rating, String mode_of_oper_code,
			String purpose_of_advn, String acct_occp_code, String source_of_fund, Character del_flg,
			Character entity_cre_flg, String nom_name1, String nom_name2, String tam_deposit_status,
			Date tam_open_effective_date, Date tam_maturity_date, BigDecimal tam_maturity_amount,
			BigDecimal tam_auto_renewed_counter, String lam_acct_status_flg, BigDecimal ldt_emi_amt, String free_text_8,
			String free_text_10, String free_text_12, String type_of_advn, Date relationshipopendate,
			BigDecimal lam_rep_perd_mths, Date lam_maturity_date) {
		super();
		this.sol_id = sol_id;
		this.schm_type = schm_type;
		this.schm_code = schm_code;
		this.gl_sub_head_code = gl_sub_head_code;
		this.acct_ownership = acct_ownership;
		this.acct_opn_date = acct_opn_date;
		this.cust_id = cust_id;
		this.cif_id = cif_id;
		this.foracid = foracid;
		this.acid = acid;
		this.acct_name = acct_name;
		this.acct_short_name = acct_short_name;
		this.acct_crncy_code = acct_crncy_code;
		this.sanct_lim = sanct_lim;
		this.clr_bal_amt = clr_bal_amt;
		this.acct_cls_flg = acct_cls_flg;
		this.acct_cls_date = acct_cls_date;
		this.frez_code = frez_code;
		this.frez_reason_code = frez_reason_code;
		this.bal_on_frez_date = bal_on_frez_date;
		this.last_frez_date = last_frez_date;
		this.last_unfrez_date = last_unfrez_date;
		this.last_tran_date = last_tran_date;
		this.last_any_tran_date = last_any_tran_date;
		this.lchg_time = lchg_time;
		this.acct_locn_code = acct_locn_code;
		this.rcre_time = rcre_time;
		this.acct_status = acct_status;
		this.acct_status_date = acct_status_date;
		this.v_risk_rate = v_risk_rate;
		this.v_nre_rating = v_nre_rating;
		this.mode_of_oper_code = mode_of_oper_code;
		this.purpose_of_advn = purpose_of_advn;
		this.acct_occp_code = acct_occp_code;
		this.source_of_fund = source_of_fund;
		this.del_flg = del_flg;
		this.entity_cre_flg = entity_cre_flg;
		this.nom_name1 = nom_name1;
		this.nom_name2 = nom_name2;
		this.tam_deposit_status = tam_deposit_status;
		this.tam_open_effective_date = tam_open_effective_date;
		this.tam_maturity_date = tam_maturity_date;
		this.tam_maturity_amount = tam_maturity_amount;
		this.tam_auto_renewed_counter = tam_auto_renewed_counter;
		this.lam_acct_status_flg = lam_acct_status_flg;
		this.ldt_emi_amt = ldt_emi_amt;
		this.free_text_8 = free_text_8;
		this.free_text_10 = free_text_10;
		this.free_text_12 = free_text_12;
		this.type_of_advn = type_of_advn;
		this.relationshipopendate = relationshipopendate;
		this.lam_rep_perd_mths = lam_rep_perd_mths;
		this.lam_maturity_date = lam_maturity_date;
	}
	
			
}

package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="GAM")
public class AccountsInquiryEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	acid;
	private String	entity_cre_flg;
	private String	del_flg;
	private String	sol_id;
	private String	acct_prefix;
	private String	acct_num;
	private String	bacid;
	@Id
	private String	foracid;
	private String	acct_name;
	private String	acct_short_name;
	private String	cust_id;
	private String	emp_id;
	private String	gl_sub_head_code;
	private String	acct_ownership;
	private String	schm_code;
	private BigDecimal	dr_bal_lim;
	private String	acct_rpt_code;
	private String	frez_code;
	private String	frez_reason_code;
	private Date	acct_opn_date;
	private String	acct_cls_flg;
	private Date	acct_cls_date;
	private BigDecimal	clr_bal_amt;
	private BigDecimal	tot_mod_times;
	private BigDecimal	ledg_num;
	private BigDecimal	un_clr_bal_amt;
	private BigDecimal	drwng_power;
	private BigDecimal	sanct_lim;
	private BigDecimal	adhoc_lim;
	private BigDecimal	emer_advn;
	private BigDecimal	dacc_lim;
	private BigDecimal	system_reserved_amt;
	private BigDecimal	single_tran_lim;
	private BigDecimal	clean_adhoc_lim;
	private BigDecimal	clean_emer_advn;
	private BigDecimal	clean_single_tran_lim;
	private BigDecimal	system_gen_lim;
	private String	chq_alwd_flg;
	private BigDecimal	cash_excp_amt_lim;
	private BigDecimal	clg_excp_amt_lim;
	private BigDecimal	xfer_excp_amt_lim;
	private BigDecimal	cash_cr_excp_amt_lim;
	private BigDecimal	clg_cr_excp_amt_lim;
	private BigDecimal	xfer_cr_excp_amt_lim;
	private BigDecimal	cash_abnrml_amt_lim;
	private BigDecimal	clg_abnrml_amt_lim;
	private BigDecimal	xfer_abnrml_amt_lim;
	private BigDecimal	cum_dr_amt;
	private BigDecimal	cum_cr_amt;
	private BigDecimal	acrd_cr_amt;
	private Date	last_tran_date;
	private String	mode_of_oper_code;
	private String	pb_ps_code;
	private String	serv_chrg_coll_flg;
	private String	free_text;
	private String	acct_turnover_det_flg;
	private String	nom_available_flg;
	private String	acct_locn_code;
	private Date	last_purge_date;
	private BigDecimal	bal_on_purge_date;
	private String	int_paid_flg;
	private String	int_coll_flg;
	private Date	last_any_tran_date;
	private String	hashed_no;
	private String	lchg_user_id;
	private Date	lchg_time;
	private String	rcre_user_id;
	private Date	rcre_time;
	private String	limit_b2kid;
	private String	drwng_power_ind;
	private BigDecimal	drwng_power_pcnt;
	private String	micr_chq_chrg_coll_flg;
	private Date	last_turnover_date;
	private BigDecimal	notional_rate;
	private String	notional_rate_code;
	private BigDecimal	fx_clr_bal_amt;
	private BigDecimal	fx_bal_on_purge_date;
	private String	fd_ref_num;
	private BigDecimal	fx_cum_cr_amt;
	private BigDecimal	fx_cum_dr_amt;
	private String	crncy_code;
	private String	source_of_fund;
	private String	anw_non_cust_alwd_flg;
	private String	acct_crncy_code;
	private BigDecimal	lien_amt;
	private String	acct_classification_flg;
	private String	system_only_acct_flg;
	private String	single_tran_flg;
	private BigDecimal	utilised_amt;
	private String	inter_sol_access_flg;
	private String	purge_allowed_flg;
	private String	purge_text;
	private Date	min_value_date;
	private String	acct_mgr_user_id;
	private String	schm_type;
	private Date	last_frez_date;
	private Date	last_unfrez_date;
	private BigDecimal	bal_on_frez_date;
	private String	swift_allowed_flg;
	private BigDecimal	dacc_lim_pcnt;
	private BigDecimal	dacc_lim_abs;
	private String	chrg_level_code;
	private String	acct_cls_chrg_pend_verf;
	private String	partitioned_flg;
	private String	partitioned_type;
	private String	pbf_download_flg;
	private Date	pbf_delink_date;
	private String	wtax_flg;
	private String	wtax_amount_scope_flg;
	private String	int_adj_for_deduction_flg;
	private String	operative_acid;
	private String	phone_num;
	private String	native_lang_name;
	private String	nat_lang_title_code;
	private String	lang_code;
	private BigDecimal	ts_cnt;
	private String	pool_id;
	private String	allow_sweeps;
	private BigDecimal	order_of_utilisation;
	private BigDecimal	wtax_pcnt;
	private BigDecimal	wtax_floor_limit;
	private String	dsa_penal_flg;
	private String	product_group;
	private String	source_deal_code;
	private String	disburse_deal_code;
	private BigDecimal	sweep_in_min_bal;
	private BigDecimal	used_single_tran_lim;
	private BigDecimal	used_clean_single_tran_lim;
	private BigDecimal	used_un_clr_over_dacc_amt;
	private BigDecimal	ffd_contrib_to_acct;
	private String	acct_creation_mode;
	private String	wtax_level_flg;
	private Date	last_modified_date;
	private BigDecimal	future_bal_amt;
	private BigDecimal	util_future_bal_amt;
	private BigDecimal	dafa_lim;
	private BigDecimal	dafa_lim_abs;
	private BigDecimal	dafa_lim_pcnt;
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(String entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getAcct_prefix() {
		return acct_prefix;
	}
	public void setAcct_prefix(String acct_prefix) {
		this.acct_prefix = acct_prefix;
	}
	public String getAcct_num() {
		return acct_num;
	}
	public void setAcct_num(String acct_num) {
		this.acct_num = acct_num;
	}
	public String getBacid() {
		return bacid;
	}
	public void setBacid(String bacid) {
		this.bacid = bacid;
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
	public String getAcct_short_name() {
		return acct_short_name;
	}
	public void setAcct_short_name(String acct_short_name) {
		this.acct_short_name = acct_short_name;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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
	public String getSchm_code() {
		return schm_code;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	public BigDecimal getDr_bal_lim() {
		return dr_bal_lim;
	}
	public void setDr_bal_lim(BigDecimal dr_bal_lim) {
		this.dr_bal_lim = dr_bal_lim;
	}
	public String getAcct_rpt_code() {
		return acct_rpt_code;
	}
	public void setAcct_rpt_code(String acct_rpt_code) {
		this.acct_rpt_code = acct_rpt_code;
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
	public Date getAcct_opn_date() {
		return acct_opn_date;
	}
	public void setAcct_opn_date(Date acct_opn_date) {
		this.acct_opn_date = acct_opn_date;
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
	public BigDecimal getClr_bal_amt() {
		return clr_bal_amt;
	}
	public void setClr_bal_amt(BigDecimal clr_bal_amt) {
		this.clr_bal_amt = clr_bal_amt;
	}
	public BigDecimal getTot_mod_times() {
		return tot_mod_times;
	}
	public void setTot_mod_times(BigDecimal tot_mod_times) {
		this.tot_mod_times = tot_mod_times;
	}
	public BigDecimal getLedg_num() {
		return ledg_num;
	}
	public void setLedg_num(BigDecimal ledg_num) {
		this.ledg_num = ledg_num;
	}
	public BigDecimal getUn_clr_bal_amt() {
		return un_clr_bal_amt;
	}
	public void setUn_clr_bal_amt(BigDecimal un_clr_bal_amt) {
		this.un_clr_bal_amt = un_clr_bal_amt;
	}
	public BigDecimal getDrwng_power() {
		return drwng_power;
	}
	public void setDrwng_power(BigDecimal drwng_power) {
		this.drwng_power = drwng_power;
	}
	public BigDecimal getSanct_lim() {
		return sanct_lim;
	}
	public void setSanct_lim(BigDecimal sanct_lim) {
		this.sanct_lim = sanct_lim;
	}
	public BigDecimal getAdhoc_lim() {
		return adhoc_lim;
	}
	public void setAdhoc_lim(BigDecimal adhoc_lim) {
		this.adhoc_lim = adhoc_lim;
	}
	public BigDecimal getEmer_advn() {
		return emer_advn;
	}
	public void setEmer_advn(BigDecimal emer_advn) {
		this.emer_advn = emer_advn;
	}
	public BigDecimal getDacc_lim() {
		return dacc_lim;
	}
	public void setDacc_lim(BigDecimal dacc_lim) {
		this.dacc_lim = dacc_lim;
	}
	public BigDecimal getSystem_reserved_amt() {
		return system_reserved_amt;
	}
	public void setSystem_reserved_amt(BigDecimal system_reserved_amt) {
		this.system_reserved_amt = system_reserved_amt;
	}
	public BigDecimal getSingle_tran_lim() {
		return single_tran_lim;
	}
	public void setSingle_tran_lim(BigDecimal single_tran_lim) {
		this.single_tran_lim = single_tran_lim;
	}
	public BigDecimal getClean_adhoc_lim() {
		return clean_adhoc_lim;
	}
	public void setClean_adhoc_lim(BigDecimal clean_adhoc_lim) {
		this.clean_adhoc_lim = clean_adhoc_lim;
	}
	public BigDecimal getClean_emer_advn() {
		return clean_emer_advn;
	}
	public void setClean_emer_advn(BigDecimal clean_emer_advn) {
		this.clean_emer_advn = clean_emer_advn;
	}
	public BigDecimal getClean_single_tran_lim() {
		return clean_single_tran_lim;
	}
	public void setClean_single_tran_lim(BigDecimal clean_single_tran_lim) {
		this.clean_single_tran_lim = clean_single_tran_lim;
	}
	public BigDecimal getSystem_gen_lim() {
		return system_gen_lim;
	}
	public void setSystem_gen_lim(BigDecimal system_gen_lim) {
		this.system_gen_lim = system_gen_lim;
	}
	public String getChq_alwd_flg() {
		return chq_alwd_flg;
	}
	public void setChq_alwd_flg(String chq_alwd_flg) {
		this.chq_alwd_flg = chq_alwd_flg;
	}
	public BigDecimal getCash_excp_amt_lim() {
		return cash_excp_amt_lim;
	}
	public void setCash_excp_amt_lim(BigDecimal cash_excp_amt_lim) {
		this.cash_excp_amt_lim = cash_excp_amt_lim;
	}
	public BigDecimal getClg_excp_amt_lim() {
		return clg_excp_amt_lim;
	}
	public void setClg_excp_amt_lim(BigDecimal clg_excp_amt_lim) {
		this.clg_excp_amt_lim = clg_excp_amt_lim;
	}
	public BigDecimal getXfer_excp_amt_lim() {
		return xfer_excp_amt_lim;
	}
	public void setXfer_excp_amt_lim(BigDecimal xfer_excp_amt_lim) {
		this.xfer_excp_amt_lim = xfer_excp_amt_lim;
	}
	public BigDecimal getCash_cr_excp_amt_lim() {
		return cash_cr_excp_amt_lim;
	}
	public void setCash_cr_excp_amt_lim(BigDecimal cash_cr_excp_amt_lim) {
		this.cash_cr_excp_amt_lim = cash_cr_excp_amt_lim;
	}
	public BigDecimal getClg_cr_excp_amt_lim() {
		return clg_cr_excp_amt_lim;
	}
	public void setClg_cr_excp_amt_lim(BigDecimal clg_cr_excp_amt_lim) {
		this.clg_cr_excp_amt_lim = clg_cr_excp_amt_lim;
	}
	public BigDecimal getXfer_cr_excp_amt_lim() {
		return xfer_cr_excp_amt_lim;
	}
	public void setXfer_cr_excp_amt_lim(BigDecimal xfer_cr_excp_amt_lim) {
		this.xfer_cr_excp_amt_lim = xfer_cr_excp_amt_lim;
	}
	public BigDecimal getCash_abnrml_amt_lim() {
		return cash_abnrml_amt_lim;
	}
	public void setCash_abnrml_amt_lim(BigDecimal cash_abnrml_amt_lim) {
		this.cash_abnrml_amt_lim = cash_abnrml_amt_lim;
	}
	public BigDecimal getClg_abnrml_amt_lim() {
		return clg_abnrml_amt_lim;
	}
	public void setClg_abnrml_amt_lim(BigDecimal clg_abnrml_amt_lim) {
		this.clg_abnrml_amt_lim = clg_abnrml_amt_lim;
	}
	public BigDecimal getXfer_abnrml_amt_lim() {
		return xfer_abnrml_amt_lim;
	}
	public void setXfer_abnrml_amt_lim(BigDecimal xfer_abnrml_amt_lim) {
		this.xfer_abnrml_amt_lim = xfer_abnrml_amt_lim;
	}
	public BigDecimal getCum_dr_amt() {
		return cum_dr_amt;
	}
	public void setCum_dr_amt(BigDecimal cum_dr_amt) {
		this.cum_dr_amt = cum_dr_amt;
	}
	public BigDecimal getCum_cr_amt() {
		return cum_cr_amt;
	}
	public void setCum_cr_amt(BigDecimal cum_cr_amt) {
		this.cum_cr_amt = cum_cr_amt;
	}
	public BigDecimal getAcrd_cr_amt() {
		return acrd_cr_amt;
	}
	public void setAcrd_cr_amt(BigDecimal acrd_cr_amt) {
		this.acrd_cr_amt = acrd_cr_amt;
	}
	public Date getLast_tran_date() {
		return last_tran_date;
	}
	public void setLast_tran_date(Date last_tran_date) {
		this.last_tran_date = last_tran_date;
	}
	public String getMode_of_oper_code() {
		return mode_of_oper_code;
	}
	public void setMode_of_oper_code(String mode_of_oper_code) {
		this.mode_of_oper_code = mode_of_oper_code;
	}
	public String getPb_ps_code() {
		return pb_ps_code;
	}
	public void setPb_ps_code(String pb_ps_code) {
		this.pb_ps_code = pb_ps_code;
	}
	public String getServ_chrg_coll_flg() {
		return serv_chrg_coll_flg;
	}
	public void setServ_chrg_coll_flg(String serv_chrg_coll_flg) {
		this.serv_chrg_coll_flg = serv_chrg_coll_flg;
	}
	public String getFree_text() {
		return free_text;
	}
	public void setFree_text(String free_text) {
		this.free_text = free_text;
	}
	public String getAcct_turnover_det_flg() {
		return acct_turnover_det_flg;
	}
	public void setAcct_turnover_det_flg(String acct_turnover_det_flg) {
		this.acct_turnover_det_flg = acct_turnover_det_flg;
	}
	public String getNom_available_flg() {
		return nom_available_flg;
	}
	public void setNom_available_flg(String nom_available_flg) {
		this.nom_available_flg = nom_available_flg;
	}
	public String getAcct_locn_code() {
		return acct_locn_code;
	}
	public void setAcct_locn_code(String acct_locn_code) {
		this.acct_locn_code = acct_locn_code;
	}
	public Date getLast_purge_date() {
		return last_purge_date;
	}
	public void setLast_purge_date(Date last_purge_date) {
		this.last_purge_date = last_purge_date;
	}
	public BigDecimal getBal_on_purge_date() {
		return bal_on_purge_date;
	}
	public void setBal_on_purge_date(BigDecimal bal_on_purge_date) {
		this.bal_on_purge_date = bal_on_purge_date;
	}
	public String getInt_paid_flg() {
		return int_paid_flg;
	}
	public void setInt_paid_flg(String int_paid_flg) {
		this.int_paid_flg = int_paid_flg;
	}
	public String getInt_coll_flg() {
		return int_coll_flg;
	}
	public void setInt_coll_flg(String int_coll_flg) {
		this.int_coll_flg = int_coll_flg;
	}
	public Date getLast_any_tran_date() {
		return last_any_tran_date;
	}
	public void setLast_any_tran_date(Date last_any_tran_date) {
		this.last_any_tran_date = last_any_tran_date;
	}
	public String getHashed_no() {
		return hashed_no;
	}
	public void setHashed_no(String hashed_no) {
		this.hashed_no = hashed_no;
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
	public String getLimit_b2kid() {
		return limit_b2kid;
	}
	public void setLimit_b2kid(String limit_b2kid) {
		this.limit_b2kid = limit_b2kid;
	}
	public String getDrwng_power_ind() {
		return drwng_power_ind;
	}
	public void setDrwng_power_ind(String drwng_power_ind) {
		this.drwng_power_ind = drwng_power_ind;
	}
	public BigDecimal getDrwng_power_pcnt() {
		return drwng_power_pcnt;
	}
	public void setDrwng_power_pcnt(BigDecimal drwng_power_pcnt) {
		this.drwng_power_pcnt = drwng_power_pcnt;
	}
	public String getMicr_chq_chrg_coll_flg() {
		return micr_chq_chrg_coll_flg;
	}
	public void setMicr_chq_chrg_coll_flg(String micr_chq_chrg_coll_flg) {
		this.micr_chq_chrg_coll_flg = micr_chq_chrg_coll_flg;
	}
	public Date getLast_turnover_date() {
		return last_turnover_date;
	}
	public void setLast_turnover_date(Date last_turnover_date) {
		this.last_turnover_date = last_turnover_date;
	}
	public BigDecimal getNotional_rate() {
		return notional_rate;
	}
	public void setNotional_rate(BigDecimal notional_rate) {
		this.notional_rate = notional_rate;
	}
	public String getNotional_rate_code() {
		return notional_rate_code;
	}
	public void setNotional_rate_code(String notional_rate_code) {
		this.notional_rate_code = notional_rate_code;
	}
	public BigDecimal getFx_clr_bal_amt() {
		return fx_clr_bal_amt;
	}
	public void setFx_clr_bal_amt(BigDecimal fx_clr_bal_amt) {
		this.fx_clr_bal_amt = fx_clr_bal_amt;
	}
	public BigDecimal getFx_bal_on_purge_date() {
		return fx_bal_on_purge_date;
	}
	public void setFx_bal_on_purge_date(BigDecimal fx_bal_on_purge_date) {
		this.fx_bal_on_purge_date = fx_bal_on_purge_date;
	}
	public String getFd_ref_num() {
		return fd_ref_num;
	}
	public void setFd_ref_num(String fd_ref_num) {
		this.fd_ref_num = fd_ref_num;
	}
	public BigDecimal getFx_cum_cr_amt() {
		return fx_cum_cr_amt;
	}
	public void setFx_cum_cr_amt(BigDecimal fx_cum_cr_amt) {
		this.fx_cum_cr_amt = fx_cum_cr_amt;
	}
	public BigDecimal getFx_cum_dr_amt() {
		return fx_cum_dr_amt;
	}
	public void setFx_cum_dr_amt(BigDecimal fx_cum_dr_amt) {
		this.fx_cum_dr_amt = fx_cum_dr_amt;
	}
	public String getCrncy_code() {
		return crncy_code;
	}
	public void setCrncy_code(String crncy_code) {
		this.crncy_code = crncy_code;
	}
	public String getSource_of_fund() {
		return source_of_fund;
	}
	public void setSource_of_fund(String source_of_fund) {
		this.source_of_fund = source_of_fund;
	}
	public String getAnw_non_cust_alwd_flg() {
		return anw_non_cust_alwd_flg;
	}
	public void setAnw_non_cust_alwd_flg(String anw_non_cust_alwd_flg) {
		this.anw_non_cust_alwd_flg = anw_non_cust_alwd_flg;
	}
	public String getAcct_crncy_code() {
		return acct_crncy_code;
	}
	public void setAcct_crncy_code(String acct_crncy_code) {
		this.acct_crncy_code = acct_crncy_code;
	}
	public BigDecimal getLien_amt() {
		return lien_amt;
	}
	public void setLien_amt(BigDecimal lien_amt) {
		this.lien_amt = lien_amt;
	}
	public String getAcct_classification_flg() {
		return acct_classification_flg;
	}
	public void setAcct_classification_flg(String acct_classification_flg) {
		this.acct_classification_flg = acct_classification_flg;
	}
	public String getSystem_only_acct_flg() {
		return system_only_acct_flg;
	}
	public void setSystem_only_acct_flg(String system_only_acct_flg) {
		this.system_only_acct_flg = system_only_acct_flg;
	}
	public String getSingle_tran_flg() {
		return single_tran_flg;
	}
	public void setSingle_tran_flg(String single_tran_flg) {
		this.single_tran_flg = single_tran_flg;
	}
	public BigDecimal getUtilised_amt() {
		return utilised_amt;
	}
	public void setUtilised_amt(BigDecimal utilised_amt) {
		this.utilised_amt = utilised_amt;
	}
	public String getInter_sol_access_flg() {
		return inter_sol_access_flg;
	}
	public void setInter_sol_access_flg(String inter_sol_access_flg) {
		this.inter_sol_access_flg = inter_sol_access_flg;
	}
	public String getPurge_allowed_flg() {
		return purge_allowed_flg;
	}
	public void setPurge_allowed_flg(String purge_allowed_flg) {
		this.purge_allowed_flg = purge_allowed_flg;
	}
	public String getPurge_text() {
		return purge_text;
	}
	public void setPurge_text(String purge_text) {
		this.purge_text = purge_text;
	}
	public Date getMin_value_date() {
		return min_value_date;
	}
	public void setMin_value_date(Date min_value_date) {
		this.min_value_date = min_value_date;
	}
	public String getAcct_mgr_user_id() {
		return acct_mgr_user_id;
	}
	public void setAcct_mgr_user_id(String acct_mgr_user_id) {
		this.acct_mgr_user_id = acct_mgr_user_id;
	}
	public String getSchm_type() {
		return schm_type;
	}
	public void setSchm_type(String schm_type) {
		this.schm_type = schm_type;
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
	public BigDecimal getBal_on_frez_date() {
		return bal_on_frez_date;
	}
	public void setBal_on_frez_date(BigDecimal bal_on_frez_date) {
		this.bal_on_frez_date = bal_on_frez_date;
	}
	public String getSwift_allowed_flg() {
		return swift_allowed_flg;
	}
	public void setSwift_allowed_flg(String swift_allowed_flg) {
		this.swift_allowed_flg = swift_allowed_flg;
	}
	public BigDecimal getDacc_lim_pcnt() {
		return dacc_lim_pcnt;
	}
	public void setDacc_lim_pcnt(BigDecimal dacc_lim_pcnt) {
		this.dacc_lim_pcnt = dacc_lim_pcnt;
	}
	public BigDecimal getDacc_lim_abs() {
		return dacc_lim_abs;
	}
	public void setDacc_lim_abs(BigDecimal dacc_lim_abs) {
		this.dacc_lim_abs = dacc_lim_abs;
	}
	public String getChrg_level_code() {
		return chrg_level_code;
	}
	public void setChrg_level_code(String chrg_level_code) {
		this.chrg_level_code = chrg_level_code;
	}
	public String getAcct_cls_chrg_pend_verf() {
		return acct_cls_chrg_pend_verf;
	}
	public void setAcct_cls_chrg_pend_verf(String acct_cls_chrg_pend_verf) {
		this.acct_cls_chrg_pend_verf = acct_cls_chrg_pend_verf;
	}
	public String getPartitioned_flg() {
		return partitioned_flg;
	}
	public void setPartitioned_flg(String partitioned_flg) {
		this.partitioned_flg = partitioned_flg;
	}
	public String getPartitioned_type() {
		return partitioned_type;
	}
	public void setPartitioned_type(String partitioned_type) {
		this.partitioned_type = partitioned_type;
	}
	public String getPbf_download_flg() {
		return pbf_download_flg;
	}
	public void setPbf_download_flg(String pbf_download_flg) {
		this.pbf_download_flg = pbf_download_flg;
	}
	public Date getPbf_delink_date() {
		return pbf_delink_date;
	}
	public void setPbf_delink_date(Date pbf_delink_date) {
		this.pbf_delink_date = pbf_delink_date;
	}
	public String getWtax_flg() {
		return wtax_flg;
	}
	public void setWtax_flg(String wtax_flg) {
		this.wtax_flg = wtax_flg;
	}
	public String getWtax_amount_scope_flg() {
		return wtax_amount_scope_flg;
	}
	public void setWtax_amount_scope_flg(String wtax_amount_scope_flg) {
		this.wtax_amount_scope_flg = wtax_amount_scope_flg;
	}
	public String getInt_adj_for_deduction_flg() {
		return int_adj_for_deduction_flg;
	}
	public void setInt_adj_for_deduction_flg(String int_adj_for_deduction_flg) {
		this.int_adj_for_deduction_flg = int_adj_for_deduction_flg;
	}
	public String getOperative_acid() {
		return operative_acid;
	}
	public void setOperative_acid(String operative_acid) {
		this.operative_acid = operative_acid;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getNative_lang_name() {
		return native_lang_name;
	}
	public void setNative_lang_name(String native_lang_name) {
		this.native_lang_name = native_lang_name;
	}
	public String getNat_lang_title_code() {
		return nat_lang_title_code;
	}
	public void setNat_lang_title_code(String nat_lang_title_code) {
		this.nat_lang_title_code = nat_lang_title_code;
	}
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	public BigDecimal getTs_cnt() {
		return ts_cnt;
	}
	public void setTs_cnt(BigDecimal ts_cnt) {
		this.ts_cnt = ts_cnt;
	}
	public String getPool_id() {
		return pool_id;
	}
	public void setPool_id(String pool_id) {
		this.pool_id = pool_id;
	}
	public String getAllow_sweeps() {
		return allow_sweeps;
	}
	public void setAllow_sweeps(String allow_sweeps) {
		this.allow_sweeps = allow_sweeps;
	}
	public BigDecimal getOrder_of_utilisation() {
		return order_of_utilisation;
	}
	public void setOrder_of_utilisation(BigDecimal order_of_utilisation) {
		this.order_of_utilisation = order_of_utilisation;
	}
	public BigDecimal getWtax_pcnt() {
		return wtax_pcnt;
	}
	public void setWtax_pcnt(BigDecimal wtax_pcnt) {
		this.wtax_pcnt = wtax_pcnt;
	}
	public BigDecimal getWtax_floor_limit() {
		return wtax_floor_limit;
	}
	public void setWtax_floor_limit(BigDecimal wtax_floor_limit) {
		this.wtax_floor_limit = wtax_floor_limit;
	}
	public String getDsa_penal_flg() {
		return dsa_penal_flg;
	}
	public void setDsa_penal_flg(String dsa_penal_flg) {
		this.dsa_penal_flg = dsa_penal_flg;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public String getSource_deal_code() {
		return source_deal_code;
	}
	public void setSource_deal_code(String source_deal_code) {
		this.source_deal_code = source_deal_code;
	}
	public String getDisburse_deal_code() {
		return disburse_deal_code;
	}
	public void setDisburse_deal_code(String disburse_deal_code) {
		this.disburse_deal_code = disburse_deal_code;
	}
	public BigDecimal getSweep_in_min_bal() {
		return sweep_in_min_bal;
	}
	public void setSweep_in_min_bal(BigDecimal sweep_in_min_bal) {
		this.sweep_in_min_bal = sweep_in_min_bal;
	}
	public BigDecimal getUsed_single_tran_lim() {
		return used_single_tran_lim;
	}
	public void setUsed_single_tran_lim(BigDecimal used_single_tran_lim) {
		this.used_single_tran_lim = used_single_tran_lim;
	}
	public BigDecimal getUsed_clean_single_tran_lim() {
		return used_clean_single_tran_lim;
	}
	public void setUsed_clean_single_tran_lim(BigDecimal used_clean_single_tran_lim) {
		this.used_clean_single_tran_lim = used_clean_single_tran_lim;
	}
	public BigDecimal getUsed_un_clr_over_dacc_amt() {
		return used_un_clr_over_dacc_amt;
	}
	public void setUsed_un_clr_over_dacc_amt(BigDecimal used_un_clr_over_dacc_amt) {
		this.used_un_clr_over_dacc_amt = used_un_clr_over_dacc_amt;
	}
	public BigDecimal getFfd_contrib_to_acct() {
		return ffd_contrib_to_acct;
	}
	public void setFfd_contrib_to_acct(BigDecimal ffd_contrib_to_acct) {
		this.ffd_contrib_to_acct = ffd_contrib_to_acct;
	}
	public String getAcct_creation_mode() {
		return acct_creation_mode;
	}
	public void setAcct_creation_mode(String acct_creation_mode) {
		this.acct_creation_mode = acct_creation_mode;
	}
	public String getWtax_level_flg() {
		return wtax_level_flg;
	}
	public void setWtax_level_flg(String wtax_level_flg) {
		this.wtax_level_flg = wtax_level_flg;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public BigDecimal getFuture_bal_amt() {
		return future_bal_amt;
	}
	public void setFuture_bal_amt(BigDecimal future_bal_amt) {
		this.future_bal_amt = future_bal_amt;
	}
	public BigDecimal getUtil_future_bal_amt() {
		return util_future_bal_amt;
	}
	public void setUtil_future_bal_amt(BigDecimal util_future_bal_amt) {
		this.util_future_bal_amt = util_future_bal_amt;
	}
	public BigDecimal getDafa_lim() {
		return dafa_lim;
	}
	public void setDafa_lim(BigDecimal dafa_lim) {
		this.dafa_lim = dafa_lim;
	}
	public BigDecimal getDafa_lim_abs() {
		return dafa_lim_abs;
	}
	public void setDafa_lim_abs(BigDecimal dafa_lim_abs) {
		this.dafa_lim_abs = dafa_lim_abs;
	}
	public BigDecimal getDafa_lim_pcnt() {
		return dafa_lim_pcnt;
	}
	public void setDafa_lim_pcnt(BigDecimal dafa_lim_pcnt) {
		this.dafa_lim_pcnt = dafa_lim_pcnt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AccountsInquiryEntity [acid=" + acid + ", entity_cre_flg=" + entity_cre_flg + ", del_flg=" + del_flg
				+ ", sol_id=" + sol_id + ", acct_prefix=" + acct_prefix + ", acct_num=" + acct_num + ", bacid=" + bacid
				+ ", foracid=" + foracid + ", acct_name=" + acct_name + ", acct_short_name=" + acct_short_name
				+ ", cust_id=" + cust_id + ", emp_id=" + emp_id + ", gl_sub_head_code=" + gl_sub_head_code
				+ ", acct_ownership=" + acct_ownership + ", schm_code=" + schm_code + ", dr_bal_lim=" + dr_bal_lim
				+ ", acct_rpt_code=" + acct_rpt_code + ", frez_code=" + frez_code + ", frez_reason_code="
				+ frez_reason_code + ", acct_opn_date=" + acct_opn_date + ", acct_cls_flg=" + acct_cls_flg
				+ ", acct_cls_date=" + acct_cls_date + ", clr_bal_amt=" + clr_bal_amt + ", tot_mod_times="
				+ tot_mod_times + ", ledg_num=" + ledg_num + ", un_clr_bal_amt=" + un_clr_bal_amt + ", drwng_power="
				+ drwng_power + ", sanct_lim=" + sanct_lim + ", adhoc_lim=" + adhoc_lim + ", emer_advn=" + emer_advn
				+ ", dacc_lim=" + dacc_lim + ", system_reserved_amt=" + system_reserved_amt + ", single_tran_lim="
				+ single_tran_lim + ", clean_adhoc_lim=" + clean_adhoc_lim + ", clean_emer_advn=" + clean_emer_advn
				+ ", clean_single_tran_lim=" + clean_single_tran_lim + ", system_gen_lim=" + system_gen_lim
				+ ", chq_alwd_flg=" + chq_alwd_flg + ", cash_excp_amt_lim=" + cash_excp_amt_lim + ", clg_excp_amt_lim="
				+ clg_excp_amt_lim + ", xfer_excp_amt_lim=" + xfer_excp_amt_lim + ", cash_cr_excp_amt_lim="
				+ cash_cr_excp_amt_lim + ", clg_cr_excp_amt_lim=" + clg_cr_excp_amt_lim + ", xfer_cr_excp_amt_lim="
				+ xfer_cr_excp_amt_lim + ", cash_abnrml_amt_lim=" + cash_abnrml_amt_lim + ", clg_abnrml_amt_lim="
				+ clg_abnrml_amt_lim + ", xfer_abnrml_amt_lim=" + xfer_abnrml_amt_lim + ", cum_dr_amt=" + cum_dr_amt
				+ ", cum_cr_amt=" + cum_cr_amt + ", acrd_cr_amt=" + acrd_cr_amt + ", last_tran_date=" + last_tran_date
				+ ", mode_of_oper_code=" + mode_of_oper_code + ", pb_ps_code=" + pb_ps_code + ", serv_chrg_coll_flg="
				+ serv_chrg_coll_flg + ", free_text=" + free_text + ", acct_turnover_det_flg=" + acct_turnover_det_flg
				+ ", nom_available_flg=" + nom_available_flg + ", acct_locn_code=" + acct_locn_code
				+ ", last_purge_date=" + last_purge_date + ", bal_on_purge_date=" + bal_on_purge_date
				+ ", int_paid_flg=" + int_paid_flg + ", int_coll_flg=" + int_coll_flg + ", last_any_tran_date="
				+ last_any_tran_date + ", hashed_no=" + hashed_no + ", lchg_user_id=" + lchg_user_id + ", lchg_time="
				+ lchg_time + ", rcre_user_id=" + rcre_user_id + ", rcre_time=" + rcre_time + ", limit_b2kid="
				+ limit_b2kid + ", drwng_power_ind=" + drwng_power_ind + ", drwng_power_pcnt=" + drwng_power_pcnt
				+ ", micr_chq_chrg_coll_flg=" + micr_chq_chrg_coll_flg + ", last_turnover_date=" + last_turnover_date
				+ ", notional_rate=" + notional_rate + ", notional_rate_code=" + notional_rate_code
				+ ", fx_clr_bal_amt=" + fx_clr_bal_amt + ", fx_bal_on_purge_date=" + fx_bal_on_purge_date
				+ ", fd_ref_num=" + fd_ref_num + ", fx_cum_cr_amt=" + fx_cum_cr_amt + ", fx_cum_dr_amt=" + fx_cum_dr_amt
				+ ", crncy_code=" + crncy_code + ", source_of_fund=" + source_of_fund + ", anw_non_cust_alwd_flg="
				+ anw_non_cust_alwd_flg + ", acct_crncy_code=" + acct_crncy_code + ", lien_amt=" + lien_amt
				+ ", acct_classification_flg=" + acct_classification_flg + ", system_only_acct_flg="
				+ system_only_acct_flg + ", single_tran_flg=" + single_tran_flg + ", utilised_amt=" + utilised_amt
				+ ", inter_sol_access_flg=" + inter_sol_access_flg + ", purge_allowed_flg=" + purge_allowed_flg
				+ ", purge_text=" + purge_text + ", min_value_date=" + min_value_date + ", acct_mgr_user_id="
				+ acct_mgr_user_id + ", schm_type=" + schm_type + ", last_frez_date=" + last_frez_date
				+ ", last_unfrez_date=" + last_unfrez_date + ", bal_on_frez_date=" + bal_on_frez_date
				+ ", swift_allowed_flg=" + swift_allowed_flg + ", dacc_lim_pcnt=" + dacc_lim_pcnt + ", dacc_lim_abs="
				+ dacc_lim_abs + ", chrg_level_code=" + chrg_level_code + ", acct_cls_chrg_pend_verf="
				+ acct_cls_chrg_pend_verf + ", partitioned_flg=" + partitioned_flg + ", partitioned_type="
				+ partitioned_type + ", pbf_download_flg=" + pbf_download_flg + ", pbf_delink_date=" + pbf_delink_date
				+ ", wtax_flg=" + wtax_flg + ", wtax_amount_scope_flg=" + wtax_amount_scope_flg
				+ ", int_adj_for_deduction_flg=" + int_adj_for_deduction_flg + ", operative_acid=" + operative_acid
				+ ", phone_num=" + phone_num + ", native_lang_name=" + native_lang_name + ", nat_lang_title_code="
				+ nat_lang_title_code + ", lang_code=" + lang_code + ", ts_cnt=" + ts_cnt + ", pool_id=" + pool_id
				+ ", allow_sweeps=" + allow_sweeps + ", order_of_utilisation=" + order_of_utilisation + ", wtax_pcnt="
				+ wtax_pcnt + ", wtax_floor_limit=" + wtax_floor_limit + ", dsa_penal_flg=" + dsa_penal_flg
				+ ", product_group=" + product_group + ", source_deal_code=" + source_deal_code
				+ ", disburse_deal_code=" + disburse_deal_code + ", sweep_in_min_bal=" + sweep_in_min_bal
				+ ", used_single_tran_lim=" + used_single_tran_lim + ", used_clean_single_tran_lim="
				+ used_clean_single_tran_lim + ", used_un_clr_over_dacc_amt=" + used_un_clr_over_dacc_amt
				+ ", ffd_contrib_to_acct=" + ffd_contrib_to_acct + ", acct_creation_mode=" + acct_creation_mode
				+ ", wtax_level_flg=" + wtax_level_flg + ", last_modified_date=" + last_modified_date
				+ ", future_bal_amt=" + future_bal_amt + ", util_future_bal_amt=" + util_future_bal_amt + ", dafa_lim="
				+ dafa_lim + ", dafa_lim_abs=" + dafa_lim_abs + ", dafa_lim_pcnt=" + dafa_lim_pcnt + ", getAcid()="
				+ getAcid() + ", getEntity_cre_flg()=" + getEntity_cre_flg() + ", getDel_flg()=" + getDel_flg()
				+ ", getSol_id()=" + getSol_id() + ", getAcct_prefix()=" + getAcct_prefix() + ", getAcct_num()="
				+ getAcct_num() + ", getBacid()=" + getBacid() + ", getForacid()=" + getForacid() + ", getAcct_name()="
				+ getAcct_name() + ", getAcct_short_name()=" + getAcct_short_name() + ", getCust_id()=" + getCust_id()
				+ ", getEmp_id()=" + getEmp_id() + ", getGl_sub_head_code()=" + getGl_sub_head_code()
				+ ", getAcct_ownership()=" + getAcct_ownership() + ", getSchm_code()=" + getSchm_code()
				+ ", getDr_bal_lim()=" + getDr_bal_lim() + ", getAcct_rpt_code()=" + getAcct_rpt_code()
				+ ", getFrez_code()=" + getFrez_code() + ", getFrez_reason_code()=" + getFrez_reason_code()
				+ ", getAcct_opn_date()=" + getAcct_opn_date() + ", getAcct_cls_flg()=" + getAcct_cls_flg()
				+ ", getAcct_cls_date()=" + getAcct_cls_date() + ", getClr_bal_amt()=" + getClr_bal_amt()
				+ ", getTot_mod_times()=" + getTot_mod_times() + ", getLedg_num()=" + getLedg_num()
				+ ", getUn_clr_bal_amt()=" + getUn_clr_bal_amt() + ", getDrwng_power()=" + getDrwng_power()
				+ ", getSanct_lim()=" + getSanct_lim() + ", getAdhoc_lim()=" + getAdhoc_lim() + ", getEmer_advn()="
				+ getEmer_advn() + ", getDacc_lim()=" + getDacc_lim() + ", getSystem_reserved_amt()="
				+ getSystem_reserved_amt() + ", getSingle_tran_lim()=" + getSingle_tran_lim()
				+ ", getClean_adhoc_lim()=" + getClean_adhoc_lim() + ", getClean_emer_advn()=" + getClean_emer_advn()
				+ ", getClean_single_tran_lim()=" + getClean_single_tran_lim() + ", getSystem_gen_lim()="
				+ getSystem_gen_lim() + ", getChq_alwd_flg()=" + getChq_alwd_flg() + ", getCash_excp_amt_lim()="
				+ getCash_excp_amt_lim() + ", getClg_excp_amt_lim()=" + getClg_excp_amt_lim()
				+ ", getXfer_excp_amt_lim()=" + getXfer_excp_amt_lim() + ", getCash_cr_excp_amt_lim()="
				+ getCash_cr_excp_amt_lim() + ", getClg_cr_excp_amt_lim()=" + getClg_cr_excp_amt_lim()
				+ ", getXfer_cr_excp_amt_lim()=" + getXfer_cr_excp_amt_lim() + ", getCash_abnrml_amt_lim()="
				+ getCash_abnrml_amt_lim() + ", getClg_abnrml_amt_lim()=" + getClg_abnrml_amt_lim()
				+ ", getXfer_abnrml_amt_lim()=" + getXfer_abnrml_amt_lim() + ", getCum_dr_amt()=" + getCum_dr_amt()
				+ ", getCum_cr_amt()=" + getCum_cr_amt() + ", getAcrd_cr_amt()=" + getAcrd_cr_amt()
				+ ", getLast_tran_date()=" + getLast_tran_date() + ", getMode_of_oper_code()=" + getMode_of_oper_code()
				+ ", getPb_ps_code()=" + getPb_ps_code() + ", getServ_chrg_coll_flg()=" + getServ_chrg_coll_flg()
				+ ", getFree_text()=" + getFree_text() + ", getAcct_turnover_det_flg()=" + getAcct_turnover_det_flg()
				+ ", getNom_available_flg()=" + getNom_available_flg() + ", getAcct_locn_code()=" + getAcct_locn_code()
				+ ", getLast_purge_date()=" + getLast_purge_date() + ", getBal_on_purge_date()="
				+ getBal_on_purge_date() + ", getInt_paid_flg()=" + getInt_paid_flg() + ", getInt_coll_flg()="
				+ getInt_coll_flg() + ", getLast_any_tran_date()=" + getLast_any_tran_date() + ", getHashed_no()="
				+ getHashed_no() + ", getLchg_user_id()=" + getLchg_user_id() + ", getLchg_time()=" + getLchg_time()
				+ ", getRcre_user_id()=" + getRcre_user_id() + ", getRcre_time()=" + getRcre_time()
				+ ", getLimit_b2kid()=" + getLimit_b2kid() + ", getDrwng_power_ind()=" + getDrwng_power_ind()
				+ ", getDrwng_power_pcnt()=" + getDrwng_power_pcnt() + ", getMicr_chq_chrg_coll_flg()="
				+ getMicr_chq_chrg_coll_flg() + ", getLast_turnover_date()=" + getLast_turnover_date()
				+ ", getNotional_rate()=" + getNotional_rate() + ", getNotional_rate_code()=" + getNotional_rate_code()
				+ ", getFx_clr_bal_amt()=" + getFx_clr_bal_amt() + ", getFx_bal_on_purge_date()="
				+ getFx_bal_on_purge_date() + ", getFd_ref_num()=" + getFd_ref_num() + ", getFx_cum_cr_amt()="
				+ getFx_cum_cr_amt() + ", getFx_cum_dr_amt()=" + getFx_cum_dr_amt() + ", getCrncy_code()="
				+ getCrncy_code() + ", getSource_of_fund()=" + getSource_of_fund() + ", getAnw_non_cust_alwd_flg()="
				+ getAnw_non_cust_alwd_flg() + ", getAcct_crncy_code()=" + getAcct_crncy_code() + ", getLien_amt()="
				+ getLien_amt() + ", getAcct_classification_flg()=" + getAcct_classification_flg()
				+ ", getSystem_only_acct_flg()=" + getSystem_only_acct_flg() + ", getSingle_tran_flg()="
				+ getSingle_tran_flg() + ", getUtilised_amt()=" + getUtilised_amt() + ", getInter_sol_access_flg()="
				+ getInter_sol_access_flg() + ", getPurge_allowed_flg()=" + getPurge_allowed_flg()
				+ ", getPurge_text()=" + getPurge_text() + ", getMin_value_date()=" + getMin_value_date()
				+ ", getAcct_mgr_user_id()=" + getAcct_mgr_user_id() + ", getSchm_type()=" + getSchm_type()
				+ ", getLast_frez_date()=" + getLast_frez_date() + ", getLast_unfrez_date()=" + getLast_unfrez_date()
				+ ", getBal_on_frez_date()=" + getBal_on_frez_date() + ", getSwift_allowed_flg()="
				+ getSwift_allowed_flg() + ", getDacc_lim_pcnt()=" + getDacc_lim_pcnt() + ", getDacc_lim_abs()="
				+ getDacc_lim_abs() + ", getChrg_level_code()=" + getChrg_level_code()
				+ ", getAcct_cls_chrg_pend_verf()=" + getAcct_cls_chrg_pend_verf() + ", getPartitioned_flg()="
				+ getPartitioned_flg() + ", getPartitioned_type()=" + getPartitioned_type() + ", getPbf_download_flg()="
				+ getPbf_download_flg() + ", getPbf_delink_date()=" + getPbf_delink_date() + ", getWtax_flg()="
				+ getWtax_flg() + ", getWtax_amount_scope_flg()=" + getWtax_amount_scope_flg()
				+ ", getInt_adj_for_deduction_flg()=" + getInt_adj_for_deduction_flg() + ", getOperative_acid()="
				+ getOperative_acid() + ", getPhone_num()=" + getPhone_num() + ", getNative_lang_name()="
				+ getNative_lang_name() + ", getNat_lang_title_code()=" + getNat_lang_title_code() + ", getLang_code()="
				+ getLang_code() + ", getTs_cnt()=" + getTs_cnt() + ", getPool_id()=" + getPool_id()
				+ ", getAllow_sweeps()=" + getAllow_sweeps() + ", getOrder_of_utilisation()="
				+ getOrder_of_utilisation() + ", getWtax_pcnt()=" + getWtax_pcnt() + ", getWtax_floor_limit()="
				+ getWtax_floor_limit() + ", getDsa_penal_flg()=" + getDsa_penal_flg() + ", getProduct_group()="
				+ getProduct_group() + ", getSource_deal_code()=" + getSource_deal_code() + ", getDisburse_deal_code()="
				+ getDisburse_deal_code() + ", getSweep_in_min_bal()=" + getSweep_in_min_bal()
				+ ", getUsed_single_tran_lim()=" + getUsed_single_tran_lim() + ", getUsed_clean_single_tran_lim()="
				+ getUsed_clean_single_tran_lim() + ", getUsed_un_clr_over_dacc_amt()=" + getUsed_un_clr_over_dacc_amt()
				+ ", getFfd_contrib_to_acct()=" + getFfd_contrib_to_acct() + ", getAcct_creation_mode()="
				+ getAcct_creation_mode() + ", getWtax_level_flg()=" + getWtax_level_flg()
				+ ", getLast_modified_date()=" + getLast_modified_date() + ", getFuture_bal_amt()="
				+ getFuture_bal_amt() + ", getUtil_future_bal_amt()=" + getUtil_future_bal_amt() + ", getDafa_lim()="
				+ getDafa_lim() + ", getDafa_lim_abs()=" + getDafa_lim_abs() + ", getDafa_lim_pcnt()="
				+ getDafa_lim_pcnt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

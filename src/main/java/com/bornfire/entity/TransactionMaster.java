package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity  
@Table(name="DTD")
public class TransactionMaster implements Serializable {
	@EmbeddedId
	TransactionMasterEmbeddedId transactionmasterembeddedid;
	
	public TransactionMasterEmbeddedId getTransactionmasterembeddedid() {
		return transactionmasterembeddedid;
	}
	public void setTransactionmasterembeddedid(TransactionMasterEmbeddedId transactionmasterembeddedid) {
		this.transactionmasterembeddedid = transactionmasterembeddedid;
	}
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	tran_date;

	
	private String	part_tran_srl_num;
	private String	del_flg;
	private String	tran_type;
	private String	tran_sub_type;

	private String	gl_sub_head_code;

	@ManyToOne
	@JoinColumn(name="acid", referencedColumnName="acid")
	private AccountsInquiryEntity accountsinquiryentity;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	value_date;
	private BigDecimal	tran_amt;
	private String	tran_particular;
	private String	entry_user_id;
	private String	pstd_user_id;
	private String	vfd_user_id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	entry_date;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	pstd_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	vfd_date;
	private String	rpt_code;
	private String	ref_num;
	private String	instrmnt_type;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	instrmnt_date;
	private String	instrmnt_num;
	private String	instrmnt_alpha;
	private String	tran_rmks;
	private String	pstd_flg;
	private String	prnt_advc_ind;
	private String	amt_reservation_ind;
	private BigDecimal	reservation_amt;
	private String	restrict_modify_ind;
	private String	lchg_user_id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	lchg_time;
	private String	rcre_user_id;
	private Date	rcre_time;
	private String	cust_id;
	private String	voucher_print_flg;
	private String	module_id;
	private String	br_code;
	private BigDecimal	fx_tran_amt;
	private String	rate_code;
	private BigDecimal	rate;
	private String	crncy_code;
	private String	navigation_flg;
	private String	tran_crncy_code;
	private String	ref_crncy_code;
	private BigDecimal	ref_amt;
	private String	sol_id;
	private String	bank_code;
	private String	trea_ref_num;
	private BigDecimal	trea_rate;
	private BigDecimal	ts_cnt;
	private String	gst_upd_flg;
	private String	iso_flg;
	private String	eabfab_upd_flg;
	private String	lift_lien_flg;
	private String	proxy_post_ind;
	private String	si_srl_num;
	private Date	si_org_exec_date;
	private String	pr_srl_num;
	private String	serial_num;
	private String	del_memo_pad;
	private String	uad_module_id;
	private String	uad_module_key;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	reversal_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date	reversal_value_date;
	private String	pttm_event_type;
	private String	proxy_acid;
	private String	tod_entity_type;
	private String	tod_entity_id;
	private String	dth_init_sol_id;
	private BigDecimal	regularization_amt;
	private BigDecimal	principal_portion_amt;
	private String	tf_entity_sol_id;
	private String	tran_particular_2;
	private String	tran_particular_code;
	private String	tr_status;
	private String	party_code;
	
	
	
	
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
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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
	
	public String getGl_sub_head_code() {
		return gl_sub_head_code;
	}
	public void setGl_sub_head_code(String gl_sub_head_code) {
		this.gl_sub_head_code = gl_sub_head_code;
	}
	
	public AccountsInquiryEntity getAccountsinquiryentity() {
		return accountsinquiryentity;
	}
	public void setAccountsinquiryentity(AccountsInquiryEntity accountsinquiryentity) {
		this.accountsinquiryentity = accountsinquiryentity;
	}
	public Date getValue_date() {
		return value_date;
	}
	public void setValue_date(Date value_date) {
		this.value_date = value_date;
	}
	public BigDecimal getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getTran_particular() {
		return tran_particular;
	}
	public void setTran_particular(String tran_particular) {
		this.tran_particular = tran_particular;
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
	public String getRpt_code() {
		return rpt_code;
	}
	public void setRpt_code(String rpt_code) {
		this.rpt_code = rpt_code;
	}
	public String getRef_num() {
		return ref_num;
	}
	public void setRef_num(String ref_num) {
		this.ref_num = ref_num;
	}
	public String getInstrmnt_type() {
		return instrmnt_type;
	}
	public void setInstrmnt_type(String instrmnt_type) {
		this.instrmnt_type = instrmnt_type;
	}
	public Date getInstrmnt_date() {
		return instrmnt_date;
	}
	public void setInstrmnt_date(Date instrmnt_date) {
		this.instrmnt_date = instrmnt_date;
	}
	public String getInstrmnt_num() {
		return instrmnt_num;
	}
	public void setInstrmnt_num(String instrmnt_num) {
		this.instrmnt_num = instrmnt_num;
	}
	public String getInstrmnt_alpha() {
		return instrmnt_alpha;
	}
	public void setInstrmnt_alpha(String instrmnt_alpha) {
		this.instrmnt_alpha = instrmnt_alpha;
	}
	public String getTran_rmks() {
		return tran_rmks;
	}
	public void setTran_rmks(String tran_rmks) {
		this.tran_rmks = tran_rmks;
	}
	public String getPstd_flg() {
		return pstd_flg;
	}
	public void setPstd_flg(String pstd_flg) {
		this.pstd_flg = pstd_flg;
	}
	public String getPrnt_advc_ind() {
		return prnt_advc_ind;
	}
	public void setPrnt_advc_ind(String prnt_advc_ind) {
		this.prnt_advc_ind = prnt_advc_ind;
	}
	public String getAmt_reservation_ind() {
		return amt_reservation_ind;
	}
	public void setAmt_reservation_ind(String amt_reservation_ind) {
		this.amt_reservation_ind = amt_reservation_ind;
	}
	public BigDecimal getReservation_amt() {
		return reservation_amt;
	}
	public void setReservation_amt(BigDecimal reservation_amt) {
		this.reservation_amt = reservation_amt;
	}
	public String getRestrict_modify_ind() {
		return restrict_modify_ind;
	}
	public void setRestrict_modify_ind(String restrict_modify_ind) {
		this.restrict_modify_ind = restrict_modify_ind;
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
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getVoucher_print_flg() {
		return voucher_print_flg;
	}
	public void setVoucher_print_flg(String voucher_print_flg) {
		this.voucher_print_flg = voucher_print_flg;
	}
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getBr_code() {
		return br_code;
	}
	public void setBr_code(String br_code) {
		this.br_code = br_code;
	}
	public BigDecimal getFx_tran_amt() {
		return fx_tran_amt;
	}
	public void setFx_tran_amt(BigDecimal fx_tran_amt) {
		this.fx_tran_amt = fx_tran_amt;
	}
	public String getRate_code() {
		return rate_code;
	}
	public void setRate_code(String rate_code) {
		this.rate_code = rate_code;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getCrncy_code() {
		return crncy_code;
	}
	public void setCrncy_code(String crncy_code) {
		this.crncy_code = crncy_code;
	}
	public String getNavigation_flg() {
		return navigation_flg;
	}
	public void setNavigation_flg(String navigation_flg) {
		this.navigation_flg = navigation_flg;
	}
	public String getTran_crncy_code() {
		return tran_crncy_code;
	}
	public void setTran_crncy_code(String tran_crncy_code) {
		this.tran_crncy_code = tran_crncy_code;
	}
	public String getRef_crncy_code() {
		return ref_crncy_code;
	}
	public void setRef_crncy_code(String ref_crncy_code) {
		this.ref_crncy_code = ref_crncy_code;
	}
	public BigDecimal getRef_amt() {
		return ref_amt;
	}
	public void setRef_amt(BigDecimal ref_amt) {
		this.ref_amt = ref_amt;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getTrea_ref_num() {
		return trea_ref_num;
	}
	public void setTrea_ref_num(String trea_ref_num) {
		this.trea_ref_num = trea_ref_num;
	}
	public BigDecimal getTrea_rate() {
		return trea_rate;
	}
	public void setTrea_rate(BigDecimal trea_rate) {
		this.trea_rate = trea_rate;
	}
	public BigDecimal getTs_cnt() {
		return ts_cnt;
	}
	public void setTs_cnt(BigDecimal ts_cnt) {
		this.ts_cnt = ts_cnt;
	}
	public String getGst_upd_flg() {
		return gst_upd_flg;
	}
	public void setGst_upd_flg(String gst_upd_flg) {
		this.gst_upd_flg = gst_upd_flg;
	}
	public String getIso_flg() {
		return iso_flg;
	}
	public void setIso_flg(String iso_flg) {
		this.iso_flg = iso_flg;
	}
	public String getEabfab_upd_flg() {
		return eabfab_upd_flg;
	}
	public void setEabfab_upd_flg(String eabfab_upd_flg) {
		this.eabfab_upd_flg = eabfab_upd_flg;
	}
	public String getLift_lien_flg() {
		return lift_lien_flg;
	}
	public void setLift_lien_flg(String lift_lien_flg) {
		this.lift_lien_flg = lift_lien_flg;
	}
	public String getProxy_post_ind() {
		return proxy_post_ind;
	}
	public void setProxy_post_ind(String proxy_post_ind) {
		this.proxy_post_ind = proxy_post_ind;
	}
	public String getSi_srl_num() {
		return si_srl_num;
	}
	public void setSi_srl_num(String si_srl_num) {
		this.si_srl_num = si_srl_num;
	}
	public Date getSi_org_exec_date() {
		return si_org_exec_date;
	}
	public void setSi_org_exec_date(Date si_org_exec_date) {
		this.si_org_exec_date = si_org_exec_date;
	}
	public String getPr_srl_num() {
		return pr_srl_num;
	}
	public void setPr_srl_num(String pr_srl_num) {
		this.pr_srl_num = pr_srl_num;
	}
	public String getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}
	public String getDel_memo_pad() {
		return del_memo_pad;
	}
	public void setDel_memo_pad(String del_memo_pad) {
		this.del_memo_pad = del_memo_pad;
	}
	public String getUad_module_id() {
		return uad_module_id;
	}
	public void setUad_module_id(String uad_module_id) {
		this.uad_module_id = uad_module_id;
	}
	public String getUad_module_key() {
		return uad_module_key;
	}
	public void setUad_module_key(String uad_module_key) {
		this.uad_module_key = uad_module_key;
	}
	public Date getReversal_date() {
		return reversal_date;
	}
	public void setReversal_date(Date reversal_date) {
		this.reversal_date = reversal_date;
	}
	public Date getReversal_value_date() {
		return reversal_value_date;
	}
	public void setReversal_value_date(Date reversal_value_date) {
		this.reversal_value_date = reversal_value_date;
	}
	public String getPttm_event_type() {
		return pttm_event_type;
	}
	public void setPttm_event_type(String pttm_event_type) {
		this.pttm_event_type = pttm_event_type;
	}
	public String getProxy_acid() {
		return proxy_acid;
	}
	public void setProxy_acid(String proxy_acid) {
		this.proxy_acid = proxy_acid;
	}
	public String getTod_entity_type() {
		return tod_entity_type;
	}
	public void setTod_entity_type(String tod_entity_type) {
		this.tod_entity_type = tod_entity_type;
	}
	public String getTod_entity_id() {
		return tod_entity_id;
	}
	public void setTod_entity_id(String tod_entity_id) {
		this.tod_entity_id = tod_entity_id;
	}
	public String getDth_init_sol_id() {
		return dth_init_sol_id;
	}
	public void setDth_init_sol_id(String dth_init_sol_id) {
		this.dth_init_sol_id = dth_init_sol_id;
	}
	public BigDecimal getRegularization_amt() {
		return regularization_amt;
	}
	public void setRegularization_amt(BigDecimal regularization_amt) {
		this.regularization_amt = regularization_amt;
	}
	public BigDecimal getPrincipal_portion_amt() {
		return principal_portion_amt;
	}
	public void setPrincipal_portion_amt(BigDecimal principal_portion_amt) {
		this.principal_portion_amt = principal_portion_amt;
	}
	public String getTf_entity_sol_id() {
		return tf_entity_sol_id;
	}
	public void setTf_entity_sol_id(String tf_entity_sol_id) {
		this.tf_entity_sol_id = tf_entity_sol_id;
	}
	public String getTran_particular_2() {
		return tran_particular_2;
	}
	public void setTran_particular_2(String tran_particular_2) {
		this.tran_particular_2 = tran_particular_2;
	}
	public String getTran_particular_code() {
		return tran_particular_code;
	}
	public void setTran_particular_code(String tran_particular_code) {
		this.tran_particular_code = tran_particular_code;
	}
	public String getTr_status() {
		return tr_status;
	}
	public void setTr_status(String tr_status) {
		this.tr_status = tr_status;
	}
	public String getParty_code() {
		return party_code;
	}
	public void setParty_code(String party_code) {
		this.party_code = party_code;
	}
	
	
	
	
	


}
package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity  
@Table(name="BAML_ACCT_TRANS_TABLE")
public class TRAN_MASTER implements Serializable{
	@EmbeddedId
	TransactionMasterEmbeddedId transactionmasterembeddedid;
	
	public TransactionMasterEmbeddedId getTransactionmasterembeddedid() {
		return transactionmasterembeddedid;
	}
	public void setTransactionmasterembeddedid(TransactionMasterEmbeddedId transactionmasterembeddedid) {
		this.transactionmasterembeddedid = transactionmasterembeddedid;
	}
	private String	sol_id;
	private String	gl_sub_head_code;
	
	@ManyToOne
	@JoinColumn(name="acid", referencedColumnName="acid")
	private ACCT_MASTER aCCT_MASTER;
	
	public ACCT_MASTER getaCCT_MASTER() {
		return aCCT_MASTER;
	}
	public void setaCCT_MASTER(ACCT_MASTER aCCT_MASTER) {
		this.aCCT_MASTER = aCCT_MASTER;
	}
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	tran_date;
	private String	tran_type;
	private String	tran_sub_type;
	private String	part_tran_srl_num;
	private String	tran_crncy_code;
	private BigDecimal	tran_amt;
	private String	tran_particular;
	private String	tran_rmks;
	private String	ref_num;
	private String	module_id;
	private String	tr_status;
	private String	entry_user_id;
	private String	pstd_user_id;
	private String	vfd_user_id;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	entry_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	vfd_date;
	private String	pstd_flg;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	pstd_date;
	private String	del_flg;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	rcre_time;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	lchg_time;
	private String	cust_id;
	private String	rate_code;
	private BigDecimal	rate;
	private String	crncy_code;
	private BigDecimal	fx_tran_amt;
	private String	tran_particular_code;
	private String	tran_free_code1;
	private String	tran_free_code2;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	reversal_date;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	reversal_value_date;
	private String	schm_code;
	private String	dtt_flow_code;
	private String	loan_flow_code;
	private String	ctran_id;
	private String	reversal_flg;
	private Date	rev_tran_date;
	private String	rev_tran_id;
	private String	rev_part_tran_srl_num;
	private String	recv_bankbranchcode;
	private String	recv_acctnumber;
	private String	recv_name;
	private String	tran_ref_ben;
	private String	payee_name;
	private Date	extraction_date;
	private String	transaction_indicator;
	private String	free_text1;
	private String	free_text2;
	private String	free_text3;
	private String	dtt_contra_acid;
	private String eft_cif_id;
	private String eft_foracid;
	private BigDecimal eft_amount;
	private String eft_acid;
	
	public String getReversal_flg() {
		return reversal_flg;
	}
	public void setReversal_flg(String reversal_flg) {
		this.reversal_flg = reversal_flg;
	}
	public Date getRev_tran_date() {
		return rev_tran_date;
	}
	public void setRev_tran_date(Date rev_tran_date) {
		this.rev_tran_date = rev_tran_date;
	}
	public String getRev_tran_id() {
		return rev_tran_id;
	}
	public void setRev_tran_id(String rev_tran_id) {
		this.rev_tran_id = rev_tran_id;
	}
	public String getRev_part_tran_srl_num() {
		return rev_part_tran_srl_num;
	}
	public void setRev_part_tran_srl_num(String rev_part_tran_srl_num) {
		this.rev_part_tran_srl_num = rev_part_tran_srl_num;
	}
	public String getRecv_bankbranchcode() {
		return recv_bankbranchcode;
	}
	public void setRecv_bankbranchcode(String recv_bankbranchcode) {
		this.recv_bankbranchcode = recv_bankbranchcode;
	}
	public String getRecv_acctnumber() {
		return recv_acctnumber;
	}
	public void setRecv_acctnumber(String recv_acctnumber) {
		this.recv_acctnumber = recv_acctnumber;
	}
	public String getRecv_name() {
		return recv_name;
	}
	public void setRecv_name(String recv_name) {
		this.recv_name = recv_name;
	}
	public String getTran_ref_ben() {
		return tran_ref_ben;
	}
	public void setTran_ref_ben(String tran_ref_ben) {
		this.tran_ref_ben = tran_ref_ben;
	}
	public String getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}
	public Date getExtraction_date() {
		return extraction_date;
	}
	public void setExtraction_date(Date extraction_date) {
		this.extraction_date = extraction_date;
	}
	public String getTransaction_indicator() {
		return transaction_indicator;
	}
	public void setTransaction_indicator(String transaction_indicator) {
		this.transaction_indicator = transaction_indicator;
	}
	public String getFree_text1() {
		return free_text1;
	}
	public void setFree_text1(String free_text1) {
		this.free_text1 = free_text1;
	}
	public String getFree_text2() {
		return free_text2;
	}
	public void setFree_text2(String free_text2) {
		this.free_text2 = free_text2;
	}
	public String getFree_text3() {
		return free_text3;
	}
	public void setFree_text3(String free_text3) {
		this.free_text3 = free_text3;
	}
	public String getDtt_contra_acid() {
		return dtt_contra_acid;
	}
	public void setDtt_contra_acid(String dtt_contra_acid) {
		this.dtt_contra_acid = dtt_contra_acid;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getGl_sub_head_code() {
		return gl_sub_head_code;
	}
	public void setGl_sub_head_code(String gl_sub_head_code) {
		this.gl_sub_head_code = gl_sub_head_code;
	}

	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
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
	public String getPart_tran_srl_num() {
		return part_tran_srl_num;
	}
	public void setPart_tran_srl_num(String part_tran_srl_num) {
		this.part_tran_srl_num = part_tran_srl_num;
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
	public String getTran_particular() {
		return tran_particular;
	}
	public void setTran_particular(String tran_particular) {
		this.tran_particular = tran_particular;
	}
	public String getTran_rmks() {
		return tran_rmks;
	}
	public void setTran_rmks(String tran_rmks) {
		this.tran_rmks = tran_rmks;
	}
	public String getRef_num() {
		return ref_num;
	}
	public void setRef_num(String ref_num) {
		this.ref_num = ref_num;
	}
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getTr_status() {
		return tr_status;
	}
	public void setTr_status(String tr_status) {
		this.tr_status = tr_status;
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
	public Date getPstd_date() {
		return pstd_date;
	}
	public void setPstd_date(Date pstd_date) {
		this.pstd_date = pstd_date;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public Date getRcre_time() {
		return rcre_time;
	}
	public void setRcre_time(Date rcre_time) {
		this.rcre_time = rcre_time;
	}
	public Date getLchg_time() {
		return lchg_time;
	}
	public void setLchg_time(Date lchg_time) {
		this.lchg_time = lchg_time;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
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
	public BigDecimal getFx_tran_amt() {
		return fx_tran_amt;
	}
	public void setFx_tran_amt(BigDecimal fx_tran_amt) {
		this.fx_tran_amt = fx_tran_amt;
	}
	public String getTran_particular_code() {
		return tran_particular_code;
	}
	public void setTran_particular_code(String tran_particular_code) {
		this.tran_particular_code = tran_particular_code;
	}
	public String getTran_free_code1() {
		return tran_free_code1;
	}
	public void setTran_free_code1(String tran_free_code1) {
		this.tran_free_code1 = tran_free_code1;
	}
	public String getTran_free_code2() {
		return tran_free_code2;
	}
	public void setTran_free_code2(String tran_free_code2) {
		this.tran_free_code2 = tran_free_code2;
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
	public String getSchm_code() {
		return schm_code;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	
	public String getDtt_flow_code() {
		return dtt_flow_code;
	}
	public void setDtt_flow_code(String dtt_flow_code) {
		this.dtt_flow_code = dtt_flow_code;
	}
	public String getLoan_flow_code() {
		return loan_flow_code;
	}
	public void setLoan_flow_code(String loan_flow_code) {
		this.loan_flow_code = loan_flow_code;
	}
	public String getCtran_id() {
		return ctran_id;
	}
	public void setCtran_id(String ctran_id) {
		this.ctran_id = ctran_id;
	}
	public String getEft_cif_id() {
		return eft_cif_id;
	}
	public void setEft_cif_id(String eft_cif_id) {
		this.eft_cif_id = eft_cif_id;
	}
	public String getEft_foracid() {
		return eft_foracid;
	}
	public void setEft_foracid(String eft_foracid) {
		this.eft_foracid = eft_foracid;
	}
	public BigDecimal getEft_amount() {
		return eft_amount;
	}
	public void setEft_amount(BigDecimal eft_amount) {
		this.eft_amount = eft_amount;
	}
	public String getEft_acid() {
		return eft_acid;
	}
	public void setEft_acid(String eft_acid) {
		this.eft_acid = eft_acid;
	}
	public TRAN_MASTER(TransactionMasterEmbeddedId transactionmasterembeddedid, String sol_id, String gl_sub_head_code,
			ACCT_MASTER aCCT_MASTER, Date tran_date, String tran_type, String tran_sub_type, String part_tran_srl_num,
			String tran_crncy_code, BigDecimal tran_amt, String tran_particular, String tran_rmks, String ref_num,
			String module_id, String tr_status, String entry_user_id, String pstd_user_id, String vfd_user_id,
			Date entry_date, Date vfd_date, String pstd_flg, Date pstd_date, String del_flg, Date rcre_time,
			Date lchg_time, String cust_id, String rate_code, BigDecimal rate, String crncy_code,
			BigDecimal fx_tran_amt, String tran_particular_code, String tran_free_code1, String tran_free_code2,
			Date reversal_date, Date reversal_value_date, String schm_code, String dtt_flow_code, String loan_flow_code,
			String ctran_id, String reversal_flg, Date rev_tran_date, String rev_tran_id, String rev_part_tran_srl_num,
			String recv_bankbranchcode, String recv_acctnumber, String recv_name, String tran_ref_ben,
			String payee_name, Date extraction_date, String transaction_indicator, String free_text1, String free_text2,
			String free_text3, String dtt_contra_acid, String eft_cif_id, String eft_foracid, BigDecimal eft_amount,
			String eft_acid) {
		super();
		this.transactionmasterembeddedid = transactionmasterembeddedid;
		this.sol_id = sol_id;
		this.gl_sub_head_code = gl_sub_head_code;
		this.aCCT_MASTER = aCCT_MASTER;
		this.tran_date = tran_date;
		this.tran_type = tran_type;
		this.tran_sub_type = tran_sub_type;
		this.part_tran_srl_num = part_tran_srl_num;
		this.tran_crncy_code = tran_crncy_code;
		this.tran_amt = tran_amt;
		this.tran_particular = tran_particular;
		this.tran_rmks = tran_rmks;
		this.ref_num = ref_num;
		this.module_id = module_id;
		this.tr_status = tr_status;
		this.entry_user_id = entry_user_id;
		this.pstd_user_id = pstd_user_id;
		this.vfd_user_id = vfd_user_id;
		this.entry_date = entry_date;
		this.vfd_date = vfd_date;
		this.pstd_flg = pstd_flg;
		this.pstd_date = pstd_date;
		this.del_flg = del_flg;
		this.rcre_time = rcre_time;
		this.lchg_time = lchg_time;
		this.cust_id = cust_id;
		this.rate_code = rate_code;
		this.rate = rate;
		this.crncy_code = crncy_code;
		this.fx_tran_amt = fx_tran_amt;
		this.tran_particular_code = tran_particular_code;
		this.tran_free_code1 = tran_free_code1;
		this.tran_free_code2 = tran_free_code2;
		this.reversal_date = reversal_date;
		this.reversal_value_date = reversal_value_date;
		this.schm_code = schm_code;
		this.dtt_flow_code = dtt_flow_code;
		this.loan_flow_code = loan_flow_code;
		this.ctran_id = ctran_id;
		this.reversal_flg = reversal_flg;
		this.rev_tran_date = rev_tran_date;
		this.rev_tran_id = rev_tran_id;
		this.rev_part_tran_srl_num = rev_part_tran_srl_num;
		this.recv_bankbranchcode = recv_bankbranchcode;
		this.recv_acctnumber = recv_acctnumber;
		this.recv_name = recv_name;
		this.tran_ref_ben = tran_ref_ben;
		this.payee_name = payee_name;
		this.extraction_date = extraction_date;
		this.transaction_indicator = transaction_indicator;
		this.free_text1 = free_text1;
		this.free_text2 = free_text2;
		this.free_text3 = free_text3;
		this.dtt_contra_acid = dtt_contra_acid;
		this.eft_cif_id = eft_cif_id;
		this.eft_foracid = eft_foracid;
		this.eft_amount = eft_amount;
		this.eft_acid = eft_acid;
	}
	public TRAN_MASTER() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}

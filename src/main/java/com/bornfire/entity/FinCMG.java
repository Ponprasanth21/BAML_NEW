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
@Table(name = "CUST_MAST_GEN_TABLE")
public class FinCMG implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String	cust_id;
	private String	cust_minor_flg;
	private String	cust_sex;
	private String	cust_card_hold_flg;
	private String	share_holder_flg;
	private String	ps_freq_type;
	private String	ps_freq_week_num;
	private String	ps_freq_hldy_stat;
	private String	purge_allowed_flg;
	private String	is_swift_code_of_bank;
	private String	cust_chrg_history_flg;
	private String	party_flg;
	private String	combined_stmt_reqd;
	private String	loans_stmt_type;
	private String	td_stmt_type;
	private String	despatch_mode;
	private String	address_type;
	private String	allow_sweeps;
	private String	cust_creation_mode;
	private String	tr_cpty_flg;
	private String	income_freq;
	private String	entity_cre_flg;
	private String	del_flg;
	private String	cust_nre_flg;
	private Date	cust_stat_chg_date;
	private Date	cust_rating_date;
	private Date	cust_advn_as_on_date;
	private Date	lchg_time;
	private Date	rcre_time;
	private Date	cust_asset_class_date;
	private Date	cust_membership_date;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	date_of_birth;
	private Date	psprt_issu_date;
	private Date	psprt_exp_date;
	private Date	cust_pref_till_date;
	private Date	tds_exmpt_end_date;
	private Date	tds_exmpt_submit_date;
	private Date	cs_last_printed_date;
	private Date	cs_next_due_date;
	@Temporal(TemporalType.DATE)
	private Date	cust_opn_date;
	private Date	cust_first_acct_date;
	private Date	cust_assets_as_on_date;
	private BigDecimal	ps_freq_week_day;
	private BigDecimal	ps_freq_start_dd;
	private BigDecimal	cust_fin_year_end_mnth;
	private BigDecimal	cust_dep_in_othr_bank;
	private BigDecimal	cust_othr_lim;
	private BigDecimal	cust_net_worth;
	private BigDecimal	cust_investmnts;
	private BigDecimal	cust_business_assets;
	private BigDecimal	cust_prop_assets;
	private BigDecimal	income_to;
	private BigDecimal	income_from;
	private BigDecimal	cust_floor_limit_tds;
	private BigDecimal	value_share_held;
	private BigDecimal	cust_tot_fund_base;
	private BigDecimal	offline_cum_debit_limit;
	private BigDecimal	cust_tot_non_fund_base;
	private BigDecimal	cust_salary;
	private BigDecimal	tot_tod_alwd_times;
	private BigDecimal	ts_cnt;
	private BigDecimal	num_of_accounts;
	private BigDecimal	tot_mod_times;
	private BigDecimal	total_shares_held;
	
	private String	cust_emp_id;
	private String	cust_perm_pin_code;
	private String	cust_short_name;
	private String	lang_code;
	private String	cust_perm_sub_pin_code;
	private String	cust_emp_sub_pin_code;
	private String	cust_comu_pin_code;
	private String	cust_comu_sub_pin_code;
	private String	cust_emp_pin_code;
	private String	cust_level_charges_acct;
	private String	dsa_id;
	private String	cust_swift_code;
	private String	psprt_num;
	private String	employer_id;
	private String	cust_comu_phone_num_1;
	private String	cust_comu_phone_num_2;
	private String	cust_comu_telex_num;
	private String	lchg_user_id;
	private String	rcre_user_id;
	private String	cust_pager_no;
	private String	cust_fax_no;
	private String	free_text_1;
	private String	free_text_2;
	private String	free_text_3;
	private String	free_text_4;
	private String	free_text_5;
	private String	free_text_6;
	private String	free_text_7;
	private String	free_text_8;
	private String	free_text_9;
	private String	free_text_10;
	private String	free_text_11;
	private String	free_text_12;
	private String	free_text_13;
	private String	free_text_14;
	private String	free_text_15;
	private String	acct_mgr_user_id;
	private String	cust_emp_phone_num_1;
	private String	cust_emp_phone_num_2;
	private String	cust_emp_telex_num;
	private String	cust_perm_fax_num;
	private String	cust_emp_fax_num;
	private String	cust_perm_phone_num_2;
	private String	pref_code;
	private String	tr_cpty_mnemonic;
	private String	free_text_16;
	private String	free_text_17;
	private String	free_text_18;
	private String	free_text_19;
	private String	free_text_20;
	private String	cust_perm_phone_num;
	private String	cust_perm_telex_num;
	private String	chrg_dr_foracid;
	private String	nat_id_card_num;
	private String	member_type;
	private String	purge_text;
	private String	cust_mgr_opin;
	private String	crm_cust_id;
	private String	pan_gir_num;
	private String	psprt_det;
	private String	crncy_code;
	private String	income_crncy;
	private String	tds_exmpt_ref_num;
	private String	cust_introd_name;
	private String	cust_emp_addr1;
	private String	cust_comu_addr2;
	private String	cust_perm_addr1;
	private String	cust_perm_addr2;
	private String	cust_comu_addr1;
	private String	cust_emp_addr2;
	private String	cust_stat_code;
	private String	cust_const;
	private String	cust_hlth_code;
	private String	cust_asset_class;
	private String	cust_caste_code;
	private String	tds_tbl_code;
	private String	cust_marital_status;
	private String	chrg_level_code;
	private String	nat_lang_title_code;
	private String	comb_stmt_chrg_code;
	private String	cust_emp_city_code;
	private String	cust_emp_state_code;
	private String	cust_emp_cntry_code;
	private String	income_src;
	private String	cust_title_code;
	private String	cust_perm_city_code;
	private String	cust_perm_state_code;
	private String	cust_perm_cntry_code;
	private String	cust_grp;
	private String	cust_comu_city_code;
	private String	cust_comu_state_code;
	private String	cust_comu_cntry_code;
	private String	cust_occp_code;
	private String	cust_commu_code;
	private String	cust_sector_code;
	private String	cust_sub_sector_code;
	private String	cust_rating_code;
	private String	introd_title_code;
	private String	cust_introd_stat_code;
	private String	cust_type_code;
	private String	cust_free_text;
	private String	cust_src_of_income;
	private String	cust_perm_email_id;
	private String	cust_emp_email_id;
	private String	email_id;
	private String	cust_othr_bank_code;
	private String	chrg_dr_sol_id;
	private String	primary_sol_id;
	private String	cust_name;
	private String	cust_last_name;
	private String	native_lang_name;
	private String	cust_first_name;
	private String	tds_exmpt_rmks;
	private String	cust_middle_name;
	
	private String	cust_introd_cust_id;
	private String	tds_cust_id;
	private String	cust_employee_no;
	public FinCMG() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FinCMG(String cust_id, String entity_cre_flg, String del_flg, String cust_title_code, String cust_name,
			String cust_perm_phone_num, String cust_sex, String cust_grp, Date cust_first_acct_date,
			BigDecimal num_of_accounts, String nat_id_card_num, Date date_of_birth, String psprt_num,
			Date psprt_issu_date, String psprt_det, Date psprt_exp_date, String cust_marital_status, String cust_fax_no,
			String crncy_code, String email_id, String cust_nre_flg,Date cs_next_due_date,Date cust_opn_date ) {
		super();
		this.cust_id = cust_id;
		this.entity_cre_flg = entity_cre_flg;
		this.del_flg = del_flg;
		this.cust_title_code = cust_title_code;
		this.cust_name = cust_name;
		this.cust_perm_phone_num = cust_perm_phone_num;
		this.cust_sex = cust_sex;
		this.cust_grp = cust_grp;
		this.cust_first_acct_date = cust_first_acct_date;
		this.num_of_accounts = num_of_accounts;
		this.nat_id_card_num = nat_id_card_num;
		this.date_of_birth = date_of_birth;
		this.psprt_num = psprt_num;
		this.psprt_issu_date = psprt_issu_date;
		this.psprt_det = psprt_det;
		this.psprt_exp_date = psprt_exp_date;
		this.cust_marital_status = cust_marital_status;
		this.cust_fax_no = cust_fax_no;
		this.crncy_code = crncy_code;
		this.email_id = email_id;
		this.cust_nre_flg = cust_nre_flg;
		this.cs_next_due_date = cs_next_due_date;
		this.cust_opn_date = cust_opn_date;
	}

	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_minor_flg() {
		return cust_minor_flg;
	}
	public void setCust_minor_flg(String cust_minor_flg) {
		this.cust_minor_flg = cust_minor_flg;
	}
	public String getCust_sex() {
		return cust_sex;
	}
	public void setCust_sex(String cust_sex) {
		this.cust_sex = cust_sex;
	}
	public String getCust_card_hold_flg() {
		return cust_card_hold_flg;
	}
	public void setCust_card_hold_flg(String cust_card_hold_flg) {
		this.cust_card_hold_flg = cust_card_hold_flg;
	}
	public String getShare_holder_flg() {
		return share_holder_flg;
	}
	public void setShare_holder_flg(String share_holder_flg) {
		this.share_holder_flg = share_holder_flg;
	}
	public String getPs_freq_type() {
		return ps_freq_type;
	}
	public void setPs_freq_type(String ps_freq_type) {
		this.ps_freq_type = ps_freq_type;
	}
	public String getPs_freq_week_num() {
		return ps_freq_week_num;
	}
	public void setPs_freq_week_num(String ps_freq_week_num) {
		this.ps_freq_week_num = ps_freq_week_num;
	}
	public String getPs_freq_hldy_stat() {
		return ps_freq_hldy_stat;
	}
	public void setPs_freq_hldy_stat(String ps_freq_hldy_stat) {
		this.ps_freq_hldy_stat = ps_freq_hldy_stat;
	}
	public String getPurge_allowed_flg() {
		return purge_allowed_flg;
	}
	public void setPurge_allowed_flg(String purge_allowed_flg) {
		this.purge_allowed_flg = purge_allowed_flg;
	}
	public String getIs_swift_code_of_bank() {
		return is_swift_code_of_bank;
	}
	public void setIs_swift_code_of_bank(String is_swift_code_of_bank) {
		this.is_swift_code_of_bank = is_swift_code_of_bank;
	}
	public String getCust_chrg_history_flg() {
		return cust_chrg_history_flg;
	}
	public void setCust_chrg_history_flg(String cust_chrg_history_flg) {
		this.cust_chrg_history_flg = cust_chrg_history_flg;
	}
	public String getParty_flg() {
		return party_flg;
	}
	public void setParty_flg(String party_flg) {
		this.party_flg = party_flg;
	}
	public String getCombined_stmt_reqd() {
		return combined_stmt_reqd;
	}
	public void setCombined_stmt_reqd(String combined_stmt_reqd) {
		this.combined_stmt_reqd = combined_stmt_reqd;
	}
	public String getLoans_stmt_type() {
		return loans_stmt_type;
	}
	public void setLoans_stmt_type(String loans_stmt_type) {
		this.loans_stmt_type = loans_stmt_type;
	}
	public String getTd_stmt_type() {
		return td_stmt_type;
	}
	public void setTd_stmt_type(String td_stmt_type) {
		this.td_stmt_type = td_stmt_type;
	}
	public String getDespatch_mode() {
		return despatch_mode;
	}
	public void setDespatch_mode(String despatch_mode) {
		this.despatch_mode = despatch_mode;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public String getAllow_sweeps() {
		return allow_sweeps;
	}
	public void setAllow_sweeps(String allow_sweeps) {
		this.allow_sweeps = allow_sweeps;
	}
	public String getCust_creation_mode() {
		return cust_creation_mode;
	}
	public void setCust_creation_mode(String cust_creation_mode) {
		this.cust_creation_mode = cust_creation_mode;
	}
	public String getTr_cpty_flg() {
		return tr_cpty_flg;
	}
	public void setTr_cpty_flg(String tr_cpty_flg) {
		this.tr_cpty_flg = tr_cpty_flg;
	}
	public String getIncome_freq() {
		return income_freq;
	}
	public void setIncome_freq(String income_freq) {
		this.income_freq = income_freq;
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
	public String getCust_nre_flg() {
		return cust_nre_flg;
	}
	public void setCust_nre_flg(String cust_nre_flg) {
		this.cust_nre_flg = cust_nre_flg;
	}
	public Date getCust_stat_chg_date() {
		return cust_stat_chg_date;
	}
	public void setCust_stat_chg_date(Date cust_stat_chg_date) {
		this.cust_stat_chg_date = cust_stat_chg_date;
	}
	public Date getCust_rating_date() {
		return cust_rating_date;
	}
	public void setCust_rating_date(Date cust_rating_date) {
		this.cust_rating_date = cust_rating_date;
	}
	public Date getCust_advn_as_on_date() {
		return cust_advn_as_on_date;
	}
	public void setCust_advn_as_on_date(Date cust_advn_as_on_date) {
		this.cust_advn_as_on_date = cust_advn_as_on_date;
	}
	public Date getLchg_time() {
		return lchg_time;
	}
	public void setLchg_time(Date lchg_time) {
		this.lchg_time = lchg_time;
	}
	public Date getRcre_time() {
		return rcre_time;
	}
	public void setRcre_time(Date rcre_time) {
		this.rcre_time = rcre_time;
	}
	public Date getCust_asset_class_date() {
		return cust_asset_class_date;
	}
	public void setCust_asset_class_date(Date cust_asset_class_date) {
		this.cust_asset_class_date = cust_asset_class_date;
	}
	public Date getCust_membership_date() {
		return cust_membership_date;
	}
	public void setCust_membership_date(Date cust_membership_date) {
		this.cust_membership_date = cust_membership_date;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getPsprt_issu_date() {
		return psprt_issu_date;
	}
	public void setPsprt_issu_date(Date psprt_issu_date) {
		this.psprt_issu_date = psprt_issu_date;
	}
	public Date getPsprt_exp_date() {
		return psprt_exp_date;
	}
	public void setPsprt_exp_date(Date psprt_exp_date) {
		this.psprt_exp_date = psprt_exp_date;
	}
	public Date getCust_pref_till_date() {
		return cust_pref_till_date;
	}
	public void setCust_pref_till_date(Date cust_pref_till_date) {
		this.cust_pref_till_date = cust_pref_till_date;
	}
	public Date getTds_exmpt_end_date() {
		return tds_exmpt_end_date;
	}
	public void setTds_exmpt_end_date(Date tds_exmpt_end_date) {
		this.tds_exmpt_end_date = tds_exmpt_end_date;
	}
	public Date getTds_exmpt_submit_date() {
		return tds_exmpt_submit_date;
	}
	public void setTds_exmpt_submit_date(Date tds_exmpt_submit_date) {
		this.tds_exmpt_submit_date = tds_exmpt_submit_date;
	}
	public Date getCs_last_printed_date() {
		return cs_last_printed_date;
	}
	public void setCs_last_printed_date(Date cs_last_printed_date) {
		this.cs_last_printed_date = cs_last_printed_date;
	}
	public Date getCs_next_due_date() {
		return cs_next_due_date;
	}
	public void setCs_next_due_date(Date cs_next_due_date) {
		this.cs_next_due_date = cs_next_due_date;
	}
	public Date getCust_opn_date() {
		return cust_opn_date;
	}
	public void setCust_opn_date(Date cust_opn_date) {
		this.cust_opn_date = cust_opn_date;
	}
	public Date getCust_first_acct_date() {
		return cust_first_acct_date;
	}
	public void setCust_first_acct_date(Date cust_first_acct_date) {
		this.cust_first_acct_date = cust_first_acct_date;
	}
	public Date getCust_assets_as_on_date() {
		return cust_assets_as_on_date;
	}
	public void setCust_assets_as_on_date(Date cust_assets_as_on_date) {
		this.cust_assets_as_on_date = cust_assets_as_on_date;
	}
	public BigDecimal getPs_freq_week_day() {
		return ps_freq_week_day;
	}
	public void setPs_freq_week_day(BigDecimal ps_freq_week_day) {
		this.ps_freq_week_day = ps_freq_week_day;
	}
	public BigDecimal getPs_freq_start_dd() {
		return ps_freq_start_dd;
	}
	public void setPs_freq_start_dd(BigDecimal ps_freq_start_dd) {
		this.ps_freq_start_dd = ps_freq_start_dd;
	}
	public BigDecimal getCust_fin_year_end_mnth() {
		return cust_fin_year_end_mnth;
	}
	public void setCust_fin_year_end_mnth(BigDecimal cust_fin_year_end_mnth) {
		this.cust_fin_year_end_mnth = cust_fin_year_end_mnth;
	}
	public BigDecimal getCust_dep_in_othr_bank() {
		return cust_dep_in_othr_bank;
	}
	public void setCust_dep_in_othr_bank(BigDecimal cust_dep_in_othr_bank) {
		this.cust_dep_in_othr_bank = cust_dep_in_othr_bank;
	}
	public BigDecimal getCust_othr_lim() {
		return cust_othr_lim;
	}
	public void setCust_othr_lim(BigDecimal cust_othr_lim) {
		this.cust_othr_lim = cust_othr_lim;
	}
	public BigDecimal getCust_net_worth() {
		return cust_net_worth;
	}
	public void setCust_net_worth(BigDecimal cust_net_worth) {
		this.cust_net_worth = cust_net_worth;
	}
	public BigDecimal getCust_investmnts() {
		return cust_investmnts;
	}
	public void setCust_investmnts(BigDecimal cust_investmnts) {
		this.cust_investmnts = cust_investmnts;
	}
	public BigDecimal getCust_business_assets() {
		return cust_business_assets;
	}
	public void setCust_business_assets(BigDecimal cust_business_assets) {
		this.cust_business_assets = cust_business_assets;
	}
	public BigDecimal getCust_prop_assets() {
		return cust_prop_assets;
	}
	public void setCust_prop_assets(BigDecimal cust_prop_assets) {
		this.cust_prop_assets = cust_prop_assets;
	}
	public BigDecimal getIncome_to() {
		return income_to;
	}
	public void setIncome_to(BigDecimal income_to) {
		this.income_to = income_to;
	}
	public BigDecimal getIncome_from() {
		return income_from;
	}
	public void setIncome_from(BigDecimal income_from) {
		this.income_from = income_from;
	}
	public BigDecimal getCust_floor_limit_tds() {
		return cust_floor_limit_tds;
	}
	public void setCust_floor_limit_tds(BigDecimal cust_floor_limit_tds) {
		this.cust_floor_limit_tds = cust_floor_limit_tds;
	}
	public BigDecimal getValue_share_held() {
		return value_share_held;
	}
	public void setValue_share_held(BigDecimal value_share_held) {
		this.value_share_held = value_share_held;
	}
	public BigDecimal getCust_tot_fund_base() {
		return cust_tot_fund_base;
	}
	public void setCust_tot_fund_base(BigDecimal cust_tot_fund_base) {
		this.cust_tot_fund_base = cust_tot_fund_base;
	}
	public BigDecimal getOffline_cum_debit_limit() {
		return offline_cum_debit_limit;
	}
	public void setOffline_cum_debit_limit(BigDecimal offline_cum_debit_limit) {
		this.offline_cum_debit_limit = offline_cum_debit_limit;
	}
	public BigDecimal getCust_tot_non_fund_base() {
		return cust_tot_non_fund_base;
	}
	public void setCust_tot_non_fund_base(BigDecimal cust_tot_non_fund_base) {
		this.cust_tot_non_fund_base = cust_tot_non_fund_base;
	}
	public BigDecimal getCust_salary() {
		return cust_salary;
	}
	public void setCust_salary(BigDecimal cust_salary) {
		this.cust_salary = cust_salary;
	}
	public BigDecimal getTot_tod_alwd_times() {
		return tot_tod_alwd_times;
	}
	public void setTot_tod_alwd_times(BigDecimal tot_tod_alwd_times) {
		this.tot_tod_alwd_times = tot_tod_alwd_times;
	}
	public BigDecimal getTs_cnt() {
		return ts_cnt;
	}
	public void setTs_cnt(BigDecimal ts_cnt) {
		this.ts_cnt = ts_cnt;
	}
	public BigDecimal getNum_of_accounts() {
		return num_of_accounts;
	}
	public void setNum_of_accounts(BigDecimal num_of_accounts) {
		this.num_of_accounts = num_of_accounts;
	}
	public BigDecimal getTot_mod_times() {
		return tot_mod_times;
	}
	public void setTot_mod_times(BigDecimal tot_mod_times) {
		this.tot_mod_times = tot_mod_times;
	}
	public BigDecimal getTotal_shares_held() {
		return total_shares_held;
	}
	public void setTotal_shares_held(BigDecimal total_shares_held) {
		this.total_shares_held = total_shares_held;
	}
	public String getCust_emp_id() {
		return cust_emp_id;
	}
	public void setCust_emp_id(String cust_emp_id) {
		this.cust_emp_id = cust_emp_id;
	}
	public String getCust_perm_pin_code() {
		return cust_perm_pin_code;
	}
	public void setCust_perm_pin_code(String cust_perm_pin_code) {
		this.cust_perm_pin_code = cust_perm_pin_code;
	}
	public String getCust_short_name() {
		return cust_short_name;
	}
	public void setCust_short_name(String cust_short_name) {
		this.cust_short_name = cust_short_name;
	}
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	public String getCust_perm_sub_pin_code() {
		return cust_perm_sub_pin_code;
	}
	public void setCust_perm_sub_pin_code(String cust_perm_sub_pin_code) {
		this.cust_perm_sub_pin_code = cust_perm_sub_pin_code;
	}
	public String getCust_emp_sub_pin_code() {
		return cust_emp_sub_pin_code;
	}
	public void setCust_emp_sub_pin_code(String cust_emp_sub_pin_code) {
		this.cust_emp_sub_pin_code = cust_emp_sub_pin_code;
	}
	public String getCust_comu_pin_code() {
		return cust_comu_pin_code;
	}
	public void setCust_comu_pin_code(String cust_comu_pin_code) {
		this.cust_comu_pin_code = cust_comu_pin_code;
	}
	public String getCust_comu_sub_pin_code() {
		return cust_comu_sub_pin_code;
	}
	public void setCust_comu_sub_pin_code(String cust_comu_sub_pin_code) {
		this.cust_comu_sub_pin_code = cust_comu_sub_pin_code;
	}
	public String getCust_emp_pin_code() {
		return cust_emp_pin_code;
	}
	public void setCust_emp_pin_code(String cust_emp_pin_code) {
		this.cust_emp_pin_code = cust_emp_pin_code;
	}
	public String getCust_level_charges_acct() {
		return cust_level_charges_acct;
	}
	public void setCust_level_charges_acct(String cust_level_charges_acct) {
		this.cust_level_charges_acct = cust_level_charges_acct;
	}
	public String getDsa_id() {
		return dsa_id;
	}
	public void setDsa_id(String dsa_id) {
		this.dsa_id = dsa_id;
	}
	public String getCust_swift_code() {
		return cust_swift_code;
	}
	public void setCust_swift_code(String cust_swift_code) {
		this.cust_swift_code = cust_swift_code;
	}
	public String getPsprt_num() {
		return psprt_num;
	}
	public void setPsprt_num(String psprt_num) {
		this.psprt_num = psprt_num;
	}
	public String getEmployer_id() {
		return employer_id;
	}
	public void setEmployer_id(String employer_id) {
		this.employer_id = employer_id;
	}
	public String getCust_comu_phone_num_1() {
		return cust_comu_phone_num_1;
	}
	public void setCust_comu_phone_num_1(String cust_comu_phone_num_1) {
		this.cust_comu_phone_num_1 = cust_comu_phone_num_1;
	}
	public String getCust_comu_phone_num_2() {
		return cust_comu_phone_num_2;
	}
	public void setCust_comu_phone_num_2(String cust_comu_phone_num_2) {
		this.cust_comu_phone_num_2 = cust_comu_phone_num_2;
	}
	public String getCust_comu_telex_num() {
		return cust_comu_telex_num;
	}
	public void setCust_comu_telex_num(String cust_comu_telex_num) {
		this.cust_comu_telex_num = cust_comu_telex_num;
	}
	public String getLchg_user_id() {
		return lchg_user_id;
	}
	public void setLchg_user_id(String lchg_user_id) {
		this.lchg_user_id = lchg_user_id;
	}
	public String getRcre_user_id() {
		return rcre_user_id;
	}
	public void setRcre_user_id(String rcre_user_id) {
		this.rcre_user_id = rcre_user_id;
	}
	public String getCust_pager_no() {
		return cust_pager_no;
	}
	public void setCust_pager_no(String cust_pager_no) {
		this.cust_pager_no = cust_pager_no;
	}
	public String getCust_fax_no() {
		return cust_fax_no;
	}
	public void setCust_fax_no(String cust_fax_no) {
		this.cust_fax_no = cust_fax_no;
	}
	public String getFree_text_1() {
		return free_text_1;
	}
	public void setFree_text_1(String free_text_1) {
		this.free_text_1 = free_text_1;
	}
	public String getFree_text_2() {
		return free_text_2;
	}
	public void setFree_text_2(String free_text_2) {
		this.free_text_2 = free_text_2;
	}
	public String getFree_text_3() {
		return free_text_3;
	}
	public void setFree_text_3(String free_text_3) {
		this.free_text_3 = free_text_3;
	}
	public String getFree_text_4() {
		return free_text_4;
	}
	public void setFree_text_4(String free_text_4) {
		this.free_text_4 = free_text_4;
	}
	public String getFree_text_5() {
		return free_text_5;
	}
	public void setFree_text_5(String free_text_5) {
		this.free_text_5 = free_text_5;
	}
	public String getFree_text_6() {
		return free_text_6;
	}
	public void setFree_text_6(String free_text_6) {
		this.free_text_6 = free_text_6;
	}
	public String getFree_text_7() {
		return free_text_7;
	}
	public void setFree_text_7(String free_text_7) {
		this.free_text_7 = free_text_7;
	}
	public String getFree_text_8() {
		return free_text_8;
	}
	public void setFree_text_8(String free_text_8) {
		this.free_text_8 = free_text_8;
	}
	public String getFree_text_9() {
		return free_text_9;
	}
	public void setFree_text_9(String free_text_9) {
		this.free_text_9 = free_text_9;
	}
	public String getFree_text_10() {
		return free_text_10;
	}
	public void setFree_text_10(String free_text_10) {
		this.free_text_10 = free_text_10;
	}
	public String getFree_text_11() {
		return free_text_11;
	}
	public void setFree_text_11(String free_text_11) {
		this.free_text_11 = free_text_11;
	}
	public String getFree_text_12() {
		return free_text_12;
	}
	public void setFree_text_12(String free_text_12) {
		this.free_text_12 = free_text_12;
	}
	public String getFree_text_13() {
		return free_text_13;
	}
	public void setFree_text_13(String free_text_13) {
		this.free_text_13 = free_text_13;
	}
	public String getFree_text_14() {
		return free_text_14;
	}
	public void setFree_text_14(String free_text_14) {
		this.free_text_14 = free_text_14;
	}
	public String getFree_text_15() {
		return free_text_15;
	}
	public void setFree_text_15(String free_text_15) {
		this.free_text_15 = free_text_15;
	}
	public String getAcct_mgr_user_id() {
		return acct_mgr_user_id;
	}
	public void setAcct_mgr_user_id(String acct_mgr_user_id) {
		this.acct_mgr_user_id = acct_mgr_user_id;
	}
	public String getCust_emp_phone_num_1() {
		return cust_emp_phone_num_1;
	}
	public void setCust_emp_phone_num_1(String cust_emp_phone_num_1) {
		this.cust_emp_phone_num_1 = cust_emp_phone_num_1;
	}
	public String getCust_emp_phone_num_2() {
		return cust_emp_phone_num_2;
	}
	public void setCust_emp_phone_num_2(String cust_emp_phone_num_2) {
		this.cust_emp_phone_num_2 = cust_emp_phone_num_2;
	}
	public String getCust_emp_telex_num() {
		return cust_emp_telex_num;
	}
	public void setCust_emp_telex_num(String cust_emp_telex_num) {
		this.cust_emp_telex_num = cust_emp_telex_num;
	}
	public String getCust_perm_fax_num() {
		return cust_perm_fax_num;
	}
	public void setCust_perm_fax_num(String cust_perm_fax_num) {
		this.cust_perm_fax_num = cust_perm_fax_num;
	}
	public String getCust_emp_fax_num() {
		return cust_emp_fax_num;
	}
	public void setCust_emp_fax_num(String cust_emp_fax_num) {
		this.cust_emp_fax_num = cust_emp_fax_num;
	}
	public String getCust_perm_phone_num_2() {
		return cust_perm_phone_num_2;
	}
	public void setCust_perm_phone_num_2(String cust_perm_phone_num_2) {
		this.cust_perm_phone_num_2 = cust_perm_phone_num_2;
	}
	public String getPref_code() {
		return pref_code;
	}
	public void setPref_code(String pref_code) {
		this.pref_code = pref_code;
	}
	public String getTr_cpty_mnemonic() {
		return tr_cpty_mnemonic;
	}
	public void setTr_cpty_mnemonic(String tr_cpty_mnemonic) {
		this.tr_cpty_mnemonic = tr_cpty_mnemonic;
	}
	public String getFree_text_16() {
		return free_text_16;
	}
	public void setFree_text_16(String free_text_16) {
		this.free_text_16 = free_text_16;
	}
	public String getFree_text_17() {
		return free_text_17;
	}
	public void setFree_text_17(String free_text_17) {
		this.free_text_17 = free_text_17;
	}
	public String getFree_text_18() {
		return free_text_18;
	}
	public void setFree_text_18(String free_text_18) {
		this.free_text_18 = free_text_18;
	}
	public String getFree_text_19() {
		return free_text_19;
	}
	public void setFree_text_19(String free_text_19) {
		this.free_text_19 = free_text_19;
	}
	public String getFree_text_20() {
		return free_text_20;
	}
	public void setFree_text_20(String free_text_20) {
		this.free_text_20 = free_text_20;
	}
	public String getCust_perm_phone_num() {
		return cust_perm_phone_num;
	}
	public void setCust_perm_phone_num(String cust_perm_phone_num) {
		this.cust_perm_phone_num = cust_perm_phone_num;
	}
	public String getCust_perm_telex_num() {
		return cust_perm_telex_num;
	}
	public void setCust_perm_telex_num(String cust_perm_telex_num) {
		this.cust_perm_telex_num = cust_perm_telex_num;
	}
	public String getChrg_dr_foracid() {
		return chrg_dr_foracid;
	}
	public void setChrg_dr_foracid(String chrg_dr_foracid) {
		this.chrg_dr_foracid = chrg_dr_foracid;
	}
	public String getNat_id_card_num() {
		return nat_id_card_num;
	}
	public void setNat_id_card_num(String nat_id_card_num) {
		this.nat_id_card_num = nat_id_card_num;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public String getPurge_text() {
		return purge_text;
	}
	public void setPurge_text(String purge_text) {
		this.purge_text = purge_text;
	}
	public String getCust_mgr_opin() {
		return cust_mgr_opin;
	}
	public void setCust_mgr_opin(String cust_mgr_opin) {
		this.cust_mgr_opin = cust_mgr_opin;
	}
	public String getCrm_cust_id() {
		return crm_cust_id;
	}
	public void setCrm_cust_id(String crm_cust_id) {
		this.crm_cust_id = crm_cust_id;
	}
	public String getPan_gir_num() {
		return pan_gir_num;
	}
	public void setPan_gir_num(String pan_gir_num) {
		this.pan_gir_num = pan_gir_num;
	}
	public String getPsprt_det() {
		return psprt_det;
	}
	public void setPsprt_det(String psprt_det) {
		this.psprt_det = psprt_det;
	}
	public String getCrncy_code() {
		return crncy_code;
	}
	public void setCrncy_code(String crncy_code) {
		this.crncy_code = crncy_code;
	}
	public String getIncome_crncy() {
		return income_crncy;
	}
	public void setIncome_crncy(String income_crncy) {
		this.income_crncy = income_crncy;
	}
	public String getTds_exmpt_ref_num() {
		return tds_exmpt_ref_num;
	}
	public void setTds_exmpt_ref_num(String tds_exmpt_ref_num) {
		this.tds_exmpt_ref_num = tds_exmpt_ref_num;
	}
	public String getCust_introd_name() {
		return cust_introd_name;
	}
	public void setCust_introd_name(String cust_introd_name) {
		this.cust_introd_name = cust_introd_name;
	}
	public String getCust_emp_addr1() {
		return cust_emp_addr1;
	}
	public void setCust_emp_addr1(String cust_emp_addr1) {
		this.cust_emp_addr1 = cust_emp_addr1;
	}
	public String getCust_comu_addr2() {
		return cust_comu_addr2;
	}
	public void setCust_comu_addr2(String cust_comu_addr2) {
		this.cust_comu_addr2 = cust_comu_addr2;
	}
	public String getCust_perm_addr1() {
		return cust_perm_addr1;
	}
	public void setCust_perm_addr1(String cust_perm_addr1) {
		this.cust_perm_addr1 = cust_perm_addr1;
	}
	public String getCust_perm_addr2() {
		return cust_perm_addr2;
	}
	public void setCust_perm_addr2(String cust_perm_addr2) {
		this.cust_perm_addr2 = cust_perm_addr2;
	}
	public String getCust_comu_addr1() {
		return cust_comu_addr1;
	}
	public void setCust_comu_addr1(String cust_comu_addr1) {
		this.cust_comu_addr1 = cust_comu_addr1;
	}
	public String getCust_emp_addr2() {
		return cust_emp_addr2;
	}
	public void setCust_emp_addr2(String cust_emp_addr2) {
		this.cust_emp_addr2 = cust_emp_addr2;
	}
	public String getCust_stat_code() {
		return cust_stat_code;
	}
	public void setCust_stat_code(String cust_stat_code) {
		this.cust_stat_code = cust_stat_code;
	}
	public String getCust_const() {
		return cust_const;
	}
	public void setCust_const(String cust_const) {
		this.cust_const = cust_const;
	}
	public String getCust_hlth_code() {
		return cust_hlth_code;
	}
	public void setCust_hlth_code(String cust_hlth_code) {
		this.cust_hlth_code = cust_hlth_code;
	}
	public String getCust_asset_class() {
		return cust_asset_class;
	}
	public void setCust_asset_class(String cust_asset_class) {
		this.cust_asset_class = cust_asset_class;
	}
	public String getCust_caste_code() {
		return cust_caste_code;
	}
	public void setCust_caste_code(String cust_caste_code) {
		this.cust_caste_code = cust_caste_code;
	}
	public String getTds_tbl_code() {
		return tds_tbl_code;
	}
	public void setTds_tbl_code(String tds_tbl_code) {
		this.tds_tbl_code = tds_tbl_code;
	}
	public String getCust_marital_status() {
		return cust_marital_status;
	}
	public void setCust_marital_status(String cust_marital_status) {
		this.cust_marital_status = cust_marital_status;
	}
	public String getChrg_level_code() {
		return chrg_level_code;
	}
	public void setChrg_level_code(String chrg_level_code) {
		this.chrg_level_code = chrg_level_code;
	}
	public String getNat_lang_title_code() {
		return nat_lang_title_code;
	}
	public void setNat_lang_title_code(String nat_lang_title_code) {
		this.nat_lang_title_code = nat_lang_title_code;
	}
	public String getComb_stmt_chrg_code() {
		return comb_stmt_chrg_code;
	}
	public void setComb_stmt_chrg_code(String comb_stmt_chrg_code) {
		this.comb_stmt_chrg_code = comb_stmt_chrg_code;
	}
	public String getCust_emp_city_code() {
		return cust_emp_city_code;
	}
	public void setCust_emp_city_code(String cust_emp_city_code) {
		this.cust_emp_city_code = cust_emp_city_code;
	}
	public String getCust_emp_state_code() {
		return cust_emp_state_code;
	}
	public void setCust_emp_state_code(String cust_emp_state_code) {
		this.cust_emp_state_code = cust_emp_state_code;
	}
	public String getCust_emp_cntry_code() {
		return cust_emp_cntry_code;
	}
	public void setCust_emp_cntry_code(String cust_emp_cntry_code) {
		this.cust_emp_cntry_code = cust_emp_cntry_code;
	}
	public String getIncome_src() {
		return income_src;
	}
	public void setIncome_src(String income_src) {
		this.income_src = income_src;
	}
	public String getCust_title_code() {
		return cust_title_code;
	}
	public void setCust_title_code(String cust_title_code) {
		this.cust_title_code = cust_title_code;
	}
	public String getCust_perm_city_code() {
		return cust_perm_city_code;
	}
	public void setCust_perm_city_code(String cust_perm_city_code) {
		this.cust_perm_city_code = cust_perm_city_code;
	}
	public String getCust_perm_state_code() {
		return cust_perm_state_code;
	}
	public void setCust_perm_state_code(String cust_perm_state_code) {
		this.cust_perm_state_code = cust_perm_state_code;
	}
	public String getCust_perm_cntry_code() {
		return cust_perm_cntry_code;
	}
	public void setCust_perm_cntry_code(String cust_perm_cntry_code) {
		this.cust_perm_cntry_code = cust_perm_cntry_code;
	}
	public String getCust_grp() {
		return cust_grp;
	}
	public void setCust_grp(String cust_grp) {
		this.cust_grp = cust_grp;
	}
	public String getCust_comu_city_code() {
		return cust_comu_city_code;
	}
	public void setCust_comu_city_code(String cust_comu_city_code) {
		this.cust_comu_city_code = cust_comu_city_code;
	}
	public String getCust_comu_state_code() {
		return cust_comu_state_code;
	}
	public void setCust_comu_state_code(String cust_comu_state_code) {
		this.cust_comu_state_code = cust_comu_state_code;
	}
	public String getCust_comu_cntry_code() {
		return cust_comu_cntry_code;
	}
	public void setCust_comu_cntry_code(String cust_comu_cntry_code) {
		this.cust_comu_cntry_code = cust_comu_cntry_code;
	}
	public String getCust_occp_code() {
		return cust_occp_code;
	}
	public void setCust_occp_code(String cust_occp_code) {
		this.cust_occp_code = cust_occp_code;
	}
	public String getCust_commu_code() {
		return cust_commu_code;
	}
	public void setCust_commu_code(String cust_commu_code) {
		this.cust_commu_code = cust_commu_code;
	}
	public String getCust_sector_code() {
		return cust_sector_code;
	}
	public void setCust_sector_code(String cust_sector_code) {
		this.cust_sector_code = cust_sector_code;
	}
	public String getCust_sub_sector_code() {
		return cust_sub_sector_code;
	}
	public void setCust_sub_sector_code(String cust_sub_sector_code) {
		this.cust_sub_sector_code = cust_sub_sector_code;
	}
	public String getCust_rating_code() {
		return cust_rating_code;
	}
	public void setCust_rating_code(String cust_rating_code) {
		this.cust_rating_code = cust_rating_code;
	}
	public String getIntrod_title_code() {
		return introd_title_code;
	}
	public void setIntrod_title_code(String introd_title_code) {
		this.introd_title_code = introd_title_code;
	}
	public String getCust_introd_stat_code() {
		return cust_introd_stat_code;
	}
	public void setCust_introd_stat_code(String cust_introd_stat_code) {
		this.cust_introd_stat_code = cust_introd_stat_code;
	}
	public String getCust_type_code() {
		return cust_type_code;
	}
	public void setCust_type_code(String cust_type_code) {
		this.cust_type_code = cust_type_code;
	}
	public String getCust_free_text() {
		return cust_free_text;
	}
	public void setCust_free_text(String cust_free_text) {
		this.cust_free_text = cust_free_text;
	}
	public String getCust_src_of_income() {
		return cust_src_of_income;
	}
	public void setCust_src_of_income(String cust_src_of_income) {
		this.cust_src_of_income = cust_src_of_income;
	}
	public String getCust_perm_email_id() {
		return cust_perm_email_id;
	}
	public void setCust_perm_email_id(String cust_perm_email_id) {
		this.cust_perm_email_id = cust_perm_email_id;
	}
	public String getCust_emp_email_id() {
		return cust_emp_email_id;
	}
	public void setCust_emp_email_id(String cust_emp_email_id) {
		this.cust_emp_email_id = cust_emp_email_id;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getCust_othr_bank_code() {
		return cust_othr_bank_code;
	}
	public void setCust_othr_bank_code(String cust_othr_bank_code) {
		this.cust_othr_bank_code = cust_othr_bank_code;
	}
	public String getChrg_dr_sol_id() {
		return chrg_dr_sol_id;
	}
	public void setChrg_dr_sol_id(String chrg_dr_sol_id) {
		this.chrg_dr_sol_id = chrg_dr_sol_id;
	}
	public String getPrimary_sol_id() {
		return primary_sol_id;
	}
	public void setPrimary_sol_id(String primary_sol_id) {
		this.primary_sol_id = primary_sol_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_last_name() {
		return cust_last_name;
	}
	public void setCust_last_name(String cust_last_name) {
		this.cust_last_name = cust_last_name;
	}
	public String getNative_lang_name() {
		return native_lang_name;
	}
	public void setNative_lang_name(String native_lang_name) {
		this.native_lang_name = native_lang_name;
	}
	public String getCust_first_name() {
		return cust_first_name;
	}
	public void setCust_first_name(String cust_first_name) {
		this.cust_first_name = cust_first_name;
	}
	public String getTds_exmpt_rmks() {
		return tds_exmpt_rmks;
	}
	public void setTds_exmpt_rmks(String tds_exmpt_rmks) {
		this.tds_exmpt_rmks = tds_exmpt_rmks;
	}
	public String getCust_middle_name() {
		return cust_middle_name;
	}
	public void setCust_middle_name(String cust_middle_name) {
		this.cust_middle_name = cust_middle_name;
	}
	public String getCust_introd_cust_id() {
		return cust_introd_cust_id;
	}
	public void setCust_introd_cust_id(String cust_introd_cust_id) {
		this.cust_introd_cust_id = cust_introd_cust_id;
	}
	public String getTds_cust_id() {
		return tds_cust_id;
	}
	public void setTds_cust_id(String tds_cust_id) {
		this.tds_cust_id = tds_cust_id;
	}
	public String getCust_employee_no() {
		return cust_employee_no;
	}
	public void setCust_employee_no(String cust_employee_no) {
		this.cust_employee_no = cust_employee_no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FinCMG(String cust_id, String cust_minor_flg, String cust_sex, String cust_card_hold_flg,
			String share_holder_flg, String ps_freq_type, String ps_freq_week_num, String ps_freq_hldy_stat,
			String purge_allowed_flg, String is_swift_code_of_bank, String cust_chrg_history_flg, String party_flg,
			String combined_stmt_reqd, String loans_stmt_type, String td_stmt_type, String despatch_mode,
			String address_type, String allow_sweeps, String cust_creation_mode, String tr_cpty_flg, String income_freq,
			String entity_cre_flg, String del_flg, String cust_nre_flg, Date cust_stat_chg_date, Date cust_rating_date,
			Date cust_advn_as_on_date, Date lchg_time, Date rcre_time, Date cust_asset_class_date,
			Date cust_membership_date, Date date_of_birth, Date psprt_issu_date, Date psprt_exp_date,
			Date cust_pref_till_date, Date tds_exmpt_end_date, Date tds_exmpt_submit_date, Date cs_last_printed_date,
			Date cs_next_due_date, Date cust_opn_date, Date cust_first_acct_date, Date cust_assets_as_on_date,
			BigDecimal ps_freq_week_day, BigDecimal ps_freq_start_dd, BigDecimal cust_fin_year_end_mnth,
			BigDecimal cust_dep_in_othr_bank, BigDecimal cust_othr_lim, BigDecimal cust_net_worth,
			BigDecimal cust_investmnts, BigDecimal cust_business_assets, BigDecimal cust_prop_assets,
			BigDecimal income_to, BigDecimal income_from, BigDecimal cust_floor_limit_tds, BigDecimal value_share_held,
			BigDecimal cust_tot_fund_base, BigDecimal offline_cum_debit_limit, BigDecimal cust_tot_non_fund_base,
			BigDecimal cust_salary, BigDecimal tot_tod_alwd_times, BigDecimal ts_cnt, BigDecimal num_of_accounts,
			BigDecimal tot_mod_times, BigDecimal total_shares_held, String cust_emp_id, String cust_perm_pin_code,
			String cust_short_name, String lang_code, String cust_perm_sub_pin_code, String cust_emp_sub_pin_code,
			String cust_comu_pin_code, String cust_comu_sub_pin_code, String cust_emp_pin_code,
			String cust_level_charges_acct, String dsa_id, String cust_swift_code, String psprt_num, String employer_id,
			String cust_comu_phone_num_1, String cust_comu_phone_num_2, String cust_comu_telex_num, String lchg_user_id,
			String rcre_user_id, String cust_pager_no, String cust_fax_no, String free_text_1, String free_text_2,
			String free_text_3, String free_text_4, String free_text_5, String free_text_6, String free_text_7,
			String free_text_8, String free_text_9, String free_text_10, String free_text_11, String free_text_12,
			String free_text_13, String free_text_14, String free_text_15, String acct_mgr_user_id,
			String cust_emp_phone_num_1, String cust_emp_phone_num_2, String cust_emp_telex_num,
			String cust_perm_fax_num, String cust_emp_fax_num, String cust_perm_phone_num_2, String pref_code,
			String tr_cpty_mnemonic, String free_text_16, String free_text_17, String free_text_18, String free_text_19,
			String free_text_20, String cust_perm_phone_num, String cust_perm_telex_num, String chrg_dr_foracid,
			String nat_id_card_num, String member_type, String purge_text, String cust_mgr_opin, String crm_cust_id,
			String pan_gir_num, String psprt_det, String crncy_code, String income_crncy, String tds_exmpt_ref_num,
			String cust_introd_name, String cust_emp_addr1, String cust_comu_addr2, String cust_perm_addr1,
			String cust_perm_addr2, String cust_comu_addr1, String cust_emp_addr2, String cust_stat_code,
			String cust_const, String cust_hlth_code, String cust_asset_class, String cust_caste_code,
			String tds_tbl_code, String cust_marital_status, String chrg_level_code, String nat_lang_title_code,
			String comb_stmt_chrg_code, String cust_emp_city_code, String cust_emp_state_code,
			String cust_emp_cntry_code, String income_src, String cust_title_code, String cust_perm_city_code,
			String cust_perm_state_code, String cust_perm_cntry_code, String cust_grp, String cust_comu_city_code,
			String cust_comu_state_code, String cust_comu_cntry_code, String cust_occp_code, String cust_commu_code,
			String cust_sector_code, String cust_sub_sector_code, String cust_rating_code, String introd_title_code,
			String cust_introd_stat_code, String cust_type_code, String cust_free_text, String cust_src_of_income,
			String cust_perm_email_id, String cust_emp_email_id, String email_id, String cust_othr_bank_code,
			String chrg_dr_sol_id, String primary_sol_id, String cust_name, String cust_last_name,
			String native_lang_name, String cust_first_name, String tds_exmpt_rmks, String cust_middle_name,
			String cust_introd_cust_id, String tds_cust_id, String cust_employee_no) {
		super();
		this.cust_id = cust_id;
		this.cust_minor_flg = cust_minor_flg;
		this.cust_sex = cust_sex;
		this.cust_card_hold_flg = cust_card_hold_flg;
		this.share_holder_flg = share_holder_flg;
		this.ps_freq_type = ps_freq_type;
		this.ps_freq_week_num = ps_freq_week_num;
		this.ps_freq_hldy_stat = ps_freq_hldy_stat;
		this.purge_allowed_flg = purge_allowed_flg;
		this.is_swift_code_of_bank = is_swift_code_of_bank;
		this.cust_chrg_history_flg = cust_chrg_history_flg;
		this.party_flg = party_flg;
		this.combined_stmt_reqd = combined_stmt_reqd;
		this.loans_stmt_type = loans_stmt_type;
		this.td_stmt_type = td_stmt_type;
		this.despatch_mode = despatch_mode;
		this.address_type = address_type;
		this.allow_sweeps = allow_sweeps;
		this.cust_creation_mode = cust_creation_mode;
		this.tr_cpty_flg = tr_cpty_flg;
		this.income_freq = income_freq;
		this.entity_cre_flg = entity_cre_flg;
		this.del_flg = del_flg;
		this.cust_nre_flg = cust_nre_flg;
		this.cust_stat_chg_date = cust_stat_chg_date;
		this.cust_rating_date = cust_rating_date;
		this.cust_advn_as_on_date = cust_advn_as_on_date;
		this.lchg_time = lchg_time;
		this.rcre_time = rcre_time;
		this.cust_asset_class_date = cust_asset_class_date;
		this.cust_membership_date = cust_membership_date;
		this.date_of_birth = date_of_birth;
		this.psprt_issu_date = psprt_issu_date;
		this.psprt_exp_date = psprt_exp_date;
		this.cust_pref_till_date = cust_pref_till_date;
		this.tds_exmpt_end_date = tds_exmpt_end_date;
		this.tds_exmpt_submit_date = tds_exmpt_submit_date;
		this.cs_last_printed_date = cs_last_printed_date;
		this.cs_next_due_date = cs_next_due_date;
		this.cust_opn_date = cust_opn_date;
		this.cust_first_acct_date = cust_first_acct_date;
		this.cust_assets_as_on_date = cust_assets_as_on_date;
		this.ps_freq_week_day = ps_freq_week_day;
		this.ps_freq_start_dd = ps_freq_start_dd;
		this.cust_fin_year_end_mnth = cust_fin_year_end_mnth;
		this.cust_dep_in_othr_bank = cust_dep_in_othr_bank;
		this.cust_othr_lim = cust_othr_lim;
		this.cust_net_worth = cust_net_worth;
		this.cust_investmnts = cust_investmnts;
		this.cust_business_assets = cust_business_assets;
		this.cust_prop_assets = cust_prop_assets;
		this.income_to = income_to;
		this.income_from = income_from;
		this.cust_floor_limit_tds = cust_floor_limit_tds;
		this.value_share_held = value_share_held;
		this.cust_tot_fund_base = cust_tot_fund_base;
		this.offline_cum_debit_limit = offline_cum_debit_limit;
		this.cust_tot_non_fund_base = cust_tot_non_fund_base;
		this.cust_salary = cust_salary;
		this.tot_tod_alwd_times = tot_tod_alwd_times;
		this.ts_cnt = ts_cnt;
		this.num_of_accounts = num_of_accounts;
		this.tot_mod_times = tot_mod_times;
		this.total_shares_held = total_shares_held;
		this.cust_emp_id = cust_emp_id;
		this.cust_perm_pin_code = cust_perm_pin_code;
		this.cust_short_name = cust_short_name;
		this.lang_code = lang_code;
		this.cust_perm_sub_pin_code = cust_perm_sub_pin_code;
		this.cust_emp_sub_pin_code = cust_emp_sub_pin_code;
		this.cust_comu_pin_code = cust_comu_pin_code;
		this.cust_comu_sub_pin_code = cust_comu_sub_pin_code;
		this.cust_emp_pin_code = cust_emp_pin_code;
		this.cust_level_charges_acct = cust_level_charges_acct;
		this.dsa_id = dsa_id;
		this.cust_swift_code = cust_swift_code;
		this.psprt_num = psprt_num;
		this.employer_id = employer_id;
		this.cust_comu_phone_num_1 = cust_comu_phone_num_1;
		this.cust_comu_phone_num_2 = cust_comu_phone_num_2;
		this.cust_comu_telex_num = cust_comu_telex_num;
		this.lchg_user_id = lchg_user_id;
		this.rcre_user_id = rcre_user_id;
		this.cust_pager_no = cust_pager_no;
		this.cust_fax_no = cust_fax_no;
		this.free_text_1 = free_text_1;
		this.free_text_2 = free_text_2;
		this.free_text_3 = free_text_3;
		this.free_text_4 = free_text_4;
		this.free_text_5 = free_text_5;
		this.free_text_6 = free_text_6;
		this.free_text_7 = free_text_7;
		this.free_text_8 = free_text_8;
		this.free_text_9 = free_text_9;
		this.free_text_10 = free_text_10;
		this.free_text_11 = free_text_11;
		this.free_text_12 = free_text_12;
		this.free_text_13 = free_text_13;
		this.free_text_14 = free_text_14;
		this.free_text_15 = free_text_15;
		this.acct_mgr_user_id = acct_mgr_user_id;
		this.cust_emp_phone_num_1 = cust_emp_phone_num_1;
		this.cust_emp_phone_num_2 = cust_emp_phone_num_2;
		this.cust_emp_telex_num = cust_emp_telex_num;
		this.cust_perm_fax_num = cust_perm_fax_num;
		this.cust_emp_fax_num = cust_emp_fax_num;
		this.cust_perm_phone_num_2 = cust_perm_phone_num_2;
		this.pref_code = pref_code;
		this.tr_cpty_mnemonic = tr_cpty_mnemonic;
		this.free_text_16 = free_text_16;
		this.free_text_17 = free_text_17;
		this.free_text_18 = free_text_18;
		this.free_text_19 = free_text_19;
		this.free_text_20 = free_text_20;
		this.cust_perm_phone_num = cust_perm_phone_num;
		this.cust_perm_telex_num = cust_perm_telex_num;
		this.chrg_dr_foracid = chrg_dr_foracid;
		this.nat_id_card_num = nat_id_card_num;
		this.member_type = member_type;
		this.purge_text = purge_text;
		this.cust_mgr_opin = cust_mgr_opin;
		this.crm_cust_id = crm_cust_id;
		this.pan_gir_num = pan_gir_num;
		this.psprt_det = psprt_det;
		this.crncy_code = crncy_code;
		this.income_crncy = income_crncy;
		this.tds_exmpt_ref_num = tds_exmpt_ref_num;
		this.cust_introd_name = cust_introd_name;
		this.cust_emp_addr1 = cust_emp_addr1;
		this.cust_comu_addr2 = cust_comu_addr2;
		this.cust_perm_addr1 = cust_perm_addr1;
		this.cust_perm_addr2 = cust_perm_addr2;
		this.cust_comu_addr1 = cust_comu_addr1;
		this.cust_emp_addr2 = cust_emp_addr2;
		this.cust_stat_code = cust_stat_code;
		this.cust_const = cust_const;
		this.cust_hlth_code = cust_hlth_code;
		this.cust_asset_class = cust_asset_class;
		this.cust_caste_code = cust_caste_code;
		this.tds_tbl_code = tds_tbl_code;
		this.cust_marital_status = cust_marital_status;
		this.chrg_level_code = chrg_level_code;
		this.nat_lang_title_code = nat_lang_title_code;
		this.comb_stmt_chrg_code = comb_stmt_chrg_code;
		this.cust_emp_city_code = cust_emp_city_code;
		this.cust_emp_state_code = cust_emp_state_code;
		this.cust_emp_cntry_code = cust_emp_cntry_code;
		this.income_src = income_src;
		this.cust_title_code = cust_title_code;
		this.cust_perm_city_code = cust_perm_city_code;
		this.cust_perm_state_code = cust_perm_state_code;
		this.cust_perm_cntry_code = cust_perm_cntry_code;
		this.cust_grp = cust_grp;
		this.cust_comu_city_code = cust_comu_city_code;
		this.cust_comu_state_code = cust_comu_state_code;
		this.cust_comu_cntry_code = cust_comu_cntry_code;
		this.cust_occp_code = cust_occp_code;
		this.cust_commu_code = cust_commu_code;
		this.cust_sector_code = cust_sector_code;
		this.cust_sub_sector_code = cust_sub_sector_code;
		this.cust_rating_code = cust_rating_code;
		this.introd_title_code = introd_title_code;
		this.cust_introd_stat_code = cust_introd_stat_code;
		this.cust_type_code = cust_type_code;
		this.cust_free_text = cust_free_text;
		this.cust_src_of_income = cust_src_of_income;
		this.cust_perm_email_id = cust_perm_email_id;
		this.cust_emp_email_id = cust_emp_email_id;
		this.email_id = email_id;
		this.cust_othr_bank_code = cust_othr_bank_code;
		this.chrg_dr_sol_id = chrg_dr_sol_id;
		this.primary_sol_id = primary_sol_id;
		this.cust_name = cust_name;
		this.cust_last_name = cust_last_name;
		this.native_lang_name = native_lang_name;
		this.cust_first_name = cust_first_name;
		this.tds_exmpt_rmks = tds_exmpt_rmks;
		this.cust_middle_name = cust_middle_name;
		this.cust_introd_cust_id = cust_introd_cust_id;
		this.tds_cust_id = tds_cust_id;
		this.cust_employee_no = cust_employee_no;
	}



	

}

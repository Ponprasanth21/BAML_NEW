package com.bornfire.entity.t6;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.bornfire.entity.AML_KYC_Parameter;

@Entity
@Table(name = "T6_KYC_CDD_REVIEW_FREQ_TABLE")
public class T6Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2066263246724362734L;
	
	
	
	

	private String	d1_current_individuals_low;
	private String	e1_current_individuals_medium;
	private String	f1_current_individuals_high;
	
	private String	d2_current_corporates_low;
	private String	e2_current_corporates_medium;
	private String	f2_current_corporates_high;
	
	private String	d3_current_non_profit_organizations_low;
	private String	e3_current_non_profit_organizations_medium;
	private String	f3_current_non_profit_organizations_high;
	
	
	private String	d4_current_trusts_other_than_npos_and_tcsps_low;
	private String	e4_current_trusts_other_than_npos_and_tcsps_medium;
	private String	f4_current_trusts_other_than_npos_and_tcsps_high;
	
	private String	d5_current_all_others_low;
	private String	e5_current_all_others_medium;
	private String	f5_current_all_others_high;

	private String	d6_current_peps_domestic_low;
	private String	e6_current_peps_domestic_medium;
	private String	f6_current_peps_domestic_high;
	
	private String	d7_current_peps_foreign_low;
	private String	e7_current_peps_foreign_medium;
	private String	f7_current_peps_foreign_high;
	
	private String	d8_current_trust_and_company_service_providers_tcps_low;
	private String	e8_current_trust_and_company_service_providers_tcps_medium;
	private String	f8_current_trust_and_company_service_providers_tcps_high;
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	report_date;
	private Date	report_due_date;
	private Date	rep_submit_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_from;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	rep_period_to;
	private String	rep_freq;
	private String	nil_report_flg;
	private String srl_no;
	private String entity_flg;
	private String modify_flg;
	
	
	
	
	
	
	
	public String getD1_current_individuals_low() {
		return d1_current_individuals_low;
	}
	public String getE1_current_individuals_medium() {
		return e1_current_individuals_medium;
	}
	public String getF1_current_individuals_high() {
		return f1_current_individuals_high;
	}

	public String getD2_current_corporates_low() {
		return d2_current_corporates_low;
	}
	public String getE2_current_corporates_medium() {
		return e2_current_corporates_medium;
	}
	public String getF2_current_corporates_high() {
		return f2_current_corporates_high;
	}
	
	public String getD3_current_non_profit_organizations_low() {
		return d3_current_non_profit_organizations_low;
	}
	public String getE3_current_non_profit_organizations_medium() {
		return e3_current_non_profit_organizations_medium;
	}
	public String getF3_current_non_profit_organizations_high() {
		return f3_current_non_profit_organizations_high;
	}
	
	public String getD4_current_trusts_other_than_npos_and_tcsps_low() {
		return d4_current_trusts_other_than_npos_and_tcsps_low;
	}
	public String getE4_current_trusts_other_than_npos_and_tcsps_medium() {
		return e4_current_trusts_other_than_npos_and_tcsps_medium;
	}
	public String getF4_current_trusts_other_than_npos_and_tcsps_high() {
		return f4_current_trusts_other_than_npos_and_tcsps_high;
	}

	public String getD5_current_all_others_low() {
		return d5_current_all_others_low;
	}
	public String getE5_current_all_others_medium() {
		return e5_current_all_others_medium;
	}
	public String getF5_current_all_others_high() {
		return f5_current_all_others_high;
	}
	
	public String getD6_current_peps_domestic_low() {
		return d6_current_peps_domestic_low;
	}
	public String getE6_current_peps_domestic_medium() {
		return e6_current_peps_domestic_medium;
	}
	public String getF6_current_peps_domestic_high() {
		return f6_current_peps_domestic_high;
	}
	
	public String getD7_current_peps_foreign_low() {
		return d7_current_peps_foreign_low;
	}
	public String getE7_current_peps_foreign_medium() {
		return e7_current_peps_foreign_medium;
	}
	public String getF7_current_peps_foreign_high() {
		return f7_current_peps_foreign_high;
	}
	
	public String getD8_current_trust_and_company_service_providers_tcps_low() {
		return d8_current_trust_and_company_service_providers_tcps_low;
	}
	public String getE8_current_trust_and_company_service_providers_tcps_medium() {
		return e8_current_trust_and_company_service_providers_tcps_medium;
	}
	public String getF8_current_trust_and_company_service_providers_tcps_high() {
		return f8_current_trust_and_company_service_providers_tcps_high;
	}
	public Date getReport_date() {
		return report_date;
	}
	public Date getReport_due_date() {
		return report_due_date;
	}
	public Date getRep_submit_date() {
		return rep_submit_date;
	}
	public Date getRep_period_from() {
		return rep_period_from;
	}
	public Date getRep_period_to() {
		return rep_period_to;
	}
	public String getRep_freq() {
		return rep_freq;
	}
	
	public String getNil_report_flg() {
		return nil_report_flg;
	}

	public void setD1_current_individuals_low(String d1_current_individuals_low) {
		this.d1_current_individuals_low = d1_current_individuals_low;
	}
	public void setE1_current_individuals_medium(String e1_current_individuals_medium) {
		this.e1_current_individuals_medium = e1_current_individuals_medium;
	}
	public void setF1_current_individuals_high(String f1_current_individuals_high) {
		this.f1_current_individuals_high = f1_current_individuals_high;
	}
	
	public void setD2_current_corporates_low(String d2_current_corporates_low) {
		this.d2_current_corporates_low = d2_current_corporates_low;
	}
	public void setE2_current_corporates_medium(String e2_current_corporates_medium) {
		this.e2_current_corporates_medium = e2_current_corporates_medium;
	}
	public void setF2_current_corporates_high(String f2_current_corporates_high) {
		this.f2_current_corporates_high = f2_current_corporates_high;
	}

	public void setD3_current_non_profit_organizations_low(String d3_current_non_profit_organizations_low) {
		this.d3_current_non_profit_organizations_low = d3_current_non_profit_organizations_low;
	}
	public void setE3_current_non_profit_organizations_medium(String e3_current_non_profit_organizations_medium) {
		this.e3_current_non_profit_organizations_medium = e3_current_non_profit_organizations_medium;
	}
	public void setF3_current_non_profit_organizations_high(String f3_current_non_profit_organizations_high) {
		this.f3_current_non_profit_organizations_high = f3_current_non_profit_organizations_high;
	}
	
	public void setD4_current_trusts_other_than_npos_and_tcsps_low(String d4_current_trusts_other_than_npos_and_tcsps_low) {
		this.d4_current_trusts_other_than_npos_and_tcsps_low = d4_current_trusts_other_than_npos_and_tcsps_low;
	}
	public void setE4_current_trusts_other_than_npos_and_tcsps_medium(
			String e4_current_trusts_other_than_npos_and_tcsps_medium) {
		this.e4_current_trusts_other_than_npos_and_tcsps_medium = e4_current_trusts_other_than_npos_and_tcsps_medium;
	}
	public void setF4_current_trusts_other_than_npos_and_tcsps_high(
			String f4_current_trusts_other_than_npos_and_tcsps_high) {
		this.f4_current_trusts_other_than_npos_and_tcsps_high = f4_current_trusts_other_than_npos_and_tcsps_high;
	}
	
	public void setD5_current_all_others_low(String d5_current_all_others_low) {
		this.d5_current_all_others_low = d5_current_all_others_low;
	}
	public void setE5_current_all_others_medium(String e5_current_all_others_medium) {
		this.e5_current_all_others_medium = e5_current_all_others_medium;
	}
	public void setF5_current_all_others_high(String f5_current_all_others_high) {
		this.f5_current_all_others_high = f5_current_all_others_high;
	}
	
	public void setD6_current_peps_domestic_low(String d6_current_peps_domestic_low) {
		this.d6_current_peps_domestic_low = d6_current_peps_domestic_low;
	}
	public void setE6_current_peps_domestic_medium(String e6_current_peps_domestic_medium) {
		this.e6_current_peps_domestic_medium = e6_current_peps_domestic_medium;
	}
	public void setF6_current_peps_domestic_high(String f6_current_peps_domestic_high) {
		this.f6_current_peps_domestic_high = f6_current_peps_domestic_high;
	}
	
	public void setD7_current_peps_foreign_low(String d7_current_peps_foreign_low) {
		this.d7_current_peps_foreign_low = d7_current_peps_foreign_low;
	}
	public void setE7_current_peps_foreign_medium(String e7_current_peps_foreign_medium) {
		this.e7_current_peps_foreign_medium = e7_current_peps_foreign_medium;
	}
	public void setF7_current_peps_foreign_high(String f7_current_peps_foreign_high) {
		this.f7_current_peps_foreign_high = f7_current_peps_foreign_high;
	}
	
	public void setD8_current_trust_and_company_service_providers_tcps_low(
			String d8_current_trust_and_company_service_providers_tcps_low) {
		this.d8_current_trust_and_company_service_providers_tcps_low = d8_current_trust_and_company_service_providers_tcps_low;
	}
	public void setE8_current_trust_and_company_service_providers_tcps_medium(
			String e8_current_trust_and_company_service_providers_tcps_medium) {
		this.e8_current_trust_and_company_service_providers_tcps_medium = e8_current_trust_and_company_service_providers_tcps_medium;
	}
	public void setF8_current_trust_and_company_service_providers_tcps_high(
			String f8_current_trust_and_company_service_providers_tcps_high) {
		this.f8_current_trust_and_company_service_providers_tcps_high = f8_current_trust_and_company_service_providers_tcps_high;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public void setReport_due_date(Date report_due_date) {
		this.report_due_date = report_due_date;
	}
	public void setRep_submit_date(Date rep_submit_date) {
		this.rep_submit_date = rep_submit_date;
	}
	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}
	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}
	public void setRep_freq(String rep_freq) {
		this.rep_freq = rep_freq;
	}
	
	public void setNil_report_flg(String nil_report_flg) {
		this.nil_report_flg = nil_report_flg;
	}
	
	
	
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	
	
	
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	
	
	
	
	

	
	public T6Report(String d1_current_individuals_low, String e1_current_individuals_medium,
			String f1_current_individuals_high, String d2_current_corporates_low, String e2_current_corporates_medium,
			String f2_current_corporates_high, String d3_current_non_profit_organizations_low,
			String e3_current_non_profit_organizations_medium, String f3_current_non_profit_organizations_high,
			String d4_current_trusts_other_than_npos_and_tcsps_low,
			String e4_current_trusts_other_than_npos_and_tcsps_medium,
			String f4_current_trusts_other_than_npos_and_tcsps_high, String d5_current_all_others_low,
			String e5_current_all_others_medium, String f5_current_all_others_high, String d6_current_peps_domestic_low,
			String e6_current_peps_domestic_medium, String f6_current_peps_domestic_high,
			String d7_current_peps_foreign_low, String e7_current_peps_foreign_medium,
			String f7_current_peps_foreign_high, String d8_current_trust_and_company_service_providers_tcps_low,
			String e8_current_trust_and_company_service_providers_tcps_medium,
			String f8_current_trust_and_company_service_providers_tcps_high, Date report_date, Date report_due_date,
			Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq, String nil_report_flg,
			String srl_no, String entity_flg, String modify_flg) {
		super();
		this.d1_current_individuals_low = d1_current_individuals_low;
		this.e1_current_individuals_medium = e1_current_individuals_medium;
		this.f1_current_individuals_high = f1_current_individuals_high;
		this.d2_current_corporates_low = d2_current_corporates_low;
		this.e2_current_corporates_medium = e2_current_corporates_medium;
		this.f2_current_corporates_high = f2_current_corporates_high;
		this.d3_current_non_profit_organizations_low = d3_current_non_profit_organizations_low;
		this.e3_current_non_profit_organizations_medium = e3_current_non_profit_organizations_medium;
		this.f3_current_non_profit_organizations_high = f3_current_non_profit_organizations_high;
		this.d4_current_trusts_other_than_npos_and_tcsps_low = d4_current_trusts_other_than_npos_and_tcsps_low;
		this.e4_current_trusts_other_than_npos_and_tcsps_medium = e4_current_trusts_other_than_npos_and_tcsps_medium;
		this.f4_current_trusts_other_than_npos_and_tcsps_high = f4_current_trusts_other_than_npos_and_tcsps_high;
		this.d5_current_all_others_low = d5_current_all_others_low;
		this.e5_current_all_others_medium = e5_current_all_others_medium;
		this.f5_current_all_others_high = f5_current_all_others_high;
		this.d6_current_peps_domestic_low = d6_current_peps_domestic_low;
		this.e6_current_peps_domestic_medium = e6_current_peps_domestic_medium;
		this.f6_current_peps_domestic_high = f6_current_peps_domestic_high;
		this.d7_current_peps_foreign_low = d7_current_peps_foreign_low;
		this.e7_current_peps_foreign_medium = e7_current_peps_foreign_medium;
		this.f7_current_peps_foreign_high = f7_current_peps_foreign_high;
		this.d8_current_trust_and_company_service_providers_tcps_low = d8_current_trust_and_company_service_providers_tcps_low;
		this.e8_current_trust_and_company_service_providers_tcps_medium = e8_current_trust_and_company_service_providers_tcps_medium;
		this.f8_current_trust_and_company_service_providers_tcps_high = f8_current_trust_and_company_service_providers_tcps_high;
		this.report_date = report_date;
		this.report_due_date = report_due_date;
		this.rep_submit_date = rep_submit_date;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
		this.rep_freq = rep_freq;
		this.nil_report_flg = nil_report_flg;
		this.srl_no = srl_no;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}
	public T6Report(AML_KYC_Parameter up) {
		
		
		this.d1_current_individuals_low = up.getD1_current_individuals_low();
		this.e1_current_individuals_medium = up.getE1_current_individuals_medium();
		this.f1_current_individuals_high = up.getF1_current_individuals_high();
		
		this.d2_current_corporates_low = up.getD2_current_corporates_low();
		this.e2_current_corporates_medium = up.getE2_current_corporates_medium();
		this.f2_current_corporates_high = up.getF2_current_corporates_high();
	
		this.d3_current_non_profit_organizations_low = up.getD3_current_non_profit_organizations_low();
		this.e3_current_non_profit_organizations_medium = up.getE3_current_non_profit_organizations_medium();
		this.f3_current_non_profit_organizations_high = up.getF3_current_non_profit_organizations_high();
	
		this.d4_current_trusts_other_than_npos_and_tcsps_low = up.getD4_current_trusts_other_than_npos_and_tcsps_low();
		this.e4_current_trusts_other_than_npos_and_tcsps_medium = up.getE4_current_trusts_other_than_npos_and_tcsps_medium();
		this.f4_current_trusts_other_than_npos_and_tcsps_high = up.getF4_current_trusts_other_than_npos_and_tcsps_high();
		
		this.d5_current_all_others_low = up.getD5_current_all_others_low();
		this.e5_current_all_others_medium = up.getE5_current_all_others_medium();
		this.f5_current_all_others_high = up.getF5_current_all_others_high();
		
		this.d6_current_peps_domestic_low = up.getD6_current_peps_domestic_low();
		this.e6_current_peps_domestic_medium = up.getE6_current_peps_domestic_medium();
		this.f6_current_peps_domestic_high = up.getF6_current_peps_domestic_high();
	
		this.d7_current_peps_foreign_low = up.getD7_current_peps_foreign_low();
		this.e7_current_peps_foreign_medium = up.getE7_current_peps_foreign_medium();
		this.f7_current_peps_foreign_high = up.getF7_current_peps_foreign_high();
		
		this.d8_current_trust_and_company_service_providers_tcps_low = up.getD8_current_trust_and_company_service_providers_tcps_low();
		this.e8_current_trust_and_company_service_providers_tcps_medium = up.getE8_current_trust_and_company_service_providers_tcps_medium();
		this.f8_current_trust_and_company_service_providers_tcps_high = up.getF8_current_trust_and_company_service_providers_tcps_high();
		this.srl_no = up.getSrl_no();
		this.entity_flg = up.getEntity_flg();
		this.modify_flg = up.getModify_flg();
	}
	public T6Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}
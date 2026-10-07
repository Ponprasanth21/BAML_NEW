package com.bornfire.entity.t5;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T5_RISK_RATING_MIG_SUMARY_TABLE")
public class T5Report {
	
	private String	t5_1_name;
	private BigDecimal	t5_1a_total_cust_upgrade_pre_facetoface;
	private BigDecimal	t5_1b_total_cust_upgrade_pre_non_facetoface;
	private BigDecimal	t5_1c_total_cust_upgrade_cur_facetoface;
	private BigDecimal	t5_1d_total_cust_upgrade_cur_non_facetoface;
	private String	t5_2_name;
	private BigDecimal	t5_2a_high_to_med_pre_facetoface;
	private BigDecimal	t5_2b_high_to_med_pre_non_facetoface;
	private BigDecimal	t5_2c_high_to_med_cur_facetoface;
	private BigDecimal	t5_2d_high_to_med_cur_non_facetoface;
	private String	t5_3_name;
	private BigDecimal	t5_3a_high_to_low_pre_facetoface;
	private BigDecimal	t5_3b_high_to_low_pre_non_facetoface;
	private BigDecimal	t5_3c_high_to_low_cur_facetoface;
	private BigDecimal	t5_3d_high_to_low_cur_non_facetoface;
	private String	t5_4_name;
	private BigDecimal	t5_4a_med_to_low_pre_facetoface;
	private BigDecimal	t5_4b_med_to_low_pre_non_facetoface;
	private BigDecimal	t5_4c_med_to_low_cur_facetoface;
	private BigDecimal	t5_4d_med_to_low_cur_non_facetoface;
	private String	t5_5_name;
	private BigDecimal	t5_5a_total_cust_downgrade_pre_facetoface;
	private BigDecimal	t5_5b_total_cust_downgrade_pre_non_facetoface;
	private BigDecimal	t5_5c_total_cust_downgrade_cur_facetoface;
	private BigDecimal	t5_5d_total_cust_downgrade_cur_non_facetoface;
	private String	t5_6_name;
	private BigDecimal	t5_6a_low_to_med_pre_facetoface;
	private BigDecimal	t5_6b_low_to_med_pre_non_facetoface;
	private BigDecimal	t5_6c_low_to_med_cur_facetoface;
	private BigDecimal	t5_6d_low_to_med_cur_non_facetoface;
	private String	t5_7_name;
	private BigDecimal	t5_7a_low_to_high_pre_facetoface;
	private BigDecimal	t5_7b_low_to_high_pre_non_facetoface;
	private BigDecimal	t5_7c_low_to_high_cur_facetoface;
	private BigDecimal	t5_7d_low_to_high_cur_non_facetoface;
	private String	t5_8_name;
	private BigDecimal	t5_8a_med_to_high_pre_facetoface;
	private BigDecimal	t5_8b_med_to_high_pre_non_facetoface;
	private BigDecimal	t5_8c_med_to_high_cur_facetoface;
	private BigDecimal	t5_8d_med_to_high_cur_non_facetoface;
	private String	t5_9_name;
	private BigDecimal	t5_9a_total_cust_unchange_pre_facetoface;
	private BigDecimal	t5_9b_total_cust_unchange_pre_non_facetoface;
	private BigDecimal	t5_9c_total_cust_unchange_cur_facetoface;
	private BigDecimal	t5_9d_total_cust_unchange_cur_non_facetoface;
	private String	t5_10_name;
	private BigDecimal	t5_10a_total_pre_facetoface;
	private BigDecimal	t5_10b_total_pre_non_facetoface;
	private BigDecimal	t5_10c_total_cur_facetoface;
	private BigDecimal	t5_10d_total_cur_non_facetoface;
	private String	t5_11_name;
	private String	t5_11a_validation;
	private String	t5_11b_validation;
	private String	t5_11c_validation;
	private String	t5_11d_validation;
	private String	t5_12_name;
	private BigDecimal	t5_12a_customer_terminated_cdd_pre_facetoface;
	private BigDecimal	t5_12b_customer_terminated_cdd_pre_non_facetoface;
	private BigDecimal	t5_12c_customer_terminated_cdd_cur_facetoface;
	private BigDecimal	t5_12d_customer_terminated_cdd_cur_non_facetoface;
	private String	t5_13_name;
	private BigDecimal	t5_13a_customer_ceased_pre_facetoface;
	private BigDecimal	t5_13b_customer_ceased_pre_non_facetoface;
	private BigDecimal	t5_13c_customer_ceased_cur_facetoface;
	private BigDecimal	t5_13d_customer_ceased_cur_non_facetoface;
	private Date	t5_report_submit_date;
	private Date	t5_report_generate_date;
	private Date	t5_report_due_date;
	private String	t5_nil_report_flg;
	private Date	t5_report_from_date;
	
	@Id
	private Date	t5_report_to_date;
	private String	t5_frequency;
	public String getT5_1_name() {
		return t5_1_name;
	}
	public void setT5_1_name(String t5_1_name) {
		this.t5_1_name = t5_1_name;
	}
	public BigDecimal getT5_1a_total_cust_upgrade_pre_facetoface() {
		return t5_1a_total_cust_upgrade_pre_facetoface;
	}
	public void setT5_1a_total_cust_upgrade_pre_facetoface(BigDecimal t5_1a_total_cust_upgrade_pre_facetoface) {
		this.t5_1a_total_cust_upgrade_pre_facetoface = t5_1a_total_cust_upgrade_pre_facetoface;
	}
	public BigDecimal getT5_1b_total_cust_upgrade_pre_non_facetoface() {
		return t5_1b_total_cust_upgrade_pre_non_facetoface;
	}
	public void setT5_1b_total_cust_upgrade_pre_non_facetoface(BigDecimal t5_1b_total_cust_upgrade_pre_non_facetoface) {
		this.t5_1b_total_cust_upgrade_pre_non_facetoface = t5_1b_total_cust_upgrade_pre_non_facetoface;
	}
	public BigDecimal getT5_1c_total_cust_upgrade_cur_facetoface() {
		return t5_1c_total_cust_upgrade_cur_facetoface;
	}
	public void setT5_1c_total_cust_upgrade_cur_facetoface(BigDecimal t5_1c_total_cust_upgrade_cur_facetoface) {
		this.t5_1c_total_cust_upgrade_cur_facetoface = t5_1c_total_cust_upgrade_cur_facetoface;
	}
	public BigDecimal getT5_1d_total_cust_upgrade_cur_non_facetoface() {
		return t5_1d_total_cust_upgrade_cur_non_facetoface;
	}
	public void setT5_1d_total_cust_upgrade_cur_non_facetoface(BigDecimal t5_1d_total_cust_upgrade_cur_non_facetoface) {
		this.t5_1d_total_cust_upgrade_cur_non_facetoface = t5_1d_total_cust_upgrade_cur_non_facetoface;
	}
	public String getT5_2_name() {
		return t5_2_name;
	}
	public void setT5_2_name(String t5_2_name) {
		this.t5_2_name = t5_2_name;
	}
	public BigDecimal getT5_2a_high_to_med_pre_facetoface() {
		return t5_2a_high_to_med_pre_facetoface;
	}
	public void setT5_2a_high_to_med_pre_facetoface(BigDecimal t5_2a_high_to_med_pre_facetoface) {
		this.t5_2a_high_to_med_pre_facetoface = t5_2a_high_to_med_pre_facetoface;
	}
	public BigDecimal getT5_2b_high_to_med_pre_non_facetoface() {
		return t5_2b_high_to_med_pre_non_facetoface;
	}
	public void setT5_2b_high_to_med_pre_non_facetoface(BigDecimal t5_2b_high_to_med_pre_non_facetoface) {
		this.t5_2b_high_to_med_pre_non_facetoface = t5_2b_high_to_med_pre_non_facetoface;
	}
	public BigDecimal getT5_2c_high_to_med_cur_facetoface() {
		return t5_2c_high_to_med_cur_facetoface;
	}
	public void setT5_2c_high_to_med_cur_facetoface(BigDecimal t5_2c_high_to_med_cur_facetoface) {
		this.t5_2c_high_to_med_cur_facetoface = t5_2c_high_to_med_cur_facetoface;
	}
	public BigDecimal getT5_2d_high_to_med_cur_non_facetoface() {
		return t5_2d_high_to_med_cur_non_facetoface;
	}
	public void setT5_2d_high_to_med_cur_non_facetoface(BigDecimal t5_2d_high_to_med_cur_non_facetoface) {
		this.t5_2d_high_to_med_cur_non_facetoface = t5_2d_high_to_med_cur_non_facetoface;
	}
	public String getT5_3_name() {
		return t5_3_name;
	}
	public void setT5_3_name(String t5_3_name) {
		this.t5_3_name = t5_3_name;
	}
	public BigDecimal getT5_3a_high_to_low_pre_facetoface() {
		return t5_3a_high_to_low_pre_facetoface;
	}
	public void setT5_3a_high_to_low_pre_facetoface(BigDecimal t5_3a_high_to_low_pre_facetoface) {
		this.t5_3a_high_to_low_pre_facetoface = t5_3a_high_to_low_pre_facetoface;
	}
	public BigDecimal getT5_3b_high_to_low_pre_non_facetoface() {
		return t5_3b_high_to_low_pre_non_facetoface;
	}
	public void setT5_3b_high_to_low_pre_non_facetoface(BigDecimal t5_3b_high_to_low_pre_non_facetoface) {
		this.t5_3b_high_to_low_pre_non_facetoface = t5_3b_high_to_low_pre_non_facetoface;
	}
	public BigDecimal getT5_3c_high_to_low_cur_facetoface() {
		return t5_3c_high_to_low_cur_facetoface;
	}
	public void setT5_3c_high_to_low_cur_facetoface(BigDecimal t5_3c_high_to_low_cur_facetoface) {
		this.t5_3c_high_to_low_cur_facetoface = t5_3c_high_to_low_cur_facetoface;
	}
	public BigDecimal getT5_3d_high_to_low_cur_non_facetoface() {
		return t5_3d_high_to_low_cur_non_facetoface;
	}
	public void setT5_3d_high_to_low_cur_non_facetoface(BigDecimal t5_3d_high_to_low_cur_non_facetoface) {
		this.t5_3d_high_to_low_cur_non_facetoface = t5_3d_high_to_low_cur_non_facetoface;
	}
	public String getT5_4_name() {
		return t5_4_name;
	}
	public void setT5_4_name(String t5_4_name) {
		this.t5_4_name = t5_4_name;
	}
	public BigDecimal getT5_4a_med_to_low_pre_facetoface() {
		return t5_4a_med_to_low_pre_facetoface;
	}
	public void setT5_4a_med_to_low_pre_facetoface(BigDecimal t5_4a_med_to_low_pre_facetoface) {
		this.t5_4a_med_to_low_pre_facetoface = t5_4a_med_to_low_pre_facetoface;
	}
	public BigDecimal getT5_4b_med_to_low_pre_non_facetoface() {
		return t5_4b_med_to_low_pre_non_facetoface;
	}
	public void setT5_4b_med_to_low_pre_non_facetoface(BigDecimal t5_4b_med_to_low_pre_non_facetoface) {
		this.t5_4b_med_to_low_pre_non_facetoface = t5_4b_med_to_low_pre_non_facetoface;
	}
	public BigDecimal getT5_4c_med_to_low_cur_facetoface() {
		return t5_4c_med_to_low_cur_facetoface;
	}
	public void setT5_4c_med_to_low_cur_facetoface(BigDecimal t5_4c_med_to_low_cur_facetoface) {
		this.t5_4c_med_to_low_cur_facetoface = t5_4c_med_to_low_cur_facetoface;
	}
	public BigDecimal getT5_4d_med_to_low_cur_non_facetoface() {
		return t5_4d_med_to_low_cur_non_facetoface;
	}
	public void setT5_4d_med_to_low_cur_non_facetoface(BigDecimal t5_4d_med_to_low_cur_non_facetoface) {
		this.t5_4d_med_to_low_cur_non_facetoface = t5_4d_med_to_low_cur_non_facetoface;
	}
	public String getT5_5_name() {
		return t5_5_name;
	}
	public void setT5_5_name(String t5_5_name) {
		this.t5_5_name = t5_5_name;
	}
	public BigDecimal getT5_5a_total_cust_downgrade_pre_facetoface() {
		return t5_5a_total_cust_downgrade_pre_facetoface;
	}
	public void setT5_5a_total_cust_downgrade_pre_facetoface(BigDecimal t5_5a_total_cust_downgrade_pre_facetoface) {
		this.t5_5a_total_cust_downgrade_pre_facetoface = t5_5a_total_cust_downgrade_pre_facetoface;
	}
	public BigDecimal getT5_5b_total_cust_downgrade_pre_non_facetoface() {
		return t5_5b_total_cust_downgrade_pre_non_facetoface;
	}
	public void setT5_5b_total_cust_downgrade_pre_non_facetoface(BigDecimal t5_5b_total_cust_downgrade_pre_non_facetoface) {
		this.t5_5b_total_cust_downgrade_pre_non_facetoface = t5_5b_total_cust_downgrade_pre_non_facetoface;
	}
	public BigDecimal getT5_5c_total_cust_downgrade_cur_facetoface() {
		return t5_5c_total_cust_downgrade_cur_facetoface;
	}
	public void setT5_5c_total_cust_downgrade_cur_facetoface(BigDecimal t5_5c_total_cust_downgrade_cur_facetoface) {
		this.t5_5c_total_cust_downgrade_cur_facetoface = t5_5c_total_cust_downgrade_cur_facetoface;
	}
	public BigDecimal getT5_5d_total_cust_downgrade_cur_non_facetoface() {
		return t5_5d_total_cust_downgrade_cur_non_facetoface;
	}
	public void setT5_5d_total_cust_downgrade_cur_non_facetoface(BigDecimal t5_5d_total_cust_downgrade_cur_non_facetoface) {
		this.t5_5d_total_cust_downgrade_cur_non_facetoface = t5_5d_total_cust_downgrade_cur_non_facetoface;
	}
	public String getT5_6_name() {
		return t5_6_name;
	}
	public void setT5_6_name(String t5_6_name) {
		this.t5_6_name = t5_6_name;
	}
	public BigDecimal getT5_6a_low_to_med_pre_facetoface() {
		return t5_6a_low_to_med_pre_facetoface;
	}
	public void setT5_6a_low_to_med_pre_facetoface(BigDecimal t5_6a_low_to_med_pre_facetoface) {
		this.t5_6a_low_to_med_pre_facetoface = t5_6a_low_to_med_pre_facetoface;
	}
	public BigDecimal getT5_6b_low_to_med_pre_non_facetoface() {
		return t5_6b_low_to_med_pre_non_facetoface;
	}
	public void setT5_6b_low_to_med_pre_non_facetoface(BigDecimal t5_6b_low_to_med_pre_non_facetoface) {
		this.t5_6b_low_to_med_pre_non_facetoface = t5_6b_low_to_med_pre_non_facetoface;
	}
	public BigDecimal getT5_6c_low_to_med_cur_facetoface() {
		return t5_6c_low_to_med_cur_facetoface;
	}
	public void setT5_6c_low_to_med_cur_facetoface(BigDecimal t5_6c_low_to_med_cur_facetoface) {
		this.t5_6c_low_to_med_cur_facetoface = t5_6c_low_to_med_cur_facetoface;
	}
	public BigDecimal getT5_6d_low_to_med_cur_non_facetoface() {
		return t5_6d_low_to_med_cur_non_facetoface;
	}
	public void setT5_6d_low_to_med_cur_non_facetoface(BigDecimal t5_6d_low_to_med_cur_non_facetoface) {
		this.t5_6d_low_to_med_cur_non_facetoface = t5_6d_low_to_med_cur_non_facetoface;
	}
	public String getT5_7_name() {
		return t5_7_name;
	}
	public void setT5_7_name(String t5_7_name) {
		this.t5_7_name = t5_7_name;
	}
	public BigDecimal getT5_7a_low_to_high_pre_facetoface() {
		return t5_7a_low_to_high_pre_facetoface;
	}
	public void setT5_7a_low_to_high_pre_facetoface(BigDecimal t5_7a_low_to_high_pre_facetoface) {
		this.t5_7a_low_to_high_pre_facetoface = t5_7a_low_to_high_pre_facetoface;
	}
	public BigDecimal getT5_7b_low_to_high_pre_non_facetoface() {
		return t5_7b_low_to_high_pre_non_facetoface;
	}
	public void setT5_7b_low_to_high_pre_non_facetoface(BigDecimal t5_7b_low_to_high_pre_non_facetoface) {
		this.t5_7b_low_to_high_pre_non_facetoface = t5_7b_low_to_high_pre_non_facetoface;
	}
	public BigDecimal getT5_7c_low_to_high_cur_facetoface() {
		return t5_7c_low_to_high_cur_facetoface;
	}
	public void setT5_7c_low_to_high_cur_facetoface(BigDecimal t5_7c_low_to_high_cur_facetoface) {
		this.t5_7c_low_to_high_cur_facetoface = t5_7c_low_to_high_cur_facetoface;
	}
	public BigDecimal getT5_7d_low_to_high_cur_non_facetoface() {
		return t5_7d_low_to_high_cur_non_facetoface;
	}
	public void setT5_7d_low_to_high_cur_non_facetoface(BigDecimal t5_7d_low_to_high_cur_non_facetoface) {
		this.t5_7d_low_to_high_cur_non_facetoface = t5_7d_low_to_high_cur_non_facetoface;
	}
	public String getT5_8_name() {
		return t5_8_name;
	}
	public void setT5_8_name(String t5_8_name) {
		this.t5_8_name = t5_8_name;
	}
	public BigDecimal getT5_8a_med_to_high_pre_facetoface() {
		return t5_8a_med_to_high_pre_facetoface;
	}
	public void setT5_8a_med_to_high_pre_facetoface(BigDecimal t5_8a_med_to_high_pre_facetoface) {
		this.t5_8a_med_to_high_pre_facetoface = t5_8a_med_to_high_pre_facetoface;
	}
	public BigDecimal getT5_8b_med_to_high_pre_non_facetoface() {
		return t5_8b_med_to_high_pre_non_facetoface;
	}
	public void setT5_8b_med_to_high_pre_non_facetoface(BigDecimal t5_8b_med_to_high_pre_non_facetoface) {
		this.t5_8b_med_to_high_pre_non_facetoface = t5_8b_med_to_high_pre_non_facetoface;
	}
	public BigDecimal getT5_8c_med_to_high_cur_facetoface() {
		return t5_8c_med_to_high_cur_facetoface;
	}
	public void setT5_8c_med_to_high_cur_facetoface(BigDecimal t5_8c_med_to_high_cur_facetoface) {
		this.t5_8c_med_to_high_cur_facetoface = t5_8c_med_to_high_cur_facetoface;
	}
	public BigDecimal getT5_8d_med_to_high_cur_non_facetoface() {
		return t5_8d_med_to_high_cur_non_facetoface;
	}
	public void setT5_8d_med_to_high_cur_non_facetoface(BigDecimal t5_8d_med_to_high_cur_non_facetoface) {
		this.t5_8d_med_to_high_cur_non_facetoface = t5_8d_med_to_high_cur_non_facetoface;
	}
	public String getT5_9_name() {
		return t5_9_name;
	}
	public void setT5_9_name(String t5_9_name) {
		this.t5_9_name = t5_9_name;
	}
	public BigDecimal getT5_9a_total_cust_unchange_pre_facetoface() {
		return t5_9a_total_cust_unchange_pre_facetoface;
	}
	public void setT5_9a_total_cust_unchange_pre_facetoface(BigDecimal t5_9a_total_cust_unchange_pre_facetoface) {
		this.t5_9a_total_cust_unchange_pre_facetoface = t5_9a_total_cust_unchange_pre_facetoface;
	}
	public BigDecimal getT5_9b_total_cust_unchange_pre_non_facetoface() {
		return t5_9b_total_cust_unchange_pre_non_facetoface;
	}
	public void setT5_9b_total_cust_unchange_pre_non_facetoface(BigDecimal t5_9b_total_cust_unchange_pre_non_facetoface) {
		this.t5_9b_total_cust_unchange_pre_non_facetoface = t5_9b_total_cust_unchange_pre_non_facetoface;
	}
	public BigDecimal getT5_9c_total_cust_unchange_cur_facetoface() {
		return t5_9c_total_cust_unchange_cur_facetoface;
	}
	public void setT5_9c_total_cust_unchange_cur_facetoface(BigDecimal t5_9c_total_cust_unchange_cur_facetoface) {
		this.t5_9c_total_cust_unchange_cur_facetoface = t5_9c_total_cust_unchange_cur_facetoface;
	}
	public BigDecimal getT5_9d_total_cust_unchange_cur_non_facetoface() {
		return t5_9d_total_cust_unchange_cur_non_facetoface;
	}
	public void setT5_9d_total_cust_unchange_cur_non_facetoface(BigDecimal t5_9d_total_cust_unchange_cur_non_facetoface) {
		this.t5_9d_total_cust_unchange_cur_non_facetoface = t5_9d_total_cust_unchange_cur_non_facetoface;
	}
	public String getT5_10_name() {
		return t5_10_name;
	}
	public void setT5_10_name(String t5_10_name) {
		this.t5_10_name = t5_10_name;
	}
	public BigDecimal getT5_10a_total_pre_facetoface() {
		return t5_10a_total_pre_facetoface;
	}
	public void setT5_10a_total_pre_facetoface(BigDecimal t5_10a_total_pre_facetoface) {
		this.t5_10a_total_pre_facetoface = t5_10a_total_pre_facetoface;
	}
	public BigDecimal getT5_10b_total_pre_non_facetoface() {
		return t5_10b_total_pre_non_facetoface;
	}
	public void setT5_10b_total_pre_non_facetoface(BigDecimal t5_10b_total_pre_non_facetoface) {
		this.t5_10b_total_pre_non_facetoface = t5_10b_total_pre_non_facetoface;
	}
	public BigDecimal getT5_10c_total_cur_facetoface() {
		return t5_10c_total_cur_facetoface;
	}
	public void setT5_10c_total_cur_facetoface(BigDecimal t5_10c_total_cur_facetoface) {
		this.t5_10c_total_cur_facetoface = t5_10c_total_cur_facetoface;
	}
	public BigDecimal getT5_10d_total_cur_non_facetoface() {
		return t5_10d_total_cur_non_facetoface;
	}
	public void setT5_10d_total_cur_non_facetoface(BigDecimal t5_10d_total_cur_non_facetoface) {
		this.t5_10d_total_cur_non_facetoface = t5_10d_total_cur_non_facetoface;
	}
	public String getT5_11_name() {
		return t5_11_name;
	}
	public void setT5_11_name(String t5_11_name) {
		this.t5_11_name = t5_11_name;
	}
	public String getT5_11a_validation() {
		return t5_11a_validation;
	}
	public void setT5_11a_validation(String t5_11a_validation) {
		this.t5_11a_validation = t5_11a_validation;
	}
	public String getT5_11b_validation() {
		return t5_11b_validation;
	}
	public void setT5_11b_validation(String t5_11b_validation) {
		this.t5_11b_validation = t5_11b_validation;
	}
	public String getT5_11c_validation() {
		return t5_11c_validation;
	}
	public void setT5_11c_validation(String t5_11c_validation) {
		this.t5_11c_validation = t5_11c_validation;
	}
	public String getT5_11d_validation() {
		return t5_11d_validation;
	}
	public void setT5_11d_validation(String t5_11d_validation) {
		this.t5_11d_validation = t5_11d_validation;
	}
	public String getT5_12_name() {
		return t5_12_name;
	}
	public void setT5_12_name(String t5_12_name) {
		this.t5_12_name = t5_12_name;
	}
	public BigDecimal getT5_12a_customer_terminated_cdd_pre_facetoface() {
		return t5_12a_customer_terminated_cdd_pre_facetoface;
	}
	public void setT5_12a_customer_terminated_cdd_pre_facetoface(BigDecimal t5_12a_customer_terminated_cdd_pre_facetoface) {
		this.t5_12a_customer_terminated_cdd_pre_facetoface = t5_12a_customer_terminated_cdd_pre_facetoface;
	}
	public BigDecimal getT5_12b_customer_terminated_cdd_pre_non_facetoface() {
		return t5_12b_customer_terminated_cdd_pre_non_facetoface;
	}
	public void setT5_12b_customer_terminated_cdd_pre_non_facetoface(
			BigDecimal t5_12b_customer_terminated_cdd_pre_non_facetoface) {
		this.t5_12b_customer_terminated_cdd_pre_non_facetoface = t5_12b_customer_terminated_cdd_pre_non_facetoface;
	}
	public BigDecimal getT5_12c_customer_terminated_cdd_cur_facetoface() {
		return t5_12c_customer_terminated_cdd_cur_facetoface;
	}
	public void setT5_12c_customer_terminated_cdd_cur_facetoface(BigDecimal t5_12c_customer_terminated_cdd_cur_facetoface) {
		this.t5_12c_customer_terminated_cdd_cur_facetoface = t5_12c_customer_terminated_cdd_cur_facetoface;
	}
	public BigDecimal getT5_12d_customer_terminated_cdd_cur_non_facetoface() {
		return t5_12d_customer_terminated_cdd_cur_non_facetoface;
	}
	public void setT5_12d_customer_terminated_cdd_cur_non_facetoface(
			BigDecimal t5_12d_customer_terminated_cdd_cur_non_facetoface) {
		this.t5_12d_customer_terminated_cdd_cur_non_facetoface = t5_12d_customer_terminated_cdd_cur_non_facetoface;
	}
	public String getT5_13_name() {
		return t5_13_name;
	}
	public void setT5_13_name(String t5_13_name) {
		this.t5_13_name = t5_13_name;
	}
	public BigDecimal getT5_13a_customer_ceased_pre_facetoface() {
		return t5_13a_customer_ceased_pre_facetoface;
	}
	public void setT5_13a_customer_ceased_pre_facetoface(BigDecimal t5_13a_customer_ceased_pre_facetoface) {
		this.t5_13a_customer_ceased_pre_facetoface = t5_13a_customer_ceased_pre_facetoface;
	}
	public BigDecimal getT5_13b_customer_ceased_pre_non_facetoface() {
		return t5_13b_customer_ceased_pre_non_facetoface;
	}
	public void setT5_13b_customer_ceased_pre_non_facetoface(BigDecimal t5_13b_customer_ceased_pre_non_facetoface) {
		this.t5_13b_customer_ceased_pre_non_facetoface = t5_13b_customer_ceased_pre_non_facetoface;
	}
	public BigDecimal getT5_13c_customer_ceased_cur_facetoface() {
		return t5_13c_customer_ceased_cur_facetoface;
	}
	public void setT5_13c_customer_ceased_cur_facetoface(BigDecimal t5_13c_customer_ceased_cur_facetoface) {
		this.t5_13c_customer_ceased_cur_facetoface = t5_13c_customer_ceased_cur_facetoface;
	}
	public BigDecimal getT5_13d_customer_ceased_cur_non_facetoface() {
		return t5_13d_customer_ceased_cur_non_facetoface;
	}
	public void setT5_13d_customer_ceased_cur_non_facetoface(BigDecimal t5_13d_customer_ceased_cur_non_facetoface) {
		this.t5_13d_customer_ceased_cur_non_facetoface = t5_13d_customer_ceased_cur_non_facetoface;
	}
	public Date getT5_report_submit_date() {
		return t5_report_submit_date;
	}
	public void setT5_report_submit_date(Date t5_report_submit_date) {
		this.t5_report_submit_date = t5_report_submit_date;
	}
	public Date getT5_report_generate_date() {
		return t5_report_generate_date;
	}
	public void setT5_report_generate_date(Date t5_report_generate_date) {
		this.t5_report_generate_date = t5_report_generate_date;
	}
	public Date getT5_report_due_date() {
		return t5_report_due_date;
	}
	public void setT5_report_due_date(Date t5_report_due_date) {
		this.t5_report_due_date = t5_report_due_date;
	}
	public String getT5_nil_report_flg() {
		return t5_nil_report_flg;
	}
	public void setT5_nil_report_flg(String t5_nil_report_flg) {
		this.t5_nil_report_flg = t5_nil_report_flg;
	}
	public Date getT5_report_from_date() {
		return t5_report_from_date;
	}
	public void setT5_report_from_date(Date t5_report_from_date) {
		this.t5_report_from_date = t5_report_from_date;
	}
	public Date getT5_report_to_date() {
		return t5_report_to_date;
	}
	public void setT5_report_to_date(Date t5_report_to_date) {
		this.t5_report_to_date = t5_report_to_date;
	}
	public String getT5_frequency() {
		return t5_frequency;
	}
	public void setT5_frequency(String t5_frequency) {
		this.t5_frequency = t5_frequency;
	}
	public T5Report(String t5_1_name, BigDecimal t5_1a_total_cust_upgrade_pre_facetoface,
			BigDecimal t5_1b_total_cust_upgrade_pre_non_facetoface, BigDecimal t5_1c_total_cust_upgrade_cur_facetoface,
			BigDecimal t5_1d_total_cust_upgrade_cur_non_facetoface, String t5_2_name,
			BigDecimal t5_2a_high_to_med_pre_facetoface, BigDecimal t5_2b_high_to_med_pre_non_facetoface,
			BigDecimal t5_2c_high_to_med_cur_facetoface, BigDecimal t5_2d_high_to_med_cur_non_facetoface,
			String t5_3_name, BigDecimal t5_3a_high_to_low_pre_facetoface,
			BigDecimal t5_3b_high_to_low_pre_non_facetoface, BigDecimal t5_3c_high_to_low_cur_facetoface,
			BigDecimal t5_3d_high_to_low_cur_non_facetoface, String t5_4_name,
			BigDecimal t5_4a_med_to_low_pre_facetoface, BigDecimal t5_4b_med_to_low_pre_non_facetoface,
			BigDecimal t5_4c_med_to_low_cur_facetoface, BigDecimal t5_4d_med_to_low_cur_non_facetoface,
			String t5_5_name, BigDecimal t5_5a_total_cust_downgrade_pre_facetoface,
			BigDecimal t5_5b_total_cust_downgrade_pre_non_facetoface,
			BigDecimal t5_5c_total_cust_downgrade_cur_facetoface,
			BigDecimal t5_5d_total_cust_downgrade_cur_non_facetoface, String t5_6_name,
			BigDecimal t5_6a_low_to_med_pre_facetoface, BigDecimal t5_6b_low_to_med_pre_non_facetoface,
			BigDecimal t5_6c_low_to_med_cur_facetoface, BigDecimal t5_6d_low_to_med_cur_non_facetoface,
			String t5_7_name, BigDecimal t5_7a_low_to_high_pre_facetoface,
			BigDecimal t5_7b_low_to_high_pre_non_facetoface, BigDecimal t5_7c_low_to_high_cur_facetoface,
			BigDecimal t5_7d_low_to_high_cur_non_facetoface, String t5_8_name,
			BigDecimal t5_8a_med_to_high_pre_facetoface, BigDecimal t5_8b_med_to_high_pre_non_facetoface,
			BigDecimal t5_8c_med_to_high_cur_facetoface, BigDecimal t5_8d_med_to_high_cur_non_facetoface,
			String t5_9_name, BigDecimal t5_9a_total_cust_unchange_pre_facetoface,
			BigDecimal t5_9b_total_cust_unchange_pre_non_facetoface,
			BigDecimal t5_9c_total_cust_unchange_cur_facetoface,
			BigDecimal t5_9d_total_cust_unchange_cur_non_facetoface, String t5_10_name,
			BigDecimal t5_10a_total_pre_facetoface, BigDecimal t5_10b_total_pre_non_facetoface,
			BigDecimal t5_10c_total_cur_facetoface, BigDecimal t5_10d_total_cur_non_facetoface, String t5_11_name,
			String t5_11a_validation, String t5_11b_validation, String t5_11c_validation,
			String t5_11d_validation, String t5_12_name, BigDecimal t5_12a_customer_terminated_cdd_pre_facetoface,
			BigDecimal t5_12b_customer_terminated_cdd_pre_non_facetoface,
			BigDecimal t5_12c_customer_terminated_cdd_cur_facetoface,
			BigDecimal t5_12d_customer_terminated_cdd_cur_non_facetoface, String t5_13_name,
			BigDecimal t5_13a_customer_ceased_pre_facetoface, BigDecimal t5_13b_customer_ceased_pre_non_facetoface,
			BigDecimal t5_13c_customer_ceased_cur_facetoface, BigDecimal t5_13d_customer_ceased_cur_non_facetoface,
			Date t5_report_submit_date, Date t5_report_generate_date, Date t5_report_due_date, String t5_nil_report_flg,
			Date t5_report_from_date, Date t5_report_to_date, String t5_frequency) {
		super();
		this.t5_1_name = t5_1_name;
		this.t5_1a_total_cust_upgrade_pre_facetoface = t5_1a_total_cust_upgrade_pre_facetoface;
		this.t5_1b_total_cust_upgrade_pre_non_facetoface = t5_1b_total_cust_upgrade_pre_non_facetoface;
		this.t5_1c_total_cust_upgrade_cur_facetoface = t5_1c_total_cust_upgrade_cur_facetoface;
		this.t5_1d_total_cust_upgrade_cur_non_facetoface = t5_1d_total_cust_upgrade_cur_non_facetoface;
		this.t5_2_name = t5_2_name;
		this.t5_2a_high_to_med_pre_facetoface = t5_2a_high_to_med_pre_facetoface;
		this.t5_2b_high_to_med_pre_non_facetoface = t5_2b_high_to_med_pre_non_facetoface;
		this.t5_2c_high_to_med_cur_facetoface = t5_2c_high_to_med_cur_facetoface;
		this.t5_2d_high_to_med_cur_non_facetoface = t5_2d_high_to_med_cur_non_facetoface;
		this.t5_3_name = t5_3_name;
		this.t5_3a_high_to_low_pre_facetoface = t5_3a_high_to_low_pre_facetoface;
		this.t5_3b_high_to_low_pre_non_facetoface = t5_3b_high_to_low_pre_non_facetoface;
		this.t5_3c_high_to_low_cur_facetoface = t5_3c_high_to_low_cur_facetoface;
		this.t5_3d_high_to_low_cur_non_facetoface = t5_3d_high_to_low_cur_non_facetoface;
		this.t5_4_name = t5_4_name;
		this.t5_4a_med_to_low_pre_facetoface = t5_4a_med_to_low_pre_facetoface;
		this.t5_4b_med_to_low_pre_non_facetoface = t5_4b_med_to_low_pre_non_facetoface;
		this.t5_4c_med_to_low_cur_facetoface = t5_4c_med_to_low_cur_facetoface;
		this.t5_4d_med_to_low_cur_non_facetoface = t5_4d_med_to_low_cur_non_facetoface;
		this.t5_5_name = t5_5_name;
		this.t5_5a_total_cust_downgrade_pre_facetoface = t5_5a_total_cust_downgrade_pre_facetoface;
		this.t5_5b_total_cust_downgrade_pre_non_facetoface = t5_5b_total_cust_downgrade_pre_non_facetoface;
		this.t5_5c_total_cust_downgrade_cur_facetoface = t5_5c_total_cust_downgrade_cur_facetoface;
		this.t5_5d_total_cust_downgrade_cur_non_facetoface = t5_5d_total_cust_downgrade_cur_non_facetoface;
		this.t5_6_name = t5_6_name;
		this.t5_6a_low_to_med_pre_facetoface = t5_6a_low_to_med_pre_facetoface;
		this.t5_6b_low_to_med_pre_non_facetoface = t5_6b_low_to_med_pre_non_facetoface;
		this.t5_6c_low_to_med_cur_facetoface = t5_6c_low_to_med_cur_facetoface;
		this.t5_6d_low_to_med_cur_non_facetoface = t5_6d_low_to_med_cur_non_facetoface;
		this.t5_7_name = t5_7_name;
		this.t5_7a_low_to_high_pre_facetoface = t5_7a_low_to_high_pre_facetoface;
		this.t5_7b_low_to_high_pre_non_facetoface = t5_7b_low_to_high_pre_non_facetoface;
		this.t5_7c_low_to_high_cur_facetoface = t5_7c_low_to_high_cur_facetoface;
		this.t5_7d_low_to_high_cur_non_facetoface = t5_7d_low_to_high_cur_non_facetoface;
		this.t5_8_name = t5_8_name;
		this.t5_8a_med_to_high_pre_facetoface = t5_8a_med_to_high_pre_facetoface;
		this.t5_8b_med_to_high_pre_non_facetoface = t5_8b_med_to_high_pre_non_facetoface;
		this.t5_8c_med_to_high_cur_facetoface = t5_8c_med_to_high_cur_facetoface;
		this.t5_8d_med_to_high_cur_non_facetoface = t5_8d_med_to_high_cur_non_facetoface;
		this.t5_9_name = t5_9_name;
		this.t5_9a_total_cust_unchange_pre_facetoface = t5_9a_total_cust_unchange_pre_facetoface;
		this.t5_9b_total_cust_unchange_pre_non_facetoface = t5_9b_total_cust_unchange_pre_non_facetoface;
		this.t5_9c_total_cust_unchange_cur_facetoface = t5_9c_total_cust_unchange_cur_facetoface;
		this.t5_9d_total_cust_unchange_cur_non_facetoface = t5_9d_total_cust_unchange_cur_non_facetoface;
		this.t5_10_name = t5_10_name;
		this.t5_10a_total_pre_facetoface = t5_10a_total_pre_facetoface;
		this.t5_10b_total_pre_non_facetoface = t5_10b_total_pre_non_facetoface;
		this.t5_10c_total_cur_facetoface = t5_10c_total_cur_facetoface;
		this.t5_10d_total_cur_non_facetoface = t5_10d_total_cur_non_facetoface;
		this.t5_11_name = t5_11_name;
		this.t5_11a_validation = t5_11a_validation;
		this.t5_11b_validation = t5_11b_validation;
		this.t5_11c_validation = t5_11c_validation;
		this.t5_11d_validation = t5_11d_validation;
		this.t5_12_name = t5_12_name;
		this.t5_12a_customer_terminated_cdd_pre_facetoface = t5_12a_customer_terminated_cdd_pre_facetoface;
		this.t5_12b_customer_terminated_cdd_pre_non_facetoface = t5_12b_customer_terminated_cdd_pre_non_facetoface;
		this.t5_12c_customer_terminated_cdd_cur_facetoface = t5_12c_customer_terminated_cdd_cur_facetoface;
		this.t5_12d_customer_terminated_cdd_cur_non_facetoface = t5_12d_customer_terminated_cdd_cur_non_facetoface;
		this.t5_13_name = t5_13_name;
		this.t5_13a_customer_ceased_pre_facetoface = t5_13a_customer_ceased_pre_facetoface;
		this.t5_13b_customer_ceased_pre_non_facetoface = t5_13b_customer_ceased_pre_non_facetoface;
		this.t5_13c_customer_ceased_cur_facetoface = t5_13c_customer_ceased_cur_facetoface;
		this.t5_13d_customer_ceased_cur_non_facetoface = t5_13d_customer_ceased_cur_non_facetoface;
		this.t5_report_submit_date = t5_report_submit_date;
		this.t5_report_generate_date = t5_report_generate_date;
		this.t5_report_due_date = t5_report_due_date;
		this.t5_nil_report_flg = t5_nil_report_flg;
		this.t5_report_from_date = t5_report_from_date;
		this.t5_report_to_date = t5_report_to_date;
		this.t5_frequency = t5_frequency;
	}
	
	public T5Report() {}
	

	
	

}

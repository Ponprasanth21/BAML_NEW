package com.bornfire.entity.t4;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T4_CUSTOMER_PROFILING_SUMMARY_TABLE")
public class T4ReportSum {
	
	
	private BigDecimal	instance_code;
	private String	instance_name;
	private BigDecimal	low_risk_face_to_face;
	private BigDecimal	low_risk_non_face_to_face;
	private BigDecimal	med_risk_face_to_face;
	private BigDecimal	med_risk_non_face_to_face;
	private BigDecimal	high_risk_face_to_face;
	private BigDecimal	high_risk_non_face_to_face;
	private BigDecimal	rejected_risk_face_to_face;
	private BigDecimal	rejected_risk_non_face_to_face;
	@Id
	private Date	report_date;
	private Date	rep_period_from;
	private Date	rep_period_to;
	private String	rep_freq;
	private String	nil_report_flg;
	
	
	
	
	
	
	
	public BigDecimal getInstance_code() {
		return instance_code;
	}
	public String getInstance_name() {
		return instance_name;
	}
	public BigDecimal getLow_risk_face_to_face() {
		return low_risk_face_to_face;
	}
	public BigDecimal getLow_risk_non_face_to_face() {
		return low_risk_non_face_to_face;
	}
	public BigDecimal getMed_risk_face_to_face() {
		return med_risk_face_to_face;
	}
	public BigDecimal getMed_risk_non_face_to_face() {
		return med_risk_non_face_to_face;
	}
	public BigDecimal getHigh_risk_face_to_face() {
		return high_risk_face_to_face;
	}
	public BigDecimal getHigh_risk_non_face_to_face() {
		return high_risk_non_face_to_face;
	}
	public BigDecimal getRejected_risk_face_to_face() {
		return rejected_risk_face_to_face;
	}
	public BigDecimal getRejected_risk_non_face_to_face() {
		return rejected_risk_non_face_to_face;
	}
	public Date getReport_date() {
		return report_date;
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
	public void setInstance_code(BigDecimal instance_code) {
		this.instance_code = instance_code;
	}
	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}
	public void setLow_risk_face_to_face(BigDecimal low_risk_face_to_face) {
		this.low_risk_face_to_face = low_risk_face_to_face;
	}
	public void setLow_risk_non_face_to_face(BigDecimal low_risk_non_face_to_face) {
		this.low_risk_non_face_to_face = low_risk_non_face_to_face;
	}
	public void setMed_risk_face_to_face(BigDecimal med_risk_face_to_face) {
		this.med_risk_face_to_face = med_risk_face_to_face;
	}
	public void setMed_risk_non_face_to_face(BigDecimal med_risk_non_face_to_face) {
		this.med_risk_non_face_to_face = med_risk_non_face_to_face;
	}
	public void setHigh_risk_face_to_face(BigDecimal high_risk_face_to_face) {
		this.high_risk_face_to_face = high_risk_face_to_face;
	}
	public void setHigh_risk_non_face_to_face(BigDecimal high_risk_non_face_to_face) {
		this.high_risk_non_face_to_face = high_risk_non_face_to_face;
	}
	public void setRejected_risk_face_to_face(BigDecimal rejected_risk_face_to_face) {
		this.rejected_risk_face_to_face = rejected_risk_face_to_face;
	}
	public void setRejected_risk_non_face_to_face(BigDecimal rejected_risk_non_face_to_face) {
		this.rejected_risk_non_face_to_face = rejected_risk_non_face_to_face;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
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
	
	
	public T4ReportSum(BigDecimal instance_code, String instance_name, BigDecimal low_risk_face_to_face,
			BigDecimal low_risk_non_face_to_face, BigDecimal med_risk_face_to_face,
			BigDecimal med_risk_non_face_to_face, BigDecimal high_risk_face_to_face,
			BigDecimal high_risk_non_face_to_face, BigDecimal rejected_risk_face_to_face,
			BigDecimal rejected_risk_non_face_to_face, Date report_date, Date rep_period_from, Date rep_period_to,
			String rep_freq, String nil_report_flg) {
		super();
		this.instance_code = instance_code;
		this.instance_name = instance_name;
		this.low_risk_face_to_face = low_risk_face_to_face;
		this.low_risk_non_face_to_face = low_risk_non_face_to_face;
		this.med_risk_face_to_face = med_risk_face_to_face;
		this.med_risk_non_face_to_face = med_risk_non_face_to_face;
		this.high_risk_face_to_face = high_risk_face_to_face;
		this.high_risk_non_face_to_face = high_risk_non_face_to_face;
		this.rejected_risk_face_to_face = rejected_risk_face_to_face;
		this.rejected_risk_non_face_to_face = rejected_risk_non_face_to_face;
		this.report_date = report_date;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
		this.rep_freq = rep_freq;
		this.nil_report_flg = nil_report_flg;
	}
	
	
	
	public T4ReportSum() {
		
		super();
		// TODO Auto-generated constructor stub
		
		
		
		
	}


}
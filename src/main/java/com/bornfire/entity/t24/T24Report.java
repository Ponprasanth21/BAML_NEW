package com.bornfire.entity.t24;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "T24_INT_ADT_AML_CFT_TABLE")
public class T24Report {
	
	private String	d1a_int_adt_ho;
	private String	d2a_int_adt_bo;
	private String	d3a_noa_under_int_adt;
	private String	d4a_det_of_areas;
	private String	d5a_note;
	
	private BigDecimal	c1d_int_adt_ho_num;
	private BigDecimal	c2d_int_adt_bo_num;
	private BigDecimal	c3d_numa_unper_int_adt_num;
	private BigDecimal	c4d_det_of_areas_num;
	private BigDecimal	c5d_note_num;
	private BigDecimal	c1e_int_adt_ho_num_grade;
	private BigDecimal	c2e_int_adt_bo_num_grade;
	private BigDecimal	c3e_num_gradea_unper_int_adt_num_grade;
	private BigDecimal	c4e_det_of_areas_num_grade;
	private BigDecimal	c5e_note_num_grade;
	private String	report_code;
	private String	report_name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Id
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
	private String	arch_flg;
	private String entity_flg;
	private String modify_flg;
	private String del_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date entry_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date verify_time;
	
	
	
	
	public String getD1a_int_adt_ho() {
		return d1a_int_adt_ho;
	}
	public void setD1a_int_adt_ho(String d1a_int_adt_ho) {
		this.d1a_int_adt_ho = d1a_int_adt_ho;
	}
	public String getD2a_int_adt_bo() {
		return d2a_int_adt_bo;
	}
	public void setD2a_int_adt_bo(String d2a_int_adt_bo) {
		this.d2a_int_adt_bo = d2a_int_adt_bo;
	}
	public String getD3a_noa_under_int_adt() {
		return d3a_noa_under_int_adt;
	}
	public void setD3a_noa_under_int_adt(String d3a_noa_under_int_adt) {
		this.d3a_noa_under_int_adt = d3a_noa_under_int_adt;
	}
	public String getD4a_det_of_areas() {
		return d4a_det_of_areas;
	}
	public void setD4a_det_of_areas(String d4a_det_of_areas) {
		this.d4a_det_of_areas = d4a_det_of_areas;
	}
	public String getD5a_note() {
		return d5a_note;
	}
	public void setD5a_note(String d5a_note) {
		this.d5a_note = d5a_note;
	}
	
	public BigDecimal getC1d_int_adt_ho_num() {
		return c1d_int_adt_ho_num;
	}
	public void setC1d_int_adt_ho_num(BigDecimal c1d_int_adt_ho_num) {
		this.c1d_int_adt_ho_num = c1d_int_adt_ho_num;
	}
	public BigDecimal getC2d_int_adt_bo_num() {
		return c2d_int_adt_bo_num;
	}
	public void setC2d_int_adt_bo_num(BigDecimal c2d_int_adt_bo_num) {
		this.c2d_int_adt_bo_num = c2d_int_adt_bo_num;
	}
	public BigDecimal getC3d_numa_unper_int_adt_num() {
		return c3d_numa_unper_int_adt_num;
	}
	public void setC3d_numa_unper_int_adt_num(BigDecimal c3d_numa_unper_int_adt_num) {
		this.c3d_numa_unper_int_adt_num = c3d_numa_unper_int_adt_num;
	}
	public BigDecimal getC4d_det_of_areas_num() {
		return c4d_det_of_areas_num;
	}
	public void setC4d_det_of_areas_num(BigDecimal c4d_det_of_areas_num) {
		this.c4d_det_of_areas_num = c4d_det_of_areas_num;
	}
	public BigDecimal getC5d_note_num() {
		return c5d_note_num;
	}
	public void setC5d_note_num(BigDecimal c5d_note_num) {
		this.c5d_note_num = c5d_note_num;
	}
	public BigDecimal getC1e_int_adt_ho_num_grade() {
		return c1e_int_adt_ho_num_grade;
	}
	public void setC1e_int_adt_ho_num_grade(BigDecimal c1e_int_adt_ho_num_grade) {
		this.c1e_int_adt_ho_num_grade = c1e_int_adt_ho_num_grade;
	}
	public BigDecimal getC2e_int_adt_bo_num_grade() {
		return c2e_int_adt_bo_num_grade;
	}
	public void setC2e_int_adt_bo_num_grade(BigDecimal c2e_int_adt_bo_num_grade) {
		this.c2e_int_adt_bo_num_grade = c2e_int_adt_bo_num_grade;
	}
	public BigDecimal getC3e_num_gradea_unper_int_adt_num_grade() {
		return c3e_num_gradea_unper_int_adt_num_grade;
	}
	public void setC3e_num_gradea_unper_int_adt_num_grade(BigDecimal c3e_num_gradea_unper_int_adt_num_grade) {
		this.c3e_num_gradea_unper_int_adt_num_grade = c3e_num_gradea_unper_int_adt_num_grade;
	}
	public BigDecimal getC4e_det_of_areas_num_grade() {
		return c4e_det_of_areas_num_grade;
	}
	public void setC4e_det_of_areas_num_grade(BigDecimal c4e_det_of_areas_num_grade) {
		this.c4e_det_of_areas_num_grade = c4e_det_of_areas_num_grade;
	}
	public BigDecimal getC5e_note_num_grade() {
		return c5e_note_num_grade;
	}
	public void setC5e_note_num_grade(BigDecimal c5e_note_num_grade) {
		this.c5e_note_num_grade = c5e_note_num_grade;
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
	public Date getReport_due_date() {
		return report_due_date;
	}
	public void setReport_due_date(Date report_due_date) {
		this.report_due_date = report_due_date;
	}
	public Date getRep_submit_date() {
		return rep_submit_date;
	}
	public void setRep_submit_date(Date rep_submit_date) {
		this.rep_submit_date = rep_submit_date;
	}
	public Date getRep_period_from() {
		return rep_period_from;
	}
	public void setRep_period_from(Date rep_period_from) {
		this.rep_period_from = rep_period_from;
	}
	public Date getRep_period_to() {
		return rep_period_to;
	}
	public void setRep_period_to(Date rep_period_to) {
		this.rep_period_to = rep_period_to;
	}
	public String getRep_freq() {
		return rep_freq;
	}
	public void setRep_freq(String rep_freq) {
		this.rep_freq = rep_freq;
	}
	public String getNil_report_flg() {
		return nil_report_flg;
	}
	public void setNil_report_flg(String nil_report_flg) {
		this.nil_report_flg = nil_report_flg;
	}
	public String getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(String arch_flg) {
		this.arch_flg = arch_flg;
	}
	
	
	
	
	
	
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public Date getVerify_time() {
		return verify_time;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public T24Report(String d1a_int_adt_ho, String d2a_int_adt_bo, String d3a_noa_under_int_adt,
			String d4a_det_of_areas, String d5a_note, BigDecimal c1d_int_adt_ho_num, BigDecimal c2d_int_adt_bo_num,
			BigDecimal c3d_numa_unper_int_adt_num, BigDecimal c4d_det_of_areas_num, BigDecimal c5d_note_num,
			BigDecimal c1e_int_adt_ho_num_grade, BigDecimal c2e_int_adt_bo_num_grade,
			BigDecimal c3e_num_gradea_unper_int_adt_num_grade, BigDecimal c4e_det_of_areas_num_grade,
			BigDecimal c5e_note_num_grade, String report_code, String report_name, Date report_date,
			Date report_due_date, Date rep_submit_date, Date rep_period_from, Date rep_period_to, String rep_freq,
			String nil_report_flg, String arch_flg, String entity_flg, String modify_flg, String del_flg,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time) {
		super();
		this.d1a_int_adt_ho = d1a_int_adt_ho;
		this.d2a_int_adt_bo = d2a_int_adt_bo;
		this.d3a_noa_under_int_adt = d3a_noa_under_int_adt;
		this.d4a_det_of_areas = d4a_det_of_areas;
		this.d5a_note = d5a_note;
		
		this.c1d_int_adt_ho_num = c1d_int_adt_ho_num;
		this.c2d_int_adt_bo_num = c2d_int_adt_bo_num;
		this.c3d_numa_unper_int_adt_num = c3d_numa_unper_int_adt_num;
		this.c4d_det_of_areas_num = c4d_det_of_areas_num;
		this.c5d_note_num = c5d_note_num;
		this.c1e_int_adt_ho_num_grade = c1e_int_adt_ho_num_grade;
		this.c2e_int_adt_bo_num_grade = c2e_int_adt_bo_num_grade;
		this.c3e_num_gradea_unper_int_adt_num_grade = c3e_num_gradea_unper_int_adt_num_grade;
		this.c4e_det_of_areas_num_grade = c4e_det_of_areas_num_grade;
		this.c5e_note_num_grade = c5e_note_num_grade;
		this.report_code = report_code;
		this.report_name = report_name;
		this.report_date = report_date;
		this.report_due_date = report_due_date;
		this.rep_submit_date = rep_submit_date;
		this.rep_period_from = rep_period_from;
		this.rep_period_to = rep_period_to;
		this.rep_freq = rep_freq;
		this.nil_report_flg = nil_report_flg;
		this.arch_flg = arch_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}
	public T24Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}

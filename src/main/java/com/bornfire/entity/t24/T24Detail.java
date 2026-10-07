package com.bornfire.entity.t24;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T24_INT_ADT_AML_CFT_DETAILS")
public class T24Detail {
	
	
	private BigDecimal	pre_ho_num;
	private BigDecimal	pre_bo_num;
	private BigDecimal	pre_noa_int_adt_num;
	private String	pre_det_area_covered;
	private String	pre_note_areas_checks;
	private BigDecimal	pre_ho_num_grade;
	private BigDecimal	pre_bo_num_grade;
	private BigDecimal	pre_noa_int_adt_num_grade;
	private String	pre_det_area_covered_grade;
	private String	pre_note_areas_checks_grade;
	private BigDecimal	cur_ho_num;
	private BigDecimal	cur_bo_num;
	private BigDecimal	cur_noa_int_adt_num;
	private String	cur_det_area_covered;
	private String	cur_note_areas_checks;
	private BigDecimal	cur_ho_num_grade;
	private BigDecimal	cur_bo_num_grade;
	private BigDecimal	cur_noa_int_adt_num_grade;
	private String	cur_det_area_covered_grade;
	private String	cur_note_areas_checks_grade;
	private String	qtr_flg;
	private String	entity_flg;
	private String	del_flg;
	private String	modify_flg;
	private Date	entry_date;
	private Date	modify_date;
	private Date	verify_date;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private String	report_code;
	private String	report_name;
	@Id
	private Date	report_date;
	private String	arch_flg;
	public BigDecimal getPre_ho_num() {
		return pre_ho_num;
	}
	public void setPre_ho_num(BigDecimal pre_ho_num) {
		this.pre_ho_num = pre_ho_num;
	}
	public BigDecimal getPre_bo_num() {
		return pre_bo_num;
	}
	public void setPre_bo_num(BigDecimal pre_bo_num) {
		this.pre_bo_num = pre_bo_num;
	}
	public BigDecimal getPre_noa_int_adt_num() {
		return pre_noa_int_adt_num;
	}
	public void setPre_noa_int_adt_num(BigDecimal pre_noa_int_adt_num) {
		this.pre_noa_int_adt_num = pre_noa_int_adt_num;
	}
	public String getPre_det_area_covered() {
		return pre_det_area_covered;
	}
	public void setPre_det_area_covered(String pre_det_area_covered) {
		this.pre_det_area_covered = pre_det_area_covered;
	}
	public String getPre_note_areas_checks() {
		return pre_note_areas_checks;
	}
	public void setPre_note_areas_checks(String pre_note_areas_checks) {
		this.pre_note_areas_checks = pre_note_areas_checks;
	}
	public BigDecimal getPre_ho_num_grade() {
		return pre_ho_num_grade;
	}
	public void setPre_ho_num_grade(BigDecimal pre_ho_num_grade) {
		this.pre_ho_num_grade = pre_ho_num_grade;
	}
	public BigDecimal getPre_bo_num_grade() {
		return pre_bo_num_grade;
	}
	public void setPre_bo_num_grade(BigDecimal pre_bo_num_grade) {
		this.pre_bo_num_grade = pre_bo_num_grade;
	}
	public BigDecimal getPre_noa_int_adt_num_grade() {
		return pre_noa_int_adt_num_grade;
	}
	public void setPre_noa_int_adt_num_grade(BigDecimal pre_noa_int_adt_num_grade) {
		this.pre_noa_int_adt_num_grade = pre_noa_int_adt_num_grade;
	}
	public String getPre_det_area_covered_grade() {
		return pre_det_area_covered_grade;
	}
	public void setPre_det_area_covered_grade(String pre_det_area_covered_grade) {
		this.pre_det_area_covered_grade = pre_det_area_covered_grade;
	}
	public String getPre_note_areas_checks_grade() {
		return pre_note_areas_checks_grade;
	}
	public void setPre_note_areas_checks_grade(String pre_note_areas_checks_grade) {
		this.pre_note_areas_checks_grade = pre_note_areas_checks_grade;
	}
	public BigDecimal getCur_ho_num() {
		return cur_ho_num;
	}
	public void setCur_ho_num(BigDecimal cur_ho_num) {
		this.cur_ho_num = cur_ho_num;
	}
	public BigDecimal getCur_bo_num() {
		return cur_bo_num;
	}
	public void setCur_bo_num(BigDecimal cur_bo_num) {
		this.cur_bo_num = cur_bo_num;
	}
	public BigDecimal getCur_noa_int_adt_num() {
		return cur_noa_int_adt_num;
	}
	public void setCur_noa_int_adt_num(BigDecimal cur_noa_int_adt_num) {
		this.cur_noa_int_adt_num = cur_noa_int_adt_num;
	}
	public String getCur_det_area_covered() {
		return cur_det_area_covered;
	}
	public void setCur_det_area_covered(String cur_det_area_covered) {
		this.cur_det_area_covered = cur_det_area_covered;
	}
	public String getCur_note_areas_checks() {
		return cur_note_areas_checks;
	}
	public void setCur_note_areas_checks(String cur_note_areas_checks) {
		this.cur_note_areas_checks = cur_note_areas_checks;
	}
	public BigDecimal getCur_ho_num_grade() {
		return cur_ho_num_grade;
	}
	public void setCur_ho_num_grade(BigDecimal cur_ho_num_grade) {
		this.cur_ho_num_grade = cur_ho_num_grade;
	}
	public BigDecimal getCur_bo_num_grade() {
		return cur_bo_num_grade;
	}
	public void setCur_bo_num_grade(BigDecimal cur_bo_num_grade) {
		this.cur_bo_num_grade = cur_bo_num_grade;
	}
	public BigDecimal getCur_noa_int_adt_num_grade() {
		return cur_noa_int_adt_num_grade;
	}
	public void setCur_noa_int_adt_num_grade(BigDecimal cur_noa_int_adt_num_grade) {
		this.cur_noa_int_adt_num_grade = cur_noa_int_adt_num_grade;
	}
	public String getCur_det_area_covered_grade() {
		return cur_det_area_covered_grade;
	}
	public void setCur_det_area_covered_grade(String cur_det_area_covered_grade) {
		this.cur_det_area_covered_grade = cur_det_area_covered_grade;
	}
	public String getCur_note_areas_checks_grade() {
		return cur_note_areas_checks_grade;
	}
	public void setCur_note_areas_checks_grade(String cur_note_areas_checks_grade) {
		this.cur_note_areas_checks_grade = cur_note_areas_checks_grade;
	}
	public String getQtr_flg() {
		return qtr_flg;
	}
	public void setQtr_flg(String qtr_flg) {
		this.qtr_flg = qtr_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Date getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
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
	public String getArch_flg() {
		return arch_flg;
	}
	public void setArch_flg(String arch_flg) {
		this.arch_flg = arch_flg;
	}
	public T24Detail(BigDecimal pre_ho_num, BigDecimal pre_bo_num, BigDecimal pre_noa_int_adt_num,
			String pre_det_area_covered, String pre_note_areas_checks, BigDecimal pre_ho_num_grade,
			BigDecimal pre_bo_num_grade, BigDecimal pre_noa_int_adt_num_grade, String pre_det_area_covered_grade,
			String pre_note_areas_checks_grade, BigDecimal cur_ho_num, BigDecimal cur_bo_num,
			BigDecimal cur_noa_int_adt_num, String cur_det_area_covered, String cur_note_areas_checks,
			BigDecimal cur_ho_num_grade, BigDecimal cur_bo_num_grade, BigDecimal cur_noa_int_adt_num_grade,
			String cur_det_area_covered_grade, String cur_note_areas_checks_grade, String qtr_flg, String entity_flg,
			String del_flg, String modify_flg, Date entry_date, Date modify_date, Date verify_date, String entry_user,
			String modify_user, String verify_user, String report_code, String report_name, Date report_date,
			String arch_flg) {
		super();
		this.pre_ho_num = pre_ho_num;
		this.pre_bo_num = pre_bo_num;
		this.pre_noa_int_adt_num = pre_noa_int_adt_num;
		this.pre_det_area_covered = pre_det_area_covered;
		this.pre_note_areas_checks = pre_note_areas_checks;
		this.pre_ho_num_grade = pre_ho_num_grade;
		this.pre_bo_num_grade = pre_bo_num_grade;
		this.pre_noa_int_adt_num_grade = pre_noa_int_adt_num_grade;
		this.pre_det_area_covered_grade = pre_det_area_covered_grade;
		this.pre_note_areas_checks_grade = pre_note_areas_checks_grade;
		this.cur_ho_num = cur_ho_num;
		this.cur_bo_num = cur_bo_num;
		this.cur_noa_int_adt_num = cur_noa_int_adt_num;
		this.cur_det_area_covered = cur_det_area_covered;
		this.cur_note_areas_checks = cur_note_areas_checks;
		this.cur_ho_num_grade = cur_ho_num_grade;
		this.cur_bo_num_grade = cur_bo_num_grade;
		this.cur_noa_int_adt_num_grade = cur_noa_int_adt_num_grade;
		this.cur_det_area_covered_grade = cur_det_area_covered_grade;
		this.cur_note_areas_checks_grade = cur_note_areas_checks_grade;
		this.qtr_flg = qtr_flg;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.report_code = report_code;
		this.report_name = report_name;
		this.report_date = report_date;
		this.arch_flg = arch_flg;
	}
	
	public T24Detail() {}

	

}

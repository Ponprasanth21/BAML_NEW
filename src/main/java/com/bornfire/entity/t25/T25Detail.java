package com.bornfire.entity.t25;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T25_AMLCFT_APPL_DETAIL")
public class T25Detail {

	private String ho_pre_applicable;
	private String bo_pre_applicable;
	private String nbti_pre_applicable;
	private String ho_cur_applicable;
	private String bo_cur_applicable;
	private String nbti_cur_applicable;
	private String qtr_flg;
	private String entity_flg;
	private String del_flg;
	private String modify_flg;
	private Date entry_date;
	private Date modify_date;
	private String verify_date;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private String report_code;
	private String report_name;
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date report_date;
	private String arch_flg;

	public String getHo_pre_applicable() {
		return ho_pre_applicable;
	}

	public void setHo_pre_applicable(String ho_pre_applicable) {
		this.ho_pre_applicable = ho_pre_applicable;
	}

	public String getBo_pre_applicable() {
		return bo_pre_applicable;
	}

	public void setBo_pre_applicable(String bo_pre_applicable) {
		this.bo_pre_applicable = bo_pre_applicable;
	}

	public String getNbti_pre_applicable() {
		return nbti_pre_applicable;
	}

	public void setNbti_pre_applicable(String nbti_pre_applicable) {
		this.nbti_pre_applicable = nbti_pre_applicable;
	}

	public String getHo_cur_applicable() {
		return ho_cur_applicable;
	}

	public void setHo_cur_applicable(String ho_cur_applicable) {
		this.ho_cur_applicable = ho_cur_applicable;
	}

	public String getBo_cur_applicable() {
		return bo_cur_applicable;
	}

	public void setBo_cur_applicable(String bo_cur_applicable) {
		this.bo_cur_applicable = bo_cur_applicable;
	}

	public String getNbti_cur_applicable() {
		return nbti_cur_applicable;
	}

	public void setNbti_cur_applicable(String nbti_cur_applicable) {
		this.nbti_cur_applicable = nbti_cur_applicable;
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

	public String getVerify_date() {
		return verify_date;
	}

	public void setVerify_date(String verify_date) {
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

	public T25Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T25Detail(Date parse) {
		this.report_date = report_date;
	}

}

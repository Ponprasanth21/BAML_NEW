package com.bornfire.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="BAML_STR")
public class BAML_STR {
	
	@Id
	private String str_ref_no;
	private String	from_rptr_name;
	private String	from_rptr_position;
	private BigDecimal	from_rpt_extn_ph;
	private String	to_mlro_dmlro;
	private String	cust_client_name;
	private String	cust_client_addr;
	private BigDecimal	cust_client_tel;
	private BigDecimal	cust_client_fax;
	private BigDecimal	cust_client_mob;
	private String	cust_client_occp;
	private String	cust_client_employer;
	private String	cust_client_id_card_no;
	private String	cust_client_otr_id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	cust_client_date;
	private String	cust_client_place_of_birth;
	private String	tran_ref;
	private BigDecimal	tran_amount;
	private String	resons_for_susp;
	private Blob	rptr_sig;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	sig_date;
	private String	mlro_remarks;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	modify_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	auth_time;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	date_of_report;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date	date_submission;
	private String	remarks;
	
	
	
	public String getFrom_rptr_name() {
		return from_rptr_name;
	}
	public String getFrom_rptr_position() {
		return from_rptr_position;
	}
	public BigDecimal getFrom_rpt_extn_ph() {
		return from_rpt_extn_ph;
	}
	
	public String getCust_client_name() {
		return cust_client_name;
	}
	public String getCust_client_addr() {
		return cust_client_addr;
	}
	public BigDecimal getCust_client_tel() {
		return cust_client_tel;
	}
	public BigDecimal getCust_client_fax() {
		return cust_client_fax;
	}
	public BigDecimal getCust_client_mob() {
		return cust_client_mob;
	}
	public String getCust_client_occp() {
		return cust_client_occp;
	}
	public String getCust_client_employer() {
		return cust_client_employer;
	}
	public String getCust_client_id_card_no() {
		return cust_client_id_card_no;
	}
	public String getCust_client_otr_id() {
		return cust_client_otr_id;
	}
	public Date getCust_client_date() {
		return cust_client_date;
	}
	public String getCust_client_place_of_birth() {
		return cust_client_place_of_birth;
	}
	public String getTran_ref() {
		return tran_ref;
	}
	public BigDecimal getTran_amount() {
		return tran_amount;
	}
	public String getResons_for_susp() {
		return resons_for_susp;
	}
	public Blob getRptr_sig() {
		return rptr_sig;
	}
	public Date getSig_date() {
		return sig_date;
	}
	public String getMlro_remarks() {
		return mlro_remarks;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public Date getDate_of_report() {
		return date_of_report;
	}
	public Date getDate_submission() {
		return date_submission;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setFrom_rptr_name(String from_rptr_name) {
		this.from_rptr_name = from_rptr_name;
	}
	public void setFrom_rptr_position(String from_rptr_position) {
		this.from_rptr_position = from_rptr_position;
	}
	public void setFrom_rpt_extn_ph(BigDecimal from_rpt_extn_ph) {
		this.from_rpt_extn_ph = from_rpt_extn_ph;
	}
	
	public void setCust_client_name(String cust_client_name) {
		this.cust_client_name = cust_client_name;
	}
	public void setCust_client_addr(String cust_client_addr) {
		this.cust_client_addr = cust_client_addr;
	}
	public void setCust_client_tel(BigDecimal cust_client_tel) {
		this.cust_client_tel = cust_client_tel;
	}
	public void setCust_client_fax(BigDecimal cust_client_fax) {
		this.cust_client_fax = cust_client_fax;
	}
	public void setCust_client_mob(BigDecimal cust_client_mob) {
		this.cust_client_mob = cust_client_mob;
	}
	public void setCust_client_occp(String cust_client_occp) {
		this.cust_client_occp = cust_client_occp;
	}
	public void setCust_client_employer(String cust_client_employer) {
		this.cust_client_employer = cust_client_employer;
	}
	public void setCust_client_id_card_no(String cust_client_id_card_no) {
		this.cust_client_id_card_no = cust_client_id_card_no;
	}
	public void setCust_client_otr_id(String cust_client_otr_id) {
		this.cust_client_otr_id = cust_client_otr_id;
	}
	public void setCust_client_date(Date cust_client_date) {
		this.cust_client_date = cust_client_date;
	}
	public void setCust_client_place_of_birth(String cust_client_place_of_birth) {
		this.cust_client_place_of_birth = cust_client_place_of_birth;
	}
	public void setTran_ref(String tran_ref) {
		this.tran_ref = tran_ref;
	}
	public void setTran_amount(BigDecimal tran_amount) {
		this.tran_amount = tran_amount;
	}
	public void setResons_for_susp(String resons_for_susp) {
		this.resons_for_susp = resons_for_susp;
	}
	public void setRptr_sig(Blob rptr_sig) {
		this.rptr_sig = rptr_sig;
	}
	public void setSig_date(Date sig_date) {
		this.sig_date = sig_date;
	}
	public void setMlro_remarks(String mlro_remarks) {
		this.mlro_remarks = mlro_remarks;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public void setDate_of_report(Date date_of_report) {
		this.date_of_report = date_of_report;
	}
	public void setDate_submission(Date date_submission) {
		this.date_submission = date_submission;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getStr_ref_no() {
		return str_ref_no;
	}
	public void setStr_ref_no(String str_ref_no) {
		this.str_ref_no = str_ref_no;
	}
	
	public String getTo_mlro_dmlro() {
		return to_mlro_dmlro;
	}
	public void setTo_mlro_dmlro(String to_mlro_dmlro) {
		this.to_mlro_dmlro = to_mlro_dmlro;
	}
	
	public BAML_STR() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BAML_STR(String str_ref_no, String from_rptr_name, String from_rptr_position, BigDecimal from_rpt_extn_ph,
			String to_mlro_dmlro, String cust_client_name, String cust_client_addr, BigDecimal cust_client_tel,
			BigDecimal cust_client_fax, BigDecimal cust_client_mob, String cust_client_occp,
			String cust_client_employer, String cust_client_id_card_no, String cust_client_otr_id,
			Date cust_client_date, String cust_client_place_of_birth, String tran_ref, BigDecimal tran_amount,
			String resons_for_susp, Blob rptr_sig, Date sig_date, String mlro_remarks, String del_flg,
			String entity_flg, String modify_flg, String entry_user, String modify_user, String auth_user,
			Date modify_time, Date auth_time, Date date_of_report, Date date_submission, String remarks) {
		super();
		this.str_ref_no = str_ref_no;
		this.from_rptr_name = from_rptr_name;
		this.from_rptr_position = from_rptr_position;
		this.from_rpt_extn_ph = from_rpt_extn_ph;
		this.to_mlro_dmlro = to_mlro_dmlro;
		this.cust_client_name = cust_client_name;
		this.cust_client_addr = cust_client_addr;
		this.cust_client_tel = cust_client_tel;
		this.cust_client_fax = cust_client_fax;
		this.cust_client_mob = cust_client_mob;
		this.cust_client_occp = cust_client_occp;
		this.cust_client_employer = cust_client_employer;
		this.cust_client_id_card_no = cust_client_id_card_no;
		this.cust_client_otr_id = cust_client_otr_id;
		this.cust_client_date = cust_client_date;
		this.cust_client_place_of_birth = cust_client_place_of_birth;
		this.tran_ref = tran_ref;
		this.tran_amount = tran_amount;
		this.resons_for_susp = resons_for_susp;
		this.rptr_sig = rptr_sig;
		this.sig_date = sig_date;
		this.mlro_remarks = mlro_remarks;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.date_of_report = date_of_report;
		this.date_submission = date_submission;
		this.remarks = remarks;
	}

	
	
	

}

package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="\"BAML_SCR_PARM_SCHM_TYPE\"", schema="\"AML\"")
public class Aml_Scr_Parm_Schm_Type_Entity implements Serializable{
	public Aml_Scr_Parm_Schm_Type_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Aml_Scr_Parm_Schm_Type_Entity(String ref_no2, String crncy_code2, BigDecimal loan_limit2,
			String loanschemes,String loan_conditions2, String schm_code2, String glsubHeadcode, String custId, String parameters2,
			Date startdate, Date enddate, String renarsk1, String remarks22, Date entrytime, Date modifytime,
			Date varifytime, String entryuser, String modifyuser, String verifyuser, Character entityflg, Character delflg,
			Character modifyflg) {
		this.ref_no = ref_no2;
		this.crncy_code = crncy_code2;
		this.loan_limit = loan_limit2;
		this.loan_scheme=loanschemes;
		this.loan_conditions = loan_conditions2;
		this.schm_code = schm_code2;
		this.gl_sub_head_code = glsubHeadcode;
		this.cust_id = custId;
		this.parameters = parameters2;
		this.start_date = startdate;
		this.end_date = enddate;
		this.remarks1 = renarsk1;
		this.remarks2 = remarks22;
		this.aml_entry_time = entrytime;
		this.aml_modify_time = modifytime;
		this.aml_verify_time = varifytime;
		this.aml_entry_user = entryuser;
		this.aml_modify_user = modifyuser;
		this.aml_verify_user = verifyuser;
		this.entity_flag = entityflg;
		this.del_flag = delflg;
		this.modify_flag = modifyflg;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "\"REF_NO\"")
	private String	ref_no;
	@Column(name = "\"CRNCY_CODE\"")
	private String	crncy_code;
	@Column(name = "\"LOAN_LIMIT\"")
	private BigDecimal	loan_limit;
	@Column(name = "\"LOAN_SCHEME\"")
	private String	loan_scheme;
	@Column(name = "\"LOAN_CONDITIONS\"")
	private String	loan_conditions;
	@Column(name = "\"SCHM_CODE\"")
	private String	schm_code;
	@Column(name = "\"GL_SUB_HEAD_CODE\"")
	private String	gl_sub_head_code;
	@Column(name = "\"CUST_ID\"")
	private String	cust_id;
	@Column(name = "\"PARAMETERS\"")
	private String	parameters;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	@Column(name = "\"START_DATE\"")
	private Date	start_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	@Column(name = "\"END_DATE\"")
	private Date	end_date;
	@Column(name = "\"REMARKS1\"")
	private String	remarks1;
	@Column(name = "\"REMARKS2\"")
	private String	remarks2;

	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	@Column(name = "\"AML_ENTRY_TIME\"")
	private Date	aml_entry_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	@Column(name = "\"AML_MODIFY_TIME\"")
	private Date	aml_modify_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	@Column(name = "\"AML_VERIFY_TIME\"")
	private Date	aml_verify_time;
	
	
	@Column(name = "\"AML_ENTRY_USER\"")
	private String	aml_entry_user;
	@Column(name = "\"AML_MODIFY_USER\"")
	private String	aml_modify_user;
	@Column(name = "\"AML_VERIFY_USER\"")
	private String	aml_verify_user;
	@Column(name = "\"ENTITY_FLAG\"")
	private Character	entity_flag;
	@Column(name = "\"DEL_FLAG\"")
	private Character	del_flag;
	@Column(name = "\"MODIFY_FLAG\"")
	private Character	modify_flag;
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}
	public String getCrncy_code() {
		return crncy_code;
	}
	public void setCrncy_code(String crncy_code) {
		this.crncy_code = crncy_code;
	}
	public BigDecimal getLoan_limit() {
		return loan_limit;
	}
	public void setLoan_limit(BigDecimal loan_limit) {
		this.loan_limit = loan_limit;
	}
	public String getLoan_scheme() {
		return loan_scheme;
	}
	public void setLoan_scheme(String loan_scheme) {
		this.loan_scheme = loan_scheme;
	}
	public String getLoan_conditions() {
		return loan_conditions;
	}
	public void setLoan_conditions(String loan_conditions) {
		this.loan_conditions = loan_conditions;
	}
	public String getSchm_code() {
		return schm_code;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	public String getGl_sub_head_code() {
		return gl_sub_head_code;
	}
	public void setGl_sub_head_code(String gl_sub_head_code) {
		this.gl_sub_head_code = gl_sub_head_code;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getAml_entry_time() {
		return aml_entry_time;
	}
	public void setAml_entry_time(Date aml_entry_time) {
		this.aml_entry_time = aml_entry_time;
	}
	public Date getAml_modify_time() {
		return aml_modify_time;
	}
	public void setAml_modify_time(Date aml_modify_time) {
		this.aml_modify_time = aml_modify_time;
	}
	public Date getAml_verify_time() {
		return aml_verify_time;
	}
	public void setAml_verify_time(Date aml_verify_time) {
		this.aml_verify_time = aml_verify_time;
	}
	public String getAml_entry_user() {
		return aml_entry_user;
	}
	public void setAml_entry_user(String aml_entry_user) {
		this.aml_entry_user = aml_entry_user;
	}
	public String getAml_modify_user() {
		return aml_modify_user;
	}
	public void setAml_modify_user(String aml_modify_user) {
		this.aml_modify_user = aml_modify_user;
	}
	public String getAml_verify_user() {
		return aml_verify_user;
	}
	public void setAml_verify_user(String aml_verify_user) {
		this.aml_verify_user = aml_verify_user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	public Character getEntity_flag() {
		return entity_flag;
	}
	public void setEntity_flag(Character entity_flag) {
		this.entity_flag = entity_flag;
	}
	public Character getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(Character del_flag) {
		this.del_flag = del_flag;
	}
	public Character getModify_flag() {
		return modify_flag;
	}
	public void setModify_flag(Character modify_flag) {
		this.modify_flag = modify_flag;
	}

	public void setEntity_flag(String entity_flag) {
		this.entity_flag = (entity_flag != null && !entity_flag.isEmpty()) ? entity_flag.charAt(0) : null;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = (del_flag != null && !del_flag.isEmpty()) ? del_flag.charAt(0) : null;
	}
	public void setModify_flag(String modify_flag) {
		this.modify_flag = (modify_flag != null && !modify_flag.isEmpty()) ? modify_flag.charAt(0) : null;
	}

	public Character getEntity_flg() {
		return entity_flag;
	}
	public void setEntity_flg(Character entity_flg) {
		this.entity_flag = entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		setEntity_flag(entity_flg);
	}
	public Character getDel_flg() {
		return del_flag;
	}
	public void setDel_flg(Character del_flg) {
		this.del_flag = del_flg;
	}
	public void setDel_flg(String del_flg) {
		setDel_flag(del_flg);
	}
	public Character getModify_flg() {
		return modify_flag;
	}
	public void setModify_flg(Character modify_flg) {
		this.modify_flag = modify_flag;
	}
	public void setModify_flg(String modify_flg) {
		setModify_flag(modify_flg);
	}

}

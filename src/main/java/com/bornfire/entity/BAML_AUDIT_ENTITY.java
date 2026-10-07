package com.bornfire.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "BAML_AUDIT_TABLE_FINACLE")
public class BAML_AUDIT_ENTITY {

	
	@Id
	private String  srl_num;
	private String	sol_id;
	private String	sol_desc;
	
	private String	foracid;
	private String	acct_name;
	@DateTimeFormat(pattern =  "dd-MM-yyyy")
	private Date	audit_date;
	private String	init_sol_id;
	private String	char_audit_date;
	private String	funct_code;
	private String	remarks;
	private String	enterer_id;
	private String	auth_id;
	private String	table_name;
	private String	table_abr;
	private String	modified_value;
	private String table_key;

	public String getSrl_num() {
		return srl_num;
	}
	public void setSrl_num(String srl_num) {
		this.srl_num = srl_num;
	}
	public String getSol_id() {
		return sol_id;
	}
	public void setSol_id(String sol_id) {
		this.sol_id = sol_id;
	}
	public String getSol_desc() {
		return sol_desc;
	}
	public void setSol_desc(String sol_desc) {
		this.sol_desc = sol_desc;
	}
	public String getForacid() {
		return foracid;
	}
	public void setForacid(String foracid) {
		this.foracid = foracid;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public String getInit_sol_id() {
		return init_sol_id;
	}
	public void setInit_sol_id(String init_sol_id) {
		this.init_sol_id = init_sol_id;
	}
	public String getChar_audit_date() {
		return char_audit_date;
	}
	public void setChar_audit_date(String char_audit_date) {
		this.char_audit_date = char_audit_date;
	}
	public String getFunct_code() {
		return funct_code;
	}
	public void setFunct_code(String funct_code) {
		this.funct_code = funct_code;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEnterer_id() {
		return enterer_id;
	}
	public void setEnterer_id(String enterer_id) {
		this.enterer_id = enterer_id;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_abr() {
		return table_abr;
	}
	public void setTable_abr(String table_abr) {
		this.table_abr = table_abr;
	}
	public String getModified_value() {
		return modified_value;
	}
	public void setModified_value(String modified_value) {
		this.modified_value = modified_value;
	}
	public String getTable_key() {
		return table_key;
	}
	public void setTable_key(String table_key) {
		this.table_key = table_key;
	}
	public BAML_AUDIT_ENTITY(String srl_num, String sol_id, String sol_desc, String foracid, String acct_name,
			Date audit_date, String init_sol_id, String char_audit_date, String funct_code, String remarks,
			String enterer_id, String auth_id, String table_name, String table_abr, String modified_value,
			String table_key) {
		super();
		this.srl_num = srl_num;
		this.sol_id = sol_id;
		this.sol_desc = sol_desc;
		this.foracid = foracid;
		this.acct_name = acct_name;
		this.audit_date = audit_date;
		this.init_sol_id = init_sol_id;
		this.char_audit_date = char_audit_date;
		this.funct_code = funct_code;
		this.remarks = remarks;
		this.enterer_id = enterer_id;
		this.auth_id = auth_id;
		this.table_name = table_name;
		this.table_abr = table_abr;
		this.modified_value = modified_value;
		this.table_key = table_key;
	}
	public BAML_AUDIT_ENTITY() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}

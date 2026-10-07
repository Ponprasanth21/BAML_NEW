package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BAML_ACCESS_ROLE_TABLE")
public class AMLAccessRole {

	@Id
	private String	role_id;
	private String	role_desc;
	private String	permissions;
	private String	work_class;
	private String	domain_id;
	private String	admin;
	private String	inquiry;
	private String	monitoring;
	private String	list_management;
	private String	interfaces;
	private String	screening;
	private String	risk_management;
	private String	transaction;
	private String	general;
	private String	report;
	private String	entity_flg;
	private String	auth_flg;
	private String	modify_flg;
	private String	del_flg;
	private String	menulist;
	private String	case_management;
	private String  thirdparty_transaction;
	private String	amlreport;
	private String 	strreport;
	private String 	auditlog;
	private String	amlarchival;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getWork_class() {
		return work_class;
	}
	public void setWork_class(String work_class) {
		this.work_class = work_class;
	}
	public String getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getInquiry() {
		return inquiry;
	}
	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public String getList_management() {
		return list_management;
	}
	public void setList_management(String list_management) {
		this.list_management = list_management;
	}
	public String getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	public String getScreening() {
		return screening;
	}
	public void setScreening(String screening) {
		this.screening = screening;
	}
	public String getRisk_management() {
		return risk_management;
	}
	public void setRisk_management(String risk_management) {
		this.risk_management = risk_management;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getAuth_flg() {
		return auth_flg;
	}
	public void setAuth_flg(String auth_flg) {
		this.auth_flg = auth_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getMenulist() {
		return menulist;
	}
	public void setMenulist(String menulist) {
		this.menulist = menulist;
	}
	public String getCase_management() {
		return case_management;
	}
	public void setCase_management(String case_management) {
		this.case_management = case_management;
	}
	public String getAmlreport() {
		return amlreport;
	}
	public void setAmlreport(String amlreport) {
		this.amlreport = amlreport;
	}
	public String getStrreport() {
		return strreport;
	}
	public void setStrreport(String strreport) {
		this.strreport = strreport;
	}
	public String getAuditlog() {
		return auditlog;
	}
	public void setAuditlog(String auditlog) {
		this.auditlog = auditlog;
	}
	public String getAmlarchival() {
		return amlarchival;
	}
	public void setAmlarchival(String amlarchival) {
		this.amlarchival = amlarchival;
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
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	
	
	
	
	public String getThirdparty_transaction() {
		return thirdparty_transaction;
	}
	public void setThirdparty_transaction(String thirdparty_transaction) {
		this.thirdparty_transaction = thirdparty_transaction;
	}

	public AMLAccessRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AMLAccessRole(String role_id, String role_desc, String permissions, String work_class, String domain_id,
			String admin, String inquiry, String monitoring, String list_management, String interfaces,
			String screening, String risk_management, String transaction, String general, String report,
			String entity_flg, String auth_flg, String modify_flg, String del_flg, String menulist,
			String case_management, String thirdparty_transaction, String amlreport, String strreport, String auditlog,
			String amlarchival, String entry_user, String modify_user, String auth_user, Date entry_time,
			Date modify_time, Date auth_time) {
		super();
		this.role_id = role_id;
		this.role_desc = role_desc;
		this.permissions = permissions;
		this.work_class = work_class;
		this.domain_id = domain_id;
		this.admin = admin;
		this.inquiry = inquiry;
		this.monitoring = monitoring;
		this.list_management = list_management;
		this.interfaces = interfaces;
		this.screening = screening;
		this.risk_management = risk_management;
		this.transaction = transaction;
		this.general = general;
		this.report = report;
		this.entity_flg = entity_flg;
		this.auth_flg = auth_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.menulist = menulist;
		this.case_management = case_management;
		this.thirdparty_transaction = thirdparty_transaction;
		this.amlreport = amlreport;
		this.strreport = strreport;
		this.auditlog = auditlog;
		this.amlarchival = amlarchival;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
	}
	
	
	
		
	
}

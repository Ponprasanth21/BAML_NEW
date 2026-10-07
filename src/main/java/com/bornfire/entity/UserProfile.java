package com.bornfire.entity;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "\"BAML_USER_PROFILE_TABLE\"", schema = "\"AML\"")
public class UserProfile implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Column(name = "\"BANK_CODE\"")
	private String bank_code;

	@Column(name = "\"BANK_NAME\"")
	private String bank_name;

	@Column(name = "\"BRANCH_CODE\"")
	private String branch_code;

	@Column(name = "\"BRANCH_NAME\"")
	private String branch_name;

	@Column(name = "\"EMP_ID\"")
	private String empid;

	@Column(name = "\"EMP_NAME\"")
	private String emp_name;

	@Id
	@Column(name = "\"USER_ID\"")
	private String userid;

	@Column(name = "\"USER_NAME\"")
	private String username;

	@Column(name = "\"INACTIVE_TIME\"")
	private String inactive_time;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"ACC_EXP_DATE\"")
	private Date acc_exp_date;

	@Column(name = "\"LOGIN_LOW\"")
	private String login_low;

	@Column(name = "\"LOGIN_HIGH\"")
	private String login_high;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"DISABLE_START_DATE\"")
	private Date disable_start_date;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"DISABLE_END_DATE\"")
	private Date disable_end_date;

	@Column(name = "\"PASSWORD\"")
	private String password;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"PASS_EXP_DATE\"")
	private Date pass_exp_date;

	@Column(name = "\"USER_STATUS\"")
	private String user_status;

	@Column(name = "\"LOGIN_STATUS\"")
	private String login_status;

	@Column(name = "\"VIRTUAL_FLG\"")
	private String virtual_flg;

	@Column(name = "\"WORK_CLASS\"")
	private String work_class;

	@Column(name = "\"MOB_NUMBER\"")
	private String mob_number;

	@Column(name = "\"EMAIL_ID\"")
	private String email_id;

	@Column(name = "\"ROLE_ID\"")
	private String role_id;

	@Column(name = "\"ROLE_DESC\"")
	private String role_desc;

	@Column(name = "\"PERMISSIONS\"")
	private String permissions;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "\"PER_EFFCTIVE_DATE\"")
	private String per_effctive_date;

	@Column(name = "\"ADMIN\"")
	private String admin;

	@Column(name = "\"XBRL_CONFIGURATION\"")
	private String xbrl_configuration;

	@Column(name = "\"XBRL_REPORT\"")
	private String xbrl_report;

	@Column(name = "\"SCHEDULER\"")
	private String scheduler;

	@Column(name = "\"EXECUTION\"")
	private String execution;

	@Column(name = "\"MIS_REPORTS\"")
	private String mis_reports;

	@Column(name = "\"XML_REPORTS\"")
	private String xml_reports;

	@Column(name = "\"ARCHIVEL\"")
	private String archivel;

	@Column(name = "\"GENERAL_INQ\"")
	private String general_inq;

	@Column(name = "\"AUDIT_INQ\"")
	private String audit_inq;

	@Column(name = "\"CHANNEL\"")
	private String channel;

	@Column(name = "\"ENTRY_USER\"")
	private String entry_user;

	@Column(name = "\"ENTRY_TIME\"")
	private Date entry_time;

	@Column(name = "\"AUTH_USER\"")
	private String auth_user;

	@Column(name = "\"AUTH_TIME\"")
	private Date auth_time;

	@Column(name = "\"MODIFY_USER\"")
	private String modify_user;

	@Column(name = "\"MODIFY_TIME\"")
	private Date modify_time;

	@Column(name = "\"ENTITY_FLG\"")
	private String entity_flg;

	@Column(name = "\"AUTH_FLG\"")
	private String auth_flg;

	@Column(name = "\"MODIFY_FLG\"")
	private String modify_flg;

	@Column(name = "\"DEL_FLG\"")
	private String del_flg;

	@Column(name = "\"SESSION_ID\"")
	private String session_id;

	@Column(name = "\"LOGIN_FLG\"")
	private String login_flg;

	@Column(name = "\"USER_LOCKED_FLG\"")
	private String user_locked_flg;

	@Column(name = "\"NO_OF_ATTMP\"")
	private Integer no_of_attmp;

	@Column(name = "\"DISABLE_FLG\"")
	private String disable_flg;

	@Column(name = "\"PHOTO\"")
	private Blob photo;

	@Column(name = "\"DOMAIN_ID\"")
	private String domain_id;

	@Column(name = "\"NEW_USER_FLG\"")
	private String new_user_flg;

	@Column(name = "\"REMARK\"")
	private String remark;

	public String getNew_user_flg() {
		return new_user_flg;
	}

	public void setNew_user_flg(String new_user_flg) {
		this.new_user_flg = new_user_flg;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String user_id) {
		this.userid = user_id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInactive_time() {
		return inactive_time;
	}

	public void setInactive_time(String inactive_time) {
		this.inactive_time = inactive_time;
	}

	public Date getAcc_exp_date() {
		return acc_exp_date;
	}

	public void setAcc_exp_date(Date acc_exp_date) {
		this.acc_exp_date = acc_exp_date;
	}

	public String getLogin_low() {
		return login_low;
	}

	public void setLogin_low(String login_low) {
		this.login_low = login_low;
	}

	public String getLogin_high() {
		return login_high;
	}

	public void setLogin_high(String login_high) {
		this.login_high = login_high;
	}

	public Date getDisable_start_date() {
		return disable_start_date;
	}

	public void setDisable_start_date(Date disable_start_date) {
		this.disable_start_date = disable_start_date;
	}

	public Date getDisable_end_date() {
		return disable_end_date;
	}

	public void setDisable_end_date(Date disable_end_date) {
		this.disable_end_date = disable_end_date;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPass_exp_date() {
		return pass_exp_date;
	}

	public void setPass_exp_date(Date pass_exp_date) {
		this.pass_exp_date = pass_exp_date;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public String getVirtual_flg() {
		return virtual_flg;
	}

	public void setVirtual_flg(String virtual_flg) {
		this.virtual_flg = virtual_flg;
	}

	public String getWork_class() {
		return work_class;
	}

	public void setWork_class(String work_class) {
		this.work_class = work_class;
	}

	public String getMob_number() {
		return mob_number;
	}

	public void setMob_number(String mob_number) {
		this.mob_number = mob_number;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

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

	public String getPer_effctive_date() {
		return per_effctive_date;
	}

	public void setPer_effctive_date(String per_effctive_date) {
		this.per_effctive_date = per_effctive_date;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getXbrl_configuration() {
		return xbrl_configuration;
	}

	public void setXbrl_configuration(String xbrl_configuration) {
		this.xbrl_configuration = xbrl_configuration;
	}

	public String getXbrl_report() {
		return xbrl_report;
	}

	public void setXbrl_report(String xbrl_report) {
		this.xbrl_report = xbrl_report;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getMis_reports() {
		return mis_reports;
	}

	public void setMis_reports(String mis_reports) {
		this.mis_reports = mis_reports;
	}

	public String getXml_reports() {
		return xml_reports;
	}

	public void setXml_reports(String xml_reports) {
		this.xml_reports = xml_reports;
	}

	public String getArchivel() {
		return archivel;
	}

	public void setArchivel(String archivel) {
		this.archivel = archivel;
	}

	public String getGeneral_inq() {
		return general_inq;
	}

	public void setGeneral_inq(String general_inq) {
		this.general_inq = general_inq;
	}

	public String getAudit_inq() {
		return audit_inq;
	}

	public void setAudit_inq(String audit_inq) {
		this.audit_inq = audit_inq;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public String getAuth_user() {
		return auth_user;
	}

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}

	public Date getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
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

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getLogin_flg() {
		return login_flg;
	}

	public void setLogin_flg(String login_flg) {
		this.login_flg = login_flg;
	}

	public String getUser_locked_flg() {
		return user_locked_flg;
	}

	public void setUser_locked_flg(String user_locked_flg) {
		this.user_locked_flg = user_locked_flg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getNo_of_attmp() {
		return no_of_attmp;
	}

	public void setNo_of_attmp(Integer no_of_attmp) {
		this.no_of_attmp = no_of_attmp;
	}

	public String getDisable_flg() {
		return disable_flg;
	}

	public void setDisable_flg(String disable_flg) {
		this.disable_flg = disable_flg;
	}

	@JsonIgnore
	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public String getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		if (this.getAcc_exp_date().after(new Date())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean isAccountNonLocked() {
		boolean status = true;
		if (this.getUser_locked_flg().equals("Y")) {
			status = false;
		} else {
			status = true;
		}

		return status;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if (this.getPass_exp_date().after(new Date())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isEnabled() {

		Date currDate = new Date();
		if (this.getDisable_flg().equals("Y")
				|| (currDate.after(this.getDisable_start_date()) && currDate.before(this.disable_end_date))
				|| this.entity_flg.equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isLoginAllowed() {

		DateFormat dateFormat = new SimpleDateFormat("hh:mm");

		try {
			Date loginHigh = dateFormat.parse(this.login_high);
			Date loginLow = dateFormat.parse(this.login_low);

			LocalTime high = LocalDateTime.ofInstant(loginHigh.toInstant(), ZoneId.systemDefault()).toLocalTime();
			LocalTime low = LocalDateTime.ofInstant(loginLow.toInstant(), ZoneId.systemDefault()).toLocalTime();
			LocalTime currTime = java.time.LocalTime.now();

			if (currTime.isAfter(low) && currTime.isBefore(high)) {
				return true;
			} else {

				return false;
			}

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return false;
	}

	public UserProfile(String bank_code, String bank_name, String branch_code, String branch_name, String empid,
			String emp_name, String userid, String username, String inactive_time, Date acc_exp_date, String login_low,
			String login_high, Date disable_start_date, Date disable_end_date, String password, Date pass_exp_date,
			String user_status, String login_status, String virtual_flg, String work_class, String mob_number,
			String email_id, String role_id, String role_desc, String permissions, String per_effctive_date,
			String admin, String xbrl_configuration, String xbrl_report, String scheduler, String execution,
			String mis_reports, String xml_reports, String archivel, String general_inq, String audit_inq,
			String channel, String entry_user, Date entry_time, String auth_user, Date auth_time, String modify_user,
			Date modify_time, String entity_flg, String auth_flg, String modify_flg, String del_flg, String session_id,
			String login_flg, String user_locked_flg, Integer no_of_attmp, String disable_flg, String domain_id,
			String new_user_flg, String remark) {
		super();
		this.bank_code = bank_code;
		this.bank_name = bank_name;
		this.branch_code = branch_code;
		this.branch_name = branch_name;
		this.empid = empid;
		this.emp_name = emp_name;
		this.userid = userid;
		this.username = username;
		this.inactive_time = inactive_time;
		this.acc_exp_date = acc_exp_date;
		this.login_low = login_low;
		this.login_high = login_high;
		this.disable_start_date = disable_start_date;
		this.disable_end_date = disable_end_date;
		this.password = password;
		this.pass_exp_date = pass_exp_date;
		this.user_status = user_status;
		this.login_status = login_status;
		this.virtual_flg = virtual_flg;
		this.work_class = work_class;
		this.mob_number = mob_number;
		this.email_id = email_id;
		this.role_id = role_id;
		this.role_desc = role_desc;
		this.permissions = permissions;
		this.per_effctive_date = per_effctive_date;
		this.admin = admin;
		this.xbrl_configuration = xbrl_configuration;
		this.xbrl_report = xbrl_report;
		this.scheduler = scheduler;
		this.execution = execution;
		this.mis_reports = mis_reports;
		this.xml_reports = xml_reports;
		this.archivel = archivel;
		this.general_inq = general_inq;
		this.audit_inq = audit_inq;
		this.channel = channel;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.auth_user = auth_user;
		this.auth_time = auth_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.entity_flg = entity_flg;
		this.auth_flg = auth_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.session_id = session_id;
		this.login_flg = login_flg;
		this.user_locked_flg = user_locked_flg;
		this.no_of_attmp = no_of_attmp;
		this.disable_flg = disable_flg;
		this.domain_id = domain_id;
		this.new_user_flg = new_user_flg;
		this.remark = remark;
	}

	public UserProfile() {
	}

	@Override
	public String toString() {
		return "UserProfile [bank_code=" + bank_code + ", bank_name=" + bank_name + ", branch_code=" + branch_code
				+ ", branch_name=" + branch_name + ", empid=" + empid + ", emp_name=" + emp_name + ", userid=" + userid
				+ ", username=" + username + ", inactive_time=" + inactive_time + ", acc_exp_date=" + acc_exp_date
				+ ", login_low=" + login_low + ", login_high=" + login_high + ", disable_start_date="
				+ disable_start_date + ", disable_end_date=" + disable_end_date + ", password=" + password
				+ ", pass_exp_date=" + pass_exp_date + ", user_status=" + user_status + ", login_status=" + login_status
				+ ", virtual_flg=" + virtual_flg + ", work_class=" + work_class + ", mob_number=" + mob_number
				+ ", email_id=" + email_id + ", role_id=" + role_id + ", role_desc=" + role_desc + ", permissions="
				+ permissions + ", per_effctive_date=" + per_effctive_date + ", admin=" + admin
				+ ", xbrl_configuration=" + xbrl_configuration + ", xbrl_report=" + xbrl_report + ", scheduler="
				+ scheduler + ", execution=" + execution + ", mis_reports=" + mis_reports + ", xml_reports="
				+ xml_reports + ", archivel=" + archivel + ", general_inq=" + general_inq + ", audit_inq=" + audit_inq
				+ ", channel=" + channel + ", entry_user=" + entry_user + ", entry_time=" + entry_time + ", auth_user="
				+ auth_user + ", auth_time=" + auth_time + ", modify_user=" + modify_user + ", modify_time="
				+ modify_time + ", entity_flg=" + entity_flg + ", auth_flg=" + auth_flg + ", modify_flg=" + modify_flg
				+ ", del_flg=" + del_flg + ", session_id=" + session_id + ", login_flg=" + login_flg
				+ ", user_locked_flg=" + user_locked_flg + ", no_of_attmp=" + no_of_attmp + ", disable_flg="
				+ disable_flg + ", domain_id=" + domain_id + ", new_user_flg=" + new_user_flg + ", remark=" + remark
				+ "]";
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public UserProfile get() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserProfile(UserProfileModEn userProfile) {
		this.bank_code = userProfile.getBank_code();
		this.bank_name = userProfile.getBank_name();
		this.branch_code = userProfile.getBranch_code();
		this.branch_name = userProfile.getBranch_name();
		this.empid = userProfile.getEmpid();
		this.emp_name = userProfile.getEmp_name();
		this.userid = userProfile.getUserid();
		this.username = userProfile.getUsername();
		this.inactive_time = userProfile.getInactive_time();
		this.acc_exp_date = userProfile.getAcc_exp_date();
		this.login_low = userProfile.getLogin_low();
		this.login_high = userProfile.getLogin_high();
		this.disable_start_date = userProfile.getDisable_start_date();
		this.disable_end_date = userProfile.getDisable_end_date();
		this.password = userProfile.getPassword();
		this.pass_exp_date = userProfile.getPass_exp_date();
		this.user_status = userProfile.getUser_status();
		this.login_status = userProfile.getLogin_status();
		this.virtual_flg = userProfile.getVirtual_flg();
		this.work_class = userProfile.getWork_class();
		this.mob_number = userProfile.getMob_number();
		this.email_id = userProfile.getEmail_id();
		this.role_id = userProfile.getRole_id();
		this.role_desc = userProfile.getRole_desc();
		this.permissions = userProfile.getPermissions();
		this.per_effctive_date = userProfile.getPer_effctive_date();
		this.admin = userProfile.getAdmin();
		this.xbrl_configuration = userProfile.getXbrl_configuration();
		this.xbrl_report = userProfile.getXbrl_report();
		this.scheduler = userProfile.getScheduler();
		this.execution = userProfile.getExecution();
		this.mis_reports = userProfile.getMis_reports();
		this.xml_reports = userProfile.getXml_reports();
		this.archivel = userProfile.getArchivel();
		this.general_inq = userProfile.getGeneral_inq();
		this.audit_inq = userProfile.getAudit_inq();
		this.channel = userProfile.getChannel();
		this.entry_user = userProfile.getEntry_user();
		this.entry_time = userProfile.getEntry_time();
		this.auth_user = userProfile.getAuth_user();
		this.auth_time = userProfile.getAuth_time();
		this.modify_user = userProfile.getModify_user();
		this.modify_time = userProfile.getModify_time();
		this.entity_flg = userProfile.getEntity_flg();
		this.auth_flg = userProfile.getAuth_flg();
		this.modify_flg = userProfile.getModify_flg();
		this.del_flg = userProfile.getDel_flg();
		this.session_id = userProfile.getSession_id();
		;
		this.login_flg = userProfile.getLogin_flg();
		this.user_locked_flg = userProfile.getUser_locked_flg();
		this.no_of_attmp = userProfile.getNo_of_attmp();
		this.disable_flg = userProfile.getDisable_flg();
		this.domain_id = userProfile.getDomain_id();
		this.new_user_flg = userProfile.getNew_user_flg();
		this.remark = userProfile.getRemark();

	}

}

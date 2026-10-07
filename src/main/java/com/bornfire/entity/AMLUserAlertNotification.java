package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="BAML_USER_ALERT_NOTIFICATION_TABLE")
public class AMLUserAlertNotification {
	
	
	
	
	private String	alert_type;
	private BigDecimal	cuntry_code;
	private BigDecimal	phone_num;
	private String	user_alert_id;
	private BigDecimal	msg_srl_no;
	private Date	msg_rcre_date;
	private Date	msg_date;
	private String	msg_type;
	private String	module_id;
	@Id
	private BigDecimal	user_alert_srl_no;
	private Date	user_alert_rcre_date;
	private Date	user_alert_date;
	private String	user_alert_sub;
	private String	user_alert_body;
	private String	user_alert_sign;
	private String	msg_status;
	private String	msg_delivery;
	private String	msg_return_status;
	private String	foracid;
	private String	cust_id;
	private String	del_flg;
	public String getAlert_type() {
		return alert_type;
	}
	public BigDecimal getCuntry_code() {
		return cuntry_code;
	}
	public BigDecimal getPhone_num() {
		return phone_num;
	}
	public String getUser_alert_id() {
		return user_alert_id;
	}
	public BigDecimal getMsg_srl_no() {
		return msg_srl_no;
	}
	public Date getMsg_rcre_date() {
		return msg_rcre_date;
	}
	public Date getMsg_date() {
		return msg_date;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public String getModule_id() {
		return module_id;
	}
	public BigDecimal getUser_alert_srl_no() {
		return user_alert_srl_no;
	}
	public Date getUser_alert_rcre_date() {
		return user_alert_rcre_date;
	}
	public Date getUser_alert_date() {
		return user_alert_date;
	}
	public String getUser_alert_sub() {
		return user_alert_sub;
	}
	public String getUser_alert_body() {
		return user_alert_body;
	}
	public String getUser_alert_sign() {
		return user_alert_sign;
	}
	public String getMsg_status() {
		return msg_status;
	}
	public String getMsg_delivery() {
		return msg_delivery;
	}
	public String getMsg_return_status() {
		return msg_return_status;
	}
	public String getForacid() {
		return foracid;
	}
	public String getCust_id() {
		return cust_id;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public void setCuntry_code(BigDecimal cuntry_code) {
		this.cuntry_code = cuntry_code;
	}
	public void setPhone_num(BigDecimal phone_num) {
		this.phone_num = phone_num;
	}
	public void setUser_alert_id(String user_alert_id) {
		this.user_alert_id = user_alert_id;
	}
	public void setMsg_srl_no(BigDecimal msg_srl_no) {
		this.msg_srl_no = msg_srl_no;
	}
	public void setMsg_rcre_date(Date msg_rcre_date) {
		this.msg_rcre_date = msg_rcre_date;
	}
	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public void setUser_alert_srl_no(BigDecimal user_alert_srl_no) {
		this.user_alert_srl_no = user_alert_srl_no;
	}
	public void setUser_alert_rcre_date(Date user_alert_rcre_date) {
		this.user_alert_rcre_date = user_alert_rcre_date;
	}
	public void setUser_alert_date(Date user_alert_date) {
		this.user_alert_date = user_alert_date;
	}
	public void setUser_alert_sub(String user_alert_sub) {
		this.user_alert_sub = user_alert_sub;
	}
	public void setUser_alert_body(String user_alert_body) {
		this.user_alert_body = user_alert_body;
	}
	public void setUser_alert_sign(String user_alert_sign) {
		this.user_alert_sign = user_alert_sign;
	}
	public void setMsg_status(String msg_status) {
		this.msg_status = msg_status;
	}
	public void setMsg_delivery(String msg_delivery) {
		this.msg_delivery = msg_delivery;
	}
	public void setMsg_return_status(String msg_return_status) {
		this.msg_return_status = msg_return_status;
	}
	public void setForacid(String foracid) {
		this.foracid = foracid;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public AMLUserAlertNotification(String alert_type, BigDecimal cuntry_code, BigDecimal phone_num,
			String user_alert_id, BigDecimal msg_srl_no, Date msg_rcre_date, Date msg_date, String msg_type,
			String module_id, BigDecimal user_alert_srl_no, Date user_alert_rcre_date, Date user_alert_date,
			String user_alert_sub, String user_alert_body, String user_alert_sign, String msg_status,
			String msg_delivery, String msg_return_status, String foracid, String cust_id, String del_flg) {
		super();
		this.alert_type = alert_type;
		this.cuntry_code = cuntry_code;
		this.phone_num = phone_num;
		this.user_alert_id = user_alert_id;
		this.msg_srl_no = msg_srl_no;
		this.msg_rcre_date = msg_rcre_date;
		this.msg_date = msg_date;
		this.msg_type = msg_type;
		this.module_id = module_id;
		this.user_alert_srl_no = user_alert_srl_no;
		this.user_alert_rcre_date = user_alert_rcre_date;
		this.user_alert_date = user_alert_date;
		this.user_alert_sub = user_alert_sub;
		this.user_alert_body = user_alert_body;
		this.user_alert_sign = user_alert_sign;
		this.msg_status = msg_status;
		this.msg_delivery = msg_delivery;
		this.msg_return_status = msg_return_status;
		this.foracid = foracid;
		this.cust_id = cust_id;
		this.del_flg = del_flg;
	}
	public AMLUserAlertNotification() {
		super();
		// TODO Auto-generated constructor stub
	}



}

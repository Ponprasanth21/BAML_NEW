package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AMLUserAlertNotificationpojo {

	private Date msg_date;
	private String msg_type;
	private String module_id;
	private String user_alert_sub;
	private String user_alert_body;
	private String msg_status;
	private String alert_type;
    private BigDecimal user_alert_srl_no;
    
    
    
	public Date getMsg_date() {
		return msg_date;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public String getModule_id() {
		return module_id;
	}
	public String getUser_alert_sub() {
		return user_alert_sub;
	}
	public String getUser_alert_body() {
		return user_alert_body;
	}
	public String getMsg_status() {
		return msg_status;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public BigDecimal getUser_alert_srl_no() {
		return user_alert_srl_no;
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
	public void setUser_alert_sub(String user_alert_sub) {
		this.user_alert_sub = user_alert_sub;
	}
	public void setUser_alert_body(String user_alert_body) {
		this.user_alert_body = user_alert_body;
	}
	public void setMsg_status(String msg_status) {
		this.msg_status = msg_status;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public void setUser_alert_srl_no(BigDecimal user_alert_srl_no) {
		this.user_alert_srl_no = user_alert_srl_no;
	}
    
    
    

}

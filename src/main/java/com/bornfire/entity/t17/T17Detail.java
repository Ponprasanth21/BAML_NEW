package com.bornfire.entity.t17;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T17_TRAN_MON_DETAILS")
public class T17Detail {
	private String	acct_no;
	private String	acct_name;
	private Date	tran_date;
	private String	tran_id;
	private BigDecimal	part_tran_id;
	private String	part_tran_type;
	private String	tran_crncy;
	private BigDecimal	tran_amt;
	private String	tran_particulars;
	private String	tran_channel;
	private String	tran_category;
	private String	tran_mon_code;
	private Date	tran_mon_time;
	private String	qtr_flg;
	private Date	entity_flg;
	private Date	del_flg;
	private Date	modify_flg;
	private Date	entry_date;
	private Date	modify_date;
	private String	verify_date;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private Date	report_code;
	private Date	report_name;
	
	@Id
	private Date	report_date;
	private String	arch_flg;
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public BigDecimal getPart_tran_id() {
		return part_tran_id;
	}
	public void setPart_tran_id(BigDecimal part_tran_id) {
		this.part_tran_id = part_tran_id;
	}
	public String getPart_tran_type() {
		return part_tran_type;
	}
	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	public String getTran_crncy() {
		return tran_crncy;
	}
	public void setTran_crncy(String tran_crncy) {
		this.tran_crncy = tran_crncy;
	}
	public BigDecimal getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getTran_particulars() {
		return tran_particulars;
	}
	public void setTran_particulars(String tran_particulars) {
		this.tran_particulars = tran_particulars;
	}
	public String getTran_channel() {
		return tran_channel;
	}
	public void setTran_channel(String tran_channel) {
		this.tran_channel = tran_channel;
	}
	public String getTran_category() {
		return tran_category;
	}
	public void setTran_category(String tran_category) {
		this.tran_category = tran_category;
	}
	public String getTran_mon_code() {
		return tran_mon_code;
	}
	public void setTran_mon_code(String tran_mon_code) {
		this.tran_mon_code = tran_mon_code;
	}
	public Date getTran_mon_time() {
		return tran_mon_time;
	}
	public void setTran_mon_time(Date tran_mon_time) {
		this.tran_mon_time = tran_mon_time;
	}
	public String getQtr_flg() {
		return qtr_flg;
	}
	public void setQtr_flg(String qtr_flg) {
		this.qtr_flg = qtr_flg;
	}
	public Date getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(Date entity_flg) {
		this.entity_flg = entity_flg;
	}
	public Date getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(Date del_flg) {
		this.del_flg = del_flg;
	}
	public Date getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(Date modify_flg) {
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
	public Date getReport_code() {
		return report_code;
	}
	public void setReport_code(Date report_code) {
		this.report_code = report_code;
	}
	public Date getReport_name() {
		return report_name;
	}
	public void setReport_name(Date report_name) {
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
	public T17Detail(String acct_no, String acct_name, Date tran_date, String tran_id, BigDecimal part_tran_id,
			String part_tran_type, String tran_crncy, BigDecimal tran_amt, String tran_particulars, String tran_channel,
			String tran_category, String tran_mon_code, Date tran_mon_time, String qtr_flg, Date entity_flg,
			Date del_flg, Date modify_flg, Date entry_date, Date modify_date, String verify_date, String entry_user,
			String modify_user, String verify_user, Date report_code, Date report_name, Date report_date,
			String arch_flg) {
		super();
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.tran_date = tran_date;
		this.tran_id = tran_id;
		this.part_tran_id = part_tran_id;
		this.part_tran_type = part_tran_type;
		this.tran_crncy = tran_crncy;
		this.tran_amt = tran_amt;
		this.tran_particulars = tran_particulars;
		this.tran_channel = tran_channel;
		this.tran_category = tran_category;
		this.tran_mon_code = tran_mon_code;
		this.tran_mon_time = tran_mon_time;
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

	public T17Detail() {}
	
	

}

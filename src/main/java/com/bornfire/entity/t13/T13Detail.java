package com.bornfire.entity.t13;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = " T13_IND_CASH_DEP_DETAILS")
public class T13Detail implements Serializable {

	@EmbeddedId
	T13DetailId t13detailid;

	private String cust_name;
	private String acct_no;
	private String acct_name;
	private Date tran_date;
	private String tran_id;
	private BigDecimal part_tran_id;
	private Character part_tran_type;
	private String tran_crncy;
	private BigDecimal tran_amt;
	private String tran_particulars;
	private String tran_type;
	private String tran_sub_type;
	private Character qtr_flg;
	private Character entity_flg;
	private Character del_flg;
	private Character modify_flg;
	private Date entry_date;
	private Date modify_date;
	private Date verify_date;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private String report_code;
	private String report_name;

	public T13DetailId getT13detailid() {
		return t13detailid;
	}

	public void setT13detailid(T13DetailId t13detailid) {
		this.t13detailid = t13detailid;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

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

	public Character getPart_tran_type() {
		return part_tran_type;
	}

	public void setPart_tran_type(Character part_tran_type) {
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

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	public String getTran_sub_type() {
		return tran_sub_type;
	}

	public void setTran_sub_type(String tran_sub_type) {
		this.tran_sub_type = tran_sub_type;
	}

	public Character getQtr_flg() {
		return qtr_flg;
	}

	public void setQtr_flg(Character qtr_flg) {
		this.qtr_flg = qtr_flg;
	}

	public Character getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}

	public Character getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}

	public Character getModify_flg() {
		return modify_flg;
	}

	public void setModify_flg(Character modify_flg) {
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

	public Character getArch_flg() {
		return arch_flg;
	}

	public void setArch_flg(Character arch_flg) {
		this.arch_flg = arch_flg;
	}

	private Character arch_flg;

	public T13Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T13Detail(T13DetailId t13detailid, String cust_name, String acct_no, String acct_name, Date tran_date,
			String tran_id, BigDecimal part_tran_id, Character part_tran_type, String tran_crncy, BigDecimal tran_amt,
			String tran_particulars, String tran_type, String tran_sub_type, Character qtr_flg, Character entity_flg,
			Character del_flg, Character modify_flg, Date entry_date, Date modify_date, Date verify_date, String entry_user,
			String modify_user, String verify_user, String report_code, String report_name, Character arch_flg) {
		super();
		this.t13detailid = t13detailid;
		this.cust_name = cust_name;
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.tran_date = tran_date;
		this.tran_id = tran_id;
		this.part_tran_id = part_tran_id;
		this.part_tran_type = part_tran_type;
		this.tran_crncy = tran_crncy;
		this.tran_amt = tran_amt;
		this.tran_particulars = tran_particulars;
		this.tran_type = tran_type;
		this.tran_sub_type = tran_sub_type;
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
		this.arch_flg = arch_flg;
	}
	
	
	

}

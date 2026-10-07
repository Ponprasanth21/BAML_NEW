package com.bornfire.entity;

import java.util.Date;

public class TRANTEMP {

	private Date tran_date;
	private String part_tran_type;
	private String tran_sub_type;
	private String part_tran_srl_num;
	private String tran_crncy_code;
	private String tran_amt;
	private String tran_particular;
	private String foracid;
	private String module_id;
	private String acct_name;
	private String cust_id;
	private String acid;
	private String tran_id;
	private String cif_id;

	
	
	public String getCif_id() {
		return cif_id;
	}

	public void setCif_id(String cif_id) {
		this.cif_id = cif_id;
	}

	public String getAcid() {
		return acid;
	}

	public void setAcid(String acid) {
		this.acid = acid;
	}

	public String getTran_id() {
		return tran_id;
	}

	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}

	
	public Date getTran_date() {
		return tran_date;
	}

	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}



	public String getPart_tran_type() {
		return part_tran_type;
	}

	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}

	public String getTran_sub_type() {
		return tran_sub_type;
	}

	public void setTran_sub_type(String tran_sub_type) {
		this.tran_sub_type = tran_sub_type;
	}

	public String getPart_tran_srl_num() {
		return part_tran_srl_num;
	}

	public void setPart_tran_srl_num(String part_tran_srl_num) {
		this.part_tran_srl_num = part_tran_srl_num;
	}

	public String getTran_crncy_code() {
		return tran_crncy_code;
	}

	public void setTran_crncy_code(String tran_crncy_code) {
		this.tran_crncy_code = tran_crncy_code;
	}

	public String getTran_amt() {
		return tran_amt;
	}

	public void setTran_amt(String tran_amt) {
		this.tran_amt = tran_amt;
	}

	public String getTran_particular() {
		return tran_particular;
	}

	public void setTran_particular(String tran_particular) {
		this.tran_particular = tran_particular;
	}

	public String getForacid() {
		return foracid;
	}

	public void setForacid(String foracid) {
		this.foracid = foracid;
	}

	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}



	public TRANTEMP(Date tran_date, String part_tran_type, String tran_sub_type, String part_tran_srl_num,
			String tran_crncy_code, String tran_amt, String tran_particular, String foracid, String module_id,
			String acct_name, String cust_id, String acid, String tran_id,String cif_id) {
		super();
		this.tran_date = tran_date;
		this.part_tran_type = part_tran_type;
		this.tran_sub_type = tran_sub_type;
		this.part_tran_srl_num = part_tran_srl_num;
		this.tran_crncy_code = tran_crncy_code;
		this.tran_amt = tran_amt;
		this.tran_particular = tran_particular;
		this.foracid = foracid;
		this.module_id = module_id;
		this.acct_name = acct_name;
		this.cust_id = cust_id;
		this.acid = acid;
		this.tran_id = tran_id;
		this.cif_id = cif_id;
	}

	public TRANTEMP() {
		super();
		// TODO Auto-generated constructor stub
	}

}

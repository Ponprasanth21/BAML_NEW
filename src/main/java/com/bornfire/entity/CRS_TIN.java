package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CRS_TIN_TABLE")
public class CRS_TIN {
	
	
	private String	acct_no;
	private String	messref;
	@Id
	private String	docrefid;
	private String	first_name;
	private String	last_name;
	private String	street;
	private String	post_code;
	private String	address_1;
	private String	address;
	private String	city;
	private String	ctry;
	private String	tin;
	private String	crncy;
	private BigDecimal	bal;
	private BigDecimal	interest;
	private Date	dob;
	public String getAcct_no() {
		return acct_no;
	}
	public String getMessref() {
		return messref;
	}
	public String getDocrefid() {
		return docrefid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public String getStreet() {
		return street;
	}
	public String getPost_code() {
		return post_code;
	}
	public String getAddress_1() {
		return address_1;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getCtry() {
		return ctry;
	}
	public String getTin() {
		return tin;
	}
	public String getCrncy() {
		return crncy;
	}
	public BigDecimal getBal() {
		return bal;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public Date getDob() {
		return dob;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public void setMessref(String messref) {
		this.messref = messref;
	}
	public void setDocrefid(String docrefid) {
		this.docrefid = docrefid;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCtry(String ctry) {
		this.ctry = ctry;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public void setCrncy(String crncy) {
		this.crncy = crncy;
	}
	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public CRS_TIN(String acct_no, String messref, String docrefid, String first_name, String last_name, String street,
			String post_code, String address_1, String address, String city, String ctry, String tin, String crncy,
			BigDecimal interest1) {
		super();
		this.acct_no = acct_no;
		this.messref = messref;
		this.docrefid = docrefid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street = street;
		this.post_code = post_code;
		this.address_1 = address_1;
		this.address = address;
		this.city = city;
		this.ctry = ctry;
		this.tin = tin;
		this.crncy = crncy;
		//this.bal = bal1;
		this.interest = interest1;
		//this.dob = dob;
	}
	
	
	public CRS_TIN() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

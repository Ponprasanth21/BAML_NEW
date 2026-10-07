package com.bornfire.entity.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDIVIDUAL_DOCUMENT")
public class IndividualDocument {

	private String typeOfDocument;
	private String typeOfDocument2;
	private Date dateOfIssue;
	private String num;
	private String note;
	private String issuingCountry;
	private String countryOfIssue;  
	private String cityOfIssue;
	public IndividualDocument() {}
	public String getTypeOfDocument() {
		return typeOfDocument;
	}
	
	@XmlElement(name = "TYPE_OF_DOCUMENT", required = false)
	public void setTypeOfDocument(String typeOfDocument) {
		this.typeOfDocument = typeOfDocument;
	}
	public String getTypeOfDocument2() {
		return typeOfDocument2;
	}
	
	@XmlElement(name = "TYPE_OF_DOCUMENT2", required = false)
	public void setTypeOfDocument2(String typeOfDocument2) {
		this.typeOfDocument2 = typeOfDocument2;
	}
	public Date getDateOfIssue() {
		return dateOfIssue;
	}
	@XmlElement(name = "DATE_OF_ISSUE", required = false)
	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public String getNum() {
		return num;
	}
	@XmlElement(name = "NUMBER", required = false)
	public void setNum(String num) {
		this.num = num;
	}
	public String getNote() {
		return note;
	}
	
	@XmlElement(name = "NOTE", required = false)
	public void setNote(String note) {
		this.note = note;
	}
	public String getIssuingCountry() {
		return issuingCountry;
	}
	
	@XmlElement(name = "ISSUING_COUNTRY", required = false)
	public void setIssuingCountry(String issuingCountry) {
		this.issuingCountry = issuingCountry;
	}
	public String getCountryOfIssue() {
		return countryOfIssue;
	}
	
	@XmlElement(name = "COUNTRY_OF_ISSUE", required = false)
	public void setCountryOfIssue(String countryOfIssue) {
		this.countryOfIssue = countryOfIssue;
	}
	public String getCityOfIssue() {
		return cityOfIssue;
	}
	
	@XmlElement(name = "CITY_OF_ISSUE", required = false)
	public void setCityOfIssue(String cityOfIssue) {
		this.cityOfIssue = cityOfIssue;
	}
	public IndividualDocument(String typeOfDocument, String typeOfDocument2, Date dateOfIssue, String num, String note,
			String issuingCountry, String countryOfIssue, String cityOfIssue) {
		this.typeOfDocument = typeOfDocument;
		this.typeOfDocument2 = typeOfDocument2;
		this.dateOfIssue = dateOfIssue;
		this.num = num;
		this.note = note;
		this.issuingCountry = issuingCountry;
		this.countryOfIssue = countryOfIssue;
		this.cityOfIssue = cityOfIssue;
	}
	@Override
	public String toString() {
		return "IndividualDocument [typeOfDocument=" + typeOfDocument + ", typeOfDocument2=" + typeOfDocument2
				+ ", dateOfIssue=" + dateOfIssue + ", num=" + num + ", note=" + note + ", issuingCountry="
				+ issuingCountry + ", countryOfIssue=" + countryOfIssue + ", cityOfIssue=" + cityOfIssue
				+ ", getTypeOfDocument()=" + getTypeOfDocument() + ", getTypeOfDocument2()=" + getTypeOfDocument2()
				+ ", getDateOfIssue()=" + getDateOfIssue() + ", getNum()=" + getNum() + ", getNote()=" + getNote()
				+ ", getIssuingCountry()=" + getIssuingCountry() + ", getCountryOfIssue()=" + getCountryOfIssue()
				+ ", getCityOfIssue()=" + getCityOfIssue() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
}

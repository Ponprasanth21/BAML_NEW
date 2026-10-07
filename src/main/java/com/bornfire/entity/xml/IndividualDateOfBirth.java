package com.bornfire.entity.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDIVIDUAL_DATE_OF_BIRTH")
public class IndividualDateOfBirth {
	
	private String typeOfDate;
	private String year;
	private String fromYear;
	private String toYear;
	private String date;
		
	public IndividualDateOfBirth() {}

	public String getTypeOfDate() {
		return typeOfDate;
	}

	@XmlElement(name = "TYPE_OF_DATE", required = false)
	public void setTypeOfDate(String typeOfDate) {
		this.typeOfDate = typeOfDate;
	}

	public String getYear() {
		return year;
	}

	@XmlElement(name = "YEAR", required = false)
	public void setYear(String year) {
		this.year = year;
	}

	public String getFromYear() {
		return fromYear;
	}

	@XmlElement(name = "FROM_YEAR", required = false)
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	@XmlElement(name = "TO_YEAR", required = false)
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getDate() {
		return date;
	}

	@XmlElement(name = "DATE", required = false)
	public void setDate(String date) {
		this.date = date;
	}

	public IndividualDateOfBirth(String typeOfDate, String year, String fromYear, String toYear, String date) {
		this.typeOfDate = typeOfDate;
		this.year = year;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.date = date;
	}

	@Override
	public String toString() {
		return "IndividualDateOfBirth [typeOfDate=" + typeOfDate + ", year=" + year + ", fromYear=" + fromYear
				+ ", toYear=" + toYear + ", date=" + date + "]";
	}

	
	
}

package com.bornfire.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDIVIDUAL_PLACE_OF_BIRTH")
public class IndividualPlaceOfBirth {

	private String city;
	private String stateProvince;
	private String country;
	private String note;
	public String getCity() {
		return city;
	}
	
	@XmlElement(name = "CITY", required = false)
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	
	@XmlElement(name = "STATE_PROVINCE", required = false)
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getCountry() {
		return country;
	}
	
	@XmlElement(name = "COUNTRY", required = false)
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNote() {
		return note;
	}
	
	@XmlElement(name = "NOTE", required = false)
	public void setNote(String note) {
		this.note = note;
	}
	public IndividualPlaceOfBirth(String city, String stateProvince, String country, String note) {
		this.city = city;
		this.stateProvince = stateProvince;
		this.country = country;
		this.note = note;
	}
	
	public IndividualPlaceOfBirth() {}

	@Override
	public String toString() {
		return "IndividualPlaceOfBirth [city=" + city + ", stateProvince=" + stateProvince + ", country=" + country
				+ ", note=" + note + "]";
	}
	
	
}

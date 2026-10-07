package com.bornfire.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDIVIDUAL_ADDRESS")
public class IndividualAddress {

	private String street;
	private String city;
	private String state;
	private String stateProvince;
	private String country;
	private String note;

	public IndividualAddress() {
	}

	public String getStreet() {
		return street; 
	}

	@XmlElement(name = "STREET", required = false)
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	@XmlElement(name = "CITY", required = false)
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@XmlElement(name = "STATE", required = false)
	public void setState(String state) {
		this.state = state;
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

	public IndividualAddress(String street, String city, String state, String stateProvince, String country,
			String note) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.stateProvince = stateProvince;
		this.country = country;
		this.note = note;
		
		
	}

	@Override
	public String toString() {
		return "IndividualAddress [street=" + street + ", city=" + city + ", state=" + state + ", stateProvince="
				+ stateProvince + ", country=" + country + ", note=" + note + "]";
	}

	
}

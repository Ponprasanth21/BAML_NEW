package com.bornfire.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ENTITY_ADDRESS")
public class EntityAddress {
	
	
	private String  street;
	private String  city;
	private String  country;
	private String  note;
	private String zipcode;
	
	
	 public String getZipcode() {
		return zipcode;
	}
   @XmlElement(name="ZIP_CODE")
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public EntityAddress() {
	 }
	 
	public String getStreet() {
		return street;
	}
	
	@XmlElement(name = "STREET")

	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	@XmlElement(name = "CITY")

	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	
	@XmlElement(name = "COUNTRY")

	public void setCountry(String country) {
		this.country = country;
		
		
	}

	public String getNote() {
		return note;
	}
	@XmlElement(name = "NOTE")

	public void setNote(String note) {
		this.note = note;
	}

	

	
	public EntityAddress(String street, String city, String country, String note, String zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.note = note;
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "EntityAddress [street=" + street + ", city=" + city + ", country=" + country + ", note=" + note
				+ ", zipcode=" + zipcode + "]";
	}
	
	
	
	
	

}

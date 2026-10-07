package com.bornfire.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "",name = "DESIGNATION")
public class Designation {

	
	private List<String> value;

	public List<String> getValue() {
		return value;
	}

	@XmlElement(name = "VALUE", required = false)
	public void setValue(List<String> value) {
		this.value = value;
	}

	public Designation() {
	}

	public Designation(List<String> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Designation [value=" + value + "]";
	}
	
	

	
}

package com.bornfire.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NATIONALITY")
public class Nationality {

	private List<String> value;

	public List<String> getValue() {
		return value;
	}
	
	

	@Override
	public String toString() {
		return "Nationality [value=" + value + "]";
	}



	@XmlElement(name = "VALUE", required = false)
	public void setValue(List<String> value) {
		this.value = value;
	}

	public Nationality() {
	}

	public Nationality(List<String> value) {
		this.value = value;
	}
	
	
}

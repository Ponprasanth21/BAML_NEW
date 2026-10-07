package com.bornfire.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "TITLE")
public class Title {

	private List<String> value;

	public List<String> getValue() {
		return value;
	}
	
	

	@Override
	public String toString() {
		return "Title [value=" + value + "]";
	}



	@XmlElement(name = "VALUE", required = false)
	public void setValue(List<String> value) {
		this.value = value;
	}

	public Title() {
	}

	public Title(List<String> value) {
		this.value = value;
	}
	
	
}
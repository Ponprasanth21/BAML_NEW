package com.bornfire.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LIST_TYPE")
public class ListType {

	private List<String> value;

 public List<String> getValue() {
		return value;
	}
   @XmlElement(name = "VALUE", required = false)
	public void setValue(List<String> value) {
		this.value = value;
	}

    public ListType(List<String> value) {
	super();
	this.value = value;
}
	public ListType() {}
	@Override
	public String toString() {
		return "ListType [value=" + value + "]";
	}
	
	
}



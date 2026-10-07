package com.bornfire.entity.xml;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LAST_DAY_UPDATED")
public class LastDateUpdated {

	private List<Date> value;

	
	public LastDateUpdated() {
	}

	public List<Date> getValue() {
		return value;
	}
	
	@XmlElement(name = "VALUE", required = false)
	public void setValue(List<Date> value) {
		this.value = value;
	}

	public LastDateUpdated(List<Date> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LastDateUpdated [value=" + value + "]";
	}

	
}

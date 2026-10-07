package com.bornfire.entity.xml;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LAST_DAY_UPDATED")
public class LastDayUpdated {
	
	private List<Date> value;

	public LastDayUpdated() {
	}

	
	
	public List<Date> getValue() {
		return value;
	}
	
	
	@XmlElement(name = "VALUE")
    public void setValue(List<Date> value) {
		this.value = value;
	}



	public LastDayUpdated(List<Date> value) {
		super();
		this.value = value;
	}



	@Override
	public String toString() {
		return "LastDayUpdated [value=" + value + "]";
	}

	
	
	
	
	
	

}

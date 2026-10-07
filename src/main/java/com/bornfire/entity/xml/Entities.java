package com.bornfire.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@XmlRootElement(name = "ENTITIES")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entities {
	
	private List<Entity> entity;

	public List<Entity> getEntity() {
		return entity;
	}
   @XmlElement(name="ENTITY")
	public void setEntity(List<Entity> entity) {
		this.entity = entity;
	}

	public Entities(List<Entity> entity) {
		super();
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "Entities [entity=" + entity + ", getEntity()=" + getEntity() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public Entities() {}

}
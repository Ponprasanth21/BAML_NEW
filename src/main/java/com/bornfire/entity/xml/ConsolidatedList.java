package com.bornfire.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "CONSOLIDATED_LIST")
public class ConsolidatedList {

	
	private Individuals ind;
	private Entities ent;

	public Individuals getInd() {
		return ind;
	}

	@XmlElement(name = "INDIVIDUALS")
	public void setInd(Individuals ind) {
		this.ind = ind; 
	}

	public Entities getEnt() {
		return ent;
	}

	@XmlElement(name = "ENTITIES")
	public void setEnt(Entities ent) {
		this.ent = ent;
	}

	@Override
	public String toString() {
		return "ConsolidatedList [ind=" + ind + ", ent=" + ent + ", getInd()=" + getInd() + ", getEnt()=" + getEnt()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public ConsolidatedList(Individuals ind, Entities ent) {
		super();
		this.ind = ind;
		this.ent = ent;
	}

	public ConsolidatedList() {

	}

}

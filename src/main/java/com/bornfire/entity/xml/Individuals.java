package com.bornfire.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;


@XmlRootElement(name = "INDIVIDUALS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Individuals {

	
	private List<Individual> individual;

	public List<Individual> getIndividual() {
		return individual;
	}
	@XmlElement(name = "INDIVIDUAL")
public void setIndividual(List<Individual> individual) {
		this.individual = individual;
	}

	public Individuals(List<Individual> individual) {
		super();
		this.individual = individual;
	}

	@Override
	public String toString() {
		return "Individuals [individual=" + individual + ", getIndividual()=" + getIndividual() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Individuals() {
	}
}

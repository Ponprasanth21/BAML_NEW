package com.bornfire.taskapi;

import java.util.List;

import com.ibm.icu.math.BigDecimal;

public class LiftAPI {
	
	private BigDecimal numberOfElevators;
	private BigDecimal numberOfFloors;
	private List<elevators> elevators;
	public BigDecimal getNumberOfElevators() {
		return numberOfElevators;
	}
	public void setNumberOfElevators(BigDecimal numberOfElevators) {
		this.numberOfElevators = numberOfElevators;
	}
	public BigDecimal getNumberOfFloors() {
		return numberOfFloors;
	}
	public void setNumberOfFloors(BigDecimal numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	public List<elevators> getElevators() {
		return elevators;
	}
	public void setElevators(List<elevators> elevators) {
		this.elevators = elevators;
	}
	public LiftAPI(BigDecimal numberOfElevators, BigDecimal numberOfFloors,
			List<com.bornfire.taskapi.elevators> elevators) {
		super();
		this.numberOfElevators = numberOfElevators;
		this.numberOfFloors = numberOfFloors;
		this.elevators = elevators;
	}
	public LiftAPI() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

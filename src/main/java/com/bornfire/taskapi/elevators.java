package com.bornfire.taskapi;

public class elevators {
	
	private String name;
	private String stops;
	private String direction;
	private String currentStop;
	private String status;
	private String totalTrip;
	private String totalHours;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStops() {
		return stops;
	}
	public void setStops(String stops) {
		this.stops = stops;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getCurrentStop() {
		return currentStop;
	}
	public void setCurrentStop(String currentStop) {
		this.currentStop = currentStop;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalTrip() {
		return totalTrip;
	}
	public void setTotalTrip(String totalTrip) {
		this.totalTrip = totalTrip;
	}
	public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	public elevators(String name, String stops, String direction, String currentStop, String status, String totalTrip,
			String totalHours) {
		super();
		this.name = name;
		this.stops = stops;
		this.direction = direction;
		this.currentStop = currentStop;
		this.status = status;
		this.totalTrip = totalTrip;
		this.totalHours = totalHours;
	}
	public elevators() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

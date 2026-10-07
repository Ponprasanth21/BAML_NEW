package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ThirdPartyResponse {
	
	private String TranID;
	private String TranDateTime;
	private String Status;
	public String getTranID() {
		return TranID;
	}
	public String getTranDateTime() {
		return TranDateTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setTranID(String tranID) {
		TranID = tranID;
	}
	public void setTranDateTime(String tranDateTime) {
		TranDateTime = tranDateTime;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public ThirdPartyResponse(String tranID, String tranDateTime) {
		super();
		TranID = tranID;
		TranDateTime = tranDateTime;
		
	}
	public ThirdPartyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


}

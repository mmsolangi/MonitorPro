package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ChartStatusDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<ChartSummaryDTO> getSummary() {
		return summary;
	}
	public void setSummary(ArrayList<ChartSummaryDTO> summary) {
		this.summary = summary;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	String status;	 
	ArrayList <ChartSummaryDTO> summary;
	String message;
	
}

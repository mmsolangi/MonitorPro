package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ChartSummaryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<ChartDetailDTO> getDetail() {
		return detail;
	}
	public void setDetail(ArrayList<ChartDetailDTO> detail) {
		this.detail = detail;
	}
	String status;
	ArrayList <ChartDetailDTO> detail;


}

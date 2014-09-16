package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PageDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	String currentPage;
	String totalRecords;
	public String getPageNumber() {
		return currentPage;
	}
	public void setPageNumber(String pageNumber) {
		this.currentPage = pageNumber;
	}
	public String getTotalNoOfRecords() {
		return totalRecords;
	}
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalRecords = totalNoOfRecords;
	}

	
	ArrayList<LogHeader> data;
	public ArrayList<LogHeader> getLogHeader() {
		return data;
	}
	public void setLogHeader(ArrayList<LogHeader> logHeader) {
		this.data = logHeader;
	}
	
	

}

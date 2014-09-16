package com.rc.json.dto;

import java.io.Serializable;

/*
 * lob":"Orders",
 * "orderid":"111",
 * "status":"Success",
 * "fromdate":"07/17/2013",
 * "todate":"07/17/2013",
 * "currpage":"1",
 * "pagesize":"10",
 * "sortby":"outcomeHdrID",
 * "sortdir":"Asc"}

*/
public class QueryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String getLob() {
		return lob;
	}

	public String getOrderid() {
		return orderid;
	}

	public String getStatus() {
		return status;
	}

	public String getFromdate() {
		return fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public String getCurrpage() {
		return currpage;
	}

	public String getPagesize() {
		return pagesize;
	}

	public String getSortby() {
		return sortby;
	}

	public String getSortdir() {
		return sortdir;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public void setCurrpage(String currpage) {
		this.currpage = currpage;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}

	public void setSortdir(String sortdir) {
		this.sortdir = sortdir;
	}

	String lob;
	String orderid;
	String status;
	String fromdate;
	String todate;
	String currpage;
	String pagesize;
	String sortby;
	String sortdir;
	String applicationcode;
	String payloadSearch;
	
	public String getPayloadSearch() {
		return payloadSearch;
	}

	public void setPayloadSearch(String payloadSearch) {
		this.payloadSearch = payloadSearch;
	}

	public String getApplicationcode() {
		return applicationcode;
	}

	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}

	public String toString() {
		
		  return  "QueryDTO store="+applicationcode+" lob=" + lob + ", orderid=" + orderid + ", status=" + status + " fromdate="+fromdate+ " todate="+todate+" currpage="+currpage+" pagesize="+pagesize+" sortby="+sortby+" sortdir="+sortdir;
		   
	}
}

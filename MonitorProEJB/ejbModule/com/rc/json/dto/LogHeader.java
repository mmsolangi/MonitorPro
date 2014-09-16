package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class LogHeader implements Serializable{
	private static final long serialVersionUID = 1L;
	public String getOutcomeHdrID() {
		return outcomeHdrID;
	}
	public void setOutcomeHdrID(String outcomeHdrID) {
		this.outcomeHdrID = outcomeHdrID;
	}
	public String getSlCode() {
		return slCode;
	}
	public void setSlCode(String slCode) {
		this.slCode = slCode;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getCorrelID() {
		return correlID;
	}
	public void setCorrelID(String correlID) {
		this.correlID = correlID;
	}
	public String getClientUserID() {
		return clientUserID;
	}
	public void setClientUserID(String clientUserID) {
		this.clientUserID = clientUserID;
	}
	public String getClientReqDateTime() {
		return clientReqDateTime;
	}
	public void setClientReqDateTime(String clientReqDateTime) {
		this.clientReqDateTime = clientReqDateTime;
	}
	public String getOutcomeStatus() {
		return outcomeStatus;
	}
	public void setOutcomeStatus(String outcomeStatus) {
		this.outcomeStatus = outcomeStatus;
	}
	public String getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public ArrayList<LogHeaderDetail> getLogHeaderDetail() {
		return detail;
	}
	public void setLogHeaderDetail(ArrayList<LogHeaderDetail> logHeaderDetail) {
		this.detail = logHeaderDetail;
	}
	String outcomeHdrID;
	String slCode;
	String applicationID;
	String correlID;
	String clientUserID;
	String clientReqDateTime;
	String outcomeStatus;
	String timeTaken;
	String errorCode;
	String lob;
	String storeName;
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public ArrayList<LogHeaderDetail> getDetail() {
		return detail;
	}
	public void setDetail(ArrayList<LogHeaderDetail> detail) {
		this.detail = detail;
	}
	ArrayList <LogHeaderDetail> detail;


}

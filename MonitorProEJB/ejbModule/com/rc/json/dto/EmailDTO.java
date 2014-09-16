package com.rc.json.dto;

import java.io.Serializable;

public class EmailDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}

	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public String getLobid() {
		return lobid;
	}
	public void setLobid(String lobid) {
		this.lobid = lobid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	String lob;
	String event;
	String emails;
	String lobid;
	String id;
	
}

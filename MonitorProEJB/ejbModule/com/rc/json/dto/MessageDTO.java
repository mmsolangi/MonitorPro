package com.rc.json.dto;

import java.io.Serializable;



public class MessageDTO implements Serializable{
	private static final long serialVersionUID = 1L;
    public String getMsgCreatedDate() {
		return messageDate;
	}
	public void setMsgCreatedDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return username;
	}
	public void setUserId(String username) {
		this.username = username;
	}
	private String messageDate;
    private String message;
    private String username;

}

package com.rc.json.dto;

import java.io.Serializable;

public class LobDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

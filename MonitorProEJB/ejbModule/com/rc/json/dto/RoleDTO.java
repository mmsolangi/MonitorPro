package com.rc.json.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	String lobid;

	public String getLobid() {
		return lobid;
	}

	public void setLobid(String lobid) {
		this.lobid = lobid;
	}
}

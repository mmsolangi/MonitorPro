package com.rc.json.dto;

import java.io.Serializable;

public class LoginTokenDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	String token;
	String isadmin;

	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

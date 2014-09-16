package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	public int getUserID() {
		return id;
	}
	public void setUserID(int userID) {
		this.id = userID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	int id;
	String token;
	String username;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String password;
	String email;
	String status;
	String isadmin;
	
	String phone;
	String address;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsAdmin() {
		return isadmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isadmin = isAdmin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	ArrayList <RoleDTO> roles;
	public ArrayList<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<RoleDTO> roles) {
		this.roles = roles;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	

}

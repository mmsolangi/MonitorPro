package com.rc.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long loginid;

	private Timestamp expireon;

	private String isexpired;

	private Timestamp logindate;

	private String tokenid;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public Login() {
	}

	public long getLoginid() {
		return this.loginid;
	}

	public void setLoginid(long loginid) {
		this.loginid = loginid;
	}

	public Timestamp getExpireon() {
		return this.expireon;
	}

	public void setExpireon(Timestamp expireon) {
		this.expireon = expireon;
	}

	public String getIsexpired() {
		return this.isexpired;
	}

	public void setIsexpired(String isexpired) {
		this.isexpired = isexpired;
	}

	public Timestamp getLogindate() {
		return this.logindate;
	}

	public void setLogindate(Timestamp logindate) {
		this.logindate = logindate;
	}

	public String getTokenid() {
		return this.tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
package com.rc.jpa;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Login implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loginid")
	private long loginid;

	private String tokenid; 

	private Timestamp logindate;

	private Timestamp expireon;

	private String isexpired;

	@ManyToOne
	@JoinColumn(name="USERID")
	private User userid;

	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	public long getLoginid() {
		return this.loginid;
	}

	public void setLoginid(long loginid) {
		this.loginid = loginid;
	}

	public String getTokenid() {
		return this.tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public Timestamp getLogindate() {
		return this.logindate;
	}

	public void setLogindate(Timestamp logindate) {
		this.logindate = logindate;
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

	public User getUserid() {
		return this.userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

}

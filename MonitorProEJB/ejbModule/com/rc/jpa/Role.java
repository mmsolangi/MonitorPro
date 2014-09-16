package com.rc.jpa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleid")
	private int roleid;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="LOBID")
	private Lob lobid;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="USERID")
	private User userid;

	private static final long serialVersionUID = 1L;

	public Role() {
		super();
	}

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public Lob getLobid() {
		return this.lobid;
	}

	public void setLobid(Lob lobid) {
		this.lobid = lobid;
	}

	public User getUserid() {
		return this.userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

}

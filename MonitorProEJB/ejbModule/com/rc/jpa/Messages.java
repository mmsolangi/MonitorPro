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
public class Messages implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="messageid")
	private long messageid;

	private String messagedesc;

	private Timestamp messagedate;

	@ManyToOne
	@JoinColumn(name="USERID")
	private User userid;

	private static final long serialVersionUID = 1L;

	public Messages() {
		super();
	}

	public long getMessageid() {
		return this.messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

	public String getMessagedesc() {
		return this.messagedesc;
	}

	public void setMessagedesc(String messagedesc) {
		this.messagedesc = messagedesc;
	}

	public Timestamp getMessagedate() {
		return this.messagedate;
	}

	public void setMessagedate(Timestamp messagedate) {
		this.messagedate = messagedate;
	}

	public User getUserid() {
		return this.userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

}

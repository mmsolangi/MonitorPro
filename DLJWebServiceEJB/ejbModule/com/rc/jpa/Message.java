package com.rc.jpa;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp messagedate;

	private String messagedesc;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long messageid;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public Message() {
	}

	public Timestamp getMessagedate() {
		return this.messagedate;
	}

	public void setMessagedate(Timestamp messagedate) {
		this.messagedate = messagedate;
	}

	public String getMessagedesc() {
		return this.messagedesc;
	}

	public void setMessagedesc(String messagedesc) {
		this.messagedesc = messagedesc;
	}

	public long getMessageid() {
		return this.messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
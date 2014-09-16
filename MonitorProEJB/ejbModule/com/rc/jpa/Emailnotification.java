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

@Entity
public class Emailnotification implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="enid")
	private int enid;

	
	//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="LOBID")
	private Lob lobid;

	private String emails;

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	private String event;
	

	public String getEvent() {
		return event;
	}


	private static final long serialVersionUID = 1L;

	public Emailnotification() {
		super();
	}

	public Lob getLobid() {
		return lobid;
	}

	public void setLobid(Lob lobid) {
		this.lobid = lobid;
	}



	public int getEnid() {
		return this.enid;
	}

	public void setEnid(int enid) {
		this.enid = enid;
	}



	public void setEvent(String event) {
		this.event = event;
	}



}

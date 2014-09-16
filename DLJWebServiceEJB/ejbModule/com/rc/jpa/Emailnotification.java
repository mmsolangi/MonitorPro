package com.rc.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the emailnotification database table.
 * 
 */
@Entity
@NamedQuery(name="Emailnotification.findAll", query="SELECT e FROM Emailnotification e")
public class Emailnotification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int enid;

	private String emails;

	private String event;

	private int lobid;

	public Emailnotification() {
	}

	public int getEnid() {
		return this.enid;
	}

	public void setEnid(int enid) {
		this.enid = enid;
	}

	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getLobid() {
		return this.lobid;
	}

	public void setLobid(int lobid) {
		this.lobid = lobid;
	}

}
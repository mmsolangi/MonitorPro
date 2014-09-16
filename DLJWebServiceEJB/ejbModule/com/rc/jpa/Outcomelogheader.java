package com.rc.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the outcomelogheader database table.
 * 
 */
@Entity
@NamedQuery(name="Outcomelogheader.findAll", query="SELECT o FROM Outcomelogheader o")
public class Outcomelogheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long outcomehdrid;

	private String applicationcode;

	private Timestamp clientrequestdatetime;

	private String clientuserid;

	private String correlationid;

	private Timestamp dbupdcreatedttm;

	private String dbupdcreateusr;

	private String errorcode;

	private String errortype;

	private String lobcode;

	private String outcomestatus;

	private String parentcorrelationid;

	private String slcode;

	private BigInteger timetaken;

	//bi-directional many-to-one association to Outcomelogdetail
	@OneToMany(mappedBy="outcomelogheader", cascade=CascadeType.ALL)	
	private List<Outcomelogdetail> outcomelogdetails;

	public Outcomelogheader() {
	}

	public long getOutcomehdrid() {
		return this.outcomehdrid;
	}

	public void setOutcomehdrid(long outcomehdrid) {
		this.outcomehdrid = outcomehdrid;
	}

	public String getApplicationcode() {
		return this.applicationcode;
	}

	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}

	public Timestamp getClientrequestdatetime() {
		return this.clientrequestdatetime;
	}

	public void setClientrequestdatetime(Timestamp clientrequestdatetime) {
		this.clientrequestdatetime = clientrequestdatetime;
	}

	public String getClientuserid() {
		return this.clientuserid;
	}

	public void setClientuserid(String clientuserid) {
		this.clientuserid = clientuserid;
	}

	public String getCorrelationid() {
		return this.correlationid;
	}

	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}

	public Timestamp getDbupdcreatedttm() {
		return this.dbupdcreatedttm;
	}

	public void setDbupdcreatedttm(Timestamp dbupdcreatedttm) {
		this.dbupdcreatedttm = dbupdcreatedttm;
	}

	public String getDbupdcreateusr() {
		return this.dbupdcreateusr;
	}

	public void setDbupdcreateusr(String dbupdcreateusr) {
		this.dbupdcreateusr = dbupdcreateusr;
	}

	public String getErrorcode() {
		return this.errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrortype() {
		return this.errortype;
	}

	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}

	public String getLobcode() {
		return this.lobcode;
	}

	public void setLobcode(String lobcode) {
		this.lobcode = lobcode;
	}

	public String getOutcomestatus() {
		return this.outcomestatus;
	}

	public void setOutcomestatus(String outcomestatus) {
		this.outcomestatus = outcomestatus;
	}

	public String getParentcorrelationid() {
		return this.parentcorrelationid;
	}

	public void setParentcorrelationid(String parentcorrelationid) {
		this.parentcorrelationid = parentcorrelationid;
	}

	public String getSlcode() {
		return this.slcode;
	}

	public void setSlcode(String slcode) {
		this.slcode = slcode;
	}

	public BigInteger getTimetaken() {
		return this.timetaken;
	}

	public void setTimetaken(BigInteger timetaken) {
		this.timetaken = timetaken;
	}

	public List<Outcomelogdetail> getOutcomelogdetails() {
		return this.outcomelogdetails;
	}

	public void setOutcomelogdetails(List<Outcomelogdetail> outcomelogdetails) {
		this.outcomelogdetails = outcomelogdetails;
	}

	public Outcomelogdetail addOutcomelogdetail(Outcomelogdetail outcomelogdetail) {
		getOutcomelogdetails().add(outcomelogdetail);
		outcomelogdetail.setOutcomelogheader(this);

		return outcomelogdetail;
	}

	public Outcomelogdetail removeOutcomelogdetail(Outcomelogdetail outcomelogdetail) {
		getOutcomelogdetails().remove(outcomelogdetail);
		outcomelogdetail.setOutcomelogheader(null);

		return outcomelogdetail;
	}

}
package com.rc.jpa;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name="OUTCOMELOGHEADER")
public class Outcomelogheader implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="outcomehdrid")
	private long outcomehdrid;

	private String slcode;

	private String applicationcode;

	private String correlationid;

	private String parentcorrelationid;

	private String clientuserid;

	private Timestamp clientrequestdatetime;

	private String outcomestatus;

	private long timetaken;

	private String errorcode;

	private String dbupdcreateusr;

	private Timestamp dbupdcreatedttm;

	private String lobcode;

	private String errortype;

	@OneToMany(mappedBy="outcomehdrid")
	private Set<Outcomelogdetail> outcomelogdetailCollection;

	private static final long serialVersionUID = 1L;

	public Outcomelogheader() {
		super();
	}

	public long getOutcomehdrid() {
		return this.outcomehdrid;
	}

	public void setOutcomehdrid(long outcomehdrid) {
		this.outcomehdrid = outcomehdrid;
	}

	public String getSlcode() {
		return this.slcode;
	}

	public void setSlcode(String slcode) {
		this.slcode = slcode;
	}

	public String getApplicationcode() {
		return this.applicationcode;
	}

	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}

	public String getCorrelationid() {
		return this.correlationid;
	}

	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}

	public String getParentcorrelationid() {
		return this.parentcorrelationid;
	}

	public void setParentcorrelationid(String parentcorrelationid) {
		this.parentcorrelationid = parentcorrelationid;
	}

	public String getClientuserid() {
		return this.clientuserid;
	}

	public void setClientuserid(String clientuserid) {
		this.clientuserid = clientuserid;
	}

	public Timestamp getClientrequestdatetime() {
		return this.clientrequestdatetime;
	}

	public void setClientrequestdatetime(Timestamp clientrequestdatetime) {
		this.clientrequestdatetime = clientrequestdatetime;
	}

	public String getOutcomestatus() {
		return this.outcomestatus;
	}

	public void setOutcomestatus(String outcomestatus) {
		this.outcomestatus = outcomestatus;
	}

	public long getTimetaken() {
		return this.timetaken;
	}

	public void setTimetaken(long timetaken) {
		this.timetaken = timetaken;
	}

	public String getErrorcode() {
		return this.errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getDbupdcreateusr() {
		return this.dbupdcreateusr;
	}

	public void setDbupdcreateusr(String dbupdcreateusr) {
		this.dbupdcreateusr = dbupdcreateusr;
	}

	public Timestamp getDbupdcreatedttm() {
		return this.dbupdcreatedttm;
	}

	public void setDbupdcreatedttm(Timestamp dbupdcreatedttm) {
		this.dbupdcreatedttm = dbupdcreatedttm;
	}

	public String getLobcode() {
		return this.lobcode;
	}

	public void setLobcode(String lobcode) {
		this.lobcode = lobcode;
	}

	public String getErrortype() {
		return this.errortype;
	}

	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}

	public Set<Outcomelogdetail> getOutcomelogdetailCollection() {
		return this.outcomelogdetailCollection;
	}

	public void setOutcomelogdetailCollection(Set<Outcomelogdetail> outcomelogdetailCollection) {
		this.outcomelogdetailCollection = outcomelogdetailCollection;
	}

}

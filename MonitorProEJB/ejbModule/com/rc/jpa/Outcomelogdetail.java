package com.rc.jpa;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Outcomelogdetail implements Serializable {
	@Id
	private long outcomedtlid;

	private long executionseq;

	private String slstepname;

	private String sourcesystemerrorcode;

	@Lob
	private String sourcesystemerrordump;

	private String sourcesystemerrordesc;

	@Lob
	private String inputpayload;

	@Lob
	private String outputpayload;

	private String debugentry;

	private String server;

	private Timestamp entrytimeatstep;

	private Timestamp exittimeatstep;

	private String errortype;

	private String applicationcode;

	private Timestamp dbupdcreatedttm;

	@Lob
	private byte[] inputpayloadenc;

	@Lob
	private byte[] outputpayloadenc;

	private String dbupdcreateusr;

	@ManyToOne
	@JoinColumn(name="OUTCOMEHDRID")
	private Outcomelogheader outcomehdrid;

	private static final long serialVersionUID = 1L;

	public Outcomelogdetail() {
		super();
	}

	public long getOutcomedtlid() {
		return this.outcomedtlid;
	}

	public void setOutcomedtlid(long outcomedtlid) {
		this.outcomedtlid = outcomedtlid;
	}

	public long getExecutionseq() {
		return this.executionseq;
	}

	public void setExecutionseq(long executionseq) {
		this.executionseq = executionseq;
	}

	public String getSlstepname() {
		return this.slstepname;
	}

	public void setSlstepname(String slstepname) {
		this.slstepname = slstepname;
	}

	public String getSourcesystemerrorcode() {
		return this.sourcesystemerrorcode;
	}

	public void setSourcesystemerrorcode(String sourcesystemerrorcode) {
		this.sourcesystemerrorcode = sourcesystemerrorcode;
	}

	public String getSourcesystemerrordump() {
		return this.sourcesystemerrordump;
	}

	public void setSourcesystemerrordump(String sourcesystemerrordump) {
		this.sourcesystemerrordump = sourcesystemerrordump;
	}

	public String getSourcesystemerrordesc() {
		return this.sourcesystemerrordesc;
	}

	public void setSourcesystemerrordesc(String sourcesystemerrordesc) {
		this.sourcesystemerrordesc = sourcesystemerrordesc;
	}

	public String getInputpayload() {
		return this.inputpayload;
	}

	public void setInputpayload(String inputpayload) {
		this.inputpayload = inputpayload;
	}

	public String getOutputpayload() {
		return this.outputpayload;
	}

	public void setOutputpayload(String outputpayload) {
		this.outputpayload = outputpayload;
	}

	public String getDebugentry() {
		return this.debugentry;
	}

	public void setDebugentry(String debugentry) {
		this.debugentry = debugentry;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Timestamp getEntrytimeatstep() {
		return this.entrytimeatstep;
	}

	public void setEntrytimeatstep(Timestamp entrytimeatstep) {
		this.entrytimeatstep = entrytimeatstep;
	}

	public Timestamp getExittimeatstep() {
		return this.exittimeatstep;
	}

	public void setExittimeatstep(Timestamp exittimeatstep) {
		this.exittimeatstep = exittimeatstep;
	}

	public String getErrortype() {
		return this.errortype;
	}

	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}

	public String getApplicationcode() {
		return this.applicationcode;
	}

	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}

	public Timestamp getDbupdcreatedttm() {
		return this.dbupdcreatedttm;
	}

	public void setDbupdcreatedttm(Timestamp dbupdcreatedttm) {
		this.dbupdcreatedttm = dbupdcreatedttm;
	}

	public byte[] getInputpayloadenc() {
		return this.inputpayloadenc;
	}

	public void setInputpayloadenc(byte[] inputpayloadenc) {
		this.inputpayloadenc = inputpayloadenc;
	}

	public byte[] getOutputpayloadenc() {
		return this.outputpayloadenc;
	}

	public void setOutputpayloadenc(byte[] outputpayloadenc) {
		this.outputpayloadenc = outputpayloadenc;
	}

	public String getDbupdcreateusr() {
		return this.dbupdcreateusr;
	}

	public void setDbupdcreateusr(String dbupdcreateusr) {
		this.dbupdcreateusr = dbupdcreateusr;
	}

	public Outcomelogheader getOutcomehdrid() {
		return this.outcomehdrid;
	}

	public void setOutcomehdrid(Outcomelogheader outcomehdrid) {
		this.outcomehdrid = outcomehdrid;
	}

}

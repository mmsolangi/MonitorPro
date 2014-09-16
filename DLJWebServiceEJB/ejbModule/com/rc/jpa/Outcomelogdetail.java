package com.rc.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import javax.persistence.Lob;


/**
 * The persistent class for the outcomelogdetail database table.
 * 
 */
@Entity
@NamedQuery(name="Outcomelogdetail.findAll", query="SELECT o FROM Outcomelogdetail o")
public class Outcomelogdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long outcomedtlid;

	private String applicationcode;

	private Timestamp dbupdcreatedttm;

	private String dbupdcreateusr;

	@Lob
	private String debugentry;

	private Timestamp entrytimeatstep;

	private String errortype;

	private long executionseq;

	private Timestamp exittimeatstep;

	@Lob
	private String inputpayload;

	@Lob
	private byte[] inputpayloadenc;

	@Lob
	private String outputpayload;

	@Lob
	private byte[] outputpayloadenc;

	private String server;

	private String slstepname;

	private String sourcesystemerrorcode;

	@Lob
	private String sourcesystemerrordesc;

	@Lob
	private String sourcesystemerrordump;

	//bi-directional many-to-one association to Outcomelogheader
	@ManyToOne
	@JoinColumn(name="OUTCOMEHDRID")
	private Outcomelogheader outcomelogheader;

	public Outcomelogdetail() {
	}

	public long getOutcomedtlid() {
		return this.outcomedtlid;
	}

	public void setOutcomedtlid(long outcomedtlid) {
		this.outcomedtlid = outcomedtlid;
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

	public String getDbupdcreateusr() {
		return this.dbupdcreateusr;
	}

	public void setDbupdcreateusr(String dbupdcreateusr) {
		this.dbupdcreateusr = dbupdcreateusr;
	}

	public String getDebugentry() {
		return this.debugentry;
	}

	public void setDebugentry(String debugentry) {
		this.debugentry = debugentry;
	}

	public Timestamp getEntrytimeatstep() {
		return this.entrytimeatstep;
	}

	public void setEntrytimeatstep(Timestamp entrytimeatstep) {
		this.entrytimeatstep = entrytimeatstep;
	}

	public String getErrortype() {
		return this.errortype;
	}

	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}

	public long getExecutionseq() {
		return this.executionseq;
	}

	public void setExecutionseq(long executionseq) {
		this.executionseq = executionseq;
	}

	public Timestamp getExittimeatstep() {
		return this.exittimeatstep;
	}

	public void setExittimeatstep(Timestamp exittimeatstep) {
		this.exittimeatstep = exittimeatstep;
	}

	public String getInputpayload() {
		return this.inputpayload;
	}

	public void setInputpayload(String inputpayload) {
		this.inputpayload = inputpayload;
	}

	public byte[] getInputpayloadenc() {
		return this.inputpayloadenc;
	}

	public void setInputpayloadenc(byte[] inputpayloadenc) {
		this.inputpayloadenc = inputpayloadenc;
	}

	public String getOutputpayload() {
		return this.outputpayload;
	}

	public void setOutputpayload(String outputpayload) {
		this.outputpayload = outputpayload;
	}

	public byte[] getOutputpayloadenc() {
		return this.outputpayloadenc;
	}

	public void setOutputpayloadenc(byte[] outputpayloadenc) {
		this.outputpayloadenc = outputpayloadenc;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
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

	public String getSourcesystemerrordesc() {
		return this.sourcesystemerrordesc;
	}

	public void setSourcesystemerrordesc(String sourcesystemerrordesc) {
		this.sourcesystemerrordesc = sourcesystemerrordesc;
	}

	public String getSourcesystemerrordump() {
		return this.sourcesystemerrordump;
	}

	public void setSourcesystemerrordump(String sourcesystemerrordump) {
		this.sourcesystemerrordump = sourcesystemerrordump;
	}

	public Outcomelogheader getOutcomelogheader() {
		return this.outcomelogheader;
	}

	public void setOutcomelogheader(Outcomelogheader outcomelogheader) {
		this.outcomelogheader = outcomelogheader;
	}

}
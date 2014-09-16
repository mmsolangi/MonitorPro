package com.rc.json.dto;

import java.io.Serializable;
import java.sql.Timestamp;


public class LogHeaderDetail implements Serializable{

	private static final long serialVersionUID = 1L;           
    
	public String getOutcomeDTLID() {
		return outcomeDTLID;
	}
	public void setOutcomeDTLID(String outcomeDTLID) {
		this.outcomeDTLID = outcomeDTLID;
	}
	public String getSlStepName() {
		return slStepName;
	}
	public void setSlStepName(String slStepName) {
		this.slStepName = slStepName;
	}
	public String getSourceSystemErrorCode() {
		return sourceSystemErrorCode;
	}
	public void setSourceSystemErrorCode(String sourceSystemErrorCode) {
		this.sourceSystemErrorCode = sourceSystemErrorCode;
	}
	public String getSourceSystemErrorDesc() {
		return sourceSystemErrorDesc;
	}
	public void setSourceSystemErrorDesc(String sourceSystemErrorDesc) {
		this.sourceSystemErrorDesc = sourceSystemErrorDesc;
	}
	public String getInputPayload() {
		return inputPayload;
	}
	public void setInputPayload(String inputPayload) {
		this.inputPayload = inputPayload;
	}
	public String getOutputPayload() {
		return outputPayload;
	}
	public void setOutputPayload(String outputPayload) {
		this.outputPayload = outputPayload;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	String outcomeDTLID;
	String slStepName;
	String sourceSystemErrorCode;
	String sourceSystemErrorDesc;
	String inputPayload;
	String outputPayload;
	String applicationCode;
	String logLevel;
	
	
	public long getExecutionSeq() {
		return executionSeq;
	}
	public void setExecutionSeq(long executionSeq) {
		this.executionSeq = executionSeq;
	}
	public String getDebugEntry() {
		return debugEntry;
	}
	public void setDebugEntry(String debugEntry) {
		this.debugEntry = debugEntry;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public Timestamp getEntryTimeatstep() {
		return entryTimeatstep;
	}
	public void setEntryTimeatstep(Timestamp entryTimeatstep) {
		this.entryTimeatstep = entryTimeatstep;
	}
	public Timestamp getExitTimeatstep() {
		return exitTimeatstep;
	}
	public void setExitTimeatstep(Timestamp exitTimeatstep) {
		this.exitTimeatstep = exitTimeatstep;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public Timestamp getDbupdCreatedttm() {
		return dbupdCreatedttm;
	}
	public void setDbupdCreatedttm(Timestamp dbupdCreatedttm) {
		this.dbupdCreatedttm = dbupdCreatedttm;
	}
	public byte[] getInputPayloaEenc() {
		return inputPayloaEenc;
	}
	public void setInputPayloaEenc(byte[] inputPayloaEenc) {
		this.inputPayloaEenc = inputPayloaEenc;
	}
	public byte[] getOutputPayloadEnc() {
		return outputPayloadEnc;
	}
	public void setOutputPayloadEnc(byte[] outputPayloadEnc) {
		this.outputPayloadEnc = outputPayloadEnc;
	}
	public String getDbupdCreateusr() {
		return dbupdCreateusr;
	}
	public void setDbupdCreateusr(String dbupdCreateusr) {
		this.dbupdCreateusr = dbupdCreateusr;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	long executionSeq;
	String debugEntry;
	String server;
	Timestamp entryTimeatstep;
	Timestamp exitTimeatstep;
	String errorType;	
	Timestamp dbupdCreatedttm;
	byte[] inputPayloaEenc;
	byte[] outputPayloadEnc;
	String dbupdCreateusr;
	
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

}

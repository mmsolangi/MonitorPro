package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class StatusDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	String status;
	PageDTO lob;
	ArrayList <ChartSummaryDTO> summary;
	ArrayList <TransactionDTO> transactions;
	ArrayList <MessageDTO> messages;
	String message;
	LoginTokenDTO data;
	ArrayList <LobDTO> lobs;	
	ArrayList <EmailDTO> nfemails;
	ArrayList <UserDTO> users;
	private PerfGraphDTO performance;
	UserDTO user;
	ArrayList <StoreDTO> stores;
	
	public ArrayList<StoreDTO> getStores() {
		return stores;
	}
	public void setStores(ArrayList<StoreDTO> stores) {
		this.stores = stores;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public PageDTO getLob() {
		return lob;
	}
	public void setLob(PageDTO lob) {
		this.lob = lob;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<ChartSummaryDTO> getSummary() {
		return summary;
	}
	public void setSummary(ArrayList<ChartSummaryDTO> summary) {
		this.summary = summary;
	}
	
	public ArrayList<TransactionDTO> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

	public ArrayList<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<MessageDTO> messages) {
		this.messages = messages;
	}

	public LoginTokenDTO getData() {
		return data;
	}
	public void setData(LoginTokenDTO data) {
		this.data = data;
	}

	public ArrayList<LobDTO> getLobTabs() {
		return lobs;
	}
	public void setLobTabs(ArrayList<LobDTO> lobs) {
		this.lobs = lobs;
	}

	public ArrayList<EmailDTO> getNfemails() {
		return nfemails;
	}
	public void setNfemails(ArrayList<EmailDTO> nfemails) {
		this.nfemails = nfemails;
	}
	
	public ArrayList<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<UserDTO> users) {
		this.users = users;
	}
	
	public ArrayList<LobDTO> getLobs() {
		return lobs;
	}
	public void setLobs(ArrayList<LobDTO> lobs) {
		this.lobs = lobs;
	}
	public PerfGraphDTO getPerformance() {
		return performance;
	}
	public void setPerformance(PerfGraphDTO performance) {
		this.performance = performance;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}

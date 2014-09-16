package com.rc.json.dto;

import java.io.Serializable;

public class TransactionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getNoOfTransactions() {
		return transactions;
	}
	public void setNoOfTransactions(int transactions) {
		this.transactions = transactions;
	}
	int hour;
	int transactions;

}

package com.rc.json.dto;

import java.io.Serializable;

public class ChartDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	String name;
	int count;
}

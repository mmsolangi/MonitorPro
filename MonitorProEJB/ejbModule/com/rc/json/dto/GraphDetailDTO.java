package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class GraphDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Double> getData() {
		return data;
	}
	public void setData(ArrayList<Double> data) {
		this.data = data;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private String name;
	private ArrayList<Double> data;
}

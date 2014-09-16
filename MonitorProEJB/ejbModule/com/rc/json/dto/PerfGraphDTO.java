package com.rc.json.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PerfGraphDTO implements Serializable{
	private static final long serialVersionUID = 1L;



	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public ArrayList<GraphDetailDTO> getSeries() {
		return series;
	}
	public void setSeries(ArrayList<GraphDetailDTO> series) {
		this.series = series;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	ArrayList<String>categories;
	ArrayList <GraphDetailDTO> series;
	
	
}

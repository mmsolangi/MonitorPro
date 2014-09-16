package com.rc.json.transformer;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.rc.json.dto.LogHeader;
import com.rc.json.dto.QueryDTO;
import com.rc.json.dto.StatusDTO;

public class MonitorProTransformer {

	public static String ConvertToJSONString(StatusDTO dto) {
		String jsonLogHeaderString = null;
		Gson gson = new Gson();
		jsonLogHeaderString = gson.toJson(dto);
		return jsonLogHeaderString;
	}
	
	public static QueryDTO BuildQueryObj(String query){
		
		Gson gson = new Gson();
		QueryDTO obj = gson.fromJson(query, QueryDTO.class);

		return obj;
	}
}
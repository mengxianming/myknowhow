package com.mogoroom.tasktracker;

import java.util.HashMap;
import java.util.Map;

public class ParamsBuilder {
	

	private Map<String, String> params = new HashMap<String, String>();

	public static ParamsBuilder newOne(){
		return new ParamsBuilder();
	}
	public ParamsBuilder put(String name, String value) {
		params.put(name, value);
		return this;
	}
	public  Map<String, String> build(){
		return params;
	}

}

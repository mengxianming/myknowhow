package com.mogoroom.service.base.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 可选择字段的查询关键信息
 * 
 * @author bricks.mong@2017年8月9日
 * @version   
 * @since JDK 1.7
 */
public class SelectableCriteria extends QCriteria{
	private List<String> selects = new ArrayList<String>();
	
	public SelectableCriteria select(String... columns){
		if(columns.length > 0){
			selects.addAll(Arrays.asList(columns));
		}
		return this;
	}
	
	public String getSelects() {
		if(selects.isEmpty()){
			return null;
		}
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < selects.size(); i++){
			if(i == 0){
				result.append(selects.get(i));
			}else{
				result.append(",").append(selects.get(i));
			}
		}
		return result.toString();
	}
	
}
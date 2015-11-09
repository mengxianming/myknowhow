/**
 * 
 */
package com.autonavi.tsp.workbackend.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author mengxianming
 *
 * 2015年6月18日
 */
public class JacksonUtil {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @param dto
	 * @return
	 */
	public static String toJsonString(Object bean, PropertyNamingStrategy pns) {
		if(bean != null){
			ObjectMapper om = new ObjectMapper();
			om.setPropertyNamingStrategy(pns);
			om.setDateFormat(dateFormat );
			om.setSerializationInclusion(Include.NON_NULL);  
			try {
				return om.writeValueAsString(bean);
			} catch (JsonProcessingException e) {
				throw new RuntimeException("error when serialize java object to string.", e);
			}
		}	
		
		return null;
	}

}

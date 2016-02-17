package my.study.exceldemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json与对象之间转换的工具类
 * @author mengxianming-2016年1月11日
 *
 */
public class JsonUtil{	
	private static ObjectMapper om = new ObjectMapper();
	
	
	static{		
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));		
	}
	
	/**
	 * 把对象序列化成json串。所有字段都输出。
	 * @param bean
	 * @return
	 * @author mengxianming-2016年1月12日
	 */
	public static String toJson(Object bean){
		return toJson(bean, null, null);
	}
	
	/**
	 * 把对象序列化成json串。所有字段都输出。
	 * @param bean
	 * @return
	 * @author mengxianming-2016年1月12日
	 */
	public static String toJson(Object bean, DateFormat df){
		return toJson(bean, df, null);
	}
	
	/**
	 * 把对象序列化成json串。所有字段都输出。
	 * @param bean
	 * @return
	 * @author mengxianming-2016年1月12日
	 */
	public static String toJson(Object bean, DateFormat df, Include include){
		DateFormat dfbak = om.getDateFormat();
		try {			
			if(include != null){
				om.setSerializationInclusion(include);
			}
			if(df != null){
				om.setDateFormat(df);
			}
			return om.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} finally{
			om.setDateFormat(dfbak);
			om.setSerializationInclusion(Include.USE_DEFAULTS);
		}
	}
	
	
	/**
	 * 把对象序列化成json串。null或者空值字段不输出。
	 * @param bean
	 * @return
	 * @author mengxianming-2016年1月12日
	 */
	public static String toJsonExcludeNull(Object bean){
		return toJson(bean, null, Include.NON_NULL);
	}
		
	public static <T> T fromJson(String json, Class<T> type){
		try {
			return om.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public static <T> List<T> fromJsonToList(String json, Class<T> elementType) {
		try {
			return om.readValue(json, om.getTypeFactory().constructCollectionType(ArrayList.class, elementType));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
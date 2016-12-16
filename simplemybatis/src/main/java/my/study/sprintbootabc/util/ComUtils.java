package my.study.sprintbootabc.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * 
 * @author 蒙先铭-2016年12月13日
 *
 */
public class ComUtils {
	@SuppressWarnings("unchecked")
	public static <T> T createDefaultEntity(Class<T> clazz) {
		try {
			Object obj = clazz.newInstance();
			PropertyDescriptor[] pros = PropertyUtils.getPropertyDescriptors(clazz);
			for(PropertyDescriptor pro : pros){
				if(pro.getWriteMethod() != null){
					//has reader and writer
					PropertyUtils.setProperty(obj, pro.getName(), getDefaltVal(pro.getPropertyType()));
				}
			}
			return (T)obj;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private static Object getDefaltVal(Class<?> propertyType) throws Exception {
		if(String.class.isAssignableFrom(propertyType)){
			return "";
		}
		if(Date.class.isAssignableFrom(propertyType)){
			return DateUtils.truncate(new Date(), Calendar.SECOND);
		}
		if(Number.class.isAssignableFrom(propertyType)){
			Constructor<?> c = propertyType.getConstructor(String.class);
			return c.newInstance("0");
		}
		if(Boolean.class.isAssignableFrom(propertyType)){
			return false;
		}
		
		return null;
	}
	

	/**
	 * @param split
	 * @param class1
	 */
	public static <T extends Number> List<T> toNumbers(String[] numbers, Class<T> type) {
		List<T> ret = new ArrayList<T>(numbers.length);
		for(String num : numbers){
			try {
				ret.add(type.getConstructor(String.class).newInstance(num));
			}
			catch (Exception e) {
				throw new RuntimeException(String.format("Can't convert from '%s' to %s type", num, type), e);				
			}
		}

		return ret;
		
	}


	
	public static Class<?>[] getGenericTypes(Class<?> subClass) {
		Type sType = subClass.getGenericSuperclass();
		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
		
		Class<?>[] genericTypes = new Class<?>[generics.length];
		for(int i =0; i < generics.length; i++){
			genericTypes[i] = (Class<?>) (generics[i]);
		}		
		return genericTypes;
	}

	public static String toJson(Object rec2) {
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			return om.writeValueAsString(rec2);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 判断对象是否为空。
	 * 空对象可以为：
	 * null、空字符串、长度为0数组、size()为0的集合类、size()为0的MAP。
	 * @param obj
	 * @return
	 * @author mengxianming-2016年2月29日
	 */
	public static boolean isEmpty(Object obj ){
		if(obj == null){
			return true;
		}		
		if(obj instanceof String){
			return "".equals( obj );
		}	
		if(obj.getClass().isArray()){
			return Array.getLength(obj) == 0;
		}	
		if(obj instanceof Collection){
			return ((Collection<?>)obj).size() == 0;
		}
		if(obj instanceof Map){
			return ((Map<?,?>)obj).size() == 0;
		}

		return false;
	}
	public static boolean isNotEmpty(Object obj ){
		return !isEmpty(obj);
	}
	
	/**
	 * 把一系列的kv对转换成Map
	 * @param keyvals
	 * @return
	 */
	public static Map<String, Object> keyValues2Map(Object... keyvals) {
		Map<String, Object> res = new LinkedHashMap<String, Object>();
		for(int i =0; i < keyvals.length; i +=2){
			String key = String.valueOf(keyvals[i]);
			Object val = i + 1 < keyvals.length ? keyvals[i + 1] : null;
			res.put(key, val);
			
		}
		return res;
	}

}

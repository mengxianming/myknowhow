package my.study.spstudy.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class TestUtil{
	/**
	 * @param map
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static String toJson(Object obj) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
		
	}

	/**
	 * @param bvo
	 * @param bvo2
	 * @param strings
	 * @throws Exception 
	 * @throws InvocationTargetException 
	 * @throws  
	 */
	public static void assertPropertiesEqual(Object bvo, Object bvo2, String[] propNames) throws Exception {
		for(String prop : propNames){
			Object v1 = PropertyUtils.getProperty(bvo, prop);
			Object v2 = PropertyUtils.getProperty(bvo2, prop);
			Assert.assertEquals(v1, v2);
		}
		
	}

	@SuppressWarnings("unchecked")
	public static <T> T createDefaultEntity(Class<T> clazz) {
		try {
			Object obj = clazz.newInstance();
			PropertyDescriptor[] pros = PropertyUtils.getPropertyDescriptors(clazz);
			for(PropertyDescriptor pro : pros){
				if(pro.getWriteMethod() != null ){
					//has reader and writer
					Object val =  getDefaltVal(pro.getPropertyType());
					PropertyUtils.setProperty(obj, pro.getName(), val);
				}
			}
			return (T) obj;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private static Object getDefaltVal(Class<?> propertyType) throws Exception {
		if(String.class.isAssignableFrom(propertyType)){
			return "";
		}
		if(Date.class.isAssignableFrom(propertyType)){
			return new Date();
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
	
	public static Class<?>[] getGenericTypes(Class<?> subClass) {
		Type sType = subClass.getGenericSuperclass();
		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();

		Class<?>[] genericTypes = new Class<?>[generics.length];
		for(int i =0; i < generics.length; i++){
			genericTypes[i] = (Class<?>) (generics[i]);
		}		
		return genericTypes;
	}
}
/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

import com.study.autoprodtool.form.EntityForm;
import com.study.autoprodtool.form.FieldMapping;
import com.study.autoprodtool.form.ListCriteria;


/**
 * Descriptions
 *
 * @version 2015-2-6
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class ComUtils {

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
				throw new ConversionException(String.format("Can't convert from '%s' to %s type", num, type), e);				
			}
		}

		return ret;
		
	}

	/**
	 * @param request
	 * @param listCriteria
	 */
	public static void populateJqGridPagerSorterInfo(HttpServletRequest request, Integer pageLimit,
			ListCriteria<?> listCriteria) {
		Pager pager = new Pager().setLimit(pageLimit);
		if(!CheckUtil.isNull(request.getParameter("page"))){
			pager.setPage(Integer.valueOf(request.getParameter("page")));
		}		
		
		listCriteria.setPager(pager);
		listCriteria.setSortField(request.getParameter("sidx"));
		listCriteria.setSortOrder(request.getParameter("sord"));
		
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
	
	public static <E> void iterate(Collection<E> collection, ForEach<E> forEach){
		if(collection != null){
			Iterator<E> it = collection.iterator();
			while(it.hasNext()){
				forEach.perform(it.next());
			}
		}
	}
	
	public static void iterate(Object bean, ForEach<PropertyValue> forEach){
		if(bean == null){
			return;
		}
		PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(bean);
		for(PropertyDescriptor prop : props){		
			if(prop.getReadMethod() == null){
				continue;
			}
			try {
				PropertyValue pv = new PropertyValue();
				pv.setName(prop.getName());
				pv.setValue(PropertyUtils.getProperty(bean, pv.getName()));
				pv.setPropertyDescriptor(prop);
				forEach.perform(pv);
			}
			catch (Exception e) {
				continue;
			}
			
		}
	}
	
	public static <F extends EntityForm<F, ?>> String getMappedEntityFieldName(Class<F> entityFormClass, String entityFormFieldName){		
		String ret = entityFormFieldName;		
		FieldMapping annotation = null;
		try {
			PropertyDescriptor desciptor = BeanUtils.getPropertyDescriptor(entityFormClass, entityFormFieldName);
			if(desciptor != null){
				Method setter = desciptor.getReadMethod();
				annotation = setter.getAnnotation(FieldMapping.class);			
			}
			//override the annotation from setter method if field is annotated.
			Field field = entityFormClass.getDeclaredField(entityFormFieldName);
			if(field != null && field.isAnnotationPresent(FieldMapping.class)){
				annotation = field.getAnnotation(FieldMapping.class);
			}
		}
		catch (Exception e) {
			throw new PropertyAccessException(e);
		}
		
		if(annotation != null && !CheckUtil.isNull(annotation.value())){
			ret = annotation.value();					
		}
		
		return ret;
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
}

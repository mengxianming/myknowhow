/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
}

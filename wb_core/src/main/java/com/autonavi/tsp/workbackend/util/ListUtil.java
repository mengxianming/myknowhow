/**
 * 
 */
package com.autonavi.tsp.workbackend.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.autonavi.tsp.common.util.CheckUtil;

/**
 * @author mengxianming
 *
 * 2015年5月26日
 */
public class ListUtil {
	public static interface ElementFilter<T>{
		boolean filter(T ele);
	}

	/**
	 * @param cityResList
	 * @param string
	 * @return
	 */
	public static <K, T> Map<K, T> listToMap(List<T> beanList, String keyProp) {
		Map<K, T> result = new HashMap<K, T>();
		if(!CheckUtil.isNull(beanList) && !CheckUtil.isNull(keyProp)){
			for(T bean : beanList){
				try {
					@SuppressWarnings("unchecked")
					K val = (K) PropertyUtils.getProperty(bean, keyProp);
					if(val != null){
						result.put(val, bean);
					}
					
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				
			}
		}
		return result;
	}
	
	/**
	 * @param cityResList
	 * @param string
	 * @return
	 */
	public static <K, T> Map<K, List<T>> groupListBy(List<T> beanList, String keyProp) {
		Map<K, List<T>> result = new HashMap<K, List<T>>();
		if(!CheckUtil.isNull(beanList) && !CheckUtil.isNull(keyProp)){
			for(T bean : beanList){
				try {
					@SuppressWarnings("unchecked")
					K val = (K) PropertyUtils.getProperty(bean, keyProp);
					if(val != null){
						List<T> list = result.get(val);
						if(CheckUtil.isNull(list)){
							list = new ArrayList<T>();
							result.put(val, list);
						}
						list.add(bean);
					}
					
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				
			}
		}
		return result;
	}

	/**
	 * 
	 * @param cityList
	 * @param prop
	 * @param descOrAsc
	 * @return
	 */
	public static <T> List<T> orderBy(List<T> list, final String prop, final String descOrAsc) {
		
		Comparator<? super T> comp = new Comparator<T>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public int compare(T o1, T o2) {
				try {					
					Object propVal1 = getProperty(o1, prop);
					Object propVal2 = getProperty(o2, prop);
					int result = 0;
					if(propVal1 == propVal2){
						result = 0;
					}else if(propVal1 == null && propVal2 != null){
						result = 1;
					}else if(propVal1 != null && propVal2 == null){
						result = -1;
					}else{						
						if(propVal1.getClass() != propVal2.getClass()){
							throw new RuntimeException("element's property value must have the same type.");
						}
						if(!Comparable.class.isAssignableFrom(propVal1.getClass())){
							throw new RuntimeException("element's property in the list not implement Comparable interface. The list cannot sort.");
						}
						result = ((Comparable)propVal1).compareTo(propVal2);
					}
					
					
					return "desc".equalsIgnoreCase(descOrAsc) ? -result : result;
				} catch (RuntimeException e) {
					throw e;
				} catch(Exception e){
					throw new RuntimeException(e.getMessage(), e);
				}		
				
			}
		};
		
		ArrayList<T> result = new ArrayList<T>(list);
		Collections.sort(result, comp);
		return result;
	}

	private static Object getProperty(Object bean, String prop) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//cast map[key] --> map(key) which quired by PropertyUtils
		prop = prop.replace("[", "(").replace("]", ")");
		
		return PropertyUtils.getProperty(bean, prop);
	}

	/**
	 * @param lists
	 * @return
	 */
	public static <T> List<T> filter(List<T> list, ElementFilter<T> condition) {
		List<T> result = new ArrayList<T>();
		if(list != null){
			for(T ele : list){
				if(condition.filter(ele)){
					result.add(ele);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @param lists
	 * @return
	 */
	public static <K, V> Map<K, V> filterMap(Map<K, V> maplist, ElementFilter<Map.Entry<K, V>> condition) {
		Map<K, V> result = new HashMap<K, V>();
		if(maplist != null){
			for(Map.Entry<K, V> entry : maplist.entrySet()){
				if(condition.filter(entry)){
					result.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		return result;
	}

	/**
	 * @param lists
	 * @return
	 */
	public static <T> T first(List<T> list, ElementFilter<T> condition) {
		if(list != null){
			for(T ele : list){
				if(condition.filter(ele)){
					return ele;
				}
			}
		}
		
		return null;
	}

	public static class ChainMap<K, V> extends HashMap<K,V>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public ChainMap<K, V> chainPut(K k, V v){
			put(k, v);
			return this;
		}
		
	}
	/**
	 * 
	 */
	public static <K, V> ChainMap<K, V> chainMap(Class<K> ktype, Class<V> vtype) {
		return new ChainMap<K, V>();	
	}

	/**
	 * @param matchedPublishes
	 * @param string
	 * @return
	 */
	public static <Element, PropType> List<PropType> select(List<Element> list, String property) {
		List<PropType> result = new ArrayList<PropType>();
		if(list != null && !CheckUtil.isNull(property)){			
			for(Element bean : list){
				try {
					@SuppressWarnings("unchecked")
					PropType val = (PropType) getProperty(bean, property);
					result.add(val);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				} 
			}
		}
		return result;
	}
	
}

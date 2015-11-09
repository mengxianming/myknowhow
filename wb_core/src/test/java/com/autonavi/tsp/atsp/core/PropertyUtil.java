/**
 * 
 */
package com.autonavi.tsp.atsp.core;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author mengxianming
 *
 * 2015年5月21日
 */
public class PropertyUtil {

	/**
	 * @param dto
	 * @param string
	 * @param string2
	 * @param string3
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] selectProps(Object bean, String... props) {
		Object[] ret = new Object[props.length];
		for(int i = 0; i < props.length; i++){
			String prop = props[i];
			Object val = null;
			if(bean instanceof Map){
				val = ((Map)bean).get(prop);				
			}else{
				try {
					val = PropertyUtils.getProperty(bean, prop);
				} catch (Exception e) {
					val = null;
				} 
			}
			
			ret[i] = val;
		}
		return ret;
	}

}

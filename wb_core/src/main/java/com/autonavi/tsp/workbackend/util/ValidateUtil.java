package com.autonavi.tsp.workbackend.util;

import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.workbackend.exception.CommonException;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.List;
import java.util.Map;

/**
 * @author mengxianming
 *
 * 2015年5月22日
 */
public class ValidateUtil {

	/**
	 * 
	 * @param bean
	 * @param checkProps
	 * @throws CommonException
	 */
	public static void checkEmpty(Object bean, String...  checkProps) throws CommonException {
		for(String prop : checkProps ){
			Object val;
			try {
				val = PropertyUtils.getProperty(bean, prop);
			} catch (Exception e) {
				throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION.getCode(), e.getMessage());
			}
			checkEmpty(prop, val);
		}
	}

	public static void checkEmpty(Map<String, Object> dynaObj, String...  checkProps) throws CommonException {
		for(String prop : checkProps ){
			Object val = dynaObj.get(prop);
			checkEmpty(prop, val);
		}
	}

	public static void checkEmpty(String prop, Object value) throws CommonException {
		if(CheckUtil.isNull(value)){
			throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
					String.format("参数%s必须输入。", prop));
		}
	}

	/**
	 *
	 * @param bean
	 * @param prop
	 * @param valueRange
	 * @throws CommonException
	 */
	public static void checkInRange(Object bean, String prop, List<?> valueRange) throws CommonException {
		Object val;
		try {
			val = PropertyUtils.getProperty(bean, prop);
		} catch (Exception e) {
			throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION.getCode(), e.getMessage());
		}
		checkInRange(val, valueRange, prop);
	}

	/**
	 *
	 * @param val
	 * @param valueRange
	 * @param prop
	 * @throws CommonException
	 */
	public static void checkInRange(Object val, List<?> valueRange, String prop) throws CommonException {		
		for(Object each : valueRange){
			if(each == val || each.equals(val)){
				//check ok
				return;
			}
		}
		
		throw new CommonException(ExceptionCodeEnum.PARAM_INVALID.getCode(),
				String.format("参数%s不在正确范围内。", prop));
	}

}

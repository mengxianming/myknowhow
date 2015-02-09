/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

import java.util.ArrayList;
import java.util.List;


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

}

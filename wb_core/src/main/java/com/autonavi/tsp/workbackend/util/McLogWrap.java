/**
 * 
 */
package com.autonavi.tsp.workbackend.util;

import com.autonavi.tsp.common.util.McLog;

/**
 * @author mengxianming
 *
 * 2015年6月10日
 */
public abstract class McLogWrap {

	/**
	 * 
	 * @param params
	 */
	public static void appLog(String... params) {
		try {
			McLog.appLog(params);
		} catch (Exception e) {
			throw new RuntimeException("调用Mclog类输出log时出错", e);
		}
		
	}
	
}


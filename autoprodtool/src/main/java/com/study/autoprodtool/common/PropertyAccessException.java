/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

/**
 * Descriptions
 *
 * @version 2015-2-10
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class PropertyAccessException extends RuntimeException {

	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PropertyAccessException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PropertyAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public PropertyAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PropertyAccessException(Throwable cause) {
		super(cause);
	}
	

}

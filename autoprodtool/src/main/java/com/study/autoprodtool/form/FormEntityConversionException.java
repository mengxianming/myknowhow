/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.form;

/**
 * Descriptions
 *
 * @version 2015-2-6
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class FormEntityConversionException extends RuntimeException {


	/**
	 * 
	 */
	public FormEntityConversionException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FormEntityConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FormEntityConversionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FormEntityConversionException(Throwable cause) {
		super(cause);
	}

	/** */
	private static final long serialVersionUID = 1L;

}

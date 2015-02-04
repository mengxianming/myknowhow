/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;


/**
 * Errorcode Defines
 *
 * @version 2014年5月14日
 * @author Wonday
 * @since JDK1.6
 *
 */
public interface CommonErrorCodes{
	
	// OK
	public static final ErrorCode S_OK = new ErrorCode("S00", "Success");
	// Other error
	public static final ErrorCode E_OTHER = new ErrorCode("E0000", "Other error");	
	// Parameter value error
	public static final ErrorCode E_PARAM_VALUE = new ErrorCode("E0001", "Parameter value is error (param={0})");
	// Parameter format error
	public static final ErrorCode E_PARAM_FORMAT = new ErrorCode("E0002", "Parameter format is error (param={0})");
	// Parameter empty error
	public static final ErrorCode E_PARAM_EMPTY = new ErrorCode("E0003", "Parameter is empty (param={0})");
	// Record not exist error
	public static final ErrorCode E_MULTI_RECORD = new ErrorCode("E0004", "Record should be unique (table={0}, key={1})");
	// Record not exist error
	public static final ErrorCode E_RECORD_NOT_EXIST = new ErrorCode("E0005", "Record not exist (table={0}, key={1})");

}

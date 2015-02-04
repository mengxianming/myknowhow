package com.study.autoprodtool.common;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * ErrorCode
 * 
 * @version 2014年5月14日
 * @author Wonday
 * @since JDK1.6
 * 
 */
public class ErrorCode {

	public final String code;
	private final String message;
	private static MessageSource messageSource = null;

	/**
	 * @param code
	 * @param message
	 */
	public ErrorCode(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	public static void setMessageSource(MessageSource messageSource) {
		ErrorCode.messageSource = messageSource;
	}
	
	/* 
	 * return the Localized formated message
	 * @param args object array
	 */

	public String getLocalizedMessage(Object...args) {
		
		if (args == null) args = new Object[0];
		// convert to string to avoid number format
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				args[i] = "null";
			}
			else {
				args[i] = args[i].toString();
			}
		}
		
		String retMsg;
		if (messageSource != null) {
			Locale locale = LocaleContextHolder.getLocale();
			retMsg = messageSource.getMessage(this.code, args, locale);
		} else {
			retMsg = getMessage(args);
		}
		return retMsg;
	}

	/* 
	 * return the formated message
	 * @param args object array
	 */
	public String getMessage(Object...args) {
		
		if (args == null) args = new Object[0];
		// convert to string to avoid number format
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				args[i] = "null";
			}
			else {
				args[i] = args[i].toString();
			}
		}
		
		String retMsg = "[" + this.code + "] " + MessageFormat.format(this.message, args);
		return retMsg;
	}


}

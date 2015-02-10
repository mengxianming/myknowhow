/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * Descriptions
 *
 * @version 2014-9-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class MyNamingStrategy extends DefaultNamingStrategy{
	/** */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.hibernate.cfg.NamingStrategy#classToTableName(java.lang.String)
	 */
	public String classToTableName(String className) {
		String tableName = className.substring(className.lastIndexOf(".") + 1);
		tableName = convertCamel(tableName, "tb_");
		return tableName;
	}

	/**
	 * @param className
	 * @param tn
	 */
	private String convertCamel(String camelString, String prefix) {
		StringBuilder result =  new StringBuilder(prefix == null ? "" : prefix);
		//ignore the first char
		result.append(camelString.charAt(0));
		
		int prvpos = 1;
		for(int i = 1; i < camelString.length(); i++){
			if(Character.isUpperCase(camelString.charAt(i))){
				result.append(camelString.substring(prvpos, i)).append("_");
				prvpos = i;
			}
		}
		result.append(camelString.substring(prvpos));
		return result.toString().toLowerCase();
	}

	

	/* (non-Javadoc)
	 * @see org.hibernate.cfg.NamingStrategy#propertyToColumnName(java.lang.String)
	 */
	public String propertyToColumnName(String propertyName) {		
		return convertCamel(propertyName, "");
	}
	
	/* (non-Javadoc)
	 * @see org.hibernate.cfg.DefaultNamingStrategy#foreignKeyColumnName(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName,
			String referencedColumnName) {		
		return convertCamel(propertyName + "_id", "");
	}
}

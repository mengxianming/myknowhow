/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.entity;

import javax.persistence.Entity;

/**
 * Descriptions
 *
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Entity
public class Division extends DBEntity {
	private String name;
	private String parent;
	private String pparent;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}
	/**
	 * @return the pparent
	 */
	public String getPparent() {
		return pparent;
	}
	/**
	 * @param pparent the pparent to set
	 */
	public void setPparent(String pparent) {
		this.pparent = pparent;
	}
	
	
	
	

}

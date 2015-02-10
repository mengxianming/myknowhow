/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_division_id")
	@SequenceGenerator(name="seq_division_id", sequenceName="seq_division_id", allocationSize=1)
	private Long id;
	private String name;
	private String parent;
	private String pparent;
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
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

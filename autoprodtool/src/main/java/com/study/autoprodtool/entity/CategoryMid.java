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
 * @version 2015-2-13
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Entity
public class CategoryMid extends DBEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_category_mid_id")
	@SequenceGenerator(name="seq_category_mid_id", sequenceName="seq_category_mid_id", allocationSize=1)	
	private Long id;
	private String name;

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.entity.DBEntity#getId()
	 */
	@Override
	public Long getId() {		
		return id;
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.entity.DBEntity#setId(java.lang.Long)
	 */
	@Override
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

}

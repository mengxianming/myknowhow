/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.autoprodtool.dao.DivisionDAO;
import com.study.autoprodtool.entity.Division;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Repository
public class DivisionDAOImpl extends CrudDAOImpl<Division> implements DivisionDAO{

	/**
	 * @param entityClazz
	 */
	public DivisionDAOImpl() {
		super(Division.class);		
	}

	

}

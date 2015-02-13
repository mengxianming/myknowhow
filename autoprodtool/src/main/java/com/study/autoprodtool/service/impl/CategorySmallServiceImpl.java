/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.CategorySmallDAO;
import com.study.autoprodtool.entity.CategorySmall;
import com.study.autoprodtool.service.CategorySmallService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class CategorySmallServiceImpl extends CrudServiceImpl<CategorySmall> implements CategorySmallService{
	@Autowired
	private CategorySmallDAO categorySmallDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<CategorySmall> getCrudDAO() {		
		return this.categorySmallDAO;
	}
	
}

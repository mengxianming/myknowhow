/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.CategoryMidDAO;
import com.study.autoprodtool.entity.CategoryMid;
import com.study.autoprodtool.service.CategoryMidService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class CategoryMidServiceImpl extends CrudServiceImpl<CategoryMid> implements CategoryMidService{
	@Autowired
	private CategoryMidDAO categoryMidDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<CategoryMid> getCrudDAO() {		
		return this.categoryMidDAO;
	}
	
}

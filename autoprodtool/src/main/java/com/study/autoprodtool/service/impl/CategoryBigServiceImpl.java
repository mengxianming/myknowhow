/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.CategoryBigDAO;
import com.study.autoprodtool.entity.CategoryBig;
import com.study.autoprodtool.service.CategoryBigService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class CategoryBigServiceImpl extends CrudServiceImpl<CategoryBig> implements CategoryBigService{
	@Autowired
	private CategoryBigDAO categoryBigDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<CategoryBig> getCrudDAO() {		
		return this.categoryBigDAO;
	}
	
}

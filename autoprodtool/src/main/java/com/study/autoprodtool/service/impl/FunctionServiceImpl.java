/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.FunctionDAO;
import com.study.autoprodtool.entity.Function;
import com.study.autoprodtool.service.FunctionService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class FunctionServiceImpl extends CrudServiceImpl<Function> implements FunctionService{
	@Autowired
	private FunctionDAO functionDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<Function> getCrudDAO() {		
		return this.functionDAO;
	}
	
}

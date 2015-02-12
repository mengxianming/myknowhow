/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CompanyDAO;
import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.entity.Company;
import com.study.autoprodtool.service.CompanyService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class CompanyServiceImpl extends CrudServiceImpl<Company> implements CompanyService{
	@Autowired
	private CompanyDAO companyDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<Company> getCrudDAO() {		
		return this.companyDAO;
	}			
	
}

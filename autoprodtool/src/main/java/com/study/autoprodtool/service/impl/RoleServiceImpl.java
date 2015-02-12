/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.RoleDAO;
import com.study.autoprodtool.entity.Role;
import com.study.autoprodtool.service.RoleService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService{
	@Autowired
	private RoleDAO roleDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<Role> getCrudDAO() {		
		return this.roleDAO;
	}
	
}

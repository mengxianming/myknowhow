/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.UserDAO;
import com.study.autoprodtool.entity.User;
import com.study.autoprodtool.service.UserService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService{

	private UserDAO routeDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<User> getCrudDAO() {		
		return this.routeDAO;
	}
	/**
	 * @param routeDAO the routeDAO to set
	 */
	@Autowired
	public void setUserDAO(UserDAO routeDAO) {
		this.routeDAO = routeDAO;
	}
		
	public List<User> selectAll(Integer start, Integer limit) throws Exception {		
		return routeDAO.selectAll(start, limit);
	}

}

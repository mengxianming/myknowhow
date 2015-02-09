/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.autoprodtool.dao.UserDAO;
import com.study.autoprodtool.entity.User;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Repository
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO{

	/**
	 * @param entityClazz
	 */
	public UserDAOImpl() {
		super(User.class);		
	}

	

}

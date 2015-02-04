/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.dao.impl;

import org.springframework.stereotype.Repository;

import com.study.autoprodtool.dao.RouteDAO;
import com.study.autoprodtool.entity.Route;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Repository
public class RouteDAOImpl extends CrudDAOImpl<Route> implements RouteDAO{

	/**
	 * @param entityClazz
	 */
	public RouteDAOImpl() {
		super(Route.class);		
	}

}

/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.RouteDAO;
import com.study.autoprodtool.entity.Route;
import com.study.autoprodtool.service.RouteService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class RouteServiceImpl extends CrudServiceImpl<Route> implements RouteService{

	private RouteDAO routeDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<Route> getCrudDAO() {		
		return this.routeDAO;
	}
	/**
	 * @param routeDAO the routeDAO to set
	 */
	@Autowired
	public void setRouteDAO(RouteDAO routeDAO) {
		this.routeDAO = routeDAO;
	}
		
	public List<Route> selectAll(Integer start, Integer limit) throws Exception {		
		return routeDAO.selectAll(start, limit);
	}

}

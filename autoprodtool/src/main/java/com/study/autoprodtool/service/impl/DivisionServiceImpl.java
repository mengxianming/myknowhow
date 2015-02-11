/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.DivisionDAO;
import com.study.autoprodtool.entity.Division;
import com.study.autoprodtool.service.DivisionService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Service
public class DivisionServiceImpl extends CrudServiceImpl<Division> implements DivisionService{

	private DivisionDAO divisionDAO;

	/* (non-Javadoc)
	 * @see my.study.dynaweb.service.impl.CrudServiceImpl#getCrudDAO()
	 */
	@Override
	public CrudDAO<Division> getCrudDAO() {		
		return this.divisionDAO;
	}
	/**
	 * @param routeDAO the routeDAO to set
	 */
	@Autowired
	public void setDivisionDAO(DivisionDAO divisionDAO) {
		this.divisionDAO = divisionDAO;
	}
		
	public List<Division> selectAll(Integer start, Integer limit) throws Exception {		
		return divisionDAO.selectAll(start, limit);
	}	
}

/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import java.io.Serializable;
import java.util.List;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.RestrictionProvider;
import com.study.autoprodtool.service.CrudService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public abstract class CrudServiceImpl<T> implements CrudService<T>{	

	public abstract CrudDAO<T> getCrudDAO();
	
	/**
	 * @param key
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectOne(java.io.Serializable)
	 */
	public T selectOne(Serializable key) throws Exception {
		return getCrudDAO().selectOne(key);
	}

	/**
	 * @param restrictions
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectList(my.study.dynaweb.dao.RestrictionProvider)
	 */
	public List<T> selectList(RestrictionProvider restrictions) throws Exception {
		return getCrudDAO().selectList(restrictions);
	}

	/**
	 * @param restrictions
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectIdList(my.study.dynaweb.dao.RestrictionProvider)
	 */
	public List<Long> selectIdList(RestrictionProvider restrictions) throws Exception {
		return getCrudDAO().selectIdList(restrictions);
	}

	/**
	 * @param restrictions
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectListSize(my.study.dynaweb.dao.RestrictionProvider)
	 */
	public int selectListSize(RestrictionProvider restrictions) throws Exception {
		return getCrudDAO().selectListSize(restrictions);
	}

	/**
	 * @param entity
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#insert(java.lang.Object)
	 */
	public void insert(T entity) throws Exception {
		getCrudDAO().insert(entity);
	}

	/**
	 * @param entity
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#update(java.lang.Object)
	 */
	public void update(T entity) throws Exception {
		getCrudDAO().update(entity);
	}

	/**
	 * @param entity
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#delete(java.lang.Object)
	 */
	public void delete(T entity) throws Exception {
		getCrudDAO().delete(entity);
	}

	

}

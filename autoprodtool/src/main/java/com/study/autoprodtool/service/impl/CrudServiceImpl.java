/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.RestrictionProvider;
import com.study.autoprodtool.entity.Entity;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CrudService;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public abstract class CrudServiceImpl<T extends Entity> implements CrudService<T>{	

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
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectList(my.study.dynaweb.dao.ListCriteria<?>)
	 */
	public List<T> selectList(ListCriteria<?> restrictions) throws Exception {
		RestrictionProvider provider = restrictions.toRestrictionProvider();
		if(needPager(restrictions)){
			restrictions.getPager().setTotal(getCrudDAO().selectListSize(provider));
		}
		
		return getCrudDAO().selectList(provider);
	}

	/**
	 * @param restrictions
	 * @return
	 */
	private boolean needPager(ListCriteria<?> restrictions) {
		return restrictions.getPager() != null
				&& restrictions.getPager().getPage() != null
				&& restrictions.getPager().getLimit() != null;
	}

	/**
	 * @param restrictions
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectIdList(my.study.dynaweb.dao.ListCriteria<?>)
	 */
	public List<Long> selectIdList(ListCriteria<?> restrictions) throws Exception {
		RestrictionProvider provider = restrictions.toRestrictionProvider();
		if(needPager(restrictions)){
			restrictions.getPager().setTotal(getCrudDAO().selectListSize(provider));
		}
		
		return getCrudDAO().selectIdList(provider);
	}

	/**
	 * @param restrictions
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectListSize(my.study.dynaweb.dao.ListCriteria<?>)
	 */
	public int selectListSize(ListCriteria<?> restrictions) throws Exception {
		return getCrudDAO().selectListSize(restrictions.toRestrictionProvider());
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

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.service.CrudService#selectListByIds(java.lang.Long[])
	 */
	@Override
	public Map<Long, T> selectListByIds(Long[] idList) throws Exception {
		HashMap<Long, T> ret = new HashMap<Long, T>();
		if(CheckUtil.isNull(idList)){
			return ret;
		}
		List<T> list = getCrudDAO().selectListByField("id", idList);
		for(T ent : list){
			ret.put(ent.getId(), ent);
		}
		
		return ret;
	}

	

}

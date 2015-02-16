/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.RestrictionProvider;
import com.study.autoprodtool.entity.DBEntity;
import com.study.autoprodtool.form.FilterListCriteria;
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
public abstract class CrudServiceImpl<T extends DBEntity> implements CrudService<T>{	

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
	 * @param listCriteria
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectList(my.study.dynaweb.dao.ListCriteria<?>)
	 */
	public List<T> selectList(ListCriteria<?> listCriteria) throws Exception {
		RestrictionProvider provider = listCriteria.toRestrictionProvider();
		if(needPager(listCriteria)){
			listCriteria.getPager().setTotal(getCrudDAO().selectListSize(provider));
		}
		
		return getCrudDAO().selectList(provider);
	}

	/**
	 * @param listCriteria
	 * @return
	 */
	private boolean needPager(ListCriteria<?> listCriteria) {
		return listCriteria.getPager() != null
				&& listCriteria.getPager().getPage() != null
				&& listCriteria.getPager().getLimit() != null;
	}

	/**
	 * @param listCriteria
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectIdList(my.study.dynaweb.dao.ListCriteria<?>)
	 */
	public List<Long> selectIdList(ListCriteria<?> listCriteria) throws Exception {
		RestrictionProvider provider = listCriteria.toRestrictionProvider();
		if(needPager(listCriteria)){
			listCriteria.getPager().setTotal(getCrudDAO().selectListSize(provider));
		}
		
		return getCrudDAO().selectIdList(provider);
	}

	/**
	 * @param listCriteria
	 * @return
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#selectListSize(my.study.dynaweb.dao.ListCriteria<?>)
	 */
	public int selectListSize(ListCriteria<?> listCriteria) throws Exception {
		return getCrudDAO().selectListSize(listCriteria.toRestrictionProvider());
	}

	/**
	 * @param entity
	 * @throws Exception
	 * @see my.study.dynaweb.dao.getCrudDAO()#insert(java.lang.Object)
	 */
	public void insert(T entity) throws Exception {
		entity.removeTransientsBeforeSave();
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

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.service.CrudService#selectAll(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<T> selectAll(Integer start, Integer limit) throws Exception {		
		return getCrudDAO().selectAll(start, limit);
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.service.CrudService#selectFilterOptions(com.study.autoprodtool.form.FilterListForm)
	 */
	@Override
	public Map<String, List<Object>> selectFilterOptions(FilterListCriteria<?> fiterListCriteria, boolean distinct) throws Exception {
		HashMap<String, List<Object>> ret = new HashMap<String, List<Object>>();
		List<String> names = fiterListCriteria.getFilterNames();		
		//get every field values
		for(String fn : names){			
			String mappedName = ComUtils.getMappedEntityFieldName(fiterListCriteria.getEntityFormClass(), fn);
			List<Object> fieldVals = selectFiledList(mappedName, fiterListCriteria);
			if(distinct){
				//distinct and sort the list
				fieldVals = new ArrayList<Object>(new TreeSet<Object>(fieldVals));
			}
			ret.put(fn, fieldVals);				
		}	
			
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.service.CrudService#selectFiledList(java.lang.String, com.study.autoprodtool.form.ListCriteria)
	 */
	@Override
	public <V> List<V> selectFiledList(String field, ListCriteria<?> listCriteria) throws Exception {		
		return getCrudDAO().selectFiledList(field, listCriteria.toRestrictionProvider());
	}
		
}

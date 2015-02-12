/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.study.autoprodtool.entity.DBEntity;
import com.study.autoprodtool.form.ListCriteria;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public interface CrudService<T extends DBEntity> {
	T selectOne(Serializable key) throws Exception;;
	List<T> selectList(ListCriteria<?> restrictions) throws Exception;
	List<Long> selectIdList(ListCriteria<?> restrictions) throws Exception;
	int selectListSize(ListCriteria<?> restrictions) throws Exception;
	void insert(T entity) throws Exception;
	void update(T entity) throws Exception;
	void delete(T entity) throws Exception;
	Map<Long, T> selectListByIds(Long[] idList) throws Exception;
	public List<T> selectAll(Integer start, Integer limit) throws Exception;
}

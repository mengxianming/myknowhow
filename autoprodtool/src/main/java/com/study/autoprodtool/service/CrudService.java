/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service;

import java.io.Serializable;
import java.util.List;

import com.study.autoprodtool.dao.RestrictionProvider;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public interface CrudService<T> {
	T selectOne(Serializable key) throws Exception;;
	List<T> selectList(RestrictionProvider restrictions) throws Exception;
	List<Long> selectIdList(RestrictionProvider restrictions) throws Exception;
	int selectListSize(RestrictionProvider restrictions) throws Exception;
	void insert(T entity) throws Exception;
	void update(T entity) throws Exception;
	void delete(T entity) throws Exception;
}

/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.service;

import java.util.List;

import com.study.autoprodtool.entity.Division;

/**
 * Descriptions
 *
 * @version 2014-11-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public interface DivisionService extends CrudService<Division>{
	List<Division> selectAll(Integer start, Integer limit) throws Exception;

}

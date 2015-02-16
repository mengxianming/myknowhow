/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Descriptions
 *
 * @version 2014-9-4
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public interface CrudDAO<T> {	
	T selectOne(Serializable key) throws Exception;;
	List<T> selectList(RestrictionProvider restrictions) throws Exception;
	List<Long> selectIdList(RestrictionProvider restrictions) throws Exception;
	int selectListSize(RestrictionProvider restrictions) throws Exception;
	void insert(T entity) throws Exception;
	void update(T entity) throws Exception;
	void delete(T entity) throws Exception;
	<V> List<V> selectFiledList(String field, RestrictionProvider restrictions) throws Exception;
	
	/**
	 * エンティティのフィールドの値範囲により、エンティティを検索する
	 * @param field 指定エンティティのフィールド名
	 * @param values 検索条件としての指定フィールドの値リスト。
	 * @return 検索条件に満たすすべてのエンティティ
	 */
	List<T> selectListByField(String field, Object[] values) throws Exception;
	/**
	 * エンティティのフィールドの値範囲により、エンティティを検索する
	 * @param field 指定エンティティのフィールド名
	 * @param values 検索条件としての指定フィールドの値リスト。
	 * @return 検索条件に満たすすべてのエンティティ
	 */
	List<T> selectListByField(String field, Object[] values, Integer start, Integer limit) throws Exception;
	
	/**
	 * サンプルにより、エンティティを検索する
	 * サンプルの全てのフィールドはＡＮＤ関係で結合する
	 * @param field
	 * @param values
	 * @return
	 */
	List<T> selectListByExample(T example) throws Exception;
	/**
	 * サンプルにより、エンティティを検索する
	 * サンプルの全てのフィールドはＡＮＤ関係で結合する
	 * @param field
	 * @param values
	 * @return
	 */
	List<T> selectListByExample(T example, Integer start, Integer limit) throws Exception;
	
	/**
	 * キーワードにより、エンティティを検索する
	 * @param keyword
	 * @param entityClass
	 * @param fields
	 * @return
	 */
	List<T> selectListByKeyword(String keyword, String[] fields) throws Exception;
	
	
	/**
	 * キーワードにより、エンティティを検索する
	 * @param keyword
	 * @param entityClass
	 * @param fields
	 * @return
	 */
	List<T> selectListByKeyword(String keyword, String[] fields, Integer start, Integer limit) throws Exception;
	/**
	 * 全レコードの中、指定ページのデータを取得。
	 * ページのstartまたはlimit引数を指定しない場合、全レコードを取得
	 * @param start
	 * @param limit
	 * @return
	 */
	List<T> selectAll(Integer start, Integer limit) throws Exception;

}

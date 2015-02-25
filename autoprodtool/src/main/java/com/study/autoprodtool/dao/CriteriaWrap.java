/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;

/**
 * Descriptions
 *
 * @version 2015-2-25
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class CriteriaWrap implements Criteria {
	private Criteria criteria;
	private Map<String, String> aliasMap = new HashMap<String, String>();

	/**
	 * 
	 */
	public CriteriaWrap(Criteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return
	 * @see org.hibernate.Criteria#getAlias()
	 */
	public String getAlias() {
		return criteria.getAlias();
	}

	/**
	 * @param projection
	 * @return
	 * @see org.hibernate.Criteria#setProjection(org.hibernate.criterion.Projection)
	 */
	public Criteria setProjection(Projection projection) {
		return criteria.setProjection(projection);
	}

	/**
	 * @param criterion
	 * @return
	 * @see org.hibernate.Criteria#add(org.hibernate.criterion.Criterion)
	 */
	public Criteria add(Criterion criterion) {
		return criteria.add(criterion);
	}

	/**
	 * @param order
	 * @return
	 * @see org.hibernate.Criteria#addOrder(org.hibernate.criterion.Order)
	 */
	public Criteria addOrder(Order order) {
		return criteria.addOrder(order);
	}

	/**
	 * @param associationPath
	 * @param mode
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#setFetchMode(java.lang.String, org.hibernate.FetchMode)
	 */
	public Criteria setFetchMode(String associationPath, FetchMode mode) throws HibernateException {
		return criteria.setFetchMode(associationPath, mode);
	}

	/**
	 * @param lockMode
	 * @return
	 * @see org.hibernate.Criteria#setLockMode(org.hibernate.LockMode)
	 */
	public Criteria setLockMode(LockMode lockMode) {
		return criteria.setLockMode(lockMode);
	}

	/**
	 * @param alias
	 * @param lockMode
	 * @return
	 * @see org.hibernate.Criteria#setLockMode(java.lang.String, org.hibernate.LockMode)
	 */
	public Criteria setLockMode(String alias, LockMode lockMode) {
		return criteria.setLockMode(alias, lockMode);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createAlias(java.lang.String, java.lang.String)
	 */
	public Criteria createAlias(String associationPath, String alias) throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createAlias(associationPath, alias);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @return
	 */
	private boolean aliasExists(String associationPath, String alias) {		
		if(alias != null && alias.equals(getAliasMap().get(associationPath))){
			return true;
		}
		if(alias != null && associationPath != null){
			getAliasMap().put(associationPath, alias);
		}
		return false;
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createAlias(java.lang.String, java.lang.String, org.hibernate.sql.JoinType)
	 */
	public Criteria createAlias(String associationPath, String alias, JoinType joinType) throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createAlias(associationPath, alias, joinType);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @return
	 * @throws HibernateException
	 * @deprecated
	 * @see org.hibernate.Criteria#createAlias(java.lang.String, java.lang.String, int)
	 */
	public Criteria createAlias(String associationPath, String alias, int joinType) throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createAlias(associationPath, alias, joinType);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @param withClause
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createAlias(java.lang.String, java.lang.String, org.hibernate.sql.JoinType,
	 *      org.hibernate.criterion.Criterion)
	 */
	public Criteria createAlias(String associationPath, String alias, JoinType joinType, Criterion withClause)
			throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createAlias(associationPath, alias, joinType, withClause);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @param withClause
	 * @return
	 * @throws HibernateException
	 * @deprecated
	 * @see org.hibernate.Criteria#createAlias(java.lang.String, java.lang.String, int,
	 *      org.hibernate.criterion.Criterion)
	 */
	public Criteria createAlias(String associationPath, String alias, int joinType, Criterion withClause)
			throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createAlias(associationPath, alias, joinType, withClause);
	}

	/**
	 * @param associationPath
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String)
	 */
	public Criteria createCriteria(String associationPath) throws HibernateException {
		return criteria.createCriteria(associationPath);
	}

	/**
	 * @param associationPath
	 * @param joinType
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, org.hibernate.sql.JoinType)
	 */
	public Criteria createCriteria(String associationPath, JoinType joinType) throws HibernateException {
		return criteria.createCriteria(associationPath, joinType);
	}

	/**
	 * @param associationPath
	 * @param joinType
	 * @return
	 * @throws HibernateException
	 * @deprecated
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, int)
	 */
	public Criteria createCriteria(String associationPath, int joinType) throws HibernateException {
		return criteria.createCriteria(associationPath, joinType);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, java.lang.String)
	 */
	public Criteria createCriteria(String associationPath, String alias) throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createCriteria(associationPath, alias);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, java.lang.String, org.hibernate.sql.JoinType)
	 */
	public Criteria createCriteria(String associationPath, String alias, JoinType joinType) throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createCriteria(associationPath, alias, joinType);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @return
	 * @throws HibernateException
	 * @deprecated
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, java.lang.String, int)
	 */
	public Criteria createCriteria(String associationPath, String alias, int joinType) throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createCriteria(associationPath, alias, joinType);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @param withClause
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, java.lang.String, org.hibernate.sql.JoinType,
	 *      org.hibernate.criterion.Criterion)
	 */
	public Criteria createCriteria(String associationPath, String alias, JoinType joinType, Criterion withClause)
			throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createCriteria(associationPath, alias, joinType, withClause);
	}

	/**
	 * @param associationPath
	 * @param alias
	 * @param joinType
	 * @param withClause
	 * @return
	 * @throws HibernateException
	 * @deprecated
	 * @see org.hibernate.Criteria#createCriteria(java.lang.String, java.lang.String, int,
	 *      org.hibernate.criterion.Criterion)
	 */
	public Criteria createCriteria(String associationPath, String alias, int joinType, Criterion withClause)
			throws HibernateException {
		if(aliasExists(associationPath, alias)){
			return this;
		}
		return criteria.createCriteria(associationPath, alias, joinType, withClause);
	}

	/**
	 * @param resultTransformer
	 * @return
	 * @see org.hibernate.Criteria#setResultTransformer(org.hibernate.transform.ResultTransformer)
	 */
	public Criteria setResultTransformer(ResultTransformer resultTransformer) {
		return criteria.setResultTransformer(resultTransformer);
	}

	/**
	 * @param maxResults
	 * @return
	 * @see org.hibernate.Criteria#setMaxResults(int)
	 */
	public Criteria setMaxResults(int maxResults) {
		return criteria.setMaxResults(maxResults);
	}

	/**
	 * @param firstResult
	 * @return
	 * @see org.hibernate.Criteria#setFirstResult(int)
	 */
	public Criteria setFirstResult(int firstResult) {
		return criteria.setFirstResult(firstResult);
	}

	/**
	 * @return
	 * @see org.hibernate.Criteria#isReadOnlyInitialized()
	 */
	public boolean isReadOnlyInitialized() {
		return criteria.isReadOnlyInitialized();
	}

	/**
	 * @return
	 * @see org.hibernate.Criteria#isReadOnly()
	 */
	public boolean isReadOnly() {
		return criteria.isReadOnly();
	}

	/**
	 * @param readOnly
	 * @return
	 * @see org.hibernate.Criteria#setReadOnly(boolean)
	 */
	public Criteria setReadOnly(boolean readOnly) {
		return criteria.setReadOnly(readOnly);
	}

	/**
	 * @param fetchSize
	 * @return
	 * @see org.hibernate.Criteria#setFetchSize(int)
	 */
	public Criteria setFetchSize(int fetchSize) {
		return criteria.setFetchSize(fetchSize);
	}

	/**
	 * @param timeout
	 * @return
	 * @see org.hibernate.Criteria#setTimeout(int)
	 */
	public Criteria setTimeout(int timeout) {
		return criteria.setTimeout(timeout);
	}

	/**
	 * @param cacheable
	 * @return
	 * @see org.hibernate.Criteria#setCacheable(boolean)
	 */
	public Criteria setCacheable(boolean cacheable) {
		return criteria.setCacheable(cacheable);
	}

	/**
	 * @param cacheRegion
	 * @return
	 * @see org.hibernate.Criteria#setCacheRegion(java.lang.String)
	 */
	public Criteria setCacheRegion(String cacheRegion) {
		return criteria.setCacheRegion(cacheRegion);
	}

	/**
	 * @param comment
	 * @return
	 * @see org.hibernate.Criteria#setComment(java.lang.String)
	 */
	public Criteria setComment(String comment) {
		return criteria.setComment(comment);
	}

	/**
	 * @param flushMode
	 * @return
	 * @see org.hibernate.Criteria#setFlushMode(org.hibernate.FlushMode)
	 */
	public Criteria setFlushMode(FlushMode flushMode) {
		return criteria.setFlushMode(flushMode);
	}

	/**
	 * @param cacheMode
	 * @return
	 * @see org.hibernate.Criteria#setCacheMode(org.hibernate.CacheMode)
	 */
	public Criteria setCacheMode(CacheMode cacheMode) {
		return criteria.setCacheMode(cacheMode);
	}

	/**
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#list()
	 */
	@SuppressWarnings("rawtypes")
	public List list() throws HibernateException {
		return criteria.list();
	}

	/**
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#scroll()
	 */
	public ScrollableResults scroll() throws HibernateException {
		return criteria.scroll();
	}

	/**
	 * @param scrollMode
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#scroll(org.hibernate.ScrollMode)
	 */
	public ScrollableResults scroll(ScrollMode scrollMode) throws HibernateException {
		return criteria.scroll(scrollMode);
	}

	/**
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.Criteria#uniqueResult()
	 */
	public Object uniqueResult() throws HibernateException {
		return criteria.uniqueResult();
	}

	public Map<String, String> getAliasMap() {
		return this.aliasMap ;

	}

}

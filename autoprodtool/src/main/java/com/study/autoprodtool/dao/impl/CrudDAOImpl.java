package com.study.autoprodtool.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.autoprodtool.dao.CrudDAO;
import com.study.autoprodtool.dao.RestrictionProvider;

/**
 * Descriptions
 *
 * @version 2014-9-4
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public abstract class CrudDAOImpl<T> implements CrudDAO<T>{
	private static Log log = LogFactory.getLog(CrudDAOImpl.class);
	
	private Class<T> entityClazz;
	private SessionFactory sessionFactory;
	
	public CrudDAOImpl(Class<T> entityClazz){
		this.entityClazz = entityClazz;
	}		
	
	

	/* (non-Javadoc)
	 * @see my.study.hibernate.dao.CrudDAO#selectOne(Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T selectOne(final Serializable key) {
		Criteria criteria = getSession().createCriteria(entityClazz);
		criteria.add(Restrictions.eq("id", key));
		return (T)criteria.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see my.study.hibernate.dao.CrudDAO#selectList(RestrictionProvider)
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectList(final RestrictionProvider restrictions) {
		Criteria criteria = getSession().createCriteria(entityClazz);
		restrictions.addRestriction(criteria);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see my.study.hibernate.dao.CrudDAO#insert(java.lang.Object)
	 */
	public void insert(T entity) throws Exception {
		getSession().persist(entity);	
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see my.study.hibernate.dao.CrudDAO#update(java.lang.Object)
	 */
	public void update(T entity) throws Exception {
		getSession().merge(entity);
		getSession().flush();
	}

	/* (non-Javadoc)
	 * @see my.study.hibernate.dao.CrudDAO#delete(java.lang.Object)
	 */
	public void delete(T entity) throws Exception {
		getSession().delete(entity);
		getSession().flush();
	}


	/* (non-Javadoc)
	 * @see my.study.hibernate.dao.CrudDAO#getEntityClazz()
	 */
	public Class<T> getEntityClazz() {		
		return entityClazz;
	}


	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;		
	}
	
	
	private Session getSession() {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		}
		catch (HibernateException e) {			
			log.warn("Cant get session from the current thread...Create a new Session");
			session = sessionFactory.openSession();			
		}
		return session;
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListSize(net.suntec.dynabiz.service.masterdataservice.dao.RestrictionProvider)
	 */
	public int selectListSize(RestrictionProvider restrictions) throws Exception {
		Criteria criteria = getSession().createCriteria(entityClazz);
		restrictions.addRestriction(criteria);
		criteria.setProjection(Projections.rowCount());		
		Object ret = criteria.uniqueResult();
		return ret == null ? 0 : Integer.valueOf(ret.toString());
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectAll(java.lang.Integer, java.lang.Integer)
	 */	
	public List<T> selectAll(final Integer offset, final Integer limit) {	
		return selectList(new RestrictionProvider(){

			@Override
			public void addRestriction(Criteria criteria) {
				setPageLimit(criteria, offset, limit);				
			}
			
		});
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListByField(java.lang.String, java.lang.Object[])
	 */	
	public List<T> selectListByField(final String field, final Object[] values) throws Exception {		
		return selectListByField(field, values, null, null);
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListByExample(net.suntec.dynabiz.service.masterdataservice.entity.Entity)
	 */	
	public List<T> selectListByExample(final T example) throws Exception {
		return selectListByExample(example, null, null);
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListByKeyword(java.lang.String, java.lang.String[])
	 */	
	public List<T> selectListByKeyword(final String keyword, final String[] fields) throws Exception {
		return selectListByKeyword(keyword, fields, null, null);
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListByField(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */	
	public List<T> selectListByField(final String field, final Object[] values, final Integer start, final Integer limit) throws Exception {
		return selectList(new RestrictionProvider(){

			@Override
			public void addRestriction(Criteria criteria) {
				criteria.add(Restrictions.in(field, values));
				setPageLimit(criteria, start, limit);
				
			}
			
		});
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListByExample(net.suntec.dynabiz.service.masterdataservice.entity.Entity, java.lang.Integer, java.lang.Integer)
	 */	
	public List<T> selectListByExample(final T example, final Integer start, final Integer limit) throws Exception {
		return selectList(new RestrictionProvider(){

			@Override
			public void addRestriction(Criteria criteria) {
				criteria.add(Example.create(example));		
				setPageLimit(criteria, start, limit);
			}
			
		});
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectListByKeyword(java.lang.String, java.lang.String[], java.lang.Integer, java.lang.Integer)
	 */	
	public List<T> selectListByKeyword(final String keyword, final String[] fields, final Integer start, final Integer limit) throws Exception {
		return selectList(new RestrictionProvider(){
			
			public void addRestriction(Criteria criteria) {
				Disjunction ors = Restrictions.disjunction();
				for(String field : fields){
					ors.add(Restrictions.like(field, keyword));
				}
				criteria.add(ors);			
				setPageLimit(criteria, start, limit);
			}
			
		});
	}



	/**
	 * @param offset
	 * @param limit
	 * @param criteria
	 */
	private void setPageLimit(Criteria criteria, final Integer offset, final Integer limit) {
		if(offset != null && limit != null){
			criteria.setFirstResult(offset);
			criteria.setMaxResults(limit);
		}
	}



	/* (non-Javadoc)
	 * @see net.suntec.dynabiz.service.masterdataservice.dao.CrudDAO#selectIdList(net.suntec.dynabiz.service.masterdataservice.dao.RestrictionProvider)
	 */
	@SuppressWarnings("unchecked")	
	public List<Long> selectIdList(RestrictionProvider restrictions) throws Exception {
		Criteria criteria = getSession().createCriteria(entityClazz);
		criteria.setProjection(Projections.id());	
		restrictions.addRestriction(criteria);
		return criteria.list();
	}

}

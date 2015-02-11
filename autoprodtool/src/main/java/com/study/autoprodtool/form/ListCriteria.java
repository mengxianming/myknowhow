/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.form;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.study.autoprodtool.common.Pager;
import com.study.autoprodtool.dao.RestrictionProvider;

/**
 * Descriptions
 *
 * @version 2015-2-6
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public abstract class ListCriteria<F>{
	private Pager pager;
	private String sortField;
	private String sortOrder;
	private String keyword;
	private F filter;
	
	/**
	 * @return the pager
	 */
	public Pager getPager() {
		return pager;
	}
	/**
	 * @param pager the pager to set
	 */
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}
	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the filter
	 */
	public F getFilter() {
		return filter;
	}
	/**
	 * @param filter the filter to set
	 */
	public void setFilter(F filter) {
		this.filter = filter;
	}
	
	public Order getOrder(){
		if(sortField != null){
			return "desc".equalsIgnoreCase(sortOrder) ? Order.desc(sortField) 
					: Order.desc(sortField);
		}
		return null;
	}
	
	public RestrictionProvider toRestrictionProvider(){
		return new RestrictionProvider() {
			
			@Override
			public void addRestriction(Criteria criteria) {				
				addCriterions(criteria);		
			}

			@Override
			public void addOrder(Criteria criteria) {
				if(getOrder() != null){
					criteria.addOrder(getOrder());
				}				
			}

			@Override
			public void addPager(Criteria criteria) {
				if(pager != null && pager.getPage() != null && pager.getLimit() != null){
					criteria.setFirstResult(pager.getOffset());
					criteria.setFetchSize(pager.getLimit());
				}					
			}
		};
	}
	
	protected abstract void addCriterions(Criteria criteria);
}

package com.study.autoprodtool.dao;


public interface RestrictionProvider {
	void addRestriction(CriteriaWrap criteria);
	void addOrder(CriteriaWrap criteria);
	void addPager(CriteriaWrap criteria);
}
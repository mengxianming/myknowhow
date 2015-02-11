package com.study.autoprodtool.dao;

import org.hibernate.Criteria;

public interface RestrictionProvider {
	void addRestriction(Criteria criteria);
	void addOrder(Criteria criteria);
	void addPager(Criteria criteria);
}
package com.study.autoprodtool.dao;

import org.hibernate.Criteria;

public interface RestrictionProvider {
	void addRestriction(Criteria criteria);
}
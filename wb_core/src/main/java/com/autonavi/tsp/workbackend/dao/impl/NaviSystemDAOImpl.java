package com.autonavi.tsp.workbackend.dao.impl;

import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.dao.define.NaviSystemDAO;
import com.autonavi.tsp.workbackend.model.NaviSystem;
import com.autonavi.tsp.workbackend.model.NaviSystemExample;

@Repository
public class NaviSystemDAOImpl extends BaseDAOImpl<NaviSystem, NaviSystemExample, String> implements NaviSystemDAO{
	
}
package com.autonavi.tsp.workbackend.dao.define;

import java.util.List;

import com.autonavi.tsp.workbackend.model.NaviBaseResInfo;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfoExample;

public interface NaviBaseResInfoDAO extends BaseDAO<NaviBaseResInfo, NaviBaseResInfoExample, Long>{
	List<NaviBaseResInfo> selectByResourceVersionId(String resourceVersionId);
	
}
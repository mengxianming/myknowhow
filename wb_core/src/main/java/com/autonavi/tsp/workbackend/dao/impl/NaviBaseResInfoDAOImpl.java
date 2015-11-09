package com.autonavi.tsp.workbackend.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.dao.define.NaviBaseResInfoDAO;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfo;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfoExample;
@Repository
public class NaviBaseResInfoDAOImpl extends BaseDAOImpl<NaviBaseResInfo, NaviBaseResInfoExample, Long> implements NaviBaseResInfoDAO{

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviBaseResInfoDAO#selectByResourceVersionId(java.lang.String)
	 */
	@Override
	public List<NaviBaseResInfo> selectByResourceVersionId(String resourceVersionId) {		
		NaviBaseResInfoExample example = new NaviBaseResInfoExample();
		example.createCriteria().andResourceVersionIdEqualTo(resourceVersionId);
		return selectByExample(example);
	}

	
	
}
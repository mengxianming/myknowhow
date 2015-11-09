package com.autonavi.tsp.workbackend.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO;
import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviCityListExample;
@Repository
public class NaviCityListDAOImpl extends BaseDAOImpl<NaviCityList, NaviCityListExample, Long> implements NaviCityListDAO{
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO#selectByResVersion(java.lang.String)
	 */
	@Override
	public List<NaviCityList> selectByResVersion(String resVersion) {		
		NaviCityListExample example = new NaviCityListExample();
		example.createCriteria().andResVersionEqualTo(resVersion);
		return selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO#selectByAdcode(java.lang.String, java.lang.String)
	 */
	@Override
	public NaviCityList selectByAdcode(String resVersion, String adcode) {
		NaviCityListExample example = new NaviCityListExample();
		example.createCriteria().andResVersionEqualTo(resVersion)
		.andAdcodeEqualTo(adcode);
		return selectOneByExample(example);
	}

	
}
package com.autonavi.tsp.workbackend.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.constant.NaviResStatuses;
import com.autonavi.tsp.workbackend.constant.ResUpdateTypes;
import com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfo;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfoExample;
@Repository
public class NaviMapCityInfoDAOImpl extends BaseDAOImpl<NaviMapCityInfo, NaviMapCityInfoExample, Long> implements NaviMapCityInfoDAO{	

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO#selectByResourceVersionId(java.lang.String)
	 */
	@Override
	public List<NaviMapCityInfo> selectByResourceVersionId(String resourceVersionId) {
		NaviMapCityInfoExample example = new NaviMapCityInfoExample();
		example.setOrderByClause("adcode desc");
		example.createCriteria().andResourceVersionIdEqualTo(resourceVersionId)
		.andStatusEqualTo(NaviResStatuses.USABLE.getValue());
		return selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO#selectMaxVersionFullData(java.lang.String, java.util.Collection)
	 */
	@Override
	public List<NaviMapCityInfo> selectMaxVersionFullData(String maxResVersion, Collection<String> availableResVersionIds) {		
		return customSqlMapper.selectMaxVersionFullData(maxResVersion, availableResVersionIds);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO#selectAddDataByResourceVersionId(java.lang.String)
	 */
	@Override
	public List<NaviMapCityInfo> selectAddDataByResourceVersionId(String resourceVersionId) {
		NaviMapCityInfoExample example = new NaviMapCityInfoExample();
		example.setOrderByClause("adcode desc");
		example.createCriteria().andResourceVersionIdEqualTo(resourceVersionId)
		.andUpdateTypeEqualTo(ResUpdateTypes.ADD.getValue())
		.andStatusEqualTo(NaviResStatuses.USABLE.getValue());
		return selectByExample(example);
	}

	
	
}
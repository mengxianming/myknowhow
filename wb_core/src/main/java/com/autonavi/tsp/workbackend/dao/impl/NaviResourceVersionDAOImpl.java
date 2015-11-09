package com.autonavi.tsp.workbackend.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.dao.define.NaviResourceVersionDAO;
import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.model.NaviResourceVersionExample;
import com.autonavi.tsp.workbackend.util.page.Page;
@Repository
public class NaviResourceVersionDAOImpl extends BaseDAOImpl<NaviResourceVersion, NaviResourceVersionExample, String> implements NaviResourceVersionDAO{
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.NaviResourceVersionDAO#selectResourcePublishCountPerVersion(java.util.List)
	 */
	@Override
	public List<Map<String, Object>> selectResourcePublishCountPerVersion(List<String> versionIds) {		
		return customSqlMapper.selectResourcePublishCountPerVersion(versionIds);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviResourceVersionDAO#findByPage(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<NaviResourceVersion> findByPage(Integer pageNO, Integer pageSize) {
		return customSqlMapper.selectNaviResourceVersionList(pageNO, pageSize);
	}
	
}
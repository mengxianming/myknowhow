package com.autonavi.tsp.workbackend.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO;
import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.model.NaviResourcePublishExample;
import com.autonavi.tsp.workbackend.util.page.Page;
@Repository
public class NaviResourcePublishDAOImpl extends BaseDAOImpl<NaviResourcePublish, NaviResourcePublishExample, Long> implements NaviResourcePublishDAO{

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO#findByPage(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<NaviResourcePublish> findByPage(Integer pageNO, Integer pageSize) {
		return customSqlMapper.selectNaviResourcePublishList(pageNO, pageSize);
	}	

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO#getAllBaseResPublishByApkv(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<NaviResourcePublish> getAllBaseResPublishByApkv(String syscode, String apkVersion, String dpiName,
			String pid, String osver) {
		return customSqlMapper.getAllBaseResPublishByApkv(syscode, apkVersion, dpiName, pid, osver);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO#getAllCityResPublishByApkv(java.lang.String, java.lang.String)
	 */
	@Override
	public List<NaviResourcePublish> getAllCityResPublishByApkv(String syscode, String apkVersion) {
		return customSqlMapper.getAllCityResPublishByApkv(syscode, apkVersion);
	}
	
}
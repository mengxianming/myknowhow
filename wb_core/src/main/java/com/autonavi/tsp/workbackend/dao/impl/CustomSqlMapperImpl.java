package com.autonavi.tsp.workbackend.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autonavi.tsp.workbackend.constant.NaviResTypes;
import com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfo;
import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.util.ListUtil;
import com.autonavi.tsp.workbackend.util.ListUtil.ChainMap;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;

@Repository
public class CustomSqlMapperImpl extends SqlSessionDaoSupport implements CustomSqlMapper{
	public static final String CUSTOM_SQL_MAPPER_NS = CustomSqlMapper.class.getName();
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper#selectNaviResourcePublishList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<NaviResourcePublish> selectNaviResourcePublishList(Integer pageNO, Integer pageSize) {
		PageHelper.startPage(pageNO, pageSize);
		List<NaviResourcePublish> list = getSqlSession().selectList(CUSTOM_SQL_MAPPER_NS.concat(".selectNaviResourcePublishList"));
		return (Page<NaviResourcePublish>) list;
	}
	
	@Override
	public List<NaviResourcePublish> getAllBaseResPublishByApkv(String syscode, String apkVersion, String dpiName, String pid, String osver) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("syscode", syscode);
		conditions.put("apkVersion", apkVersion);
		conditions.put("dpiName", dpiName);	
		
		//add by mmx: 基础资源需要根据pid以及os版本进行刷选
		conditions.put("pid", pid);
		conditions.put("osver", osver);		
		
		conditions.put("resType", NaviResTypes.BASERES.getValue());
		
		return  getSqlSession().selectList(CUSTOM_SQL_MAPPER_NS.concat(".getAllBaseResPublishByApkv"), conditions);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper#getAllBaseResPublishByApkv(java.lang.String, java.lang.String)
	 */
	@Override
	public List<NaviResourcePublish> getAllCityResPublishByApkv(String syscode, String apkVersion) {
		ChainMap<String, Object> params = ListUtil.chainMap(String.class, Object.class)
				.chainPut("syscode", syscode)
				.chainPut("apkVersion", apkVersion)
				.chainPut("resType", NaviResTypes.CITYRES.getValue());
		
		
		return  getSqlSession().selectList(CUSTOM_SQL_MAPPER_NS.concat(".getAllCityResPublishByApkv"), params);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper#selectResourcePublishCountPerVersion(java.util.List)
	 */
	@Override
	public List<Map<String, Object>> selectResourcePublishCountPerVersion(List<String> versionIds) {
		return getSqlSession().selectList(CUSTOM_SQL_MAPPER_NS.concat(".selectResourcePublishCountPerVersion"), versionIds);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper#selectNaviResourceVersionList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<NaviResourceVersion> selectNaviResourceVersionList(Integer pageNO, Integer pageSize) {
		PageHelper.startPage(pageNO, pageSize);
		List<NaviResourceVersion> list = getSqlSession().selectList(CUSTOM_SQL_MAPPER_NS.concat(".selectNaviResourceVersionList"));
		return (Page<NaviResourceVersion>) list;

	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper#selectMaxVersionFullData(java.lang.String, java.util.Collection)
	 */
	@Override
	public List<NaviMapCityInfo> selectMaxVersionFullData(String maxResVersion, Collection<String> availableResVersionIds) {
		ChainMap<String, Object> params = ListUtil.chainMap(String.class, Object.class)
				.chainPut("maxResVersion", maxResVersion)
				.chainPut("availableResVersionIds", availableResVersionIds);
		return getSqlSession().selectList(CUSTOM_SQL_MAPPER_NS.concat(".selectMaxVersionFullData"), params);
	}
	
}
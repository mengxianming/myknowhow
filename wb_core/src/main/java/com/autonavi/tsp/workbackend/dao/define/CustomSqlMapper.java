package com.autonavi.tsp.workbackend.dao.define;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.autonavi.tsp.workbackend.model.NaviMapCityInfo;
import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.util.page.Page;


public interface CustomSqlMapper{
	public Page<NaviResourcePublish> selectNaviResourcePublishList(Integer pageNO, Integer pageSize);
	public List<NaviResourcePublish> getAllBaseResPublishByApkv(String syscode, String apkVersion, String dpiName, String pid, String osver);
	public List<NaviResourcePublish> getAllCityResPublishByApkv(String syscode, String apkVersion);
	public List<Map<String, Object>> selectResourcePublishCountPerVersion(List<String> versionIds);
	public Page<NaviResourceVersion> selectNaviResourceVersionList(Integer pageNO, Integer pageSize);
	public List<NaviMapCityInfo> selectMaxVersionFullData(String maxResVersion, Collection<String> availableResVersionIds);
	
	
}
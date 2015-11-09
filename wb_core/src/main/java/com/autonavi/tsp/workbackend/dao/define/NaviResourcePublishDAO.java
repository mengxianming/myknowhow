package com.autonavi.tsp.workbackend.dao.define;

import java.util.List;

import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.model.NaviResourcePublishExample;
import com.autonavi.tsp.workbackend.util.page.Page;

public interface NaviResourcePublishDAO extends BaseDAO<NaviResourcePublish, NaviResourcePublishExample, Long>{
	 /**
     * 分页查询
     * @param param
     * @return
     */
    Page<NaviResourcePublish> findByPage(Integer pageNO, Integer pageSize);

    /**
     * 获取与用户本地apk相匹配的基础资源包发布记录列表
     * @param syscode
     * @param apkVersion
     * @param dpiName
     * @param pid
     * @param osver
     * @return
     */
    List<NaviResourcePublish> getAllBaseResPublishByApkv(String syscode, String apkVersion, String dpiName, String pid, String osver);

    /**
     * 获取与用户本地apk相匹配的城市资源包发布记录列表
     * @param syscode
     * @param apkVersion
     * @return
     */
    List<NaviResourcePublish> getAllCityResPublishByApkv(String syscode, String apkVersion);

}
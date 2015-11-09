package com.autonavi.tsp.workbackend.dao.define;

import java.util.Collection;
import java.util.List;

import com.autonavi.tsp.workbackend.model.NaviMapCityInfo;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfoExample;

public interface NaviMapCityInfoDAO extends BaseDAO<NaviMapCityInfo, NaviMapCityInfoExample, Long>{    
    /**
     * 根据资源版本id查询城市数据包信息
     * @param resourceVersionId
     * @return
     */
    List<NaviMapCityInfo> selectByResourceVersionId(String resourceVersionId);
    /**
     * 查询指定资源版本id下的所有增量数据包信息
     * @param resourceVersionId
     * @return
     */
    List<NaviMapCityInfo> selectAddDataByResourceVersionId(String resourceVersionId);
    public List<NaviMapCityInfo> selectMaxVersionFullData(String maxResVersion, Collection<String> availableResVersionIds);
}
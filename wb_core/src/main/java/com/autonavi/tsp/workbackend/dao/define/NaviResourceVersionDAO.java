package com.autonavi.tsp.workbackend.dao.define;

import java.util.List;
import java.util.Map;

import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.model.NaviResourceVersionExample;
import com.autonavi.tsp.workbackend.util.page.Page;

public interface NaviResourceVersionDAO extends BaseDAO<NaviResourceVersion, NaviResourceVersionExample, String>{	
	Page<NaviResourceVersion> findByPage(Integer pageNO, Integer pageSize);
    /**
     * 批量获取每个资源版本下的【发布中】或者【停止发布】状态的发布记录数。<br>
     * 该结果用于判断资源版本是否可以删除。<br>
     * 返回的结果的每一个元素结果如下：{"versionId":1, "publishCount": 0}.
     * @param versionIds 资源版本id列表。
     * @return
     */
    List<Map<String, Object>> selectResourcePublishCountPerVersion(List<String> versionIds);
}
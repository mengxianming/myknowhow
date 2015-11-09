package com.autonavi.tsp.workbackend.dao.define;

import java.util.List;

import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviCityListExample;

public interface NaviCityListDAO extends BaseDAO<NaviCityList, NaviCityListExample, Long>{
	List<NaviCityList> selectByResVersion(String resVersion);
	NaviCityList selectByAdcode(String resVersion, String adcode);
}
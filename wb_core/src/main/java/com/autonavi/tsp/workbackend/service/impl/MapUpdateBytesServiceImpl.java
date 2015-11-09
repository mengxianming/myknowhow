/**
 * autonavi.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.common.util.GZipUtils;
import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO;
import com.autonavi.tsp.workbackend.dto.MapDataCityDto;
import com.autonavi.tsp.workbackend.dto.MapMaxCityDto;
import com.autonavi.tsp.workbackend.dto.naviMap.NaviMapDataDto;
import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviCityListExample;
import com.autonavi.tsp.workbackend.service.IMapUpdateBytesService;
import com.autonavi.tsp.workbackend.service.IMapUpdateService;
import com.autonavi.tsp.workbackend.service.INaviResourceVersionService;
import com.autonavi.tsp.workbackend.util.JacksonUtil;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * Created by chuanbo.wei on 2015/4/15.
 */
public class MapUpdateBytesServiceImpl implements IMapUpdateBytesService {
//	private static Log log = LogFactory.getLog(MapUpdateBytesServiceImpl.class);
	@Autowired
	NaviCityListDAO naviCityListMapper;
    @Autowired
    private IMapUpdateService mapUpdateService;
    @Autowired
    private INaviResourceVersionService naviResourceVersionService;
 

    @Override
    public byte[] getMapByteData(String imei, String model, String os,String userid,String sid,String processtime,String protversion,String language,
                                     String pid,String apkv,String syscode,String mapversion ,String resolution, String needtaiwan, String[] mapvlist,String enginev
                                     , byte[] localCityVersions, boolean forLocalOnly) throws Exception{
    	NaviMapDataDto dto = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion, language,
    			pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev, localCityVersions, forLocalOnly);
    	if(dto != null){
    		String json = JacksonUtil.toJsonString(dto, PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    		return GZipUtils.compress(json.getBytes("utf-8"));
    	
    	}
    	
    	return new byte[0];

    }

   

    @Override
    public MapMaxCityDto getMapCityData(String adcode) throws Exception{
        MapMaxCityDto maxCityDto = new MapMaxCityDto();
        
        List<NaviCityList> list = null;
        try{
        	String maxresVersion = naviResourceVersionService.queryMaxCityResVersion();
			
        	if(!CheckUtil.isNull(maxresVersion)){
        		if(StringUtil.isEmpty(adcode)){
                	list = getAllProvinces(maxresVersion);
                }else{
                	list = getProvinceCities(adcode, maxresVersion);
                }
        	}            
           
            if(null!=list && list.size()>0){
                MapDataCityDto[] dtos = new MapDataCityDto[list.size()];
                int i = 0;
                for (NaviCityList po : list){
                    MapDataCityDto dto = new MapDataCityDto();
                    dto.setAdcode(po.getAdcode());
                    dto.setNameZh(po.getNameZh());
                    dto.setNameEn(po.getNameEn());
                    dto.setNameFt(po.getNameFt());
                    dtos[i++] = dto;
                }
                maxCityDto.setMapDataCitys(dtos);
                maxCityDto.setResult(true);
            }else{
                maxCityDto.setRespCode("1001");
                maxCityDto.setRespMsg("返回数据为空");
                maxCityDto.setResult(false);
            }
        }catch (Exception e){
            maxCityDto.setRespCode("1002");
            maxCityDto.setRespMsg("服务端未知异常");
            maxCityDto.setResult(false);
        }
        return maxCityDto;
    }



	/**
	 * @param adcode
	 * @param maxresVersion
	 * @return
	 */
	private List<NaviCityList> getProvinceCities(String adcode, String maxresVersion) {
		List<NaviCityList> list;
		NaviCityListExample example2 = new NaviCityListExample();
		example2.or()
		.andStatusEqualTo("0")
		.andResVersionEqualTo(maxresVersion)
		.andParentCodeEqualTo(adcode);
		
		list = naviCityListMapper.selectByExample(example2);
		return list;
	}



	/**
	 * @param maxresVersion
	 * @return
	 */
	private List<NaviCityList> getAllProvinces(String maxresVersion) {
		List<NaviCityList> list;
		NaviCityListExample example1 = new NaviCityListExample();
		example1.or()
		.andStatusEqualTo("0")
		.andResVersionEqualTo(maxresVersion)
		.andParentCodeIsNull();
		example1.or()
		.andStatusEqualTo("0")
		.andResVersionEqualTo(maxresVersion)
		.andParentCodeEqualTo("");
		
		list = naviCityListMapper.selectByExample(example1);
		return list;
	}



	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.IMapUpdateBytesService#getServerTime()
	 */
	@Override
	public Date getServerTime() {		
		return new Date();
	}
}
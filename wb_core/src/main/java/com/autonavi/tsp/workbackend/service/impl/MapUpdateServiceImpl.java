/**
 * autonavi.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.common.util.DateUtil;
import com.autonavi.tsp.common.util.GZipUtils;
import com.autonavi.tsp.common.util.PropertiesParse;
import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.constant.PlatformIds;
import com.autonavi.tsp.workbackend.constant.ResPublishTargetUserTypes;
import com.autonavi.tsp.workbackend.constant.ResUpdateTypes;
import com.autonavi.tsp.workbackend.constant.ResUpgradeTypes;
import com.autonavi.tsp.workbackend.dao.define.NaviBaseResInfoDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO;
import com.autonavi.tsp.workbackend.dto.naviMap.BaseResDto;
import com.autonavi.tsp.workbackend.dto.naviMap.CityDataDto;
import com.autonavi.tsp.workbackend.dto.naviMap.MapDataParams;
import com.autonavi.tsp.workbackend.dto.naviMap.NaviMapDataDto;
import com.autonavi.tsp.workbackend.dto.naviMap.ProviceDataDto;
import com.autonavi.tsp.workbackend.dto.naviMap.ResVersionDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.exception.ValidationException;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfo;
import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfo;
import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.service.IMapUpdateService;
import com.autonavi.tsp.workbackend.service.INaviWhiteListService;
import com.autonavi.tsp.workbackend.service.ISessionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ListUtil;
import com.autonavi.tsp.workbackend.util.ListUtil.ElementFilter;

/**
 * Created by chuanbo.wei on 2015/4/15.
 */
public class MapUpdateServiceImpl implements IMapUpdateService {
	//城市排序器
	private static class CityDataComparator implements Comparator<CityDataDto> {
		public int compare(CityDataDto city1, CityDataDto city2) {
			return city1.getNameEn().compareTo(city2.getNameEn());
		}
	}	
	
	//省份排序器
	private static class ProvinceComparator implements Comparator<ProviceDataDto> {	
		@Override
		public int compare(ProviceDataDto o1, ProviceDataDto o2) {

			String mappedName1 = mapName(o1.getAdcode(), o1.getNameEn());
			String mappedName2 = mapName(o2.getAdcode(), o2.getNameEn());

			return mappedName1.compareTo(mappedName2);
		}


		/**
		 * @param adcode
		 * @param nameEn
		 * @return
		 */
		private String mapName(String adcode, String nameEn) {
			int idx = 2000;
			if(zxsAdcodes.contains(adcode)){
				idx = 1000 + zxsAdcodes.indexOf(adcode); 
			}else if(gangaoAdcodes.contains(adcode)){
				idx = 3000 + gangaoAdcodes.indexOf(adcode); 
			}

			return idx + " " + nameEn;
		}

	}

	private static Log log = LogFactory.getLog(MapUpdateServiceImpl.class);

	private static String PROVICE_ADCODE_TAIWANG = "710000";
	private static String PROVICE_ADCODE_XIANGGANG = "810000";
	private static String PROVICE_ADCODE_AOMEN = "820000";
	
	private static List<String> zxsAdcodes = Arrays.asList("110000","120000","310000","500000");	
	private static List<String> gangaoAdcodes = Arrays.asList(PROVICE_ADCODE_XIANGGANG, PROVICE_ADCODE_AOMEN);

	@Autowired
	NaviResourcePublishDAO naviResourcePublishMapper;
	@Autowired
	NaviMapCityInfoDAO naviMapCityInfoMapper;
	@Autowired
	NaviCityListDAO naviCityListDAO;
	@Autowired
	NaviBaseResInfoDAO naviBaseResInfoDAO;
	@Autowired
	ISessionService sessionService;
	@Autowired
	INaviWhiteListService naviWhiteListService;

	private static final String BASERES_NAME_ZH="基础资源";
	private static final String BASERES_NAME_EN="BasicResources";
	private static final String BASERES_NAME_FT="基礎資源";
	private static final String FORCE_UPDATE="{'zw':'最新版本需要重新下载最新地图数据才能正常使用，感谢您的支持。','yw':'New APP need to download the latest map data, thank you for your support.','ft':'最新版本需要重新下載最新地圖數據才能正常使用，感謝您的支持。'}";
	private static final String BASE_RES_UPDATE="{'zw':'发现基础资源有更新，更新后界面显示更完整。','yw':'Basic Resources updates, UI will be more complete.','ft':'發現基礎資源有更新，更新後界面顯示更完整。'}";
	private static final String MAP_DATE_UPDATE="{'zw':'检测到地图数据有更新，是否立即更新？','yw':'Map data is updated, whether Update Now?','ft':'檢測到地圖數據有更新，是否立即更新？'}";
	private static final String NO_MATCH_DATA="{'zw':'没有匹配的数据供下载 ','yw':'No data available for download','ft':'沒有匹配的數據供下載'}";

	private static final String DATA_DIR_PREFIX = "data/chn/";


	@Override
	public NaviMapDataDto getMapData(String imei, String model, String os,String userid,String sid,String processtime,String protversion,String language,
			String pid,String apkv,String syscode,String mapversion ,String resolution, String needtaiwan, String[] mapvlist,String enginev,
			byte[] localCityVersions, boolean forLocalOnly){
		//convert to dto
		//NOTICE: processtime， protversion， mapvlist are not used any more. They will be deleted in future
		MapDataParams mapDataParams = new MapDataParams();
		mapDataParams.setImei(imei);
		mapDataParams.setModel(model);
		mapDataParams.setOs(os);
		mapDataParams.setUserid(userid);
		mapDataParams.setSid(sid);
		mapDataParams.setLanguage(language);
		mapDataParams.setPid(pid);
		mapDataParams.setApkv(apkv);
		mapDataParams.setSyscode(syscode);
		mapDataParams.setMapversion(mapversion);
		mapDataParams.setResolution(resolution);
		mapDataParams.setNeedtaiwan(needtaiwan);
		mapDataParams.setEnginev(enginev);
		mapDataParams.setLocalCityVersions(localCityVersions);
		mapDataParams.setForLocalOnly(forLocalOnly);
				
		return queryMapData(mapDataParams);
	}

	/**
	 * @param localCityVersions
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	private Map<String, String> getLocalCityVersionsMap(byte[] localCityVersions) throws Exception {
		Map<String, String> result = new HashMap<String, String>();

		if(CheckUtil.isNull(localCityVersions)){
			return result;
		}
		
		String json = new String(GZipUtils.decompress(localCityVersions), "utf-8");
		log.debug("ungzip content for localCityVersions:" + json);
		List<String> cvlines;
		try {
			cvlines = JSON.parseArray(json, String.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CommonException(ExceptionCodeEnum.MU_PARAM_INVALID_LOCALCITYVERSIONS);
		}
		for(String cvline : cvlines){
			String[] cvarr = cvline.split(",");
			String adcode = cvarr[0];
			String resver = cvarr[1];
			if(CheckUtil.isNull(adcode) || CheckUtil.isNull(resver)){
				throw new CommonException(ExceptionCodeEnum.MU_PARAM_INVALID_LOCALCITYVERSIONS);
			}
			result.put(adcode, resver);
		}

		return result;
	}

	/**
	 * 获取基础资源包的最新版本更新信息。目前基础资源包只有全量更新、没有增量更新。
	 * @param resVersion
	 * @param syscode
	 * @param apkv
	 * @param resolution
	 * @return
	 * @throws Exception
	 */
	private BaseResDto getBaseResData(final MapDataParams mapDataParams) throws Exception {
		//获取与用户终端适配的所有基础资源版本信息		
		List<NaviResourcePublish> matchedPublishes = naviResourcePublishMapper.getAllBaseResPublishByApkv(mapDataParams.getSyscode(), 
				mapDataParams.getApkv(), mapDataParams.getResolution(), mapDataParams.getPid(), mapDataParams.getOs());
		
		//若没有适配基础资源版本信息、则报错
		// 此处应该通过||连接、而不是&&。已经修正。
		if(CheckUtil.isNull(matchedPublishes)){
			throw new CommonException(1003, NO_MATCH_DATA);
		}
	
		//找出最新发布版本、且优先级更高（强制更新优先度高）的发布
		NaviResourcePublish matchedPublish = getLatestPublish(matchedPublishes);
		
		//获取最大版本		
		String maxResVerNo = getResVersion(matchedPublish.getExtras());
		
		//获取最大版本下的所有与用户apk匹配的基础数据
		List<NaviBaseResInfo> baseResInfoList = naviBaseResInfoDAO.selectByResourceVersionId(matchedPublish.getResourceVersionId());				
		
		class AllAddDataFilter implements ElementFilter<NaviBaseResInfo>{
			ResUpdateTypes resUpdateType;
			public  AllAddDataFilter(ResUpdateTypes resUpdateType){
				this.resUpdateType = resUpdateType;
			}

			@Override
			public boolean filter(NaviBaseResInfo baseResData) {
				//找到匹配更新类型、分辨率、操作系统版本的数据
				if(resUpdateType.getValue().equals(baseResData.getUpdateType())
						&& baseResData.getDpiName().equals(mapDataParams.getResolution())){
					if(CheckUtil.isNull(mapDataParams.getPid())){
						return true;
					}
					if(mapDataParams.getPid().equals(baseResData.getPid())){
						if(PlatformIds.IOS.getValue().equals(mapDataParams.getPid())
								&& !CheckUtil.isNull(mapDataParams.getOs())){
							return mapDataParams.getOs().equals(baseResData.getOsver());
						}
						return true;
					}
					
				}
				
				return false;
			}
			
		};
		NaviBaseResInfo resAll = ListUtil.first(baseResInfoList, new AllAddDataFilter(ResUpdateTypes.FULL));	
		NaviBaseResInfo resAdd = ListUtil.first(baseResInfoList,  new AllAddDataFilter(ResUpdateTypes.ADD));			
		
		//若没有比用户既存基础资源包版本更高的全量包或者增量包信息、则报错
		if(null==resAll && null==resAdd){
			throw new CommonException(1003, NO_MATCH_DATA);
		}
				
		
		BaseResDto dto = new BaseResDto();
		//若用户版本为空、或者等于最大版本、或者不合法，或者增量包不存在，则为针对最大版本的全量更新
		if(StringUtil.isEmpty(mapDataParams.getMapversion()) 
				|| mapDataParams.getMapversion().equals(maxResVerNo)
				|| !containsInPublishList(matchedPublishes, mapDataParams.getMapversion())
				|| resAdd == null){
			dto.setUpdateType(ResUpdateTypes.FULL.getValue());			
		}else{
			//其余情况则为针对最新版本的增量更新
			dto.setUpdateType(ResUpdateTypes.ADD.getValue());
		}

		//判断用户是否在白名单里（若发布的用户范围为白名单用户)
		checkUserInWhiteList(mapDataParams.getUserid(), mapDataParams.getImei(), matchedPublish.getDownloadType());

		//判断是否强制用户更新最新版本基础资源包
		checkForceUpdate(dto, mapDataParams.getMapversion(), matchedPublish, matchedPublishes);
		

		dto.setVersion(maxResVerNo);		
		String pixUrl = PropertiesParse.getProperty("aliyun.oss.visite_url","");
		dto.setNameZh(BASERES_NAME_ZH);
		dto.setNameFt(BASERES_NAME_FT);
		dto.setNameEn(BASERES_NAME_EN);
		if(resAll != null){
			dto.setAllMd5(CheckUtil.makeNotNull(resAll.getMd5(), String.class));
			dto.setAllSize(CheckUtil.makeNotNull(resAll.getSize(), Long.class));
			dto.setAllZipSize(CheckUtil.makeNotNull(resAll.getZipSize(), Long.class));
			dto.setAllUrl(pixUrl + resAll.getOssName());
		}		
		if(resAdd != null){
			dto.setAddMd5(CheckUtil.makeNotNull(resAdd.getMd5(), String.class));
			dto.setAddSize(CheckUtil.makeNotNull(resAdd.getSize(), Long.class));
			dto.setAddZipSize(CheckUtil.makeNotNull(resAdd.getZipSize(), Long.class));
			dto.setAddUrl(pixUrl + resAdd.getOssName());
		}
		return dto;
	}

	private String getResVersion(Map<String, Object> extras) {
		final String key = "navi_resource_version_res_version";
		return extras == null ? null : (String)extras .get(key);
	}
	
	/**
	 * @param matchedPublishes
	 * @param mapversion
	 * @return
	 */
	private boolean containsInPublishList(List<NaviResourcePublish> matchedPublishes, String mapversion) {
		for(NaviResourcePublish publish : matchedPublishes){
			if(mapversion.equals(getResVersion(publish.getExtras()))){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户是否在白名单里（若发布的用户范围为白名单用户)
	 * @param userId
	 * @param imei
	 * @param resAll
	 * @throws Exception
	 * @throws CommonException
	 */
	private void checkUserInWhiteList(String userId, String imei, String downloadType) throws Exception {
		if(ResPublishTargetUserTypes.WHITELIST.getValue().equals(downloadType)){
			if(!naviWhiteListService.checkUserInWhiteList(userId, imei)){
				throw new CommonException(ExceptionCodeEnum.MU_USER_NOT_IN_WHITELIST, "userId=" + userId + ", imei=" + imei);
			}
		}
	}

	/**
	 * 检查基础资源升级类型.
	 * 1. 若用户当前基础资源包版本在db中找不到相关发布记录、则强制用户更新
	 * 2. 若用户当前基础资源包版本在db中能找到相应发布记录、则根据db中最新版本发布记录的配置、或强制更新、或不强制更新。
	 * 3. 若用户当前基础资源包版本为空、则强制用户更新
	 * @param baseVersion
	 * @param reslist
	 * @return
	 */
	private void checkForceUpdate(BaseResDto baseResDto, String baseVersion, NaviResourcePublish matchedPublish, List<NaviResourcePublish> availablePublishes){		
		boolean force = true;
		if(StringUtil.isNotEmpty(baseVersion)){	//本地基础资源版本号不为空，判断apk和基础资源是否兼容
			if(containsInPublishList(availablePublishes, baseVersion)){//match
				force = ResUpgradeTypes.YES.getValue().equals(matchedPublish.getUpgradeType());				
			}else{	//基础资源和apk不匹配
				force = true;
			}
		}else{	//本地基础资源版本号为空，下发非强制升级
			force = false;
		}
		
		if(force){
			baseResDto.setUpgradeType(ResUpgradeTypes.YES.getValue());
			baseResDto.setMemo(FORCE_UPDATE);
		}else{
			baseResDto.setUpgradeType(ResUpgradeTypes.NO.getValue());
			baseResDto.setMemo(BASE_RES_UPDATE);
		}
		
	}

	private void getAllMapvByApkv(ResVersionDto dto, MapDataParams mapDataParams, Map<String, String> mLocalCityVersions) throws Exception {	
		//获取与用户终端适配的所有城市资源包版本信息		
		List<NaviResourcePublish> matchedPublishes = naviResourcePublishMapper.getAllCityResPublishByApkv(mapDataParams.getSyscode(), mapDataParams.getApkv());
		//若没有匹配的城市资源包版本信息、则报错
		if(null == matchedPublishes || matchedPublishes.size() <= 0){
			throw new CommonException(1008, NO_MATCH_DATA);
		}

		//lists按照res_version降序排序、故第一个元素为版本最大元素、即最新版本的城市数据。
		NaviResourcePublish matchedPublish = getLatestPublish(matchedPublishes);

		//判断用户是否在白名单里（若发布的用户范围为白名单用户)
		checkUserInWhiteList(mapDataParams.getUserid(), mapDataParams.getImei(), matchedPublish.getDownloadType());

		dto.setMapv(getResVersion(matchedPublish.getExtras()));

		
		//判断是否强制用户更新城市地图包
		String[] mapvlist = mLocalCityVersions.values().toArray(new String[0]);//		
		getUpgradeType(dto, mapvlist,matchedPublishes, matchedPublish);
		
		dto.setNocross(getAllNoCross(matchedPublish));
		dto.setBaseurl(PropertiesParse.getProperty("aliyun.oss.visite_url",""));

		//获取最新资源包版本下的所有省份、城市的更新信息
		dto.setProvices(getMapvCityList(dto, matchedPublish, matchedPublishes, mapDataParams.getNeedtaiwan(), mLocalCityVersions, mapDataParams.isForLocalOnly()));
		
	}

	/**
	 * @param lists
	 * @return
	 */
	private NaviResourcePublish getLatestPublish(List<NaviResourcePublish> availables) {
		//find the max ver
		NaviResourcePublish latest = availables.get(0);
		for(int i = 1; i < availables.size(); i++){
			NaviResourcePublish cadidate = availables.get(i);
			if(getResVersion(cadidate.getExtras()).compareTo(getResVersion(latest.getExtras())) > 0){
				latest = cadidate;
				break;
			}
		}
		
		//check if there is a force update version
		for(int i = 1; i < availables.size(); i++){
			NaviResourcePublish cadidate = availables.get(i);
			if(getResVersion(cadidate.getExtras()).equals(getResVersion(latest.getExtras()))
					&& ResUpgradeTypes.YES.getValue().equals(cadidate.getUpgradeType())){
				//强制更新的版本优先度高
				latest = cadidate;
				break;
			}
		}

		return latest;
	}

	private ProviceDataDto[] getMapvCityList(ResVersionDto dto, NaviResourcePublish matchedPublish, List<NaviResourcePublish> matchedPublishes,
			String needtaiwan, final Map<String, String> mLocalCityVersions, boolean forLocalOnly) throws Exception {
		final String resVer = getResVersion(matchedPublish.getExtras());

		//获取大数据版本下的所有省份、城市信息。
		List<NaviCityList> cityAndProvinceList = naviCityListDAO.selectByResVersion(resVer);
		if(null==cityAndProvinceList || cityAndProvinceList.size()<=0){
			throw new CommonException(1008, NO_MATCH_DATA);
		}
				
		//获取省份集合
		List<NaviCityList> provinceList =  ListUtil.filter(cityAndProvinceList, new ElementFilter<NaviCityList>() {

			@Override
			public boolean filter(NaviCityList city) {				
				return StringUtil.isEmpty(city.getParentCode());
			}
		});  
		Map<String, NaviCityList> provinceMap = ListUtil.listToMap(provinceList, "adcode");   
		if(StringUtil.isEmpty(needtaiwan) || "0".equals(needtaiwan)){
			//台湾数据，不需要
			provinceMap.remove(PROVICE_ADCODE_TAIWANG);
		}


		//获取城市集合
		List<NaviCityList> cityList =  ListUtil.filter(cityAndProvinceList, new ElementFilter<NaviCityList>() {

			@Override
			public boolean filter(NaviCityList city) {				
				return StringUtil.isNotEmpty(city.getParentCode());
			}
		}); 
		//得到每个省份对应的城市列表、key为省份的adcode、value是城市列表
		Map<String, List<NaviCityList>> provinceCitysMap = ListUtil.groupListBy(cityList, "parentCode");  

		//获取城市增量数据集合
		List<NaviMapCityInfo> cityAddDataList = naviMapCityInfoMapper.selectAddDataByResourceVersionId(matchedPublish.getResourceVersionId());
		
		//获取所有城市的全量数据
		Collection<String> avalableResVersionIds = ListUtil.select(matchedPublishes, "resourceVersionId");		
		List<NaviMapCityInfo> cityAllDataList = naviMapCityInfoMapper.selectMaxVersionFullData(resVer, avalableResVersionIds);
		//城市全量数据转换成map、方便处理
		Map<String, NaviMapCityInfo> cityAllDataMap = ListUtil.listToMap(cityAllDataList, "adcode");


		//若为强制更新、则返回全量数据包
		//判断是否强制更新不只是看latestver的配置、应该看前面的计算结果
		if(ResUpgradeTypes.YES.getValue().equals(dto.getUpgradeType())){
			if(CheckUtil.isNull(mLocalCityVersions)//没有传入本地地图版本、
					|| !forLocalOnly //传入本地地图版本、但没有要求只返回本地数据
					){
				//则返回全国全部城市的全量数据包
				return process(provinceMap, provinceCitysMap, cityAllDataMap, null);
			}else{
				//传入本地地图版本、且要求只返回本地数据、则返回本地城市的全量数据包
				Map<String, NaviMapCityInfo> localCityAllDataMap = ListUtil.filterMap(cityAllDataMap,
						new ElementFilter<Entry<String, NaviMapCityInfo>>(){

					@Override
					public boolean filter(Entry<String, NaviMapCityInfo> entry) {				
						return mLocalCityVersions.get(entry.getKey()) != null;
					}

				});
				return processLocalOnly(provinceMap, provinceCitysMap, localCityAllDataMap,	null, mLocalCityVersions);     
			}
		}

		//非强制更新的场合
		if(CheckUtil.isNull(mLocalCityVersions)){
			//没有传入本地地图版本
			//则返回全国全部城市的全量数据包
			return process(provinceMap, provinceCitysMap, cityAllDataMap, null);
		}else{
			//传入本地地图版本、则检查本地城市是否有增量包
			List<NaviMapCityInfo> localCityAddDataList = ListUtil.filter(cityAddDataList, new ElementFilter<NaviMapCityInfo>() {

				@Override
				public boolean filter(NaviMapCityInfo city) {
					//城市数据需为增量更新包, 且包含在用户本地城市里、且该增量包所基于的版本与本地城市版本一致				
					return  ResUpdateTypes.ADD.getValue().equals(city.getUpdateType())
							&& mLocalCityVersions.get(city.getAdcode()) != null
							&& mLocalCityVersions.get(city.getAdcode()).equals(city.getBaseResVersion());
				}
			});  
			Map<String, NaviMapCityInfo> localCityAddDataMap = ListUtil.listToMap(localCityAddDataList, "adcode");

			if(forLocalOnly){
				//只保留本地城市的全量包
				Map<String, NaviMapCityInfo> localCityAllDataMap = ListUtil.filterMap(cityAllDataMap,
						new ElementFilter<Entry<String, NaviMapCityInfo>>(){
		
					@Override
					public boolean filter(Entry<String, NaviMapCityInfo> entry) {				
						return mLocalCityVersions.get(entry.getKey()) != null;
					}
		
				});
				return processLocalOnly(provinceMap, provinceCitysMap, localCityAllDataMap,
						localCityAddDataMap, mLocalCityVersions);        		
			}else{
				//返回本地以外城市的全量包以及本地增量包信息
				return process(provinceMap, provinceCitysMap, cityAllDataMap, localCityAddDataMap);    
			}
		} 


	}

	/**
	 * @param mLocalCityVersions
	 * @param version
	 * @param provinceMap
	 * @param provinceCitysMap
	 * @param localCityAllDataMap
	 * @param localCityAddDataMap
	 * @return
	 */
	private ProviceDataDto[] processLocalOnly(Map<String, NaviCityList> provinceMap, Map<String, List<NaviCityList>> provinceCitysMap,
			Map<String, NaviMapCityInfo> localCityAllDataMap, Map<String, NaviMapCityInfo> localCityAddDataMap,
			final Map<String, String> mLocalCityVersions) {
		//保留本地数据的省份
		Map<String, NaviCityList> localProvinceMap = ListUtil.filterMap(provinceMap,
				new ElementFilter<Entry<String, NaviCityList>>(){

			@Override
			public boolean filter(Entry<String, NaviCityList> entry) {
				String padcode = entry.getValue().getAdcode();
				for(String ladcode : mLocalCityVersions.keySet()){
					//长度相同、且前缀相同
					if(ladcode.length() == padcode.length() 
							&& ladcode.substring(0, ladcode.length() - 4).equals(padcode.substring(0, padcode.length() - 4))){
						return true;
					}
				}
				return false;
			}

		});
		
		//只保留本地城市
		Map<String, List<NaviCityList>> localProvinceCitysMap = new HashMap<String, List<NaviCityList>>();
		for(String padcode : provinceCitysMap.keySet()){
			if(localProvinceMap.containsKey(padcode)){
				List<NaviCityList> localCitys = new ArrayList<NaviCityList>();
				List<NaviCityList> citys = provinceCitysMap.get(padcode);
				for(NaviCityList city : citys){
					if(mLocalCityVersions.containsKey(city.getAdcode())){
						localCitys.add(city);
					}
				}
				localProvinceCitysMap.put(padcode, localCitys);
			}
			
			
		}
		
		//返回本地数据的全量包以及增量包信息
		return process(localProvinceMap, localProvinceCitysMap, localCityAllDataMap, localCityAddDataMap);
	}


	/**
	 * @param provinceMap
	 * @param provinceCitysMap
	 * @param cityDataMap
	 * @param b
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ProviceDataDto[] process(Map<String, NaviCityList> provinceMap,
			Map<String, List<NaviCityList>> provinceCitysMap, Map<String, NaviMapCityInfo> cityAllDataMap,
			Map<String, NaviMapCityInfo> cityAddDataMap){
		provinceMap = CheckUtil.makeNotNull(provinceMap, HashMap.class);
		provinceCitysMap = CheckUtil.makeNotNull(provinceCitysMap, HashMap.class);
		cityAllDataMap = CheckUtil.makeNotNull(cityAllDataMap, HashMap.class);
		cityAddDataMap = CheckUtil.makeNotNull(cityAddDataMap, HashMap.class);
		
		List<ProviceDataDto> provinces = toProvinces(provinceMap);
		//处理省、直辖市级别的更新信息
		for(ProviceDataDto province : provinces){			
			if(isZhixiashi(province)){
				//该省为直辖市				
				
				//默认为全更新
				province.setUpdateType(ResUpdateTypes.FULL.getValue());
				
				//设置更新信息
				NaviMapCityInfo addData = cityAddDataMap.get(province.getAdcode());				
				if(addData != null){
					province.setUpdateType(ResUpdateTypes.ADD.getValue());
					province.setAddMd5(CheckUtil.makeNotNull(addData.getMd5(), String.class));
					province.setAddSize(CheckUtil.makeNotNull(addData.getSize(), Long.class));
					province.setAddUrl(getDownloadUrl(addData));
					province.setAddZipSize(CheckUtil.makeNotNull(addData.getZipSize(), Long.class));
					province.setRelatedCities(getRelatedCities(addData));
				}
				NaviMapCityInfo allData = cityAllDataMap.get(province.getAdcode());
				if(allData != null){
					province.setAllMd5(CheckUtil.makeNotNull(allData.getMd5(), String.class));
					province.setAllSize(CheckUtil.makeNotNull(allData.getSize(), Long.class));
					province.setAllUrl(getDownloadUrl(allData));
					province.setAllZipSize(CheckUtil.makeNotNull(allData.getZipSize(), Long.class));
					province.setRelatedCities(getRelatedCities(allData));
					//版本为全量数据版本
					province.setVersion(getResVersion(allData.getExtras()));
				}				 
			}else{				
				//处理各个市的更新信息
				List<NaviCityList> provinceCitys = provinceCitysMap.get(province.getAdcode());
				if(CheckUtil.isNull(provinceCitys)){
					//该省没有城市数据，不做处理
					continue;
				}
				List<CityDataDto> provinceCityDatas = new ArrayList<CityDataDto>();
				for(NaviCityList cityInfo : provinceCitys){
					//先copy基本信息
					CityDataDto cityDto = toCityDataDto(cityInfo);
					//默认为全更新
					cityDto.setUpdateType(ResUpdateTypes.FULL.getValue());

					//再设置更新信息
					NaviMapCityInfo addData = cityAddDataMap.get(cityDto.getAdcode());
					if(addData != null){
						cityDto.setUpdateType(ResUpdateTypes.ADD.getValue());
						cityDto.setAddMd5(CheckUtil.makeNotNull(addData.getMd5(), String.class));
						cityDto.setAddSize(CheckUtil.makeNotNull(addData.getSize(), Long.class));
						cityDto.setAddUrl(getDownloadUrl(addData));
						cityDto.setAddZipSize(CheckUtil.makeNotNull(addData.getZipSize(), Long.class));		
						cityDto.setRelatedCities(getRelatedCities(addData));
					}
					NaviMapCityInfo allData = cityAllDataMap.get(cityDto.getAdcode());
					if(allData != null){
						cityDto.setAllMd5(CheckUtil.makeNotNull(allData.getMd5(), String.class));
						cityDto.setAllSize(CheckUtil.makeNotNull(allData.getSize(), Long.class));
						cityDto.setAllUrl(getDownloadUrl(allData));
						cityDto.setAllZipSize(CheckUtil.makeNotNull(allData.getZipSize(), Long.class));
						cityDto.setRelatedCities(getRelatedCities(allData));
						//版本为全量数据版本
						cityDto.setVersion(getResVersion(allData.getExtras()));
					}		

					//最后把处理好的城市信息加入到缓存列表里		
					provinceCityDatas.add(cityDto);					
				}				
				//对省份下的所有城市按照拼音排序
				Collections.sort(provinceCityDatas, new CityDataComparator());
				//最后把处理好的城市信息加入省的城市列表里	
				province.addCityAll(provinceCityDatas);
			}
		}			

		return provinces.toArray(new ProviceDataDto[0]);
	}

	/**
	 * @param addData
	 * @return
	 */
	private String getDownloadUrl(NaviMapCityInfo mapData) {		
		if(mapData.getOssName() != null && mapData.getOssName().length() > 0){
			return mapData.getOssName();
		}
		return "";
	}

	/**
	 * @param province
	 * @return
	 */
	private boolean isZhixiashi(ProviceDataDto province) {
		String adcode = province.getAdcode();
		return gangaoAdcodes.contains(adcode) || zxsAdcodes.contains(adcode);
	}

	/**
	 * @param cityInfo
	 * @return
	 */
	private CityDataDto toCityDataDto(NaviCityList cityInfo) {
		CityDataDto city = new CityDataDto();
				
		city.setNameZh(CheckUtil.makeNotNull(cityInfo.getNameZh(), String.class));
		city.setNameFt(CheckUtil.makeNotNull(cityInfo.getNameFt(), String.class));
		city.setNameEn(CheckUtil.makeNotNull(cityInfo.getNameEn(), String.class));
		city.setAdcode(CheckUtil.makeNotNull(cityInfo.getAdcode(), String.class));
		//add dataDir property
		city.setDataDir(DATA_DIR_PREFIX + CheckUtil.makeNotNull(cityInfo.getPkgname(), String.class));
		return city;
	}

	/**
	 * @param cityInfo
	 * @param city
	 */
	private String[] getRelatedCities(NaviMapCityInfo cityInfo) {
		//关联城市
		if(!CheckUtil.isNull(cityInfo.getRelatedCities())){
			return cityInfo.getRelatedCities().split(",");
		}
		return null;
	}

	/**
	 * @param values
	 * @return
	 */
	private List<ProviceDataDto> toProvinces(Map<String, NaviCityList> provinceMap) {        
		List<ProviceDataDto> result = new ArrayList<ProviceDataDto>();
		for (NaviCityList cityInfo : provinceMap.values()){
			ProviceDataDto pro = new ProviceDataDto();
						
			pro.setNameZh(CheckUtil.makeNotNull(cityInfo.getNameZh(), String.class));
			pro.setNameFt(CheckUtil.makeNotNull(cityInfo.getNameFt(), String.class));
			pro.setNameEn(CheckUtil.makeNotNull(cityInfo.getNameEn(), String.class));
			pro.setAdcode(CheckUtil.makeNotNull(cityInfo.getAdcode(), String.class));
			//add dataDir property
			pro.setDataDir(DATA_DIR_PREFIX + CheckUtil.makeNotNull(cityInfo.getPkgname(), String.class));
			
			result.add(pro);
		}

		//按规则排序:直辖市在最前面、一般省份按照拼音排序、港澳排在后面
		Collections.sort(result, new ProvinceComparator());

		return result;
	}

	/**
	 * 判断地图数据升级类型
	 * 1. 若用户本地所有城市的资源包版本都合法（即包含在适配列表里）、则不强制更新
	 * 2. 否则强制更新
	 * 
	 * 最新的判断逻辑： 若用户本地所有的城市资源版本都合法、则根据最新版本的配置信息、决定是否强制更新。
	 * @param localMaps 用户本地所有城市资源包版本
	 * @param allMaps 与用户终端适配的所有城市资源包版本
	 * @return
	 */
	private void getUpgradeType(ResVersionDto dto, String[] localResVers, List<NaviResourcePublish> matchedPublishes, NaviResourcePublish matchedPublish){
		List<String> apk_mapv_notmatchList = new ArrayList<String>();
		boolean forceUpdate = false;

		//最新版本发布时配置为非强制更新时、需要结合客户终端条件判断是否需强制更新
		if(ResUpgradeTypes.NO.getValue().equals(matchedPublish.getUpgradeType())){
			//判断每个用户本地版本是否在适配列表里           
			if (null != localResVers && localResVers.length > 0){
				for(String localmapv : localResVers){
					if(StringUtil.isNotEmpty(localmapv) && !containsInPublishList(matchedPublishes, localmapv)){
						apk_mapv_notmatchList.add(localmapv);
						forceUpdate = true;
					}
				}
			}
		}else{
			//发布配置为强制更新
			forceUpdate = true;
		}

		if(forceUpdate){//地图数据和apk不匹配
			dto.setUpgradeType(ResUpgradeTypes.YES.getValue());
			dto.setMemo(FORCE_UPDATE);			
		}else{
			dto.setUpgradeType(ResUpgradeTypes.NO.getValue());
			dto.setMemo(MAP_DATE_UPDATE);

		}
		dto.setNomatchs(apk_mapv_notmatchList.toArray(new String[0]));
	}

	/**
	 * 获取所有不能交叉使用的
	 * 处理逻辑：
	 * 1. 对于最新版本数据包所对应的大数据版本、获取该大数据版本下的所有城市数据包。
	 * 2. 对每个城市数据包的不匹配版本数据（not_matchl字段）进行合并汇总。
	 * 3. 返回汇总结果
	 * @param apkvmapvs
	 * @return
	 */
	private String[] getAllNoCross(NaviResourcePublish matchedPublish){
		String nocross =  (String) matchedPublish.getExtras().get("navi_resource_version_not_matchl");		
		return nocross == null ? null : nocross.split(",");
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.IMapUpdateService#queryMapData(com.autonavi.tsp.workbackend.dto.naviMap.MapDataParams)
	 */
	@Override
	public NaviMapDataDto queryMapData(MapDataParams mapDataParams) {

		NaviMapDataDto dto = new NaviMapDataDto();
		dto.setProcesstime(DateUtil.dateTimeToStr(new Date(), "yyyyMMddHHmmss"));
		if(StringUtil.isEmpty(mapDataParams.getSid())){
			return setErrorCode(dto, ExceptionCodeEnum.MU_SID_EMPTY);
		}
		if(StringUtil.isEmpty(mapDataParams.getApkv())){			
			return setErrorCode(dto, ExceptionCodeEnum.MU_PARAM_EMPTY_APKV);
		}
		if(StringUtil.isEmpty(mapDataParams.getSyscode())){
			return setErrorCode(dto, ExceptionCodeEnum.MU_PARAM_EMPTY_SYSCODE);
		}
		if(StringUtil.isEmpty(mapDataParams.getPid())){			
			return setErrorCode(dto, ExceptionCodeEnum.MU_PARAM_EMPTY_PID);
		}
		

		//check session
		try {
			sessionService.validateSession(mapDataParams.getSid());
		} catch (ValidationException e1) {
			log.error(e1.getMessage());
			dto.setResp_code(1002);
			dto.setResp_msg("用户没有权限或会话过期");
			dto.setIsError("true");
			return dto;
		}


		try {
			Map<String, String> mLocalCityVersions = getLocalCityVersionsMap(mapDataParams.getLocalCityVersions());

			ResVersionDto resVersionDto = new ResVersionDto();
			//获取最新版本基础资源包信息
			BaseResDto baseResDto = getBaseResData(mapDataParams);
			resVersionDto.setBaseres(baseResDto);

			//获取最新版本的城市地图包信息
			getAllMapvByApkv(resVersionDto, mapDataParams, mLocalCityVersions);


			dto.setResp_msg("success");
			dto.setResVersionDto(resVersionDto);
			dto.setIsError("false");
		}catch (CommonException e) {
			dto.setIsError("true");
			dto.setResp_code(e.getCode());
			dto.setResp_msg(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			dto.setIsError("true");
			dto.setResp_code(1010);
			dto.setResp_msg("服务器错误");
		}
		return dto;
	
	}

	/**
	 * @param dto
	 * @return
	 */
	private NaviMapDataDto setErrorCode(NaviMapDataDto dto, ExceptionCodeEnum errorCode) {
		dto.setResp_code(errorCode.getCode());
		dto.setResp_msg(errorCode.getMessage());
		dto.setIsError("true");
		return dto;
	}



}
/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.autonavi.tsp.atsp.core.PropertyUtil;
import com.autonavi.tsp.atsp.core.TxTestBase;
import com.autonavi.tsp.workbackend.constant.IOSVers;
import com.autonavi.tsp.workbackend.constant.NameValueEnumHelper;
import com.autonavi.tsp.workbackend.constant.NaviResPublishStatuses;
import com.autonavi.tsp.workbackend.constant.NaviResTypes;
import com.autonavi.tsp.workbackend.constant.PlatformIds;
import com.autonavi.tsp.workbackend.constant.ResDataTypes;
import com.autonavi.tsp.workbackend.constant.ResPublishTargetUserTypes;
import com.autonavi.tsp.workbackend.constant.ResUpdateTypes;
import com.autonavi.tsp.workbackend.constant.ResUpgradeTypes;
import com.autonavi.tsp.workbackend.dao.NaviMapCityInfoMapper;
import com.autonavi.tsp.workbackend.dao.NaviResourcePublishMapper;
import com.autonavi.tsp.workbackend.dao.NaviResourceVersionMapper;
import com.autonavi.tsp.workbackend.dao.NaviSystemMapper;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.NaviBaseResInfoDto;
import com.autonavi.tsp.workbackend.dto.NaviMapCityInfoDto;
import com.autonavi.tsp.workbackend.dto.NaviResourcePublishDto;
import com.autonavi.tsp.workbackend.dto.NaviResourceVersionDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.SimpleCityInfoDto;
import com.autonavi.tsp.workbackend.dto.filter.MapCityInfoFilter;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.model.NaviResourcePublishExample;
import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.model.NaviSystem;
import com.autonavi.tsp.workbackend.service.INaviBaseResInfoService;
import com.autonavi.tsp.workbackend.service.INaviMapCityInfoService;
import com.autonavi.tsp.workbackend.service.INaviResourcePublishService;
import com.autonavi.tsp.workbackend.service.INaviResourceVersionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ListUtil;

/**
 * @author mengxianming
 *
 * 2015年5月27日
 */
public class AutonaviResourceServiceImplTest extends TxTestBase{
	@Autowired
	private INaviResourceVersionService naviResourceVersionService;	
	@Autowired
	private INaviResourcePublishService naviResourcePublishService;
	@Autowired
	private INaviMapCityInfoService naviMapCityInfoService;
	@Autowired
	private INaviBaseResInfoService naviBaseResInfoService;
	
	
	@Autowired
	NaviResourcePublishMapper naviResourcePublishMapper;
	@Autowired
	NaviResourceVersionMapper naviResourceVersionMapper;
	@Autowired
	NaviMapCityInfoMapper naviMapCityInfoMapper;
	@Autowired
	NaviSystemMapper naviSystemMapper;


	private NaviResourceVersionDto prepareResourceVersion(NaviResTypes resType) throws Exception{		
		NaviResourceVersionDto versionDto = new NaviResourceVersionDto();
		versionDto.setResVersion("testver1.0.0");
		versionDto.setMemo("test resource version");

		String resourceId = naviResourceVersionService.insert(versionDto);
		NaviResourceVersion ver = naviResourceVersionMapper.selectByPrimaryKey(resourceId);
		Assert.assertNotNull(ver);

		NaviResourceVersionDto resverdto = new NaviResourceVersionDto();
		resverdto.setWrapper(ver);
		return resverdto;
	}

	private NaviSystem prepareSyscode(){

		NaviSystem record = new NaviSystem();
		record.setSyscode("-123");
		record.setSysname("Android");
		record.setStatus("0");//able status

		naviSystemMapper.insert(record);
		NaviSystem dbrc = naviSystemMapper.selectByPrimaryKey(record.getSyscode());
		Assert.assertNotNull(dbrc);
		return dbrc;
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#addCityResource(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List)}.
	 * @throws Exception 
	 */
	@Test
	public void testAddCityResource_RelatedCities() throws Exception {

		List<NaviMapCityInfoDto> lists = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		lists.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		lists.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Kunshan");
		lists.add(dto3);		
		//set related city
		dto2.setRelatedCities(dto3.getAdcode());
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));

		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);

		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, lists);

		//reslect
		PageDto<NaviMapCityInfoDto> listtmp = naviMapCityInfoService.queryCityResInfo(toListCriteria(0, 10, resver.getId(), null));
		List<NaviMapCityInfoDto> cityList = ListUtil.orderBy(listtmp.getResultList(), "adcode", "asc");
		List<NaviMapCityInfoDto> srcCityList = ListUtil.orderBy(lists, "adcode", "asc");
		//compare
		for(int i = 0; i < srcCityList.size(); i++){
			Assert.assertEquals(srcCityList.get(i).getCity(), cityList.get(i).getCity());
			Assert.assertEquals(srcCityList.get(i).getRelatedCities(), cityList.get(i).getRelatedCities());
		}

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#addBaseResource(Long, String, String, String, String, List)
	 * @throws Exception 
	 */
	@Test
	public void testAddBaseResource_addPidOsverProps() throws Exception {

		List<NaviBaseResInfoDto> list = new ArrayList<NaviBaseResInfoDto>();
		NaviBaseResInfoDto dto = new NaviBaseResInfoDto();
		dto.setPkgname("testbaseres.zip");
		dto.setPid(PlatformIds.IOS.getValue());
		dto.setOsver(IOSVers.IOS5.getValue());
		list.add(dto);
		NaviBaseResInfoDto dto2 = new NaviBaseResInfoDto();
		dto2.setPkgname("testbaseres2.zip");
		dto2.setPid(PlatformIds.IOS.getValue());
		dto2.setOsver(IOSVers.IOS6.getValue());
		list.add(dto2);

		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.BASERES);


		naviBaseResInfoService.addBaseResource(resver, list);

		//reslect
		PageDto<NaviBaseResInfoDto> listtmp = naviBaseResInfoService.queryBaseResInfo(1, 10, resver.getId());
		List<NaviBaseResInfoDto> basereslist = ListUtil.orderBy(listtmp.getResultList(), "pkgname", "asc");
		List<NaviBaseResInfoDto> srcBaseResList = ListUtil.orderBy(list, "pkgname", "asc");
		//compare
		for(int i = 0; i < srcBaseResList.size(); i++){
			Assert.assertEquals(srcBaseResList.get(i).getPid(), basereslist.get(i).getPid());
			Assert.assertEquals(srcBaseResList.get(i).getOsver(), basereslist.get(i).getOsver());
		}

	}

	private ListCriteria<MapCityInfoFilter> toListCriteria(Integer pageNO, Integer pageSize, String resourceVersionId,
			String keyword) {
		ListCriteria<MapCityInfoFilter> criteria = new ListCriteria<MapCityInfoFilter>();
		criteria.setPageIndex(pageNO);
		criteria.setPageSize(pageSize);
		criteria.setFilter(new MapCityInfoFilter());
		criteria.getFilter().setResourceVersionId(resourceVersionId);
		criteria.getFilter().setKeyword(keyword);
		return criteria;
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#addResourcePublish(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testAddResourcePublish_WhiteList() throws Exception {
		// prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		NaviSystem syscode = prepareSyscode();

		NaviResourcePublishDto publish = new NaviResourcePublishDto();
		publish.setResourceVersionId(resver.getId());
		publish.setSyscode(syscode.getSyscode());
		publish.setApkVersion("AOS1.00");
		publish.setUpgradeType(ResUpgradeTypes.NO.getValue());
		publish.setTargetUserType(ResPublishTargetUserTypes.WHITELIST.getValue());

		// execute target
		naviResourcePublishService.insert(publish);

		// reselect
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		// assert
		Assert.assertEquals(1, list.size());
		Assert.assertArrayEquals(PropertyUtil.selectProps(list.get(0), "resourceVersionId",
				"syscode", "apkVersion", "upgradeType", "downloadType"),
				PropertyUtil.selectProps(publish, "resourceVersionId",
						"syscode", "apkVersion", "upgradeType", "targetUserType"));

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#queryResourcePublish(int, int)}.
	 * @throws Exception 
	 */
	@Test
	public void testQueryResourcePublish_WhiteList() throws Exception {
		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		NaviSystem syscode = prepareSyscode();

		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);

		//execute target
		//get all
		PageDto<NaviResourcePublishDto> pageList = naviResourcePublishService.queryDetailedList(1, 10000);
		Assert.assertNotNull(pageList.getResultList());

		for (NaviResourcePublishDto dto : pageList.getResultList()) {
			if (dto.getMapVersion().equals(resver.getResVersion())) {
				Object[] expected = PropertyUtil.selectProps(dto, "syscode", "apkVersion", "upgradeType");
				Object[] actual = PropertyUtil.selectProps(publishDto, "syscode", "apkVersion", "upgradeType");
				// do assert
				Assert.assertArrayEquals(expected, actual);
				//发布记录列表中增加显示【适用用户】信息。
				Assert.assertEquals(dto.getTargetUserType(), targetUserType.getValue());;
				Assert.assertEquals(dto.getTargetUserTypeName(), targetUserType.getName());;

				return;
			}
		}

		Assert.fail();
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#queryResourcePublish(int, int)}.
	 * @throws Exception 
	 */
	@Test
	public void testQueryResourcePublish_NoResCount() throws Exception {
		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		NaviSystem syscode = prepareSyscode();

		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);

		//execute target
		//get all
		PageDto<NaviResourcePublishDto> pageList = naviResourcePublishService.queryDetailedList(1, 1000);
		Assert.assertNotNull(pageList.getResultList());

		for (NaviResourcePublishDto dto : pageList.getResultList()) {
			if (dto.getMapVersion().equals(resver.getResVersion())) {				
				// do assert
				//文件数信息不在需要在发布记录列表中显示
				Assert.assertEquals(PropertyUtils.getPropertyDescriptor(dto, "resCount"), null);
				return;
			}
		}

		Assert.fail();
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourcePublish(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteResourcePublish_NoPublishStatus() throws Exception {
		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		NaviSystem syscode = prepareSyscode();

		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);

		//reselect
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		Assert.assertEquals(list.size(), 1);
		NaviResourcePublish publish = list.get(0);
		//make sure it is NO publish status
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.NO.getValue());
		//execute target
		naviResourcePublishService.deleteByKey(new DeleteKeyDto<Long>(publish.getId()));
		//reselect
		publish = naviResourcePublishMapper.selectByPrimaryKey(publish.getId());
		Assert.assertNull(publish);

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourcePublish(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteResourcePublish_PublishStatus() throws Exception {
		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		NaviSystem syscode = prepareSyscode();

		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);

		//reselect
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		Assert.assertEquals(list.size(), 1);
		Long publishId = list.get(0).getId();

		//change status to publish
		naviResourcePublishService.updateResourcePublishStatus(null, publishId, NaviResPublishStatuses.YES.getValue());
		//reselect
		NaviResourcePublish publish = naviResourcePublishMapper.selectByPrimaryKey(publishId);
		//make sure it is publish status
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.YES.getValue());		

		//execute target
		try {
			naviResourcePublishService.deleteByKey(new DeleteKeyDto<Long>(publish.getId()));
		} catch (CommonException e) {
			Assert.assertEquals(e.getCode(), ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW.getCode());
		}
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourcePublish(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteResourcePublish_StopStatus() throws Exception {
		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		NaviSystem syscode = prepareSyscode();

		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);

		//reselect
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		Assert.assertEquals(list.size(), 1);
		Long publishId = list.get(0).getId();

		//change status to stop
		naviResourcePublishService.updateResourcePublishStatus(null, publishId, NaviResPublishStatuses.STOP.getValue());
		//reselect
		NaviResourcePublish publish = naviResourcePublishMapper.selectByPrimaryKey(publishId);
		//make sure it is publish status
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.STOP.getValue());		

		//execute target
		naviResourcePublishService.deleteByKey(new DeleteKeyDto<Long>(publish.getId()));
		//reselect
		publish = naviResourcePublishMapper.selectByPrimaryKey(publish.getId());
		Assert.assertNull(publish);
	}


	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourceVersion(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeleteResourceVersion_MapResNoPublish() throws Exception {
		List<NaviMapCityInfoDto> cityList = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		cityList.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		cityList.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Kunshan");
		cityList.add(dto3);
		//set related city
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));	

		//prepare resource version
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);		

		//add city 
		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, cityList);

		//make sure prepare data is ok
		PageDto cityList2 = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null));
		Assert.assertEquals(cityList2.getResultList().size(), cityList.size());
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		//make sure no publis records
		Assert.assertEquals(list.size(), 0);

		//execute target
		naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));

		//reselect
		Assert.assertNull(naviResourceVersionMapper.selectByPrimaryKey(resver.getId()));
		Assert.assertEquals(naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null)).getTotal(), 0);
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourceVersion(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeleteResourceVersion_MapResNoPublishStatus() throws Exception {
		List<NaviMapCityInfoDto> cityList = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		cityList.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		cityList.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Kunshan");
		cityList.add(dto3);
		//set related city
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));	

		//prepare resource version
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		//prepare resource publish and its status is NO publish status.
		NaviSystem syscode = prepareSyscode();
		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);


		//add city 
		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, cityList);

		//make sure prepare data is ok
		PageDto cityList2 = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null));
		Assert.assertEquals(cityList2.getResultList().size(), cityList.size());
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		//make sure one publish record and its status is NO publish status
		Assert.assertEquals(list.size(), 1);
		Assert.assertEquals(list.get(0).getStatus(), NaviResPublishStatuses.NO.getValue());

		//execute target
		naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));

		//reselect
		Assert.assertNull(naviResourceVersionMapper.selectByPrimaryKey(resver.getId()));
		Assert.assertEquals(naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null)).getTotal(), 0);
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourceVersion(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeleteResourceVersion_MapResPublishOrStopStatus() throws Exception {
		List<NaviMapCityInfoDto> cityList = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		cityList.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		cityList.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Kunshan");
		cityList.add(dto3);
		//set related city
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));	

		//prepare resource version
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		//prepare resource publish and its status is publish status.
		NaviSystem syscode = prepareSyscode();
		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);



		//add city 
		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, cityList);

		//make sure prepare data is ok
		PageDto cityList2 = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null));
		Assert.assertEquals(cityList2.getResultList().size(), cityList.size());
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		//make sure one publish record and its status is publish status
		Assert.assertEquals(list.size(), 1);
		NaviResourcePublish publish = list.get(0);
		publish.setStatus(NaviResPublishStatuses.YES.getValue());
		naviResourcePublishMapper.updateByPrimaryKeySelective(publish);
		publish = naviResourcePublishMapper.selectByPrimaryKey(publish.getId());
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.YES.getValue());

		//execute target
		try {
			naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));
		} catch (CommonException e) {
			Assert.assertEquals(e.getCode(), ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW.getCode());
			Assert.assertEquals(e.getMessage(), "只有该地图资源关联的全部发布单的状态为【未发布】状态才允许删除。");			
		}

		//change publish status to STOP
		publish.setStatus(NaviResPublishStatuses.STOP.getValue());
		naviResourcePublishMapper.updateByPrimaryKeySelective(publish);
		publish = naviResourcePublishMapper.selectByPrimaryKey(publish.getId());
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.STOP.getValue());
		//execute target
		try {
			naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));
		} catch (CommonException e) {
			Assert.assertEquals(e.getCode(), ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW.getCode());
			Assert.assertEquals(e.getMessage(), "只有该地图资源关联的全部发布单的状态为【未发布】状态才允许删除。");	
		}

	}


	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourceVersion(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeleteResourceVersion_BaseResNoPublish() throws Exception {

		List<NaviBaseResInfoDto> baseResList = new ArrayList<NaviBaseResInfoDto>();
		NaviBaseResInfoDto model = new NaviBaseResInfoDto();
		model.setSize(1234L);
		model.setZipSize(123L);
		model.setDpiName("hdpi");
		model.setPkgname("ChinaMap");
		baseResList.add(model);


		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.BASERES);


		//add base resource
		naviBaseResInfoService.addBaseResource(resver, baseResList);

		//make sure prepare data is ok		
		PageDto baseResList2 = naviBaseResInfoService.queryBaseResInfo(1, 0, resver.getId());
		Assert.assertEquals(baseResList2.getResultList().size(), baseResList.size());
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		//make sure no publis records
		Assert.assertEquals(list.size(), 0);

		//execute target
		naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));

		//reselect
		Assert.assertNull(naviResourceVersionMapper.selectByPrimaryKey(resver.getId()));
		Assert.assertEquals(naviBaseResInfoService.queryBaseResInfo(1, 0, resver.getId()).getTotal(), 0);
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourceVersion(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeleteResourceVersion_BaseResNoPublishStatus() throws Exception {

		List<NaviBaseResInfoDto> baseResList = new ArrayList<NaviBaseResInfoDto>();
		NaviBaseResInfoDto model = new NaviBaseResInfoDto();
		model.setSize(1234L);
		model.setZipSize(123L);
		model.setDpiName("hdpi");
		model.setPkgname("ChinaMap");
		baseResList.add(model);


		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.BASERES);
		//prepare resource publish and its status is NO publish status.
		NaviSystem syscode = prepareSyscode();
		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);


		//add base resource
		naviBaseResInfoService.addBaseResource(resver, baseResList);

		//make sure prepare data is ok		
		PageDto baseResList2 = naviBaseResInfoService.queryBaseResInfo(1, 0, resver.getId());
		Assert.assertEquals(baseResList2.getResultList().size(), baseResList.size());
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		//make sure one publish record and its status is NO publish status
		Assert.assertEquals(list.size(), 1);
		NaviResourcePublish publish = list.get(0);
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.NO.getValue());

		//execute target
		naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));

		//reselect
		Assert.assertNull(naviResourceVersionMapper.selectByPrimaryKey(resver.getId()));
		Assert.assertEquals(naviBaseResInfoService.queryBaseResInfo(1, 0, resver.getId()).getTotal(), 0);
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#deleteResourceVersion(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testDeleteResourceVersion_BaseResPublishOrStopStatus() throws Exception {

		List<NaviBaseResInfoDto> baseResList = new ArrayList<NaviBaseResInfoDto>();
		NaviBaseResInfoDto model = new NaviBaseResInfoDto();
		model.setSize(1234L);
		model.setZipSize(123L);
		model.setDpiName("hdpi");
		model.setPkgname("ChinaMap");
		baseResList.add(model);


		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.BASERES);
		//prepare resource publish and its status is NO publish status.
		NaviSystem syscode = prepareSyscode();
		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);


		//add base resource
		naviBaseResInfoService.addBaseResource(resver, baseResList);

		//make sure prepare data is ok		
		PageDto baseResList2 = naviBaseResInfoService.queryBaseResInfo(1, 0, resver.getId());
		Assert.assertEquals(baseResList2.getResultList().size(), baseResList.size());
		NaviResourcePublishExample example = new NaviResourcePublishExample();
		example.createCriteria().andResourceVersionIdEqualTo(resver.getId());
		List<NaviResourcePublish> list = naviResourcePublishMapper.selectByExample(example);
		//make sure one publish record and its status is publish status
		Assert.assertEquals(list.size(), 1);
		NaviResourcePublish publish = list.get(0);
		publish.setStatus(NaviResPublishStatuses.YES.getValue());
		naviResourcePublishMapper.updateByPrimaryKeySelective(publish);
		publish = naviResourcePublishMapper.selectByPrimaryKey(publish.getId());
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.YES.getValue());


		//execute target
		try {
			naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));
		} catch (CommonException e) {
			Assert.assertEquals(e.getCode(), ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW.getCode());
		}

		//change publish status to STOP
		publish.setStatus(NaviResPublishStatuses.STOP.getValue());
		naviResourcePublishMapper.updateByPrimaryKeySelective(publish);
		publish = naviResourcePublishMapper.selectByPrimaryKey(publish.getId());
		Assert.assertEquals(publish.getStatus(), NaviResPublishStatuses.STOP.getValue());
		//execute target
		try {
			naviResourceVersionService.deleteByKey(new DeleteKeyDto<String>(resver.getId()));
		} catch (CommonException e) {
			Assert.assertEquals(e.getCode(), ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW.getCode());
		}

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#getConstantValues(java.lang.String)}.
	 * @throws CommonException 
	 */
	@Test
	public void testGetConstantValues_ResDownloadTypeConstant() throws CommonException {
		String fullClassName = "com.autonavi.tsp.workbackend.constants.ResDownloadTypeConstant";
		try {
			NameValueEnumHelper.getConstantValues(fullClassName);
		} catch (CommonException e) {
			Assert.assertEquals(e.getCode(), -1);
		}
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#queryResource(int, int)}.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testQueryResource_Extras() throws Exception {
		List<NaviMapCityInfoDto> cityList = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		cityList.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		cityList.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Kunshan");
		cityList.add(dto3);
		//set related city
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));		

		//prepare resource version
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);
		//prepare resource publish and its status is NO publish status.
		NaviSystem syscode = prepareSyscode();
		NaviResourcePublishDto publishDto = new NaviResourcePublishDto();
		publishDto.setResourceVersionId(resver.getId());
		publishDto.setSyscode(syscode.getSyscode());
		publishDto.setApkVersion("AOS1.00");
		ResUpgradeTypes upgradeType = ResUpgradeTypes.NO;
		publishDto.setUpgradeType(upgradeType.getValue());
		ResPublishTargetUserTypes targetUserType = ResPublishTargetUserTypes.WHITELIST;
		publishDto.setTargetUserType(targetUserType.getValue());

		naviResourcePublishService.insert(publishDto);

		//add city 
		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, cityList);
		

		//execute target
		//get all
		List versions = naviResourceVersionService.queryResourceVersion(1, 10000).getResultList();
		Assert.assertTrue(versions.size() > 0);
		for(int i = 0; i < versions.size();i++){
			NaviResourceVersionDto ver = (NaviResourceVersionDto) versions.get(i);
			if(ver.getId().equals(resver.getId())){
				Assert.assertEquals(ver.getResCount().intValue(), cityList.size());
				return;
			}
		}

		Assert.fail();

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#queryRelatedCities(String)
	 * @throws Exception 
	 */
	@Test
	public void testQueryRelatedCities() throws Exception {

		List<NaviMapCityInfoDto> lists = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		lists.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		lists.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Kunshan");
		lists.add(dto3);
		//set related city
		dto2.setRelatedCities(dto3.getAdcode());
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));	

		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);

		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, lists);

		//reslect		
		List<SimpleCityInfoDto> listtmp = naviMapCityInfoService.queryRelatedCities(resver.getId());
		Assert.assertEquals(listtmp.size(), 1);

		//compare
		Assert.assertEquals(listtmp.get(0).getAdcode(), dto2.getAdcode());
		Assert.assertEquals(listtmp.get(0).getRelatedCities().get(0).getAdcode(), dto2.getRelatedCities());

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#queryCityResInfo()
	 * @throws Exception 
	 */
	@Test
	public void testQueryCityResInfo_Keyword() throws Exception {

		List<NaviMapCityInfoDto> lists = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		lists.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		lists.add(dto2);
		NaviMapCityInfoDto dto3 = new NaviMapCityInfoDto();
		dto3.setAdcode("30010");
		dto3.setProvince("Jiangsu");
		dto3.setCity("Nanjing");
		lists.add(dto3);
		//set related city
		String relatedCityMap = JSON.toJSONString(Collections.singletonMap(dto2.getAdcode(), new String[]{dto3.getAdcode()}));	

		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);

		naviMapCityInfoService.updateBatch(null, resver.getId(), relatedCityMap, lists);

		//NO keyword case
		List<NaviMapCityInfoDto> listtmp = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null)).getResultList();
		Assert.assertEquals(listtmp.size(), 3);

		List<NaviMapCityInfoDto> list1 = ListUtil.orderBy(listtmp, "adcode", null);
		List<NaviMapCityInfoDto> list2 = ListUtil.orderBy(Arrays.asList(dto, dto2, dto3), "adcode", null);
		//compare
		for(int i =0; i < list1.size(); i++){
			Assert.assertEquals(list1.get(i).getAdcode(), list2.get(i).getAdcode());
			Assert.assertEquals(list1.get(i).getCity(), list2.get(i).getCity());
		}


		//HAS keyword case
		listtmp = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), "jing")).getResultList();
		Assert.assertEquals(listtmp.size(), 2);

		list1 = ListUtil.orderBy(listtmp, "adcode", null);
		list2 = ListUtil.orderBy(Arrays.asList(dto, dto3), "adcode", null);
		//compare
		for(int i =0; i < list1.size(); i++){
			Assert.assertEquals(list1.get(i).getAdcode(), list2.get(i).getAdcode());
			Assert.assertEquals(list1.get(i).getCity(), list2.get(i).getCity());
		}

		//Test Sort desc
		ListCriteria<MapCityInfoFilter> criteria = toListCriteria(1, 0, resver.getId(), null);
		criteria.setSortField("adcode");
		criteria.setSortOrder("desc");
		list1 = naviMapCityInfoService.queryCityResInfo(criteria).getResultList();
		Assert.assertEquals(list1.size(), 3);


		list2 = ListUtil.orderBy(Arrays.asList(dto, dto2, dto3), "adcode", "desc");
		//compare
		for(int i =0; i < list1.size(); i++){
			Assert.assertEquals(list1.get(i).getAdcode(), list2.get(i).getAdcode());
			Assert.assertEquals(list1.get(i).getCity(), list2.get(i).getCity());
		}

		//Test Sort asc
		criteria = toListCriteria(1, 0, resver.getId(), null);
		criteria.setSortField("adcode");
		criteria.setSortOrder("asc");
		list1 = naviMapCityInfoService.queryCityResInfo(criteria).getResultList();
		Assert.assertEquals(list1.size(), 3);


		list2 = ListUtil.orderBy(Arrays.asList(dto, dto2, dto3), "adcode", "asc");
		//compare
		for(int i =0; i < list1.size(); i++){
			Assert.assertEquals(list1.get(i).getAdcode(), list2.get(i).getAdcode());
			Assert.assertEquals(list1.get(i).getCity(), list2.get(i).getCity());
		}

		//Test Sort default
		criteria = toListCriteria(1, 0, resver.getId(), null);
		criteria.setSortField("adcode");
		list1 = naviMapCityInfoService.queryCityResInfo(criteria).getResultList();
		Assert.assertEquals(list1.size(), 3);


		list2 = ListUtil.orderBy(Arrays.asList(dto, dto2, dto3), "adcode", "asc");
		//compare
		for(int i =0; i < list1.size(); i++){
			Assert.assertEquals(list1.get(i).getAdcode(), list2.get(i).getAdcode());
			Assert.assertEquals(list1.get(i).getCity(), list2.get(i).getCity());
		}

	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.AutonaviResourceServiceImpl#batchUpdateCityResInfo()
	 * @throws Exception 
	 */
	@Test
	public void testBatchUpdateCityResInfo() throws Exception {

		List<NaviMapCityInfoDto> cityList = new ArrayList<NaviMapCityInfoDto>();
		NaviMapCityInfoDto dto = new NaviMapCityInfoDto();
		dto.setAdcode("10000");
		dto.setProvince("Beijing");
		dto.setCity("Beijing");
		dto.setDataType(ResDataTypes.COMPLETE.getValue());
		dto.setUpdateType(ResUpdateTypes.FULL.getValue());
		cityList.add(dto);
		NaviMapCityInfoDto dto2 = new NaviMapCityInfoDto();
		dto2.setAdcode("20000");
		dto2.setProvince("Shanghai");
		dto2.setCity("Shanghai");
		dto2.setDataType(ResDataTypes.COMPLETE.getValue());
		dto2.setUpdateType(ResUpdateTypes.FULL.getValue());
		cityList.add(dto2);


		//prepare data
		NaviResourceVersionDto resver = prepareResourceVersion(NaviResTypes.CITYRES);

		naviMapCityInfoService.updateBatch(null, resver.getId(), null, cityList);
		cityList = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null)).getResultList();


		String updatedUpdateType = "-";

		//ALL update case
		naviMapCityInfoService.batchUpdateCityResInfo(resver.getId(), null, updatedUpdateType);
		//reslect to compare
		List<NaviMapCityInfoDto> listtmp = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), null)).getResultList();
		Assert.assertEquals(listtmp.size(), cityList.size());
		//compare
		for(NaviMapCityInfoDto city : listtmp){
			Assert.assertEquals(city.getUpdateType(), updatedUpdateType);
		}

		//PART update case
		naviMapCityInfoService.batchUpdateCityResInfo(resver.getId(), Arrays.asList(cityList.get(0).getId()), updatedUpdateType);
		//reslect to compare
		listtmp = naviMapCityInfoService.queryCityResInfo(toListCriteria(1, 0, resver.getId(), dto.getCity())).getResultList();
		Assert.assertEquals(listtmp.size(), 1);
		//compare
		for(NaviMapCityInfoDto city : listtmp){
			Assert.assertEquals(city.getUpdateType(), updatedUpdateType);
		}


		//INVLAID targetid case
		Long NOTEXISTID = -1111L;
		boolean ok  = false;
		try {
			naviMapCityInfoService.batchUpdateCityResInfo(resver.getId(), Arrays.asList(NOTEXISTID), updatedUpdateType);
		} catch (CommonException e) {
			ok = true;
			Assert.assertEquals(e.getCode(), ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode());
		}
		Assert.assertTrue(ok);

	}

}

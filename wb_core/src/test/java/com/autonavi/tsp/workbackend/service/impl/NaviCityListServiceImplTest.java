/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.autonavi.tsp.atsp.core.TxTestBase;
import com.autonavi.tsp.workbackend.dto.NaviCityListDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.service.INaviCityListService;
import com.autonavi.tsp.workbackend.util.ListUtil;

/**
 * @author mengxianming
 *
 * 2015年5月28日
 */
public class NaviCityListServiceImplTest extends TxTestBase{
	@Autowired
	INaviCityListService service;

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#getNaviCityListMapper()}.
	 */
	@Test
	public void testGetNaviCityListMapper() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#setNaviCityListMapper(com.autonavi.tsp.workbackend.dao.NaviCityListMapper)}.
	 */
	@Test
	public void testSetNaviCityListMapper() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryAllNaviCityList()}.
	 */
	@Test
	public void testQueryAllNaviCityList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryNaviCityList(int, int)}.
	 */
	@Test
	public void testQueryNaviCityList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#addNaviCityList(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddNaviCityList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#updateNaviCityList(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateNaviCityListStringStringStringStringStringStringStringStringStringStringStringStringStringStringStringString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#deleteById(java.lang.String)}.
	 */
	@Test
	public void testDeleteById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#insertNaviCityList(java.util.List)}.
	 */
	@Test
	public void testInsertNaviCityList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#insertNaviCity(com.autonavi.tsp.workbackend.model.NaviCityList)}.
	 */
	@Test
	public void testInsertNaviCity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#updateNaviCityList(com.autonavi.tsp.workbackend.model.NaviCityList)}.
	 */
	@Test
	public void testUpdateNaviCityListNaviCityList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryProvince(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String)}.
	 */
	@Test
	public void testQueryProvince() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryCityList(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testQueryCityList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryCityListById(java.lang.String)}.
	 */
	@Test
	public void testQueryCityListById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#selectByMapv(java.lang.String)}.
	 */
	@Test
	public void testSelectByMapv() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryNewVersion()}.
	 */
	@Test
	public void testQueryNewVersion() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#deleteByMapv(java.lang.String)}.
	 */
	@Test
	public void testDeleteByMapv() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryCityListLikeName(java.lang.String, java.lang.String)}.
	 * @throws CommonException 
	 */
	@Test
	public void testQueryCityListLikeName() throws CommonException {
		List<NaviCityList> list = new ArrayList<NaviCityList>();
		String resVersion = "20";
		NaviCityList dto = new NaviCityList();		
		dto.setResVersion(resVersion);
		dto.setAdcode("001");
		dto.setNameEn("Nanking");
		dto.setNameZh("南京");
		list.add(dto);
		
		NaviCityList dto2 = new NaviCityList();
		dto2.setResVersion(resVersion);
		dto2.setAdcode("002");
		dto2.setNameEn("Nanning");
		dto2.setNameZh("南宁");
		list.add(dto2);
		
		NaviCityList dto3 = new NaviCityList();
		dto3.setResVersion(resVersion);
		dto3.setAdcode("000");
		dto3.setNameEn("Peking");
		dto3.setNameZh("北京");
		list.add(dto3);
		
		//prepare city data
		service.insertNaviCityList(list);
		
		//execute target
		//nameZh case
		List<NaviCityListDto> naviCityList2 = service.queryCityListLikeName(resVersion, "南");
		Assert.assertEquals(naviCityList2.size(), 2);
		List<NaviCityList> naviCityList = ListUtil.orderBy(Arrays.asList(dto, dto2), "nameZh", "desc");
		naviCityList2 = ListUtil.orderBy(naviCityList2, "nameZh", "desc");
		for(int i = 0; i < naviCityList2.size(); i++){
			Assert.assertEquals(naviCityList2.get(i).getNameZh(), naviCityList.get(i).getNameZh());
		}
		
		//nameEn case
		naviCityList2 = service.queryCityListLikeName(resVersion, "king");
		Assert.assertEquals(naviCityList2.size(), 2);
		naviCityList = ListUtil.orderBy(Arrays.asList(dto, dto3), "nameEn", "desc");
		naviCityList2 = ListUtil.orderBy(naviCityList2, "nameEn", "desc");
		for(int i = 0; i < naviCityList2.size(); i++){
			Assert.assertEquals(naviCityList2.get(i).getNameEn(), naviCityList.get(i).getNameEn());
		}
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviCityListServiceImpl#queryCityListLikeName(java.lang.String, java.lang.String)}.
	 * @throws CommonException 
	 */
	@Test
	public void testQueryCityListLikeName_EmptyName() throws CommonException {
		List<NaviCityList> list = new ArrayList<NaviCityList>();
		String resVersion = "20";
		NaviCityList dto = new NaviCityList();		
		dto.setResVersion(resVersion);
		dto.setAdcode("001");
		dto.setNameEn("Nanking");
		dto.setNameZh("南京");
		list.add(dto);
		
		NaviCityList dto2 = new NaviCityList();
		dto2.setResVersion(resVersion);
		dto2.setAdcode("002");
		dto2.setNameEn("Nanning");
		dto2.setNameZh("南宁");
		list.add(dto2);
		
		NaviCityList dto3 = new NaviCityList();
		dto3.setResVersion(resVersion);
		dto3.setAdcode("000");
		dto3.setNameEn("Peking");
		dto3.setNameZh("北京");
		list.add(dto3);
		
		//prepare city data
		service.insertNaviCityList(list);
		
		//execute target
		List<NaviCityListDto> naviCityList2 = service.queryCityListLikeName(resVersion, null);
		Assert.assertEquals(naviCityList2.size(), list.size());
		List<NaviCityList> naviCityList = ListUtil.orderBy(list, "nameZh", "desc");
		naviCityList2 = ListUtil.orderBy(naviCityList2, "nameZh", "desc");
		for(int i = 0; i < naviCityList2.size(); i++){
			Assert.assertEquals(naviCityList2.get(i).getResVersion(), naviCityList.get(i).getResVersion());
			Assert.assertEquals(naviCityList2.get(i).getNameZh(), naviCityList.get(i).getNameZh());
		}

	}

}

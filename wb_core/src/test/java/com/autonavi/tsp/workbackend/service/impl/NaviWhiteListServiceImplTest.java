/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.autonavi.tsp.atsp.core.PropertyUtil;
import com.autonavi.tsp.atsp.core.TxTestBase;
import com.autonavi.tsp.workbackend.constant.NaviWhiteList_TYPE;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.ListDto;
import com.autonavi.tsp.workbackend.dto.NaviWhiteListDto;
import com.autonavi.tsp.workbackend.service.INaviWhiteListService;
import com.autonavi.tsp.workbackend.util.ListUtil;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
public class NaviWhiteListServiceImplTest extends TxTestBase{
	@Autowired
	INaviWhiteListService service;

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviWhiteListServiceImpl#insert(com.autonavi.tsp.workbackend.dto.NaviWhiteListDto)}.
	 * @throws Exception 
	 */
	@Test
	public void testInsert() throws Exception {
		NaviWhiteListDto dto = new NaviWhiteListDto();
		dto.setType(NaviWhiteList_TYPE.USERID.getValue());
		dto.setUserId("testUserId");
		dto.setImei("123456123456123");
		Long key = service.insert(dto);
	
		Map<String, Object> entityMap = jdbcTemplate.queryForMap("select * from navi_white_list where id=?", key);
		Assert.assertNotNull(entityMap);
		Assert.assertArrayEquals(PropertyUtil.selectProps(dto, "id", "userId", "imei"), PropertyUtil.selectProps(entityMap, "ID", "user_id", "imei"));
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviWhiteListServiceImpl#selectByKey(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectByKey() throws Exception {
		NaviWhiteListDto dto = new NaviWhiteListDto();
		dto.setType(NaviWhiteList_TYPE.USERID.getValue());
		dto.setUserId("testUserId");
		dto.setImei("123456123456123");
		Long key = service.insert(dto);
		
		NaviWhiteListDto dto2 = service.selectByKey(key);
		Assert.assertNotNull(dto2);
		Assert.assertArrayEquals(PropertyUtil.selectProps(dto, "id", "userId", "imei"), PropertyUtil.selectProps(dto2, "id", "userId", "imei"));
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviWhiteListServiceImpl#deleteByKey(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteByKey() throws Exception {
		NaviWhiteListDto dto = new NaviWhiteListDto();
		dto.setType(NaviWhiteList_TYPE.USERID.getValue());
		dto.setUserId("testUserId");
		dto.setImei("123456123456123");
		Long key = service.insert(dto);
		
		service.deleteByKey(new DeleteKeyDto<Long>(dto.getKey()));
		//reselect to check
		List<Map<String, Object>> entityList = jdbcTemplate.queryForList("select * from navi_white_list where id=?", key);
		Assert.assertTrue(entityList == null || entityList.size() == 0);
		
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviWhiteListServiceImpl#update(com.autonavi.tsp.workbackend.dto.NaviWhiteListDto)}.
	 * @throws Exception 
	 */
	@Test
	public void testUpdate() throws Exception {
		NaviWhiteListDto dto = new NaviWhiteListDto();
		dto.setType(NaviWhiteList_TYPE.USERID.getValue());
		dto.setUserId("testUserId");
		dto.setImei("123456123456123");
		Long key = service.insert(dto);
		
		//prepare update dto
		dto.setUserId("updated userId");
		service.update(dto);
		
		//reslect to check
		Map<String, Object> entityMap = jdbcTemplate.queryForMap("select * from navi_white_list where id=?", key);
		Assert.assertNotNull(entityMap);
		Assert.assertArrayEquals(PropertyUtil.selectProps(dto, "id", "userId", "imei"), PropertyUtil.selectProps(entityMap, "ID", "user_id", "imei"));
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviWhiteListServiceImpl#insertBatch}.
	 * @throws Exception 
	 */
	@Test
	public void testInsertBatch() throws Exception {
		ListDto<NaviWhiteListDto> list = new ListDto<NaviWhiteListDto>();
		NaviWhiteListDto dto = new NaviWhiteListDto();
		dto.setType(NaviWhiteList_TYPE.USERID.getValue());
		dto.setUserId("testUserId");
		dto.setImei("123456123456123");
		list.add(dto);
		NaviWhiteListDto dto2 = new NaviWhiteListDto();
		dto2.setType(NaviWhiteList_TYPE.USERID.getValue());
		dto2.setUserId("testUserId2");
		dto.setImei("2222222222");
		list.add(dto2);
		
		List<Long> keys = service.insertBatch(list);
		Assert.assertEquals(keys.size(), list.size());
		
		//reslect to check
		List<Map<String, Object>> list2 = jdbcTemplate.queryForList(
				String.format("select * from navi_white_list where id in (%s)", StringUtils.join(keys.toArray(), ",")));
		
		Assert.assertEquals(list.size(), list2.size());
		ListUtil.orderBy(list, "userId", "asc");
		ListUtil.orderBy(list2, "user_id", "asc");
		for(int i = 0; i < list.size(); i++){
			
			Assert.assertArrayEquals(PropertyUtil.selectProps(list.get(i), "id", "userId", "imei"),
					PropertyUtil.selectProps(list2.get(i), "ID", "user_id", "imei"));
		}
		
	}

}

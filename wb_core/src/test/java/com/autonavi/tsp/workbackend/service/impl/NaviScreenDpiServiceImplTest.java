/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.autonavi.tsp.atsp.core.TxTestBase;
import com.autonavi.tsp.workbackend.constant.PlatformIds;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.NaviScreenDpiDto;
import com.autonavi.tsp.workbackend.service.INaviScreenDpiService;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
public class NaviScreenDpiServiceImplTest extends TxTestBase{
	@Autowired
	INaviScreenDpiService service;

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviScreenDpiServiceImpl#insert(com.autonavi.tsp.workbackend.dto.NaviScreenDpiDto)}.
	 * @throws Exception 
	 */
	@Test
	public void testInsert() throws Exception {
		NaviScreenDpiDto dto = new NaviScreenDpiDto();
		dto.setName("hdpi");
		dto.setMemo("test dpi");
		dto.setPid(PlatformIds.ANDROID.getValue());
		String key = service.insert(dto);
	
		Map<String, Object> entityMap = jdbcTemplate.queryForMap("select * from navi_screen_dpi where name=?", key);
		Assert.assertNotNull(entityMap);
		Assert.assertEquals(dto.getMemo(), entityMap.get("memo"));
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviScreenDpiServiceImpl#selectByKey(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectByKey() throws Exception {
		NaviScreenDpiDto dto = new NaviScreenDpiDto();
		dto.setName("hdpi");
		dto.setMemo("test dpi");
		dto.setPid(PlatformIds.ANDROID.getValue());
		String key = service.insert(dto);
		
		NaviScreenDpiDto dto2 = service.selectByKey(key);
		Assert.assertNotNull(dto2);
		Assert.assertEquals(dto2.getMemo(), dto.getMemo());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviScreenDpiServiceImpl#deleteByKey(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteByKey() throws Exception {
		NaviScreenDpiDto dto = new NaviScreenDpiDto();
		dto.setName("hdpi");
		dto.setMemo("test dpi");
		dto.setPid(PlatformIds.ANDROID.getValue());
		String key = service.insert(dto);
		
		service.deleteByKey(new DeleteKeyDto<String>(dto.getKey()));
		//reselect to check
		List<Map<String, Object>> entityList = jdbcTemplate.queryForList("select * from navi_screen_dpi where name=?", key);
		Assert.assertTrue(entityList == null || entityList.size() == 0);
		
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.NaviScreenDpiServiceImpl#update(com.autonavi.tsp.workbackend.dto.NaviScreenDpiDto)}.
	 * @throws Exception 
	 */
	@Test
	public void testUpdate() throws Exception {
		NaviScreenDpiDto dto = new NaviScreenDpiDto();
		dto.setName("hdpi");
		dto.setMemo("test dpi");
		dto.setPid(PlatformIds.ANDROID.getValue());
		String key = service.insert(dto);
		
		//prepare update dto
		dto.setMemo("updated memo");
		service.update(dto);
		
		//reslect to check
		Map<String, Object> entityMap = jdbcTemplate.queryForMap("select * from navi_screen_dpi where name=?", key);
		Assert.assertNotNull(entityMap);
		Assert.assertEquals(dto.getMemo(), entityMap.get("memo"));
	}

}

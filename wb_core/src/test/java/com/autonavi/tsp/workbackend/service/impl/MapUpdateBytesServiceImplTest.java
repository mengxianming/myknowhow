/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.autonavi.tsp.atsp.core.TxTestBase;
import com.autonavi.tsp.common.util.GZipUtils;
import com.autonavi.tsp.workbackend.dto.naviMap.NaviMapDataDto;
import com.autonavi.tsp.workbackend.service.IMapUpdateBytesService;

/**
 * @author mengxianming
 *
 * 2015年6月23日
 */
public class MapUpdateBytesServiceImplTest extends TxTestBase{
	@Autowired
	IMapUpdateBytesService mapUpdateBytesService;

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateBytesServiceImpl#getMapByteData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetMapByteData() throws Exception {
		String imei = "1234";
		String model = "Lenovol";
		String os = "v12.5";
		String userid = null;
		String sid = "1234567";
		String processtime = null;
		String protversion = null;
		String language = null;
		String pid = "1";
		String apkv = "654";
		String syscode="3047";
		String mapversion = "32";
		String resolution = "hdpi";
		String needtaiwan = null;
		String[] mapvlist = null;
		String enginev = null;
		byte[] localCityVersions = GZipUtils.compress("[\"110000,29.1.031005.0004\", \"340800,29.1.031005.0004\", \"350200,29.1.031005.0004\"]".getBytes("utf-8"));
		boolean forLocalOnly = true;
		byte[] result = mapUpdateBytesService.getMapByteData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		String json = new String(GZipUtils.decompress(result), "utf-8");
		System.out.println(json);
		JSONObject jobj = JSON.parseObject(json);
		Assert.assertEquals(jobj.getString("is_error"), "false");
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void tmptest() throws Exception {
		String imei = "1234";
		String model = "Lenovol";
		String os = "v12.5";
		String userid = null;
		String sid = "1234567";
		String processtime = null;
		String protversion = null;
		String language = null;
		String pid = "2";
		String apkv = "14";
		String syscode="41001";
		String mapversion = "";
		String resolution = "640x960";
		String needtaiwan = null;
		String[] mapvlist = new String[]{"30.1.031005.0005"};
		String enginev = null;
		byte[] localCityVersions = GZipUtils.compress("[\"350200,30.1.031005.0005\"] ".getBytes("utf-8"));
		boolean forLocalOnly = true;
		byte[] result = mapUpdateBytesService.getMapByteData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		String json = new String(GZipUtils.decompress(result), "utf-8");
		System.out.println(json);
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateBytesServiceImpl#getMapCityData(java.lang.String)}.
	 */
	@Test
	public void testGetMapCityData() {
		fail("Not yet implemented"); // TODO
	}

}

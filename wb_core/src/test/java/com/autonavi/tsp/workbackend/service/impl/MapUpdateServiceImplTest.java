/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.autonavi.tsp.atsp.core.TxTestBase;
import com.autonavi.tsp.common.util.GZipUtils;
import com.autonavi.tsp.workbackend.dto.naviMap.NaviMapDataDto;
import com.autonavi.tsp.workbackend.service.IMapUpdateService;

/**
 * @author mengxianming
 *
 * 2015年6月19日
 */
public class MapUpdateServiceImplTest extends TxTestBase{
	@Autowired
	IMapUpdateService mapUpdateService;

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void testGetMapData() throws Exception {
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
		byte[] localCityVersions = GZipUtils.compress("[\"110000,29.1.031005.0004\", \"340800,29.1.031005.0004\"]".getBytes("utf-8"));
		boolean forLocalOnly = true;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
		Assert.assertEquals(result.getResVersionDto().getProvices().length, 2);
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void testGetMapDataBaseResAndroid() throws Exception {
		String imei = "358240053677514";
		String model = "Lenovol";
		String os = "22";
		String userid = null;
		String sid = "0123456789abcdef0123456789abcdef";
		String processtime = "20150627110952";
		String protversion = "1";
		String language = "0";
		String pid = "1";
		String apkv = "2106";
		String syscode="3047";
		String mapversion = "38";
		String resolution = "hdpi";
		String needtaiwan = "0";
		String[] mapvlist = null;
		String enginev = "7.3.031005.0054";
		
//		byte[] localCityVersions = GZipUtils.compress(	"[\"350200,30.1.031005.0005\",\"350600,30.1.031005.0005\"] ".getBytes("utf-8"));
		boolean forLocalOnly = false;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				null, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
		Assert.assertEquals(result.getResVersionDto().getProvices().length, 2);
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
		String os = "5";
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
		String[] mapvlist = null;
		String enginev = null;
		byte[] localCityVersions = GZipUtils.compress("[\"350200,30.1.031005.0005\"] ".getBytes("utf-8"));
		boolean forLocalOnly = false;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
		Assert.assertEquals(result.getResVersionDto().getProvices().length, 2);
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void testGetMapData_ForceUpdate_LocalOnly() throws Exception {
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
		byte[] localCityVersions = GZipUtils.compress("[\"110000,29.1.031005.0004\", \"340800,29.1.031005.0004\"]".getBytes("utf-8"));
		boolean forLocalOnly = true;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
		Assert.assertEquals(result.getResVersionDto().getProvices().length, 2);
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void testGetMapData_ForceUpdate_NotLocalOnly() throws Exception {
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
		byte[] localCityVersions = GZipUtils.compress("[\"110000,29.1.031005.0004\", \"340800,29.1.031005.0004\"]".getBytes("utf-8"));
		boolean forLocalOnly = false;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
		Assert.assertEquals(result.getResVersionDto().getProvices().length, 33);
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void testGetMapData_ForceUpdate_NullLocalCityVersions() throws Exception {
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
		String needtaiwan = "0";
		String[] mapvlist = null;
		String enginev = null;
		byte[] localCityVersions = null;
		boolean forLocalOnly = false;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
		Assert.assertEquals(result.getResVersionDto().getProvices().length, 33);
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.service.impl.MapUpdateServiceImpl#getMapData(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, byte[], boolean)}.
	 * @throws Exception 
	 * @throws  
	 */
	@Test
	public void testGetMapData_NotLocalOnly() throws Exception {
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
		byte[] localCityVersions = GZipUtils.compress("[\"110000,29.1.031005.0004\", \"340800,29.1.031005.0004\"]".getBytes("utf-8"));
		boolean forLocalOnly = false;
		NaviMapDataDto result = mapUpdateService.getMapData(imei, model, os, userid, sid, processtime, protversion,	language,
				pid, apkv, syscode, mapversion, resolution, needtaiwan, mapvlist, enginev,
				localCityVersions, forLocalOnly);
		System.out.println(JSON.toJSONString(result));
		Assert.assertEquals(result.getIsError(), "false");
	}

}

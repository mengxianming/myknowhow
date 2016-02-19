package my.study.exceldemo;

import org.junit.Test;

import junit.framework.Assert;
import my.study.exceldemo.vo.Config;
import my.study.exceldemo.vo.EnumGenOpt;
import my.study.exceldemo.vo.SqlGenOpt;

public class MiscTest{
	
	@Test
	public void testIfDoubleIsInt(){
		double d = 11.9;
		System.out.println((long)d);
		System.out.println(d - ((long)d) > 0);
		
		d = 11.0;
		System.out.println(d - ((long)d) == 0);
	}
	
	@Test
	public void testConfig2Json0(){
		Config c = new Config();
		c.setExcelFilePath("D:/mengxm/账单详细类型管理表.xls");
		c.setStartRowNum(3);
		c.setStartColNum(2);
		c.setSheetNum(1);
		c.setMode(0);
		String json = JsonUtil.toJson(c);
		System.out.println(json);
		
		Config c2 = JsonUtil.fromJson(json, Config.class);
		Assert.assertEquals(json, JsonUtil.toJson(c2));
	}
	
	@Test
	public void testConfig2Json1(){
		Config c = new Config();
		SqlGenOpt sqlGenOpt = new SqlGenOpt();
		sqlGenOpt.setTableName("acct.acct_billdtltype");
		c.setSqlGenOpt(sqlGenOpt);
		c.setExcelFilePath("D:/mengxm/账单详细类型管理表.xls");
		c.setStartRowNum(3);
		c.setStartColNum(2);
		c.setSheetNum(1);
		c.setMaxColCount(4);
		c.setMode(1);
		String json = JsonUtil.toJson(c);
		System.out.println(json);
		
		Config c2 = JsonUtil.fromJson(json, Config.class);
		Assert.assertEquals(json, JsonUtil.toJson(c2));
	}
	
	@Test
	public void testConfig2Json2(){
		Config c = new Config();
		EnumGenOpt enumGenOpt = new EnumGenOpt();
		enumGenOpt.setPkgName("my.study");
		enumGenOpt.setClassName("BusiTypeEnum");
		enumGenOpt.setKeyIdx(0);
		enumGenOpt.setValIdx(1);
		enumGenOpt.setEnumItemNameIdx(2);
		c.setEnumGenOpt(enumGenOpt);
		c.setExcelFilePath("D:/mengxm/账单详细类型管理表.xls");
		c.setStartRowNum(3);
		c.setStartColNum(2);
		c.setSheetNum(1);
		c.setMode(2);
		String json = JsonUtil.toJson(c);
		System.out.println(json);
		
		Config c2 = JsonUtil.fromJson(json, Config.class);
		Assert.assertEquals(json, JsonUtil.toJson(c2));
	}
	
	@Test
	public void testMainPrintOnly() throws Exception{
		String[] args = new String[1];
		args[0] = "{\"excelFilePath\":\"classpath:/input.xls\",\"sheetNum\":1,\"startRowNum\":2,\"startColNum\":2,\"maxColCount\":null,\"mode\":0,\"sqlGenOpt\":null,\"enumGenOpt\":null}";;
		
		Main.main(args);
	}
	
	
	@Test
	public void testMainSqlGen() throws Exception{
		String[] args = new String[1];
		args[0] = "{\"excelFilePath\":\"classpath:/input.xls\",\"sheetNum\":1,\"startRowNum\":2,\"startColNum\":2,"
				+ "\"maxColCount\":null,\"mode\":1,\"sqlGenOpt\":{\"tableName\":\"acct.acct_billdtltype\"},\"enumGenOpt\":null}";;
		
		Main.main(args);
	}
	
	@Test
	public void testMainSqlGen2() throws Exception{
		String[] args = new String[1];
		args[0] = "{\"excelFilePath\":\"classpath:/input.xls\",\"sheetNum\":1,\"startRowNum\":2,\"startColNum\":2,"
				+ "\"maxColCount\":4,\"mode\":1,\"sqlGenOpt\":{\"tableName\":\"acct.acct_billdtltype\"},\"enumGenOpt\":null}";;
		
		Main.main(args);
	}
	
	@Test
	public void testMainEnumGen() throws Exception{
		String[] args = new String[1];
		args[0] = "{\"excelFilePath\":\"classpath:/账单详细类型管理表.xls\",\"sheetNum\":1,\"startRowNum\":17,\"startColNum\":3,"
				+ "\"maxColCount\":null,\"mode\":2,\"sqlGenOpt\":null,\"enumGenOpt\":{\"pkgName\":\"my.study\",\"className\":\"BusiTypeEnum\",\"keyIdx\":0,\"valIdx\":1,\"enumItemNameIdx\":1}}";
		
		Main.main(args);
	}
	
	@Test
	public void testMainJsonGen() throws Exception{
		String[] args = new String[1];
		args[0] = "{\"excelFilePath\":\"classpath:/账单详细类型管理表.xls\",\"sheetNum\":1,\"startRowNum\":3,\"startColNum\":2,\"maxColCount\":null,\"mode\":3,\"sqlGenOpt\":null,\"enumGenOpt\":null}";;
		
		Main.main(args);
	}
	
	

}
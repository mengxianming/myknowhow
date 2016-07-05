package my.study.jsontool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class MiscTest{
	@Test
	public void test(){
		System.out.println(null == null);
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
	public void testFromJson(){
		Config c = new Config();
		c.setExcelFilePath("D:/mengxm/账单详细类型管理表.xls");
		c.setStartRowNum(3);
		c.setStartColNum(2);
		c.setSheetNum(1);
		c.setMode(0);
		String json = JsonUtil.toJson(c);
		System.out.println(json);
		
		Map<?, ?> c2 = JsonUtil.fromJson(json, Map.class);
		Assert.assertEquals(json, JsonUtil.toJson(c2));
	}
	
	@Test
	public void testFromJson2() throws JsonProcessingException, IOException{
		Object[] beans = new Object[2];
		Config c = new Config();
		c.setStartColNum(2);
		c.setSheetNum(1);
		c.setMode(0);
		beans[0] = c;
		 c = new Config();
		 c.setStartColNum(3);
		 c.setSheetNum(11);
		 c.setMode(22);
		 beans[1] = c;

		String json = JsonUtil.toJson(beans);
		System.out.println(json);
		
		Object beans2 = null;
		ObjectMapper om = new ObjectMapper();
		JsonNode t = om.readTree(json);
		if(t.isArray()){
			beans2 = JsonUtil.fromJson(json, List.class);
		}else{
			beans2 = JsonUtil.fromJson(json, Map.class);
		}
		Assert.assertEquals(json, JsonUtil.toJson(beans2));
	}
	
	
	@Test
	public void testMainPrettyPrint() throws Exception{
		String[] args = new String[1];
		args[0] = "-p";
		String input = "{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\"sheetNum\":1,\"startRowNum\":3,\"startColNum\":2,\"maxColCount\":null,\"mode\":0,\"sqlGenOpt\":null,\"enumGenOpt\":null}";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);
		Main.main(args);
	}
	
	@Test
	public void testMainPrettyPrintWithCharset() throws Exception{
		String[] args = new String[3];
		args[0] = "-p";
		args[1] = "-c";
		args[2] = "gbk";
		String input = "{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\"sheetNum\":1,\"startRowNum\":3,\"startColNum\":2,\"maxColCount\":null,\"mode\":0,\"sqlGenOpt\":null,\"enumGenOpt\":null}";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes(args[2]));
		System.setIn(bis);
		Main.main(args);
	}
	
	@Test
	public void testMainCompactPrint() throws Exception{
		String[] args = new String[1];
		args[0] = "-P";
		String input = "{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\n\"sheetNum\":1,\"startRowNum\":3,\n\"startColNum\":2,\"maxColCount\":null,\"mode\":0,\"sqlGenOpt\":null,\"enumGenOpt\":null}";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);
		Main.main(args);
	}
	
	@Test
	public void testMainJson2Xml() throws Exception{
		String[] args = new String[1];
		args[0] = "-x";
		String input = "{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\n\"sheetNum\":1,\"startRowNum\":3,\n\"startColNum\":2,\"maxColCount\":null,\"mode\":0,\"sqlGenOpt\":{\"i\":1},\"enumGenOpt\":null}";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);
		Main.main(args);
	}
	
	@Test
	public void testMainJson2XmlArray() throws Exception{
		String[] args = new String[1];
		args[0] = "-x";
		String input = "[{\"excelFilePath\":null,\"sheetNum\":1,\"startRowNum\":0,\"startColNum\":2,\"maxColCount\":null,\"mode\":0},{\"excelFilePath\":null,\"sheetNum\":11,\"startRowNum\":0,\"startColNum\":3,\"maxColCount\":null,\"mode\":22}]\n";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);
		Main.main(args);
	}
	
	@Test
	public void testMainXml2Json() throws Exception{
		String[] args = new String[1];
		args[0] = "-j";
		String input = "<root>  <excelFilePath>D:/mengxm/账单详细类型管理表.xls</excelFilePath>  <sheetNum>1</sheetNum>  <startRowNum>3</startRowNum>  <startColNum>2</startColNum>  <maxColCount/>  <mode>0</mode>  <sqlGenOpt>    <i>1</i>  </sqlGenOpt>  <enumGenOpt/></root>";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);
		Main.main(args);
	}
	
	@Test
	public void testMainEscapeJava() throws Exception{
		String[] args = new String[1];
		args[0] = "-e";
		String input = "{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\n\"sheetNum\":1}";
		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);		
		Main.main(args);
	}
	
	@Test
	public void testMainUnescapeJava() throws Exception{
		String[] args = new String[1];
		args[0] = "-E";
		String input = "{\\\"excelFilePath\\\":\\\"D:/mengxm/账单详细类型管理表.xls\\\",\\n\\\"sheetNum\\\":1}\n";

		System.out.println("source:" + input);
		System.out.println("converted:");
		InputStream bis = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(bis);
		Main.main(args);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGenericCast(){
		List<Number> nums;
		List<? extends Number> ints = new ArrayList<Integer>(Arrays.asList(new Integer(1)));
		nums = (List<Number>) ints;
		System.out.println(nums);
		

		List<? extends Number> floats = Arrays.asList(new Float(1.1));
		nums.addAll(floats);
		System.out.println(nums);
	}
	
}
package my.study.exceldemo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;

import my.study.exceldemo.vo.Config;
import my.study.exceldemo.vo.XlsWriteOpt;
import my.study.exceldemo.write.WriteMain;
import my.study.jsontool.JsonUtil;

public class WriteMainTest{
	@Test
	public void testWriteXlsFromTabStr() throws Exception{		
		Config c = new Config();
		c.setExcelFilePath("D:/tmp/testWriteXlsFromBeanList.xls");
		XlsWriteOpt wo = new XlsWriteOpt();
		wo.setMode(0);
		c.setSheetName("testSheet");
		c.setWriteOpt(wo);
				
		String[] args = new String[1];
		args[0] = JsonUtil.toJson(c);
		System.out.print("config:\n" + args[0]);
		InputStream is = getClass().getResourceAsStream("/tabstrsource.txt");
		System.setIn(is);
		WriteMain.main(args);		
		
	}
	
	@Test
	public void testWriteXlsFromJson() throws Exception{		
		Config c = new Config();
		c.setExcelFilePath("D:/tmp/testWriteXlsFromJson.xls");
		XlsWriteOpt wo = new XlsWriteOpt();
		wo.setMode(1);
		c.setSheetName("testSheet");
		c.setWriteOpt(wo);
				
		String[] args = new String[1];
		args[0] = JsonUtil.toJson(c);
		System.out.print("config:\n" + args[0]);
		
		String input = JsonUtil.toJson(Arrays.asList(c, c, c));
		InputStream is = new ByteArrayInputStream(input.getBytes("utf8"));
		System.setIn(is);
		WriteMain.main(args);		
	}
	
	@Test
	public void testWriteXlsFromDB() throws Exception{		
		Config c = new Config();
		c.setExcelFilePath("D:/tmp/testWriteXlsFromDB.xls");
		XlsWriteOpt wo = new XlsWriteOpt();
		wo.setJdbcDriver("com.mysql.jdbc.Driver");
		wo.setJdbcUrl("jdbc:mysql://192.168.31.3:3306/mxm_dev?useUnicode=true&characterEncoding=UTF-8");
		wo.setJdbcUsername("root");
		wo.setJdbcPassword("123456");
		wo.setSql("select * from acct.acct_billtype");
		wo.setMode(2);
		c.setSheetName("testSheet");
		c.setWriteOpt(wo);
		
		String[] args = new String[1];
		args[0] = JsonUtil.toJson(c);
		System.out.print("config:\n" + args[0]);
		WriteMain.main(args);		
		
	}
	
	
	
	

}
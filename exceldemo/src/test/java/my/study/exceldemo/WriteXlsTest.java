package my.study.exceldemo;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import my.study.exceldemo.vo.Config;
import my.study.exceldemo.vo.XlsWriteOpt;
import my.study.exceldemo.write.BeanListContentProvider;
import my.study.exceldemo.write.ContentProvider;
import my.study.exceldemo.write.DBContentProvider;
import my.study.exceldemo.write.JsonContentProvider;
import my.study.exceldemo.write.TabDelimStringContentProvider;
import my.study.exceldemo.write.WriteXls;

public class WriteXlsTest{
	@Test
	public void testWriteXlsFromBeanList() throws Exception{
		Config c = new Config();
		c.setExcelFilePath("D:/mengxm/账单详细类型管理表.xls");
		c.setStartRowNum(3);
		c.setStartColNum(2);
		c.setSheetNum(1);
		c.setMode(0);
		
		List<?> list = Arrays.asList(c, c);
		ContentProvider cp = new BeanListContentProvider(list, null);
		new WriteXls().writeXls("d:/tmp/my.xls", "configList", cp);;
		
	}
	
	@Test
	public void testWriteXlsFromJson() throws Exception{		
		String json = "[{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\"sheetNum\":1,\"startRowNum\":3,\"startColNum\":2,\"mode\":0},{\"excelFilePath\":\"D:/mengxm/账单详细类型管理表.xls\",\"sheetNum\":1,\"startRowNum\":3,\"startColNum\":2,\"mode\":0}]\n";
		ContentProvider cp = new JsonContentProvider(json, null);
		new WriteXls().writeXls("d:/tmp/my2.xls", "configList", cp);;
		
	}
	
	@Test
	public void testWriteXlsFromDB() throws Exception{		
		Config c = new Config();
		XlsWriteOpt wo = new XlsWriteOpt();
		wo.setJdbcDriver("com.mysql.jdbc.Driver");
		wo.setJdbcUrl("jdbc:mysql://192.168.31.3:3306/mxm_dev?useUnicode=true&characterEncoding=UTF-8");
		wo.setJdbcUsername("root");
		wo.setJdbcPassword("123456");
		wo.setSql("select * from acct.acct_billtype");
		c.setWriteOpt(wo);
		ContentProvider cp = new DBContentProvider(c, null);
		new WriteXls().writeXls("d:/tmp/my3.xls", "datafromdb", cp);;
		
	}
	
	@Test
	public void testWriteXlsFromTabStr() throws Exception{		
		InputStream src = getClass().getResourceAsStream("/tabstrsource.txt");
		String ct = IOUtils.toString(src, "utf8");
		ContentProvider cp = new TabDelimStringContentProvider(ct, null);
		new WriteXls().writeXls("d:/tmp/my4.xls", "datafromdb", cp);;
		
	}
	
	
	

}
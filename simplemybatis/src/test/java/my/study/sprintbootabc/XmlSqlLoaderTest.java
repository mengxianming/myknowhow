package my.study.sprintbootabc;

import org.junit.Test;

import my.study.sprintbootabc.mapper.sql.XmlSqlLoader;

/**
 * 
 * @author 蒙先铭-2016年12月16日
 *
 */
public class XmlSqlLoaderTest {  
     
	@Test
	public void test() throws Exception{
		XmlSqlLoader loader = new XmlSqlLoader();
		loader.load(getClass().getResourceAsStream("comsql.xml"));
		
		System.out.println(loader.getSqlMap());
		
	}      
    
}
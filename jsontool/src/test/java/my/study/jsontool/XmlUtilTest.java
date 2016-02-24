package my.study.jsontool;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class XmlUtilTest {
	public static class TestBean{
		int id;
		String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	@Test
    public void testToXml() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","chris");
        map.put("island","faranga");

        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("name","chris");
        map2.put("island","faranga");
        
        map.put("boss", map2);
      
        System.out.println(XmlUtil.toXml(map));

    }
	
	@Test
    public void testListToXml() {
		TestBean b1 = new TestBean();
		b1.setId(1);
		b1.setName("bean1");
		
		TestBean b2 = new TestBean();
		b2.setId(2);
		b2.setName("bean2");
		

        System.out.println(XmlUtil.toXml(Arrays.asList(b1, b2)));

    }
	
	@Test
    public void testFromXml() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","chris");
        map.put("age","20");

        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("name","myboss");
        map2.put("age","40");
        
        map.put("boss", map2);
      
        String xml = XmlUtil.toXml(map);
        System.out.println(xml);
        
        Map<?, ?> toMap = XmlUtil.fromXml(xml);
        System.out.println(toMap);

    }
	
	@Test
    public void testJsonToXml() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","chris");
        map.put("age","20");

        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("name","myboss");
        map2.put("age","40");
        
        map.put("boss", map2);
      
        String json = JsonUtil.toJson(map);
        System.out.println(json);
        
        System.out.println(XmlUtil.jsonToXml(json));

    }
	
	@Test
    public void testXmlToJson() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","chris");
        map.put("age","20");

        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("name","myboss");
        map2.put("age","40");
        
        map.put("boss", map2);
      
        String xml = XmlUtil.toXml(map);
        System.out.println(xml);
        
        System.out.println(XmlUtil.xmlToJson(xml));

    }
	
//	@Test
//    public void testXmlToJson2() throws IOException {
//        String xml = IOUtils.toString(getClass().getResourceAsStream("beans.xml"), "utf8");
//               
//        System.out.println(XmlUtil.xmlToJson(xml));
//
//    }


    
}
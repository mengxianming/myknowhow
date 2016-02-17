package my.study.jsontool;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class XmlUtilTest {

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


    
}
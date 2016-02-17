package my.study.jsontool;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XmlUtil {
	@SuppressWarnings("rawtypes")
	public static class MapEntryConverter implements Converter {        
		public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                writer.startNode(entry.getKey().toString());
                Object val = entry.getValue();
                if ( null != val ) {                	
                	if(val instanceof AbstractMap){
                		//递归写入
                		marshal(val, writer, context);
                	}else{
                		 writer.setValue(val.toString());
                	}                   
                }
                writer.endNode();
            }

        }

        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();

            while(reader.hasMoreChildren()) {
                reader.moveDown();

                String key = reader.getNodeName(); // nodeName aka element's name
                Object value;
                if(reader.hasMoreChildren()){
                	//递归读复合节点
                	value = unmarshal(reader, context);
                }else{
                	value = reader.getValue();
                }
                
                value = "".equals(value) ? null : value;

                map.put(key, value);
                
                reader.moveUp();
            }

            return map;
        }

    }

	private static XStream xstream = new XStream();
	static{
		xstream.registerConverter(new MapEntryConverter());
		xstream.alias("root", Map.class);
		xstream.alias("root", LinkedHashMap.class);
	}

	public static String toXml(Map<?, ?> map) {	
        return xstream.toXML(map);
	}
	
	public static Map<?, ?> fromXml(String xml) {	
        return (Map<?, ?>) xstream.fromXML(xml, new LinkedHashMap<String, Object>());
	}
	
	public static String jsonToXml(String json) {
		Map<?, ?> map = JsonUtil.fromJson(json, LinkedHashMap.class);
        return toXml(map);
	}
	
	public static String xmlToJson(String xml) {		
		Map<?, ?> map = fromXml(xml);
		
        return JsonUtil.toJson(map);
	}

}

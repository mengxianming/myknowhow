package my.study.sprintbootabc.mapper.sql;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author 蒙先铭-2016年12月16日
 *
 */
public class XmlSqlLoader {  
	protected static final String SQLTAG = "sql";
	private Map<String, String> sqlMap = new HashMap<>();
	
	public synchronized void load(InputStream resource) throws Exception{	
		sqlMap = new HashMap<>();
				
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        SAXParser parser = factory.newSAXParser();  
        parser.parse(resource, new DefaultHandler(){
        	private boolean isSqlTag;
        	private StringBuilder sql = new StringBuilder();
        	private String sqlId;
        	
        	@Override
        	public void startElement(String uri, String localName, String qName, Attributes attributes)
        			throws SAXException {
        		isSqlTag = SQLTAG.equals(qName); 
        		if(isSqlTag){
        			sqlId = attributes.getValue("id");
        		}
        		
        	}
        	
        	@Override
        	public void characters(char[] ch, int start, int length) throws SAXException {        		
        		if(isSqlTag){
        			sql.append(new String(ch, start, length));
        		}
        	}
        	
        	@Override
        	public void endElement(String uri, String localName, String qName) throws SAXException {
        		if(isSqlTag){
        			isSqlTag = false;
        			
        			if(sqlId != null){
        				sqlMap.put(sqlId, sql.toString());
        			}
        		}
        	}
        	
        });  
    
	}
	
	public String getSqlById(String sqlId){
		return sqlMap.get(sqlId);
	} 
	
	public Map<String, String> getSqlMap(){
		return new HashMap<>(sqlMap);
	}
    
    
}
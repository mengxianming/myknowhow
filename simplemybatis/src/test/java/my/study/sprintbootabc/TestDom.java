package my.study.sprintbootabc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** 
 * dom读写xml 
 * @author whwang 
 */  
public class TestDom {  
      
    public static void main(String[] args) {  
    	readSax();  
        //write();   
    }  
      
    public static void read() {  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        try {  
            DocumentBuilder builder = dbf.newDocumentBuilder();  
            InputStream in = TestDom.class.getResourceAsStream("comsql.xml");  
            Document doc = builder.parse(in);  
            // root <university>   
            Element root = doc.getDocumentElement();  
            if (root == null) return;  
            System.err.println(root.getAttribute("name"));  
            // all college node   
            NodeList collegeNodes = root.getChildNodes();  
            if (collegeNodes == null) return;  
            for(int i = 0; i < collegeNodes.getLength(); i++) {  
                Node node = (Node) collegeNodes.item(i);  
                System.out.println("id:" + node.getAttributes().getNamedItem("id"));
                System.out.println("content:" + node.getNodeValue());
            }  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
    }  
    
    public static void readSax() {  
        try {  
            SAXParserFactory factory = SAXParserFactory.newInstance();  
            SAXParser parser = factory.newSAXParser();  
            InputStream in = TestDom.class.getResourceAsStream("comsql.xml");  
            parser.parse(in, new DefaultHandler(){
            	private boolean isSqlTag;
            	private StringBuilder sql = new StringBuilder();
            	private String sqlId;
            	private Map<String, String> sqlMap = new HashMap<>();
            	@Override
            	public void startElement(String uri, String localName, String qName, Attributes attributes)
            			throws SAXException {
            		System.out.println("startElement()");
            		
            		isSqlTag = "sql".equals(qName); 
            		if(isSqlTag){
            			sqlId = attributes.getValue("id");
            		}
            		
            	}
            	
            	@Override
            	public void characters(char[] ch, int start, int length) throws SAXException {
            		System.out.println("characters()");
            		
            		if(isSqlTag){
            			sql.append(new String(ch, start, length));
            		}
            	}
            	
            	@Override
            	public void endElement(String uri, String localName, String qName) throws SAXException {
            		System.out.println("endElement()");
            		
            		if(isSqlTag){
            			isSqlTag = false;
            			
            			if(sqlId != null){
            				sqlMap.put(sqlId, sql.toString());
            				System.out.println("sqlMap:" + sqlMap);
            			}
            		}
            	}
            	
            });  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
   
    
    
}
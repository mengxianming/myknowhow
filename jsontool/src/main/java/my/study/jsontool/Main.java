package my.study.jsontool;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class Main{
	public static void main(String[] args) throws Exception{
		if(args.length == 0){
			System.out.println("usage cmd {[-p] | [-P] | [-x] | [-j] | [-e] | [-E]} [-c charsetname]");
			return;
		}
		Boolean prettyFlg = null;
		String charsetname = "utf8";
		Boolean jsonToXml = null;
		Boolean xml2Json = null;
		Boolean escape = null;
		for(int i = 0; i< args.length; i++){
			String arg = args[i];
			if(arg.equals("-p")){
				prettyFlg = true;
			}else if(arg.equals("-P")){
				prettyFlg = false;
			}else if(arg.equals("-x")){
				jsonToXml = true;
			}else if(arg.equals("-j")){
				xml2Json = true;
			}else if(arg.equals("-e")){
				escape = true;
			}else if(arg.equals("-E")){
				escape = false;
			}else if(arg.equals("-c")){
				charsetname = args[i + 1];
			}
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IOUtils.copy(System.in, os);
		byte[] buffer = os.toByteArray();
		String source = new String(buffer, charsetname);
		String converted = source;
		
		if(xml2Json != null){
			converted = XmlUtil.xmlToJson(source);
		}
		
		if(prettyFlg != null){			
			Map<?, ?> bean = JsonUtil.fromJson(converted, Map.class);
			converted = JsonUtil.toJson(bean, null, null, prettyFlg);					
		}
		
		if(jsonToXml != null){
			converted = XmlUtil.jsonToXml(converted);
		}
		
		if(escape != null){
			if(escape){
				converted = StringEscapeUtils.escapeJava(converted);
			}else{
				converted = StringEscapeUtils.unescapeJava(converted);
			}
			converted = convertUnicode(converted);
		}
		
		
		System.out.println(converted);
	}
	
	public static String convertUnicode(String escaped){
		if(escaped.indexOf("\\u")==-1)
	        return escaped;

	    String processed="";

	    int position=escaped.indexOf("\\u");
	    while(position!=-1) {
	        if(position!=0)
	            processed+=escaped.substring(0,position);
	        String token=escaped.substring(position+2,position+6);
	        escaped=escaped.substring(position+6);
	        processed+=(char)Integer.parseInt(token,16);
	        position=escaped.indexOf("\\u");
	    }
	    processed+=escaped;

	    return processed;
	}
		
}
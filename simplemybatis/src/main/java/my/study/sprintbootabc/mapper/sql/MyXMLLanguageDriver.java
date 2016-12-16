package my.study.sprintbootabc.mapper.sql;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class MyXMLLanguageDriver extends XMLLanguageDriver 
                                           implements LanguageDriver {
	XmlSqlLoader xmlSqlloader = new XmlSqlLoader();
	public MyXMLLanguageDriver() {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource cfg = resolver.getResource(getXmlSqlLocation());
		if(cfg.exists()){			
			try {
				xmlSqlloader.load(cfg.getInputStream());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
    private String getXmlSqlLocation() {
		return "classpath:dynasql/comsql.xml";
	}
	public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
		String xmlsql = xmlSqlloader.getSqlById(script);
		script = "<script>" + xmlsql +"</script>";
        return super.createSqlSource(configuration, script, parameterType);
    }
}
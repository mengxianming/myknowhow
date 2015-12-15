package my.demo.mybatisgendemo;

import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 自定义注释生成器
 * @author mengxianming-2015年12月4日
 *
 */
public class MyCommentGenerator implements CommentGenerator{

	public void addConfigurationProperties(Properties properties) {
		
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if(introspectedColumn.getRemarks() != null && introspectedColumn.getRemarks().length() > 0){
			field.addJavaDocLine("/** " + introspectedColumn.getRemarks());
			field.addJavaDocLine(" */");
		}		
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		
	}

	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		
	}

	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if(introspectedColumn.getRemarks() != null && introspectedColumn.getRemarks().length() > 0){
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * 获取" + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}	
		
	}

	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if(introspectedColumn.getRemarks() != null && introspectedColumn.getRemarks().length() > 0){
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * 设置" + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}	
		
	}

	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		
	}

	public void addJavaFileComment(CompilationUnit compilationUnit) {
		
	}

	public void addComment(XmlElement xmlElement) {
		
	}

	public void addRootComment(XmlElement rootElement) {
		
	}
	
}
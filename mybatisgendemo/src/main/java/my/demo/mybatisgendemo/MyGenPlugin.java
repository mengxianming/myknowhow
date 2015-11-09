/**
 * 
 */
package my.demo.mybatisgendemo;

import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author admin-2015年10月22日
 *
 */
public class MyGenPlugin extends PluginAdapter {

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */
	public boolean validate(List<String> arg0) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#modelBaseRecordClassGenerated(org.mybatis.generator.api.dom.java.TopLevelClass, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
		topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));

		Field sid = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
		sid.setFinal(true);
		sid.setStatic(true);
		sid.setVisibility(JavaVisibility.PRIVATE);
		sid.setInitializationString("1L");
		topLevelClass.getFields().add(0, sid );
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}



	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#clientSelectByPrimaryKeyMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		method.setName("selectById");
		return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
	}
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapSelectByPrimaryKeyElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {		
		changeXmlId(element, "selectById");

		return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
	}


	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#clientUpdateByPrimaryKeySelectiveMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		method.setName("update");
		return super.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable);
	}
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapUpdateByPrimaryKeySelectiveElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		changeXmlId(element, "update");
		return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#clientDeleteByPrimaryKeyMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		method.setName("delete");
		return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
	}
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapDeleteByPrimaryKeyElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeXmlId(element, "delete");
		return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#clientGenerated(org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.dom.java.TopLevelClass, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		String name = interfaze.getType().getShortName();
		String entName = name.substring(0, name.length() - 6);
		FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType("com.mogoroom.acct.dao.mapper.BaseAcctMapper<?>".replace("?", entName));
		interfaze.addSuperInterface(superInterface);

		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
		//add selectByFeature		
		Method method = new Method("selectByFeature");
		method.setReturnType(new FullyQualifiedJavaType("java.util.List<?>".replace("?", entName)));		
		method.addParameter(new Parameter(new FullyQualifiedJavaType("com.mogoroom.acct.dao.domain." + entName), "feature"));
		interfaze.addMethod(method);

		//add selectAll
		Method method2 = new Method("selectAll");
		method2.setReturnType(new FullyQualifiedJavaType("java.util.List<?>".replace("?", entName)));		
		method2.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "_status"));
		interfaze.addMethod(method2);

		//add selectByFieldList
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));		
		Method selectByFieldList = new Method("selectByFieldList");
		selectByFieldList.setReturnType(new FullyQualifiedJavaType("java.util.List<?>".replace("?", entName)));		
		Parameter fieldParam = new Parameter(new FullyQualifiedJavaType("String"), "field");
		fieldParam.addAnnotation("@Param(\"field\")");
		selectByFieldList.addParameter(fieldParam);
		Parameter valsParam = new Parameter(new FullyQualifiedJavaType("java.util.List<?>"), "values");
		valsParam.addAnnotation("@Param(\"values\")");
		selectByFieldList.addParameter(valsParam);
		interfaze.addMethod(selectByFieldList);

		//add selectListPageInfo
		//this method is defned in the SUPER INTERFACE. SO no need to define again

		//add deleteByFeature
		Method dbf = new Method("deleteByFeature");
		dbf.setReturnType(new FullyQualifiedJavaType("int"));		
		dbf.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "feature"));
		interfaze.addMethod(dbf);

		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapDocumentGenerated(org.mybatis.generator.api.dom.xml.Document, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document, final IntrospectedTable introspectedTable) {
		//add deleteByFeature element
		Element deleteByFeature = new Element() {

			@Override
			public String getFormattedContent(int indentLevel) {												  
				String xml= 	"  <delete id=\"deleteByFeature\" parameterType=\"{1}\" >\n"
						+ "    delete from {2}\n"
						+ "	<where> \n"
						+ "	  {3}\n"
						+ " </where>\n"
						+ "    </delete>\n";
				xml = xml.replace("{1}", introspectedTable.getBaseRecordType());
				xml = xml.replace("{2}", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

				StringBuilder conds = new StringBuilder();
				String tpl = 	"    <if test=\"{1} != null\" >\n"
						+ "          and {2} = #{{1},jdbcType={3}}\n"
						+ "    </if>\n";

				for(IntrospectedColumn col : introspectedTable.getAllColumns()){
					String cond = tpl.replace("{1}", col.getJavaProperty())
							.replace("{2}", col.getActualColumnName())
							.replace("{3}", col.getJdbcTypeName());
					conds.append(cond);
				}
				xml = xml.replace("{3}", conds);

				return xml;
			}
		};
		document.getRootElement().addElement(deleteByFeature);		

		//add selectByFeature element
		Element selectByFeatureEle = new Element() {

			@Override
			public String getFormattedContent(int indentLevel) {
				String xml= 	"  <select id=\"selectByFeature\" parameterType=\"{1}\" resultMap=\"BaseResultMap\">\n"
						+ "    select\n"
						+ "    <include refid=\"Base_Column_List\" />\n"
						+ "    from {2}\n"
						+ "	where 1=1\n"
						+ "	{3}\n"
						+ "  </select>\n";
				xml = xml.replace("{1}", introspectedTable.getBaseRecordType());
				xml =  xml.replace("{2}", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

				StringBuilder conds = new StringBuilder();
				String tpl = 	"    <if test=\"{1} != null\" >\n"
						+ "          and {2} = #{{1},jdbcType={3}}\n"
						+ "    </if>\n";

				for(IntrospectedColumn col : introspectedTable.getAllColumns()){
					String cond = tpl.replace("{1}", col.getJavaProperty())
							.replace("{2}", col.getActualColumnName())
							.replace("{3}", col.getJdbcTypeName());
					conds.append(cond);
				}
				xml = xml.replace("{3}", conds);

				return xml;
			}
		};
		document.getRootElement().addElement(selectByFeatureEle);

		//add selectAll element
		Element selectAll = new Element() {

			@Override
			public String getFormattedContent(int indentLevel) {
				String xml= 	"  <select id=\"selectAll\" resultMap=\"BaseResultMap\">\n"
						+ "    select\n"
						+ "    <include refid=\"Base_Column_List\" />\n"
						+ "    from {1}\n"
						+ "  </select>\n";

				xml = xml.replace("{1}", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
				return xml;


			}
		};
		document.getRootElement().addElement(selectAll);

		//add selectByFieldList element
		Element selectByFieldList = new Element() {

			@Override
			public String getFormattedContent(int indentLevel) {
				String xml= 	"  <select id=\"selectByFieldList\" parameterType=\"map\" resultMap=\"BaseResultMap\">\n"
						+ "    select\n"
						+ "    <include refid=\"Base_Column_List\" />\n"
						+ "    from {1}\n"
						+ "    where ${field} in\n"
						+ "    <foreach close=\")\" collection=\"values\" item=\"val\" open=\"(\" separator=\",\">\n"
						+ "	        #{val}\n"
						+ "  </foreach>\n"
						+ "  </select>\n";

				xml = xml.replace("{1}", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
				return xml;


			}
		};
		document.getRootElement().addElement(selectByFieldList);


		//add selectListPageInfo element
		Element selectListPageInfo = new Element() {

			@Override
			public String getFormattedContent(int indentLevel) {
				String xml= 	"  <select id=\"selectListPageInfo\" parameterType=\"{1}\" resultMap=\"BaseResultMap\">\n"
						+ "    select\n"
						+ "    <include refid=\"Base_Column_List\" />\n"
						+ "    from {2}\n"
						+ "	where 1=1\n"
						+ "	{3}\n"
						+ "  </select>\n";
				xml = xml.replace("{1}", "map");
				xml =  xml.replace("{2}", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

				StringBuilder conds = new StringBuilder();
				String tpl = 	"    <if test=\"po.{1} != null\" >\n"
						+ "          and {2} = #{po.{1},jdbcType={3}}\n"
						+ "    </if>\n";

				for(IntrospectedColumn col : introspectedTable.getAllColumns()){
					String cond = tpl.replace("{1}", col.getJavaProperty())
							.replace("{2}", col.getActualColumnName())
							.replace("{3}", col.getJdbcTypeName());
					conds.append(cond);
				}
				xml = xml.replace("{3}", conds);

				return xml;
			}
		};
		document.getRootElement().addElement(selectListPageInfo);




		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	/**
	 * @param element
	 */
	private void changeXmlId(XmlElement element, String newId) {
		Iterator<Attribute> itr = element.getAttributes().iterator();
		Attribute attr;
		while(itr.hasNext()){
			attr = itr.next();
			if(attr.getName().equals("id")){
				itr.remove();
			}
		}
		element.addAttribute(new Attribute("id", newId));
	}

	public static void generate() {  
		String config = MyGenPlugin.class.getClassLoader().getResource(  
				"generatorConfig.xml").getFile();  
		String[] arg = { "-configfile", config, "-overwrite" };  
		ShellRunner.main(arg);  
	}  
	public static void main(String[] args) {  
		generate();  
	}  

}

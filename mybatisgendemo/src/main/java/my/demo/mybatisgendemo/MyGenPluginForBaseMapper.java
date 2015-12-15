/**
 * 
 */
package my.demo.mybatisgendemo;

import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author admin-2015年10月22日
 *
 */
public class MyGenPluginForBaseMapper extends PluginAdapter {

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.Plugin#validate(java.util.List)
	 */
	public boolean validate(List<String> arg0) {
		return true;
	}
	
	/**
	 * @param introspectedTable
	 * @return
	 * @author admin-2015年11月12日
	 */
	protected CharSequence getBaseRecordShortName(IntrospectedTable introspectedTable) {
		CharSequence entName = introspectedTable.getBaseRecordType().replaceAll("^.*\\.", "");
		return entName;
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
	 * @see org.mybatis.generator.api.PluginAdapter#clientInsertMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapInsertElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#clientInsertSelectiveMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		method.setName("insert");
		return super.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable);
	}
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapInsertSelectiveElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeXmlId(element, "insert");
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
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
	 * @see org.mybatis.generator.api.PluginAdapter#clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.dom.java.Interface, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		return false;
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
		interfaze.addImportedType(new FullyQualifiedJavaType("com.mogoroom.service.base.dao.BaseMapper"));
		
		
		FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType("com.mogoroom.service.base.dao.BaseMapper<?>".replace("?", getBaseRecordShortName(introspectedTable)));
		interfaze.addSuperInterface(superInterface);

		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	/* (non-Javadoc)
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapDocumentGenerated(org.mybatis.generator.api.dom.xml.Document, org.mybatis.generator.api.IntrospectedTable)
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document, final IntrospectedTable introspectedTable) {		

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
}

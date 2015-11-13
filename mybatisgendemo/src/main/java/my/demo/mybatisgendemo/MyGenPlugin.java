/**
 * 
 */
package my.demo.mybatisgendemo;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;

/**
 * @author admin-2015年10月22日
 *
 */
public class MyGenPlugin extends MyGenPluginForBaseMapper {
	private static boolean useBase = true;
	/**
	 * @param useBase the useBase to set
	 */
	public static void setUseBase(boolean useBase) {
		MyGenPlugin.useBase = useBase;
	}
	
	
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {		
		boolean ret = super.clientGenerated(interfaze, topLevelClass, introspectedTable);
		if(useBase){
			return ret;
		}
		
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
		//add selectByFeature		
		Method method = new Method("selectByFeature");		
		method.setReturnType(new FullyQualifiedJavaType("java.util.List<?>".replace("?", getBaseRecordShortName(introspectedTable))));		
		method.addParameter(new Parameter(new FullyQualifiedJavaType("com.mogoroom.acct.dao.domain." + getBaseRecordShortName(introspectedTable)), "feature"));
		interfaze.addMethod(method);

		//add selectByFieldList
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));		
		Method selectByFieldList = new Method("selectByFieldList");
		selectByFieldList.setReturnType(new FullyQualifiedJavaType("java.util.List<?>".replace("?", getBaseRecordShortName(introspectedTable))));		
		Parameter fieldParam = new Parameter(new FullyQualifiedJavaType("String"), "field");
		fieldParam.addAnnotation("@Param(\"field\")");
		selectByFieldList.addParameter(fieldParam);
		Parameter valsParam = new Parameter(new FullyQualifiedJavaType("java.util.List<?>"), "values");
		valsParam.addAnnotation("@Param(\"values\")");
		selectByFieldList.addParameter(valsParam);
		interfaze.addMethod(selectByFieldList);

		//add deleteByFeature
		Method dbf = new Method("deleteByFeature");
		dbf.setReturnType(new FullyQualifiedJavaType("int"));		
		dbf.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "feature"));
		interfaze.addMethod(dbf);

		return ret;
	}
	
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, final IntrospectedTable introspectedTable) {
		boolean ret = super.sqlMapDocumentGenerated(document, introspectedTable);
		if(useBase){
			return ret;
		}
		
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


		return ret;
	}	
}

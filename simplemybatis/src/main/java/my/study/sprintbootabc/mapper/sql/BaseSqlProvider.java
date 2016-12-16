package my.study.sprintbootabc.mapper.sql;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;

import my.study.sprintbootabc.model.BaseModel;
import my.study.sprintbootabc.model.meta.ColumnDef;
import my.study.sprintbootabc.model.meta.ModelMeta;
import my.study.sprintbootabc.util.ComUtils;

public abstract class BaseSqlProvider<T> {
	private ModelMeta modelMeta;
	
	
	public BaseSqlProvider() {
		Class<?>[] genCls = ComUtils.getGenericTypes(getClass());
		if(genCls != null && genCls.length > 0){
			@SuppressWarnings("unchecked")
			Class<? extends BaseModel<?>> entityClass = (Class<? extends BaseModel<?>>) genCls[0];	
			modelMeta = ModelMeta.createModelMeta(entityClass);
		}
		
	}
	public ModelMeta getModelMeta() {
		return modelMeta;
	}
		
	public String insert(){
		SQL sql = new SQL().INSERT_INTO(modelMeta.getTableName());
		sql.VALUES(StringUtils.join(getColumnNames(false), ","), StringUtils.join(modelMeta.getColumnPlaceHolders(false), ","));
		return sql.toString();
	}
	
	

	public String update(T rec){
		SQL sql = new SQL().UPDATE(modelMeta.getTableName());
		for(Entry<String, ColumnDef> entry : modelMeta.getFieldColumnMap().entrySet()){
			ColumnDef col = entry.getValue();
			if(!Boolean.TRUE.equals(col.getIsPK())){
				sql.SET(eqClause(col));
			}
		}
		sql.WHERE(eqClause(modelMeta.getPkCol()));
		return sql.toString();
	}

	public String updateSelective(T rec){
		SQL sql = new SQL().UPDATE(modelMeta.getTableName());
		for(Entry<String, ColumnDef> entry :  modelMeta.getFieldColumnMap().entrySet()){
			ColumnDef col = entry.getValue();
			if(!Boolean.TRUE.equals(col.getIsPK())
					&& ComUtils.isNotEmpty(getProperty(rec, col.getFieldName()))){
				sql.SET(eqClause(col));
			}
		}
		sql.WHERE(eqClause(modelMeta.getPkCol()));
		return sql.toString();
	}

	public String delete(){
		SQL sql = new SQL().DELETE_FROM(modelMeta.getTableName());
		sql.WHERE(eqClause(modelMeta.getPkCol()));
		return sql.toString();
	}

	public String selectById(){
		SQL sql = new SQL().SELECT(StringUtils.join(getColumnNames(false), ","))
				.FROM(modelMeta.getTableName())
				.WHERE(eqClause(modelMeta.getPkCol()));
		return sql.toString();
	}
			
	
	protected String getJdbcTypeString(ColumnDef col) {
		return col.getJdbcType() == null || col.getJdbcType() == JdbcType.NULL ? "" : ", jdbcType=" + col.getJdbcType().name();
	}

	protected List<String> getColumnNames(boolean excludePK) {
		return modelMeta.getColumnNames(excludePK);
	}

	
	protected String eqClause(ColumnDef col) {
		return col.getFieldName() + "= #{" + col.getName() + getJdbcTypeString(col) +"}";
	}

	private Object getProperty(Object rec, String fieldName) {
		try {
			return PropertyUtils.getProperty(rec, fieldName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(), e);
		}		
	}

}

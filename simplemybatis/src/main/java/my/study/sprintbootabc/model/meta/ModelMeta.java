package my.study.sprintbootabc.model.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;

import my.study.sprintbootabc.dao.annotation.Column;
import my.study.sprintbootabc.dao.annotation.Entity;
import my.study.sprintbootabc.model.BaseModel;

public class ModelMeta {
	
	private Class<?> entityClass;
	
	private ColumnDef pkCol;
	private String tableName;
	private Map<String, ColumnDef> fieldColumnMap;
	
	private ModelMeta(Class<? extends BaseModel<?>> entityClass) {
		
		this.entityClass = entityClass;
		
		parseTableName();
		parseFieldColumnMap();
		
		//获取pk
		for(ColumnDef col : fieldColumnMap.values()){
			if(Boolean.TRUE.equals(col.getIsPK())){
				pkCol = col;
				break;
			}
		}
	}
	
	public  static ModelMeta createModelMeta(Class<? extends BaseModel<?>> entityClass) {
		return new ModelMeta(entityClass);
	}
	
	public Class<?> getEntityClass() {
		return entityClass;
	}
	public Map<String, ColumnDef> getFieldColumnMap() {
		return fieldColumnMap;
	}
	public ColumnDef getPkCol() {
		return pkCol;
	}
	public String getTableName() {
		return tableName;
	}
	
	protected void parseTableName() {
		Entity anno = entityClass.getAnnotation(Entity.class);
		tableName = anno == null || anno.value() == null || anno.value().length() == 0? entityClass.getSimpleName() : anno.value();
	}
	
	protected void parseFieldColumnMap() {
		Map<String, ColumnDef> result = new LinkedHashMap<>();
		
		List<String> excludeFields = new ArrayList<>();
		Entity anno = entityClass.getAnnotation(Entity.class);
		if(anno != null && anno.excludeFields().length > 0){
			for(String ef : anno.excludeFields()){
				excludeFields.add(ef);
			}
		}
		
		Field[] fields = entityClass.getDeclaredFields();
		for(Field f : fields){
			String fname = f.getName();
			if(!Modifier.isStatic(f.getModifiers())
					&& !excludeFields.contains(fname)){
				ColumnDef cd = new ColumnDef();		
				cd.setFieldName(fname);
				cd.setName(fname);
				
				Column col = f.getAnnotation(Column.class);
				if(col != null){
					if(col.value().length() > 0){
						cd.setName(col.value());
					}
					cd.setIsPK(col.isPK());
					cd.setJdbcType(col.jdbcType());
				}
				
				result.put(fname, cd);
			}
		}
		
		fieldColumnMap = result;			
	}

			
	public List<String> getColumnPlaceHolders(boolean excludePK) {
		List<String> result = new ArrayList<>();
		for(ColumnDef col : fieldColumnMap.values()){
			if(Boolean.TRUE.equals(col.getIsPK()) && excludePK){
				continue;
			}
			String jdbcType = getJdbcTypeString(col);
			result.add("#{" + col.getName() + jdbcType + "}");
		}
		
		return result;
	}

	protected String getJdbcTypeString(ColumnDef col) {
		return col.getJdbcType() == null || col.getJdbcType() == JdbcType.NULL ? "" : ", jdbcType=" + col.getJdbcType().name();
	}

	public List<String> getColumnNames(boolean excludePK) {
		List<String> result = new ArrayList<>();
		for(ColumnDef col : fieldColumnMap.values()){
			if(Boolean.TRUE.equals(col.getIsPK()) && excludePK){
				continue;
			}
			result.add(col.getName());
		}
		
		return result;
	}


}

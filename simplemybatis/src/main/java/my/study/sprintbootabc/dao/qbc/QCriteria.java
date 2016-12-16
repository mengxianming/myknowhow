package my.study.sprintbootabc.dao.qbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import my.study.sprintbootabc.model.BaseModel;
import my.study.sprintbootabc.model.meta.ModelMeta;
/**
 * 用于构建动态sql
 * @author mengxianming-2015年11月25日
 *
 */
public class QCriteria {
	private List<ConditionAndRelation> conditionAndRelations = new ArrayList<ConditionAndRelation>();
	private List<OrderBy> orderBys = new ArrayList<OrderBy>();
	private Integer limit;
	private Integer offset;
	private ModelMeta modelMeta;
	
	private QCriteria(Class<? extends BaseModel<?>> modelClass) {
		modelMeta = ModelMeta.createModelMeta(modelClass);
	}
	
	public static QCriteria createFor(final Class<? extends BaseModel<?>> modelClass){
		return new QCriteria(modelClass);
	}	
	
	public String getTableName() {
		return modelMeta.getTableName();
	}

	public List<String> getColumnNames() {
		return modelMeta.getColumnNames(false);
	}
	
	public String getColumnNamesString() {
		return StringUtils.join(getColumnNames(), ",");
	}
	
	public ModelMeta getModelMeta() {
		return modelMeta;
	}

	public QCriteria and(Condition... conditions){
		if(conditions == null || conditions.length == 0){
			throw new IllegalArgumentException("参数不能为空");
		}
		conditionAndRelations.add(ConditionAndRelation.and(conditions));
		return this;
	}
	
	/**
	 * 添加and条件集合、集合中各个条件使用or连接。
	 * 构建出的sql类似: and ( id=1 or id=2).
	 * 使用本方法时、请检查mapper.xml文件中的动态sql是否支持本方法。
	 * @param cond1
	 * @param cond2
	 * @param others
	 * @return
	 * @author mengxianming-2016年1月27日
	 */
	public QCriteria andWithOrs(Condition cond1, Condition cond2, Condition... others){		
		ArrayList<Condition> conditions = new ArrayList<Condition>();
		conditions.add(cond1);
		conditions.add(cond2);
		if(others != null && others.length > 0){
			for(Condition c : others){
				conditions.add(c);
			}
		}
		
		conditionAndRelations.add(ConditionAndRelation.andWithOrs(conditions.toArray(new Condition[0])));
		return this;
	}

	public QCriteria or(Condition... conditions){
		if(conditions == null || conditions.length == 0){
			throw new IllegalArgumentException("参数不能为空");
		}
		conditionAndRelations.add(ConditionAndRelation.or(conditions));
		return this;
	}
	
	public QCriteria addOrderBy(String field, OrderDirection dir){
		orderBys.add(new OrderBy(field, dir));
		return this;
	}
	
	public QCriteria limit(Integer limit){
		this.limit = limit;
		return this;
	}
	
	public QCriteria offset(Integer offset){
		this.offset = offset;
		return this;
	}	
	
	public List<ConditionAndRelation> getConditionAndRelations() {
		return conditionAndRelations;
	}
	public List<OrderBy> getOrderBys() {
		return orderBys;
	}
	public Integer getLimit() {
		return limit;
	}
	public Integer getOffset() {
		return offset;
	}	
	
	public String getSelects(){
		return null;
	}
	
	public String getGroupBys(){
		return null;
	}
}

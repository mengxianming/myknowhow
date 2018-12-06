package com.abc.service.base.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 用于构建动态sql
 * @author mengxianming-2015年11月25日
 *
 */
public class QCriteria {
	private static enum OP{
		LT("<"),
		LE("<="),
		EQ("="),
		NE("<>"),
		GT(">"),
		GE(">="),
		IN("IN"),
		ISNULL("IS NULL"),
		ISNOTNULL("IS NOT NULL")
		;
		String op;
		private OP(String op){
			this.op = op;
		}
		public String getOp() {
			return op;
		}
		@Override
		public String toString() {
			return getOp();
		}		
	}
	
	/**
	 * 查询结果排序方向
	 * @author admin
	 *
	 */
	public static enum OrderDirection{
		ASC("asc"),
		DESC("desc");
		private String dir;
		private OrderDirection(String dir){
			this.dir = dir;
		}
		public String getDir() {
			return dir;
		}		
	}
	/**
	 * 查询条件
	 * @author admin
	 *
	 */
	public static class Condition{
		private String field;
		private Object value;
		private OP op;
		public static Condition eq(String field, Object value){
			return new Condition(field, value, OP.EQ);
		}
		public static Condition ne(String field, Object value){
			return new Condition(field, value, OP.NE);
		}
		public static Condition lt(String field, Object value){
			return new Condition(field, value, OP.LT);
		}
		public static Condition le(String field, Object value){
			return new Condition(field, value, OP.LE);
		}
		public static Condition gt(String field, Object value){
			return new Condition(field, value, OP.GT);
		}
		public static Condition ge(String field, Object value){
			return new Condition(field, value, OP.GE);
		}
		public static Condition isNull(String field){
			return new Condition(field, null, OP.ISNULL);
		}
		public static Condition isNotNull(String field){
			return new Condition(field, null, OP.ISNOTNULL);
		}
		public static Condition in(String field, List<?> values){
			return new Condition(field, values, OP.IN);
		}
		
		private Condition(String field, Object value, OP op){
			this.field = field;
			this.value = value;
			this.op = op;
		}
		public String getField() {
			return field;
		}
		public Object getValue() {
			return value;
		}
		
		/**
		 * 当op为IN时，value必须指定为数组或者List。这种情况下本方法返回一个List。
		 * 其他情况下返回null。
		 * @return
		 * @author mengxianming-2015年11月25日
		 */
		@SuppressWarnings("unchecked")
		public List<Object> getValueList(){
			if(op == OP.IN){
				if(value instanceof List){
					return (List<Object>) value;
				}else if(value instanceof Object[]){
					Object[] arr = (Object[]) value;
					return Arrays.asList(arr);
				}
			}
			return null;
		}
		public String getOp() {
			return op.getOp();
		}		
	}
	public static class ConditionAndRelation{
		Condition[] conditions;
		String andOr;
		public static ConditionAndRelation and(Condition... conditions){
			ConditionAndRelation ins = new ConditionAndRelation();
			ins.conditions = conditions;
			ins.andOr = "AND";
			return ins;
		}
		public static ConditionAndRelation or(Condition... conditions){
			ConditionAndRelation ins = new ConditionAndRelation();
			ins.conditions = conditions;
			ins.andOr = "OR";
			return ins;
		}
		public String getAndOr() {
			return andOr;
		}
		public Condition[] getConditions() {
			return conditions;
		}
	}
	
	public static class OrderBy{
		private String field;
		private OrderDirection dir;
		public OrderBy(String field, OrderDirection dir){
			this.field = field;
			this.dir = dir;
		}
		public String getOrderBySql(){
			return field.concat(" ").concat(dir == null ? OrderDirection.ASC.getDir() : dir.getDir());
		}
	}
	private List<ConditionAndRelation> conditionAndRelations = new ArrayList<ConditionAndRelation>();
	private List<OrderBy> orderBys = new ArrayList<OrderBy>();
	private Integer limit;
	private Integer offset;
	
	
	public QCriteria and(Condition condition){
		conditionAndRelations.add(ConditionAndRelation.and(condition));
		return this;
	}

	public QCriteria or(Condition... conditions){
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
}

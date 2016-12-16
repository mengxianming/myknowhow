package my.study.sprintbootabc.dao.qbc;

import java.util.Arrays;
import java.util.List;

/**
 * 查询条件
 * @author admin
 *
 */
public class Condition{
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
	public static Condition like(String field, String pattern){
		return new Condition(field, pattern, OP.LIKE);
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
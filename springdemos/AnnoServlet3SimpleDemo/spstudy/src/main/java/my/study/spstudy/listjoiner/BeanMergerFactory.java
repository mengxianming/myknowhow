package my.study.spstudy.listjoiner;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BeanMergerFactory{
	@SuppressWarnings("unchecked")
	private static void doJoin0(Object left, Object right, String joinToField) throws Exception {				
		Field field = left.getClass().getDeclaredField(joinToField);
		field.setAccessible(true);
		Object fieldVal = field.get(left);				
		if(Collection.class.isAssignableFrom(field.getType())){
			Collection<Object> val = (Collection<Object>) fieldVal;
			if(val == null){
				if(Modifier.isAbstract(field.getType().getModifiers())
						|| Modifier.isInterface(field.getType().getModifiers())){
					//属性声明的类型为抽象类型、不能实例化。使用默认的集合类
					if(List.class.isAssignableFrom(field.getType())){
						val = new ArrayList<Object>();
					}else if(Set.class.isAssignableFrom(field.getType())){
						val = new LinkedHashSet<Object>();
					}
				}else{
					//属性声明的类型为具体类型、可以实例化。
					val = (Collection<Object>) field.getType().newInstance();						
				}
			}	
			
			
			if(val != null){
				val.add(right);
			}
			fieldVal = val;
		}else{
			fieldVal = right; 
		}
		
		field.set(left, fieldVal);		
	}
	
	/**
	 * 把符合连接条件的右边元素设置到左边元素的某个属性里。<br>
	 * 若指定的左边元素属性为集合类、则add到该集合里。<br>
	 * 若指定的左边元素属性为集合类、且未初始化、则先初始化该属性、再加入到集合里。<br>
	 * 若指定的左边元素属性不为集合类、则直接修改该属性值。<br>
	 * @param joinToField
	 * @return
	 */
	public static BeanMerger merge2LeftField(final String leftAlia, final String rightAlia, final String leftFieldToMerge){
		return new BeanMerger(){

			@Override
			public void mergeDataRow(DataRow dataRow) throws Exception {
				DataCol left = dataRow.getDataCol(leftAlia);
				DataCol right = dataRow.getDataCol(rightAlia);
				if(left != null && right != null){
					doJoin0(left.bean, right.bean, leftFieldToMerge);	
				}
			}
			
		};
	}
	
	/**
	 * 把符合连接条件的左边元素设置到右边元素的某个属性里。<br>
	 * 若指定的右边元素属性为集合类、则add到该集合里。<br>
	 * 若指定的右边元素属性为集合类、且未初始化、则先初始化该属性、再加入到集合里。<br>
	 * 若指定的右边元素属性不为集合类、则直接修改该属性值。<br>
	 * @param joinToField
	 * @return
	 */
	public static BeanMerger merge2RightField(final String leftAlia, final String rightAlia, final String rightFieldToMerge){
		return new BeanMerger(){

			@Override
			public void mergeDataRow(DataRow dataRow) throws Exception {
				Object left = dataRow.getDataCol(leftAlia);
				Object right = dataRow.getDataCol(rightAlia);
				doJoin0(right, left, rightFieldToMerge);	
			}
			
		};
	}
	 
}
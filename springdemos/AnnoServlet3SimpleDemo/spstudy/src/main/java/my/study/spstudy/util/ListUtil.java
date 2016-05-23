package my.study.spstudy.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author mengxianming
 *
 * 2015年5月26日
 */
public class ListUtil {
	/**
	 * 用于对集合中的元素进行过滤的条件函数。
	 * @author admin
	 *
	 * @param <T>
	 */
	public static interface ElementFilter<T>{
		boolean filter(T ele);
	}

	/**
	 * 根据集合元素的指定属性名、把List转换成Map。其中key为指定属性名所对应的值, value为集合元素。<br>
	 * 若多个元素有相同的属性值、则value为最后处理的元素。
	 * @param beanList List集合
	 * @param keyProp 指定为key的属性名
	 * @return
	 */
	public static <K, T> Map<K, T> listToMap(List<T> beanList, String keyProp) {
		Map<K, T> result = new LinkedHashMap<K, T>();
		if(!CheckUtil.isEmpty(beanList) && !CheckUtil.isEmpty(keyProp)){
			for(T bean : beanList){
				try {
					@SuppressWarnings("unchecked")
					K val = (K) PropertyUtils.getProperty(bean, keyProp);
					if(val != null){
						result.put(val, bean);
					}
					
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				
			}
		}
		return result;
	}
	
	/**
	 * 根据集合元素的指定属性名、对List的元素进行分组。其中key为指定属性名所对应的值、value为具有相同属性值的元素的集合。
	 * @param beanList List集合
	 * @param keyProp 进行分组的属性名。
	 * @return
	 */
	public static <K, T> Map<K, List<T>> groupListBy(List<T> beanList, String keyProp) {
		Map<K, List<T>> result = new LinkedHashMap<K, List<T>>();
		if(!CheckUtil.isEmpty(beanList) && !CheckUtil.isEmpty(keyProp)){
			for(T bean : beanList){
				try {
					@SuppressWarnings("unchecked")
					K val = (K) PropertyUtils.getProperty(bean, keyProp);
					if(val != null){
						List<T> list = result.get(val);
						if(CheckUtil.isEmpty(list)){
							list = new ArrayList<T>();
							result.put(val, list);
						}
						list.add(bean);
					}
					
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				
			}
		}
		return result;
	}

	/**
	 * 对List集合按照指定属性进行升序或者降序排列。<br>
	 * 由于作为输入参数的list集合可能不能修改、本方法的实现不对输入list进行排序、<br>
	 * 只返回排序后的输入list拷贝。
	 * @param list 
	 * @param prop 需要排序的字段。
	 * @param descOrAsc 降序或者升序。分别取值为desc或者asc、但是不区分大小写。
	 * @return 排序好的List集合。
	 */
	public static <T> List<T> orderBy(List<T> list, final String prop, final String descOrAsc) {
		return orderBy(list, prop, descOrAsc, false);
	}
	
	/**
	 * 对List集合按照指定属性进行原地升序或者降序排列。<br>
	 * @param list 
	 * @param prop 需要排序的字段。
	 * @param descOrAsc 降序或者升序。分别取值为desc或者asc、但是不区分大小写。
	 */
	public static <T> void orderByInplace(List<T> list, final String prop, final String descOrAsc) {
		orderBy(list, prop, descOrAsc, true);
	}
	
	
	private static <T> List<T> orderBy(List<T> list, final String prop, final String descOrAsc, boolean inplace) {
		
		Comparator<? super T> comp = new Comparator<T>() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public int compare(T o1, T o2) {
				try {					
					Object propVal1 = getProperty(o1, prop);
					Object propVal2 = getProperty(o2, prop);
					int result = 0;
					if(propVal1 == propVal2){
						result = 0;
					}else if(propVal1 == null && propVal2 != null){
						result = 1;
					}else if(propVal1 != null && propVal2 == null){
						result = -1;
					}else{						
						if(propVal1.getClass() != propVal2.getClass()){
							throw new RuntimeException("element's property value must have the same type.");
						}
						if(!Comparable.class.isAssignableFrom(propVal1.getClass())){
							throw new RuntimeException("element's property in the list not implement Comparable interface. The list cannot sort.");
						}
						result = ((Comparable)propVal1).compareTo(propVal2);
					}
					
					
					return "desc".equalsIgnoreCase(descOrAsc) ? -result : result;
				} catch (RuntimeException e) {
					throw e;
				} catch(Exception e){
					throw new RuntimeException(e.getMessage(), e);
				}		
				
			}
		};
				
		List<T> result = inplace ? list : new ArrayList<T>(list);
		Collections.sort(result, comp);
		return result;
	}
	

	private static Object getProperty(Object bean, String prop) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//cast map[key] --> map(key) which quired by PropertyUtils
		prop = prop.replace("[", "(").replace("]", ")");
		
		return PropertyUtils.getProperty(bean, prop);
	}

	/**
	 * 对集合元素进行过滤、返回符合过滤条件的子集。<br>
	 * 返回值的类型与输入集合类型一致、但是要求输入集合的class必须有一个无参数构造函数。否则抛出运行异常。
	 * 若输入集合为null、则返回值为null。
	 * @param list 输入集合
	 * @param condition
	 * @return 符合过滤条件的输入集合的子集。
	 */
	@SuppressWarnings("unchecked")
	public static <SET extends Collection<E>, E> SET filter(SET list, ElementFilter<E> condition) {
		if(list == null){
			return null;
		}
		SET result;
		try {
			result = (SET) list.getClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		if(list != null){
			for(E ele : list){
				if(condition.filter(ele)){
					result.add(ele);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 对map的entry元素进行过滤、返回符合过滤条件的新的map。
	 * @param maplist
	 * @param condition
	 * @return
	 */
	public static <K, V> Map<K, V> filterMap(Map<K, V> maplist, ElementFilter<Map.Entry<K, V>> condition) {
		Map<K, V> result = new LinkedHashMap<K, V>();
		if(maplist != null){
			for(Map.Entry<K, V> entry : maplist.entrySet()){
				if(condition.filter(entry)){
					result.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		return result;
	}

	/**
	 * 查询集合中符合过滤条件的第一个元素。
	 * @param list
	 * @param condition
	 * @return
	 */
	public static <T> T first(Collection<T> list, ElementFilter<T> condition) {
		if(list != null){
			for(T ele : list){
				if(condition.filter(ele)){
					return ele;
				}
			}
		}
		
		return null;
	}

	/**
	 * 选择集合中每个元素的指定属性值的集合并返回。
	 * @param list
	 * @param property
	 * @return
	 */
	public static <Element, PropType> List<PropType> select(Collection<Element> list, String property){
		return select(list, property, false, false);
	}
	/**
	 * 选择集合中每个元素的指定属性值的集合、排除掉空值后返回。<br>
	 * 空值定义为：null, 空字符串、size为0的集合或者数组。
	 * @param list
	 * @param property
	 * @return
	 */
	public static <Element, PropType> List<PropType> selectExcludeEmptyValue(Collection<Element> list, String property){
		return select(list, property, true, false);
	}
	/**
	 * 选择集合中每个元素的指定属性值的集合、去除重复值后返回。
	 * @param list
	 * @param property
	 * @return
	 */
	public static <Element, PropType> List<PropType> selectDistinct(Collection<Element> list, String property){
		return select(list, property, false, true);
	}
	/**
	 * 选择集合中每个元素的指定属性值的集合、排除掉空值并去除重复值后返回。
	 * 空值定义为：null, 空字符串、size为0的集合或者数组。
	 * @param list
	 * @param property
	 * @return
	 */
	public static <Element, PropType> List<PropType> selectDistinctExcludeEmptyValue(Collection<Element> list, String property){
		return select(list, property, true, true);
	}
	
	
	private static <Element, PropType> List<PropType> select(Collection<Element> list, String property, boolean excludeEmptyValue, boolean distinct) {
		List<PropType> result = new ArrayList<PropType>();
		if(list != null && !CheckUtil.isEmpty(property)){	
			try {
				for(Element bean : list){
					@SuppressWarnings("unchecked")
					PropType val = (PropType) getProperty(bean, property);
					if(excludeEmptyValue && CheckUtil.isEmpty(val) ){
						continue;
					}
					result.add(val);					
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} 
			
		}
		if(distinct){
			result = new ArrayList<PropType>(new LinkedHashSet<PropType>(result));
		}
		return result;
	}

		
	
	/**
	 * 将字符类型的数字list转换为真实的数字类型。<br>
	 * 目前支持基本数字类型的包装类以及BigInteger, BigDecimal等支持通过构造函数把string类型的
	 * 数字构造自身的数字类型。
	 * @param strNumbers
	 * @param toType
	 * @return
	 * @author mengxianming-2015年11月16日
	 */
	public static <N extends Number> List<N> toNumbers(List<String> strNumbers, Class<N> toType){
		if(CheckUtil.isEmpty(strNumbers)){
			return Collections.emptyList();
		}
		List<N> numbers = new ArrayList<N>(strNumbers.size());
		try {
			Constructor<N> c = toType.getConstructor(String.class);
			for(String num : strNumbers){				
				numbers.add(c.newInstance(num));
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return numbers;
	}
	
	/**
	 * 对两个list进行等值连接。
	 * @param left
	 * @param right
	 * @param leftJoinProp
	 * @param rightJoinProp
	 * @param process
	 * @throws Exception
	 * @see {@link JoinProcessorFactory}
	 */
	public static <E1, E2> void equijoin(List<E1> left, List<E2> right, String leftJoinProp, String rightJoinProp, JoinProcessor<E1, E2> process) throws Exception{
		Map<Object, List<E2>> rightMap = ListUtil.groupListBy(right, rightJoinProp);
		for(E1 bean : left){
			Object leftval = getProperty(bean, leftJoinProp);
			List<E2> matches = rightMap.get(leftval);
			if(matches != null){
				for(E2 rbean : matches){
					process.doJoin(bean, rbean);
				}
			}
		}
	}
	/**
	 * 对两个list进行自然等值连接。
	 * @param left
	 * @param right
	 * @param joinProp
	 * @param process
	 * @throws Exception
	 */
	public static <E1, E2> void naturalJoin(List<E1> left, List<E2> right, String joinProp, JoinProcessor<E1, E2> process) throws Exception{
		equijoin(left, right, joinProp, joinProp, process);
	}
	
	
}

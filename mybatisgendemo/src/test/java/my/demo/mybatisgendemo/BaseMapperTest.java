package my.demo.mybatisgendemo;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import com.vipshop.adp.common.dao.QCriteria;
import com.vipshop.adp.common.dao.SelectableCriteria;
import com.vipshop.adp.common.dao.QCriteria.Condition;

import junit.framework.Assert;

/**
 * 
 * 所有与本类所有定义方法相同的mapper接口的测试类
 * @author bricks.mong@2017年7月5日
 * @version @param <T>  
 * @since JDK 1.7
 */
@ComponentScan(excludeFilters={@Filter(Service.class)}, basePackages="com.vipshop.adp.common.dao" )
public abstract class BaseMapperTest<M, T> extends TxTestBase{	
	private M mapper;
	private Class<T> targetEntityClass;
	private ObjectMapper objectMapper = new ObjectMapper();
		

	public BaseMapperTest() {
	
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		mapper = (M) applicationContext.getBean(TestUtil.getGenericTypes(getClass())[0]);
		targetEntityClass = (Class<T>) TestUtil.getGenericTypes(getClass())[1];
	}
		
	public M getMapper() {
		return mapper;
	}
	
	/**
	 * @return
	 * @author mengxianming-2015年11月6日
	 */
	protected T createInsertEntity() {
		return (T) TestUtil.createDefaultEntity(targetEntityClass);
	}
	
	@Test
	public void testDeleteById() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
				 
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "deleteById", getId(record));
    	System.out.println(objectMapper.writeValueAsString(ret));
    }

	private Number getId(Object record) {
		Number id = ReflectionTestUtils.invokeMethod(record, "getId");
		return id;
	}

	@Test
	public void testInsert() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		System.out.println(objectMapper.writeValueAsString(record));
    }

	@Test
	public void testIinsertSelective() throws Exception{
		Object record = createInsertEntity();
    	int count = ReflectionTestUtils.invokeMethod(mapper, "insertSelective", record);
    	Assert.assertEquals(1, count);
		System.out.println(objectMapper.writeValueAsString(record));
    }

	@Test
	public void testSelectById() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
		Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectById", getId(record));
		System.out.println("before insert:" + objectMapper.writeValueAsString(record));
		System.out.println("select result:" + objectMapper.writeValueAsString(ret));
    }

	@Test
	public void testUpdateSelective() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
    	count =  ReflectionTestUtils.invokeMethod(mapper, "updateSelective", record);
    	Assert.assertEquals(1, count);
    	
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectById", getId(record));
		System.out.println("before update:" + objectMapper.writeValueAsString(record));
		System.out.println("select result:" + objectMapper.writeValueAsString(ret));
    }

	@Test
	public  void testUpdate() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
    	count =  ReflectionTestUtils.invokeMethod(mapper, "update", record);
    	Assert.assertEquals(1, count);
    	
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectById", getId(record));
		System.out.println("before update:" + objectMapper.writeValueAsString(record));
		System.out.println("select result:" + objectMapper.writeValueAsString(ret));
		
    }

	@Test
	public void testSelectByFeature() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
		Object record2 = createInsertEntity();
		count = ReflectionTestUtils.invokeMethod(mapper, "insert", record2);
		Assert.assertEquals(1, count);
		
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectByFeature", record);
    	System.out.println("selectByFeature result:" + objectMapper.writeValueAsString(ret));
    }

	@Test
	public void testSelectByFieldList() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
		Object record2 = createInsertEntity();
		count = ReflectionTestUtils.invokeMethod(mapper, "insert", record2);
		Assert.assertEquals(1, count);
		
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectByFieldList", "id", Arrays.asList(getId(record), getId(record2)));
    	System.out.println("selectByFieldList result:" + objectMapper.writeValueAsString(ret));
    }

	@Test
	public void testDeleteByFeature(){
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
    	int ret = ReflectionTestUtils.invokeMethod(mapper, "deleteByFeature", record);
    	Assert.assertEquals(1, ret);
    }

	@Test
	public void testSelectByCriteria() throws Exception{
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
		Object record2 = createInsertEntity();
		count = ReflectionTestUtils.invokeMethod(mapper, "insert", record2);
		Assert.assertEquals(1, count);
		
		QCriteria criteria = new QCriteria().and(Condition.in("id", Arrays.asList(getId(record), getId(record2))));
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectByCriteria", criteria);
    	System.out.println("selectByCriteria result:" + objectMapper.writeValueAsString(ret));
    }
	
	@Test
	public void testSelectRawByCriteria() throws Exception{
		Method testMethod = ReflectionUtils.findMethod(mapper.getClass(), "selectRawByCriteria", SelectableCriteria.class);
		if(testMethod == null){
			Assert.fail("待测试方法不存在: selectRawByCriteria");
		}
		
		Object record = createInsertEntity();
		int count = ReflectionTestUtils.invokeMethod(mapper, "insert", record);
		Assert.assertEquals(1, count);
		
		Object record2 = createInsertEntity();
		count = ReflectionTestUtils.invokeMethod(mapper, "insert", record2);
		Assert.assertEquals(1, count);
		
		SelectableCriteria criteria = new SelectableCriteria();
		criteria.and(Condition.in("id", Arrays.asList(getId(record), getId(record2))));
    	Object ret = ReflectionTestUtils.invokeMethod(mapper, "selectRawByCriteria", criteria);
    	System.out.println("selectRawByCriteria result:" + objectMapper.writeValueAsString(ret));
    	
    	criteria = new SelectableCriteria().select("count(1)");
		criteria.and(Condition.in("id", Arrays.asList(getId(record), getId(record2))));
    	ret = ReflectionTestUtils.invokeMethod(mapper, "selectRawByCriteria", criteria);
    	System.out.println("selectRawByCriteria result2:" + objectMapper.writeValueAsString(ret));
    }
	
}


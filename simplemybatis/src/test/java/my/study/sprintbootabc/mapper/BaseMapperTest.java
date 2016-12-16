package my.study.sprintbootabc.mapper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;

import my.study.sprintbootabc.TxTestBase;
import my.study.sprintbootabc.model.BaseModel;
import my.study.sprintbootabc.util.ComUtils;


public abstract class BaseMapperTest<T extends BaseModel<T>> extends TxTestBase{    
   

	
    @Test
  	public void testInsert() throws Exception {
      	T rec = createEntity();
  		int c = getBaseMapper().insert(rec);
  		
  		Assert.assertEquals(c, 1);		
  	}

    protected T createEntity(){
    	@SuppressWarnings("unchecked")
		Class<T> cls = (Class<T>) ComUtils.getGenericTypes(getClass())[0];
    	return ComUtils.createDefaultEntity(cls);    	
    }

	protected abstract BaseMapper<T> getBaseMapper();

	@Test
	public void testUpdate() throws Exception {
    	T rec = createEntity();
		int c = getBaseMapper().insert(rec);		
		Assert.assertEquals(c, 1);		
		
		T rec2 = getBaseMapper().selectById((Integer)rec.getKey());
		System.out.println(ComUtils.toJson(rec2));
		
		modifyEntity(rec2);
		getBaseMapper().update(rec2);
		
		T rec3 = getBaseMapper().selectById((Integer)rec2.getKey());
		Assert.assertEquals(ComUtils.toJson(rec2), ComUtils.toJson(rec3));
		
	}


    protected abstract void modifyEntity(T rec2);

	@Test
	public void testUpdateSelective() throws Exception {
		T rec = createEntity();
		int c = getBaseMapper().insert(rec);		
		Assert.assertEquals(c, 1);		
		
		T rec2 = createEntity();
		setKey(rec2, rec.getKey());		modifyEntity(rec2);	
		getBaseMapper().updateSelective(rec2);
		
		T rec3 = getBaseMapper().selectById((Integer)rec.getKey());
		System.out.println(ComUtils.toJson(rec3));
	}


	public void setKey(Object model, Object key){
		try {
			PropertyUtils.setProperty(model, "id", key);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			//this entity has not id property as pk, ignore error	
			System.out.println(e.getMessage());
		}
	}
	
    @Test
	public void testDelete() throws Exception {
    	T rec = createEntity();
		int c = getBaseMapper().insert(rec);		
		Assert.assertEquals(c, 1);		
		
		T rec2 = getBaseMapper().selectById((Integer)rec.getKey());
		System.out.println(ComUtils.toJson(rec2));
		
		T rec3 = createEntity();
		setKey(rec3, rec.getKey());
		Assert.assertEquals(1, getBaseMapper().delete(rec3));
		
		Assert.assertNull(getBaseMapper().selectById((Integer)rec.getKey()));
	}


    @Test
	public void testSelectById() throws Exception {
    	T rec = createEntity();
		getBaseMapper().insert(rec);
		
		T rec2 = getBaseMapper().selectById((Integer)rec.getKey());
		Assert.assertNotNull(rec2);
		
		System.out.println(ComUtils.toJson(rec2));
	}
}
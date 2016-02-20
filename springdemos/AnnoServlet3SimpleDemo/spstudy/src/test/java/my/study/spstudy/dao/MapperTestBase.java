/**
 * 
 */
package my.study.spstudy.dao;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.Assert;
import my.study.spstudy.TxTestBase;
import my.study.spstudy.dao.QCriteria.Condition;
import my.study.spstudy.util.TestUtil;

/**
 * @author mengxianming-2015年11月6日
 *
 */
public abstract class MapperTestBase<Mapper extends BaseMapper<Entity>, Entity> extends TxTestBase{
	
	
	public abstract Mapper getBaseMapper();
	
	
	
	/**
	 * @return
	 * @author mengxianming-2015年11月6日
	 */
	@SuppressWarnings("unchecked")
	protected Entity createInsertEntity() {
		return (Entity) TestUtil.createDefaultEntity(TestUtil.getGenericTypes(getClass())[1]);
	}
	
	@Test
	public void testDelete() {
		Integer billId = null;
		int i = getBaseMapper().delete(billId);
		Assert.assertEquals(0, i);
	}

	@Test
	public void testInsert() {
		Entity record = createInsertEntity();
		
		getBaseMapper().insert(record);
		
	}
	
	@Test
	public void testSelectById() {
		Entity ent = getBaseMapper().selectById(-1);
		Assert.assertEquals(null, ent);
	}

	@Test
	public void testUpdate() {
		Entity record = createInsertEntity();
		
		getBaseMapper().insert(record);
		getBaseMapper().update(record);
	}
	
	@Test
	public void testSelectByFeature() {
		Entity record = createInsertEntity();
		
		getBaseMapper().selectByFeature(record);
	}
	
	@Test
	public void testSelectByFieldList() {
		getBaseMapper().selectByFieldList("id", Arrays.asList(1));
	}
	
	@Test
	public void testDeleteByFeature() {
		Entity record = createInsertEntity();
		getBaseMapper().insert(record);
		
		getBaseMapper().deleteByFeature(record);
	}
	
	@Test
	public void testSelectByCriteria() {
		QCriteria crt = new QCriteria().and(Condition.eq("id", 1));
		getBaseMapper().selectByCriteria(crt);
	}
	

}

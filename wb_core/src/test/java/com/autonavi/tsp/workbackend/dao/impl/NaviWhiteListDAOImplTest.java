/**
 * 
 */
package com.autonavi.tsp.workbackend.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.autonavi.tsp.atsp.core.DaoTestBase;
import com.autonavi.tsp.workbackend.constant.NaviWhiteList_TYPE;
import com.autonavi.tsp.workbackend.dao.define.NaviWhiteListDAO;
import com.autonavi.tsp.workbackend.model.NaviWhiteList;
import com.autonavi.tsp.workbackend.model.NaviWhiteListExample;

/**
 * @author mengxianming
 *
 * 2015年7月10日
 */
public class NaviWhiteListDAOImplTest extends DaoTestBase{
	@Autowired
	NaviWhiteListDAO daoImpl;

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#countByExample(java.lang.Object)}.
	 */
	@Test
	public void testCountByExample() {
		NaviWhiteList record = insertTestData();
		
		NaviWhiteListExample example = new NaviWhiteListExample();
		example.createCriteria().andUserIdEqualTo(record.getUserId());
		int count = daoImpl.countByExample(example);
		Assert.assertEquals(1, count);
	}

	/**
	 * @return
	 */
	private NaviWhiteList insertTestData() {
		NaviWhiteList record = new NaviWhiteList();
		record.setUserId("testUser");
		record.setType(NaviWhiteList_TYPE.USERID.getValue());
		int c = daoImpl.insert(record);
		System.out.print("change record num:" + c);
		Assert.assertEquals(1, c);
		return record;
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#deleteByExample(java.lang.Object)}.
	 */
	@Test
	public void testDeleteByExample() {
		NaviWhiteList record = insertTestData();
		
		NaviWhiteListExample example = new NaviWhiteListExample();
		example.createCriteria().andUserIdEqualTo(record.getUserId());
		int count = daoImpl.deleteByExample(example);
		Assert.assertEquals(1, count);
		
		Assert.assertEquals(null, daoImpl.selectByPrimaryKey(record.getId()));
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#deleteByPrimaryKey(java.lang.Object)}.
	 */
	@Test
	public void testDeleteByPrimaryKey() {
		NaviWhiteList record = insertTestData();

		int count = daoImpl.deleteByPrimaryKey(record.getId());
		Assert.assertEquals(1, count);

		Assert.assertEquals(null, daoImpl.selectByPrimaryKey(record.getId()));
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		NaviWhiteList record = insertTestData();
		Assert.assertEquals(record.getId(), daoImpl.selectByPrimaryKey(record.getId()).getId());
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#batchInsert(List)
	 */
	@Test
	public void testBatchInsert() {
		NaviWhiteList record = new NaviWhiteList();
		record.setUserId("testUser");
		record.setType(NaviWhiteList_TYPE.USERID.getValue());
		
		NaviWhiteList record2 = new NaviWhiteList();
		record2.setUserId("testUser2");
		record2.setType(NaviWhiteList_TYPE.USERID.getValue());
		
		int c = daoImpl.insertBatch(Arrays.asList(record, record2));
		Assert.assertEquals(2, c);
		
		Assert.assertEquals(record.getUserId(), daoImpl.selectByPrimaryKey(record.getId()).getUserId());
		Assert.assertEquals(record2.getUserId(), daoImpl.selectByPrimaryKey(record2.getId()).getUserId());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#insertSelective(java.lang.Object)}.
	 */
	@Test
	public void testInsertSelective() {
		NaviWhiteList record = new NaviWhiteList();
		record.setUserId("testUser");
		record.setType(NaviWhiteList_TYPE.USERID.getValue());
		int c = daoImpl.insertSelective(record);
		Assert.assertEquals(1, c);
		
		Assert.assertEquals(record.getCreateId(), daoImpl.selectByPrimaryKey(record.getId()).getCreateId());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#selectByExample(java.lang.Object)}.
	 */
	@Test
	public void testSelectByExampleExample() {
		NaviWhiteList record = insertTestData();

		NaviWhiteListExample example = new NaviWhiteListExample();
		example.createCriteria().andUserIdEqualTo(record.getUserId());
		List<NaviWhiteList> dbrecords = daoImpl.selectByExample(example);
		Assert.assertEquals(1, dbrecords.size());

		Assert.assertEquals(record.getId(), dbrecords.get(0).getId());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#selectByPrimaryKey(java.lang.Object)}.
	 */
	@Test
	public void testSelectByPrimaryKey() {
		NaviWhiteList record = insertTestData();
		NaviWhiteList dbrecord = daoImpl.selectByPrimaryKey(record.getId());
		
		Assert.assertEquals(record.getId(), dbrecord.getId());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#updateByExampleSelective(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testUpdateByExampleSelective() {
		NaviWhiteList record = insertTestData();
		record.setImei("added imei");

		NaviWhiteListExample example = new NaviWhiteListExample();
		example.createCriteria().andUserIdEqualTo(record.getUserId());
		int c = daoImpl.updateByExampleSelective(record, example);
		Assert.assertEquals(1, c);

		NaviWhiteList dbrecord = daoImpl.selectByPrimaryKey(record.getId());

		Assert.assertEquals(record.getImei(), dbrecord.getImei());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#updateByExample(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testUpdateByExample() {
		NaviWhiteList record = insertTestData();
		record.setImei("added imei");

		NaviWhiteListExample example = new NaviWhiteListExample();
		example.createCriteria().andUserIdEqualTo(record.getUserId());
		int c = daoImpl.updateByExample(record, example);
		Assert.assertEquals(1, c);

		NaviWhiteList dbrecord = daoImpl.selectByPrimaryKey(record.getId());

		Assert.assertEquals(record.getImei(), dbrecord.getImei());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#updateByPrimaryKeySelective(java.lang.Object)}.
	 */
	@Test
	public void testUpdateByPrimaryKeySelective() {
		NaviWhiteList record = insertTestData();
		record.setImei("added imei");
		int c = daoImpl.updateByPrimaryKeySelective(record);
		Assert.assertEquals(1, c);
		
		NaviWhiteList dbrecord = daoImpl.selectByPrimaryKey(record.getId());
		Assert.assertEquals(record.getImei(), dbrecord.getImei());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#updateByPrimaryKey(java.lang.Object)}.
	 */
	@Test
	public void testUpdateByPrimaryKey() {
		NaviWhiteList record = insertTestData();
		record.setImei("added imei");
		int c = daoImpl.updateByPrimaryKey(record);
		Assert.assertEquals(1, c);
		
		NaviWhiteList dbrecord = daoImpl.selectByPrimaryKey(record.getId());
		Assert.assertEquals(record.getImei(), dbrecord.getImei());
	}
	
	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#batchUpdateByPrimaryKeySelective(List)}.
	 */
	@Test
	public void testBatchUpdateByPrimaryKeySelective() {
		NaviWhiteList record = insertTestData();
		NaviWhiteList record2 = insertTestData();
		record.setImei("added imei");
		record2.setImei("added imei2");
		
		int c = daoImpl.updateBatchByPrimaryKeySelective(Arrays.asList(record, record2));
		Assert.assertEquals(2, c);
		
		Assert.assertEquals(record.getImei(), daoImpl.selectByPrimaryKey(record.getId()).getImei());
		Assert.assertEquals(record2.getImei(), daoImpl.selectByPrimaryKey(record2.getId()).getImei());
	}

	/**
	 * Test method for {@link com.autonavi.tsp.workbackend.dao.impl.BaseDAOImpl#selectByExampleWithPager(java.lang.Object, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testSelectByExampleWithPager() {
		NaviWhiteList record = insertTestData();
		NaviWhiteList record2 = insertTestData();
		Assert.assertNotEquals(record.getId(), record2.getId());
		
		NaviWhiteListExample example = new NaviWhiteListExample();
		example.createCriteria().andUserIdEqualTo(record.getUserId());
		
		List<NaviWhiteList> list = daoImpl.selectByExampleWithPager(example, null, null);		
		Assert.assertEquals(2, list.size());
		
		list = daoImpl.selectByExampleWithPager(example, 1, 1);		
		Assert.assertEquals(1, list.size());
		
		list = daoImpl.selectByExampleWithPager(example, 1, 10);		
		Assert.assertEquals(2, list.size());
		
		list = daoImpl.selectByExampleWithPager(example, 2, 10);		
		Assert.assertEquals(0, list.size());
		
	}

}

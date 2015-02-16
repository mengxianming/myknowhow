/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.dao.impl;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.autoprodtool.common.TxTestBase;
import com.study.autoprodtool.dao.RestrictionProvider;
import com.study.autoprodtool.dao.UserDAO;
import com.study.autoprodtool.entity.User;
import com.study.autoprodtool.form.FilterListCriteria;
import com.study.autoprodtool.form.UserForm;

/**
 * Descriptions
 *
 * @version 2015-2-16
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class UserDAOImplTest extends TxTestBase{
	@Autowired
	private UserDAO userDAO;

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectOne(java.io.Serializable)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectOne() throws Exception {
		Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		
		User user = userDAO.selectOne(id);
		Assert.assertTrue(user != null);
		Assert.assertEquals(user.getId(), id);
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectList(com.study.autoprodtool.dao.RestrictionProvider)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectList() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectList(new RestrictionProvider() {
			
			@Override
			public void addRestriction(Criteria criteria) {
				criteria.add(Restrictions.in("id", new Object[]{id, id2}));
				
			}
			
			@Override
			public void addPager(Criteria criteria) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addOrder(Criteria criteria) {
				// TODO Auto-generated method stub
				
			}
		});
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(inList(id, list, "id"));
		Assert.assertTrue(inList(id2, list, "id"));
	}
	
	

	/**
	 * @param id
	 * @param list
	 * @param string
	 * @return
	 * @throws Exception 
	 */
	private boolean inList(Object value, List<?> list, String prop) throws Exception {
		Set<Object> vals = new LinkedHashSet<Object>();
		if(list != null){
			for(Object bean : list){
				if(prop != null && prop.length() > 0){
					vals.add(PropertyUtils.getProperty(bean, prop));
				}else{
					vals.add(bean);
				}
			}
		}
		return vals.contains(value);
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#insert(com.study.autoprodtool.entity.DBEntity)}.
	 * @throws Exception 
	 */
	@Test
	public void testInsert() throws Exception {
		User entity = new User();
		entity.setName("meng");
		userDAO.insert(entity);
		
		Assert.assertTrue(entity.getId() != null);
		Map<String, Object> dbentity = jdbcTemplate.queryForMap("select * from tb_user where id=?", entity.getId());
		Assert.assertTrue(dbentity != null);
		Assert.assertEquals(dbentity.get("id"), entity.getId());
		Assert.assertEquals(dbentity.get("name"), entity.getName());
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#update(com.study.autoprodtool.entity.DBEntity)}.
	 * @throws Exception 
	 */
	@Test
	public void testUpdate() throws Exception {
		User entity = new User();
		entity.setId(1L);
		entity.setName("meng");
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", entity.getId(), entity.getName());
		
		//do update
		entity.setName("meng2");
		userDAO.update(entity);
		
		//select from db and compare
		Map<String, Object> dbentity = jdbcTemplate.queryForMap("select * from tb_user where id=?", entity.getId());
		Assert.assertEquals(dbentity.get("id"), entity.getId());
		Assert.assertEquals(dbentity.get("name"), entity.getName());
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#delete(com.study.autoprodtool.entity.DBEntity)}.
	 * @throws Exception 
	 */
	@Test
	public void testDelete() throws Exception {
		User entity = new User();
		entity.setId(1L);
		entity.setName("meng");
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", entity.getId(), entity.getName());
		
		//do delete
		userDAO.delete(entity);
		
		//select from db and compare
		Long size = jdbcTemplate.queryForObject("select count(*) from tb_user where id=?", Long.class, entity.getId());
		Assert.assertTrue(size == 0);
	}
	

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListSize(com.study.autoprodtool.dao.RestrictionProvider)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectListSize() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		int size = userDAO.selectListSize(new RestrictionProvider() {
			
			@Override
			public void addRestriction(Criteria criteria) {
				criteria.add(Restrictions.in("id", new Object[]{id, id2}));
				
			}
			
			@Override
			public void addPager(Criteria criteria) {
				
				
			}
			
			@Override
			public void addOrder(Criteria criteria) {
				
				
			}
		});
		Assert.assertEquals(2, size);
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectAll(java.lang.Integer, java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectAll() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectAll(null, null);
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(inList(id, list, "id"));
		Assert.assertTrue(inList(id2, list, "id"));
	}
	
	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectAll(java.lang.Integer, java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectAllPager() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectAll(0, 1);
		Assert.assertEquals(1, list.size());
		Assert.assertTrue(list.get(0).getId() == id || list.get(0).getId() == id2);
	}
	
	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectAll(java.lang.Integer, java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectAllPagerBigLimit() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectAll(0, 100);
		Assert.assertEquals(2, list.size());
	}
	
	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectAll(java.lang.Integer, java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectAllPagerBigOffset() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectAll(100, 2);
		Assert.assertEquals(0, list.size());
	}
	

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByField(java.lang.String, java.lang.Object[])}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectListByField() throws Exception {
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id, "meng");
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectListByField("id", new Object[]{id, id2});
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(inList(id, list, "id"));
		Assert.assertTrue(inList(id2, list, "id"));
		
		list = userDAO.selectListByField("id", new Object[]{id});
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(id, list.get(0).getId());
		
		list = userDAO.selectListByField("name", new Object[]{"meng", "meng2"});
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(inList("meng", list, "name"));
		Assert.assertTrue(inList("meng2", list, "name"));
	}
	
	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByField(java.lang.String, java.lang.Object[])}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectListByFieldAssociate() throws Exception {
		jdbcTemplate.update("insert into tb_company(id, name) values(?, ?)", 1L, "companyName");
		
		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id, "meng", 1L);
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");
		
		List<User> list = userDAO.selectListByField("company.name", new Object[]{"companyName"});
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("companyName", list.get(0).getCompany().getName());		
	}
	

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByExample(com.study.autoprodtool.entity.DBEntity)}.
	 */
	@Test
	public void testSelectListByExampleT() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByKeyword(java.lang.String, java.lang.String[])}.
	 */
	@Test
	public void testSelectListByKeywordStringStringArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByField(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testSelectListByFieldStringObjectArrayIntegerInteger() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByExample(com.study.autoprodtool.entity.DBEntity, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testSelectListByExampleTIntegerInteger() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectListByKeyword(java.lang.String, java.lang.String[], java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testSelectListByKeywordStringStringArrayIntegerInteger() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectIdList(com.study.autoprodtool.dao.RestrictionProvider)}.
	 */
	@Test
	public void testSelectIdList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.study.autoprodtool.dao.impl.CrudDAOImpl#selectFiledList(java.lang.String, com.study.autoprodtool.dao.RestrictionProvider)}.
	 * @throws Exception 
	 */
	@Test
	public void testSelectFiledList() throws Exception {
		jdbcTemplate.update("insert into tb_company(id, name) values(?, ?)", 1L, "companyName");

		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id, "meng", 1L);
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name) values(?, ?)", id2, "meng2");

		List<String> list = userDAO.selectFiledList("name", new RestrictionProvider() {

			@Override
			public void addRestriction(Criteria criteria) {


			}

			@Override
			public void addPager(Criteria criteria) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addOrder(Criteria criteria) {
				// TODO Auto-generated method stub

			}
		});
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(inList("meng", list, null));		
	}
	
	@Test
	public void testSelectFiledListAssociate() throws Exception {
		jdbcTemplate.update("insert into tb_company(id, name) values(?, ?)", 1L, "companyName");

		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id, "meng", 1L);
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id2, "meng2", null);

		List<String> list = userDAO.selectFiledList("company.name", new RestrictionProvider() {

			@Override
			public void addRestriction(Criteria criteria) {

			}

			@Override
			public void addPager(Criteria criteria) {
				// TODO Auto-generated method stub

			}

			@Override
			public void addOrder(Criteria criteria) {
				// TODO Auto-generated method stub

			}
		});
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(inList("companyName", list, null));	
		Assert.assertTrue(inList(null, list, null));		
	}
	
	@Test
	public void testSelectFiledListAssociate2() throws Exception {
		jdbcTemplate.update("insert into tb_company(id, name) values(?, ?)", 1L, "testCompanyName");
		jdbcTemplate.update("insert into tb_company(id, name) values(?, ?)", 2L, "testCompanyName2");

		final Long id = 1L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id, "meng", 1L);
		final Long id2 = 2L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id2, "meng2", 2L);
		final Long id3 = 3L;
		jdbcTemplate.update("insert into tb_user(id, name, company_id) values(?, ?, ?)", id3, "meng3", null);

		FilterListCriteria<UserForm> listCriteria= new FilterListCriteria<UserForm>(UserForm.class);
		listCriteria.setFilterNames(Arrays.asList("companyName"));
		UserForm filter = new UserForm();
		filter.setCompanyName("testCompanyName");
		listCriteria.setFilter(filter);
		List<String> list = userDAO.selectFiledList("company.name", listCriteria.toRestrictionProvider());
		Assert.assertEquals(1, list.size());
		Assert.assertTrue(inList("testCompanyName", list, null));
	}

}

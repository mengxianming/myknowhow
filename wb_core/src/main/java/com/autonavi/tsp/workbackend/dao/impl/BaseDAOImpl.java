package com.autonavi.tsp.workbackend.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dao.define.CustomSqlMapper;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;

public class BaseDAOImpl<Entity, Example, Key> extends SqlSessionDaoSupport implements BaseDAO<Entity, Example, Key> {
		
	public static final int BATCH_DEAL_NUM = 20;
	
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	protected CustomSqlMapper customSqlMapper;

	/* (non-Javadoc)
	 * @see org.mybatis.spring.support.SqlSessionDaoSupport#setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory)
	 */
	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#countByExample(java.lang.Object)
	 */
	@Override
	public int countByExample(Example example) {		
		return getSqlSession().selectOne(getStatement("countByExample"), example);
	}

	/**
	 * @param string
	 * @return
	 */
	private String getStatement(String statementId) {		
		for(Class<?> intf : getClass().getInterfaces()){
			String fullname = intf.getName();
			Pattern p = Pattern.compile("(.+)define\\.(.+)DAO");
			Matcher rs = p.matcher(fullname);		
			if(rs.matches()){
				return rs.group(1) + rs.group(2) + "Mapper." + statementId;
			}
			
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#deleteByExample(java.lang.Object
	 * )
	 */
	@Override
	public int deleteByExample(Example example) {
		return getSqlSession().delete(getStatement("deleteByExample"), example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#deleteByPrimaryKey(java.lang
	 * .Object)
	 */
	@Override
	public int deleteByPrimaryKey(Key key) {
		return getSqlSession().delete(getStatement("deleteByPrimaryKey"), key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.autonavi.tsp.workbackend.dao.BaseDAO#insert(java.lang.Object)
	 */
	@Override
	public int insert(Entity record) {
		return getSqlSession().insert(getStatement("insert"), record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#insertSelective(java.lang.Object
	 * )
	 */
	@Override
	public int insertSelective(Entity record) {
		return getSqlSession().insert(getStatement("insertSelective"), record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#selectByExample(java.lang.Object
	 * )
	 */
	@Override
	public List<Entity> selectByExample(Example example) {
		return getSqlSession().selectList(getStatement("selectByExample"), example);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#selectByPrimaryKey(java.lang
	 * .Object)
	 */
	@Override
	public Entity selectByPrimaryKey(Key key) {
		return getSqlSession().selectOne(getStatement("selectByPrimaryKey"), key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#updateByExampleSelective(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public int updateByExampleSelective(Entity record, Example example) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("example", example);
		return getSqlSession().update(getStatement("updateByExampleSelective"), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#updateByExample(java.lang.Object
	 * , java.lang.Object)
	 */
	@Override
	public int updateByExample(Entity record, Example example) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("example", example);
		return getSqlSession().update(getStatement("updateByExample"), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#updateByPrimaryKeySelective(
	 * java.lang.Object)
	 */
	@Override
	public int updateByPrimaryKeySelective(Entity record) {
		return getSqlSession().update(getStatement("updateByPrimaryKeySelective"), record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.autonavi.tsp.workbackend.dao.BaseDAO#updateByPrimaryKey(java.lang
	 * .Object)
	 */
	@Override
	public int updateByPrimaryKey(Entity record) {
		return getSqlSession().update(getStatement("updateByPrimaryKey"), record);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.BaseDAO#selectByExampleWithPager(java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Entity> selectByExampleWithPager(Example example, Integer pageNo, Integer pageSize) {		
		if(pageNo != null && pageNo > 0 && pageSize != null && pageSize > 0){
			PageHelper.startPage(pageNo, pageSize);
			return (Page<Entity>) getSqlSession().selectList(getStatement("selectByExample"), example);
		}

		Page<Entity> page = new Page<Entity>();
		List<Entity> list = getSqlSession().selectList(getStatement("selectByExample"), example);		
		page.addAll(list);
		//相当于查询第一页
		page.setPageNum(1);
		//这种情况相当于pageSize=total
		page.setPageSize(page.size());
		//仍然要设置total
		page.setTotal(page.size());
		//返回结果仍然为Page类型 - 便于后面对接收类型的统一处理
		return page;

	}

	public int insertBatch(List<Entity> entities) {  
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);  
		int i = 0;  
		for(int cnt = entities.size(); i < cnt; i++) {  
			batchSession.insert(getStatement("insert"), entities.get(i));  
			if((i + 1) % BATCH_DEAL_NUM == 0) {//BATCH_DEAL_NUM为批量提交的条数  
				batchSession.flushStatements();  
			}  
		}  
		batchSession.flushStatements();  
		batchSession.close();  
		return i;  
	}  

	public int updateBatchByPrimaryKeySelective(List<Entity> list) {  
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);  
		int i = 0;  
		for(int cnt = list.size(); i < cnt; i++) {  
			batchSession.update(getStatement("updateByPrimaryKeySelective"), list.get(i));  
			if((i + 1) % BATCH_DEAL_NUM == 0) {  
				batchSession.flushStatements();  
			}  
		}  
		batchSession.flushStatements();  
		batchSession.close();  
		return i;  
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.BaseDAO#selectByExampleWithLimit(java.lang.Object, java.lang.Integer)
	 */
	@Override
	public List<Entity> selectByExampleWithLimit(Example example, Integer limit) {
		if(limit != null){
			PageHelper.startPage(1, limit, false);
		}
		return getSqlSession().selectList(getStatement("selectByExample"), example);		
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.BaseDAO#selectOneByExample(java.lang.Object)
	 */
	@Override
	public Entity selectOneByExample(Example example) {
		List<Entity> list = selectByExample(example);
		if(list == null || list.size() == 0){
			return null;
		}
		if(list.size() > 1){
			throw new RuntimeException("expect only one record but found " + list.size());
		}
		return list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.dao.define.BaseDAO#selectFirstOneByExample(java.lang.Object)
	 */
	@Override
	public Entity selectFirstOneByExample(Example example) {
		List<Entity> list = selectByExampleWithLimit(example, 1);
		if(list == null || list.size() == 0){
			return null;
		}
		
		return list.get(0);
	}  

}
package my.study.spstudy.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import my.study.spstudy.dao.BaseMapper;
import my.study.spstudy.dao.QCriteria;
import my.study.spstudy.util.TestUtil;

public abstract class BaseDAO<T, Mapper> extends SqlSessionDaoSupport implements BaseMapper<T> {  
    
    protected Class<?> getMapperInterface(){
    	return TestUtil.getGenericTypes(getClass())[1];
    }
    
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    	super.setSqlSessionFactory(sqlSessionFactory);
    }
    
	@Override
	public int delete(Integer id) {
		return getSqlSession().delete(getStatement("delete"), id);
	}
	
	protected String getStatement(String methodName) {
		return String.format("%s.%s",getMapperInterface().getName(), methodName);
	}
	
	@Override
	public int insert(T record) {
		return getSqlSession().insert(getStatement("insert"), record);
	}
	@Override
	public T selectById(Integer id) {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}
	@Override
	public int update(T record) {
		return getSqlSession().update(getStatement("update"), record);
	}
	@Override
	public List<T> selectByFeature(T feature) {
		return getSqlSession().selectList(getStatement("selectByFeature"), feature);
	}
	@Override
	public List<T> selectByFieldList(String field, List<?> values) {		
		return getSqlSession().selectList(getStatement("selectByFieldList"), TestUtil.toMap("field", field, "values", values));
	}
		
	@Override
	public int deleteByFeature(T feature) {
		return getSqlSession().delete(getStatement("deleteByFeature"), feature);
	}
	@Override
	public List<T> selectByCriteria(QCriteria criteria) {
		return getSqlSession().selectList(getStatement("selectByCriteria"), criteria);
	}
    
}


    

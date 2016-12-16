package my.study.sprintbootabc.dao.impl;

import my.study.sprintbootabc.dao.BaseDAO;
import my.study.sprintbootabc.mapper.BaseMapper;

public abstract class BaseDAOImpl<T> implements BaseDAO<T>{
	
	
	protected abstract BaseMapper<T> getBaseMapper();

	
	@Override
	public int insert(T record) throws Exception {
		return getBaseMapper().insert(record);
	}

	
	@Override
	public int update(T record) throws Exception {
		return getBaseMapper().update(record);
	}

	@Override
	public int updateSelective(T record) throws Exception {
		return getBaseMapper().updateSelective(record);
	}

	@Override
	public int delete(T rec) throws Exception {
		return getBaseMapper().delete(rec);
	}
	
	@Override
	public T selectById(Integer id) throws Exception {
		return getBaseMapper().selectById(id);
	}
	
}

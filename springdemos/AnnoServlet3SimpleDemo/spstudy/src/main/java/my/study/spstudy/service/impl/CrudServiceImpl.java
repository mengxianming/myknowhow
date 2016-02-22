package my.study.spstudy.service.impl;


import java.util.List;

import my.study.spstudy.dao.BaseMapper;
import my.study.spstudy.dao.QCriteria;
import my.study.spstudy.service.ICrudService;
import my.study.spstudy.vo.Page;

public abstract class CrudServiceImpl<T> implements ICrudService<T> {
		
	 public abstract BaseMapper<T> getBaseMapper();

	@Override
	public List<T> findList(Page page) throws Exception {		
		QCriteria crt = new QCriteria();
		if(page != null){
			crt.offset(page.getOffset());
			crt.limit(page.getLimit());
		}
		return getBaseMapper().selectByCriteria(crt);
	}

	@Override
	public T findById(Integer id) throws Exception {
		return getBaseMapper().selectById(id);
	}

	@Override
	public void create(T rec) throws Exception {
		getBaseMapper().insert(rec);
	}

	@Override
	public void update(T rec) throws Exception {
		getBaseMapper().update(rec);		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		getBaseMapper().delete(id);		
	}
	
}

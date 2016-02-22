package my.study.spstudy.service;

import java.util.List;

import my.study.spstudy.vo.Page;

public interface ICrudService<T> {
	List<T> findList(Page page) throws Exception;
	T findById(Integer id) throws Exception;
	void create(T rec) throws Exception;
	void update(T rec) throws Exception;
	void deleteById(Integer id) throws Exception;
}

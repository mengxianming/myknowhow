package my.study.sprintbootabc.dao;
public interface BaseDAO<T>{
	int insert(T record) throws Exception;
	int update(T record) throws Exception;
	int updateSelective(T record) throws Exception;
	int delete(T rec) throws Exception;
	T selectById(Integer id) throws Exception;
}


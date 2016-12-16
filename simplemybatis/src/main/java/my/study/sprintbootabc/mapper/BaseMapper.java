package my.study.sprintbootabc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;

import my.study.sprintbootabc.dao.qbc.QCriteria;
import my.study.sprintbootabc.mapper.sql.MyXMLLanguageDriver;
import my.study.sprintbootabc.model.Renter;

public interface BaseMapper<T>{
	int insert(T record) throws Exception;
	int update(T record) throws Exception;
	int updateSelective(T record) throws Exception;
	int delete(T rec) throws Exception;
	T selectById(Integer id) throws Exception;
	
	@Lang(MyXMLLanguageDriver.class)
	@Select("selectByCriteria")
	List<Renter> selectByCriteria(QCriteria crt) throws Exception;
}


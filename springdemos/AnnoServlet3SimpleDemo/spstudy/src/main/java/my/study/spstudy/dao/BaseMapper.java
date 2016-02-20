package my.study.spstudy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface BaseMapper<T> {
	int delete(Integer id);

    int insert(T record);

    T selectById(Integer id);

    int update(T record);

    List<T> selectByFeature(T feature);

    List<T> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(T feature);

    List<T> selectByCriteria(QCriteria criteria);
	
	
}

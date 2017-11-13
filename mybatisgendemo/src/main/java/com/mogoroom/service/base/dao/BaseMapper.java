package com.mogoroom.service.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface BaseMapper<E, P> {
    int deleteById(P id);

    int insert(E record);

    int insertSelective(E record);

    E selectById(P id);

    int updateSelective(E record);

    int update(E record);

    List<E> selectByFeature(E feature);

    List<E> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(E feature);

    List<E> selectByCriteria(QCriteria criteria);
    
    List<Map<String, Object>> selectRawByCriteria(SelectableCriteria criteria);
}
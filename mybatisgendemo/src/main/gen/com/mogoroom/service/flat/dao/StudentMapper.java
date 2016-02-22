package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import com.mogoroom.service.flat.domain.Student;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper extends BaseMapper<Student> {
    int delete(Integer id);

    int insert(Student record);

    Student selectById(Integer id);

    int update(Student record);

    List<Student> selectByFeature(Student feature);

    List<Student> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(Student feature);

    List<Student> selectByCriteria(QCriteria criteria);
}
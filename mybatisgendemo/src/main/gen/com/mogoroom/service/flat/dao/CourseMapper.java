package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import com.mogoroom.service.flat.domain.Course;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper extends BaseMapper<Course> {
    int delete(Integer id);

    int insert(Course record);

    Course selectById(Integer id);

    int update(Course record);

    List<Course> selectByFeature(Course feature);

    List<Course> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(Course feature);

    List<Course> selectByCriteria(QCriteria criteria);
}
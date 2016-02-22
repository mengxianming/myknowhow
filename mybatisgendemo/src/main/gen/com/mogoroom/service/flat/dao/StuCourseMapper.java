package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import com.mogoroom.service.flat.domain.StuCourse;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCourseMapper extends BaseMapper<StuCourse> {
    int delete(Integer id);

    int insert(StuCourse record);

    StuCourse selectById(Integer id);

    int update(StuCourse record);

    List<StuCourse> selectByFeature(StuCourse feature);

    List<StuCourse> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(StuCourse feature);

    List<StuCourse> selectByCriteria(QCriteria criteria);
}
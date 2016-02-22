package my.study.spstudy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.study.spstudy.dao.BaseMapper;
import my.study.spstudy.dao.StuCourseMapper;
import my.study.spstudy.domain.StuCourse;
import my.study.spstudy.service.IStuCourseService;

@Service
@Transactional
public class StuCourseServiceImpl extends CrudServiceImpl<StuCourse> implements IStuCourseService {
	@Autowired
	StuCourseMapper stuCourseeMapper;
	
	@Override
	public BaseMapper<StuCourse> getBaseMapper() {
		return stuCourseeMapper;
	}		
}

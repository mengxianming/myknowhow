package my.study.spstudy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.study.spstudy.dao.BaseMapper;
import my.study.spstudy.dao.CourseMapper;
import my.study.spstudy.domain.Course;
import my.study.spstudy.service.ICourseService;

@Service
@Transactional
public class CourseServiceImpl extends CrudServiceImpl<Course> implements ICourseService {
	@Autowired
	CourseMapper courseMapper;
	
	@Override
	public BaseMapper<Course> getBaseMapper() {
		return courseMapper;
	}		
}

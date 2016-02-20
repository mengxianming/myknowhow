package my.study.spstudy.dao;

import org.springframework.beans.factory.annotation.Autowired;

import my.study.spstudy.domain.Course;

public class CourseMapperTest extends MapperTestBase<CourseMapper, Course>{
	
	@Autowired
	CourseMapper mapper;

	@Override
	public CourseMapper getBaseMapper() {
		return mapper;
	}
	
}
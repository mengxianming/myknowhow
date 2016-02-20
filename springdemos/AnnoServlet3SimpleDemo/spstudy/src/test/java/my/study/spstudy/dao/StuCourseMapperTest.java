package my.study.spstudy.dao;

import org.springframework.beans.factory.annotation.Autowired;

import my.study.spstudy.domain.StuCourse;

public class StuCourseMapperTest extends MapperTestBase<StuCourseMapper, StuCourse>{
	
	@Autowired
	StuCourseMapper mapper;

	@Override
	public StuCourseMapper getBaseMapper() {
		return mapper;
	}
	
}
package my.study.spstudy.dao;

import org.springframework.beans.factory.annotation.Autowired;

import my.study.spstudy.domain.Student;

public class StudentMapperTest extends MapperTestBase<StudentMapper, Student>{
	
	@Autowired
	StudentMapper mapper;

	@Override
	public StudentMapper getBaseMapper() {
		return mapper;
	}
	
}
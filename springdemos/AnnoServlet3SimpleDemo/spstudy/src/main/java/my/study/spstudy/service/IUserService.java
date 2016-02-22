package my.study.spstudy.service;

import my.study.spstudy.domain.Student;

public interface IUserService extends ICrudService<Student>{
	void testException(Integer type) throws Exception;
}

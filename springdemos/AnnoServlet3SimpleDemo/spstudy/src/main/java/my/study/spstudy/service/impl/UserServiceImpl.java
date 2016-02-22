package my.study.spstudy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.study.spstudy.dao.BaseMapper;
import my.study.spstudy.dao.StudentMapper;
import my.study.spstudy.domain.Student;
import my.study.spstudy.service.IUserService;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<Student> implements IUserService {
	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public BaseMapper<Student> getBaseMapper() {
		return studentMapper;
	}
	
	@Override
	public void testException(Integer type) throws Exception {
		if(type == 1){
			throw new RuntimeException("runtime exeption for type " + type);
		}
		else {
			throw new Exception("common exeption for type " + type);
		}
		
	}
	
}

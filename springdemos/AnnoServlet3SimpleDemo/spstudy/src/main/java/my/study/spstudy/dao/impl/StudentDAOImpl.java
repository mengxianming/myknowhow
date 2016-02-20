package my.study.spstudy.dao.impl;

import org.springframework.stereotype.Repository;

import my.study.spstudy.dao.StudentMapper;
import my.study.spstudy.domain.Student;

@Repository
public class StudentDAOImpl extends BaseDAO<Student, StudentMapper> implements StudentMapper{
	
}
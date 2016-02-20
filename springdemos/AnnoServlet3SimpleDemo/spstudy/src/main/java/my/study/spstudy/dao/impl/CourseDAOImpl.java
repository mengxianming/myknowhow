package my.study.spstudy.dao.impl;

import org.springframework.stereotype.Repository;

import my.study.spstudy.dao.CourseMapper;
import my.study.spstudy.domain.Course;

@Repository
public class CourseDAOImpl extends BaseDAO<Course, CourseMapper> implements CourseMapper{
	
}
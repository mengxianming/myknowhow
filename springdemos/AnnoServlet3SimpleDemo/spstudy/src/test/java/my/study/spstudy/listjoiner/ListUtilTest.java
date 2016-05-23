package my.study.spstudy.listjoiner;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import my.study.spstudy.domain.Course;
import my.study.spstudy.domain.StuCourse;
import my.study.spstudy.domain.Student;
import my.study.spstudy.util.JoinProcessor;
import my.study.spstudy.util.JsonUtil;
import my.study.spstudy.util.ListUtil;

public class ListUtilTest {	
	
	private Student student(Integer id, String name, Integer age) {
		Student stu = new Student();
		stu.setAge(age);
		stu.setName(name);
		stu.setId(id);
		return stu;
	}

	

	@Test
	public void testEquijoin() throws Exception {
		List<Student> list = Arrays.asList(student(1, "meng", 11),
				student(2, "zhang", 21),
				student(3, "huang", 11));

		//		List<Course> list2 = Arrays.asList(course(1, "Chinese"), course(2, "english"));

		List<StuCourse> list3 = Arrays.asList(stuCourse(1, 1), stuCourse(1, 2));

		ListJoiner lj = ListJoiner.init(list, "stu").leftJoin(list3, "sc").on("stu.id==sc.studentId");
		lj.doBeanMerge(BeanMergerFactory.merge2LeftField("stu", "sc", "stuCourseList"));
		System.out.println(JsonUtil.toJson(list));
		Assert.assertEquals(4, lj.getRows().size());

		lj = ListJoiner.init(list, "stu").innerJoin(list3, "sc").on("stu.id==sc.studentId");
		Assert.assertEquals(2, lj.getRows().size());

		lj = ListJoiner.init(list, "stu").rightJoin(list3, "sc").on("stu.id==sc.studentId");
		Assert.assertEquals(list3.size(), lj.getRows().size());

	}

	private StuCourse stuCourse(Integer sid, Integer cid) {
		StuCourse rs = new StuCourse();
		rs.setCourseId(cid);
		rs.setStudentId(sid);
		return rs;
	}
	
	private Course course(Integer id, String name) {
		Course rs = new Course();
		rs.setId(id);
		rs.setName(name);
		return rs;
	}
	

	@Test
	public void testNaturalJoin() throws Exception {
		List<Student> list = Arrays.asList(student(1, "meng", 11),
				student(2, "zhang", 21),
				student(3, "huang", 11));
		
		List<Student> list2 = Arrays.asList(student(10, "meng", 11),
				student(20, "zhang", 21),
				student(30, "huang", 11));
	
		ListUtil.naturalJoin(list, list2, "name", new JoinProcessor<Student, Student>(){

			@Override
			public void doJoin(Student left, Student right) throws Exception {
				System.out.println(JsonUtil.toJson(new Object[]{left, right}));
				
			}
			
		});
		
		
	}

}

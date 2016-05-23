package my.study.spstudy.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import my.study.spstudy.domain.Course;
import my.study.spstudy.domain.StuCourse;
import my.study.spstudy.domain.Student;
import my.study.spstudy.util.ListUtil.ElementFilter;

public class ListUtilTest {	
	@Test
	public void testListToMap() {
		List<Student> stuList = stuList();
		System.out.println(JsonUtil.toJson(ListUtil.listToMap(stuList, "age")));
		System.out.println(JsonUtil.toJson(ListUtil.listToMap(stuList, "id")));
	}

	private List<Student> stuList() {
		List<Student> list = Arrays.asList(student(1, "meng", 11),
				student(2, "zhang", 21),
				student(3, "huang", 11));
		
		return new ArrayList<Student>(list);
	}

	private Student student(Integer id, String name, Integer age) {
		Student stu = new Student();
		stu.setAge(age);
		stu.setName(name);
		stu.setId(id);
		return stu;
	}

	@Test
	public void testGroupListBy() {
		List<Student> stuList = stuList();
		System.out.println(JsonUtil.toJson(ListUtil.groupListBy(stuList, "age")));
	}

	@Test
	public void testOrderBy() {
		List<Student> stuList = stuList();
		System.out.println(JsonUtil.toJson(ListUtil.orderBy(stuList, "age", "desc")));
		System.out.println(JsonUtil.toJson(ListUtil.orderBy(stuList, "age", "asc")));
	}

	@Test
	public void testOrderByInplace() {
		List<Student> stuList = stuList();
		ListUtil.orderByInplace(stuList, "age", "desc");
		System.out.println(JsonUtil.toJson(stuList));
		ListUtil.orderByInplace(stuList, "age", "asc");
		System.out.println(JsonUtil.toJson(stuList));
	}

	@Test
	public void testFilter() {
		List<Student> stuList = stuList();
		List<Student> result = ListUtil.filter(stuList, new ElementFilter<Student>(){

			@Override
			public boolean filter(Student ele) {
				return ele.getId() > 2;
			}
			
		});
		System.out.println(JsonUtil.toJson(result));
		
	}

	@Test
	public void testFilterMap() {
		List<Student> stuList = stuList();
		Map<Integer, Student> stuMap = ListUtil.listToMap(stuList, "id");
		Map<Integer, Student> result = ListUtil.filterMap(stuMap, new ElementFilter<Entry<Integer, Student>>(){

			@Override
			public boolean filter(Entry<Integer, Student> ele) {				
				return ele.getKey() > 2;
			}
			
		});
		System.out.println(JsonUtil.toJson(result));
	}

	@Test
	public void testFirst() {
		List<Student> stuList = stuList();
		Student result = ListUtil.first(stuList, new ElementFilter<Student>(){

			@Override
			public boolean filter(Student ele) {
				return ele.getId() > 2;
			}
			
		});
		System.out.println(JsonUtil.toJson(result));
	}

	@Test
	public void testSelect() {
		List<Student> stuList = stuList();
		List<String> result = ListUtil.select(stuList, "name");
		System.out.println(JsonUtil.toJson(result));
		
	}

	@Test
	public void testSelectExcludeEmptyValue() {
		List<Student> stuList = stuList();
		stuList.add(student(10, "", 201));
		List<Object> result = ListUtil.selectExcludeEmptyValue(stuList, "name");
		System.out.println(JsonUtil.toJson(result));
	}

	@Test
	public void testSelectDistinct() {
		List<Student> stuList = stuList();
		stuList.add(student(10, "meng", 201));
		List<Object> result = ListUtil.selectDistinct(stuList, "name");
		System.out.println(JsonUtil.toJson(result));
	}

	@Test
	public void testSelectDistinctExcludeEmptyValue() {
		List<Student> stuList = stuList();
		stuList.add(student(10, "meng", 201));
		stuList.add(student(110, "", 201));
		List<Object> result = ListUtil.selectDistinctExcludeEmptyValue(stuList, "name");
		System.out.println(JsonUtil.toJson(result));
	}

	@Test
	public void testToNumbers() {
		Object result = ListUtil.toNumbers(Arrays.asList("123", "1.00", "1.001"), Float.class);
		System.out.println(JsonUtil.toJson(result));
		
		result = ListUtil.toNumbers(Arrays.asList("123", "1.00", "1.001"), BigDecimal.class);
		System.out.println(JsonUtil.toJson(result));
		
	}

	@Test
	public void testEquijoin() throws Exception {
		List<Student> list = Arrays.asList(student(1, "meng", 11),
				student(2, "zhang", 21),
				student(3, "huang", 11));
		
		List<Course> list2 = Arrays.asList(course(1, "Chinese"), course(2, "english"));
		
		List<StuCourse> list3 = Arrays.asList(stuCourse(1, 1), stuCourse(1, 2));
	
		ListUtil.equijoin(list3, list2, "courseId", "id", JoinProcessorFactory.join2LeftField("course", StuCourse.class, Course.class));
		System.out.println(JsonUtil.toJson(list3));
		
		ListUtil.equijoin(list, list3, "id", "studentId", JoinProcessorFactory.join2LeftField("stuCourseList", Student.class, StuCourse.class));
		System.out.println(JsonUtil.toJson(list));
		
		
	}

	private StuCourse stuCourse(Integer cid, Integer sid) {
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

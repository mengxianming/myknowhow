package my.study.spstudy.listjoiner;

import java.util.Map;

import org.junit.Test;

import my.study.spstudy.domain.StuCourse;
import my.study.spstudy.domain.Student;
import my.study.spstudy.util.TestUtil;

public class JoinExpressionTest {

	@Test
	public void testEval() {
		JoinExpression expr = new JoinExpression("1 > 2");
		System.out.println(expr.eval(null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEval2() {
		JoinExpression expr = new JoinExpression("1 + 2");
		System.out.println(expr.eval(null));
	}

	@Test
	public void testEval3() {
		Student stu = student(1, "meng", 11);
		StuCourse sc = stuCourse(1, 1);
		Map<String, Object> ctx = TestUtil.toMap("stu", stu, "sc", sc);
		JoinExpression expr = new JoinExpression("stu.id==sc.studentId && stu.name=='meng'");
		System.out.println(expr.eval(ctx));
	}
	
	private Student student(Integer id, String name, Integer age) {
		Student stu = new Student();
		stu.setAge(age);
		stu.setName(name);
		stu.setId(id);
		return stu;
	}
	
	private StuCourse stuCourse(Integer sid, Integer cid) {
		StuCourse rs = new StuCourse();
		rs.setCourseId(cid);
		rs.setStudentId(sid);
		return rs;
	}
}

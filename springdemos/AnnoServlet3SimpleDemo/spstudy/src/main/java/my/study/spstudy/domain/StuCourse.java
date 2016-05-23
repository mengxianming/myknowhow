package my.study.spstudy.domain;

import java.io.Serializable;

public class StuCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer studentId;

    private Integer courseId;

    private String grade;
    
    private Student student;
    private Course course;
    
    public Course getCourse() {
		return course;
	}
    public void setCourse(Course course) {
		this.course = course;
	}
    public Student getStudent() {
		return student;
	}
    public void setStudent(Student student) {
		this.student = student;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }
}
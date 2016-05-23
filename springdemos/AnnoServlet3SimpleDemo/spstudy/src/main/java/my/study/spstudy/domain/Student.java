package my.study.spstudy.domain;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;
    
    private List<StuCourse> stuCourseList;
    
    public List<StuCourse> getStuCourseList() {
		return stuCourseList;
	}
    public void setStuCourseList(List<StuCourse> stuCourseList) {
		this.stuCourseList = stuCourseList;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
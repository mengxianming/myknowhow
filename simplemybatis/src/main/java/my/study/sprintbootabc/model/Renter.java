package my.study.sprintbootabc.model;

import my.study.sprintbootabc.dao.annotation.Column;
import my.study.sprintbootabc.dao.annotation.Entity;

@Entity(value="renter")
public class Renter extends BaseModel<Renter>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(isPK=true)
	private Integer id;
	private String name;
	private String phone;
	private Integer age;
	
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
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}


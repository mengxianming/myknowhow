package my.study.sprintbootabc.model;

import java.util.Date;

import my.study.sprintbootabc.dao.annotation.Column;
import my.study.sprintbootabc.dao.annotation.Entity;

@Entity(value="room")
public class Room extends BaseModel<Room>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(isPK=true)
	private Integer id;
	private String name;
	private Double area;
	private Date createTime;
	
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
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}


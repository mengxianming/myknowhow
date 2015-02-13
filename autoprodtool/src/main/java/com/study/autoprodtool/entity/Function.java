/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Descriptions
 *
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Entity
public class Function extends DBEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_function_id")
	@SequenceGenerator(name="seq_function_id", sequenceName="seq_function_id", allocationSize=1)	
	private Long id;
	@Column(unique=true)
	private String name;
	private Integer order;
	@ManyToOne
	private CategoryBig categoryBig;
	@ManyToOne
	private CategoryMid categoryMid;
	@ManyToOne
	private CategorySmall categorySmall;
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * @return the categoryBig
	 */
	public CategoryBig getCategoryBig() {
		return categoryBig;
	}
	/**
	 * @param categoryBig the categoryBig to set
	 */
	public void setCategoryBig(CategoryBig categoryBig) {
		this.categoryBig = categoryBig;
	}
	/**
	 * @return the categoryMid
	 */
	public CategoryMid getCategoryMid() {
		return categoryMid;
	}
	/**
	 * @param categoryMid the categoryMid to set
	 */
	public void setCategoryMid(CategoryMid categoryMid) {
		this.categoryMid = categoryMid;
	}
	/**
	 * @return the categorySmall
	 */
	public CategorySmall getCategorySmall() {
		return categorySmall;
	}
	/**
	 * @param categorySmall the categorySmall to set
	 */
	public void setCategorySmall(CategorySmall categorySmall) {
		this.categorySmall = categorySmall;
	}
	
	
	
}

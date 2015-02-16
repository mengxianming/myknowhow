/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.form;

import com.study.autoprodtool.entity.Function;

/**
 * Descriptions
 *
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class FunctionForm extends EntityForm<FunctionForm, Function>{
	private Long id;	
	private String name;
	private Integer order;
	
	@FieldMapping("categoryBig.id")	
	private Long categoryBigId;
	@FieldMapping("categoryBig.name")	
	private String categoryBigName;
	
	@FieldMapping("categoryMid.id")
	private Long categoryMidId;	
	@FieldMapping("categoryMid.name")
	private String categoryMidName;	
	
	@FieldMapping("categorySmall.id")
	private Long categorySmallId;
	@FieldMapping("categorySmall.name")
	private String categorySmallName;
	
	private String description;
	

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
	 * @return the categoryBigId
	 */
	public Long getCategoryBigId() {
		return categoryBigId;
	}
	/**
	 * @param categoryBigId the categoryBigId to set
	 */
	public void setCategoryBigId(Long categoryBigId) {
		this.categoryBigId = categoryBigId;
	}
	/**
	 * @return the categoryBigName
	 */
	public String getCategoryBigName() {
		return categoryBigName;
	}
	/**
	 * @param categoryBigName the categoryBigName to set
	 */
	public void setCategoryBigName(String categoryBigName) {
		this.categoryBigName = categoryBigName;
	}
	/**
	 * @return the categoryMidId
	 */
	public Long getCategoryMidId() {
		return categoryMidId;
	}
	/**
	 * @param categoryMidId the categoryMidId to set
	 */
	public void setCategoryMidId(Long categoryMidId) {
		this.categoryMidId = categoryMidId;
	}
	/**
	 * @return the categoryMidName
	 */
	public String getCategoryMidName() {
		return categoryMidName;
	}
	/**
	 * @param categoryMidName the categoryMidName to set
	 */
	public void setCategoryMidName(String categoryMidName) {
		this.categoryMidName = categoryMidName;
	}
	/**
	 * @return the categorySmallId
	 */
	public Long getCategorySmallId() {
		return categorySmallId;
	}
	/**
	 * @param categorySmallId the categorySmallId to set
	 */
	public void setCategorySmallId(Long categorySmallId) {
		this.categorySmallId = categorySmallId;
	}
	/**
	 * @return the categorySmallName
	 */
	public String getCategorySmallName() {
		return categorySmallName;
	}
	/**
	 * @param categorySmallName the categorySmallName to set
	 */
	public void setCategorySmallName(String categorySmallName) {
		this.categorySmallName = categorySmallName;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	
	
}

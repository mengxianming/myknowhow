/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.form;

import com.study.autoprodtool.entity.Division;

/**
 * Descriptions
 *
 * @version 2015-2-11
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class DivisionForm extends EntityForm<DivisionForm, Division>{
	private Long id;
	private String pparent;
	private String parent;
	private String name;
	@FieldMapping("company.id")
	private Long companyId;
	@FieldMapping("company.name")
	private String companyName;
	
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
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}
	/**
	 * @return the pparent
	 */
	public String getPparent() {
		return pparent;
	}
	/**
	 * @param pparent the pparent to set
	 */
	public void setPparent(String pparent) {
		this.pparent = pparent;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

}

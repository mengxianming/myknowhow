package com.study.autoprodtool.form;

import java.util.Date;

import com.study.autoprodtool.entity.User;

/**
 * Descriptions
 *
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class UserForm extends EntityForm<UserForm, User>{
	private String id;
	private String loginName;
	private String password;
	@FieldMapping("role.id")
	private Long roleId;
	@FieldMapping("role.name")
	private String roleName;
	private String name;
	private String nameKana;
	private String nameEnglish;
	private String nickname;
	
	@FieldMapping("company.id")
	private Long companyId;
	@FieldMapping("company.name")
	private String companyName;
	@FieldMapping("division.id")
	private Long divisionId;
	@FieldMapping("division.name")
	private String divisionName;
	@FieldMapping("division.parent")
	private String divisionParent;
	@FieldMapping("division.pparent")
	private String divisionPparent;
	
	private String email;
	private Short sumaryMailFlag;
	private Short articleMailFlag;
	
	@FieldMapping("agent.id")
	private Long agentId;
	@FieldMapping("agent.name")
	private String agentName;
		
	private Date lastLogin;	
	private Short status;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getSumaryMailFlag() {
		return sumaryMailFlag;
	}

	public void setSumaryMailFlag(Short sumaryMailFlag) {
		this.sumaryMailFlag = sumaryMailFlag;
	}

	public Short getArticleMailFlag() {
		return articleMailFlag;
	}

	public void setArticleMailFlag(Short articleMailFlag) {
		this.articleMailFlag = articleMailFlag;
	}

	/**
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
	}
	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionParent() {
		return divisionParent;
	}

	public void setDivisionParent(String divisionParent) {
		this.divisionParent = divisionParent;
	}

	public String getDivisionPparent() {
		return divisionPparent;
	}

	public void setDivisionPparent(String divisionPparent) {
		this.divisionPparent = divisionPparent;
	}
	
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}

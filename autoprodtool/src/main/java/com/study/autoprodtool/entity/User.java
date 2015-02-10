/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.entity;

import java.util.Date;

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
public class User extends DBEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_user_id")
	@SequenceGenerator(name="seq_user_id", sequenceName="seq_user_id", allocationSize=1)
	private Long id;
	private String loginName;
	private String password;
	@ManyToOne
	private Role role;
	private String name;
	private String nameKana;
	private String nameEnglish;
	private String nickname;
	
	@ManyToOne
	private Company company;
	@ManyToOne
	private Division division;
	
	private String email;
	private Short sumaryMailFlag;
	private Short articleMailFlag;
	
	@ManyToOne
	private User agent;
		
	private Date lastLogin;
	
	private Short status;
	
	/* (non-Javadoc)
	 * @see com.study.autoprodtool.entity.DBEntity#getId()
	 */
	@Override	
	public Long getId() {		
		return id;
	}
	/* (non-Javadoc)
	 * @see com.study.autoprodtool.entity.DBEntity#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
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

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
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

}

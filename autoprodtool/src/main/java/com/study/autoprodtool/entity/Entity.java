package com.study.autoprodtool.entity;

import java.util.Date;

import javax.persistence.Id;

/**
 * DBテーブルを表すエンティティ。
 * 全テーブルが共有している属性はこのクラスに格納する。
 * @author mengxm 2013-5-27 created
 *
 */
@javax.persistence.Entity
public abstract class Entity {
	@Id
	protected Long id;
	protected Date createTime;	
	protected Date updateTime;
	
	public Long getId() {		
		return id;
	}

	public void setId(Long key) {
		this.id = key;
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
		 
	
	public static <E extends Entity> E emptyEntity(Class<E> clazz, Long id){
		E ent;
		try {
			ent = clazz.newInstance();
		}
		catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
		
		ent.setId(id);
		return ent;
	}
}
 

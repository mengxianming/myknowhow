package com.study.autoprodtool.entity;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.study.autoprodtool.common.PropertyAccessException;

/**
 * DBテーブルを表すエンティティ。
 * 全テーブルが共有している属性はこのクラスに格納する。
 * @author mengxm 2013-5-27 created
 *
 */
@MappedSuperclass  
public abstract class DBEntity {
	protected Date createTime;	
	protected Date updateTime;
		
	abstract public Long getId();

	abstract public void setId(Long id);
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
	
	
	public void removeTransientsBeforeSave(){
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(this);
		//set properties base on setter, and check annotated method
		for(Method method : getClass().getMethods()){
			PropertyDescriptor desciptor = BeanUtils.findPropertyForMethod(method);				
			if(desciptor == null){
				continue;
			}
			String propName = desciptor.getName();
			if(method.isAnnotationPresent(ManyToOne.class)){					
				// check invalid transient references
				DBEntity refEntity = (DBEntity)beanWrapper.getPropertyValue(propName);
				if(refEntity != null && refEntity.getId() == null){
					//remove this reference entity by set it to null
					beanWrapper.setPropertyValue(propName, null);
				}
									
			}
		}
		
		//then check annotated field, override the value set by annotated method
		try {
			for(Field field : getClass().getDeclaredFields()){				
				if(field.isAnnotationPresent(ManyToOne.class)){
					// check invalid transient references				
					field.setAccessible(true);
					DBEntity refEntity = (DBEntity)field.get(this);
					if(refEntity != null && refEntity.getId() == null){
						//remove this reference entity by set it to null
						field.set(this, null);
					}
					
				}
			}
		}
		catch (Exception e) {
			throw new PropertyAccessException(e);
		}		
	}
		 
	
	public static <E extends DBEntity> E emptyEntity(Class<E> clazz, Long id){
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
 

/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.form;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.entity.DBEntity;

/**
 * Descriptions
 *
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public abstract class EntityForm<F extends EntityForm<?, ?>, E extends DBEntity> {
	private Class<E> dbentityClass;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public EntityForm() {
		Class<?>[] genericClasses = ComUtils.getGenericTypes(getClass());
		dbentityClass = (Class<E>)genericClasses[1];
	}
	
	@SuppressWarnings("unchecked")
	public F initFromEntity(E entity){	
		if(entity == null){
			return (F)this;
		}
		
		try {
			BeanWrapper formWrapper = PropertyAccessorFactory.forBeanPropertyAccess(this);
			formWrapper.setAutoGrowNestedPaths(true);
			BeanWrapper entityWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
			entityWrapper.setAutoGrowNestedPaths(true);
			
			//set properties base on setter, and check annotated method
			for(Method method : getClass().getMethods()){
				PropertyDescriptor desciptor = BeanUtils.findPropertyForMethod(method);				
				if(desciptor == null || desciptor.getWriteMethod() == null){
					continue;
				}
				String propName = desciptor.getName();
				FieldMapping annotation = method.getAnnotation(FieldMapping.class);
				if(annotation == null){					
					//set the same property from entity checking if readable
					if(entityWrapper.isReadableProperty(propName)){
						formWrapper.setPropertyValue(propName, entityWrapper.getPropertyValue(propName));
					}
										
				}else if(CheckUtil.isNull(annotation.value())){
					formWrapper.setPropertyValue(propName, entityWrapper.getPropertyValue(propName));
				}				
				else{
					//set the mapped property from entity, using the provided mapping info from the annotation
					String mappedName = annotation.value();
					formWrapper.setPropertyValue(propName, entityWrapper.getPropertyValue(mappedName));				
				}
			}
			
			//then check annotated field, override the value set by annotated method
			for(Field field : getClass().getDeclaredFields()){	
				FieldMapping annotation = field.getAnnotation(FieldMapping.class);
				if(annotation != null && !CheckUtil.isNull(annotation.value())){
					String mappedName = annotation.value();
					field.setAccessible(true);
					field.set(this, entityWrapper.getPropertyValue(mappedName));
				}
			}					
		}
		catch (Exception e) {			
			throw new FormEntityConversionException(e);
		}		
		
		return (F)this;
	}
	
	@SuppressWarnings("unchecked")
	public E toEntity(){		
		try {
			BeanWrapper formWrapper = PropertyAccessorFactory.forBeanPropertyAccess(this);
			BeanWrapper entityWrapper = PropertyAccessorFactory.forBeanPropertyAccess(dbentityClass.newInstance());
			entityWrapper.setAutoGrowNestedPaths(true);
			
			//set properties base on setter, and check annotated method
			for(Method method : getClass().getMethods()){
				PropertyDescriptor desciptor = BeanUtils.findPropertyForMethod(method);				
				if(desciptor == null){
					continue;
				}
				String propName = desciptor.getName();
				FieldMapping annotation = method.getAnnotation(FieldMapping.class);
				if(annotation == null){					
					//set the same property from form object checking if writable
					if(entityWrapper.isWritableProperty(propName)){
						entityWrapper.setPropertyValue(propName, formWrapper.getPropertyValue(propName));	
					}														
				}else if(CheckUtil.isNull(annotation.value())){
					entityWrapper.setPropertyValue(propName, formWrapper.getPropertyValue(propName));
				}else{
					//set the mapped property from form object, using the provided mapping info from the annotation
					String mappedName = annotation.value();
					entityWrapper.setPropertyValue(mappedName, formWrapper.getPropertyValue(propName));				
				}
			}
			
			//then check annotated field, override the value set by annotated method
			for(Field field : getClass().getDeclaredFields()){	
				FieldMapping annotation = field.getAnnotation(FieldMapping.class);
				if(annotation != null && !CheckUtil.isNull(annotation.value())){
					String mappedName = annotation.value();		
					field.setAccessible(true);
					entityWrapper.setPropertyValue(mappedName, field.get(this));				
				}
			}	
			
			return (E)entityWrapper.getWrappedInstance();
		}
		catch (Exception e) {			
			throw new FormEntityConversionException(e);
		}		
	}	

}

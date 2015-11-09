/**
 * 
 */
package com.autonavi.tsp.workbackend.util;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import com.autonavi.tsp.common.dto.BucSSOUserDto;
import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.AbstractEntity;
import com.autonavi.tsp.workbackend.util.page.PageHelper;

/**
 * @author mengxianming
 *
 * 2015年5月22日
 */
public class ServiceImplUtil {
	private static Log log = LogFactory.getLog(ServiceImplUtil.class);

	@SuppressWarnings("unchecked")
	public static <T, D extends Serializable> List<T> listByExample(Object mybatisExample , Object mybatisMapper, ListCriteria<D> listCriteria) throws CommonException {
		
		
		if(listCriteria.getPageIndex() != null && listCriteria.getPageSize() != null){
			//set pager 
			PageHelper.startPage(listCriteria.getPageIndex(), listCriteria.getPageSize(), true);
		}
		//set sorter
		if(!CheckUtil.isNull(listCriteria.getSortField())){
			//call method like: void NaviScreenDpiExample.setOrderByClause()
			Method method = ReflectionUtils.findMethod(mybatisExample.getClass(), "setOrderByClause", String.class);
			ReflectionUtils.invokeMethod(method, mybatisExample, String.format("%s %s",
					listCriteria.getSortField(), listCriteria.getSortOrder() == null ? "" :  listCriteria.getSortOrder()));
			
		}
		
		//set example conditions	
		//call method like: Criteria criteria = NaviScreenDpiExample.createCriteria()
		Method createCriteriaMethod = ReflectionUtils.findMethod(mybatisExample.getClass(), "createCriteria");
		Object criteria = ReflectionUtils.invokeMethod(createCriteriaMethod, mybatisExample);
		
		D dto = listCriteria.getFilter();
		if(dto != null){
			PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(dto);
			for(PropertyDescriptor prop : props){
				if(prop.getWriteMethod() != null){//has setting and reader
					String propName = prop.getName();
					Object propValue;
					try {
						propValue = PropertyUtils.getProperty(dto, propName);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
						ExceptionCodeEnum code = ExceptionCodeEnum.UNKNOW_EXCEPTION;
						throw new CommonException(code.getCode(), e.getMessage());
					} 
					if(!CheckUtil.isNull(propValue)){
						String methodName = "and" + propName.substring(0, 1).toUpperCase().concat(propName.substring(1)) + "EqualTo";
						
						Method andXxxEqualToMethod = ReflectionUtils.findMethod(criteria.getClass(), methodName, prop.getPropertyType());
						if(andXxxEqualToMethod != null && !Modifier.isStatic(andXxxEqualToMethod.getModifiers())){
							//set field condition... eg:  select ... where fieldName = fieldValue;
							// all the condition will be combinded with AND 
							ReflectionUtils.invokeMethod(andXxxEqualToMethod, criteria, propValue);
						}
					}
					
				}
			}
			
		}
		

		//call method like: List<NaviScreenDpi> list = naviScreenDpiMapper.selectByExample(mybatisExample);
		Method selectByExampleMethod = ReflectionUtils.findMethod(mybatisMapper.getClass(), "selectByExample", mybatisExample.getClass());
		return (List<T>) ReflectionUtils.invokeMethod(selectByExampleMethod, mybatisMapper, mybatisExample);
		
	}

	
	/**
	 * @param entityDto
	 * @param entity
	 */
	public static void setCreatorInfo(BucSSOUserDto user, AbstractEntity entity) {		
		try {
			if(PropertyUtils.isWriteable(entity, "createTime")){
				PropertyUtils.setProperty(entity, "createTime", new Date());
			}
			
			if(user != null){
				if(PropertyUtils.isWriteable(entity, "createId")){
					PropertyUtils.setProperty(entity, "createId", user.getEmpId() == null? null : Long.valueOf(user.getEmpId() ));
				}
				
				if(PropertyUtils.isReadable(entity, "createName")){
					PropertyUtils.setProperty(entity, "createName", user.getLastName());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("write property error", e);
		} 
		
		
		setUpdatorInfo(user, entity);
	}
	
	/**
	 * @param entityDto
	 * @param entity
	 */
	public static void setUpdatorInfo(BucSSOUserDto user, AbstractEntity entity) {
		try {
			if(PropertyUtils.isWriteable(entity, "updateTime")){
				PropertyUtils.setProperty(entity, "updateTime", new Date());
			}
			
			if(user != null){
				if(PropertyUtils.isWriteable(entity, "updateId")){
					PropertyUtils.setProperty(entity, "updateId", user.getEmpId() == null? null : Long.valueOf(user.getEmpId() ));
				}
				
				if(PropertyUtils.isReadable(entity, "updateName")){
					PropertyUtils.setProperty(entity, "updateName", user.getLastName());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("write property error", e);
		} 
		
	}


	/**
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}

package my.study.sprintbootabc.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class BaseModel<T> implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public T clone() throws CloneNotSupportedException {		
		return (T)super.clone();
	}
	
	public Object getKey(){
		try {
			return PropertyUtils.getProperty(this, "id");
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			//this entity has not id property as pk, ignore error
			return null;
		}
	}
	
}

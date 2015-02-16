package com.study.autoprodtool.form;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.common.PropertyValue;

/**
 * Descriptions
 *
 * @version 2015-2-15
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class FilterListCriteria<F extends EntityForm<F,?>> extends ExampleListCriteria<F> {	
	/**
	 * @param entityFormClass
	 */
	public FilterListCriteria(Class<F> entityFormClass) {
		super(entityFormClass);
	}

	private List<String> filterNames;
	
	public List<String> getFilterNames(){
		return filterNames;
	}
	/**
	 * @param filterNames the filterNames to set
	 */
	public void setFilterNames(List<String> filterNames) {
		this.filterNames = filterNames;
	}
	
	public Map<String, Object> getSelections(){
		Map<String, Object>  ret = new HashMap<String, Object>();
		List<PropertyValue> pvs = getFilterPropertyValues();
		for(PropertyValue pv : pvs){
			ret.put(pv.getName(), pv.getValue());
		}
		return ret;
	}	
	
	/* (non-Javadoc)
	 * @see com.study.autoprodtool.form.ExampleListCriteria#addCriterions(org.hibernate.Criteria)
	 */
	@Override
	protected void addCriterions(Criteria criteria) {
		List<PropertyValue> pvs = getFilterPropertyValues();
		@SuppressWarnings("unchecked")
		List<String> filterNameList = filterNames == null ? Collections.EMPTY_LIST : filterNames;
		
		for(String filterName : filterNameList){
			Criteria curCriteria = criteria;
			String mappedName = ComUtils.getMappedEntityFieldName(getEntityFormClass(), filterName);
			String propName = mappedName;
			int lastDot = mappedName.lastIndexOf(".");
			if(lastDot > 0){
				String path = mappedName.substring(0, lastDot);
				propName = mappedName.substring(lastDot + 1);
				curCriteria = criteria.createCriteria(path, JoinType.LEFT_OUTER_JOIN);
			}
			
			for(PropertyValue pv : pvs){
				if(pv.getName().equals(filterName)){
					curCriteria.add(Restrictions.eq(propName, pv.getValue())); 
				}
			}
			
		}
	}
	
	
}

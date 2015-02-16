package com.study.autoprodtool.form;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.common.ForEach;
import com.study.autoprodtool.common.PropertyValue;

/**
 * Descriptions
 *
 * @version 2015-2-15
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class ExampleListCriteria<F extends EntityForm<F,?>> extends ListCriteria<F> {
	private Class<F> entityFormClass;

	/**
	 * 
	 */
	public ExampleListCriteria(Class<F> entityFormClass) {
		this.entityFormClass = entityFormClass;
	}
	
	@Override
	protected void addCriterions(final Criteria criteria) {		
		List<PropertyValue> pvs = getFilterPropertyValues();
		for(PropertyValue pv : pvs){
			String mappedName = ComUtils.getMappedEntityFieldName(entityFormClass, pv.getName());
			String propName = mappedName;
			int lastDot = mappedName.lastIndexOf(".");
			if(lastDot > 0){
				String path = mappedName.substring(0, lastDot);
				propName = mappedName.substring(lastDot + 1);
				Criteria subCriteria = criteria.createCriteria(path, JoinType.LEFT_OUTER_JOIN);
				subCriteria.add(Restrictions.eq(propName, pv.getValue()));
			}else{
				criteria.add(Restrictions.eq(propName, pv.getValue())); 
			}
		}
	}	

	/**
	 * @return
	 */
	protected List<PropertyValue> getFilterPropertyValues() {
		final List<PropertyValue> pvs = new ArrayList<PropertyValue>();
		final F filterObject = getFilter();
		ComUtils.iterate(filterObject, new ForEach<PropertyValue>() {

			@Override
			public void perform(PropertyValue elem) {							
				if(!CheckUtil.isNull(elem.getValue())){
					pvs.add(elem);
				}

			}
		});
		return pvs;
	}
	
	/**
	 * @return the entityFormClass
	 */
	@JsonIgnore
	public Class<F> getEntityFormClass() {
		return entityFormClass;
	}
	
}

package com.study.autoprodtool.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.entity.DBEntity;
import com.study.autoprodtool.form.EntityForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CrudService;

/**
 * base controller class for entity controller
 *
 * @version 2015-2-12
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public abstract class AbstractEntityController<F extends EntityForm<F, E>, E extends DBEntity> {		
	@Value("${pageLimit}")
	protected Integer pageLimit;
	private Class<F> entityFormClass;
	
	abstract CrudService<E> getCrudService();
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AbstractEntityController() {
		Class<?>[] types = ComUtils.getGenericTypes(getClass());
		entityFormClass = (Class<F>)types[0];
	}
	
		
	protected ListJsonResult list(ListCriteria<F> listCriteria, HttpServletRequest request) throws Exception {
		ComUtils.populateJqGridPagerSorterInfo(request, pageLimit, listCriteria);
		List<E> list = getCrudService().selectList(listCriteria);			
		List<F> ret = new ArrayList<F>();
		for(E ent : list){
			ret.add(entityFormClass.newInstance().initFromEntity(ent));
		}

		return ListJsonResult.success(ret, listCriteria.getPager());
	}	
		
	
	protected JsonResult create(F form) throws Exception{
		E ent = form.toEntity();
		getCrudService().insert(ent);
		return JsonResult.success(ent.getId());
	}
	
	protected JsonResult update(F form) throws Exception{
		E ent = form.toEntity();
		getCrudService().update(ent);
		return JsonResult.success(null);
	}
		
	protected JsonResult delete(String ids) throws Exception{
		if(CheckUtil.isNull(ids)){
			throw new Exception("empty parameter: ids");
		}
		List<Long> idList = ComUtils.toNumbers(ids.split(","), Long.class);
		
		
		Map<Long, E> entities = getCrudService().selectListByIds(idList.toArray(new Long[0]));
		//check parameter
		for(Long id : idList){
			if(!entities.containsKey(id)){
				throw new Exception(String.format("invalid parameter: %s... the entity for id %s does not exist.", ids, id));
			}
		}
		
		//do delete
		for(Long id : idList){
			getCrudService().delete(entities.get(id));
		}
		
		return JsonResult.success(null);
	}

	
	abstract ListCriteria<F> initListCriteria(HttpServletRequest request);

}

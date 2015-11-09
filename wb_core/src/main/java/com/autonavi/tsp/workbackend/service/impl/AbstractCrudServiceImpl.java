/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.autonavi.tsp.common.util.ReflectUtil;
import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.WrapDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.AbstractEntity;
import com.autonavi.tsp.workbackend.service.ICrudService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ServiceImplUtil;
import com.autonavi.tsp.workbackend.util.page.Page;

/**
 * @author mengxianming
 *
 * 2015年7月30日
 */
public abstract class AbstractCrudServiceImpl<DTO extends WrapDto<Entity, Key>, Entity extends AbstractEntity, EntityExample, Key> implements ICrudService<DTO, Key>{
	
	private Class<DTO> dtoClass;
	private Class<EntityExample> entityExampleClass;
	
	
	@SuppressWarnings("unchecked")
	public AbstractCrudServiceImpl(){
		Class<?>[] genericTypes = ReflectUtil.getGenericTypes(getClass());
		dtoClass = (Class<DTO>) genericTypes[0];
		entityExampleClass = (Class<EntityExample>) genericTypes[2];
	}
	
	
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#insert(com.autonavi.tsp.workbackend.dto.SerializableEntity)
	 */
	@Override
	public Key insert(DTO entityDto) throws Exception {
		Entity entity = entityDto.getWrapper();
		
		ServiceImplUtil.setCreatorInfo(entityDto.getUserDto(), entity);
		getBaseDAO().insert(entity);
		return entityDto.getKey();
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#selectByKey(java.lang.Object)
	 */
	@Override
	public DTO selectByKey(Key key) throws Exception {
		Entity entity = getBaseDAO().selectByPrimaryKey(key);
		if(entity != null){
			DTO dto = newDTO();
			dto.setWrapper(entity);
			return dto;
		}
		return null;
	}

	/**
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	private DTO newDTO() throws Exception {		
		return dtoClass.newInstance();
	}


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#deleteByKey(java.lang.Object)
	 */
	@Override
	public void deleteByKey(DeleteKeyDto<Key> key) throws Exception {
		Entity record = getBaseDAO().selectByPrimaryKey(key.getKey());
		if(record == null){
			throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(), "selectByKey: " + key));
		}
		
		getBaseDAO().deleteByPrimaryKey(key.getKey());		
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#update(com.autonavi.tsp.workbackend.dto.SerializableEntity)
	 */
	@Override
	public void update(DTO entityDto) throws Exception {
		if(entityDto.getKey() == null){
			throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
                    String.format("要更新数据的主键必须存在。"));
		}
		Entity record = getBaseDAO().selectByPrimaryKey(entityDto.getKey());
		if(record == null){
			throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"selectByKey：" + entityDto.getKey()));
		}
		
		Entity entity = entityDto.getWrapper();
		
		ServiceImplUtil.setUpdatorInfo(entityDto.getUserDto(), entity);
		int count = getBaseDAO().updateByPrimaryKeySelective(entity);
		if(count == 0){
			throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format("update count is 0."));
		}
		
	}

	/**
	 * @return
	 */
	public abstract BaseDAO<Entity, EntityExample, Key> getBaseDAO();


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#listByExample(com.autonavi.tsp.workbackend.dto.ListCriteria)
	 */
	@Override
	public PageDto<DTO> listByExample(ListCriteria<DTO> criteria) throws Exception {
		
		List<Entity> list = ServiceImplUtil.listByExample(newEntityExample(), getBaseDAO(), criteria);
		ArrayList<DTO> dtoList = new ArrayList<DTO>();		
		for(Entity model : list){
			DTO dto = newDTO();
			dto.setWrapper(model);
			dtoList.add(dto);
		}
		
		PageDto<DTO> result = new PageDto<DTO>();
		result.setResultList(dtoList);
		//set page info
		if(list instanceof Page){
			Page<?> page = (Page<?>) list;
			result.setPageNum(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setTotal(page.getTotal());	
			result.setPages(page.getPages());
		}

		return result;
	}


	/**
	 * @return
	 * @throws Exception 
	 * @throws InstantiationException 
	 */
	private EntityExample newEntityExample() throws Exception {		
		return entityExampleClass.newInstance();
	}


}

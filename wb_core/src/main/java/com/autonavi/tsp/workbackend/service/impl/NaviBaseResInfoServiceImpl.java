/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.constant.NaviResStatuses;
import com.autonavi.tsp.workbackend.constant.NaviResTypes;
import com.autonavi.tsp.workbackend.dao.define.NaviBaseResInfoDAO;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.NaviBaseResInfoDto;
import com.autonavi.tsp.workbackend.dto.NaviResourceVersionDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfo;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfoExample;
import com.autonavi.tsp.workbackend.service.INaviBaseResInfoService;
import com.autonavi.tsp.workbackend.service.INaviResourceVersionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ServiceImplUtil;
import com.autonavi.tsp.workbackend.util.ValidateUtil;
import com.autonavi.tsp.workbackend.util.page.Page;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
@Service("naviBaseResInfoService")
public class NaviBaseResInfoServiceImpl implements INaviBaseResInfoService{
	@Autowired
	private NaviBaseResInfoDAO naviBaseResInfoMapper;
	@Autowired
	private INaviResourceVersionService naviResourceVersionService;


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#insert(com.autonavi.tsp.workbackend.dto.SerializableEntity)
	 */
	@Override
	public Long insert(NaviBaseResInfoDto entityDto) throws Exception {
		NaviBaseResInfo entity = entityDto.getWrapper();

		ServiceImplUtil.setCreatorInfo(entityDto.getUserDto(), entity);
		naviBaseResInfoMapper.insert(entity);
		return entity.getId();
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#selectByKey(java.lang.Object)
	 */
	@Override
	public NaviBaseResInfoDto selectByKey(Long key) {
		NaviBaseResInfo entity = naviBaseResInfoMapper.selectByPrimaryKey(key);
		if(entity != null){
			NaviBaseResInfoDto dto = new NaviBaseResInfoDto();
			dto.setWrapper(entity);
			return dto;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#deleteByKey(java.lang.Object)
	 */
	@Override
	public void deleteByKey(DeleteKeyDto<Long> key) throws Exception {
		NaviBaseResInfo record = naviBaseResInfoMapper.selectByPrimaryKey(key.getKey());
		if(record == null){
			throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
					String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"NaviBaseResInfo：" + key));
		}

		naviBaseResInfoMapper.deleteByPrimaryKey(key.getKey());		
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#update(com.autonavi.tsp.workbackend.dto.SerializableEntity)
	 */
	@Override
	public void update(NaviBaseResInfoDto entityDto) throws Exception {
		if(entityDto.getKey() == null){
			throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
					String.format("要更新数据的主键必须存在。"));
		}
		NaviBaseResInfo record = naviBaseResInfoMapper.selectByPrimaryKey(entityDto.getKey());
		if(record == null){
			throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
					String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"NaviBaseResInfo：" + entityDto.getKey()));
		}

		NaviBaseResInfo entity = entityDto.getWrapper();

		if(CheckUtil.isAllFieldsNull(entity, new String[]{"id"})){
			throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
					String.format("必须指定要更新的数据"));
		}

		ServiceImplUtil.setUpdatorInfo(entityDto.getUserDto(), entity);
		int count = naviBaseResInfoMapper.updateByPrimaryKeySelective(entity);
		if(count == 0){
			throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
					String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"NaviBaseResInfo：" + entityDto.getKey()));
		}

	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#listByExample(com.autonavi.tsp.workbackend.dto.ListCriteria)
	 */
	@Override
	public PageDto<NaviBaseResInfoDto> listByExample(ListCriteria<NaviBaseResInfoDto> criteria) throws Exception {

		List<NaviBaseResInfo> list = ServiceImplUtil.listByExample(new NaviBaseResInfoExample(), naviBaseResInfoMapper, criteria);
		ArrayList<NaviBaseResInfoDto> dtoList = new ArrayList<NaviBaseResInfoDto>();		
		for(NaviBaseResInfo model : list){			
			dtoList.add(toNaviResInfoDto(model));
		}

		PageDto<NaviBaseResInfoDto> result = new PageDto<NaviBaseResInfoDto>();
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

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviBaseResInfoService#addBaseResource(com.autonavi.tsp.workbackend.dto.NaviResourceVersionDto, java.util.List)
	 */
	@Override
	public String addBaseResource(NaviResourceVersionDto baseResVersion, List<NaviBaseResInfoDto> lists) throws Exception {

		String resourceVersionId = baseResVersion.getId();
		baseResVersion.setResType(NaviResTypes.BASERES.getValue());
		if(StringUtil.isEmpty(resourceVersionId)){        	
			resourceVersionId = naviResourceVersionService.insert(baseResVersion);            
		}else{
			naviResourceVersionService.update(baseResVersion);//更新主记录
		}

		if(null!=lists && lists.size()>0){
			List<NaviBaseResInfo> updates = new ArrayList<NaviBaseResInfo>();
			List<NaviBaseResInfo> inserts = new ArrayList<NaviBaseResInfo>();
			for(NaviBaseResInfoDto dto : lists){
				if(null!=dto.getId() && dto.getId()>0){
					NaviBaseResInfo model = dto.getWrapper();
					updates.add(model);
				}else{
					NaviBaseResInfo model = dto.getWrapper();
					model.setResourceVersionId(resourceVersionId);
					inserts.add(model);
				}

			}
			if(inserts.size()>0){
				naviBaseResInfoMapper.insertBatch(inserts);
			}
			if(updates.size()>0){//更新记录
				naviBaseResInfoMapper.updateBatchByPrimaryKeySelective(updates);
			}
		}

		return resourceVersionId;
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviBaseResInfoService#uploadBaseResource(com.autonavi.tsp.workbackend.dto.NaviBaseResInfoDto)
	 */
	@Override
	public Long uploadBaseResource(NaviBaseResInfoDto baseRes) throws Exception {
		ValidateUtil.checkEmpty("baseRes", baseRes);
		//刚上传的资源不可用
		baseRes.setStatus(NaviResStatuses.UPLOADED.getValue());
		return insert(baseRes);			
	}


	public PageDto<NaviBaseResInfoDto> queryBaseResInfo(int pageNO, int pageSize, String resourceVersionId) throws Exception {
		ValidateUtil.checkEmpty("resourceVersionId", resourceVersionId);

		ListCriteria<NaviBaseResInfoDto> ct = new ListCriteria<NaviBaseResInfoDto>();
		ct.setFilter(new NaviBaseResInfoDto());
		ct.getFilter().setResourceVersionId(resourceVersionId);
		return listByExample(ct);
	}

	/**
	 * @param baseres
	 * @return
	 */
	private NaviBaseResInfoDto toNaviResInfoDto(NaviBaseResInfo baseres) {
		NaviBaseResInfoDto dto = new NaviBaseResInfoDto();
		dto.setWrapper(baseres);		
		return dto;
	}
}

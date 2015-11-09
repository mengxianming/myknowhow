/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.common.dto.BucSSOUserDto;
import com.autonavi.tsp.workbackend.constant.NameValueEnumHelper;
import com.autonavi.tsp.workbackend.constant.NaviResPublishStatuses;
import com.autonavi.tsp.workbackend.constant.NaviResTypes;
import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.NaviResourcePublishDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviResourcePublish;
import com.autonavi.tsp.workbackend.model.NaviResourcePublishExample;
import com.autonavi.tsp.workbackend.service.INaviResourcePublishService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ServiceImplUtil;
import com.autonavi.tsp.workbackend.util.page.Page;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
@Service("naviResourcePublishService")
public class NaviResourcePublishServiceImpl  extends AbstractCrudServiceImpl<NaviResourcePublishDto, NaviResourcePublish, NaviResourcePublishExample, Long> implements INaviResourcePublishService{
	@Autowired
	private NaviResourcePublishDAO naviResourcePublishMapper;


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#insert(com.autonavi.tsp.workbackend.dto.SerializableEntity)
	 */
	@Override
	public Long insert(NaviResourcePublishDto entityDto) throws Exception {
		entityDto.setStatus(NaviResPublishStatuses.NO.getValue());

		return super.insert(entityDto);
	}
	
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#listByExample(com.autonavi.tsp.workbackend.dto.ListCriteria)
	 */
	@Override
	public PageDto<NaviResourcePublishDto> listByExample(ListCriteria<NaviResourcePublishDto> criteria)
			throws Exception {
		//sort by update time
		criteria.setSortField("update_time");
		criteria.setSortOrder("desc");		
				
		return super.listByExample(criteria);
	}


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#deleteByKey(java.lang.Object)
	 */
	@Override
	public void deleteByKey(DeleteKeyDto<Long> key) throws Exception {
		Long id = key.getKey();
		NaviResourcePublish publish = naviResourcePublishMapper.selectByPrimaryKey(id);
		if(publish == null){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.DELETE_DATA_NOT_EXIST;
            throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "资源发布记录", "id=" + id));
		}
		
		if(!isResourcePublishDeletable(publish)){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "请检查资源发布记录是否处于【发布中】状态。处于发布中状态的记录不能删除。"));
		}
		naviResourcePublishMapper.deleteByPrimaryKey(id);	
	}


	public PageDto<NaviResourcePublishDto> queryDetailedList(int pageNO, int pageSize) throws CommonException {
		Page<NaviResourcePublish> resourcePublishs =(Page<NaviResourcePublish>) naviResourcePublishMapper.findByPage(pageNO, pageSize);
		List<NaviResourcePublishDto> listDto = new ArrayList<NaviResourcePublishDto>();
		if(null!=resourcePublishs && resourcePublishs.size()>0){
			for (NaviResourcePublish publish : resourcePublishs){
				NaviResourcePublishDto dto = new NaviResourcePublishDto();
				dto.setWrapper(publish);

				dto.setResType((String) publish.getExtras().get("navi_resource_version_res_type"));				
				if(NaviResTypes.BASERES.getValue().equals(dto.getResType())){//基础数据                        
					dto.setBaseVersion((String) publish.getExtras().get("navi_resource_version_res_version"));//版本号
				}else{
					dto.setMapVersion((String) publish.getExtras().get("navi_resource_version_res_version"));
				}	                   
				
				//add by mxm 2015-05-26: add downloadType and downloadTypeName to the dto, in order to show on the UI.
				dto.setTargetUserType(publish.getDownloadType());
				
				//add by mxm 2015-05-26:用于在资源发布列表的操作栏是否显示【删除】按钮。
				dto.setDeletable(isResourcePublishDeletable(publish));

				listDto.add(dto);
			}
		}

		PageDto<NaviResourcePublishDto> pageDto = new PageDto<NaviResourcePublishDto>(resourcePublishs.getPageNum(),resourcePublishs.getPageSize(),
				resourcePublishs.getTotal(),resourcePublishs.getPages(),listDto);
		return pageDto;

	}

	/**
	 * @param publish
	 * @return
	 */
	private boolean isResourcePublishDeletable(NaviResourcePublish publish) {
		//若发布记录为"未发布"或者“停止发布"状态、可以删除该条发布记录。 
		if(publish != null
				&& (NaviResPublishStatuses.NO.getValue().equals(publish.getStatus())
						|| NaviResPublishStatuses.STOP.getValue().equals(publish.getStatus()))){
			return true;
		}
		return false;
	}
	
	public void updateResourcePublishStatus(BucSSOUserDto user, Long id, String status) throws CommonException {
        if(null==id){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "id"));
        }
        NaviResourcePublish resourcePublish = naviResourcePublishMapper.selectByPrimaryKey(id);
        if(null==resourcePublish){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "资源版本发布记录"+id));
        }
        if(NameValueEnumHelper.getByValue(NaviResPublishStatuses.class, status) == null){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "发布状态"+status));
        }
        if(resourcePublish.getStatus().equals(status)){
            return;
        }
        
        NaviResourcePublish publish = new NaviResourcePublish();
        publish.setId(resourcePublish.getId());
        publish.setStatus(status);//0：未发布，1：已发布，2：停止发布
        ServiceImplUtil.setUpdatorInfo(user, publish);
        naviResourcePublishMapper.updateByPrimaryKeySelective(publish);
    }

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#getBaseDAO()
	 */
	@Override
	public BaseDAO<NaviResourcePublish, NaviResourcePublishExample, Long> getBaseDAO() {
		return naviResourcePublishMapper;
	}
}

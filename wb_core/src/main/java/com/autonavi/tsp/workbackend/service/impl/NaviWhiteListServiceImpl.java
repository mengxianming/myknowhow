/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviWhiteListDAO;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.ListDto;
import com.autonavi.tsp.workbackend.dto.NaviWhiteListDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviWhiteList;
import com.autonavi.tsp.workbackend.model.NaviWhiteListExample;
import com.autonavi.tsp.workbackend.service.INaviWhiteListService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
@Service("naviWhiteListService")
public class NaviWhiteListServiceImpl extends AbstractCrudServiceImpl<NaviWhiteListDto, NaviWhiteList, NaviWhiteListExample, Long> implements INaviWhiteListService{
	@Autowired
	private NaviWhiteListDAO naviWhiteListMapper;



	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#listByExample(com.autonavi.tsp.workbackend.dto.ListCriteria)
	 */
	@Override
	public PageDto<NaviWhiteListDto> listByExample(ListCriteria<NaviWhiteListDto> criteria) throws Exception {
		//sort by update time
		criteria.setSortField("update_time");
		criteria.setSortOrder("desc");

		return super.listByExample(criteria);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviWhiteListService#insertBatch(java.util.List)
	 */
	@Override
	public List<Long> insertBatch(ListDto<NaviWhiteListDto> list) throws Exception {
		ArrayList<Long> result = new ArrayList<Long>();
		if(!CheckUtil.isNull(list)){
			for(NaviWhiteListDto dto : list){
				result.add(insert(dto));
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviWhiteListService#checkUserInWhiteList(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkUserInWhiteList(String userId, String imei) throws CommonException {
		if(CheckUtil.isNull(userId) && CheckUtil.isNull(imei)){
			throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
					String.format("userId或者imei必须至少指定一个。"));
		}

		if(!CheckUtil.isNull(userId)){
			NaviWhiteListExample example = new NaviWhiteListExample();
			example.createCriteria().andUserIdEqualTo(userId);
			int cout = naviWhiteListMapper.countByExample(example);
			if(cout > 0 ){
				return true;
			}
		}

		if(!CheckUtil.isNull(imei)){
			NaviWhiteListExample example = new NaviWhiteListExample();
			example.createCriteria().andImeiEqualTo(imei);
			int cout = naviWhiteListMapper.countByExample(example);
			if(cout > 0 ){
				return true;
			}
		}

		return false;
	}
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#getBaseDAO()
	 */
	@Override
	public BaseDAO<NaviWhiteList, NaviWhiteListExample, Long> getBaseDAO() {
		return naviWhiteListMapper;
	}

}

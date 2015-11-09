/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviScreenDpiDAO;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.NaviScreenDpiDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.model.NaviScreenDpi;
import com.autonavi.tsp.workbackend.model.NaviScreenDpiExample;
import com.autonavi.tsp.workbackend.service.INaviScreenDpiService;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
@Service("naviScreenDpiService")
public class NaviScreenDpiServiceImpl extends AbstractCrudServiceImpl<NaviScreenDpiDto, NaviScreenDpi, NaviScreenDpiExample, String> implements INaviScreenDpiService{
	@Autowired
	private NaviScreenDpiDAO naviScreenDpiMapper;
	

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#list(com.autonavi.tsp.workbackend.dto.ListCriteria)
	 */
	@Override
	public PageDto<NaviScreenDpiDto> listByExample(ListCriteria<NaviScreenDpiDto> listCriteria) throws Exception {	
		//sort by update time
		listCriteria.setSortField("update_time");
		listCriteria.setSortOrder("desc");				
		
		return super.listByExample(listCriteria);
	}
	
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#getBaseDAO()
	 */
	@Override
	public BaseDAO<NaviScreenDpi, NaviScreenDpiExample, String> getBaseDAO() {
		return naviScreenDpiMapper;
	}

	
	
}

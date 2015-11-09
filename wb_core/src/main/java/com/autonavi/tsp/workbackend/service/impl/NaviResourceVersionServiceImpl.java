/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.workbackend.constant.NaviResTypes;
import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviBaseResInfoDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviResourcePublishDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviResourceVersionDAO;
import com.autonavi.tsp.workbackend.dto.DeleteKeyDto;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.NaviResourceVersionDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviBaseResInfoExample;
import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfoExample;
import com.autonavi.tsp.workbackend.model.NaviResourcePublishExample;
import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.model.NaviResourceVersionExample;
import com.autonavi.tsp.workbackend.service.INaviResourceVersionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ServiceImplUtil;
import com.autonavi.tsp.workbackend.util.page.Page;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
@Service("naviResourceVersionService")
public class NaviResourceVersionServiceImpl extends AbstractCrudServiceImpl<NaviResourceVersionDto, NaviResourceVersion, NaviResourceVersionExample, String> implements INaviResourceVersionService{
	@Autowired
	private NaviResourceVersionDAO naviResourceVersionMapper;
	@Autowired
	private NaviResourcePublishDAO naviResourcePublishMapper;
	@Autowired
	private NaviBaseResInfoDAO naviBaseResInfoMapper;
	@Autowired
	private NaviMapCityInfoDAO naviMapCityInfoMapper;
	@Autowired
	private NaviCityListDAO naviCityListDAO;
	

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ICrudService#insert(com.autonavi.tsp.workbackend.dto.SerializableEntity)
	 */
	@Override
	public String insert(NaviResourceVersionDto entityDto) throws Exception {
		entityDto.setId( ServiceImplUtil.getUUID());       
		return super.insert(entityDto);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#listByExample(com.autonavi.tsp.workbackend.dto.ListCriteria)
	 */
	@Override
	public PageDto<NaviResourceVersionDto> listByExample(ListCriteria<NaviResourceVersionDto> criteria)
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
	public void deleteByKey(DeleteKeyDto<String> key) throws Exception {
		String id = key.getKey();
		
		NaviResourceVersion version = naviResourceVersionMapper.selectByPrimaryKey(id);
		if(version == null){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.DELETE_DATA_NOT_EXIST;
            throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "资源版本", "id=" + id));
		}
		
		//检查是否允许删除
		Map<String, Boolean> deletableMap = getResourceVersionDeletableMap(Collections.singletonList(id));
		if(!deletableMap.get(id)){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.DELETE_DATA_NOT_ALLOW;
            throw new CommonException(codeEnum.getCode(),String.format("只有该地图资源关联的全部发布单的状态为【未发布】状态才允许删除。"));
		}
		
		
		
		//执行删除		
		//删除发布记录
		NaviResourcePublishExample pexample = new NaviResourcePublishExample();
		pexample.createCriteria().andResourceVersionIdEqualTo(id);
		naviResourcePublishMapper.deleteByExample(pexample);
		//删除基础资源
		if(NaviResTypes.BASERES.getValue().equals(version.getResType())){
			NaviBaseResInfoExample bexample = new NaviBaseResInfoExample();
			bexample.createCriteria().andResourceVersionIdEqualTo(id);
			naviBaseResInfoMapper.deleteByExample(bexample );
		}
		//删除城市地图资源
		else{
			NaviMapCityInfoExample cexample = new NaviMapCityInfoExample();
			cexample.createCriteria().andResourceVersionIdEqualTo(id);
			naviMapCityInfoMapper.deleteByExample(cexample);
		}	
		
		//删除版本
		naviResourceVersionMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * @param version
	 * @return
	 */
	private Map<String, Boolean> getResourceVersionDeletableMap(List<String> versionIds) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<Map<String, Object>> counts = new ArrayList<Map<String,Object>>();
		if(!CheckUtil.isNull(versionIds)){
			counts = naviResourceVersionMapper.selectResourcePublishCountPerVersion(versionIds);
		}
				
		if(counts != null){
			for(Map<String, Object> count : counts){
				String vid = (String) count.get("versionId");
				Number num = (Number) count.get("publishCount");
				result.put(vid, num.intValue() <= 0);
			}
		}
		
		for(String vid : versionIds){
			if(!result.containsKey(vid)){
				//default is deletable
				result.put(vid, true);
			}
		}
		
		return result;
	}
	
    public String queryMaxBaseResVersion() throws CommonException {       
        NaviResourceVersionExample example = new NaviResourceVersionExample();
        example.createCriteria().andResTypeEqualTo(NaviResTypes.BASERES.getValue());
        example.setOrderByClause("res_version desc");
        
        NaviResourceVersion ver = naviResourceVersionMapper.selectFirstOneByExample(example);
		  
		return ver == null ? "" : ver.getResVersion();
    }
    
    public PageDto<NaviResourceVersionDto> queryResourceVersion(int pageNO, int pageSize) throws CommonException {
   	 Page<NaviResourceVersion> resourceVersions = naviResourceVersionMapper.findByPage(pageNO, pageSize);
        List<NaviResourceVersionDto> listDto = toNaviResVersionDtoList(resourceVersions);            

        PageDto<NaviResourceVersionDto> pageDto = new PageDto<NaviResourceVersionDto>(resourceVersions.getPageNum(),resourceVersions.getPageSize(),
        		resourceVersions.getTotal(),resourceVersions.getPages(),listDto);
        return pageDto;
   }
    
    /**
  	 * @param resourceVersions
  	 * @return
  	 */
    private List<NaviResourceVersionDto> toNaviResVersionDtoList(List<NaviResourceVersion> resourceVersions) {
    	List<NaviResourceVersionDto> listDto = new ArrayList<NaviResourceVersionDto>();
    	if(null!=resourceVersions && resourceVersions.size()>0){                          
    		for (NaviResourceVersion version : resourceVersions){
    			NaviResourceVersionDto dto = new NaviResourceVersionDto();
    			dto.setWrapper(version);

    			if(NaviResTypes.BASERES.getValue().equals(dto.getResType())){//基础数据
    				dto.setResCount((Long) version.getExtras().get("navi_base_res_info_count"));
    				dto.setBaseVersion(version.getResVersion());//版本号
    			}else{
    				dto.setResCount((Long) version.getExtras().get("navi_map_city_info_count"));
    				dto.setMapVersion(version.getResVersion());
    			}
    			
    			listDto.add(dto);
    		}
    	}
    	return listDto;
    }
    
    public List<NaviResourceVersionDto> queryAllCityResVersion() throws CommonException {		
		NaviResourceVersionExample example = new NaviResourceVersionExample();
		example.createCriteria().andResTypeEqualTo(NaviResTypes.CITYRES.getValue());
		List<NaviResourceVersion> list = naviResourceVersionMapper.selectByExample(example);
		List<NaviResourceVersionDto> result = toNaviResVersionDtoList(list);
		return result;
	}
    
   
	public String queryMaxCityResVersion() {
		NaviResourceVersionExample example = new NaviResourceVersionExample();
		example.createCriteria().andResTypeEqualTo(NaviResTypes.CITYRES.getValue());
		example.setOrderByClause("res_version desc");
		NaviResourceVersion ver = naviResourceVersionMapper.selectFirstOneByExample(example);
		return ver == null ? null : ver.getResVersion();		
	}
	
	public PageDto<NaviResourceVersionDto> queryCityResVersion(int pageNO, int pageSize) throws CommonException {
		NaviResourceVersionExample example = new NaviResourceVersionExample();
		example.createCriteria().andResTypeEqualTo(NaviResTypes.CITYRES.getValue());
		example.setOrderByClause("res_version desc");
		
		Page<NaviResourceVersion> page = naviResourceVersionMapper.selectByExampleWithPager(example, pageNO, pageSize);
		ArrayList<NaviResourceVersionDto> dtolist = new ArrayList<NaviResourceVersionDto>();
		for(NaviResourceVersion ver : page){
			NaviResourceVersionDto dto = new NaviResourceVersionDto();
			dto.setWrapper(ver);
			dtolist.add(dto);
		}
		return new PageDto<NaviResourceVersionDto>(page.getPageNum(), page.getPageSize(), page.getTotal(), page.getPages(), dtolist);
		
	}


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#getBaseDAO()
	 */
	@Override
	public BaseDAO<NaviResourceVersion, NaviResourceVersionExample, String> getBaseDAO() {
		return naviResourceVersionMapper;
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviResourceVersionService#addCityResVersion(com.autonavi.tsp.workbackend.dto.NaviResourceVersionDto, java.util.List)
	 */
	@Override
	public String addCityResVersion(NaviResourceVersionDto version, List<NaviCityList> cityList) throws Exception {
		String id = insert(version);
		naviCityListDAO.insertBatch(cityList);
		return id;
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviResourceVersionService#queryCityResByResVersion(java.lang.String)
	 */
	@Override
	public NaviResourceVersionDto queryCityResByResVersion(String resVersion) throws Exception {
		NaviResourceVersionExample example = new NaviResourceVersionExample();
		example.createCriteria().andResTypeEqualTo(NaviResTypes.CITYRES.getValue())
		.andResVersionEqualTo(resVersion);
		
		NaviResourceVersion model = naviResourceVersionMapper.selectOneByExample(example);
		return toNaviResVersionDtoList(Arrays.asList(model)).get(0);
	}
}

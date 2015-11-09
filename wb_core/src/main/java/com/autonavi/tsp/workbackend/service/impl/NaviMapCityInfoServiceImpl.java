/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.autonavi.tsp.common.dto.BucSSOUserDto;
import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.workbackend.constant.NaviResStatuses;
import com.autonavi.tsp.workbackend.dao.define.BaseDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviMapCityInfoDAO;
import com.autonavi.tsp.workbackend.dao.define.NaviResourceVersionDAO;
import com.autonavi.tsp.workbackend.dto.ListCriteria;
import com.autonavi.tsp.workbackend.dto.NaviMapCityInfoDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.SimpleCityInfoDto;
import com.autonavi.tsp.workbackend.dto.filter.MapCityInfoFilter;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfo;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfoExample;
import com.autonavi.tsp.workbackend.model.NaviMapCityInfoExample.Criteria;
import com.autonavi.tsp.workbackend.model.NaviResourceVersion;
import com.autonavi.tsp.workbackend.model.NaviResourceVersionExample;
import com.autonavi.tsp.workbackend.service.INaviMapCityInfoService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ListUtil;
import com.autonavi.tsp.workbackend.util.ServiceImplUtil;
import com.autonavi.tsp.workbackend.util.ValidateUtil;
import com.autonavi.tsp.workbackend.util.page.Page;

/**
 * @author mengxianming
 *
 * 2015年5月20日
 */
@Service("naviMapCityInfoService")
public class NaviMapCityInfoServiceImpl extends AbstractCrudServiceImpl<NaviMapCityInfoDto, NaviMapCityInfo, NaviMapCityInfoExample, Long> implements INaviMapCityInfoService{
	private static Log log = LogFactory.getLog(NaviMapCityInfoServiceImpl.class);
	
	@Autowired
	private NaviMapCityInfoDAO naviMapCityInfoMapper;
	@Autowired
	private NaviResourceVersionDAO naviResourceVersionMapper;
	


	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviMapCityInfoService#updateBatch(com.autonavi.tsp.common.dto.BucSSOUserDto, java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public void updateBatch(BucSSOUserDto operator, String cityResVersionId, String relatedCityMap,
			List<NaviMapCityInfoDto> cityResList) throws Exception {

    	ValidateUtil.checkEmpty("资源版本", cityResVersionId);
    	NaviResourceVersion ver = naviResourceVersionMapper.selectByPrimaryKey(cityResVersionId);
    	if(ver == null){
    		throw new CommonException(ExceptionCodeEnum.PARAM_INVALID.getCode(), "资源版本参数非法");
    	}

    	if(null!=cityResList && cityResList.size()>0){            
    		List<NaviMapCityInfo> updates = new ArrayList<NaviMapCityInfo>();
    		List<NaviMapCityInfo> inserts = new ArrayList<NaviMapCityInfo>();
    		for(NaviMapCityInfoDto dto : cityResList){
    			
    			NaviMapCityInfo model = dto.getWrapper();
				model.setResourceVersionId(cityResVersionId);
				//设置资源可用
				model.setStatus(NaviResStatuses.USABLE.getValue());

				//add by mxm: 地图增量更新功能
				checkResVersionExist(dto.getBaseResVersion());

				if(null == dto.getId() || dto.getId() <= 0){
					ServiceImplUtil.setCreatorInfo(operator, model);
    				inserts.add(model);
    			}else{
    				ServiceImplUtil.setUpdatorInfo(operator, model);
    				updates.add(model);
    			}
				
    			
    		}

    		//更新记录
    		if(updates.size() > 0){
    			naviMapCityInfoMapper.updateBatchByPrimaryKeySelective(updates);
    		}
    		if(inserts.size() > 0){
    			naviMapCityInfoMapper.insertBatch(inserts);
    		}

    	}

    	//add by mxm: 城市数据包增加城市关联。
    	setRelatedCities(operator, relatedCityMap, cityResVersionId);		
	}
	
	 /**
   	 * @param relatedCityMap
   	 * @param lists
   	 * @throws CommonException
   	 */
    private void setRelatedCities(BucSSOUserDto operator, String relatedCityMap, String resourceVersionId) throws CommonException {   
    	//根据resource获取所有的城市数据
    	NaviMapCityInfoExample example = new NaviMapCityInfoExample();
    	example.createCriteria()
    		.andStatusEqualTo("0")
    		.andResourceVersionIdEqualTo(resourceVersionId);
    	List<NaviMapCityInfo> lists = naviMapCityInfoMapper.selectByExample(example);
    	
		Map<Long, NaviMapCityInfo> toUpdates = new HashMap<Long, NaviMapCityInfo>();
		//首先假设所有的地图数据都没有关联城市
    	for(NaviMapCityInfo city : lists){
    		if(!CheckUtil.isNull(city.getRelatedCities())){
    			//注意：不能设为null、因为mybatis对应的sql语句只更新不为null的字段。
    			city.setRelatedCities("");
        		
        		toUpdates.put(city.getId(), city);
    		}    		
    	}
    	
    	if(!CheckUtil.isNull(relatedCityMap)){
    		//然后根据UI传过来的关联城市信息设置关联城市。
        	JSONObject mRelatedCityMap = JSON.parseObject(relatedCityMap);
        	Map<String, List<NaviMapCityInfo>> citiesMap = ListUtil.groupListBy(lists, "adcode");
        	Set<String> adcodes = citiesMap.keySet();

        	for(Map.Entry<String, Object> entry : mRelatedCityMap.entrySet()){
        		//判断选择关联的城市是否在上传的城市包列表内。
        		//若存在则处理城市关联数据
        		//若不存在、则报错。
        		String adcode = entry.getKey();
        		if(!adcodes.contains(adcode)){
        			ExceptionCodeEnum ec = ExceptionCodeEnum.RELATED_CITY_NOT_EXIST_IN_UPLOAD_CITY_LIST;
        			throw new CommonException(ec.getCode(), String.format(ec.getMessage(), entry));
        		}


        		String[] relatedCities = new String[0];
        		JSONArray arr = (JSONArray) entry.getValue();
        		if(arr != null && arr.size() > 0){
        			//检查被关联城市是否都在城市数据列表里
        			if(!adcodes.containsAll(arr)){
        				ExceptionCodeEnum ec = ExceptionCodeEnum.RELATED_CITY_NOT_EXIST_IN_UPLOAD_CITY_LIST;
        				throw new CommonException(ec.getCode(), String.format(ec.getMessage(), entry));
        			}

        			relatedCities = arr.toArray(new String[0]);     

        		}
        		for(NaviMapCityInfo city : citiesMap.get(adcode)){
        			city.setRelatedCities(StringUtils.join(relatedCities, ","));
            		//add to update list
            		toUpdates.put(city.getId(), city);
        		}
        		
        	}
    	}  

    	//do update    	
    	ArrayList<NaviMapCityInfo> updates = new ArrayList<NaviMapCityInfo>(toUpdates.values());
    	for(NaviMapCityInfo city : updates){
    		ServiceImplUtil.setUpdatorInfo(operator, city);    		
    	}
    	naviMapCityInfoMapper.updateBatchByPrimaryKeySelective(updates);

    }
	
	/**
	 * @param baseResVersion
     * @throws CommonException 
	 */
	private void checkResVersionExist(String resVersion) throws CommonException {
		if(!CheckUtil.isNull(resVersion)){
			NaviResourceVersionExample example = new NaviResourceVersionExample();
			example.createCriteria().andResVersionEqualTo(resVersion);
			List<NaviResourceVersion> list = naviResourceVersionMapper.selectByExample(example);
			if(CheckUtil.isNull(list)){
				throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(), "资源版本不存在：" + resVersion);
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviMapCityInfoService#uploadCityResource(com.autonavi.tsp.workbackend.dto.NaviMapCityInfoDto)
	 */
	@Override
	public Long uploadCityResource(NaviMapCityInfoDto cityRes) throws Exception {
		ValidateUtil.checkEmpty("cityRes", cityRes);
		//刚上传的资源不可用
		cityRes.setStatus(NaviResStatuses.UPLOADED.getValue());
		return insert(cityRes);	
	}
	
	
	public PageDto<NaviMapCityInfoDto> queryCityResInfo(ListCriteria<MapCityInfoFilter> criteria) throws CommonException {
		if (CheckUtil.isNull(criteria.getFilter().getResourceVersionId())) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "resourceVersionId"));
		}

		NaviMapCityInfoExample example = new NaviMapCityInfoExample();
		example.setOrderByClause(criteria.getOrderByClause());
		if (CheckUtil.isNull(criteria.getFilter().getKeyword())) {
			example.createCriteria().andStatusEqualTo(NaviResStatuses.USABLE.getValue())
					.andResourceVersionIdEqualTo(criteria.getFilter().getResourceVersionId());
		} else {
			String kw = "%" + criteria.getFilter().getKeyword() + "%";
			example.or().andStatusEqualTo(NaviResStatuses.USABLE.getValue())
			.andResourceVersionIdEqualTo(criteria.getFilter().getResourceVersionId())
					.andProvinceLike(kw);
			example.or().andStatusEqualTo(NaviResStatuses.USABLE.getValue())
			.andResourceVersionIdEqualTo(criteria.getFilter().getResourceVersionId())
					.andCityLike(kw);
		}

		Page<NaviMapCityInfo> cityList = naviMapCityInfoMapper.selectByExampleWithPager(example, criteria.getPageIndex(), criteria.getPageSize());
		List<NaviMapCityInfoDto> dtoList = toNaviResInfoDtoList(cityList);

		return new PageDto<NaviMapCityInfoDto>(cityList.getPageNum(),
				cityList.getPageSize(), cityList.getTotal(), cityList.getPages(), dtoList);
	}
	
	private List<NaviMapCityInfoDto> toNaviResInfoDtoList(List<NaviMapCityInfo> cityList) {
		List<NaviMapCityInfoDto> listDto = new ArrayList<NaviMapCityInfoDto>();
		if(null!=cityList && cityList.size()>0){
			for (NaviMapCityInfo city : cityList){
				NaviMapCityInfoDto  dto = new NaviMapCityInfoDto();
				dto.setWrapper(city);
				listDto.add(dto);
			}
		}		

		return listDto;
	}
	
	
	public List<SimpleCityInfoDto> queryRelatedCities(String resourceVersionId) throws CommonException {		
		if(CheckUtil.isNull(resourceVersionId)){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(), "资源版本ID必须指定。");
		}
		
		//get related cities
		Map<String, SimpleCityInfoDto> result = new LinkedHashMap<String, SimpleCityInfoDto>();
		NaviMapCityInfoExample example = new NaviMapCityInfoExample();
		example.createCriteria().andResourceVersionIdEqualTo(resourceVersionId);
		List<NaviMapCityInfo> list = naviMapCityInfoMapper.selectByExample(example);
		
		//convert to map for easy search
		Map<Object, NaviMapCityInfo> map = ListUtil.listToMap(list, "adcode");
		//search for cities that have related cities
		for(NaviMapCityInfo city : list){
			if(!CheckUtil.isNull(city.getRelatedCities()) 
					&& !result.containsKey(city.getAdcode())){
				SimpleCityInfoDto sc = toSimpleCityInfoDto(city);
				
				String[] relatedAdcodes = city.getRelatedCities().split(",");
				for(String relatedAdcode : relatedAdcodes){
					NaviMapCityInfo relatedCity = map.get(relatedAdcode) ;
					if(relatedCity == null){
						log.warn(String.format("被关联城市不在资源包城市列表里。该数据可能为脏数据：{资源版本ID:%s, 城市adcode:%s, 被关联城市adcode:%s}",
								resourceVersionId, city.getAdcode(), relatedAdcode));
						continue;
					}
					//add related city
					SimpleCityInfoDto relatedSc = toSimpleCityInfoDto(relatedCity);
					sc.getRelatedCities().add(relatedSc);
				}
				
				//Only those cities that have related city are contained in the result.
				if(sc.getRelatedCities().size() > 0){
					result.put(sc.getAdcode(), sc);
				}
				
				
			}
		}
		
		return new ArrayList<SimpleCityInfoDto>(result.values());
	}

	/**
	 * @param city
	 */
	private SimpleCityInfoDto toSimpleCityInfoDto(NaviMapCityInfo city) {
		SimpleCityInfoDto sc = new SimpleCityInfoDto();
		sc.setAdcode(city.getAdcode());
		sc.setNameZh(city.getCity());
		sc.setRelatedCities(new ArrayList<SimpleCityInfoDto>());
		return sc;
	}
	
	
	public void batchUpdateCityResInfo(String resourceVersionId, List<Long> targetIds, String updateType) throws CommonException {
		//check targetIds
		if(!CheckUtil.isNull(targetIds)){
			NaviMapCityInfoExample example = new NaviMapCityInfoExample();
			example.createCriteria().andIdIn(targetIds);
			List<NaviMapCityInfo> list = naviMapCityInfoMapper.selectByExample(example);
			Map<Object, NaviMapCityInfo> idMap = ListUtil.listToMap(list, "id");
			for(Long id : targetIds){
				if(idMap.get(id) == null){
					ExceptionCodeEnum code = ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION;
					//db record corresponded to the id not exist
					throw new CommonException(code.getCode(), "城市id对应的记录在数据库不存在: id=" + id);
				}
			}

		}	
		
		
		NaviMapCityInfo record = new NaviMapCityInfo();
		record.setUpdateType(updateType);
		
		NaviMapCityInfoExample example = new NaviMapCityInfoExample();
		Criteria criteria = example.createCriteria().andResourceVersionIdEqualTo(resourceVersionId);
		if(!CheckUtil.isNull(targetIds)){
			criteria.andIdIn(targetIds);

		}		
		naviMapCityInfoMapper.updateByExampleSelective(record, example);	
		
	}
	
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.impl.AbstractCrudServiceImpl#getBaseDAO()
	 */
	@Override
	public BaseDAO<NaviMapCityInfo, NaviMapCityInfoExample, Long> getBaseDAO() {		
		return naviMapCityInfoMapper;
	}
	
}

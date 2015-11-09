package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.common.dto.BucSSOUserDto;
import com.autonavi.tsp.common.util.CheckUtil;
import com.autonavi.tsp.common.util.ParameterConstant;
import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.dao.define.NaviCityListDAO;
import com.autonavi.tsp.workbackend.dto.NaviCityListDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviCityListExample;
import com.autonavi.tsp.workbackend.model.NaviCityListExample.Criteria;
import com.autonavi.tsp.workbackend.service.INaviCityListService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.ValidateUtil;

@Service("naviCityListService")
public class NaviCityListServiceImpl implements INaviCityListService {
	@Autowired
	private NaviCityListDAO naviCityListMapper;
	
	@Override
	public List<NaviCityListDto> queryAllNaviCityList() throws CommonException {
		NaviCityListExample example = new NaviCityListExample();
		example.createCriteria().andStatusEqualTo("0");

		List<NaviCityList> list = naviCityListMapper.selectByExample(example);
		List<NaviCityListDto> listDto = new ArrayList<NaviCityListDto>();

		if (null != list && list.size() > 0) {
			for (NaviCityList record : list) {
				NaviCityListDto dto = new NaviCityListDto();
				dto.setWrapper(record);

				listDto.add(dto);
			}
		}
		return listDto;
	}	

	@Override
	public void deleteById(Long id) throws CommonException {
		if (id == null) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "id"));
		}
		naviCityListMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertNaviCityList(List<NaviCityList> naviCityList) throws CommonException {
		if (CheckUtil.isNull(naviCityList)) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "naviCityList is null"));
		}
		naviCityListMapper.insertBatch(naviCityList);
	}

	@Override
	public void insertNaviCity(NaviCityList naviCityList) throws CommonException {
		if (naviCityList == null) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "naviCityList is null"));
		}
		naviCityListMapper.insertSelective(naviCityList);

	}

	@Override
	public void updateNaviCityList(NaviCityList record) throws CommonException {
		if (record == null) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "naviCityList is null"));
		}
		naviCityListMapper.updateByPrimaryKeySelective(record);

	}

	@Override
	public List<NaviCityListDto> queryProvince(BucSSOUserDto user, String resVersion) throws CommonException {
		ValidateUtil.checkEmpty("resVersion", resVersion);
		
		NaviCityListExample example = new NaviCityListExample();
		example.createCriteria()
		.andParentCodeIsNull()
		.andResVersionEqualTo(resVersion);
		List<NaviCityList> list = naviCityListMapper.selectByExample(example);
		
		List<NaviCityListDto> listDto = toDtoList(list);
		return listDto;
	}

	@Override
	public List<NaviCityListDto> queryCityList(BucSSOUserDto userDto, String parentCode, String resVersion)
			throws CommonException {
		ValidateUtil.checkEmpty("parentCode", parentCode);
		ValidateUtil.checkEmpty("resVersion", resVersion);
		
		NaviCityListExample example = new NaviCityListExample();
		if (parentCode.equals(ParameterConstant.RequestCity.BEIJING_CITY)
				|| parentCode.equals(ParameterConstant.RequestCity.SHANGHAI_CITY)
				|| parentCode.equals(ParameterConstant.RequestCity.TIANJING_CITY)
				|| parentCode.equals(ParameterConstant.RequestCity.CHONGQING_CITY)
				|| parentCode.equals(ParameterConstant.RequestCity.HONGKONG)
				|| parentCode.equals(ParameterConstant.RequestCity.MACAU)
				|| parentCode.equals(ParameterConstant.RequestCity.TAIWAN)) {
			
			example.createCriteria().andAdcodeEqualTo(parentCode)
			.andResVersionEqualTo(resVersion);
			
		} else {
			example.createCriteria().andParentCodeEqualTo(parentCode)
			.andResVersionEqualTo(resVersion);
		}

		List<NaviCityList> list = naviCityListMapper.selectByExample(example);
		List<NaviCityListDto> listDto = toDtoList(list);
		return listDto;
	}

	@Override
	public NaviCityListDto queryCityListById(Long id) throws CommonException {
		if (id == null) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "id"));
		}
		NaviCityList record = naviCityListMapper.selectByPrimaryKey(id);
		
		NaviCityListDto dto = new NaviCityListDto();
		dto.setWrapper(record);			
		return dto;

	}

	@Override
	public List<NaviCityListDto> selectByResVersion(String resVersion) throws CommonException {
		ValidateUtil.checkEmpty("resVersion", resVersion);
		
		NaviCityListExample example = new NaviCityListExample();
		example.createCriteria().andResVersionEqualTo(resVersion);
		List<NaviCityList> list = naviCityListMapper.selectByExample(example);
		
		List<NaviCityListDto> listDto = toDtoList(list);
		return listDto;
	}

	/**
	 * @param list
	 * @param listDto
	 */
	private List<NaviCityListDto> toDtoList(List<NaviCityList> list) {
		List<NaviCityListDto> listDto = new ArrayList<NaviCityListDto>();

		if (null != list && list.size() > 0) {
			for (NaviCityList record : list) {
				NaviCityListDto dto = new NaviCityListDto();
				dto.setWrapper(record);
				listDto.add(dto);
			}
		}

		return listDto;
	}

	@Override
	public NaviCityListDto queryNewVersion() throws CommonException {
		NaviCityListExample example = new NaviCityListExample();
		example.setOrderByClause("res_version desc");
		List<NaviCityList> list = naviCityListMapper.selectByExampleWithLimit(example, 1);
		if(CheckUtil.isNull(list)){
			return null;
		}
		NaviCityListDto dto = new NaviCityListDto();
		dto.setWrapper(list.get(0));
		return dto;
	}

	@Override
	public void deleteByResVersion(String resVersion) throws CommonException {
		ValidateUtil.checkEmpty("resVersion", resVersion);		
		
		NaviCityListExample example = new NaviCityListExample();
		example.createCriteria().andResVersionEqualTo(resVersion);
		naviCityListMapper.deleteByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.autonavi.tsp.workbackend.service.INaviCityListService#
	 * queryCityListLikeName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<NaviCityListDto> queryCityListLikeName(String resVersion, String nameKeyword) throws CommonException {
		ValidateUtil.checkEmpty("resVersion", resVersion);			

		NaviCityListExample example = new NaviCityListExample();
		if (StringUtil.isEmpty(nameKeyword)) {
			example.createCriteria().andResVersionEqualTo(resVersion);
		} else {
			example.or().andResVersionEqualTo(resVersion).andNameZhLike("%" + nameKeyword + "%");
			example.or().andResVersionEqualTo(resVersion).andNameFtLike("%" + nameKeyword + "%");
			example.or().andResVersionEqualTo(resVersion).andNameEnLike("%" + nameKeyword + "%");
		}

		List<NaviCityList> list = naviCityListMapper.selectByExample(example);
		return toDtoList(list);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.INaviCityListService#queryOneByPkgname(java.lang.String, java.lang.String)
	 */
	@Override
	public NaviCityListDto queryOneByPkgname(String resVersion, String pkgname) throws CommonException {
		ValidateUtil.checkEmpty("pkgname", pkgname);
		
		NaviCityListExample example = new NaviCityListExample();
		Criteria ct = example.createCriteria();
		if (!StringUtil.isEmpty(resVersion)) {
			ct.andResVersionEqualTo(resVersion);
		}
		ct.andPkgnameEqualTo(pkgname);
		
		NaviCityList city = naviCityListMapper.selectFirstOneByExample(example);		
		if(CheckUtil.isNull(city)){
			return null;
		}
		
		//获取父节点信息
		NaviCityList parent = null;
		if(!CheckUtil.isNull(city.getParentCode())){
			parent = naviCityListMapper.selectByAdcode(resVersion, city.getParentCode());
		}
		
		NaviCityListDto dto = new NaviCityListDto();
		dto.setWrapper(city);
		dto.setParent(parent);
		return dto;
	}

}

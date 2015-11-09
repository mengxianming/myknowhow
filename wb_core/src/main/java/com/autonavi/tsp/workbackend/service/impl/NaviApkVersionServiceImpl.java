package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.workbackend.dao.define.NaviApkVersionDAO;
import com.autonavi.tsp.workbackend.dto.NaviApkVersionDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviApkVersion;
import com.autonavi.tsp.workbackend.model.NaviApkVersionExample;
import com.autonavi.tsp.workbackend.service.INaviApkVersionService;
import com.autonavi.tsp.workbackend.util.ValidateUtil;
import com.autonavi.tsp.workbackend.util.page.Page;

@Service("naviApkVersionService")
public class NaviApkVersionServiceImpl implements INaviApkVersionService {
	@Autowired
	private NaviApkVersionDAO naviApkVersionMapper;

	@Override
	public List<NaviApkVersionDto> queryAllNaviApkVersion() throws CommonException {
		NaviApkVersionExample example = new NaviApkVersionExample();
		example.createCriteria().andStatusEqualTo("0");
		example.setOrderByClause("create_time desc");
		List<NaviApkVersion> list = naviApkVersionMapper.selectByExample(example);
		
		List<NaviApkVersionDto> listDto = new ArrayList<NaviApkVersionDto>();
		if (null != list && list.size() > 0) {
			for (NaviApkVersion naviApkVersion : list) {
				NaviApkVersionDto dto = new NaviApkVersionDto();
				dto.setWrapper(naviApkVersion);				
				listDto.add(dto);
			}
		}
		return listDto;
	}

	@Override
	public PageDto<NaviApkVersionDto> queryNaviApkVersion(int pageNO, int pageSize) throws CommonException {
		NaviApkVersionExample example = new NaviApkVersionExample();
		example.createCriteria().andStatusEqualTo("0");		
		example.setOrderByClause("create_time desc");
		Page<NaviApkVersion> naviApkVersions = naviApkVersionMapper.selectByExampleWithPager(example, pageNO, pageSize);
		
		List<NaviApkVersionDto> listDto = new ArrayList<NaviApkVersionDto>();
		if (null != naviApkVersions && naviApkVersions.size() > 0) {
			for (NaviApkVersion naviApkVersion : naviApkVersions) {
				NaviApkVersionDto dto = new NaviApkVersionDto();
				dto.setWrapper(naviApkVersion);				
				listDto.add(dto);
			}
		}

		PageDto<NaviApkVersionDto> pageDto = new PageDto<NaviApkVersionDto>(naviApkVersions.getPageNum(),
				naviApkVersions.getPageSize(), naviApkVersions.getTotal(), naviApkVersions.getPages(), listDto);
		return pageDto;
	}

	@Override
	public void deleteBySyscode(String syscode, String apkv) throws CommonException {
		ValidateUtil.checkEmpty("syscode", syscode);
		ValidateUtil.checkEmpty("apkv", apkv);		
		
		NaviApkVersionExample example = new NaviApkVersionExample();
		example.createCriteria().andSyscodeEqualTo(syscode)
		.andApkvEqualTo(apkv);
		naviApkVersionMapper.deleteByExample(example);
	}

	@Override
	public void insetNaviApkVersion(NaviApkVersion naviApkVersion) throws CommonException {
		ValidateUtil.checkEmpty("naviApkVersion", naviApkVersion);
		naviApkVersionMapper.insert(naviApkVersion);
	}

	@Override
	public void updateApkVersioin(NaviApkVersion naviApkVersion) throws CommonException {
		ValidateUtil.checkEmpty("naviApkVersion", naviApkVersion);
		naviApkVersionMapper.updateByPrimaryKeySelective(naviApkVersion);
	}

	@Override
	public NaviApkVersionDto findByKey(String syscode, String apkv) throws CommonException {
		ValidateUtil.checkEmpty("syscode", syscode);
		ValidateUtil.checkEmpty("apkv", apkv);	
		
		NaviApkVersionExample example = new NaviApkVersionExample();
		example.createCriteria().andSyscodeEqualTo(syscode)
		.andApkvEqualTo(apkv);
		NaviApkVersion naviApkVersion = naviApkVersionMapper.selectOneByExample(example);
		if(naviApkVersion == null){
			return null;
		}
		NaviApkVersionDto dto = new NaviApkVersionDto();
		dto.setWrapper(naviApkVersion);
		return dto;
	}

	@Override
	public NaviApkVersionDto queryCurrentNewApkVersion() throws CommonException {
		NaviApkVersionExample example = new NaviApkVersionExample();
		example.setOrderByClause("apkv desc");
		List<NaviApkVersion> list = naviApkVersionMapper.selectByExampleWithLimit(example, 1);
		if(list.size() <= 0){
			return null;
		}
		NaviApkVersion naviApkVersion = list.get(0);
		
		NaviApkVersionDto dto = new NaviApkVersionDto();
		dto.setWrapper(naviApkVersion);
		return dto;
	}

}

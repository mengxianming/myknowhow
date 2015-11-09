package com.autonavi.tsp.workbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.common.util.DateUtil;
import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.dao.define.NaviSystemDAO;
import com.autonavi.tsp.workbackend.dto.NaviSysCodeDto;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.NaviSystem;
import com.autonavi.tsp.workbackend.model.NaviSystemExample;
import com.autonavi.tsp.workbackend.service.INaviSysCodeService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.page.Page;

@Service("naviSysCodeService")
public class NaviSysCodeServiceImpl implements INaviSysCodeService{

	@Autowired
	private NaviSystemDAO naviSysCodeMapper;

	@Override
	public List<NaviSysCodeDto> queryAllNaviSysCode() throws CommonException {
		NaviSystemExample example = new NaviSystemExample();
		example.setOrderByClause("update_time desc");
		example.createCriteria().andStatusEqualTo("0");
		List<NaviSystem> list = naviSysCodeMapper.selectByExample(example);
		
		List<NaviSysCodeDto> listDto = new ArrayList<NaviSysCodeDto>();		
		if(null!=list && list.size()>0){
			for (NaviSystem naviSysCode : list){				
				NaviSysCodeDto dto = new NaviSysCodeDto();
				dto.setCreateId(naviSysCode.getCreateId());
				dto.setCreateName(naviSysCode.getCreateName());
				dto.setCreateTime(DateUtil.dateTimeToStr(naviSysCode.getCreateTime()));
				dto.setMemo(naviSysCode.getMemo());
				dto.setStatus(naviSysCode.getStatus());
				dto.setSyscode(naviSysCode.getSyscode());
				dto.setSysname(naviSysCode.getSysname());
				dto.setUpdateId(naviSysCode.getUpdateId());
				dto.setUpdateName(naviSysCode.getUpdateName());
				dto.setUpdateTime(DateUtil.dateTimeToStr(naviSysCode.getUpdateTime()));
				listDto.add(dto);
			}
		}
		return listDto;
	}

	@Override
	public PageDto<NaviSysCodeDto> queryNaviSysCode(int pageNO, int pageSize) throws CommonException {

		NaviSystemExample example = new NaviSystemExample();
		example.setOrderByClause("update_time desc");
		example.createCriteria().andStatusEqualTo("0");
		Page<NaviSystem> naviSysCodes = (Page<NaviSystem>) naviSysCodeMapper.selectByExampleWithPager(example, pageNO,
				pageSize);
		List<NaviSysCodeDto> listDto = new ArrayList<NaviSysCodeDto>();

		if (null != naviSysCodes && naviSysCodes.size() > 0) {
			for (NaviSystem naviSysCode : naviSysCodes) {
				NaviSysCodeDto dto = new NaviSysCodeDto();
				dto.setSysname(naviSysCode.getSysname());
				dto.setCreateId(naviSysCode.getCreateId());
				dto.setCreateName(naviSysCode.getCreateName());
				dto.setCreateTime(DateUtil.dateTimeToStr(naviSysCode.getCreateTime()));
				dto.setMemo(naviSysCode.getMemo());
				dto.setStatus(naviSysCode.getStatus());
				dto.setSyscode(naviSysCode.getSyscode());
				dto.setUpdateId(naviSysCode.getUpdateId());
				dto.setUpdateName(naviSysCode.getUpdateName());
				dto.setUpdateTime(DateUtil.dateTimeToStr(naviSysCode.getUpdateTime()));
				listDto.add(dto);
			}
		}

		PageDto<NaviSysCodeDto> pageDto = new PageDto<NaviSysCodeDto>(naviSysCodes.getPageNum(),
				naviSysCodes.getPageSize(), naviSysCodes.getTotal(), naviSysCodes.getPages(), listDto);
		return pageDto;

	}

	@Override
	public void deleteBySyscode(String syscode) throws CommonException {
		if (StringUtil.isEmpty(syscode)) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(codeEnum.getMessage(), "syscode"));
		}
		NaviSystemExample example = new NaviSystemExample();
		example.createCriteria().andSyscodeEqualTo(syscode);
		naviSysCodeMapper.deleteByExample(example);
	}



	@Override
	public void insertNaviSysCode(NaviSystem record) throws CommonException {
		if(record==null){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "naviSysCode"));
		}
		naviSysCodeMapper.insert(record);		
	}

	@Override
	public void updateNaviSysCode(NaviSystem record) throws CommonException {
		if(record==null){
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "naviSysCode"));
		}
		naviSysCodeMapper.updateByPrimaryKeySelective(record);

	}

	@Override
	public NaviSysCodeDto querySysCodeById(String syscode) throws CommonException {
		if (StringUtil.isEmpty(syscode)) {
			ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
			throw new CommonException(codeEnum.getCode(), String.format(
					codeEnum.getMessage(), "syscode"));
		}
		NaviSystem record = naviSysCodeMapper.selectByPrimaryKey(syscode);
		if (null == record) {
			return null;
		}
		NaviSysCodeDto dto = new NaviSysCodeDto();
		dto.setCreateId(record.getCreateId());
		dto.setCreateName(record.getCreateName());
		dto.setCreateTime(DateUtil.dateTimeToStr(record.getCreateTime()));
		dto.setMemo(record.getMemo());
		dto.setStatus(record.getStatus());
		dto.setSyscode(syscode);
		dto.setSysname(record.getSysname());
		dto.setUpdateId(record.getUpdateId());
		dto.setUpdateName(record.getUpdateName());
		dto.setUpdateTime(DateUtil.dateTimeToStr(record.getUpdateTime()));
		return dto;

	}

}

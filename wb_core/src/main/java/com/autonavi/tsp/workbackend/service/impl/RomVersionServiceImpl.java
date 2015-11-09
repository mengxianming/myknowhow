/**
 * autonavi.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.autonavi.tsp.workbackend.service.impl;

import com.autonavi.tsp.common.util.DateUtil;
import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.dao.*;
import com.autonavi.tsp.workbackend.dto.*;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.*;
import com.autonavi.tsp.workbackend.service.IRomVersionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by you on 2015/1/6.
 */
@Service("romVersionService")
public class RomVersionServiceImpl implements IRomVersionService {

    private static int default_page_no = 1;

    private static int default_page_size = 20;

    @Autowired
    private RomVersionMapper romVersionMapper;

    @Autowired
    private RomPushMapper romPushMapper;

    @Autowired
    private RomMatchCarMapper romMatchCarMapper;

    @Autowired
    VehicleBrandMapper vehicleBrandMapper;

    @Autowired
    VehicleVersionMapper vehicleVersionMapper;

    @Autowired
    VehicleSeriesMapper vehicleSeriesMapper;

    @Autowired
    VehicleYearMapper vehicleYearMapper;

    @Override
    public PageDto<RomVersionDto> queryRomVersion(int pageNO, int pageSize) throws CommonException {
        try {
            if(pageNO<=0){
                pageNO = default_page_no;
            }
            if(pageSize<=0){
                pageSize = default_page_size;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            PageHelper.startPage(pageNO, pageSize, true);
            Page<RomVersion> romVersions=(Page<RomVersion>) romVersionMapper.findByPage(params);
            List<RomVersionDto> listDto = new ArrayList<RomVersionDto>();
            if(null!=romVersions && romVersions.size()>0){
                RomVersionDto dto;
                for (RomVersion romVersion : romVersions){
                    if(null==romVersion){
                        continue;
                    }
                    dto = new RomVersionDto();
                    dto.setId(romVersion.getId());
                    dto.setCreateName(romVersion.getCreateName());
                    dto.setName(romVersion.getName());
                    dto.setMemo(romVersion.getMemo());
                    dto.setCreateTime(DateUtil.dateTimeToStr(romVersion.getCreateTime()));
                    dto.setSize(romVersion.getSize());
                    dto.setVersion(romVersion.getVersion());
                    listDto.add(dto);
                }
            }

            PageDto<RomVersionDto> pageDto = new PageDto<RomVersionDto>(romVersions.getPageNum(),romVersions.getPageSize(),
                    romVersions.getTotal(),romVersions.getPages(),listDto);
            return pageDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new  CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public PageDto<RomPushDto> queryRomPush(int pageNO, int pageSize) throws CommonException {
        try {
            if(pageNO<=0){
                pageNO = default_page_no;
            }
            if(pageSize<=0){
                pageSize = default_page_size;
            }

            Map<String, Object> params = new HashMap<String, Object>();
            PageHelper.startPage(pageNO, pageSize, true);
            Page<RomPush> romPushs = (Page<RomPush>) romPushMapper.findByPage(params);
            List<RomPushDto> listDto = new ArrayList<RomPushDto>();
            if(null!=romPushs && romPushs.size()>0){
                RomPushDto dto;
                for (RomPush romPush : romPushs){
                    if(null==romPush){
                        continue;
                    }
                    dto = new RomPushDto();
                    dto.setId(romPush.getId());
                    dto.setCreateName(romPush.getCreateName());
                    dto.setVersion(romPush.getVersion());
                    dto.setVersionTo(romPush.getVersionTo());
                    dto.setMatchCarShortName(romPush.getMatchCarShortName());
                    dto.setUpgradeType(romPush.getUpgradeType());
                    dto.setName(romPush.getName());
                    dto.setToName(romPush.getToName());
                    dto.setUpgradeTime(DateUtil.dateTimeToStr(romPush.getUpgradeTime()));
                    dto.setCreateTime(DateUtil.dateTimeToStr(romPush.getCreateTime()));
                    listDto.add(dto);
                }
            }

            PageDto<RomPushDto> pageDto = new PageDto<RomPushDto>(romPushs.getPageNum(),romPushs.getPageSize(),
                    romPushs.getTotal(),romPushs.getPages(),listDto);
            return pageDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new  CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public List<RomMatchCarNameDto> queryRomMatchCar(String romVersionId) throws CommonException {
        if(null==romVersionId){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "romVersionId"));
        }
        try {
            List<RomMatchCarName> list = romMatchCarMapper.selectByRomPushId(romVersionId);

            List<RomMatchCarNameDto> listDto = new ArrayList<RomMatchCarNameDto>();
            if(null!=list && list.size()>0){
                RomMatchCarNameDto dto;
                for (RomMatchCarName romPush : list){
                    if(null==romPush){
                        continue;
                    }
                    dto = new RomMatchCarNameDto();
                    dto.setCarBrandName(romPush.getCarBrandName());
                    dto.setModelName(romPush.getModelName());
                    dto.setModelYearName(romPush.getModelYearName());
                    dto.setExhaustVolumeName(romPush.getExhaustVolumeName());
                    dto.setCarBrandId(romPush.getCarBrandId());
                    dto.setModelId(romPush.getModelId());
                    dto.setModelYearId(romPush.getModelYearId());
                    dto.setExhaustVolumeId(romPush.getExhaustVolumeId());
                    listDto.add(dto);
                }
            }
            return listDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public void addRomVersionFile(String key, String name, String filePath, Double size, String version, Long userId, String userName) throws CommonException {
        if(StringUtil.isEmpty(name)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "name"));
        }
        if( StringUtil.isEmpty(filePath)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "filePath"));
        }
        if(null==size){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "size"));
        }
        if(StringUtil.isEmpty(version)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "version"));
        }
        try {
            RomVersion romVersion = new RomVersion();
//            String id = UUID.randomUUID().toString().replaceAll("-", "");
            romVersion.setId(key);
            romVersion.setName(name);
            romVersion.setFilePath(filePath);
            romVersion.setSize(new BigDecimal(size));
            romVersion.setVersion(version);
            romVersion.setCreateTime(new Date());
            romVersion.setStatus("1");
            romVersion.setCreateId(userId);
            romVersion.setCreateName(userName);
            romVersionMapper.insertSelective(romVersion);
//            return id;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public void updateRomVersion(String id, String memo, Long userId, String userName) throws CommonException {
        if(StringUtil.isEmpty(id)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "id"));
        }
        if(StringUtil.isEmpty(memo)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "memo"));
        }
        try {
            RomVersion romVersion = new RomVersion();
            romVersion.setId(id);
            romVersion.setMemo(memo);
            romVersion.setStatus("0");
            romVersion.setCreateId(userId);
            romVersion.setCreateName(userName);
            romVersion.setUpdateTime(new Date());
            romVersionMapper.updateByPrimaryKeySelective(romVersion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public void updateRomVersionStatus(String id, String status, Long userId, String userName) throws CommonException {
        if(StringUtil.isEmpty(id)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "id"));
        }
        if(StringUtil.isEmpty(status)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "status"));
        }
        try {
            RomVersion romVersion = new RomVersion();
            romVersion.setId(id);
            romVersion.setStatus(status);
            romVersion.setUpdateTime(new Date());
            romVersion.setUpdateId(userId);
            romVersion.setUpdateName(userName);
            romVersionMapper.updateByPrimaryKeySelective(romVersion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public void addRomPush(String romVersionId,String toRomVersionId, String upgradeTime, String upgradeType, Long userId, String userName) throws CommonException {
        if(StringUtil.isEmpty(romVersionId)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "romVersionId"));
        }
        if(StringUtil.isEmpty(toRomVersionId)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "toRomVersionId"));
        }
        if(StringUtil.isEmpty(upgradeTime)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "upgradeTime"));
        }
        if(StringUtil.isEmpty(upgradeType)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "upgradeType"));
        }
        try {
            RomVersion romVersion = romVersionMapper.selectByPrimaryKey(romVersionId);
            if(null==romVersion){
//                ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
                throw new CommonException(1000, "ROM版本不存在！" + romVersionId);
            }
            RomVersion romVersion2 = romVersionMapper.selectByPrimaryKey(toRomVersionId);
            if(null==romVersion2){
//                ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
                throw new CommonException(5000, "ROM版本不存在！" + toRomVersionId);
            }
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            RomPush romPush = new RomPush();
            romPush.setId(id);
            romPush.setName(romVersion.getName());
            romPush.setUpgradeType(upgradeType);
            romPush.setRomVersionId(romVersionId);
            romPush.setVersion(romVersion.getVersion());
            romPush.setToName(romVersion2.getName());
            romPush.setToRomVersionId(toRomVersionId);
            romPush.setVersionTo(romVersion2.getVersion());
            romPush.setUpgradeTime(DateUtil.strToDateTime(upgradeTime));
            romPush.setStatus("0");
            romPush.setCreateId(userId);
            romPush.setCreateName(userName);
            StringBuilder shortName = new StringBuilder("");

            List<RomMatchCarName> romMatchCarNameList = romMatchCarMapper.selectByRomPushId(toRomVersionId);
            if(null!=romMatchCarNameList && romMatchCarNameList.size()>0){
                for(RomMatchCarName cm : romMatchCarNameList){
                    if(null==cm){
                        continue;
                    }
                    if(shortName.length()>30){
                        break;
                    }
                    if(null!=cm.getCarBrandName()){
                        shortName.append(cm.getCarBrandName());
                    }
                    if(null!=cm.getModelName()){
                        shortName.append(" ").append(cm.getModelName());
                    }
                    if(null!=cm.getModelYearName()){
                        shortName.append(" ").append(cm.getModelYearName());
                    }
                    if(null!=cm.getExhaustVolumeName()){
                        shortName.append(" ").append(cm.getExhaustVolumeName());
                    }

                }
            }
            romPush.setMatchCarShortName(shortName.length()>30?shortName.substring(0,30):shortName.toString());
            romPushMapper.insert(romPush);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public List<RomVersionShortDto> queryLowRomVersion(String romVersionId) throws CommonException {
        if(null==romVersionId){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "romVersionId"));
        }
        try {
            List<RomVersion> list = romVersionMapper.queryLowRomVersion(romVersionId);

            List<RomVersionShortDto> listDto = new ArrayList<RomVersionShortDto>();
            if(null!=list && list.size()>0){
                RomVersionShortDto dto;
                for (RomVersion romVersion : list){
                    if(null==romVersion){
                        continue;
                    }
                    dto = new RomVersionShortDto();
                    dto.setName(romVersion.getName());
                    dto.setId(romVersion.getId());
                    dto.setVersion(romVersion.getVersion());
                    listDto.add(dto);
                }
            }
            return listDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public List<RomVersionDto> queryAllRomVersion() throws CommonException {
        RomVersionExample example = new RomVersionExample();
        example.createCriteria().andStatusEqualTo("0");

        List<RomVersion> list = romVersionMapper.selectByExample(example);
        List<RomVersionDto> listDto = new ArrayList<RomVersionDto>();
        if(null!=list && list.size()>0){
            RomVersionDto dto;
            for (RomVersion romVersion : list){
                if(null==romVersion){
                    continue;
                }
                dto = new RomVersionDto();
                dto.setId(romVersion.getId());
                dto.setCreateName(romVersion.getCreateName());
                dto.setName(romVersion.getName());
                dto.setMemo(romVersion.getMemo());
                dto.setCreateTime(DateUtil.dateTimeToStr(romVersion.getCreateTime()));
                dto.setSize(romVersion.getSize());
                dto.setVersion(romVersion.getVersion());
                listDto.add(dto);
            }
        }
        return listDto;
    }

    @Override
    public List<RomVersionDto> getlRomVersionByIds(String[] romVersionIds) throws CommonException {
        if(null==romVersionIds || romVersionIds.length<=0){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "romVersionIds"));
        }
        List<String> idLists = Arrays.asList(romVersionIds);
        RomVersionExample example = new RomVersionExample();
        example.createCriteria().andStatusEqualTo("0").andIdIn(idLists);
        List<RomVersion> list = romVersionMapper.selectByExample(example);

        List<RomVersionDto> listDto = new ArrayList<RomVersionDto>();
        if(null!=list && list.size()>0){
            RomVersionDto dto;
            for (RomVersion romVersion : list){
                if(null==romVersion){
                    continue;
                }
                dto = new RomVersionDto();
                dto.setId(romVersion.getId());
                dto.setCreateName(romVersion.getCreateName());
                dto.setName(romVersion.getName());
                dto.setMemo(romVersion.getMemo());
                dto.setCreateTime(DateUtil.dateTimeToStr(romVersion.getCreateTime()));
                dto.setSize(romVersion.getSize());
                dto.setVersion(romVersion.getVersion());
                listDto.add(dto);
            }
        }
        return listDto;
    }

    @Override
    public void updateRomVersionMatchCar(String id, List<RomMatchCarDto> carMatch, Long userId, String userName) throws CommonException {
        if(StringUtil.isEmpty(id)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "id"));
        }
        if(null==carMatch || carMatch.size()<=0){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "carMatch"));
        }
        try {
            List<RomMatchCar> lists = new ArrayList<RomMatchCar>();
            RomMatchCar romMatchCar;
            for(RomMatchCarDto dto : carMatch){
                romMatchCar = new RomMatchCar();
                romMatchCar.setRomVersionId(id);
                romMatchCar.setCarBrandId(dto.getCarBrandId());
                romMatchCar.setModelId(dto.getModelId());
                romMatchCar.setModelYearId(dto.getModelYearId());
                romMatchCar.setExhaustVolumeId(dto.getExhaustVolumeId());
                lists.add(romMatchCar);
            }
            romMatchCarMapper.insertBatch(lists);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
    }

    @Override
    public RomVersionDto getNextUpgradeRomVersion(String romName, String version) throws CommonException {
        if(StringUtil.isEmpty(romName)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "romName"));
        }
        if(StringUtil.isEmpty(version)){
            ExceptionCodeEnum codeEnum = ExceptionCodeEnum.PARAM_MUST_INPUT;
            throw new CommonException(codeEnum.getCode(),String.format(codeEnum.getMessage(), "version"));
        }
        try {
            RomVersion romVersion = romVersionMapper.getNextUpgradeRomVersion(romName, version);
            if(null!=romVersion){
                RomVersionDto dto = new RomVersionDto();
                dto.setId(romVersion.getId());
                dto.setCreateName(romVersion.getCreateName());
                dto.setName(romVersion.getName());
                dto.setMemo(romVersion.getMemo());
                dto.setCreateTime(DateUtil.dateTimeToStr(romVersion.getCreateTime()));
                dto.setSize(romVersion.getSize());
                dto.setVersion(romVersion.getVersion());
                dto.setFilePath(romVersion.getFilePath());
                return dto;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
        }
        return null;
    }
}
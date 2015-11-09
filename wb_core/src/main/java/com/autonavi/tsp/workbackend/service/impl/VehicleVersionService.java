package com.autonavi.tsp.workbackend.service.impl;

import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.dao.VehicleBrandMapper;
import com.autonavi.tsp.workbackend.dao.VehicleSeriesMapper;
import com.autonavi.tsp.workbackend.dao.VehicleVersionMapper;
import com.autonavi.tsp.workbackend.dao.VehicleYearMapper;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.VehicleVersionDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.VehicleVersion;
import com.autonavi.tsp.workbackend.model.VehicleVersionExample;
import com.autonavi.tsp.workbackend.model.VehicleYear;
import com.autonavi.tsp.workbackend.service.IVehicleVersionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chongsong on 15/1/14.
 * chong.song@autonavi.com
 */
@Service("vehicleVersionService")
public class VehicleVersionService implements IVehicleVersionService{
    @Autowired
    VehicleBrandMapper brandMapper;
    @Autowired
    VehicleSeriesMapper seriesMapper;
    @Autowired
    VehicleYearMapper yearMapper;
    @Autowired
    VehicleVersionMapper vehicleVersionMapper;


    @Override
    public boolean createVehicleVersion(long yId, String versionName, String createUser) throws Exception {
        VehicleYear year = yearMapper.selectByPrimaryKey(yId);
        if (year == null) {
            throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"年款：" + yId));
        }
        if(StringUtil.isEmpty(versionName)) {
            throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
                    String.format(ExceptionCodeEnum.PARAM_MUST_INPUT.getMessage(), "versionName"));
        }
        VehicleVersion conf = new VehicleVersion();
        conf.setbId(year.getbId());
        conf.setBrandName(year.getBrandName());
        conf.setsId(year.getsId());
        conf.setSeriesName(year.getSeriesName());
        conf.setyId(year.getId());
        conf.setYearName(year.getYearName());
        conf.setcId(year.getcId());
        conf.setCreateUser(createUser);
        conf.setVersionName(versionName);
        conf.setCreateTime(new Date());
        conf.setModifyUser(createUser);
        conf.setModifyTime(new Date());
        conf.setCompanyName(year.getCompanyName());
        vehicleVersionMapper.insert(conf);
        return true;
    }

    @Override
    public boolean updateVehicleVersion(long cId,long vId, long bId, long sId, long yId,String companyName,String brandName,String seriesName, String yearName, String modifyUser,String versionName) throws Exception {
        VehicleVersion  vehicleVersion= vehicleVersionMapper.selectByPrimaryKey(vId);
        if (vehicleVersion == null) {
            throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"版本：" + vId));
        }
        if(StringUtil.isEmpty(versionName)) {
            throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
                    String.format(ExceptionCodeEnum.PARAM_MUST_INPUT.getMessage(), "versionName"));
        }
        VehicleVersion conf = new VehicleVersion();
        conf.setId(vId);
        conf.setcId(cId>0?cId:vehicleVersion.getcId());
        conf.setsId(sId>0?sId:vehicleVersion.getsId());
        conf.setbId(bId>0?bId:vehicleVersion.getbId());
        conf.setyId(yId>0?yId:vehicleVersion.getyId());
        conf.setBrandName(brandName.equals("-1")?vehicleVersion.getBrandName():brandName);
        conf.setCreateTime(vehicleVersion.getCreateTime());
        conf.setCreateUser(vehicleVersion.getCreateUser());
        conf.setSeriesName(seriesName.equals("-1")?vehicleVersion.getSeriesName():seriesName);
        conf.setModifyTime(new Date());
        conf.setModifyUser(modifyUser);
        conf.setYearName(yearName.equals("-1")?vehicleVersion.getYearName():yearName);
        conf.setVersionName(versionName.equals("-1")?vehicleVersion.getVersionName():versionName);
        conf.setCompanyName(companyName.equals("-1")?vehicleVersion.getCompanyName():companyName);
        vehicleVersionMapper.updateByPrimaryKey(conf);
        return true;
    }

    @Override
    public boolean removeVehicleVersion(long vId) throws Exception {
        if(vId <= 0){
            throw new CommonException(ExceptionCodeEnum.PARAM_MUST_NUMBER.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"versionId"));
        }
        vehicleVersionMapper.deleteByPrimaryKey(vId);
        return true;
    }

    @Override
    public PageDto<VehicleVersionDto> findVehicleVersion(long cId, long bId, long sId, long yId, String versionName, int pageNo, int pageSize) throws Exception {

        if(versionName==null||versionName.equals("")){
            throw new Exception("");
        }
        VehicleVersionExample vehicleVersionExample = new VehicleVersionExample();
        VehicleVersionExample.Criteria example = vehicleVersionExample.createCriteria();
        if(!versionName.equals("-1")){
            example.andVersionNameEqualTo(versionName);
        }
        if(cId>0){
            example.andCIdEqualTo(cId);
        }
        if(bId>0){
            example.andBIdEqualTo(bId);
        }
        if(sId>0){
            example.andSIdEqualTo(sId);
        }
        if(yId>0){
            example.andYIdEqualTo(yId);
        }

        PageHelper.startPage(pageNo,pageSize);
        List<VehicleVersion> result = vehicleVersionMapper.selectByExample(vehicleVersionExample);
        if(result.size()<=0){
           return null;
        }
        PageDto<VehicleVersionDto> pageDto = new PageDto<VehicleVersionDto>();
        if(result instanceof Page){
            Page page = (Page)result;
            pageDto.setPageNum(page.getPageNum());
            pageDto.setPageSize(page.getPageSize());
            pageDto.setPages(page.getPages());
            pageDto.setTotal(page.getTotal());
        }

        List<VehicleVersionDto> vehicleVersionDtos = new ArrayList<VehicleVersionDto>();
        for(int i=0;i<result.size();i++){
            VehicleVersionDto vehicleVersionDto = new VehicleVersionDto();
            VehicleVersion vehicleVersion = result.get(i);

            vehicleVersionDto.setId(vehicleVersion.getId());
            vehicleVersionDto.setSeriesName(vehicleVersion.getSeriesName());
            vehicleVersionDto.setCreateUser(vehicleVersion.getCreateUser());
            vehicleVersionDto.setbId(vehicleVersion.getbId());
            vehicleVersionDto.setBrandName(vehicleVersion.getBrandName());
            vehicleVersionDto.setcId(vehicleVersion.getcId());
            vehicleVersionDto.setCreateTime(vehicleVersion.getCreateTime());
            vehicleVersionDto.setModifyTime(vehicleVersion.getModifyTime());
            vehicleVersionDto.setModifyUser(vehicleVersion.getModifyUser());
            vehicleVersionDto.setYearName(vehicleVersion.getYearName());
            vehicleVersionDto.setsId(vehicleVersion.getsId());
            vehicleVersionDto.setyId(vehicleVersion.getyId());
            vehicleVersionDto.setVersionName(vehicleVersion.getVersionName());
            vehicleVersionDto.setCompanyName(vehicleVersion.getCompanyName());
            vehicleVersionDtos.add(vehicleVersionDto);
        }
        pageDto.setResultList(vehicleVersionDtos);
        return pageDto;
    }
}

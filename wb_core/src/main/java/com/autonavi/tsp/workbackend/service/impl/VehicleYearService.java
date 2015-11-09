package com.autonavi.tsp.workbackend.service.impl;

import com.autonavi.tsp.common.util.StringUtil;
import com.autonavi.tsp.workbackend.dao.VehicleBrandMapper;
import com.autonavi.tsp.workbackend.dao.VehicleSeriesMapper;
import com.autonavi.tsp.workbackend.dao.VehicleVersionMapper;
import com.autonavi.tsp.workbackend.dao.VehicleYearMapper;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.VehicleYearDto;
import com.autonavi.tsp.workbackend.exception.CommonException;
import com.autonavi.tsp.workbackend.model.*;
import com.autonavi.tsp.workbackend.service.IVehicleYearService;
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
@Service("vehicleYearService")
public class VehicleYearService implements IVehicleYearService {
    @Autowired
    VehicleBrandMapper brandMapper;
    @Autowired
    VehicleSeriesMapper seriesMapper;
    @Autowired
    VehicleYearMapper yearMapper;
    @Autowired
    VehicleVersionMapper vehicleVersionMapper;

    @Override
    public boolean createVehicleYear(long sId, String yearName, String createUser) throws Exception {
        VehicleSeries vehicleSeries = seriesMapper.selectByPrimaryKey(sId);
        if (vehicleSeries == null) {
            throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"系列：" + sId));
        }
        if(StringUtil.isEmpty(yearName)) {
            throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
                    String.format(ExceptionCodeEnum.PARAM_MUST_INPUT.getMessage(), "yearName"));
        }
        VehicleYear year = new VehicleYear();
        year.setcId(vehicleSeries.getcId());
        year.setbId(vehicleSeries.getbId());
        year.setBrandName(vehicleSeries.getBrandName());
        year.setsId(vehicleSeries.getId());
        year.setSeriesName(vehicleSeries.getSeriesName());
        year.setCreateUser(createUser);
        year.setYearName(yearName);
        year.setCreateTime(new Date());
        year.setModifyTime(new Date());
        year.setModifyUser(createUser);
        year.setCompanyName(vehicleSeries.getCompanyName());
        yearMapper.insert(year);
        return true;
    }


    @Override
    public boolean updateVehicleYear(long cId, long bId, long sId, long yId, String companyName, String brandName, String seriesName, String yearName, String modifyUser) throws Exception {
        VehicleYear vehicle = yearMapper.selectByPrimaryKey(yId);
        if (vehicle == null) {
            throw new CommonException(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"年款：" + vehicle));
        }
        if(StringUtil.isEmpty(yearName)) {
            throw new CommonException(ExceptionCodeEnum.PARAM_MUST_INPUT.getCode(),
                    String.format(ExceptionCodeEnum.PARAM_MUST_INPUT.getMessage(), "yearName"));
        }
        VehicleYear vehicleYear = new VehicleYear();
        vehicleYear.setId(yId);
        vehicleYear.setcId(cId>0?cId:vehicle.getcId());
        vehicleYear.setsId(sId>0?sId:vehicle.getsId());
        vehicleYear.setbId(bId>0?bId:vehicle.getbId());
        vehicleYear.setBrandName(brandName.equals("-1")?vehicle.getBrandName():brandName);
        vehicleYear.setCreateTime(vehicle.getCreateTime());
        vehicleYear.setCreateUser(vehicle.getCreateUser());
        vehicleYear.setSeriesName(seriesName.equals("-1")?vehicle.getSeriesName():seriesName);
        vehicleYear.setModifyTime(new Date());
        vehicleYear.setModifyUser(modifyUser);
        vehicleYear.setYearName(yearName.equals("-1")?vehicle.getYearName():yearName);
        vehicleYear.setCompanyName(companyName.equals("-1")?vehicle.getCompanyName():companyName);
        yearMapper.updateByPrimaryKey(vehicleYear);
        return true;
    }

    @Override
    public boolean removeVehicleYear(long yId) throws Exception {
        if(yId <= 0){
            throw new CommonException(ExceptionCodeEnum.PARAM_MUST_NUMBER.getCode(),
                    String.format(ExceptionCodeEnum.DATA_NOT_FOUND_EXCEPTION.getMessage(),"yearId"));
        }
//        yearMapper.deleteByPrimaryKey(yId);
//        return true;

        boolean success = false;
        try {
            VehicleYear vehicleYear = yearMapper.selectByPrimaryKey(yId);

            if(vehicleYear==null||vehicleYear.equals("")){
                throw new Exception("车年款不存在或已被删除");
            }

//            Remove remove = new Remove() ;
//            remove.stileBUsed(seriesId);
            VehicleVersionExample vehicleVersionExample = new VehicleVersionExample();
            vehicleVersionExample.createCriteria().andYIdEqualTo(yId);
            List<VehicleVersion> vehicleVersion = vehicleVersionMapper.selectByExample(vehicleVersionExample);
            if(vehicleVersion.size()>0){
                return success;
            }
            yearMapper.deleteByPrimaryKey(yId);
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public PageDto<VehicleYearDto> findVehicleYear(long cId, long bId, long sId,String yearName, int pageNo, int pageSize) throws Exception {

        if(yearName==null||yearName.equals("")){
            throw new Exception("");
        }
        VehicleYearExample vehicleYearExample = new VehicleYearExample();

        VehicleYearExample.Criteria example = vehicleYearExample.createCriteria();

        if(!yearName.equals("-1")){
            example.andYearNameEqualTo(yearName);
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

        PageHelper.startPage(pageNo,pageSize);
        List<VehicleYear> result = yearMapper.selectByExample(vehicleYearExample);
        if(result.size()<=0){
            return null;
        }

        PageDto<VehicleYearDto> pageDto = new PageDto<VehicleYearDto>();
        if(result instanceof Page){
            Page page = (Page)result;
            pageDto.setPageNum(page.getPageNum());
            pageDto.setPageSize(page.getPageSize());
            pageDto.setPages(page.getPages());
            pageDto.setTotal(page.getTotal());
        }
        List<VehicleYearDto> vehicleYearDtos = new ArrayList<VehicleYearDto>();
        for(int i=0;i<result.size();i++){
            VehicleYearDto vehicleYearDto = new VehicleYearDto();
            VehicleYear vehicleSeries = result.get(i);

            vehicleYearDto.setId(vehicleSeries.getId());
            vehicleYearDto.setSeriesName(vehicleSeries.getSeriesName());
            vehicleYearDto.setCreateUser(vehicleSeries.getCreateUser());
            vehicleYearDto.setbId(vehicleSeries.getbId());
            vehicleYearDto.setBrandName(vehicleSeries.getBrandName());
            vehicleYearDto.setcId(vehicleSeries.getcId());
            vehicleYearDto.setCreateTime(vehicleSeries.getCreateTime());
            vehicleYearDto.setModifyTime(vehicleSeries.getModifyTime());
            vehicleYearDto.setModifyUser(vehicleSeries.getModifyUser());
            vehicleYearDto.setYearName(vehicleSeries.getYearName());
            vehicleYearDto.setsId(vehicleSeries.getsId());
            vehicleYearDto.setCompanyName(vehicleSeries.getCompanyName());

            vehicleYearDtos.add(vehicleYearDto);
        }
        pageDto.setResultList(vehicleYearDtos);
        return pageDto;
    }
}

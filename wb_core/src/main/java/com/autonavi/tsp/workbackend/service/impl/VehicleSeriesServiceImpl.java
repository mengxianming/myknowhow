package com.autonavi.tsp.workbackend.service.impl;

import com.autonavi.tsp.workbackend.dao.VehicleBrandMapper;
import com.autonavi.tsp.workbackend.dao.VehicleSeriesMapper;
import com.autonavi.tsp.workbackend.dao.VehicleYearMapper;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.VehicleSeriesDto;
import com.autonavi.tsp.workbackend.model.*;
import com.autonavi.tsp.workbackend.service.IVehicleSeriesService;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dell on 2015/1/13.
 */
@Service("vehicleSeriesService")

public class VehicleSeriesServiceImpl implements IVehicleSeriesService {

    @Autowired
    private VehicleBrandMapper vehicleBrandMapper;

    @Autowired
    private VehicleSeriesMapper vehicleSeriesMapper;

    @Autowired
    private VehicleYearMapper vehicleYearMapper;

    @Override
    public boolean createVehicleSeries(long bId, String seriesName, String createUser) throws Exception {

        if(seriesName ==null ||seriesName.equals("")){
            throw new Exception("车系为空");
        }
        boolean success = false;
        try {
            VehicleBrand vehicleBrand = vehicleBrandMapper.selectByPrimaryKey(bId);
            if (vehicleBrand == null || vehicleBrand.equals("")) {
                throw new Exception("汽车品牌不存在");
            }
            VehicleSeries vehicleSeries = new VehicleSeries();
            vehicleSeries.setSeriesName(seriesName);
            vehicleSeries.setBrandName(vehicleBrand.getBrandName());
            vehicleSeries.setbId(bId);
            vehicleSeries.setcId(vehicleBrand.getcId());
            vehicleSeries.setCreateTime(new Date());
            vehicleSeries.setCreateUser(createUser);
            vehicleSeries.setModifyTime(new Date());
            vehicleSeries.setModifyUser(createUser);
            vehicleSeries.setCompanyName(vehicleBrand.getCompanyName());

            vehicleSeriesMapper.insert(vehicleSeries);
            success = true;
        }catch ( Exception e){
            e.printStackTrace();
        }

        return success;
    }

    @Override
    public boolean updateVehicleSeries(long cId,long bId, long sId, String companyName, String seriesNam,String brandName,String modifyUser) throws Exception {

        if(seriesNam==null||seriesNam.equals("")){
            throw new Exception("传入参数为空");
        }
        VehicleSeries vehicle = vehicleSeriesMapper.selectByPrimaryKey(sId);
        if(vehicle==null||vehicle.equals("")){
            throw new Exception("跟新数据不存在");
        }

        boolean success = false;
        VehicleSeries vehicleSeries = new VehicleSeries();
        vehicleSeries.setId(sId);
        vehicleSeries.setbId(bId > 0 ? bId : vehicle.getbId());
        vehicleSeries.setcId(cId > 0 ? cId:vehicle.getcId());
        vehicleSeries.setBrandName(brandName.equals("-1") ? vehicle.getBrandName() : brandName);
        vehicleSeries.setSeriesName(seriesNam.equals("-1")? vehicle.getSeriesName():seriesNam);
        vehicleSeries.setCompanyName(companyName.equals("-1")? vehicle.getCompanyName():companyName);
        vehicleSeries.setCreateTime(vehicle.getCreateTime());
        vehicleSeries.setCreateUser(vehicle.getCreateUser());


        vehicleSeries.setModifyTime(new Date());
        vehicleSeries.setModifyUser(modifyUser);
        try {
            vehicleSeriesMapper.updateByPrimaryKeySelective(vehicleSeries);
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public PageDto<VehicleSeriesDto> findVehicleSeries(long cId, long bId,String seriesName, int pageNo, int pageSize) throws Exception {

        if(seriesName==null||seriesName.equals("")){
            throw new Exception("");
        }
        VehicleSeriesExample vehicleSeriesExample = new VehicleSeriesExample();
        VehicleSeriesExample.Criteria example =   vehicleSeriesExample.createCriteria();

        if(!seriesName.equals("-1")){
            example.andSeriesNameEqualTo(seriesName);
        }
        if(cId>0){
            example.andCIdEqualTo(cId);
        }
        if(bId>0){
            example.andBIdEqualTo(bId);
        }

        PageHelper.startPage(pageNo,pageSize);
        List<VehicleSeries> result = vehicleSeriesMapper.selectByExample(vehicleSeriesExample);
        if(result.size()<=0){
           return null;
        }
        PageDto<VehicleSeriesDto> pageDto = new PageDto<VehicleSeriesDto>();
        if(result instanceof Page){
            Page page = (Page)result;
            pageDto.setPageNum(page.getPageNum());
            pageDto.setPageSize(page.getPageSize());
            pageDto.setPages(page.getPages());
            pageDto.setTotal(page.getTotal());
        }
        List<VehicleSeriesDto> vehicleSeriesDtos = new ArrayList<VehicleSeriesDto>();
        for(int i=0;i<result.size();i++){
            VehicleSeriesDto vehicleSeriesDto = new VehicleSeriesDto();
            VehicleSeries vehicleSeries = result.get(i);

            vehicleSeriesDto.setId(vehicleSeries.getId());
            vehicleSeriesDto.setSeriesName(vehicleSeries.getSeriesName());
            vehicleSeriesDto.setCreateUser(vehicleSeries.getCreateUser());
            vehicleSeriesDto.setbId(vehicleSeries.getbId());
            vehicleSeriesDto.setBrandName(vehicleSeries.getBrandName());
            vehicleSeriesDto.setcId(vehicleSeries.getcId());
            vehicleSeriesDto.setCreateTime(vehicleSeries.getCreateTime());
            vehicleSeriesDto.setModifyTime(vehicleSeries.getModifyTime());
            vehicleSeriesDto.setModifyUser(vehicleSeries.getModifyUser());
            vehicleSeriesDto.setCompanyName(vehicleSeries.getCompanyName());
            vehicleSeriesDtos.add(vehicleSeriesDto);


        }
        pageDto.setResultList(vehicleSeriesDtos);
        return pageDto;
    }

    @Override
    public boolean removeVehicleSeries(long sId) throws Exception {
        if(sId<=0){
            throw new Exception("删除异常");
        }
        boolean success = false;
        try {
        VehicleSeries vehicleSeries = vehicleSeriesMapper.selectByPrimaryKey(sId);

        if(vehicleSeries==null||vehicleSeries.equals("")){
            throw new Exception("车系不存在或已被删除");
        }

//            Remove remove = new Remove() ;
//            remove.stileBUsed(seriesId);
            VehicleYearExample vehicleYearExample = new VehicleYearExample();
            vehicleYearExample.createCriteria().andSIdEqualTo(sId);
            List<VehicleYear> vehicleYear = vehicleYearMapper.selectByExample(vehicleYearExample);
            if(vehicleYear.size()>0){
                return success;
            }
            vehicleSeriesMapper.deleteByPrimaryKey(sId);
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean checkSeriesName(String name) throws Exception {
        if(name==null||name.equals("")){
            throw new Exception("汽车品牌不能为空");
        }
        boolean success = false;
        try {
            VehicleSeriesExample vehicleSeriesExample = new VehicleSeriesExample();
            vehicleSeriesExample.createCriteria().andSeriesNameEqualTo(name);
            List<VehicleSeries> result = vehicleSeriesMapper.selectByExample(vehicleSeriesExample);
            if(result.size()>0){
                throw new Exception ("汽车品牌已存在");
            }
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    return success;
    }

//    @Override
//    public String findAllSeries() throws Exception {
//        VehicleSeriesExample vehicleSeriesExample = new VehicleSeriesExample();
//        vehicleSeriesExample.createCriteria().andIdIsNotNull();
//        List<VehicleSeries> result = vehicleSeriesMapper.selectByExample(vehicleSeriesExample);
//        if(result.size()<=0){
//            throw new Exception("查询结果为空");
//        }
//        return result.toString();
//    }
}

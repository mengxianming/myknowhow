package com.autonavi.tsp.workbackend.service.impl;

import com.autonavi.tsp.workbackend.dao.*;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.VehicleBrandDto;
import com.autonavi.tsp.workbackend.dto.VehicleCompanyDto;
import com.autonavi.tsp.workbackend.model.*;
import com.autonavi.tsp.workbackend.service.IVehicleCompanyService;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dell on 2015/1/13.
 */
@Service("vehicleCompanyService")
public class VehicleCompanyServiceImpl implements IVehicleCompanyService{

    private static int default_page_no = 1;

    private static int default_page_size = 20;

    @Autowired
    private VehicleCompanyMapper vehicleCompanyMapper;
    @Autowired
    private VehicleBrandMapper vehicleBrandMapper;
    @Autowired
    private VehicleSeriesMapper vehicleSeriesMapper;
    @Autowired
    private VehicleYearMapper vehicleYearMapper;
    @Autowired
    private VehicleVersionMapper vehicleVersionMapper;
    @Override
    public boolean createVehicleCompany(String name,String createUser) throws Exception {
        if(name==null ||name.equals("")){
            throw new Exception ("名称不能为空");
        }

        boolean success = false;

        VehicleCompany vehicleCompany = new VehicleCompany();
        vehicleCompany.setCompanyName(name);
        vehicleCompany.setCreateTime(new Date());
        vehicleCompany.setCreateUser(createUser);
        vehicleCompany.setModifyTime(new Date());
        vehicleCompany.setModifyUser(createUser);
        try {
            int result = vehicleCompanyMapper.insert(vehicleCompany);
            success=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateVehicleCompany(String name,String modifyUser,long cId)  throws Exception{
        boolean success = false;
        if(name==null||name.equals("")){
            throw new Exception("");
        }
        if(modifyUser==null||modifyUser.equals("")){
            throw new Exception("modifyUser为空");
        }
        VehicleCompany vehicle = vehicleCompanyMapper.selectByPrimaryKey(cId);
        VehicleCompany vehicleCompany = new VehicleCompany();
        vehicleCompany.setId(cId);
        vehicleCompany.setCompanyName(name.equals("-1")?vehicle.getCompanyName():name);
        vehicleCompany.setCreateTime(vehicle.getCreateTime());
        vehicleCompany.setCreateUser(vehicle.getCreateUser());
        vehicleCompany.setModifyTime(new Date());
        vehicleCompany.setModifyUser(modifyUser);

        try {
            int result = vehicleCompanyMapper.updateByPrimaryKeySelective(vehicleCompany);
            success=true;
        }catch (Exception e){
            e.printStackTrace();
        }
          return success;
    }

    @Override
    public PageDto<VehicleCompanyDto> findVehicleCompany(String name,int pNo,int pSize) throws Exception {
        if(name.equals("")){
            throw new Exception("");
        }

        VehicleCompanyExample vehicleCompanyExample = new VehicleCompanyExample();
        VehicleCompanyExample.Criteria example =   vehicleCompanyExample.createCriteria();
        if(!name.equals("-1")){
            example.andCompanyNameEqualTo(name);
        }
        PageHelper.startPage(pNo,pSize);
        List<VehicleCompany> result = vehicleCompanyMapper.selectByExample(vehicleCompanyExample);
        if(result.size()<=0){
            return null;
        }

        PageDto<VehicleCompanyDto> pageDto = new PageDto<VehicleCompanyDto>();
        if(result instanceof Page){
            Page page = (Page)result;
            pageDto.setPageNum(page.getPageNum());
            pageDto.setPageSize(page.getPageSize());
            pageDto.setPages(page.getPages());
            pageDto.setTotal(page.getTotal());
        }
        List<VehicleCompanyDto> vehicleCompanyDtos = new ArrayList<VehicleCompanyDto>();
        for(int i=0;i<result.size();i++){
            VehicleCompanyDto vehicleCompanyDto = new VehicleCompanyDto();
            VehicleCompany vehicleCompany = result.get(i);

            vehicleCompanyDto.setId(vehicleCompany.getId());
            vehicleCompanyDto.setCompanyName(vehicleCompany.getCompanyName());
            vehicleCompanyDto.setCreateUser(vehicleCompany.getCreateUser());
            vehicleCompanyDto.setCreateTime(vehicleCompany.getCreateTime());
            vehicleCompanyDto.setModifyTime(vehicleCompany.getModifyTime());
            vehicleCompanyDto.setModifyUser(vehicleCompany.getModifyUser());
            vehicleCompanyDtos.add(vehicleCompanyDto);
        }
//        List<VehicleCompanyDto> finalResult = new ArrayList<VehicleCompanyDto>();
//
//        finalResult.add(vehicleCompanyDtos);
//        pageDto.setResultList(finalResult);
//        PageDto<VehicleCompanyDto> pageDtos = new PageDto<VehicleCompanyDto>(vehicleCompanys.getPageNum(),vehicleCompanys.getPageSize(),
//                vehicleCompanys.getTotal(),vehicleCompanys.getPages(),vehicleCompanyDtos);
        pageDto.setResultList(vehicleCompanyDtos);
        return pageDto;
    }

    @Override
    public boolean removeVehilceCompany(Long cId) throws Exception {
        if(cId<=0){
            throw new Exception("删除异常");
        }
        boolean success = false;
        try {
            VehicleCompany vehicleCompany = vehicleCompanyMapper.selectByPrimaryKey(cId);
            if(vehicleCompany==null||vehicleCompany.equals("")){
                throw new Exception("该数据已删除");
            }
//            Remove remove = new Remove();
//            remove.stileBUsed(companyId);
            VehicleBrandExample vehicleBrandExample = new VehicleBrandExample();
            vehicleBrandExample.createCriteria().andCIdEqualTo(cId);
            List<VehicleBrand> vehicleBrand = vehicleBrandMapper.selectByExample(vehicleBrandExample);
            if(vehicleBrand.size()>0){
                return success;
            }
            int result = vehicleCompanyMapper.deleteByPrimaryKey(cId);
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean checkCompanyName(String name) throws Exception {
        if(name==null||name.equals("")){
            throw new Exception("汽车品牌不能为空");
        }
        boolean success = false;

        VehicleCompanyExample vehicleCompanyExample = new VehicleCompanyExample();
        vehicleCompanyExample.createCriteria().andCompanyNameEqualTo(name);
        try {
            List<VehicleCompany> result = vehicleCompanyMapper.selectByExample(vehicleCompanyExample);
            if(result.size()>0){
                throw new Exception ("汽车品牌已存在");
            }
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

}

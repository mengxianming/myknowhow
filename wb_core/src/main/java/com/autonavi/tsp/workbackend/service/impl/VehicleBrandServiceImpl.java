package com.autonavi.tsp.workbackend.service.impl;

import com.autonavi.tsp.workbackend.dao.VehicleBrandMapper;
import com.autonavi.tsp.workbackend.dao.VehicleCompanyMapper;
import com.autonavi.tsp.workbackend.dao.VehicleSeriesMapper;
import com.autonavi.tsp.workbackend.dto.PageDto;
import com.autonavi.tsp.workbackend.dto.VehicleBrandDto;
import com.autonavi.tsp.workbackend.model.*;
import com.autonavi.tsp.workbackend.service.IVehicleBrandService;
import com.autonavi.tsp.workbackend.util.page.Page;
import com.autonavi.tsp.workbackend.util.page.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2015/1/12.
 */
@Service("vehicleBrandService")
public class VehicleBrandServiceImpl implements IVehicleBrandService {

    private static int default_page_no = 1;

    private static int default_page_size = 20;

    @Autowired
    private VehicleBrandMapper vehicleBrandMapper;
    @Autowired
    private VehicleCompanyMapper vehicleCompanyMapper;
    @Autowired
    private VehicleSeriesMapper vehicleSeriesMapper;
    @Override
    public boolean createVehicleBrand(String name,String createUser,long cId) throws Exception {

        if(name==null ||name.equals("")){
            throw new Exception ("名称不能为空");
        }

        boolean success = false;
        try {
            VehicleCompany vehicleCompany = vehicleCompanyMapper.selectByPrimaryKey(cId);
            if (vehicleCompany == null || vehicleCompany.equals("")) {
                throw new Exception("车企不存在");
            }

            VehicleBrand vehicleBrand = new VehicleBrand();
            vehicleBrand.setBrandName(name);
            vehicleBrand.setCreateUser(createUser);
            vehicleBrand.setCreateTime(new Date());
            vehicleBrand.setModifyTime(new Date());
            vehicleBrand.setModifyUser(createUser);
            vehicleBrand.setCompanyName(vehicleCompany.getCompanyName());
            vehicleBrand.setcId(cId);
            int result = vehicleBrandMapper.insert(vehicleBrand);
            success = true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

//    @Override
//    public boolean updateVehicleBrand(String brandName, String modifyUser, long bId, String companyname, long cId) throws Exception {
//        return false;
//    }

    @Override
    public boolean updateVehicleBrand(String brandName, String modifyUser, long bId, String companyname, long cId) throws Exception {
        if(modifyUser==null||modifyUser.equals("")){
            throw new Exception("");
        }
//        VehicleBrandExample vehicleBrandExample = new VehicleBrandExample();
//        vehicleBrandExample.createCriteria().andBrandNameEqualTo(vehicleBrandDto.getBrandName()).andIdEqualTo(vehicleBrandDto.getId()).andOriginatorEqualTo(vehicleBrandDto.getOriginator())
//                .andCreateTimeEqualTo(DateUtil.getCurrentDate());

        boolean success = false;
        try {
            VehicleBrand vehicle = vehicleBrandMapper.selectByPrimaryKey(bId);
            if(vehicle==null||vehicle.equals("")){
                throw new Exception("更新数据不存在");
            }
            VehicleBrand vehicleBrand = new VehicleBrand();
            vehicleBrand.setId(bId);
            vehicleBrand.setcId(cId>0?cId:vehicle.getcId());
            vehicleBrand.setBrandName(brandName.equals("-1")?vehicle.getBrandName():brandName);
            vehicleBrand.setCompanyName(companyname.equals("-1")?vehicle.getCompanyName():companyname);
            vehicleBrand.setCreateTime(vehicle.getCreateTime());
            vehicleBrand.setCreateUser(vehicle.getCreateUser());
            vehicleBrand.setModifyUser(modifyUser);
            vehicleBrand.setModifyTime(new Date());
            int result = vehicleBrandMapper.updateByPrimaryKeySelective(vehicleBrand);

            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public PageDto<VehicleBrandDto> findVehicleBrand(String name, int pNo, int pSize, long cId)throws Exception {
        if(name==null||name.equals("")){
            throw new Exception("");
        }
        VehicleBrandExample vehicleBrandExample = new VehicleBrandExample();
        VehicleBrandExample.Criteria example =   vehicleBrandExample.createCriteria();
        if(!name.equals("-1")){
            example.andBrandNameEqualTo(name);
        }
        if(cId>0){
            example.andCIdEqualTo(cId);
        }
        PageHelper.startPage(pNo,pSize);
        List<VehicleBrand> result = vehicleBrandMapper.selectByExample(vehicleBrandExample);
        if(result == null || result.size()<=0){
            return null;
        }
        PageDto<VehicleBrandDto> pageDto = new PageDto<VehicleBrandDto>();
        if(result instanceof Page){
            Page page = (Page)result;
            pageDto.setPageNum(page.getPageNum());
            pageDto.setPageSize(page.getPageSize());
            pageDto.setPages(page.getPages());
            pageDto.setTotal(page.getTotal());
        }
        List<VehicleBrandDto> vehicleBrandDtos = new ArrayList<VehicleBrandDto>();
        for(int i=0;i<result.size();i++){
            VehicleBrandDto vehicleBrandDto = new VehicleBrandDto();
            VehicleBrand vehicleBrand = result.get(i);

            vehicleBrandDto.setId(vehicleBrand.getId());
            vehicleBrandDto.setcId(vehicleBrand.getcId());
            vehicleBrandDto.setBrandName(vehicleBrand.getBrandName());
            vehicleBrandDto.setCreateUser(vehicleBrand.getCreateUser());
            vehicleBrandDto.setCreateTime(vehicleBrand.getCreateTime());
            vehicleBrandDto.setModifyUser(vehicleBrand.getModifyUser());
            vehicleBrandDto.setModifyTime(vehicleBrand.getModifyTime());
            vehicleBrandDto.setCompanyName(vehicleBrand.getCompanyName());

            vehicleBrandDtos.add(vehicleBrandDto);
        }
        pageDto.setResultList(vehicleBrandDtos);
        return pageDto;
    }

    @Override
    public boolean removeVehilceBrand(Long bId) throws Exception {
        if(bId<=0){
            throw new Exception("删除异常");
        }
        boolean success = false;
        try {
            VehicleBrand vehicleBrand = vehicleBrandMapper.selectByPrimaryKey(bId);
            if(vehicleBrand==null||vehicleBrand.equals("")){
                throw new Exception("删除数据不存在");
            }
//            Remove remove = new Remove();
//            remove.stileBUsed(brandId);
            VehicleSeriesExample vehicleSeriesExample = new VehicleSeriesExample();
            vehicleSeriesExample.createCriteria().andBIdEqualTo(bId);
            List<VehicleSeries> vehicleVersion = vehicleSeriesMapper.selectByExample(vehicleSeriesExample);
            if(vehicleVersion.size()>0){
                return success;
            }
            int result = vehicleBrandMapper.deleteByPrimaryKey(bId);
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean checkBrandName(String name) throws Exception {
        if(name==null||name.equals("")){
            throw new Exception("汽车品牌不能为空");
        }
        boolean success = false;
        try {
        VehicleBrandExample vehicleBrandExample = new VehicleBrandExample();
        vehicleBrandExample.createCriteria().andBrandNameEqualTo(name);
        List<VehicleBrand> result = vehicleBrandMapper.selectByExample(vehicleBrandExample);

        if(result.size()>0){
            throw new Exception ("汽车品牌已存在");
        }
            success=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

}

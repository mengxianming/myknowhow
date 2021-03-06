package com.autonavi.tsp.workbackend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.autonavi.tsp.workbackend.model.VehicleCompany;
import com.autonavi.tsp.workbackend.model.VehicleCompanyExample;

public interface VehicleCompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int countByExample(VehicleCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int deleteByExample(VehicleCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int insert(VehicleCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int insertSelective(VehicleCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    List<VehicleCompany> selectByExample(VehicleCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    VehicleCompany selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByExampleSelective(@Param("record") VehicleCompany record, @Param("example") VehicleCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByExample(@Param("record") VehicleCompany record, @Param("example") VehicleCompanyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByPrimaryKeySelective(VehicleCompany record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_company
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByPrimaryKey(VehicleCompany record);
}
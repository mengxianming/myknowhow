package com.autonavi.tsp.workbackend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.autonavi.tsp.workbackend.model.VehicleSeries;
import com.autonavi.tsp.workbackend.model.VehicleSeriesExample;

public interface VehicleSeriesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int countByExample(VehicleSeriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int deleteByExample(VehicleSeriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int insert(VehicleSeries record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int insertSelective(VehicleSeries record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    List<VehicleSeries> selectByExample(VehicleSeriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    VehicleSeries selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByExampleSelective(@Param("record") VehicleSeries record, @Param("example") VehicleSeriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByExample(@Param("record") VehicleSeries record, @Param("example") VehicleSeriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByPrimaryKeySelective(VehicleSeries record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vehicle_series
     *
     * @mbggenerated Fri Jan 16 17:45:20 CST 2015
     */
    int updateByPrimaryKey(VehicleSeries record);
}
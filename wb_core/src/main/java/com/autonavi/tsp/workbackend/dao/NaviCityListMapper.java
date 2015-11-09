package com.autonavi.tsp.workbackend.dao;

import com.autonavi.tsp.workbackend.model.NaviCityList;
import com.autonavi.tsp.workbackend.model.NaviCityListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NaviCityListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int countByExample(NaviCityListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int deleteByExample(NaviCityListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int insert(NaviCityList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int insertSelective(NaviCityList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    List<NaviCityList> selectByExample(NaviCityListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    NaviCityList selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByExampleSelective(@Param("record") NaviCityList record, @Param("example") NaviCityListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByExample(@Param("record") NaviCityList record, @Param("example") NaviCityListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByPrimaryKeySelective(NaviCityList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_citylist
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByPrimaryKey(NaviCityList record);
}
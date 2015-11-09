package com.autonavi.tsp.workbackend.dao;

import com.autonavi.tsp.workbackend.model.NaviScreenDpi;
import com.autonavi.tsp.workbackend.model.NaviScreenDpiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NaviScreenDpiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int countByExample(NaviScreenDpiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int deleteByExample(NaviScreenDpiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int deleteByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int insert(NaviScreenDpi record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int insertSelective(NaviScreenDpi record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    List<NaviScreenDpi> selectByExample(NaviScreenDpiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    NaviScreenDpi selectByPrimaryKey(String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByExampleSelective(@Param("record") NaviScreenDpi record, @Param("example") NaviScreenDpiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByExample(@Param("record") NaviScreenDpi record, @Param("example") NaviScreenDpiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByPrimaryKeySelective(NaviScreenDpi record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_screen_dpi
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByPrimaryKey(NaviScreenDpi record);
}
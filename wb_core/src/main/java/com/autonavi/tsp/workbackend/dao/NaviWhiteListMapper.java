package com.autonavi.tsp.workbackend.dao;

import com.autonavi.tsp.workbackend.model.NaviWhiteList;
import com.autonavi.tsp.workbackend.model.NaviWhiteListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NaviWhiteListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int countByExample(NaviWhiteListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int deleteByExample(NaviWhiteListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int insert(NaviWhiteList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int insertSelective(NaviWhiteList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    List<NaviWhiteList> selectByExample(NaviWhiteListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    NaviWhiteList selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByExampleSelective(@Param("record") NaviWhiteList record, @Param("example") NaviWhiteListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByExample(@Param("record") NaviWhiteList record, @Param("example") NaviWhiteListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByPrimaryKeySelective(NaviWhiteList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table navi_white_list
     *
     * @mbggenerated Tue Jul 14 17:28:14 CST 2015
     */
    int updateByPrimaryKey(NaviWhiteList record);
}
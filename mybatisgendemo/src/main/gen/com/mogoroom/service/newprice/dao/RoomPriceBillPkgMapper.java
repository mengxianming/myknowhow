package com.mogoroom.service.newprice.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.newprice.domain.RoomPriceBillPkg;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomPriceBillPkgMapper extends BaseMapper<RoomPriceBillPkg> {
    int delete(Integer id);

    int insert(RoomPriceBillPkg record);

    RoomPriceBillPkg selectById(Integer id);

    int update(RoomPriceBillPkg record);

    List<RoomPriceBillPkg> selectByFeature(RoomPriceBillPkg feature);

    List<RoomPriceBillPkg> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomPriceBillPkg feature);
}
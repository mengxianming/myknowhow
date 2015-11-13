package com.mogoroom.service.newprice.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.newprice.domain.RoomPrice;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomPriceMapper extends BaseMapper<RoomPrice> {
    int delete(Integer id);

    int insert(RoomPrice record);

    RoomPrice selectById(Integer id);

    int update(RoomPrice record);

    List<RoomPrice> selectByFeature(RoomPrice feature);

    List<RoomPrice> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomPrice feature);
}
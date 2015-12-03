package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.flat.domain.RoomPriceNew;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomPriceNewMapper extends BaseMapper<RoomPriceNew> {
    int delete(Integer id);

    int insert(RoomPriceNew record);

    RoomPriceNew selectById(Integer id);

    int update(RoomPriceNew record);

    List<RoomPriceNew> selectByFeature(RoomPriceNew feature);

    List<RoomPriceNew> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomPriceNew feature);
}
package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.flat.domain.RoomBasePrice;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomBasePriceMapper extends BaseMapper<RoomBasePrice> {
    int delete(Integer id);

    int insert(RoomBasePrice record);

    RoomBasePrice selectById(Integer id);

    int update(RoomBasePrice record);

    List<RoomBasePrice> selectByFeature(RoomBasePrice feature);

    List<RoomBasePrice> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomBasePrice feature);
}
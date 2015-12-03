package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.flat.domain.RoomPriceBizType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomPriceBizTypeMapper extends BaseMapper<RoomPriceBizType> {
    int delete(Integer id);

    int insert(RoomPriceBizType record);

    RoomPriceBizType selectById(Integer id);

    int update(RoomPriceBizType record);

    List<RoomPriceBizType> selectByFeature(RoomPriceBizType feature);

    List<RoomPriceBizType> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomPriceBizType feature);
}
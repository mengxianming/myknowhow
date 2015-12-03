package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.flat.domain.RoomPriceDtl;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomPriceDtlMapper extends BaseMapper<RoomPriceDtl> {
    int delete(Integer id);

    int insert(RoomPriceDtl record);

    RoomPriceDtl selectById(Integer id);

    int update(RoomPriceDtl record);

    List<RoomPriceDtl> selectByFeature(RoomPriceDtl feature);

    List<RoomPriceDtl> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomPriceDtl feature);
}
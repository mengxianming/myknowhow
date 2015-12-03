package com.mogoroom.service.flat.dao;

import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import com.mogoroom.service.flat.domain.RoomPricePayType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomPricePayTypeMapper extends BaseMapper<RoomPricePayType> {
    int delete(Integer id);

    int insert(RoomPricePayType record);

    RoomPricePayType selectById(Integer id);

    int update(RoomPricePayType record);

    List<RoomPricePayType> selectByFeature(RoomPricePayType feature);

    List<RoomPricePayType> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomPricePayType feature);

    List<RoomPricePayType> selectByCriteria(QCriteria criteria);
}
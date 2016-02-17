package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.RoomStateTrans;
import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomStateTransMapper extends BaseMapper<RoomStateTrans> {
    int delete(Integer id);

    int insert(RoomStateTrans record);

    RoomStateTrans selectById(Integer id);

    int update(RoomStateTrans record);

    List<RoomStateTrans> selectByFeature(RoomStateTrans feature);

    List<RoomStateTrans> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomStateTrans feature);

    List<RoomStateTrans> selectByCriteria(QCriteria criteria);
}
package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.RoomStateTransHis;
import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomStateTransHisMapper extends BaseMapper<RoomStateTransHis> {
    int delete(Integer id);

    int insert(RoomStateTransHis record);

    RoomStateTransHis selectById(Integer id);

    int update(RoomStateTransHis record);

    List<RoomStateTransHis> selectByFeature(RoomStateTransHis feature);

    List<RoomStateTransHis> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(RoomStateTransHis feature);

    List<RoomStateTransHis> selectByCriteria(QCriteria criteria);
}
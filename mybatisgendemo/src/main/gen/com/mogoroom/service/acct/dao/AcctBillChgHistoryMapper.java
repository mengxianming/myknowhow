package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctBillChgHistory;
import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcctBillChgHistoryMapper extends BaseMapper<AcctBillChgHistory> {
    int delete(Integer id);

    int insert(AcctBillChgHistory record);

    AcctBillChgHistory selectById(Integer id);

    int update(AcctBillChgHistory record);

    List<AcctBillChgHistory> selectByFeature(AcctBillChgHistory feature);

    List<AcctBillChgHistory> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(AcctBillChgHistory feature);

    List<AcctBillChgHistory> selectByCriteria(QCriteria criteria);
}
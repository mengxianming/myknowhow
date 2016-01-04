package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctBillDtlChgHistory;
import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcctBillDtlChgHistoryMapper extends BaseMapper<AcctBillDtlChgHistory> {
    int delete(Integer id);

    int insert(AcctBillDtlChgHistory record);

    AcctBillDtlChgHistory selectById(Integer id);

    int update(AcctBillDtlChgHistory record);

    List<AcctBillDtlChgHistory> selectByFeature(AcctBillDtlChgHistory feature);

    List<AcctBillDtlChgHistory> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(AcctBillDtlChgHistory feature);

    List<AcctBillDtlChgHistory> selectByCriteria(QCriteria criteria);
}
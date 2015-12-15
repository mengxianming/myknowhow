package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctTradeLogUnionPayRefundWithBLOBs;
import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcctTradeLogUnionPayRefundMapper extends BaseMapper<AcctTradeLogUnionPayRefund> {
    int delete(Integer id);

    int insert(AcctTradeLogUnionPayRefundWithBLOBs record);

    AcctTradeLogUnionPayRefundWithBLOBs selectById(Integer id);

    int update(AcctTradeLogUnionPayRefundWithBLOBs record);

    List<AcctTradeLogUnionPayRefund> selectByFeature(AcctTradeLogUnionPayRefund feature);

    List<AcctTradeLogUnionPayRefund> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(AcctTradeLogUnionPayRefund feature);

    List<AcctTradeLogUnionPayRefund> selectByCriteria(QCriteria criteria);
}
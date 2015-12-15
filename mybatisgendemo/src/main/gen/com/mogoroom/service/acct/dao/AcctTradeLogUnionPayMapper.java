package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctTradeLogUnionPayWithBLOBs;
import com.mogoroom.service.base.dao.BaseMapper;
import com.mogoroom.service.base.dao.QCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcctTradeLogUnionPayMapper extends BaseMapper<AcctTradeLogUnionPay> {
    int delete(Integer id);

    int insert(AcctTradeLogUnionPayWithBLOBs record);

    AcctTradeLogUnionPayWithBLOBs selectById(Integer id);

    int update(AcctTradeLogUnionPayWithBLOBs record);

    List<AcctTradeLogUnionPay> selectByFeature(AcctTradeLogUnionPay feature);

    List<AcctTradeLogUnionPay> selectByFieldList(@Param("field") String field, @Param("values") List<?> values);

    int deleteByFeature(AcctTradeLogUnionPay feature);

    List<AcctTradeLogUnionPay> selectByCriteria(QCriteria criteria);
}
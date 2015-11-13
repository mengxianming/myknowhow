package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctBillDtlType;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AcctBillDtlTypeMapper extends BaseMapper<AcctBillDtlType> {
    int delete(Integer id);

    int insert(AcctBillDtlType record);

    AcctBillDtlType selectById(Integer id);

    int update(AcctBillDtlType record);
}
package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AcctBillDtlType;

public interface AcctBillDtlTypeMapper extends BaseAcctMapper<AcctBillDtlType> {
    int delete(Integer id);

    int insert(AcctBillDtlType record);

    AcctBillDtlType selectById(Integer id);

    int update(AcctBillDtlType record);
}
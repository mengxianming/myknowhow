package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AcctBillType;

public interface AcctBillTypeMapper extends BaseAcctMapper<AcctBillType> {
    int delete(Integer id);

    int insert(AcctBillType record);

    AcctBillType selectById(Integer id);

    int update(AcctBillType record);
}
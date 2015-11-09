package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AcctBillDtl;

public interface AcctBillDtlMapper extends BaseAcctMapper<AcctBillDtl> {
    int delete(Integer billDtlId);

    int insert(AcctBillDtl record);

    AcctBillDtl selectById(Integer billDtlId);

    int update(AcctBillDtl record);
}
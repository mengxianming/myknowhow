package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AcctBill;

public interface AcctBillMapper extends BaseAcctMapper<AcctBill> {
    int delete(Integer billId);

    int insert(AcctBill record);

    AcctBill selectById(Integer billId);

    int update(AcctBill record);
}
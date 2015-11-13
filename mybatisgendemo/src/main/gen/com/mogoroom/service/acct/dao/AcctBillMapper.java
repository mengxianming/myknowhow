package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctBill;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AcctBillMapper extends BaseMapper<AcctBill> {
    int delete(Integer billId);

    int insert(AcctBill record);

    AcctBill selectById(Integer billId);

    int update(AcctBill record);
}
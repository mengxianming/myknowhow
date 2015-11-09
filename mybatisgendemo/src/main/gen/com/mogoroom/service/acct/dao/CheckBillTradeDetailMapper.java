package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckBillTradeDetail;

public interface CheckBillTradeDetailMapper extends BaseAcctMapper<CheckBillTradeDetail> {
    int delete(Integer id);

    int insert(CheckBillTradeDetail record);

    CheckBillTradeDetail selectById(Integer id);

    int update(CheckBillTradeDetail record);
}
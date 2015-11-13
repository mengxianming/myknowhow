package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckBillTradeDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckBillTradeDetailMapper extends BaseMapper<CheckBillTradeDetail> {
    int delete(Integer id);

    int insert(CheckBillTradeDetail record);

    CheckBillTradeDetail selectById(Integer id);

    int update(CheckBillTradeDetail record);
}
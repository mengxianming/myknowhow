package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckTradeDetail;

public interface CheckTradeDetailMapper extends BaseAcctMapper<CheckTradeDetail> {
    int delete(Integer id);

    int insert(CheckTradeDetail record);

    CheckTradeDetail selectById(Integer id);

    int update(CheckTradeDetail record);
}
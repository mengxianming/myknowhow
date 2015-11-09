package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckTradeAlipay;

public interface CheckTradeAlipayMapper extends BaseAcctMapper<CheckTradeAlipay> {
    int delete(Integer id);

    int insert(CheckTradeAlipay record);

    CheckTradeAlipay selectById(Integer id);

    int update(CheckTradeAlipay record);
}
package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckTradeWeixin;

public interface CheckTradeWeixinMapper extends BaseAcctMapper<CheckTradeWeixin> {
    int delete(Integer id);

    int insert(CheckTradeWeixin record);

    CheckTradeWeixin selectById(Integer id);

    int update(CheckTradeWeixin record);
}
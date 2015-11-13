package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckTradeWeixin;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckTradeWeixinMapper extends BaseMapper<CheckTradeWeixin> {
    int delete(Integer id);

    int insert(CheckTradeWeixin record);

    CheckTradeWeixin selectById(Integer id);

    int update(CheckTradeWeixin record);
}
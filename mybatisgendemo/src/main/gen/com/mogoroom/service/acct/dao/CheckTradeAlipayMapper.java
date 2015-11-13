package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckTradeAlipay;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckTradeAlipayMapper extends BaseMapper<CheckTradeAlipay> {
    int delete(Integer id);

    int insert(CheckTradeAlipay record);

    CheckTradeAlipay selectById(Integer id);

    int update(CheckTradeAlipay record);
}
package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckTradeDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckTradeDetailMapper extends BaseMapper<CheckTradeDetail> {
    int delete(Integer id);

    int insert(CheckTradeDetail record);

    CheckTradeDetail selectById(Integer id);

    int update(CheckTradeDetail record);
}
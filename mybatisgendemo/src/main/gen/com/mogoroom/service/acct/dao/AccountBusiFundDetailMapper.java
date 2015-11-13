package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountBusiFundDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountBusiFundDetailMapper extends BaseMapper<AccountBusiFundDetail> {
    int delete(Integer id);

    int insert(AccountBusiFundDetail record);

    AccountBusiFundDetail selectById(Integer id);

    int update(AccountBusiFundDetail record);
}
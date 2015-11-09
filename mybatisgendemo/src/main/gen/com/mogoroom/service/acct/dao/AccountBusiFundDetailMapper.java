package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountBusiFundDetail;

public interface AccountBusiFundDetailMapper extends BaseAcctMapper<AccountBusiFundDetail> {
    int delete(Integer id);

    int insert(AccountBusiFundDetail record);

    AccountBusiFundDetail selectById(Integer id);

    int update(AccountBusiFundDetail record);
}
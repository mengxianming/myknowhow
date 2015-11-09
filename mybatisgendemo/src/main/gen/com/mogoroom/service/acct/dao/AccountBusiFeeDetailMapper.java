package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountBusiFeeDetail;

public interface AccountBusiFeeDetailMapper extends BaseAcctMapper<AccountBusiFeeDetail> {
    int delete(Integer id);

    int insert(AccountBusiFeeDetail record);

    AccountBusiFeeDetail selectById(Integer id);

    int update(AccountBusiFeeDetail record);
}
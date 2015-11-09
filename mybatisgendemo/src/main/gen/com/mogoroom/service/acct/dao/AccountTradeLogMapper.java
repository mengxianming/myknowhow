package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountTradeLog;

public interface AccountTradeLogMapper extends BaseAcctMapper<AccountTradeLog> {
    int delete(Integer id);

    int insert(AccountTradeLog record);

    AccountTradeLog selectById(Integer id);

    int update(AccountTradeLog record);
}
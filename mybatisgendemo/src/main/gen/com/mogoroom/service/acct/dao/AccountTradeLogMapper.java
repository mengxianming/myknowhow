package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountTradeLog;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountTradeLogMapper extends BaseMapper<AccountTradeLog> {
    int delete(Integer id);

    int insert(AccountTradeLog record);

    AccountTradeLog selectById(Integer id);

    int update(AccountTradeLog record);
}
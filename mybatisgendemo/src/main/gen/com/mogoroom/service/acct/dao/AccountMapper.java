package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.Account;

public interface AccountMapper extends BaseAcctMapper<Account> {
    int delete(Integer id);

    int insert(Account record);

    Account selectById(Integer id);

    int update(Account record);
}
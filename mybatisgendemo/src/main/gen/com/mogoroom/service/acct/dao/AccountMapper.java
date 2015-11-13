package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.Account;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountMapper extends BaseMapper<Account> {
    int delete(Integer id);

    int insert(Account record);

    Account selectById(Integer id);

    int update(Account record);
}
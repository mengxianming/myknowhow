package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountFundType;

public interface AccountFundTypeMapper extends BaseAcctMapper<AccountFundType> {
    int delete(Integer id);

    int insert(AccountFundType record);

    AccountFundType selectById(Integer id);

    int update(AccountFundType record);
}
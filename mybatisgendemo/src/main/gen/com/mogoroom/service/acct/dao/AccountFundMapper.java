package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountFund;

public interface AccountFundMapper extends BaseAcctMapper<AccountFund> {
    int delete(Integer id);

    int insert(AccountFund record);

    AccountFund selectById(Integer id);

    int update(AccountFund record);
}
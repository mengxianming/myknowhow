package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountBillTypeToFundType;

public interface AccountBillTypeToFundTypeMapper extends BaseAcctMapper<AccountBillTypeToFundType> {
    int delete(Integer billType);

    int insert(AccountBillTypeToFundType record);

    AccountBillTypeToFundType selectById(Integer billType);

    int update(AccountBillTypeToFundType record);
}
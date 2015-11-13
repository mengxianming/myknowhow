package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountBillTypeToFundType;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountBillTypeToFundTypeMapper extends BaseMapper<AccountBillTypeToFundType> {
    int delete(Integer billType);

    int insert(AccountBillTypeToFundType record);

    AccountBillTypeToFundType selectById(Integer billType);

    int update(AccountBillTypeToFundType record);
}
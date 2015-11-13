package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountFundType;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountFundTypeMapper extends BaseMapper<AccountFundType> {
    int delete(Integer id);

    int insert(AccountFundType record);

    AccountFundType selectById(Integer id);

    int update(AccountFundType record);
}
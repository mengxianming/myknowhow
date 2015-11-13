package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountFund;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountFundMapper extends BaseMapper<AccountFund> {
    int delete(Integer id);

    int insert(AccountFund record);

    AccountFund selectById(Integer id);

    int update(AccountFund record);
}
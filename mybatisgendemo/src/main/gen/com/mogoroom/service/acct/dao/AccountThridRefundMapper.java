package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountThridRefund;

public interface AccountThridRefundMapper extends BaseAcctMapper<AccountThridRefund> {
    int delete(Integer id);

    int insert(AccountThridRefund record);

    AccountThridRefund selectById(Integer id);

    int update(AccountThridRefund record);
}
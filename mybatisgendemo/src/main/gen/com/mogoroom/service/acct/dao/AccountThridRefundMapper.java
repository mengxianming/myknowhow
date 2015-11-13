package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountThridRefund;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountThridRefundMapper extends BaseMapper<AccountThridRefund> {
    int delete(Integer id);

    int insert(AccountThridRefund record);

    AccountThridRefund selectById(Integer id);

    int update(AccountThridRefund record);
}
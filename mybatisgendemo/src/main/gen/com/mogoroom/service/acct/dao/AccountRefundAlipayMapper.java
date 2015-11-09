package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountRefundAlipay;

public interface AccountRefundAlipayMapper extends BaseAcctMapper<AccountRefundAlipay> {
    int delete(Integer id);

    int insert(AccountRefundAlipay record);

    AccountRefundAlipay selectById(Integer id);

    int update(AccountRefundAlipay record);
}
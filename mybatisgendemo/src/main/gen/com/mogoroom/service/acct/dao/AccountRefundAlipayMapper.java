package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountRefundAlipay;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountRefundAlipayMapper extends BaseMapper<AccountRefundAlipay> {
    int delete(Integer id);

    int insert(AccountRefundAlipay record);

    AccountRefundAlipay selectById(Integer id);

    int update(AccountRefundAlipay record);
}
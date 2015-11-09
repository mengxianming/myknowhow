package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountRefundWeiXin;

public interface AccountRefundWeiXinMapper extends BaseAcctMapper<AccountRefundWeiXin> {
    int delete(Integer id);

    int insert(AccountRefundWeiXin record);

    AccountRefundWeiXin selectById(Integer id);

    int update(AccountRefundWeiXin record);
}
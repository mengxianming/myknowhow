package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountRefundWeiXin;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountRefundWeiXinMapper extends BaseMapper<AccountRefundWeiXin> {
    int delete(Integer id);

    int insert(AccountRefundWeiXin record);

    AccountRefundWeiXin selectById(Integer id);

    int update(AccountRefundWeiXin record);
}
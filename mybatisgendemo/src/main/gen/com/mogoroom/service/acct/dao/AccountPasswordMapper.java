package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountPassword;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountPasswordMapper extends BaseMapper<AccountPassword> {
    int delete(Integer id);

    int insert(AccountPassword record);

    AccountPassword selectById(Integer id);

    int update(AccountPassword record);
}
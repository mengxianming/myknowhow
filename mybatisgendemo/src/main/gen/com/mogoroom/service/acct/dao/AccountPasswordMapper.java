package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountPassword;

public interface AccountPasswordMapper extends BaseAcctMapper<AccountPassword> {
    int delete(Integer id);

    int insert(AccountPassword record);

    AccountPassword selectById(Integer id);

    int update(AccountPassword record);
}
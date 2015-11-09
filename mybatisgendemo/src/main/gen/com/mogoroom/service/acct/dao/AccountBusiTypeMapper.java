package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountBusiType;

public interface AccountBusiTypeMapper extends BaseAcctMapper<AccountBusiType> {
    int delete(Integer id);

    int insert(AccountBusiType record);

    AccountBusiType selectById(Integer id);

    int update(AccountBusiType record);
}
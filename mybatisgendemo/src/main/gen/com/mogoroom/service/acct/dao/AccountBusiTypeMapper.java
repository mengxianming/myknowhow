package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountBusiType;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountBusiTypeMapper extends BaseMapper<AccountBusiType> {
    int delete(Integer id);

    int insert(AccountBusiType record);

    AccountBusiType selectById(Integer id);

    int update(AccountBusiType record);
}
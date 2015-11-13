package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountBusiFeeDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountBusiFeeDetailMapper extends BaseMapper<AccountBusiFeeDetail> {
    int delete(Integer id);

    int insert(AccountBusiFeeDetail record);

    AccountBusiFeeDetail selectById(Integer id);

    int update(AccountBusiFeeDetail record);
}
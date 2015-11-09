package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountBusiBillDetail;

public interface AccountBusiBillDetailMapper extends BaseAcctMapper<AccountBusiBillDetail> {
    int delete(Integer id);

    int insert(AccountBusiBillDetail record);

    AccountBusiBillDetail selectById(Integer id);

    int update(AccountBusiBillDetail record);
}
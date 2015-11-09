package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AccountBusiRec;

public interface AccountBusiRecMapper extends BaseAcctMapper<AccountBusiRec> {
    int delete(Integer doneCode);

    int insert(AccountBusiRec record);

    AccountBusiRec selectById(Integer doneCode);

    int update(AccountBusiRec record);
}
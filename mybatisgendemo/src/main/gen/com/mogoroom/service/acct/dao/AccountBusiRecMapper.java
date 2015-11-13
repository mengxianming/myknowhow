package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountBusiRec;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountBusiRecMapper extends BaseMapper<AccountBusiRec> {
    int delete(Integer doneCode);

    int insert(AccountBusiRec record);

    AccountBusiRec selectById(Integer doneCode);

    int update(AccountBusiRec record);
}
package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AccountBusiBillDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AccountBusiBillDetailMapper extends BaseMapper<AccountBusiBillDetail> {
    int delete(Integer id);

    int insert(AccountBusiBillDetail record);

    AccountBusiBillDetail selectById(Integer id);

    int update(AccountBusiBillDetail record);
}
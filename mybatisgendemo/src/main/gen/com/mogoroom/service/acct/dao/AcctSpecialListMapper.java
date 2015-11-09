package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AcctSpecialList;

public interface AcctSpecialListMapper extends BaseAcctMapper<AcctSpecialList> {
    int delete(Integer id);

    int insert(AcctSpecialList record);

    AcctSpecialList selectById(Integer id);

    int update(AcctSpecialList record);
}
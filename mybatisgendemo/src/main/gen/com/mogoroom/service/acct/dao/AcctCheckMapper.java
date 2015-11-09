package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.AcctCheck;

public interface AcctCheckMapper extends BaseAcctMapper<AcctCheck> {
    int delete(Integer id);

    int insert(AcctCheck record);

    AcctCheck selectById(Integer id);

    int update(AcctCheck record);
}
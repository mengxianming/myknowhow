package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctSpecialList;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AcctSpecialListMapper extends BaseMapper<AcctSpecialList> {
    int delete(Integer id);

    int insert(AcctSpecialList record);

    AcctSpecialList selectById(Integer id);

    int update(AcctSpecialList record);
}
package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctCheck;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AcctCheckMapper extends BaseMapper<AcctCheck> {
    int delete(Integer id);

    int insert(AcctCheck record);

    AcctCheck selectById(Integer id);

    int update(AcctCheck record);
}
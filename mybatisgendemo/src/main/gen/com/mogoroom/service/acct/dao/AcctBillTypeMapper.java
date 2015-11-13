package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.AcctBillType;
import com.mogoroom.service.base.dao.BaseMapper;

public interface AcctBillTypeMapper extends BaseMapper<AcctBillType> {
    int delete(Integer id);

    int insert(AcctBillType record);

    AcctBillType selectById(Integer id);

    int update(AcctBillType record);
}
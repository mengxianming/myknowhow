package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckBillFundDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckBillFundDetailMapper extends BaseMapper<CheckBillFundDetail> {
    int delete(Integer id);

    int insert(CheckBillFundDetail record);

    CheckBillFundDetail selectById(Integer id);

    int update(CheckBillFundDetail record);
}
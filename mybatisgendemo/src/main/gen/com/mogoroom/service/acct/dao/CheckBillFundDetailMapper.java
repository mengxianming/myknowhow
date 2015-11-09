package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckBillFundDetail;

public interface CheckBillFundDetailMapper extends BaseAcctMapper<CheckBillFundDetail> {
    int delete(Integer id);

    int insert(CheckBillFundDetail record);

    CheckBillFundDetail selectById(Integer id);

    int update(CheckBillFundDetail record);
}
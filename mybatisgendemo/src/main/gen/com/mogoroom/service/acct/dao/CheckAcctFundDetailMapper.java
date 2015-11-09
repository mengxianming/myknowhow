package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckAcctFundDetail;

public interface CheckAcctFundDetailMapper extends BaseAcctMapper<CheckAcctFundDetail> {
    int delete(Integer id);

    int insert(CheckAcctFundDetail record);

    CheckAcctFundDetail selectById(Integer id);

    int update(CheckAcctFundDetail record);
}
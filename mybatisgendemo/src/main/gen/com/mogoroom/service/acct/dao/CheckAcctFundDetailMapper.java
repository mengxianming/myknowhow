package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckAcctFundDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckAcctFundDetailMapper extends BaseMapper<CheckAcctFundDetail> {
    int delete(Integer id);

    int insert(CheckAcctFundDetail record);

    CheckAcctFundDetail selectById(Integer id);

    int update(CheckAcctFundDetail record);
}
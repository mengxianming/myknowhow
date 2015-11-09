package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.dao.domain.CheckBizDetail;

public interface CheckBizDetailMapper extends BaseAcctMapper<CheckBizDetail> {
    int delete(Integer id);

    int insert(CheckBizDetail record);

    CheckBizDetail selectById(Integer id);

    int update(CheckBizDetail record);
}
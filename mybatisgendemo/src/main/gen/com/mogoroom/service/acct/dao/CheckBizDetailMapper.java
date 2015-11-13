package com.mogoroom.service.acct.dao;

import com.mogoroom.service.acct.domain.CheckBizDetail;
import com.mogoroom.service.base.dao.BaseMapper;

public interface CheckBizDetailMapper extends BaseMapper<CheckBizDetail> {
    int delete(Integer id);

    int insert(CheckBizDetail record);

    CheckBizDetail selectById(Integer id);

    int update(CheckBizDetail record);
}
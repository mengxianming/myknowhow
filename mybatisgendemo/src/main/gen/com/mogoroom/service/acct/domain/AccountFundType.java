package com.mogoroom.service.acct.domain;

import java.io.Serializable;

public class AccountFundType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String fundCode;

    private Integer fundName;

    private Byte status;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    public Integer getFundName() {
        return fundName;
    }

    public void setFundName(Integer fundName) {
        this.fundName = fundName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
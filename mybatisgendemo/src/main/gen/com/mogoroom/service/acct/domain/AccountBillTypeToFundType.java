package com.mogoroom.service.acct.domain;

import java.io.Serializable;

public class AccountBillTypeToFundType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer billType;

    private Integer fromFundType;

    private Integer toFundType;

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Integer getFromFundType() {
        return fromFundType;
    }

    public void setFromFundType(Integer fromFundType) {
        this.fromFundType = fromFundType;
    }

    public Integer getToFundType() {
        return toFundType;
    }

    public void setToFundType(Integer toFundType) {
        this.toFundType = toFundType;
    }
}
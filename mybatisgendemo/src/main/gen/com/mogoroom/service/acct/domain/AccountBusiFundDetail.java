package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountBusiFundDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer doneCode;

    private Integer acctId;

    private Integer fundType;

    private BigDecimal amount;

    private BigDecimal beforeAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(Integer doneCode) {
        this.doneCode = doneCode;
    }

    public Integer getAcctId() {
        return acctId;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }
}
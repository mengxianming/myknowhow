package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountFund implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer acctId;

    private Integer fundType;

    private BigDecimal amount;

    private BigDecimal frozen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }
}
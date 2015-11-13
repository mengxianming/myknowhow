package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountBusiBillDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer doneCode;

    private Integer billId;

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

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
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
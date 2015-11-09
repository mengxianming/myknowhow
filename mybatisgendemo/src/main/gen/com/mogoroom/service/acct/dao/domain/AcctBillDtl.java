package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AcctBillDtl implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer billDtlId;

    private Integer billId;

    private Integer billDtlType;

    private BigDecimal amount;

    public Integer getBillDtlId() {
        return billDtlId;
    }

    public void setBillDtlId(Integer billDtlId) {
        this.billDtlId = billDtlId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getBillDtlType() {
        return billDtlType;
    }

    public void setBillDtlType(Integer billDtlType) {
        this.billDtlType = billDtlType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
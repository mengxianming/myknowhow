package com.mogoroom.service.flat.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class RoomPriceDtl implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer priceId;

    private Integer billType;

    private Integer billDtlType;

    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
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
package com.mogoroom.service.newprice.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class RoomPriceBillPkg implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roomPriceId;

    private Integer billType;

    private Byte billDtlType;

    private BigDecimal amount;

    private BigDecimal basePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomPriceId() {
        return roomPriceId;
    }

    public void setRoomPriceId(Integer roomPriceId) {
        this.roomPriceId = roomPriceId;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Byte getBillDtlType() {
        return billDtlType;
    }

    public void setBillDtlType(Byte billDtlType) {
        this.billDtlType = billDtlType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}
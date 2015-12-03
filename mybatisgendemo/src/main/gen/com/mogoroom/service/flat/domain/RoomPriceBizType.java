package com.mogoroom.service.flat.domain;

import java.io.Serializable;

public class RoomPriceBizType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer payTypeId;

    private Byte payStage;

    private Integer bizType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public Byte getPayStage() {
        return payStage;
    }

    public void setPayStage(Byte payStage) {
        this.payStage = payStage;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
}
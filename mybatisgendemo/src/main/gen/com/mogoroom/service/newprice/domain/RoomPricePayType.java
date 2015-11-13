package com.mogoroom.service.newprice.domain;

import java.io.Serializable;

public class RoomPricePayType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Byte rentPeriods;

    private Byte depositPeriods;

    private Byte finaProdType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getRentPeriods() {
        return rentPeriods;
    }

    public void setRentPeriods(Byte rentPeriods) {
        this.rentPeriods = rentPeriods;
    }

    public Byte getDepositPeriods() {
        return depositPeriods;
    }

    public void setDepositPeriods(Byte depositPeriods) {
        this.depositPeriods = depositPeriods;
    }

    public Byte getFinaProdType() {
        return finaProdType;
    }

    public void setFinaProdType(Byte finaProdType) {
        this.finaProdType = finaProdType;
    }
}
package com.mogoroom.service.flat.domain;

import java.io.Serializable;

public class RoomPricePayType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Byte rentPeriods;

    private Byte foregiftPeriods;

    private Byte finProdType;

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

    public Byte getForegiftPeriods() {
        return foregiftPeriods;
    }

    public void setForegiftPeriods(Byte foregiftPeriods) {
        this.foregiftPeriods = foregiftPeriods;
    }

    public Byte getFinProdType() {
        return finProdType;
    }

    public void setFinProdType(Byte finProdType) {
        this.finProdType = finProdType;
    }
}
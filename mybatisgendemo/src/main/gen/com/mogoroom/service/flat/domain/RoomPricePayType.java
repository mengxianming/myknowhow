package com.mogoroom.service.flat.domain;

import java.io.Serializable;

public class RoomPricePayType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    //付款类型名，如付三押一+蘑菇宝
    private String name;

    //首期款租金期数
    private Byte rentPeriods;

    //首期款押金期数
    private Byte foregiftPeriods;

    //金融产品类型。0 不使用; 1 蘑菇宝；
    private Byte finProdType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取付款类型名，如付三押一+蘑菇宝
     */
    public String getName() {
        return name;
    }

    /**
     * 设置付款类型名，如付三押一+蘑菇宝
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取首期款租金期数
     */
    public Byte getRentPeriods() {
        return rentPeriods;
    }

    /**
     * 设置首期款租金期数
     */
    public void setRentPeriods(Byte rentPeriods) {
        this.rentPeriods = rentPeriods;
    }

    /**
     * 获取首期款押金期数
     */
    public Byte getForegiftPeriods() {
        return foregiftPeriods;
    }

    /**
     * 设置首期款押金期数
     */
    public void setForegiftPeriods(Byte foregiftPeriods) {
        this.foregiftPeriods = foregiftPeriods;
    }

    /**
     * 获取金融产品类型。0 不使用; 1 蘑菇宝；
     */
    public Byte getFinProdType() {
        return finProdType;
    }

    /**
     * 设置金融产品类型。0 不使用; 1 蘑菇宝；
     */
    public void setFinProdType(Byte finProdType) {
        this.finProdType = finProdType;
    }
}
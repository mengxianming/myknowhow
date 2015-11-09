package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;

public class AcctBillType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer billType;

    private String billName;

    private Byte status;

    private Integer sort;

    private Integer penaltyRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName == null ? null : billName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(Integer penaltyRate) {
        this.penaltyRate = penaltyRate;
    }
}
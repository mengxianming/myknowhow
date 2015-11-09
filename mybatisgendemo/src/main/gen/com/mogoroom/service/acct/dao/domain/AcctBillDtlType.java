package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;

public class AcctBillDtlType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer billDtlType;

    private String billDtlName;

    private Byte status;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillDtlType() {
        return billDtlType;
    }

    public void setBillDtlType(Integer billDtlType) {
        this.billDtlType = billDtlType;
    }

    public String getBillDtlName() {
        return billDtlName;
    }

    public void setBillDtlName(String billDtlName) {
        this.billDtlName = billDtlName == null ? null : billDtlName.trim();
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
}
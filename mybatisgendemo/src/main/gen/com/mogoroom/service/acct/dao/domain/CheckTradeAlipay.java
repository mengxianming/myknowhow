package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;

public class CheckTradeAlipay implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String soDoneCode;

    private String fcDoneCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSoDoneCode() {
        return soDoneCode;
    }

    public void setSoDoneCode(String soDoneCode) {
        this.soDoneCode = soDoneCode == null ? null : soDoneCode.trim();
    }

    public String getFcDoneCode() {
        return fcDoneCode;
    }

    public void setFcDoneCode(String fcDoneCode) {
        this.fcDoneCode = fcDoneCode == null ? null : fcDoneCode.trim();
    }
}
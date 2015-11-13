package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.util.Date;

public class AccountTradeLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String soDoneCode;

    private Integer busiType;

    private Byte channel;

    private Integer fundChannel;

    private String fcDoneCode;

    private Byte useFund;

    private Integer billId;

    private Date createTime;

    private Integer createByType;

    private Integer createBy;

    private Date updateTime;

    private Integer status;

    private String remark;

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

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public Integer getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(Integer fundChannel) {
        this.fundChannel = fundChannel;
    }

    public String getFcDoneCode() {
        return fcDoneCode;
    }

    public void setFcDoneCode(String fcDoneCode) {
        this.fcDoneCode = fcDoneCode == null ? null : fcDoneCode.trim();
    }

    public Byte getUseFund() {
        return useFund;
    }

    public void setUseFund(Byte useFund) {
        this.useFund = useFund;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateByType() {
        return createByType;
    }

    public void setCreateByType(Integer createByType) {
        this.createByType = createByType;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
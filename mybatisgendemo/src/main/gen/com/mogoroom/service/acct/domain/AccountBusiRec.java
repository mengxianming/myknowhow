package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.util.Date;

public class AccountBusiRec implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer doneCode;

    private String tradelogid;

    private Integer busiType;

    private Byte status;

    private Integer reRecId;

    private Date createTime;

    private Integer createBy;

    private Byte createByType;

    private Byte channel;

    private String remark;

    public Integer getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(Integer doneCode) {
        this.doneCode = doneCode;
    }

    public String getTradelogid() {
        return tradelogid;
    }

    public void setTradelogid(String tradelogid) {
        this.tradelogid = tradelogid == null ? null : tradelogid.trim();
    }

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getReRecId() {
        return reRecId;
    }

    public void setReRecId(Integer reRecId) {
        this.reRecId = reRecId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Byte getCreateByType() {
        return createByType;
    }

    public void setCreateByType(Byte createByType) {
        this.createByType = createByType;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
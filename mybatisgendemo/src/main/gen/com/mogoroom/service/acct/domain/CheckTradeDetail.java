package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.util.Date;

public class CheckTradeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer checkId;

    private String tradeNo;

    private String oppoTradeNo;

    private Date createTime;

    private Date updateTime;

    private Integer updateBy;

    private Byte fixed;

    private String remark;

    private Byte verified;

    private Integer fundChannel;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getOppoTradeNo() {
        return oppoTradeNo;
    }

    public void setOppoTradeNo(String oppoTradeNo) {
        this.oppoTradeNo = oppoTradeNo == null ? null : oppoTradeNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Byte getFixed() {
        return fixed;
    }

    public void setFixed(Byte fixed) {
        this.fixed = fixed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getVerified() {
        return verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    public Integer getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(Integer fundChannel) {
        this.fundChannel = fundChannel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
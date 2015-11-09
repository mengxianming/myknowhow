package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class CheckBillTradeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer checkId;

    private Integer tradeLogId;

    private Integer billTradeLogId;

    private Date createTime;

    private Date updateTime;

    private Integer updateBy;

    private Byte fixed;

    private String remark;

    private Byte verified;

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

    public Integer getTradeLogId() {
        return tradeLogId;
    }

    public void setTradeLogId(Integer tradeLogId) {
        this.tradeLogId = tradeLogId;
    }

    public Integer getBillTradeLogId() {
        return billTradeLogId;
    }

    public void setBillTradeLogId(Integer billTradeLogId) {
        this.billTradeLogId = billTradeLogId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
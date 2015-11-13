package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.util.Date;

public class AcctCheck implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer updateBy;

    private Byte fixed;

    private String remark;

    private Byte verified;

    private Byte checkResult;

    private Byte fundChannel;

    private Date checkDate;

    private Byte checkType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Byte getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Byte checkResult) {
        this.checkResult = checkResult;
    }

    public Byte getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(Byte fundChannel) {
        this.fundChannel = fundChannel;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Byte getCheckType() {
        return checkType;
    }

    public void setCheckType(Byte checkType) {
        this.checkType = checkType;
    }
}
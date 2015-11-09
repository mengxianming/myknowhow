package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class AcctSpecialList implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer acctId;

    private Date createTime;

    private Integer createBy;

    private Byte createByType;

    private Date updateTime;

    private Integer updateBy;

    private Byte udpateByType;

    private Byte blackType;

    private Byte status;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcctId() {
        return acctId;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
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

    public Byte getUdpateByType() {
        return udpateByType;
    }

    public void setUdpateByType(Byte udpateByType) {
        this.udpateByType = udpateByType;
    }

    public Byte getBlackType() {
        return blackType;
    }

    public void setBlackType(Byte blackType) {
        this.blackType = blackType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
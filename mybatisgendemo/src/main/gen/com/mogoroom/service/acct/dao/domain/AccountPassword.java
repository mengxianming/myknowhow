package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class AccountPassword implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer acctId;

    private Byte passType;

    private String password;

    private Date createTime;

    private Integer createBy;

    private Integer createByType;

    private Date updateTime;

    private Integer updateBy;

    private Integer updateByType;

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

    public Byte getPassType() {
        return passType;
    }

    public void setPassType(Byte passType) {
        this.passType = passType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public Integer getCreateByType() {
        return createByType;
    }

    public void setCreateByType(Integer createByType) {
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

    public Integer getUpdateByType() {
        return updateByType;
    }

    public void setUpdateByType(Integer updateByType) {
        this.updateByType = updateByType;
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
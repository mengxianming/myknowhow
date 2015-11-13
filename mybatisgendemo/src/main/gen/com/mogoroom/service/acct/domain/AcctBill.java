package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AcctBill implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer billId;

    private Integer billType;

    private BigDecimal amount;

    private BigDecimal unpayAmount;

    private Integer acctId;

    private Integer toAcctId;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Byte origBillType;

    private Integer origBillId;

    private Date deadline;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUnpayAmount() {
        return unpayAmount;
    }

    public void setUnpayAmount(BigDecimal unpayAmount) {
        this.unpayAmount = unpayAmount;
    }

    public Integer getAcctId() {
        return acctId;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }

    public Integer getToAcctId() {
        return toAcctId;
    }

    public void setToAcctId(Integer toAcctId) {
        this.toAcctId = toAcctId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getOrigBillType() {
        return origBillType;
    }

    public void setOrigBillType(Byte origBillType) {
        this.origBillType = origBillType;
    }

    public Integer getOrigBillId() {
        return origBillId;
    }

    public void setOrigBillId(Integer origBillId) {
        this.origBillId = origBillId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
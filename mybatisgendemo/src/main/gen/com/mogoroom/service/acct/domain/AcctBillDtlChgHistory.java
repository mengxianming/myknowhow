package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AcctBillDtlChgHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 账单变更履历表id
     */
    private Integer billChgHistoryId;

    /** 账单id。参考账单表。
     */
    private Integer billId;

    /** 账单详细id。参考账单详细表。
     */
    private Integer billDtlId;

    /** 账单详细变更前类型
     */
    private Integer billDtlType;

    /** 账单详细变更前金额
     */
    private BigDecimal oldAmount;

    /** 账单详细变更后金额
     */
    private BigDecimal newAmount;

    /** 创建时间
     */
    private Date createTime;

    /** 创建人id
     */
    private Integer createBy;

    /** 创建人类型。参考字段表useType。
     */
    private Byte createByType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账单变更履历表id
     */
    public Integer getBillChgHistoryId() {
        return billChgHistoryId;
    }

    /**
     * 设置账单变更履历表id
     */
    public void setBillChgHistoryId(Integer billChgHistoryId) {
        this.billChgHistoryId = billChgHistoryId;
    }

    /**
     * 获取账单id。参考账单表。
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * 设置账单id。参考账单表。
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * 获取账单详细id。参考账单详细表。
     */
    public Integer getBillDtlId() {
        return billDtlId;
    }

    /**
     * 设置账单详细id。参考账单详细表。
     */
    public void setBillDtlId(Integer billDtlId) {
        this.billDtlId = billDtlId;
    }

    /**
     * 获取账单详细变更前类型
     */
    public Integer getBillDtlType() {
        return billDtlType;
    }

    /**
     * 设置账单详细变更前类型
     */
    public void setBillDtlType(Integer billDtlType) {
        this.billDtlType = billDtlType;
    }

    /**
     * 获取账单详细变更前金额
     */
    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    /**
     * 设置账单详细变更前金额
     */
    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    /**
     * 获取账单详细变更后金额
     */
    public BigDecimal getNewAmount() {
        return newAmount;
    }

    /**
     * 设置账单详细变更后金额
     */
    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人id
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人id
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建人类型。参考字段表useType。
     */
    public Byte getCreateByType() {
        return createByType;
    }

    /**
     * 设置创建人类型。参考字段表useType。
     */
    public void setCreateByType(Byte createByType) {
        this.createByType = createByType;
    }
}
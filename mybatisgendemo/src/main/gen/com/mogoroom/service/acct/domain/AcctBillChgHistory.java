package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AcctBillChgHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 账单id。参考账单表。
     */
    private Integer billId;

    /** 业务类型。参考comm_busitype表。
     */
    private Integer busiType;

    /** 账单变更前金额
     */
    private BigDecimal oldAmount;

    /** 变更前的未付金额
     */
    private BigDecimal oldUnpayAmount;

    /** 账单变更前截止日期
     */
    private Date oldDeadline;

    /** 账单变更后金额
     */
    private BigDecimal newAmount;

    /** 变更后的未付金额
     */
    private BigDecimal newUnpayAmount;

    /** 账单变更后截止日期
     */
    private Date newDeadline;

    /** 创建时间
     */
    private Date createTime;

    /** 创建人id
     */
    private Integer createBy;

    /** 创建人类型。参考字段表useType。
     */
    private Byte createByType;

    /** 操作渠道。参考字典表。
     */
    private Byte channel;

    /** 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
     * 获取业务类型。参考comm_busitype表。
     */
    public Integer getBusiType() {
        return busiType;
    }

    /**
     * 设置业务类型。参考comm_busitype表。
     */
    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取账单变更前金额
     */
    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    /**
     * 设置账单变更前金额
     */
    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    /**
     * 获取变更前的未付金额
     */
    public BigDecimal getOldUnpayAmount() {
        return oldUnpayAmount;
    }

    /**
     * 设置变更前的未付金额
     */
    public void setOldUnpayAmount(BigDecimal oldUnpayAmount) {
        this.oldUnpayAmount = oldUnpayAmount;
    }

    /**
     * 获取账单变更前截止日期
     */
    public Date getOldDeadline() {
        return oldDeadline;
    }

    /**
     * 设置账单变更前截止日期
     */
    public void setOldDeadline(Date oldDeadline) {
        this.oldDeadline = oldDeadline;
    }

    /**
     * 获取账单变更后金额
     */
    public BigDecimal getNewAmount() {
        return newAmount;
    }

    /**
     * 设置账单变更后金额
     */
    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    /**
     * 获取变更后的未付金额
     */
    public BigDecimal getNewUnpayAmount() {
        return newUnpayAmount;
    }

    /**
     * 设置变更后的未付金额
     */
    public void setNewUnpayAmount(BigDecimal newUnpayAmount) {
        this.newUnpayAmount = newUnpayAmount;
    }

    /**
     * 获取账单变更后截止日期
     */
    public Date getNewDeadline() {
        return newDeadline;
    }

    /**
     * 设置账单变更后截止日期
     */
    public void setNewDeadline(Date newDeadline) {
        this.newDeadline = newDeadline;
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

    /**
     * 获取操作渠道。参考字典表。
     */
    public Byte getChannel() {
        return channel;
    }

    /**
     * 设置操作渠道。参考字典表。
     */
    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
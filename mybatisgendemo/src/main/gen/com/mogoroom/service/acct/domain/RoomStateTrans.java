package com.mogoroom.service.acct.domain;

import java.io.Serializable;

public class RoomStateTrans implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 房态类型（即维度)。1: 上下架状态类型、2:审核状态类型、3:占用状态类型
     */
    private Byte statusType;

    /** 迁移前的状态。
     */
    private Byte origStatus;

    /** 状态迁移的触发事件id。参考comm_busitype表的busiType字段。
     */
    private Integer busiType;

    /** 迁移后的状态。
     */
    private Byte toStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取房态类型（即维度)。1: 上下架状态类型、2:审核状态类型、3:占用状态类型
     */
    public Byte getStatusType() {
        return statusType;
    }

    /**
     * 设置房态类型（即维度)。1: 上下架状态类型、2:审核状态类型、3:占用状态类型
     */
    public void setStatusType(Byte statusType) {
        this.statusType = statusType;
    }

    /**
     * 获取迁移前的状态。
     */
    public Byte getOrigStatus() {
        return origStatus;
    }

    /**
     * 设置迁移前的状态。
     */
    public void setOrigStatus(Byte origStatus) {
        this.origStatus = origStatus;
    }

    /**
     * 获取状态迁移的触发事件id。参考comm_busitype表的busiType字段。
     */
    public Integer getBusiType() {
        return busiType;
    }

    /**
     * 设置状态迁移的触发事件id。参考comm_busitype表的busiType字段。
     */
    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取迁移后的状态。
     */
    public Byte getToStatus() {
        return toStatus;
    }

    /**
     * 设置迁移后的状态。
     */
    public void setToStatus(Byte toStatus) {
        this.toStatus = toStatus;
    }
}
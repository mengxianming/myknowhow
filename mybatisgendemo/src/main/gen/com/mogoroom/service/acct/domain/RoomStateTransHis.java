package com.mogoroom.service.acct.domain;

import java.io.Serializable;

public class RoomStateTransHis implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 房间id。参考flat_room表id字段。
     */
    private Integer roomId;

    /** 迁移前的上架下架状态。参考flat_room表的onlineStatus字段。
     */
    private Byte onlineStatus;

    /** 迁移前的审核状态。参考flat_room表的verifyStatus字段。
     */
    private Byte verifyStatus;

    /** 迁移前的房间占用状态。参考flat_room表的availableStatus字段。
     */
    private Byte availableStatus;

    /** 状态迁移的触发事件id。参考comm_busitype表的busiType字段。
     */
    private Integer busiType;

    /** 迁移后的上架下架状态。参考flat_room表的onlineStatus字段。
     */
    private Byte toOnlineStatus;

    /** 迁移后的审核状态。参考flat_room表的verifyStatus字段。
     */
    private Byte toVerifyStatus;

    /** 迁移后的房间占用状态。参考flat_room表的availableStatus字段。
     */
    private Byte toAvailableStatus;

    /** 业务记录id。参考comm_business_record表的id字段。
     */
    private Integer soDoneCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取房间id。参考flat_room表id字段。
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * 设置房间id。参考flat_room表id字段。
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * 获取迁移前的上架下架状态。参考flat_room表的onlineStatus字段。
     */
    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * 设置迁移前的上架下架状态。参考flat_room表的onlineStatus字段。
     */
    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    /**
     * 获取迁移前的审核状态。参考flat_room表的verifyStatus字段。
     */
    public Byte getVerifyStatus() {
        return verifyStatus;
    }

    /**
     * 设置迁移前的审核状态。参考flat_room表的verifyStatus字段。
     */
    public void setVerifyStatus(Byte verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    /**
     * 获取迁移前的房间占用状态。参考flat_room表的availableStatus字段。
     */
    public Byte getAvailableStatus() {
        return availableStatus;
    }

    /**
     * 设置迁移前的房间占用状态。参考flat_room表的availableStatus字段。
     */
    public void setAvailableStatus(Byte availableStatus) {
        this.availableStatus = availableStatus;
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
     * 获取迁移后的上架下架状态。参考flat_room表的onlineStatus字段。
     */
    public Byte getToOnlineStatus() {
        return toOnlineStatus;
    }

    /**
     * 设置迁移后的上架下架状态。参考flat_room表的onlineStatus字段。
     */
    public void setToOnlineStatus(Byte toOnlineStatus) {
        this.toOnlineStatus = toOnlineStatus;
    }

    /**
     * 获取迁移后的审核状态。参考flat_room表的verifyStatus字段。
     */
    public Byte getToVerifyStatus() {
        return toVerifyStatus;
    }

    /**
     * 设置迁移后的审核状态。参考flat_room表的verifyStatus字段。
     */
    public void setToVerifyStatus(Byte toVerifyStatus) {
        this.toVerifyStatus = toVerifyStatus;
    }

    /**
     * 获取迁移后的房间占用状态。参考flat_room表的availableStatus字段。
     */
    public Byte getToAvailableStatus() {
        return toAvailableStatus;
    }

    /**
     * 设置迁移后的房间占用状态。参考flat_room表的availableStatus字段。
     */
    public void setToAvailableStatus(Byte toAvailableStatus) {
        this.toAvailableStatus = toAvailableStatus;
    }

    /**
     * 获取业务记录id。参考comm_business_record表的id字段。
     */
    public Integer getSoDoneCode() {
        return soDoneCode;
    }

    /**
     * 设置业务记录id。参考comm_business_record表的id字段。
     */
    public void setSoDoneCode(Integer soDoneCode) {
        this.soDoneCode = soDoneCode;
    }
}
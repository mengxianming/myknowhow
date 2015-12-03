package com.mogoroom.service.flat.domain;

import java.io.Serializable;
import java.util.Date;

public class RoomPriceNew implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roomId;

    private Integer bizType;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Integer createBy;

    private Byte createByType;

    private Byte createChannel;

    private Date deleteTime;

    private Integer deleteBy;

    private Byte deleteByType;

    private Byte deleteChannel;

    private Byte valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Byte getCreateChannel() {
        return createChannel;
    }

    public void setCreateChannel(Byte createChannel) {
        this.createChannel = createChannel;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(Integer deleteBy) {
        this.deleteBy = deleteBy;
    }

    public Byte getDeleteByType() {
        return deleteByType;
    }

    public void setDeleteByType(Byte deleteByType) {
        this.deleteByType = deleteByType;
    }

    public Byte getDeleteChannel() {
        return deleteChannel;
    }

    public void setDeleteChannel(Byte deleteChannel) {
        this.deleteChannel = deleteChannel;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }
}
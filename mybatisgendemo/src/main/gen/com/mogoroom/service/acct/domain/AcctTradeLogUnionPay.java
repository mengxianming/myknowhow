package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.util.Date;

public class AcctTradeLogUnionPay implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 账单ID
     */
    private Integer billId;

    /** 商户代码.已被批准加入银联互联网系统的商户代码.
     */
    private String merId;

    /** 向银联发送的订单号
     */
    private String orderId;

    /** 订单描述
     */
    private String orderRemark;

    /** 支付状态。1:初始状态。2：支付成功。3:撤销支付成功。4:退款成功
     */
    private Byte payStatus;

    /** 订单推送请求时间
     */
    private Date sendOrderRequestTime;

    /** 银联受理订单号.
商户推送订单后银联系统返回该流水号，商户调用支付控件时使用
     */
    private String tn;

    /** 支付结果回调时间
     */
    private Date payResultCallbackTime;

    /** 查询流水号.
由银联返回，用于在后续类交易中唯一标识一笔交易
     */
    private String queryId;

    private String revokeReuestTime;

    private Date revokeRequestCallbackTime;

    /** 消费撤销交易的交易流水号 供查询用
     */
    private String revokeQueryId;
    

    /** 订单推送请求参数。具体组成参考银联的订单推送接口API文档。json格式存储。
     */
    private String sendOrderRequest;

    /** 支付结果回调参数。由银联系统回调、记录传入参数。json格式存储。
     */
    private String payResultCallback;

    /** 撤销支付请求参数。具体组成参考银联【撤销支付】接口API文档。json格式存储。
     */
    private String revokeRequest;

    /** 支付撤销结果回调参数。由银联系统回调、传入参数。json格式存储。
     */
    private String revokeRequestCallback;

    /**
     * 获取订单推送请求参数。具体组成参考银联的订单推送接口API文档。json格式存储。
     */
    public String getSendOrderRequest() {
        return sendOrderRequest;
    }

    /**
     * 设置订单推送请求参数。具体组成参考银联的订单推送接口API文档。json格式存储。
     */
    public void setSendOrderRequest(String sendOrderRequest) {
        this.sendOrderRequest = sendOrderRequest == null ? null : sendOrderRequest.trim();
    }

    /**
     * 获取支付结果回调参数。由银联系统回调、记录传入参数。json格式存储。
     */
    public String getPayResultCallback() {
        return payResultCallback;
    }

    /**
     * 设置支付结果回调参数。由银联系统回调、记录传入参数。json格式存储。
     */
    public void setPayResultCallback(String payResultCallback) {
        this.payResultCallback = payResultCallback == null ? null : payResultCallback.trim();
    }

    /**
     * 获取撤销支付请求参数。具体组成参考银联【撤销支付】接口API文档。json格式存储。
     */
    public String getRevokeRequest() {
        return revokeRequest;
    }

    /**
     * 设置撤销支付请求参数。具体组成参考银联【撤销支付】接口API文档。json格式存储。
     */
    public void setRevokeRequest(String revokeRequest) {
        this.revokeRequest = revokeRequest == null ? null : revokeRequest.trim();
    }

    /**
     * 获取支付撤销结果回调参数。由银联系统回调、传入参数。json格式存储。
     */
    public String getRevokeRequestCallback() {
        return revokeRequestCallback;
    }

    /**
     * 设置支付撤销结果回调参数。由银联系统回调、传入参数。json格式存储。
     */
    public void setRevokeRequestCallback(String revokeRequestCallback) {
        this.revokeRequestCallback = revokeRequestCallback == null ? null : revokeRequestCallback.trim();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账单ID
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * 设置账单ID
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * 获取商户代码.已被批准加入银联互联网系统的商户代码.
     */
    public String getMerId() {
        return merId;
    }

    /**
     * 设置商户代码.已被批准加入银联互联网系统的商户代码.
     */
    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    /**
     * 获取向银联发送的订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置向银联发送的订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取订单描述
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * 设置订单描述
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    /**
     * 获取支付状态。1:初始状态。2：支付成功。3:撤销支付成功。4:退款成功
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态。1:初始状态。2：支付成功。3:撤销支付成功。4:退款成功
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取订单推送请求时间
     */
    public Date getSendOrderRequestTime() {
        return sendOrderRequestTime;
    }

    /**
     * 设置订单推送请求时间
     */
    public void setSendOrderRequestTime(Date sendOrderRequestTime) {
        this.sendOrderRequestTime = sendOrderRequestTime;
    }

    /**
     * 获取银联受理订单号.
商户推送订单后银联系统返回该流水号，商户调用支付控件时使用
     */
    public String getTn() {
        return tn;
    }

    /**
     * 设置银联受理订单号.
商户推送订单后银联系统返回该流水号，商户调用支付控件时使用
     */
    public void setTn(String tn) {
        this.tn = tn == null ? null : tn.trim();
    }

    /**
     * 获取支付结果回调时间
     */
    public Date getPayResultCallbackTime() {
        return payResultCallbackTime;
    }

    /**
     * 设置支付结果回调时间
     */
    public void setPayResultCallbackTime(Date payResultCallbackTime) {
        this.payResultCallbackTime = payResultCallbackTime;
    }

    /**
     * 获取查询流水号.
由银联返回，用于在后续类交易中唯一标识一笔交易
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * 设置查询流水号.
由银联返回，用于在后续类交易中唯一标识一笔交易
     */
    public void setQueryId(String queryId) {
        this.queryId = queryId == null ? null : queryId.trim();
    }

    public String getRevokeReuestTime() {
        return revokeReuestTime;
    }

    public void setRevokeReuestTime(String revokeReuestTime) {
        this.revokeReuestTime = revokeReuestTime == null ? null : revokeReuestTime.trim();
    }

    public Date getRevokeRequestCallbackTime() {
        return revokeRequestCallbackTime;
    }

    public void setRevokeRequestCallbackTime(Date revokeRequestCallbackTime) {
        this.revokeRequestCallbackTime = revokeRequestCallbackTime;
    }

    /**
     * 获取消费撤销交易的交易流水号 供查询用
     */
    public String getRevokeQueryId() {
        return revokeQueryId;
    }

    /**
     * 设置消费撤销交易的交易流水号 供查询用
     */
    public void setRevokeQueryId(String revokeQueryId) {
        this.revokeQueryId = revokeQueryId == null ? null : revokeQueryId.trim();
    }
}
package com.mogoroom.service.acct.domain;

public class AcctTradeLogUnionPayWithBLOBs extends AcctTradeLogUnionPay {
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
}
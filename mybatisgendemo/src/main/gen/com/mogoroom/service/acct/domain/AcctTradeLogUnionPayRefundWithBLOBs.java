package com.mogoroom.service.acct.domain;

public class AcctTradeLogUnionPayRefundWithBLOBs extends AcctTradeLogUnionPayRefund {
    /** 退款请求参数。具体组成参考银联【退款】接口API文档。json格式存储。
     */
    private String refundRequest;

    /** 支付退款结果回调参数。由银联系统回调、传入参数。json格式存储。
     */
    private String refundRequestCallback;

    /**
     * 获取退款请求参数。具体组成参考银联【退款】接口API文档。json格式存储。
     */
    public String getRefundRequest() {
        return refundRequest;
    }

    /**
     * 设置退款请求参数。具体组成参考银联【退款】接口API文档。json格式存储。
     */
    public void setRefundRequest(String refundRequest) {
        this.refundRequest = refundRequest == null ? null : refundRequest.trim();
    }

    /**
     * 获取支付退款结果回调参数。由银联系统回调、传入参数。json格式存储。
     */
    public String getRefundRequestCallback() {
        return refundRequestCallback;
    }

    /**
     * 设置支付退款结果回调参数。由银联系统回调、传入参数。json格式存储。
     */
    public void setRefundRequestCallback(String refundRequestCallback) {
        this.refundRequestCallback = refundRequestCallback == null ? null : refundRequestCallback.trim();
    }
}
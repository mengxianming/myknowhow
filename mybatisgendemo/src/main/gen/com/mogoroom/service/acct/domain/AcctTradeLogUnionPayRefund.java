package com.mogoroom.service.acct.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AcctTradeLogUnionPayRefund implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 银联交易logId
     */
    private Integer tradeLogUnionPayId;

    /** 退款请求时间
     */
    private Date refundRequestTime;

    /** 退款回调时间
     */
    private Date refundRequestCallbackTime;

    /** 消费退款交易的交易流水号 供查询用
     */
    private String refundQueryId;

    /** 退款金额
     */
    private BigDecimal refundAmount;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取银联交易logId
     */
    public Integer getTradeLogUnionPayId() {
        return tradeLogUnionPayId;
    }

    /**
     * 设置银联交易logId
     */
    public void setTradeLogUnionPayId(Integer tradeLogUnionPayId) {
        this.tradeLogUnionPayId = tradeLogUnionPayId;
    }

    /**
     * 获取退款请求时间
     */
    public Date getRefundRequestTime() {
        return refundRequestTime;
    }

    /**
     * 设置退款请求时间
     */
    public void setRefundRequestTime(Date refundRequestTime) {
        this.refundRequestTime = refundRequestTime;
    }

    /**
     * 获取退款回调时间
     */
    public Date getRefundRequestCallbackTime() {
        return refundRequestCallbackTime;
    }

    /**
     * 设置退款回调时间
     */
    public void setRefundRequestCallbackTime(Date refundRequestCallbackTime) {
        this.refundRequestCallbackTime = refundRequestCallbackTime;
    }

    /**
     * 获取消费退款交易的交易流水号 供查询用
     */
    public String getRefundQueryId() {
        return refundQueryId;
    }

    /**
     * 设置消费退款交易的交易流水号 供查询用
     */
    public void setRefundQueryId(String refundQueryId) {
        this.refundQueryId = refundQueryId == null ? null : refundQueryId.trim();
    }

    /**
     * 获取退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
}
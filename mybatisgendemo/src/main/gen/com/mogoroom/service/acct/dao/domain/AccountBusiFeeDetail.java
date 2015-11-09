package com.mogoroom.service.acct.dao.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountBusiFeeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer doneCode;

    private Integer acctId;

    private BigDecimal amount;

    private Integer fundChannel;

    private String bankCode;

    private String bankCard;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(Integer doneCode) {
        this.doneCode = doneCode;
    }

    public Integer getAcctId() {
        return acctId;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(Integer fundChannel) {
        this.fundChannel = fundChannel;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
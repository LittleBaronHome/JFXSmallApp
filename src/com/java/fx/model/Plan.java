package com.java.fx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Plan {
    private Long id;
    @JsonProperty("account_id")
    private Long accountId;
    private String interval;
    private Date date;
    private String type;
    private String classify;
    private BigDecimal money;
    private String note;

    public Plan() {
    }

    public Plan(Long accountId, String interval, Date date, String type, String classify, BigDecimal money, String note) {
        this.accountId = accountId;
        this.interval = interval;
        this.date = date;
        this.type = type;
        this.classify = classify;
        this.money = money;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

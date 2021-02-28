package com.java.fx.model;

import java.math.BigDecimal;
import java.util.Date;

public class PlanView {
    private Date date;
    private String type;
    private String classify;
    private BigDecimal money;
    private String note;

    public PlanView() {
    }

    public PlanView(Long accountId, Date date, String type, String classify, BigDecimal money, String note) {
        this.date = date;
        this.type = type;
        this.classify = classify;
        this.money = money;
        this.note = note;
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

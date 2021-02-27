package com.java.fx.model.JsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.fx.model.Dictionary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author qiaojiyuan
 * @date 2021/2/25
 */
public class Conf {
    @JsonProperty("p")
    private String password;
    @JsonProperty("c")
    private Long createTimeMillis;
    @JsonProperty("u")
    private Long updateTimeMillis;
    @JsonProperty("a")
    private List<Dictionary> account;
    @JsonProperty("ic")
    private List<Dictionary> incomeClassify;
    @JsonProperty("pc")
    private List<Dictionary> payClassify;

    public Conf() {
    }

    public Conf(String password, Long createTimeMillis, List<Dictionary> account) {
        this.password = password;
        this.createTimeMillis = createTimeMillis;
        this.account = account;
    }

    public static Conf defaultConf(String password) {
        Conf conf = new Conf();
        conf.password = password;
        conf.createTimeMillis = System.currentTimeMillis();
        conf.account = Collections.singletonList(new Dictionary(1L, "default", "默认账户"));
        conf.incomeClassify = Arrays.asList(
                new Dictionary(1L, "salary", "工资"),
                new Dictionary(2L, "others", "其他")
                );
        conf.payClassify = Arrays.asList(
                new Dictionary(1L, "meal", "餐费"),
                new Dictionary(2L, "traffic", "交通费"),
                new Dictionary(3L, "phone", "话费"),
                new Dictionary(4L, "network", "网费"),
                new Dictionary(5L, "others", "其他")
                );

        return conf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreateTimeMillis() {
        return createTimeMillis;
    }

    public void setCreateTimeMillis(Long createTimeMillis) {
        this.createTimeMillis = createTimeMillis;
    }

    public Long getUpdateTimeMillis() {
        return updateTimeMillis;
    }

    public void setUpdateTimeMillis(Long updateTimeMillis) {
        this.updateTimeMillis = updateTimeMillis;
    }

    public List<Dictionary> getAccount() {
        return account;
    }

    public void setAccount(List<Dictionary> account) {
        this.account = account;
    }

    public List<Dictionary> getIncomeClassify() {
        return incomeClassify;
    }

    public void setIncomeClassify(List<Dictionary> incomeClassify) {
        this.incomeClassify = incomeClassify;
    }

    public List<Dictionary> getPayClassify() {
        return payClassify;
    }

    public void setPayClassify(List<Dictionary> payClassify) {
        this.payClassify = payClassify;
    }
}

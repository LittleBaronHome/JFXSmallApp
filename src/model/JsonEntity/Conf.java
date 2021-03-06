package model.JsonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import Util.DataUtil;
import model.Dictionary;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


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
    private List<Dictionary<BigDecimal>> account;
    @JsonProperty("ic")
    private List<Dictionary> incomeClassify;
    @JsonProperty("pc")
    private List<Dictionary> payClassify;

    public Conf() {
    }

    public Conf(String password, Long createTimeMillis, List<Dictionary<BigDecimal>> account) {
        this.password = password;
        this.createTimeMillis = createTimeMillis;
        this.account = account;
    }

    public static Conf defaultConf(String password) {
        Conf conf = new Conf();
        conf.password = password;
        conf.createTimeMillis = System.currentTimeMillis();
        conf.account = DataUtil.DEFAULT_ACCOUNT;
        conf.incomeClassify = DataUtil.DEFAULT_INCOME_CLASSIFY;
        conf.payClassify = DataUtil.DEFAULT_PAY_CLASSIFY;

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

    public List<Dictionary<BigDecimal>> getAccount() {
        return account;
    }

    public void setAccount(List<Dictionary<BigDecimal>> account) {
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

    public Dictionary getAccount(String text) {
        return account.stream().filter(item -> text.equals(item.getText())).collect(Collectors.toList()).get(0);
    }
}

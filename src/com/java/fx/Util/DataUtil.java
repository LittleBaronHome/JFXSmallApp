package com.java.fx.Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.fx.model.Dictionary;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiaojiyuan
 * @date 2021/2/25
 */
public class DataUtil {
    public static final List<Dictionary<BigDecimal>> DEFAULT_ACCOUNT = Arrays.asList(
            new Dictionary<>(1L, "default", "默认账户", BigDecimal.ZERO),
            new Dictionary<>(2L, "wechat", "微信", BigDecimal.ZERO),
            new Dictionary<>(3L, "payBo", "支付宝", BigDecimal.ZERO),
            new Dictionary<>(4L, "bankCardA", "银行卡A", BigDecimal.ZERO),
            new Dictionary<>(5L, "bankCardB", "银行卡B", BigDecimal.ZERO)
    );
    public static final List<Dictionary> DEFAULT_INCOME_CLASSIFY = Arrays.asList(
            new Dictionary(1L, "salary", "工资"),
            new Dictionary(2L, "profit", "收益"),
            new Dictionary(3L, "others", "其他")
    );
    public static final List<Dictionary> DEFAULT_PAY_CLASSIFY = Arrays.asList(
            new Dictionary(1L, "meal", "餐费"),
            new Dictionary(2L, "traffic", "交通费"),
            new Dictionary(3L, "phone", "话费"),
            new Dictionary(4L, "network", "网费"),
            new Dictionary(5L, "rent", "房租"),
            new Dictionary(6L, "others", "其他")
    );
    public static final List<String> INTERVAL = Arrays.asList("月度", "周度", "自定义");
    public static final String INTERVAL_MONTH = "月度";
    public static final String INTERVAL_WEEK = "周度";
    public static final String INTERVAL_CUS = "自定义";

    public static final String INCOME = "收入";
    public static final String PAY = "支出";

    public static final List<String> WEEK = Arrays.asList("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    public static final List<String> MONTH = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "最后一天");

    public static final String VIEW_CURRENT_YEAR = "本年";
    public static final String VIEW_CURRENT_MONTH = "本月";
    public static final String VIEW_CUSTOMER = "自定义";

    private static ObjectMapper mapper = new ObjectMapper();

    public static void write(File file, Object obj) {
        write(file, obj, true);
    }
    public static void write(File file, Object obj, boolean thread) {
        if (thread) {
            new Thread(new WriteThread(file, obj)).start();
        } else {
            try {
                mapper.writeValue(file, obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T read(File file, Class<T> typeReference) {
        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T read(File file, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

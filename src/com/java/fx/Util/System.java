package com.java.fx.Util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiaojiyuan
 * @date 2021/2/25
 */
public class System {
    public static String rootPath = null;
    public static String dataFileRoot = null;
    private static final String CONF = "conf.json";
    private static final String PLAN = "plan.json";
    private static final String RECORD = "record.json";
    public static final List<String> INTERVAL = Arrays.asList("年度", "季度", "月度", "周度", "自定义");


    public static String getUserRootPath() {
        return System.dataFileRoot + Local.username;
    }

    public static String getAccountRootPath() {
        return System.dataFileRoot + Local.username + "/" + Local.account;
    }

    public static String getConfPath() {
        return System.dataFileRoot + Local.username + "/" + System.CONF;
    }

    public static String getPlanPath() {
        return System.dataFileRoot + Local.username + "/" + Local.account + "/" + System.PLAN;
    }

    public static String getRecordPath() {
        return System.dataFileRoot + Local.username + "/" + Local.account + "/" + System.RECORD;
    }
}

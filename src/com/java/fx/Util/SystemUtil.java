package com.java.fx.Util;

/**
 * @author qiaojiyuan
 * @date 2021/2/25
 */
public class SystemUtil {
    public static String rootPath = null;
    public static String dataFileRoot = null;
    private static final String CONF = "conf.json";
    private static final String PLAN = "plan.json";
    private static final String RECORD = "record.json";

    public static String getUserRootPath() {
        return getUserRootPath(Local.username);
    }

    public static String getUserRootPath(String username) {
        return SystemUtil.dataFileRoot + username;
    }

    public static String getAccountRootPath() {
        return getAccountRootPath(Local.username);
    }

    public static String getAccountRootPath(String username) {
        return getAccountRootPath(Local.username, Local.account.getValue());
    }

    public static String getAccountRootPath(String username, String account) {
        return SystemUtil.dataFileRoot + username + "/" + account;
    }

    public static String getConfPath() {
        return getConfPath(Local.username);
    }

    public static String getConfPath(String username) {
        return SystemUtil.dataFileRoot + username + "/" + SystemUtil.CONF;
    }

    public static String getPlanPath() {
        return getPlanPath(Local.username, Local.account.getValue());
    }

    public static String getRecordPath() {
        return getRecordPath(Local.username, Local.account.getValue());
    }

    public static String getPlanPath(String username) {
        return getPlanPath(username, Local.account.getValue());
    }

    public static String getRecordPath(String username) {
        return getRecordPath(username, Local.account.getValue());
    }

    public static String getPlanPath(String username, String account) {
        return SystemUtil.dataFileRoot + username + "/" + account + "/" + SystemUtil.PLAN;
    }

    public static String getRecordPath(String username, String account) {
        return SystemUtil.dataFileRoot + username + "/" + account + "/" + SystemUtil.RECORD;
    }
}

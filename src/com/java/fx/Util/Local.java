package com.java.fx.Util;

import com.java.fx.model.JsonEntity.Conf;
import com.java.fx.model.Plan;
import com.java.fx.model.Record;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Local {
    public static String username = null;
    public static String account = "default";
    public static Long accountId = 1L;
    public static Conf system = new Conf();
    public static List<Plan> plan = new ArrayList<>();
    public static Long planIdMax = 0L;
    public static List<Record> record = new ArrayList<>();
    public static Long recordIdMax = 0L;

    public static void setPlan(List<Plan> planList) {
        plan = planList;
        if (planList.size() > 0) {
            planIdMax = planList.stream().map(Plan::getId).max((a, b) -> Math.toIntExact(a - b)).get();
        }
    }

    public static void setRecord(List<Record> recordList) {
        record = recordList;
        if (recordList.size() > 0) {
            recordIdMax = recordList.stream().map(Record::getId).max((a, b) -> Math.toIntExact(a - b)).get();
        }
    }

    public static void addPlan(Plan planList) {
        plan.add(planList);
        planIdMax++;
    }

    public static void addRecord(Record recordList) {
        record.add(recordList);
        recordIdMax++;
    }

    public static void removePlan(Plan planList) {
        plan.remove(planList);
        planIdMax--;
    }

    public static void removeRecord(Record recordList) {
        record.remove(recordList);
        recordIdMax--;
    }

    public static void persistence() {
        DataUtil.write(new File(System.getConfPath()), system);
        DataUtil.write(new File(System.getPlanPath()), plan);
        DataUtil.write(new File(System.getRecordPath()), record);
    }

}

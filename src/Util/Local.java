package Util;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Dictionary;
import model.JsonEntity.Conf;
import model.Plan;
import model.Record;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Local {
    public static String username = null;
    public static Dictionary<BigDecimal> account = null;
    public static Long accountId = null;
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
        DataUtil.write(new File(SystemUtil.getConfPath()), system);
        DataUtil.write(new File(SystemUtil.getPlanPath()), plan);
        DataUtil.write(new File(SystemUtil.getRecordPath()), record);
    }

    public static void init(Dictionary account) {
        Local.account = account;
        Local.accountId = account.getId();
        File planFile = new File(SystemUtil.getPlanPath());
        File recordFile = new File(SystemUtil.getRecordPath());
        if (planFile.exists() && recordFile.exists()) {
            Local.setPlan(DataUtil.read(new File(planFile.getPath()), new TypeReference<ArrayList<Plan>>(){}));
            Local.setRecord(DataUtil.read(new File(recordFile.getPath()), new TypeReference<ArrayList<Record>>(){}));
        }
    }

}

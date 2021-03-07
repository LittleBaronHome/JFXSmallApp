package Updated;

import Util.DataUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import model.Record;

import java.io.File;
import java.util.ArrayList;

/**
 * Record中添加是否纳入总计字段
 */
public class Updated_1_0_1 {
    public void update() {
        File system = new File(Updated_1_0_1.class.getResource("../Resources/system").getPath());
        if (system.exists()) {
            File[] users = system.listFiles();
            for (File f : users) {
                if (f.isDirectory()) {
                    for (File u : f.listFiles()) {
                        File record = new File(system.getPath() + "/" + f.getName() + "/" + u.getName() + "/record.json");
                        if (record.exists()) {
                            ArrayList<Record> recordList = DataUtil.read(record, new TypeReference<ArrayList<Record>>() {
                            });
                            for (Record r : recordList) {
                                r.setReckonInTotal(true);
                            }
                            DataUtil.write(record, recordList, false);
                        }
                    }
                }
            }
        }
    }
}

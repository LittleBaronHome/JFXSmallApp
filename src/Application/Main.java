package Application;

import Panel.LoginPanel;
import Panel.MainPanel;
import Panel.Prompt.UpdatingPanel;
import Util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.deploy.util.StringUtils;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * @author qiaojiyuan
 * @date 2021/1/14
 */
public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
            Main.primaryStage = primaryStage;
            Main.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/60044.gif")));
            File updated = new File(SystemUtil.rootPath + "../Updated");
            if (updated.exists()) {
                File confFile = new File(SystemUtil.rootPath + "../Updated/conf.json");
                HashMap confMap = DataUtil.read(confFile, new TypeReference<HashMap>(){});
                String version = confMap.get("version").toString();
                List<String> updatedFiles = new ArrayList<>();
                for (File f : updated.listFiles()) {
                    if (!f.getName().endsWith(".class") || f.getName().contains("$")) {
                        continue;
                    }
                    String[] fName = f.getName().replace(".class", "").split("_");
                    fName[0] = "";
                    if (StringUtil.join(fName, "_", 1).compareTo(version) > 0) {
                        updatedFiles.add(f.getName().replace(".class", ""));
                    }
                }
                if (updatedFiles.size() > 0) {
                    PanelUtil.show(new UpdatingPanel(), MainPanel.UPDATED_PANEL);
                    if (updatedFiles.size() > 1) {
                        updatedFiles.sort(String::compareTo);
                    }
                    String max = "";
                    for (String c : updatedFiles) {
                        Class clazz = Class.forName("Updated." + c);
                        Method method = clazz.getMethod("update");
                        method.invoke(clazz.newInstance());
                        max = c;
                    }
                    confMap.put("version", StringUtil.join(max.split("_"), "_", 1));
                    DataUtil.write(confFile, confMap, false);
                    Thread.sleep(3000);
                }
            }
            PanelUtil.show(new LoginPanel(), MainPanel.LOGIN_PANEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        SystemUtil.rootPath = getClass().getResource("").getPath();
        SystemUtil.dataFileRoot = SystemUtil.rootPath + "../Resources/system/";
    }

    @Override
    public void stop() {
        if (Local.username != null && Local.account != null) {
            Local.persistence();
        }
    }
}

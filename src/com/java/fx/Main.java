package com.java.fx;

import com.java.fx.Panel.LoginPanel;
import com.java.fx.Panel.MainPanel;
import com.java.fx.Util.Local;
import com.java.fx.Util.SystemUtil;
import com.java.fx.Util.PanelUtil;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
//        StyleManager.getInstance().addUserAgentStylesheet(SystemUtil.rootPath + "Resources/main.css");
        Main.primaryStage = primaryStage;
        Main.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/java/fx/Resources/60044.gif")));
        PanelUtil.show(new LoginPanel(), MainPanel.LOGIN_PANEL);
//        PanelUtil.create(new IndexPanel(), MainPanel.INDEX_PANEL);
    }

    @Override
    public void init() {
        SystemUtil.rootPath = getClass().getResource("").getPath();
        SystemUtil.dataFileRoot = SystemUtil.rootPath + "Resources/system/";
    }

    @Override
    public void stop() {
        if (Local.username != null && Local.account != null) {
            Local.persistence();
        }
    }
}

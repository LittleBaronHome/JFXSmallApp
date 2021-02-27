package com.java.fx;

import com.java.fx.Panel.LoginPanel;
import com.java.fx.Panel.MainPanel;
import com.java.fx.Util.Local;
import com.java.fx.Util.System;
import com.java.fx.Util.PanelUtil;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


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
//        StyleManager.getInstance().addUserAgentStylesheet(System.rootPath + "Resources/main.css");
        Main.primaryStage = primaryStage;
        Main.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/java/fx/Resources/60044.gif")));
        PanelUtil.show(new LoginPanel(), MainPanel.LOGIN_PANEL);
//        PanelUtil.create(new IndexPanel(), MainPanel.INDEX_PANEL);
    }

    @Override
    public void init() {
        System.rootPath = getClass().getResource("").getPath();
        System.dataFileRoot = System.rootPath + "Resources/system/";
    }

    @Override
    public void stop() {
        Local.persistence();
    }
}

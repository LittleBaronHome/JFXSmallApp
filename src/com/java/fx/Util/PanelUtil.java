package com.java.fx.Util;

import com.java.fx.Main;
import com.java.fx.Panel.MainPanel;
import com.java.fx.model.StageInfo;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class PanelUtil {
    private static Scene currentScene = null;
    private static HashMap<String, Scene> sceneMap = new HashMap<>();
    private static HashMap<String, StageInfo> sceneStageMap = new HashMap<>();
    private static HashMap<String, Stage> promptMap = new HashMap<>();
    private static int promptHeight = 0;
    private static int promptIndex = 0;

    public static <T extends MainPanel> String create(T clazz) {
        return create(clazz, String.valueOf(countScene() + 1));
    }

    public static <T extends MainPanel> String create(T clazz, String key) {
        Scene scene = clazz.init();
        clazz.action();
        addScene(key, scene, StageInfo.create(clazz));
        return key;
    }

    public static <T extends MainPanel> String show(T clazz) {
        return show(clazz, String.valueOf(countScene() + 1));
    }

    public static <T extends MainPanel> String show(T clazz, String key) {
        create(clazz, key);
        show(key);
        return key;
    }

    public static <T extends MainPanel> String show(String key, T clazz) {
        if (hasScene(key)) {
            show(key);
            return key;
        }
        return show(clazz, key);
    }

    public static void show(Scene scene) {
        Main.primaryStage.setScene(scene);
        if (!Main.primaryStage.isShowing()) {
            Main.primaryStage.show();
        }
        currentScene = scene;
    }

    public static void show(String key) {
        Main.primaryStage.setScene(new Scene(new AnchorPane()));
        Scene scene = getScene(key);
        setSceneStage(key);
        Main.primaryStage.setScene(scene);
        if (!Main.primaryStage.isShowing()) {
            Main.primaryStage.show();
        }
        currentScene = scene;
    }

    public static void addScene(String key, Scene scene, StageInfo stageInfo) {
        sceneMap.put(key, scene);
        sceneStageMap.put(key, stageInfo);
    }

    public static Scene removeScene(String key) {
        sceneStageMap.remove(key);
        return sceneMap.remove(key);
    }

    public static Scene getScene(String key) {
        return sceneMap.get(key);
    }

    private static void setSceneStage(String key) {
        sceneStageMap.get(key).transfer(Main.primaryStage);
    }

    public static StageInfo getSceneStage(String key) {
        return sceneStageMap.get(key);
    }

    public static boolean hasScene(String key) {
        return sceneMap.containsKey(key);
    }

    public static int countScene() {
        return sceneMap.size();
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void setWindowsInCenter() {
        Rectangle2D rtl = Screen.getPrimary().getVisualBounds();
        Main.primaryStage.setX((rtl.getMaxX() - Main.primaryStage.getWidth()) / 2);
        Main.primaryStage.setY((rtl.getMaxY() - Main.primaryStage.getHeight()) / 2);
    }

    public static void setModalInCenter(Stage parent, Stage modal) {
        modal.setX(parent.getX() + ((parent.getWidth() - modal.getWidth()) / 2));
        modal.setY(parent.getY() + ((parent.getHeight() - modal.getHeight()) / 2));
    }

    public static void setModalInTop(Stage parent, Stage modal, int top) {
        modal.setX(parent.getX() + ((parent.getWidth() - modal.getWidth()) / 2));
        modal.setY(parent.getY() + top + promptHeight);
    }

    public static String addPrompt(Stage stage, int height) {
        promptHeight += height;
        String key = String.valueOf(promptIndex++);
        promptMap.put(key, stage);
        return key;
    }

    public static void dcrPrompt(String key, int height) {
        promptHeight -= height;
        promptMap.remove(key);
        promptMap.values().forEach(item -> item.setY(item.getY() - height));
        if (promptMap.size() == 0) {
            promptIndex = 0;
        }
    }

    public static void refreshSize() {

    }
}

package model;

import Panel.MainPanel;
import Util.PanelUtil;
import javafx.stage.Stage;

/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class StageInfo {
    private double width;
    private double height;
    private boolean resizable;
    private String tittle;
    private double x;
    private double y;

    public StageInfo() {
    }

    public StageInfo(Stage stage) {
        this.width = stage.getWidth();
        this.height = stage.getHeight();
        this.resizable = stage.isResizable();
        this.tittle = stage.getTitle();
        this.x = stage.getX();
        this.y = stage.getY();
    }

    public static <T extends MainPanel> StageInfo  create(T clazz) {
        StageInfo stageInfo = new StageInfo();
        stageInfo.width = clazz.getWidth();
        stageInfo.height = clazz.getHeight();
        stageInfo.resizable = clazz.isResizable();
        stageInfo.tittle = clazz.getTitle();
        return stageInfo;
    }

    public void transfer(Stage stage) {
        transfer(stage, true);
    }

    public void transfer(Stage stage, boolean isCenter) {
        stage.setWidth(this.width);
        stage.setHeight(this.height);
        if (isCenter) {
            PanelUtil.setWindowsInCenter();
        }
        stage.setResizable(this.resizable);
        stage.setTitle(this.tittle);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

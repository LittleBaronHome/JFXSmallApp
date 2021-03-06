package Panel;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class MainPanel {
    public static final String LOGIN_PANEL = "login";
    public static final String SIGN_PANEL = "sign";
    public static final String INDEX_PANEL = "index";

    private double width;
    private double height;
    private String title;
    private boolean resizable;

    public Scene init(){
        return new Scene(new AnchorPane());
    }

    public void action(){}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }
}

package Application;

import Panel.LoginPanel;
import Panel.MainPanel;
import Util.Local;
import Util.PanelUtil;
import Util.SystemUtil;
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
        try {
            Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
            Main.primaryStage = primaryStage;
            Main.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/60044.gif")));
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

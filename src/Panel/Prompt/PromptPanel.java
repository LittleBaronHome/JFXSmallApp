package Panel.Prompt;

import Util.PanelUtil;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author qiaojiyuan
 * @date 2021/1/29
 */
public class PromptPanel {
    Stage stage;
    private Stage parent;
    private static int marginTop = 40;

    PromptPanel(Stage parent) {
        this.parent = parent;
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initOwner(parent);
        stage.initModality(Modality.NONE);
        stage.setResizable(false);
    }

    public void show() {
        PanelUtil.setModalInTop(parent, stage, marginTop);
        stage.show();
        String key = PanelUtil.addPrompt(stage, marginTop);
        Timer timer =  new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(() -> stage.close());
                PanelUtil.dcrPrompt(key, marginTop);
                timer.cancel();
            }
        }, 5000);
        parent.xProperty().addListener((obs, oldVal, newVal) -> PanelUtil.setModalInTop(parent, stage, marginTop));
        parent.yProperty().addListener((obs, oldVal, newVal) -> PanelUtil.setModalInTop(parent, stage, marginTop));
    }

}

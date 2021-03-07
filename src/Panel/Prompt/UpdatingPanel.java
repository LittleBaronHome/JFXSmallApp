package Panel.Prompt;

import Panel.MainPanel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * @author qiaojiyuan
 * @date 2021/1/27
 */
public class UpdatingPanel extends MainPanel {
    private BorderPane ac;
    private Scene scene;
    private Label updatedLabel;

    @Override
    public Scene init(){
        ac = new BorderPane();
        scene = new Scene(ac);

        // 标签
        updatedLabel = new Label("更新中...");
        updatedLabel.setStyle("-fx-font-size: 20");

        ac.setCenter(updatedLabel);
        setWidth(350);
        setHeight(230);
        setResizable(false);
        setTitle("更新");

        return scene;
    }

    @Override
    public void action() {
        super.action();
    }
}

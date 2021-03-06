package Panel.Prompt;

import Util.StringUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author qiaojiyuan
 * @date 2021/2/2
 */
public class SystemPrompt extends PromptPanel {
    SystemPrompt(Stage parent) {
        super(parent);
    }
    public void init(String text) {
        init(text, "#FFFFFF");
    }

    public void init(String text, String backgroundColor) {
        init(text, backgroundColor, "#000000");
    }

    public void init(String text, String backgroundColor, String fontColor) {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);

        Label label = new Label(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(15));
        label.setTextFill(Paint.valueOf(fontColor));
        int countSize = StringUtil.countSize(label.getText());
        stage.setWidth(countSize > 150 ? countSize : 150);
        stage.setHeight(30);

        flowPane.getChildren().add(label);

        Scene scene = new Scene(flowPane);
        scene.setFill(null);
        flowPane.setStyle(
                "-fx-background-color: " + backgroundColor + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #707070;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;" +
                        "-fx-opacity: 0.7"
        );
        stage.setScene(scene);
    }

    @Override
    public void show() {
        super.show();
    }
}

package Panel.Modal;

import Util.PanelUtil;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.function.Predicate;

/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class ModalPanel {
    public final static String CLOSE_F_CLOSE = "关闭";
    public final static String CANCEL_F_CLOSE = "取消";
    public final static String SUBMIT_F_SUBMIT = "提交";
    public final static String SURE_F_SUBMIT = "确定";
    protected Stage stage;
    protected Scene scene;
    protected BorderPane bp;
    protected Stage parent;
    protected Label title;
    private AnchorPane ap;
    private final int bottomButtonPos = 5;
    private final int bottomButtonWidth = 45 + bottomButtonPos;
    private int bottomButtonCount = 0;

    public ModalPanel (Stage parent) {
        this.parent = parent;
        stage = new Stage();
        bp = new BorderPane();
        scene = new Scene(bp);

        bp.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #707070;" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 1;"
        );

        title = new Label("Modal");
        title.setStyle(
                "-fx-background-color: #F8F8F8;" +
                "-fx-background-radius: 10; -fx-padding: 0 5"
        );
        title.setPrefHeight(30);
        title.setPrefWidth(parent.getWidth());
        bp.setTop(title);

        ap = new AnchorPane();
        ap.setStyle(
                "-fx-background-color: #F8F8F8;" +
                "-fx-background-radius: 10; -fx-padding: 0 5"
        );
        ap.setPrefWidth(parent.getWidth());
        ap.setPrefHeight(40);
        bp.setBottom(ap);

        scene.setFill(null);
        stage.setScene(scene);
        stage.initOwner(parent);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
    }

    public void show() {
        PanelUtil.setModalInCenter(parent, stage);
        stage.show();
    }

    protected void setTitle(String t) {
        title.setText(t);
    }

    protected void recountStageSize(double width, double height) {
        stage.setHeight(height + 70);
        stage.setWidth(width);
    }

    protected void setBottomButton() {
        setBottomButton(CLOSE_F_CLOSE);
    }

    protected void setBottomButton(String text) {
        setBottomButton(text, e -> true);
    }

//    protected void setBottomButton(Consumer<ActionEvent> consumer) {
//        setBottomButton(CLOSE_F_CLOSE, consumer);
//    }
//
//    protected void setBottomButton(String text, Consumer<ActionEvent> consumer) {
//        Button buttonClose = new Button(text);
//        AnchorPane.setBottomAnchor(buttonClose, 5.0);
//        AnchorPane.setRightAnchor(buttonClose, (double) (bottomButtonPos + (bottomButtonWidth * bottomButtonCount++)));
//        buttonClose.setOnAction(e -> {
//            bottomButtonCount = 0;
//           if (consumer != null) {
//               consumer.accept(e);
//           }
//            stage.close();
//        });
//        ap.getChildren().add(buttonClose);
//    }

    protected void setBottomButton(Predicate<ActionEvent> predicate) {
        setBottomButton(CLOSE_F_CLOSE, predicate);
    }

    protected void setBottomButton(String text, Predicate<ActionEvent> predicate) {
        Button buttonClose = new Button(text);
        AnchorPane.setBottomAnchor(buttonClose, 5.0);
        AnchorPane.setRightAnchor(buttonClose, (double) (bottomButtonPos + (bottomButtonWidth * bottomButtonCount++)));
        buttonClose.setOnAction(e -> {
            boolean isClose = true;
            bottomButtonCount = 0;
           if (predicate != null) {
               isClose = predicate.test(e);
           }
           if (isClose) {
               stage.close();
           }
        });
        ap.getChildren().add(buttonClose);
    }
}

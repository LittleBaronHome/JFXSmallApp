package Panel.Modal;

import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class ErrorModalPanel extends SystemModal{

    public ErrorModalPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        show("出错了！");
    }

    public void show(String text) {
        FlowPane flowPane = init(text);
        label.setTextFill(Paint.valueOf("#FF3333"));
        bp.setCenter(flowPane);
        setTitle("错误信息");
        setBottomButton(CLOSE_F_CLOSE);
       super.show();
    }
}

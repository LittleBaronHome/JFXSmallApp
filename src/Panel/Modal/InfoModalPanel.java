package Panel.Modal;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class InfoModalPanel extends SystemModal{

    public InfoModalPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        show("");
    }

    public void show(String text) {
        FlowPane flowPane = init(text);
        bp.setCenter(flowPane);
        setTitle("提示信息");
        setBottomButton(ModalPanel.CLOSE_F_CLOSE);
       super.show();
    }
}

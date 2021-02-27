package com.java.fx.Panel.Modal;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class ConfirmModalPanel extends SystemModal{

    public ConfirmModalPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        show("确认提交吗？");
    }

    public void show(String text) {
        FlowPane flowPane = init(text);
        bp.setCenter(flowPane);
        setTitle("确认信息");
        setBottomButton(CANCEL_F_CLOSE);
        setBottomButton(SURE_F_SUBMIT);
       super.show();
    }
}

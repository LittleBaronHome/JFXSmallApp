package com.java.fx.Panel.Prompt;

import javafx.stage.Stage;

/**
 * @author qiaojiyuan
 * @date 2021/1/29
 */
public class InfoPromptPanel extends SystemPrompt {
    public InfoPromptPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        show("提示信息。");
    }

    public void show(String text) {
        init(text);
        super.show();
    }
}

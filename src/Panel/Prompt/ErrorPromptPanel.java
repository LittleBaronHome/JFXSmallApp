package Panel.Prompt;

import javafx.stage.Stage;

/**
 * @author qiaojiyuan
 * @date 2021/1/29
 */
public class ErrorPromptPanel extends SystemPrompt {
    public ErrorPromptPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        show("出错了！");
    }

    public void show(String text) {
        init(text, "#CC3333", "#FFFFFF");
        super.show();
    }
}

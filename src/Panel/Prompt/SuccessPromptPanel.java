package Panel.Prompt;

import javafx.stage.Stage;

/**
 * @author qiaojiyuan
 * @date 2021/1/29
 */
public class SuccessPromptPanel extends SystemPrompt {
    public SuccessPromptPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        show("成功信息。");
    }

    public void show(String text) {
        init(text, "#00FFFF");
        super.show();
    }
}

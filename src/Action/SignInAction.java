package Action;

import Panel.MainPanel;
import Util.DataUtil;
import Util.PanelUtil;
import Util.StringUtil;
import Util.SystemUtil;
import model.Dictionary;
import model.JsonEntity.Conf;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

/**
 * @author qiaojiyuan
 * @date 2021/1/27
 */
public class SignInAction {
    private Pane ac;
    private boolean isLoginError = false;

    public SignInAction(Pane ac) {
        this.ac = ac;
    }

    public void reLogin(TextField testFiledUsername, TextField textFiledPassword, TextField textFiledPasswordSure, Label labelError) {
        testFiledUsername.clear();
        textFiledPassword.clear();
        textFiledPasswordSure.clear();
        if (isLoginError) {
            isLoginError = false;
            ac.getChildren().remove(labelError);
        }
        PanelUtil.show(MainPanel.LOGIN_PANEL);
    }

    public void signIn(TextField testFiledUsername, TextField textFiledPassword, TextField textFiledPasswordSure, Label labelError) {
        String username = testFiledUsername.getText();
        String password = textFiledPassword.getText();
        if (!password.equals(textFiledPasswordSure.getText())) {
            labelError.setText("两次输入密码不一致！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
            return;
        }

        File userFord = new File(SystemUtil.getUserRootPath(username));
        if (userFord.exists()) {
            labelError.setText("用户名已存在！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
            return;
        }

        if (userFord.mkdir()) {
            File conf = new File(SystemUtil.getConfPath(username));
            DataUtil.write(conf, Conf.defaultConf(StringUtil.encrypt(password)), false);
            for (Dictionary acc : DataUtil.DEFAULT_ACCOUNT) {
                File accountParent = new File(SystemUtil.getAccountRootPath(username, acc.getValue()));
                if (accountParent.mkdir()) {
                    File plan = new File(SystemUtil.getPlanPath(username, acc.getValue()));
                    DataUtil.write(plan, new ArrayList<>(), false);
                    File record = new File(SystemUtil.getRecordPath(username, acc.getValue()));
                    DataUtil.write(record, new ArrayList<>(), false);
                }
            }
            labelError.setText("注册成功！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
        } else {
            labelError.setText("文件夹创建失败！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
        }

    }
}

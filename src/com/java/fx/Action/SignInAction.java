package com.java.fx.Action;

import com.java.fx.Panel.MainPanel;
import com.java.fx.Util.*;
import com.java.fx.Util.System;
import com.java.fx.model.Dictionary;
import com.java.fx.model.JsonEntity.Conf;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

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
        Local.username = testFiledUsername.getText();
        String password = textFiledPassword.getText();
        if (!password.equals(textFiledPasswordSure.getText())) {
            labelError.setText("两次输入密码不一致！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
            return;
        }

        File userFord = new File(System.getUserRootPath());
        if (userFord.exists()) {
            labelError.setText("用户名已存在！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
            return;
        }

        File accountParent = new File(System.getAccountRootPath());
        if (userFord.mkdir()) {
            File conf = new File(System.getConfPath());
            DataUtil.write(conf, Conf.defaultConf(StringUtil.encrypt(password)), false);
            if (accountParent.mkdir()) {
                File plan = new File(System.getPlanPath());
                DataUtil.write(plan, new ArrayList<>(), false);
                File record = new File(System.getRecordPath());
                DataUtil.write(record, new ArrayList<>(), false);
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

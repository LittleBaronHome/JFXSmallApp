package com.java.fx.Action;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.fx.Panel.IndexPanel;
import com.java.fx.Panel.MainPanel;
import com.java.fx.Panel.SignInPanel;
import com.java.fx.Util.*;
import com.java.fx.Util.SystemUtil;
import com.java.fx.model.JsonEntity.Conf;
import com.java.fx.model.Plan;
import com.java.fx.model.Record;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

/**
 * @author qiaojiyuan
 * @date 2021/1/27
 */
public class LoginAction {
    private Pane ac;
    private boolean isLoginError = false;

    public LoginAction(Pane ac) {
        this.ac = ac;
    }

    public void login(TextField testFiledUsername, TextField textFiledPassword, Label labelError) {
        String username = testFiledUsername.getText();
        String password = textFiledPassword.getText();
        File confFile = new File(SystemUtil.getConfPath(username));
        if (!confFile.exists()) {
            labelError.setText("用户名不存在，请联系管理员！");
            if (!isLoginError) {
                ac.getChildren().add(labelError);
                isLoginError = true;
            }
        } else {
            Conf system = DataUtil.read(confFile, Conf.class);
            // Sys123456 Administrator@Password8513.2
            String p = system.getPassword() != null ? system.getPassword() : "9ba27f692d26bb14ca7101c1abfbb673";
            if (p.equals(StringUtil.encrypt(password))) {
                system.setUpdateTimeMillis(java.lang.System.currentTimeMillis());
                Local.system = system;
                Local.username = username;
                Local.account = system.getAccount().get(0);
                Local.accountId = Local.account.getId();
                File planFile = new File(SystemUtil.getPlanPath());
                File recordFile = new File(SystemUtil.getRecordPath());
                if (!planFile.exists() || !recordFile.exists()) {
                    labelError.setText("用户名数据文件不存在！" );
                    if (!isLoginError) {
                        ac.getChildren().add(labelError);
                        isLoginError = true;
                    }
                } else {
                    Local.setPlan(DataUtil.read(new File(planFile.getPath()), new TypeReference<ArrayList<Plan>>(){}));
                    Local.setRecord(DataUtil.read(new File(recordFile.getPath()), new TypeReference<ArrayList<Record>>(){}));
                    if (isLoginError) {
                        isLoginError = false;
                        ac.getChildren().remove(labelError);
                    }
                    PanelUtil.show(new IndexPanel(), MainPanel.INDEX_PANEL);
                }
            } else{
                if (!ac.getChildren().contains(labelError)) {
                    labelError.setText("用户名或密码错误！");
                    if (!isLoginError) {
                        ac.getChildren().add(labelError);
                        isLoginError = true;
                    }
                }
            }
        }
    }

    public void reset(TextField testFiledUsername, TextField textFiledPassword, Label labelError) {
        testFiledUsername.clear();
        textFiledPassword.clear();
        if (isLoginError) {
            isLoginError = false;
            ac.getChildren().remove(labelError);
        }
    }

    public void signIn() {
        PanelUtil.show(MainPanel.SIGN_PANEL, new SignInPanel());
    }
}

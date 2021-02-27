package com.java.fx.Panel;

import com.java.fx.Action.LoginAction;
import com.java.fx.Main;
import com.java.fx.Util.PanelUtil;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 * @author qiaojiyuan
 * @date 2021/1/27
 */
public class LoginPanel extends MainPanel {
    private AnchorPane ac;
    private Scene scene;
    private Label labelUsername;
    private Label labelPassword;
    private Label labelError;
    private Label labelSignIn;
    private TextField testFiledUsername;
    private TextField textFiledPassword;
    private Button buttonLogin;
    private Button buttonReset;

    @Override
    public Scene init(){
        ac = new AnchorPane();
        scene = new Scene(ac);

        // 标签
        labelUsername = new Label("用户名:");
        AnchorPane.setLeftAnchor(labelUsername, 35.0);
        AnchorPane.setTopAnchor(labelUsername, 32.0);
        labelPassword = new Label("密\u3000码:");
        AnchorPane.setLeftAnchor(labelPassword, 35.0);
        AnchorPane.setTopAnchor(labelPassword, 82.0);
        labelError = new Label();
        labelError.setTextFill(Paint.valueOf("FF0000"));
        AnchorPane.setLeftAnchor(labelError, 85.0);
        AnchorPane.setTopAnchor(labelError, 112.0);
        labelSignIn = new Label("注册用户");
        labelSignIn.setTextFill(Paint.valueOf("0066FF"));
        AnchorPane.setLeftAnchor(labelSignIn, 265.0);
        AnchorPane.setTopAnchor(labelSignIn, 35.0);
        labelSignIn.setCursor(Cursor.HAND);

        // 输入框
        testFiledUsername = new TextField();
        AnchorPane.setLeftAnchor(testFiledUsername, 95.0);
        AnchorPane.setTopAnchor(testFiledUsername, 30.0);
        testFiledUsername.setTooltip(new Tooltip("用户名"));
        testFiledUsername.setPromptText("用户名");

        // 密码框
        textFiledPassword = new PasswordField();
        AnchorPane.setLeftAnchor(textFiledPassword, 95.0);
        AnchorPane.setTopAnchor(textFiledPassword, 80.0);
        textFiledPassword.setTooltip(new Tooltip("密码"));
        textFiledPassword.setPromptText("密码");

        // 按钮
        buttonLogin = new Button("登  陆");
        AnchorPane.setLeftAnchor(buttonLogin, 85.0);
        AnchorPane.setTopAnchor(buttonLogin, 130.0);
        buttonReset = new Button("重  置");
        AnchorPane.setLeftAnchor(buttonReset, 190.0);
        AnchorPane.setTopAnchor(buttonReset, 130.0);

        ac.getChildren().addAll(testFiledUsername, textFiledPassword, labelSignIn,
                labelUsername, labelPassword,
                buttonLogin, buttonReset);

        setWidth(350);
        setHeight(230);
        setResizable(false);
        setTitle("登陆");

        return scene;
    }

    @Override
    public void action() {
        LoginAction loginAction = new LoginAction(ac);
        // 按钮事件
        buttonLogin.setOnAction(e -> loginAction.login(testFiledUsername, textFiledPassword, labelError));
        buttonReset.setOnAction(e -> loginAction.reset(testFiledUsername, textFiledPassword, labelError));
        labelSignIn.setOnMouseClicked(e -> loginAction.signIn());

        // 快捷键
        KeyCombination enter = new KeyCodeCombination(KeyCode.ENTER);
        scene.getAccelerators().put(enter, () -> loginAction.login(testFiledUsername, textFiledPassword, labelError));
    }
}

package Panel;

import Action.SignInAction;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 * @author qiaojiyuan
 * @date 2021/1/27
 */
public class SignInPanel extends MainPanel {
    private AnchorPane ac;
    private Scene scene;
    private Label labelUsername;
    private Label labelPassword;
    private Label labelPasswordSure;
    private Label labelError;
    private TextField testFiledUsername;
    private TextField textFiledPassword;
    private TextField textFiledPasswordSure;
    private Button buttonSignIn;
    private Button buttonReLogin;

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
        labelPasswordSure = new Label("密码确认:");
        AnchorPane.setLeftAnchor(labelPasswordSure, 35.0);
        AnchorPane.setTopAnchor(labelPasswordSure, 132.0);
        labelError = new Label();
        labelError.setTextFill(Paint.valueOf("FF0000"));
        AnchorPane.setLeftAnchor(labelError, 85.0);
        AnchorPane.setTopAnchor(labelError, 162.0);

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

        // 密码确认框
        textFiledPasswordSure = new PasswordField();
        AnchorPane.setLeftAnchor(textFiledPasswordSure, 95.0);
        AnchorPane.setTopAnchor(textFiledPasswordSure, 130.0);
        textFiledPasswordSure.setTooltip(new Tooltip("密码确认"));
        textFiledPasswordSure.setPromptText("密码确认");

        // 按钮
        buttonSignIn = new Button("注\u3000\u3000册");
        AnchorPane.setLeftAnchor(buttonSignIn, 85.0);
        AnchorPane.setTopAnchor(buttonSignIn, 180.0);
        buttonReLogin = new Button("返回登录");
        AnchorPane.setLeftAnchor(buttonReLogin, 190.0);
        AnchorPane.setTopAnchor(buttonReLogin, 180.0);

        ac.getChildren().addAll(testFiledUsername, textFiledPassword, labelPasswordSure,
                labelUsername, labelPassword, textFiledPasswordSure,
                buttonSignIn, buttonReLogin);

        setWidth(300);
        setHeight(280);
        setResizable(false);
        setTitle("注册");

        return scene;
    }

    @Override
    public void action() {
        try {
            SignInAction signInAction = new SignInAction(ac);
            // 按钮事件
            buttonReLogin.setOnAction(e -> signInAction.reLogin(testFiledUsername, textFiledPassword, textFiledPasswordSure, labelError));
            buttonSignIn.setOnAction(e -> signInAction.signIn(testFiledUsername, textFiledPassword, textFiledPasswordSure, labelError));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

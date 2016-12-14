package group2.grade15.njuse.presentation.loginui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.bl.loginbl.CustomerLoginImpl;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.presentation.applyui.CustomerApplyController;
import group2.grade15.njuse.presentation.customerui.CustomerMainController;
import group2.grade15.njuse.presentation.myanimation.ChangeWidth;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerLoginController implements Initializable {

    private Stage currentStage;

    public void setStage(Stage s) {
        currentStage = s;
    }

    @FXML
    private Pane loginVBoxBack;

    @FXML
    private VBox loginVBox;

    @FXML
    private Label loginLabel, applyLabel;

    @FXML
    private TextField accoutField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(loginLabel,"file:client/src/main/res/login/login");
        CustomeButton.implButton(applyLabel,"file:client/src/main/res/login/apply");

        //渐入动画
        Fade loginFadeIn = new Fade(loginVBox, 300, true);
        loginVBox.setOpacity(0);
        loginFadeIn.play();
    }


    @FXML
    private void showApplyPane() {

        //使申请窗口从右边推出，原面板淡出，加载申请账号面板
        ChangeWidth loginExtendFromRight = new ChangeWidth(loginVBoxBack, 300, 700);
        loginExtendFromRight.setOnFinished((ActionEvent e) -> loadApplyPanel());

        Fade loginFadeOut = new Fade(loginVBox, 300, false);
        loginFadeOut.setOnFinished((ActionEvent e) -> loginExtendFromRight.play());

        loginFadeOut.play();

    }

    private void loadApplyPanel() {
        try {
            FXMLLoader customerApplyLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/applyui/CustomerApply.fxml"));
            currentStage.setScene(new Scene(customerApplyLoader.load()));
            CustomerApplyController applyController = customerApplyLoader.getController();
            applyController.setStage(currentStage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void login() {

        LoginControllerServ loginServ = new CustomerLoginImpl();
        CustomerServ customerServ = new CustomerController();

        String id = accoutField.getText();
        String password = passwordField.getText();

        switch (loginServ.login(id,password)){
            case SUCCESS:
                jumpToMain(customerServ.getInfo(Integer.parseInt(id)));
                break;
            case FAILED:
                Alert wrongPswAlert = new Alert(Alert.AlertType.ERROR, "密码错误!");
                wrongPswAlert.showAndWait();
                break;
            case NON_EXISTENT:
                Alert invalidAccountAlert = new Alert(Alert.AlertType.ERROR, "账号不存在!");
                invalidAccountAlert.showAndWait();
                break;
            case CONNECTION_EXCEPTION:
                Alert netErrorInfo = new Alert(Alert.AlertType.ERROR, "网络连接错误!");
                netErrorInfo.showAndWait();
                break;
        }

    }

    private void jumpToMain(CustomerVO customerVO) {

        try {
            FXMLLoader customerMainLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerMain.fxml"));
            Stage customerStage = new Stage();
            customerStage.setScene(new Scene(customerMainLoader.load()));

            CustomerMainController mainController = customerMainLoader.getController();
            mainController.initData(customerVO);

            currentStage.close();

            customerStage.setTitle("酒店预订系统——客户端");
            customerStage.sizeToScene();
            customerStage.setResizable(false);
            customerStage.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

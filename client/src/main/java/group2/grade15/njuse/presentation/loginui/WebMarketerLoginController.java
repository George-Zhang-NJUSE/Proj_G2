package group2.grade15.njuse.presentation.loginui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.bl.loginbl.CustomerLoginImpl;
import group2.grade15.njuse.bl.loginbl.WebMarketerLoginImpl;
import group2.grade15.njuse.bl.webmarketerbl.WebMarketerController;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.blservice.WebMarketerServ;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.webmarketerui.WebMarketerMainController;
import group2.grade15.njuse.vo.WebMarketerVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static group2.grade15.njuse.utility.ResultMessage.*;

/**
 * Created by ALIENWARE-PC on 2016/12/1.
 */
public class WebMarketerLoginController implements Initializable {
    @FXML
    private Label login;
    @FXML
    private TextField accoutField;
    @FXML
    private TextField passwordField;

    private Stage currentStage;

    LoginControllerServ loginServ = new WebMarketerLoginImpl();
    WebMarketerServ webmarketerServ = new WebMarketerController();

    private void login() {

        LoginControllerServ loginServ = new WebMarketerLoginImpl();
        WebMarketerServ webMarketerServ = new WebMarketerController();

        String id = (accoutField.getText());
        String password = passwordField.getText();

        switch (loginServ.login(id,password)){
            case SUCCESS:
                jumpToMain(webMarketerServ.getInfo(id));
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
    private void jumpToMain(WebMarketerVO vo) {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/res/fxml/webmarketer/WebMarketerMain.fxml"));
            Stage webMarketerMainStage = new Stage();
            webMarketerMainStage.setScene(new Scene(loader.load()));
            ((WebMarketerMainController)loader.getController()).vo=vo;
            ((WebMarketerMainController)loader.getController()).workingNumber.setText(vo.getStaffID());
            currentStage.close();
            webMarketerMainStage.sizeToScene();
            webMarketerMainStage.setResizable(false);
            webMarketerMainStage.setTitle("酒店预订系统——网站营销人员端");
            webMarketerMainStage.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(login, "file:client/src/main/res/login/login");
        login.setOnMouseClicked((MouseEvent e) -> {
            login();
        });
    }

}

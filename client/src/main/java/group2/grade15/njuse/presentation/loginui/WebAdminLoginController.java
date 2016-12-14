package group2.grade15.njuse.presentation.loginui;
/**
 * Created by George on 2016/11/16.
 */

import group2.grade15.njuse.bl.loginbl.WebAdminLoginImpl;
import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.presentation.webadminui.WebAdminController;
import group2.grade15.njuse.vo.WebAdminVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WebAdminLoginController {

    private Stage currentStage;

    @FXML
    private ImageView loginIconImage;

    @FXML
    private TextField accoutField;

    @FXML
    private PasswordField passwordField;

    public void setStage(Stage s) {
        currentStage = s;
    }

    @FXML
    protected void mouseInLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/login_enter.png"));
    }

    @FXML
    protected void mouseExitLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/login.png"));
    }

    @FXML
    protected void mousePressLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/login_press.png"));
    }

    @FXML
    protected void mouseReleaseLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/login_enter.png"));
        login();
    }

    private void login() {

        LoginControllerServ loginServ = new WebAdminLoginImpl();
        WebAdminServ webAdminService=new group2.grade15.njuse.bl.webadminbl.WebAdminController();

        String id = accoutField.getText();
        String password = passwordField.getText();

        switch (loginServ.login(id,password)){
            case SUCCESS:
                jumpToMain(webAdminService.getInfo((id)));
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

    private void jumpToMain(WebAdminVO info) {
        try {
            FXMLLoader webAdminMainLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webadminui/WebAdminMain.fxml"));
            Stage webAdminStage = new Stage();
            webAdminStage.setScene(new Scene((Parent) webAdminMainLoader.load()));

            WebAdminController webAdminController = webAdminMainLoader.<WebAdminController>getController();
            webAdminController.setAdminVO(info);

            currentStage.close();

            webAdminStage.setTitle("酒店预订系统——网站管理人员端");
            webAdminStage.sizeToScene();
            webAdminStage.setResizable(false);
            webAdminStage.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

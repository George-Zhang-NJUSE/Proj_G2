package group2.grade15.njuse.presentation.loginui;
/**
 * Created by George on 2016/11/16.
 */

import group2.grade15.njuse.presentation.webadminui.WebAdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        loginIconImage.setImage(new Image("file:client/src/main/res/login/loginicon_enter.png"));
    }

    @FXML
    protected void mouseExitLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/loginicon.png"));
    }

    @FXML
    protected void mousePressLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/loginicon_press.png"));
    }

    @FXML
    protected void mouseReleaseLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/loginicon_enter.png"));
        login();
    }

    private void login() {
        // TODO: 2016/11/18
//        int webAdminId = Integer.parseInt(accoutField.getText());
//        String webAdminPsw = passwordField.getText();
//
//       switch (loginControllerServ.login(webAdminId, webAdminPsw, IDType.webAdmin)) {
//            case SUCCESS:
//
//
//        }

//        Alert accountInvalid = new Alert(Alert.AlertType.ERROR, "该账号未注册");
//        accountInvalid.showAndWait();

        jumpToMain();

    }

    private void jumpToMain() {
        try {
            FXMLLoader webAdminMainLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webadminui/WebAdminMain.fxml"));
            Stage webAdminStage = new Stage();
            webAdminStage.setScene(new Scene((Parent) webAdminMainLoader.load()));

            WebAdminController webAdminController = webAdminMainLoader.<WebAdminController>getController();
//            webAdminController.initData();

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

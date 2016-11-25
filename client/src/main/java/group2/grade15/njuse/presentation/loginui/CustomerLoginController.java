package group2.grade15.njuse.presentation.loginui;

import group2.grade15.njuse.presentation.myanimation.ChangeWidth;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.webadminui.WebAdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerLoginController {

    private Stage currentStage;

    public void setStage(Stage s) {
        currentStage = s;
    }

    @FXML
    private Pane loginVboxBack;

    @FXML
    private VBox loginVBox;

    @FXML
    private ImageView loginIconImage;

    @FXML
    private ImageView applyIconImage;

    @FXML
    private TextField accoutField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void mouseInLogin() {
        loginIconImage.setImage(new Image("file:client/src/main/res/login/loginicon_movein.png"));
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
        loginIconImage.setImage(new Image("file:client/src/main/res/login/loginicon_movein.png"));
        login();
    }

    @FXML
    protected void mouseInApply() {
        applyIconImage.setImage(new Image("file:client/src/main/res/login/applyicon_movein.png"));
    }

    @FXML
    protected void mouseExitApply() {
        applyIconImage.setImage(new Image("file:client/src/main/res/login/applyicon.png"));
    }

    @FXML
    protected void mousePressApply() {
        applyIconImage.setImage(new Image("file:client/src/main/res/login/applyicon_press.png"));
    }

    @FXML
    protected void mouseReleaseApply() {
        applyIconImage.setImage(new Image("file:client/src/main/res/login/applyicon_movein.png"));
        showApplyPanel();
    }

    private void showApplyPanel() {

        //使申请窗口从右边推出，原面板淡出，申请窗口淡入
        ChangeWidth loginExtendFromRight = new ChangeWidth(loginVboxBack, 500, 700);
        loginExtendFromRight.setOnFinished((ActionEvent e)->loadApplyPanel());

        Fade loginFadeOut = new Fade(loginVBox, 200, false);
        loginFadeOut.setOnFinished((ActionEvent e)->loginExtendFromRight.play());

        loginFadeOut.play();


    }

    private void loadApplyPanel() {
        try {
            FXMLLoader customerApplyLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/applyui/CustomerApply.fxml"));
            currentStage.setScene(new Scene(customerApplyLoader.load()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

//        jumpToMain();

    }

    private void jumpToMain() {
        // TODO: 2016/11/25 customer主界面还没写 
        try {
            FXMLLoader webAdminMainLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webadminui/WebAdminMain.fxml"));
            Stage webAdminStage = new Stage();
            webAdminStage.setScene(new Scene((Parent) webAdminMainLoader.load()));

            WebAdminController webAdminController = webAdminMainLoader.<WebAdminController>getController();
//            webAdminController.initData();

            currentStage.close();

            webAdminStage.setTitle("酒店预订系统——客户端");
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

package group2.grade15.njuse.presentation.loginui;

import group2.grade15.njuse.presentation.applyui.CustomerApplyController;
import group2.grade15.njuse.presentation.customerui.CustomerMainController;
import group2.grade15.njuse.presentation.myanimation.ChangeWidth;
import group2.grade15.njuse.presentation.myanimation.Fade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private ImageView loginIconImage;

    @FXML
    private ImageView applyIconImage;

    @FXML
    private TextField accoutField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //渐入动画
        Fade loginFadeIn = new Fade(loginVBox, 300, true);
        loginVBox.setOpacity(0);
        loginFadeIn.setOnFinished((ActionEvent e)->loginVBox.setOpacity(1));
        loginFadeIn.play();
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

    @FXML
    protected void mouseInApply() {
        applyIconImage.setImage(new Image("file:client/src/main/res/login/applyicon_enter.png"));
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
        applyIconImage.setImage(new Image("file:client/src/main/res/login/applyicon_enter.png"));
        showApplyPanel();
    }

    private void showApplyPanel() {

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
        // TODO: 2016/11/25 customer主界面还没写 
        try {
            FXMLLoader customerMainLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerMain.fxml"));
            Stage customerStage = new Stage();
            customerStage.setScene(new Scene((Parent) customerMainLoader.load()));

            CustomerMainController mainController = customerMainLoader.<CustomerMainController>getController();
//            webAdminController.initData();

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

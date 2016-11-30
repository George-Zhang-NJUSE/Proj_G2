package group2.grade15.njuse.presentation.applyui;

import group2.grade15.njuse.presentation.loginui.CustomerLoginController;
import group2.grade15.njuse.presentation.myanimation.ChangeWidth;
import group2.grade15.njuse.presentation.myanimation.Fade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerApplyController implements Initializable {

    private Stage currentStage;

    private String username, password, confirmPsw, idNum, phoneContact, extraInfo;

    @FXML
    private Pane applyPaneBack;

    @FXML
    private GridPane applyPane;

    @FXML
    private TextField usernameField, phoneContactField, enterpriseNameField, idNumField;

    @FXML
    private PasswordField passwordField, confirmPswField;

    @FXML
    private CheckBox isEnterpriseCheckBox;

    @FXML
    private Label extraInfoHint;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private ImageView cancelIconImage, confirmIconImage;

    public void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fade applyFadeIn = new Fade(applyPane, 300, true);
        applyPane.setOpacity(0);
        applyFadeIn.setOnFinished((ActionEvent e)->applyPane.setOpacity(1));
        applyFadeIn.play();
    }


    @FXML
    protected void changeAdditionalInfo() {
        if (isEnterpriseCheckBox.isSelected()) {
            extraInfoHint.setText("企业名称");
            enterpriseNameField.setVisible(true);
            enterpriseNameField.setText(null);
            birthdayPicker.setVisible(false);
        } else {
            extraInfoHint.setText("生日");
            enterpriseNameField.setVisible(false);
            birthdayPicker.setVisible(true);
            birthdayPicker.getEditor().setText(null);
        }
    }


    private void rollBackToLogin() {
        // TODO: 2016/11/25

        //使申请窗口退回右边,原面板淡出，加载登录面板
        ChangeWidth applyShrinkToRight = new ChangeWidth(applyPaneBack, 300, 220);
        applyShrinkToRight.setOnFinished((ActionEvent e) -> loadLoginPanel());

        Fade loginFadeOut = new Fade(applyPane, 300, false);
        loginFadeOut.setOnFinished((ActionEvent e) -> applyShrinkToRight.play());

        loginFadeOut.play();

    }

    private void loadLoginPanel() {
        try {
            FXMLLoader customerLoginLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/loginui/CustomerLogin.fxml"));
            currentStage.setScene(new Scene(customerLoginLoader.load()));
            CustomerLoginController loginController = customerLoginLoader.getController();
            loginController.setStage(currentStage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void applyAccount() {
        username = usernameField.getText();
        password = passwordField.getText();
        confirmPsw = confirmPswField.getText();
        phoneContact = phoneContactField.getText();
        idNum = idNumField.getText();

        extraInfo = (isEnterpriseCheckBox.isSelected()) ? enterpriseNameField.getText() : birthdayPicker.getEditor().getText();
        // TODO: 2016/11/25 apply with the info above

    }

    @FXML
    protected void mouseEnterCancel() {
        cancelIconImage.setImage(new Image("file:client/src/main/res/apply/cancelicon_movein.png"));
    }

    @FXML
    protected void mouseExitCancel() {
        cancelIconImage.setImage(new Image("file:client/src/main/res/apply/cancelicon.png"));
    }

    @FXML
    protected void mousePressCancel() {
        cancelIconImage.setImage(new Image("file:client/src/main/res/apply/cancelicon_press.png"));
    }

    @FXML
    protected void mouseReleaseCancel() {
        cancelIconImage.setImage(new Image("file:client/src/main/res/apply/cancelicon_movein.png"));
        rollBackToLogin();
    }

    @FXML
    protected void mouseEnterConfirm() {
        confirmIconImage.setImage(new Image("file:client/src/main/res/apply/confirmicon_movein.png"));
    }

    @FXML
    protected void mouseExitConfirm() {
        confirmIconImage.setImage(new Image("file:client/src/main/res/apply/confirmicon.png"));
    }

    @FXML
    protected void mousePressConfirm() {
        confirmIconImage.setImage(new Image("file:client/src/main/res/apply/confirmicon_press.png"));
    }

    @FXML
    protected void mouseReleaseConfirm() {
        confirmIconImage.setImage(new Image("file:client/src/main/res/apply/confirmicon_movein.png"));
//        applyAccount();
    }


}

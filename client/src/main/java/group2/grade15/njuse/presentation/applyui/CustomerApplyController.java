package group2.grade15.njuse.presentation.applyui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerApplyController {

    private String username,password,confirmPsw,phoneContact,extraInfo;

    @FXML
    private GridPane loginPane;

    @FXML
    private TextField usernameField,phoneContactField,enterpriseNameField;

    @FXML
    private PasswordField passwordField,confirmPswField;

    @FXML
    private CheckBox isEnterpriseCheckBox;

    @FXML
    private Label extraInfoHint;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private ImageView cancelIconImage,confirmIconImage;

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
        //使申请窗口退回右边

    }

    private void applyAccount() {
        username = usernameField.getText();
        password = passwordField.getText();
        confirmPsw = confirmPswField.getText();
        phoneContact = phoneContactField.getText();

        extraInfo = (isEnterpriseCheckBox.isSelected())? enterpriseNameField.getText():birthdayPicker.getEditor().getText();
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
//        rollBackToLogin();
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

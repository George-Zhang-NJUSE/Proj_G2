package group2.grade15.njuse.presentation.applyui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.presentation.loginui.CustomerLoginController;
import group2.grade15.njuse.presentation.myanimation.ChangeWidth;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerApplyController implements Initializable {

    private Stage currentStage;
    private CustomerVO newCustomer;

    @FXML
    private Pane applyPaneBack;

    @FXML
    private GridPane applyPane;

    @FXML
    private TextField usernameField, phoneContactField, enterpriseNameField;

    @FXML
    private PasswordField passwordField, confirmPswField;

    @FXML
    private CheckBox isEnterpriseCheckBox;

    @FXML
    private Label extraInfoHint, confirmLabel, cancelLabel;

    @FXML
    private DatePicker birthdayPicker;


    public void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //加载按钮样式
        CustomeButton.implButton(confirmLabel,"file:client/src/main/res/apply/confirm");
        CustomeButton.implButton(cancelLabel,"file:client/src/main/res/apply/cancel");

        Fade applyFadeIn = new Fade(applyPane, 300, true);
        applyPane.setOpacity(0);
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

    @FXML
    private void rollBackToLogin() {

        //使申请窗口退回右边,原面板淡出，加载登录面板
        ChangeWidth applyShrinkToRight = new ChangeWidth(applyPaneBack, 300, 220);
        applyShrinkToRight.setOnFinished((ActionEvent e) -> loadLoginPane());

        Fade loginFadeOut = new Fade(applyPane, 300, false);
        loginFadeOut.setOnFinished((ActionEvent e) -> applyShrinkToRight.play());

        loginFadeOut.play();

    }

    private void loadLoginPane() {
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

    @FXML
    private void applyAccount() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String phoneContact = phoneContactField.getText();
        MemberType memberType;
        String enterpriseName=null;
        Date birthday=null;
        java.sql.Date sqlBirthday = null;

        if (isEnterpriseCheckBox.isSelected()) {
            memberType = MemberType.vip;
            enterpriseName = enterpriseNameField.getText();
        }else{
            memberType = MemberType.normal;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                birthday = dateFormat.parse(birthdayPicker.getEditor().getText());
                sqlBirthday = new java.sql.Date(birthday.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        // TODO: 2016/11/25 apply with the info above
        CustomerVO customerVO = new CustomerVO(0, username, password, phoneContact, sqlBirthday, 100, memberType, enterpriseName);
        CustomerServ customerServ = new CustomerController();
        newCustomer=customerServ.addCustomer(customerVO);
        if (newCustomer != null) {
            Alert successInfo = new Alert(Alert.AlertType.INFORMATION, "注册成功，您的账号为：" + newCustomer.getId());
            successInfo.showAndWait();
        } else {
            Alert failInfo = new Alert(Alert.AlertType.ERROR, "注册失败，联系方式已被使用！");
            failInfo.showAndWait();
        }
    }


}

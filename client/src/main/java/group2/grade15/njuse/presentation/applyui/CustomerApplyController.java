package group2.grade15.njuse.presentation.applyui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.presentation.myanimation.ChangeWidth;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerApplyController implements Initializable {

    private CustomerVO newCustomer;
    private StackPane applyPaneBack;
    private VBox loginPane;

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
    private void changeAdditionalInfo() {
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
        Fade loginFadeIn = new Fade(loginPane, 300, true);

        ChangeWidth applyShrinkToRight = new ChangeWidth(applyPaneBack, 300, 220);
        applyShrinkToRight.setOnFinished((ActionEvent e) -> {
            loginPane.setVisible(true);
            loginFadeIn.play();
        });

        Fade applyFadeOut = new Fade(applyPane, 300, false);
        applyFadeOut.setOnFinished((ActionEvent e) -> {
            applyPaneBack.getChildren().remove(applyPane);
            applyShrinkToRight.play();
        });

        applyFadeOut.play();

    }

    public void initData(StackPane backPane, VBox loginBox){
        applyPaneBack = backPane;
        loginPane = loginBox;
    }

    @FXML
    private void applyAccount() {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPsw = confirmPswField.getText();
        String phoneContact = phoneContactField.getText();
        MemberType memberType;
        String enterpriseName=null;
        Date birthday=null;
        java.sql.Date sqlBirthday = null;

        if(!confirmPsw.equals(password)){

            Alert contradictoryPsw=new Alert(Alert.AlertType.ERROR,"注册失败，确认密码与密码不一致！");
            contradictoryPsw.showAndWait();

        }else{

            if (phoneContact.length() != 11) {

                Alert wrongPhoneLen = new Alert(Alert.AlertType.ERROR, "注册失败，联系方式位数不对！");
                wrongPhoneLen.showAndWait();

            } else {

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

                CustomerVO customerVO = new CustomerVO(0, username, password, phoneContact, sqlBirthday, 100, memberType, enterpriseName);
                CustomerServ customerServ = new CustomerController();
                newCustomer=customerServ.addCustomer(customerVO);

                if (newCustomer != null) {
                    Alert successInfo = new Alert(Alert.AlertType.INFORMATION, "注册成功，您的账号为：" + newCustomer.getId());
                    successInfo.setOnCloseRequest((DialogEvent e)->rollBackToLogin());
                    successInfo.showAndWait();
                } else {
                    Alert failInfo = new Alert(Alert.AlertType.ERROR, "注册失败，联系方式已被使用！");
                    failInfo.showAndWait();
                }

            }

        }

    }


}

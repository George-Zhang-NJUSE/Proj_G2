package group2.grade15.njuse.presentation.customerui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerInfoController implements Initializable{

    private CustomerVO customerVO;
    private CustomerServ customerServ;

    @FXML
    private Node rootNode;

    @FXML
    private VBox creditRecordBox;

    @FXML
    private TextField userNameField, phoneNumberField;

    @FXML
    private Label editUserNameLabel, editPhoneNumberLabel, cancelLabel, confirmLabel;

    @FXML
    private void requestEditUserName() {
        userNameField.setEditable(true);
        showControlButtons();
        userNameField.requestFocus();
    }

    @FXML
    private void requestEditPhoneNumber() {
        phoneNumberField.setEditable(true);
        showControlButtons();
        phoneNumberField.requestFocus();
    }

    @FXML
    private void saveEdition() {

        String newUserName = userNameField.getText();
        String newPhoneNum = phoneNumberField.getText();
        CustomerVO modifiedVO = new CustomerVO(customerVO.getId(), newUserName, customerVO.getPassword(), newPhoneNum,
                        customerVO.getBirthday(), customerVO.getCredit(), customerVO.getType(), customerVO.getCompanyName());

        ResultMessage result = customerServ.modifyInfo(modifiedVO);
        switch (result) {
            case SUCCESS:
                Alert successInfo = new Alert(Alert.AlertType.INFORMATION, "修改成功！");
                successInfo.showAndWait();
                break;
            case FAILED:
                Alert failInfo = new Alert(Alert.AlertType.ERROR, "联系方式已被占用，修改失败！");
                failInfo.showAndWait();
                break;
            case CONNECTION_EXCEPTION:
                Alert netErrorInfo = new Alert(Alert.AlertType.ERROR, "由于网络连接的原因，修改失败！");
                netErrorInfo.showAndWait();
                break;
        }

        if (result == ResultMessage.SUCCESS) {
            customerVO = modifiedVO;
            CommonData.getInstance().setCustomerVO(customerVO);
        }

        userNameField.setEditable(false);
        phoneNumberField.setEditable(false);
        hideControlButtons();
    }

    @FXML
    private void cancelEdition() {

        userNameField.setEditable(false);
        phoneNumberField.setEditable(false);
        hideControlButtons();
    }


    private void showControlButtons() {
        cancelLabel.setVisible(true);
        confirmLabel.setVisible(true);
    }

    private void hideControlButtons() {
        cancelLabel.setVisible(false);
        confirmLabel.setVisible(false);
    }

    private void show() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        popIn.setOnFinished((ActionEvent e) -> loadCreditHistory());
        fadeIn.play();
        popIn.play();
    }

    private void loadCreditHistory() {
        try {
            ArrayList<CreditVO> creditList = customerServ.getCreditHistory(customerVO.getId()).getCreditList();
            creditRecordBox.getChildren().clear();

            ArrayList<CreditRecordItemController> controllerList = new ArrayList<>();

            for (CreditVO creditVO:creditList) {
                FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/CreditRecordItem.fxml"));
                Node single = loader.load();
                creditRecordBox.getChildren().add(single);
                CreditRecordItemController creditRecordItemController = loader.getController();
                creditRecordItemController.initData(creditVO);
                controllerList.add(creditRecordItemController);
            }

            controllerList.forEach(CreditRecordItemController::show);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(editUserNameLabel, "file:client/src/main/res/customer/edit");
        CustomeButton.implButton(editPhoneNumberLabel, "file:client/src/main/res/customer/edit");
        CustomeButton.implButton(cancelLabel, "file:client/src/main/res/customer/cancel");
        CustomeButton.implButton(confirmLabel, "file:client/src/main/res/customer/confirm");

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);
    }

    public void initDataAndShow() {
        customerServ = new CustomerController();

        customerVO = CommonData.getInstance().getCustomerVO();
        userNameField.setText(customerVO.getName());
        phoneNumberField.setText(customerVO.getContact());
        show();
    }

}

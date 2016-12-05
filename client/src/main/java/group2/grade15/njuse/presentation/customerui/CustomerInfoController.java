package group2.grade15.njuse.presentation.customerui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
        // TODO: 2016/12/5 更新客户信息

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
        Fade fadeIn = new Fade(rootNode, 300, true);
        fadeIn.play();
    }

    private void loadCreditHistory() {
        try {
            creditRecordBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CreditRecordItem.fxml"));
                Node singleItemTemplate = loader.load();
                CreditRecordItemController creditRecordItemController = loader.getController();

                ItemList.add(singleItemTemplate);
            }

            creditRecordBox.getChildren().addAll(ItemList);

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

        //为渐入动画做准备
        rootNode.setOpacity(0);
    }

    public void initDataAndShow(CustomerVO vo) {
        // TODO: 2016/12/5 加载数据

        loadCreditHistory();

        show();
    }

}

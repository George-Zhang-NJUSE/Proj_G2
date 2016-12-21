package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ChangeReason;
import group2.grade15.njuse.vo.CreditVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/11/30.
 */
public class CreditChargeController implements Initializable {
    public Pane motherPane;

    @FXML
    private TextField accountField;
    @FXML
    private TextField accountCheckField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField PWField;
    @FXML
    private Label backInfo;
    @FXML
    private Label check;
    @FXML
    private Label cancel;

    public WebMarketerMainController webMarketerMainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CustomeButton.implButton(check, "file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/webmarketer/Cancel");
        check.setOnMouseClicked((MouseEvent event) -> {
            charge();
        });
    }

    private void clean(){
        accountField.setText("");
        accountCheckField.setText("");
        amountField.setText("");
        PWField.setText("");
    }
    public void charge(){
        //TODO
        CreditVO creditVO=new CreditVO(Integer.parseInt(accountField.getText()),0,0,0,Double.parseDouble(amountField.getText()),null, ChangeReason.charge);
        switch (WebMarketerMainController.webMarketerService.modifyCredit(creditVO)) {
            case SUCCESS:
                webMarketerMainController.alert("充值完成");
                clean();
                break;
            case CONNECTION_EXCEPTION:
                webMarketerMainController.alert("未连接到服务器");
                break;
            case FAILED:
                webMarketerMainController.alert("充值失败");
                break;
        }
    }
}

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CustomeButton.implButton(check, "file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/webmarketer/Cancel");
        cancel.setOnMouseClicked((MouseEvent event) -> {
            charge();
        });
    }


    public void charge(){
        //TODO
        CreditVO creditVO=new CreditVO(Integer.parseInt(accountField.getText()),0,0,0,Double.parseDouble(amountField.getText()),null, ChangeReason.charge);
        WebMarketerMainController.webMarketerService.modifyCredit(creditVO);
    }
}

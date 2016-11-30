package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/11/30.
 */
public class CreditChargeController implements Initializable {
    @FXML
    private Label check;
    @FXML
    private Label cancel;
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/webmarketer/Cancel");

    }
}

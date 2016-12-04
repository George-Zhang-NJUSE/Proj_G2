package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/1.
 */
public class FixController implements Initializable {
    @FXML
    private Label check;
    @FXML
    private Label clear;
    @FXML
    private TextField searchID;
    @FXML
    private TextField orderID;
    @FXML
    private TextField currentState;
    @FXML
    private TextField hotelName;
    @FXML
    private TextField address;
    @FXML
    private TextField prefCheckInTime;
    @FXML
    private TextField prefCheckOutTime;
    @FXML
    private TextField restoredCredit;
    @FXML
    private TextArea fixReason;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(clear, "file:client/src/main/res/webmarketer/Cancel");
    }

    public void clear() {
        orderID.setText("");
        currentState.setText("");
        hotelName.setText("");
        address.setText("");
        prefCheckInTime.setText("");
        prefCheckOutTime.setText("");
        restoredCredit.setText("");
        fixReason.setText("");

    }

    public void openFromID() {
        //TODO

    }

    public void openFromClick() {
        //TODO
    }

    public ResultMessage fixCommit() {
        //TODO implement the function of committing an order fixing.
        return null;
    }

    public ResultMessage showUnfixed() {
        //TODO implements the function to show the unnormal orders which are not fixed yet;
        return null;
    }

    public ResultMessage showFixed() {
        //TODO implements the function to show the unnormal orders which are fixed;
        return null;
    }
}

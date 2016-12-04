package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/4.
 */
public class RoomManageController implements Initializable {
    @FXML
    private Label addButton;
    @FXML
    private Label modifyButton;
    @FXML
    private Label deleteButton;

    @FXML
    private GridPane addPane;
    @FXML
    private GridPane modifyPane;
    @FXML
    private GridPane deletePane;
    @FXML
    private HBox checkPane;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    private GridPane now;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");

    }
}

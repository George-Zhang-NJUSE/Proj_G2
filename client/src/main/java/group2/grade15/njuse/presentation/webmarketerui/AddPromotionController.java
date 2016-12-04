package group2.grade15.njuse.presentation.webmarketerui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/2.
 */
public class AddPromotionController implements Initializable {
    private int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
    @FXML
    private ComboBox type;
    @FXML
    private GridPane VIP;
    @FXML
    private GridPane time;
    @FXML
    private GridPane rank;
    @FXML
    private ComboBox requiredRank;
    @FXML
    private TextField CBD;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

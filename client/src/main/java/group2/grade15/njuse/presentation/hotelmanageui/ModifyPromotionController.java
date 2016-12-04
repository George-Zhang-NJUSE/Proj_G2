package group2.grade15.njuse.presentation.hotelmanageui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by luoy on 2016/12/3.
 */
public class ModifyPromotionController implements Initializable {
    private int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
    @FXML
    private ComboBox type;
    @FXML
    private GridPane company;
    @FXML
    private GridPane time;
    @FXML
    private TextField companyString;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setEditable(){
        boolean sw=true;
        name.setEditable(sw);
        cut.setEditable(sw);
        type.setEditable(sw);

        companyString.setEditable(sw);
        startDate.setEditable(sw);
        endDate.setEditable(sw);
    }
    public void setUneditable(){
        boolean sw=false;
        name.setEditable(sw);
        cut.setEditable(sw);
        type.setEditable(sw);
        companyString.setEditable(sw);
        startDate.setEditable(sw);
        endDate.setEditable(sw);
    }
}

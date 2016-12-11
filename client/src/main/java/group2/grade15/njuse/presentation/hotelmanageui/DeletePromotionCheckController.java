package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/11.
 */
public class DeletePromotionCheckController implements Initializable {
    @FXML
    private Label check;
    @FXML
    private Label cancel;

    public group2.grade15.njuse.presentation.hotelmanageui.PromotionManageController promotionManageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/webmarketer/Cancel");
    }

    public void delete(){
        promotionManageController.deletePromotion();
    }

    public void back(){
        promotionManageController.closeOpPane();
    }
}

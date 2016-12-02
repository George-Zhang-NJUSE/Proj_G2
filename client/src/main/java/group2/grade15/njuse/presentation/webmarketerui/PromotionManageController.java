package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/1.
 */
public class PromotionManageController implements Initializable{
    @FXML
    private Label changeStateButton;
    @FXML
    private Label modifyButton;
    @FXML
    private Label deleteButton;
    @FXML
    private TableView activatedList;
    @FXML
    private TableView unactivatedList;
    @FXML
    private Tab activatedTab;
    @FXML
    private Tab unactivatedTab;
    @FXML
    private Pane opPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/webmarketer/play");
        CustomeButton.implButton(modifyButton, "file:client/src/main/res/webmarketer/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/webmarketer/delete");
        unactivatedTab.setOnSelectionChanged((Event e)->{
            switchToUnactivated();
        });
        activatedTab.setOnSelectionChanged((Event e)->{
            switchToActivated();
        });

    }
    public void switchToActivated(){
        changeStateButton.setText("中止");
        ImageView a=(ImageView) changeStateButton.getGraphic();
        a.setImage(new Image("file:client/src/main/res/webmarketer/stop.png"));
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/webmarketer/stop");
    }
    public void switchToUnactivated(){
        changeStateButton.setText("激活");
        ImageView a=(ImageView) changeStateButton.getGraphic();
        a.setImage(new Image("file:client/src/main/res/webmarketer/play.png"));
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/webmarketer/play");
    }
}

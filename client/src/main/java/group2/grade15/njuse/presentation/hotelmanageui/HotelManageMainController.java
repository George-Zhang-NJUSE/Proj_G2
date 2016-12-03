package group2.grade15.njuse.presentation.hotelmanageui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by luoy on 2016/12/2.
 */
public class HotelManageMainController implements Initializable {
    @FXML
    private FlowPane infoTag;
    @FXML
    private FlowPane orderManageTag;
    @FXML
    private FlowPane roomManageTag;
    @FXML
    private FlowPane promotionManageTag;
    @FXML
    private Rectangle infoRec;
    @FXML
    private Rectangle orderManageRec;
    @FXML
    private Rectangle roomManageRec;
    @FXML
    private Rectangle promotionManageRec;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSwither(infoTag);
        addSwither(orderManageTag);
        addSwither(roomManageTag);
        addSwither(promotionManageTag);

    }
    @FXML
    private void toInfo(){
        infoRec.setVisible(true);
        orderManageRec.setVisible(false);
        roomManageRec.setVisible(false);
        promotionManageRec.setVisible(false);


    }
    @FXML
    private void toOrderManage(){
        infoRec.setVisible(false);
        orderManageRec.setVisible(true);
        roomManageRec.setVisible(false);
        promotionManageRec.setVisible(false);

    }
    @FXML
    private void toRoomManage(){
        infoRec.setVisible(false);
        orderManageRec.setVisible(false);
        roomManageRec.setVisible(true);
        promotionManageRec.setVisible(false);

    }
    @FXML
    private void toPromotionManage(){
        infoRec.setVisible(false);
        orderManageRec.setVisible(false);
        roomManageRec.setVisible(false);
        promotionManageRec.setVisible(true);

    }
    public void addSwither(FlowPane pane){
        pane.setOnMouseEntered((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(230,230,230)");
        });
        pane.setOnMouseExited((MouseEvent e)->{
            pane.setStyle("-fx-background-color: inherit");
        });
        pane.setOnMousePressed((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(210,210,210)");
        });
    }
}

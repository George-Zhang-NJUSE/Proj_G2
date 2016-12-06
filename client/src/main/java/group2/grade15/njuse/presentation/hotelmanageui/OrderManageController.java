package group2.grade15.njuse.presentation.hotelmanageui;

import com.sun.org.apache.xpath.internal.operations.Or;
import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/4.
 */
public class OrderManageController implements Initializable {
    @FXML
    private Label checkinButton;
    @FXML
    private Label checkoutButton;
    @FXML
    private Label overtimeButton;
    @FXML
    private GridPane checkinPane;
    @FXML
    private GridPane checkoutPane;
    @FXML
    private GridPane overtimeCheckinPane;
    @FXML
    private HBox optionBox;
    @FXML
    private TableView<OrderVO> unexeTable;
    private GridPane now;
    @FXML
    private Pane checkPane;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        impleButton(checkinButton);
        impleButton(checkoutButton);
        impleButton(overtimeButton);
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");

    }

    public void toCheckin(){
        optionBox.setVisible(false);
        checkinPane.setVisible(true);
        checkPane.setVisible(true);
        now=checkinPane;
        Fade cin=new Fade(checkPane,200,true);cin.play();
        Fade in = new Fade(checkinPane, 200, true);
        in.play();
    }
    public void toCheckout(){
        optionBox.setVisible(false);
        checkoutPane.setVisible(true);
        checkPane.setVisible(true);
        now=checkoutPane;
        Fade cin=new Fade(checkPane,200,true);cin.play();
        Fade in = new Fade(checkoutPane, 200, true);
        in.play();
    }
    public void toOvertimeCheckin(){
        optionBox.setVisible(false);
        overtimeCheckinPane.setVisible(true);
        checkPane.setVisible(true);
        now=overtimeCheckinPane;
        Fade cin=new Fade(checkPane,200,true);cin.play();
        Fade in = new Fade(overtimeCheckinPane, 200, true);
        in.play();

    }
    public void back(){
        now.setVisible(false);
        optionBox.setVisible(true);
        checkPane.setVisible(false);
        Fade in = new Fade(optionBox, 200, true);
        in.play();
    }
    public void checkeAction(){

    }
    public void checkin(){
        //TODO
    }
    public void checkout(){
        //TODO
    }
    public void overtimeCheckin(){
        //TODO
    }
    private void impleButton(Label label){
        label.setOnMouseEntered((MouseEvent e)->{
            label.setStyle("-fx-border-color: rgb(29,171,226);-fx-border-radius: 10; -fx-border-width: 2");
            //label.setStyle("-fx-text-fill: rgb(29,171,226)");
        });
        label.setOnMouseExited((Event)->{
            label.setStyle("-fx-border-color: rgb(0,0,0);-fx-border-radius: 10; -fx-border-width: 2; -fx-text-fill: rgb(0,0,0)");
        });
        label.setOnMousePressed((MouseEvent e)->{
            //label.setStyle("-fx-border-color: rgb(29,171,226)");
            label.setStyle("-fx-border-color: rgb(29,171,226);-fx-border-radius: 10; -fx-border-width: 2; -fx-text-fill: rgb(29,171,226)");
        });
        label.setOnMouseReleased((MouseEvent e)->{
            label.setStyle("-fx-border-color: rgb(29,171,226);-fx-border-radius: 10; -fx-border-width: 2; -fx-text-fill: rgb(0,0,0)");
        });
    }

    //逻辑的数据处理部分
    public OrderVO getOrderVO(){
        int index=unexeTable.;
        OrderVO result=unexeTable.getItems().get(index);
    }
}

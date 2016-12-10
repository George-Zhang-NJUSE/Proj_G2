package group2.grade15.njuse.presentation.hotelmanageui;

import com.sun.org.apache.xpath.internal.operations.Or;
import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
    private TableView<OrderVO> unexeList,checkinList,completeList,innormalList,cancelList;
    @FXML
    private Tab unexe;
    @FXML
    private Tab checkin;
    @FXML
    private Tab complete;
    @FXML
    private Tab innormal;
    @FXML
    private Tab canceled;
    @FXML
    private TextField orderID;
    @FXML
    private TextField customerID;
    @FXML
    private TextField checkInDate;
    @FXML
    private TextField checkOutDate;
    @FXML
    private TextField roomType;
    @FXML
    private TextField roomNum;
    @FXML
    private TextField totalPrice;
    @FXML
    private TextField orderState;


    private Tab workingTab;

    private GridPane now;

    @FXML
    private Pane checkPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label check;
    @FXML
    private Label cancel;




    enum WorkingTab{
        UNEXE,CHECKIN,COMPLETE,INNORMAL,CANCEL
    }

    //逻辑部分
    private WorkingTab state;
    public static OrderServ orderService=new OrderController();
    public static OrderListServ orderListService=new OrderController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        impleButton(checkinButton);
        impleButton(checkoutButton);
        impleButton(overtimeButton);
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");

    }
    public void tab1(){
        //OrderListVO list=orderListService.get;
        state=WorkingTab.UNEXE;
    }
    public void tab2(){
        state=WorkingTab.CHECKIN;
    }
    public void tab3(){
        state=WorkingTab.COMPLETE;
    }
    public void tab4(){
        state=WorkingTab.INNORMAL;
    }
    public void tab5(){
        state=WorkingTab.CANCEL;
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

    private void fillTbale(){
        OrderVO vo=getSelectedOrderVO();
        fillTable(vo);
    }
    private void fillTable(OrderVO vo){
        orderID.setText(Integer.toString(vo.getOrderID()));
        customerID.setText(Integer.toString(vo.getCustomerID()));
        checkInDate.setText(vo.getCheckInTime().toString());
        checkOutDate.setText(vo.getCheckOutTime().toString());
        roomType.setText(vo.getType().toString());
        roomNum.setText(Integer.toString(vo.getRoomSum()));
        totalPrice.setText(Double.toString(vo.getAmount()));
        orderState.setText(vo.getState().toString());

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
    public OrderVO getSelectedOrderVO(){
        switch (state) {
            case UNEXE:
                return getOrderVO(unexeList);
            case CHECKIN:
                return getOrderVO(checkinList);
            case COMPLETE:
                return getOrderVO(completeList);
            case INNORMAL:
                return getOrderVO(innormalList);
            case CANCEL:
                return getOrderVO(cancelList);
            default:
                return null;
        }
    }
    private OrderVO getOrderVO(TableView<OrderVO> List){
        int index= List.getSelectionModel().getSelectedIndex();
        return List.getItems().get(index);
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
}

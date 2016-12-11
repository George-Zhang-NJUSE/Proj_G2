package group2.grade15.njuse.presentation.hotelmanageui;

import com.sun.org.apache.regexp.internal.RE;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.OrderVO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
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
    private TableView<Order> unexeList;
    @FXML
    private TableView<Order> checkinList;
    @FXML
    private TableView<Order> completeList;
    @FXML
    private TableView<Order> innormalList;
    @FXML
    private TableView<Order> cancelList;


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

    private GridPane now;

    @FXML
    private Pane checkPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label check;
    @FXML
    private Label cancel;

    @FXML
    private Label message;



    enum WorkingTab{
        UNEXE,CHECKIN,COMPLETE,INNORMAL,CANCEL
    }
    private ObservableList<Order> unexeListData,checkinListData,completeListData,innormalListData,cancelListData;
    private String[] properties={"orderId","customerId","promotionId","amount","inDate","outDate","createTime","finalDate"};

    //逻辑部分
    private WorkingTab workingTab;
    public static OrderServ orderService=new OrderController();
    public static OrderListServ orderListService=new OrderController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        impleButton(checkinButton);
        impleButton(checkoutButton);
        impleButton(overtimeButton);
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");
        for(int i=0;i<properties.length;i++) {
            ((TableColumn)unexeList.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            ((TableColumn)checkinList.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            ((TableColumn)completeList.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            ((TableColumn)innormalList.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            ((TableColumn)cancelList.getColumns().get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
        }
        unexeListData= FXCollections.observableArrayList();
        unexeList.setItems(unexeListData);
        checkinListData= FXCollections.observableArrayList();
        checkinList.setItems(checkinListData);
        completeListData= FXCollections.observableArrayList();
        completeList.setItems(completeListData);
        innormalListData= FXCollections.observableArrayList();
        innormalList.setItems(innormalListData);
        cancelListData= FXCollections.observableArrayList();
        cancelList.setItems(cancelListData);

        showAllOrder();
    }
    public void tab1(){
        //OrderListVO list=orderListService.get;
        workingTab =WorkingTab.UNEXE;
    }
    public void tab2(){
        workingTab =WorkingTab.CHECKIN;
    }
    public void tab3(){
        workingTab =WorkingTab.COMPLETE;
    }
    public void tab4(){
        workingTab =WorkingTab.INNORMAL;
    }
    public void tab5(){
        workingTab =WorkingTab.CANCEL;
    }


    private void removeSelectedOrderFromList(TableView table){
        int index=table.getSelectionModel().getSelectedIndex();
        table.getItems().remove(index);
    }

    private void addOrderToList(TableView table, OrderVO vo) {
        table.getItems().add(new Order(vo));
    }
    private void showAllOrder(){
        unexeList.getItems().clear();
        checkinList.getItems().clear();
        innormalListData.clear();
        cancelListData.clear();
        ArrayList<OrderVO> orderVOs=HotelManageMainController.hotelOrderController.getAllOrderListByHotelID(HotelManageMainController.hotelVO.getId()).getOrderList();
        for(int i=0;i<orderVOs.size();i++) {
            OrderVO vo = orderVOs.get(i);
            switch (vo.getState()) {
                case unexecuted:
                    unexeListData.add(new Order(vo));
                    break;
                case executed:
                    checkinListData.add(new Order(vo));
                    break;
                case abnormal:
                    innormalListData.add(new Order(vo));
                    break;
                case revoked:
                    cancelListData.add(new Order(vo));
                    break;
            }
        }
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

    private void fillTable(){
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
        switch (workingTab) {
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
    private OrderVO getOrderVO(TableView<Order> List){
        int index= List.getSelectionModel().getSelectedIndex();
        Order order=  List.getItems().get(index);

        OrderVO orderVO=order.vo;
        return orderVO;
    }

    public void checkeAction(){

    }
    public void checkin(){
        //TODO

        OrderVO vo=getSelectedOrderVO();
        if(vo.getState()==OrderState.unexecuted){
            if(ResultMessage.SUCCESS==HotelManageMainController.hotelManagerController.modifyState(vo.getOrderID(), OrderState.executed)){
                message.setText("操作成功");
                removeSelectedOrderFromList(unexeList);
                addOrderToList(checkinList,vo);
            }else{
                message.setText("操作失败");
            };
        }else{
            message.setText("该订单不是未执行订单");
        }
    }
    public void checkout(){
        //TODO
    }
    public void overtimeCheckin(){
        //TODO
        OrderVO vo=getSelectedOrderVO();
        if (vo.getState() == OrderState.abnormal) {
            if(ResultMessage.SUCCESS==HotelManageMainController.hotelManagerController.modifyState(vo.getOrderID(),OrderState.executed)){
                message.setText("操作成功");
                removeSelectedOrderFromList(innormalList);
                addOrderToList(checkinList, vo);
            }else{
                message.setText("操作失败");
            }
        }else{
            message.setText("该订单不是异常订单");
        }

    }





    public static class Order{
        private final SimpleIntegerProperty orderId;
        private final SimpleIntegerProperty customerId;
        private final SimpleIntegerProperty hotelId;
        private final SimpleIntegerProperty promotionId;
        private final SimpleDoubleProperty amount;
        private final SimpleStringProperty inDate;
        private final SimpleStringProperty outDate;
        private final SimpleStringProperty finalDate;
        private final SimpleStringProperty createTime;
        private final SimpleIntegerProperty roomNum;
        private final SimpleStringProperty roomType;
        private final SimpleIntegerProperty numOfCustomer;
        private final SimpleBooleanProperty haveKid;
        private final SimpleStringProperty state;
        private final OrderVO vo;

        public Order(OrderVO vo){
            orderId = new SimpleIntegerProperty(vo.getOrderID());
            customerId = new SimpleIntegerProperty(vo.getCustomerID());
            hotelId = new SimpleIntegerProperty(vo.getHotelID());
            promotionId = new SimpleIntegerProperty(vo.getPromotionID());
            amount = new SimpleDoubleProperty(vo.getAmount());
            inDate = new SimpleStringProperty(vo.getCheckInTime().toString());
            outDate = new SimpleStringProperty(vo.getCheckOutTime().toString());
            finalDate = new SimpleStringProperty(vo.getFinalExecuteTime().toString());
            createTime = new SimpleStringProperty(vo.getCreateTime().toString());
            roomNum = new SimpleIntegerProperty(vo.getRoomSum());
            roomType = new SimpleStringProperty(vo.getType().toString());
            numOfCustomer = new SimpleIntegerProperty(vo.getNumOfCustomer());
            haveKid = new SimpleBooleanProperty(vo.isHaveChild());
            state = new SimpleStringProperty(vo.getState().toString());
            this.vo=vo;

        }
        public int getOrderId(){
            return orderId.get();
        }
        public int getCustomerId(){
            return customerId.get();
        }
        public int getHotelId(){
            return hotelId.get();
        }
        public int getPromotionId(){
            return promotionId.get();
        }
        public double getAmount(){
            return amount.get();
        }
        public String getInDate(){
            return inDate.get();
        }
        public String getOutDate(){
            return outDate.get();
        }
        public String getFinalDate(){
            return finalDate.get();
        }
        public int getRoomNum(){
            return roomNum.get();
        }
        public String getRoomType(){
            return roomType.get();
        }
        public int getNumOfCustomer(){
            return numOfCustomer.get();
        }
        public boolean getHaveKid(){
            return haveKid.get();
        }
        public String getState(){
            return state.get();
        }

    }
}

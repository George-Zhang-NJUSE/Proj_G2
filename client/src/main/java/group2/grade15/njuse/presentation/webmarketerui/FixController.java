package group2.grade15.njuse.presentation.webmarketerui;

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

import java.net.URL;
import java.util.ArrayList;
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
    private TextField hotelID;

    @FXML
    private TextField prefCheckInTime;
    @FXML
    private TextField prefCheckOutTime;
    @FXML
    private TextField finalExeTime;
    @FXML
    private TextField roomType;
    @FXML
    private TextField roomNum;
    @FXML
    private TextField numOfCustomer;
    @FXML
    private CheckBox kidCheck;
    @FXML
    private TextField restoredCredit;
    @FXML
    private TextArea fixReason;
    @FXML
    private TableView<Order> unsolvedList;
    @FXML
    private TableView<Order> solvedList;

    private ObservableList<Order> unsolvedListData;
    private ObservableList<Order> solvedListData;
    private String[] properties = {"orderId", "customerId", "hotelId", "promotionId", "amount", "inDate", "outDate", "createTime", "finalDate", "RoomNum", "roomType"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(clear, "file:client/src/main/res/webmarketer/Cancel");
        ObservableList unList=unsolvedList.getColumns();
        ObservableList sList = solvedList.getColumns();
        for(int i=0;i<properties.length;i++) {
            ((TableColumn)unList.get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            ((TableColumn)sList.get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
        }
        unsolvedListData= FXCollections.observableArrayList();
        solvedListData=FXCollections.observableArrayList();

        unsolvedList.setItems(unsolvedListData);
        solvedList.setItems(solvedListData);

        unsolvedList.setOnMouseClicked((MouseEvent e)->{
            try {
                int index = unsolvedList.getSelectionModel().getSelectedIndex();
                openFromClick(unsolvedListData.get(index));
                check.setVisible(true);
                clear.setVisible(true);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        solvedList.setOnMouseClicked((MouseEvent e)->{
            try {
                int index = solvedList.getSelectionModel().getSelectedIndex();
                openFromClick(solvedListData.get(index));
                check.setVisible(false);
                clear.setVisible(false);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });

    }
    public void showAllOrder(){
        //TODO 从网站营销这边拿到的列表应该不需要ID
        //ArrayList<OrderVO> un=WebMarketerMainController.webMarketerService.getAbnomalOrderList().getOrderList();
    }

    public void clear() {
        orderID.setText("");
        currentState.setText("");
        hotelID.setText("");
        prefCheckInTime.setText("");
        prefCheckOutTime.setText("");
        finalExeTime.setText("");
        restoredCredit.setText("");
        fixReason.setText("");
        roomType.setText("");
        roomNum.setText("");

    }

    public void openFromID() {
        //TODO

    }

    public void openFromClick(Order order) {
        //TODO
        orderID.setText(String.valueOf(order.getOrderId()));
        currentState.setText(order.getState());
        hotelID.setText(String.valueOf(order.getHotelId()));
        roomType.setText(order.getRoomType());
        roomNum.setText(String.valueOf(order.getRoomNum()));
        prefCheckInTime.setText(order.getInDate());
        prefCheckOutTime.setText(order.getOutDate());
        finalExeTime.setText(order.getFinalDate());
        numOfCustomer.setText(String.valueOf(order.getNumOfCustomer()));
        if (order.getHaveKid()) {
            kidCheck.setSelected(true);
        }else{
            kidCheck.setSelected(false);
        }
    }
    public void fixCommit(){

    }
    public void fixCommit(OrderVO vo) {
        //TODO implement the function of committing an order fixing.
        if(ResultMessage.SUCCESS==WebMarketerMainController.webMarketerService.modifyState(vo.getOrderID(), OrderState.revoked)){
            int index=unsolvedList.getSelectionModel().getSelectedIndex();
            unsolvedListData.remove(index);

        }

    }

    public ResultMessage showUnfixed() {
        //TODO implements the function to show the unnormal orders which are not fixed yet;
        return null;
    }

    public ResultMessage showFixed() {
        //TODO implements the function to show the unnormal orders which are fixed;
        return null;
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
        public String getCreateTime(){
            return createTime.get();
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

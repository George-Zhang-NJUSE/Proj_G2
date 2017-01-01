package group2.grade15.njuse.presentation.hotelmanageui;

import com.sun.istack.internal.Nullable;
import com.sun.org.apache.regexp.internal.RE;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.webmarketerui.WebMarketerMainController;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;
import group2.grade15.njuse.vo.OrderListVO;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static group2.grade15.njuse.presentation.hotelmanageui.HotelManageMainController.hotelManagerController;

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
    private TextField finalDate;
    @FXML
    private TextField roomType;
    @FXML
    private TextField roomNum;
    @FXML
    private TextField totalPrice;
    @FXML
    private TextField orderState;

    @FXML
    private TextField timeCI;
    @FXML
    private TextField adultCI;
    @FXML
    private TextField kidCI;

    @FXML
    private TextField timeCO;

    @FXML
    private TextField timeOC;
    @FXML
    private TextField adultOC;
    @FXML
    private TextField kidOC;
    @FXML
    private TextField restoreCredit;



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
    private Label refresh;

    @FXML
    private Label message;

    private int stage;//0= checkin 1=checkout 2=overtimecheckin


    private boolean initialized=false;
    enum WorkingTab{
        UNEXE,CHECKIN,COMPLETE,INNORMAL,CANCEL
    }
    private ObservableList<Order> unexeListData,checkinListData,completeListData,innormalListData,cancelListData;
    private String[] properties={"orderId","customerId","amount","inDate","outDate","createTime","finalDate"};

    //逻辑部分
    private WorkingTab workingTab;
    public static OrderServ orderService=new OrderController();
    public static OrderListServ orderListService=new OrderController();
    public HotelManageMainController hotelManageMainController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        impleButton(checkinButton);
        impleButton(checkoutButton);
        impleButton(overtimeButton);
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");
        CustomeButton.implButton(refresh, "file:client/src/main/res/button/refresh");
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
        unexeList.setOnMouseClicked((MouseEvent e)->{
            showFromClick();
        });
        checkinList.setOnMouseClicked((MouseEvent e)->{
            showFromClick();
        });
        completeList.setOnMouseClicked((MouseEvent e)->{
            showFromClick();
        });
        innormalList.setOnMouseClicked((MouseEvent e)->{
            showFromClick();
        });
        cancelList.setOnMouseClicked((MouseEvent e)->{
            showFromClick();
        });



        check.setOnMouseClicked((MouseEvent e)->{
            switch (stage) {
                case 0:
                    checkin();break;
                case 1:
                    checkout();break;
                case 2:
                    overtimeCheckin();break;
            }
        });

        showAllOrder();
        initialized=true;
        tab1();
    }
    @FXML
    private void tab1(){
        if(!initialized)
            return;
        workingTab =WorkingTab.UNEXE;
        checkoutButton.setVisible(false);
        overtimeButton.setVisible(false);
        checkinButton.setVisible(true);
    }
    @FXML
    private void tab2(){
        if(!initialized)
            return;
        workingTab =WorkingTab.CHECKIN;
        checkoutButton.setVisible(true);
        overtimeButton.setVisible(false);
        checkinButton.setVisible(false);
    }
    @FXML
    private void tab3(){
        if(!initialized)
            return;
        workingTab = WorkingTab.COMPLETE;
        checkoutButton.setVisible(false);
        overtimeButton.setVisible(false);
        checkinButton.setVisible(false);
    }
    @FXML
    private void tab4(){
        if(!initialized)
            return;
        workingTab = WorkingTab.INNORMAL;
        checkoutButton.setVisible(false);
        overtimeButton.setVisible(true);
        checkinButton.setVisible(false);
    }
    @FXML
    private void tab5(){
        if(!initialized)
            return;
        workingTab =WorkingTab.CANCEL;
        checkoutButton.setVisible(false);
        overtimeButton.setVisible(false);
        checkinButton.setVisible(false);
    }

    @FXML
    /**
     * 将所有表格清空 并重新获取数据库表格并分发到各个表格中
     */
    private void showAllOrder() {

        unexeList.getItems().clear();
        checkinList.getItems().clear();
        innormalListData.clear();
        cancelListData.clear();
        OrderListVO temp = orderListService.getAllOrderListByHotelID(HotelManageMainController.hotelVO.getId());
        if (temp == null) {
            return;
        }

        ArrayList<OrderVO> orderVOs = temp.getOrderList();
        for (int i = 0; i < orderVOs.size(); i++) {
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
                case complete:
                    completeListData.add(new Order(vo));
            }
        }

    }

    /**
     * 以下三个方法都是分别调出各个子操作的界面的方法
     */
    @FXML
    private void toCheckin(){
        stage=0;
        optionBox.setVisible(false);
        checkinPane.setVisible(true);
        checkPane.setVisible(true);
        now=checkinPane;
        timeCI.setText(new Timestamp(System.currentTimeMillis()).toString());
        Fade cin=new Fade(checkPane,200,true);cin.play();
        Fade in = new Fade(checkinPane, 200, true);
        in.play();
    }
    @FXML
    private void toCheckout(){
        stage=1;
        optionBox.setVisible(false);
        checkoutPane.setVisible(true);
        checkPane.setVisible(true);
        now=checkoutPane;
        timeCO.setText(new Timestamp(System.currentTimeMillis()).toString());
        Fade cin=new Fade(checkPane,200,true);cin.play();
        Fade in = new Fade(checkoutPane, 200, true);
        in.play();
    }
    @FXML
    private void toOvertimeCheckin(){
        stage=2;
        optionBox.setVisible(false);
        overtimeCheckinPane.setVisible(true);
        checkPane.setVisible(true);
        now=overtimeCheckinPane;
        timeOC.setText(new Timestamp(System.currentTimeMillis()).toString());
        Fade cin=new Fade(checkPane,200,true);cin.play();
        Fade in = new Fade(overtimeCheckinPane, 200, true);
        in.play();

    }

    /**
     * 将操作面板关闭的方法
     */
    @FXML
    private void back(){
        now.setVisible(false);
        optionBox.setVisible(true);
        checkPane.setVisible(false);
        Fade in = new Fade(optionBox, 200, true);
        in.play();
    }

    /**
     * 将OrderVO信息填写到右侧信息栏中
     * @param vo
     */
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

    /**
     * 将label实现按钮的样式变化方法
     * @param label
     */
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

    /**
     * 从列表中获取被选中的订单的VO
     * @return 被选中的订单VO
     */
    private OrderVO getSelectedOrderVO(){

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

    /**
     * getSelectedOrderVO()的子方法
     * @param List
     * @return
     */
    private OrderVO getOrderVO(TableView<Order> List){
        int index= List.getSelectionModel().getSelectedIndex();
        Order order=  List.getItems().get(index);

        OrderVO orderVO=order.vo;
        return orderVO;
    }

    /**
     * 执行入住操作时的方法
     */
    public void checkin(){
        OrderVO vo=getSelectedOrderVO();
        Order order = new Order(vo);
        if (adultCI.getText().length() == 0 || kidCI.getText().length() == 0) {
            hotelManageMainController.alert("填写内容不能为空");
            return;
        }
        order.numOfCustomer.set(Integer.parseInt(adultCI.getText())+Integer.parseInt(kidCI.getText()));
        order.inDate.set(new Timestamp(System.currentTimeMillis()).toString());

        try{
            vo = toVO(order);
        }catch(Exception e){
            hotelManageMainController.alert("未按照格式输入数据");
            return;
        }
        if(vo.getState()==OrderState.unexecuted){
            if(ResultMessage.SUCCESS== hotelManagerController.modifyState(vo.getOrderID(), OrderState.executed)){
                hotelManageMainController.alert("操作成功");
                hotelManageMainController.upDateHotelVO();
                showAllOrder();
            }else{
                hotelManageMainController.alert("操作失败");
            };
        }else{
            hotelManageMainController.alert("该订单不是未执行订单");
        }
    }
    private void showFromClick(){
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderVO vo;
        try {
            vo = getSelectedOrderVO();
        }catch (Exception e){
            return;
        }
        fillTable(vo);

    }

    /**
     * 执行退房操作的方法
     * 将订单状态从 executed 改为 complete
     */
    public void checkout(){
        OrderVO vo=getSelectedOrderVO();
        Order order = new Order(vo);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(timeCO.getText()==""){
            hotelManageMainController.alert("不能为空");
            return;
        }
        order.inDate.set(new Timestamp(System.currentTimeMillis()).toString());
        try{
            vo = toVO(order);
        }catch(Exception e){
            hotelManageMainController.alert("未按照格式输入数据");
            return;
        }
        if(vo.getState()==OrderState.executed){
            if(ResultMessage.SUCCESS== hotelManagerController.modifyState(vo.getOrderID(), OrderState.complete)){
                hotelManageMainController.alert("操作成功");
                hotelManageMainController.upDateHotelVO();
                showAllOrder();
            }else{
                hotelManageMainController.alert("操作失败");
            };
        }else{
            hotelManageMainController.alert("该订单不是已入住订单");
        }

    }

    /**
     * 执行延迟入住的方法
     */
    public void overtimeCheckin(){
        //TODO
        if (adultOC.getText().length() == 0 || kidOC.getText().length() == 0) {
            hotelManageMainController.alert("填写内容不能为空");
            return;
        }
        OrderVO vo=getSelectedOrderVO();
        Order order = new Order(vo);
        order.numOfCustomer.set(Integer.parseInt(adultOC.getText())+Integer.parseInt(kidOC.getText()));
        order.inDate.set(new Timestamp(System.currentTimeMillis()).toString());
        vo = toVO(order);
        if (vo.getState() == OrderState.abnormal) {
            if(ResultMessage.SUCCESS== hotelManagerController.modifyState(vo.getOrderID(),OrderState.executed)){
                hotelManageMainController.alert("操作成功");
                hotelManageMainController.upDateHotelVO();
                showAllOrder();
            }else{
                hotelManageMainController.alert("操作失败");
            }
        }else{
            hotelManageMainController.alert("该订单不是异常订单");
        }

    }

    /**
     * 将包装类Order转换为OrderVO的方法
     * @param order
     * @return
     */
    public static OrderVO toVO(Order order) {
        Timestamp indate=Timestamp.valueOf(order.getInDate());
        Timestamp outdate = Timestamp.valueOf(order.getOutDate());
        Timestamp finaldate = Timestamp.valueOf(order.getFinalDate());
        OrderVO vo=new OrderVO(
                order.getOrderId(),
                order.getCustomerId(),
                order.getHotelId(),
                order.getAmount(),
                indate,//inDate
                outdate,//oDate
                order.vo.getCreateTime(),
                finaldate,//finalDate
                order.getRoomNum(),
                order.vo.getType(),
                order.getNumOfCustomer(),
                order.getHaveKid(),
                order.vo.getState()
        );
        return vo;
    }


    /**
     * 用于列表显示时的包装类
     */
    public static class Order{
        private final SimpleIntegerProperty orderId;
        private final SimpleIntegerProperty customerId;
        private final SimpleIntegerProperty hotelId;
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
        private  OrderVO vo;

        public Order(OrderVO vo){
            orderId = new SimpleIntegerProperty(vo.getOrderID());
            customerId = new SimpleIntegerProperty(vo.getCustomerID());
            hotelId = new SimpleIntegerProperty(vo.getHotelID());
            amount = new SimpleDoubleProperty(vo.getAmount());

            //
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            inDate = new SimpleStringProperty(df.format(vo.getCheckInTime()));
            outDate = new SimpleStringProperty(df.format(vo.getCheckOutTime()));
            finalDate = new SimpleStringProperty(df.format(vo.getFinalExecuteTime()));
            createTime = new SimpleStringProperty(df.format(vo.getCreateTime()));

            //
            roomNum = new SimpleIntegerProperty(vo.getRoomSum());
            roomType = new SimpleStringProperty(vo.getType().toString());
            numOfCustomer = new SimpleIntegerProperty(vo.getNumOfCustomer());
            haveKid = new SimpleBooleanProperty(vo.isHaveChild());
            state = new SimpleStringProperty(vo.getState().toString());
            this.vo=vo;

        }
        public void refreshVO(){
            this.vo = toVO(this);
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

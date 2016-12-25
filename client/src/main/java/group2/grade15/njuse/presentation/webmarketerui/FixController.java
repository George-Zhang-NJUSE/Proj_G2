package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.bl.webmarketerbl.WebMarketerController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.WebMarketerServ;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ChangeReason;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;
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
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private ChoiceBox<String> creditRestore;
    @FXML
    private TextArea fixReason;
    @FXML
    private Label searchMessage;
    @FXML
    private Label searchButton;
    @FXML
    private TableView<Order> unsolvedList;
    @FXML
    private TableView<Order> unexecutedList;
    @FXML
    private Label refresh;

    private ObservableList<Order> unsolvedListData;
    private ObservableList<Order> unexecutedListData;
    private String[] properties = {"orderId", "customerId", "hotelId", "promotionId", "amount", "inDate", "outDate", "createTime", "finalDate", "RoomNum", "roomType"};

    public OrderListServ orderListServ=new OrderController();
    public WebMarketerServ webMarketerServ = new WebMarketerController();
    public WebMarketerMainController webMarketerMainController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //将各个按钮用的LABEL绑定上按钮的样式
        CustomeButton.implButton(check, "file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(clear, "file:client/src/main/res/webmarketer/Cancel");
        CustomeButton.implButton(refresh, "file:client/src/main/res/button/refresh");

        ObservableList unList=unsolvedList.getColumns();
        ObservableList sList = unexecutedList.getColumns();
        for(int i=0;i<properties.length;i++) {
            ((TableColumn)unList.get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            ((TableColumn)sList.get(i)).setCellValueFactory(new PropertyValueFactory<>(properties[i]));
        }
        unsolvedListData= FXCollections.observableArrayList();
        unexecutedListData=FXCollections.observableArrayList();

        unsolvedList.setItems(unsolvedListData);
        unexecutedList.setItems(unexecutedListData);

        unsolvedList.setOnMouseClicked((MouseEvent e)->{
            try {
                int index = unsolvedList.getSelectionModel().getSelectedIndex();
                if(index>=unsolvedListData.size())
                    return;
                openFromClick(unsolvedListData.get(index));
                check.setVisible(true);
                clear.setVisible(true);
            }catch (ArrayIndexOutOfBoundsException e2){
                return;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        unexecutedList.setOnMouseClicked((MouseEvent e)->{
            try {
                int index = unexecutedList.getSelectionModel().getSelectedIndex();
                openFromClick(unexecutedListData.get(index));
                check.setVisible(false);
                clear.setVisible(false);
            }catch (ArrayIndexOutOfBoundsException e2){
                return;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        searchButton.setOnMouseClicked((MouseEvent e)->{
            openFromID();
        });
        showAllOrder();
        creditRestore.setItems(FXCollections.observableArrayList(
                "全额恢复", "恢复一半"
        ));

    }

    /**
     * 获取所有的订单，并根据订单的状态分发到各个TableView
     */
    public void showAllOrder(){
         //这部分是用来添加异常订单部分的
        ArrayList<OrderVO> un=orderListServ.getAbnormalOrderList().getOrderList();

        for(int i=0;i<un.size();i++) {
            unsolvedListData.add(new Order(un.get(i)));
        }

        //下面是用来添加未执行订单部分的

        Date temp = new Date(System.currentTimeMillis());
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Date s = Date.valueOf(df2.format(temp));
        Date e = new Date(s.getTime() + 1000 * 60 * 60 * 24);
        if (orderListServ.getUnexecutedOrderListToday(s, e)==null) {
            return;
        }
        ArrayList<OrderVO> unexe = orderListServ.getUnexecutedOrderListToday(s, e).getOrderList();
        for (int i=0;i<unexe.size();i++) {
            unexecutedListData.add(new Order(unexe.get(i)));
        }
    }

    /**
     * 将下方信息栏清空
     */
    private void clear() {
        orderID.setText("");
        currentState.setText("");
        hotelID.setText("");
        prefCheckInTime.setText("");
        prefCheckOutTime.setText("");
        finalExeTime.setText("");
        creditRestore.setValue("全额恢复");
        fixReason.setText("");
        roomType.setText("");
        roomNum.setText("");

    }

    /**
     * 在下方信息栏中显示订单详情
     * 其中订单的是根据订单ID获取的
     */
    public void openFromID() {
        try {
            int id = Integer.parseInt(searchID.getText());

            ArrayList<OrderVO> list=orderListServ.getAbnormalOrderList().getOrderList();
                for(int i=0;i<list.size();i++) {
                    if (list.get(i).getOrderID() == id) {
                        openFromClick(new Order(list.get(i)));
                        return;
                    }
                }
            searchMessage.setText("未找到该订单");
            return;
        } catch (Exception e) {
            searchMessage.setText("输入ID无效");
            return;
        }
    }

    /**
     * 将作为映射数据类的Order显示到下方信息栏
     * @param order
     */
    public void openFromClick(Order order) {
        orderID.setText(String.valueOf(order.getOrderId()));
        currentState.setText(order.getState());
        hotelID.setText(String.valueOf(order.getHotelId()));
        roomType.setText(order.getRoomType());
        roomNum.setText(String.valueOf(order.getRoomNum()));
        prefCheckInTime.setText(order.getInDate());
        prefCheckOutTime.setText(order.getOutDate());
        finalExeTime.setText(order.getFinalDate());
    }

    /**
     * 从未修复异常订单列表中获取选中订单
     * @return
     */
    public OrderVO getSelectedOrderVO(){
        int index=unsolvedList.getSelectionModel().getSelectedIndex();
        return unsolvedListData.get(index).vo;
    }

    @FXML
    /**
     * 用于提交撤销异常订单
     */
    private void fixCommit(){
        try {
            OrderVO vo = getSelectedOrderVO();
        } catch (ArrayIndexOutOfBoundsException e) {
            webMarketerMainController.alert("未选中订单");
            return;
        }
        fixCommit(getSelectedOrderVO());
    }
    private void fixCommit(OrderVO vo) {
        if(ResultMessage.SUCCESS==WebMarketerMainController.webMarketerService.modifyState(vo.getOrderID(), OrderState.revoked)){
            double change=vo.getAmount();
            if (creditRestore.getValue() == "恢复一半") {
                change=change/2;
            }
            CreditVO creditVO = new CreditVO(vo.getCustomerID(), vo.getOrderID(), 0, 0, change, new Date(System.currentTimeMillis()), ChangeReason.orderCancelled);
            webMarketerServ.modifyCredit(creditVO);
            int index = unsolvedList.getSelectionModel().getSelectedIndex();

            unexecutedList.getItems().add((unsolvedListData.get(index)));
            unsolvedListData.remove(index);
            webMarketerMainController.alert("已经完成申述");
        }

    }

    /**
     * 映射数据的包装内部类
     */
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
        private OrderVO vo;

        public Order(OrderVO vo){
            orderId = new SimpleIntegerProperty(vo.getOrderID());
            customerId = new SimpleIntegerProperty(vo.getCustomerID());
            hotelId = new SimpleIntegerProperty(vo.getHotelID());
            promotionId = new SimpleIntegerProperty(vo.getPromotionID());
            amount = new SimpleDoubleProperty(vo.getAmount());

            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            inDate = new SimpleStringProperty(df.format(vo.getCheckInTime()));
            outDate = new SimpleStringProperty(df.format(vo.getCheckOutTime()));
            finalDate = new SimpleStringProperty(df.format(vo.getFinalExecuteTime()));
            createTime = new SimpleStringProperty(df.format(vo.getCreateTime()));


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

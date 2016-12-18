package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionController;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.bl.webpromotionbl.WebPromotionController;
import group2.grade15.njuse.blservice.HotelPromotionServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.OrderVO;
import group2.grade15.njuse.vo.RoomVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class MakeOrderController implements Initializable {

    private int hotelID;
    private Pane parentPane;
    private ArrayList<RoomVO> availableRoomList;//和roomTypeComboBox内容对应
    private int maxRoomNum, maxCustomerNum;
    private OrderServ orderServ;
    private OrderVO completedOrder;
    private boolean isValidOrder=false;

    @FXML
    private Node rootNode;

    @FXML
    private Label cancelLabel, confirmLabel, hotelNameLabel, addressLabel, minusRoomLabel, roomNumLabel, addRoomLabel,
            minusCustomerLabel, customerNumLabel, addCustomerLabel, promotionLabel, totalPriceLabel;

    @FXML
    private DatePicker checkInDatePicker, checkOutDatePicker;

    @FXML
    private ComboBox checkInHourComboBox, checkOutHourComboBox, roomTypeComboBox;

    @FXML
    private CheckBox hasChildCheckBox;

    @FXML
    private void close() {
        //退出动画
        Fade fadeOut = new Fade(rootNode, 200, false);
        Pop popOut = new Pop(rootNode, 200, false);
        fadeOut.setOnFinished((ActionEvent e) -> parentPane.getChildren().remove(rootNode));
        popOut.play();
        fadeOut.play();
    }

    private String getRoomInfo(RoomVO roomVO) {
        String roomType = LiteralList.roomTypeList[roomVO.getType().ordinal()];
        String price = Double.toString(roomVO.getPrice()) + "元/天";
        String spareNum = "剩" + Integer.toString(roomVO.getSpareRoomNum()) + "间";
        return roomType + " " + price + " " + spareNum;
    }

    @FXML
    private void minusRoomNum() {
        int actualRoomNum = Integer.parseInt(roomNumLabel.getText());
        if (actualRoomNum > 0) {
            actualRoomNum--;
            roomNumLabel.setText(Integer.toString(actualRoomNum));
        }

        updateOrderInfo();
    }

    @FXML
    private void minusCustomerNum() {
        int actualCustomerNum = Integer.parseInt(customerNumLabel.getText());
        if (actualCustomerNum > 0) {
            actualCustomerNum--;
            customerNumLabel.setText(Integer.toString(actualCustomerNum));
        }

        updateOrderInfo();
    }

    @FXML
    private void addRoomNum() {
        int actualRoomNum = Integer.parseInt(roomNumLabel.getText());
        if (actualRoomNum < maxRoomNum) {
            actualRoomNum++;
            roomNumLabel.setText(Integer.toString(actualRoomNum));
        }

        updateOrderInfo();
    }

    @FXML
    private void addCustomerNum() {
        int actualCustomerNum = Integer.parseInt(customerNumLabel.getText());
        if (actualCustomerNum < maxCustomerNum) {
            actualCustomerNum++;
            customerNumLabel.setText(Integer.toString(actualCustomerNum));
        }

        updateOrderInfo();
    }

    @FXML
    private void updateMaxNum() {
        RoomVO selectedRoom = availableRoomList.get(roomTypeComboBox.getSelectionModel().getSelectedIndex());
        maxRoomNum = selectedRoom.getSpareRoomNum();
        maxCustomerNum = maxRoomNum * 10;

        if (Integer.parseInt(roomNumLabel.getText()) > maxRoomNum) {
            roomNumLabel.setText(Integer.toString(maxRoomNum));
        }

        if (Integer.parseInt(customerNumLabel.getText()) > maxCustomerNum) {
            customerNumLabel.setText(Integer.toString(maxCustomerNum));
        }

        updateOrderInfo();
    }

    @FXML
    private void updateOrderInfo() { //每一次界面信息更新都被调用

        String checkInDateStr = checkInDatePicker.getEditor().getText();
        String checkOutDateStr = checkOutDatePicker.getEditor().getText();

        SimpleDateFormat dayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());

        try {

            if ( checkInDateStr.length()>0 && currentDate.compareTo(dayDateFormat.parse(checkInDateStr)) >= 0) {

                Alert tooEarlyDate = new Alert(Alert.AlertType.ERROR, "入住时间必须晚于今天！");
                tooEarlyDate.showAndWait();

            } else {

                if (checkInDateStr.length()>0 && checkOutDateStr.length()>0) {

                    if (checkOutDateStr.compareTo(checkInDateStr) > 0) {//退房日期必须晚于入住日期

                        Date checkInDateFull = null, checkOutDateFull = null;

                        //扩展至完全格式
                        String checkInTimeStr = (String) checkInHourComboBox.getValue();
                        checkInTimeStr = checkInTimeStr.substring(0, 2) + ":00:00";
                        String checkOutTimeStr = (String) checkOutHourComboBox.getValue();
                        checkOutTimeStr = checkOutTimeStr.substring(0, 2) + ":00:00";

                        //设置精确到小时的入住、退房时间
                        checkInDateStr = checkInDateStr + " " + checkInTimeStr;
                        checkOutDateStr = checkOutDateStr + " " + checkOutTimeStr;

                        SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        checkInDateFull = fullDateFormat.parse(checkInDateStr);
                        checkOutDateFull = fullDateFormat.parse(checkOutDateStr);

                        Timestamp checkInTimestamp = new Timestamp(checkInDateFull.getTime());
                        Timestamp checkOutTimestamp = new Timestamp(checkOutDateFull.getTime());
                        Timestamp createTimestamp = new Timestamp(System.currentTimeMillis());

                        //抓取其他信息
                        RoomType roomType = availableRoomList.get(roomTypeComboBox.getSelectionModel().getSelectedIndex()).getType();
                        int roomNum = Integer.parseInt(roomNumLabel.getText());
                        int customerNum = Integer.parseInt(customerNumLabel.getText());
                        boolean hasChild = hasChildCheckBox.isSelected();
                        int customerID = CommonData.getInstance().getCustomerVO().getId();

                        //获得总价及促销策略
                        completedOrder = orderServ.createOrder(new OrderVO(0, customerID, hotelID, 0, checkInTimestamp, checkOutTimestamp,
                                createTimestamp, null, roomNum, roomType, customerNum, hasChild, OrderState.unexecuted));
                        totalPriceLabel.setText(Double.toString(completedOrder.getAmount()));

                        int promotionID = completedOrder.getPromotionID();

                        if (promotionID != 0) {
                            if (promotionID % 2 == 0) {//是酒店促销策略
                                HotelPromotionServ hotelPromotionServ = new HotelPromotionController();
                                promotionLabel.setText(hotelPromotionServ.getHotelPromotion(hotelID,promotionID).getName());
                            }else{//是网站促销策略
                                WebPromotionServ webPromotionServ = new WebPromotionController();
                                promotionLabel.setText(webPromotionServ.getWebPromotion(promotionID).getName());
                            }
                        }else{//没有享受任何促销策略
                            promotionLabel.setText("本订单没有享受优惠");
                        }

                        isValidOrder=true;

                    } else {
                        Alert incorrectTimeAlert = new Alert(Alert.AlertType.ERROR, "退房日期必须晚于入住日期！");
                        incorrectTimeAlert.showAndWait();
                    }

                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void submitOrder() {

        if (totalPriceLabel.getText().equals("0") || customerNumLabel.getText().equals("0") || roomNumLabel.getText().equals("0")) {
            Alert uncompletedInfoAlert = new Alert(Alert.AlertType.ERROR, "请完善订单信息！");
            uncompletedInfoAlert.showAndWait();
        }else if(isValidOrder){
            ResultMessage result = orderServ.saveOrder(completedOrder);
            switch (result) {
                case SUCCESS:
                    Alert successInfo=new Alert(Alert.AlertType.INFORMATION,"提交成功！");
                    successInfo.setOnCloseRequest((DialogEvent e)->close());
                    successInfo.showAndWait();
                    break;
                case FAILED:
                    Alert failInfo = new Alert(Alert.AlertType.ERROR, "由于服务器的原因，提交失败！");
                    failInfo.showAndWait();
                    break;
                case CONNECTION_EXCEPTION:
                    Alert netError = new Alert(Alert.AlertType.ERROR, "网络连接出现错误，提交失败！");
                    netError.showAndWait();
                    break;
            }
        }else {
            Alert wrongDateAlert = new Alert(Alert.AlertType.ERROR, "请填写正确的日期！");
            wrongDateAlert.showAndWait();
        }
    }


    public void initData(int hotelID, String hotelName, String address, ArrayList<RoomVO> roomList) {

        this.hotelID = hotelID;
        hotelNameLabel.setText(hotelName);
        addressLabel.setText(address);

        //设置可订房间列表
        availableRoomList = new ArrayList<>();
        for (RoomVO roomVO : roomList) {
            if (roomVO.getSpareRoomNum() > 0) {
                availableRoomList.add(roomVO);
            }
        }

        ArrayList<String> roomInfoList = new ArrayList<>();
        for (RoomVO spareRoomVO : availableRoomList) {
            roomInfoList.add(getRoomInfo(spareRoomVO));
        }

        roomTypeComboBox.setItems(FXCollections.observableArrayList(roomInfoList));
        roomTypeComboBox.getSelectionModel().select(0);
        updateMaxNum();
    }

    public void show() {
        //弹出式进入动画
        Fade fadeIn = new Fade(rootNode, 200, true);
        Pop popIn = new Pop(rootNode, 200, true);

        rootNode.setVisible(true);
        fadeIn.play();
        popIn.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        orderServ = new OrderController();

        //加载非联网数据
        checkInHourComboBox.setItems(FXCollections.observableArrayList(LiteralList.hourList));
        checkInHourComboBox.getSelectionModel().select(0);
        checkOutHourComboBox.setItems(FXCollections.observableArrayList(LiteralList.hourList));
        checkOutHourComboBox.getSelectionModel().select(0);

        //加载按钮变化样式
        CustomeButton.implButton(cancelLabel, "file:client/src/main/res/customer/cancel");
        CustomeButton.implButton(confirmLabel, "file:client/src/main/res/customer/confirm");
        CustomeButton.implButton(minusRoomLabel, "file:client/src/main/res/order/reduce");
        CustomeButton.implButton(minusCustomerLabel, "file:client/src/main/res/order/reduce");
        CustomeButton.implButton(addRoomLabel, "file:client/src/main/res/order/add");
        CustomeButton.implButton(addCustomerLabel, "file:client/src/main/res/order/add");

        //为弹出式动画做准备
        rootNode.setVisible(false);
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.5);
        rootNode.setScaleY(0.5);
        rootNode.setLayoutX(300);
        rootNode.setLayoutY(50);

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);
    }
}

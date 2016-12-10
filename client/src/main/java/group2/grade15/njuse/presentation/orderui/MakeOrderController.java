package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.RoomVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class MakeOrderController implements Initializable {

    private int hotelID;
    private Pane parentPane;
    private ArrayList<RoomVO> availableRoomList;
    private int maxRoomNum, maxCustomerNum;

    @FXML
    private Node rootNode;

    @FXML
    private Label cancelLabel, confirmLabel, hotelNameLabel, addressLabel, minusRoomLabel, roomNumLabel, addRoomLabel,
            minusCustomerLabel, customerNumLabel, addCustomerLabel, promotionLabel, totalPriceLabel;

    @FXML
    private DatePicker checkInDatePicker, checkOutDatePicker;

    @FXML
    private ComboBox CheckInHourComboBox, checkOutHourComboBox, roomTypeComboBox;

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
    }

    @FXML
    private void minusCustomerNum() {
        int actualCustomerNum = Integer.parseInt(customerNumLabel.getText());
        if (actualCustomerNum > 0) {
            actualCustomerNum--;
            customerNumLabel.setText(Integer.toString(actualCustomerNum));
        }
    }

    @FXML
    private void addRoomNum() {
        int actualRoomNum = Integer.parseInt(roomNumLabel.getText());
        if (actualRoomNum < maxRoomNum) {
            actualRoomNum++;
            roomNumLabel.setText(Integer.toString(actualRoomNum));
        }
    }

    @FXML
    private void addCustomerNum() {
        int actualCustomerNum = Integer.parseInt(customerNumLabel.getText());
        if (actualCustomerNum < maxCustomerNum) {
            actualCustomerNum++;
            customerNumLabel.setText(Integer.toString(actualCustomerNum));
        }
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
    }

    @FXML
    private void getTotalPrice() {
        // TODO: 2016/12/10  
    }


    public void initData(int hotelID, String hotelName, String address, ArrayList<RoomVO> roomList) {

        this.hotelID = hotelID;
        hotelNameLabel.setText(hotelName);
        addressLabel.setText(address);

        roomNumLabel.setText("0");
        customerNumLabel.setText("0");
        totalPriceLabel.setText("0");

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

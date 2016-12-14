package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class HotelItemController implements Initializable {

    private HotelVO hotelVO;
    private OrderListServ orderListServ;
    private ArrayList<OrderVO> myOrders;

    @FXML
    private Node rootNode;

    @FXML
    private ImageView hotelImageView;

    @FXML
    private Label showDetailLabel, makeOrderLabel, hotelNameLabel, starLabel, addressLabel, scoreLabel,
                unexecutedNumLabel, executedNumLabel, revokedNumLabel, abnormalNumLabel;

    @FXML
    private void showHotelDetail() {
        try {
            FXMLLoader hotelDetailLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/HotelDetail.fxml"));
            hotelDetailLoader.load();
            HotelDetailController detailController = hotelDetailLoader.getController();
            detailController.initDataAndShow(hotelVO, myOrders);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/MakeOrder.fxml"));
            makeOrderLoader.load();

            MakeOrderController makeOrderController = makeOrderLoader.getController();
            makeOrderController.initData(hotelVO.getId(),hotelVO.getName(),hotelVO.getConcreteAddress(),hotelVO.getRoomList());
            makeOrderController.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initData(HotelVO vo) {
        if (vo != null) {
            hotelVO=vo;
            orderListServ = new OrderController();

            int customerID = CommonData.getInstance().getCustomerVO().getId();
            int hotelID = hotelVO.getId();
            ArrayList<OrderVO> unexecutedOrders = orderListServ.getUnexecutedOrderListInHotel(customerID, hotelID).getOrderList();
            ArrayList<OrderVO> executedOrders = orderListServ.getExecutedOrderListInHotel(customerID, hotelID).getOrderList();
            ArrayList<OrderVO> revokedOrders = orderListServ.getRevokedOrderListInHotel(customerID, hotelID).getOrderList();
            ArrayList<OrderVO> abnormalOrders = orderListServ.getAbnormalOrderList(customerID, hotelID).getOrderList();

            unexecutedNumLabel.setText("未执行*" + unexecutedOrders.size());
            executedNumLabel.setText("已执行*" + executedOrders.size());
            revokedNumLabel.setText("已撤销*" + revokedOrders.size());
            abnormalNumLabel.setText("异常*" + abnormalOrders.size());

            myOrders = new ArrayList<>();
            myOrders.addAll(unexecutedOrders);
            myOrders.addAll(executedOrders);
            myOrders.addAll(revokedOrders);
            myOrders.addAll(abnormalOrders);

            hotelImageView.setImage(new Image(new ByteArrayInputStream(hotelVO.getPicture()[0])));
            hotelNameLabel.setText(hotelVO.getName());
            starLabel.setText(Integer.toString(hotelVO.getRank()));
            addressLabel.setText(hotelVO.getConcreteAddress());
            scoreLabel.setText(Double.toString(hotelVO.getScore()));

        }else{
            System.out.println("没有联网数据");
        }
    }

    public void show() {
        Fade fadeIn = new Fade(rootNode, 300, true);
        fadeIn.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //加载按钮变化样式
        CustomeButton.implButton(showDetailLabel, "file:client/src/main/res/customer/more");
        CustomeButton.implButton(makeOrderLabel, "file:client/src/main/res/search/makeorder");

        //为渐入动画做准备
        rootNode.setOpacity(0);

    }
}

package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderListVO;
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
import java.text.DecimalFormat;
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

            OrderListVO unexecutedListVO = orderListServ.getUnexecutedOrderListInHotel(customerID, hotelID);
            OrderListVO executedListVO = orderListServ.getExecutedOrderListInHotel(customerID, hotelID);
            OrderListVO revokedListVO = orderListServ.getRevokedOrderListInHotel(customerID, hotelID);
            OrderListVO abnormalListVO = orderListServ.getAbnormalOrderList(customerID, hotelID);

            myOrders = new ArrayList<>();

            if(unexecutedListVO!=null){
                ArrayList<OrderVO> unexecutedOrders = unexecutedListVO.getOrderList();
                unexecutedNumLabel.setText("未执行*" + unexecutedOrders.size());
                myOrders.addAll(unexecutedOrders);
            }

            if (executedListVO != null) {
                ArrayList<OrderVO> executedOrders = executedListVO.getOrderList();
                executedNumLabel.setText("已执行*" + executedOrders.size());
                myOrders.addAll(executedOrders);
            }

            if (revokedListVO != null) {
                ArrayList<OrderVO> revokedOrders = revokedListVO.getOrderList();
                revokedNumLabel.setText("已撤销*" + revokedOrders.size());
                myOrders.addAll(revokedOrders);
            }

            if (abnormalListVO != null) {
                ArrayList<OrderVO> abnormalOrders = abnormalListVO.getOrderList();
                abnormalNumLabel.setText("异常*" + abnormalOrders.size());
                myOrders.addAll(abnormalOrders);
            }

            if (hotelVO.getPicture() != null) {
                hotelImageView.setImage(new Image(new ByteArrayInputStream(hotelVO.getPicture()[0])));
            } else {
                hotelImageView.setImage(new Image("file:client/src/main/res/customer/defaultimage.png"));
            }

            hotelNameLabel.setText(hotelVO.getName());
            starLabel.setText(Integer.toString(hotelVO.getRank()));
            addressLabel.setText(hotelVO.getConcreteAddress());

            DecimalFormat scoreFormat=new DecimalFormat("0.00");
            scoreLabel.setText(scoreFormat.format(hotelVO.getScore()));

        }else{
            System.out.println("没有联网数据");
        }
    }

    public void show() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);

        fadeIn.play();
        popIn.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //加载按钮变化样式
        CustomeButton.implButton(showDetailLabel, "file:client/src/main/res/customer/more");
        CustomeButton.implButton(makeOrderLabel, "file:client/src/main/res/search/makeorder");

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

    }
}

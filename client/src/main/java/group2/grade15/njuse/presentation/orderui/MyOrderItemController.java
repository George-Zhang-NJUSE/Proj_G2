package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.bl.hotelbl.HotelController;
import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderItemController implements Initializable{

    private OrderVO orderVO;
    private HotelVO hotelVO;

    @FXML
    private Node rootNode;

    @FXML
    private Label showDetailLabel, orderIDLabel, priceLabel, createTimeLabel, hotelNameLabel, addressLabel, orderStateLabel;

    @FXML
    private HBox hotelInfoBox;

    @FXML
    private void showMyOrderDetail() {
        try {
            FXMLLoader orderDetailLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/MyOrderDetail.fxml"));
            orderDetailLoader.load();
            MyOrderDetailController orderDetailController = orderDetailLoader.getController();

            orderDetailController.initData(orderVO, hotelNameLabel.getText(), addressLabel.getText(), hotelVO.getId());
            orderDetailController.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData(OrderVO vo) {

        HotelServ hotelServ = new HotelController();
        orderVO = vo;
        orderIDLabel.setText(Integer.toString(orderVO.getOrderID()));
        priceLabel.setText(Double.toString(orderVO.getAmount()));

        createTimeLabel.setText(orderVO.getCreateTime().toString().split("\\.")[0]);

        hotelVO=hotelServ.getInfo(orderVO.getHotelID());
        hotelNameLabel.setText(hotelVO.getName());
        addressLabel.setText(hotelVO.getConcreteAddress());
        orderStateLabel.setText(LiteralList.orderStateList[orderVO.getState().ordinal()]);

    }

    private void adaptToActualWidth() {
        //宽度太窄时不显示部分内容
        Pane container= (Pane)rootNode.getParent();
        if (container.getWidth() < 900) {
            hotelInfoBox.setVisible(false);
            hotelInfoBox.setPrefWidth(0);
        }
    }

    public void show() {
        adaptToActualWidth();
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

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

    }
}

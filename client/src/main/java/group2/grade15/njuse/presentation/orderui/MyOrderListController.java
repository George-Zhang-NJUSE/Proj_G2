package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderListController implements Initializable{

    private boolean initialized=false;
    private OrderListVO allOrderListVO, unexecutedOrderListVO,executedOrderListVO, revokedOrderListVO, abnormalOrderListVO;

    @FXML
    private Node rootNode;

    @FXML
    private VBox allOrderBox,unexecutedOrderBox, executedOrderBox, revokedOrderBox, abnormalOrderBox;

    @FXML
    private void showAllOrder() {
        if (initialized) {  //在loader.load()的过程中被调用时，不加载子界面
            fillOrderItem(allOrderBox,allOrderListVO);
        }
    }

    @FXML
    private void showUnexecutedOrder() {
        fillOrderItem(unexecutedOrderBox,unexecutedOrderListVO);
    }

    @FXML
    private void showExecutedOrder() {
        fillOrderItem(executedOrderBox,executedOrderListVO);
    }

    @FXML
    private void showRevokedOrder() {
        fillOrderItem(revokedOrderBox,revokedOrderListVO);
    }

    @FXML
    private void showAbnormalOrder() {
        fillOrderItem(abnormalOrderBox,abnormalOrderListVO);
    }

    private void showThePane() {
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        popIn.setOnFinished((ActionEvent e) -> showAllOrder());
        fadeIn.play();
        popIn.play();
    }

    public void initDataAndShow() {
        OrderListServ orderListServ = new OrderController();
        int customerID = CommonData.getInstance().getCustomerVO().getId();

        allOrderListVO = orderListServ.getAllOrderListByCustomerID(customerID);
        unexecutedOrderListVO = orderListServ.getUnexecutedOrderListByCustomerID(customerID);
        executedOrderListVO = orderListServ.getExecutedOrderListByCustomerID(customerID);
        revokedOrderListVO = orderListServ.getRevokedOrderListByCustomerID(customerID);
        abnormalOrderListVO = orderListServ.getAbnormalOrderListByCustomerID(customerID);

        showThePane();
    }

    private void fillOrderItem(VBox container, OrderListVO listVO) {
        container.getChildren().clear();

        if (listVO != null) {
            ArrayList<OrderVO> orderList = listVO.getOrderList();
            ArrayList<MyOrderItemController> controllerList = new ArrayList<>();

            try {

                for (OrderVO orderVO : orderList) {
                    FXMLLoader orderItemLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderItem.fxml"));
                    Node singleItem = orderItemLoader.load();

                    MyOrderItemController orderItemController = orderItemLoader.getController();

                    container.getChildren().add(singleItem);
                    orderItemController.initData(orderVO);
                    controllerList.add(orderItemController);
                }

                for (MyOrderItemController controller : controllerList) {
                    controller.show();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);
    }

}

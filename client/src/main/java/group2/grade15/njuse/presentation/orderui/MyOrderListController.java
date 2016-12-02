package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderListController {

    private Pane parentPane;

    @FXML
    private VBox allOrderBox,unexecutedOrderBox, revokedOrderBox, abnormalOrderBox;

    @FXML
    protected void showAllOrder() {

    }

    @FXML
    protected void showUnexecutedOrder() {

    }

    @FXML
    protected void showExecutedOrder() {

    }

    @FXML
    protected void showRevokedOrder() {

    }

    @FXML
    protected void showAbnormalOrder() {

    }

    private void fillOrderItem(VBox container, OrderListVO listVO) {
        container.getChildren().clear();
        ArrayList<Node> itemList = new ArrayList<>();

        if (listVO != null) {
            ArrayList<OrderVO> orderList=listVO.getOrderList();
            for (OrderVO orderVO : orderList) {

            }
        }

    }



    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }
}

package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        try {
            allOrderBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            // TODO: 2016/12/2 需要更改为正确的逻辑
            for(int i=0;i<15;++i) {
                FXMLLoader orderItemLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderItem.fxml"));
                Node singleItemTemplate=orderItemLoader.load();
                MyOrderItemController orderItemController = orderItemLoader.getController();
                orderItemController.setParentPane(parentPane);
                ItemList.add(singleItemTemplate);
            }

            allOrderBox.getChildren().addAll(ItemList);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

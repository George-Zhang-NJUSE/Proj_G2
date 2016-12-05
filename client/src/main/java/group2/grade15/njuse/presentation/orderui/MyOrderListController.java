package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
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

    private Pane parentPane;

    @FXML
    private Node rootNode;

    @FXML
    private VBox allOrderBox, unexecutedOrderBox, revokedOrderBox, abnormalOrderBox;

    @FXML
    protected void showAllOrder() {

        if (parentPane != null) {  //在loader.load()的过程中被调用时，不加载子界面
            try {
                allOrderBox.getChildren().clear();


                // TODO: 2016/12/2 需要更改为正确的逻辑
                for (int i = 0; i < 15; ++i) {
                    FXMLLoader orderItemLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderItem.fxml"));
                    Node singleItemTemplate = orderItemLoader.load();

                    MyOrderItemController orderItemController = orderItemLoader.getController();
                    orderItemController.setParentPane(parentPane);

                    allOrderBox.getChildren().add(singleItemTemplate);
                    orderItemController.initDataAndShow(null);
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void initDataAndShow() {
        // TODO: 2016/12/5  对传入的数据进行处理




        showThePane();
    }

    private void fillOrderItem(VBox container, OrderListVO listVO) {
        container.getChildren().clear();
        ArrayList<Node> itemList = new ArrayList<>();

        if (listVO != null) {
            ArrayList<OrderVO> orderList = listVO.getOrderList();
            for (OrderVO orderVO : orderList) {

            }
        }

    }


    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //为渐入动画做准备
        rootNode.setOpacity(0);

    }

    private void showThePane() {
        Fade fadeIn = new Fade(rootNode, 300, true);
        fadeIn.setOnFinished((ActionEvent e) -> showAllOrder());
        fadeIn.play();
    }
}

package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderItemController {

    private Pane parentPane;
    private OrderVO orderVO;


    @FXML
    protected void showMyOrderDetail() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderDetail.fxml"));
            parentPane.getChildren().add(loader.load());
            MyOrderDetailController orderDetailController = loader.getController();

            orderDetailController.setParentPane(parentPane);
            orderDetailController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData(OrderVO vo) {
        orderVO = vo;

    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }
}
